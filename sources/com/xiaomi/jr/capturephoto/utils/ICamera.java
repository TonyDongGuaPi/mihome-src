package com.xiaomi.jr.capturephoto.utils;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.xiaomi.jr.common.os.SystemProperties;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.ReflectUtil;
import com.xiaomi.jr.common.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class ICamera {
    private static String d = "ICamera";
    private static final List<String> e = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    public Camera f10352a;
    public boolean b;
    public FlipPreviewFrameMode c;
    private Context f;
    private byte[] g;
    private int h;
    private int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private Handler n;
    private HandlerThread o;

    public enum FlipPreviewFrameMode {
        NONE,
        HORIZONTAL,
        VERTICAL,
        CENTER
    }

    public interface FramePrepareCallback {
        void a(byte[] bArr);
    }

    static {
        e.add("lithium");
        e.add("chiron");
        e.add("polaris");
    }

    public ICamera(Context context) {
        this(context, true);
    }

    public ICamera(Context context, boolean z) {
        this.c = FlipPreviewFrameMode.NONE;
        this.o = new HandlerThread("reverseFrame");
        this.f = context.getApplicationContext();
        this.j = z;
        Point e2 = Utils.e(context);
        this.k = e2.x;
        this.l = e2.y;
        String str = d;
        MifiLog.b(str, "Screen width: " + this.k + ", height: " + this.l);
    }

    public boolean a() {
        return this.f10352a != null;
    }

    public Camera a(boolean z) {
        e();
        b(z);
        try {
            int c2 = c(z);
            String str = d;
            MifiLog.b(str, "front camera id: " + c2);
            this.f10352a = Camera.open(c2);
            Camera.Parameters parameters = this.f10352a.getParameters();
            String str2 = d;
            MifiLog.b(str2, "camera original parameters: " + parameters.flatten());
            int i2 = this.k;
            int i3 = this.l;
            int i4 = this.k < this.l ? this.k : this.l;
            if (i4 < 1080) {
                float f2 = 1080.0f / ((float) i4);
                int round = Math.round(((float) this.k) * f2);
                i3 = Math.round(f2 * ((float) this.l));
                i2 = round;
            }
            Camera.Size a2 = a(i2, i3, parameters.getSupportedPreviewSizes());
            if (a2 == null) {
                MifiLog.e(d, "cal best preview size fail");
                return null;
            }
            this.h = a2.width;
            this.i = a2.height;
            String str3 = d;
            MifiLog.b(str3, "preview width: " + this.h + ", height: " + this.i);
            parameters.setPreviewSize(this.h, this.i);
            Camera.Size a3 = a(this.k, this.l, parameters.getSupportedPictureSizes());
            if (a3 == null) {
                MifiLog.e(d, "cal best picture size fail");
                return null;
            }
            String str4 = d;
            MifiLog.b(str4, "picture width: " + a3.width + ", height: " + a3.height);
            parameters.setPictureSize(a3.width, a3.height);
            a(parameters);
            if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
                parameters.setFocusMode("continuous-picture");
            } else if (parameters.getSupportedFocusModes().contains("auto")) {
                parameters.setFocusMode("auto");
            }
            this.f10352a.setParameters(parameters);
            String str5 = d;
            MifiLog.b(str5, "camera applied parameters: " + parameters.flatten());
            this.m = a(c2);
            this.f10352a.setDisplayOrientation(this.m);
            this.g = new byte[(((this.h * this.i) * ImageFormat.getBitsPerPixel(parameters.getPreviewFormat())) / 8)];
            return this.f10352a;
        } catch (Exception e2) {
            this.f10352a = null;
            e2.printStackTrace();
        }
    }

    private void b(boolean z) {
        this.b = false;
        this.c = FlipPreviewFrameMode.NONE;
        if (z && this.j) {
            this.b = e.contains(Build.DEVICE);
            if (this.b && !a(this.f.getPackageName())) {
                this.c = FlipPreviewFrameMode.CENTER;
            }
            if (TextUtils.equals(Build.DEVICE, "N1T")) {
                this.c = FlipPreviewFrameMode.HORIZONTAL;
            }
        }
    }

    private static boolean a(String str) {
        return b(str) || c(str);
    }

    private static boolean b(String str) {
        try {
            Object a2 = ReflectUtil.a(Class.forName("miui.util.FeatureParser"), "getStringArray", new Class[]{String.class}, (Object) null, "camera_rotate_packagelist");
            if (a2 != null && (a2 instanceof String[])) {
                for (String equals : (String[]) a2) {
                    if (TextUtils.equals(str, equals)) {
                        return true;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private static boolean c(String str) {
        String a2 = SystemProperties.a("camera.rotate.packagelist");
        if (TextUtils.isEmpty(a2)) {
            a2 = SystemProperties.a("vendor.camera.rotate.packagelist");
        }
        if (!TextUtils.isEmpty(a2)) {
            for (String equals : a2.split(",")) {
                if (TextUtils.equals(str, equals)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int c(boolean z) {
        if (TextUtils.equals(Build.DEVICE, "N1T")) {
            return 0;
        }
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == z) {
                return i2;
            }
        }
        return z ? 1 : 0;
    }

    private void a(Camera.Parameters parameters) {
        String str = parameters.get("auto-exposure-values");
        if (str != null && str.contains("center-weighted-adv")) {
            parameters.set("auto-exposure", "center-weighted-adv");
        } else if (str != null && str.contains("center-weighted")) {
            parameters.set("auto-exposure", "center-weighted");
            parameters.setExposureCompensation(parameters.getMaxExposureCompensation() / 5);
        }
    }

    public void b() {
        if (this.f10352a != null) {
            try {
                if (TextUtils.equals(this.f10352a.getParameters().getFocusMode(), "auto")) {
                    this.f10352a.autoFocus((Camera.AutoFocusCallback) null);
                }
            } catch (RuntimeException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(RelativeLayout.LayoutParams layoutParams) {
        if (this.j) {
            float max = Math.max((((float) this.k) * 1.0f) / ((float) this.i), (((float) this.l) * 1.0f) / ((float) this.h));
            layoutParams.width = Math.round(((float) this.i) * max);
            layoutParams.height = Math.round(max * ((float) this.h));
        } else {
            float max2 = Math.max((((float) this.l) * 1.0f) / ((float) this.i), (((float) this.k) * 1.0f) / ((float) this.h));
            layoutParams.width = Math.round(((float) this.h) * max2);
            layoutParams.height = Math.round(max2 * ((float) this.i));
        }
        int i2 = (this.k - layoutParams.width) / 2;
        int i3 = (this.l - layoutParams.height) / 2;
        layoutParams.setMargins(i2, i3, i2, i3);
    }

    public void a(Camera.PreviewCallback previewCallback) {
        if (this.f10352a != null) {
            try {
                this.f10352a.addCallbackBuffer(this.g);
                this.f10352a.setPreviewCallbackWithBuffer(previewCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public byte[] c() {
        return this.g;
    }

    public void a(SurfaceTexture surfaceTexture) {
        if (this.f10352a != null) {
            try {
                this.f10352a.setPreviewTexture(surfaceTexture);
                this.f10352a.startPreview();
                this.f10352a.startFaceDetection();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void d() {
        if (this.f10352a != null) {
            this.f10352a.stopPreview();
        }
    }

    public void e() {
        if (this.f10352a != null) {
            try {
                this.f10352a.stopPreview();
                this.f10352a.setPreviewCallbackWithBuffer((Camera.PreviewCallback) null);
                this.f10352a.stopFaceDetection();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                this.f10352a.release();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.f10352a = null;
        }
    }

    private Camera.Size a(int i2, int i3, List<Camera.Size> list) {
        Camera.Size size = null;
        if (list == null) {
            return null;
        }
        int min = Math.min(i2, i3);
        double a2 = a(i2, i3);
        double d2 = Double.MAX_VALUE;
        double d3 = 0.43d;
        ArrayList<Camera.Size> arrayList = new ArrayList<>();
        for (Camera.Size next : list) {
            int min2 = Math.min(next.width, next.height);
            double abs = Math.abs(a(next.width, next.height) - a2);
            if (abs < d2) {
                d2 = abs;
            }
            if (min2 == min && abs < d3) {
                size = next;
                d3 = abs;
            }
        }
        if (size != null) {
            return size;
        }
        for (Camera.Size next2 : list) {
            if (Math.abs(Math.abs(a(next2.width, next2.height) - a2) - d2) < 1.0E-6d) {
                arrayList.add(next2);
            }
        }
        double d4 = 2.147483647E9d;
        int max = Math.max(i2, i3);
        for (Camera.Size size2 : arrayList) {
            double abs2 = (double) Math.abs(Math.max(size2.width, size2.height) - max);
            if (abs2 < d4) {
                size = size2;
                d4 = abs2;
            }
        }
        return size;
    }

    private double a(int i2, int i3) {
        double d2;
        double d3;
        if (i2 > i3) {
            d2 = (double) i2;
            d3 = (double) i3;
        } else {
            d2 = (double) i3;
            d3 = (double) i2;
        }
        Double.isNaN(d2);
        Double.isNaN(d3);
        return d2 / d3;
    }

    public int a(int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        int i3 = 0;
        switch (((WindowManager) this.f.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                i3 = 90;
                break;
            case 2:
                i3 = 180;
                break;
            case 3:
                i3 = 270;
                break;
        }
        if (cameraInfo.facing != 1) {
            return ((cameraInfo.orientation - i3) + 360) % 360;
        }
        int i4 = (360 - ((cameraInfo.orientation + i3) % 360)) % 360;
        if (this.b) {
            return (i4 + 180) % 360;
        }
        return i4;
    }

    public void a(byte[] bArr, final FramePrepareCallback framePrepareCallback) {
        if (this.n == null) {
            this.o.start();
            this.n = new Handler(this.o.getLooper()) {
                public void handleMessage(Message message) {
                    if (message != null) {
                        byte[] bArr = (byte[]) message.obj;
                        ICamera.this.a(bArr);
                        if (framePrepareCallback != null) {
                            framePrepareCallback.a(bArr);
                        }
                    }
                }
            };
        }
        Message obtain = Message.obtain();
        obtain.obj = bArr;
        this.n.sendMessage(obtain);
    }

    /* access modifiers changed from: private */
    public void a(byte[] bArr) {
        if (bArr != null) {
            if (this.c == FlipPreviewFrameMode.CENTER) {
                b(bArr);
            } else if (this.c == FlipPreviewFrameMode.HORIZONTAL) {
                a(bArr, this.h, this.i);
            } else {
                FlipPreviewFrameMode flipPreviewFrameMode = this.c;
                FlipPreviewFrameMode flipPreviewFrameMode2 = FlipPreviewFrameMode.VERTICAL;
            }
        }
    }

    private static void a(byte[] bArr, int i2, int i3) {
        if (bArr != null && i2 * i3 == (bArr.length * 2) / 3) {
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 < i2 / 2; i5++) {
                    int i6 = i4 * i2;
                    int i7 = i6 + i5;
                    int i8 = i6 + (i2 - i5);
                    byte b2 = bArr[i7];
                    bArr[i7] = bArr[i8];
                    bArr[i8] = b2;
                }
            }
        }
    }

    private static void b(byte[] bArr) {
        if (bArr != null) {
            int length = (bArr.length * 2) / 3;
            for (int i2 = 0; length > i2; i2++) {
                byte b2 = bArr[length];
                bArr[length] = bArr[i2];
                bArr[i2] = b2;
                length--;
            }
        }
    }

    private static void b(byte[] bArr, int i2, int i3) {
        if (bArr != null && i2 <= bArr.length - 1 && i3 <= bArr.length - 1 && i2 >= 0 && i3 >= 0) {
            while (i3 > i2) {
                int i4 = i3 - 1;
                byte b2 = bArr[i4];
                byte b3 = bArr[i3];
                bArr[i4] = bArr[i2];
                int i5 = i2 + 1;
                bArr[i3] = bArr[i5];
                bArr[i2] = b2;
                bArr[i5] = b3;
                i3 -= 2;
                i2 += 2;
            }
        }
    }

    public int f() {
        return this.k;
    }

    public int g() {
        return this.l;
    }

    public int h() {
        return this.h;
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.m;
    }
}
