package com.ximalaya.ting.android.opensdk.auth.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public final class f {
    private static boolean a(String str) {
        return str != null && !str.trim().equals("") && !str.equals("\\s");
    }

    private static String b() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String c(long j) {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(j));
    }

    @SuppressLint({"SimpleDateFormat"})
    private static long a(String str, String str2) {
        try {
            return new SimpleDateFormat(str).parse(str2).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static long b(String str) {
        return a("yyyy-MM-dd HH:mm:ss", str);
    }

    private static String a(int i) {
        StringBuffer stringBuffer = new StringBuffer("");
        int i2 = i / 3600;
        int i3 = i % 3600;
        if (i2 > 0) {
            stringBuffer.append(i2 + ":");
        }
        int i4 = i3 / 60;
        int i5 = i3 % 60;
        if (i4 / 10 > 0) {
            stringBuffer.append(i4 + ":");
        } else {
            stringBuffer.append("0");
            stringBuffer.append(i4);
            stringBuffer.append(":");
        }
        int i6 = i5 % 60;
        if (i6 / 10 > 0) {
            stringBuffer.append(i6);
        } else {
            stringBuffer.append("0");
            stringBuffer.append(i6);
        }
        return stringBuffer.toString();
    }

    private static String c(String str) {
        try {
            String path = new URI(str).getPath();
            String[] split = path.split("/");
            Pattern compile = Pattern.compile("([一-龥]+)");
            StringBuffer stringBuffer = new StringBuffer(47);
            for (String str2 : split) {
                if (compile.matcher(str2).find()) {
                    str2 = URLEncoder.encode(str2, "UTF-8");
                }
                stringBuffer.append(str2);
                stringBuffer.append(IOUtils.f15883a);
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            return str.replace(path, stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static boolean d(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.matches("^1[3|4|5|7|8][0-9]\\d{8}$");
        }
        return false;
    }

    private static boolean e(String str) {
        return b("133|153|180|181|189", str.substring(0, 3));
    }

    private static boolean f(String str) {
        if (b("135|136|137|138|139|147|150|151|152|157|158|159|178|182|183|184|187|188", str.substring(0, 3)) || b("134[0-8]", str.substring(0, 4))) {
            return true;
        }
        return false;
    }

    private static boolean g(String str) {
        return b("130|131|132|145|155|156|176|185|186|189", str.substring(0, 3));
    }

    private static boolean h(String str) {
        return b("170[0|5|9]", str.substring(0, 4));
    }

    private static boolean i(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.matches("^(?=\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$).{6,30}$");
        }
        return false;
    }

    private static boolean j(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.matches("^(?=\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$).{6,64}$");
        }
        return false;
    }

    private static double b(int i) {
        double d = (double) (i / 1024);
        Double.isNaN(d);
        return new BigDecimal(d / 1024.0d).setScale(1, 4).doubleValue();
    }

    private static String k(String str) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '\"') {
                    sb.append("&quot;");
                } else if (charAt == '<') {
                    sb.append("&lt;");
                } else if (charAt != '>') {
                    switch (charAt) {
                        case '&':
                            sb.append("&amp;");
                            break;
                        case '\'':
                            sb.append("&#39;");
                            break;
                        default:
                            sb.append(charAt);
                            break;
                    }
                } else {
                    sb.append("&gt;");
                }
            }
        }
        return sb.toString();
    }

    private static String l(String str) {
        try {
            return str.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", a.b).replace("&#39;", "'").replace("&quot;", "\"");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String c(int i) {
        int i2 = i % 100;
        StringBuffer stringBuffer = new StringBuffer((i / 100) + ".");
        if (i2 >= 10 || i2 < 0) {
            stringBuffer.append(i2);
        } else {
            stringBuffer.append("0");
            stringBuffer.append(i2);
        }
        return stringBuffer.toString();
    }

    private static String d(long j) {
        long j2 = j >> 10;
        if (j2 < 1024) {
            return String.format("%d Kb", new Object[]{Long.valueOf(j2)});
        } else if ((j2 >> 10) < 1024) {
            double d = (double) j2;
            Double.isNaN(d);
            return String.format("%.2f M", new Object[]{Double.valueOf(d / 1024.0d)});
        } else {
            double d2 = (double) j2;
            Double.isNaN(d2);
            return String.format("%.2f GB", new Object[]{Double.valueOf(d2 / 2048.0d)});
        }
    }

    private static String d(int i) {
        if (i < 10000) {
            return String.valueOf(i);
        }
        if (i < 100000000) {
            return String.valueOf(i / 10000) + "万";
        }
        return String.valueOf(i / 100000000) + "亿";
    }

    private static boolean b(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r6 = r6.split("\\.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int[] m(java.lang.String r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "\\."
            java.lang.String[] r6 = r6.split(r1)
            int r1 = r6.length
            r2 = 4
            if (r1 == r2) goto L_0x000f
            return r0
        L_0x000f:
            int[] r2 = new int[r2]
            r3 = 0
        L_0x0012:
            if (r3 >= r1) goto L_0x002c
            r4 = r6[r3]     // Catch:{ Exception -> 0x002b }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x002b }
            r2[r3] = r4     // Catch:{ Exception -> 0x002b }
            r4 = r2[r3]     // Catch:{ Exception -> 0x002b }
            if (r4 < 0) goto L_0x002a
            r4 = r2[r3]     // Catch:{ Exception -> 0x002b }
            r5 = 255(0xff, float:3.57E-43)
            if (r4 <= r5) goto L_0x0027
            goto L_0x002a
        L_0x0027:
            int r3 = r3 + 1
            goto L_0x0012
        L_0x002a:
            return r0
        L_0x002b:
            return r0
        L_0x002c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.utils.f.m(java.lang.String):int[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r5 = r5.split("\\.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean n(java.lang.String r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "."
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L_0x0033
            java.lang.String r1 = "."
            boolean r1 = r5.endsWith(r1)
            if (r1 == 0) goto L_0x0015
            goto L_0x0033
        L_0x0015:
            java.lang.String r1 = "\\."
            java.lang.String[] r5 = r5.split(r1)
            int r1 = r5.length
            r2 = 4
            if (r1 == r2) goto L_0x0020
            return r0
        L_0x0020:
            java.lang.String r2 = "\\d|[1-9][0-9]|1[0-9][0-9]|2(([0-4][0-9])|(5[0-5]))"
            r3 = 0
        L_0x0023:
            if (r3 >= r1) goto L_0x0031
            r4 = r5[r3]
            boolean r4 = b(r2, r4)
            if (r4 != 0) goto L_0x002e
            return r0
        L_0x002e:
            int r3 = r3 + 1
            goto L_0x0023
        L_0x0031:
            r5 = 1
            return r5
        L_0x0033:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.utils.f.n(java.lang.String):boolean");
    }

    private static boolean o(String str) {
        int[] m = m(str);
        if (m == null || m[0] != 255) {
            return false;
        }
        if (m[1] != 255) {
            if (m[2] == 0 && m[3] == 0 && (m[1] == 128 || m[1] == 0 || m[1] == 192 || m[1] == 224 || m[1] == 240 || m[1] == 248 || m[1] == 252 || m[1] == 254)) {
                return true;
            }
            return false;
        } else if (m[2] != 255) {
            if (m[3] == 0 && (m[2] == 128 || m[2] == 0 || m[2] == 192 || m[2] == 224 || m[2] == 240 || m[2] == 248 || m[2] == 252 || m[2] == 254)) {
                return true;
            }
            return false;
        } else if (m[3] == 128 || m[3] == 0 || m[3] == 192 || m[3] == 224 || m[3] == 240 || m[3] == 248 || m[3] == 252 || m[3] == 254) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean p(String str) {
        return Pattern.compile("[\\x41-\\x5a]").matcher(str).find();
    }

    private static boolean q(String str) {
        return Pattern.compile("[\\x61-\\x7a]").matcher(str).find();
    }

    private static boolean r(String str) {
        return Pattern.compile("[\\x30-\\x39]").matcher(str).find();
    }

    private static boolean s(String str) {
        return Pattern.compile("[\\x11-\\x2f]|[\\x3b-\\x40]|[\\x5b-\\x60]|[\\x7a-\\x7e]").matcher(str).find();
    }

    private static boolean t(String str) {
        return Pattern.compile("[^\\x11-\\x7e]|[\\s]").matcher(str).find();
    }

    private static boolean u(String str) {
        return Pattern.compile("[\\s]").matcher(str).find();
    }

    private static String v(String str) {
        StringBuffer stringBuffer = new StringBuffer("");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            String valueOf = String.valueOf(str.charAt(i));
            if (!valueOf.matches("[一-龥]")) {
                stringBuffer.append(valueOf);
            }
        }
        return stringBuffer.toString();
    }

    private static int w(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (String.valueOf(str.charAt(i2)).matches("[一-龥]")) {
                i++;
            }
        }
        return i;
    }

    private static boolean x(String str) {
        return Pattern.compile("[一-龥]").matcher(str).find();
    }

    private static boolean y(String str) {
        return Pattern.compile("[\\\\\"'_~&<()>%{}]").matcher(str).find();
    }

    private static String a() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    private static String a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j * 1000));
    }
}
