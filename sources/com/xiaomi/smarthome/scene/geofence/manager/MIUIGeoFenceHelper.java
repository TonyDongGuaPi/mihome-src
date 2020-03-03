package com.xiaomi.smarthome.scene.geofence.manager;

import android.text.TextUtils;
import com.qti.location.sdk.IZatGeofenceService;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.scene.geofence.manager.model.GeoFenceItem;
import java.util.UUID;
import java.util.regex.Pattern;

public class MIUIGeoFenceHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21542a = "-";
    public static final String b = "geofence";
    public static final String c = (GlobalSetting.p + "-" + b + "-\\w{16}-\\w+-\\w{16}");
    public static volatile Pattern d = null;

    public static boolean a(String str) {
        if (d == null) {
            d = Pattern.compile(c);
        }
        if (!TextUtils.isEmpty(str) && d.matcher(str).matches()) {
            return true;
        }
        return false;
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String replace = UUID.randomUUID().toString().replace("-", "");
        return GlobalSetting.p + "-" + b + "-" + MD5.d(str) + "-" + str2 + "-" + MD5.d(replace);
    }

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return GlobalSetting.p + "-" + b + "-" + MD5.d(str) + "-" + str2 + "-" + MD5.d(str3);
    }

    public static IZatGeofenceService.IzatGeofence a(GeoFenceItem geoFenceItem) {
        IZatGeofenceService.IzatDwellNotify izatDwellNotify;
        if (geoFenceItem == null) {
            return null;
        }
        IZatGeofenceService.IzatGeofence izatGeofence = new IZatGeofenceService.IzatGeofence(geoFenceItem.b(), geoFenceItem.c(), geoFenceItem.d());
        if (geoFenceItem.a() == 1) {
            izatDwellNotify = new IZatGeofenceService.IzatDwellNotify(3, 1);
            izatGeofence.a(IZatGeofenceService.IzatGeofenceTransitionTypes.ENTERED_ONLY);
        } else if (geoFenceItem.a() == 2) {
            izatDwellNotify = new IZatGeofenceService.IzatDwellNotify(3, 2);
            izatGeofence.a(IZatGeofenceService.IzatGeofenceTransitionTypes.EXITED_ONLY);
        } else if (geoFenceItem.a() == 3) {
            izatDwellNotify = new IZatGeofenceService.IzatDwellNotify(3, 3);
            izatGeofence.a(IZatGeofenceService.IzatGeofenceTransitionTypes.ENTERED_AND_EXITED);
        } else {
            GrayLogDelegate.a("MIUIGeoFenceHelper", "invalid GeoFenceItem type");
            return null;
        }
        izatGeofence.a(izatDwellNotify);
        izatGeofence.a(IZatGeofenceService.IzatGeofenceConfidence.MEDIUM);
        izatGeofence.a(30000);
        return izatGeofence;
    }
}
