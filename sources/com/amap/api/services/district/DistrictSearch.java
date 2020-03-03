package com.amap.api.services.district;

import android.content.Context;
import com.amap.api.services.a.bd;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IDistrictSearch;

public class DistrictSearch {

    /* renamed from: a  reason: collision with root package name */
    private IDistrictSearch f4454a;

    public interface OnDistrictSearchListener {
        void onDistrictSearched(DistrictResult districtResult);
    }

    public DistrictSearch(Context context) {
        if (this.f4454a == null) {
            try {
                this.f4454a = new bd(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DistrictSearchQuery getQuery() {
        if (this.f4454a != null) {
            return this.f4454a.getQuery();
        }
        return null;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        if (this.f4454a != null) {
            this.f4454a.setQuery(districtSearchQuery);
        }
    }

    public DistrictResult searchDistrict() throws AMapException {
        if (this.f4454a != null) {
            return this.f4454a.searchDistrict();
        }
        return null;
    }

    public void searchDistrictAsyn() {
        if (this.f4454a != null) {
            this.f4454a.searchDistrictAsyn();
        }
    }

    public void searchDistrictAnsy() {
        if (this.f4454a != null) {
            this.f4454a.searchDistrictAnsy();
        }
    }

    public void setOnDistrictSearchListener(OnDistrictSearchListener onDistrictSearchListener) {
        if (this.f4454a != null) {
            this.f4454a.setOnDistrictSearchListener(onDistrictSearchListener);
        }
    }
}
