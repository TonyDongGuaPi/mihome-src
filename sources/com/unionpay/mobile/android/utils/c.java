package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.coloros.mcssdk.c.a;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import miuipub.security.DigestUtils;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static String f9740a = "[{\"type\":\"app\",\"sort\":100,\"package_info\":[{\"schema\":\"com.unionpay.uppay\",\"version\":\".*\",\"sign\":\"23137B5BE6AEF6682B41E6536F08367E0949A1CC\",\"sort\":101}],\"need_install\":true,\"install_msg\":\"�Ƿ����ذ�װ��������֧������\",\"url\":\"https://mobile.unionpay.com/getclient?platform=android&type=securepayplugin\",\"download_app\":\"UPPayPluginEx.apk\",\"download_title\":\"��������֧������\",\"download_desp\":\"��������֧������\",\"md5\":\"D75BB2802E61738A9A03BF014F927D9A\"},{\"type\":\"jar\",\"sort\":200}]";
    private static SimpleDateFormat b = new SimpleDateFormat("yyyyMMddhhmmss");
    private static HashMap<String, String> c = new d();
    private static long[] d = new long[256];

    static {
        for (int i = 0; i < 256; i++) {
            long j = (long) i;
            for (int i2 = 0; i2 < 8; i2++) {
                j = (j >> 1) ^ ((((int) j) & 1) != 0 ? -7661587058870466123L : 0);
            }
            d[i] = j;
        }
    }

    public static String a() {
        return b.format(new Date(System.currentTimeMillis()));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|(11:6|7|8|9|12|13|(1:15)|16|(1:18)|19|(1:21))|22|23|24|(3:26|(2:28|(3:37|30|31)(1:39))(1:38)|32)|36|33) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c5, code lost:
        return "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x008e */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r9) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "configs"
            java.lang.String r1 = com.unionpay.mobile.android.utils.PreferenceUtils.a((android.content.Context) r9, (java.lang.String) r1)
            java.lang.String r2 = "mode"
            java.lang.String r2 = com.unionpay.mobile.android.utils.PreferenceUtils.a((android.content.Context) r9, (java.lang.String) r2)
            java.lang.String r3 = "or"
            java.lang.String r9 = com.unionpay.mobile.android.utils.PreferenceUtils.a((android.content.Context) r9, (java.lang.String) r3)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            r4 = 0
            r5 = 2
            if (r3 != 0) goto L_0x008e
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x008e
            boolean r3 = android.text.TextUtils.isEmpty(r9)
            if (r3 != 0) goto L_0x008e
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x008e }
            r3.<init>(r1)     // Catch:{ Exception -> 0x008e }
            java.lang.String r1 = "sign"
            java.lang.String r1 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r3, (java.lang.String) r1)     // Catch:{ Exception -> 0x008e }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0038 }
            goto L_0x0039
        L_0x0038:
            r2 = 0
        L_0x0039:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x008e }
            java.lang.String r7 = "configs"
            java.lang.String r7 = r3.getString(r7)     // Catch:{ Exception -> 0x008e }
            byte[] r7 = android.util.Base64.decode(r7, r5)     // Catch:{ Exception -> 0x008e }
            r6.<init>(r7)     // Catch:{ Exception -> 0x008e }
            java.lang.String r7 = ""
            java.lang.String r8 = "sePayConf"
            boolean r8 = r3.has(r8)     // Catch:{ Exception -> 0x008e }
            if (r8 == 0) goto L_0x0061
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x008e }
            java.lang.String r8 = "sePayConf"
            java.lang.String r3 = r3.getString(r8)     // Catch:{ Exception -> 0x008e }
            byte[] r3 = android.util.Base64.decode(r3, r5)     // Catch:{ Exception -> 0x008e }
            r7.<init>(r3)     // Catch:{ Exception -> 0x008e }
        L_0x0061:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x008e }
            if (r3 == 0) goto L_0x0069
            java.lang.String r7 = ""
        L_0x0069:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008e }
            r3.<init>()     // Catch:{ Exception -> 0x008e }
            r3.append(r6)     // Catch:{ Exception -> 0x008e }
            r3.append(r7)     // Catch:{ Exception -> 0x008e }
            r3.append(r9)     // Catch:{ Exception -> 0x008e }
            java.lang.String r9 = r3.toString()     // Catch:{ Exception -> 0x008e }
            java.lang.String r9 = f(r9)     // Catch:{ Exception -> 0x008e }
            java.lang.String r9 = b((java.lang.String) r9)     // Catch:{ Exception -> 0x008e }
            java.lang.String r1 = com.unionpay.mobile.android.utils.PreferenceUtils.forConfig(r2, r1)     // Catch:{ Exception -> 0x008e }
            boolean r9 = r1.equals(r9)     // Catch:{ Exception -> 0x008e }
            if (r9 == 0) goto L_0x008e
            r0 = r6
        L_0x008e:
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00c5 }
            r9.<init>(r0)     // Catch:{ JSONException -> 0x00c5 }
            int r0 = r9.length()
        L_0x0097:
            if (r4 >= r0) goto L_0x00c2
            java.lang.Object r1 = com.unionpay.mobile.android.utils.j.b((org.json.JSONArray) r9, (int) r4)
            if (r1 == 0) goto L_0x00bf
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            java.lang.String r2 = "type"
            java.lang.String r2 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r1, (java.lang.String) r2)
            java.lang.String r3 = "app"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x00bf
            java.lang.String r9 = "ca"
            java.lang.String r9 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r1, (java.lang.String) r9)
            java.lang.String r0 = new java.lang.String
            byte[] r9 = android.util.Base64.decode(r9, r5)
            r0.<init>(r9)
            return r0
        L_0x00bf:
            int r4 = r4 + 1
            goto L_0x0097
        L_0x00c2:
            java.lang.String r9 = ""
            return r9
        L_0x00c5:
            java.lang.String r9 = ""
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.utils.c.a(android.content.Context):java.lang.String");
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(Operators.CONDITION_IF_MIDDLE);
            }
        }
        return sb.toString();
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            return false;
        }
    }

    public static boolean a(String str) {
        return str.matches("[0-9A-Fa-f]+");
    }

    public static String b(Context context) {
        String str = c.get(f.b(context));
        return !TextUtils.isEmpty(str) ? str : Tags.LuckyShake.VALUE_SUCCESS_CODE;
    }

    public static String b(Context context, String str) {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        String str2;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e3) {
            e3.printStackTrace();
            x509Certificate = null;
        }
        try {
            str2 = a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            str2 = null;
            return str2.replaceAll(":", "");
        } catch (CertificateEncodingException e5) {
            e5.printStackTrace();
            str2 = null;
            return str2.replaceAll(":", "");
        }
        return str2.replaceAll(":", "");
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        char[] charArray = a.f.toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (byte b2 : str.getBytes()) {
            sb.append(charArray[(b2 & 240) >> 4]);
            sb.append(charArray[b2 & 15]);
        }
        return sb.toString().trim();
    }

    public static String c(String str) {
        try {
            return new BigDecimal(str).divide(new BigDecimal("100")).toString();
        } catch (Exception unused) {
            return "1";
        }
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() / 3; i++) {
            sb.append("cmd");
        }
        for (int i2 = 0; i2 < str.length() % 3; i2++) {
            sb.append("cmd".charAt(i2));
        }
        byte[] bytes = str.getBytes();
        byte[] bytes2 = sb.toString().getBytes();
        byte[] bArr = new byte[str.length()];
        for (int i3 = 0; i3 < bytes.length; i3++) {
            bArr[i3] = (byte) (bytes[i3] ^ bytes2[i3]);
        }
        return b.a(bArr);
    }

    public static String e(String str) {
        long j;
        if (str == null || str.length() == 0) {
            j = 0;
        } else {
            byte[] bArr = new byte[(str.length() * 2)];
            int i = 0;
            for (char c2 : str.toCharArray()) {
                int i2 = i + 1;
                bArr[i] = (byte) (c2 & 255);
                i = i2 + 1;
                bArr[i2] = (byte) (c2 >> 8);
            }
            j = -1;
            for (byte b2 : bArr) {
                j = (j >> 8) ^ d[(b2 ^ ((int) j)) & 255];
            }
        }
        return Long.toHexString(j);
    }

    private static String f(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance(DigestUtils.b);
            instance.reset();
            instance.update(bytes);
            return b.a(instance.digest());
        } catch (Exception unused) {
            return null;
        }
    }
}
