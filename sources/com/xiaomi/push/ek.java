package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.push.ai;
import com.xiaomi.push.service.ah;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import java.io.File;
import java.util.List;

public class ek extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f12710a;
    private SharedPreferences b;
    private ah c;

    public ek(Context context) {
        this.f12710a = context;
        this.b = context.getSharedPreferences("mipush_extra", 0);
        this.c = ah.a(context);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:45|44|61|63|64|66|67|29|68|69) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:13|14|(3:15|16|(2:73|18)(2:19|(1:74)(4:30|31|(1:75)(2:33|(2:35|76)(2:36|77))|72)))|(3:22|23|(2:25|26))|27|28) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:42|43|(0)|54|55|56) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0066 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x009d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:66:0x00b4 */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0094 A[SYNTHETIC, Splitter:B:49:0x0094] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:66:0x00b4=Splitter:B:66:0x00b4, B:27:0x0066=Splitter:B:27:0x0066, B:54:0x009d=Splitter:B:54:0x009d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.xiaomi.push.hw> a(java.io.File r11) {
        /*
            r10 = this;
            com.xiaomi.push.ds r0 = com.xiaomi.push.ds.a()
            com.xiaomi.push.dr r0 = r0.b()
            if (r0 != 0) goto L_0x000d
            java.lang.String r0 = ""
            goto L_0x0011
        L_0x000d:
            java.lang.String r0 = r0.a()
        L_0x0011:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x0019
            return r2
        L_0x0019:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 4
            byte[] r4 = new byte[r3]
            java.lang.Object r5 = com.xiaomi.push.dx.f12702a
            monitor-enter(r5)
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            android.content.Context r7 = r10.f12710a     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            java.io.File r7 = r7.getExternalFilesDir(r2)     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            java.lang.String r8 = "push_cdata.lock"
            r6.<init>(r7, r8)     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            com.xiaomi.push.y.c(r6)     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            java.lang.String r8 = "rw"
            r7.<init>(r6, r8)     // Catch:{ Exception -> 0x00a4, all -> 0x008f }
            java.nio.channels.FileChannel r6 = r7.getChannel()     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            java.nio.channels.FileLock r6 = r6.lock()     // Catch:{ Exception -> 0x008d, all -> 0x008a }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00a6, all -> 0x0088 }
            r8.<init>(r11)     // Catch:{ Exception -> 0x00a6, all -> 0x0088 }
        L_0x0048:
            int r11 = r8.read(r4)     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            if (r11 == r3) goto L_0x004f
            goto L_0x005b
        L_0x004f:
            int r11 = com.xiaomi.push.ac.a((byte[]) r4)     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            byte[] r2 = new byte[r11]     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            int r9 = r8.read(r2)     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            if (r9 == r11) goto L_0x006d
        L_0x005b:
            if (r6 == 0) goto L_0x0066
            boolean r11 = r6.isValid()     // Catch:{ all -> 0x00b2 }
            if (r11 == 0) goto L_0x0066
            r6.release()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            com.xiaomi.push.y.a((java.io.Closeable) r8)     // Catch:{ all -> 0x00b2 }
        L_0x0069:
            com.xiaomi.push.y.a((java.io.Closeable) r7)     // Catch:{ all -> 0x00b2 }
            goto L_0x00b8
        L_0x006d:
            byte[] r11 = com.xiaomi.push.dw.a(r0, r2)     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            if (r11 == 0) goto L_0x0048
            int r2 = r11.length     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            if (r2 != 0) goto L_0x0077
            goto L_0x0048
        L_0x0077:
            com.xiaomi.push.hw r2 = new com.xiaomi.push.hw     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            r2.<init>()     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            com.xiaomi.push.iy.a(r2, (byte[]) r11)     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            r1.add(r2)     // Catch:{ Exception -> 0x0086, all -> 0x0083 }
            goto L_0x0048
        L_0x0083:
            r11 = move-exception
            r2 = r8
            goto L_0x0092
        L_0x0086:
            r2 = r8
            goto L_0x00a6
        L_0x0088:
            r11 = move-exception
            goto L_0x0092
        L_0x008a:
            r11 = move-exception
            r6 = r2
            goto L_0x0092
        L_0x008d:
            r6 = r2
            goto L_0x00a6
        L_0x008f:
            r11 = move-exception
            r6 = r2
            r7 = r6
        L_0x0092:
            if (r6 == 0) goto L_0x009d
            boolean r0 = r6.isValid()     // Catch:{ all -> 0x00b2 }
            if (r0 == 0) goto L_0x009d
            r6.release()     // Catch:{ IOException -> 0x009d }
        L_0x009d:
            com.xiaomi.push.y.a((java.io.Closeable) r2)     // Catch:{ all -> 0x00b2 }
            com.xiaomi.push.y.a((java.io.Closeable) r7)     // Catch:{ all -> 0x00b2 }
            throw r11     // Catch:{ all -> 0x00b2 }
        L_0x00a4:
            r6 = r2
            r7 = r6
        L_0x00a6:
            if (r6 == 0) goto L_0x00b4
            boolean r11 = r6.isValid()     // Catch:{ all -> 0x00b2 }
            if (r11 == 0) goto L_0x00b4
            r6.release()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00b4
        L_0x00b2:
            r11 = move-exception
            goto L_0x00ba
        L_0x00b4:
            com.xiaomi.push.y.a((java.io.Closeable) r2)     // Catch:{ all -> 0x00b2 }
            goto L_0x0069
        L_0x00b8:
            monitor-exit(r5)     // Catch:{ all -> 0x00b2 }
            return r1
        L_0x00ba:
            monitor-exit(r5)     // Catch:{ all -> 0x00b2 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ek.a(java.io.File):java.util.List");
    }

    private boolean b() {
        if (az.e(this.f12710a)) {
            return false;
        }
        if (!az.g(this.f12710a) || d()) {
            return (az.h(this.f12710a) && !c()) || az.i(this.f12710a);
        }
        return true;
    }

    private boolean c() {
        if (!this.c.a(ht.Upload3GSwitch.a(), true)) {
            return false;
        }
        return Math.abs((System.currentTimeMillis() / 1000) - this.b.getLong("last_upload_data_timestamp", -1)) > ((long) Math.max(86400, this.c.a(ht.Upload3GFrequency.a(), 432000)));
    }

    private boolean d() {
        if (!this.c.a(ht.Upload4GSwitch.a(), true)) {
            return false;
        }
        return Math.abs((System.currentTimeMillis() / 1000) - this.b.getLong("last_upload_data_timestamp", -1)) > ((long) Math.max(86400, this.c.a(ht.Upload4GFrequency.a(), 259200)));
    }

    private void e() {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putLong("last_upload_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    public int a() {
        return 1;
    }

    public void run() {
        File file = new File(this.f12710a.getExternalFilesDir((String) null), "push_cdata.data");
        if (!az.d(this.f12710a)) {
            if (file.length() > 1863680) {
                file.delete();
            }
        } else if (!b() && file.exists()) {
            List<hw> a2 = a(file);
            if (!ad.a(a2)) {
                int size = a2.size();
                if (size > 4000) {
                    a2 = a2.subList(size + LoginErrorCode.D, size);
                }
                ih ihVar = new ih();
                ihVar.a(a2);
                byte[] a3 = y.a(iy.a(ihVar));
                in inVar = new in("-1", false);
                inVar.c(hy.DataCollection.f114a);
                inVar.a(a3);
                dr b2 = ds.a().b();
                if (b2 != null) {
                    b2.a(inVar, ho.Notification, (ib) null);
                }
                e();
            }
            file.delete();
            this.b.edit().remove("ltapn").commit();
        }
    }
}
