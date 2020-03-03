package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

class SensorsTest implements PermissionTest {
    private static final SensorEventListener b = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private Context f2448a;

    SensorsTest(Context context) {
        this.f2448a = context;
    }

    public boolean a() throws Throwable {
        SensorManager sensorManager = (SensorManager) this.f2448a.getSystemService("sensor");
        try {
            Sensor defaultSensor = sensorManager.getDefaultSensor(21);
            sensorManager.registerListener(b, defaultSensor, 3);
            sensorManager.unregisterListener(b, defaultSensor);
            return true;
        } catch (Throwable unused) {
            return !this.f2448a.getPackageManager().hasSystemFeature("android.hardware.sensor.heartrate");
        }
    }
}
