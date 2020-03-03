package com.xiaomi.qrcode.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.xiaomi.qrcode.camera.open.CameraFacing;
import com.xiaomi.qrcode.camera.open.OpenCamera;

final class CameraConfigurationManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12988a = "CameraConfiguration";
    private final Context b;
    private int c;
    private int d;
    private Point e;
    private Point f;
    private Point g;
    private Point h;

    CameraConfigurationManager(Context context) {
        this.b = context;
    }

    /* access modifiers changed from: package-private */
    public void a(OpenCamera openCamera) {
        int i;
        Camera.Parameters parameters = openCamera.a().getParameters();
        Display defaultDisplay = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        switch (rotation) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 90;
                break;
            case 2:
                i = 180;
                break;
            case 3:
                i = 270;
                break;
            default:
                if (rotation % 90 == 0) {
                    i = (rotation + 360) % 360;
                    break;
                } else {
                    throw new IllegalArgumentException("Bad rotation: " + rotation);
                }
        }
        Log.i(f12988a, "Display at: " + i);
        int c2 = openCamera.c();
        Log.i(f12988a, "Camera at: " + c2);
        if (openCamera.b() == CameraFacing.FRONT) {
            c2 = (360 - c2) % 360;
            Log.i(f12988a, "Front camera overriden to: " + c2);
        }
        this.d = ((c2 + 360) - i) % 360;
        Log.i(f12988a, "Final display orientation: " + this.d);
        if (openCamera.b() == CameraFacing.FRONT) {
            Log.i(f12988a, "Compensating rotation for front camera");
            this.c = (360 - this.d) % 360;
        } else {
            this.c = this.d;
        }
        Log.i(f12988a, "Clockwise rotation from display to camera: " + this.c);
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.e = point;
        Log.i(f12988a, "Screen resolution in current orientation: " + this.e);
        this.f = CameraConfigurationUtils.a(parameters, this.e);
        Log.i(f12988a, "Camera resolution: " + this.f);
        this.g = CameraConfigurationUtils.a(parameters, this.e);
        Log.i(f12988a, "Best available preview size: " + this.g);
        boolean z = true;
        boolean z2 = this.e.x < this.e.y;
        if (this.g.x >= this.g.y) {
            z = false;
        }
        if (z2 == z) {
            this.h = this.g;
        } else {
            this.h = new Point(this.g.y, this.g.x);
        }
        Log.i(f12988a, "Preview size on screen: " + this.h);
    }

    /* access modifiers changed from: package-private */
    public void a(OpenCamera openCamera, boolean z) {
        Camera a2 = openCamera.a();
        Camera.Parameters parameters = a2.getParameters();
        if (parameters == null) {
            Log.w(f12988a, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(f12988a, "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w(f12988a, "In camera config safe mode -- most settings will not be honored");
        }
        a(parameters, PreferenceManager.getDefaultSharedPreferences(this.b), z);
        CameraConfigurationUtils.a(parameters, true, true, z);
        parameters.setPreviewSize(this.g.x, this.g.y);
        a2.setParameters(parameters);
        a2.setDisplayOrientation(this.d);
        Camera.Size previewSize = a2.getParameters().getPreviewSize();
        if (previewSize == null) {
            return;
        }
        if (this.g.x != previewSize.width || this.g.y != previewSize.height) {
            Log.w(f12988a, "Camera said it supported preview size " + this.g.x + 'x' + this.g.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            this.g.x = previewSize.width;
            this.g.y = previewSize.height;
        }
    }

    /* access modifiers changed from: package-private */
    public Point a() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public Point b() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public Point c() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public Point d() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Camera camera) {
        String flashMode;
        if (camera == null || camera.getParameters() == null || (flashMode = camera.getParameters().getFlashMode()) == null) {
            return false;
        }
        if ("on".equals(flashMode) || "torch".equals(flashMode)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        a(parameters, z, false);
        camera.setParameters(parameters);
    }

    private void a(Camera.Parameters parameters, SharedPreferences sharedPreferences, boolean z) {
        a(parameters, FrontLightMode.readPref(sharedPreferences) == FrontLightMode.ON, z);
    }

    private void a(Camera.Parameters parameters, boolean z, boolean z2) {
        CameraConfigurationUtils.a(parameters, z);
        PreferenceManager.getDefaultSharedPreferences(this.b);
    }
}
