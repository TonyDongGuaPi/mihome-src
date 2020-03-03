package com.megvii.livenessdetection.obf;

import android.content.Context;
import android.os.Build;
import com.taobao.weex.annotation.JSMethod;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.youpin.PackageUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static Context f6687a;
    private static c b;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c(context);
            }
            cVar = b;
        }
        return cVar;
    }

    private c(Context context) {
        f6687a = context;
    }

    public final boolean a(String str, String str2) {
        boolean z;
        try {
            System.loadLibrary("livenessdetection_v2.4.5");
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        if (z) {
            return true;
        }
        String str3 = str + "_bak";
        File filesDir = f6687a.getFilesDir();
        if (a(filesDir.toString(), str3, str, str2)) {
            File file = new File(filesDir.toString() + File.separator + (str3 + File.separator + (ShareConstants.o + str + JSMethod.NOT_SET + str2 + ".so")));
            StringBuilder sb = new StringBuilder("copy lib to ");
            sb.append(file.toString());
            d.a(sb.toString());
            if (file.exists()) {
                try {
                    System.load(file.toString());
                    return true;
                } catch (UnsatisfiedLinkError e) {
                    d.a("SoProtect", e.toString());
                }
            } else {
                d.b("SoProtect", String.format(Locale.ENGLISH, "error can't find %1$s lib in plugins_lib", new Object[]{str}));
            }
        } else {
            d.a("SoProtect", String.format(Locale.ENGLISH, "error copy %1$s lib fail", new Object[]{str}));
        }
        return z;
    }

    private void a(File file, String str) {
        try {
            for (File a2 : file.listFiles(new a(this, str))) {
                a(a2);
            }
        } catch (Exception e) {
            d.a("SoProtect", e.toString());
        }
    }

    private void a(File file) {
        if (!file.exists()) {
            d.b("SoProtect", "所删除的文件不存在！\n");
        } else if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            for (File a2 : file.listFiles()) {
                a(a2);
            }
            file.delete();
        }
    }

    private boolean a(String str, String str2, String str3, String str4) {
        String str5;
        String str6 = Build.CPU_ABI;
        String str7 = ShareConstants.o + str3 + JSMethod.NOT_SET + str4 + ".so";
        if (PackageUtils.c.equals(str6)) {
            str5 = "lib/x86/" + str7;
        } else if ("armeabi-v7a".equals(str6)) {
            str5 = "lib/armeabi-v7a/" + str7;
        } else {
            d.a("SoProtect", "apse is not support for this mode");
            return false;
        }
        try {
            File file = new File(str + File.separator + str2);
            File file2 = new File(file.toString() + File.separator + str7);
            if (file2.exists()) {
                d.b("SoProtect", "file " + file2.toString() + " is exist");
                return true;
            }
            a(file, ShareConstants.o + str3);
            file.mkdirs();
            boolean a2 = a(str, str5, str7, file2);
            if (a2 || !str6.equals("armeabi-v7a")) {
                return a2;
            }
            d.b("SoProtect", String.format("%s arch copy failed, try to copy %s arch", new Object[]{"armeabi-v7a", "armeabi"}));
            return a(str, "lib/armeabi/" + str7, str7, file2);
        } catch (Exception e) {
            d.a("SoProtect", e.toString());
            return false;
        }
    }

    private boolean a(String str, String str2, String str3, File file) {
        InputStream resourceAsStream = c.class.getClassLoader().getResourceAsStream(str2);
        if (resourceAsStream != null) {
            if (str == null) {
                d.a("SoProtect", "apse file cann't be null...");
            }
            boolean a2 = a(resourceAsStream, file);
            try {
                resourceAsStream.close();
                return a2;
            } catch (IOException e) {
                d.a("SoProtect", e.toString());
                return a2;
            }
        } else {
            d.b("SoProtect", "error: can't find " + str3 + " in apk");
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x007d A[SYNTHETIC, Splitter:B:47:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0082 A[Catch:{ IOException -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0087 A[Catch:{ IOException -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0099 A[SYNTHETIC, Splitter:B:59:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00a1 A[Catch:{ IOException -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00a6 A[Catch:{ IOException -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00b7 A[SYNTHETIC, Splitter:B:70:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00bf A[Catch:{ IOException -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00c4 A[Catch:{ IOException -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.io.InputStream r6, java.io.File r7) {
        /*
            r0 = 0
            r1 = 0
            boolean r2 = r7.exists()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x006f, all -> 0x006b }
            if (r2 == 0) goto L_0x000b
            r7.delete()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x006f, all -> 0x006b }
        L_0x000b:
            r7.createNewFile()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x006f, all -> 0x006b }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x006f, all -> 0x006b }
            r2.<init>(r6)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x006f, all -> 0x006b }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0068, IOException -> 0x0065, all -> 0x0062 }
            r6.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0068, IOException -> 0x0065, all -> 0x0062 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x005c, IOException -> 0x0056, all -> 0x004f }
            r7.<init>(r6)     // Catch:{ FileNotFoundException -> 0x005c, IOException -> 0x0056, all -> 0x004f }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0045, all -> 0x003f }
        L_0x0021:
            int r3 = r2.read(r0)     // Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0045, all -> 0x003f }
            r4 = -1
            if (r3 == r4) goto L_0x002c
            r7.write(r0, r1, r3)     // Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0045, all -> 0x003f }
            goto L_0x0021
        L_0x002c:
            r7.flush()     // Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0045, all -> 0x003f }
            r6.flush()     // Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0045, all -> 0x003f }
            r0 = 1
            r6.close()     // Catch:{ IOException -> 0x009d }
            r2.close()     // Catch:{ IOException -> 0x009d }
            r7.close()     // Catch:{ IOException -> 0x009d }
            r1 = 1
            goto L_0x00b3
        L_0x003f:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x00b5
        L_0x0045:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x0072
        L_0x004a:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x008e
        L_0x004f:
            r7 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L_0x00b5
        L_0x0056:
            r7 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L_0x0072
        L_0x005c:
            r7 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L_0x008e
        L_0x0062:
            r6 = move-exception
            r7 = r0
            goto L_0x00b5
        L_0x0065:
            r6 = move-exception
            r7 = r0
            goto L_0x0072
        L_0x0068:
            r6 = move-exception
            r7 = r0
            goto L_0x008e
        L_0x006b:
            r6 = move-exception
            r7 = r0
            r2 = r7
            goto L_0x00b5
        L_0x006f:
            r6 = move-exception
            r7 = r0
            r2 = r7
        L_0x0072:
            java.lang.String r3 = "SoProtect"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b4 }
            com.megvii.livenessdetection.obf.d.a(r3, r6)     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x0080
            r0.close()     // Catch:{ IOException -> 0x009d }
        L_0x0080:
            if (r2 == 0) goto L_0x0085
            r2.close()     // Catch:{ IOException -> 0x009d }
        L_0x0085:
            if (r7 == 0) goto L_0x00b3
            r7.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00b3
        L_0x008b:
            r6 = move-exception
            r7 = r0
            r2 = r7
        L_0x008e:
            java.lang.String r3 = "SoProtect"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b4 }
            com.megvii.livenessdetection.obf.d.a(r3, r6)     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x009f
            r0.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x009f
        L_0x009d:
            r6 = move-exception
            goto L_0x00aa
        L_0x009f:
            if (r2 == 0) goto L_0x00a4
            r2.close()     // Catch:{ IOException -> 0x009d }
        L_0x00a4:
            if (r7 == 0) goto L_0x00b3
            r7.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00b3
        L_0x00aa:
            java.lang.String r7 = "SoProtect"
            java.lang.String r6 = r6.toString()
            com.megvii.livenessdetection.obf.d.a(r7, r6)
        L_0x00b3:
            return r1
        L_0x00b4:
            r6 = move-exception
        L_0x00b5:
            if (r0 == 0) goto L_0x00bd
            r0.close()     // Catch:{ IOException -> 0x00bb }
            goto L_0x00bd
        L_0x00bb:
            r7 = move-exception
            goto L_0x00c8
        L_0x00bd:
            if (r2 == 0) goto L_0x00c2
            r2.close()     // Catch:{ IOException -> 0x00bb }
        L_0x00c2:
            if (r7 == 0) goto L_0x00d1
            r7.close()     // Catch:{ IOException -> 0x00bb }
            goto L_0x00d1
        L_0x00c8:
            java.lang.String r7 = r7.toString()
            java.lang.String r0 = "SoProtect"
            com.megvii.livenessdetection.obf.d.a(r0, r7)
        L_0x00d1:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenessdetection.obf.c.a(java.io.InputStream, java.io.File):boolean");
    }

    class a implements FileFilter {

        /* renamed from: a  reason: collision with root package name */
        private String f6688a = "";

        public a(c cVar, String str) {
            this.f6688a = str;
        }

        public final boolean accept(File file) {
            return file.getName().startsWith(this.f6688a);
        }
    }
}
