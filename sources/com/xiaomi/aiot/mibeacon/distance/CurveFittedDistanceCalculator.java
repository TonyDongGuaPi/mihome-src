package com.xiaomi.aiot.mibeacon.distance;

import com.xiaomi.aiot.mibeacon.logging.LogManager;

public class CurveFittedDistanceCalculator implements DistanceCalculator {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9975a = "CurveFittedDistanceCalculator";
    private double b;
    private double c;
    private double d;

    public CurveFittedDistanceCalculator(double d2, double d3, double d4) {
        this.b = d2;
        this.c = d3;
        this.d = d4;
    }

    public double a(int i, double d2) {
        double d3;
        if (d2 == 0.0d) {
            return -1.0d;
        }
        LogManager.b(f9975a, "calculating distance based on mRssi of %s and txPower of %s", Double.valueOf(d2), Integer.valueOf(i));
        double d4 = (double) i;
        Double.isNaN(d4);
        double d5 = d2 / d4;
        if (d5 < 1.0d) {
            d3 = Math.pow(d5, 10.0d);
        } else {
            d3 = this.d + (this.b * Math.pow(d5, this.c));
        }
        LogManager.b(f9975a, "avg mRssi: %s distance: %s", Double.valueOf(d2), Double.valueOf(d3));
        return d3;
    }
}
