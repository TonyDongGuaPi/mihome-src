package com.reactnative.camera.RCTCamera;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.List;

public class RCTCameraView extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private final OrientationEventListener f8659a;
    /* access modifiers changed from: private */
    public final Context b;
    private RCTCameraViewFinder c = null;
    private int d = -1;
    private int e = 1;
    private int f = 0;
    private String g = "high";
    private int h = -1;
    private int i = -1;
    private int j = 0;
    private boolean k = false;

    public RCTCameraView(Context context) {
        super(context);
        this.b = context;
        RCTCamera.a(b(context));
        this.f8659a = new OrientationEventListener(context, 3) {
            public void onOrientationChanged(int i) {
                if (RCTCameraView.this.a(RCTCameraView.this.b)) {
                    RCTCameraView.this.a();
                }
            }
        };
        if (this.f8659a.canDetectOrientation()) {
            this.f8659a.enable();
        } else {
            this.f8659a.disable();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        a(i2, i3, i4, i5);
    }

    public void onViewAdded(View view) {
        if (this.c != view) {
            removeView(this.c);
            addView(this.c, 0);
        }
    }

    public void setAspect(int i2) {
        this.e = i2;
        a();
    }

    public void setCameraType(int i2) {
        if (this.c != null) {
            this.c.a(i2);
            RCTCamera.a().k(i2);
            return;
        }
        this.c = new RCTCameraViewFinder(this.b, i2);
        if (-1 != this.i) {
            this.c.d(this.i);
        }
        if (-1 != this.h) {
            this.c.c(this.h);
        }
        if (this.j != 0) {
            this.c.e(this.j);
        }
        this.c.a(this.k);
        addView(this.c);
    }

    public void setCaptureMode(int i2) {
        this.f = i2;
        if (this.c != null) {
            this.c.b(i2);
        }
    }

    public void setCaptureQuality(String str) {
        this.g = str;
        if (this.c != null) {
            this.c.a(str);
        }
    }

    public void setTorchMode(int i2) {
        this.h = i2;
        if (this.c != null) {
            this.c.c(i2);
        }
    }

    public void setFlashMode(int i2) {
        this.i = i2;
        if (this.c != null) {
            this.c.d(i2);
        }
    }

    public void setZoom(int i2) {
        this.j = i2;
        if (this.c != null) {
            this.c.e(i2);
        }
    }

    public void setOrientation(int i2) {
        RCTCamera.a().h(i2);
        if (this.c != null) {
            a();
        }
    }

    public void setBarcodeScannerEnabled(boolean z) {
        RCTCamera.a().a(z);
    }

    public void setBarCodeTypes(List<String> list) {
        RCTCamera.a().a(list);
    }

    public void setClearWindowBackground(boolean z) {
        this.k = z;
        if (this.c != null) {
            this.c.a(z);
        }
    }

    public void stopPreview() {
        if (this.c != null) {
            this.c.d();
        }
    }

    public void startPreview() {
        if (this.c != null) {
            this.c.c();
        }
    }

    /* access modifiers changed from: private */
    public boolean a(Context context) {
        int b2 = b(context);
        if (this.d == b2) {
            return false;
        }
        this.d = b2;
        RCTCamera.a().j(this.d);
        return true;
    }

    private int b(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getOrientation();
    }

    /* access modifiers changed from: private */
    public void a() {
        a(getLeft(), getTop(), getRight(), getBottom());
    }

    private void a(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        if (this.c != null) {
            float f2 = (float) (i4 - i2);
            float f3 = (float) (i5 - i3);
            switch (this.e) {
                case 0:
                    double b2 = this.c.b();
                    double d2 = (double) f3;
                    Double.isNaN(d2);
                    double d3 = d2 * b2;
                    double d4 = (double) f2;
                    if (d3 >= d4) {
                        i6 = (int) d3;
                        i7 = (int) f3;
                        break;
                    } else {
                        Double.isNaN(d4);
                        i7 = (int) (d4 / b2);
                        i6 = (int) f2;
                        break;
                    }
                case 1:
                    double b3 = this.c.b();
                    double d5 = (double) f3;
                    Double.isNaN(d5);
                    double d6 = d5 * b3;
                    double d7 = (double) f2;
                    if (d6 <= d7) {
                        i6 = (int) d6;
                        i7 = (int) f3;
                        break;
                    } else {
                        Double.isNaN(d7);
                        i7 = (int) (d7 / b3);
                        i6 = (int) f2;
                        break;
                    }
                default:
                    i6 = (int) f2;
                    i7 = (int) f3;
                    break;
            }
            int i8 = (int) ((f2 - ((float) i6)) / 2.0f);
            int i9 = (int) ((f3 - ((float) i7)) / 2.0f);
            RCTCamera.a().a(this.c.a(), (int) f2, (int) f3);
            this.c.layout(i8, i9, i6 + i8, i7 + i9);
            postInvalidate(getLeft(), getTop(), getRight(), getBottom());
        }
    }
}
