package com.unionpay.mobile.android.utils;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.nocard.views.l;

final class m implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ l f9744a;

    m(l lVar) {
        this.f9744a = lVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.f9744a.b == null) {
                    return true;
                }
                break;
            case 2:
                if (this.f9744a.b == null) {
                    return true;
                }
                break;
            default:
                return true;
        }
        ((l) this.f9744a.b).r();
        return true;
    }
}
