package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.WindowManager;
import cn.tongdun.android.core.O0o0o0o0o;
import cn.tongdun.android.core.q9gqqq99999qq.g999gqq9ggqgqq;
import cn.tongdun.android.core.q9gqqq99999qq.gqgqgqq9gq9q9q9;
import cn.tongdun.android.core.q9gqqq99999qq.qgg99qqg9gq;
import cn.tongdun.android.core.q9gqqq99999qq.qgggqg999gg9qqggq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.q9qq99qg9qqgqg9gqgg9;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;
import cn.tongdun.android.shell.common.CollectorError;
import cn.tongdun.android.shell.utils.LogUtil;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class gqq9qggqgg9g99 {
    private static gqq9qggqgg9g99 gqg9qq9gqq9q9q;
    private ConnectivityManager g999gqq9ggqgqq = null;
    private WifiManager g9q9q9g9 = null;
    private SensorManager gqgqgqq9gq9q9q9 = null;
    private TelephonyManager q9gqqq99999qq = null;
    private WindowManager q9q99gq99gggqg9qqqgg = null;
    private Context q9qq99qg9qqgqg9gqgg9 = null;
    private LocationManager qgg99qqg9gq = null;
    private g999gqq9ggqgqq qgg9qgg9999g9g = null;
    private q9qgqg9 qq9q9ggg = null;
    private ActivityManager qqq9gg9gqq9qgg99q = null;

    public static gqq9qggqgg9g99 gqg9qq9gqq9q9q(Context context) {
        if (gqg9qq9gqq9q9q == null) {
            synchronized (gqq9qggqgg9g99.class) {
                if (gqg9qq9gqq9q9q == null) {
                    gqg9qq9gqq9q9q = new gqq9qggqgg9g99(context);
                }
            }
        }
        return gqg9qq9gqq9q9q;
    }

    private gqq9qggqgg9g99(Context context) {
        this.q9qq99qg9qqgqg9gqgg9 = context;
        this.qgg9qgg9999g9g = new g999gqq9ggqgqq();
        this.qq9q9ggg = new q9qgqg9();
        try {
            this.q9gqqq99999qq = (TelephonyManager) context.getSystemService("phone");
            this.g9q9q9g9 = (WifiManager) context.getSystemService("wifi");
            this.qqq9gg9gqq9qgg99q = (ActivityManager) context.getSystemService("activity");
            this.q9q99gq99gggqg9qqqgg = (WindowManager) context.getSystemService("window");
            this.g999gqq9ggqgqq = (ConnectivityManager) context.getSystemService("connectivity");
            this.gqgqgqq9gq9q9q9 = (SensorManager) context.getSystemService("sensor");
            this.qgg99qqg9gq = (LocationManager) context.getSystemService("location");
        } catch (Exception unused) {
            CollectorError.addError(CollectorError.TYPE.ERROR_GETSERVICE_NULL, gqg9qq9gqq9q9q("561a764e25442f433e4b7318650f61106b162e533953244e", 45));
        }
    }

    public synchronized int gqg9qq9gqq9q9q() {
        int i;
        i = 0;
        if (this.qgg9qgg9999g9g != null) {
            long currentTimeMillis = System.currentTimeMillis();
            int gqg9qq9gqq9q9q2 = this.qgg9qgg9999g9g.gqg9qq9gqq9q9q(qg999gqqq99qqg.qgg9qgg9999g9g()) | 0 | this.qgg9qgg9999g9g.qgg9qgg9999g9g((gqg9qq9gqq9q9q) qgg99qqg9gq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9)) | this.qgg9qgg9999g9g.q9qq99qg9qqgqg9gqgg9((gqg9qq9gqq9q9q) qqg9qgqqqgqqgg9qqqqq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9)) | this.qgg9qgg9999g9g.qqgg99qg9q(g9q9q9g9.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.q99qq99g(qqgg999.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.gqqqqgggqq9gq9(ggg9gqgq9g9qqg.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.gg999qq9q9g9gq9q(g9qq9gg9g9qqgg99qg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.q9qqgq9qgqg9q(q9qgq99ggqgqqgq9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.q9gqqq99999qq(q9q99gq99gggqg9qqqgg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.qqq9gg9gqq9qgg99q(qggg9q9gg99q9q9gq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.g9q9q9g9));
            this.qgg9qgg9999g9g.ggqqq9q9(ggq9qqqqg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.gggg9gqq9g9gqqgg99(g9qqqggqqq.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.gg99q9gq9qqgg9g(gg99qgggg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.g9q9q9g9(g9qqqggqqg9gg9g9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.q9gqqq99999qq));
            this.qgg9qgg9999g9g.q9q99gq99gggqg9qqqgg(ggqggqq9g.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.g999gqq9ggqgqq(gg999qq9q9g9gq9q.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.g999gqq9ggqgqq, this.g9q9q9g9));
            this.qgg9qgg9999g9g.gqgqgqq9gq9q9q9(q99qgqqqq9qqqqg99gq9q.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.g9q9q9g9));
            this.qgg9qgg9999g9g.qgg99qqg9gq(gqggg9gq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.qq9q9ggg(qgg9q9qqg9qgq9g.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.qgggqg999gg9qqggq(g999gqq9ggqgqq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.g999gqq9ggqgqq));
            this.qgg9qgg9999g9g.qq9gq9g9g99(qg99q9q9g9gq99.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.qqg9qgqqqgqqgg9qqqqq(gqg9gq99999ggg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.gqq9qggqgg9g99(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.q9ggg99qqqq(g9q9qqq99ggq99.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.qq99gqggggq(qgqg9q9999ggg9qq9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.qqq9gg9gqq9qgg99q));
            this.qgg9qgg9999g9g.qq9g9qqqq9qqgg9q9(q9gg99qgq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.g9qqggg99gqq99g9q(gg99q9gq9qqgg9g.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.q9gg9qqqqggqggq(qqgg99qg9q.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.gggqqggggqgqggg99(g99qg99gqg99q.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.g9q99qgqggqq99(qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.qqg9qgqq(q99qgq99.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.gqgqgqq9gq9q9q9, this.q9q99gq99gggqg9qqqgg));
            this.qgg9qgg9999g9g.ggqqqgggqq99ggq99qgg(gggg9gqq9g9gqqgg99.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.qg999gqqq99qqg(q99qq99g.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.qqq9gg9gqq9qgg99q));
            this.qgg9qgg9999g9g.qgg9qgggq99qqg9gqq(qqggggq9g.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.g9gg9qgg9qgg9qg(ggqqqgggqq99ggq99qgg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.gggq99ggqqg(q9gqqq99999qq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.qqgg999(qgqqq9qggg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.qgqq99qg99qqg(gqgq9ggg99qg99gg9gq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.qgqqq9qggg(qqg9qgqq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.g9g9g99(qgg9qgggq99qqg9gqq.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.qgg99qqg9gq));
            this.qgg9qgg9999g9g.g9qq9gg9g9qqgg99qg(g99q9g9q999gqqq.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.g9q9gq9gq9gggggqgg9(qg9qgqg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.g9q9qqq99ggq99(gqqqqgggqq9gq9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.g9q9q9g9));
            this.qgg9qgg9999g9g.qq9gq9qqqgg9q9g9g9q(ggqqq9q9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.gq9q99qg9g9gg9gq99g9(g9q99qgqggqq99.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.gqg99g9q9(g9g9g99q9gq9gqg99qg.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.q9gg9gg9ggq9gqqq9(qgg9qgg9999g9g.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.gggqgg9g9qg99g9(gggq99ggqqg.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.g9qqqggqqq(gggqgg9g9qg99g9.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.ggq9qqqqg(gq9q99qg9g9gg9gq99g9.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.g9g99g99999999(gqg99g9q9.qgg9qgg9999g9g());
            this.qgg9qgg9999g9g.qgg9q9qqg9qgq9g(qq9gq9qqqgg9q9g9g9q.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.gqgq9ggg99qg99gg9gq(g9gg9qgg9qgg9qg.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9, this.qgg99qqg9gq));
            this.qgg9qgg9999g9g.g9qqqggqqg9gg9g9(q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.ggqggqq9g(g9q9gq9gq9gggggqgg9.gqg9qq9gqq9q9q(this.q9qq99qg9qqgqg9gqgg9));
            this.qgg9qgg9999g9g.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q(currentTimeMillis, System.currentTimeMillis()));
            this.qgg9qgg9999g9g.q9qgq99ggqgqqgq9(q9gg9gg9ggq9gqqq9.qgg9qgg9999g9g());
            i = gqg9qq9gqq9q9q2 | 233;
        }
        return i;
    }

    private gqgqgqq9gq9q9q9 gqg9qq9gqq9q9q(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(gqg9qq9gqq9q9q("5c7d657a5f475b4f", 68), j2 - j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gqgqgqq9gq9q9q9 gqgqgqq9gq9q9q92 = new gqgqgqq9gq9q9q9();
        gqgqgqq9gq9q9q92.gqg9qq9gqq9q9q = jSONObject.toString();
        gqgqgqq9gq9q9q92.q9qq99qg9qqgqg9gqgg9 = true;
        return gqgqgqq9gq9q9q92;
    }

    public byte[] qgg9qgg9999g9g() {
        qgg99qqg9gq.q9qq99qg9qqgqg9gqgg9();
        try {
            return gqg9qq9gqq9q9q(this.qgg9qgg9999g9g, q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q, Integer.MAX_VALUE);
        } catch (Throwable th) {
            CollectorError.addError(CollectorError.TYPE.ERROR_TRANSFORM, CollectorError.catchErr(th));
            return null;
        }
    }

    public String gqg9qq9gqq9q9q(int i) {
        qgg99qqg9gq.q9qq99qg9qqgqg9gqgg9();
        try {
            byte[] gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q(this.qgg9qgg9999g9g, q9qq99qg9qqgqg9gqgg9.qgg9qgg9999g9g, i);
            if (gqg9qq9gqq9q9q2 == null) {
                return "";
            }
            return new String(gqg9qq9gqq9q9q2, gqg9qq9gqq9q9q("4417675c72", 35));
        } catch (Throwable th) {
            CollectorError.addError(CollectorError.TYPE.ERROR_TRANSFORM, CollectorError.catchErr(th));
            return null;
        }
    }

    private byte[] gqg9qq9gqq9q9q(g999gqq9ggqgqq g999gqq9ggqgqq2, String str, int i) throws Throwable {
        byte[] bArr;
        O0o0o0o0o.Oo0 = q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q(g999gqq9ggqgqq2, str, i);
        try {
            bArr = LogUtil.Logdata();
        } catch (Throwable unused) {
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        if (str.equals(q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q)) {
            return bArr;
        }
        return Base64.encode(bArr, 0);
    }

    static String q9qq99qg9qqgqg9gqgg9() {
        return q9ggg99qqqq.gqg9qq9gqq9q9q();
    }

    static String qgg9qgg9999g9g(Context context) {
        return gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context);
    }

    static String q9qq99qg9qqgqg9gqgg9(Context context) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context);
    }

    static String q9gqqq99999qq() {
        return q9ggg99qqqq.qgg9qgg9999g9g();
    }

    static String gqg9qq9gqq9q9q(Context context, TelephonyManager telephonyManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, telephonyManager);
    }

    static String gqg9qq9gqq9q9q(Context context, WifiManager wifiManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, wifiManager);
    }

    static String q9gqqq99999qq(Context context) {
        return q9ggg99qqqq.qgg9qgg9999g9g(context);
    }

    static String gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager, WifiManager wifiManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, connectivityManager, wifiManager);
    }

    static qgggqg999gg9qqggq qgg9qgg9999g9g(Context context, WifiManager wifiManager) {
        return q9ggg99qqqq.qgg9qgg9999g9g(context, wifiManager);
    }

    static String g9q9q9g9(Context context) {
        return q9ggg99qqqq.q9qq99qg9qqgqg9gqgg9(context);
    }

    static String qqq9gg9gqq9qgg99q(Context context) {
        return q9ggg99qqqq.q9gqqq99999qq(context);
    }

    static String gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, connectivityManager);
    }

    static String g9q9q9g9() {
        return q9ggg99qqqq.q9qq99qg9qqgqg9gqgg9();
    }

    static Boolean qqq9gg9gqq9qgg99q() {
        return Boolean.valueOf(q9ggg99qqqq.q9gqqq99999qq());
    }

    static String q9q99gq99gggqg9qqqgg(Context context) {
        return q9ggg99qqqq.g9q9q9g9(context);
    }

    static String g999gqq9ggqgqq(Context context) {
        return q9ggg99qqqq.qqq9gg9gqq9qgg99q(context);
    }

    static Map gqgqgqq9gq9q9q9(Context context) {
        return cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.g999gqq9ggqgqq.gqg9qq9gqq9q9q(context);
    }

    static String gqg9qq9gqq9q9q(Context context, ActivityManager activityManager) {
        return new StringBuilder(cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.g999gqq9ggqgqq.gqg9qq9gqq9q9q(context, activityManager)).toString();
    }

    static int qgg99qqg9gq(Context context) {
        return q9ggg99qqqq.q9q99gq99gggqg9qqqgg(context);
    }

    static String q9q99gq99gggqg9qqqgg() {
        return q9ggg99qqqq.g9q9q9g9();
    }

    static String g999gqq9ggqgqq() {
        return q9ggg99qqqq.qqq9gg9gqq9qgg99q();
    }

    static String gqgqgqq9gq9q9q9() throws Throwable {
        return q9ggg99qqqq.qgg99qqg9gq();
    }

    static String qgg99qqg9gq() {
        return q9ggg99qqqq.g999gqq9ggqgqq();
    }

    static String qq9q9ggg() {
        return q9ggg99qqqq.gqgqgqq9gq9q9q9();
    }

    static String qq9q9ggg(Context context) {
        return q9ggg99qqqq.g999gqq9ggqgqq(context);
    }

    static Integer qgggqg999gg9qqggq() {
        return q9ggg99qqqq.qq9q9ggg();
    }

    static String qgggqg999gg9qqggq(Context context) {
        return q9ggg99qqqq.gqgqgqq9gq9q9q9(context);
    }

    static String qq9gq9g9g99(Context context) {
        return q9ggg99qqqq.qgg99qqg9gq(context);
    }

    static String gqg9qq9gqq9q9q(Context context, SensorManager sensorManager, WindowManager windowManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, sensorManager, windowManager);
    }

    static String qq9gq9g9g99() {
        return q9ggg99qqqq.qgggqg999gg9qqggq();
    }

    static String qgg9qgg9999g9g(Context context, ActivityManager activityManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, activityManager);
    }

    static String qqg9qgqqqgqqgg9qqqqq() {
        return q9ggg99qqqq.qq9gq9g9g99();
    }

    static String gqg9qq9gqq9q9q(Context context, LocationManager locationManager) {
        return q9ggg99qqqq.gqg9qq9gqq9q9q(context, locationManager);
    }

    static String qqg9qgqqqgqqgg9qqqqq(Context context) {
        return q9ggg99qqqq.qgggqg999gg9qqggq(context);
    }

    static String gqqqqgggqq9gq9() {
        return q9ggg99qqqq.qqg9qgqqqgqqgg9qqqqq();
    }

    static Boolean gqqqqgggqq9gq9(Context context) {
        return q9ggg99qqqq.qq9gq9g9g99(context);
    }

    static String gqq9qggqgg9g99() {
        return q9qgqg9.qgg9qgg9999g9g();
    }

    static String q9ggg99qqqq() {
        return q9qgqg9.g9q9q9g9();
    }

    static String qq99gqggggq() {
        return q9qgqg9.q9qq99qg9qqgqg9gqgg9();
    }

    static String q9qqgq9qgqg9q() {
        return q9qgqg9.q9gqqq99999qq();
    }

    static String g9qqggg99gqq99g9q() {
        return q9ggg99qqqq.q9q99gq99gggqg9qqqgg();
    }

    static Long q9gg9qqqqggqggq() {
        long currentTimeMillis = System.currentTimeMillis() - cn.tongdun.android.core.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;
        if (currentTimeMillis < 0) {
            currentTimeMillis = 1;
        }
        return Long.valueOf(currentTimeMillis);
    }

    static String ggqqq9q9() {
        return q9ggg99qqqq.gqqqqgggqq9gq9();
    }

    static String gggg9gqq9g9gqqgg99() {
        return q9ggg99qqqq.gqq9qggqgg9g99();
    }

    static String gg999qq9q9g9gq9q() {
        return q9ggg99qqqq.q9ggg99qqqq();
    }

    static String gqq9qggqgg9g99(Context context) {
        return q9ggg99qqqq.qqg9qgqqqgqqgg9qqqqq(context);
    }

    static String q9ggg99qqqq(Context context) {
        return q9ggg99qqqq.gqqqqgggqq9gq9(context);
    }

    static String qgqqq9qggg() {
        return q9ggg99qqqq.qq99gqggggq();
    }

    static Integer q9qq99qg9qqgqg9gqgg9(Context context, WifiManager wifiManager) {
        return q9ggg99qqqq.q9qq99qg9qqgqg9gqgg9(context, wifiManager);
    }

    static String qq99gqggggq(Context context) {
        return q9ggg99qqqq.q9ggg99qqqq(context);
    }

    static String q9qqgq9qgqg9q(Context context) {
        return q9ggg99qqqq.qq9q9ggg(context);
    }

    static String g9qqggg99gqq99g9q(Context context) {
        return q9ggg99qqqq.qq99gqggggq(context);
    }

    static Integer qgg9qgg9999g9g(Context context, LocationManager locationManager) {
        return q9ggg99qqqq.qgg9qgg9999g9g(context, locationManager);
    }

    public void gqg9qq9gqq9q9q(String str) {
        if (this.qgg9qgg9999g9g != null) {
            this.qgg9qgg9999g9g.gg99qgggg(qgqq99qg99qqg.gqg9qq9gqq9q9q(str));
        }
    }

    static String q9gg9qqqqggqggq(Context context) {
        return q9ggg99qqqq.q9qqgq9qgqg9q(context);
    }

    static String ggqqq9q9(Context context) {
        return q9ggg99qqqq.gqq9qggqgg9g99(context);
    }

    static String gggg9gqq9g9gqqgg99(Context context) {
        return q9ggg99qqqq.g9qqggg99gqq99g9q(context);
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 53);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 49);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
