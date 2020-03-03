package com.xiaomi.smarthome.stat.report;

import android.os.Handler;
import com.xiaomi.smarthome.core.server.CoreService;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class StatLogUploader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22768a = "";
    private static final String b = "stat_temp.log";
    private static FileWriter c = null;
    private static final int d = 10;
    /* access modifiers changed from: private */
    public StatLogCache e;
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<String> f;
    /* access modifiers changed from: private */
    public volatile boolean g;
    private Runnable h;

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static final java.lang.String a(java.lang.String r3, java.lang.String r4) {
        /*
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.q
            if (r0 == 0) goto L_0x007b
            android.util.Log.i(r3, r4)
            java.io.FileWriter r0 = c
            if (r0 != 0) goto L_0x0049
            android.content.Context r0 = com.xiaomi.smarthome.frame.FrameManager.f()
            java.lang.String r0 = com.xiaomi.smarthome.stat.report.StatLogCache.a((android.content.Context) r0)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "stat_temp.log"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            boolean r0 = r1.exists()     // Catch:{ Exception -> 0x0041 }
            if (r0 != 0) goto L_0x0032
            r1.createNewFile()     // Catch:{ Exception -> 0x0041 }
        L_0x0032:
            java.lang.String r0 = "stat_temp.log"
            monitor-enter(r0)     // Catch:{ Exception -> 0x0041 }
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ all -> 0x003e }
            r2.<init>(r1)     // Catch:{ all -> 0x003e }
            c = r2     // Catch:{ all -> 0x003e }
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            goto L_0x0049
        L_0x003e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            throw r1     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            r0 = move-exception
            java.lang.String r1 = "STAT-ERROR"
            java.lang.String r2 = "Failed to create temp file for stat log"
            android.util.Log.e(r1, r2, r0)
        L_0x0049:
            java.io.FileWriter r0 = c
            if (r0 == 0) goto L_0x007b
            java.lang.String r0 = "stat_temp.log"
            monitor-enter(r0)
            java.io.FileWriter r1 = c     // Catch:{ all -> 0x0078 }
            if (r1 == 0) goto L_0x0076
            java.io.FileWriter r1 = c     // Catch:{ Exception -> 0x006e }
            java.io.Writer r3 = r1.append(r3)     // Catch:{ Exception -> 0x006e }
            java.lang.String r1 = ":"
            java.io.Writer r3 = r3.append(r1)     // Catch:{ Exception -> 0x006e }
            java.io.Writer r3 = r3.append(r4)     // Catch:{ Exception -> 0x006e }
            java.lang.String r1 = "\r\n"
            java.io.Writer r3 = r3.append(r1)     // Catch:{ Exception -> 0x006e }
            r3.flush()     // Catch:{ Exception -> 0x006e }
            goto L_0x0076
        L_0x006e:
            r3 = move-exception
            java.lang.String r1 = "STAT-ERROR"
            java.lang.String r2 = "Failed to write temp file for stat log"
            android.util.Log.e(r1, r2, r3)     // Catch:{ all -> 0x0078 }
        L_0x0076:
            monitor-exit(r0)     // Catch:{ all -> 0x0078 }
            goto L_0x007b
        L_0x0078:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0078 }
            throw r3
        L_0x007b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatLogUploader.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public StatLogUploader() {
        this.e = null;
        this.f = new ConcurrentLinkedQueue<>();
        this.g = false;
        this.h = new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
                if (com.xiaomi.smarthome.stat.report.StatLogUploader.b(r9.f22769a).isEmpty() != false) goto L_0x003e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
                r0 = (java.lang.String) com.xiaomi.smarthome.stat.report.StatLogUploader.b(r9.f22769a).poll();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
                if (r0 == null) goto L_0x0020;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
                r4.add(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
                r3 = r4.isEmpty();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
                if (r3 == false) goto L_0x004d;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
                r0 = com.xiaomi.smarthome.stat.report.StatLogCache.e();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
                if (r0 == null) goto L_0x004d;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
                r4.addAll(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
                if (r4.isEmpty() == false) goto L_0x0068;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:0x0054, code lost:
                r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.c(r9.f22769a).a();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
                if (r0 != null) goto L_0x0066;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x0060, code lost:
                com.xiaomi.smarthome.stat.report.StatLogUploader.a(r9.f22769a, false);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x0065, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
                r6 = r0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
                r0 = null;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x006e, code lost:
                if (r4.isEmpty() == false) goto L_0x0081;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:32:0x0070, code lost:
                r0 = ((com.xiaomi.smarthome.stat.report.StatLogVisitor) r6.second).a(((java.lang.Integer) r6.first).intValue());
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
                r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.a(r9.f22769a, (java.util.List) r4);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:0x008b, code lost:
                if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x0093;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x008d, code lost:
                com.xiaomi.smarthome.stat.report.StatLogUploader.a(r9.f22769a, false);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x0092, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
                r5 = r0;
                com.xiaomi.smarthome.core.server.internal.statistic.api.StatApi.a().a(com.xiaomi.smarthome.core.server.CoreService.getAppContext(), r0, (com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback<com.xiaomi.smarthome.core.server.internal.statistic.entity.StatInfoResult, com.xiaomi.smarthome.core.server.internal.CoreError>) new com.xiaomi.smarthome.stat.report.StatLogUploader.AnonymousClass1.AnonymousClass1(r9));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a7, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a8, code lost:
                com.xiaomi.smarthome.stat.report.StatLogUploader.a(r9.f22769a, false);
                com.xiaomi.smarthome.stat.report.StatLogUploader.a("STAT-FAILED-ERR", r1.getMessage() + "\r\n" + r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r9 = this;
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    boolean r0 = r0.g
                    if (r0 == 0) goto L_0x0009
                    return
                L_0x0009:
                    java.util.LinkedList r4 = new java.util.LinkedList
                    r4.<init>()
                    monitor-enter(r9)
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this     // Catch:{ all -> 0x00cb }
                    boolean r0 = r0.g     // Catch:{ all -> 0x00cb }
                    if (r0 == 0) goto L_0x0019
                    monitor-exit(r9)     // Catch:{ all -> 0x00cb }
                    return
                L_0x0019:
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this     // Catch:{ all -> 0x00cb }
                    r1 = 1
                    boolean unused = r0.g = r1     // Catch:{ all -> 0x00cb }
                    monitor-exit(r9)     // Catch:{ all -> 0x00cb }
                L_0x0020:
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    java.util.concurrent.ConcurrentLinkedQueue r0 = r0.f
                    boolean r0 = r0.isEmpty()
                    if (r0 != 0) goto L_0x003e
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    java.util.concurrent.ConcurrentLinkedQueue r0 = r0.f
                    java.lang.Object r0 = r0.poll()
                    java.lang.String r0 = (java.lang.String) r0
                    if (r0 == 0) goto L_0x0020
                    r4.add(r0)
                    goto L_0x0020
                L_0x003e:
                    boolean r3 = r4.isEmpty()
                    if (r3 == 0) goto L_0x004d
                    java.util.LinkedList r0 = com.xiaomi.smarthome.stat.report.StatLogCache.e()
                    if (r0 == 0) goto L_0x004d
                    r4.addAll(r0)
                L_0x004d:
                    boolean r0 = r4.isEmpty()
                    r7 = 0
                    if (r0 == 0) goto L_0x0068
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    com.xiaomi.smarthome.stat.report.StatLogCache r0 = r0.e
                    android.util.Pair r0 = r0.a()
                    if (r0 != 0) goto L_0x0066
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    boolean unused = r0.g = r7
                    return
                L_0x0066:
                    r6 = r0
                    goto L_0x006a
                L_0x0068:
                    r0 = 0
                    goto L_0x0066
                L_0x006a:
                    boolean r0 = r4.isEmpty()
                    if (r0 == 0) goto L_0x0081
                    java.lang.Object r0 = r6.second
                    com.xiaomi.smarthome.stat.report.StatLogVisitor r0 = (com.xiaomi.smarthome.stat.report.StatLogVisitor) r0
                    java.lang.Object r1 = r6.first
                    java.lang.Integer r1 = (java.lang.Integer) r1
                    int r1 = r1.intValue()
                    java.lang.String r0 = r0.a(r1)
                    goto L_0x0087
                L_0x0081:
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    java.lang.String r0 = r0.a((java.util.List<java.lang.String>) r4)
                L_0x0087:
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    if (r1 == 0) goto L_0x0093
                    com.xiaomi.smarthome.stat.report.StatLogUploader r0 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    boolean unused = r0.g = r7
                    return
                L_0x0093:
                    com.xiaomi.smarthome.stat.report.StatLogUploader$1$1 r8 = new com.xiaomi.smarthome.stat.report.StatLogUploader$1$1     // Catch:{ Exception -> 0x00a7 }
                    r1 = r8
                    r2 = r9
                    r5 = r0
                    r1.<init>(r3, r4, r5, r6)     // Catch:{ Exception -> 0x00a7 }
                    com.xiaomi.smarthome.core.server.internal.statistic.api.StatApi r1 = com.xiaomi.smarthome.core.server.internal.statistic.api.StatApi.a()     // Catch:{ Exception -> 0x00a7 }
                    android.content.Context r2 = com.xiaomi.smarthome.core.server.CoreService.getAppContext()     // Catch:{ Exception -> 0x00a7 }
                    r1.a((android.content.Context) r2, (java.lang.String) r0, (com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback<com.xiaomi.smarthome.core.server.internal.statistic.entity.StatInfoResult, com.xiaomi.smarthome.core.server.internal.CoreError>) r8)     // Catch:{ Exception -> 0x00a7 }
                    goto L_0x00ca
                L_0x00a7:
                    r1 = move-exception
                    com.xiaomi.smarthome.stat.report.StatLogUploader r2 = com.xiaomi.smarthome.stat.report.StatLogUploader.this
                    boolean unused = r2.g = r7
                    java.lang.String r2 = "STAT-FAILED-ERR"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r1 = r1.getMessage()
                    r3.append(r1)
                    java.lang.String r1 = "\r\n"
                    r3.append(r1)
                    r3.append(r0)
                    java.lang.String r0 = r3.toString()
                    com.xiaomi.smarthome.stat.report.StatLogUploader.a((java.lang.String) r2, (java.lang.String) r0)
                L_0x00ca:
                    return
                L_0x00cb:
                    r0 = move-exception
                    monitor-exit(r9)     // Catch:{ all -> 0x00cb }
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatLogUploader.AnonymousClass1.run():void");
            }
        };
        this.e = new StatLogCache(CoreService.getAppContext());
        this.h.run();
    }

    public void a(String str) {
        if (str != null && str.length() > 2) {
            this.f.offer(str);
            a("STAT-SERVICE", str);
        }
        this.h.run();
    }

    /* access modifiers changed from: private */
    public void a() {
        new Handler().postDelayed(this.h, 10);
    }

    /* access modifiers changed from: private */
    public String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String next : list) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(next);
        }
        return sb.toString();
    }
}
