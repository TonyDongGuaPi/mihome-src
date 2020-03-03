package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;

final class n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9333a;

    n(Context context) {
        this.f9333a = context;
    }

    public final void run() {
        if (this.f9333a == null) {
            StatServiceImpl.q.g("The Context of StatService.onStop() can not be null!");
            return;
        }
        StatServiceImpl.i(this.f9333a);
        if (!StatServiceImpl.a()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (k.B(this.f9333a)) {
                if (StatConfig.b()) {
                    StatServiceImpl.q.b((Object) "onStop isBackgroundRunning flushDataToDB");
                }
                StatServiceImpl.a(this.f9333a, -1);
            }
        }
    }
}
