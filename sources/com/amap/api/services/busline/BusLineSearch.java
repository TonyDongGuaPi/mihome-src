package com.amap.api.services.busline;

import android.content.Context;
import com.amap.api.services.a.az;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IBusLineSearch;

public class BusLineSearch {

    /* renamed from: a  reason: collision with root package name */
    private IBusLineSearch f4433a = null;

    public interface OnBusLineSearchListener {
        void onBusLineSearched(BusLineResult busLineResult, int i);
    }

    public BusLineSearch(Context context, BusLineQuery busLineQuery) {
        if (this.f4433a == null) {
            try {
                this.f4433a = new az(context, busLineQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BusLineResult searchBusLine() throws AMapException {
        if (this.f4433a != null) {
            return this.f4433a.searchBusLine();
        }
        return null;
    }

    public void setOnBusLineSearchListener(OnBusLineSearchListener onBusLineSearchListener) {
        if (this.f4433a != null) {
            this.f4433a.setOnBusLineSearchListener(onBusLineSearchListener);
        }
    }

    public void searchBusLineAsyn() {
        if (this.f4433a != null) {
            this.f4433a.searchBusLineAsyn();
        }
    }

    public void setQuery(BusLineQuery busLineQuery) {
        if (this.f4433a != null) {
            this.f4433a.setQuery(busLineQuery);
        }
    }

    public BusLineQuery getQuery() {
        if (this.f4433a != null) {
            return this.f4433a.getQuery();
        }
        return null;
    }
}
