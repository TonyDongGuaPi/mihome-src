package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.coloros.mcssdk.c.a;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.regex.Pattern;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleDateFormat f9844a = new SimpleDateFormat("yyyyMMddhhmmss");
    private static HashMap b = new c();

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|(11:6|7|8|9|12|13|(1:15)|16|(1:18)|19|(1:23))|24|25|26|(3:28|(2:30|(3:38|32|33)(1:41))(1:40)|34)|39|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00cb, code lost:
        return "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0094 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r9) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "configs"
            java.lang.String r1 = com.unionpay.utils.UPUtils.a((android.content.Context) r9, (java.lang.String) r1)
            java.lang.String r2 = "mode"
            java.lang.String r2 = com.unionpay.utils.UPUtils.a((android.content.Context) r9, (java.lang.String) r2)
            java.lang.String r3 = "or"
            java.lang.String r9 = com.unionpay.utils.UPUtils.a((android.content.Context) r9, (java.lang.String) r3)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            r4 = 0
            r5 = 2
            if (r3 != 0) goto L_0x0094
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0094
            boolean r3 = android.text.TextUtils.isEmpty(r9)
            if (r3 != 0) goto L_0x0094
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0094 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0094 }
            java.lang.String r1 = "sign"
            java.lang.String r1 = com.unionpay.utils.i.a((org.json.JSONObject) r3, (java.lang.String) r1)     // Catch:{ Exception -> 0x0094 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0038 }
            goto L_0x0039
        L_0x0038:
            r2 = 0
        L_0x0039:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0094 }
            java.lang.String r7 = "configs"
            java.lang.String r7 = r3.getString(r7)     // Catch:{ Exception -> 0x0094 }
            byte[] r7 = android.util.Base64.decode(r7, r5)     // Catch:{ Exception -> 0x0094 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0094 }
            java.lang.String r7 = ""
            java.lang.String r8 = "sePayConf"
            boolean r8 = r3.has(r8)     // Catch:{ Exception -> 0x0094 }
            if (r8 == 0) goto L_0x0061
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x0094 }
            java.lang.String r8 = "sePayConf"
            java.lang.String r3 = r3.getString(r8)     // Catch:{ Exception -> 0x0094 }
            byte[] r3 = android.util.Base64.decode(r3, r5)     // Catch:{ Exception -> 0x0094 }
            r7.<init>(r3)     // Catch:{ Exception -> 0x0094 }
        L_0x0061:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x0094 }
            if (r3 == 0) goto L_0x0069
            java.lang.String r7 = ""
        L_0x0069:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0094 }
            r3.<init>()     // Catch:{ Exception -> 0x0094 }
            r3.append(r6)     // Catch:{ Exception -> 0x0094 }
            r3.append(r7)     // Catch:{ Exception -> 0x0094 }
            r3.append(r9)     // Catch:{ Exception -> 0x0094 }
            java.lang.String r9 = r3.toString()     // Catch:{ Exception -> 0x0094 }
            java.lang.String r9 = com.unionpay.utils.UPUtils.a(r9)     // Catch:{ Exception -> 0x0094 }
            java.lang.String r9 = a((java.lang.String) r9)     // Catch:{ Exception -> 0x0094 }
            java.lang.String r1 = com.unionpay.utils.UPUtils.forConfig(r2, r1)     // Catch:{ Exception -> 0x0094 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0094 }
            if (r2 != 0) goto L_0x0094
            boolean r9 = r1.equals(r9)     // Catch:{ Exception -> 0x0094 }
            if (r9 == 0) goto L_0x0094
            r0 = r6
        L_0x0094:
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00cb }
            r9.<init>(r0)     // Catch:{ JSONException -> 0x00cb }
            int r0 = r9.length()
        L_0x009d:
            if (r4 >= r0) goto L_0x00c8
            java.lang.Object r1 = com.unionpay.utils.i.a((org.json.JSONArray) r9, (int) r4)
            if (r1 == 0) goto L_0x00c5
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            java.lang.String r2 = "type"
            java.lang.String r2 = com.unionpay.utils.i.a((org.json.JSONObject) r1, (java.lang.String) r2)
            java.lang.String r3 = "app"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x00c5
            java.lang.String r9 = "ca"
            java.lang.String r9 = com.unionpay.utils.i.a((org.json.JSONObject) r1, (java.lang.String) r9)
            java.lang.String r0 = new java.lang.String
            byte[] r9 = android.util.Base64.decode(r9, r5)
            r0.<init>(r9)
            return r0
        L_0x00c5:
            int r4 = r4 + 1
            goto L_0x009d
        L_0x00c8:
            java.lang.String r9 = ""
            return r9
        L_0x00cb:
            java.lang.String r9 = ""
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.a(android.content.Context):java.lang.String");
    }

    public static String a(InputStream inputStream, String str) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return byteArrayOutputStream.toString(str);
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static String a(String str) {
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
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
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
        File file = new File(str);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(map);
            String bigInteger = new BigInteger(1, instance.digest()).toString(16);
            try {
                fileInputStream.close();
                return bigInteger;
            } catch (IOException e) {
                e.printStackTrace();
                return bigInteger;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                fileInputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return null;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public static String c(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return context.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return "";
    }

    public static void c(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                for (File path : file.listFiles()) {
                    c(path.getPath());
                }
            }
            file.delete();
        }
    }

    public static String d(String str) {
        return !TextUtils.isEmpty((CharSequence) b.get(str)) ? (String) b.get(str) : str;
    }

    public static boolean d(Context context, String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
        }
        return packageInfo != null;
    }

    public static final boolean e(String str) {
        return !Pattern.compile("[^0-9]+").matcher(str).find();
    }
}
