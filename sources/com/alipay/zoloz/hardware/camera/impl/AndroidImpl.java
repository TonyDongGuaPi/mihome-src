package com.alipay.zoloz.hardware.camera.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.DisplayUtil;
import com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting;
import com.alipay.zoloz.hardware.camera.a;
import com.alipay.zoloz.hardware.camera.b;
import com.alipay.zoloz.hardware.camera.c;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.List;

@SuppressLint({"InlinedApi"})
public class AndroidImpl implements c {

    /* renamed from: a  reason: collision with root package name */
    private static AndroidImpl f1200a;
    private Context b;
    private Camera c;
    private Camera.Parameters d;
    /* access modifiers changed from: private */
    public b e;
    private int f = 90;
    private int g;
    private int h;
    private DeviceSetting i = new DeviceSetting();
    private final Object j = new Object();
    /* access modifiers changed from: private */
    public int k = 0;
    /* access modifiers changed from: private */
    public int l = 0;
    /* access modifiers changed from: private */
    public int m = 0;
    /* access modifiers changed from: private */
    public int n = 0;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;

    public PointF colorToDepth(PointF pointF) {
        return null;
    }

    public PointF depthToColor(PointF pointF) {
        return null;
    }

    public int getDepthHeight() {
        return 0;
    }

    public int getDepthWidth() {
        return 0;
    }

    public void setFrameAvailableListener(SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener) {
    }

    private AndroidImpl(Context context) {
        if (context != null) {
            this.b = context;
            this.o = false;
            this.p = false;
            this.q = false;
            this.r = false;
            return;
        }
        throw new IllegalArgumentException("Context can't be null");
    }

    public static synchronized AndroidImpl getInstance(Context context) {
        AndroidImpl androidImpl;
        synchronized (AndroidImpl.class) {
            if (f1200a == null) {
                f1200a = new AndroidImpl(context);
            }
            f1200a.a(context);
            androidImpl = f1200a;
        }
        return androidImpl;
    }

    public void initCamera(DeviceSetting deviceSetting) {
        if (!this.o) {
            if (deviceSetting != null) {
                this.i = deviceSetting;
            }
            this.o = true;
        }
    }

    public void releaseCamera() {
        if (this.o) {
            this.o = false;
            this.b = null;
        }
    }

    public void openCamera(DeviceSetting deviceSetting) {
        if (!this.p) {
            if (deviceSetting != null) {
                this.i = deviceSetting;
            }
            this.p = true;
        }
    }

    public void closeCamera() {
        if (this.p) {
            this.p = false;
        }
    }

    public void startCamera() {
        int i2;
        if (!this.q) {
            this.h = Camera.getNumberOfCameras();
            if (this.i.isCameraAuto()) {
                i2 = this.h <= 1 ? 0 : 1;
            } else {
                i2 = this.i.getCameraID();
            }
            if (a(i2)) {
                this.q = true;
            }
        }
    }

    public void stopCamera() {
        if (this.q) {
            this.e = null;
            stopPreview();
            if (this.c != null) {
                synchronized (this.j) {
                    try {
                        this.c.release();
                        this.c = null;
                        this.q = false;
                    } catch (Exception e2) {
                        BioLog.e(e2.toString());
                    }
                }
            }
        }
    }

    public void startPreview(SurfaceHolder surfaceHolder, float f2, int i2, int i3) {
        BioLog.d("startPreview...");
        if (!this.r && this.c != null) {
            try {
                this.c.setPreviewDisplay(surfaceHolder);
                this.c.startPreview();
                this.r = true;
            } catch (Exception e2) {
                BioLog.e(e2.toString());
                if (this.e != null) {
                    this.e.onError(-1);
                }
            }
        }
    }

    public void stopPreview() {
        if (this.r && this.c != null) {
            synchronized (this.j) {
                try {
                    this.c.setOneShotPreviewCallback((Camera.PreviewCallback) null);
                    this.c.setPreviewCallback((Camera.PreviewCallback) null);
                    this.c.stopPreview();
                } catch (Exception e2) {
                    BioLog.e(e2.toString());
                }
            }
            this.r = false;
        }
    }

    public void setCallback(b bVar) {
        this.e = bVar;
    }

    public int getCameraViewRotation() {
        return this.f;
    }

    public int getColorWidth() {
        return this.k;
    }

    public int getColorHeight() {
        return this.l;
    }

    public int getPreviewWidth() {
        return this.m;
    }

    public int getPreviewHeight() {
        return this.n;
    }

    private void a(Context context) {
        this.b = context;
    }

    private boolean a(int i2) {
        BioLog.i("realStartCamera");
        try {
            this.c = Camera.open(i2);
            if (this.c == null) {
                if (this.e != null) {
                    this.e.onError(-1);
                }
                return false;
            }
            this.g = i2;
            if (this.c != null) {
                this.d = this.c.getParameters();
                a();
                this.c.setParameters(this.d);
                if (this.e == null) {
                    return true;
                }
                this.c.setPreviewCallback(new Camera.PreviewCallback() {
                    public void onPreviewFrame(byte[] bArr, Camera camera) {
                        AndroidImpl.this.e.onPreviewFrame(new a(ByteBuffer.wrap(bArr), AndroidImpl.this.k, AndroidImpl.this.l, 0, (ShortBuffer) null, 0, 0, AndroidImpl.this.m, AndroidImpl.this.n));
                    }
                });
                return true;
            }
            return false;
        } catch (Exception unused) {
            if (this.e != null) {
                this.e.onError(-1);
            }
        } catch (Throwable unused2) {
            if (this.e != null) {
                this.e.onError(-1);
            }
        }
    }

    private int a(DeviceSetting deviceSetting) {
        if (deviceSetting == null) {
            throw new IllegalArgumentException("deviceSetting can't be null");
        } else if (deviceSetting.isDisplayAuto()) {
            return b(this.g);
        } else {
            return deviceSetting.getDisplayAngle();
        }
    }

    private void a() {
        if (this.d != null) {
            Camera.Size a2 = com.alipay.zoloz.hardware.camera.a.a.a().a(this.d.getSupportedPreviewSizes(), DisplayUtil.getScreenRate(this.b), 600);
            if (a2 != null) {
                this.m = a2.width;
                this.n = a2.height;
                this.k = this.m;
                this.l = this.n;
                this.d.setPreviewSize(this.m, this.n);
            }
            if (this.i != null) {
                this.f = a(this.i);
                this.c.setDisplayOrientation(this.f);
            }
            List<String> supportedFocusModes = this.d.getSupportedFocusModes();
            if (supportedFocusModes == null) {
                return;
            }
            if (supportedFocusModes.contains("continuous-video")) {
                this.d.setFocusMode("continuous-video");
            } else {
                supportedFocusModes.contains("auto");
            }
        }
    }

    private int b(int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        int i3 = 0;
        switch (((WindowManager) this.b.getSystemService("window")).getDefaultDisplay().getRotation()) {
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
        if (cameraInfo.facing == 1) {
            if (com.alipay.android.phone.a.a.a.b.booleanValue()) {
                cameraInfo.orientation = 270;
            }
            return (360 - ((cameraInfo.orientation + i3) % 360)) % 360;
        }
        if (com.alipay.android.phone.a.a.a.b.booleanValue()) {
            cameraInfo.orientation = 90;
        }
        return ((cameraInfo.orientation - i3) + 360) % 360;
    }
}
