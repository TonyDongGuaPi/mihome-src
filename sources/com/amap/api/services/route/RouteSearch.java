package com.amap.api.services.route;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.alipay.sdk.util.i;
import com.amap.api.services.a.bj;
import com.amap.api.services.a.s;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.IRouteSearch;
import java.util.ArrayList;
import java.util.List;

public class RouteSearch {
    public static final int BUS_COMFORTABLE = 4;
    public static final int BUS_DEFAULT = 0;
    public static final int BUS_LEASE_CHANGE = 2;
    public static final int BUS_LEASE_WALK = 3;
    public static final int BUS_NO_SUBWAY = 5;
    public static final int BUS_SAVE_MONEY = 1;
    public static final int BusComfortable = 4;
    public static final int BusDefault = 0;
    public static final int BusLeaseChange = 2;
    public static final int BusLeaseWalk = 3;
    public static final int BusNoSubway = 5;
    public static final int BusSaveMoney = 1;
    public static final int DRIVEING_PLAN_AVOID_CONGESTION_CHOICE_HIGHWAY = 9;
    public static final int DRIVEING_PLAN_AVOID_CONGESTION_FASTEST_SAVE_MONEY = 11;
    public static final int DRIVEING_PLAN_AVOID_CONGESTION_NO_HIGHWAY = 4;
    public static final int DRIVEING_PLAN_AVOID_CONGESTION_SAVE_MONEY = 6;
    public static final int DRIVEING_PLAN_AVOID_CONGESTION_SAVE_MONEY_NO_HIGHWAY = 7;
    public static final int DRIVEING_PLAN_CHOICE_HIGHWAY = 8;
    public static final int DRIVEING_PLAN_DEFAULT = 1;
    public static final int DRIVEING_PLAN_FASTEST_SHORTEST = 10;
    public static final int DRIVEING_PLAN_NO_HIGHWAY = 2;
    public static final int DRIVEING_PLAN_SAVE_MONEY = 3;
    public static final int DRIVEING_PLAN_SAVE_MONEY_NO_HIGHWAY = 5;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION = 12;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION_NO_HIGHWAY = 15;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION_NO_HIGHWAY_SAVE_MONEY = 18;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION_SAVE_MONEY = 17;
    public static final int DRIVING_MULTI_CHOICE_HIGHWAY = 19;
    public static final int DRIVING_MULTI_CHOICE_HIGHWAY_AVOID_CONGESTION = 20;
    public static final int DRIVING_MULTI_CHOICE_NO_HIGHWAY = 13;
    public static final int DRIVING_MULTI_CHOICE_SAVE_MONEY = 14;
    public static final int DRIVING_MULTI_CHOICE_SAVE_MONEY_NO_HIGHWAY = 16;
    public static final int DRIVING_MULTI_STRATEGY_FASTEST_SAVE_MONEY_SHORTEST = 5;
    public static final int DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST = 11;
    public static final int DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST_AVOID_CONGESTION = 10;
    public static final int DRIVING_NORMAL_CAR = 0;
    public static final int DRIVING_PLUGIN_HYBRID_CAR = 2;
    public static final int DRIVING_PURE_ELECTRIC_VEHICLE = 1;
    public static final int DRIVING_SINGLE_AVOID_CONGESTION = 4;
    public static final int DRIVING_SINGLE_DEFAULT = 0;
    public static final int DRIVING_SINGLE_NO_EXPRESSWAYS = 3;
    public static final int DRIVING_SINGLE_NO_HIGHWAY = 6;
    public static final int DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY = 7;
    public static final int DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 9;
    public static final int DRIVING_SINGLE_SAVE_MONEY = 1;
    public static final int DRIVING_SINGLE_SAVE_MONEY_AVOID_CONGESTION = 8;
    public static final int DRIVING_SINGLE_SHORTEST = 2;
    public static final int DrivingAvoidCongestion = 4;
    public static final int DrivingDefault = 0;
    public static final int DrivingMultiStrategy = 5;
    public static final int DrivingNoExpressways = 3;
    public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
    public static final int DrivingNoHighWay = 6;
    public static final int DrivingNoHighWaySaveMoney = 7;
    public static final int DrivingSaveMoney = 1;
    public static final int DrivingSaveMoneyAvoidCongestion = 8;
    public static final int DrivingShortDistance = 2;
    public static final int RIDING_DEFAULT = 0;
    public static final int RIDING_FAST = 2;
    public static final int RIDING_RECOMMEND = 1;
    public static final int RidingDefault = 0;
    public static final int RidingFast = 2;
    public static final int RidingRecommend = 1;
    public static final int TRUCK_AVOID_CONGESTION = 1;
    public static final int TRUCK_AVOID_CONGESTION_CHOICE_HIGHWAY = 9;
    public static final int TRUCK_AVOID_CONGESTION_NO_HIGHWAY = 4;
    public static final int TRUCK_AVOID_CONGESTION__SAVE_MONEY = 6;
    public static final int TRUCK_AVOID_CONGESTION__SAVE_MONEY_NO_HIGHWAY = 7;
    public static final int TRUCK_CHOICE_HIGHWAY = 8;
    public static final int TRUCK_NO_HIGHWAY = 2;
    public static final int TRUCK_SAVE_MONEY = 3;
    public static final int TRUCK_SAVE_MONEY_NO_HIGHWAY = 5;
    public static final int TRUCK_SIZE_HEAVY = 4;
    public static final int TRUCK_SIZE_LIGHT = 2;
    public static final int TRUCK_SIZE_MEDIUM = 3;
    public static final int TRUCK_SIZE_MINI = 1;
    public static final int WALK_DEFAULT = 0;
    public static final int WALK_MULTI_PATH = 1;
    public static final int WalkDefault = 0;
    public static final int WalkMultipath = 1;

