package com.unionpay.mobile.android.utils;

import android.os.Handler;
import com.unionpay.mobile.android.nocard.views.b;
import org.simalliance.openmobileapi.SEService;

public final class l implements SEService.CallBack {

    /* renamed from: a  reason: collision with root package name */
    private static SEService f9743a;
    /* access modifiers changed from: private */
    public b b;
    private Handler.Callback c = new m(this);
    private Handler d = new Handler(this.c);

    public static SEService a() {
        return f9743a;
    }

    public final void serviceConnected(SEService sEService) {
        k.c("uppay", "se service connected");
        k.c("uppay", "mSEService:" + f9743a);
        k.c("uppay", "mSEService.isConnected:" + f9743a.isConnected());
        this.d.sendEmptyMessage(1);
    }
}
