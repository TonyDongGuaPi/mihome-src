package com.drew.imaging;

public final class PhotographicConversions {

    /* renamed from: a  reason: collision with root package name */
    public static final double f5178a = Math.sqrt(2.0d);

    private PhotographicConversions() throws Exception {
        throw new Exception("Not intended for instantiation.");
    }

    public static double a(double d) {
        return Math.pow(f5178a, d);
    }

    public static double b(double d) {
        return (double) ((float) (1.0d / Math.exp(d * Math.log(2.0d))));
    }
}
