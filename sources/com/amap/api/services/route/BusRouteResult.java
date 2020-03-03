package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

public class BusRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<BusRouteResult> CREATOR = new Parcelable.Creator<BusRouteResult>() {
        /* renamed from: a */
        public BusRouteResult createFromParcel(Parcel parcel) {
            return new BusRouteResult(parcel);
        }

        /* renamed from: a */
        public BusRouteResult[] newArray(int i) {
            return new BusRouteResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private float f4488a;
    private List<BusPath> b = new ArrayList();
    private RouteSearch.BusRouteQuery c;

    public int describeContents() {
        return 0;
    }

    public float getTaxiCost() {
        return this.f4488a;
    }

    public void setTaxiCost(float f) {
        this.f4488a = f;
    }

    public List<BusPath> getPaths() {
        return this.b;
    }

    public void setPaths(List<BusPath> list) {
        this.b = list;
    }

    public RouteSearch.BusRouteQuery getBusQuery() {
        return this.c;
    }

    public void setBusQuery(RouteSearch.BusRouteQuery busRouteQuery) {
        this.c = busRouteQuery;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f4488a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public BusRouteResult(Parcel parcel) {
        super(parcel);
        this.f4488a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(BusPath.CREATOR);
        this.c = (RouteSearch.BusRouteQuery) parcel.readParcelable(RouteSearch.BusRouteQuery.class.getClassLoader());
    }

    public BusRouteResult() {
    }
}
