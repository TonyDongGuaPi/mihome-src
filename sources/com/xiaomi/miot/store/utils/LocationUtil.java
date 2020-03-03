package com.xiaomi.miot.store.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;

public class LocationUtil {
    public static Location a(@NonNull Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager != null) {
                Location location = null;
                for (String lastKnownLocation : locationManager.getProviders(true)) {
                    Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                    if (lastKnownLocation2 != null) {
                        if (location == null || lastKnownLocation2.getAccuracy() < location.getAccuracy()) {
                            location = lastKnownLocation2;
                        }
                    }
                }
                return location;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
