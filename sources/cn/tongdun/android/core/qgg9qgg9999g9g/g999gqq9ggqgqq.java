package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.net.ConnectivityManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class g999gqq9ggqgqq implements gqg9qq9gqq9q9q {
    private final Context gqg9qq9gqq9q9q;
    private final ConnectivityManager qgg9qgg9999g9g;

    private g999gqq9ggqgqq(Context context, ConnectivityManager connectivityManager) {
        this.gqg9qq9gqq9q9q = context;
        this.qgg9qgg9999g9g = connectivityManager;
    }

    static g999gqq9ggqgqq gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager) {
        return new g999gqq9ggqgqq(context, connectivityManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q, this.qgg9qgg9999g9g);
    }
}
