package com.reactnative.camera.RCTCamera;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.facebook.react.bridge.ReactApplicationContext;

public class RCTSensorOrientationChecker {

    /* renamed from: a  reason: collision with root package name */
    int f8665a = 0;
    private SensorEventListener b = new Listener();
    private SensorManager c;
    /* access modifiers changed from: private */
    public RCTSensorOrientationListener d = null;

    public RCTSensorOrientationChecker(ReactApplicationContext reactApplicationContext) {
        this.c = (SensorManager) reactApplicationContext.getSystemService("sensor");
    }

    public void a() {
        this.c.registerListener(this.b, this.c.getDefaultSensor(1), 3);
    }

    public void b() {
        this.c.unregisterListener(this.b);
    }

    private class Listener implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        private Listener() {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            if (f < 5.0f && f > -5.0f && f2 > 5.0f) {
                RCTSensorOrientationChecker.this.f8665a = 0;
            } else if (f < -5.0f && f2 < 5.0f && f2 > -5.0f) {
                RCTSensorOrientationChecker.this.f8665a = 3;
            } else if (f < 5.0f && f > -5.0f && f2 < -5.0f) {
                RCTSensorOrientationChecker.this.f8665a = 2;
            } else if (f > 5.0f && f2 < 5.0f && f2 > -5.0f) {
                RCTSensorOrientationChecker.this.f8665a = 1;
            }
            if (RCTSensorOrientationChecker.this.d != null) {
                RCTSensorOrientationChecker.this.d.a();
            }
        }
    }

    public int c() {
        return this.f8665a;
    }

    public void a(RCTSensorOrientationListener rCTSensorOrientationListener) {
        this.d = rCTSensorOrientationListener;
    }

    public void d() {
        this.d = null;
    }
}
