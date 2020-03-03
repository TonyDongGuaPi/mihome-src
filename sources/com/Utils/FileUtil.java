package com.Utils;

import android.content.Context;
import android.text.TextUtils;
import com.mijia.app.AppConstant;
import com.mijia.debug.SDKLog;
import com.mijia.player.FileNamer;
import com.smarthome_camera.R;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

public class FileUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f664a = "FileUtil";

    public static String a(long j) {
        Context context = PluginHostApi.instance().context();
        if (j <= 0) {
            return "0 K";
        }
        if (j < 1024) {
            return "1 K";
        }
        if (j < 1048576) {
            long j2 = j / 1024;
            if (j2 > 100) {
                DecimalFormat decimalFormat = new DecimalFormat("##0");
                StringBuilder sb = new StringBuilder();
                double d = (double) j;
                Double.isNaN(d);
                sb.append(decimalFormat.format(a(d / 1024.0d)));
                sb.append(" K");
                return sb.toString();
            } else if (j2 > 10) {
                DecimalFormat decimalFormat2 = new DecimalFormat("##0.0");
                StringBuilder sb2 = new StringBuilder();
                double d2 = (double) j;
                Double.isNaN(d2);
                sb2.append(decimalFormat2.format(a(d2 / 1024.0d)));
                sb2.append(" K");
                return sb2.toString();
            } else {
                DecimalFormat decimalFormat3 = new DecimalFormat("##0.00");
                StringBuilder sb3 = new StringBuilder();
                double d3 = (double) j;
                Double.isNaN(d3);
                sb3.append(decimalFormat3.format(a(d3 / 1024.0d)));
                sb3.append(" K");
                return sb3.toString();
            }
        } else if (j < 1073741824) {
            long j3 = j / 1048576;
            if (j3 > 100) {
                DecimalFormat decimalFormat4 = new DecimalFormat("##0");
                StringBuilder sb4 = new StringBuilder();
                double d4 = (double) j;
                Double.isNaN(d4);
                sb4.append(decimalFormat4.format(a(d4 / 1048576.0d)));
                sb4.append(context.getString(R.string.bps_m_new));
                return sb4.toString();
            } else if (j3 > 10) {
                DecimalFormat decimalFormat5 = new DecimalFormat("##0.0");
                StringBuilder sb5 = new StringBuilder();
                double d5 = (double) j;
                Double.isNaN(d5);
                sb5.append(decimalFormat5.format(a(d5 / 1048576.0d)));
                sb5.append(context.getString(R.string.bps_m_new));
                return sb5.toString();
            } else {
                DecimalFormat decimalFormat6 = new DecimalFormat("##0.00");
                StringBuilder sb6 = new StringBuilder();
                double d6 = (double) j;
                Double.isNaN(d6);
                sb6.append(decimalFormat6.format(a(d6 / 1048576.0d)));
                sb6.append(context.getString(R.string.bps_m_new));
                return sb6.toString();
            }
        } else if (j < 1099511627776L) {
            long j4 = j / 1073741824;
            if (j4 > 100) {
                DecimalFormat decimalFormat7 = new DecimalFormat("##0");
                StringBuilder sb7 = new StringBuilder();
                double d7 = (double) j;
                Double.isNaN(d7);
                sb7.append(decimalFormat7.format(a(d7 / 1.073741824E9d)));
                sb7.append(context.getString(R.string.bps_g));
                return sb7.toString();
            } else if (j4 > 10) {
                DecimalFormat decimalFormat8 = new DecimalFormat("##0.0");
                StringBuilder sb8 = new StringBuilder();
                double d8 = (double) j;
                Double.isNaN(d8);
                sb8.append(decimalFormat8.format(a(d8 / 1.073741824E9d)));
                sb8.append(context.getString(R.string.bps_g));
                return sb8.toString();
            } else {
                DecimalFormat decimalFormat9 = new DecimalFormat("##0.0");
                StringBuilder sb9 = new StringBuilder();
                double d9 = (double) j;
                Double.isNaN(d9);
                sb9.append(decimalFormat9.format(a(d9 / 1.073741824E9d)));
                sb9.append(context.getString(R.string.bps_g));
                return sb9.toString();
            }
        } else {
            long j5 = j / 1099511627776L;
            if (j5 > 100) {
                DecimalFormat decimalFormat10 = new DecimalFormat("##0");
                StringBuilder sb10 = new StringBuilder();
                double d10 = (double) j;
                Double.isNaN(d10);
                sb10.append(decimalFormat10.format(d10 / 1.099511627776E12d));
                sb10.append(" T");
                return sb10.toString();
            } else if (j5 > 10) {
                DecimalFormat decimalFormat11 = new DecimalFormat("##0.0");
                StringBuilder sb11 = new StringBuilder();
                double d11 = (double) j;
                Double.isNaN(d11);
                sb11.append(decimalFormat11.format(d11 / 1.099511627776E12d));
                sb11.append(" T");
                return sb11.toString();
            } else {
                DecimalFormat decimalFormat12 = new DecimalFormat("##0.00");
                StringBuilder sb12 = new StringBuilder();
                double d12 = (double) j;
                Double.isNaN(d12);
                sb12.append(decimalFormat12.format(d12 / 1.099511627776E12d));
                sb12.append(" T");
                return sb12.toString();
            }
        }
    }

    public static double a(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal.setScale(1, RoundingMode.HALF_UP);
        return (double) bigDecimal.floatValue();
    }

    public static String a(String str) {
        String str2 = AppConstant.b + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String a(boolean z, String str) {
        return a(System.currentTimeMillis(), z, str);
    }

    public static String a(long j, boolean z, String str) {
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a2);
        sb.append(File.separator);
        sb.append(FileNamer.a().a(j, z));
        sb.append(z ? ".mp4" : ".jpg");
        return sb.toString();
    }

    public static String b(String str) {
        String str2 = XmPluginHostApi.instance().application().getDir("mijia_camera", 0) + str + File.separator;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, str + ".temp").getAbsolutePath();
    }

    public static String c(String str) {
        String str2 = AppConstant.c + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static byte[] d(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            LogUtil.b(f664a, "file2Byte FileNotFoundException:" + e.getLocalizedMessage());
            return null;
        } catch (IOException e2) {
            LogUtil.b(f664a, "file2Byte IOException:" + e2.getLocalizedMessage());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0091 A[SYNTHETIC, Splitter:B:33:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2 A[SYNTHETIC, Splitter:B:38:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d5 A[SYNTHETIC, Splitter:B:45:0x00d5] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f6 A[SYNTHETIC, Splitter:B:50:0x00f6] */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(byte[] r3, java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            if (r2 != 0) goto L_0x0015
            boolean r2 = r1.isDirectory()     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            if (r2 == 0) goto L_0x0015
            r1.mkdirs()     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
        L_0x0015:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r2.<init>()     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r2.append(r4)     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            java.lang.String r4 = java.io.File.separator     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r2.append(r4)     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r2.append(r5)     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            java.lang.String r4 = r2.toString()     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0073, all -> 0x0070 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x006e }
            r5.<init>(r4)     // Catch:{ Exception -> 0x006e }
            r5.write(r3)     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
            r5.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x005a
        L_0x003f:
            r3 = move-exception
            java.lang.String r5 = "FileUtil"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "byte2File IOException 1:"
            r0.append(r1)
            java.lang.String r3 = r3.getLocalizedMessage()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r3)
        L_0x005a:
            r4.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x00d1
        L_0x005f:
            r3 = move-exception
            java.lang.String r4 = "FileUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            goto L_0x00be
        L_0x0068:
            r3 = move-exception
            r0 = r5
            goto L_0x00d3
        L_0x006b:
            r3 = move-exception
            r0 = r5
            goto L_0x0075
        L_0x006e:
            r3 = move-exception
            goto L_0x0075
        L_0x0070:
            r3 = move-exception
            r4 = r0
            goto L_0x00d3
        L_0x0073:
            r3 = move-exception
            r4 = r0
        L_0x0075:
            java.lang.String r5 = "FileUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d2 }
            r1.<init>()     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = "byte2File Exception:"
            r1.append(r2)     // Catch:{ all -> 0x00d2 }
            java.lang.String r3 = r3.getLocalizedMessage()     // Catch:{ all -> 0x00d2 }
            r1.append(r3)     // Catch:{ all -> 0x00d2 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x00d2 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r3)     // Catch:{ all -> 0x00d2 }
            if (r0 == 0) goto L_0x00b0
            r0.close()     // Catch:{ IOException -> 0x0095 }
            goto L_0x00b0
        L_0x0095:
            r3 = move-exception
            java.lang.String r5 = "FileUtil"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "byte2File IOException 1:"
            r0.append(r1)
            java.lang.String r3 = r3.getLocalizedMessage()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r3)
        L_0x00b0:
            if (r4 == 0) goto L_0x00d1
            r4.close()     // Catch:{ IOException -> 0x00b6 }
            goto L_0x00d1
        L_0x00b6:
            r3 = move-exception
            java.lang.String r4 = "FileUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
        L_0x00be:
            java.lang.String r0 = "byte2File IOException 2:"
            r5.append(r0)
            java.lang.String r3 = r3.getLocalizedMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r4, r3)
        L_0x00d1:
            return
        L_0x00d2:
            r3 = move-exception
        L_0x00d3:
            if (r0 == 0) goto L_0x00f4
            r0.close()     // Catch:{ IOException -> 0x00d9 }
            goto L_0x00f4
        L_0x00d9:
            r5 = move-exception
            java.lang.String r0 = "FileUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "byte2File IOException 1:"
            r1.append(r2)
            java.lang.String r5 = r5.getLocalizedMessage()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r0, r5)
        L_0x00f4:
            if (r4 == 0) goto L_0x0115
            r4.close()     // Catch:{ IOException -> 0x00fa }
            goto L_0x0115
        L_0x00fa:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "byte2File IOException 2:"
            r5.append(r0)
            java.lang.String r4 = r4.getLocalizedMessage()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "FileUtil"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r4)
        L_0x0115:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Utils.FileUtil.a(byte[], java.lang.String, java.lang.String):void");
    }

    public static String e(String str) {
        String str2 = AppConstant.j + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String f(String str) {
        String str2 = AppConstant.k + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String a() {
        String str = AppConstant.k + File.separator;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String a(String str, int i, String str2) {
        String f = f(str);
        if (TextUtils.isEmpty(f)) {
            return null;
        }
        SDKLog.b("AlbumActivity", "generateTimeLapseFilepath taskID=" + i + ",taskName=" + str2);
        return f + File.separator + i + JSMethod.NOT_SET + str2 + File.separator;
    }

    public static String b(String str, int i, String str2) {
        if (TextUtils.isEmpty(f(str))) {
            return null;
        }
        return a(str, i, str2) + "pic/";
    }

    public static String c(String str, int i, String str2) {
        String b = b(str, i, str2);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        File file = new File(b);
        if (!file.exists()) {
            file.mkdirs();
        }
        return b + a(Calendar.getInstance()) + ".jpg";
    }

    public static String a(Calendar calendar) {
        return ("" + calendar.get(1) + a(calendar.get(2) + 1) + a(calendar.get(5))) + JSMethod.NOT_SET + ("" + a(calendar.get(11)) + a(calendar.get(12)) + a(calendar.get(13)));
    }

    private static String a(int i) {
        if (i < 0) {
            return "";
        }
        if (i < 0 || i > 9) {
            return "" + i;
        }
        return "0" + i;
    }

    public static String g(String str) {
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        File file = new File(a2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, "wxQcord.jpg").getAbsolutePath();
    }

    public static String b() {
        String str = AppConstant.h + File.separator;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String c() {
        String str = AppConstant.i + File.separator;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String d() {
        String str = AppConstant.l + File.separator;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }
}
