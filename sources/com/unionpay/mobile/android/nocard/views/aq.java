package com.unionpay.mobile.android.nocard.views;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.unionpay.mobile.android.views.order.l;

final class aq implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ao f9599a;

    aq(ao aoVar) {
        this.f9599a = aoVar;
    }

    public final boolean handleMessage(Message message) {
        if (this.f9599a.f9608a.aP != l.f9759a.intValue() || this.f9599a.f9608a.J) {
            return true;
        }
        if (!TextUtils.isEmpty(this.f9599a.f9608a.u)) {
            this.f9599a.a(13, this.f9599a.p);
            return true;
        } else if (!this.f9599a.f9608a.aC) {
            return true;
        } else {
            ao.d(this.f9599a);
            return true;
        }
    }
}
