package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.q9gqqq99999qq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgggqg999gg9qqggq;
import cn.tongdun.android.shell.common.HelperJNI;
import cn.tongdun.android.shell.utils.BoxUtil;
import com.mi.util.permission.Permission;
import java.io.File;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class g9g9g99 {
    static String qgg9qgg9999g9g(Context context) {
        return "";
    }

    @TargetApi(9)
    static String gqg9qq9gqq9q9q(String str) {
        try {
            NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(InetAddress.getByName(str));
            if (byInetAddress != null) {
                return qgggqg999gg9qqggq.gqg9qq9gqq9q9q(byInetAddress.getHardwareAddress());
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    static String gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager, WifiManager wifiManager) {
        String str = "";
        if (gqgqgqq9gq9q9q9.qgg9qgg9999g9g(21) && gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return str;
            }
            for (Network network : connectivityManager.getAllNetworks()) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.getType() == activeNetworkInfo.getType()) {
                    for (InetAddress next : connectivityManager.getLinkProperties(network).getDnsServers()) {
                        if (str.length() > 0) {
                            str = str + gqg9qq9gqq9q9q("46", 100);
                        }
                        str = str + next.getHostAddress();
                    }
                }
            }
            return str;
        } else if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_WIFI_STATE") || !wifiManager.isWifiEnabled()) {
            return (str + q9gqqq99999qq.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("0d1a761e74036b53255834027e08634a", 68))) + gqg9qq9gqq9q9q("46", 29) + q9gqqq99999qq.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("0d1e761a74076b57255c34067e0c634d", 64));
        } else {
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            return (str + qgggqg999gg9qqggq.gqg9qq9gqq9q9q(dhcpInfo.dns1)) + gqg9qq9gqq9q9q("46", 103) + qgggqg999gg9qqggq.gqg9qq9gqq9q9q(dhcpInfo.dns2);
        }
    }

    static Map gqg9qq9gqq9q9q(Context context, String... strArr) {
        HashMap hashMap = new HashMap();
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, Permission.y)) {
            return hashMap;
        }
        ArrayList arrayList = new ArrayList();
        String str = "";
        String str2 = "";
        try {
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                String name = t.getName();
                arrayList.add(name);
                for (T t2 : Collections.list(t.getInetAddresses())) {
                    if (!t2.isLoopbackAddress()) {
                        str = t2.getHostAddress();
                        if (qgggqg999gg9qqggq.qgg9qgg9999g9g(str)) {
                            str2 = str2 + str + gqg9qq9gqq9q9q("46", 99);
                        }
                    }
                }
                if (q9ggg99qqqq.gqg9qq9gqq9q9q.equals(name)) {
                    hashMap.put(q9ggg99qqqq.g9q9q9g9, gqg9qq9gqq9q9q((NetworkInterface) t));
                    hashMap.put(q9ggg99qqqq.gqg9qq9gqq9q9q, str);
                } else if (q9ggg99qqqq.qgg9qgg9999g9g.equals(name)) {
                    hashMap.put(q9ggg99qqqq.qqq9gg9gqq9qgg99q, gqg9qq9gqq9q9q((NetworkInterface) t));
                    hashMap.put(q9ggg99qqqq.qgg9qgg9999g9g, str);
                }
            }
            if (q9ggg99qqqq.q9gqqq99999qq.equals(strArr[0])) {
                hashMap.put(q9ggg99qqqq.q9gqqq99999qq, qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str2) ? "" : str2.substring(0, str2.length() - 1));
                return hashMap;
            } else if (!q9ggg99qqqq.q9qq99qg9qqgqg9gqgg9.equals(strArr[0])) {
                return hashMap;
            } else {
                String obj = arrayList.toString();
                hashMap.put(q9ggg99qqqq.q9qq99qg9qqgqg9gqgg9, obj.substring(1, obj.length() - 1));
                return hashMap;
            }
        } catch (SocketException unused) {
            return hashMap;
        }
    }

    @TargetApi(9)
    private static String gqg9qq9gqq9q9q(NetworkInterface networkInterface) {
        String str = "";
        for (InterfaceAddress next : networkInterface.getInterfaceAddresses()) {
            if (qgggqg999gg9qqggq.qgg9qgg9999g9g(next.getAddress().getHostAddress())) {
                short networkPrefixLength = next.getNetworkPrefixLength();
                str = str + qgggqg999gg9qqggq.qgg9qgg9999g9g((int) networkPrefixLength) + gqg9qq9gqq9q9q("46", 43);
            }
        }
        return str.length() > 1 ? str.substring(0, str.length() - 1) : str;
    }

    static String gqg9qq9gqq9q9q(Context context) {
        try {
            return HelperJNI.simplemd5(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a A[SYNTHETIC, Splitter:B:17:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String q9qq99qg9qqgqg9gqgg9(android.content.Context r6) {
        /*
            java.lang.System.currentTimeMillis()
            java.lang.String r6 = r6.getPackageResourcePath()
            r0 = 256(0x100, float:3.59E-43)
            r1 = 0
            byte[] r2 = new byte[r0]     // Catch:{ Exception -> 0x0037 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0037 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0037 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x0037 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0037 }
            int r6 = r3.available()     // Catch:{ Exception -> 0x0038 }
            if (r6 >= r0) goto L_0x001d
            r0 = r6
        L_0x001d:
            int r6 = r6 - r0
            long r4 = (long) r6     // Catch:{ Exception -> 0x0038 }
            r3.skip(r4)     // Catch:{ Exception -> 0x0038 }
            r6 = 0
            int r6 = r3.read(r2, r6, r0)     // Catch:{ Exception -> 0x0038 }
            if (r0 != r6) goto L_0x0031
            java.lang.String r6 = cn.tongdun.android.shell.common.HelperJNI.simplemd5(r2)     // Catch:{ Exception -> 0x0038 }
            r3.close()     // Catch:{ Exception -> 0x0038 }
            return r6
        L_0x0031:
            r3.close()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r6 = ""
            return r6
        L_0x0037:
            r3 = r1
        L_0x0038:
            if (r3 == 0) goto L_0x003d
            r3.close()     // Catch:{ Exception -> 0x003d }
        L_0x003d:
            java.lang.String r6 = ""
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.qgg9qgg9999g9g.g9g9g99.q9qq99qg9qqgqg9gqgg9(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int q9gqqq99999qq(android.content.Context r4) {
        /*
            java.lang.String r0 = "0e572f0b3206265e695163477e41730b25033a1f2c1e6659614466516b516246"
            r1 = 10
            java.lang.String r0 = gqg9qq9gqq9q9q((java.lang.String) r0, (int) r1)
            java.lang.String r1 = "096661253c3728303332766f7078617f676c727d"
            r2 = 54
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r2)
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r2 = 0
            android.content.pm.PackageInfo r3 = r4.getPackageInfo(r0, r2)     // Catch:{ NameNotFoundException -> 0x0023 }
            java.lang.String r3 = r3.packageName     // Catch:{ NameNotFoundException -> 0x0023 }
            boolean r0 = r0.equals(r3)     // Catch:{ NameNotFoundException -> 0x0023 }
            if (r0 == 0) goto L_0x0023
            r0 = 1
            goto L_0x0024
        L_0x0023:
            r0 = 0
        L_0x0024:
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r1, r2)     // Catch:{ NameNotFoundException -> 0x0033 }
            java.lang.String r4 = r4.packageName     // Catch:{ NameNotFoundException -> 0x0033 }
            boolean r4 = r1.equals(r4)     // Catch:{ NameNotFoundException -> 0x0033 }
            if (r4 == 0) goto L_0x0033
            r4 = r0 | 256(0x100, float:3.59E-43)
            r0 = r4
        L_0x0033:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.qgg9qgg9999g9g.g9g9g99.q9gqqq99999qq(android.content.Context):int");
    }

    static int gqg9qq9gqq9q9q() {
        try {
            throw new Exception(gqg9qq9gqq9q9q("0e4a755b734c2704200424", 23));
        } catch (Exception e) {
            int i = 0;
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (gqg9qq9gqq9q9q("0e562f0a3207265f695063467e40730a25023a1e2c1f66694e7652605346635d6e5e6c", 11).equals(stackTraceElement.getClassName()) && gqg9qq9gqq9q9q("071c651b", 76).equals(stackTraceElement.getMethodName())) {
                    i |= 2;
                }
                if (gqg9qq9gqq9q9q("0e262f7a3277262f692063367e30737a25723a6e2c6f66194e0652105336632d6e2e6c", 123).equals(stackTraceElement.getClassName()) && gqg9qq9gqq9q9q("0215671f6f16423142354c34651c7400730b", 64).equals(stackTraceElement.getMethodName())) {
                    i |= 4;
                }
                if (gqg9qq9gqq9q9q("0948610b2e0424123914345e735969487e54715933182f455b66456e5e7f72587545", 24).equals(stackTraceElement.getClassName()) && (i2 = i2 + 1) == 2) {
                    i |= 512;
                }
                if (gqg9qq9gqq9q9q("090261413c5328543356760b701c611b67087219397a270d31", 82).equals(stackTraceElement.getClassName()) && gqg9qq9gqq9q9q("037b7162756c74", 32).equals(stackTraceElement.getMethodName())) {
                    i |= 1024;
                }
            }
            return i;
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int qgg9qgg9999g9g() {
        /*
            r0 = 0
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r1.<init>()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r2.<init>()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r3 = "456d2d70213c"
            r4 = 110(0x6e, float:1.54E-43)
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r2.append(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            int r3 = android.os.Process.myPid()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r2.append(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r3 = "450b231a20"
            r4 = 21
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r2.append(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r5.<init>(r2)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r2 = "1f65672e72"
            r6 = 56
            java.lang.String r2 = gqg9qq9gqq9q9q((java.lang.String) r2, (int) r6)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r4.<init>(r5, r2)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
        L_0x0043:
            java.lang.String r2 = r3.readLine()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            if (r2 == 0) goto L_0x007b
            java.lang.String r4 = "444d32"
            r5 = 76
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            boolean r4 = r2.endsWith(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            if (r4 != 0) goto L_0x0065
            java.lang.String r4 = "44532540"
            r5 = 75
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            boolean r4 = r2.endsWith(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            if (r4 == 0) goto L_0x0043
        L_0x0065:
            java.lang.String r4 = "4a"
            r5 = 115(0x73, float:1.61E-43)
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            int r4 = r2.lastIndexOf(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            int r4 = r4 + 1
            java.lang.String r2 = r2.substring(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            r1.add(r2)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            goto L_0x0043
        L_0x007b:
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
        L_0x007f:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            if (r2 == 0) goto L_0x00af
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r4 = r2.toLowerCase()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            java.lang.String r5 = "1276676a716b777b6c766f74"
            r6 = 34
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r6)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            boolean r4 = r4.contains(r5)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            if (r4 == 0) goto L_0x009f
            r0 = r0 | 8
        L_0x009f:
            java.lang.String r4 = "095261113c0328043306765b704c614b67587249"
            r5 = 2
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            boolean r2 = r2.contains(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            if (r2 == 0) goto L_0x007f
            r0 = r0 | 2048(0x800, float:2.87E-42)
            goto L_0x007f
        L_0x00af:
            r3.close()     // Catch:{ Exception -> 0x00b5, all -> 0x00b3 }
            goto L_0x00b5
        L_0x00b3:
            r0 = move-exception
            throw r0
        L_0x00b5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.qgg9qgg9999g9g.g9g9g99.qgg9qgg9999g9g():int");
    }

    static int q9qq99qg9qqgqg9gqgg9() {
        return BoxUtil.box3(2333);
    }

    static String q9gqqq99999qq() {
        StringBuilder sb = new StringBuilder();
        File file = new File(gqg9qq9gqq9q9q("4503250922182a5a635362496515", 3));
        String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("443a2e28", 60);
        String[] list = file.list();
        if (list == null) {
            return sb.toString();
        }
        for (String str : list) {
            if (str != null && str.endsWith(gqg9qq9gqq9q9q)) {
                String str2 = new String(str);
                if (sb.length() > 0) {
                    sb.append(gqg9qq9gqq9q9q("46", 4));
                }
                sb.append(str2.substring(0, str2.length() - 4));
            }
        }
        return HelperJNI.simplemd5(sb.toString().getBytes());
    }

    static String g9q9q9g9() {
        try {
            String[] split = HelperJNI.getBaseBand().split(gqg9qq9gqq9q9q("46", 27));
            if (split.length == 1) {
                return split[0];
            }
            if (split.length < 2) {
                return "";
            }
            if (split[0].equals(split[1])) {
                return split[0];
            }
            return split[0] + gqg9qq9gqq9q9q("46", 36) + split[1];
        } catch (Exception unused) {
            return "";
        }
    }

    static String g9q9q9g9(Context context) {
        BluetoothAdapter defaultAdapter;
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.BLUETOOTH") || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || !defaultAdapter.isEnabled()) {
            return "";
        }
        return defaultAdapter.getAddress();
    }

    public static String qqq9gg9gqq9qgg99q(Context context) {
        try {
            String packageName = context.getPackageName();
            String str = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
            return packageName + gqg9qq9gqq9q9q("4009", 89) + str;
        } catch (Exception unused) {
            return null;
        }
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
            byte b = (byte) (i ^ 92);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.PAY_ORDER_ID);
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
