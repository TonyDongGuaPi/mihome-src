package com.xiaomi.smarthome.newui.amappoi;

import com.amap.api.services.core.LatLonPoint;

public class LatLngEntity {

    /* renamed from: a  reason: collision with root package name */
    private final double f20450a;
    private final double b;

    public LatLngEntity(String str) {
        this.f20450a = Double.parseDouble(str.split(",")[0]);
        this.b = Double.parseDouble(str.split(",")[1]);
    }

    public LatLngEntity(double d, double d2) {
        this.f20450a = d2;
        this.b = d;
    }

    public LatLngEntity(LatLonPoint latLonPoint) {
        this.f20450a = latLonPoint.getLongitude();
        this.b = latLonPoint.getLatitude();
    }

    public double a() {
        return this.b;
    }

    public double b() {
        return this.f20450a;
    }
}
