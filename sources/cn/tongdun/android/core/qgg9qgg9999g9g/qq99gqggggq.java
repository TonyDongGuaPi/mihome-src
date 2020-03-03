package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import cn.tongdun.android.core.q9gqqq99999qq.qq9q9ggg;

final class qq99gqggggq implements SensorEventListener {
    final /* synthetic */ qq9q9ggg gqg9qq9gqq9q9q;
    final /* synthetic */ SensorManager qgg9qgg9999g9g;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (sensorEvent.sensor.getType() == 4) {
            this.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(fArr[0], fArr[1], fArr[2]);
        }
        this.qgg9qgg9999g9g.unregisterListener(this);
    }
}
