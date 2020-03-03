package com.xiaomi.smarthome.listcamera;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import com.mijia.camera.CameraNativePluginManager;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.camera.MessageReceiver;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.IXmPluginMessageReceiver;
import com.xiaomi.smarthome.fastvideo.VideoView;
import com.xiaomi.smarthome.fastvideo.decoder.H264Decoder;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.listcamera.receiver.LocalSocketReceiver;
import com.xiaomi.smarthome.listcamera.receiver.Receiver;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.module.blur.StackBlurManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CameraFrameManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19223a = 2;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    private static CameraFrameManager f = null;
    private static final String g = "camera_frame_manager";
    private static final int h = 2;
    private static final int i = 1;
    private static final int j = 3;
    private static final int k = 4;
    private static final int l = 5;
    /* access modifiers changed from: private */
    public ConcurrentMap<String, FrameContext> m = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public ConcurrentMap<String, VideoFrame> n = new ConcurrentHashMap();
    private HandlerThread o = new MessageHandlerThread(g);
    private int p = 2;
    /* access modifiers changed from: private */
    public Handler q;
    /* access modifiers changed from: private */
    public Handler r = new Handler(Looper.getMainLooper());
    private HashMap<String, IXmPluginMessageReceiver> s = new HashMap<>();

    public interface CameraBitmapCallback {
        void onBitmapLoaded(Bitmap bitmap, long j);
    }

    public static CameraFrameManager a() {
        if (f == null) {
            f = new CameraFrameManager();
        }
        return f;
    }

    private class ThreadHandler extends Handler {
        ThreadHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    for (String str : CameraFrameManager.this.n.keySet()) {
                        CameraFrameManager.this.a(str, (VideoFrame) CameraFrameManager.this.n.get(str));
                    }
                    CameraFrameManager.this.n.clear();
                    return;
                case 2:
                    FrameContext frameContext = (FrameContext) message.obj;
                    VideoFrame a2 = CameraFrameManager.this.c(frameContext.f19224a);
                    if (a2 != null) {
                        CameraFrameManager.this.n.put(frameContext.f19224a, a2);
                        CameraFrameManager.this.q.sendMessage(CameraFrameManager.this.q.obtainMessage(4, frameContext));
                        return;
                    }
                    return;
                case 4:
                    Log.e(CameraFrameManager.g, "start Load Cache");
                    final FrameContext frameContext2 = (FrameContext) message.obj;
                    VideoFrame videoFrame = (VideoFrame) CameraFrameManager.this.n.get(frameContext2.f19224a);
                    if (videoFrame == null) {
                        videoFrame = CameraFrameManager.this.c(frameContext2.f19224a);
                    }
                    if (videoFrame == null || videoFrame.width <= 0 || videoFrame.height <= 0) {
                        frameContext2.d.post(new Runnable() {
                            public void run() {
                            }
                        });
                        return;
                    }
                    final Bitmap createBitmap = Bitmap.createBitmap(videoFrame.width, videoFrame.height, Bitmap.Config.RGB_565);
                    createBitmap.copyPixelsFromBuffer(IntBuffer.allocate(videoFrame.width * videoFrame.height));
                    if (H264Decoder.decodeIFrame(videoFrame.data, videoFrame.data.length, videoFrame.width, videoFrame.height, videoFrame.videoType, createBitmap) == 0 && frameContext2.d != null) {
                        CameraFrameManager.this.r.post(new Runnable() {
                            public void run() {
                                if (frameContext2 == null) {
                                    return;
                                }
                                if (!frameContext2.i || !frameContext2.f) {
                                    frameContext2.d.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                    frameContext2.d.setImageBitmap(DisplayUtils.a(CommonApplication.getAppContext(), createBitmap, 0.5f, 15));
                                    frameContext2.d.setVisibility(0);
                                    return;
                                }
                                frameContext2.d.setVisibility(8);
                            }
                        });
                        return;
                    }
                    return;
                case 5:
                    FrameContext frameContext3 = (FrameContext) message.obj;
                    VideoFrame videoFrame2 = (VideoFrame) CameraFrameManager.this.n.get(frameContext3.f19224a);
                    if (videoFrame2 == null) {
                        videoFrame2 = CameraFrameManager.this.c(frameContext3.f19224a);
                    }
                    if (videoFrame2 != null) {
                        long j = videoFrame2.timeStamp;
                        Bitmap createBitmap2 = Bitmap.createBitmap(videoFrame2.width, videoFrame2.height, Bitmap.Config.RGB_565);
                        createBitmap2.copyPixelsFromBuffer(IntBuffer.allocate(videoFrame2.width * videoFrame2.height));
                        if (H264Decoder.decodeIFrame(videoFrame2.data, videoFrame2.data.length, videoFrame2.width, videoFrame2.height, videoFrame2.videoType, createBitmap2) == 0) {
                            Point a3 = DisplayUtils.a(CommonApplication.getAppContext());
                            Bitmap createBitmap3 = Bitmap.createBitmap(a3.x, a3.y, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(createBitmap3);
                            int width = (createBitmap3.getWidth() * createBitmap3.getHeight()) / createBitmap2.getHeight();
                            canvas.drawBitmap(createBitmap2, new Rect(0, 0, createBitmap2.getWidth(), createBitmap2.getHeight()), new Rect((-(width - createBitmap3.getWidth())) / 2, 0, (width + createBitmap3.getWidth()) / 2, createBitmap3.getHeight()), new Paint(1));
                            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(createBitmap3, createBitmap3.getWidth() / 5, createBitmap3.getHeight() / 5, false);
                            new StackBlurManager().a(createScaledBitmap, 5);
                            CameraFrameManager.this.r.post(new Runnable(createScaledBitmap, j) {
                                private final /* synthetic */ Bitmap f$1;
                                private final /* synthetic */ long f$2;

                                {
                                    this.f$1 = r2;
                                    this.f$2 = r3;
                                }

                                public final void run() {
                                    CameraFrameManager.ThreadHandler.a(CameraFrameManager.FrameContext.this, this.f$1, this.f$2);
                                }
                            });
                            return;
                        }
                        CameraFrameManager.this.r.post(new Runnable() {
                            public final void run() {
                                CameraFrameManager.ThreadHandler.b(CameraFrameManager.FrameContext.this);
                            }
                        });
                        return;
                    }
                    CameraFrameManager.this.r.post(new Runnable() {
                        public final void run() {
                            CameraFrameManager.ThreadHandler.a(CameraFrameManager.FrameContext.this);
                        }
                    });
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void a(FrameContext frameContext, Bitmap bitmap, long j) {
            if (frameContext.l != null) {
                frameContext.l.onBitmapLoaded(bitmap, j);
            }
            frameContext.l = null;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void b(FrameContext frameContext) {
            if (frameContext.l != null) {
                frameContext.l.onBitmapLoaded((Bitmap) null, 0);
            }
            frameContext.l = null;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void a(FrameContext frameContext) {
            if (frameContext.l != null) {
                frameContext.l.onBitmapLoaded((Bitmap) null, 0);
            }
            frameContext.l = null;
        }
    }

    public class FrameContext {

        /* renamed from: a  reason: collision with root package name */
        String f19224a;
        VideoView b;
        ImageView c;
        ImageView d;
        Receiver e;
        boolean f;
        boolean g = true;
        public boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        CameraBitmapCallback l;

        FrameContext(String str, Receiver receiver, boolean z) {
            this.f19224a = str;
            this.e = receiver;
            this.f = false;
            this.h = z;
        }

        public void a(VideoView videoView) {
            this.b = videoView;
        }

        public void a(CameraBitmapCallback cameraBitmapCallback) {
            this.l = cameraBitmapCallback;
            CameraFrameManager.this.q.sendMessage(CameraFrameManager.this.q.obtainMessage(5, this));
        }

        public void a(Device device, ImageView imageView) {
            this.d = imageView;
            if (device.isOnline) {
                if (this.i && this.f) {
                    return;
                }
                if (CameraFrameManager.this.n.get(this.f19224a) != null) {
                    CameraFrameManager.this.q.sendMessage(CameraFrameManager.this.q.obtainMessage(4, this));
                    return;
                }
                CameraFrameManager cameraFrameManager = CameraFrameManager.this;
                if (cameraFrameManager.b("cache_frame_" + this.f19224a).exists()) {
                    CameraFrameManager.this.q.sendMessage(CameraFrameManager.this.q.obtainMessage(2, this));
                }
            }
        }

        public void b(VideoView videoView) {
            if (this.b != null && this.b.equals(videoView)) {
                this.b = null;
            }
        }

        public boolean a() {
            return this.i;
        }

        public boolean b() {
            return this.g;
        }
    }

    private CameraFrameManager() {
        this.o.start();
        this.q = new ThreadHandler(this.o.getLooper());
    }

    public FrameContext a(String str) {
        return (FrameContext) this.m.get(str);
    }

    public void a(Context context) {
        PluginRecord d2;
        try {
            for (String str : this.m.keySet()) {
                FrameContext a2 = a(str);
                if (!a2.h) {
                    Device b2 = SmartHomeDeviceManager.a().b(str);
                    if (a2 != null) {
                        a2.e.g();
                    }
                    if (!(b2 == null || (d2 = CoreApi.a().d(b2.model)) == null || !d2.l())) {
                        Intent intent = new Intent();
                        intent.putExtra("run_on_main", a2.h);
                        a(context, b2, 21, intent);
                    }
                    this.m.remove(str);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.q.sendEmptyMessage(1);
    }

    public boolean a(Device device) {
        FrameContext a2;
        if (device == null || (a2 = a(device.did)) == null) {
            return false;
        }
        return a2.i;
    }

    public boolean b(Device device) {
        FrameContext a2;
        if (device != null && (a2 = a(device.did)) != null && a2.i && a2.f) {
            return true;
        }
        return false;
    }

    public boolean b() {
        int i2 = 0;
        for (FrameContext frameContext : this.m.values()) {
            if (frameContext != null && frameContext.i) {
                i2++;
            }
        }
        if (i2 >= 3) {
            return true;
        }
        return false;
    }

    public boolean c(Device device) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            return a2.b();
        }
        return false;
    }

    public int c() {
        int i2 = 0;
        for (Map.Entry value : this.m.entrySet()) {
            FrameContext frameContext = (FrameContext) value.getValue();
            if (frameContext != null && frameContext.i) {
                i2++;
            }
        }
        return i2;
    }

    private static class MyReceiverCallback implements Receiver.Callback {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<CameraFrameManager> f19225a;

        public MyReceiverCallback(CameraFrameManager cameraFrameManager) {
            this.f19225a = new WeakReference<>(cameraFrameManager);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
            r1 = (com.xiaomi.smarthome.listcamera.CameraFrameManager.FrameContext) com.xiaomi.smarthome.listcamera.CameraFrameManager.d(r0).get(r4);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.String r4, com.xiaomi.smarthome.camera.VideoFrame r5) {
            /*
                r3 = this;
                java.lang.ref.WeakReference<com.xiaomi.smarthome.listcamera.CameraFrameManager> r0 = r3.f19225a
                java.lang.Object r0 = r0.get()
                com.xiaomi.smarthome.listcamera.CameraFrameManager r0 = (com.xiaomi.smarthome.listcamera.CameraFrameManager) r0
                if (r0 != 0) goto L_0x000b
                return
            L_0x000b:
                java.util.concurrent.ConcurrentMap r1 = r0.m
                java.lang.Object r1 = r1.get(r4)
                com.xiaomi.smarthome.listcamera.CameraFrameManager$FrameContext r1 = (com.xiaomi.smarthome.listcamera.CameraFrameManager.FrameContext) r1
                if (r1 == 0) goto L_0x0044
                boolean r2 = r1.i
                if (r2 == 0) goto L_0x0044
                boolean r2 = r5.isIFrame
                if (r2 == 0) goto L_0x0026
                java.util.concurrent.ConcurrentMap r0 = r0.n
                r0.put(r4, r5)
            L_0x0026:
                com.xiaomi.smarthome.fastvideo.VideoView r4 = r1.b
                if (r4 == 0) goto L_0x002f
                com.xiaomi.smarthome.fastvideo.VideoView r4 = r1.b
                r4.drawVideoFrame(r5)
            L_0x002f:
                boolean r4 = r1.f
                if (r4 != 0) goto L_0x0044
                r4 = 1
                r1.f = r4
                android.widget.ImageView r4 = r1.c
                if (r4 == 0) goto L_0x0044
                android.widget.ImageView r4 = r1.c
                com.xiaomi.smarthome.listcamera.CameraFrameManager$MyReceiverCallback$1 r5 = new com.xiaomi.smarthome.listcamera.CameraFrameManager$MyReceiverCallback$1
                r5.<init>(r1)
                r4.post(r5)
            L_0x0044:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.listcamera.CameraFrameManager.MyReceiverCallback.a(java.lang.String, com.xiaomi.smarthome.camera.VideoFrame):void");
        }
    }

    public void a(Device device, boolean z) {
        LocalSocketReceiver localSocketReceiver = new LocalSocketReceiver(device.did);
        localSocketReceiver.a(new MyReceiverCallback(this));
        localSocketReceiver.f();
        this.m.put(device.did, new FrameContext(device.did, localSocketReceiver, z));
    }

    public void d(Device device) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            a2.g = !a2.g;
        }
    }

    public void e(Device device) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            a2.g = true;
        }
    }

    public void f(Device device) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            PluginRecord d2 = CoreApi.a().d(device.model);
            if (d2 != null && d2.l()) {
                Intent intent = new Intent();
                intent.putExtra("run_on_main", a2.h);
                a(CommonApplication.getAppContext(), device, 21, intent);
            }
            a2.e.g();
            this.m.remove(device.did);
        }
    }

    public void a(Device device, VideoView videoView) {
        FrameContext a2;
        if (device != null && (a2 = a(device.did)) != null) {
            a2.a(videoView);
        }
    }

    public void a(Device device, ImageView imageView, ImageView imageView2) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            a2.a(device, imageView);
            a2.c = imageView2;
            if (imageView != null && device.isOnline) {
                imageView.setVisibility(0);
            }
            if (a2 != null && a2.i && !a2.f && a2.c != null) {
                a2.c.setVisibility(0);
                AnimationDrawable animationDrawable = (AnimationDrawable) CommonApplication.getAppContext().getResources().getDrawable(R.drawable.camera_loading);
                a2.c.setImageDrawable(animationDrawable);
                animationDrawable.start();
            }
        }
    }

    public void a(Device device, CameraBitmapCallback cameraBitmapCallback) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            a2.a(cameraBitmapCallback);
        } else if (cameraBitmapCallback != null) {
            cameraBitmapCallback.onBitmapLoaded((Bitmap) null, 0);
        }
    }

    public void b(Device device, VideoView videoView) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            a2.b(videoView);
        }
    }

    public void a(Context context, Device device) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            Intent intent = new Intent();
            intent.putExtra("run_on_main", a2.h);
            a(context, device, 20, intent);
            a2.i = false;
            a2.f = false;
            if (a2.c != null) {
                Drawable drawable = a2.c.getDrawable();
                if (drawable != null && (drawable instanceof AnimationDrawable)) {
                    ((AnimationDrawable) drawable).stop();
                }
                a2.c.setVisibility(8);
            }
            a(device, a2.d, a2.c);
        }
    }

    public void b(Context context) {
        for (String a2 : this.m.keySet()) {
            FrameContext a3 = a(a2);
            if (a3 != null && a3.i && !a3.h) {
                Intent intent = new Intent();
                intent.putExtra("run_on_main", a3.h);
                Device n2 = SmartHomeDeviceManager.a().n(a3.f19224a);
                if (n2 != null) {
                    a(context, n2, 20, intent);
                    a3.i = false;
                    a3.f = false;
                    if (a3.c != null) {
                        Drawable drawable = a3.c.getDrawable();
                        if (drawable != null && (drawable instanceof AnimationDrawable)) {
                            ((AnimationDrawable) drawable).stop();
                        }
                        a3.c.setVisibility(8);
                    }
                    a(n2, a3.d, a3.c);
                }
            }
        }
    }

    public void a(Context context, Device device, ImageView imageView) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            a(context, device, 1, a2.g);
            if (!a2.i) {
                a2.i = true;
                imageView.setVisibility(0);
                AnimationDrawable animationDrawable = (AnimationDrawable) CommonApplication.getAppContext().getResources().getDrawable(R.drawable.camera_loading);
                imageView.setImageDrawable(animationDrawable);
                animationDrawable.start();
                a2.c = imageView;
            }
        }
    }

    public void b(Context context, Device device, ImageView imageView) {
        FrameContext a2;
        if (!b() && (a2 = a(device.did)) != null) {
            a(context, device, 1, a2.g);
            if (!a2.i) {
                a2.i = true;
                imageView.setVisibility(0);
                AnimationDrawable animationDrawable = (AnimationDrawable) CommonApplication.getAppContext().getResources().getDrawable(R.drawable.camera_loading);
                imageView.setImageDrawable(animationDrawable);
                animationDrawable.start();
                a2.c = imageView;
            }
        }
    }

    public void a(Context context, Device device, int i2, boolean z) {
        FrameContext a2 = a(device.did);
        if (a2 != null) {
            Log.e("Device_Renderer", "start play - " + a2);
            Intent intent = new Intent();
            intent.putExtra("request_frame_rate", i2);
            intent.putExtra("mute", z);
            intent.putExtra("run_on_main", a2.h);
            if (!a2.j) {
                a(context, device, 18, intent);
                a2.j = true;
            }
            a(context, device, 19, intent);
        }
    }

    private static class MySendMessageCallback extends PluginApi.SendMessageCallback {

        /* renamed from: a  reason: collision with root package name */
        final WeakReference<CameraFrameManager> f19227a;
        final boolean b;
        final XQProgressHorizontalDialog c;
        final PluginDownloadTask d;
        final WeakReference<Context> e;
        final PluginRecord f;

        private MySendMessageCallback(CameraFrameManager cameraFrameManager, boolean z, XQProgressHorizontalDialog xQProgressHorizontalDialog, PluginDownloadTask pluginDownloadTask, PluginRecord pluginRecord, Context context) {
            this.f19227a = new WeakReference<>(cameraFrameManager);
            this.b = z;
            this.c = xQProgressHorizontalDialog;
            this.d = pluginDownloadTask;
            this.f = pluginRecord;
            this.e = new WeakReference<>(context);
        }

        public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            CameraFrameManager cameraFrameManager = (CameraFrameManager) this.f19227a.get();
            Context context = (Context) this.e.get();
            if (cameraFrameManager != null && context != null) {
                pluginDownloadTask.a(this.d);
                if (this.c != null && (context instanceof Activity)) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        Activity activity = (Activity) context;
                        if (activity.isFinishing() || activity.isDestroyed()) {
                            return;
                        }
                    } else if (((Activity) context).isFinishing()) {
                        return;
                    }
                    this.c.a(100, 0);
                    this.c.c();
                    this.c.setCancelable(true);
                    this.c.show();
                    this.c.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            CoreApi.a().a(MySendMessageCallback.this.f.o(), MySendMessageCallback.this.d, (CoreApi.CancelPluginDownloadCallback) null);
                        }
                    });
                }
            }
        }

        public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
            if (this.b) {
                int i = (int) (f2 * 100.0f);
                if (i >= 99) {
                    i = 99;
                }
                if (this.c != null) {
                    this.c.a(100, i);
                }
            } else if (this.c != null) {
                this.c.a(100, (int) (f2 * 100.0f));
            }
        }

        public void onDownloadSuccess(PluginRecord pluginRecord) {
            if (!this.b && this.c != null) {
                this.c.dismiss();
            }
        }

        public void onDownloadFailure(PluginError pluginError) {
            if (!this.b && this.c != null) {
                this.c.dismiss();
            }
        }

        public void onDownloadCancel() {
            if (!this.b && this.c != null) {
                this.c.dismiss();
            }
        }

        public void onSendSuccess(Bundle bundle) {
            if (this.b && this.c != null) {
                this.c.dismiss();
            }
        }

        public void onSendFailure(Error error) {
            if (this.b && this.c != null) {
                this.c.dismiss();
            }
        }

        public void onSendCancel() {
            if (this.b && this.c != null) {
                this.c.dismiss();
            }
        }
    }

    public void a(Context context, Device device, int i2, Intent intent) {
        Context context2 = context;
        Device device2 = device;
        if (CameraNativePluginManager.a().a(device2.model)) {
            IXmPluginMessageReceiver iXmPluginMessageReceiver = this.s.get(device2.model);
            if (iXmPluginMessageReceiver == null) {
                iXmPluginMessageReceiver = new MessageReceiver();
                this.s.put(device2.model, iXmPluginMessageReceiver);
            }
            iXmPluginMessageReceiver.handleMessage(context, (XmPluginPackage) null, i2, intent, device.newDeviceStat());
            return;
        }
        PluginRecord d2 = CoreApi.a().d(device2.model);
        if (d2 != null) {
            PluginApi.getInstance().sendMessage(context, d2, i2, intent, device.newDeviceStat(), (RunningProcess) null, false, new MySendMessageCallback(!d2.l() && !d2.k(), XQProgressHorizontalDialog.b(context2, context2.getString(R.string.plugin_downloading) + d2.p() + context2.getString(R.string.plugin)), new PluginDownloadTask(), d2, context));
        }
    }

    /* access modifiers changed from: private */
    public File b(String str) {
        String str2;
        if (!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            str2 = CommonApplication.getAppContext().getCacheDir().getPath();
        } else if (CommonApplication.getAppContext().getExternalCacheDir() != null) {
            str2 = CommonApplication.getAppContext().getExternalCacheDir().getPath();
        } else {
            str2 = CommonApplication.getAppContext().getCacheDir().getPath();
        }
        return new File(str2 + File.separator + str);
    }

    /* access modifiers changed from: private */
    public void a(String str, VideoFrame videoFrame) {
        File b2 = b("cache_frame_" + str);
        try {
            if (!b2.exists()) {
                b2.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(b2);
            a(videoFrame.width, (OutputStream) fileOutputStream);
            a(videoFrame.height, (OutputStream) fileOutputStream);
            a(videoFrame.size, (OutputStream) fileOutputStream);
            a(videoFrame.videoType, (OutputStream) fileOutputStream);
            fileOutputStream.write(videoFrame.data);
            fileOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0075 A[SYNTHETIC, Splitter:B:37:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0080 A[SYNTHETIC, Splitter:B:44:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x008c A[SYNTHETIC, Splitter:B:51:0x008c] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x0070=Splitter:B:34:0x0070, B:41:0x007b=Splitter:B:41:0x007b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.camera.VideoFrame c(java.lang.String r15) {
        /*
            r14 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "cache_frame_"
            r0.append(r1)
            r0.append(r15)
            java.lang.String r15 = r0.toString()
            java.io.File r15 = r14.b((java.lang.String) r15)
            boolean r0 = r15.exists()
            r1 = 0
            if (r0 != 0) goto L_0x001d
            return r1
        L_0x001d:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x006e, all -> 0x006b }
            r0.<init>(r15)     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x006e, all -> 0x006b }
            int r7 = r14.a((java.io.InputStream) r0)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            int r8 = r14.a((java.io.InputStream) r0)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            int r6 = r14.a((java.io.InputStream) r0)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            int r11 = r14.a((java.io.InputStream) r0)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            if (r7 < 0) goto L_0x005e
            if (r8 < 0) goto L_0x005e
            if (r6 < 0) goto L_0x005e
            r2 = 2097152(0x200000, float:2.938736E-39)
            if (r6 <= r2) goto L_0x003d
            goto L_0x005e
        L_0x003d:
            byte[] r3 = new byte[r6]     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            r2 = 0
            int r4 = r3.length     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            int r2 = r0.read(r3, r2, r4)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            if (r2 == r6) goto L_0x0050
            r0.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r15 = move-exception
            r15.printStackTrace()
        L_0x004f:
            return r1
        L_0x0050:
            com.xiaomi.smarthome.camera.VideoFrame r13 = new com.xiaomi.smarthome.camera.VideoFrame     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            r4 = 0
            long r9 = r15.lastModified()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            r12 = 1
            r2 = r13
            r2.<init>(r3, r4, r6, r7, r8, r9, r11, r12)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0067 }
            r1 = r13
        L_0x005e:
            r0.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r15 = move-exception
            r15.printStackTrace()
        L_0x0066:
            return r1
        L_0x0067:
            r15 = move-exception
            goto L_0x0070
        L_0x0069:
            r15 = move-exception
            goto L_0x007b
        L_0x006b:
            r15 = move-exception
            r0 = r1
            goto L_0x008a
        L_0x006e:
            r15 = move-exception
            r0 = r1
        L_0x0070:
            r15.printStackTrace()     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0088
            r0.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0079:
            r15 = move-exception
            r0 = r1
        L_0x007b:
            r15.printStackTrace()     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0088
            r0.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r15 = move-exception
            r15.printStackTrace()
        L_0x0088:
            return r1
        L_0x0089:
            r15 = move-exception
        L_0x008a:
            if (r0 == 0) goto L_0x0094
            r0.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0094:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.listcamera.CameraFrameManager.c(java.lang.String):com.xiaomi.smarthome.camera.VideoFrame");
    }

    private int a(InputStream inputStream) {
        byte[] bArr = new byte[4];
        try {
            if (inputStream.read(bArr, 0, 4) != 4) {
                return -1;
            }
            return (bArr[0] & 255) | 0 | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private void a(int i2, OutputStream outputStream) {
        try {
            outputStream.write(new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)});
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
