package com.amap.api.services.busline;

import android.content.Context;
import com.amap.api.services.a.ba;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusStationSearch;

public class BusStationSearch {

    /* renamed from: a  reason: collision with root package name */
    private IBusStationSearch f4437a;

    public interface OnBusStationSearchListener {
        void onBusStationSearched(BusStationResult busStationResult, int i);
    }

    public BusStationSearch(Context context, BusStationQuery busStationQuery) {
        if (this.f4437a == null) {
            try {
                this.f4437a = new ba(context, busStationQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BusStationResult searchBusStation() throws AMapException {
        if (this.f4437a != null) {
            return this.f4437a.searchBusStation();
        }
        return null;
    }

    public void setOnBusStationSearchListener(OnBusStationSearchListener onBusStationSearchListener) {
        if (this.f4437a != null) {
            this.f4437a.setOnBusStationSearchListener(onBusStationSearchListener);
        }
    }

    public void searchBusStationAsyn() {
        if (this.f4437a != null) {
            this.f4437a.searchBusStationAsyn();
        }
    }

    public void setQuery(BusStationQuery busStationQuery) {
        if (this.f4437a != null) {
            this.f4437a.setQuery(busStationQuery);
        }
    }

    public BusStationQuery getQuery() {
        if (this.f4437a != null) {
            return this.f4437a.getQuery();
        }
        return null;
    }
}
