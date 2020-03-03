package com.unionpay.mobile.android.pro.pboc.engine;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class j implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9676a;

    j(b bVar) {
        this.f9676a = bVar;
    }

    public final void run() {
        Message obtainMessage;
        Handler a2;
        synchronized (this.f9676a) {
            k.c("UPCardEngine", " se_return : 8");
            if (b.bm) {
                if (this.f9676a.h != null) {
                    obtainMessage = this.f9676a.h.obtainMessage(8, new ArrayList());
                    a2 = this.f9676a.h;
                }
            } else if (this.f9676a.w != null) {
                ArrayList<c> b = this.f9676a.w.b();
                if (!this.f9676a.a("com.unionpay.tsmservice", 18) && this.f9676a.h != null) {
                    obtainMessage = this.f9676a.h.obtainMessage(8, b);
                    a2 = this.f9676a.h;
                }
            }
            a2.sendMessage(obtainMessage);
        }
    }
}
