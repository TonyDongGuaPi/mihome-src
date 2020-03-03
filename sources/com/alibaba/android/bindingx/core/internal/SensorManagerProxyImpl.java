package com.alibaba.android.bindingx.core.internal;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.alibaba.android.bindingx.core.LogProxy;
import java.util.List;

class SensorManagerProxyImpl implements SensorManagerProxy {

    /* renamed from: a  reason: collision with root package name */
    private final SensorManager f762a;

    SensorManagerProxyImpl(SensorManager sensorManager) {
        this.f762a = sensorManager;
    }

    public boolean a(SensorEventListener sensorEventListener, int i, int i2, Handler handler) {
        List<Sensor> sensorList = this.f762a.getSensorList(i);
        if (sensorList.isEmpty()) {
            return false;
        }
        return this.f762a.registerListener(sensorEventListener, sensorList.get(0), i2, handler);
    }

    public void a(SensorEventListener sensorEventListener, int i) {
        List<Sensor> sensorList = this.f762a.getSensorList(i);
        if (!sensorList.isEmpty()) {
            try {
                this.f762a.unregisterListener(sensorEventListener, sensorList.get(0));
            } catch (Throwable unused) {
                LogProxy.d("Failed to unregister device sensor " + sensorList.get(0).getName());
            }
        }
    }
}
