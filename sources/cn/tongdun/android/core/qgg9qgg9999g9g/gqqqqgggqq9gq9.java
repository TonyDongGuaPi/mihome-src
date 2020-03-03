package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.net.wifi.WifiManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class gqqqqgggqq9gq9 implements gqg9qq9gqq9q9q {
    private final WifiManager gqg9qq9gqq9q9q;
    private final Context qgg9qgg9999g9g;

    private gqqqqgggqq9gq9(Context context, WifiManager wifiManager) {
        this.gqg9qq9gqq9q9q = wifiManager;
        this.qgg9qgg9999g9g = context;
    }

    public static gqqqqgggqq9gq9 gqg9qq9gqq9q9q(Context context, WifiManager wifiManager) {
        return new gqqqqgggqq9gq9(context, wifiManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.q9qq99qg9qqgqg9gqgg9(this.qgg9qgg9999g9g, this.gqg9qq9gqq9q9q);
    }
}
