package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.adobe.xmp.XMPConst;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class bq {

    /* renamed from: a  reason: collision with root package name */
    public static int f4345a = -1;
    public static String b = "";

    public static class a {
        @Deprecated
        public c A;
        public c B;
        public b C;
        public b D;
        public b E;
        public b F;
        public f G;
        /* access modifiers changed from: private */
        public boolean H;

        /* renamed from: a  reason: collision with root package name */
        public String f4346a;
        public int b = -1;
        @Deprecated
        public JSONObject c;
        @Deprecated
        public JSONObject d;
        @Deprecated
        public JSONObject e;
        @Deprecated
        public JSONObject f;
        @Deprecated
        public JSONObject g;
        @Deprecated
        public JSONObject h;
        @Deprecated
        public JSONObject i;
        @Deprecated
        public JSONObject j;
        @Deprecated
        public JSONObject k;
        @Deprecated
        public JSONObject l;
        @Deprecated
        public JSONObject m;
        @Deprecated
        public JSONObject n;
        @Deprecated
        public JSONObject o;
        @Deprecated
        public JSONObject p;
        @Deprecated
        public JSONObject q;
        @Deprecated
        public JSONObject r;
        @Deprecated
        public JSONObject s;
        @Deprecated
        public JSONObject t;
        @Deprecated
        public JSONObject u;
        @Deprecated
        public JSONObject v;
        public JSONObject w;
        public C0034a x;
        public d y;
        public e z;

        /* renamed from: com.amap.api.services.a.bq$a$a  reason: collision with other inner class name */
        public static class C0034a {

            /* renamed from: a  reason: collision with root package name */
            public boolean f4347a;
            public boolean b;
            public JSONObject c;
        }

        public static class b {

            /* renamed from: a  reason: collision with root package name */
            public boolean f4348a;
            public String b;
            public String c;
            public String d;
            public boolean e;
        }

        public static class c {

            /* renamed from: a  reason: collision with root package name */
            public String f4349a;
            public String b;
        }

        public static class d {

            /* renamed from: a  reason: collision with root package name */
            public String f4350a;
            public String b;
            public String c;
        }

        public static class e {

            /* renamed from: a  reason: collision with root package name */
            public boolean f4351a;
        }

        public static class f {

            /* renamed from: a  reason: collision with root package name */
            public boolean f4352a;
            public boolean b;
            public boolean c;
            public String d;
            public String e;
            public String f;
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] split = URLDecoder.decode(str).split("/");
            if (split[split.length - 1].charAt(4) % 2 == 1) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return z;
        }
    }

    public static a a(Context context, by byVar, String str, Map<String, String> map) {
        return a(context, byVar, str, map, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00af, code lost:
        r3 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0097 */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[ExcHandler: IllegalBlockSizeException (unused javax.crypto.IllegalBlockSizeException), PHI: r10 
      PHI: (r10v18 java.lang.String) = (r10v0 java.lang.String), (r10v0 java.lang.String), (r10v0 java.lang.String), (r10v22 java.lang.String) binds: [B:1:0x0015, B:2:?, B:3:0x001a, B:27:0x0097] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:1:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.amap.api.services.a.bq.a a(android.content.Context r8, com.amap.api.services.a.by r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11, boolean r12) {
        /*
            com.amap.api.services.a.bq$a r12 = new com.amap.api.services.a.bq$a
            r12.<init>()
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            r12.w = r0
            com.amap.api.services.a.bv r0 = com.amap.api.services.a.bv.a()
            r0.a((android.content.Context) r8)
            r0 = 0
            r1 = 0
            com.amap.api.services.a.db r2 = new com.amap.api.services.a.db     // Catch:{ bo -> 0x00b3, IllegalBlockSizeException -> 0x00af, Throwable -> 0x00a2 }
            r2.<init>()     // Catch:{ bo -> 0x00b3, IllegalBlockSizeException -> 0x00af, Throwable -> 0x00a2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            r3.<init>()     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            r3.append(r10)     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            java.lang.String r4 = ";14N;"
            r3.append(r4)     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            java.lang.String r4 = "15K"
            r3.append(r4)     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            java.lang.String r4 = ";"
            r3.append(r4)     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            java.lang.String r4 = "16H"
            r3.append(r4)     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            java.lang.String r3 = r3.toString()     // Catch:{ bo -> 0x00a0, Throwable -> 0x0097, IllegalBlockSizeException -> 0x00af }
            com.amap.api.services.a.bq$b r10 = new com.amap.api.services.a.bq$b     // Catch:{ bo -> 0x0093, Throwable -> 0x0091, IllegalBlockSizeException -> 0x00b0 }
            r10.<init>(r8, r9, r3, r11)     // Catch:{ bo -> 0x0093, Throwable -> 0x0091, IllegalBlockSizeException -> 0x00b0 }
            boolean r11 = r10.a()     // Catch:{ bo -> 0x0093, Throwable -> 0x0091, IllegalBlockSizeException -> 0x00b0 }
            com.amap.api.services.a.dh r10 = r2.a(r10, r11)     // Catch:{ bo -> 0x0093, Throwable -> 0x0091, IllegalBlockSizeException -> 0x00b0 }
            if (r10 == 0) goto L_0x0058
            byte[] r11 = r10.f4403a     // Catch:{ bo -> 0x0053, IllegalBlockSizeException -> 0x0050, Throwable -> 0x004c }
            goto L_0x0059
        L_0x004c:
            r11 = move-exception
            r2 = r11
            r11 = r0
            goto L_0x00a7
        L_0x0050:
            r11 = r0
            goto L_0x00c3
        L_0x0053:
            r11 = move-exception
            r2 = r11
            r11 = r0
            goto L_0x00b8
        L_0x0058:
            r11 = r0
        L_0x0059:
            r2 = 16
            byte[] r4 = new byte[r2]     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            int r5 = r11.length     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            int r5 = r5 - r2
            byte[] r5 = new byte[r5]     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            java.lang.System.arraycopy(r11, r1, r4, r1, r2)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            int r6 = r11.length     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            int r6 = r6 - r2
            java.lang.System.arraycopy(r11, r2, r5, r1, r6)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            javax.crypto.spec.SecretKeySpec r2 = new javax.crypto.spec.SecretKeySpec     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            java.lang.String r6 = "AES"
            r2.<init>(r4, r6)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            java.lang.String r4 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r4 = javax.crypto.Cipher.getInstance(r4)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            javax.crypto.spec.IvParameterSpec r6 = new javax.crypto.spec.IvParameterSpec     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            byte[] r7 = com.amap.api.services.a.bz.c()     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            r6.<init>(r7)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            r7 = 2
            r4.init(r7, r2, r6)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            byte[] r2 = r4.doFinal(r5)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            java.lang.String r2 = com.amap.api.services.a.bz.a((byte[]) r2)     // Catch:{ bo -> 0x008f, IllegalBlockSizeException -> 0x00c3, Throwable -> 0x008d }
            r0 = r2
            goto L_0x00c3
        L_0x008d:
            r2 = move-exception
            goto L_0x00a7
        L_0x008f:
            r2 = move-exception
            goto L_0x00b8
        L_0x0091:
            r10 = r3
            goto L_0x0097
        L_0x0093:
            r10 = move-exception
            r11 = r10
            r10 = r3
            goto L_0x00a1
        L_0x0097:
            com.amap.api.services.a.bo r11 = new com.amap.api.services.a.bo     // Catch:{ bo -> 0x00b3, IllegalBlockSizeException -> 0x00af, Throwable -> 0x00a2 }
            java.lang.String r2 = "未知的错误"
            r11.<init>(r2)     // Catch:{ bo -> 0x00b3, IllegalBlockSizeException -> 0x00af, Throwable -> 0x00a2 }
            throw r11     // Catch:{ bo -> 0x00b3, IllegalBlockSizeException -> 0x00af, Throwable -> 0x00a2 }
        L_0x00a0:
            r11 = move-exception
        L_0x00a1:
            throw r11     // Catch:{ bo -> 0x00b3, IllegalBlockSizeException -> 0x00af, Throwable -> 0x00a2 }
        L_0x00a2:
            r11 = move-exception
            r3 = r10
            r2 = r11
            r10 = r0
            r11 = r10
        L_0x00a7:
            java.lang.String r4 = "at"
            java.lang.String r5 = "lc"
            com.amap.api.services.a.cl.c(r2, r4, r5)
            goto L_0x00c3
        L_0x00af:
            r3 = r10
        L_0x00b0:
            r10 = r0
            r11 = r10
            goto L_0x00c3
        L_0x00b3:
            r11 = move-exception
            r3 = r10
            r2 = r11
            r10 = r0
            r11 = r10
        L_0x00b8:
            java.lang.String r4 = r2.a()
            r12.f4346a = r4
            java.lang.String r4 = "/v3/iasdkauth"
            com.amap.api.services.a.cl.a((com.amap.api.services.a.by) r9, (java.lang.String) r4, (com.amap.api.services.a.bo) r2)
        L_0x00c3:
            if (r11 != 0) goto L_0x00c6
            return r12
        L_0x00c6:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x00d0
            java.lang.String r0 = com.amap.api.services.a.bz.a((byte[]) r11)
        L_0x00d0:
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ Throwable -> 0x025b }
            r11.<init>(r0)     // Catch:{ Throwable -> 0x025b }
            java.lang.String r0 = "status"
            boolean r0 = r11.has(r0)     // Catch:{ Throwable -> 0x025b }
            if (r0 == 0) goto L_0x0263
            java.lang.String r0 = "status"
            int r0 = r11.getInt(r0)     // Catch:{ Throwable -> 0x025b }
            r2 = 1
            if (r0 != r2) goto L_0x00eb
            f4345a = r2     // Catch:{ Throwable -> 0x025b }
            goto L_0x012c
        L_0x00eb:
            if (r0 != 0) goto L_0x012c
            java.lang.String r0 = "authcsid"
            java.lang.String r2 = "authgsid"
            if (r10 == 0) goto L_0x00f7
            java.lang.String r0 = r10.c     // Catch:{ Throwable -> 0x025b }
            java.lang.String r2 = r10.d     // Catch:{ Throwable -> 0x025b }
        L_0x00f7:
            com.amap.api.services.a.bz.a(r8, r0, r2, r11)     // Catch:{ Throwable -> 0x025b }
            f4345a = r1     // Catch:{ Throwable -> 0x025b }
            java.lang.String r10 = "info"
            boolean r10 = r11.has(r10)     // Catch:{ Throwable -> 0x025b }
            if (r10 == 0) goto L_0x010c
            java.lang.String r10 = "info"
            java.lang.String r10 = r11.getString(r10)     // Catch:{ Throwable -> 0x025b }
            b = r10     // Catch:{ Throwable -> 0x025b }
        L_0x010c:
            java.lang.String r10 = ""
            java.lang.String r0 = "infocode"
            boolean r0 = r11.has(r0)     // Catch:{ Throwable -> 0x025b }
            if (r0 == 0) goto L_0x011c
            java.lang.String r10 = "infocode"
            java.lang.String r10 = r11.getString(r10)     // Catch:{ Throwable -> 0x025b }
        L_0x011c:
            java.lang.String r0 = "/v3/iasdkauth"
            java.lang.String r4 = b     // Catch:{ Throwable -> 0x025b }
            com.amap.api.services.a.cl.a(r9, r0, r4, r2, r10)     // Catch:{ Throwable -> 0x025b }
            int r9 = f4345a     // Catch:{ Throwable -> 0x025b }
            if (r9 != 0) goto L_0x012c
            java.lang.String r8 = b     // Catch:{ Throwable -> 0x025b }
            r12.f4346a = r8     // Catch:{ Throwable -> 0x025b }
            return r12
        L_0x012c:
            java.lang.String r9 = "ver"
            boolean r9 = r11.has(r9)     // Catch:{ Throwable -> 0x013f }
            if (r9 == 0) goto L_0x0147
            java.lang.String r9 = "ver"
            int r9 = r11.getInt(r9)     // Catch:{ Throwable -> 0x013f }
            r12.b = r9     // Catch:{ Throwable -> 0x013f }
            goto L_0x0147
        L_0x013f:
            r9 = move-exception
            java.lang.String r10 = "at"
            java.lang.String r0 = "lc"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r9, (java.lang.String) r10, (java.lang.String) r0)     // Catch:{ Throwable -> 0x025b }
        L_0x0147:
            java.lang.String r9 = "result"
            boolean r9 = com.amap.api.services.a.bz.a((org.json.JSONObject) r11, (java.lang.String) r9)     // Catch:{ Throwable -> 0x025b }
            if (r9 == 0) goto L_0x0263
            com.amap.api.services.a.bq$a$a r9 = new com.amap.api.services.a.bq$a$a     // Catch:{ Throwable -> 0x025b }
            r9.<init>()     // Catch:{ Throwable -> 0x025b }
            r9.f4347a = r1     // Catch:{ Throwable -> 0x025b }
            r9.b = r1     // Catch:{ Throwable -> 0x025b }
            r12.x = r9     // Catch:{ Throwable -> 0x025b }
            java.lang.String r10 = "result"
            org.json.JSONObject r10 = r11.getJSONObject(r10)     // Catch:{ Throwable -> 0x025b }
            java.lang.String r11 = ";"
            java.lang.String[] r11 = r3.split(r11)     // Catch:{ Throwable -> 0x0183 }
            if (r11 == 0) goto L_0x018b
            int r0 = r11.length     // Catch:{ Throwable -> 0x0183 }
            if (r0 <= 0) goto L_0x018b
            int r0 = r11.length     // Catch:{ Throwable -> 0x0183 }
            r2 = 0
        L_0x016d:
            if (r2 >= r0) goto L_0x018b
            r3 = r11[r2]     // Catch:{ Throwable -> 0x0183 }
            boolean r4 = r10.has(r3)     // Catch:{ Throwable -> 0x0183 }
            if (r4 == 0) goto L_0x0180
            org.json.JSONObject r4 = r12.w     // Catch:{ Throwable -> 0x0183 }
            java.lang.Object r5 = r10.get(r3)     // Catch:{ Throwable -> 0x0183 }
            r4.putOpt(r3, r5)     // Catch:{ Throwable -> 0x0183 }
        L_0x0180:
            int r2 = r2 + 1
            goto L_0x016d
        L_0x0183:
            r11 = move-exception
            java.lang.String r0 = "at"
            java.lang.String r2 = "co"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r2)     // Catch:{ Throwable -> 0x025b }
        L_0x018b:
            java.lang.String r11 = "16H"
            boolean r11 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r11)     // Catch:{ Throwable -> 0x025b }
            if (r11 == 0) goto L_0x01a6
            java.lang.String r11 = "16H"
            org.json.JSONObject r11 = r10.getJSONObject(r11)     // Catch:{ Throwable -> 0x025b }
            java.lang.String r0 = "able"
            java.lang.String r11 = r11.optString(r0)     // Catch:{ Throwable -> 0x025b }
            boolean r11 = a((java.lang.String) r11, (boolean) r1)     // Catch:{ Throwable -> 0x025b }
            boolean unused = r12.H = r11     // Catch:{ Throwable -> 0x025b }
        L_0x01a6:
            java.lang.String r11 = "11K"
            boolean r11 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r11)     // Catch:{ Throwable -> 0x025b }
            if (r11 == 0) goto L_0x01d9
            java.lang.String r11 = "11K"
            org.json.JSONObject r11 = r10.getJSONObject(r11)     // Catch:{ Throwable -> 0x01d1 }
            java.lang.String r0 = "able"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Throwable -> 0x01d1 }
            boolean r0 = a((java.lang.String) r0, (boolean) r1)     // Catch:{ Throwable -> 0x01d1 }
            r9.f4347a = r0     // Catch:{ Throwable -> 0x01d1 }
            java.lang.String r0 = "off"
            boolean r0 = r11.has(r0)     // Catch:{ Throwable -> 0x01d1 }
            if (r0 == 0) goto L_0x01d9
            java.lang.String r0 = "off"
            org.json.JSONObject r11 = r11.getJSONObject(r0)     // Catch:{ Throwable -> 0x01d1 }
            r9.c = r11     // Catch:{ Throwable -> 0x01d1 }
            goto L_0x01d9
        L_0x01d1:
            r9 = move-exception
            java.lang.String r11 = "AuthConfigManager"
            java.lang.String r0 = "loadException"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r9, (java.lang.String) r11, (java.lang.String) r0)     // Catch:{ Throwable -> 0x025b }
        L_0x01d9:
            java.lang.String r9 = "001"
            boolean r9 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r9)     // Catch:{ Throwable -> 0x025b }
            if (r9 == 0) goto L_0x01f1
            java.lang.String r9 = "001"
            org.json.JSONObject r9 = r10.getJSONObject(r9)     // Catch:{ Throwable -> 0x025b }
            com.amap.api.services.a.bq$a$d r11 = new com.amap.api.services.a.bq$a$d     // Catch:{ Throwable -> 0x025b }
            r11.<init>()     // Catch:{ Throwable -> 0x025b }
            a((org.json.JSONObject) r9, (com.amap.api.services.a.bq.a.d) r11)     // Catch:{ Throwable -> 0x025b }
            r12.y = r11     // Catch:{ Throwable -> 0x025b }
        L_0x01f1:
            java.lang.String r9 = "002"
            boolean r9 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r9)     // Catch:{ Throwable -> 0x025b }
            if (r9 == 0) goto L_0x0209
            java.lang.String r9 = "002"
            org.json.JSONObject r9 = r10.getJSONObject(r9)     // Catch:{ Throwable -> 0x025b }
            com.amap.api.services.a.bq$a$c r11 = new com.amap.api.services.a.bq$a$c     // Catch:{ Throwable -> 0x025b }
            r11.<init>()     // Catch:{ Throwable -> 0x025b }
            a((org.json.JSONObject) r9, (com.amap.api.services.a.bq.a.c) r11)     // Catch:{ Throwable -> 0x025b }
            r12.A = r11     // Catch:{ Throwable -> 0x025b }
        L_0x0209:
            java.lang.String r9 = "14S"
            boolean r9 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r9)     // Catch:{ Throwable -> 0x025b }
            if (r9 == 0) goto L_0x0221
            java.lang.String r9 = "14S"
            org.json.JSONObject r9 = r10.getJSONObject(r9)     // Catch:{ Throwable -> 0x025b }
            com.amap.api.services.a.bq$a$c r11 = new com.amap.api.services.a.bq$a$c     // Catch:{ Throwable -> 0x025b }
            r11.<init>()     // Catch:{ Throwable -> 0x025b }
            a((org.json.JSONObject) r9, (com.amap.api.services.a.bq.a.c) r11)     // Catch:{ Throwable -> 0x025b }
            r12.B = r11     // Catch:{ Throwable -> 0x025b }
        L_0x0221:
            a((com.amap.api.services.a.bq.a) r12, (org.json.JSONObject) r10)     // Catch:{ Throwable -> 0x025b }
            java.lang.String r9 = "14Z"
            boolean r9 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r9)     // Catch:{ Throwable -> 0x025b }
            if (r9 == 0) goto L_0x023c
            java.lang.String r9 = "14Z"
            org.json.JSONObject r9 = r10.getJSONObject(r9)     // Catch:{ Throwable -> 0x025b }
            com.amap.api.services.a.bq$a$f r11 = new com.amap.api.services.a.bq$a$f     // Catch:{ Throwable -> 0x025b }
            r11.<init>()     // Catch:{ Throwable -> 0x025b }
            a((org.json.JSONObject) r9, (com.amap.api.services.a.bq.a.f) r11)     // Catch:{ Throwable -> 0x025b }
            r12.G = r11     // Catch:{ Throwable -> 0x025b }
        L_0x023c:
            java.lang.String r9 = "151"
            boolean r9 = com.amap.api.services.a.bz.a((org.json.JSONObject) r10, (java.lang.String) r9)     // Catch:{ Throwable -> 0x025b }
            if (r9 == 0) goto L_0x0254
            java.lang.String r9 = "151"
            org.json.JSONObject r9 = r10.getJSONObject(r9)     // Catch:{ Throwable -> 0x025b }
            com.amap.api.services.a.bq$a$e r11 = new com.amap.api.services.a.bq$a$e     // Catch:{ Throwable -> 0x025b }
            r11.<init>()     // Catch:{ Throwable -> 0x025b }
            a((org.json.JSONObject) r9, (com.amap.api.services.a.bq.a.e) r11)     // Catch:{ Throwable -> 0x025b }
            r12.z = r11     // Catch:{ Throwable -> 0x025b }
        L_0x0254:
            a((com.amap.api.services.a.bq.a) r12, (org.json.JSONObject) r10)     // Catch:{ Throwable -> 0x025b }
            a((android.content.Context) r8, (org.json.JSONObject) r10)     // Catch:{ Throwable -> 0x025b }
            goto L_0x0263
        L_0x025b:
            r8 = move-exception
            java.lang.String r9 = "at"
            java.lang.String r10 = "lc"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r8, (java.lang.String) r9, (java.lang.String) r10)
        L_0x0263:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bq.a(android.content.Context, com.amap.api.services.a.by, java.lang.String, java.util.Map, boolean):com.amap.api.services.a.bq$a");
    }

    private static void a(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("15K");
            boolean a2 = a(jSONObject2.optString("isTargetAble"), false);
            if (!a(jSONObject2.optString("able"), false)) {
                bv.a().b(context);
            } else {
                bv.a().a(context, a2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str) && !jSONObject.getString(str).equals(XMPConst.ai)) {
            return jSONObject.optString(str);
        }
        return "";
    }

    private static void a(JSONObject jSONObject, a.b bVar) {
        if (bVar != null) {
            try {
                String a2 = a(jSONObject, "m");
                String a3 = a(jSONObject, "u");
                String a4 = a(jSONObject, "v");
                String a5 = a(jSONObject, "able");
                String a6 = a(jSONObject, "on");
                bVar.c = a2;
                bVar.b = a3;
                bVar.d = a4;
                bVar.f4348a = a(a5, false);
                bVar.e = a(a6, true);
            } catch (Throwable th) {
                ci.a(th, "at", "pe");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.f fVar) {
        if (fVar != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "md5info");
                String a4 = a(jSONObject, "url");
                String a5 = a(jSONObject, "able");
                String a6 = a(jSONObject, "on");
                String a7 = a(jSONObject, "mobileable");
                fVar.e = a2;
                fVar.f = a3;
                fVar.d = a4;
                fVar.f4352a = a(a5, false);
                fVar.b = a(a6, false);
                fVar.c = a(a7, false);
            } catch (Throwable th) {
                ci.a(th, "at", "pes");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.c cVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                cVar.b = a2;
                cVar.f4349a = a3;
            } catch (Throwable th) {
                ci.a(th, "at", "psc");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.d dVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                String a4 = a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    if (!TextUtils.isEmpty(a4)) {
                        dVar.f4350a = a3;
                        dVar.b = a2;
                        dVar.c = a4;
                    }
                }
            } catch (Throwable th) {
                ci.a(th, "at", "psu");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.e eVar) {
        if (eVar != null && jSONObject != null) {
            eVar.f4351a = a(jSONObject.optString("able"), false);
        }
    }

    static class b extends dc {
        private String c;
        private Map<String, String> d;
        private boolean e;

        public byte[] b() {
            return null;
        }

        public Map<String, String> e() {
            return null;
        }

        /* access modifiers changed from: protected */
        public String f() {
            return "3.0";
        }

        b(Context context, by byVar, String str, Map<String, String> map) {
            super(context, byVar);
            this.c = str;
            this.d = map;
            this.e = Build.VERSION.SDK_INT != 19;
        }

        public boolean a() {
            return this.e;
        }

        public String i() {
            return this.e ? "https://restapi.amap.com/v3/iasdkauth" : "http://restapi.amap.com/v3/iasdkauth";
        }

        public byte[] c() {
            return bz.a(bz.a(r()));
        }

        private Map<String, String> r() {
            String u = bt.u(this.f4398a);
            if (TextUtils.isEmpty(u)) {
                u = bt.i(this.f4398a);
            }
            if (!TextUtils.isEmpty(u)) {
                u = bw.a(new StringBuilder(u).reverse().toString());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("authkey", this.c);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put(AgentOptions.k, "json");
            hashMap.put("androidversion", Build.VERSION.SDK_INT + "");
            hashMap.put("deviceId", u);
            hashMap.put("manufacture", Build.MANUFACTURER);
            if (this.d != null && !this.d.isEmpty()) {
                hashMap.putAll(this.d);
            }
            hashMap.put("abitype", bz.a(this.f4398a));
            hashMap.put("ext", this.b.d());
            return hashMap;
        }
    }

    private static void a(a aVar, JSONObject jSONObject) {
        try {
            if (bz.a(jSONObject, "11B")) {
                aVar.h = jSONObject.getJSONObject("11B");
            }
            if (bz.a(jSONObject, "11C")) {
                aVar.k = jSONObject.getJSONObject("11C");
            }
            if (bz.a(jSONObject, "11I")) {
                aVar.l = jSONObject.getJSONObject("11I");
            }
            if (bz.a(jSONObject, "11H")) {
                aVar.m = jSONObject.getJSONObject("11H");
            }
            if (bz.a(jSONObject, "11E")) {
                aVar.n = jSONObject.getJSONObject("11E");
            }
            if (bz.a(jSONObject, "11F")) {
                aVar.o = jSONObject.getJSONObject("11F");
            }
            if (bz.a(jSONObject, "13A")) {
                aVar.q = jSONObject.getJSONObject("13A");
            }
            if (bz.a(jSONObject, "13J")) {
                aVar.i = jSONObject.getJSONObject("13J");
            }
            if (bz.a(jSONObject, "11G")) {
                aVar.p = jSONObject.getJSONObject("11G");
            }
            if (bz.a(jSONObject, "006")) {
                aVar.r = jSONObject.getJSONObject("006");
            }
            if (bz.a(jSONObject, "010")) {
                aVar.s = jSONObject.getJSONObject("010");
            }
            if (bz.a(jSONObject, "11Z")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("11Z");
                a.b bVar = new a.b();
                a(jSONObject2, bVar);
                aVar.C = bVar;
            }
            if (bz.a(jSONObject, "135")) {
                aVar.j = jSONObject.getJSONObject("135");
            }
            if (bz.a(jSONObject, "13S")) {
                aVar.g = jSONObject.getJSONObject("13S");
            }
            if (bz.a(jSONObject, "121")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("121");
                a.b bVar2 = new a.b();
                a(jSONObject3, bVar2);
                aVar.D = bVar2;
            }
            if (bz.a(jSONObject, "122")) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("122");
                a.b bVar3 = new a.b();
                a(jSONObject4, bVar3);
                aVar.E = bVar3;
            }
            if (bz.a(jSONObject, "123")) {
                JSONObject jSONObject5 = jSONObject.getJSONObject("123");
                a.b bVar4 = new a.b();
                a(jSONObject5, bVar4);
                aVar.F = bVar4;
            }
            if (bz.a(jSONObject, "011")) {
                aVar.c = jSONObject.getJSONObject("011");
            }
            if (bz.a(jSONObject, "012")) {
                aVar.d = jSONObject.getJSONObject("012");
            }
            if (bz.a(jSONObject, "013")) {
                aVar.e = jSONObject.getJSONObject("013");
            }
            if (bz.a(jSONObject, "014")) {
                aVar.f = jSONObject.getJSONObject("014");
            }
            if (bz.a(jSONObject, "145")) {
                aVar.t = jSONObject.getJSONObject("145");
            }
            if (bz.a(jSONObject, "14B")) {
                aVar.u = jSONObject.getJSONObject("14B");
            }
            if (bz.a(jSONObject, "14D")) {
                aVar.v = jSONObject.getJSONObject("14D");
            }
        } catch (Throwable th) {
            cl.c(th, "at", "pe");
        }
    }
}
