package com.amap.api.services.weather;

import com.amap.api.services.a.s;

public class WeatherSearchQuery implements Cloneable {
    public static final int WEATHER_TYPE_FORECAST = 2;
    public static final int WEATHER_TYPE_LIVE = 1;

    /* renamed from: a  reason: collision with root package name */
    private String f4556a;
    private int b = 1;

    public WeatherSearchQuery(String str, int i) {
        this.f4556a = str;
        this.b = i;
    }

    public WeatherSearchQuery() {
    }

    public String getCity() {
        return this.f4556a;
    }

    public int getType() {
        return this.b;
    }

    public WeatherSearchQuery clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            s.a(e, "WeatherSearchQuery", "clone");
        }
        return new WeatherSearchQuery(this.f4556a, this.b);
    }
}
