package cn.tongdun.android.core.q9gqqq99999qq;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Display;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.el.parse.Operators;
import com.unionpay.tsmservice.data.AppStatus;
import java.io.File;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

public class qgg99qqg9gq {
    public static String g9q9q9g9 = "";
    public static final List qqq9gg9gqq9qgg99q = new ArrayList();
    public int gqg9qq9gqq9q9q;
    public Class[] q9gqqq99999qq;
    private Map q9q99gq99gggqg9qqqgg = new HashMap();
    public String q9qq99qg9qqgqg9gqgg9;
    public String qgg9qgg9999g9g;

    public qgg99qqg9gq(int i, String str, String str2, Class[] clsArr) {
        this.gqg9qq9gqq9q9q = i;
        this.qgg9qgg9999g9g = str;
        this.q9qq99qg9qqgqg9gqgg9 = str2;
        this.q9gqqq99999qq = clsArr;
    }

    public qgg99qqg9gq() {
    }

    static {
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(0, Settings.System.class.getName(), qgg9qgg9999g9g("4c39761e51184a1f43", 14), new Class[]{ContentResolver.class, String.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(1, SharedPreferences.Editor.class.getName(), qgg9qgg9999g9g("5b66714156474d4044", 86), new Class[]{String.class, String.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(3, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c4d766a507d416d50765b714c4a61", 122), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(4, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c217619531e584a27713f7e3869", 22), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(5, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c2676165705480f4e2363", 17), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(6, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c4b76694f6f45696d456540477b5f745863", 124), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(7, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c5c767b4c7f724965526d5f4f64576b507c", 107), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(8, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c7d76475d565e4e43576b7b71606b6660565a4a", 74), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(9, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c46767c5d6d5e75436c6753724461517a4c46634a6b", 113), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(10, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c0076274c236e1c7b0b681e73034f2c4324", 55), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(11, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c4076644e634f687e457750", 119), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(12, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c05763f5d2e5e36432f7c027517", 50), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(13, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c2f761850115031733d71286c2e6d", 24), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(14, TelephonyManager.class.getName(), qgg9qgg9999g9g("4c1f762f573c48364e007209600a7619612a523d53275526", 40), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(15, WifiInfo.class.getName(), qgg9qgg9999g9g("4c36760f5a0d7828783e6f286f", 1), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(16, WifiInfo.class.getName(), qgg9qgg9999g9g("4c67765a4f6b6a6b7c7c6a7c", 80), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(17, WifiInfo.class.getName(), qgg9qgg9999g9g("4c75765276487b", 66), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(18, WifiInfo.class.getName(), qgg9qgg9999g9g("4c4b767d677d7d70", 124), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(19, WifiManager.class.getName(), qgg9qgg9999g9g("4c68765f5a5e5a555c4241444063676b6e", 95), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(20, WifiManager.class.getName(), qgg9qgg9999g9g("4c0676365a3d49046e0c67", 49), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(21, WifiManager.class.getName(), qgg9qgg9999g9g("4c4c766b466949557e43785a605d", 123), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(22, NetworkInterface.class.getName(), qgg9qgg9999g9g("4c4b76715d605e78436161467b576c436b416d57", 124), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(23, Proxy.class.getName(), qgg9qgg9999g9g("4c0a7636512a56", 61), new Class[]{Context.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(24, Proxy.class.getName(), qgg9qgg9999g9g("4c3a761e49034f", 13), new Class[]{Context.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(25, System.class.getName(), qgg9qgg9999g9g("4c267602541f4b0a5c0c51", 17), new Class[]{String.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(26, PackageManager.class.getName(), qgg9qgg9999g9g("4c47767a516756725b7252657054725c785a7a71557d5d", 112), new Class[]{String.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(27, PackageManager.class.getName(), qgg9qgg9999g9g("4c12763647344f3e493c651b6d12", 37), new Class[]{String.class, Integer.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(28, PackageManager.class.getName(), qgg9qgg9999g9g("4c0f7632512f563a5b3a523b660a64026e046c12", 56), new Class[]{Integer.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(29, File.class.getName(), qgg9qgg9999g9g("4c417674556549665067415270476c", 118), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(30, ActivityManager.class.getName(), qgg9qgg9999g9g("4c2a760c511751105619652c77346f", 29), new Class[]{Integer.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(31, ComponentName.class.getName(), qgg9qgg9999g9g("4c0e762a47284f224920620f6e07", 57), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(32, Modifier.class.getName(), qgg9qgg9999g9g("42295406411b5e08", 6), new Class[]{Integer.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(33, Debug.class.getName(), qgg9qgg9999g9g("42625e4359544b544943786f796f7269657864", 77), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(34, Process.class.getName(), qgg9qgg9999g9g("4675444c49", 84), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(35, TimeZone.class.getName(), qgg9qgg9999g9g("4c37761145077d2e7d3b6b2a", 0), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(36, TimeZone.class.getName(), qgg9qgg9999g9g("4c3a760a610d663f7120762962", 13), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(37, Locale.class.getName(), qgg9qgg9999g9g("4c0876305b3f522d462b44", 63), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(38, Intent.class.getName(), qgg9qgg9999g9g("4c387605511f40145a", 15), new Class[]{String.class}));
        if (Build.VERSION.SDK_INT < 28 && !Build.VERSION.RELEASE.equals(qgg9qgg9999g9g("7b", 107))) {
            qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(39, Intent.class.getName(), qgg9qgg9999g9g("4c6876594b554d46", 95), new Class[]{String.class}));
            qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(2, qgg9qgg9999g9g("4a5f6b49764f7b053a1967644d6e4a7f4242605f7f4a684c754063", 101), qgg9qgg9999g9g("4c5e76", 105), new Class[]{String.class, String.class}));
            qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(63, qgg9qgg9999g9g("4a606b7676707b3a3a26675b4d514a40427d60607f756873757f63", 90), qgg9qgg9999g9g("4c4976", 126), new Class[]{String.class}));
        }
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(40, Intent.class.getName(), qgg9qgg9999g9g("4c5d766b5b6b58625c6d77507b5668", 106), new Class[]{String.class, Boolean.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(41, Intent.class.getName(), qgg9qgg9999g9g("4c3676004d0d5c2d61216732", 1), new Class[]{String.class, Byte.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(42, Intent.class.getName(), qgg9qgg9999g9g("4c7d765a4d5d505b61666d607e", 74), new Class[]{String.class, Short.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(43, Intent.class.getName(), qgg9qgg9999g9g("4c2f76185d114e26732a7539", 24), new Class[]{String.class, Character.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(44, Intent.class.getName(), qgg9qgg9999g9g("4c1a7627513d60006c067f", 45), new Class[]{String.class, Integer.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(45, Intent.class.getName(), qgg9qgg9999g9g("4c61765955585c7a61766765", 86), new Class[]{String.class, Long.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(46, Intent.class.getName(), qgg9qgg9999g9g("4c58766a5c69527c63416f477c", 111), new Class[]{String.class, Float.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(47, Intent.class.getName(), qgg9qgg9999g9g("4c5a766a5d704a7e435e7e527841", 109), new Class[]{String.class, Double.TYPE}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(48, Intent.class.getName(), qgg9qgg9999g9g("4c37761051164a1143337e3f782c", 0), new Class[]{String.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(49, Display.class.getName(), qgg9qgg9999g9g("4c237600480d5811", 20), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(50, Display.class.getName(), qgg9qgg9999g9g("4c5c76605b6c556349", 107), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(51, BluetoothAdapter.class.getName(), qgg9qgg9999g9g("4c277612531245055305", 16), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(52, Settings.Secure.class.getName(), qgg9qgg9999g9g("4c7a765d515b4a5c43", 77), new Class[]{ContentResolver.class, String.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(53, ActivityManager.class.getName(), qgg9qgg9999g9g("4c0f76365e3e5c235713701b79", 56), new Class[]{ActivityManager.MemoryInfo.class}));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(54, StatFs.class.getName(), qgg9qgg9999g9g("4c2d761b581854106c2a7f35", 26), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(55, StatFs.class.getName(), qgg9qgg9999g9g("4c6076565855545d6c677f78565b5752", 87), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(56, StatFs.class.getName(), qgg9qgg9999g9g("4c0176375834543c7c10660b7c", 54), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(57, StatFs.class.getName(), qgg9qgg9999g9g("4c5276645867546f7c4366587c605f6156", 101), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(58, StatFs.class.getName(), qgg9qgg9999g9g("4c4a767f4168496d446e4a676d496e45665d", 125), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(59, StatFs.class.getName(), qgg9qgg9999g9g("4c2776124105490044034a0a6d246e2866305913581a", 16), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(60, Location.class.getName(), qgg9qgg9999g9g("4c6876505b454658474946", 95), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(61, Location.class.getName(), qgg9qgg9999g9g("4c55766d556c5c6241635062", 98), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(62, InetAddress.class.getName(), qgg9qgg9999g9g("425556765669446a46626c476c517b477b", 122), new Class[0]));
        qqq9gg9gqq9qgg99q.add(new qgg99qqg9gq(64, LocationManager.class.getName(), qgg9qgg9999g9g("4c5976615b735c4c794d615443774f755a685c69", 110), new Class[]{String.class}));
    }

    public void gqg9qq9gqq9q9q() {
        this.q9q99gq99gggqg9qqqgg.clear();
    }

    public void gqg9qq9gqq9q9q(String str, int i) {
        if (this.q9q99gq99gggqg9qqqgg.containsKey(str)) {
            ((List) this.q9q99gq99gggqg9qqqgg.get(str)).add(Integer.valueOf(i));
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        this.q9q99gq99gggqg9qqqgg.put(str, arrayList);
    }

    public String toString() {
        return new JSONObject(this.q9q99gq99gggqg9qqqgg).toString();
    }

    public static int gqg9qq9gqq9q9q(String str) {
        for (qgg99qqg9gq qgg99qqg9gq : qqq9gg9gqq9qgg99q) {
            if (str.contains(qgg99qqg9gq.qgg9qgg9999g9g) && str.contains(qgg99qqg9gq.q9qq99qg9qqgqg9gqgg9) && str.contains(gqg9qq9gqq9q9q(qgg99qqg9gq.q9gqqq99999qq))) {
                return qgg99qqg9gq.gqg9qq9gqq9q9q;
            }
        }
        return -1;
    }

    public static void qgg9qgg9999g9g() {
        try {
            for (qgg99qqg9gq qgg99qqg9gq : qqq9gg9gqq9qgg99q) {
                if ((Class.forName(qgg99qqg9gq.qgg9qgg9999g9g).getDeclaredMethod(qgg99qqg9gq.q9qq99qg9qqgqg9gqgg9, qgg99qqg9gq.q9gqqq99999qq).getModifiers() & 256) != 0 && !qgg9qgg9999g9g("461d442449", 60).equals(qgg99qqg9gq.q9qq99qg9qqgqg9gqgg9)) {
                    g9q9q9g9 += qgg99qqg9gq.q9qq99qg9qqgqg9gqgg9 + Operators.CONDITION_IF_MIDDLE;
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void q9qq99qg9qqgqg9gqgg9() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace != null) {
            for (int i = 0; i < stackTrace.length - 1; i++) {
                String methodName = stackTrace[i].getMethodName();
                String className = stackTrace[i].getClassName();
                if (methodName != null) {
                    int i2 = i + 1;
                    if (methodName.equals(stackTrace[i2].getMethodName()) && className.equals(stackTrace[i2].getClassName()) && stackTrace[i2].isNativeMethod() && !className.contains(qgg9qgg9999g9g("534867547155", 117))) {
                        g9q9q9g9 += qgg9qgg9999g9g("74", 79) + className + qgg9qgg9999g9g(AppStatus.OPEN, 20) + methodName;
                    }
                }
            }
        }
    }

    private static String gqg9qq9gqq9q9q(Class[] clsArr) {
        StringBuilder sb = new StringBuilder();
        for (Class cls : clsArr) {
            if (sb.length() > 0) {
                sb.append(qgg9qgg9999g9g(AppStatus.VIEW, 11));
            }
            sb.append(cls.getName());
        }
        return sb.toString();
    }

    public static String qgg9qgg9999g9g(String str, int i) {
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
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.USER_LOCK_TIME);
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
