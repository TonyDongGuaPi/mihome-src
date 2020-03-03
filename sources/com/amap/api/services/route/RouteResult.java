package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

public class RouteResult implements Parcelable {
    public static final Parcelable.Creator<RouteResult> CREATOR = new Parcelable.Creator<RouteResult>() {
        /* renamed from: a */
        public RouteResult createFromParcel(Parcel parcel) {
            return new RouteResult(parcel);
        }

        /* renamed from: a */
        public RouteResult[] newArray(int i) {
            return new RouteResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private LatLonPoint f4513a;
    private LatLonPoint b;

    public int describeContents() {
        return 0;
    }

    public LatLonPoint getStartPos() {
        return this.f4513a;
    }

    public void setStartPos(LatLonPoint latLonPoint) {
        this.f4513a = latLonPoint;
    }

    public LatLonPoint getTargetPos() {
        return this.b;
    }

    public void setTargetPos(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4513a, i);
        parcel.writeParcelable(this.b, i);
    }

    public RouteResult(Parcel parcel) {
        this.f4513a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public RouteResult() {
    }
}
