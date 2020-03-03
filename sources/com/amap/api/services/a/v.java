package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.DriveRoutePlanResult;
import com.amap.api.services.route.RouteSearch;

public class v extends k<RouteSearch.DrivePlanQuery, DriveRoutePlanResult> {
    public v(Context context, RouteSearch.DrivePlanQuery drivePlanQuery) {
        super(context, drivePlanQuery);
    }

    /* access modifiers changed from: protected */
    public String g() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=");
        stringBuffer.append(bp.f(this.d));
        if (((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo() != null) {
            stringBuffer.append("&origin=");
            stringBuffer.append(s.a(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getFrom()));
            if (!z.i(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&originid=");
                stringBuffer.append(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=");
            stringBuffer.append(s.a(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getTo()));
            if (!z.i(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destinationid=");
                stringBuffer.append(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getDestinationPoiID());
            }
            if (!z.i(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origintype=");
                stringBuffer.append(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getOriginType());
            }
            if (!z.i(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getDestinationType())) {
                stringBuffer.append("&destinationtype=");
                stringBuffer.append(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getDestinationType());
            }
            if (!z.i(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getPlateProvince())) {
                stringBuffer.append("&province=");
                stringBuffer.append(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getPlateProvince());
            }
            if (!z.i(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getPlateNumber())) {
                stringBuffer.append("&number=");
                stringBuffer.append(((RouteSearch.DrivePlanQuery) this.f4426a).getFromAndTo().getPlateNumber());
            }
        }
        if (((RouteSearch.DrivePlanQuery) this.f4426a).getDestParentPoiID() != null) {
            stringBuffer.append("&parentid=");
            stringBuffer.append("" + ((RouteSearch.DrivePlanQuery) this.f4426a).getDestParentPoiID());
        }
        stringBuffer.append("&strategy=");
        stringBuffer.append("" + ((RouteSearch.DrivePlanQuery) this.f4426a).getMode());
        stringBuffer.append("&cartype=");
        stringBuffer.append("" + ((RouteSearch.DrivePlanQuery) this.f4426a).getCarType());
        stringBuffer.append("&firsttime=");
        stringBuffer.append("" + ((RouteSearch.DrivePlanQuery) this.f4426a).getFirstTime());
        stringBuffer.append("&interval=");
        stringBuffer.append("" + ((RouteSearch.DrivePlanQuery) this.f4426a).getInterval());
        stringBuffer.append("&count=");
        stringBuffer.append("" + ((RouteSearch.DrivePlanQuery) this.f4426a).getCount());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public DriveRoutePlanResult a(String str) throws AMapException {
        return z.s(str);
    }

    public String i() {
        return r.b() + "/etd/driving?";
    }
}
