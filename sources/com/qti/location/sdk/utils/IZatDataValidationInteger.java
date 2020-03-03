package com.qti.location.sdk.utils;

import com.qti.location.sdk.IZatGeofenceService;
import com.qti.location.sdk.utils.IZatValidationResults;
import java.util.HashSet;
import java.util.Iterator;

public class IZatDataValidationInteger {

    /* renamed from: a  reason: collision with root package name */
    private static String f8628a = "IZatDataValidation";

    public static HashSet<Integer> a(int[] iArr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int valueOf : iArr) {
            hashSet.add(Integer.valueOf(valueOf));
        }
        return hashSet;
    }

    public static boolean a(int i, HashSet<Integer> hashSet) {
        Iterator<Integer> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() == i) {
                return true;
            }
        }
        return false;
    }

    public static IZatValidationResults a(int i, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        String str;
        switch (iZatDataTypes) {
            case FLP_STATUS:
                if (!a(i, a(new int[]{0, 1, 2}))) {
                    str = "[FLP STATUS]Value set: 0,1,2, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case FLP_DISTANCE_INTERVAL:
                if (i <= 0) {
                    str = "[FLP_DISTANCE_INTERVAL]Range: >0, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case FLP_POWER_MODE:
                if (!a(i, a(new int[]{1, 2, 3, 4, 5}))) {
                    str = "[FLP_POWER_MODE]Value Set:1,2,3,4,5, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case GEO_RESPONSIVENESS:
                if (i < 1000 || i > 65535000) {
                    str = "[GEO_RESPONSIVENESS]Range: [1000,65535000], current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
                break;
            case GEO_EVENT:
                if (!a(i, a(new int[]{1, 2, 4, 8, 16}))) {
                    str = "[GEO_EVENT]Value set:1,2,4,8,16, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case GEO_REQUEST_TYPE:
                if (!a(i, a(new int[]{1, 2, 3, 4, 5}))) {
                    str = "[GEO_REQUEST_TYPE]Value set:1,2,3,4,5, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case GEO_ERROR_CODE:
                if (!a(i, a(new int[]{-100, -102, -103, IZatGeofenceService.e}))) {
                    str = "[GEO_ERROR_CODE]Value set:-100,-102,-103,-149,current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case GEO_ENGINE_STATUS:
                if (!a(i, a(new int[]{1, 2, 3, 4}))) {
                    str = "[GEO_ENGINE_STATUS]Value set:1,2,3,4, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case GEO_DWELL_TIME:
                if (i < 0 || i >= 65535) {
                    str = "[GEO_DWELL_TIME]Range: [0,65535), current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WIFI_EXPIRE_DAYS:
                if (i < 0) {
                    str = "[WIFI_EXPIRE_DAYS]Range: >=0, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WIFI_DAYS_VALID:
                if (i < 0) {
                    str = "[WIFI_DAYS_VALID]Range: >=0, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WIFI_DELTA_TIME:
                if (i <= 0) {
                    str = "[WIFI_DELTA_TIME]Range: >0, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WIFI_CHANNEL_NUM:
                if (i < 0) {
                    str = "[WIFI_CHANNEL_NUM]Range: >=0, current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_CELL_REGIONID1:
                if (i < 0 || i > 999) {
                    str = "[WWAN_CELL_REGIONID1]Range: [0,999], current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_CELL_REGIONID2_CDMA:
                if (i < 0 || i > 32767) {
                    str = "[WWAN_CELL_REGIONID2_CDMA]Range: [0,32767], current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_CELL_REGIONID2_OTHERS:
                if (i < 0 || i > 999) {
                    str = "[WWAN_CELL_REGIONID2_OTHERS]Range: [0,999], current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_CELL_REGIONID3_CDMA_GSM:
                if (i < 0 || i > 65535) {
                    "[WWAN_CELL_REGIONID3_CDMA_GSM]Range: [0,65535], current value: " + String.valueOf(i);
                    str = "Cell ID3 out of range";
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_CELL_REGIONID3_WCDMA_LTE:
                if (i < 1 || i > 65535) {
                    "[WWAN_CELL_REGIONID3_WCDMA_LTE]Range: [1,65535], current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_CELL_REGIONID4_CDMA_GSM:
                break;
            case WWAN_CELL_REGIONID4_WCDMA_LTE:
                if (i < 0 || i > 268435455) {
                    str = "[WWAN_CELL_REGIONID4_WCDMA_LTE]Range: [1,268435455], current value: " + String.valueOf(i);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            default:
                str = "Unknown type to be validate";
                break;
        }
        if (i >= 0 && i <= 65535) {
            return new IZatValidationResults(true);
        }
        str = "[WWAN_CELL_REGIONID4_CDMA_GSM]Range: [0,65535], current value: " + String.valueOf(i);
        IZatValidationResults iZatValidationResults = new IZatValidationResults(false, str);
        iZatValidationResults.d();
        return iZatValidationResults;
    }

    public static IZatValidationResults a(long j, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        String str;
        switch (iZatDataTypes) {
            case LOCATION_TIME:
                if (j <= 0) {
                    str = "[LOCATION_TIME]Range: >0, current value: " + String.valueOf(j);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case FLP_TIME_INTERVAL:
                if (j <= 0) {
                    str = "[FLP_TIME_INTERVAL]Range: >0, current value: " + String.valueOf(j);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case FLP_TRIP_DISTANCE:
                if (j <= 0) {
                    str = "[FLP_TRIP_DISTANCE]Range: >0, current value: " + String.valueOf(j);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case FLP_TBM_MILLIS:
                if (j <= 0) {
                    str = "[FLP_TBM_MILLIS]Range: >0, current value: " + String.valueOf(j);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            default:
                str = "Unknown type to be validate";
                break;
        }
        IZatValidationResults iZatValidationResults = new IZatValidationResults(false, str);
        iZatValidationResults.d();
        return iZatValidationResults;
    }
}
