package com.yanzhenjie.permission.checker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.alipay.mobile.common.logging.api.LogCategory;
import java.util.List;

class LocationTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private LocationManager f2415a;

    LocationTest(Context context) {
        this.f2415a = (LocationManager) context.getSystemService("location");
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})
    public boolean a() throws Throwable {
        List<String> providers = this.f2415a.getProviders(true);
        if (providers.contains("gps") || providers.contains(LogCategory.CATEGORY_NETWORK)) {
            return true;
        }
        this.f2415a.requestLocationUpdates("gps", 0, 0.0f, new MLocationListener(this.f2415a));
        return true;
    }

    private static class MLocationListener implements LocationListener {

        /* renamed from: a  reason: collision with root package name */
        private LocationManager f2416a;

        public MLocationListener(LocationManager locationManager) {
            this.f2416a = locationManager;
        }

        public void onLocationChanged(Location location) {
            this.f2416a.removeUpdates(this);
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            this.f2416a.removeUpdates(this);
        }

        public void onProviderEnabled(String str) {
            this.f2416a.removeUpdates(this);
        }

        public void onProviderDisabled(String str) {
            this.f2416a.removeUpdates(this);
        }
    }
}
