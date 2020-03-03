package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.tsmservice.mi.ITsmCallback;
import com.unionpay.tsmservice.mi.result.VendorPayStatusResult;
import com.unionpay.utils.j;

public final class h extends ITsmCallback.Stub {

    /* renamed from: a  reason: collision with root package name */
    private int f9548a = 4000;
    private Handler b;

    public h(Handler handler) {
        this.b = handler;
    }

    public final void onError(String str, String str2) {
        j.b("uppay", "errorCode:" + str + ", errorDesc:" + str2);
        Handler handler = this.b;
        Handler handler2 = this.b;
        int i = this.f9548a;
        handler.sendMessage(Message.obtain(handler2, 1, i, 0, str + str2));
    }

    public final void onResult(Bundle bundle) {
        if (this.f9548a == 4000) {
            j.b("uppay-spay", "query vendor pay status callback");
            bundle.setClassLoader(VendorPayStatusResult.class.getClassLoader());
            this.b.sendMessage(Message.obtain(this.b, 4000, ((VendorPayStatusResult) bundle.get("result")).getVendorPayStatusResult()));
        }
    }
}
