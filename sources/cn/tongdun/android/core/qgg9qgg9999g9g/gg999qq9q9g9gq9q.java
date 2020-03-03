package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class gg999qq9q9g9gq9q implements gqg9qq9gqq9q9q {
    private Context gqg9qq9gqq9q9q;
    private ConnectivityManager q9qq99qg9qqgqg9gqgg9;
    private WifiManager qgg9qgg9999g9g;

    private gg999qq9q9g9gq9q(Context context, ConnectivityManager connectivityManager, WifiManager wifiManager) {
        this.gqg9qq9gqq9q9q = context;
        this.q9qq99qg9qqgqg9gqgg9 = connectivityManager;
        this.qgg9qgg9999g9g = wifiManager;
    }

    static gg999qq9q9g9gq9q gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager, WifiManager wifiManager) {
        return new gg999qq9q9g9gq9q(context, connectivityManager, wifiManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q, this.q9qq99qg9qqgqg9gqgg9, this.qgg9qgg9999g9g);
    }
}
