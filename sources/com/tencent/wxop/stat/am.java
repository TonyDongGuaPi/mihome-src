package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.g;

final class am implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StatGameUser f9294a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    am(StatGameUser statGameUser, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9294a = statGameUser;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f9294a == null) {
            StatServiceImpl.q.g("The gameUser of StatService.reportGameUser() can not be null!");
        } else if (this.f9294a.b() == null || this.f9294a.b().length() == 0) {
            StatServiceImpl.q.g("The account of gameUser on StatService.reportGameUser() can not be null or empty!");
        } else {
            try {
                new aq(new g(this.b, StatServiceImpl.a(this.b, false, this.c), this.f9294a, this.c)).a();
            } catch (Throwable th) {
                StatServiceImpl.q.b(th);
                StatServiceImpl.a(this.b, th);
            }
        }
    }
}
