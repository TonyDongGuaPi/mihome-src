package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.location.common.network.IHttpClient;
import com.amap.location.security.Core;
import com.coloros.mcssdk.mode.CommandMessage;
import com.loc.ac;

public final class cr implements IHttpClient {
    private static ac e;

    /* renamed from: a  reason: collision with root package name */
    Context f6539a = null;
    private String b = null;
    private int c = 1;
    private ci d = null;

    public cr(Context context) {
        this.f6539a = context;
    }

    private ac a() {
        try {
            String b2 = this.d.b();
            String[] strArr = {"com.amap.api.location", "com.loc", "com.amap.api.fence", "com.amap.co"};
            if (e == null) {
                e = new ac.a("loc", b2, "AMAP_Location_SDK_Android " + b2).a(strArr).a(b2).a();
            }
        } catch (Throwable unused) {
        }
        return e;
    }

    private static String a(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split("\\$")) == null) {
            return null;
        }
        return split[0];
    }

    private boolean a(String str, byte[] bArr) {
        try {
            if (str.contains("http://control.aps.amap.com/conf/r?type=3&mid=300&sver=140")) {
                String str2 = new String(Core.xxt(a(bArr), -1), "UTF-8");
                if (this.c == -1) {
                    return true;
                }
                int a2 = cl.a(this.f6539a, "his_config", "version");
                if (a2 != -1) {
                    String b2 = cl.b(this.f6539a, "his_config", CommandMessage.COMMAND);
                    if (!a(b2).equals(a(str2))) {
                        if (a2 == this.c) {
                            cl.a(this.f6539a, "LocationCloudConfig", CommandMessage.COMMAND, b2);
                            try {
                                aq.b(a(), "cloudcheck", "云控项有改变，版本号未变");
                                return false;
                            } catch (Throwable unused) {
                                return false;
                            }
                        } else {
                            cl.a(this.f6539a, "his_config", str2, this.c);
                            return true;
                        }
                    }
                } else {
                    cl.a(this.f6539a, "his_config", str2, this.c);
                    return true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:8|9|(2:10|(1:12)(1:62))|13|14|15|16|17|18|19|20|65) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x002c */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0047 A[SYNTHETIC, Splitter:B:35:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x004c A[SYNTHETIC, Splitter:B:39:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0051 A[SYNTHETIC, Splitter:B:43:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x005a A[SYNTHETIC, Splitter:B:51:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x005f A[SYNTHETIC, Splitter:B:55:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0064 A[SYNTHETIC, Splitter:B:59:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(byte[] r7) {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Throwable -> 0x0055, all -> 0x0040 }
            r1.<init>(r7)     // Catch:{ Throwable -> 0x0055, all -> 0x0040 }
            java.util.zip.GZIPInputStream r7 = new java.util.zip.GZIPInputStream     // Catch:{ Throwable -> 0x003d, all -> 0x003a }
            r7.<init>(r1)     // Catch:{ Throwable -> 0x003d, all -> 0x003a }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0038, all -> 0x0033 }
            r2.<init>()     // Catch:{ Throwable -> 0x0038, all -> 0x0033 }
            r3 = 512(0x200, float:7.175E-43)
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x0058, all -> 0x0031 }
        L_0x0014:
            int r4 = r7.read(r3)     // Catch:{ Throwable -> 0x0058, all -> 0x0031 }
            if (r4 < 0) goto L_0x001f
            r5 = 0
            r2.write(r3, r5, r4)     // Catch:{ Throwable -> 0x0058, all -> 0x0031 }
            goto L_0x0014
        L_0x001f:
            r2.flush()     // Catch:{ Throwable -> 0x0058, all -> 0x0031 }
            byte[] r3 = r2.toByteArray()     // Catch:{ Throwable -> 0x0058, all -> 0x0031 }
            r1.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            r7.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            r0 = r3
            goto L_0x0067
        L_0x0031:
            r0 = move-exception
            goto L_0x0045
        L_0x0033:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x0045
        L_0x0038:
            r2 = r0
            goto L_0x0058
        L_0x003a:
            r7 = move-exception
            r2 = r0
            goto L_0x0043
        L_0x003d:
            r7 = r0
            r2 = r7
            goto L_0x0058
        L_0x0040:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L_0x0043:
            r0 = r7
            r7 = r2
        L_0x0045:
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            if (r7 == 0) goto L_0x004f
            r7.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            if (r2 == 0) goto L_0x0054
            r2.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            throw r0
        L_0x0055:
            r7 = r0
            r1 = r7
            r2 = r1
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            if (r7 == 0) goto L_0x0062
            r7.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            if (r2 == 0) goto L_0x0067
            r2.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0067:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cr.a(byte[]):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b A[Catch:{ Throwable -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b7 A[Catch:{ Throwable -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cd A[Catch:{ t -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00df A[SYNTHETIC, Splitter:B:38:0x00df] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.amap.location.common.network.HttpResponse a(com.amap.location.common.network.HttpRequest r12) {
        /*
            r11 = this;
            java.lang.String r0 = r12.f4591a
            java.lang.String r1 = "http://offline.aps.amap.com/LoadOfflineData/repeatData"
            boolean r1 = r0.contains(r1)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0016
            java.lang.String r1 = "http://offline.aps.amap.com/LoadOfflineData/repeatData"
            java.lang.String r4 = "http://apilocate.amap.com/offline/down"
        L_0x0010:
            java.lang.String r0 = r0.replace(r1, r4)
            r1 = 1
            goto L_0x0024
        L_0x0016:
            java.lang.String r1 = "http://cgicol.amap.com/dataPipeline/uploadData"
            boolean r1 = r0.contains(r1)
            if (r1 == 0) goto L_0x0023
            java.lang.String r1 = "http://cgicol.amap.com/dataPipeline/uploadData"
            java.lang.String r4 = "http://apilocate.amap.com/offline/up"
            goto L_0x0010
        L_0x0023:
            r1 = 0
        L_0x0024:
            com.amap.location.common.network.HttpResponse r4 = new com.amap.location.common.network.HttpResponse
            r4.<init>()
            r5 = 0
            android.content.Context r6 = r11.f6539a     // Catch:{ Throwable -> 0x0031 }
            java.net.Proxy r6 = com.loc.ab.a(r6)     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0032
        L_0x0031:
            r6 = r5
        L_0x0032:
            com.loc.cq r7 = new com.loc.cq     // Catch:{ Throwable -> 0x00ee }
            r7.<init>()     // Catch:{ Throwable -> 0x00ee }
            r7.a((java.lang.String) r0)     // Catch:{ Throwable -> 0x00ee }
            r7.a((java.net.Proxy) r6)     // Catch:{ Throwable -> 0x00ee }
            int r6 = r12.d     // Catch:{ Throwable -> 0x00ee }
            r7.b(r6)     // Catch:{ Throwable -> 0x00ee }
            int r6 = r12.d     // Catch:{ Throwable -> 0x00ee }
            r7.a((int) r6)     // Catch:{ Throwable -> 0x00ee }
            java.util.Map<java.lang.String, java.lang.String> r6 = r12.b     // Catch:{ Throwable -> 0x00ee }
            if (r1 == 0) goto L_0x00b5
            java.lang.String r1 = com.loc.w.a()     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r8 = "?"
            boolean r8 = r0.contains(r8)     // Catch:{ Throwable -> 0x00ee }
            if (r8 == 0) goto L_0x006d
            java.lang.String r8 = "?"
            int r8 = r0.lastIndexOf(r8)     // Catch:{ Throwable -> 0x00ee }
            int r8 = r8 + r3
            java.lang.String r3 = r0.substring(r8)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r3 = com.loc.ad.d((java.lang.String) r3)     // Catch:{ Throwable -> 0x00ee }
            android.content.Context r8 = r11.f6539a     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r3 = com.loc.w.a(r8, r1, r3)     // Catch:{ Throwable -> 0x00ee }
            goto L_0x0083
        L_0x006d:
            android.content.Context r3 = r11.f6539a     // Catch:{ Throwable -> 0x00ee }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r9 = "key="
            r8.<init>(r9)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r9 = r11.b     // Catch:{ Throwable -> 0x00ee }
            r8.append(r9)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r3 = com.loc.w.a(r3, r1, r8)     // Catch:{ Throwable -> 0x00ee }
        L_0x0083:
            if (r6 != 0) goto L_0x008a
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ee }
            r6.<init>()     // Catch:{ Throwable -> 0x00ee }
        L_0x008a:
            java.lang.String r8 = "User-Agent"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r10 = "AMAP_Location_SDK_Android "
            r9.<init>(r10)     // Catch:{ Throwable -> 0x00ee }
            com.loc.ci r10 = r11.d     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r10 = r10.b()     // Catch:{ Throwable -> 0x00ee }
            r9.append(r10)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x00ee }
            r6.put(r8, r9)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r8 = "key"
            java.lang.String r9 = r11.b     // Catch:{ Throwable -> 0x00ee }
            r6.put(r8, r9)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r8 = "ts"
            r6.put(r8, r1)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r1 = "scode"
            r6.put(r1, r3)     // Catch:{ Throwable -> 0x00ee }
        L_0x00b5:
            if (r6 == 0) goto L_0x00ba
            r7.a((java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ Throwable -> 0x00ee }
        L_0x00ba:
            byte[] r12 = r12.c     // Catch:{ Throwable -> 0x00ee }
            r7.f6538a = r12     // Catch:{ Throwable -> 0x00ee }
            com.loc.bg.a()     // Catch:{ t -> 0x00db }
            com.loc.bk r12 = com.loc.bg.a(r7, r2)     // Catch:{ t -> 0x00db }
            byte[] r1 = r12.f6513a     // Catch:{ t -> 0x00d9 }
            boolean r0 = r11.a(r0, r1)     // Catch:{ t -> 0x00d9 }
            if (r0 == 0) goto L_0x00d8
            r4.c = r1     // Catch:{ t -> 0x00d9 }
            r0 = 200(0xc8, float:2.8E-43)
            r4.f4592a = r0     // Catch:{ t -> 0x00d9 }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r0 = r12.b     // Catch:{ t -> 0x00d9 }
            r4.b = r0     // Catch:{ t -> 0x00d9 }
            return r4
        L_0x00d8:
            return r5
        L_0x00d9:
            r0 = move-exception
            goto L_0x00dd
        L_0x00db:
            r0 = move-exception
            r12 = r5
        L_0x00dd:
            if (r12 == 0) goto L_0x00ee
            byte[] r1 = r12.f6513a     // Catch:{ Throwable -> 0x00ee }
            r4.c = r1     // Catch:{ Throwable -> 0x00ee }
            int r0 = r0.e()     // Catch:{ Throwable -> 0x00ee }
            r4.f4592a = r0     // Catch:{ Throwable -> 0x00ee }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r12 = r12.b     // Catch:{ Throwable -> 0x00ee }
            r4.b = r12     // Catch:{ Throwable -> 0x00ee }
            return r4
        L_0x00ee:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cr.a(com.amap.location.common.network.HttpRequest):com.amap.location.common.network.HttpResponse");
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void a(ci ciVar) {
        this.d = ciVar;
        if (ciVar != null) {
            this.b = ciVar.d();
        }
        if (TextUtils.isEmpty(this.b)) {
            this.b = u.f(this.f6539a);
        }
    }
}
