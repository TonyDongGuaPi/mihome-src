package com.xiaomi.smarthome.monitor;

import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfig;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfigBuilder;
import com.xiaomi.miot.support.monitor.exceptions.MiotMonitorBaseException;
import com.xiaomi.miot.support.monitor.report.IReport;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.stat.STAT;
import org.json.JSONObject;

public class MonitorConfigWare {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1555a = true;
    private MiotMonitorConfig b;

    public MonitorConfigWare(boolean z) {
        this.f1555a = z;
        b();
    }

    public MiotMonitorConfig a() {
        return this.b;
    }

    private void b() {
        if (this.b == null) {
            this.b = MiotMonitorConfigBuilder.a().a((IReport) new IReport() {
                public void a(IReport.Func_type func_type, String str, MiotMonitorBaseException miotMonitorBaseException) {
                    if (miotMonitorBaseException != null) {
                        try {
                            if (!CoreApi.a().D() && TextUtils.equals(str, "1")) {
                                CrashReport.postCatchedException(miotMonitorBaseException);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }

                public void a(IReport.Func_type func_type, String str, String str2, JSONObject jSONObject) {
                    if (jSONObject != null) {
                        MyLog.c(str2 + jSONObject.toString());
                    }
                }

                public void a(IReport.Func_type func_type, String str, JSONObject jSONObject) {
                    if (jSONObject != null) {
                        try {
                            if (CoreApi.a().D()) {
                                MyLog.f(jSONObject.toString());
                            } else if (!TextUtils.equals(str, "1")) {
                                if (TextUtils.equals(str, "2")) {
                                    switch (AnonymousClass2.f20155a[func_type.ordinal()]) {
                                        case 1:
                                            STAT.g.a(jSONObject);
                                            return;
                                        case 2:
                                            STAT.g.b(jSONObject);
                                            return;
                                        case 3:
                                            STAT.g.f(jSONObject);
                                            return;
                                        case 4:
                                            STAT.g.e(jSONObject);
                                            return;
                                        case 5:
                                            STAT.g.c(jSONObject);
                                            return;
                                        default:
                                            return;
                                    }
                                } else {
                                    MyLog.f(jSONObject.toString());
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).a(this.f1555a).b();
        }
    }

    /* renamed from: com.xiaomi.smarthome.monitor.MonitorConfigWare$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f20155a = new int[IReport.Func_type.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.xiaomi.miot.support.monitor.report.IReport$Func_type[] r0 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f20155a = r0
                int[] r0 = f20155a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.miot.support.monitor.report.IReport$Func_type r1 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.ACTIVITY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f20155a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.miot.support.monitor.report.IReport$Func_type r1 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.APP_START     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f20155a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.miot.support.monitor.report.IReport$Func_type r1 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.FPS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f20155a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.miot.support.monitor.report.IReport$Func_type r1 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.MEMEORY     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f20155a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.xiaomi.miot.support.monitor.report.IReport$Func_type r1 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.NET     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.monitor.MonitorConfigWare.AnonymousClass2.<clinit>():void");
        }
    }
}
