package com.xiaomi.jr.http.netopt;

import com.xiaomi.jr.common.utils.MifiLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Ping {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10836a = "MifiPing";
    private static final Pattern b = Pattern.compile("(\\d*\\.)?\\d+");
    private static final String c = "ping statistics";

    Ping() {
    }

    static class PingResult {

        /* renamed from: a  reason: collision with root package name */
        boolean f10838a;
        float b;
        float c;
        float d;
        float e;

        PingResult() {
        }
    }

    static PingResult a(String str) {
        return a(str, 10, 20);
    }

    static PingResult a(String str, int i, int i2) {
        String a2 = a(str, i, i2, 0, c, 1);
        PingResult pingResult = new PingResult();
        if (a2 == null || !a2.startsWith("rtt min/avg/max/mdev")) {
            pingResult.f10838a = false;
        } else {
            pingResult.f10838a = true;
            Matcher matcher = b.matcher(a2);
            try {
                if (matcher.find()) {
                    pingResult.b = Float.parseFloat(matcher.group());
                }
                if (matcher.find()) {
                    pingResult.c = Float.parseFloat(matcher.group());
                }
                if (matcher.find()) {
                    pingResult.d = Float.parseFloat(matcher.group());
                }
                if (matcher.find()) {
                    pingResult.e = Float.parseFloat(matcher.group());
                }
            } catch (NumberFormatException e) {
                MifiLog.b(f10836a, "parse ping result fail: " + e.getMessage());
            }
        }
        return pingResult;
    }

    static String a(String str, int i, int i2, int i3, String str2, int i4) {
        String[] a2 = a(str, i, i2, i3, str2, new int[]{i4});
        if (a2 == null || a2.length != 1) {
            return null;
        }
        return a2[0];
    }

    private static String[] a(String str, int i, int i2, int i3, final String str2, final int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("ping");
        if (i != 0) {
            sb.append(" -c " + i);
        }
        if (i2 != 0) {
            sb.append(" -s " + i2);
        }
        if (i3 != 0) {
            sb.append(" -t " + i3);
        }
        sb.append(" " + str);
        try {
            final Process exec = Runtime.getRuntime().exec(sb.toString());
            final String[] strArr = new String[iArr.length];
            new Thread(new Runnable() {
                public void run() {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    int i = str2 == null ? 0 : -1;
                    int i2 = 0;
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                return;
                            }
                            if (i == -1) {
                                if (str2 != null && readLine.contains(str2)) {
                                    i = 0;
                                }
                            } else if (i2 < iArr.length) {
                                if (i == iArr[i2]) {
                                    strArr[i2] = readLine;
                                    i2++;
                                }
                                i++;
                            } else {
                                return;
                            }
                        } catch (IOException e) {
                            MifiLog.d(Ping.f10836a, "launchPing read stream exception: " + e.getMessage());
                            return;
                        }
                    }
                }
            }).start();
            exec.waitFor();
            exec.destroy();
            return strArr;
        } catch (IOException | InterruptedException e) {
            MifiLog.d(f10836a, "launchPing exception: " + e.getMessage());
            return null;
        }
    }
}
