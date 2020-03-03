package org.xutils.cache;

import android.text.TextUtils;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.FileUtil;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.common.util.MD5;
import org.xutils.common.util.ProcessLock;
import org.xutils.config.DbConfigs;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.ex.FileLockedException;
import org.xutils.x;

public final class LruDiskCache {

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<String, LruDiskCache> f4211a = new HashMap<>(5);
    private static final int b = 5000;
    private static final long c = 104857600;
    private static final int d = 3000;
    private static final String e = "xUtils_cache";
    private static final String f = ".tmp";
    private static final long m = 1000;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public final DbManager h = x.a(DbConfigs.HTTP.getConfig());
    /* access modifiers changed from: private */
    public File i;
    /* access modifiers changed from: private */
    public long j = 104857600;
    private final Executor k = new PriorityExecutor(1, true);
    /* access modifiers changed from: private */
    public long l = 0;

    public static synchronized LruDiskCache a(String str) {
        LruDiskCache lruDiskCache;
        synchronized (LruDiskCache.class) {
            if (TextUtils.isEmpty(str)) {
                str = e;
            }
            lruDiskCache = f4211a.get(str);
            if (lruDiskCache == null) {
                lruDiskCache = new LruDiskCache(str);
                f4211a.put(str, lruDiskCache);
            }
        }
        return lruDiskCache;
    }

    private LruDiskCache(String str) {
        this.i = FileUtil.a(str);
        if (this.i != null && (this.i.exists() || this.i.mkdirs())) {
            this.g = true;
        }
        d();
    }

    public LruDiskCache a(long j2) {
        if (j2 > 0) {
            long b2 = FileUtil.b();
            if (b2 > j2) {
                this.j = j2;
            } else {
                this.j = b2;
            }
        }
        return this;
    }

    public DiskCacheEntity b(String str) {
        final DiskCacheEntity diskCacheEntity;
        if (!this.g || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            diskCacheEntity = this.h.d(DiskCacheEntity.class).a("key", "=", str).f();
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
            diskCacheEntity = null;
        }
        if (diskCacheEntity != null) {
            if (diskCacheEntity.e() < System.currentTimeMillis()) {
                return null;
            }
            this.k.execute(new Runnable() {
                public void run() {
                    diskCacheEntity.c(diskCacheEntity.g() + 1);
                    diskCacheEntity.d(System.currentTimeMillis());
                    try {
                        LruDiskCache.this.h.a((Object) diskCacheEntity, "hits", "lastAccess");
                    } catch (Throwable th) {
                        LogUtil.b(th.getMessage(), th);
                    }
                }
            });
        }
        return diskCacheEntity;
    }

    public void a(DiskCacheEntity diskCacheEntity) {
        if (this.g && diskCacheEntity != null && !TextUtils.isEmpty(diskCacheEntity.d()) && diskCacheEntity.e() >= System.currentTimeMillis()) {
            try {
                this.h.d((Object) diskCacheEntity);
            } catch (DbException e2) {
                LogUtil.b(e2.getMessage(), e2);
            }
            b();
        }
    }

    public DiskCacheFile c(String str) throws InterruptedException {
        DiskCacheEntity b2;
        ProcessLock a2;
        if (!this.g || TextUtils.isEmpty(str) || (b2 = b(str)) == null || !new File(b2.c()).exists() || (a2 = ProcessLock.a(b2.c(), false, 3000)) == null || !a2.a()) {
            return null;
        }
        DiskCacheFile diskCacheFile = new DiskCacheFile(b2, b2.c(), a2);
        if (diskCacheFile.exists()) {
            return diskCacheFile;
        }
        try {
            this.h.e((Object) b2);
            return null;
        } catch (DbException e2) {
            LogUtil.b(e2.getMessage(), e2);
            return null;
        }
    }

    public DiskCacheFile b(DiskCacheEntity diskCacheEntity) throws IOException {
        if (!this.g || diskCacheEntity == null) {
            return null;
        }
        diskCacheEntity.b(new File(this.i, MD5.a(diskCacheEntity.b())).getAbsolutePath());
        String str = diskCacheEntity.c() + ".tmp";
        ProcessLock a2 = ProcessLock.a(str, true);
        if (a2 == null || !a2.a()) {
            throw new FileLockedException(diskCacheEntity.c());
        }
        DiskCacheFile diskCacheFile = new DiskCacheFile(diskCacheEntity, str, a2);
        if (!diskCacheFile.getParentFile().exists()) {
            diskCacheFile.mkdirs();
        }
        return diskCacheFile;
    }

