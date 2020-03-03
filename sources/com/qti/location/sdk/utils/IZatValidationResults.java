package com.qti.location.sdk.utils;

import android.util.Log;
import com.qti.location.sdk.IZatIllegalArgumentException;

public class IZatValidationResults {

    /* renamed from: a  reason: collision with root package name */
    private static String f8632a = "IZatDataValidation";
    private static final boolean b = Log.isLoggable(f8632a, 3);
    private static final boolean c = Log.isLoggable(f8632a, 2);
    private boolean d;
    private boolean e;
    private StringBuilder f;

    public enum IZatDataTypes {
        LOCATION_TIME,
        LOCATION_LATITUDE,
        LOCATION_LONGITUDE,
        LOCATION_ALTITUDE,
        LOCATION_SPEED,
        LOCATION_BEARING,
        LOCATION_ACCURACY,
        FLP_STATUS,
        FLP_TIME_INTERVAL,
        FLP_DISTANCE_INTERVAL,
        FLP_TRIP_DISTANCE,
        FLP_POWER_MODE,
        FLP_TBM_MILLIS,
        GEO_RESPONSIVENESS,
        GEO_RADIUS,
        GEO_FIELDS_MASK,
        GEO_EVENT,
        GEO_REQUEST_TYPE,
        GEO_ERROR_CODE,
        GEO_ENGINE_STATUS,
        GEO_DWELL_TIME,
        WIFI_EXPIRE_DAYS,
        WIFI_DAYS_VALID,
        WIFI_MAC_ADDRESS,
        WIFI_MAX_ANTENARANGE,
        WIFI_RSSI,
        WIFI_DELTA_TIME,
        WIFI_CHANNEL_NUM,
        WWAN_CELL_REGIONID1,
        WWAN_CELL_REGIONID2_CDMA,
        WWAN_CELL_REGIONID2_OTHERS,
        WWAN_CELL_REGIONID3_CDMA_GSM,
        WWAN_CELL_REGIONID3_WCDMA_LTE,
        WWAN_CELL_REGIONID4_CDMA_GSM,
        WWAN_CELL_REGIONID4_WCDMA_LTE,
        WWAN_HORIZONTAL_COV_RADIUS
    }

    public IZatValidationResults(boolean z) {
        this(z, "");
    }

    public IZatValidationResults(boolean z, String str) {
        this.e = c;
        this.d = z;
        this.f = new StringBuilder(str);
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean a() {
        return this.d;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public boolean b() {
        return this.e;
    }

    public void a(String str) {
        this.f.setLength(0);
        this.f.append(str);
    }

    public String c() {
        return this.f.toString();
    }

    public void a(IZatValidationResults iZatValidationResults) {
        if (!iZatValidationResults.a()) {
            this.d = false;
            this.f.append(10);
            this.f.append(iZatValidationResults.c());
        }
    }

    public void d() {
        if (!this.d) {
            if (this.e) {
                throw new IZatIllegalArgumentException(this.f.toString());
            } else if (b) {
                String str = f8632a;
                String sb = this.f.toString();
                Log.d(str, "Fail Info: " + sb);
            }
        }
    }
}
