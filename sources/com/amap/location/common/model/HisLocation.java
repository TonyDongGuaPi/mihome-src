package com.amap.location.common.model;

import android.location.Location;
import android.text.TextUtils;
import java.util.Objects;

public class HisLocation {
    private static final Double h = Double.valueOf(1.0E7d);

    /* renamed from: a  reason: collision with root package name */
    public long f4588a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public static HisLocation a(Location location) {
        if (location == null) {
            return null;
        }
        HisLocation hisLocation = new HisLocation();
        hisLocation.f4588a = System.currentTimeMillis();
        hisLocation.b = (int) Math.round(location.getLongitude() * h.doubleValue());
        hisLocation.c = (int) Math.round(location.getLatitude() * h.doubleValue());
        hisLocation.d = Math.round(location.getAccuracy());
        hisLocation.e = 1;
        hisLocation.f = 63;
        hisLocation.g = 0;
        return hisLocation;
    }

    public static HisLocation a(AmapLoc amapLoc) {
        if (amapLoc == null) {
            return null;
        }
        HisLocation hisLocation = new HisLocation();
        hisLocation.f4588a = System.currentTimeMillis();
        hisLocation.b = (int) Math.round(amapLoc.c() * h.doubleValue());
        hisLocation.c = (int) Math.round(amapLoc.d() * h.doubleValue());
        hisLocation.d = Math.round(amapLoc.g());
        hisLocation.e = AmapLoc.a(amapLoc) + 1;
        try {
            hisLocation.f = Integer.parseInt(amapLoc.o());
        } catch (Exception unused) {
            hisLocation.f = 63;
        }
        try {
            hisLocation.g = Integer.parseInt(amapLoc.m());
        } catch (Exception unused2) {
            hisLocation.g = 0;
        }
        return hisLocation;
    }

    public static HisLocation a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] split = str.split(",");
            HisLocation hisLocation = new HisLocation();
            hisLocation.f4588a = Long.parseLong(split[0]);
            hisLocation.b = Integer.parseInt(split[1]);
            hisLocation.c = Integer.parseInt(split[2]);
            hisLocation.d = Integer.parseInt(split[3]);
            hisLocation.e = Integer.parseInt(split[4]);
            hisLocation.f = Integer.parseInt(split[5]);
            hisLocation.g = Integer.parseInt(split[6]);
            return hisLocation;
        } catch (Exception unused) {
            return null;
        }
    }

    public double a(HisLocation hisLocation) {
        if (hisLocation == null) {
            return 0.0d;
        }
        float[] fArr = new float[1];
        double d2 = (double) this.c;
        double doubleValue = h.doubleValue();
        Double.isNaN(d2);
        double d3 = d2 / doubleValue;
        double d4 = (double) this.b;
        double doubleValue2 = h.doubleValue();
        Double.isNaN(d4);
        double d5 = d4 / doubleValue2;
        double d6 = (double) hisLocation.c;
        double doubleValue3 = h.doubleValue();
        Double.isNaN(d6);
        double d7 = d6 / doubleValue3;
        double d8 = (double) hisLocation.b;
        double doubleValue4 = h.doubleValue();
        Double.isNaN(d8);
        Location.distanceBetween(d3, d5, d7, d8 / doubleValue4, fArr);
        return (double) fArr[0];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            HisLocation hisLocation = (HisLocation) obj;
            return this.b == hisLocation.b && this.c == hisLocation.c && this.d == hisLocation.d;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)});
    }

    public String toString() {
        return this.f4588a + "," + this.b + "," + this.c + "," + this.d + "," + this.e + "," + this.f + "," + this.g;
    }
}
