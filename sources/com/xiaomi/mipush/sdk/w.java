package com.xiaomi.mipush.sdk;

class w implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ v f11566a;

    w(v vVar) {
        this.f11566a = vVar;
    }

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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public void run() {
        /*
            r9 = this;
            r0 = 0
            com.xiaomi.mipush.sdk.v r1 = r9.f11566a     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            android.content.Context r1 = r1.c     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            com.xiaomi.mipush.sdk.s r1 = com.xiaomi.mipush.sdk.s.a((android.content.Context) r1)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.util.ArrayList r1 = r1.a()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            if (r1 == 0) goto L_0x00e0
            int r2 = r1.size()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            r3 = 1
            if (r2 >= r3) goto L_0x001a
            goto L_0x00e0
        L_0x001a:
            com.xiaomi.mipush.sdk.v r2 = r9.f11566a     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            android.content.Context r2 = r2.c     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r3 = "C100000"
            java.util.HashMap r2 = com.xiaomi.mipush.sdk.ai.a(r2, r3)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            r3 = 0
        L_0x0027:
            int r4 = r1.size()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            if (r3 >= r4) goto L_0x00ed
            java.lang.Object r4 = r1.get(r3)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.io.File r4 = (java.io.File) r4     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            com.xiaomi.mipush.sdk.v r5 = r9.f11566a     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            android.content.Context r5 = r5.c     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            com.xiaomi.mipush.sdk.s r5 = com.xiaomi.mipush.sdk.s.a((android.content.Context) r5)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r5 = r5.a((java.io.File) r4)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            r7.<init>()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            com.xiaomi.mipush.sdk.v r8 = r9.f11566a     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            android.content.Context r8 = r8.c     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.io.File r8 = r8.getFilesDir()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r8 = "/crash"
            r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r8 = "/"
            r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r8 = r4.getName()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r8 = ".zip"
            r7.append(r8)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            r6.<init>(r7)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            com.xiaomi.push.y.a((java.io.File) r6, (java.io.File) r4)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            boolean r0 = r6.exists()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            if (r0 == 0) goto L_0x00d4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r0.<init>()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r7 = "https://api.xmpush.xiaomi.com/upload/crash_log?file="
            r0.append(r7)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r7 = r6.getName()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r0.append(r7)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r7 = "file"
            com.xiaomi.push.az.a(r0, r2, r6, r7)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r7.<init>()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            com.xiaomi.mipush.sdk.v r8 = r9.f11566a     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            android.content.Context r8 = r8.c     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.io.File r8 = r8.getFilesDir()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r7.append(r8)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r8 = "/crash"
            r7.append(r8)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r8.<init>()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r8.append(r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r5 = ":"
            r8.append(r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r5 = "0"
            r8.append(r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            java.lang.String r5 = r8.toString()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r0.<init>(r7, r5)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r4.renameTo(r0)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            com.xiaomi.mipush.sdk.v r0 = r9.f11566a     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            r0.d()     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
            goto L_0x00d9
        L_0x00d4:
            java.lang.String r0 = "zip crash file failed"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)     // Catch:{ IOException -> 0x00de, Throwable -> 0x00ec }
        L_0x00d9:
            int r3 = r3 + 1
            r0 = r6
            goto L_0x0027
        L_0x00de:
            r0 = move-exception
            goto L_0x00e9
        L_0x00e0:
            java.lang.String r1 = "no crash file to upload"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r1)     // Catch:{ IOException -> 0x00e6, Throwable -> 0x00ed }
            return
        L_0x00e6:
            r1 = move-exception
            r6 = r0
            r0 = r1
        L_0x00e9:
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r0)
        L_0x00ec:
            r0 = r6
        L_0x00ed:
            if (r0 == 0) goto L_0x0100
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x0100
            boolean r0 = r0.delete()
            if (r0 != 0) goto L_0x0100
            java.lang.String r0 = "delete zip crash file failed"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
        L_0x0100:
            java.lang.Object r0 = com.xiaomi.mipush.sdk.v.f11565a
            monitor-enter(r0)
            java.lang.Object r1 = com.xiaomi.mipush.sdk.v.f11565a     // Catch:{ all -> 0x010e }
            r1.notifyAll()     // Catch:{ all -> 0x010e }
            monitor-exit(r0)     // Catch:{ all -> 0x010e }
            return
        L_0x010e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x010e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.w.run():void");
    }
}
