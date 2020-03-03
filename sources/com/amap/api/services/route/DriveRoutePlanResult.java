package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

public class DriveRoutePlanResult extends RoutePlanResult implements Parcelable {
    public static final Parcelable.Creator<DriveRoutePlanResult> CREATOR = new Parcelable.Creator<DriveRoutePlanResult>() {
        /* renamed from: a */
        public DriveRoutePlanResult createFromParcel(Parcel parcel) {
            return new DriveRoutePlanResult(parcel);
        }

        /* renamed from: a */
        public DriveRoutePlanResult[] newArray(int i) {
            return new DriveRoutePlanResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private List<DrivePlanPath> f4499a = new ArrayList();
    private List<TimeInfo> b = new ArrayList();
    private RouteSearch.DrivePlanQuery c;

    public int describeContents() {
        return 0;
    }

    public List<DrivePlanPath> getPaths() {
        return this.f4499a;
    }

    public void setPaths(List<DrivePlanPath> list) {
        this.f4499a = list;
    }

    public List<TimeInfo> getTimeInfos() {
        return this.b;
    }

    public void setTimeInfos(List<TimeInfo> list) {
        this.b = list;
    }

    public void setDrivePlanQuery(RouteSearch.DrivePlanQuery drivePlanQuery) {
        this.c = drivePlanQuery;
        RouteSearch.FromAndTo fromAndTo = this.c.getFromAndTo();
        if (fromAndTo != null) {
            setStartPos(fromAndTo.getFrom());
            setTargetPos(fromAndTo.getTo());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f4499a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public DriveRoutePlanResult(Parcel parcel) {
        super(parcel);
        this.f4499a = parcel.createTypedArrayList(DrivePlanPath.CREATOR);
        this.b = parcel.createTypedArrayList(TimeInfo.CREATOR);
        this.c = (RouteSearch.DrivePlanQuery) parcel.readParcelable(RouteSearch.DrivePlanQuery.class.getClassLoader());
    }

    public DriveRoutePlanResult() {
    }
}