    public void a() {
        IOUtil.a(this.i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004c, code lost:
        r1 = r0;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0081, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081 A[ExcHandler: InterruptedException (e java.lang.InterruptedException), Splitter:B:18:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.xutils.cache.DiskCacheFile a(org.xutils.cache.DiskCacheFile r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            if (r7 == 0) goto L_0x0011
            long r1 = r7.length()
            r3 = 1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0011
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r7)
            return r0
        L_0x0011:
            boolean r1 = r6.g
            if (r1 == 0) goto L_0x00c8
            if (r7 != 0) goto L_0x0019
            goto L_0x00c8
        L_0x0019:
            org.xutils.cache.DiskCacheEntity r1 = r7.cacheEntity
            java.lang.String r2 = r7.getName()
            java.lang.String r3 = ".tmp"
            boolean r2 = r2.endsWith(r3)
            if (r2 == 0) goto L_0x00c7
            java.lang.String r2 = r1.c()     // Catch:{ InterruptedException -> 0x0093, all -> 0x008f }
            r3 = 1
            r4 = 3000(0xbb8, double:1.482E-320)
            org.xutils.common.util.ProcessLock r3 = org.xutils.common.util.ProcessLock.a((java.lang.String) r2, (boolean) r3, (long) r4)     // Catch:{ InterruptedException -> 0x0093, all -> 0x008f }
            if (r3 == 0) goto L_0x0089
            boolean r4 = r3.a()     // Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
            if (r4 == 0) goto L_0x0089
            org.xutils.cache.DiskCacheFile r4 = new org.xutils.cache.DiskCacheFile     // Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
            r4.<init>(r1, r2, r3)     // Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
            boolean r2 = r7.renameTo(r4)     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            if (r2 == 0) goto L_0x0064
            org.xutils.DbManager r0 = r6.h     // Catch:{ DbException -> 0x0050 }
            r0.d((java.lang.Object) r1)     // Catch:{ DbException -> 0x0050 }
            goto L_0x0058
        L_0x004b:
            r0 = move-exception
            r1 = r0
            r0 = r4
            goto L_0x00b4
        L_0x0050:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()     // Catch:{ InterruptedException -> 0x0081, all -> 0x004b }
            org.xutils.common.util.LogUtil.b(r1, r0)     // Catch:{ InterruptedException -> 0x0081, all -> 0x004b }
        L_0x0058:
            r6.b()     // Catch:{ InterruptedException -> 0x0081, all -> 0x004b }
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r7)
            org.xutils.common.util.IOUtil.a((java.io.File) r7)
            r7 = r4
            goto L_0x00c7
        L_0x0064:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            r2.<init>()     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            java.lang.String r5 = "rename:"
            r2.append(r5)     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            java.lang.String r5 = r7.getAbsolutePath()     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            r2.append(r5)     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            java.lang.String r2 = r2.toString()     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            r1.<init>(r2)     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
            throw r1     // Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        L_0x007f:
            r1 = move-exception
            goto L_0x00b4
        L_0x0081:
            r0 = move-exception
            goto L_0x0097
        L_0x0083:
            r1 = move-exception
            r4 = r0
            goto L_0x00b4
        L_0x0086:
            r1 = move-exception
            r4 = r0
            goto L_0x0096
        L_0x0089:
            org.xutils.ex.FileLockedException r1 = new org.xutils.ex.FileLockedException     // Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
            r1.<init>(r2)     // Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
            throw r1     // Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
        L_0x008f:
            r1 = move-exception
            r3 = r0
            r4 = r3
            goto L_0x00b4
        L_0x0093:
            r1 = move-exception
            r3 = r0
            r4 = r3
        L_0x0096:
            r0 = r1
        L_0x0097:
            java.lang.String r1 = r0.getMessage()     // Catch:{ all -> 0x00b1 }
            org.xutils.common.util.LogUtil.b(r1, r0)     // Catch:{ all -> 0x00b1 }
            if (r7 != 0) goto L_0x00aa
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r4)
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r3)
            org.xutils.common.util.IOUtil.a((java.io.File) r4)
            goto L_0x00c7
        L_0x00aa:
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r7)
            org.xutils.common.util.IOUtil.a((java.io.File) r7)
            goto L_0x00c7
        L_0x00b1:
            r0 = move-exception
            r1 = r0
            r0 = r7
        L_0x00b4:
            if (r0 != 0) goto L_0x00c0
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r4)
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r3)
            org.xutils.common.util.IOUtil.a((java.io.File) r4)
            goto L_0x00c6
        L_0x00c0:
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r7)
            org.xutils.common.util.IOUtil.a((java.io.File) r7)
        L_0x00c6:
            throw r1
        L_0x00c7:
            return r7
        L_0x00c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.cache.LruDiskCache.a(org.xutils.cache.DiskCacheFile):org.xutils.cache.DiskCacheFile");
    }

    private void b() {
        this.k.execute(new Runnable() {
            public void run() {
                List<DiskCacheEntity> g;
                if (LruDiskCache.this.g) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - LruDiskCache.this.l >= 1000) {
                        long unused = LruDiskCache.this.l = currentTimeMillis;
                        LruDiskCache.this.c();
                        try {
                            int h = (int) LruDiskCache.this.h.d(DiskCacheEntity.class).h();
                            if (h > 5010 && (g = LruDiskCache.this.h.d(DiskCacheEntity.class).c("lastAccess").c("hits").a(h + LoginErrorCode.I).b(0).g()) != null && g.size() > 0) {
                                for (DiskCacheEntity next : g) {
                                    String c = next.c();
                                    if (!TextUtils.isEmpty(c) && LruDiskCache.this.d(c)) {
                                        LruDiskCache lruDiskCache = LruDiskCache.this;
                                        if (lruDiskCache.d(c + ".tmp")) {
                                            LruDiskCache.this.h.e((Object) next);
                                        }
                                    }
                                }
                            }
                        } catch (DbException e) {
                            LogUtil.b(e.getMessage(), e);
                        }
                        while (FileUtil.a(LruDiskCache.this.i) > LruDiskCache.this.j) {
                            try {
                                List<DiskCacheEntity> g2 = LruDiskCache.this.h.d(DiskCacheEntity.class).c("lastAccess").c("hits").a(10).b(0).g();
                                if (g2 != null && g2.size() > 0) {
                                    for (DiskCacheEntity next2 : g2) {
                                        String c2 = next2.c();
                                        if (!TextUtils.isEmpty(c2) && LruDiskCache.this.d(c2)) {
                                            LruDiskCache lruDiskCache2 = LruDiskCache.this;
                                            if (lruDiskCache2.d(c2 + ".tmp")) {
                                                LruDiskCache.this.h.e((Object) next2);
                                            }
                                        }
                                    }
                                }
                            } catch (DbException e2) {
                                LogUtil.b(e2.getMessage(), e2);
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            WhereBuilder a2 = WhereBuilder.a("expires", "<", Long.valueOf(System.currentTimeMillis()));
            List<DiskCacheEntity> g2 = this.h.d(DiskCacheEntity.class).a(a2).g();
            this.h.a((Class<?>) DiskCacheEntity.class, a2);
            if (g2 != null && g2.size() > 0) {
                for (DiskCacheEntity c2 : g2) {
                    String c3 = c2.c();
                    if (!TextUtils.isEmpty(c3)) {
                        d(c3);
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    private void d() {
        this.k.execute(new Runnable() {
            public void run() {
                if (LruDiskCache.this.g) {
                    try {
                        File[] listFiles = LruDiskCache.this.i.listFiles();
                        if (listFiles != null) {
                            for (File file : listFiles) {
                                if (LruDiskCache.this.h.d(DiskCacheEntity.class).a("path", "=", file.getAbsolutePath()).h() < 1) {
                                    IOUtil.a(file);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        LogUtil.b(th.getMessage(), th);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean d(String str) {
        ProcessLock processLock;
        try {
            processLock = ProcessLock.a(str, true);
            if (processLock != null) {
                try {
                    if (processLock.a()) {
                        boolean a2 = IOUtil.a(new File(str));
                        IOUtil.a((Closeable) processLock);
                        return a2;
                    }
                } catch (Throwable th) {
                    th = th;
                    IOUtil.a((Closeable) processLock);
                    throw th;
                }
            }
            IOUtil.a((Closeable) processLock);
            return false;
        } catch (Throwable th2) {
            th = th2;
            processLock = null;
            IOUtil.a((Closeable) processLock);
            throw th;
        }
    }
}
