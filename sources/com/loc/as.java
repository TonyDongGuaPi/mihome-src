package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class as {

    /* renamed from: a  reason: collision with root package name */
    public static byte[] f6488a = "FDF1F436161AEF5B".getBytes();
    public static byte[] b = "0102030405060708".getBytes();
    public static String c = "SOCRASH";
    private static HashSet<String> d = new HashSet<>();
    private static final String f = "SOCRASH";
    private File[] e;

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f6489a;
        private String b;
        private String c;
        private String d;
        private String e;

        public a() {
        }

        private a(String str, String str2, String str3, String str4, String str5) {
            this.f6489a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public static List<a> a(String str) {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList();
            }
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(b(jSONArray.getString(i)));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return arrayList;
        }

        private static a b(String str) {
            if (TextUtils.isEmpty(str)) {
                return new a();
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new a(jSONObject.optString("mk", ""), jSONObject.optString("ak", ""), jSONObject.optString("bk", ""), jSONObject.optString("ik", ""), jSONObject.optString("nk", ""));
            } catch (Throwable unused) {
                return new a();
            }
        }

        public final String a() {
            return this.f6489a;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }

        public final String d() {
            return this.d;
        }

        public final String e() {
            return this.e;
        }
    }

    private static class b {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f6490a;
        /* access modifiers changed from: private */
        public String b;
    }

    private static void a(Context context, a aVar) throws JSONException {
        if (!TextUtils.isEmpty(aVar.b()) && !TextUtils.isEmpty(aVar.c()) && !TextUtils.isEmpty(aVar.d())) {
            int i = 0;
            SharedPreferences sharedPreferences = context.getSharedPreferences(aa.b("SO_DYNAMIC_FILE_KEY"), 0);
            JSONArray jSONArray = new JSONArray(ad.a(ae.b(ad.e(sharedPreferences.getString("SO_ERROR_KEY", "")))));
            while (i < jSONArray.length()) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.opt("mk").equals(aVar.a()) || !jSONObject.opt("ak").equals(aVar.b()) || !jSONObject.opt("bk").equals(aVar.c()) || !jSONObject.opt("ik").equals(aVar.d()) || !jSONObject.opt("nk").equals(aVar.e())) {
                    i++;
                } else {
                    return;
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("mk", aVar.a());
            jSONObject2.putOpt("ak", aVar.b());
            jSONObject2.putOpt("bk", aVar.c());
            jSONObject2.putOpt("ik", aVar.d());
            jSONObject2.putOpt("nk", aVar.e());
            jSONArray.put(jSONObject2);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("SO_ERROR_KEY", ad.g(ae.a(ad.a(jSONArray.toString()))));
            edit.commit();
        }
    }

    private static void a(Context context, byte[] bArr) {
        if (context != null) {
            try {
                String str = new String(bArr, "ISO-8859-1");
                if (str.indexOf("{\"") > 0 && str.indexOf("\"}") > 0) {
                    JSONObject jSONObject = new JSONObject(str.substring(str.indexOf("{\""), str.lastIndexOf("\"}") + 2));
                    String optString = jSONObject.optString("ik");
                    String optString2 = jSONObject.optString("jk");
                    if (!TextUtils.isEmpty(optString2)) {
                        List<a> a2 = a.a(optString);
                        for (int i = 0; i < a2.size(); i++) {
                            a aVar = a2.get(i);
                            if (optString2.contains(aVar.e())) {
                                a(context, aVar);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static boolean a(List<b> list, String str) {
        for (b next : list) {
            if (next.b.equals(str)) {
                int unused = next.f6490a = next.f6490a + 1;
                return true;
            }
        }
        return false;
    }

    private static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr.length != 16) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static byte[] a(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            byte[] a2 = y.a("a1f5886b7153004c5c99559f5261676f".getBytes(), bArr, "nFy1THrhajaZzz8U".getBytes());
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[(a2.length - 16)];
            System.arraycopy(a2, 0, bArr2, 0, 16);
            System.arraycopy(a2, 16, bArr3, 0, a2.length - 16);
            return !a(aa.a(bArr3, "MD5"), bArr2) ? new byte[0] : bArr3;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0032 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e A[Catch:{ Throwable -> 0x008e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = com.loc.ao.a(r9)     // Catch:{ Throwable -> 0x008e }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x008e }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x008e }
            boolean r0 = r1.isDirectory()     // Catch:{ Throwable -> 0x008e }
            r2 = 0
            if (r0 != 0) goto L_0x0012
            r0 = r2
            goto L_0x0016
        L_0x0012:
            java.io.File[] r0 = r1.listFiles()     // Catch:{ Throwable -> 0x008e }
        L_0x0016:
            if (r0 != 0) goto L_0x0019
            return
        L_0x0019:
            r8.e = r0     // Catch:{ Throwable -> 0x008e }
            r1 = 0
            com.loc.ac$a r3 = new com.loc.ac$a     // Catch:{ Throwable -> 0x0032 }
            java.lang.String r4 = f     // Catch:{ Throwable -> 0x0032 }
            java.lang.String r5 = "1.0"
            java.lang.String r6 = ""
            r3.<init>(r4, r5, r6)     // Catch:{ Throwable -> 0x0032 }
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ Throwable -> 0x0032 }
            com.loc.ac$a r3 = r3.a((java.lang.String[]) r4)     // Catch:{ Throwable -> 0x0032 }
            com.loc.ac r3 = r3.a()     // Catch:{ Throwable -> 0x0032 }
            r2 = r3
        L_0x0032:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x008e }
            r3.<init>()     // Catch:{ Throwable -> 0x008e }
        L_0x0037:
            int r4 = r0.length     // Catch:{ Throwable -> 0x008e }
            if (r1 >= r4) goto L_0x008e
            r4 = 10
            if (r1 >= r4) goto L_0x008e
            r4 = r0[r1]     // Catch:{ Throwable -> 0x008e }
            if (r4 == 0) goto L_0x008b
            boolean r5 = r4.exists()     // Catch:{ Throwable -> 0x008e }
            if (r5 == 0) goto L_0x008b
            boolean r5 = r4.isFile()     // Catch:{ Throwable -> 0x008e }
            if (r5 == 0) goto L_0x008b
            byte[] r5 = a((java.io.File) r4)     // Catch:{ Throwable -> 0x008e }
            if (r5 == 0) goto L_0x0088
            int r6 = r5.length     // Catch:{ Throwable -> 0x008e }
            if (r6 == 0) goto L_0x0088
            int r6 = r5.length     // Catch:{ Throwable -> 0x008e }
            r7 = 100000(0x186a0, float:1.4013E-40)
            if (r6 <= r7) goto L_0x005e
            goto L_0x0088
        L_0x005e:
            java.lang.String r6 = com.loc.aa.a((byte[]) r5)     // Catch:{ Throwable -> 0x008e }
            boolean r7 = a((java.util.List<com.loc.as.b>) r3, (java.lang.String) r6)     // Catch:{ Throwable -> 0x008e }
            if (r7 != 0) goto L_0x0088
            java.util.HashSet<java.lang.String> r7 = d     // Catch:{ Throwable -> 0x008e }
            boolean r7 = r7.contains(r6)     // Catch:{ Throwable -> 0x008e }
            if (r7 == 0) goto L_0x0071
            goto L_0x0088
        L_0x0071:
            a((android.content.Context) r9, (byte[]) r5)     // Catch:{ Throwable -> 0x008e }
            java.util.HashSet<java.lang.String> r7 = d     // Catch:{ Throwable -> 0x008e }
            r7.add(r6)     // Catch:{ Throwable -> 0x008e }
            java.lang.String r5 = com.loc.y.b((byte[]) r5)     // Catch:{ Throwable -> 0x008e }
            java.lang.String r6 = f     // Catch:{ Throwable -> 0x008e }
            com.loc.ar.a(r2, r9, r6, r5)     // Catch:{ Throwable -> 0x008e }
            if (r4 == 0) goto L_0x008b
            r4.delete()     // Catch:{ Exception -> 0x008b }
            goto L_0x008b
        L_0x0088:
            r4.delete()     // Catch:{ Throwable -> 0x008e }
        L_0x008b:
            int r1 = r1 + 1
            goto L_0x0037
        L_0x008e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.as.a(android.content.Context):void");
    }
}
