package com.alipay.deviceid.module.x;

import android.content.Context;
import android.util.Log;
import com.taobao.weex.annotation.JSMethod;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class bv {

    /* renamed from: a  reason: collision with root package name */
    public static String f898a = "";
    private static Context b;

    public bv(Context context) {
        b = context;
    }

    public final boolean a(String str) {
        String str2 = str + "_BK";
        try {
            File filesDir = b.getFilesDir();
            if (a(filesDir.toString(), str2, str)) {
                File file = new File(filesDir.toString() + File.separator + (str2 + File.separator + (ShareConstants.o + str + JSMethod.NOT_SET + f898a + ".so")));
                if (file.exists()) {
                    try {
                        System.load(file.toString());
                        return true;
                    } catch (UnsatisfiedLinkError e) {
                        Log.e("SEProtect", e.toString());
                        return false;
                    }
                } else {
                    String.format(Locale.ENGLISH, "error can't find %1$s lib in plugins_lib", new Object[]{str});
                    return false;
                }
            } else {
                Log.e("SEProtect", String.format(Locale.ENGLISH, "error copy %1$s lib fail", new Object[]{str}));
                return false;
            }
        } catch (FileNotFoundException e2) {
            Log.e("SEProtect", e2.toString());
            return false;
        }
    }

    private void a(String str, String str2) {
        try {
            for (File a2 : new File(str).listFiles(new a(str2))) {
                a(a2);
            }
        } catch (Exception e) {
            Log.e("SEProtect", e.toString());
        }
    }

    private void a(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File a2 : listFiles) {
                a(a2);
            }
            file.delete();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00de A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.String r0 = android.os.Build.CPU_ABI
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "lib"
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r10 = "_"
            r1.append(r10)
            java.lang.String r10 = f898a
            r1.append(r10)
            java.lang.String r10 = ".so"
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            java.lang.String r1 = "x86"
            boolean r1 = r1.equals(r0)
            r2 = 0
            if (r1 == 0) goto L_0x0038
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "lib/x86/"
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            goto L_0x0063
        L_0x0038:
            java.lang.String r1 = "armeabi"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x004f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "lib/armeabi/"
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            goto L_0x0063
        L_0x004f:
            java.lang.String r1 = "SEProtect"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "apse is not support for this mode: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.e(r1, r0)
            r0 = r2
        L_0x0063:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x00b0 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b0 }
            r3.<init>()     // Catch:{ Exception -> 0x00b0 }
            r3.append(r8)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = java.io.File.separator     // Catch:{ Exception -> 0x00b0 }
            r3.append(r4)     // Catch:{ Exception -> 0x00b0 }
            r3.append(r9)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00b0 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x00b0 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x00ae }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ae }
            r4.<init>()     // Catch:{ Exception -> 0x00ae }
            java.lang.String r5 = r1.toString()     // Catch:{ Exception -> 0x00ae }
            r4.append(r5)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r5 = java.io.File.separator     // Catch:{ Exception -> 0x00ae }
            r4.append(r5)     // Catch:{ Exception -> 0x00ae }
            r4.append(r10)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00ae }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00ae }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r4 = "libSE:"
            r2.<init>(r4)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r4 = r3.toString()     // Catch:{ Exception -> 0x00a9 }
            r2.append(r4)     // Catch:{ Exception -> 0x00a9 }
            r2 = r3
            goto L_0x00b6
        L_0x00a9:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x00b3
        L_0x00ae:
            r3 = move-exception
            goto L_0x00b3
        L_0x00b0:
            r1 = move-exception
            r3 = r1
            r1 = r2
        L_0x00b3:
            r3.toString()
        L_0x00b6:
            if (r2 == 0) goto L_0x00de
            boolean r3 = r2.exists()
            if (r3 == 0) goto L_0x00d3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "file "
            r8.<init>(r9)
            java.lang.String r9 = r2.toString()
            r8.append(r9)
            java.lang.String r9 = " is exist"
            r8.append(r9)
            r8 = 1
            return r8
        L_0x00d3:
            r7.a((java.lang.String) r8, (java.lang.String) r9)
            r1.mkdirs()
            boolean r8 = a(r8, r0, r10, r2)
            goto L_0x00df
        L_0x00de:
            r8 = 0
        L_0x00df:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.bv.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private static boolean a(String str, String str2, String str3, File file) {
        InputStream resourceAsStream = bv.class.getClassLoader().getResourceAsStream(str2);
        if (resourceAsStream != null) {
            if (str == null) {
                Log.e("SEProtect", "apse file cann't be null...");
            }
            boolean a2 = a(resourceAsStream, file);
            try {
                resourceAsStream.close();
                return a2;
            } catch (IOException e) {
                Log.e("SEProtect", e.toString());
                return a2;
            }
        } else {
            StringBuilder sb = new StringBuilder("error: can't find ");
            sb.append(str3);
            sb.append(" in apk");
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
            r0 = 512(0x200, float:7.175E-43)
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
            java.lang.String r3 = "SEProtect"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b4 }
            android.util.Log.e(r3, r6)     // Catch:{ all -> 0x00b4 }
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
            java.lang.String r3 = "SEProtect"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b4 }
            android.util.Log.e(r3, r6)     // Catch:{ all -> 0x00b4 }
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
            java.lang.String r7 = "SEProtect"
            java.lang.String r6 = r6.toString()
            android.util.Log.e(r7, r6)
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
            java.lang.String r0 = "SEProtect"
            android.util.Log.e(r0, r7)
        L_0x00d1:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.bv.a(java.io.InputStream, java.io.File):boolean");
    }

    class a implements FileFilter {

        /* renamed from: a  reason: collision with root package name */
        String f899a = "";

        public a(String str) {
            this.f899a = str;
        }

        public final boolean accept(File file) {
            return file.getName().startsWith(this.f899a);
        }
    }
}
