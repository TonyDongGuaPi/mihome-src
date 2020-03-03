package com.qti.location.sdk.utils;

import com.qti.location.sdk.utils.IZatValidationResults;

public class IZatDataValidationFloat {

    /* renamed from: a  reason: collision with root package name */
    private static String f8626a = "IZatDataValidation";

    public static IZatValidationResults a(float f, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        String str;
        switch (iZatDataTypes) {
            case LOCATION_LATITUDE:
                if (f < -90.0f || f > 90.0f) {
                    str = "[LOCATION_LATITUDE]Range: [-90,90], current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
                break;
            case LOCATION_LONGITUDE:
                if (f < -180.0f || f > 180.0f) {
                    str = "[LOCATION_LONGITUDE]Range: [-180,180], current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case LOCATION_SPEED:
                if (f < 0.0f) {
                    str = "[LOCATION_SPEED]Range: >=0, current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case LOCATION_BEARING:
                if (f <= 0.0f || f > 360.0f) {
                    str = "[LOCATION_BEARING]Range: (0,360], current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
                break;
            case LOCATION_ACCURACY:
                if (f <= 0.0f) {
                    str = "[LOCATION_ACCURACY]Range: >0, current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case GEO_RADIUS:
                if (f <= 0.0f) {
                    str = "[GEO_RADIUS]Range: >0, current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WIFI_MAX_ANTENARANGE:
                if (f <= 0.0f) {
                    "[WIFI_MAX_ANTENARANGE]Range: >0, current value: " + String.valueOf(f);
                    str = "Wrong wifi max antena range";
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WIFI_RSSI:
                if (f <= -128.0f || f >= 127.0f) {
                    str = "[WIFI_RSSI]Range: (-128,127), current value: " + String.valueOf(f);
                    break;
                } else {
                    return new IZatValidationResults(true);
                }
            case WWAN_HORIZONTAL_COV_RADIUS:
                if (f <= 0.0f) {
                    str = "[WWAN_HORIZONTAL_COV_RADIUS]Range: >0, current value: " + String.valueOf(f);
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
