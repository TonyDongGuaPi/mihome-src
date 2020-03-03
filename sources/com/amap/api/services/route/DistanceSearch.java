package com.amap.api.services.route;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.bc;
import com.amap.api.services.a.s;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.IDistanceSearch;
import java.util.ArrayList;
import java.util.List;

public class DistanceSearch {
    public static final int TYPE_DISTANCE = 0;
    public static final int TYPE_DRIVING_DISTANCE = 1;

    /* renamed from: a  reason: collision with root package name */
    private IDistanceSearch f4492a;

    public interface OnDistanceSearchListener {
        void onDistanceSearched(DistanceResult distanceResult, int i);
    }

    public DistanceSearch(Context context) {
        if (this.f4492a == null) {
            try {
                this.f4492a = new bc(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setDistanceSearchListener(OnDistanceSearchListener onDistanceSearchListener) {
        if (this.f4492a != null) {
            this.f4492a.setDistanceSearchListener(onDistanceSearchListener);
        }
    }

    public DistanceResult calculateRouteDistance(DistanceQuery distanceQuery) throws AMapException {
        if (this.f4492a != null) {
            return this.f4492a.calculateRouteDistance(distanceQuery);
        }
        return null;
    }

    public void calculateRouteDistanceAsyn(DistanceQuery distanceQuery) {
        if (this.f4492a != null) {
            this.f4492a.calculateRouteDistanceAsyn(distanceQuery);
        }
    }

    public static class DistanceQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<DistanceQuery> CREATOR = new Parcelable.Creator<DistanceQuery>() {
            /* renamed from: a */
            public DistanceQuery createFromParcel(Parcel parcel) {
                return new DistanceQuery(parcel);
            }

            /* renamed from: a */
            public DistanceQuery[] newArray(int i) {
                return new DistanceQuery[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private int f4493a = 1;
        private List<LatLonPoint> b = new ArrayList();
        private LatLonPoint c;

        public int describeContents() {
            return 0;
        }

        public DistanceQuery() {
        }

        protected DistanceQuery(Parcel parcel) {
            this.f4493a = parcel.readInt();
            this.b = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            this.c = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        }

        public DistanceQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                s.a(e, "DistanceSearch", "DistanceQueryclone");
            }
            DistanceQuery distanceQuery = new DistanceQuery();
            distanceQuery.setType(this.f4493a);
            distanceQuery.setOrigins(this.b);
            distanceQuery.setDestination(this.c);
            return distanceQuery;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f4493a);
            parcel.writeTypedList(this.b);
            parcel.writeParcelable(this.c, i);
        }

        public int getType() {
            return this.f4493a;
        }

        public List<LatLonPoint> getOrigins() {
            return this.b;
        }

        public LatLonPoint getDestination() {
            return this.c;
        }

        public void setType(int i) {
            this.f4493a = i;
        }

        public void setOrigins(List<LatLonPoint> list) {
            if (list != null) {
                this.b = list;
            }
        }

        public void addOrigins(LatLonPoint... latLonPointArr) {
            for (LatLonPoint latLonPoint : latLonPointArr) {
                if (latLonPoint != null) {
                    this.b.add(latLonPoint);
                }
            }
        }

        public void setDestination(LatLonPoint latLonPoint) {
            this.c = latLonPoint;
        }
    }
}
