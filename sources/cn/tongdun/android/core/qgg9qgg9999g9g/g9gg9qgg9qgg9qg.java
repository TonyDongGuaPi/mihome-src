package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.location.LocationManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class g9gg9qgg9qgg9qg implements gqg9qq9gqq9q9q {
    private final LocationManager gqg9qq9gqq9q9q;
    private Context qgg9qgg9999g9g;

    private g9gg9qgg9qgg9qg(Context context, LocationManager locationManager) {
        this.qgg9qgg9999g9g = context;
        this.gqg9qq9gqq9q9q = locationManager;
    }

    public static g9gg9qgg9qgg9qg gqg9qq9gqq9q9q(Context context, LocationManager locationManager) {
        return new g9gg9qgg9qgg9qg(context, locationManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.qgg9qgg9999g9g(this.qgg9qgg9999g9g, this.gqg9qq9gqq9q9q);
    }
}
