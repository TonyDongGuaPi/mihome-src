package com.alibaba.android.bindingx.core.internal;

import android.hardware.SensorEventListener;
import android.os.Handler;

interface SensorManagerProxy {
    void a(SensorEventListener sensorEventListener, int i);

    boolean a(SensorEventListener sensorEventListener, int i, int i2, Handler handler);
}
