package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import android.location.LocationManager;
import com.alipay.mobile.common.logging.api.LogCategory;

class LocationCoarseTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private Context f2444a;

    LocationCoarseTest(Context context) {
        this.f2444a = context;
    }

    public boolean a() throws Throwable {
        LocationManager locationManager = (LocationManager) this.f2444a.getSystemService("location");
        if (!locationManager.getProviders(true).contains(LogCategory.CATEGORY_NETWORK) && this.f2444a.getPackageManager().hasSystemFeature("android.hardware.location.network")) {
            return !locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK);
        }
        return true;
    }
}
