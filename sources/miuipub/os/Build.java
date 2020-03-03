package miuipub.os;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.mijia.model.property.CameraPropertyBase;
import miuipub.util.AttributeResolver;

public class Build extends android.os.Build {
    public static final boolean A = "HM2013022".equals(DEVICE);
    public static final boolean B = (p || q || r || s);
    public static final boolean C = (u || A || t);
    public static final boolean D = "cu".equals(SystemProperties.a("ro.carrier.name"));
    public static final boolean E = "cm".equals(SystemProperties.a("ro.carrier.name"));
    public static final boolean F = "ct".equals(SystemProperties.a("ro.carrier.name"));
    public static final boolean G = (!TextUtils.isEmpty(Build.VERSION.INCREMENTAL) && Build.VERSION.INCREMENTAL.matches(aa));
    public static final boolean H = ("user".equals(TYPE) && !G);
    public static final boolean I;
    public static final boolean J = "1".equals(SystemProperties.a("ro.miuipub.secure"));
    public static final boolean K = "1".equals(SystemProperties.a("ro.miuipub.cts"));
    public static final boolean L = "1".equals(SystemProperties.a("ro.miui.cta"));
    public static final String M = SystemProperties.a("ro.miuipub.region", "cn");
    public static final boolean N = M.endsWith("tw");
    public static final boolean O = M.endsWith("hk");
    public static final boolean P = M.endsWith("sg");
    public static final boolean Q = M.endsWith("my");
    public static final boolean R = M.endsWith("ph");
    public static final boolean S = M.endsWith("id");
    public static final boolean T = M.endsWith("in");
    public static final boolean U = M.endsWith("th");
    public static final boolean V = SystemProperties.a("ro.product.mod_device", "").endsWith("_global");
    public static final boolean W = d();
    public static final String X = "persist.sys.user_mode";
    public static final int Y = 0;
    public static final int Z = 1;

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f2986a = ("mione".equals(DEVICE) || "mione_plus".equals(DEVICE));
    private static final String aa = "\\d+.\\d+.\\d+(-internal)?";
    private static final String ab = "([A-Z]{3}|[A-Z]{7})\\d+.\\d+";
    public static final boolean b = ("MI 1S".equals(MODEL) || "MI 1SC".equals(MODEL));
    public static final boolean c = ("aries".equals(DEVICE) || "taurus".equals(DEVICE) || "taurus_td".equals(DEVICE));
    public static final boolean d = ("MI 2A".equals(MODEL) || "MI 2A TD".equals(MODEL));
    public static final boolean e = ("pisces".equals(DEVICE) || ("cancro".equals(DEVICE) && MODEL.startsWith("MI 3")));
    public static final boolean f = ("cancro".equals(DEVICE) && MODEL.startsWith("MI 4"));
    public static final boolean g = "virgo".equals(DEVICE);
    public static final boolean h = (f2986a || c || e || f || g);
    public static final boolean i = "mocha".equals(DEVICE);
    public static final boolean j = "flo".equals(DEVICE);
    public static final boolean k = "armani".equals(DEVICE);
    public static final boolean l = ("HM2014011".equals(DEVICE) || "HM2014012".equals(DEVICE));
    public static final boolean m = ("HM2013022".equals(DEVICE) || "HM2013023".equals(DEVICE) || k || l);
    public static final boolean n = ("lcsh92_wet_jb9".equals(DEVICE) || "lcsh92_wet_tdd".equals(DEVICE));
    public static final boolean o = (m || n);
    public static final boolean p = (f2986a && c());
    public static final boolean q = (c && "CDMA".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean r = (e && "MI 3C".equals(MODEL));
    public static final boolean s = (f && "CDMA".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean t = (c && "TD".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean u = (e && "TD".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean v = (f && "LTE-CMCC".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean w = (f && "LTE-CU".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean x = (f && "LTE-CT".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean y = (f && "LTE-India".equals(SystemProperties.a("persist.radio.modem")));
    public static final boolean z = (f && "LTE-SEAsa".equals(SystemProperties.a("persist.radio.modem")));

    protected Build() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    static {
        boolean z2 = false;
        if (G || H) {
            z2 = true;
        }
        I = z2;
    }

    private static boolean c() {
        String a2 = SystemProperties.a("ro.soc.name");
        return "msm8660".equals(a2) || "unkown".equals(a2);
    }

    private static boolean d() {
        if (j || i) {
            return true;
        }
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        if (((int) ((((float) Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)) / displayMetrics.density) + 0.5f)) >= 600) {
            return true;
        }
        return false;
    }

    public static boolean a(Context context) {
        return AttributeResolver.a(context);
    }

    public static boolean b(Context context) {
        return !j && !i && context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public static int a() {
        return SystemProperties.a(X, 0);
    }

    public static void a(Context context, int i2) {
        SystemProperties.b(X, Integer.toString(i2));
        ((PowerManager) context.getSystemService(CameraPropertyBase.l)).reboot((String) null);
    }

    public static String b() {
        if (!V) {
            return SystemProperties.a("ro.miui.cust_variant", "cn");
        }
        return SystemProperties.a("ro.miui.cust_variant", "hk");
    }
}