    /* renamed from: a  reason: collision with root package name */
    private IRouteSearch f4514a;

    public interface OnRoutePlanSearchListener {
        void onDriveRoutePlanSearched(DriveRoutePlanResult driveRoutePlanResult, int i);
    }

    public interface OnRouteSearchListener {
        void onBusRouteSearched(BusRouteResult busRouteResult, int i);

        void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i);

        void onRideRouteSearched(RideRouteResult rideRouteResult, int i);

        void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i);
    }

    public interface OnTruckRouteSearchListener {
        void onTruckRouteSearched(TruckRouteRestult truckRouteRestult, int i);
    }

    public RouteSearch(Context context) {
        if (this.f4514a == null) {
            try {
                this.f4514a = new bj(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setRouteSearchListener(OnRouteSearchListener onRouteSearchListener) {
        if (this.f4514a != null) {
            this.f4514a.setRouteSearchListener(onRouteSearchListener);
        }
    }

    public void setOnTruckRouteSearchListener(OnTruckRouteSearchListener onTruckRouteSearchListener) {
        if (this.f4514a != null) {
            this.f4514a.setOnTruckRouteSearchListener(onTruckRouteSearchListener);
        }
    }

    public void setOnRoutePlanSearchListener(OnRoutePlanSearchListener onRoutePlanSearchListener) {
        if (this.f4514a != null) {
            this.f4514a.setOnRoutePlanSearchListener(onRoutePlanSearchListener);
        }
    }

    public WalkRouteResult calculateWalkRoute(WalkRouteQuery walkRouteQuery) throws AMapException {
        if (this.f4514a != null) {
            return this.f4514a.calculateWalkRoute(walkRouteQuery);
        }
        return null;
    }

    public void calculateWalkRouteAsyn(WalkRouteQuery walkRouteQuery) {
        if (this.f4514a != null) {
            this.f4514a.calculateWalkRouteAsyn(walkRouteQuery);
        }
    }

    public BusRouteResult calculateBusRoute(BusRouteQuery busRouteQuery) throws AMapException {
        if (this.f4514a != null) {
            return this.f4514a.calculateBusRoute(busRouteQuery);
        }
        return null;
    }

    public void calculateBusRouteAsyn(BusRouteQuery busRouteQuery) {
        if (this.f4514a != null) {
            this.f4514a.calculateBusRouteAsyn(busRouteQuery);
        }
    }

    public DriveRouteResult calculateDriveRoute(DriveRouteQuery driveRouteQuery) throws AMapException {
        if (this.f4514a != null) {
            return this.f4514a.calculateDriveRoute(driveRouteQuery);
        }
        return null;
    }

    public void calculateDriveRouteAsyn(DriveRouteQuery driveRouteQuery) {
        if (this.f4514a != null) {
            this.f4514a.calculateDriveRouteAsyn(driveRouteQuery);
        }
    }

    public void calculateRideRouteAsyn(RideRouteQuery rideRouteQuery) {
        if (this.f4514a != null) {
            this.f4514a.calculateRideRouteAsyn(rideRouteQuery);
        }
    }

    public RideRouteResult calculateRideRoute(RideRouteQuery rideRouteQuery) throws AMapException {
        if (this.f4514a != null) {
            return this.f4514a.calculateRideRoute(rideRouteQuery);
        }
        return null;
    }

    public TruckRouteRestult calculateTruckRoute(TruckRouteQuery truckRouteQuery) throws AMapException {
        if (this.f4514a != null) {
            return this.f4514a.calculateTruckRoute(truckRouteQuery);
        }
        return null;
    }

    public void calculateTruckRouteAsyn(TruckRouteQuery truckRouteQuery) {
        if (this.f4514a != null) {
            this.f4514a.calculateTruckRouteAsyn(truckRouteQuery);
        }
    }

    public DriveRoutePlanResult calculateDrivePlan(DrivePlanQuery drivePlanQuery) throws AMapException {
        if (this.f4514a != null) {
            return this.f4514a.calculateDrivePlan(drivePlanQuery);
        }
        return null;
    }

    public void calculateDrivePlanAsyn(DrivePlanQuery drivePlanQuery) {
        if (this.f4514a != null) {
            this.f4514a.calculateDrivePlanAsyn(drivePlanQuery);
        }
    }

    public static class FromAndTo implements Parcelable, Cloneable {
        public static final Parcelable.Creator<FromAndTo> CREATOR = new Parcelable.Creator<FromAndTo>() {
            /* renamed from: a */
            public FromAndTo createFromParcel(Parcel parcel) {
                return new FromAndTo(parcel);
            }

            /* renamed from: a */
            public FromAndTo[] newArray(int i) {
                return new FromAndTo[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private LatLonPoint f4518a;
        private LatLonPoint b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;

        public int describeContents() {
            return 0;
        }

        public FromAndTo(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f4518a = latLonPoint;
            this.b = latLonPoint2;
        }

        public LatLonPoint getFrom() {
            return this.f4518a;
        }

        public LatLonPoint getTo() {
            return this.b;
        }

        public String getStartPoiID() {
            return this.c;
        }

        public void setStartPoiID(String str) {
            this.c = str;
        }

        public String getDestinationPoiID() {
            return this.d;
        }

        public void setDestinationPoiID(String str) {
            this.d = str;
        }

        public String getOriginType() {
            return this.e;
        }

        public void setOriginType(String str) {
            this.e = str;
        }

        public String getDestinationType() {
            return this.f;
        }

        public void setDestinationType(String str) {
            this.f = str;
        }

        public String getPlateProvince() {
            return this.g;
        }

        public void setPlateProvince(String str) {
            this.g = str;
        }

        public String getPlateNumber() {
            return this.h;
        }

        public void setPlateNumber(String str) {
            this.h = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4518a, i);
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
        }

        public FromAndTo(Parcel parcel) {
            this.f4518a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
        }

        public FromAndTo() {
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((((((this.d == null ? 0 : this.d.hashCode()) + 31) * 31) + (this.f4518a == null ? 0 : this.f4518a.hashCode())) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.b == null ? 0 : this.b.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31;
            if (this.f != null) {
                i = this.f.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            FromAndTo fromAndTo = (FromAndTo) obj;
            if (this.d == null) {
                if (fromAndTo.d != null) {
                    return false;
                }
            } else if (!this.d.equals(fromAndTo.d)) {
                return false;
            }
            if (this.f4518a == null) {
                if (fromAndTo.f4518a != null) {
                    return false;
                }
            } else if (!this.f4518a.equals(fromAndTo.f4518a)) {
                return false;
            }
            if (this.c == null) {
                if (fromAndTo.c != null) {
                    return false;
                }
            } else if (!this.c.equals(fromAndTo.c)) {
                return false;
            }
            if (this.b == null) {
                if (fromAndTo.b != null) {
                    return false;
                }
            } else if (!this.b.equals(fromAndTo.b)) {
                return false;
            }
            if (this.e == null) {
                if (fromAndTo.e != null) {
                    return false;
                }
            } else if (!this.e.equals(fromAndTo.e)) {
                return false;
            }
            if (this.f == null) {
                if (fromAndTo.f != null) {
                    return false;
                }
            } else if (!this.f.equals(fromAndTo.f)) {
                return false;
            }
            return true;
        }

        public FromAndTo clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e2) {
                s.a(e2, "RouteSearch", "FromAndToclone");
            }
            FromAndTo fromAndTo = new FromAndTo(this.f4518a, this.b);
            fromAndTo.setStartPoiID(this.c);
            fromAndTo.setDestinationPoiID(this.d);
            fromAndTo.setOriginType(this.e);
            fromAndTo.setDestinationType(this.f);
            return fromAndTo;
        }
    }

    public static class BusRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<BusRouteQuery> CREATOR = new Parcelable.Creator<BusRouteQuery>() {
            /* renamed from: a */
            public BusRouteQuery createFromParcel(Parcel parcel) {
                return new BusRouteQuery(parcel);
            }

            /* renamed from: a */
            public BusRouteQuery[] newArray(int i) {
                return new BusRouteQuery[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private FromAndTo f4515a;
        private int b;
        private String c;
        private String d;
        private int e;

        public int describeContents() {
            return 0;
        }

        public BusRouteQuery(FromAndTo fromAndTo, int i, String str, int i2) {
            this.f4515a = fromAndTo;
            this.b = i;
            this.c = str;
            this.e = i2;
        }

        public FromAndTo getFromAndTo() {
            return this.f4515a;
        }

        public int getMode() {
            return this.b;
        }

        public String getCity() {
            return this.c;
        }

        public int getNightFlag() {
            return this.e;
        }

        public String getCityd() {
            return this.d;
        }

        public void setCityd(String str) {
            this.d = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4515a, i);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeInt(this.e);
            parcel.writeString(this.d);
        }

        public BusRouteQuery(Parcel parcel) {
            this.f4515a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.e = parcel.readInt();
            this.d = parcel.readString();
        }

        public BusRouteQuery() {
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((((this.c == null ? 0 : this.c.hashCode()) + 31) * 31) + (this.f4515a == null ? 0 : this.f4515a.hashCode())) * 31) + this.b) * 31) + this.e) * 31;
            if (this.d != null) {
                i = this.d.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BusRouteQuery busRouteQuery = (BusRouteQuery) obj;
            if (this.c == null) {
                if (busRouteQuery.c != null) {
                    return false;
                }
            } else if (!this.c.equals(busRouteQuery.c)) {
                return false;
            }
            if (this.d == null) {
                if (busRouteQuery.d != null) {
                    return false;
                }
            } else if (!this.d.equals(busRouteQuery.d)) {
                return false;
            }
            if (this.f4515a == null) {
                if (busRouteQuery.f4515a != null) {
                    return false;
                }
            } else if (!this.f4515a.equals(busRouteQuery.f4515a)) {
                return false;
            }
            return this.b == busRouteQuery.b && this.e == busRouteQuery.e;
        }

        public BusRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e2) {
                s.a(e2, "RouteSearch", "BusRouteQueryclone");
            }
            BusRouteQuery busRouteQuery = new BusRouteQuery(this.f4515a, this.b, this.c, this.e);
            busRouteQuery.setCityd(this.d);
            return busRouteQuery;
        }
    }

    public static class WalkRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<WalkRouteQuery> CREATOR = new Parcelable.Creator<WalkRouteQuery>() {
            /* renamed from: a */
            public WalkRouteQuery createFromParcel(Parcel parcel) {
                return new WalkRouteQuery(parcel);
            }

            /* renamed from: a */
            public WalkRouteQuery[] newArray(int i) {
                return new WalkRouteQuery[i];
            }
        };
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public FromAndTo f4521a;
        /* access modifiers changed from: private */
        public int b;

        public int describeContents() {
            return 0;
        }

        public WalkRouteQuery(FromAndTo fromAndTo, int i) {
            this.f4521a = fromAndTo;
            this.b = i;
        }

        public WalkRouteQuery(FromAndTo fromAndTo) {
            this.f4521a = fromAndTo;
        }

        public FromAndTo getFromAndTo() {
            return this.f4521a;
        }

        public int getMode() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4521a, i);
            parcel.writeInt(this.b);
        }

        public WalkRouteQuery(Parcel parcel) {
            this.f4521a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public WalkRouteQuery() {
        }

        public int hashCode() {
            return (((this.f4521a == null ? 0 : this.f4521a.hashCode()) + 31) * 31) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.f4521a == null) {
                if (walkRouteQuery.f4521a != null) {
                    return false;
                }
            } else if (!this.f4521a.equals(walkRouteQuery.f4521a)) {
                return false;
            }
            return this.b == walkRouteQuery.b;
        }

        public WalkRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                s.a(e, "RouteSearch", "WalkRouteQueryclone");
            }
            return new WalkRouteQuery(this.f4521a);
        }
    }

    public static class DriveRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<DriveRouteQuery> CREATOR = new Parcelable.Creator<DriveRouteQuery>() {
            /* renamed from: a */
            public DriveRouteQuery createFromParcel(Parcel parcel) {
                return new DriveRouteQuery(parcel);
            }

            /* renamed from: a */
            public DriveRouteQuery[] newArray(int i) {
                return new DriveRouteQuery[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private FromAndTo f4517a;
        private int b;
        private List<LatLonPoint> c;
        private List<List<LatLonPoint>> d;
        private String e;
        private boolean f = true;
        private int g = 0;

        public int describeContents() {
            return 0;
        }

        public DriveRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, List<List<LatLonPoint>> list2, String str) {
            this.f4517a = fromAndTo;
            this.b = i;
            this.c = list;
            this.d = list2;
            this.e = str;
        }

        public FromAndTo getFromAndTo() {
            return this.f4517a;
        }

        public int getMode() {
            return this.b;
        }

        public int getCarType() {
            return this.g;
        }

        public List<LatLonPoint> getPassedByPoints() {
            return this.c;
        }

        public List<List<LatLonPoint>> getAvoidpolygons() {
            return this.d;
        }

        public String getAvoidRoad() {
            return this.e;
        }

        public String getPassedPointStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.c == null || this.c.size() == 0) {
                return null;
            }
            for (int i = 0; i < this.c.size(); i++) {
                LatLonPoint latLonPoint = this.c.get(i);
                stringBuffer.append(latLonPoint.getLongitude());
                stringBuffer.append(",");
                stringBuffer.append(latLonPoint.getLatitude());
                if (i < this.c.size() - 1) {
                    stringBuffer.append(i.b);
                }
            }
            return stringBuffer.toString();
        }

        public boolean hasPassPoint() {
            return !s.a(getPassedPointStr());
        }

        public String getAvoidpolygonsStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.d == null || this.d.size() == 0) {
                return null;
            }
            for (int i = 0; i < this.d.size(); i++) {
                List list = this.d.get(i);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    LatLonPoint latLonPoint = (LatLonPoint) list.get(i2);
                    stringBuffer.append(latLonPoint.getLongitude());
                    stringBuffer.append(",");
                    stringBuffer.append(latLonPoint.getLatitude());
                    if (i2 < list.size() - 1) {
                        stringBuffer.append(i.b);
                    }
                }
                if (i < this.d.size() - 1) {
                    stringBuffer.append("|");
                }
            }
            return stringBuffer.toString();
        }

        public boolean hasAvoidpolygons() {
            return !s.a(getAvoidpolygonsStr());
        }

        public boolean hasAvoidRoad() {
            return !s.a(getAvoidRoad());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4517a, i);
            parcel.writeInt(this.b);
            parcel.writeTypedList(this.c);
            if (this.d == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(this.d.size());
                for (List<LatLonPoint> writeTypedList : this.d) {
                    parcel.writeTypedList(writeTypedList);
                }
            }
            parcel.writeString(this.e);
            parcel.writeInt(this.f ? 1 : 0);
            parcel.writeInt(this.g);
        }

        public DriveRouteQuery(Parcel parcel) {
            boolean z = true;
            this.f4517a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            int readInt = parcel.readInt();
            if (readInt == 0) {
                this.d = null;
            } else {
                this.d = new ArrayList();
            }
            for (int i = 0; i < readInt; i++) {
                this.d.add(parcel.createTypedArrayList(LatLonPoint.CREATOR));
            }
            this.e = parcel.readString();
            this.f = parcel.readInt() != 1 ? false : z;
            this.g = parcel.readInt();
        }

        public DriveRouteQuery() {
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((((this.e == null ? 0 : this.e.hashCode()) + 31) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.f4517a == null ? 0 : this.f4517a.hashCode())) * 31) + this.b) * 31;
            if (this.c != null) {
                i = this.c.hashCode();
            }
            return ((hashCode + i) * 31) + this.g;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DriveRouteQuery driveRouteQuery = (DriveRouteQuery) obj;
            if (this.e == null) {
                if (driveRouteQuery.e != null) {
                    return false;
                }
            } else if (!this.e.equals(driveRouteQuery.e)) {
                return false;
            }
            if (this.d == null) {
                if (driveRouteQuery.d != null) {
                    return false;
                }
            } else if (!this.d.equals(driveRouteQuery.d)) {
                return false;
            }
            if (this.f4517a == null) {
                if (driveRouteQuery.f4517a != null) {
                    return false;
                }
            } else if (!this.f4517a.equals(driveRouteQuery.f4517a)) {
                return false;
            }
            if (this.b != driveRouteQuery.b) {
                return false;
            }
            if (this.c != null) {
                return this.c.equals(driveRouteQuery.c) && this.f == driveRouteQuery.isUseFerry() && this.g == driveRouteQuery.g;
            }
            if (driveRouteQuery.c != null) {
                return false;
            }
        }

        public DriveRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e2) {
                s.a(e2, "RouteSearch", "DriveRouteQueryclone");
            }
            DriveRouteQuery driveRouteQuery = new DriveRouteQuery(this.f4517a, this.b, this.c, this.d, this.e);
            driveRouteQuery.setUseFerry(this.f);
            driveRouteQuery.setCarType(this.g);
            return driveRouteQuery;
        }

        public boolean isUseFerry() {
            return this.f;
        }

        public void setUseFerry(boolean z) {
            this.f = z;
        }

        public void setCarType(int i) {
            this.g = i;
        }
    }

    public static class RideRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<RideRouteQuery> CREATOR = new Parcelable.Creator<RideRouteQuery>() {
            /* renamed from: a */
            public RideRouteQuery createFromParcel(Parcel parcel) {
                return new RideRouteQuery(parcel);
            }

            /* renamed from: a */
            public RideRouteQuery[] newArray(int i) {
                return new RideRouteQuery[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private FromAndTo f4519a;
        private int b;

        public int describeContents() {
            return 0;
        }

        public RideRouteQuery(FromAndTo fromAndTo, int i) {
            this.f4519a = fromAndTo;
            this.b = i;
        }

        public RideRouteQuery(FromAndTo fromAndTo) {
            this.f4519a = fromAndTo;
        }

        public FromAndTo getFromAndTo() {
            return this.f4519a;
        }

        public int getMode() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4519a, i);
            parcel.writeInt(this.b);
        }

        public RideRouteQuery(Parcel parcel) {
            this.f4519a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public RideRouteQuery() {
        }

        public int hashCode() {
            return (((this.f4519a == null ? 0 : this.f4519a.hashCode()) + 31) * 31) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.f4519a == null) {
                if (walkRouteQuery.f4521a != null) {
                    return false;
                }
            } else if (!this.f4519a.equals(walkRouteQuery.f4521a)) {
                return false;
            }
            return this.b == walkRouteQuery.b;
        }

        public RideRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                s.a(e, "RouteSearch", "RideRouteQueryclone");
            }
            return new RideRouteQuery(this.f4519a);
        }
    }

    public static class TruckRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<TruckRouteQuery> CREATOR = new Parcelable.Creator<TruckRouteQuery>() {
            /* renamed from: a */
            public TruckRouteQuery createFromParcel(Parcel parcel) {
                return new TruckRouteQuery(parcel);
            }

            /* renamed from: a */
            public TruckRouteQuery[] newArray(int i) {
                return new TruckRouteQuery[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private FromAndTo f4520a;
        private int b = 2;
        private int c;
        private List<LatLonPoint> d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;

        public int describeContents() {
            return 0;
        }

        public TruckRouteQuery(FromAndTo fromAndTo, int i2, List<LatLonPoint> list, int i3) {
            this.f4520a = fromAndTo;
            this.c = i2;
            this.d = list;
            this.b = i3;
        }

        protected TruckRouteQuery(Parcel parcel) {
            this.f4520a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            this.e = parcel.readFloat();
            this.f = parcel.readFloat();
            this.g = parcel.readFloat();
            this.h = parcel.readFloat();
            this.i = parcel.readFloat();
        }

        public void setMode(int i2) {
            this.c = i2;
        }

        public void setTruckSize(int i2) {
            this.b = i2;
        }

        public void setTruckHeight(float f2) {
            this.e = f2;
        }

        public void setTruckWidth(float f2) {
            this.f = f2;
        }

        public void setTruckLoad(float f2) {
            this.g = f2;
        }

        public void setTruckWeight(float f2) {
            this.h = f2;
        }

        public void setTruckAxis(float f2) {
            this.i = f2;
        }

        public FromAndTo getFromAndTo() {
            return this.f4520a;
        }

        public int getMode() {
            return this.c;
        }

        public boolean hasPassPoint() {
            return !s.a(getPassedPointStr());
        }

        public String getPassedPointStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.d == null || this.d.size() == 0) {
                return null;
            }
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                LatLonPoint latLonPoint = this.d.get(i2);
                stringBuffer.append(latLonPoint.getLongitude());
                stringBuffer.append(",");
                stringBuffer.append(latLonPoint.getLatitude());
                if (i2 < this.d.size() - 1) {
                    stringBuffer.append(i.b);
                }
            }
            return stringBuffer.toString();
        }

        public int getTruckSize() {
            return this.b;
        }

        public float getTruckHeight() {
            return this.e;
        }

        public float getTruckWidth() {
            return this.f;
        }

        public float getTruckLoad() {
            return this.g;
        }

        public float getTruckWeight() {
            return this.h;
        }

        public float getTruckAxis() {
            return this.i;
        }

        public TruckRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e2) {
                s.a(e2, "RouteSearch", "TruckRouteQueryclone");
            }
            return new TruckRouteQuery(this.f4520a, this.c, this.d, this.b);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeParcelable(this.f4520a, i2);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeTypedList(this.d);
            parcel.writeFloat(this.e);
            parcel.writeFloat(this.f);
            parcel.writeFloat(this.g);
            parcel.writeFloat(this.h);
            parcel.writeFloat(this.i);
        }
    }

    public static class DrivePlanQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<DrivePlanQuery> CREATOR = new Parcelable.Creator<DrivePlanQuery>() {
            /* renamed from: a */
            public DrivePlanQuery createFromParcel(Parcel parcel) {
                return new DrivePlanQuery(parcel);
            }

            /* renamed from: a */
            public DrivePlanQuery[] newArray(int i) {
                return new DrivePlanQuery[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private FromAndTo f4516a;
        private String b;
        private int c = 1;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private int g = 48;

        public int describeContents() {
            return 0;
        }

        public DrivePlanQuery(FromAndTo fromAndTo, int i, int i2, int i3) {
            this.f4516a = fromAndTo;
            this.e = i;
            this.f = i2;
            this.g = i3;
        }

        public FromAndTo getFromAndTo() {
            return this.f4516a;
        }

        public String getDestParentPoiID() {
            return this.b;
        }

        public int getMode() {
            return this.c;
        }

        public int getCarType() {
            return this.d;
        }

        public int getFirstTime() {
            return this.e;
        }

        public int getInterval() {
            return this.f;
        }

        public int getCount() {
            return this.g;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4516a, i);
            parcel.writeString(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
        }

        public DrivePlanQuery() {
        }

        protected DrivePlanQuery(Parcel parcel) {
            this.f4516a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readString();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.f4516a == null ? 0 : this.f4516a.hashCode()) + 31) * 31;
            if (this.b != null) {
                i = this.b.hashCode();
            }
            return ((((((((((hashCode + i) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + this.g;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DrivePlanQuery drivePlanQuery = (DrivePlanQuery) obj;
            if (this.f4516a == null) {
                if (drivePlanQuery.f4516a != null) {
                    return false;
                }
            } else if (!this.f4516a.equals(drivePlanQuery.f4516a)) {
                return false;
            }
            if (this.b == null) {
                if (drivePlanQuery.b != null) {
                    return false;
                }
            } else if (!this.b.equals(drivePlanQuery.b)) {
                return false;
            }
            return this.c == drivePlanQuery.c && this.d == drivePlanQuery.d && this.e == drivePlanQuery.e && this.f == drivePlanQuery.f && this.g == drivePlanQuery.g;
        }

        public DrivePlanQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e2) {
                s.a(e2, "RouteSearch", "DriveRouteQueryclone");
            }
            DrivePlanQuery drivePlanQuery = new DrivePlanQuery(this.f4516a, this.e, this.f, this.g);
            drivePlanQuery.setDestParentPoiID(this.b);
            drivePlanQuery.setMode(this.c);
            drivePlanQuery.setCarType(this.d);
            return drivePlanQuery;
        }

        public void setDestParentPoiID(String str) {
            this.b = str;
        }

        public void setMode(int i) {
            this.c = i;
        }

        public void setCarType(int i) {
            this.d = i;
        }
    }
}
