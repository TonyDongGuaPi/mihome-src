package com.drew.metadata.photoshop;

import java.util.ArrayList;

public class Subpath {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<Knot> f5265a;
    private final String b;

    public Subpath() {
        this("");
    }

    public Subpath(String str) {
        this.f5265a = new ArrayList<>();
        this.b = str;
    }

    public void a(Knot knot) {
        this.f5265a.add(knot);
    }

    public int a() {
        return this.f5265a.size();
    }

    public Iterable<Knot> b() {
        return this.f5265a;
    }

    public String c() {
        return this.b;
    }
}
