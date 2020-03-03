package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.clientreport.processor.c;

public class bj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private c f12650a;
    private Context b;

    public void a(Context context) {
        this.b = context;
    }

    public void a(c cVar) {
        this.f12650a = cVar;
    }

    public void run() {
        bo a2;
        String str;
        String str2;
        long currentTimeMillis;
        try {
            if (this.f12650a != null) {
                this.f12650a.a();
            }
            b.c("begin read and send perf / event");
            if (this.f12650a instanceof IEventProcessor) {
                a2 = bo.a(this.b);
                str = "sp_client_report_status";
                str2 = "event_last_upload_time";
                currentTimeMillis = System.currentTimeMillis();
            } else if (this.f12650a instanceof IPerfProcessor) {
                a2 = bo.a(this.b);
                str = "sp_client_report_status";
                str2 = "perf_last_upload_time";
                currentTimeMillis = System.currentTimeMillis();
            } else {
                return;
            }
            a2.a(str, str2, currentTimeMillis);
        } catch (Exception e) {
            b.a((Throwable) e);
        }
    }
}
