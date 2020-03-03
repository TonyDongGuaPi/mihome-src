package com.xiaomi.smarthome.scene.geofence.manager.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.newui.amappoi.LatLngEntity;
import com.xiaomi.smarthome.operation.js_sdk.location.GCJ2WGSConverter;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.geofence.manager.GrayLogDelegate;

public class GeoFenceItem implements Parcelable {
    public static final Parcelable.Creator<GeoFenceItem> CREATOR = new Parcelable.Creator<GeoFenceItem>() {
        /* renamed from: a */
        public GeoFenceItem createFromParcel(Parcel parcel) {
            return new GeoFenceItem(parcel);
        }

        /* renamed from: a */
        public GeoFenceItem[] newArray(int i) {
            return new GeoFenceItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f21574a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 200;
    public static final int e = 1000;
    private final double f;
    private final double g;
    private final double h;
    private long i;
    private final int j;

    public int describeContents() {
        return 0;
    }

    public int a() {
        return this.j;
    }

    public GeoFenceItem(double d2, double d3, int i2) {
        this.f = d2;
        this.g = d3;
        this.j = i2;
        this.h = 200.0d;
    }

    public GeoFenceItem(double d2, double d3, double d4, int i2) {
        this.f = d2;
        this.g = d3;
        this.h = d4;
        this.j = i2;
    }

    public double b() {
        return this.f;
    }

    public double c() {
        return this.g;
    }

    public double d() {
        return this.h;
    }

    public void e() {
        this.i = System.currentTimeMillis();
        GrayLogDelegate.a("GeoFenceItem", "timeStamp is " + this.i);
    }

    public void a(long j2) {
        this.i = j2;
    }

    public long f() {
        return this.i;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeDouble(this.f);
        parcel.writeDouble(this.g);
        parcel.writeDouble(this.h);
        parcel.writeInt(this.j);
    }

    protected GeoFenceItem(Parcel parcel) {
        this.f = parcel.readDouble();
        this.g = parcel.readDouble();
        this.h = parcel.readDouble();
        this.j = parcel.readInt();
    }

    public static GeoFenceItem a(SceneApi.ConditionIZatGeoFencing conditionIZatGeoFencing) {
        if (conditionIZatGeoFencing == null || TextUtils.isEmpty(conditionIZatGeoFencing.q)) {
            return null;
        }
        LatLngEntity c2 = GCJ2WGSConverter.c(conditionIZatGeoFencing.r, conditionIZatGeoFencing.s);
        GrayLogDelegate.a("GCJ2WGS", "lat:" + c2.a() + "  ,lon: " + c2.b());
        if (TextUtils.equals(SceneApi.ConditionIZatGeoFencing.l, conditionIZatGeoFencing.q)) {
            return new GeoFenceItem(c2.a(), c2.b(), (double) conditionIZatGeoFencing.t, 1);
        }
        if (TextUtils.equals(SceneApi.ConditionIZatGeoFencing.m, conditionIZatGeoFencing.q)) {
            return new GeoFenceItem(c2.a(), c2.b(), (double) conditionIZatGeoFencing.t, 2);
        }
        return null;
    }
}
