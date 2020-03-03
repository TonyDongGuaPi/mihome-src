package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

public class DriveRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<DriveRouteResult> CREATOR = new Parcelable.Creator<DriveRouteResult>() {
        /* renamed from: a */
        public DriveRouteResult createFromParcel(Parcel parcel) {
            return new DriveRouteResult(parcel);
        }

        /* renamed from: a */
        public DriveRouteResult[] newArray(int i) {
            return new DriveRouteResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private float f4500a;
    private List<DrivePath> b = new ArrayList();
    private RouteSearch.DriveRouteQuery c;

    public int describeContents() {
        return 0;
    }

    public float getTaxiCost() {
        return this.f4500a;
    }

    public void setTaxiCost(float f) {
        this.f4500a = f;
    }

    public List<DrivePath> getPaths() {
        return this.b;
    }

    public void setPaths(List<DrivePath> list) {
        this.b = list;
    }

    public RouteSearch.DriveRouteQuery getDriveQuery() {
        return this.c;
    }

    public void setDriveQuery(RouteSearch.DriveRouteQuery driveRouteQuery) {
        this.c = driveRouteQuery;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f4500a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public DriveRouteResult(Parcel parcel) {
        super(parcel);
        this.f4500a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(DrivePath.CREATOR);
        this.c = (RouteSearch.DriveRouteQuery) parcel.readParcelable(RouteSearch.DriveRouteQuery.class.getClassLoader());
    }

    public DriveRouteResult() {
    }
}
