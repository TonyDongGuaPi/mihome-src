package com.xiaomi.smarthome.listcamera;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.fastvideo.VideoView;
import com.xiaomi.smarthome.framework.network.NetworkManager;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FloatingCameraManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static WindowManager f19273a = null;
    /* access modifiers changed from: private */
    public static FrameLayout b = null;
    /* access modifiers changed from: private */
    public static FloatViewHolder c = null;
    /* access modifiers changed from: private */
    public static Device d = null;
    private static NetworkManager.NetworkListener e = null;
    /* access modifiers changed from: private */
    public static boolean f = false;

    public static void a() {
        if (b != null) {
            if (CameraFrameManager.a().a(d)) {
                b(c, d);
            }
            try {
                f19273a.removeView(b);
            } catch (Exception unused) {
            }
            NetworkManager.a().b(e);
            CameraFrameManager.a().f(d);
            b = null;
            c = null;
            d = null;
            f19273a = null;
        }
    }

    public static void a(final Device device) {
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        CameraFrameManager.a().f(device);
        f19273a = (WindowManager) SHApplication.getAppContext().getSystemService("window");
        if (Build.VERSION.SDK_INT >= 26) {
            layoutParams.type = 2038;
        } else {
            layoutParams.type = 2002;
        }
        layoutParams.format = 1;
        layoutParams.flags = 8;
        layoutParams.gravity = 51;
        layoutParams.x = 100;
        layoutParams.y = 100;
        int a2 = DisplayUtils.a(200.0f);
        layoutParams.width = a2;
        layoutParams.height = (a2 * 360) / 640;
        b = (FrameLayout) LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.camera_float_view_layout, (ViewGroup) null);
        c = new FloatViewHolder(b);
        d = device;
        if (CameraFrameManager.a().a(device.did) == null) {
            CameraFrameManager.a().a(device, true);
        }
        a(c, device);
        c.f19279a.setTag(device);
        c.i.setVisibility(0);
        c.g.setVisibility(0);
        c.h.setVisibility(0);
        CameraFrameManager.FrameContext a3 = CameraFrameManager.a().a(device.did);
        if (a3.a()) {
            c.g.setImageResource(R.drawable.camera_icon_btn_stop_2);
        } else {
            c.g.setImageResource(R.drawable.camera_icon_btn_play_2);
        }
        if (a3.b()) {
            c.h.setImageResource(R.drawable.camera_icon_mute);
        } else {
            c.h.setImageResource(R.drawable.camera_icon_unmute);
        }
        c.i.setEnabled(true);
        c.g.setEnabled(true);
        c.h.setEnabled(true);
        c.j.setVisibility(8);
        CameraFrameManager.a().a(device, c.d, c.j);
        if (CameraFrameManager.a().b(device)) {
            if (c.f == null) {
                a(c);
            }
            CameraFrameManager.a().a(device, c.f);
            c.d.setVisibility(8);
        }
        c.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FloatingCameraManager.a();
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(AllCameraPage.c));
            }
        });
        c.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FloatingCameraManager.c != null) {
                    CameraFrameManager.a().d(device);
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.a(FloatingCameraManager.c, device);
                    }
                    if (CameraFrameManager.a().c(device)) {
                        FloatingCameraManager.c.h.setImageResource(R.drawable.camera_icon_mute);
                    } else {
                        FloatingCameraManager.c.h.setImageResource(R.drawable.camera_icon_unmute);
                    }
                }
            }
        });
        c.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FloatingCameraManager.c != null) {
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.b(FloatingCameraManager.c, device);
                    } else if (!CameraFrameManager.a().b()) {
                        FloatingCameraManager.a(FloatingCameraManager.c, device);
                    } else {
                        return;
                    }
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.c.g.setImageResource(R.drawable.camera_icon_btn_stop_2);
                    } else {
                        FloatingCameraManager.c.g.setImageResource(R.drawable.camera_icon_btn_play_2);
                    }
                }
            }
        });
        f19273a.addView(b, layoutParams);
        b.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        b.setOnTouchListener(new View.OnTouchListener() {
            private float b;
            private float c;
            private boolean d = false;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean z;
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getRawX();
                    this.c = motionEvent.getRawY();
                } else if (motionEvent.getAction() == 2) {
                    if (Math.abs(motionEvent.getRawX() - this.b) > 5.0f) {
                        WindowManager.LayoutParams layoutParams = layoutParams;
                        layoutParams.x = (int) (((float) layoutParams.x) + (motionEvent.getRawX() - this.b));
                        z = true;
                    } else {
                        z = false;
                    }
                    if (Math.abs(motionEvent.getRawY() - this.c) > 5.0f) {
                        WindowManager.LayoutParams layoutParams2 = layoutParams;
                        layoutParams2.y = (int) (((float) layoutParams2.y) + (motionEvent.getRawY() - this.c));
                        z = true;
                    }
                    if (z) {
                        this.b = motionEvent.getRawX();
                        this.c = motionEvent.getRawY();
                        this.d = true;
                        if (FloatingCameraManager.f19273a != null) {
                            FloatingCameraManager.f19273a.updateViewLayout(FloatingCameraManager.b, layoutParams);
                        }
                    }
                } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                    if (!this.d && FloatingCameraManager.c != null) {
                        if (FloatingCameraManager.c.c.getVisibility() == 0) {
                            FloatingCameraManager.c.c.setVisibility(8);
                        } else {
                            FloatingCameraManager.c.c.setVisibility(0);
                        }
                    }
                    this.d = false;
                }
                return false;
            }
        });
        b();
        e = new NetworkManager.NetworkListener() {
            public void a() {
                if (!NetworkManager.a().e() && FloatingCameraManager.f && FloatingCameraManager.b != null) {
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.b(FloatingCameraManager.c, FloatingCameraManager.d);
                        Toast.makeText(SHApplication.getAppContext(), R.string.network_changed, 0).show();
                    }
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.c.g.setImageResource(R.drawable.camera_icon_btn_stop_2);
                    } else {
                        FloatingCameraManager.c.g.setImageResource(R.drawable.camera_icon_btn_play_2);
                    }
                }
                boolean unused = FloatingCameraManager.f = NetworkManager.a().e();
            }

            public void b() {
                if (FloatingCameraManager.b != null) {
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.b(FloatingCameraManager.c, FloatingCameraManager.d);
                        Toast.makeText(SHApplication.getAppContext(), R.string.network_changed, 0).show();
                    }
                    if (CameraFrameManager.a().a(device)) {
                        FloatingCameraManager.c.g.setImageResource(R.drawable.camera_icon_btn_stop_2);
                    } else {
                        FloatingCameraManager.c.g.setImageResource(R.drawable.camera_icon_btn_play_2);
                    }
                }
            }
        };
        NetworkManager.a().a(e);
        f = NetworkManager.a().e();
    }

    static boolean b() {
        try {
            Method declaredMethod = Class.forName("com.miui.whetstone.WhetstoneActivityManager").getDeclaredMethod("promoteApplicationLevel", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke((Object) null, new Object[]{2});
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    static void c() {
        try {
            Method declaredMethod = Class.forName("com.miui.whetstone.WhetstoneActivityManager").getDeclaredMethod("releaseApplicationPromoteLevel", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke((Object) null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }

    static void a(final FloatViewHolder floatViewHolder) {
        floatViewHolder.f = new VideoView(SHApplication.getAppContext());
        int a2 = DisplayUtils.a(200.0f);
        floatViewHolder.f.setVideoFrameSize(a2, (a2 * 370) / 640, false);
        floatViewHolder.f.disableTouch();
        floatViewHolder.f.setVideoViewListener(new VideoView.IVideoViewListener() {
            public void a() {
                if (floatViewHolder.c.getVisibility() == 0) {
                    floatViewHolder.c.setVisibility(8);
                } else {
                    floatViewHolder.c.setVisibility(0);
                }
            }
        });
        floatViewHolder.e.addView(floatViewHolder.f);
    }

    static void a(FloatViewHolder floatViewHolder, Device device) {
        if (floatViewHolder.f == null) {
            a(floatViewHolder);
        }
        CameraFrameManager.a().a(device, floatViewHolder.f);
        CameraFrameManager.a().b(SHApplication.getAppContext(), device, floatViewHolder.j);
    }

    static void b(FloatViewHolder floatViewHolder) {
        floatViewHolder.e.removeAllViews();
        c();
        floatViewHolder.f = null;
    }

    static void b(FloatViewHolder floatViewHolder, Device device) {
        CameraFrameManager.a().b(device, floatViewHolder.f);
        CameraFrameManager.a().a(SHApplication.getAppContext(), device);
        b(floatViewHolder);
    }

    static class FloatViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f19279a;
        public TextView b;
        public View c;
        public ImageView d;
        public FrameLayout e;
        public VideoView f;
        public ImageView g;
        public ImageView h;
        public ImageView i;
        public ImageView j;

        public FloatViewHolder(View view) {
            if (view instanceof ViewGroup) {
                this.f19279a = view;
                this.d = (ImageView) view.findViewById(R.id.video_view_cover);
                this.e = (FrameLayout) view.findViewById(R.id.video_view_container);
                this.c = view.findViewById(R.id.btn_bottom_view);
                this.g = (ImageView) view.findViewById(R.id.pause_button);
                this.h = (ImageView) view.findViewById(R.id.mute_button);
                this.i = (ImageView) view.findViewById(R.id.delete_btn);
                this.j = (ImageView) view.findViewById(R.id.camera_loading);
            }
        }
    }
}
