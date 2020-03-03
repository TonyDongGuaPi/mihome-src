package com.xiaomi.passport.ui.diagnosis;

import android.support.v4.util.ArrayMap;
import com.taobao.weex.el.parse.Operators;
import java.net.URI;
import java.util.regex.Pattern;

public class PingUtil {
    private static final String ipRegex = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

    public static String getIPFromUrl(String str) {
        String domain = getDomain(str);
        if (domain == null) {
            return null;
        }
        if (isMatch(ipRegex, domain)) {
            return domain;
        }
        String ping = ping(createSimplePingCommand(1, 100, domain));
        if (ping != null) {
            try {
                String substring = ping.substring(ping.indexOf("from") + 5);
                return substring.substring(0, substring.indexOf("icmp_seq") - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getMinRTT(String str) {
        return getMinRTT(str, 1, 100);
    }

    public static int getAvgRTT(String str) {
        return getAvgRTT(str, 1, 100);
    }

    public static int getMaxRTT(String str) {
        return getMaxRTT(str, 1, 100);
    }

    public static int getMdevRTT(String str) {
        return getMdevRTT(str, 1, 100);
    }

    public static int getMinRTT(String str, int i, int i2) {
        String ping;
        String domain = getDomain(str);
        if (!(domain == null || (ping = ping(createSimplePingCommand(i, i2, domain))) == null)) {
            try {
                return Math.round(Float.valueOf(ping.substring(ping.indexOf("min/avg/max/mdev") + 19).split("/")[0]).floatValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getAvgRTT(String str, int i, int i2) {
        String ping;
        String domain = getDomain(str);
        if (!(domain == null || (ping = ping(createSimplePingCommand(i, i2, domain))) == null)) {
            try {
                return Math.round(Float.valueOf(ping.substring(ping.indexOf("min/avg/max/mdev") + 19).split("/")[1]).floatValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMaxRTT(String str, int i, int i2) {
        String ping;
        String domain = getDomain(str);
        if (!(domain == null || (ping = ping(createSimplePingCommand(i, i2, domain))) == null)) {
            try {
                return Math.round(Float.valueOf(ping.substring(ping.indexOf("min/avg/max/mdev") + 19).split("/")[2]).floatValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMdevRTT(String str, int i, int i2) {
        String ping;
        String domain = getDomain(str);
        if (!(domain == null || (ping = ping(createSimplePingCommand(i, i2, domain))) == null)) {
            try {
                return Math.round(Float.valueOf(ping.substring(ping.indexOf("min/avg/max/mdev") + 19).split("/")[3].replace(" ms", "")).floatValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static float getPacketLossFloat(String str) {
        String packetLoss = getPacketLoss(str);
        if (packetLoss == null) {
            return -1.0f;
        }
        try {
            return Float.valueOf(packetLoss.replace(Operators.MOD, "")).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public static float getPacketLossFloat(String str, int i, int i2) {
        String packetLoss = getPacketLoss(str, i, i2);
        if (packetLoss == null) {
            return -1.0f;
        }
        try {
            return Float.valueOf(packetLoss.replace(Operators.MOD, "")).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public static String getPacketLoss(String str) {
        return getPacketLoss(str, 1, 100);
    }

    public static String getPacketLoss(String str, int i, int i2) {
        String ping;
        String domain = getDomain(str);
        if (!(domain == null || (ping = ping(createSimplePingCommand(i, i2, domain))) == null)) {
            try {
                String substring = ping.substring(ping.indexOf("received,"));
                return substring.substring(9, substring.indexOf("packet"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getPingString(String str, int i, int i2) {
        return ping(createSimplePingCommand(i, i2, str));
    }

    private static String getDomain(String str) {
        try {
            return URI.create(str).getHost();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isMatch(String str, String str2) {
        return Pattern.matches(str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String ping(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0042, all -> 0x003d }
            java.lang.Process r6 = r1.exec(r6)     // Catch:{ IOException -> 0x0042, all -> 0x003d }
            java.io.InputStream r1 = r6.getInputStream()     // Catch:{ IOException -> 0x003b }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003b }
            r3.<init>(r1)     // Catch:{ IOException -> 0x003b }
            r2.<init>(r3)     // Catch:{ IOException -> 0x003b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003b }
            r3.<init>()     // Catch:{ IOException -> 0x003b }
        L_0x001c:
            java.lang.String r4 = r2.readLine()     // Catch:{ IOException -> 0x003b }
            if (r4 == 0) goto L_0x002b
            r3.append(r4)     // Catch:{ IOException -> 0x003b }
            java.lang.String r4 = "\n"
            r3.append(r4)     // Catch:{ IOException -> 0x003b }
            goto L_0x001c
        L_0x002b:
            r2.close()     // Catch:{ IOException -> 0x003b }
            r1.close()     // Catch:{ IOException -> 0x003b }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x003b }
            if (r6 == 0) goto L_0x003a
            r6.destroy()
        L_0x003a:
            return r1
        L_0x003b:
            r1 = move-exception
            goto L_0x0044
        L_0x003d:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x004e
        L_0x0042:
            r1 = move-exception
            r6 = r0
        L_0x0044:
            r1.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r6 == 0) goto L_0x004c
            r6.destroy()
        L_0x004c:
            return r0
        L_0x004d:
            r0 = move-exception
        L_0x004e:
            if (r6 == 0) goto L_0x0053
            r6.destroy()
        L_0x0053:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.diagnosis.PingUtil.ping(java.lang.String):java.lang.String");
    }

    private static String createSimplePingCommand(int i, int i2, String str) {
        return "/system/bin/ping -c " + i + " -w " + i2 + " " + str;
    }

    private static String createPingCommand(ArrayMap<String, String> arrayMap, String str) {
        String str2 = "/system/bin/ping";
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            str2 = str2.concat(" " + arrayMap.keyAt(i) + " " + arrayMap.get(arrayMap.keyAt(i)));
        }
        return str2.concat(" " + str);
    }
}
