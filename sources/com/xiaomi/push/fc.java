package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.bf;
import com.xiaomi.push.service.bg;
import com.xiaomi.stat.c.c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fc {

    /* renamed from: a  reason: collision with root package name */
    private static a f12729a;
    private static Map<String, hy> b;

    public interface a {
        void a(Context context, hs hsVar);
    }

    public static int a(Enum enumR) {
        if (enumR != null) {
            if (enumR instanceof ho) {
                return enumR.ordinal() + 1001;
            }
            if (enumR instanceof hy) {
                return enumR.ordinal() + 2001;
            }
            if (enumR instanceof fi) {
                return enumR.ordinal() + 3001;
            }
        }
        return -1;
    }

    public static EventClientReport a(Context context, String str, String str2, int i, long j, String str3) {
        EventClientReport a2 = a(str);
        a2.f10077a = str2;
        a2.b = i;
        a2.c = j;
        a2.d = str3;
        return a2;
    }

    public static EventClientReport a(String str) {
        EventClientReport eventClientReport = new EventClientReport();
        eventClientReport.e = 1000;
        eventClientReport.g = 1001;
        eventClientReport.f = str;
        return eventClientReport;
    }

    public static PerfClientReport a() {
        PerfClientReport perfClientReport = new PerfClientReport();
        perfClientReport.e = 1000;
        perfClientReport.g = 1000;
        perfClientReport.f = "P100000";
        return perfClientReport;
    }

    public static PerfClientReport a(Context context, int i, long j, long j2) {
        PerfClientReport a2 = a();
        a2.f10078a = i;
        a2.b = j;
        a2.c = j2;
        return a2;
    }

    public static hs a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        hs hsVar = new hs();
        hsVar.d("category_client_report_data");
        hsVar.a("push_sdk_channel");
        hsVar.a(1);
        hsVar.b(str);
        hsVar.a(true);
        hsVar.b(System.currentTimeMillis());
        hsVar.g(context.getPackageName());
        hsVar.e(c.f23036a);
        hsVar.f(bf.a());
        hsVar.c("quality_support");
        return hsVar;
    }

    public static String a(int i) {
        return i == 1000 ? "E100000" : i == 3000 ? "E100002" : i == 2000 ? "E100001" : i == 6000 ? "E100003" : "";
    }

    public static void a(Context context, Config config) {
        ClientReportClient.a(context, config, new fa(context), new fb(context));
    }

    private static void a(Context context, hs hsVar) {
        if (a(context.getApplicationContext())) {
            bg.a(context.getApplicationContext(), hsVar);
        } else if (f12729a != null) {
            f12729a.a(context, hsVar);
        }
    }

    public static void a(Context context, List<String> list) {
        if (list != null) {
            try {
                for (String a2 : list) {
                    hs a3 = a(context, a2);
                    if (bf.a(a3, false)) {
                        b.c(a3.d() + "is not valid...");
                    } else {
                        b.c("send event/perf data item id:" + a3.d());
                        a(context, a3);
                    }
                }
            } catch (Throwable th) {
                b.d(th.getMessage());
            }
        }
    }

    public static void a(a aVar) {
        f12729a = aVar;
    }

    public static boolean a(Context context) {
        return context != null && !TextUtils.isEmpty(context.getPackageName()) && c.f23036a.equals(context.getPackageName());
    }

    public static int b(int i) {
        if (i > 0) {
            return i + 1000;
        }
        return -1;
    }

    public static hy b(String str) {
        if (b == null) {
            synchronized (hy.class) {
                if (b == null) {
                    b = new HashMap();
                    for (hy hyVar : hy.values()) {
                        b.put(hyVar.f114a.toLowerCase(), hyVar);
                    }
                }
            }
        }
        hy hyVar2 = b.get(str.toLowerCase());
        return hyVar2 != null ? hyVar2 : hy.Invalid;
    }

    public static void b(Context context) {
        ClientReportClient.b(context, c(context));
    }

    public static Config c(Context context) {
        boolean a2 = ah.a(context).a(ht.PerfUploadSwitch.a(), false);
        boolean a3 = ah.a(context).a(ht.EventUploadSwitch.a(), false);
        int a4 = ah.a(context).a(ht.PerfUploadFrequency.a(), 86400);
        return Config.a().b(a3).b((long) ah.a(context).a(ht.EventUploadFrequency.a(), 86400)).c(a2).c((long) a4).a(context);
    }
}
