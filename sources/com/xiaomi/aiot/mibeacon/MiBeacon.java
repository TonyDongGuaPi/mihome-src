package com.xiaomi.aiot.mibeacon;

import androidx.annotation.NonNull;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.aiot.mibeacon.distance.DistanceCalculator;
import com.xiaomi.aiot.mibeacon.distance.DistanceLevelProcesser;
import com.xiaomi.aiot.mibeacon.distance.KalmanRssiProcessor;
import com.xiaomi.aiot.mibeacon.distance.RssiProcessor;
import com.xiaomi.aiot.mibeacon.distance.SimpleDistanceLevelProcesser;

public class MiBeacon {
    private static DistanceCalculator j;

    /* renamed from: a  reason: collision with root package name */
    private double f9967a = Double.MAX_VALUE;
    /* access modifiers changed from: private */
    public double b = Double.MAX_VALUE;
    /* access modifiers changed from: private */
    public int c = Integer.MIN_VALUE;
    /* access modifiers changed from: private */
    public String d = "";
    /* access modifiers changed from: private */
    public String e = "";
    private int f = 1;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public int i;
    private RssiProcessor k = new KalmanRssiProcessor();
    private DistanceLevelProcesser l = new SimpleDistanceLevelProcesser(this);
    /* access modifiers changed from: private */
    public String m = "";
    private DistanceLevel n;

    protected MiBeacon() {
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public int c() {
        return this.i;
    }

    public String d() {
        return this.d;
    }

    public double e() {
        return this.b;
    }

    public String f() {
        return this.e;
    }

    public double g() {
        return this.f9967a;
    }

    public int h() {
        return this.f;
    }

    public DistanceLevel i() {
        return this.n;
    }

    /* access modifiers changed from: private */
    public void j() {
        if (j != null) {
            this.f9967a = j.a(this.c, this.b);
            this.n = this.l.a();
        }
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("btName[" + this.m + "] ");
        sb.append("btAddress[" + this.d + "] ");
        sb.append("packetCount[" + this.f + Operators.ARRAY_END_STR);
        sb.append("wifiMac[" + this.e + "] ");
        sb.append("rssi[" + String.format("%.2f", new Object[]{Double.valueOf(this.b)}) + Operators.ARRAY_END_STR);
        sb.append("txPower[" + this.c + Operators.ARRAY_END_STR);
        sb.append("distance[" + String.format("%.2f", new Object[]{Double.valueOf(this.f9967a)}) + Operators.ARRAY_END_STR);
        sb.append("disLevel[" + this.n + Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public void a(MiBeacon miBeacon) {
        if (this.k != null) {
            this.b = this.k.a(miBeacon.b);
        } else {
            this.b = miBeacon.b;
        }
        this.f++;
        j();
    }

    public static void a(DistanceCalculator distanceCalculator) {
        j = distanceCalculator;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private MiBeacon f9968a = new MiBeacon();

        public MiBeacon a() {
            if (!(this.f9968a.c == Integer.MAX_VALUE || Double.compare(this.f9968a.b, Double.MAX_VALUE) == 0)) {
                this.f9968a.j();
            }
            return this.f9968a;
        }

        public Builder a(double d) {
            double unused = this.f9968a.b = d;
            return this;
        }

        public Builder a(int i) {
            int unused = this.f9968a.c = i;
            return this;
        }

        public Builder a(String str) {
            String unused = this.f9968a.m = str;
            return this;
        }

        public Builder b(String str) {
            String unused = this.f9968a.d = str;
            return this;
        }

        public Builder c(String str) {
            String unused = this.f9968a.e = str;
            return this;
        }

        public Builder b(int i) {
            int unused = this.f9968a.g = i;
            return this;
        }

        public Builder c(int i) {
            int unused = this.f9968a.h = i;
            return this;
        }

        public Builder d(int i) {
            int unused = this.f9968a.i = i;
            return this;
        }
    }

    public enum DistanceLevel {
        Immediate("Immediate"),
        Near("Near"),
        Far("Far"),
        Unkonwn("Unkonwn");
        
        private String info;

        private DistanceLevel(String str) {
            this.info = str;
        }

        public String toString() {
            return this.info;
        }
    }
}
