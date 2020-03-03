package com.xiaomi.miot.support.monitor.core;

import android.net.Uri;
import android.os.Debug;
import android.os.Process;
import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.miot.support.monitor.core.appstart.AppStartInfo;
import com.xiaomi.miot.support.monitor.core.fps.FpsInfo;
import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.core.tasks.ITask;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;
import com.xiaomi.miot.support.monitor.report.IReport;
import com.xiaomi.miot.support.monitor.utils.AsyncThreadTask;
import com.xiaomi.miot.support.monitor.utils.CpuUtils;
import com.xiaomi.miot.support.monitor.utils.ProcessUtils;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MiotDataStorage {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1476a = "http";
    public static final String b = "tcp";
    public static final String c = "ssl";
    private static final String e = "MiotDataStorage";
    private static final String f = "h";
    private static final String g = "t";
    private static final String h = "n";
    private static final String i = "tp";
    private static final String j = "stb";
    private static final String k = "rtb";
    private static final String l = "bt";
    private static final String m = "u";
    private static final String v = "mdt";
    private static final String w = "cut";
    private static final String x = "cst";
    private static final String y = "rt";
    private static final String z = "pn";
    private long A;
    private long B;
    private long C;
    private int D;
    /* access modifiers changed from: private */
    public SoftReference<List<AppStartInfo>> E;
    /* access modifiers changed from: private */
    public String F;
    public SoftReference<Map<String, Long>> d;
    private SoftReference<Map<String, List<NetInfo>>> n;
    private SoftReference<Map<String, List<NetInfo>>> o;
    private SoftReference<Map<String, List<NetInfo>>> p;
    private String q;
    private SoftReference<List<ActivityInfo>> r;
    private String s;
    /* access modifiers changed from: private */
    public SoftReference<List<FpsInfo>> t;
    /* access modifiers changed from: private */
    public String u;

    private MiotDataStorage() {
        this.d = new SoftReference<>(new HashMap());
        this.n = new SoftReference<>(new HashMap());
        this.o = new SoftReference<>(new HashMap());
        this.p = new SoftReference<>(new HashMap());
        this.q = "netInfo";
        this.r = new SoftReference<>(new ArrayList());
        this.s = "activityInfo";
        this.t = new SoftReference<>(new ArrayList());
        this.u = "fpsInfo";
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = new SoftReference<>(new ArrayList());
        this.F = "appStartInfo";
    }

    static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static MiotDataStorage f1479a = new MiotDataStorage();

        Holder() {
        }
    }

    public static MiotDataStorage a() {
        return Holder.f1479a;
    }

    public void a(String str, String str2, NetInfo netInfo) {
        Map map;
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (netInfo != null) {
                    if (TextUtils.equals(str, b)) {
                        map = this.o.get();
                    } else if (TextUtils.equals(str, c)) {
                        map = this.p.get();
                    } else {
                        map = this.n.get();
                    }
                    if (map != null) {
                        if (map.containsKey(str2)) {
                            List list = (List) map.get(str2);
                            synchronized (this.q) {
                                if (list == null) {
                                    list = new ArrayList();
                                }
                                list.add(netInfo);
                            }
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(netInfo);
                        map.put(str2, arrayList);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            throw th;
        }
    }

    private void a(String str, String str2, List<NetInfo> list) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && list != null && list.size() != 0) {
            synchronized (this.q) {
                long j2 = 0;
                long j3 = 0;
                long j4 = 0;
                long j5 = 0;
                long j6 = 0;
                int i2 = 0;
                int i3 = 0;
                long j7 = 0;
                for (NetInfo next : list) {
                    if (next.back_type == 1) {
                        i2++;
                        j7 += next.costTime;
                        j2 += next.sentBytes;
                        j3 += next.receivedBytes;
                    } else {
                        i3++;
                        j4 += next.costTime;
                        j5 += next.sentBytes;
                        j6 += next.receivedBytes;
                    }
                }
                a(str, str2, j7, j2, j3, 1, i2);
                a(str, str2, j4, j5, j6, 2, i3);
                list.clear();
            }
        }
    }

    private void a(String str, String str2, long j2, long j3, long j4, int i2, int i3) {
        if (i3 != 0) {
            try {
                JSONObject put = new JSONObject().put("tp", str).put("h", Uri.parse(ConnectionHelper.HTTP_PREFIX + str2).getHost()).put("t", j2).put(j, j3).put(k, j4).put("bt", i2).put("n", i3).put("u", str2);
                if (i2 == 2) {
                    MiotMonitorManager.a().c().f1475a.a(IReport.Func_type.NET, "0", IReport.d, put);
                }
                MiotMonitorManager.a().c().f1475a.a(IReport.Func_type.NET, MiotMonitorManager.a().c().h.report_type, put);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void b() {
        try {
            if (MiotMonitorManager.a().c().h.switchFlag) {
                a("http", this.n);
                a(b, this.o);
                a(c, this.p);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, SoftReference<Map<String, List<NetInfo>>> softReference) {
        Map map;
        if (softReference != null && (map = softReference.get()) != null && map.size() != 0) {
            for (String str2 : map.keySet()) {
                a(str, str2, (List<NetInfo>) (List) map.get(str2));
            }
        }
    }

    public void a(ActivityInfo activityInfo) {
        if (activityInfo != null) {
            if (!TaskManager.a().b("activity")) {
                List list = this.r.get();
                if (list != null) {
                    synchronized (this.s) {
                        if (list == null) {
                            try {
                            } catch (Throwable th) {
                                throw th;
                            }
                        } else {
                            list.add(activityInfo);
                        }
                    }
                }
            } else {
                if (!(this.r == null || this.r.get() == null || this.r.get().size() <= 0)) {
                    f();
                }
                try {
                    MiotMonitorManager.a().c().f1475a.a(IReport.Func_type.ACTIVITY, MiotMonitorManager.a().c().d.report_type, activityInfo.toJson());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0071, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f() {
        /*
            r7 = this;
            com.xiaomi.miot.support.monitor.core.tasks.TaskManager r0 = com.xiaomi.miot.support.monitor.core.tasks.TaskManager.a()
            java.lang.String r1 = "activity"
            boolean r0 = r0.b(r1)
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.ref.SoftReference<java.util.List<com.xiaomi.miot.support.monitor.core.activity.ActivityInfo>> r0 = r7.r
            java.lang.Object r0 = r0.get()
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x0074
            int r1 = r0.size()
            if (r1 != 0) goto L_0x001e
            goto L_0x0074
        L_0x001e:
            java.lang.String r1 = r7.s
            monitor-enter(r1)
            if (r0 == 0) goto L_0x0070
            int r2 = r0.size()     // Catch:{ all -> 0x006e }
            if (r2 != 0) goto L_0x002a
            goto L_0x0070
        L_0x002a:
            java.lang.ref.SoftReference<java.util.List<com.xiaomi.miot.support.monitor.core.activity.ActivityInfo>> r2 = r7.r     // Catch:{ all -> 0x006e }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x006e }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x006e }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x006e }
        L_0x0036:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x006e }
            if (r3 == 0) goto L_0x0069
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x006e }
            com.xiaomi.miot.support.monitor.core.activity.ActivityInfo r3 = (com.xiaomi.miot.support.monitor.core.activity.ActivityInfo) r3     // Catch:{ all -> 0x006e }
            if (r3 == 0) goto L_0x0036
            com.xiaomi.miot.support.monitor.MiotMonitorManager r4 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ JSONException -> 0x0064 }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r4 = r4.c()     // Catch:{ JSONException -> 0x0064 }
            com.xiaomi.miot.support.monitor.report.IReport r4 = r4.f1475a     // Catch:{ JSONException -> 0x0064 }
            com.xiaomi.miot.support.monitor.report.IReport$Func_type r5 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.ACTIVITY     // Catch:{ JSONException -> 0x0064 }
            com.xiaomi.miot.support.monitor.MiotMonitorManager r6 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ JSONException -> 0x0064 }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r6 = r6.c()     // Catch:{ JSONException -> 0x0064 }
            com.xiaomi.miot.support.monitor.config.model.BaseConfigInfo r6 = r6.d     // Catch:{ JSONException -> 0x0064 }
            java.lang.String r6 = r6.report_type     // Catch:{ JSONException -> 0x0064 }
            org.json.JSONObject r3 = r3.toJson()     // Catch:{ JSONException -> 0x0064 }
            r4.a((com.xiaomi.miot.support.monitor.report.IReport.Func_type) r5, (java.lang.String) r6, (org.json.JSONObject) r3)     // Catch:{ JSONException -> 0x0064 }
            goto L_0x0036
        L_0x0064:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x006e }
            goto L_0x0036
        L_0x0069:
            r0.clear()     // Catch:{ all -> 0x006e }
            monitor-exit(r1)     // Catch:{ all -> 0x006e }
            return
        L_0x006e:
            r0 = move-exception
            goto L_0x0072
        L_0x0070:
            monitor-exit(r1)     // Catch:{ all -> 0x006e }
            return
        L_0x0072:
            monitor-exit(r1)     // Catch:{ all -> 0x006e }
            throw r0
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.support.monitor.core.MiotDataStorage.f():void");
    }

    public void a(final FpsInfo fpsInfo) {
        if (fpsInfo != null) {
            AsyncThreadTask.a((Runnable) new Runnable() {
                public void run() {
                    if (!TaskManager.a().b("fps")) {
                        List list = (List) MiotDataStorage.this.t.get();
                        if (list != null) {
                            synchronized (MiotDataStorage.this.u) {
                                if (list == null) {
                                    try {
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                } else {
                                    list.add(fpsInfo);
                                }
                            }
                        }
                    } else {
                        if (!(MiotDataStorage.this.t == null || MiotDataStorage.this.t.get() == null || ((List) MiotDataStorage.this.t.get()).size() <= 0)) {
                            MiotDataStorage.this.g();
                        }
                        try {
                            MiotMonitorManager.a().c().f1475a.a(IReport.Func_type.FPS, MiotMonitorManager.a().c().e.report_type, fpsInfo.toJson());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g() {
        /*
            r7 = this;
            java.lang.ref.SoftReference<java.util.List<com.xiaomi.miot.support.monitor.core.fps.FpsInfo>> r0 = r7.t
            java.lang.Object r0 = r0.get()
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x0079
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0011
            goto L_0x0079
        L_0x0011:
            java.lang.String r1 = r7.u
            monitor-enter(r1)
            if (r0 == 0) goto L_0x0075
            int r2 = r0.size()     // Catch:{ all -> 0x0073 }
            if (r2 != 0) goto L_0x001d
            goto L_0x0075
        L_0x001d:
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0073 }
        L_0x0021:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0073 }
            if (r3 == 0) goto L_0x006e
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0073 }
            com.xiaomi.miot.support.monitor.core.fps.FpsInfo r3 = (com.xiaomi.miot.support.monitor.core.fps.FpsInfo) r3     // Catch:{ all -> 0x0073 }
            if (r3 == 0) goto L_0x0021
            java.lang.String r4 = "MiotDataStorage"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0073 }
            r5.<init>()     // Catch:{ all -> 0x0073 }
            java.lang.String r6 = "reportFpsInfos: "
            r5.append(r6)     // Catch:{ all -> 0x0073 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0073 }
            r5.append(r6)     // Catch:{ all -> 0x0073 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0073 }
            com.xiaomi.miot.support.monitor.utils.LogX.d(r4, r5)     // Catch:{ all -> 0x0073 }
            com.xiaomi.miot.support.monitor.MiotMonitorManager r4 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ JSONException -> 0x0069 }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r4 = r4.c()     // Catch:{ JSONException -> 0x0069 }
            com.xiaomi.miot.support.monitor.report.IReport r4 = r4.f1475a     // Catch:{ JSONException -> 0x0069 }
            com.xiaomi.miot.support.monitor.report.IReport$Func_type r5 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.FPS     // Catch:{ JSONException -> 0x0069 }
            com.xiaomi.miot.support.monitor.MiotMonitorManager r6 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ JSONException -> 0x0069 }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r6 = r6.c()     // Catch:{ JSONException -> 0x0069 }
            com.xiaomi.miot.support.monitor.config.model.BaseConfigInfo r6 = r6.e     // Catch:{ JSONException -> 0x0069 }
            java.lang.String r6 = r6.report_type     // Catch:{ JSONException -> 0x0069 }
            org.json.JSONObject r3 = r3.toJson()     // Catch:{ JSONException -> 0x0069 }
            r4.a((com.xiaomi.miot.support.monitor.report.IReport.Func_type) r5, (java.lang.String) r6, (org.json.JSONObject) r3)     // Catch:{ JSONException -> 0x0069 }
            goto L_0x0021
        L_0x0069:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x0073 }
            goto L_0x0021
        L_0x006e:
            r0.clear()     // Catch:{ all -> 0x0073 }
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            return
        L_0x0073:
            r0 = move-exception
            goto L_0x0077
        L_0x0075:
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            return
        L_0x0077:
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            throw r0
        L_0x0079:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.support.monitor.core.MiotDataStorage.g():void");
    }

    public void a(int i2) {
        this.D = i2;
    }

    public int c() {
        return this.D;
    }

    public void d() {
        try {
            TaskManager.a().f1486a = true;
            this.A = System.currentTimeMillis();
            String[] a2 = new CpuUtils().a(Process.myPid());
            if (a2 != null && a2.length > 2) {
                this.B = Long.parseLong(a2[1]);
                this.C = Long.parseLong(a2[2]);
            }
            h();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void h() {
        ITask a2;
        if (this.D == 0 && (a2 = TaskManager.a().a(MiotApmTask.d)) != null && a2.f()) {
            a2.b();
        }
    }

    public void e() {
        b();
        i();
        if (this.A != 0) {
        }
    }

    private void i() {
        try {
            if (!TaskManager.a().b(MiotApmTask.d)) {
                j();
                return;
            }
            String[] a2 = new CpuUtils().a(Process.myPid());
            if (a2 != null && a2.length > 2) {
                long parseLong = Long.parseLong(a2[1]);
                long parseLong2 = Long.parseLong(a2[2]);
                long currentTimeMillis = System.currentTimeMillis() - this.A;
                long j2 = parseLong - this.B;
                long j3 = parseLong2 - this.C;
                if (this.D == 0) {
                    Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
                    Debug.getMemoryInfo(memoryInfo);
                    this.D = memoryInfo.getTotalPss();
                }
                MiotMonitorManager.a().c().f1475a.a(IReport.Func_type.MEMEORY, MiotMonitorManager.a().c().g.report_type, new JSONObject().put(v, this.D).put(w, j2).put(x, j3).put(y, currentTimeMillis).put("pn", ProcessUtils.a()));
                j();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void j() {
        this.D = 0;
    }

    public void a(final AppStartInfo appStartInfo) {
        if (appStartInfo != null) {
            AsyncThreadTask.a((Runnable) new Runnable() {
                public void run() {
                    if (!TaskManager.a().b(MiotApmTask.f)) {
                        List list = (List) MiotDataStorage.this.E.get();
                        if (list != null) {
                            synchronized (MiotDataStorage.this.F) {
                                if (list == null) {
                                    try {
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                } else {
                                    list.add(appStartInfo);
                                }
                            }
                        }
                    } else {
                        if (!(MiotDataStorage.this.E == null || MiotDataStorage.this.E.get() == null || ((List) MiotDataStorage.this.E.get()).size() <= 0)) {
                            MiotDataStorage.this.k();
                        }
                        try {
                            MiotMonitorManager.a().c().f1475a.a(IReport.Func_type.APP_START, MiotMonitorManager.a().c().f.report_type, appStartInfo.toJson());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void k() {
        /*
            r7 = this;
            java.lang.ref.SoftReference<java.util.List<com.xiaomi.miot.support.monitor.core.appstart.AppStartInfo>> r0 = r7.E
            java.lang.Object r0 = r0.get()
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x005f
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0011
            goto L_0x005f
        L_0x0011:
            java.lang.String r1 = r7.F
            monitor-enter(r1)
            if (r0 == 0) goto L_0x005b
            int r2 = r0.size()     // Catch:{ all -> 0x0059 }
            if (r2 != 0) goto L_0x001d
            goto L_0x005b
        L_0x001d:
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0059 }
        L_0x0021:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0059 }
            if (r3 == 0) goto L_0x0054
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0059 }
            com.xiaomi.miot.support.monitor.core.appstart.AppStartInfo r3 = (com.xiaomi.miot.support.monitor.core.appstart.AppStartInfo) r3     // Catch:{ all -> 0x0059 }
            if (r3 == 0) goto L_0x0021
            com.xiaomi.miot.support.monitor.MiotMonitorManager r4 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ JSONException -> 0x004f }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r4 = r4.c()     // Catch:{ JSONException -> 0x004f }
            com.xiaomi.miot.support.monitor.report.IReport r4 = r4.f1475a     // Catch:{ JSONException -> 0x004f }
            com.xiaomi.miot.support.monitor.report.IReport$Func_type r5 = com.xiaomi.miot.support.monitor.report.IReport.Func_type.APP_START     // Catch:{ JSONException -> 0x004f }
            com.xiaomi.miot.support.monitor.MiotMonitorManager r6 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ JSONException -> 0x004f }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r6 = r6.c()     // Catch:{ JSONException -> 0x004f }
            com.xiaomi.miot.support.monitor.config.model.BaseConfigInfo r6 = r6.f     // Catch:{ JSONException -> 0x004f }
            java.lang.String r6 = r6.report_type     // Catch:{ JSONException -> 0x004f }
            org.json.JSONObject r3 = r3.toJson()     // Catch:{ JSONException -> 0x004f }
            r4.a((com.xiaomi.miot.support.monitor.report.IReport.Func_type) r5, (java.lang.String) r6, (org.json.JSONObject) r3)     // Catch:{ JSONException -> 0x004f }
            goto L_0x0021
        L_0x004f:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x0021
        L_0x0054:
            r0.clear()     // Catch:{ all -> 0x0059 }
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            return
        L_0x0059:
            r0 = move-exception
            goto L_0x005d
        L_0x005b:
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            return
        L_0x005d:
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            throw r0
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.support.monitor.core.MiotDataStorage.k():void");
    }
}
