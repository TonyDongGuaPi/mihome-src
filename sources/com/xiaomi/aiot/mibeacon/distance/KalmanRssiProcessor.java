package com.xiaomi.aiot.mibeacon.distance;

public class KalmanRssiProcessor implements RssiProcessor {

    /* renamed from: a  reason: collision with root package name */
    private static final double f9976a = 0.02d;
    private static final double b = 7.0d;
    private double c = 1.0d;
    private double d = -9999.0d;

    public double a(double d2) {
        if (this.d == -9999.0d) {
            this.d = d2;
        }
        double b2 = b(d2);
        this.d = b2;
        return b2;
    }

    private double b(double d2) {
        double d3 = this.d;
        double d4 = this.c + f9976a;
        double d5 = d4 / (b + d4);
        double d6 = d3 + ((d2 - d3) * d5);
        this.c = (1.0d - d5) * d4;
        this.d = d6;
        return d6;
    }
}
