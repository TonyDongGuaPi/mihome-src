package com.xiaomi.clientreport.manager;

import android.content.Context;
import android.os.Process;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.clientreport.processor.a;
import com.xiaomi.clientreport.processor.b;
import com.xiaomi.push.g;

public class ClientReportClient {
    public static void a(Context context) {
        a(context, Config.a(context), new a(context), new b(context));
    }

    public static void a(Context context, Config config) {
        a(context, config, new a(context), new b(context));
    }

    public static void a(Context context, Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        com.xiaomi.channel.commonutils.logger.b.c("init in process " + g.a(context) + " pid :" + Process.myPid() + " threadId: " + Thread.currentThread().getId());
        a.a(context).a(config, iEventProcessor, iPerfProcessor);
        if (g.c(context)) {
            com.xiaomi.channel.commonutils.logger.b.c("init in process　start scheduleJob");
            a.a(context).b();
        }
    }

    public static void a(Context context, EventClientReport eventClientReport) {
        if (eventClientReport != null) {
            a.a(context).a(eventClientReport);
        }
    }

    public static void a(Context context, PerfClientReport perfClientReport) {
        if (perfClientReport != null) {
            a.a(context).a(perfClientReport);
        }
    }

    public static void b(Context context, Config config) {
        if (config != null) {
            a.a(context).a(config.c(), config.d(), config.f(), config.g());
        }
    }
}
