package com.tencent.wxop.stat;

import com.tencent.wxop.stat.a.d;
import com.tencent.wxop.stat.a.e;
import java.lang.Thread;

class ao implements Thread.UncaughtExceptionHandler {
    ao() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (StatConfig.c() && StatServiceImpl.t != null) {
            if (StatConfig.p()) {
                au.a(StatServiceImpl.t).a((e) new d(StatServiceImpl.t, StatServiceImpl.a(StatServiceImpl.t, false, (StatSpecifyReportedInfo) null), 2, th, thread, (StatSpecifyReportedInfo) null), (h) null, false, true);
                StatServiceImpl.q.i("MTA has caught the following uncaught exception:");
                StatServiceImpl.q.a(th);
            }
            StatServiceImpl.i(StatServiceImpl.t);
            if (StatServiceImpl.r != null) {
                StatServiceImpl.q.j("Call the original uncaught exception handler.");
                if (!(StatServiceImpl.r instanceof ao)) {
                    StatServiceImpl.r.uncaughtException(thread, th);
                }
            }
        }
    }
}
