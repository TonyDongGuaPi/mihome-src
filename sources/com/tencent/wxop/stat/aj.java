package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;

final class aj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9291a;
    final /* synthetic */ StatSpecifyReportedInfo b;

    aj(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9291a = context;
        this.b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f9291a == null) {
            StatServiceImpl.q.g("The Context of StatService.onResume() can not be null!");
        } else {
            StatServiceImpl.a(this.f9291a, k.h(this.f9291a), this.b);
        }
    }
}
