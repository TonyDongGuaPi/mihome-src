package com.amap.api.services.routepoisearch;

import android.content.Context;
import com.amap.api.services.a.bi;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRoutePOISearch;

public class RoutePOISearch {
    public static final int DrivingAvoidCongestion = 4;
    public static final int DrivingDefault = 0;
    public static final int DrivingNoExpressways = 3;
    public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
    public static final int DrivingNoHighWay = 6;
    public static final int DrivingNoHighWaySaveMoney = 7;
    public static final int DrivingSaveMoney = 1;
    public static final int DrivingSaveMoneyAvoidCongestion = 8;
    public static final int DrivingShortDistance = 2;

    /* renamed from: a  reason: collision with root package name */
    private IRoutePOISearch f4535a;

    public interface OnRoutePOISearchListener {
        void onRoutePoiSearched(RoutePOISearchResult routePOISearchResult, int i);
    }

    public enum RoutePOISearchType {
        TypeGasStation,
        TypeMaintenanceStation,
        TypeATM,
        TypeToilet,
        TypeFillingStation,
        TypeServiceArea
    }

    public RoutePOISearch(Context context, RoutePOISearchQuery routePOISearchQuery) {
        if (this.f4535a == null) {
            try {
                this.f4535a = new bi(context, routePOISearchQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPoiSearchListener(OnRoutePOISearchListener onRoutePOISearchListener) {
        if (this.f4535a != null) {
            this.f4535a.setRoutePOISearchListener(onRoutePOISearchListener);
        }
    }

    public void setQuery(RoutePOISearchQuery routePOISearchQuery) {
        if (this.f4535a != null) {
            this.f4535a.setQuery(routePOISearchQuery);
        }
    }

    public void searchRoutePOIAsyn() {
        if (this.f4535a != null) {
            this.f4535a.searchRoutePOIAsyn();
        }
    }

    public RoutePOISearchResult searchRoutePOI() throws AMapException {
        if (this.f4535a != null) {
            return this.f4535a.searchRoutePOI();
        }
        return null;
    }
}
