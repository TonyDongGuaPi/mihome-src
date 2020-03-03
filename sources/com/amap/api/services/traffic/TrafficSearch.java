package com.amap.api.services.traffic;

import android.content.Context;
import com.amap.api.services.a.bl;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.ITrafficSearch;

public class TrafficSearch {
    public static int ROAD_LEVEL_CITY_QUICK_WAY = 2;
    public static int ROAD_LEVEL_HIGH_WAY = 1;
    public static int ROAD_LEVEL_HIGH_WAY_BYROAD = 3;
    public static int ROAD_LEVEL_MAIN_WAY = 4;
    public static int ROAD_LEVEL_NONAME_WAY = 6;
    public static int ROAD_LEVEL_NORMAL_WAY = 5;

    /* renamed from: a  reason: collision with root package name */
    private ITrafficSearch f4545a;

    public interface OnTrafficSearchListener {
        void onRoadTrafficSearched(TrafficStatusResult trafficStatusResult, int i);
    }

    public TrafficSearch(Context context) {
        if (this.f4545a == null) {
            try {
                this.f4545a = new bl(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setTrafficSearchListener(OnTrafficSearchListener onTrafficSearchListener) {
        if (this.f4545a != null) {
            this.f4545a.setTrafficSearchListener(onTrafficSearchListener);
        }
    }

    public TrafficStatusResult loadTrafficByRoad(RoadTrafficQuery roadTrafficQuery) throws AMapException {
        if (this.f4545a != null) {
            return this.f4545a.loadTrafficByRoad(roadTrafficQuery);
        }
        return null;
    }

    public void loadTrafficByRoadAsyn(RoadTrafficQuery roadTrafficQuery) {
        if (this.f4545a != null) {
            this.f4545a.loadTrafficByRoadAsyn(roadTrafficQuery);
        }
    }

    public TrafficStatusResult loadTrafficByCircle(CircleTrafficQuery circleTrafficQuery) throws AMapException {
        if (this.f4545a != null) {
            return this.f4545a.loadTrafficByCircle(circleTrafficQuery);
        }
        return null;
    }

    public void loadTrafficByCircleAsyn(CircleTrafficQuery circleTrafficQuery) {
        if (this.f4545a != null) {
            this.f4545a.loadTrafficByCircleAsyn(circleTrafficQuery);
        }
    }
}
