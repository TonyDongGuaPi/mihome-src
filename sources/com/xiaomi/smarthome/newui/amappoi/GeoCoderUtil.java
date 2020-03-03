package com.xiaomi.smarthome.newui.amappoi;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

public class GeoCoderUtil implements GeocodeSearch.OnGeocodeSearchListener {
    private static GeoCoderUtil d;

    /* renamed from: a  reason: collision with root package name */
    private GeocodeSearch f20448a;
    private GeoCoderAddressListener b;
    private GeoCoderLatLngListener c;

    public interface GeoCoderAddressListener {
        void a(String str);
    }

    public interface GeoCoderLatLngListener {
        void a(LatLngEntity latLngEntity);
    }

    public static GeoCoderUtil a(Context context) {
        if (d == null) {
            d = new GeoCoderUtil(context);
        }
        return d;
    }

    private GeoCoderUtil(Context context) {
        this.f20448a = new GeocodeSearch(context);
        this.f20448a.setOnGeocodeSearchListener(this);
    }

    public void a(String str, GeoCoderAddressListener geoCoderAddressListener) {
        if (TextUtils.isEmpty(str)) {
            geoCoderAddressListener.a("");
        } else {
            a(new LatLngEntity(str), geoCoderAddressListener);
        }
    }

    public void a(LatLngEntity latLngEntity, GeoCoderAddressListener geoCoderAddressListener) {
        if (latLngEntity == null) {
            geoCoderAddressListener.a("");
            return;
        }
        this.b = geoCoderAddressListener;
        this.f20448a.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(latLngEntity.a(), latLngEntity.b()), 200.0f, GeocodeSearch.AMAP));
    }

    public String a(LatLngEntity latLngEntity) {
        if (latLngEntity == null) {
            return "";
        }
        this.b = this.b;
        try {
            return this.f20448a.getFromLocation(new RegeocodeQuery(new LatLonPoint(latLngEntity.a(), latLngEntity.b()), 200.0f, GeocodeSearch.AMAP)).getFormatAddress();
        } catch (AMapException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void a(String str, String str2, GeoCoderLatLngListener geoCoderLatLngListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            geoCoderLatLngListener.a((LatLngEntity) null);
            return;
        }
        this.c = geoCoderLatLngListener;
        this.f20448a.getFromLocationNameAsyn(new GeocodeQuery(str2, str));
    }

    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i != 1000 || regeocodeResult == null || regeocodeResult.getRegeocodeAddress() == null) {
            this.b.a("");
            return;
        }
        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
        String str = regeocodeAddress.getCity() + regeocodeAddress.getDistrict() + regeocodeAddress.getTownship() + regeocodeAddress.getStreetNumber().getStreet();
        if (regeocodeAddress.getAois().size() > 0) {
            str = str + regeocodeAddress.getAois().get(0).getAoiName();
        }
        this.b.a(str);
    }

    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (geocodeResult == null || geocodeResult.getGeocodeAddressList() == null || geocodeResult.getGeocodeAddressList().size() <= 0) {
            this.c.a((LatLngEntity) null);
        } else {
            this.c.a(new LatLngEntity(geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint()));
        }
    }
}
