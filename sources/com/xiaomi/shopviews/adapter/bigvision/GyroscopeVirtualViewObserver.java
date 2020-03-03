package com.xiaomi.shopviews.adapter.bigvision;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Iterator;
import java.util.LinkedList;

public class GyroscopeVirtualViewObserver implements SensorEventListener {
    private static final float b = 1.0E-9f;

    /* renamed from: a  reason: collision with root package name */
    private SensorManager f13077a;
    private long c;
    private double d;
    private double e;
    private double f = 1.0471975511965976d;
    private LinkedList<GravityImageVirtualView> g = new LinkedList<>();

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void a(Context context) {
        if (this.f13077a == null) {
            this.f13077a = (SensorManager) context.getSystemService("sensor");
        }
        this.f13077a.registerListener(this, this.f13077a.getDefaultSensor(4), 0);
        this.c = 0;
        this.e = 0.0d;
        this.d = 0.0d;
    }

    public void a() {
        if (this.f13077a != null) {
            this.f13077a.unregisterListener(this);
            this.f13077a = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(GravityImageVirtualView gravityImageVirtualView) {
        if (gravityImageVirtualView != null && !this.g.contains(gravityImageVirtualView)) {
            this.g.addFirst(gravityImageVirtualView);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float abs = Math.abs(sensorEvent.values[0]);
        float abs2 = Math.abs(sensorEvent.values[1]);
        float abs3 = Math.abs(sensorEvent.values[2]);
        if (sensorEvent.timestamp - this.c >= 50) {
            if (this.c == 0) {
                this.c = sensorEvent.timestamp;
            } else if (((double) (abs + abs2 + abs3)) < 0.2d) {
                return;
            }
            float f2 = ((float) (sensorEvent.timestamp - this.c)) * b;
            double d2 = this.d;
            double d3 = (double) (sensorEvent.values[1] * f2);
            Double.isNaN(d3);
            this.d = d2 + d3;
            if (this.d > this.f) {
                this.d = this.f;
            } else if (this.d < (-this.f)) {
                this.d = -this.f;
            }
            double d4 = this.e;
            double d5 = (double) (sensorEvent.values[0] * f2);
            Double.isNaN(d5);
            this.e = d4 + d5;
            if (this.e > this.f) {
                this.e = this.f;
            } else if (this.e < (-this.f)) {
                this.e = -this.f;
            }
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                GravityImageVirtualView gravityImageVirtualView = (GravityImageVirtualView) it.next();
                if (gravityImageVirtualView != null) {
                    gravityImageVirtualView.updateProgress((float) (this.e / this.f), (float) (this.d / this.f));
                }
            }
            this.c = sensorEvent.timestamp;
        }
    }

    public void a(double d2) {
        if (d2 <= 0.0d || d2 > 1.5707963267948966d) {
            throw new IllegalArgumentException("The maxRotateRadian must be between (0, π/2].");
        }
        this.f = d2;
    }
}
