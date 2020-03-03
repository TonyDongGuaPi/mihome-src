package com.loc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.loc.ac;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager;
import com.xiaomi.youpin.PackageUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class ad {

    /* renamed from: a  reason: collision with root package name */
    static String f6475a;
    private static final String[] b = {PluginSoManager.c, "x86_64"};
    private static final String[] c = {"arm", PackageUtils.c};

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        f6475a = sb.toString();
    }

    public static ac a() throws t {
        return new ac.a("collection", "1.0", "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a();
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String a(long j, String str) {
        try {
            return new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String a(Context context) {
        String[] strArr;
        String str = "";
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 28) {
            try {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                declaredField.setAccessible(true);
                str = (String) declaredField.get(applicationInfo);
            } catch (Throwable th) {
                an.a(th, "ut", "gct");
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                String[] strArr2 = (String[]) Build.class.getDeclaredField("SUPPORTED_ABIS").get((Object) null);
                if (strArr2 != null && strArr2.length > 0) {
                    str = strArr2[0];
                }
                if (!TextUtils.isEmpty(str) && Arrays.asList(b).contains(str)) {
                    String str2 = context.getApplicationInfo().nativeLibraryDir;
                    if (!TextUtils.isEmpty(str2)) {
                        if (Arrays.asList(c).contains(str2.substring(str2.lastIndexOf(File.separator) + 1)) && (strArr = (String[]) Build.class.getDeclaredField("SUPPORTED_32_BIT_ABIS").get((Object) null)) != null && strArr.length > 0) {
                            str = strArr[0];
                        }
                    }
                }
            } catch (Throwable th2) {
                an.a(th2, "ut", "gct_p");
            }
        }
        return TextUtils.isEmpty(str) ? Build.CPU_ABI : str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0041 A[SYNTHETIC, Splitter:B:32:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x004b A[SYNTHETIC, Splitter:B:37:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0057 A[SYNTHETIC, Splitter:B:44:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0061 A[SYNTHETIC, Splitter:B:49:0x0061] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.Throwable r3) {
        /*
            r0 = 0
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ Throwable -> 0x0039, all -> 0x0035 }
            r1.<init>()     // Catch:{ Throwable -> 0x0039, all -> 0x0035 }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x0032, all -> 0x002f }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0032, all -> 0x002f }
            r3.printStackTrace(r2)     // Catch:{ Throwable -> 0x002d }
        L_0x000e:
            java.lang.Throwable r3 = r3.getCause()     // Catch:{ Throwable -> 0x002d }
            if (r3 == 0) goto L_0x0018
            r3.printStackTrace(r2)     // Catch:{ Throwable -> 0x002d }
            goto L_0x000e
        L_0x0018:
            java.lang.String r3 = r1.toString()     // Catch:{ Throwable -> 0x002d }
            r1.close()     // Catch:{ Throwable -> 0x0020 }
            goto L_0x0024
        L_0x0020:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0024:
            r2.close()     // Catch:{ Throwable -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002c:
            return r3
        L_0x002d:
            r3 = move-exception
            goto L_0x003c
        L_0x002f:
            r3 = move-exception
            r2 = r0
            goto L_0x0055
        L_0x0032:
            r3 = move-exception
            r2 = r0
            goto L_0x003c
        L_0x0035:
            r3 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0055
        L_0x0039:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L_0x003c:
            r3.printStackTrace()     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ Throwable -> 0x0045 }
            goto L_0x0049
        L_0x0045:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0049:
            if (r2 == 0) goto L_0x0053
            r2.close()     // Catch:{ Throwable -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0053:
            return r0
        L_0x0054:
            r3 = move-exception
        L_0x0055:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ Throwable -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005f:
            if (r2 == 0) goto L_0x0069
            r2.close()     // Catch:{ Throwable -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0069:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ad.a(java.lang.Throwable):java.lang.String");
    }

    public static String a(Map<String, String> map) {
        Object value;
        if (map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        try {
            for (Map.Entry next : map.entrySet()) {
                if (z) {
                    z = false;
                    stringBuffer.append((String) next.getKey());
                    stringBuffer.append("=");
                    value = next.getValue();
                } else {
                    stringBuffer.append(a.b);
                    stringBuffer.append((String) next.getKey());
                    stringBuffer.append("=");
                    value = next.getValue();
                }
                stringBuffer.append((String) value);
            }
        } catch (Throwable th) {
            an.a(th, "ut", "abP");
        }
        return stringBuffer.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    public static Method a(Class cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(c(str), clsArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(Context context, String str, String str2, JSONObject jSONObject) {
        String str3;
        String str4 = "";
        String e = u.e(context);
        String b2 = aa.b(e);
        String str5 = "";
        String str6 = "";
        String str7 = "";
        String a2 = u.a(context);
        String str8 = "";
        try {
            if (jSONObject.has("info")) {
                str4 = jSONObject.getString("info");
                str7 = "请在高德开放平台官网中搜索\"" + str4 + "\"相关内容进行解决";
            }
            if ("INVALID_USER_SCODE".equals(str4)) {
                if (jSONObject.has("sec_code")) {
                    str5 = jSONObject.getString("sec_code");
                }
                if (jSONObject.has("sec_code_debug")) {
                    str6 = jSONObject.getString("sec_code_debug");
                }
                if (b2.equals(str5) || b2.equals(str6)) {
                    str3 = "请在高德开放平台官网中搜索\"请求内容过长导致业务调用失败\"相关内容进行解决";
                    Log.i("authErrLog", f6475a);
                    Log.i("authErrLog", "                                   鉴权错误信息                                  ");
                    Log.i("authErrLog", f6475a);
                    f("SHA1Package:" + e);
                    f("key:" + a2);
                    f("csid:" + str);
                    f("gsid:" + str2);
                    f("json:" + jSONObject.toString());
                    Log.i("authErrLog", "                                                                               ");
                    Log.i("authErrLog", str3);
                    Log.i("authErrLog", f6475a);
                }
                str3 = str7;
                Log.i("authErrLog", f6475a);
                Log.i("authErrLog", "                                   鉴权错误信息                                  ");
                Log.i("authErrLog", f6475a);
                f("SHA1Package:" + e);
                f("key:" + a2);
                f("csid:" + str);
                f("gsid:" + str2);
                f("json:" + jSONObject.toString());
                Log.i("authErrLog", "                                                                               ");
                Log.i("authErrLog", str3);
                Log.i("authErrLog", f6475a);
            }
            if ("INVALID_USER_KEY".equals(str4)) {
                if (jSONObject.has("key")) {
                    str8 = jSONObject.getString("key");
                }
                if (str8.length() > 0 && !a2.equals(str8)) {
                    str3 = "请在高德开放平台官网上发起技术咨询工单—>账号与Key问题，咨询INVALID_USER_KEY如何解决";
                    Log.i("authErrLog", f6475a);
                    Log.i("authErrLog", "                                   鉴权错误信息                                  ");
                    Log.i("authErrLog", f6475a);
                    f("SHA1Package:" + e);
                    f("key:" + a2);
                    f("csid:" + str);
                    f("gsid:" + str2);
                    f("json:" + jSONObject.toString());
                    Log.i("authErrLog", "                                                                               ");
                    Log.i("authErrLog", str3);
                    Log.i("authErrLog", f6475a);
                }
            }
            str3 = str7;
            Log.i("authErrLog", f6475a);
            Log.i("authErrLog", "                                   鉴权错误信息                                  ");
            Log.i("authErrLog", f6475a);
            f("SHA1Package:" + e);
            f("key:" + a2);
            f("csid:" + str);
            f("gsid:" + str2);
            f("json:" + jSONObject.toString());
            Log.i("authErrLog", "                                                                               ");
            Log.i("authErrLog", str3);
            Log.i("authErrLog", f6475a);
        } catch (Throwable unused) {
        }
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, byte b2, byte[] bArr) {
        try {
            byteArrayOutputStream.write(new byte[]{b2});
            byte b3 = b2 & 255;
            if (b3 < 255 && b3 > 0) {
                byteArrayOutputStream.write(bArr);
            } else if (b3 == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            an.a((Throwable) e, "ut", "wFie");
        }
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            try {
                byteArrayOutputStream.write(new byte[]{0});
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            int length = str.length();
            if (length > 255) {
                length = 255;
            }
            a(byteArrayOutputStream, (byte) length, a(str));
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || context.checkCallingOrSelfPermission(str) != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23) {
            try {
                if (((Integer) context.getClass().getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() != 0) {
                    return false;
                }
            } catch (Throwable unused) {
            }
        }
        return true;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static ac b() throws t {
        return new ac.a("co", "1.0.0", "AMap_co_1.0.0").a(new String[]{"com.amap.co", "com.amap.opensdk.co", "com.amap.location"}).a();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String c2 = y.c(a(str));
        try {
            return ((char) ((c2.length() % 26) + 65)) + c2;
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(Map<String, String> map) {
        String str;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(a.b);
                }
                sb.append((String) next.getKey());
                sb.append("=");
                sb.append((String) next.getValue());
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return d(str);
    }

    public static boolean b(Context context) {
        return at.a(context);
    }

    public static byte[] b(byte[] bArr) {
        try {
            return h(bArr);
        } catch (Throwable th) {
            an.a(th, "ut", "gZp");
            return new byte[0];
        }
    }

    public static String c(String str) {
        return str.length() < 2 ? "" : y.a(str.substring(1));
    }

    public static byte[] c() {
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(y.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            an.a(th, "ut", "gIV");
            return new byte[16];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0060 A[SYNTHETIC, Splitter:B:31:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0070 A[SYNTHETIC, Splitter:B:36:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083 A[SYNTHETIC, Splitter:B:43:0x0083] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0093 A[SYNTHETIC, Splitter:B:48:0x0093] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] c(byte[] r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x00a2
            int r1 = r5.length
            if (r1 != 0) goto L_0x0008
            goto L_0x00a2
        L_0x0008:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0052, all -> 0x004e }
            r1.<init>()     // Catch:{ Throwable -> 0x0052, all -> 0x004e }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Throwable -> 0x004b, all -> 0x0048 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x004b, all -> 0x0048 }
            java.util.zip.ZipEntry r3 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x0046 }
            java.lang.String r4 = "log"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0046 }
            r2.putNextEntry(r3)     // Catch:{ Throwable -> 0x0046 }
            r2.write(r5)     // Catch:{ Throwable -> 0x0046 }
            r2.closeEntry()     // Catch:{ Throwable -> 0x0046 }
            r2.finish()     // Catch:{ Throwable -> 0x0046 }
            byte[] r5 = r1.toByteArray()     // Catch:{ Throwable -> 0x0046 }
            r2.close()     // Catch:{ Throwable -> 0x002d }
            goto L_0x0037
        L_0x002d:
            r0 = move-exception
            java.lang.String r2 = "ut"
            java.lang.String r3 = "zp1"
            com.loc.an.a((java.lang.Throwable) r0, (java.lang.String) r2, (java.lang.String) r3)
        L_0x0037:
            r1.close()     // Catch:{ Throwable -> 0x003b }
            goto L_0x007f
        L_0x003b:
            r0 = move-exception
            java.lang.String r1 = "ut"
            java.lang.String r2 = "zp2"
            com.loc.an.a((java.lang.Throwable) r0, (java.lang.String) r1, (java.lang.String) r2)
            goto L_0x007f
        L_0x0046:
            r5 = move-exception
            goto L_0x0055
        L_0x0048:
            r5 = move-exception
            r2 = r0
            goto L_0x0081
        L_0x004b:
            r5 = move-exception
            r2 = r0
            goto L_0x0055
        L_0x004e:
            r5 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0081
        L_0x0052:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L_0x0055:
            java.lang.String r3 = "ut"
            java.lang.String r4 = "zp"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0080 }
            if (r2 == 0) goto L_0x006e
            r2.close()     // Catch:{ Throwable -> 0x0064 }
            goto L_0x006e
        L_0x0064:
            r5 = move-exception
            java.lang.String r2 = "ut"
            java.lang.String r3 = "zp1"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r2, (java.lang.String) r3)
        L_0x006e:
            if (r1 == 0) goto L_0x007e
            r1.close()     // Catch:{ Throwable -> 0x0074 }
            goto L_0x007e
        L_0x0074:
            r5 = move-exception
            java.lang.String r1 = "ut"
            java.lang.String r2 = "zp2"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r1, (java.lang.String) r2)
        L_0x007e:
            r5 = r0
        L_0x007f:
            return r5
        L_0x0080:
            r5 = move-exception
        L_0x0081:
            if (r2 == 0) goto L_0x0091
            r2.close()     // Catch:{ Throwable -> 0x0087 }
            goto L_0x0091
        L_0x0087:
            r0 = move-exception
            java.lang.String r2 = "ut"
            java.lang.String r3 = "zp1"
            com.loc.an.a((java.lang.Throwable) r0, (java.lang.String) r2, (java.lang.String) r3)
        L_0x0091:
            if (r1 == 0) goto L_0x00a1
            r1.close()     // Catch:{ Throwable -> 0x0097 }
            goto L_0x00a1
        L_0x0097:
            r0 = move-exception
            java.lang.String r1 = "ut"
            java.lang.String r2 = "zp2"
            com.loc.an.a((java.lang.Throwable) r0, (java.lang.String) r1, (java.lang.String) r2)
        L_0x00a1:
            throw r5
        L_0x00a2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ad.c(byte[]):byte[]");
    }

    public static String d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String[] split = str.split(a.b);
            Arrays.sort(split);
            StringBuffer stringBuffer = new StringBuffer();
            for (String append : split) {
                stringBuffer.append(append);
                stringBuffer.append(a.b);
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() > 1) {
                return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
            }
            return str;
        } catch (Throwable th) {
            an.a(th, "ut", "sPa");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0050 A[SYNTHETIC, Splitter:B:28:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005c A[SYNTHETIC, Splitter:B:35:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.security.PublicKey d() throws java.security.cert.CertificateException, java.security.spec.InvalidKeySpecException, java.security.NoSuchAlgorithmException, java.lang.NullPointerException, java.io.IOException {
        /*
            java.lang.String r0 = "MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="
            r1 = 0
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ Throwable -> 0x0049, all -> 0x0046 }
            byte[] r0 = com.loc.y.b((java.lang.String) r0)     // Catch:{ Throwable -> 0x0049, all -> 0x0046 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0049, all -> 0x0046 }
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch:{ Throwable -> 0x0044 }
            java.lang.String r3 = "RSA"
            java.security.KeyFactory r3 = java.security.KeyFactory.getInstance(r3)     // Catch:{ Throwable -> 0x0044 }
            java.security.cert.Certificate r0 = r0.generateCertificate(r2)     // Catch:{ Throwable -> 0x0044 }
            if (r0 == 0) goto L_0x003b
            if (r3 != 0) goto L_0x0021
            goto L_0x003b
        L_0x0021:
            java.security.spec.X509EncodedKeySpec r4 = new java.security.spec.X509EncodedKeySpec     // Catch:{ Throwable -> 0x0044 }
            java.security.PublicKey r0 = r0.getPublicKey()     // Catch:{ Throwable -> 0x0044 }
            byte[] r0 = r0.getEncoded()     // Catch:{ Throwable -> 0x0044 }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x0044 }
            java.security.PublicKey r0 = r3.generatePublic(r4)     // Catch:{ Throwable -> 0x0044 }
            r2.close()     // Catch:{ Throwable -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003a:
            return r0
        L_0x003b:
            r2.close()     // Catch:{ Throwable -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0043:
            return r1
        L_0x0044:
            r0 = move-exception
            goto L_0x004b
        L_0x0046:
            r0 = move-exception
            r2 = r1
            goto L_0x005a
        L_0x0049:
            r0 = move-exception
            r2 = r1
        L_0x004b:
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0058
            r2.close()     // Catch:{ Throwable -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0058:
            return r1
        L_0x0059:
            r0 = move-exception
        L_0x005a:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ Throwable -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0064:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ad.d():java.security.PublicKey");
    }

    public static byte[] d(byte[] bArr) {
        try {
            return h(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    static String e(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            an.a(th, "ut", "h2s");
            return null;
        }
    }

    public static byte[] e(String str) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    static String f(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static void f(String str) {
        int i;
        while (true) {
            if (str.length() < 78) {
                break;
            }
            String substring = str.substring(0, 78);
            Log.i("authErrLog", "|" + substring + "|");
            str = str.substring(78);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        sb.append(str);
        for (i = 0; i < 78 - str.length(); i++) {
            sb.append(" ");
        }
        sb.append("|");
        Log.i("authErrLog", sb.toString());
    }

    public static String g(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0038 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0040 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] h(byte[] r3) throws java.io.IOException, java.lang.Throwable {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r1.<init>()     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ Throwable -> 0x0029, all -> 0x0027 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0029, all -> 0x0027 }
            r2.write(r3)     // Catch:{ Throwable -> 0x0025, all -> 0x0023 }
            r2.finish()     // Catch:{ Throwable -> 0x0025, all -> 0x0023 }
            byte[] r3 = r1.toByteArray()     // Catch:{ Throwable -> 0x0025, all -> 0x0023 }
            r2.close()     // Catch:{ Throwable -> 0x0021 }
            r1.close()     // Catch:{ Throwable -> 0x001f }
            return r3
        L_0x001f:
            r3 = move-exception
            throw r3
        L_0x0021:
            r3 = move-exception
            throw r3
        L_0x0023:
            r3 = move-exception
            goto L_0x0035
        L_0x0025:
            r3 = move-exception
            goto L_0x002b
        L_0x0027:
            r3 = move-exception
            goto L_0x0036
        L_0x0029:
            r3 = move-exception
            r2 = r0
        L_0x002b:
            r0 = r1
            goto L_0x0032
        L_0x002d:
            r3 = move-exception
            r1 = r0
            goto L_0x0036
        L_0x0030:
            r3 = move-exception
            r2 = r0
        L_0x0032:
            throw r3     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r3 = move-exception
            r1 = r0
        L_0x0035:
            r0 = r2
        L_0x0036:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ Throwable -> 0x003c }
            goto L_0x003e
        L_0x003c:
            r3 = move-exception
            throw r3
        L_0x003e:
            if (r1 == 0) goto L_0x0046
            r1.close()     // Catch:{ Throwable -> 0x0044 }
            goto L_0x0046
        L_0x0044:
            r3 = move-exception
            throw r3
        L_0x0046:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ad.h(byte[]):byte[]");
    }
}
