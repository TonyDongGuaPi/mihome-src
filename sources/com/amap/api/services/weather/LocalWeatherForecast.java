package com.amap.api.services.weather;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class LocalWeatherForecast implements Parcelable {
    public static final Parcelable.Creator<LocalWeatherForecast> CREATOR = new Parcelable.Creator<LocalWeatherForecast>() {
        /* renamed from: a */
        public LocalWeatherForecast[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public LocalWeatherForecast createFromParcel(Parcel parcel) {
            return new LocalWeatherForecast(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4551a;
    private String b;
    private String c;
    private String d;
    private List<LocalDayWeatherForecast> e = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public LocalWeatherForecast() {
    }

    public String getProvince() {
        return this.f4551a;
    }

    public void setProvince(String str) {
        this.f4551a = str;
    }

    public String getCity() {
        return this.b;
    }

    public void setCity(String str) {
        this.b = str;
    }

    public String getAdCode() {
        return this.c;
    }

    public void setAdCode(String str) {
        this.c = str;
    }

    public String getReportTime() {
        return this.d;
    }

    public void setReportTime(String str) {
        this.d = str;
    }

    public List<LocalDayWeatherForecast> getWeatherForecast() {
        return this.e;
    }

    public void setWeatherForecast(List<LocalDayWeatherForecast> list) {
        this.e = list;
    }

    public LocalWeatherForecast(Parcel parcel) {
        this.f4551a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readArrayList(LocalWeatherForecast.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4551a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeList(this.e);
    }
}
