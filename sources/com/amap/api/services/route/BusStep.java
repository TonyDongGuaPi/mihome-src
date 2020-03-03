package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class BusStep implements Parcelable {
    public static final Parcelable.Creator<BusStep> CREATOR = new Parcelable.Creator<BusStep>() {
        /* renamed from: a */
        public BusStep[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public BusStep createFromParcel(Parcel parcel) {
            return new BusStep(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private RouteBusWalkItem f4489a;
    private List<RouteBusLineItem> b = new ArrayList();
    private Doorway c;
    private Doorway d;
    private RouteRailwayItem e;
    private TaxiItem f;

    public int describeContents() {
        return 0;
    }

    public RouteBusWalkItem getWalk() {
        return this.f4489a;
    }

    public void setWalk(RouteBusWalkItem routeBusWalkItem) {
        this.f4489a = routeBusWalkItem;
    }

    @Deprecated
    public RouteBusLineItem getBusLine() {
        if (this.b == null || this.b.size() == 0) {
            return null;
        }
        return this.b.get(0);
    }

    public List<RouteBusLineItem> getBusLines() {
        return this.b;
    }

    @Deprecated
    public void setBusLine(RouteBusLineItem routeBusLineItem) {
        if (this.b != null) {
            if (this.b.size() == 0) {
                this.b.add(routeBusLineItem);
            }
            this.b.set(0, routeBusLineItem);
        }
    }

    public void setBusLines(List<RouteBusLineItem> list) {
        this.b = list;
    }

    public Doorway getEntrance() {
        return this.c;
    }

    public void setEntrance(Doorway doorway) {
        this.c = doorway;
    }

    public Doorway getExit() {
        return this.d;
    }

    public void setExit(Doorway doorway) {
        this.d = doorway;
    }

    public RouteRailwayItem getRailway() {
        return this.e;
    }

    public void setRailway(RouteRailwayItem routeRailwayItem) {
        this.e = routeRailwayItem;
    }

    public TaxiItem getTaxi() {
        return this.f;
    }

    public void setTaxi(TaxiItem taxiItem) {
        this.f = taxiItem;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4489a, i);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
        parcel.writeParcelable(this.d, i);
        parcel.writeParcelable(this.e, i);
        parcel.writeParcelable(this.f, i);
    }

    public BusStep(Parcel parcel) {
        this.f4489a = (RouteBusWalkItem) parcel.readParcelable(RouteBusWalkItem.class.getClassLoader());
        this.b = parcel.createTypedArrayList(RouteBusLineItem.CREATOR);
        this.c = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
        this.d = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
        this.e = (RouteRailwayItem) parcel.readParcelable(RouteRailwayItem.class.getClassLoader());
        this.f = (TaxiItem) parcel.readParcelable(TaxiItem.class.getClassLoader());
    }

    public BusStep() {
    }
}
