package com.xiaomi.smarthome.stat.report;

import android.content.Context;
import android.content.SharedPreferences;
import com.taobao.weex.annotation.JSMethod;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class StatLogVisitor {
    public static final int e = -2;
    public static final int f = -1;
    public static final int g = 0;
    public static final int h = 0;
    public static final int i = 1;
    private static final int n = 0;
    private static final int o = 1;
    private static AtomicInteger p;

    /* renamed from: a  reason: collision with root package name */
    public final String f22771a;
    public final String b;
    public SharedPreferences c;
    public int d = 100;
    private int j;
    private int[] k = {-2, -2};
    private StatLogFile[] l;
    private boolean[][] m;

    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r7v5 */
    public StatLogVisitor(SharedPreferences sharedPreferences, String str, int i2) {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        int i3 = i2;
        SharedPreferences.Editor editor = null;
        ? r7 = 1;
        this.l = new StatLogFile[]{null, null};
        this.m = new boolean[][]{new boolean[]{false, false}, new boolean[]{false, false}};
        this.j = i3;
        this.b = "read_" + i3 + JSMethod.NOT_SET;
        this.c = sharedPreferences2;
        this.f22771a = b(str, i2);
        int i4 = 0;
        while (i4 < this.k.length) {
            int[] iArr = this.k;
            iArr[i4] = sharedPreferences2.getInt(this.b + i4, -2);
            File file = new File(a(i4, (boolean) r7));
            File file2 = new File(a(i4, false));
            long length = file.exists() ? file.length() : 0;
            this.m[i4][0] = length > 0;
            long length2 = file2.exists() ? file2.length() : 0;
            this.m[i4][r7] = length2 > 0;
            if (this.k[i4] != -2 && length + length2 < 1) {
                this.k[i4] = -2;
                editor = editor == null ? sharedPreferences.edit() : editor;
                editor.putInt(this.b + i4, -2);
            }
            if (length + length2 > 0 && this.k[i4] < 0) {
                this.k[i4] = 0;
                editor = editor == null ? sharedPreferences.edit() : editor;
                editor.putInt(this.b + i4, 0);
                if (length < 1) {
                    try {
                        if (file.exists()) {
                            file.delete();
                        }
                        if (length2 > 0) {
                            file2.renameTo(file);
                        }
                        try {
                            this.m[i4][0] = true;
                            this.m[i4][1] = false;
                        } catch (Exception unused) {
                        }
                    } catch (Exception unused2) {
                    }
                    this.l[i4] = new StatLogFile(this, i4);
                    i4++;
                    r7 = 1;
                }
            }
            this.l[i4] = new StatLogFile(this, i4);
            i4++;
            r7 = 1;
        }
        if (editor != null) {
            editor.apply();
        }
    }

    public boolean a() {
        return !this.m[0][0] && !this.m[0][1] && !this.m[1][0] && !this.m[1][1];
    }

    private static final String b(String str, int i2) {
        String str2 = str + "d" + i2 + "/";
        StatLogCache.a(str2);
        return str2;
    }

    public static final String a(String str, int i2) {
        return b(str, i2) + "w0.log";
    }

    /* access modifiers changed from: package-private */
    public String a(int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f22771a);
        sb.append(z ? "r" : "w");
        sb.append(i2);
        sb.append(".log");
        return sb.toString();
    }

    public boolean a(Context context, String str) {
        this.l[1].a(context, str);
        synchronized (this) {
            this.m[1][1] = true;
        }
        return true;
    }

    public void b() {
        SharedPreferences.Editor edit = this.c.edit();
        SharedPreferences.Editor remove = edit.remove(this.b + 0);
        remove.remove(this.b + 1).apply();
    }

    public int c() {
        return this.d;
    }

    public String a(int i2) {
        return this.l[i2].a();
    }

    public synchronized int d() {
        for (int i2 = 1; i2 >= 0; i2--) {
            if (this.k[i2] >= 0) {
                return i2;
            }
        }
        int i3 = 1;
        while (i3 >= 0) {
            File file = new File(a(i3, true));
            if (file.exists()) {
                if (file.length() > 0) {
                    this.k[i3] = 0;
                    return i3;
                }
                file.delete();
                this.m[i3][0] = false;
            }
            File file2 = new File(a(i3, false));
            if (!file2.exists() || file2.length() <= 0) {
                i3--;
            } else {
                file2.renameTo(file);
                this.k[i3] = 0;
                this.m[i3][0] = true;
                this.m[i3][1] = false;
                return i3;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public synchronized int b(int i2) {
        return this.k[i2];
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(int i2, int i3) {
        if (this.l[0] != null) {
            this.k[i2] = i3;
        }
    }

    private static int e() {
        if (p == null) {
            p = new AtomicInteger(0);
        }
        Integer valueOf = Integer.valueOf(p.getAndIncrement());
        if (valueOf.intValue() > 2147450880) {
            p.set(0);
        }
        return valueOf.intValue();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:4|5|6|7|8|(1:10)|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0042 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0016 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[Catch:{ Exception -> 0x0042 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            int[] r0 = r4.k     // Catch:{ all -> 0x0072 }
            r0 = r0[r5]     // Catch:{ all -> 0x0072 }
            r1 = -1
            if (r0 != r1) goto L_0x004e
            r0 = 1
            java.lang.String r0 = r4.a((int) r5, (boolean) r0)     // Catch:{ all -> 0x0072 }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0016 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0016 }
            r1.delete()     // Catch:{ Exception -> 0x0016 }
            goto L_0x0042
        L_0x0016:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0042 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0042 }
            r2.<init>()     // Catch:{ Exception -> 0x0042 }
            r2.append(r0)     // Catch:{ Exception -> 0x0042 }
            int r3 = e()     // Catch:{ Exception -> 0x0042 }
            r2.append(r3)     // Catch:{ Exception -> 0x0042 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0042 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0042 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0042 }
            if (r2 == 0) goto L_0x0037
            r1.delete()     // Catch:{ Exception -> 0x0042 }
        L_0x0037:
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0042 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0042 }
            r2.renameTo(r1)     // Catch:{ Exception -> 0x0042 }
            r1.delete()     // Catch:{ Exception -> 0x0042 }
        L_0x0042:
            int[] r0 = r4.k     // Catch:{ all -> 0x0072 }
            r1 = -2
            r0[r5] = r1     // Catch:{ all -> 0x0072 }
            boolean[][] r0 = r4.m     // Catch:{ all -> 0x0072 }
            r0 = r0[r5]     // Catch:{ all -> 0x0072 }
            r1 = 0
            r0[r1] = r1     // Catch:{ all -> 0x0072 }
        L_0x004e:
            android.content.SharedPreferences r0 = r4.c     // Catch:{ all -> 0x0072 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r1.<init>()     // Catch:{ all -> 0x0072 }
            java.lang.String r2 = r4.b     // Catch:{ all -> 0x0072 }
            r1.append(r2)     // Catch:{ all -> 0x0072 }
            r1.append(r5)     // Catch:{ all -> 0x0072 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0072 }
            int[] r2 = r4.k     // Catch:{ all -> 0x0072 }
            r5 = r2[r5]     // Catch:{ all -> 0x0072 }
            android.content.SharedPreferences$Editor r5 = r0.putInt(r1, r5)     // Catch:{ all -> 0x0072 }
            r5.apply()     // Catch:{ all -> 0x0072 }
            monitor-exit(r4)
            return
        L_0x0072:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatLogVisitor.c(int):void");
    }
}
