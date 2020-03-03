package com.yanzhenjie.permission.checker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.RequiresApi;

class SensorsTest implements PermissionTest {
    private static final SensorEventListener b = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private Context f2419a;

    SensorsTest(Context context) {
        this.f2419a = context;
    }

    @RequiresApi(api = 20)
    public boolean a() throws Throwable {
        SensorManager sensorManager = (SensorManager) this.f2419a.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(21);
        if (defaultSensor == null) {
            return true;
        }
        sensorManager.registerListener(b, defaultSensor, 3);
        sensorManager.unregisterListener(b);
        return true;
    }
}
