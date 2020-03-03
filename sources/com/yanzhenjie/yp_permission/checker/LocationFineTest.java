package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import android.location.LocationManager;
import java.util.List;

class LocationFineTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private Context f2445a;

    LocationFineTest(Context context) {
        this.f2445a = context;
    }

    public boolean a() throws Throwable {
        LocationManager locationManager = (LocationManager) this.f2445a.getSystemService("location");
        List<String> providers = locationManager.getProviders(true);
        boolean contains = providers.contains("gps");
        boolean contains2 = providers.contains("passive");
        if (contains || contains2 || !this.f2445a.getPackageManager().hasSystemFeature("android.hardware.location.gps")) {
            return true;
        }
        return !locationManager.isProviderEnabled("gps");
    }
}
