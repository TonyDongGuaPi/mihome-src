package com.xiaomi.qrcode.camera.open;

import android.hardware.Camera;
import java.util.List;

public final class OpenCamera {

    /* renamed from: a  reason: collision with root package name */
    private final int f12992a;
    private final Camera b;
    private final CameraFacing c;
    private final int d;

    public OpenCamera(int i, Camera camera, CameraFacing cameraFacing, int i2) {
        this.f12992a = i;
        this.b = camera;
        this.c = cameraFacing;
        this.d = i2;
    }

    public Camera a() {
        return this.b;
    }

    public CameraFacing b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public String toString() {
        return "Camera #" + this.f12992a + " : " + this.c + ',' + this.d;
    }

    public void a(int i, int i2) {
        if (this.b != null) {
            Camera.Parameters parameters = this.b.getParameters();
            Camera.Size a2 = a(parameters.getSupportedPreviewSizes(), i, i2);
            parameters.setPreviewSize(a2.width, a2.height);
            this.b.setParameters(parameters);
        }
    }

    private static Camera.Size a(List<Camera.Size> list, int i, int i2) {
        double d2 = (double) i2;
        double d3 = (double) i;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        double d5 = Double.MAX_VALUE;
        Camera.Size size = null;
        double d6 = Double.MAX_VALUE;
        for (Camera.Size next : list) {
            double d7 = (double) next.width;
            double d8 = (double) next.height;
            Double.isNaN(d7);
            Double.isNaN(d8);
            double d9 = (d7 / d8) - d4;
            if (Math.abs(d9) <= 0.1d && Math.abs(d9) < d6) {
                d6 = Math.abs(d9);
                size = next;
            }
        }
        if (size == null) {
            for (Camera.Size next2 : list) {
                double d10 = (double) next2.width;
                double d11 = (double) next2.height;
                Double.isNaN(d10);
                Double.isNaN(d11);
                double d12 = (d10 / d11) - d4;
                if (Math.abs(d12) < d5) {
                    d5 = Math.abs(d12);
                    size = next2;
                }
            }
        }
        return size;
    }
}
