package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.location.LocationManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class qgg9qgggq99qqg9gqq implements gqg9qq9gqq9q9q {
    private final LocationManager gqg9qq9gqq9q9q;
    private final Context qgg9qgg9999g9g;

    private qgg9qgggq99qqg9gqq(Context context, LocationManager locationManager) {
        this.qgg9qgg9999g9g = context;
        this.gqg9qq9gqq9q9q = locationManager;
    }

    static qgg9qgggq99qqg9gqq gqg9qq9gqq9q9q(Context context, LocationManager locationManager) {
        return new qgg9qgggq99qqg9gqq(context, locationManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.gqg9qq9gqq9q9q(this.qgg9qgg9999g9g, this.gqg9qq9gqq9q9q);
    }
}
