package com.loc;

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

public final class v {

    /* renamed from: a  reason: collision with root package name */
    public static int f6639a = -1;
    public static String b = "";

    public static class a {
        @Deprecated
        public c A;
        public c B;
        public b C;
        public b D;
        public b E;
        public b F;
        public e G;
        /* access modifiers changed from: private */
        public boolean H;

        /* renamed from: a  reason: collision with root package name */
        public String f6640a;
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
        public C0060a x;
        public d y;
        public f z;

        /* renamed from: com.loc.v$a$a  reason: collision with other inner class name */
        public static class C0060a {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6641a;
            public boolean b;
            public JSONObject c;
        }

        public static class b {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6642a;
            public String b;
            public String c;
            public String d;
            public boolean e;
        }

        public static class c {

            /* renamed from: a  reason: collision with root package name */
            public String f6643a;
            public String b;
        }

        public static class d {

            /* renamed from: a  reason: collision with root package name */
            public String f6644a;
            public String b;
            public String c;
        }

        public static class e {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6645a;
            public boolean b;
            public boolean c;
            public String d;
            public String e;
            public String f;
        }

        public static class f {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6646a;
        }
    }

    static class b extends bh {
        private String f;
        private Map<String, String> g = null;
        private boolean h;

        b(Context context, ac acVar, String str) {
            super(context, acVar);
            this.f = str;
            this.h = Build.VERSION.SDK_INT != 19;
        }

        public final Map<String, String> a() {
            return null;
        }

        public final String c() {
            return this.h ? "https://restapi.amap.com/v3/iasdkauth" : "http://restapi.amap.com/v3/iasdkauth";
        }

        /* access modifiers changed from: protected */
        public final String g() {
            return "3.0";
        }

        public final byte[] h() {
            return null;
        }

