package com.qti.location.sdk.utils;

import android.location.Location;
import android.util.Log;
import com.qti.location.sdk.IZatDBCommon;
import com.qti.location.sdk.IZatFlpService;
import com.qti.location.sdk.IZatGeofenceService;
import com.qti.location.sdk.IZatStaleDataException;
import com.qti.location.sdk.IZatWWANDBProvider;
import com.qti.location.sdk.IZatWWANDBReceiver;
import com.qti.location.sdk.IZatWiFiDBProvider;
import com.qti.location.sdk.IZatWiFiDBReceiver;
import com.qti.location.sdk.utils.IZatValidationResults;

public class IZatDataValidation {

    /* renamed from: a  reason: collision with root package name */
    private static String f8625a = "IZatDataValidation";
    private static final boolean b = Log.isLoggable(f8625a, 3);

    public static IZatValidationResults a(int i, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        if (!b) {
            return new IZatValidationResults(true);
        }
        return IZatDataValidationInteger.a(i, iZatDataTypes);
    }

    public static IZatValidationResults a(long j, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        if (!b) {
            return new IZatValidationResults(true);
        }
        return IZatDataValidationInteger.a(j, iZatDataTypes);
    }

    public static IZatValidationResults a(float f, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        if (!b) {
            return new IZatValidationResults(true);
        }
        return IZatDataValidationFloat.a(f, iZatDataTypes);
    }

    public static IZatValidationResults a(String str, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        if (!b) {
            return new IZatValidationResults(true);
        }
        return IZatDataValidationString.a(str, iZatDataTypes);
    }

