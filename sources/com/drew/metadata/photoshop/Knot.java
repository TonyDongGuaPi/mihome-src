package com.drew.metadata.photoshop;

public class Knot {

    /* renamed from: a  reason: collision with root package name */
    private final double[] f5262a = new double[6];
    private final String b;

    public Knot(String str) {
        this.b = str;
    }

    public void a(int i, double d) {
        this.f5262a[i] = d;
    }

    public double a(int i) {
        return this.f5262a[i];
    }

    public String a() {
        return this.b;
    }
}
