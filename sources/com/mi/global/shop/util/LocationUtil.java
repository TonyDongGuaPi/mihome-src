package com.mi.global.shop.util;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.mi.util.permission.PermissionUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LocationUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Location f7099a;

    public static void a(Activity activity) {
        if (f7099a == null) {
            f7099a = a(activity, "gps");
        }
        if (f7099a == null) {
            f7099a = a(activity, LogCategory.CATEGORY_NETWORK);
        }
    }

    public static Location a(Activity activity, String str) {
        LocationManager locationManager = (LocationManager) activity.getSystemService("location");
        if (!locationManager.isProviderEnabled(str)) {
            return null;
        }
        if (PermissionUtil.a((Context) activity, "android.permission.ACCESS_COARSE_LOCATION")) {
            return locationManager.getLastKnownLocation(str);
        }
        return null;
    }

    public static String a() {
        if (f7099a == null) {
            return "";
        }
        try {
            return URLEncoder.encode(Utils.c(f7099a.getLongitude() + "," + f7099a.getLatitude()).trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b() {
        if (f7099a == null) {
            return "";
        }
        return Utils.c(f7099a.getLongitude() + "," + f7099a.getLatitude()).trim();
    }
}
