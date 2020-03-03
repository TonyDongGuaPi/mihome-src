package com.xiaomi.qrcode2.camera.open;

import android.hardware.Camera;

public final class OpenCamera {

    /* renamed from: a  reason: collision with root package name */
    private final int f13034a;
    private final Camera b;
    private final CameraFacing c;
    private final int d;

    public OpenCamera(int i, Camera camera, CameraFacing cameraFacing, int i2) {
        this.f13034a = i;
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
        return "Camera #" + this.f13034a + " : " + this.c + ',' + this.d;
    }
}
