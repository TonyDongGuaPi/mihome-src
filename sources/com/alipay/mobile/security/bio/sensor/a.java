package com.alipay.mobile.security.bio.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.alipay.mobile.security.bio.sensor.SensorCollectors;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.taobao.weex.el.parse.Operators;

class a implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    String f1001a;
    int b;
    private Object c = new Object();
    private String d;
    private Sensor e;
    private SensorManager f;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public a(SensorManager sensorManager, SensorCollectors.SensorType sensorType, int i) {
        e();
        if (sensorManager != null && sensorType != null) {
            this.f = sensorManager;
            this.e = sensorManager.getDefaultSensor(sensorType.getmSensorType());
            this.f1001a = sensorType.getSensorName();
            this.b = sensorType.getmSensorType();
            if (this.e == null) {
                BioLog.i("SensorCollectWorker: " + sensorType.getSensorName() + " 注册失败.［" + System.currentTimeMillis() + Operators.ARRAY_END_STR);
                return;
            }
            BioLog.i("SensorCollectWorker: " + sensorType.getSensorName() + " 注册成功.［" + System.currentTimeMillis() + Operators.ARRAY_END_STR);
        }
    }

    public void a() {
        if (this.e != null && this.f != null) {
            this.f.registerListener(this, this.e, 50000);
            BioLog.i("SensorCollectWorker: " + this.f1001a + " 开始采集数据.［" + System.currentTimeMillis() + Operators.ARRAY_END_STR);
        }
    }

    public void b() {
        if (this.e != null && this.f != null) {
            this.f.unregisterListener(this, this.e);
            this.e = null;
            BioLog.i("SensorCollectWorker: " + this.f1001a + " 停止采集数据.［" + System.currentTimeMillis() + Operators.ARRAY_END_STR);
        }
    }

    public int c() {
        if (this.e == null) {
            return -1;
        }
        return this.b;
    }

    public String d() {
        String str;
        synchronized (this.c) {
            str = this.d;
        }
        return str;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null && sensorEvent.values != null) {
            StringBuilder sb = new StringBuilder();
            try {
                int length = sensorEvent.values.length;
                sb.append(Operators.ARRAY_START_STR);
                int i = 0;
                while (i < length) {
                    sb.append((int) (sensorEvent.values[i] * 100.0f));
                    i++;
                    if (i < length) {
                        sb.append(",");
                    }
                }
                sb.append(Operators.ARRAY_END_STR);
            } catch (Throwable unused) {
            }
            synchronized (this.c) {
                this.d = sb.toString();
            }
        }
    }

    private void e() {
        this.d = "[,,]";
        this.e = null;
    }
}
