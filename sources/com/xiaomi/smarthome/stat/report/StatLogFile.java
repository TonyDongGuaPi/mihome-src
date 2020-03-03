package com.xiaomi.smarthome.stat.report;

import android.content.Context;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.smarthome.core.server.CoreAsyncTask;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StatLogFile {
    private static final Map<String, ConcurrentLinkedQueue<String>> c = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static final ConcurrentLinkedQueue<String> d = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public static int e = 100;

    /* renamed from: a  reason: collision with root package name */
    private StatLogVisitor f22762a;
    private int b = 0;

    StatLogFile(StatLogVisitor statLogVisitor, int i) {
        this.f22762a = statLogVisitor;
        this.b = i;
    }

    public String a() {
        FileInputStream fileInputStream;
        int b2 = this.f22762a.b(this.b);
        if (b2 < 0) {
            return null;
        }
        File file = new File(this.f22762a.a(this.b, true));
        if (!file.canRead()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            if (b2 > 0) {
                try {
                    fileInputStream.skip((long) b2);
                } catch (Exception unused) {
                    IOUtils.a((InputStream) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    IOUtils.a((InputStream) fileInputStream);
                    throw th;
                }
            }
            byte[] bArr = new byte[1024];
            int c2 = this.f22762a.c();
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    int i = c2;
                    for (int i2 = 0; i2 < read; i2++) {
                        if (bArr[i2] == 10) {
                            if (i == 1) {
                                this.f22762a.a(this.b, b2 + i2 + 1);
                                sb.append(new String(bArr, 0, i2));
                                String sb2 = sb.toString();
                                IOUtils.a((InputStream) fileInputStream);
                                return sb2;
                            }
                            i--;
                            bArr[i2] = Constants.TagName.SYSTEM_NEW_VERSION;
                        }
                    }
                    b2 += read;
                    sb.append(new String(bArr, 0, read));
                    c2 = i;
                } else {
                    this.f22762a.a(this.b, -1);
                    sb.setLength(sb.length() - 1);
                    String replace = sb.toString().replace(9, '0');
                    IOUtils.a((InputStream) fileInputStream);
                    return replace;
                }
            }
        } catch (Exception unused2) {
            fileInputStream = null;
            IOUtils.a((InputStream) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            IOUtils.a((InputStream) fileInputStream);
            throw th;
        }
    }

    public static LinkedList<String> b() {
        if (d.isEmpty()) {
            return null;
        }
        LinkedList<String> linkedList = new LinkedList<>();
        while (!d.isEmpty()) {
            String poll = d.poll();
            if (poll != null) {
                linkedList.add(poll);
            }
        }
        return linkedList;
    }

    private static ConcurrentLinkedQueue<String> a(String str) {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = c.get(str);
        if (concurrentLinkedQueue != null) {
            return concurrentLinkedQueue;
        }
        ConcurrentLinkedQueue<String> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
        c.put(str, concurrentLinkedQueue2);
        return concurrentLinkedQueue2;
    }

    public static boolean a(Context context, final String str, String str2) {
        final ConcurrentLinkedQueue<String> a2 = a(str);
        a2.offer(str2);
        new CoreAsyncTask() {
            public void onCancel() {
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(5:10|(7:11|12|(1:14)|15|(5:19|(2:21|52)(1:51)|50|17|16)|22|23)|40|41|42) */
            /* JADX WARNING: Can't wrap try/catch for region: R(5:28|43|44|45|46) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x009b */
            /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a0 */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x007f A[Catch:{ all -> 0x005a }, LOOP:1: B:32:0x006d->B:36:0x007f, LOOP_END] */
            /* JADX WARNING: Removed duplicated region for block: B:37:0x0088 A[Catch:{ all -> 0x005a }, EDGE_INSN: B:53:0x0088->B:37:0x0088 ?: BREAK  
            EDGE_INSN: B:54:0x0088->B:37:0x0088 ?: BREAK  ] */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:45:0x00a0=Splitter:B:45:0x00a0, B:40:0x009b=Splitter:B:40:0x009b} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    java.util.concurrent.ConcurrentLinkedQueue r0 = r0
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L_0x0009
                    return
                L_0x0009:
                    java.util.concurrent.ConcurrentLinkedQueue r0 = r0
                    monitor-enter(r0)
                    java.util.concurrent.ConcurrentLinkedQueue r1 = r0     // Catch:{ all -> 0x00a1 }
                    boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00a1 }
                    if (r1 == 0) goto L_0x0016
                    monitor-exit(r0)     // Catch:{ all -> 0x00a1 }
                    return
                L_0x0016:
                    java.io.File r1 = new java.io.File     // Catch:{ all -> 0x00a1 }
                    java.lang.String r2 = r1     // Catch:{ all -> 0x00a1 }
                    r1.<init>(r2)     // Catch:{ all -> 0x00a1 }
                    r2 = 0
                    java.util.LinkedList r3 = new java.util.LinkedList     // Catch:{ all -> 0x00a1 }
                    r3.<init>()     // Catch:{ all -> 0x00a1 }
                    boolean r4 = r1.exists()     // Catch:{ Exception -> 0x005c }
                    if (r4 != 0) goto L_0x002c
                    r1.createNewFile()     // Catch:{ Exception -> 0x005c }
                L_0x002c:
                    java.io.FileWriter r4 = new java.io.FileWriter     // Catch:{ Exception -> 0x005c }
                    r5 = 1
                    r4.<init>(r1, r5)     // Catch:{ Exception -> 0x005c }
                L_0x0032:
                    java.util.concurrent.ConcurrentLinkedQueue r1 = r0     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    boolean r1 = r1.isEmpty()     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    if (r1 != 0) goto L_0x0050
                    java.util.concurrent.ConcurrentLinkedQueue r1 = r0     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    java.lang.Object r1 = r1.poll()     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    if (r1 == 0) goto L_0x0032
                    r4.append(r1)     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    r2 = 10
                    r4.append(r2)     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    r3.offer(r1)     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
                    goto L_0x0032
                L_0x0050:
                    r4.close()     // Catch:{ Exception -> 0x009b }
                    goto L_0x009b
                L_0x0054:
                    r1 = move-exception
                    r2 = r4
                    goto L_0x009d
                L_0x0057:
                    r1 = move-exception
                    r2 = r4
                    goto L_0x005d
                L_0x005a:
                    r1 = move-exception
                    goto L_0x009d
                L_0x005c:
                    r1 = move-exception
                L_0x005d:
                    r1.printStackTrace()     // Catch:{ all -> 0x005a }
                    java.util.concurrent.ConcurrentLinkedQueue r4 = com.xiaomi.smarthome.stat.report.StatLogFile.d     // Catch:{ all -> 0x005a }
                    int r4 = r4.size()     // Catch:{ all -> 0x005a }
                    int r5 = r3.size()     // Catch:{ all -> 0x005a }
                    int r4 = r4 + r5
                L_0x006d:
                    int r5 = r4 + -1
                    int r6 = com.xiaomi.smarthome.stat.report.StatLogFile.e     // Catch:{ all -> 0x005a }
                    if (r4 <= r6) goto L_0x0088
                    java.util.concurrent.ConcurrentLinkedQueue r4 = com.xiaomi.smarthome.stat.report.StatLogFile.d     // Catch:{ all -> 0x005a }
                    boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x005a }
                    if (r4 != 0) goto L_0x0088
                    java.util.concurrent.ConcurrentLinkedQueue r4 = com.xiaomi.smarthome.stat.report.StatLogFile.d     // Catch:{ all -> 0x005a }
                    r4.poll()     // Catch:{ all -> 0x005a }
                    r4 = r5
                    goto L_0x006d
                L_0x0088:
                    java.util.concurrent.ConcurrentLinkedQueue r4 = com.xiaomi.smarthome.stat.report.StatLogFile.d     // Catch:{ all -> 0x005a }
                    r4.addAll(r3)     // Catch:{ all -> 0x005a }
                    java.lang.String r3 = "STAT-ERR-FILE"
                    java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x005a }
                    com.xiaomi.smarthome.stat.report.StatLogUploader.a((java.lang.String) r3, (java.lang.String) r1)     // Catch:{ all -> 0x005a }
                    r2.close()     // Catch:{ Exception -> 0x009b }
                L_0x009b:
                    monitor-exit(r0)     // Catch:{ all -> 0x00a1 }
                    return
                L_0x009d:
                    r2.close()     // Catch:{ Exception -> 0x00a0 }
                L_0x00a0:
                    throw r1     // Catch:{ all -> 0x00a1 }
                L_0x00a1:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00a1 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatLogFile.AnonymousClass1.run():void");
            }
        }.execute();
        return true;
    }

    public synchronized boolean a(Context context, String str) {
        return a(context, this.f22762a.a(this.b, false), str);
    }
}
