package com.hianalytics.android.v1;

import android.content.Context;
import com.hianalytics.android.b.a.a;
import com.hianalytics.android.b.a.b;
import com.miuipub.internal.hybrid.SignUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.json.JSONObject;

public final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    boolean f5800a;
    private Context b;
    private JSONObject c;

    public c(Context context, JSONObject jSONObject, boolean z) {
        this.b = context;
        this.c = jSONObject;
        this.f5800a = z;
    }

    private String a(byte[] bArr) {
        String format = String.format("%016d", new Object[]{Long.valueOf(Math.abs(new SecureRandom().nextLong() % 10000000000000000L))});
        try {
            byte[] a2 = b.a(format, bArr);
            byte[] bytes = format.getBytes("UTF-8");
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new RSAPublicKeySpec(new BigInteger("24907259431961377209480304447420314675278854956424737688244507998454379688588314890162679979323703303509240796245532111474023047392580178709435281576624542294613207523485034492914828565153172773053351891188090398210811384185501117117991603774176386409127476628856566065613009756131651597266262540467980974946876675842468600552312158771248419700603327630677244315755445967726919102965015263135288381740211593751262078285738436597133664401598420056690274760726854877181978220226448211936820860496708860964018593025172845041095854180953040116559241637133730839837036910305932797451786785855051024967644159284784940216337"), new BigInteger("65537")));
            if (rSAPublicKey != null) {
                Cipher instance = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
                instance.init(1, rSAPublicKey);
                byte[] doFinal = instance.doFinal(bytes);
                String e = a.e(this.b);
                return "{\"vs\":\"" + e + "\",\"ed\":\"" + a.b(a2) + "\",\"ek\":\"" + a.b(doFinal) + "\"}";
            }
            throw new UnsupportedEncodingException();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private boolean a(JSONObject jSONObject, String str) {
        StringBuilder sb;
        String a2;
        String lowerCase = str.toLowerCase();
        try {
            byte[] a3 = a.a(jSONObject.toString().getBytes("UTF-8"));
            if (a3 == null || (a2 = a(a3)) == null) {
                return false;
            }
            try {
                byte[] bytes = a2.getBytes("UTF-8");
                if (lowerCase.indexOf("https") >= 0) {
                    return false;
                }
                a.h();
                return b.a(str, bytes);
            } catch (UnsupportedEncodingException e) {
                e = e;
                sb = new StringBuilder("UnsupportedEncodingException:");
                sb.append(e.getMessage());
                a.h();
                return false;
            }
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            sb = new StringBuilder("UnsupportedEncodingException:");
            sb.append(e.getMessage());
            a.h();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0127, code lost:
        if ((((java.lang.System.currentTimeMillis() / 1000) - com.hianalytics.android.b.a.a.a(r12[1])) - java.lang.Long.parseLong(r12[2])) < com.hianalytics.android.b.a.a.b().longValue()) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x012d, code lost:
        com.hianalytics.android.b.a.a.h();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0170, code lost:
        if (((java.lang.System.currentTimeMillis() / 1000) - com.hianalytics.android.b.a.a.a(r12.getString(r12.length() - 1).split(",")[2])) < com.hianalytics.android.b.a.a.b().longValue()) goto L_0x0129;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0067 A[Catch:{ JSONException -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007d A[Catch:{ JSONException -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0081 A[Catch:{ JSONException -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d8 A[SYNTHETIC, Splitter:B:47:0x00d8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r19 = this;
            r1 = r19
            org.json.JSONObject r0 = r1.c     // Catch:{ Exception -> 0x0181 }
            java.lang.String r2 = "type"
            java.lang.String r0 = r0.getString(r2)     // Catch:{ Exception -> 0x0181 }
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            android.content.Context r2 = r1.b     // Catch:{ Exception -> 0x0181 }
            org.json.JSONObject r0 = r1.c     // Catch:{ Exception -> 0x0181 }
            boolean r3 = r1.f5800a     // Catch:{ Exception -> 0x0181 }
            java.lang.String r4 = com.hianalytics.android.a.a.a.a(r2)     // Catch:{ Exception -> 0x0181 }
            if (r4 != 0) goto L_0x001e
            com.hianalytics.android.b.a.a.h()     // Catch:{ Exception -> 0x0181 }
            return
        L_0x001e:
            java.lang.String r5 = "cached"
            org.json.JSONObject r5 = com.hianalytics.android.b.a.c.b(r2, r5)     // Catch:{ Exception -> 0x0181 }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Exception -> 0x0181 }
            r6.<init>()     // Catch:{ Exception -> 0x0181 }
            java.lang.String r7 = "type"
            java.lang.String r7 = r0.getString(r7)     // Catch:{ JSONException -> 0x0177 }
            if (r7 != 0) goto L_0x0033
            return
        L_0x0033:
            java.lang.String r8 = "type"
            r0.remove(r8)     // Catch:{ JSONException -> 0x0177 }
            r8 = 0
            r9 = 1
            if (r5 == 0) goto L_0x0052
            boolean r10 = r5.isNull(r7)     // Catch:{ JSONException -> 0x0177 }
            if (r10 == 0) goto L_0x004b
            org.json.JSONArray r10 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0177 }
            r10.<init>()     // Catch:{ JSONException -> 0x0177 }
        L_0x0048:
            r11 = r5
            r5 = 1
            goto L_0x005d
        L_0x004b:
            org.json.JSONArray r10 = r5.getJSONArray(r7)     // Catch:{ JSONException -> 0x0177 }
            r11 = r5
            r5 = 0
            goto L_0x005d
        L_0x0052:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0177 }
            r5.<init>()     // Catch:{ JSONException -> 0x0177 }
            org.json.JSONArray r10 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0177 }
            r10.<init>()     // Catch:{ JSONException -> 0x0177 }
            goto L_0x0048
        L_0x005d:
            if (r3 == 0) goto L_0x0065
            if (r5 == 0) goto L_0x0065
            com.hianalytics.android.b.a.a.h()     // Catch:{ JSONException -> 0x0177 }
            return
        L_0x0065:
            if (r3 != 0) goto L_0x006a
            r10.put(r0)     // Catch:{ JSONException -> 0x0177 }
        L_0x006a:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0177 }
            r0.<init>()     // Catch:{ JSONException -> 0x0177 }
            int r3 = r10.length()     // Catch:{ JSONException -> 0x0177 }
        L_0x0073:
            int r5 = r3 + -1
            if (r8 <= r5) goto L_0x00d8
            int r3 = r0.length()     // Catch:{ JSONException -> 0x0177 }
            if (r3 > 0) goto L_0x0081
            com.hianalytics.android.b.a.a.h()     // Catch:{ JSONException -> 0x0177 }
            return
        L_0x0081:
            r11.remove(r7)     // Catch:{ JSONException -> 0x0177 }
            r11.put(r7, r0)     // Catch:{ JSONException -> 0x0177 }
            java.lang.String r3 = "g"
            r6.put(r3, r4)     // Catch:{ JSONException -> 0x0177 }
            java.lang.String r3 = "s"
            r6.put(r3, r0)     // Catch:{ JSONException -> 0x0177 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0181 }
            java.lang.String r3 = "message="
            r0.<init>(r3)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x0181 }
            r0.append(r3)     // Catch:{ Exception -> 0x0181 }
            com.hianalytics.android.b.a.a.h()     // Catch:{ Exception -> 0x0181 }
            java.lang.String r0 = com.hianalytics.android.b.a.a.i()     // Catch:{ Exception -> 0x0181 }
            boolean r0 = r1.a(r6, r0)     // Catch:{ Exception -> 0x0181 }
            if (r0 == 0) goto L_0x00cf
            java.lang.String r0 = "flag"
            android.content.SharedPreferences r0 = com.hianalytics.android.b.a.c.a(r2, r0)     // Catch:{ Exception -> 0x0181 }
            boolean r3 = com.hianalytics.android.b.a.a.f(r2)     // Catch:{ Exception -> 0x0181 }
            if (r3 == 0) goto L_0x00c6
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x0181 }
            java.lang.String r3 = "rom_version"
            java.lang.String r4 = android.os.Build.DISPLAY     // Catch:{ Exception -> 0x0181 }
            r0.putString(r3, r4)     // Catch:{ Exception -> 0x0181 }
            r0.commit()     // Catch:{ Exception -> 0x0181 }
        L_0x00c6:
            java.lang.String r0 = "cached"
            com.hianalytics.android.b.a.c.c(r2, r0)     // Catch:{ Exception -> 0x0181 }
            com.hianalytics.android.b.a.a.h()     // Catch:{ Exception -> 0x0181 }
            return
        L_0x00cf:
            java.lang.String r0 = "cached"
            com.hianalytics.android.b.a.c.a(r2, r11, r0)     // Catch:{ Exception -> 0x0181 }
            com.hianalytics.android.b.a.a.h()     // Catch:{ Exception -> 0x0181 }
            return
        L_0x00d8:
            org.json.JSONObject r5 = r10.getJSONObject(r8)     // Catch:{ JSONException -> 0x0177 }
            java.lang.String r12 = "b"
            boolean r12 = r5.has(r12)     // Catch:{ JSONException -> 0x0177 }
            r14 = 1000(0x3e8, double:4.94E-321)
            if (r12 == 0) goto L_0x0131
            java.lang.String r12 = "b"
            org.json.JSONArray r12 = r5.getJSONArray(r12)     // Catch:{ JSONException -> 0x0177 }
            if (r12 == 0) goto L_0x0173
            int r16 = r12.length()     // Catch:{ JSONException -> 0x0177 }
            if (r16 <= 0) goto L_0x0173
            int r16 = r12.length()     // Catch:{ JSONException -> 0x0177 }
            int r13 = r16 + -1
            java.lang.String r12 = r12.getString(r13)     // Catch:{ JSONException -> 0x0177 }
            java.lang.String r13 = ","
            java.lang.String[] r12 = r12.split(r13)     // Catch:{ JSONException -> 0x0177 }
            long r17 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0177 }
            long r17 = r17 / r14
            r13 = r12[r9]     // Catch:{ JSONException -> 0x0177 }
            long r13 = com.hianalytics.android.b.a.a.a((java.lang.String) r13)     // Catch:{ JSONException -> 0x0177 }
            r15 = 0
            long r17 = r17 - r13
            r13 = 2
            r12 = r12[r13]     // Catch:{ JSONException -> 0x0177 }
            long r12 = java.lang.Long.parseLong(r12)     // Catch:{ JSONException -> 0x0177 }
            r14 = 0
            long r17 = r17 - r12
            java.lang.Long r12 = com.hianalytics.android.b.a.a.b()     // Catch:{ JSONException -> 0x0177 }
            long r12 = r12.longValue()     // Catch:{ JSONException -> 0x0177 }
            int r14 = (r17 > r12 ? 1 : (r17 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x012d
        L_0x0129:
            r0.put(r5)     // Catch:{ JSONException -> 0x0177 }
            goto L_0x0173
        L_0x012d:
            com.hianalytics.android.b.a.a.h()     // Catch:{ JSONException -> 0x0177 }
            goto L_0x0173
        L_0x0131:
            java.lang.String r12 = "e"
            boolean r12 = r5.has(r12)     // Catch:{ JSONException -> 0x0177 }
            if (r12 == 0) goto L_0x0173
            java.lang.String r12 = "e"
            org.json.JSONArray r12 = r5.getJSONArray(r12)     // Catch:{ JSONException -> 0x0177 }
            if (r12 == 0) goto L_0x0173
            int r13 = r12.length()     // Catch:{ JSONException -> 0x0177 }
            if (r13 <= 0) goto L_0x0173
            int r13 = r12.length()     // Catch:{ JSONException -> 0x0177 }
            int r13 = r13 - r9
            java.lang.String r12 = r12.getString(r13)     // Catch:{ JSONException -> 0x0177 }
            java.lang.String r13 = ","
            java.lang.String[] r12 = r12.split(r13)     // Catch:{ JSONException -> 0x0177 }
            long r17 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0177 }
            long r17 = r17 / r14
            r13 = 2
            r12 = r12[r13]     // Catch:{ JSONException -> 0x0177 }
            long r12 = com.hianalytics.android.b.a.a.a((java.lang.String) r12)     // Catch:{ JSONException -> 0x0177 }
            r14 = 0
            long r17 = r17 - r12
            java.lang.Long r12 = com.hianalytics.android.b.a.a.b()     // Catch:{ JSONException -> 0x0177 }
            long r12 = r12.longValue()     // Catch:{ JSONException -> 0x0177 }
            int r14 = (r17 > r12 ? 1 : (r17 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x012d
            goto L_0x0129
        L_0x0173:
            int r8 = r8 + 1
            goto L_0x0073
        L_0x0177:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x0181 }
            java.lang.String r0 = "cached"
            com.hianalytics.android.b.a.c.c(r2, r0)     // Catch:{ Exception -> 0x0181 }
            return
        L_0x0181:
            r0 = move-exception
            r0.printStackTrace()
            android.content.Context r0 = r1.b
            java.lang.String r2 = "cached"
            com.hianalytics.android.b.a.c.c(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hianalytics.android.v1.c.run():void");
    }
}
