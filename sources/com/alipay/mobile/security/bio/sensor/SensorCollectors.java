package com.alipay.mobile.security.bio.sensor;

import android.content.Context;
import android.hardware.SensorManager;
import com.alipay.mobile.security.bio.utils.BioLog;
import java.util.ArrayList;
import java.util.List;

public class SensorCollectors {

    /* renamed from: a  reason: collision with root package name */
    SensorType[] f998a = {SensorType.ACCELEROMETER, SensorType.MAGNETIC, SensorType.GYROSCOPE};
    private List<a> b = new ArrayList();

    public enum SensorType {
        ACCELEROMETER(1, "Acceleration"),
        GYROSCOPE(4, "Gyroscope"),
        MAGNETIC(2, "Magnetic");
        
        private String mSensorName;
        private int mSensorType;

        private SensorType(int i, String str) {
            this.mSensorName = str;
            this.mSensorType = i;
        }

        public String getSensorName() {
            return this.mSensorName;
        }

        public int getmSensorType() {
            return this.mSensorType;
        }
    }

    public SensorCollectors(Context context) {
        reset(context);
    }

    public void reset(Context context) {
        if (context != null) {
            try {
                this.b.clear();
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                for (SensorType aVar : this.f998a) {
                    this.b.add(new a(sensorManager, aVar, 100));
                }
            } catch (Exception e) {
                BioLog.e(e.toString());
            }
        }
    }

    public void startListening() {
        for (a a2 : this.b) {
            a2.a();
        }
    }

    public void destroy() {
        for (a b2 : this.b) {
            b2.b();
        }
    }

    public SensorData getData() {
        SensorData sensorData = new SensorData();
        if (this.b != null) {
            for (a next : this.b) {
                if (next != null) {
                    String d = next.d();
                    if (next.c() == 1) {
                        sensorData.f1000a = d;
                    }
                    if (next.c() == 2) {
                        sensorData.c = d;
                    }
                    if (next.c() == 4) {
                        sensorData.b = d;
                    }
                }
            }
        }
        return sensorData;
    }
}
