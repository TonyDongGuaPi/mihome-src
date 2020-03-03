package com.lwansbrough.RCTCamera;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.List;

public class RCTCameraView extends ViewGroup {
    private int _actualDeviceOrientation = -1;
    private int _aspect = 1;
    private int _captureMode = 0;
    private String _captureQuality = "high";
    private boolean _clearWindowBackground = false;
    /* access modifiers changed from: private */
    public final Context _context;
    private int _flashMode = -1;
    private final OrientationEventListener _orientationListener;
    private int _torchMode = -1;
    private RCTCameraViewFinder _viewFinder = null;
    private int _zoom = 0;

    public RCTCameraView(Context context) {
        super(context);
        this._context = context;
        RCTCamera.createInstance(getDeviceOrientation(context));
        this._orientationListener = new OrientationEventListener(context, 3) {
            public void onOrientationChanged(int i) {
                if (RCTCameraView.this.setActualDeviceOrientation(RCTCameraView.this._context)) {
                    RCTCameraView.this.layoutViewFinder();
                }
            }
        };
        if (this._orientationListener.canDetectOrientation()) {
            this._orientationListener.enable();
        } else {
            this._orientationListener.disable();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutViewFinder(i, i2, i3, i4);
    }

    public void onViewAdded(View view) {
        if (this._viewFinder != view) {
            removeView(this._viewFinder);
            addView(this._viewFinder, 0);
        }
    }

    public void setAspect(int i) {
        this._aspect = i;
        layoutViewFinder();
    }

    public void setCameraType(int i) {
        if (this._viewFinder != null) {
            this._viewFinder.setCameraType(i);
            RCTCamera.getInstance().adjustPreviewLayout(i);
            return;
        }
        this._viewFinder = new RCTCameraViewFinder(this._context, i);
        if (-1 != this._flashMode) {
            this._viewFinder.setFlashMode(this._flashMode);
        }
        if (-1 != this._torchMode) {
            this._viewFinder.setTorchMode(this._torchMode);
        }
        if (this._zoom != 0) {
            this._viewFinder.setZoom(this._zoom);
        }
        this._viewFinder.setClearWindowBackground(this._clearWindowBackground);
        addView(this._viewFinder);
    }

    public void setCaptureMode(int i) {
        this._captureMode = i;
        if (this._viewFinder != null) {
            this._viewFinder.setCaptureMode(i);
        }
    }

    public void setCaptureQuality(String str) {
        this._captureQuality = str;
        if (this._viewFinder != null) {
            this._viewFinder.setCaptureQuality(str);
        }
    }

    public void setTorchMode(int i) {
        this._torchMode = i;
        if (this._viewFinder != null) {
            this._viewFinder.setTorchMode(i);
        }
    }

    public void setFlashMode(int i) {
        this._flashMode = i;
        if (this._viewFinder != null) {
            this._viewFinder.setFlashMode(i);
        }
    }

    public void setZoom(int i) {
        this._zoom = i;
        if (this._viewFinder != null) {
            this._viewFinder.setZoom(i);
        }
    }

    public void setOrientation(int i) {
        RCTCamera.getInstance().setOrientation(i);
        if (this._viewFinder != null) {
            layoutViewFinder();
        }
    }

    public void setBarcodeScannerEnabled(boolean z) {
        RCTCamera.getInstance().setBarcodeScannerEnabled(z);
    }

    public void setBarCodeTypes(List<String> list) {
        RCTCamera.getInstance().setBarCodeTypes(list);
    }

    public void setClearWindowBackground(boolean z) {
        this._clearWindowBackground = z;
        if (this._viewFinder != null) {
            this._viewFinder.setClearWindowBackground(z);
        }
    }

    public void stopPreview() {
        if (this._viewFinder != null) {
            this._viewFinder.stopPreview();
        }
    }

    public void startPreview() {
        if (this._viewFinder != null) {
            this._viewFinder.startPreview();
        }
    }

    /* access modifiers changed from: private */
    public boolean setActualDeviceOrientation(Context context) {
        int deviceOrientation = getDeviceOrientation(context);
        if (this._actualDeviceOrientation == deviceOrientation) {
            return false;
        }
        this._actualDeviceOrientation = deviceOrientation;
        RCTCamera.getInstance().setActualDeviceOrientation(this._actualDeviceOrientation);
        return true;
    }

    private int getDeviceOrientation(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getOrientation();
    }

    /* access modifiers changed from: private */
    public void layoutViewFinder() {
        layoutViewFinder(getLeft(), getTop(), getRight(), getBottom());
    }

    private void layoutViewFinder(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this._viewFinder != null) {
            float f = (float) (i3 - i);
            float f2 = (float) (i4 - i2);
            switch (this._aspect) {
                case 0:
                    double ratio = this._viewFinder.getRatio();
                    double d = (double) f2;
                    Double.isNaN(d);
                    double d2 = d * ratio;
                    double d3 = (double) f;
                    if (d2 >= d3) {
                        i5 = (int) d2;
                        i6 = (int) f2;
                        break;
                    } else {
                        Double.isNaN(d3);
                        i6 = (int) (d3 / ratio);
                        i5 = (int) f;
                        break;
                    }
                case 1:
                    double ratio2 = this._viewFinder.getRatio();
                    double d4 = (double) f2;
                    Double.isNaN(d4);
                    double d5 = d4 * ratio2;
                    double d6 = (double) f;
                    if (d5 <= d6) {
                        i5 = (int) d5;
                        i6 = (int) f2;
                        break;
                    } else {
                        Double.isNaN(d6);
                        i6 = (int) (d6 / ratio2);
                        i5 = (int) f;
                        break;
                    }
                default:
                    i5 = (int) f;
                    i6 = (int) f2;
                    break;
            }
            int i7 = (int) ((f - ((float) i5)) / 2.0f);
            int i8 = (int) ((f2 - ((float) i6)) / 2.0f);
            RCTCamera.getInstance().setPreviewVisibleSize(this._viewFinder.getCameraType(), (int) f, (int) f2);
            this._viewFinder.layout(i7, i8, i5 + i7, i6 + i8);
            postInvalidate(getLeft(), getTop(), getRight(), getBottom());
        }
    }
}
