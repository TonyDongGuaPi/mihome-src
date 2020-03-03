package com.xiaomi.youpin.common.util;

import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public final class LocationUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23256a = 120000;
    /* access modifiers changed from: private */
    public static OnLocationChangeListener b;
    private static MyLocationListener c;
    private static LocationManager d;

    public interface OnLocationChangeListener {
        void a(Location location);

        void a(String str, int i, Bundle bundle);

        void b(Location location);
    }

    private LocationUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a() {
        LocationManager locationManager = (LocationManager) Utils.a().getSystemService("location");
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }

    public static boolean b() {
        LocationManager locationManager = (LocationManager) Utils.a().getSystemService("location");
        return locationManager != null && (locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK) || locationManager.isProviderEnabled("gps"));
    }

    public static void c() {
        Utils.a().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS").addFlags(C.ENCODING_PCM_MU_LAW));
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean a(long j, long j2, OnLocationChangeListener onLocationChangeListener) {
        if (onLocationChangeListener == null) {
            return false;
        }
        d = (LocationManager) Utils.a().getSystemService("location");
        if (d == null || (!d.isProviderEnabled(LogCategory.CATEGORY_NETWORK) && !d.isProviderEnabled("gps"))) {
            Log.d("LocationUtils", "无法定位，请打开定位服务");
            return false;
        }
        b = onLocationChangeListener;
        String bestProvider = d.getBestProvider(f(), true);
        Location lastKnownLocation = d.getLastKnownLocation(bestProvider);
        if (lastKnownLocation != null) {
            onLocationChangeListener.a(lastKnownLocation);
        }
        if (c == null) {
            c = new MyLocationListener();
        }
        d.requestLocationUpdates(bestProvider, j, (float) j2, c);
        return true;
    }

    @RequiresPermission("android.permission.ACCESS_COARSE_LOCATION")
    public static void d() {
        if (d != null) {
            if (c != null) {
                d.removeUpdates(c);
                c = null;
            }
            d = null;
        }
        if (b != null) {
            b = null;
        }
    }

    private static Criteria f() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(1);
        return criteria;
    }

    public static Address a(double d2, double d3) {
        try {
            List<Address> fromLocation = new Geocoder(Utils.a(), Locale.getDefault()).getFromLocation(d2, d3, 1);
            if (fromLocation.size() > 0) {
                return fromLocation.get(0);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(double d2, double d3) {
        Address a2 = a(d2, d3);
        if (a2 == null) {
            return "unknown";
        }
        return a2.getCountryName();
    }

    public static String c(double d2, double d3) {
        Address a2 = a(d2, d3);
        if (a2 == null) {
            return "unknown";
        }
        return a2.getLocality();
    }

    public static String d(double d2, double d3) {
        Address a2 = a(d2, d3);
        if (a2 == null) {
            return "unknown";
        }
        return a2.getAddressLine(0);
    }

    public static boolean a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        boolean a2 = a(location.getProvider(), location2.getProvider());
        if (z5) {
            return true;
        }
        if (z3 && !z4) {
            return true;
        }
        if (!z3 || z6 || !a2) {
            return false;
        }
        return true;
    }

    public static boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private static class MyLocationListener implements LocationListener {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        private MyLocationListener() {
        }

        public void onLocationChanged(Location location) {
            if (LocationUtils.b != null) {
                LocationUtils.b.b(location);
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (LocationUtils.b != null) {
                LocationUtils.b.a(str, i, bundle);
            }
            switch (i) {
                case 0:
                    Log.d("LocationUtils", "当前GPS状态为服务区外状态");
                    return;
                case 1:
                    Log.d("LocationUtils", "当前GPS状态为暂停服务状态");
                    return;
                case 2:
                    Log.d("LocationUtils", "当前GPS状态为可见状态");
                    return;
                default:
                    return;
            }
        }
    }
}