        public final byte[] i() {
            String u = x.u(this.f6509a);
            if (TextUtils.isEmpty(u)) {
                u = x.h(this.f6509a);
            }
            if (!TextUtils.isEmpty(u)) {
                u = aa.b(new StringBuilder(u).reverse().toString());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("authkey", this.f);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put(AgentOptions.k, "json");
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            hashMap.put("androidversion", sb.toString());
            hashMap.put("deviceId", u);
            hashMap.put("manufacture", Build.MANUFACTURER);
            if (this.g != null && !this.g.isEmpty()) {
                hashMap.putAll(this.g);
            }
            hashMap.put("abitype", ad.a(this.f6509a));
            hashMap.put("ext", this.b.d());
            return ad.a(ad.a((Map<String, String>) hashMap));
        }

        public final boolean n() {
            return this.h;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0093, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0094, code lost:
        r3 = r13;
        r13 = null;
        r5 = r4;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a0, code lost:
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a4, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a5, code lost:
        r3 = r13;
        r13 = null;
        r5 = r4;
        r4 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0088 */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[ExcHandler: IllegalBlockSizeException (unused javax.crypto.IllegalBlockSizeException), PHI: r13 
      PHI: (r13v21 java.lang.String) = (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v25 java.lang.String) binds: [B:1:0x0013, B:2:?, B:3:0x0018, B:27:0x0088] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:1:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.loc.v.a a(android.content.Context r11, com.loc.ac r12, java.lang.String r13) {
        /*
            com.loc.v$a r0 = new com.loc.v$a
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            r0.w = r1
            com.loc.z r1 = com.loc.z.a.f6652a
            r1.a((android.content.Context) r11)
            r1 = 0
            r2 = 0
            com.loc.bg r3 = new com.loc.bg     // Catch:{ t -> 0x00a4, IllegalBlockSizeException -> 0x00a0, Throwable -> 0x0093 }
            r3.<init>()     // Catch:{ t -> 0x00a4, IllegalBlockSizeException -> 0x00a0, Throwable -> 0x0093 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ t -> 0x0091, Throwable -> 0x0088, IllegalBlockSizeException -> 0x00a0 }
            r3.<init>()     // Catch:{ t -> 0x0091, Throwable -> 0x0088, IllegalBlockSizeException -> 0x00a0 }
            r3.append(r13)     // Catch:{ t -> 0x0091, Throwable -> 0x0088, IllegalBlockSizeException -> 0x00a0 }
            java.lang.String r4 = ";14N;15K;16H"
            r3.append(r4)     // Catch:{ t -> 0x0091, Throwable -> 0x0088, IllegalBlockSizeException -> 0x00a0 }
            java.lang.String r3 = r3.toString()     // Catch:{ t -> 0x0091, Throwable -> 0x0088, IllegalBlockSizeException -> 0x00a0 }
            com.loc.v$b r13 = new com.loc.v$b     // Catch:{ t -> 0x0083, Throwable -> 0x0081, IllegalBlockSizeException -> 0x00a1 }
            r13.<init>(r11, r12, r3)     // Catch:{ t -> 0x0083, Throwable -> 0x0081, IllegalBlockSizeException -> 0x00a1 }
            boolean r4 = r13.n()     // Catch:{ t -> 0x0083, Throwable -> 0x0081, IllegalBlockSizeException -> 0x00a1 }
            com.loc.bk r13 = com.loc.bg.a(r13, r4)     // Catch:{ t -> 0x0083, Throwable -> 0x0081, IllegalBlockSizeException -> 0x00a1 }
            if (r13 == 0) goto L_0x0048
            byte[] r4 = r13.f6513a     // Catch:{ t -> 0x0043, IllegalBlockSizeException -> 0x0040, Throwable -> 0x003b }
            goto L_0x0049
        L_0x003b:
            r4 = move-exception
            r5 = r4
            r4 = r1
            goto L_0x0098
        L_0x0040:
            r4 = r1
            goto L_0x00b4
        L_0x0043:
            r4 = move-exception
            r5 = r4
            r4 = r1
            goto L_0x00a9
        L_0x0048:
            r4 = r1
        L_0x0049:
            r5 = 16
            byte[] r6 = new byte[r5]     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            int r7 = r4.length     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            int r7 = r7 - r5
            byte[] r7 = new byte[r7]     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            java.lang.System.arraycopy(r4, r2, r6, r2, r5)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            int r8 = r4.length     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            int r8 = r8 - r5
            java.lang.System.arraycopy(r4, r5, r7, r2, r8)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            javax.crypto.spec.SecretKeySpec r5 = new javax.crypto.spec.SecretKeySpec     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            java.lang.String r8 = "AES"
            r5.<init>(r6, r8)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            java.lang.String r6 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r6 = javax.crypto.Cipher.getInstance(r6)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            javax.crypto.spec.IvParameterSpec r8 = new javax.crypto.spec.IvParameterSpec     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            byte[] r9 = com.loc.ad.c()     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            r8.<init>(r9)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            r9 = 2
            r6.init(r9, r5, r8)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            byte[] r5 = r6.doFinal(r7)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            java.lang.String r5 = com.loc.ad.a((byte[]) r5)     // Catch:{ t -> 0x007f, IllegalBlockSizeException -> 0x00b4, Throwable -> 0x007d }
            r1 = r5
            goto L_0x00b4
        L_0x007d:
            r5 = move-exception
            goto L_0x0098
        L_0x007f:
            r5 = move-exception
            goto L_0x00a9
        L_0x0081:
            r13 = r3
            goto L_0x0088
        L_0x0083:
            r13 = move-exception
            r10 = r3
            r3 = r13
            r13 = r10
            goto L_0x0092
        L_0x0088:
            com.loc.t r3 = new com.loc.t     // Catch:{ t -> 0x00a4, IllegalBlockSizeException -> 0x00a0, Throwable -> 0x0093 }
            java.lang.String r4 = "未知的错误"
            r3.<init>(r4)     // Catch:{ t -> 0x00a4, IllegalBlockSizeException -> 0x00a0, Throwable -> 0x0093 }
            throw r3     // Catch:{ t -> 0x00a4, IllegalBlockSizeException -> 0x00a0, Throwable -> 0x0093 }
        L_0x0091:
            r3 = move-exception
        L_0x0092:
            throw r3     // Catch:{ t -> 0x00a4, IllegalBlockSizeException -> 0x00a0, Throwable -> 0x0093 }
        L_0x0093:
            r4 = move-exception
            r3 = r13
            r13 = r1
            r5 = r4
            r4 = r13
        L_0x0098:
            java.lang.String r6 = "at"
            java.lang.String r7 = "lc"
            com.loc.aq.b((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.String) r7)
            goto L_0x00b4
        L_0x00a0:
            r3 = r13
        L_0x00a1:
            r13 = r1
            r4 = r13
            goto L_0x00b4
        L_0x00a4:
            r4 = move-exception
            r3 = r13
            r13 = r1
            r5 = r4
            r4 = r13
        L_0x00a9:
            java.lang.String r6 = r5.a()
            r0.f6640a = r6
            java.lang.String r6 = "/v3/iasdkauth"
            com.loc.aq.a((com.loc.ac) r12, (java.lang.String) r6, (com.loc.t) r5)
        L_0x00b4:
            if (r4 != 0) goto L_0x00b7
            return r0
        L_0x00b7:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 == 0) goto L_0x00c1
            java.lang.String r1 = com.loc.ad.a((byte[]) r4)
        L_0x00c1:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Throwable -> 0x02f7 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r1 = "status"
            boolean r1 = r4.has(r1)     // Catch:{ Throwable -> 0x02f7 }
            if (r1 == 0) goto L_0x02ff
            java.lang.String r1 = "status"
            int r1 = r4.getInt(r1)     // Catch:{ Throwable -> 0x02f7 }
            r5 = 1
            if (r1 != r5) goto L_0x00dc
            f6639a = r5     // Catch:{ Throwable -> 0x02f7 }
            goto L_0x011d
        L_0x00dc:
            if (r1 != 0) goto L_0x011d
            java.lang.String r1 = "authcsid"
            java.lang.String r5 = "authgsid"
            if (r13 == 0) goto L_0x00e8
            java.lang.String r1 = r13.c     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r5 = r13.d     // Catch:{ Throwable -> 0x02f7 }
        L_0x00e8:
            com.loc.ad.a(r11, r1, r5, r4)     // Catch:{ Throwable -> 0x02f7 }
            f6639a = r2     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r13 = "info"
            boolean r13 = r4.has(r13)     // Catch:{ Throwable -> 0x02f7 }
            if (r13 == 0) goto L_0x00fd
            java.lang.String r13 = "info"
            java.lang.String r13 = r4.getString(r13)     // Catch:{ Throwable -> 0x02f7 }
            b = r13     // Catch:{ Throwable -> 0x02f7 }
        L_0x00fd:
            java.lang.String r13 = ""
            java.lang.String r1 = "infocode"
            boolean r1 = r4.has(r1)     // Catch:{ Throwable -> 0x02f7 }
            if (r1 == 0) goto L_0x010d
            java.lang.String r13 = "infocode"
            java.lang.String r13 = r4.getString(r13)     // Catch:{ Throwable -> 0x02f7 }
        L_0x010d:
            java.lang.String r1 = "/v3/iasdkauth"
            java.lang.String r6 = b     // Catch:{ Throwable -> 0x02f7 }
            com.loc.aq.a(r12, r1, r6, r5, r13)     // Catch:{ Throwable -> 0x02f7 }
            int r12 = f6639a     // Catch:{ Throwable -> 0x02f7 }
            if (r12 != 0) goto L_0x011d
            java.lang.String r11 = b     // Catch:{ Throwable -> 0x02f7 }
            r0.f6640a = r11     // Catch:{ Throwable -> 0x02f7 }
            return r0
        L_0x011d:
            java.lang.String r12 = "ver"
            boolean r12 = r4.has(r12)     // Catch:{ Throwable -> 0x0130 }
            if (r12 == 0) goto L_0x0138
            java.lang.String r12 = "ver"
            int r12 = r4.getInt(r12)     // Catch:{ Throwable -> 0x0130 }
            r0.b = r12     // Catch:{ Throwable -> 0x0130 }
            goto L_0x0138
        L_0x0130:
            r12 = move-exception
            java.lang.String r13 = "at"
            java.lang.String r1 = "lc"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r13, (java.lang.String) r1)     // Catch:{ Throwable -> 0x02f7 }
        L_0x0138:
            java.lang.String r12 = "result"
            boolean r12 = com.loc.ad.a((org.json.JSONObject) r4, (java.lang.String) r12)     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x02ff
            com.loc.v$a$a r12 = new com.loc.v$a$a     // Catch:{ Throwable -> 0x02f7 }
            r12.<init>()     // Catch:{ Throwable -> 0x02f7 }
            r12.f6641a = r2     // Catch:{ Throwable -> 0x02f7 }
            r12.b = r2     // Catch:{ Throwable -> 0x02f7 }
            r0.x = r12     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r13 = "result"
            org.json.JSONObject r13 = r4.getJSONObject(r13)     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r1 = ";"
            java.lang.String[] r1 = r3.split(r1)     // Catch:{ Throwable -> 0x0174 }
            if (r1 == 0) goto L_0x017c
            int r3 = r1.length     // Catch:{ Throwable -> 0x0174 }
            if (r3 <= 0) goto L_0x017c
            int r3 = r1.length     // Catch:{ Throwable -> 0x0174 }
            r4 = 0
        L_0x015e:
            if (r4 >= r3) goto L_0x017c
            r5 = r1[r4]     // Catch:{ Throwable -> 0x0174 }
            boolean r6 = r13.has(r5)     // Catch:{ Throwable -> 0x0174 }
            if (r6 == 0) goto L_0x0171
            org.json.JSONObject r6 = r0.w     // Catch:{ Throwable -> 0x0174 }
            java.lang.Object r7 = r13.get(r5)     // Catch:{ Throwable -> 0x0174 }
            r6.putOpt(r5, r7)     // Catch:{ Throwable -> 0x0174 }
        L_0x0171:
            int r4 = r4 + 1
            goto L_0x015e
        L_0x0174:
            r1 = move-exception
            java.lang.String r3 = "at"
            java.lang.String r4 = "co"
            com.loc.an.a((java.lang.Throwable) r1, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Throwable -> 0x02f7 }
        L_0x017c:
            java.lang.String r1 = "16H"
            boolean r1 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r1)     // Catch:{ Throwable -> 0x02f7 }
            if (r1 == 0) goto L_0x0197
            java.lang.String r1 = "16H"
            org.json.JSONObject r1 = r13.getJSONObject(r1)     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r3 = "able"
            java.lang.String r1 = r1.optString(r3)     // Catch:{ Throwable -> 0x02f7 }
            boolean r1 = a((java.lang.String) r1, (boolean) r2)     // Catch:{ Throwable -> 0x02f7 }
            boolean unused = r0.H = r1     // Catch:{ Throwable -> 0x02f7 }
        L_0x0197:
            java.lang.String r1 = "11K"
            boolean r1 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r1)     // Catch:{ Throwable -> 0x02f7 }
            if (r1 == 0) goto L_0x01ca
            java.lang.String r1 = "11K"
            org.json.JSONObject r1 = r13.getJSONObject(r1)     // Catch:{ Throwable -> 0x01c2 }
            java.lang.String r3 = "able"
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Throwable -> 0x01c2 }
            boolean r3 = a((java.lang.String) r3, (boolean) r2)     // Catch:{ Throwable -> 0x01c2 }
            r12.f6641a = r3     // Catch:{ Throwable -> 0x01c2 }
            java.lang.String r3 = "off"
            boolean r3 = r1.has(r3)     // Catch:{ Throwable -> 0x01c2 }
            if (r3 == 0) goto L_0x01ca
            java.lang.String r3 = "off"
            org.json.JSONObject r1 = r1.getJSONObject(r3)     // Catch:{ Throwable -> 0x01c2 }
            r12.c = r1     // Catch:{ Throwable -> 0x01c2 }
            goto L_0x01ca
        L_0x01c2:
            r12 = move-exception
            java.lang.String r1 = "AuthConfigManager"
            java.lang.String r3 = "loadException"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r1, (java.lang.String) r3)     // Catch:{ Throwable -> 0x02f7 }
        L_0x01ca:
            java.lang.String r12 = "001"
            boolean r12 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r12)     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x0216
            java.lang.String r12 = "001"
            org.json.JSONObject r12 = r13.getJSONObject(r12)     // Catch:{ Throwable -> 0x02f7 }
            com.loc.v$a$d r1 = new com.loc.v$a$d     // Catch:{ Throwable -> 0x02f7 }
            r1.<init>()     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x0214
            java.lang.String r3 = "md5"
            java.lang.String r3 = a((org.json.JSONObject) r12, (java.lang.String) r3)     // Catch:{ Throwable -> 0x020c }
            java.lang.String r4 = "url"
            java.lang.String r4 = a((org.json.JSONObject) r12, (java.lang.String) r4)     // Catch:{ Throwable -> 0x020c }
            java.lang.String r5 = "sdkversion"
            java.lang.String r12 = a((org.json.JSONObject) r12, (java.lang.String) r5)     // Catch:{ Throwable -> 0x020c }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x020c }
            if (r5 != 0) goto L_0x0214
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x020c }
            if (r5 != 0) goto L_0x0214
            boolean r5 = android.text.TextUtils.isEmpty(r12)     // Catch:{ Throwable -> 0x020c }
            if (r5 == 0) goto L_0x0205
            goto L_0x0214
        L_0x0205:
            r1.f6644a = r4     // Catch:{ Throwable -> 0x020c }
            r1.b = r3     // Catch:{ Throwable -> 0x020c }
            r1.c = r12     // Catch:{ Throwable -> 0x020c }
            goto L_0x0214
        L_0x020c:
            r12 = move-exception
            java.lang.String r3 = "at"
            java.lang.String r4 = "psu"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Throwable -> 0x02f7 }
        L_0x0214:
            r0.y = r1     // Catch:{ Throwable -> 0x02f7 }
        L_0x0216:
            java.lang.String r12 = "002"
            boolean r12 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r12)     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x022e
            java.lang.String r12 = "002"
            org.json.JSONObject r12 = r13.getJSONObject(r12)     // Catch:{ Throwable -> 0x02f7 }
            com.loc.v$a$c r1 = new com.loc.v$a$c     // Catch:{ Throwable -> 0x02f7 }
            r1.<init>()     // Catch:{ Throwable -> 0x02f7 }
            a((org.json.JSONObject) r12, (com.loc.v.a.c) r1)     // Catch:{ Throwable -> 0x02f7 }
            r0.A = r1     // Catch:{ Throwable -> 0x02f7 }
        L_0x022e:
            java.lang.String r12 = "14S"
            boolean r12 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r12)     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x0246
            java.lang.String r12 = "14S"
            org.json.JSONObject r12 = r13.getJSONObject(r12)     // Catch:{ Throwable -> 0x02f7 }
            com.loc.v$a$c r1 = new com.loc.v$a$c     // Catch:{ Throwable -> 0x02f7 }
            r1.<init>()     // Catch:{ Throwable -> 0x02f7 }
            a((org.json.JSONObject) r12, (com.loc.v.a.c) r1)     // Catch:{ Throwable -> 0x02f7 }
            r0.B = r1     // Catch:{ Throwable -> 0x02f7 }
        L_0x0246:
            a((com.loc.v.a) r0, (org.json.JSONObject) r13)     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r12 = "14Z"
            boolean r12 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r12)     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x02a4
            java.lang.String r12 = "14Z"
            org.json.JSONObject r12 = r13.getJSONObject(r12)     // Catch:{ Throwable -> 0x02f7 }
            com.loc.v$a$e r1 = new com.loc.v$a$e     // Catch:{ Throwable -> 0x02f7 }
            r1.<init>()     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r3 = "md5"
            java.lang.String r3 = a((org.json.JSONObject) r12, (java.lang.String) r3)     // Catch:{ Throwable -> 0x029a }
            java.lang.String r4 = "md5info"
            java.lang.String r4 = a((org.json.JSONObject) r12, (java.lang.String) r4)     // Catch:{ Throwable -> 0x029a }
            java.lang.String r5 = "url"
            java.lang.String r5 = a((org.json.JSONObject) r12, (java.lang.String) r5)     // Catch:{ Throwable -> 0x029a }
            java.lang.String r6 = "able"
            java.lang.String r6 = a((org.json.JSONObject) r12, (java.lang.String) r6)     // Catch:{ Throwable -> 0x029a }
            java.lang.String r7 = "on"
            java.lang.String r7 = a((org.json.JSONObject) r12, (java.lang.String) r7)     // Catch:{ Throwable -> 0x029a }
            java.lang.String r8 = "mobileable"
            java.lang.String r12 = a((org.json.JSONObject) r12, (java.lang.String) r8)     // Catch:{ Throwable -> 0x029a }
            r1.e = r3     // Catch:{ Throwable -> 0x029a }
            r1.f = r4     // Catch:{ Throwable -> 0x029a }
            r1.d = r5     // Catch:{ Throwable -> 0x029a }
            boolean r3 = a((java.lang.String) r6, (boolean) r2)     // Catch:{ Throwable -> 0x029a }
            r1.f6645a = r3     // Catch:{ Throwable -> 0x029a }
            boolean r3 = a((java.lang.String) r7, (boolean) r2)     // Catch:{ Throwable -> 0x029a }
            r1.b = r3     // Catch:{ Throwable -> 0x029a }
            boolean r12 = a((java.lang.String) r12, (boolean) r2)     // Catch:{ Throwable -> 0x029a }
            r1.c = r12     // Catch:{ Throwable -> 0x029a }
            goto L_0x02a2
        L_0x029a:
            r12 = move-exception
            java.lang.String r3 = "at"
            java.lang.String r4 = "pes"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Throwable -> 0x02f7 }
        L_0x02a2:
            r0.G = r1     // Catch:{ Throwable -> 0x02f7 }
        L_0x02a4:
            java.lang.String r12 = "151"
            boolean r12 = com.loc.ad.a((org.json.JSONObject) r13, (java.lang.String) r12)     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x02c7
            java.lang.String r12 = "151"
            org.json.JSONObject r12 = r13.getJSONObject(r12)     // Catch:{ Throwable -> 0x02f7 }
            com.loc.v$a$f r1 = new com.loc.v$a$f     // Catch:{ Throwable -> 0x02f7 }
            r1.<init>()     // Catch:{ Throwable -> 0x02f7 }
            if (r12 == 0) goto L_0x02c5
            java.lang.String r3 = "able"
            java.lang.String r12 = r12.optString(r3)     // Catch:{ Throwable -> 0x02f7 }
            boolean r12 = a((java.lang.String) r12, (boolean) r2)     // Catch:{ Throwable -> 0x02f7 }
            r1.f6646a = r12     // Catch:{ Throwable -> 0x02f7 }
        L_0x02c5:
            r0.z = r1     // Catch:{ Throwable -> 0x02f7 }
        L_0x02c7:
            a((com.loc.v.a) r0, (org.json.JSONObject) r13)     // Catch:{ Throwable -> 0x02f7 }
            java.lang.String r12 = "15K"
            org.json.JSONObject r12 = r13.getJSONObject(r12)     // Catch:{ Throwable -> 0x02f2 }
            java.lang.String r13 = "isTargetAble"
            java.lang.String r13 = r12.optString(r13)     // Catch:{ Throwable -> 0x02f2 }
            boolean r13 = a((java.lang.String) r13, (boolean) r2)     // Catch:{ Throwable -> 0x02f2 }
            java.lang.String r1 = "able"
            java.lang.String r12 = r12.optString(r1)     // Catch:{ Throwable -> 0x02f2 }
            boolean r12 = a((java.lang.String) r12, (boolean) r2)     // Catch:{ Throwable -> 0x02f2 }
            if (r12 != 0) goto L_0x02ec
            com.loc.z r12 = com.loc.z.a.f6652a     // Catch:{ Throwable -> 0x02f2 }
            r12.b((android.content.Context) r11)     // Catch:{ Throwable -> 0x02f2 }
            goto L_0x02ff
        L_0x02ec:
            com.loc.z r12 = com.loc.z.a.f6652a     // Catch:{ Throwable -> 0x02f2 }
            r12.a(r11, r13)     // Catch:{ Throwable -> 0x02f2 }
            goto L_0x02ff
        L_0x02f2:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ Throwable -> 0x02f7 }
            goto L_0x02ff
        L_0x02f7:
            r11 = move-exception
            java.lang.String r12 = "at"
            java.lang.String r13 = "lc"
            com.loc.an.a((java.lang.Throwable) r11, (java.lang.String) r12, (java.lang.String) r13)
        L_0x02ff:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.v.a(android.content.Context, com.loc.ac, java.lang.String):com.loc.v$a");
    }

    private static String a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject != null && jSONObject.has(str) && !jSONObject.getString(str).equals(XMPConst.ai)) ? jSONObject.optString(str) : "";
    }

    public static void a(Context context, String str) {
        u.a(context, str);
    }

    private static void a(a aVar, JSONObject jSONObject) {
        try {
            if (ad.a(jSONObject, "11B")) {
                aVar.h = jSONObject.getJSONObject("11B");
            }
            if (ad.a(jSONObject, "11C")) {
                aVar.k = jSONObject.getJSONObject("11C");
            }
            if (ad.a(jSONObject, "11I")) {
                aVar.l = jSONObject.getJSONObject("11I");
            }
            if (ad.a(jSONObject, "11H")) {
                aVar.m = jSONObject.getJSONObject("11H");
            }
            if (ad.a(jSONObject, "11E")) {
                aVar.n = jSONObject.getJSONObject("11E");
            }
            if (ad.a(jSONObject, "11F")) {
                aVar.o = jSONObject.getJSONObject("11F");
            }
            if (ad.a(jSONObject, "13A")) {
                aVar.q = jSONObject.getJSONObject("13A");
            }
            if (ad.a(jSONObject, "13J")) {
                aVar.i = jSONObject.getJSONObject("13J");
            }
            if (ad.a(jSONObject, "11G")) {
                aVar.p = jSONObject.getJSONObject("11G");
            }
            if (ad.a(jSONObject, "006")) {
                aVar.r = jSONObject.getJSONObject("006");
            }
            if (ad.a(jSONObject, "010")) {
                aVar.s = jSONObject.getJSONObject("010");
            }
            if (ad.a(jSONObject, "11Z")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("11Z");
                a.b bVar = new a.b();
                a(jSONObject2, bVar);
                aVar.C = bVar;
            }
            if (ad.a(jSONObject, "135")) {
                aVar.j = jSONObject.getJSONObject("135");
            }
            if (ad.a(jSONObject, "13S")) {
                aVar.g = jSONObject.getJSONObject("13S");
            }
            if (ad.a(jSONObject, "121")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("121");
                a.b bVar2 = new a.b();
                a(jSONObject3, bVar2);
                aVar.D = bVar2;
            }
            if (ad.a(jSONObject, "122")) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("122");
                a.b bVar3 = new a.b();
                a(jSONObject4, bVar3);
                aVar.E = bVar3;
            }
            if (ad.a(jSONObject, "123")) {
                JSONObject jSONObject5 = jSONObject.getJSONObject("123");
                a.b bVar4 = new a.b();
                a(jSONObject5, bVar4);
                aVar.F = bVar4;
            }
            if (ad.a(jSONObject, "011")) {
                aVar.c = jSONObject.getJSONObject("011");
            }
            if (ad.a(jSONObject, "012")) {
                aVar.d = jSONObject.getJSONObject("012");
            }
            if (ad.a(jSONObject, "013")) {
                aVar.e = jSONObject.getJSONObject("013");
            }
            if (ad.a(jSONObject, "014")) {
                aVar.f = jSONObject.getJSONObject("014");
            }
            if (ad.a(jSONObject, "145")) {
                aVar.t = jSONObject.getJSONObject("145");
            }
            if (ad.a(jSONObject, "14B")) {
                aVar.u = jSONObject.getJSONObject("14B");
            }
            if (ad.a(jSONObject, "14D")) {
                aVar.v = jSONObject.getJSONObject("14D");
            }
        } catch (Throwable th) {
            aq.b(th, "at", "pe");
        }
    }

    private static void a(JSONObject jSONObject, a.b bVar) {
        try {
            String a2 = a(jSONObject, "m");
            String a3 = a(jSONObject, "u");
            String a4 = a(jSONObject, "v");
            String a5 = a(jSONObject, "able");
            String a6 = a(jSONObject, "on");
            bVar.c = a2;
            bVar.b = a3;
            bVar.d = a4;
            bVar.f6642a = a(a5, false);
            bVar.e = a(a6, true);
        } catch (Throwable th) {
            an.a(th, "at", "pe");
        }
    }

    private static void a(JSONObject jSONObject, a.c cVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                cVar.b = a2;
                cVar.f6643a = a3;
            } catch (Throwable th) {
                an.a(th, "at", "psc");
            }
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] split = URLDecoder.decode(str).split("/");
            return split[split.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z;
        }
    }
}
