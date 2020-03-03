package com.xiaomi.ai.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9950a = "LocationUtils";

    public static Location a(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager != null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(2);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(1);
                String bestProvider = locationManager.getBestProvider(criteria, true);
                if (bestProvider == null) {
                    return null;
                }
                Log.b(f9950a, "best strLocationProvider " + bestProvider);
                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation == null) {
                    c cVar = new c();
                    locationManager.requestSingleUpdate(criteria, cVar, Looper.getMainLooper());
                    locationManager.removeUpdates(cVar);
                }
                return lastKnownLocation;
            }
        } catch (Exception e) {
            Log.a(f9950a, "e", e);
        }
        return null;
    }
}
