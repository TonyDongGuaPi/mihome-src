package com.xiaomi.mobilestats.common;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.mobilestats.data.ProtoMsg;

class a extends Thread {
    final /* synthetic */ Throwable w;
    final /* synthetic */ Thread x;
    final /* synthetic */ CrashHandler y;

    a(CrashHandler crashHandler, Throwable th, Thread thread) {
        this.y = crashHandler;
        this.w = th;
        this.x = thread;
    }

    public void run() {
        super.run();
        Looper.prepare();
        String unused = this.y.u = this.y.a(this.w);
        ProtoMsg.StatsMsg.CrashMsg crashProtoInfo = EncodeProtoUtil.getCrashProtoInfo(this.y.mContext, this.y.u);
        if (!StrUtil.isEmpty(this.y.u) && crashProtoInfo != null && !this.y.b(StrUtil.md5(this.y.u))) {
            StatService.saveProtoInfoToFile(this.y.mContext, "crash", crashProtoInfo.toByteArray());
        }
        if (this.y.v != null) {
            this.y.v.uncaughtException(this.x, this.w);
        } else {
            new Handler().postDelayed(new b(this), 300);
        }
        Looper.loop();
    }
}
