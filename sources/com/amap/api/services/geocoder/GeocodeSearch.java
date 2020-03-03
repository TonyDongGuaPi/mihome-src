package com.amap.api.services.geocoder;

import android.content.Context;
import com.amap.api.services.a.be;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IGeocodeSearch;
import java.util.List;

public final class GeocodeSearch {
    public static final String AMAP = "autonavi";
    public static final String GPS = "gps";

    /* renamed from: a  reason: collision with root package name */
    private IGeocodeSearch f4461a;

    public interface OnGeocodeSearchListener {
        void onGeocodeSearched(GeocodeResult geocodeResult, int i);

        void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i);
    }

    public GeocodeSearch(Context context) {
        if (this.f4461a == null) {
            try {
                this.f4461a = new be(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) throws AMapException {
        if (this.f4461a != null) {
            return this.f4461a.getFromLocation(regeocodeQuery);
        }
        return null;
    }

    public List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) throws AMapException {
        if (this.f4461a != null) {
            return this.f4461a.getFromLocationName(geocodeQuery);
        }
        return null;
    }

    public void setOnGeocodeSearchListener(OnGeocodeSearchListener onGeocodeSearchListener) {
        if (this.f4461a != null) {
            this.f4461a.setOnGeocodeSearchListener(onGeocodeSearchListener);
        }
    }

    public void getFromLocationAsyn(RegeocodeQuery regeocodeQuery) {
        if (this.f4461a != null) {
            this.f4461a.getFromLocationAsyn(regeocodeQuery);
        }
    }

    public void getFromLocationNameAsyn(GeocodeQuery geocodeQuery) {
        if (this.f4461a != null) {
            this.f4461a.getFromLocationNameAsyn(geocodeQuery);
        }
    }
}
