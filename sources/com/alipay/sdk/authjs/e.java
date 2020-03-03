package com.alipay.sdk.authjs;

import com.alipay.sdk.authjs.a;
import org.json.JSONException;

class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f1099a;
    final /* synthetic */ d b;

    e(d dVar, a aVar) {
        this.b = dVar;
        this.f1099a = aVar;
    }

    public void run() {
        a.C0028a a2 = this.b.b(this.f1099a);
        if (a2 != a.C0028a.NONE_ERROR) {
            try {
                this.b.a(this.f1099a.b(), a2, true);
            } catch (JSONException unused) {
            }
        }
    }
}