    public static IZatValidationResults a(Location location) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (location == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("location object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a((float) location.getLatitude(), IZatValidationResults.IZatDataTypes.LOCATION_LATITUDE));
        iZatValidationResults.a(a((float) location.getLongitude(), IZatValidationResults.IZatDataTypes.LOCATION_LONGITUDE));
        if (location.hasSpeed()) {
            iZatValidationResults.a(a(location.getSpeed(), IZatValidationResults.IZatDataTypes.LOCATION_SPEED));
        }
        if (location.hasBearing()) {
            iZatValidationResults.a(a(location.getBearing(), IZatValidationResults.IZatDataTypes.LOCATION_BEARING));
        }
        if (location.hasAccuracy()) {
            iZatValidationResults.a(a(location.getAccuracy(), IZatValidationResults.IZatDataTypes.LOCATION_ACCURACY));
        }
        iZatValidationResults.a(a(location.getTime(), IZatValidationResults.IZatDataTypes.LOCATION_TIME));
        return iZatValidationResults;
    }

    public static IZatValidationResults a(Location[] locationArr) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        for (Location a2 : locationArr) {
            iZatValidationResults.a(a(a2));
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatFlpService.IzatFlpRequest izatFlpRequest) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (izatFlpRequest == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("flpRequest object is null");
            return iZatValidationResults;
        }
        if (!a(izatFlpRequest.e(), IZatValidationResults.IZatDataTypes.FLP_DISTANCE_INTERVAL).a() && !a(izatFlpRequest.f(), IZatValidationResults.IZatDataTypes.FLP_TRIP_DISTANCE).a() && !a(izatFlpRequest.d(), IZatValidationResults.IZatDataTypes.FLP_TIME_INTERVAL).a()) {
            iZatValidationResults.a(false);
            iZatValidationResults.b(true);
            iZatValidationResults.a("None of FLP session interval is set");
        }
        iZatValidationResults.d();
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatGeofenceService.IzatGeofence izatGeofence) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (izatGeofence == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("geofence object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a((float) izatGeofence.c(), IZatValidationResults.IZatDataTypes.LOCATION_LATITUDE));
        iZatValidationResults.a(a((float) izatGeofence.d(), IZatValidationResults.IZatDataTypes.LOCATION_LONGITUDE));
        iZatValidationResults.a(a((float) izatGeofence.e(), IZatValidationResults.IZatDataTypes.GEO_RADIUS));
        if ((izatGeofence.b() & 64) != 0) {
            iZatValidationResults.a(a(izatGeofence.g(), IZatValidationResults.IZatDataTypes.GEO_RESPONSIVENESS));
        }
        if ((izatGeofence.b() & 32) != 0) {
            iZatValidationResults.a(a(izatGeofence.i().a(), IZatValidationResults.IZatDataTypes.GEO_DWELL_TIME));
        }
        if (!iZatValidationResults.a()) {
            iZatValidationResults.b(true);
            iZatValidationResults.d();
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatDBCommon.IZatCellInfo iZatCellInfo) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatCellInfo == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("cellInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatCellInfo.b(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID1));
        if (iZatCellInfo.f() == IZatDBCommon.IZatCellTypes.CDMA) {
            iZatValidationResults.a(a(iZatCellInfo.c(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID2_CDMA));
        } else {
            iZatValidationResults.a(a(iZatCellInfo.c(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID2_OTHERS));
        }
        if (iZatCellInfo.f() == IZatDBCommon.IZatCellTypes.CDMA || iZatCellInfo.f() == IZatDBCommon.IZatCellTypes.GSM) {
            iZatValidationResults.a(a(iZatCellInfo.d(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID3_CDMA_GSM));
        } else {
            iZatValidationResults.a(a(iZatCellInfo.d(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID3_WCDMA_LTE));
        }
        if (iZatCellInfo.f() == IZatDBCommon.IZatCellTypes.CDMA || iZatCellInfo.f() == IZatDBCommon.IZatCellTypes.GSM) {
            iZatValidationResults.a(a(iZatCellInfo.e(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID4_CDMA_GSM));
        } else {
            iZatValidationResults.a(a(iZatCellInfo.e(), IZatValidationResults.IZatDataTypes.WWAN_CELL_REGIONID4_WCDMA_LTE));
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWiFiDBReceiver.IZatAPInfo iZatAPInfo) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatAPInfo == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("apInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatAPInfo.a(), IZatValidationResults.IZatDataTypes.WIFI_MAC_ADDRESS));
        iZatValidationResults.a(a((long) iZatAPInfo.b(), IZatValidationResults.IZatDataTypes.LOCATION_TIME));
        if (iZatAPInfo.e()) {
            iZatValidationResults.a(a(iZatAPInfo.c().a()));
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWiFiDBReceiver.IZatAPLocationData iZatAPLocationData) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatAPLocationData == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("apLocInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatAPLocationData.a(), IZatValidationResults.IZatDataTypes.WIFI_MAC_ADDRESS));
        iZatValidationResults.a(a(iZatAPLocationData.b(), IZatValidationResults.IZatDataTypes.LOCATION_LATITUDE));
        iZatValidationResults.a(a(iZatAPLocationData.c(), IZatValidationResults.IZatDataTypes.LOCATION_LONGITUDE));
        try {
            iZatValidationResults.a(a(iZatAPLocationData.d(), IZatValidationResults.IZatDataTypes.WIFI_MAX_ANTENARANGE));
        } catch (IZatStaleDataException unused) {
            Log.w(f8625a, "MAR exception");
        }
        try {
            iZatValidationResults.a(a(iZatAPLocationData.e(), IZatValidationResults.IZatDataTypes.LOCATION_ACCURACY));
        } catch (IZatStaleDataException unused2) {
            Log.w(f8625a, "HEPE exception");
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWiFiDBReceiver.IZatAPSpecialInfo iZatAPSpecialInfo) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatAPSpecialInfo == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("apSpecialInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatAPSpecialInfo.f8624a, IZatValidationResults.IZatDataTypes.WIFI_MAC_ADDRESS));
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWiFiDBProvider.IZatAPScan iZatAPScan) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatAPScan == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("apScan object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatAPScan.a(), IZatValidationResults.IZatDataTypes.WIFI_MAC_ADDRESS));
        iZatValidationResults.a(a(iZatAPScan.b(), IZatValidationResults.IZatDataTypes.WIFI_RSSI));
        iZatValidationResults.a(a(iZatAPScan.c(), IZatValidationResults.IZatDataTypes.WIFI_DELTA_TIME));
        iZatValidationResults.a(a(iZatAPScan.e(), IZatValidationResults.IZatDataTypes.WIFI_CHANNEL_NUM));
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWiFiDBProvider.IZatAPObsLocData iZatAPObsLocData) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatAPObsLocData == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("apObsLocData object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatAPObsLocData.a()));
        iZatValidationResults.a(a(iZatAPObsLocData.b()));
        iZatValidationResults.a(a(iZatAPObsLocData.d(), IZatValidationResults.IZatDataTypes.LOCATION_TIME));
        for (IZatWiFiDBProvider.IZatAPScan a2 : iZatAPObsLocData.c()) {
            iZatValidationResults.a(a(a2));
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWWANDBProvider.IZatBSObsLocationData iZatBSObsLocationData) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatBSObsLocationData == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("bsObsLocData object is null");
            return iZatValidationResults;
        }
        try {
            iZatValidationResults.a(a(iZatBSObsLocationData.l()));
        } catch (IZatStaleDataException unused) {
            Log.w(f8625a, "LOCATION exception");
        }
        try {
            iZatValidationResults.a(a(iZatBSObsLocationData.m()));
        } catch (IZatStaleDataException unused2) {
            Log.w(f8625a, "CELL INFO exception");
        }
        try {
            iZatValidationResults.a(a(iZatBSObsLocationData.a(), IZatValidationResults.IZatDataTypes.LOCATION_TIME));
        } catch (IZatStaleDataException unused3) {
            Log.w(f8625a, "TIME exception");
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWWANDBReceiver.IZatBSLocationData iZatBSLocationData) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatBSLocationData == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("bsObsLocData object is null");
            return iZatValidationResults;
        }
        try {
            iZatValidationResults.a(a(iZatBSLocationData.l()));
        } catch (IZatStaleDataException unused) {
            Log.w(f8625a, "LOCATION exception");
        }
        try {
            iZatValidationResults.a(a(iZatBSLocationData.m()));
        } catch (IZatStaleDataException unused2) {
            Log.w(f8625a, "CELL INFO exception");
        }
        try {
            iZatValidationResults.a(a(iZatBSLocationData.a(), IZatValidationResults.IZatDataTypes.WWAN_HORIZONTAL_COV_RADIUS));
        } catch (IZatStaleDataException unused3) {
            Log.w(f8625a, "HORIZONTAL COV RADIUS exception");
        }
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWWANDBReceiver.IZatBSSpecialInfo iZatBSSpecialInfo) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatBSSpecialInfo == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("bsSpecialInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatBSSpecialInfo.a()));
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWWANDBProvider.IZatBSSpecialInfo iZatBSSpecialInfo) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatBSSpecialInfo == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("bsSpecialInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a(iZatBSSpecialInfo.a()));
        return iZatValidationResults;
    }

    public static IZatValidationResults a(IZatWWANDBReceiver.IZatBSInfo iZatBSInfo) {
        IZatValidationResults iZatValidationResults = new IZatValidationResults(true);
        if (!b) {
            return iZatValidationResults;
        }
        if (iZatBSInfo == null) {
            iZatValidationResults.a(false);
            iZatValidationResults.a("bsInfo object is null");
            return iZatValidationResults;
        }
        iZatValidationResults.a(a((IZatDBCommon.IZatCellInfo) iZatBSInfo.a()));
        return iZatValidationResults;
    }
}
