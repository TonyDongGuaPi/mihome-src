package com.xiaomi.smarthome.operation.js_sdk.location;

import com.xiaomi.smarthome.newui.amappoi.LatLngEntity;

public class GCJ2WGSConverter {

    /* renamed from: a  reason: collision with root package name */
    public static double f21086a = 3.141592653589793d;
    public static double b = 6378245.0d;
    public static double c = 0.006693421622965943d;

    private static boolean d(double d, double d2) {
        return d2 < 72.004d || d2 > 137.8347d || d < 0.8293d || d > 55.8271d;
    }

    private GCJ2WGSConverter() {
    }

    public static LatLngEntity a(double d, double d2) {
        double d3 = d;
        double d4 = d2;
        if (d(d, d2)) {
            return new LatLngEntity(d3, d4);
        }
        double d5 = d4 - 105.0d;
        double d6 = d3 - 35.0d;
        double e = e(d5, d6);
        double f = f(d5, d6);
        double d7 = (d3 / 180.0d) * f21086a;
        double sin = Math.sin(d7);
        double d8 = 1.0d - ((c * sin) * sin);
        double sqrt = Math.sqrt(d8);
        return new LatLngEntity(d3 + ((e * 180.0d) / (((b * (1.0d - c)) / (d8 * sqrt)) * f21086a)), d4 + ((f * 180.0d) / (((b / sqrt) * Math.cos(d7)) * f21086a)));
    }

    public static LatLngEntity b(double d, double d2) {
        LatLngEntity g = g(d, d2);
        return new LatLngEntity((d2 * 2.0d) - g.b(), (d * 2.0d) - g.a());
    }

    private static double e(double d, double d2) {
        double d3 = d * 2.0d;
        return -100.0d + d3 + (d2 * 3.0d) + (d2 * 0.2d * d2) + (0.1d * d * d2) + (Math.sqrt(Math.abs(d)) * 0.2d) + ((((Math.sin((d * 6.0d) * f21086a) * 20.0d) + (Math.sin(d3 * f21086a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f21086a * d2) * 20.0d) + (Math.sin((d2 / 3.0d) * f21086a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * f21086a) * 160.0d) + (Math.sin((d2 * f21086a) / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    private static double f(double d, double d2) {
        double d3 = d * 0.1d;
        return d + 300.0d + (d2 * 2.0d) + (d3 * d) + (d3 * d2) + (Math.sqrt(Math.abs(d)) * 0.1d) + ((((Math.sin((6.0d * d) * f21086a) * 20.0d) + (Math.sin((d * 2.0d) * f21086a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f21086a * d) * 20.0d) + (Math.sin((d / 3.0d) * f21086a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d / 12.0d) * f21086a) * 150.0d) + (Math.sin((d / 30.0d) * f21086a) * 300.0d)) * 2.0d) / 3.0d);
    }

    private static LatLngEntity g(double d, double d2) {
        double d3 = d;
        double d4 = d2;
        if (d(d, d2)) {
            return new LatLngEntity(d3, d4);
        }
        double d5 = d4 - 105.0d;
        double d6 = d3 - 35.0d;
        double e = e(d5, d6);
        double f = f(d5, d6);
        double d7 = (d3 / 180.0d) * f21086a;
        double sin = Math.sin(d7);
        double d8 = 1.0d - ((c * sin) * sin);
        double sqrt = Math.sqrt(d8);
        return new LatLngEntity(d3 + ((e * 180.0d) / (((b * (1.0d - c)) / (d8 * sqrt)) * f21086a)), d4 + ((f * 180.0d) / (((b / sqrt) * Math.cos(d7)) * f21086a)));
    }

    public static LatLngEntity c(double d, double d2) {
        double d3 = d;
        double d4 = d2;
        if (d(d, d2)) {
            return new LatLngEntity(d3, d4);
        }
        double d5 = d4 - 105.0d;
        double d6 = d3 - 35.0d;
        double e = e(d5, d6);
        double f = f(d5, d6);
        double d7 = (d3 / 180.0d) * f21086a;
        double sin = Math.sin(d7);
        double d8 = 1.0d - ((c * sin) * sin);
        double sqrt = Math.sqrt(d8);
        return new LatLngEntity(d3 - ((e * 180.0d) / (((b * (1.0d - c)) / (d8 * sqrt)) * f21086a)), d4 - ((f * 180.0d) / (((b / sqrt) * Math.cos(d7)) * f21086a)));
    }
}
