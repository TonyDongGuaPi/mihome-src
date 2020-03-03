package com.hannto.printservice.hanntoprintservice.entity;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.hannto.printservice.hanntoprintservice.HanntoPrinter;
import com.hannto.printservice.hanntoprintservice.R;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.commons.lang.CharUtils;
import org.json.JSONObject;

public class QueryJobStatusRunnable implements Runnable {
    private static final String l = "finished";
    private static final String m = "canceled";
    private static final String n = "aborted";
    private static final int o = 60;

    /* renamed from: a  reason: collision with root package name */
    private int f5778a;
    private String b;
    /* access modifiers changed from: private */
    public boolean c = false;
    private boolean d = false;
    private String e = "";
    private ScheduledExecutorService f;
    /* access modifiers changed from: private */
    public PrintJobProxy g;
    private Handler h;
    /* access modifiers changed from: private */
    public Context i;
    private String j;
    private int k = 1;
    private int p = 0;
    /* access modifiers changed from: private */
    public int q = 0;

    static /* synthetic */ int a(QueryJobStatusRunnable queryJobStatusRunnable) {
        int i2 = queryJobStatusRunnable.q;
        queryJobStatusRunnable.q = i2 + 1;
        return i2;
    }

    public QueryJobStatusRunnable(Context context, int i2, String str, ScheduledExecutorService scheduledExecutorService, PrintJobProxy printJobProxy, Handler handler, String str2) {
        this.i = context;
        this.f5778a = i2;
        this.b = str;
        this.f = scheduledExecutorService;
        this.g = printJobProxy;
        this.h = handler;
        this.j = str2;
    }

