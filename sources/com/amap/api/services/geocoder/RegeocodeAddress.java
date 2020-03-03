package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.ArrayList;
import java.util.List;

public final class RegeocodeAddress implements Parcelable {
    public static final Parcelable.Creator<RegeocodeAddress> CREATOR = new Parcelable.Creator<RegeocodeAddress>() {
        /* renamed from: a */
        public RegeocodeAddress[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public RegeocodeAddress createFromParcel(Parcel parcel) {
            return new RegeocodeAddress(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4462a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private StreetNumber h;
    private String i;
    private String j;
    private String k;
    private List<RegeocodeRoad> l;
    private List<Crossroad> m;
    private List<PoiItem> n;
    private List<BusinessArea> o;
    private List<AoiItem> p;
    private String q;

    public int describeContents() {
        return 0;
    }

    public RegeocodeAddress() {
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
    }

    public String getFormatAddress() {
        return this.f4462a;
    }

    public void setFormatAddress(String str) {
        this.f4462a = str;
    }

    public String getProvince() {
        return this.b;
    }

    public void setProvince(String str) {
        this.b = str;
    }

    public String getCity() {
        return this.c;
    }

    public void setCity(String str) {
        this.c = str;
    }

    public String getCityCode() {
        return this.i;
    }

    public void setCityCode(String str) {
        this.i = str;
    }

    public String getAdCode() {
        return this.j;
    }

    public void setAdCode(String str) {
        this.j = str;
    }

    public String getDistrict() {
        return this.d;
    }

    public void setDistrict(String str) {
        this.d = str;
    }

    public String getTownship() {
        return this.e;
    }

    public void setTownship(String str) {
        this.e = str;
    }

    public String getNeighborhood() {
        return this.f;
    }

    public void setNeighborhood(String str) {
        this.f = str;
    }

    public String getBuilding() {
        return this.g;
    }

    public void setBuilding(String str) {
        this.g = str;
    }

    public StreetNumber getStreetNumber() {
        return this.h;
    }

    public void setStreetNumber(StreetNumber streetNumber) {
        this.h = streetNumber;
    }

    public List<RegeocodeRoad> getRoads() {
        return this.l;
    }

    public void setRoads(List<RegeocodeRoad> list) {
        this.l = list;
    }

    public List<PoiItem> getPois() {
        return this.n;
    }

    public void setPois(List<PoiItem> list) {
        this.n = list;
    }

    public List<Crossroad> getCrossroads() {
        return this.m;
    }

    public void setCrossroads(List<Crossroad> list) {
        this.m = list;
    }

    public List<BusinessArea> getBusinessAreas() {
        return this.o;
    }

    public void setBusinessAreas(List<BusinessArea> list) {
        this.o = list;
    }

    public List<AoiItem> getAois() {
        return this.p;
    }

    public void setAois(List<AoiItem> list) {
        this.p = list;
    }

    public String getTowncode() {
        return this.k;
    }

    public void setTowncode(String str) {
        this.k = str;
    }

    public void setCountry(String str) {
        this.q = str;
    }

    public String getCountry() {
        return this.q;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f4462a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeValue(this.h);
        parcel.writeList(this.l);
        parcel.writeList(this.m);
        parcel.writeList(this.n);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeList(this.o);
        parcel.writeList(this.p);
        parcel.writeString(this.k);
        parcel.writeString(this.q);
    }

    private RegeocodeAddress(Parcel parcel) {
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.f4462a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = (StreetNumber) parcel.readValue(StreetNumber.class.getClassLoader());
        this.l = parcel.readArrayList(Road.class.getClassLoader());
        this.m = parcel.readArrayList(Crossroad.class.getClassLoader());
        this.n = parcel.readArrayList(PoiItem.class.getClassLoader());
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.o = parcel.readArrayList(BusinessArea.class.getClassLoader());
        this.p = parcel.readArrayList(AoiItem.class.getClassLoader());
        this.k = parcel.readString();
        this.q = parcel.readString();
    }
}