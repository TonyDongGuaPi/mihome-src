package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.deviceid.module.rpc.deviceFp.BugTrackMessageService;
import com.alipay.deviceid.module.rpc.report.open.OpenReportService;
import com.alipay.deviceid.module.rpc.report.open.model.ReportRequest;
import com.alipay.deviceid.module.rpc.report.open.model.ReportResult;
import com.alipay.security.mobile.module.http.constant.a;
import org.json.JSONObject;

public final class s {

    /* renamed from: a  reason: collision with root package name */
    private static s f936a;
    /* access modifiers changed from: private */
    public static ReportResult e;
    private bh b = null;
    private BugTrackMessageService c = null;
    /* access modifiers changed from: private */
    public OpenReportService d = null;

    private s(Context context, String str) {
        bl blVar = new bl();
        blVar.f894a = str;
        this.b = new av(context);
        this.c = (BugTrackMessageService) this.b.a(BugTrackMessageService.class, blVar);
        this.d = (OpenReportService) this.b.a(OpenReportService.class, blVar);
    }

    public final q a(Context context, r rVar) {
        final ReportRequest a2 = p.a(context, rVar);
        if (this.d == null) {
            return null;
        }
        e = null;
        new Thread(new Runnable() {
            public final void run() {
                try {
                    ReportResult unused = s.e = s.this.d.reportData(a2);
                } catch (Throwable th) {
                    ReportResult unused2 = s.e = new ReportResult();
                    s.e.success = false;
                    ReportResult a2 = s.e;
                    a2.resultCode = "static data rpc upload error, " + e.a(th);
                    z.a("Rpc failed.");
                    z.a(e.a(th));
                }
            }
        }).start();
        int i = a.f1173a;
        while (e == null && i >= 0) {
            Thread.sleep(50);
            i -= 50;
        }
        return p.a(e);
    }

    public final boolean a(String str) {
        String str2;
        if (e.a(str) || this.c == null) {
            return false;
        }
        try {
            str2 = this.c.logCollect(e.e(str));
        } catch (Exception unused) {
            str2 = null;
        }
        if (!e.a(str2)) {
            return ((Boolean) new JSONObject(str2).get("success")).booleanValue();
        }
        return false;
    }

    public static s a(Context context, String str) {
        if (context == null || e.a(str)) {
            return null;
        }
        if (f936a == null) {
            f936a = new s(context, str);
        }
        return f936a;
    }
}
