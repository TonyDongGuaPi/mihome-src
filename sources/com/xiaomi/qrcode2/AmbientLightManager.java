package com.xiaomi.qrcode2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.xiaomi.qrcode2.camera.CameraManager;

final class AmbientLightManager implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    private static final float f12994a = 45.0f;
    private static final float b = 450.0f;
    private final Context c;
    private CameraManager d;
    private Sensor e;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    AmbientLightManager(Context context) {
        this.c = context;
    }

    /* access modifiers changed from: package-private */
    public void a(CameraManager cameraManager) {
        this.d = cameraManager;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.e != null) {
            ((SensorManager) this.c.getSystemService("sensor")).unregisterListener(this);
            this.d = null;
            this.e = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this.d == null) {
            return;
        }
        if (f <= f12994a) {
            this.d.a(true);
        } else if (f >= b) {
            this.d.a(false);
        }
    }
}
