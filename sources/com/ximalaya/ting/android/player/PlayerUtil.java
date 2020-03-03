package com.ximalaya.ting.android.player;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager;
import com.ximalaya.ting.android.player.model.HttpConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerUtil {

    /* renamed from: a  reason: collision with root package name */
    private static String f2279a = "";

    public static boolean a(Context context) {
        NetworkInfo networkInfo;
        if (context == null) {
            return false;
        }
        try {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            networkInfo = null;
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean a() {
        String property = System.getProperty("os.arch");
        Log.i("os.arch", property + Constant.DEFAULT_CVN2);
        if (property == null || !property.contains("86")) {
            return false;
        }
        Log.i("os.arch", property + Constants.Plugin.PLUGINID_WEBVIEW);
        return true;
    }

    private static String h() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean b() {
        String str;
        if (Build.VERSION.SDK_INT >= 21) {
            str = Arrays.toString(Build.SUPPORTED_ABIS);
        } else {
            str = Build.CPU_ABI + Build.CPU_ABI2;
        }
        Log.i("os.arch", "supportAbi:" + str);
        if (str != null) {
            return str.contains("armeabi-v7a") || str.contains(PluginSoManager.c);
        }
        return false;
    }

    public static boolean c() {
        String lowerCase;
        String h = h();
        if (h == null || (lowerCase = h.toLowerCase()) == null || !lowerCase.contains("neon")) {
            return false;
        }
        return true;
    }

    public static void a(String str, byte[] bArr, int i) {
        try {
            if (str.contains("http")) {
                str = str.substring(str.length() - 10, str.length());
            }
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/ting/" + str);
            file.getParentFile().mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Exception unused) {
        }
    }

    public static boolean d() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String a(long j) {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        StringBuilder sb4;
        String str4;
        StringBuilder sb5;
        String str5;
        StringBuilder sb6;
        String str6;
        StringBuilder sb7;
        String str7;
        StringBuilder sb8;
        String str8;
        if (j >= 3600) {
            int i = (int) (j / 3600);
            int i2 = (int) (j % 3600);
            if (i2 >= 60) {
                int i3 = i2 / 60;
                int i4 = i2 % 60;
                StringBuilder sb9 = new StringBuilder();
                if (i < 10) {
                    sb6 = new StringBuilder();
                    str6 = "0";
                } else {
                    sb6 = new StringBuilder();
                    str6 = "";
                }
                sb6.append(str6);
                sb6.append(i);
                sb9.append(sb6.toString());
                sb9.append(":");
                if (i3 < 10) {
                    sb7 = new StringBuilder();
                    str7 = "0";
                } else {
                    sb7 = new StringBuilder();
                    str7 = "";
                }
                sb7.append(str7);
                sb7.append(i3);
                sb9.append(sb7.toString());
                sb9.append(":");
                if (i4 < 10) {
                    sb8 = new StringBuilder();
                    str8 = "0";
                } else {
                    sb8 = new StringBuilder();
                    str8 = "";
                }
                sb8.append(str8);
                sb8.append(i4);
                sb9.append(sb8.toString());
                return sb9.toString();
            }
            StringBuilder sb10 = new StringBuilder();
            if (i < 10) {
                sb4 = new StringBuilder();
                str4 = "0";
            } else {
                sb4 = new StringBuilder();
                str4 = "";
            }
            sb4.append(str4);
            sb4.append(i);
            sb10.append(sb4.toString());
            sb10.append(":");
            if (i2 < 10) {
                sb5 = new StringBuilder();
                str5 = "00:0";
            } else {
                sb5 = new StringBuilder();
                str5 = "00:";
            }
            sb5.append(str5);
            sb5.append(i2);
            sb10.append(sb5.toString());
            return sb10.toString();
        } else if (j >= 60) {
            int i5 = (int) (j / 60);
            int i6 = (int) (j % 60);
            StringBuilder sb11 = new StringBuilder();
            if (i5 < 10) {
                sb2 = new StringBuilder();
                str2 = "0";
            } else {
                sb2 = new StringBuilder();
                str2 = "";
            }
            sb2.append(str2);
            sb2.append(i5);
            sb11.append(sb2.toString());
            sb11.append(":");
            if (i6 < 10) {
                sb3 = new StringBuilder();
                str3 = "0";
            } else {
                sb3 = new StringBuilder();
                str3 = "";
            }
            sb3.append(str3);
            sb3.append(i6);
            sb11.append(sb3.toString());
            return sb11.toString();
        } else {
            if (j < 10) {
                sb = new StringBuilder();
                str = "00:0";
            } else {
                sb = new StringBuilder();
                str = "00:";
            }
            sb.append(str);
            sb.append((int) j);
            return sb.toString();
        }
    }

    public static boolean a(String str) {
        return str == null || !str.startsWith(ConnectionHelper.HTTP_PREFIX);
    }

    public static HttpURLConnection a(String str, String str2, Map<String, String> map) throws IOException {
        return a(str, true, str2, map);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.HttpURLConnection a(java.lang.String r4, boolean r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7) throws java.io.IOException {
        /*
            java.net.URL r0 = new java.net.URL
            r0.<init>(r4)
            com.ximalaya.ting.android.player.model.HttpConfig r1 = com.ximalaya.ting.android.player.StaticConfig.c
            if (r1 == 0) goto L_0x00b1
            if (r5 == 0) goto L_0x00b1
            com.ximalaya.ting.android.player.model.HttpConfig r5 = com.ximalaya.ting.android.player.StaticConfig.c
            com.ximalaya.ting.android.player.IGetHttpUrlConnectByUrl r5 = r5.o
            if (r5 == 0) goto L_0x001f
            com.ximalaya.ting.android.player.model.HttpConfig r5 = com.ximalaya.ting.android.player.StaticConfig.c
            com.ximalaya.ting.android.player.IGetHttpUrlConnectByUrl r5 = r5.o
            com.ximalaya.ting.android.player.model.HttpConfig r1 = a((java.util.Map<java.lang.String, java.lang.String>) r7)
            java.net.HttpURLConnection r4 = r5.a(r4, r6, r1)
            goto L_0x00b2
        L_0x001f:
            com.ximalaya.ting.android.player.model.HttpConfig r4 = com.ximalaya.ting.android.player.StaticConfig.c
            java.lang.String r4 = r4.f
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x00b1
            java.net.Proxy r4 = new java.net.Proxy
            java.net.Proxy$Type r5 = java.net.Proxy.Type.HTTP
            java.net.InetSocketAddress r1 = new java.net.InetSocketAddress
            com.ximalaya.ting.android.player.model.HttpConfig r2 = com.ximalaya.ting.android.player.StaticConfig.c
            java.lang.String r2 = r2.f
            com.ximalaya.ting.android.player.model.HttpConfig r3 = com.ximalaya.ting.android.player.StaticConfig.c
            int r3 = r3.g
            r1.<init>(r2, r3)
            r4.<init>(r5, r1)
            java.net.URLConnection r4 = r0.openConnection(r4)
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
            r4.setRequestMethod(r6)
            com.ximalaya.ting.android.player.model.HttpConfig r5 = com.ximalaya.ting.android.player.StaticConfig.c
            java.lang.String r5 = r5.i
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x0059
            java.lang.String r5 = "Authorization"
            com.ximalaya.ting.android.player.model.HttpConfig r1 = com.ximalaya.ting.android.player.StaticConfig.c
            java.lang.String r1 = r1.i
            r4.setRequestProperty(r5, r1)
        L_0x0059:
            if (r7 == 0) goto L_0x0081
            java.util.Set r5 = r7.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0063:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0081
            java.lang.Object r1 = r5.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            if (r1 == 0) goto L_0x0063
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            r4.setRequestProperty(r2, r1)
            goto L_0x0063
        L_0x0081:
            com.ximalaya.ting.android.player.model.HttpConfig r5 = com.ximalaya.ting.android.player.StaticConfig.c
            java.util.Map<java.lang.String, java.lang.String> r5 = r5.n
            if (r5 == 0) goto L_0x00b2
            com.ximalaya.ting.android.player.model.HttpConfig r5 = com.ximalaya.ting.android.player.StaticConfig.c
            java.util.Map<java.lang.String, java.lang.String> r5 = r5.n
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0093:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x00b2
            java.lang.Object r1 = r5.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            if (r1 == 0) goto L_0x0093
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            r4.setRequestProperty(r2, r1)
            goto L_0x0093
        L_0x00b1:
            r4 = 0
        L_0x00b2:
            if (r4 != 0) goto L_0x0130
            java.net.URLConnection r4 = r0.openConnection()
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
            r5 = 20000(0x4e20, float:2.8026E-41)
            r4.setConnectTimeout(r5)
            r4.setReadTimeout(r5)
            r5 = 1
            r4.setUseCaches(r5)
            r4.setRequestMethod(r6)
            java.lang.String r5 = com.ximalaya.ting.android.player.StaticConfig.b
            if (r5 == 0) goto L_0x00d4
            java.lang.String r5 = "User-Agent"
            java.lang.String r6 = com.ximalaya.ting.android.player.StaticConfig.b
            r4.setRequestProperty(r5, r6)
        L_0x00d4:
            java.util.Map<java.lang.String, java.lang.String> r5 = com.ximalaya.ting.android.player.StaticConfig.d
            if (r5 == 0) goto L_0x0108
            java.util.Map<java.lang.String, java.lang.String> r5 = com.ximalaya.ting.android.player.StaticConfig.d
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x0108
            java.util.Map<java.lang.String, java.lang.String> r5 = com.ximalaya.ting.android.player.StaticConfig.d
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x00ea:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0108
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            if (r6 == 0) goto L_0x00ea
            java.lang.Object r0 = r6.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            r4.setRequestProperty(r0, r6)
            goto L_0x00ea
        L_0x0108:
            if (r7 == 0) goto L_0x0130
            java.util.Set r5 = r7.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0112:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0130
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            if (r6 == 0) goto L_0x0112
            java.lang.Object r7 = r6.getKey()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            r4.setRequestProperty(r7, r6)
            goto L_0x0112
        L_0x0130:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.PlayerUtil.a(java.lang.String, boolean, java.lang.String, java.util.Map):java.net.HttpURLConnection");
    }

    private static HttpConfig a(Map<String, String> map) {
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.j = 20000;
        httpConfig.k = 20000;
        httpConfig.e = true;
        httpConfig.n = new HashMap();
        if (StaticConfig.b != null) {
            httpConfig.n.put("User-Agent", StaticConfig.b);
        }
        if (StaticConfig.d != null) {
            httpConfig.n.putAll(StaticConfig.d);
        }
        if (map != null) {
            httpConfig.n.putAll(map);
        }
        return httpConfig;
    }

    public static String e() {
        if (!TextUtils.isEmpty(f2279a)) {
            return f2279a;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < split.length; i++) {
                f2279a += split[i] + " ";
            }
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return f2279a;
    }

    public static void b(String str) {
        File file = new File(XMediaPlayerConstants.o);
        if (file.isDirectory() && !TextUtils.isEmpty(str)) {
            String str2 = str + ".chunk";
            new File(file, str2).delete();
            new File(file, str + ".index").delete();
        }
    }

    public static void c(String str) {
        String str2;
        File file = new File(XMediaPlayerConstants.o);
        if (file.isDirectory()) {
            String str3 = null;
            if (!TextUtils.isEmpty(str)) {
                String b = MD5.b(str);
                str3 = b + ".chunk";
                str2 = b + ".index";
            } else {
                str2 = null;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2 != null && ((str3 == null || !str3.equalsIgnoreCase(file2.getName())) && ((str2 == null || !str2.equalsIgnoreCase(file2.getName())) && (str2 == null || !str2.endsWith(XMediaPlayerConstants.p))))) {
                        file2.delete();
                    }
                }
            }
        }
        PlayCacheByLRU.a().b(str);
    }

    public static boolean f() {
        return "mounted".equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().canWrite();
    }

    public static final long g() {
        if (f()) {
            return 0 + a(new File(XMediaPlayerConstants.o));
        }
        return 0;
    }

    public static long a(File file) {
        long j;
        long j2 = 0;
        if (file == null || !file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            String str = null;
            if (!file.exists()) {
                str = file + " does not exist";
            } else if (!file.isDirectory()) {
                str = file + " is not a directory";
            }
            return str != null ? 0 : 0;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j = a(file2);
            } else {
                j = file2.length();
            }
            j2 += j;
        }
        return j2;
    }

    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v1 */
    /* JADX WARNING: type inference failed for: r10v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x026e  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.HttpURLConnection a(java.lang.String[] r24, java.lang.String r25, int r26, boolean r27, java.lang.String r28) {
        /*
            r1 = r24
            r2 = r25
            r3 = r26
            com.ximalaya.ting.android.player.IDomainServerIpCallback r4 = com.ximalaya.ting.android.player.StaticConfig.f2290a
            r0 = 0
            r5 = r0
            java.lang.String[][] r5 = (java.lang.String[][]) r5
            r6 = 0
            if (r4 == 0) goto L_0x0015
            r5 = r1[r6]
            java.lang.String[][] r5 = r4.a(r5)
        L_0x0015:
            java.lang.String r7 = ""
            r8 = 0
            r10 = 1
            if (r5 == 0) goto L_0x0022
            int r11 = r5.length
            if (r11 != 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r11 = 0
            goto L_0x0030
        L_0x0022:
            java.lang.String[][] r5 = new java.lang.String[r10][]
            r11 = 2
            java.lang.String[] r11 = new java.lang.String[r11]
            r12 = r1[r6]
            r11[r6] = r12
            r11[r10] = r0
            r5[r6] = r11
            r11 = 1
        L_0x0030:
            com.ximalaya.ting.android.player.xdcs.IStatToServerFactory r12 = com.ximalaya.ting.android.player.StaticConfig.e
            if (r12 == 0) goto L_0x0039
            com.ximalaya.ting.android.player.xdcs.IStatToServer r12 = r12.a()
            goto L_0x003a
        L_0x0039:
            r12 = r0
        L_0x003a:
            java.lang.String r13 = "UnicomDigestAuthenticator 000 == 开始获取httpconnect"
            com.ximalaya.ting.android.player.Logger.a((java.lang.Object) r13)
            r13 = r7
            r14 = r8
            r7 = 0
            r9 = 0
            r8 = r0
        L_0x0044:
            int r0 = r5.length
            if (r7 >= r0) goto L_0x02b4
            a((java.net.HttpURLConnection) r8)
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0232 }
            r0.<init>()     // Catch:{ Exception -> 0x0232 }
            boolean r16 = android.text.TextUtils.isEmpty(r25)     // Catch:{ Exception -> 0x0232 }
            if (r16 != 0) goto L_0x005e
            java.lang.String r6 = "RANGE"
            r0.put(r6, r2)     // Catch:{ Exception -> 0x005b }
            goto L_0x005e
        L_0x005b:
            r0 = move-exception
            goto L_0x0235
        L_0x005e:
            r6 = r5[r7]     // Catch:{ Exception -> 0x0232 }
            r6 = r6[r10]     // Catch:{ Exception -> 0x0232 }
            if (r6 == 0) goto L_0x007b
            java.lang.String r6 = "Host"
            r16 = r5[r7]     // Catch:{ Exception -> 0x0232 }
            r22 = r8
            r8 = r16[r10]     // Catch:{ Exception -> 0x022a }
            r0.put(r6, r8)     // Catch:{ Exception -> 0x022a }
            r6 = r5[r7]     // Catch:{ Exception -> 0x022a }
            r6 = r6[r10]     // Catch:{ Exception -> 0x022a }
            java.lang.String r8 = "httpdnsType"
            java.lang.String r13 = "ip"
            r0.put(r8, r13)     // Catch:{ Exception -> 0x008f }
            goto L_0x0085
        L_0x007b:
            r22 = r8
            java.lang.String r6 = "httpdnsType"
            java.lang.String r8 = "domain"
            r0.put(r6, r8)     // Catch:{ Exception -> 0x022a }
            r6 = r13
        L_0x0085:
            if (r3 != 0) goto L_0x0094
            java.lang.String r8 = "Accept-Encoding"
            java.lang.String r13 = "identity"
            r0.put(r8, r13)     // Catch:{ Exception -> 0x008f }
            goto L_0x0094
        L_0x008f:
            r0 = move-exception
            r23 = r11
            goto L_0x022e
        L_0x0094:
            r8 = r5[r7]     // Catch:{ Exception -> 0x008f }
            r13 = 0
            r8 = r8[r13]     // Catch:{ Exception -> 0x008f }
            r13 = 3
            if (r13 == r3) goto L_0x009f
            r13 = r28
            goto L_0x00a2
        L_0x009f:
            r13 = r28
            r10 = 0
        L_0x00a2:
            java.net.HttpURLConnection r8 = a(r8, r10, r13, r0)     // Catch:{ Exception -> 0x008f }
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0226 }
            r10 = 1
            if (r3 != r10) goto L_0x00b0
            r8.connect()     // Catch:{ Exception -> 0x0226 }
        L_0x00b0:
            r23 = r11
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0224 }
            int r9 = r9 + 1
            int r0 = r8.getResponseCode()     // Catch:{ Exception -> 0x0222 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r14.<init>()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r15 = "UnicomDigestAuthenticator 456 == 耗时= "
            r14.append(r15)     // Catch:{ Exception -> 0x0222 }
            long r18 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0222 }
            r15 = 0
            long r1 = r18 - r16
            r14.append(r1)     // Catch:{ Exception -> 0x021e }
            java.lang.String r1 = " | code = "
            r14.append(r1)     // Catch:{ Exception -> 0x021e }
            r14.append(r0)     // Catch:{ Exception -> 0x021e }
            java.lang.String r1 = r14.toString()     // Catch:{ Exception -> 0x021e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.Object) r1)     // Catch:{ Exception -> 0x021e }
            if (r3 != 0) goto L_0x011b
            r1 = 206(0xce, float:2.89E-43)
            if (r0 != r1) goto L_0x011b
            if (r12 == 0) goto L_0x011a
            r0 = r5[r7]     // Catch:{ Exception -> 0x021e }
            r1 = 0
            r14 = r0[r1]     // Catch:{ Exception -> 0x021e }
            java.lang.String r15 = "success"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x021e }
            r0.<init>()     // Catch:{ Exception -> 0x021e }
            r0.append(r9)     // Catch:{ Exception -> 0x021e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ Exception -> 0x021e }
            java.lang.String r16 = r0.toString()     // Catch:{ Exception -> 0x021e }
            java.lang.String r18 = com.ximalaya.ting.android.player.StaticConfig.b     // Catch:{ Exception -> 0x021e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x021e }
            r0.<init>()     // Catch:{ Exception -> 0x021e }
            r0.append(r10)     // Catch:{ Exception -> 0x021e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ Exception -> 0x021e }
            java.lang.String r19 = r0.toString()     // Catch:{ Exception -> 0x021e }
            java.lang.String r20 = ""
            r13 = r12
            r17 = r6
            r13.b(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x021e }
        L_0x011a:
            return r8
        L_0x011b:
            if (r3 == 0) goto L_0x015b
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 < r1) goto L_0x015b
            r1 = 400(0x190, float:5.6E-43)
            if (r0 >= r1) goto L_0x015b
            if (r12 == 0) goto L_0x015a
            r0 = r5[r7]     // Catch:{ Exception -> 0x021e }
            r1 = 0
            r14 = r0[r1]     // Catch:{ Exception -> 0x021e }
            java.lang.String r15 = "success"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x021e }
            r0.<init>()     // Catch:{ Exception -> 0x021e }
            r0.append(r9)     // Catch:{ Exception -> 0x021e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ Exception -> 0x021e }
            java.lang.String r16 = r0.toString()     // Catch:{ Exception -> 0x021e }
            java.lang.String r18 = com.ximalaya.ting.android.player.StaticConfig.b     // Catch:{ Exception -> 0x021e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x021e }
            r0.<init>()     // Catch:{ Exception -> 0x021e }
            r0.append(r10)     // Catch:{ Exception -> 0x021e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ Exception -> 0x021e }
            java.lang.String r19 = r0.toString()     // Catch:{ Exception -> 0x021e }
            java.lang.String r20 = ""
            r13 = r12
            r17 = r6
            r13.b(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x021e }
        L_0x015a:
            return r8
        L_0x015b:
            if (r4 == 0) goto L_0x0215
            r1 = 403(0x193, float:5.65E-43)
            if (r0 != r1) goto L_0x018f
            r1 = r24
            r2 = 0
            r13 = r1[r2]     // Catch:{ Exception -> 0x018a }
            java.lang.String r14 = "is_charge"
            boolean r13 = r13.contains(r14)     // Catch:{ Exception -> 0x018a }
            if (r13 == 0) goto L_0x0191
            if (r27 != 0) goto L_0x0191
            r0 = r1[r2]     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = r4.b(r0)     // Catch:{ Exception -> 0x018a }
            boolean r13 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x018a }
            if (r13 == 0) goto L_0x017e
            goto L_0x02b4
        L_0x017e:
            r1[r2] = r0     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = "GET"
            r2 = r25
            r13 = 1
            java.net.HttpURLConnection r0 = a(r1, r2, r3, r13, r0)     // Catch:{ Exception -> 0x0222 }
            return r0
        L_0x018a:
            r0 = move-exception
            r2 = r25
            goto L_0x0239
        L_0x018f:
            r1 = r24
        L_0x0191:
            r2 = r25
            r13 = r5[r7]     // Catch:{ Exception -> 0x0222 }
            r14 = 1
            r13 = r13[r14]     // Catch:{ Exception -> 0x0222 }
            r14 = r5[r7]     // Catch:{ Exception -> 0x0222 }
            r15 = 0
            r14 = r14[r15]     // Catch:{ Exception -> 0x0222 }
            r2 = r1[r15]     // Catch:{ Exception -> 0x0222 }
            r4.a(r13, r14, r2)     // Catch:{ Exception -> 0x0222 }
            if (r23 == 0) goto L_0x01d0
            if (r12 == 0) goto L_0x0217
            r2 = r1[r15]     // Catch:{ Exception -> 0x0222 }
            java.lang.String r13 = com.ximalaya.ting.android.player.StaticConfig.b     // Catch:{ Exception -> 0x0222 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r14.<init>()     // Catch:{ Exception -> 0x0222 }
            r14.append(r10)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r15 = ""
            r14.append(r15)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0222 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r15.<init>()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r3 = "response code is "
            r15.append(r3)     // Catch:{ Exception -> 0x0222 }
            r15.append(r0)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r0 = r15.toString()     // Catch:{ Exception -> 0x0222 }
            r12.a(r2, r13, r14, r0)     // Catch:{ Exception -> 0x0222 }
            goto L_0x0217
        L_0x01d0:
            if (r12 == 0) goto L_0x0217
            r2 = r5[r7]     // Catch:{ Exception -> 0x0222 }
            r3 = 0
            r14 = r2[r3]     // Catch:{ Exception -> 0x0222 }
            java.lang.String r15 = "fail"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r2.<init>()     // Catch:{ Exception -> 0x0222 }
            r2.append(r9)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r16 = r2.toString()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r18 = com.ximalaya.ting.android.player.StaticConfig.b     // Catch:{ Exception -> 0x0222 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r2.<init>()     // Catch:{ Exception -> 0x0222 }
            r2.append(r10)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r19 = r2.toString()     // Catch:{ Exception -> 0x0222 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0222 }
            r2.<init>()     // Catch:{ Exception -> 0x0222 }
            java.lang.String r3 = "response code is "
            r2.append(r3)     // Catch:{ Exception -> 0x0222 }
            r2.append(r0)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r20 = r2.toString()     // Catch:{ Exception -> 0x0222 }
            r13 = r12
            r17 = r6
            r13.a(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x0222 }
            goto L_0x0217
        L_0x0215:
            r1 = r24
        L_0x0217:
            r13 = r6
            r14 = r10
            r3 = 1
            r21 = 0
            goto L_0x02a8
        L_0x021e:
            r0 = move-exception
            r1 = r24
            goto L_0x0239
        L_0x0222:
            r0 = move-exception
            goto L_0x0239
        L_0x0224:
            r0 = move-exception
            goto L_0x0238
        L_0x0226:
            r0 = move-exception
            r23 = r11
            goto L_0x0238
        L_0x022a:
            r0 = move-exception
            r23 = r11
            r6 = r13
        L_0x022e:
            r10 = r14
            r8 = r22
            goto L_0x0239
        L_0x0232:
            r0 = move-exception
            r22 = r8
        L_0x0235:
            r23 = r11
            r6 = r13
        L_0x0238:
            r10 = r14
        L_0x0239:
            r0.printStackTrace()
            r2 = r5[r7]
            r3 = 1
            r2 = r2[r3]
            r13 = r5[r7]
            r14 = 0
            r13 = r13[r14]
            r15 = r1[r14]
            r4.a(r2, r13, r15)
            if (r23 == 0) goto L_0x026e
            if (r12 == 0) goto L_0x026b
            r2 = r1[r14]
            java.lang.String r13 = com.ximalaya.ting.android.player.StaticConfig.b
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r10)
            java.lang.String r15 = ""
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            java.lang.String r0 = r0.toString()
            r12.a(r2, r13, r14, r0)
        L_0x026b:
            r21 = 0
            goto L_0x02a6
        L_0x026e:
            if (r12 == 0) goto L_0x026b
            r2 = r5[r7]
            r21 = 0
            r14 = r2[r21]
            java.lang.String r15 = "fail"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
            java.lang.String r13 = ""
            r2.append(r13)
            java.lang.String r16 = r2.toString()
            java.lang.String r18 = com.ximalaya.ting.android.player.StaticConfig.b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r13 = ""
            r2.append(r13)
            java.lang.String r19 = r2.toString()
            java.lang.String r20 = r0.toString()
            r13 = r12
            r17 = r6
            r13.a(r14, r15, r16, r17, r18, r19, r20)
        L_0x02a6:
            r13 = r6
            r14 = r10
        L_0x02a8:
            int r7 = r7 + 1
            r11 = r23
            r2 = r25
            r3 = r26
            r6 = 0
            r10 = 1
            goto L_0x0044
        L_0x02b4:
            r22 = r8
            if (r12 == 0) goto L_0x02bb
            r12.a()
        L_0x02bb:
            return r22
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.PlayerUtil.a(java.lang.String[], java.lang.String, int, boolean, java.lang.String):java.net.HttpURLConnection");
    }

    public static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public static File d(String str) {
        File file = new File(str);
        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0030 A[SYNTHETIC, Splitter:B:20:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003b A[SYNTHETIC, Splitter:B:25:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r3, java.lang.String r4) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0044
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x000d
            goto L_0x0044
        L_0x000d:
            d(r4)
            r0 = 0
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x002a }
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ Exception -> 0x002a }
            r2.<init>(r4)     // Catch:{ Exception -> 0x002a }
            r1.<init>(r2)     // Catch:{ Exception -> 0x002a }
            r1.write(r3)     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0022:
            r3 = move-exception
            r0 = r1
            goto L_0x0039
        L_0x0025:
            r3 = move-exception
            r0 = r1
            goto L_0x002b
        L_0x0028:
            r3 = move-exception
            goto L_0x0039
        L_0x002a:
            r3 = move-exception
        L_0x002b:
            r3.printStackTrace()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0038:
            return
        L_0x0039:
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0043:
            throw r3
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.PlayerUtil.a(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045 A[SYNTHETIC, Splitter:B:25:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0051 A[SYNTHETIC, Splitter:B:32:0x0051] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String e(java.lang.String r3) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            r1 = 0
            if (r0 != 0) goto L_0x005a
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0013
            goto L_0x005a
        L_0x0013:
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r2.<init>(r3)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r0.<init>(r2)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0039 }
            r3.<init>()     // Catch:{ Exception -> 0x0039 }
        L_0x0022:
            java.lang.String r2 = r0.readLine()     // Catch:{ Exception -> 0x0039 }
            if (r2 == 0) goto L_0x002c
            r3.append(r2)     // Catch:{ Exception -> 0x0039 }
            goto L_0x0022
        L_0x002c:
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r0.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0038:
            return r3
        L_0x0039:
            r3 = move-exception
            goto L_0x0040
        L_0x003b:
            r3 = move-exception
            r0 = r1
            goto L_0x004f
        L_0x003e:
            r3 = move-exception
            r0 = r1
        L_0x0040:
            r3.printStackTrace()     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r3 = move-exception
            r3.printStackTrace()
        L_0x004d:
            return r1
        L_0x004e:
            r3 = move-exception
        L_0x004f:
            if (r0 == 0) goto L_0x0059
            r0.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0059:
            throw r3
        L_0x005a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.PlayerUtil.e(java.lang.String):java.lang.String");
    }
}
