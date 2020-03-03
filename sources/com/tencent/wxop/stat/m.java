package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;

final class m implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9332a;
    final /* synthetic */ StatSpecifyReportedInfo b;

    m(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9332a = context;
        this.b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f9332a == null) {
            StatServiceImpl.q.g("The Context of StatService.onPause() can not be null!");
        } else {
            StatServiceImpl.b(this.f9332a, k.h(this.f9332a), this.b);
        }
    }
}
