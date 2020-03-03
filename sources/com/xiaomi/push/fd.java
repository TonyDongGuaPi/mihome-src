package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.data.a;
import com.xiaomi.clientreport.manager.ClientReportClient;

public class fd {

    /* renamed from: a  reason: collision with root package name */
    private static volatile fd f12730a;
    private Context b;

    private fd(Context context) {
        this.b = context;
    }

    public static fd a(Context context) {
        if (f12730a == null) {
            synchronized (fd.class) {
                if (f12730a == null) {
                    f12730a = new fd(context);
                }
            }
        }
        return f12730a;
    }

    private void a(a aVar) {
        if (aVar instanceof PerfClientReport) {
            ClientReportClient.a(this.b, (PerfClientReport) aVar);
        } else if (aVar instanceof EventClientReport) {
            ClientReportClient.a(this.b, (EventClientReport) aVar);
        }
    }

    public void a(String str, int i, long j, long j2) {
        if (i >= 0 && j2 >= 0 && j > 0) {
            PerfClientReport a2 = fc.a(this.b, i, j, j2);
            a2.a(str);
            a2.b("3_7_2");
            a((a) a2);
        }
    }

    public void a(String str, Intent intent, int i, String str2) {
        if (intent != null) {
            String str3 = str;
            a(str3, fc.a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), i, System.currentTimeMillis(), str2);
        }
    }

    public void a(String str, Intent intent, String str2) {
        if (intent != null) {
            String str3 = str;
            a(str3, fc.a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), 5001, System.currentTimeMillis(), str2);
        }
    }

    public void a(String str, Intent intent, Throwable th) {
        if (intent != null) {
            String str2 = str;
            a(str2, fc.a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), 5001, System.currentTimeMillis(), th.getMessage());
        }
    }

    public void a(String str, String str2, String str3, int i, long j, String str4) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            EventClientReport a2 = fc.a(this.b, str2, str3, i, j, str4);
            a2.a(str);
            a2.b("3_7_2");
            a((a) a2);
        }
    }

    public void a(String str, String str2, String str3, int i, String str4) {
        a(str, str2, str3, i, System.currentTimeMillis(), str4);
    }

    public void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, TbsReaderView.ReaderCallback.SHOW_BAR, System.currentTimeMillis(), str4);
    }

    public void a(String str, String str2, String str3, Throwable th) {
        a(str, str2, str3, 5001, System.currentTimeMillis(), th.getMessage());
    }

    public void b(String str, String str2, String str3, String str4) {
        a(str, str2, str3, 5001, System.currentTimeMillis(), str4);
    }

    public void c(String str, String str2, String str3, String str4) {
        a(str, str2, str3, 4002, System.currentTimeMillis(), str4);
    }
}
