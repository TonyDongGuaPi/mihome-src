package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.app.ActivityManager;
import android.content.Context;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class qgqg9q9999ggg9qq9 implements gqg9qq9gqq9q9q {
    private final Context gqg9qq9gqq9q9q;
    private final ActivityManager qgg9qgg9999g9g;

    private qgqg9q9999ggg9qq9(Context context, ActivityManager activityManager) {
        this.gqg9qq9gqq9q9q = context;
        this.qgg9qgg9999g9g = activityManager;
    }

    static qgqg9q9999ggg9qq9 gqg9qq9gqq9q9q(Context context, ActivityManager activityManager) {
        return new qgqg9q9999ggg9qq9(context, activityManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q, this.qgg9qgg9999g9g);
    }
}
