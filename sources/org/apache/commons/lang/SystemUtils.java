package org.apache.commons.lang;

import com.daimajia.numberprogressbar.BuildConfig;
import java.io.File;
import java.io.PrintStream;

public class SystemUtils {
    public static final String A = f("java.vm.specification.name");
    public static final String B = f("java.vm.specification.vendor");
    public static final String C = f("java.vm.specification.version");
    public static final String D = f("java.vm.vendor");
    public static final String E = f("java.vm.version");
    public static final String F = f("line.separator");
    public static final String G = f("os.arch");
    public static final String H = f("os.name");
    public static final String I = f("os.version");
    public static final String J = f("path.separator");
    public static final String K = f(f("user.country") == null ? "user.region" : "user.country");
    public static final String L = f(aw);
    public static final String M = f(av);
    public static final String N = f("user.language");
    public static final String O = f("user.name");
    public static final String P = f("user.timezone");
    public static final String Q = i();
    public static final float R = g();
    public static final int S = h();
    public static final boolean T = d("1.1");
    public static final boolean U = d("1.2");
    public static final boolean V = d("1.3");
    public static final boolean W = d(BuildConfig.VERSION_NAME);
    public static final boolean X = d("1.5");
    public static final boolean Y = d("1.6");
    public static final boolean Z = d("1.7");

    /* renamed from: a  reason: collision with root package name */
    public static final String f3374a = f("awt.toolkit");
    public static final boolean aa = e("AIX");
    public static final boolean ab = e("HP-UX");
    public static final boolean ac = e("Irix");
    public static final boolean ad = (e("Linux") || e("LINUX"));
    public static final boolean ae = e("Mac");
    public static final boolean af = e("Mac OS X");
    public static final boolean ag = e("OS/2");
    public static final boolean ah = e("Solaris");
    public static final boolean ai = e("SunOS");
    public static final boolean aj;
    public static final boolean ak = e(au);
    public static final boolean al = c(au, "5.0");
    public static final boolean am = c("Windows 9", "4.0");
    public static final boolean an = c("Windows 9", "4.1");
    public static final boolean ao = c(au, "4.9");
    public static final boolean ap = e("Windows NT");
    public static final boolean aq = c(au, "5.1");
    public static final boolean ar = c(au, "6.0");
    public static final boolean as = c(au, "6.1");
    private static final int at = 3;
    private static final String au = "Windows";
    private static final String av = "user.home";
    private static final String aw = "user.dir";
    private static final String ax = "java.io.tmpdir";
    private static final String ay = "java.home";
    public static final String b = f("file.encoding");
    public static final String c = f("file.separator");
    public static final String d = f("java.awt.fonts");
    public static final String e = f("java.awt.graphicsenv");
    public static final String f = f("java.awt.headless");
    public static final String g = f("java.awt.printerjob");
    public static final String h = f("java.class.path");
    public static final String i = f("java.class.version");
    public static final String j = f("java.compiler");
    public static final String k = f("java.endorsed.dirs");
    public static final String l = f("java.ext.dirs");
    public static final String m = f(ay);
    public static final String n = f(ax);
    public static final String o = f("java.library.path");
    public static final String p = f("java.runtime.name");
    public static final String q = f("java.runtime.version");
    public static final String r = f("java.specification.name");
    public static final String s = f("java.specification.vendor");
    public static final String t = f("java.specification.version");
    public static final String u = f("java.util.prefs.PreferencesFactory");
    public static final String v = f("java.vendor");
    public static final String w = f("java.vendor.url");
    public static final String x = f("java.version");
    public static final String y = f("java.vm.info");
    public static final String z = f("java.vm.name");

    static {
        boolean z2 = false;
        if (aa || ab || ac || ad || af || ah || ai) {
            z2 = true;
        }
        aj = z2;
    }

    public static File a() {
        return new File(System.getProperty(ay));
    }

    public static File b() {
        return new File(System.getProperty(ax));
    }

    public static float c() {
        return R;
    }

    private static float g() {
        return a(a(x, 3));
    }

    private static int h() {
        return b(a(x, 3));
    }

    private static boolean d(String str) {
        return a(Q, str);
    }

    private static String i() {
        if (x == null) {
            return null;
        }
        for (int i2 = 0; i2 < x.length(); i2++) {
            char charAt = x.charAt(i2);
            if (charAt >= '0' && charAt <= '9') {
                return x.substring(i2);
            }
        }
        return null;
    }

    private static boolean c(String str, String str2) {
        return a(H, I, str, str2);
    }

    private static boolean e(String str) {
        return b(H, str);
    }

    private static String f(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Caught a SecurityException reading the system property '");
            stringBuffer.append(str);
            stringBuffer.append("'; the SystemUtils property value will default to null.");
            printStream.println(stringBuffer.toString());
            return null;
        }
    }

    public static File d() {
        return new File(System.getProperty(aw));
    }

    public static File e() {
        return new File(System.getProperty(av));
    }

    public static boolean f() {
        if (f != null) {
            return f.equals(Boolean.TRUE.toString());
        }
        return false;
    }

    public static boolean a(float f2) {
        return R >= f2;
    }

    public static boolean a(int i2) {
        return S >= i2;
    }

    static boolean a(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static boolean a(String str, String str2, String str3, String str4) {
        return str != null && str2 != null && str.startsWith(str3) && str2.startsWith(str4);
    }

    static boolean b(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static float a(String str) {
        return a(a(str, 3));
    }

    static int b(String str) {
        return b(a(str, 3));
    }

    static int[] c(String str) {
        return a(str, Integer.MAX_VALUE);
    }

    private static int[] a(String str, int i2) {
        if (str == null) {
            return ArrayUtils.f;
        }
        String[] w2 = StringUtils.w(str, "._- ");
        int[] iArr = new int[Math.min(i2, w2.length)];
        int i3 = 0;
        for (int i4 = 0; i4 < w2.length && i3 < i2; i4++) {
            String str2 = w2[i4];
            if (str2.length() > 0) {
                try {
                    iArr[i3] = Integer.parseInt(str2);
                    i3++;
                } catch (Exception unused) {
                }
            }
        }
        if (iArr.length <= i3) {
            return iArr;
        }
        int[] iArr2 = new int[i3];
        System.arraycopy(iArr, 0, iArr2, 0, i3);
        return iArr2;
    }

    private static float a(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0.0f;
        }
        if (iArr.length == 1) {
            return (float) iArr[0];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(iArr[0]);
        stringBuffer.append('.');
        for (int i2 = 1; i2 < iArr.length; i2++) {
            stringBuffer.append(iArr[i2]);
        }
        try {
            return Float.parseFloat(stringBuffer.toString());
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    private static int b(int[] iArr) {
        int i2 = 0;
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        if (length >= 1) {
            i2 = iArr[0] * 100;
        }
        if (length >= 2) {
            i2 += iArr[1] * 10;
        }
        return length >= 3 ? i2 + iArr[2] : i2;
    }
}
