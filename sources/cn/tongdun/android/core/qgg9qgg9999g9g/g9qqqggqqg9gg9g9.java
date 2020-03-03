package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class g9qqqggqqg9gg9g9 implements gqg9qq9gqq9q9q {
    private final Context gqg9qq9gqq9q9q;
    private final TelephonyManager qgg9qgg9999g9g;

    private g9qqqggqqg9gg9g9(Context context, TelephonyManager telephonyManager) {
        this.gqg9qq9gqq9q9q = context;
        this.qgg9qgg9999g9g = telephonyManager;
    }

    static g9qqqggqqg9gg9g9 gqg9qq9gqq9q9q(Context context, TelephonyManager telephonyManager) {
        return new g9qqqggqqg9gg9g9(context, telephonyManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q, this.qgg9qgg9999g9g);
    }
}