    public void run() {
        Log.v(HanntoPrinter.f5762a, " QueryJobStatusRunnable 开始一次查询");
        this.p++;
        if (this.p > 60) {
            a(false, this.i.getString(R.string.service_overtime));
            this.f.shutdown();
            Log.d(HanntoPrinter.f5762a, " QueryJobStatusRunnable 停止打印任务状态查询");
        } else if (this.c) {
            this.f.shutdown();
            Log.d(HanntoPrinter.f5762a, " QueryJobStatusRunnable 停止打印任务状态查询");
        } else {
            JSONObject jSONObject = new JSONObject();
            if (this.f5778a != -1) {
                try {
                    jSONObject.put("print_job_id", this.f5778a);
                    if (this.j.equals(HanntoPrinter.c)) {
                        int i2 = this.k;
                        this.k = i2 + 1;
                        jSONObject.put("query_id", i2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            XmPluginHostApi.instance().callMethod(this.b, "job_status", jSONObject, new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    String a2 = QueryJobStatusRunnable.this.a(jSONObject);
                    Log.i(HanntoPrinter.f5762a, " QueryJobStatusRunnable result = " + jSONObject.toString() + " jobStatus = " + a2);
                    if (PrinterParmater.U.equals(a2)) {
                        Log.e(HanntoPrinter.f5762a, " QueryJobStatusRunnable 获取状态异常");
                        QueryJobStatusRunnable.a(QueryJobStatusRunnable.this);
                        if (QueryJobStatusRunnable.this.q > 3) {
                            boolean unused = QueryJobStatusRunnable.this.c = true;
                            Log.d(HanntoPrinter.f5762a, " QueryJobStatusRunnable 无法获取到状态，不再获取");
                            QueryJobStatusRunnable.this.a(false, QueryJobStatusRunnable.this.i.getString(R.string.service_get_job_failed));
                        }
                    } else if ((QueryJobStatusRunnable.l.equals(a2) || "canceled".equals(a2)) && !QueryJobStatusRunnable.this.c) {
                        boolean unused2 = QueryJobStatusRunnable.this.c = true;
                        Log.d(HanntoPrinter.f5762a, " QueryJobStatusRunnable 打印完成");
                        QueryJobStatusRunnable.this.a(true, (String) null);
                    } else if (QueryJobStatusRunnable.this.b(a2)) {
                        boolean unused3 = QueryJobStatusRunnable.this.c = true;
                        Log.d(HanntoPrinter.f5762a, " QueryJobStatusRunnable 打印机任务异常结束");
                        QueryJobStatusRunnable.this.a(false, QueryJobStatusRunnable.this.i.getString(R.string.service_job_error));
                    } else if (QueryJobStatusRunnable.this.a(a2)) {
                        Log.w(HanntoPrinter.f5762a, "QueryJobStatusRunnable 打印机任务状态异常");
                        QueryJobStatusRunnable.this.c(a2);
                    } else {
                        Log.d(HanntoPrinter.f5762a, "QueryJobStatusRunnable 打印机任务状态正常");
                        QueryJobStatusRunnable.this.a();
                    }
                }

                public void onFailure(int i, String str) {
                    Log.d(HanntoPrinter.f5762a, "onFailure: " + str);
                    QueryJobStatusRunnable.a(QueryJobStatusRunnable.this);
                    if (QueryJobStatusRunnable.this.q > 3) {
                        boolean unused = QueryJobStatusRunnable.this.c = true;
                        Log.d(HanntoPrinter.f5762a, "无法获取到状态，不再获取");
                        QueryJobStatusRunnable.this.a(false, QueryJobStatusRunnable.this.i.getString(R.string.service_get_job_failed));
                    }
                }
            }, Parser.DEFAULT_PARSER);
        }
    }

    /* access modifiers changed from: private */
    public String a(JSONObject jSONObject) {
        try {
            if (this.j.equals(HanntoPrinter.b)) {
                int i2 = jSONObject.getJSONArray("result").getJSONObject(0).getInt("jm_state");
                int i3 = jSONObject.getJSONArray("result").getJSONObject(0).getInt("jm_state_reason");
                Log.d(HanntoPrinter.f5762a, "jm_state = " + i2 + " jm_state_reason = " + i3);
                if (i2 != 4) {
                    if (i2 != 5) {
                        if (i2 == 2) {
                            if (i3 == 46) {
                                return PrinterParmater.J;
                            }
                            if (i3 == 48) {
                                return PrinterParmater.K;
                            }
                            switch (i3) {
                                case 60:
                                    return PrinterParmater.D;
                                case 61:
                                    return PrinterParmater.E;
                                case 62:
                                    return PrinterParmater.F;
                                case 63:
                                    return PrinterParmater.G;
                                default:
                                    switch (i3) {
                                        case 66:
                                            return PrinterParmater.H;
                                        case 67:
                                            return PrinterParmater.I;
                                        default:
                                            return PrinterParmater.T;
                                    }
                            }
                        } else if (i2 == 3) {
                            return "aborted";
                        } else {
                            return "";
                        }
                    }
                }
                return l;
            } else if (this.j.equals(HanntoPrinter.c)) {
                return jSONObject.getJSONArray("result").getJSONObject(0).getString("job_state");
            } else {
                Log.e(HanntoPrinter.f5762a, "错误的model = " + this.j);
                return PrinterParmater.U;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return PrinterParmater.U;
        }
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        return PrinterParmater.s.equals(str) || PrinterParmater.t.equals(str) || PrinterParmater.u.equals(str) || PrinterParmater.v.equals(str) || PrinterParmater.w.equals(str) || PrinterParmater.x.equals(str) || PrinterParmater.y.equals(str) || PrinterParmater.z.equals(str) || PrinterParmater.A.equals(str) || PrinterParmater.B.equals(str) || PrinterParmater.C.equals(str) || PrinterParmater.D.equals(str) || PrinterParmater.E.equals(str) || PrinterParmater.F.equals(str) || PrinterParmater.G.equals(str) || PrinterParmater.H.equals(str) || PrinterParmater.I.equals(str) || PrinterParmater.J.equals(str) || PrinterParmater.K.equals(str) || PrinterParmater.T.equals(str);
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        return "aborted".equals(str);
    }

    /* access modifiers changed from: private */
    public void c(final String str) {
        this.h.post(new Runnable() {
            public void run() {
                String str;
                if (QueryJobStatusRunnable.this.g.isStarted()) {
                    String str2 = str;
                    char c = 65535;
                    switch (str2.hashCode()) {
                        case -2049695130:
                            if (str2.equals(PrinterParmater.E)) {
                                c = 12;
                                break;
                            }
                            break;
                        case -2011551112:
                            if (str2.equals(PrinterParmater.I)) {
                                c = 16;
                                break;
                            }
                            break;
                        case -1664977087:
                            if (str2.equals(PrinterParmater.t)) {
                                c = 1;
                                break;
                            }
                            break;
                        case -1664826372:
                            if (str2.equals(PrinterParmater.y)) {
                                c = 7;
                                break;
                            }
                            break;
                        case -1522912842:
                            if (str2.equals(PrinterParmater.C)) {
                                c = 10;
                                break;
                            }
                            break;
                        case -1345110503:
                            if (str2.equals(PrinterParmater.A)) {
                                c = 8;
                                break;
                            }
                            break;
                        case -791648701:
                            if (str2.equals(PrinterParmater.B)) {
                                c = 9;
                                break;
                            }
                            break;
                        case -759368191:
                            if (str2.equals(PrinterParmater.u)) {
                                c = 2;
                                break;
                            }
                            break;
                        case -650140598:
                            if (str2.equals(PrinterParmater.G)) {
                                c = 14;
                                break;
                            }
                            break;
                        case -584581227:
                            if (str2.equals(PrinterParmater.x)) {
                                c = 5;
                                break;
                            }
                            break;
                        case -504805509:
                            if (str2.equals(PrinterParmater.v)) {
                                c = 3;
                                break;
                            }
                            break;
                        case 102479310:
                            if (str2.equals(PrinterParmater.F)) {
                                c = CharUtils.b;
                                break;
                            }
                            break;
                        case 451649279:
                            if (str2.equals(PrinterParmater.K)) {
                                c = 18;
                                break;
                            }
                            break;
                        case 474693192:
                            if (str2.equals(PrinterParmater.H)) {
                                c = 15;
                                break;
                            }
                            break;
                        case 891052844:
                            if (str2.equals(PrinterParmater.z)) {
                                c = 6;
                                break;
                            }
                            break;
                        case 1182628106:
                            if (str2.equals(PrinterParmater.s)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 1301484287:
                            if (str2.equals(PrinterParmater.w)) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1956590186:
                            if (str2.equals(PrinterParmater.D)) {
                                c = 11;
                                break;
                            }
                            break;
                        case 2063625534:
                            if (str2.equals(PrinterParmater.J)) {
                                c = 17;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.paper_jam_title);
                            break;
                        case 1:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.OOP_title);
                            break;
                        case 2:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.tray_missing_title);
                            break;
                        case 3:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.ribbon_missing_title);
                            break;
                        case 4:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.ribbon_end_title);
                            break;
                        case 5:
                        case 6:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.ribbon_error_title);
                            break;
                        case 7:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.photo_paper_error_title);
                            break;
                        case 8:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.hardware_error_title);
                            break;
                        case 9:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.paper_eject_title);
                            break;
                        case 10:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.paper_length_title);
                            break;
                        case 11:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.door_close_sub);
                            break;
                        case 12:
                        case 13:
                        case 14:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.OOP_sub);
                            break;
                        case 15:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.paper_size_sub);
                            break;
                        case 16:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.paper_short_sub);
                            break;
                        case 17:
                        case 18:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.paper_align_sub);
                            break;
                        default:
                            str = QueryJobStatusRunnable.this.i.getString(R.string.service_job_error);
                            break;
                    }
                    QueryJobStatusRunnable.this.g.block(str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        this.h.post(new Runnable() {
            public void run() {
                if (QueryJobStatusRunnable.this.g.isBlocked()) {
                    QueryJobStatusRunnable.this.g.start();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final boolean z, final String str) {
        this.h.post(new Runnable() {
            public void run() {
                if (!QueryJobStatusRunnable.this.g.isFailed() && !QueryJobStatusRunnable.this.g.isCompleted() && !QueryJobStatusRunnable.this.g.isCancelled()) {
                    if (z) {
                        QueryJobStatusRunnable.this.g.complete();
                    } else {
                        QueryJobStatusRunnable.this.g.fail(str);
                    }
                }
            }
        });
    }
}
