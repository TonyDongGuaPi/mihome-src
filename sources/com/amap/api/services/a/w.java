package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;

public class w extends k<RouteSearch.DriveRouteQuery, DriveRouteResult> {
    public w(Context context, RouteSearch.DriveRouteQuery driveRouteQuery) {
        super(context, driveRouteQuery);
    }

    /* access modifiers changed from: protected */
    public String g() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=");
        stringBuffer.append(bp.f(this.d));
        if (((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo() != null) {
            stringBuffer.append("&origin=");
            stringBuffer.append(s.a(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getFrom()));
            if (!z.i(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&originid=");
                stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=");
            stringBuffer.append(s.a(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getTo()));
            if (!z.i(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destinationid=");
                stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getDestinationPoiID());
            }
            if (!z.i(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origintype=");
                stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getOriginType());
            }
            if (!z.i(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getDestinationType())) {
                stringBuffer.append("&destinationtype=");
                stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getDestinationType());
            }
            if (!z.i(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getPlateProvince())) {
                stringBuffer.append("&province=");
                stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getPlateProvince());
            }
            if (!z.i(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getPlateNumber())) {
                stringBuffer.append("&number=");
                stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getFromAndTo().getPlateNumber());
            }
        }
        stringBuffer.append("&strategy=");
        stringBuffer.append("" + ((RouteSearch.DriveRouteQuery) this.f4426a).getMode());
        stringBuffer.append("&extensions=all");
        stringBuffer.append("&ferry=");
        stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).isUseFerry() ^ true ? 1 : 0);
        stringBuffer.append("&cartype=");
        stringBuffer.append("" + ((RouteSearch.DriveRouteQuery) this.f4426a).getCarType());
        if (((RouteSearch.DriveRouteQuery) this.f4426a).hasPassPoint()) {
            stringBuffer.append("&waypoints=");
            stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getPassedPointStr());
        }
        if (((RouteSearch.DriveRouteQuery) this.f4426a).hasAvoidpolygons()) {
            stringBuffer.append("&avoidpolygons=");
            stringBuffer.append(((RouteSearch.DriveRouteQuery) this.f4426a).getAvoidpolygonsStr());
        }
        if (((RouteSearch.DriveRouteQuery) this.f4426a).hasAvoidRoad()) {
            stringBuffer.append("&avoidroad=");
            stringBuffer.append(c(((RouteSearch.DriveRouteQuery) this.f4426a).getAvoidRoad()));
        }
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public DriveRouteResult a(String str) throws AMapException {
        return z.b(str);
    }

    public String i() {
        return r.a() + "/direction/driving?";
    }
}
