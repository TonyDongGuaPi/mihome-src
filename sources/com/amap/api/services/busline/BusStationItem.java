package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class BusStationItem implements Parcelable {
    public static final Parcelable.Creator<BusStationItem> CREATOR = new Parcelable.Creator<BusStationItem>() {
        /* renamed from: a */
        public BusStationItem[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public BusStationItem createFromParcel(Parcel parcel) {
            return new BusStationItem(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4434a;
    private String b;
    private LatLonPoint c;
    private String d;
    private String e;
    private List<BusLineItem> f;

    public int describeContents() {
        return 0;
    }

    public BusStationItem() {
        this.f = new ArrayList();
    }

    public String getBusStationId() {
        return this.f4434a;
    }

    public void setBusStationId(String str) {
        this.f4434a = str;
    }

    public String getBusStationName() {
        return this.b;
    }

    public void setBusStationName(String str) {
        this.b = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.c;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public String getCityCode() {
        return this.d;
    }

    public void setCityCode(String str) {
        this.d = str;
    }

    public String getAdCode() {
        return this.e;
    }

    public void setAdCode(String str) {
        this.e = str;
    }

    public List<BusLineItem> getBusLineItems() {
        return this.f;
    }

    public void setBusLineItems(List<BusLineItem> list) {
        this.f = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusStationItem busStationItem = (BusStationItem) obj;
        if (this.f4434a == null) {
            if (busStationItem.f4434a != null) {
                return false;
            }
        } else if (!this.f4434a.equals(busStationItem.f4434a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        if (this.f4434a == null) {
            i = 0;
        } else {
            i = this.f4434a.hashCode();
        }
        return 31 + i;
    }

    public String toString() {
        return "BusStationName: " + this.b + " LatLonPoint: " + this.c.toString() + " BusLines: " + a(this.f) + " CityCode: " + this.d + " AdCode: " + this.e;
    }

    private String a(List<BusLineItem> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i).getBusLineName());
                if (i < list.size() - 1) {
                    stringBuffer.append("|");
                }
            }
        }
        return stringBuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.f4434a);
        parcel.writeValue(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeList(this.f);
    }

    private BusStationItem(Parcel parcel) {
        this.f = new ArrayList();
        this.b = parcel.readString();
        this.f4434a = parcel.readString();
        this.c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readArrayList(BusLineItem.class.getClassLoader());
    }
}
