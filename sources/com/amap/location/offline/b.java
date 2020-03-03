package com.amap.location.offline;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.common.model.FPS;
import com.amap.openapi.co;
import java.util.LinkedList;
import java.util.List;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private Context f4600a;
    private IOfflineCloudConfig b;
    private List<String> c = new LinkedList();
    private ProviderInfo d;
    private ContentValues e = new ContentValues();

    public b(Context context, OfflineConfig offlineConfig, IOfflineCloudConfig iOfflineCloudConfig) {
        this.f4600a = context;
        this.b = iOfflineCloudConfig;
        a(offlineConfig, iOfflineCloudConfig);
    }

    private void a() {
        this.d = null;
        if (!this.c.isEmpty()) {
            this.c.remove(0);
        }
    }

    private void a(OfflineConfig offlineConfig, IOfflineCloudConfig iOfflineCloudConfig) {
        this.c.clear();
        int i = 0;
        if (iOfflineCloudConfig != null && iOfflineCloudConfig.f() != null) {
            String[] f = iOfflineCloudConfig.f();
            int length = f.length;
            while (i < length) {
                this.c.add(f[i]);
                i++;
            }
        } else if (offlineConfig != null && offlineConfig.r != null) {
            String[] strArr = offlineConfig.r;
            int length2 = strArr.length;
            while (i < length2) {
                this.c.add(strArr[i]);
                i++;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.amap.openapi.co$a} */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f A[SYNTHETIC, Splitter:B:16:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046 A[SYNTHETIC, Splitter:B:24:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0050 A[LOOP:0: B:0:0x0000->B:31:0x0050, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.amap.openapi.co.a a(com.amap.location.common.model.FPS r9, int r10, java.lang.String r11) {
        /*
            r8 = this;
        L_0x0000:
            boolean r0 = r8.a((java.lang.String) r11)
            if (r0 == 0) goto L_0x0054
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r2 = "content://"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            android.content.pm.ProviderInfo r2 = r8.d     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r2 = r2.authority     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r1.append(r2)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            android.net.Uri r3 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String[] r6 = com.amap.openapi.co.a(r11, r9, r0, r10)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            android.content.Context r1 = r8.f4600a     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r4 = 0
            r5 = 0
            r7 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            com.amap.openapi.co$a r2 = com.amap.openapi.co.a((android.database.Cursor) r1)     // Catch:{ Exception -> 0x0044, all -> 0x0039 }
            if (r1 == 0) goto L_0x0037
            r1.close()     // Catch:{ Exception -> 0x0037 }
        L_0x0037:
            r0 = r2
            goto L_0x0049
        L_0x0039:
            r9 = move-exception
            r0 = r1
            goto L_0x003d
        L_0x003c:
            r9 = move-exception
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            r0.close()     // Catch:{ Exception -> 0x0042 }
        L_0x0042:
            throw r9
        L_0x0043:
            r1 = r0
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ Exception -> 0x0049 }
        L_0x0049:
            if (r0 == 0) goto L_0x0050
            boolean r1 = r0.f4665a
            if (r1 == 0) goto L_0x0050
            return r0
        L_0x0050:
            r8.a()
            goto L_0x0000
        L_0x0054:
            com.amap.openapi.co$a r9 = new com.amap.openapi.co$a
            r9.<init>()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.location.offline.b.a(com.amap.location.common.model.FPS, int, java.lang.String):com.amap.openapi.co$a");
    }

    public void a(OfflineConfig offlineConfig) {
        a(offlineConfig, this.b);
    }

    public boolean a(FPS fps, AmapLoc amapLoc, String str) {
        boolean z = false;
        if (!a(str)) {
            return false;
        }
        try {
            if (this.f4600a.getContentResolver().update(Uri.parse("content://" + this.d.authority), this.e, (String) null, co.a(str, fps, amapLoc, 0)) == 1) {
                z = true;
            }
        } catch (Exception unused) {
        }
        if (z) {
            return true;
        }
        a();
        return a(str);
    }

    public boolean a(String str) {
        if (this.d != null) {
            if (str == null || !str.equals(this.d.packageName)) {
                return true;
            }
            a();
        }
        while (!this.c.isEmpty()) {
            try {
                ProviderInfo resolveContentProvider = this.f4600a.getPackageManager().resolveContentProvider(this.c.get(0), 0);
                if (resolveContentProvider != null && (str == null || !str.equals(resolveContentProvider.packageName))) {
                    this.d = resolveContentProvider;
                    return true;
                }
            } catch (Exception unused) {
            }
            this.c.remove(0);
        }
        return false;
    }
}
