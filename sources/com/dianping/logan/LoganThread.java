package com.dianping.logan;

import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.dianping.logan.LoganModel;
import com.dianping.logan.SendLogRunnable;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class LoganThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5167a = "LoganThread";
    private static final int b = 60000;
    private static final long c = 86400000;
    private static final int d = 1024;
    private final Object e = new Object();
    /* access modifiers changed from: private */
    public final Object f = new Object();
    private volatile boolean g = true;
    private long h;
    private boolean i;
    private File j;
    private boolean k;
    private long l;
    private LoganProtocol m;
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<LoganModel> n;
    private String o;
    private String p;
    private long q;
    private long r;
    private long s;
    private String t;
    private String u;
    /* access modifiers changed from: private */
    public int v;
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<LoganModel> w = new ConcurrentLinkedQueue<>();
    private ExecutorService x;

    LoganThread(ConcurrentLinkedQueue<LoganModel> concurrentLinkedQueue, String str, String str2, long j2, long j3, long j4, String str3, String str4) {
        this.n = concurrentLinkedQueue;
        this.o = str;
        this.p = str2;
        this.q = j2;
        this.r = j3;
        this.s = j4;
        this.t = str3;
        this.u = str4;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (!this.i) {
            synchronized (this.e) {
                this.e.notify();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.g = false;
        if (!this.i) {
            synchronized (this.e) {
                this.e.notify();
            }
        }
    }

    public void run() {
        super.run();
        while (this.g) {
            synchronized (this.e) {
                this.i = true;
                try {
                    LoganModel poll = this.n.poll();
                    if (poll == null) {
                        this.i = false;
                        this.e.wait();
                        this.i = true;
                    } else {
                        a(poll);
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    this.i = false;
                }
            }
        }
    }

    private void a(LoganModel loganModel) {
        if (loganModel != null && loganModel.a()) {
            if (this.m == null) {
                this.m = LoganProtocol.a();
                this.m.a((OnLoganProtocolStatus) new OnLoganProtocolStatus() {
                    public void a(String str, int i) {
                        Logan.b(str, i);
                    }
                });
                this.m.a(this.o, this.p, (int) this.r, this.t, this.u);
                this.m.a(Logan.f5161a);
            }
            if (loganModel.f5165a == LoganModel.Action.WRITE) {
                a(loganModel.b);
            } else if (loganModel.f5165a == LoganModel.Action.SEND) {
                if (loganModel.c.d != null) {
                    synchronized (this.f) {
                        if (this.v == 10001) {
                            this.w.add(loganModel);
                        } else {
                            a(loganModel.c);
                        }
                    }
                }
            } else if (loganModel.f5165a == LoganModel.Action.FLUSH) {
                c();
            }
        }
    }

    private void c() {
        if (Logan.f5161a) {
            Log.d(f5167a, "Logan flush start");
        }
        if (this.m != null) {
            this.m.c();
        }
    }

    private boolean d() {
        long currentTimeMillis = System.currentTimeMillis();
        return this.h < currentTimeMillis && this.h + 86400000 > currentTimeMillis;
    }

    private void a(long j2) {
        String[] list;
        File file = new File(this.p);
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        String[] split = str.split("\\.");
                        if (split.length > 0 && Long.valueOf(split[0]).longValue() <= j2 && split.length == 1) {
                            new File(this.p, str).delete();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void a(WriteAction writeAction) {
        if (Logan.f5161a) {
            Log.d(f5167a, "Logan write start");
        }
        if (this.j == null) {
            this.j = new File(this.p);
        }
        if (!d()) {
            String a2 = Util.a();
            long a3 = Util.a(a2);
            a(a3 - this.q);
            this.h = a3;
            this.m.a(a2);
        }
        if (System.currentTimeMillis() - this.l > 60000) {
            this.k = e();
        }
        this.l = System.currentTimeMillis();
        if (this.k) {
            this.m.a(writeAction.f, writeAction.f5175a, writeAction.e, writeAction.d, writeAction.c, writeAction.b);
        }
    }

    private void a(SendAction sendAction) {
        if (Logan.f5161a) {
            Log.d(f5167a, "Logan send start");
        }
        if (!TextUtils.isEmpty(this.p) && sendAction != null && sendAction.a()) {
            if (b(sendAction)) {
                sendAction.d.a(sendAction);
                sendAction.d.a((SendLogRunnable.OnSendLogCallBackListener) new SendLogRunnable.OnSendLogCallBackListener() {
                    public void a(int i) {
                        synchronized (LoganThread.this.f) {
                            int unused = LoganThread.this.v = i;
                            if (i == 10002) {
                                LoganThread.this.n.addAll(LoganThread.this.w);
                                LoganThread.this.w.clear();
                                LoganThread.this.a();
                            }
                        }
                    }
                });
                this.v = 10001;
                if (this.x == null) {
                    this.x = Executors.newSingleThreadExecutor(new ThreadFactory() {
                        public Thread newThread(Runnable runnable) {
                            Thread thread = new Thread(Thread.currentThread().getThreadGroup(), runnable, "logan-thread-send-log", 0);
                            if (thread.isDaemon()) {
                                thread.setDaemon(false);
                            }
                            if (thread.getPriority() != 5) {
                                thread.setPriority(5);
                            }
                            return thread;
                        }
                    });
                }
                this.x.execute(sendAction.d);
            } else if (Logan.f5161a) {
                Log.d(f5167a, "Logan prepare log file failed, can't find log file");
            }
        }
    }

    private boolean b(SendAction sendAction) {
        if (Logan.f5161a) {
            Log.d(f5167a, "prepare log file");
        }
        if (a(sendAction.b)) {
            String str = this.p + File.separator + sendAction.b;
            if (sendAction.b.equals(Util.a())) {
                c();
                String str2 = this.p + File.separator + sendAction.b + ".copy";
                if (!a(str, str2)) {
                    return false;
                }
                sendAction.c = str2;
                return true;
            }
            sendAction.c = str;
            return true;
        }
        sendAction.c = "";
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0053 A[SYNTHETIC, Splitter:B:37:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005d A[SYNTHETIC, Splitter:B:42:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0068 A[SYNTHETIC, Splitter:B:49:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0072 A[SYNTHETIC, Splitter:B:54:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x007b A[SYNTHETIC, Splitter:B:61:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0085 A[SYNTHETIC, Splitter:B:66:0x0085] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:46:0x0063=Splitter:B:46:0x0063, B:34:0x004e=Splitter:B:34:0x004e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x004c, all -> 0x0049 }
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x004c, all -> 0x0049 }
            r3.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x004c, all -> 0x0049 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x004c, all -> 0x0049 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0041, all -> 0x003f }
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0041, all -> 0x003f }
            r3.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0041, all -> 0x003f }
            r5.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0041, all -> 0x003f }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003b, all -> 0x0039 }
        L_0x001a:
            int r0 = r2.read(r6)     // Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003b, all -> 0x0039 }
            if (r0 < 0) goto L_0x0027
            r5.write(r6, r1, r0)     // Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003b, all -> 0x0039 }
            r5.flush()     // Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003b, all -> 0x0039 }
            goto L_0x001a
        L_0x0027:
            r1 = 1
            r2.close()     // Catch:{ Exception -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0030:
            r5.close()     // Catch:{ Exception -> 0x0034 }
            goto L_0x0075
        L_0x0034:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x0075
        L_0x0039:
            r6 = move-exception
            goto L_0x0078
        L_0x003b:
            r6 = move-exception
            goto L_0x0043
        L_0x003d:
            r6 = move-exception
            goto L_0x0047
        L_0x003f:
            r6 = move-exception
            goto L_0x0079
        L_0x0041:
            r6 = move-exception
            r5 = r0
        L_0x0043:
            r0 = r2
            goto L_0x004e
        L_0x0045:
            r6 = move-exception
            r5 = r0
        L_0x0047:
            r0 = r2
            goto L_0x0063
        L_0x0049:
            r6 = move-exception
            r2 = r0
            goto L_0x0079
        L_0x004c:
            r6 = move-exception
            r5 = r0
        L_0x004e:
            r6.printStackTrace()     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ Exception -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r6 = move-exception
            r6.printStackTrace()
        L_0x005b:
            if (r5 == 0) goto L_0x0075
            r5.close()     // Catch:{ Exception -> 0x0034 }
            goto L_0x0075
        L_0x0061:
            r6 = move-exception
            r5 = r0
        L_0x0063:
            r6.printStackTrace()     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x0070
            r0.close()     // Catch:{ Exception -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0070:
            if (r5 == 0) goto L_0x0075
            r5.close()     // Catch:{ Exception -> 0x0034 }
        L_0x0075:
            return r1
        L_0x0076:
            r6 = move-exception
            r2 = r0
        L_0x0078:
            r0 = r5
        L_0x0079:
            if (r2 == 0) goto L_0x0083
            r2.close()     // Catch:{ Exception -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0083:
            if (r0 == 0) goto L_0x008d
            r0.close()     // Catch:{ Exception -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r5 = move-exception
            r5.printStackTrace()
        L_0x008d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dianping.logan.LoganThread.a(java.lang.String, java.lang.String):boolean");
    }

    private boolean e() {
        boolean z = false;
        try {
            StatFs statFs = new StatFs(this.p);
            long availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            if (availableBlocksLong > this.s) {
                z = true;
            }
            Log.e(f5167a, "isCanWriteSDCard:" + availableBlocksLong + "  " + this.s);
        } catch (IllegalArgumentException e2) {
            Log.e(f5167a, AppMeasurement.Param.FATAL, e2);
        }
        return z;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(this.p)) {
            return false;
        }
        File file = new File(this.p + File.separator + str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        return true;
    }
}
