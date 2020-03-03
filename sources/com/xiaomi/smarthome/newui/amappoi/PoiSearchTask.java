package com.xiaomi.smarthome.newui.amappoi;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import java.util.ArrayList;
import java.util.Iterator;

public class PoiSearchTask implements PoiSearch.OnPoiSearchListener {

    /* renamed from: a  reason: collision with root package name */
    private static PoiSearchTask f20452a;
    private PoiSearch b;
    private Context c;
    private AMapLocation d;

    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    private PoiSearchTask(Context context) {
        this.c = context;
    }

    public static PoiSearchTask a(Context context) {
        if (f20452a == null) {
            synchronized (PoiSearchTask.class) {
                if (f20452a == null) {
                    f20452a = new PoiSearchTask(context);
                }
            }
        }
        return f20452a;
    }

    public ArrayList<LocationBean> a(String str, String str2, double d2, double d3, AMapLocation aMapLocation) {
        String str3;
        this.d = aMapLocation;
        this.b = new PoiSearch(this.c, new PoiSearch.Query(str, "", str2));
        this.b.setBound(new PoiSearch.SearchBound(new LatLonPoint(d2, d3), 20000));
        this.b.setOnPoiSearchListener(this);
        try {
            PoiResult searchPOI = this.b.searchPOI();
            if (searchPOI == null || searchPOI.getQuery() == null) {
                return null;
            }
            ArrayList<LocationBean> arrayList = new ArrayList<>();
            Iterator<PoiItem> it = searchPOI.getPois().iterator();
            while (it.hasNext()) {
                PoiItem next = it.next();
                LatLonPoint latLonPoint = next.getLatLonPoint();
                arrayList.add(new LocationBean(latLonPoint.getLongitude(), latLonPoint.getLatitude(), next.getTitle(), next.getSnippet()));
            }
            if (aMapLocation != null) {
                String str4 = aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet();
                if (!TextUtils.isEmpty(aMapLocation.getAoiName())) {
                    str3 = str4 + aMapLocation.getAoiName();
                } else {
                    str3 = str4 + aMapLocation.getPoiName();
                }
                arrayList.add(0, new LocationBean(aMapLocation.getLongitude(), aMapLocation.getLatitude(), (String) null, str3));
            }
            return arrayList;
        } catch (AMapException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onPoiSearched(PoiResult poiResult, int i) {
        String str;
        if (i == 1000 && poiResult != null && poiResult.getQuery() != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<PoiItem> it = poiResult.getPois().iterator();
            while (it.hasNext()) {
                PoiItem next = it.next();
                LatLonPoint latLonPoint = next.getLatLonPoint();
                arrayList.add(new LocationBean(latLonPoint.getLongitude(), latLonPoint.getLatitude(), next.getTitle(), next.getSnippet()));
            }
            AMapLocation aMapLocation = this.d;
            if (aMapLocation != null) {
                String str2 = this.d.getCity() + this.d.getDistrict() + this.d.getStreet();
                if (!TextUtils.isEmpty(this.d.getAoiName())) {
                    str = str2 + this.d.getAoiName();
                } else {
                    str = str2 + this.d.getPoiName();
                }
                arrayList.add(0, new LocationBean(aMapLocation.getLongitude(), aMapLocation.getLatitude(), (String) null, str));
            }
        }
    }
}
