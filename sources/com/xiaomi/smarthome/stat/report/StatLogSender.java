package com.xiaomi.smarthome.stat.report;

import android.app.Activity;
import android.content.Context;
import com.mi.global.shop.model.Tags;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import com.xiaomi.smarthome.stat.PluginStatReporter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.cybergarage.http.HTTP;
import org.json.JSONObject;

public final class StatLogSender {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22763a = "switch_to_app";
    public static final String b = "switch_to_backstage";
    public static final String c = "~#INITIALIZE#~";
    private static Boolean d;
    private static StatLogSender e;
    private static String f;
    private boolean g = false;
    private final ExecutorService h = Executors.newSingleThreadExecutor();
    private SaveTempTask i = new SaveTempTask();

    public static final void a() {
        synchronized (c) {
            if (d == null) {
                d = false;
                c();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        return d.booleanValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final boolean c() {
        /*
            java.lang.Boolean r0 = d
            r1 = 1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Boolean r0 = d
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x000f
            return r1
        L_0x000f:
            java.lang.String r0 = "~#INITIALIZE#~"
            monitor-enter(r0)
            java.lang.Boolean r2 = d     // Catch:{ all -> 0x003d }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x001c
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            return r1
        L_0x001c:
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x003d }
            boolean r3 = r2.l()     // Catch:{ all -> 0x003d }
            if (r3 == 0) goto L_0x0035
            java.lang.String r3 = "~#INITIALIZE#~"
            r4 = 0
            boolean r2 = r2.b((java.lang.String) r3, (boolean) r4)     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x0035
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x003d }
            d = r1     // Catch:{ all -> 0x003d }
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            java.lang.Boolean r0 = d
            boolean r0 = r0.booleanValue()
            return r0
        L_0x003d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatLogSender.c():boolean");
    }

    private StatLogSender() {
    }

    public static final StatLogSender b() {
        if (e == null) {
            synchronized (StatLogSender.class) {
                if (e == null) {
                    e = new StatLogSender();
                }
            }
        }
        return e;
    }

    private static String d() {
        return StatLogCache.a(FrameManager.f()) + "lac";
    }

    private static String e() {
        if (f == null) {
            String d2 = d();
            if (new File(d2).canRead()) {
                f = FileUtils.a(d2);
            }
        }
        if (f == null || f.isEmpty()) {
            f = "0";
        }
        return f;
    }

    private static void a(String str) {
        if (str != null && !str.equals(f)) {
            f = str;
            String d2 = d();
            FileOutputStream fileOutputStream = null;
            try {
                FileUtils.c(d2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(d2);
                try {
                    fileOutputStream2.write(str.getBytes());
                    IOUtils.a((OutputStream) fileOutputStream2);
                } catch (Exception unused) {
                    fileOutputStream = fileOutputStream2;
                    IOUtils.a((OutputStream) fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    IOUtils.a((OutputStream) fileOutputStream);
                    throw th;
                }
            } catch (Exception unused2) {
                IOUtils.a((OutputStream) fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((OutputStream) fileOutputStream);
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00be A[SYNTHETIC, Splitter:B:31:0x00be] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d7 A[SYNTHETIC, Splitter:B:38:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e4 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e6 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f1 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f2 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0121 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0124 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0134 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0137 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0145 A[Catch:{ Exception -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0148 A[Catch:{ Exception -> 0x00dd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(com.xiaomi.smarthome.frame.core.CoreApi r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, org.json.JSONObject r18, java.lang.String r19, long r20) {
        /*
            r1 = r15
            r2 = r17
            r3 = r18
            java.lang.String r0 = r14.s()
            if (r0 == 0) goto L_0x001e
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x001e
            java.lang.String r4 = "0"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x001a
            goto L_0x001e
        L_0x001a:
            a(r0)
            goto L_0x0022
        L_0x001e:
            java.lang.String r0 = e()
        L_0x0022:
            r4 = r0
            java.lang.String r0 = com.xiaomi.smarthome.stat.report.StatSession.a(r14)
            r5 = 0
            if (r0 == 0) goto L_0x0086
            java.lang.String r6 = "\\\"session\\\":"
            boolean r6 = r0.startsWith(r6)
            if (r6 != 0) goto L_0x0033
            goto L_0x0086
        L_0x0033:
            java.lang.String r6 = "\\\"session\\\":"
            int r6 = r6.length()
            java.lang.String r0 = r0.substring(r6)
            java.lang.String r6 = ",\\\"order\\\":"
            int r6 = r0.indexOf(r6)
            r7 = 0
            java.lang.String r7 = r0.substring(r7, r6)     // Catch:{ Exception -> 0x0065 }
            long r7 = java.lang.Long.parseLong(r7)     // Catch:{ Exception -> 0x0065 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r8 = ",\\\"order\\\":"
            int r8 = r8.length()     // Catch:{ Exception -> 0x0066 }
            int r6 = r6 + r8
            java.lang.String r0 = r0.substring(r6)     // Catch:{ Exception -> 0x0066 }
            long r8 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x0066 }
            java.lang.Long r0 = java.lang.Long.valueOf(r8)     // Catch:{ Exception -> 0x0066 }
            r6 = r0
            goto L_0x00a6
        L_0x0065:
            r7 = r5
        L_0x0066:
            java.lang.String r0 = "STAT-ERROR"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "invalid session for "
            r6.append(r8)
            r6.append(r15)
            java.lang.String r8 = ":"
            r6.append(r8)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.xiaomi.smarthome.stat.report.StatLogUploader.a((java.lang.String) r0, (java.lang.String) r6)
            r6 = r5
            goto L_0x00a6
        L_0x0086:
            java.lang.String r0 = "STAT-ERROR"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "not found session for "
            r6.append(r7)
            r6.append(r15)
            java.lang.String r7 = ":"
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.xiaomi.smarthome.stat.report.StatLogUploader.a((java.lang.String) r0, (java.lang.String) r6)
            r6 = r5
            r7 = r6
        L_0x00a6:
            java.lang.String r0 = "app"
            boolean r0 = r0.equals(r15)
            r8 = -1
            if (r0 == 0) goto L_0x00d0
            java.lang.String r0 = "switch_to_backstage"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00d0
            java.lang.String r0 = "stay_time"
            if (r7 != 0) goto L_0x00be
            r10 = r8
            goto L_0x00c8
        L_0x00be:
            long r10 = r7.longValue()     // Catch:{ Exception -> 0x00cc }
            r12 = 0
            long r10 = r20 - r10
            r12 = 100
            long r10 = r10 / r12
        L_0x00c8:
            r3.put(r0, r10)     // Catch:{ Exception -> 0x00cc }
            goto L_0x00d0
        L_0x00cc:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00d0:
            org.json.JSONStringer r0 = new org.json.JSONStringer
            r0.<init>()
            if (r3 != 0) goto L_0x00e0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x00dd }
            r3.<init>()     // Catch:{ Exception -> 0x00dd }
            goto L_0x00e0
        L_0x00dd:
            r0 = move-exception
            goto L_0x016f
        L_0x00e0:
            java.lang.String r10 = "session"
            if (r7 != 0) goto L_0x00e6
            r11 = r8
            goto L_0x00ea
        L_0x00e6:
            long r11 = r7.longValue()     // Catch:{ Exception -> 0x00dd }
        L_0x00ea:
            r3.put(r10, r11)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r7 = "order"
            if (r6 != 0) goto L_0x00f2
            goto L_0x00f6
        L_0x00f2:
            long r8 = r6.longValue()     // Catch:{ Exception -> 0x00dd }
        L_0x00f6:
            r3.put(r7, r8)     // Catch:{ Exception -> 0x00dd }
            org.json.JSONStringer r0 = r0.object()     // Catch:{ Exception -> 0x00dd }
            java.lang.String r6 = "type"
            org.json.JSONStringer r0 = r0.key(r6)     // Catch:{ Exception -> 0x00dd }
            org.json.JSONStringer r0 = r0.value(r15)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r6 = "key"
            org.json.JSONStringer r0 = r0.key(r6)     // Catch:{ Exception -> 0x00dd }
            com.xiaomi.smarthome.stat.report.StatConfig r6 = com.xiaomi.smarthome.stat.report.StatConfig.a()     // Catch:{ Exception -> 0x00dd }
            java.lang.String r1 = r6.a(r15, r2)     // Catch:{ Exception -> 0x00dd }
            org.json.JSONStringer r0 = r0.value(r1)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r1 = "value"
            org.json.JSONStringer r0 = r0.key(r1)     // Catch:{ Exception -> 0x00dd }
            if (r3 != 0) goto L_0x0124
            java.lang.String r1 = ""
            goto L_0x0128
        L_0x0124:
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x00dd }
        L_0x0128:
            org.json.JSONStringer r0 = r0.value(r1)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r1 = "extra"
            org.json.JSONStringer r0 = r0.key(r1)     // Catch:{ Exception -> 0x00dd }
            if (r19 != 0) goto L_0x0137
            java.lang.String r1 = ""
            goto L_0x0139
        L_0x0137:
            r1 = r19
        L_0x0139:
            org.json.JSONStringer r0 = r0.value(r1)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r1 = "am"
            org.json.JSONStringer r0 = r0.key(r1)     // Catch:{ Exception -> 0x00dd }
            if (r16 != 0) goto L_0x0148
            java.lang.String r1 = ""
            goto L_0x014a
        L_0x0148:
            r1 = r16
        L_0x014a:
            org.json.JSONStringer r0 = r0.value(r1)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r1 = "uid"
            org.json.JSONStringer r0 = r0.key(r1)     // Catch:{ Exception -> 0x00dd }
            org.json.JSONStringer r0 = r0.value(r4)     // Catch:{ Exception -> 0x00dd }
            java.lang.String r1 = "time"
            org.json.JSONStringer r0 = r0.key(r1)     // Catch:{ Exception -> 0x00dd }
            r1 = 1000(0x3e8, double:4.94E-321)
            long r1 = r20 / r1
            org.json.JSONStringer r0 = r0.value(r1)     // Catch:{ Exception -> 0x00dd }
            org.json.JSONStringer r0 = r0.endObject()     // Catch:{ Exception -> 0x00dd }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00dd }
            return r0
        L_0x016f:
            r0.printStackTrace()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatLogSender.a(com.xiaomi.smarthome.frame.core.CoreApi, java.lang.String, java.lang.String, java.lang.String, org.json.JSONObject, java.lang.String, long):java.lang.String");
    }

    public long a(String str, Activity activity) {
        XmPluginPackage xmPluginPackage;
        if (!(activity instanceof PluginHostActivity) || (xmPluginPackage = ((PluginHostActivity) activity).getXmPluginPackage()) == null) {
            return a(str, StatReporter.a((Object) activity), true);
        }
        return a(str, PluginStatReporter.a(xmPluginPackage.getPluginId(), xmPluginPackage.getPackageId()), false);
    }

    public long a(String str, String str2, boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = z ? "page" : "plugin";
        if (str2 == null) {
            str2 = "";
        }
        objArr[1] = str2;
        return a("app", "mihome", str, StatReporter.a(objArr), "", z);
    }

    private long a(CoreApi coreApi, long j) {
        if (!this.g && coreApi.l()) {
            coreApi.b((String) null, true);
            this.g = true;
        }
        return j;
    }

    public long a(String str, String str2, String str3, JSONObject jSONObject, String str4, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final JSONObject jSONObject2 = jSONObject;
        final String str8 = str4;
        final boolean z2 = z;
        final long j = currentTimeMillis;
        this.h.execute(new Runnable() {
            public void run() {
                long unused = StatLogSender.this.a(str5, str6, str7, jSONObject2, str8, z2, j);
            }
        });
        return currentTimeMillis;
    }

    /* access modifiers changed from: private */
    public long a(String str, String str2, String str3, JSONObject jSONObject, String str4, boolean z, long j) {
        JSONObject jSONObject2;
        String str5 = str;
        String str6 = str3;
        boolean z2 = z;
        long j2 = j;
        CoreApi a2 = CoreApi.a();
        if (!StatReporter.a(str5, str6)) {
            return a(a2, j2);
        }
        if ("app".equals(str5) && f22763a.equals(str6) && !StatSession.a(a2, z2, true)) {
            return a(a2, j2);
        }
        if (jSONObject == null) {
            try {
                jSONObject2 = new JSONObject();
            } catch (Exception unused) {
                jSONObject2 = jSONObject;
            }
        } else {
            jSONObject2 = jSONObject;
        }
        try {
            jSONObject2.put("key_name", str6);
        } catch (Exception unused2) {
        }
        String a3 = a(a2, str, str2, str3, jSONObject2, str4, j);
        if ("app".equals(str5) && b.equals(str6) && !StatSession.a(a2, z2, false)) {
            return a(a2, j2);
        }
        if (a3 == null) {
            return a(a2, j2);
        }
        StatLogUploader.a("STAT-REPORT", a3.replace(",\"value\":\"{", ",\"value\":\r\n\"{").replace(",\"uid\":\"", ",\r\nuid:\"").replace("\"", "").replace(Tags.MiHome.TEL_SEPARATOR4, "").replace(HTTP.TAB, ""));
        if (!c() || !a2.l() || !a2.b(a3, false)) {
            this.i.a(a3);
        } else {
            this.g = true;
            this.i.a();
        }
        return j2;
    }

    private class SaveTempTask {
        private Executor b;
        /* access modifiers changed from: private */
        public ConcurrentLinkedQueue<String> c;

        private SaveTempTask() {
            this.b = Executors.newSingleThreadExecutor();
            this.c = new ConcurrentLinkedQueue<>();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.b.execute(new Runnable() {
                public void run() {
                    while (true) {
                        if (!SaveTempTask.this.c.isEmpty()) {
                            String str = (String) SaveTempTask.this.c.poll();
                            if (str != null && !CoreApi.a().b(str, false)) {
                                SaveTempTask.this.c.add(str);
                                SaveTempTask.this.c();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    SaveTempTask.this.b();
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void b() {
            LinkedList<String> e = StatLogCache.e();
            if (e != null && !e.isEmpty()) {
                while (!e.isEmpty()) {
                    String pop = e.pop();
                    if (pop != null) {
                        CoreApi.a().b(pop, false);
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void c() {
            if (this.c != null && !this.c.isEmpty()) {
                Context f = FrameManager.f();
                while (!this.c.isEmpty()) {
                    String poll = this.c.poll();
                    if (poll != null && !StatLogCache.a(f, poll)) {
                        a(poll);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(final String str) {
            this.b.execute(new Runnable() {
                public void run() {
                    SaveTempTask.this.c.offer(str);
                    SaveTempTask.this.c();
                }
            });
        }
    }
}
