package com.xiaomi.smarthome.core.server.internal.plugin;

import android.content.Context;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.youpin.PackageUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginSoManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14679a = "armeabi";
    public static final String b = "armeabi-v7a";
    public static final String c = "arm64-v8a";
    public static final Map<String, String[]> d = new HashMap();
    private static final Object e = new Object();
    private static PluginSoManager f;
    private static String g = b();

    static {
        d.put("arm", new String[]{"armeabi", "armeabi-v7a"});
        d.put(PackageUtils.d, new String[]{PackageUtils.d});
        d.put("mips64", new String[]{"mips64"});
        d.put(PackageUtils.c, new String[]{PackageUtils.c});
        d.put("x86_64", new String[]{"x86_64"});
        d.put("arm64", new String[]{c});
    }

    private PluginSoManager() {
    }

    public static PluginSoManager a() {
        if (f == null) {
            synchronized (e) {
                if (f == null) {
                    f = new PluginSoManager();
                }
            }
        }
        return f;
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a2 : listFiles) {
                    a(a2);
                }
                file.delete();
                return;
            }
            return;
        }
        file.delete();
    }

    public void a(Context context, long j, long j2) {
        File file = new File(PluginSetting.c(context) + File.separator + j + File.separator + j2);
        if (file.exists() && file.isDirectory()) {
            a(file);
        }
    }

    public void a(Context context, long j, long j2, String str) {
        System.currentTimeMillis();
        b();
        new CopySoTask(str, PluginSetting.b(context, j, j2)).run();
        System.currentTimeMillis();
    }

    public static String b() {
        try {
            String str = (String) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getCurrentInstructionSet", new Class[0]).invoke((Object) null, new Object[0]);
            if (GlobalSetting.u) {
                LogUtilGrey.a("PluginSoManager", "getCurrentInstructionSet:" + str);
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtilGrey.a("PluginSoManager", "getCurrentInstructionSet exception " + e2.getMessage());
            return "";
        }
    }

    public static String b(Context context, long j, long j2) {
        String[] strArr = d.get(g);
        if (strArr == null) {
            return null;
        }
        for (String a2 : strArr) {
            String a3 = PluginSetting.a(context, j, j2, a2);
            if (new File(a3).exists()) {
                return a3;
            }
        }
        return null;
    }

    private class CopySoTask implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        String f14680a;
        String b;

        CopySoTask(String str, String str2) {
            this.f14680a = str;
            this.b = str2;
        }

        private String a(String str) {
            return str.substring(str.lastIndexOf("/") + 1);
        }

        private void a(ZipFile zipFile, ZipEntry zipEntry, String str, String str2) {
            FileOutputStream fileOutputStream;
            InputStream inputStream;
            try {
                inputStream = zipFile.getInputStream(zipEntry);
                try {
                    fileOutputStream = new FileOutputStream(new File(this.b + File.separator + str, str2));
                } catch (IOException e) {
                    e = e;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                CrashReport.postCatchedException(e);
                fileOutputStream = null;
                a(inputStream, fileOutputStream);
            }
            a(inputStream, fileOutputStream);
        }

        public void a(InputStream inputStream, OutputStream outputStream) {
            if (inputStream != null && outputStream != null) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                byte[] bArr = null;
                try {
                    bArr = new byte[a((InputStream) bufferedInputStream)];
                } catch (IOException e) {
                    CrashReport.postCatchedException(e);
                }
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                    } catch (IOException e2) {
                        CrashReport.postCatchedException(e2);
                    }
                    try {
                        break;
                    } catch (IOException e3) {
                        CrashReport.postCatchedException(e3);
                        return;
                    }
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
            }
        }

        private int a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return 0;
            }
            int available = inputStream.available();
            if (available <= 0) {
                return 1024;
            }
            return available;
        }

        private void b(String str) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:34:0x00ee A[SYNTHETIC, Splitter:B:34:0x00ee] */
        /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                java.io.File r0 = new java.io.File
                java.lang.String r1 = r7.b
                r0.<init>(r1)
                r0.mkdirs()
                java.lang.String r0 = "PluginSoManager"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "write file path "
                r1.append(r2)
                java.lang.String r2 = r7.b
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                com.tencent.bugly.crashreport.BuglyLog.d(r0, r1)
                r0 = 0
                java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x00e0, all -> 0x00db }
                java.lang.String r2 = r7.f14680a     // Catch:{ IOException -> 0x00e0, all -> 0x00db }
                r1.<init>(r2)     // Catch:{ IOException -> 0x00e0, all -> 0x00db }
                java.util.Enumeration r0 = r1.entries()     // Catch:{ IOException -> 0x00d9 }
            L_0x002e:
                boolean r2 = r0.hasMoreElements()     // Catch:{ IOException -> 0x00d9 }
                if (r2 == 0) goto L_0x00d5
                java.lang.Object r2 = r0.nextElement()     // Catch:{ IOException -> 0x00d9 }
                java.util.zip.ZipEntry r2 = (java.util.zip.ZipEntry) r2     // Catch:{ IOException -> 0x00d9 }
                boolean r3 = r2.isDirectory()     // Catch:{ IOException -> 0x00d9 }
                if (r3 == 0) goto L_0x0041
                goto L_0x002e
            L_0x0041:
                java.lang.String r3 = r2.getName()     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = ".so"
                boolean r4 = r3.endsWith(r4)     // Catch:{ IOException -> 0x00d9 }
                if (r4 == 0) goto L_0x002e
                java.lang.String r4 = "arm64-v8a"
                boolean r4 = r3.contains(r4)     // Catch:{ IOException -> 0x00d9 }
                if (r4 == 0) goto L_0x007a
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d9 }
                r4.<init>()     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = r7.b     // Catch:{ IOException -> 0x00d9 }
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = java.io.File.separator     // Catch:{ IOException -> 0x00d9 }
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = "arm64-v8a"
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00d9 }
                r7.b(r4)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = "arm64-v8a"
                java.lang.String r3 = r7.a((java.lang.String) r3)     // Catch:{ IOException -> 0x00d9 }
                r7.a(r1, r2, r4, r3)     // Catch:{ IOException -> 0x00d9 }
                goto L_0x002e
            L_0x007a:
                java.lang.String r4 = "armeabi-v7a"
                boolean r4 = r3.contains(r4)     // Catch:{ IOException -> 0x00d9 }
                if (r4 == 0) goto L_0x00a7
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d9 }
                r4.<init>()     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = r7.b     // Catch:{ IOException -> 0x00d9 }
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = java.io.File.separator     // Catch:{ IOException -> 0x00d9 }
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = "armeabi-v7a"
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00d9 }
                r7.b(r4)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = "armeabi-v7a"
                java.lang.String r3 = r7.a((java.lang.String) r3)     // Catch:{ IOException -> 0x00d9 }
                r7.a(r1, r2, r4, r3)     // Catch:{ IOException -> 0x00d9 }
                goto L_0x002e
            L_0x00a7:
                java.lang.String r4 = "armeabi"
                boolean r4 = r3.contains(r4)     // Catch:{ IOException -> 0x00d9 }
                if (r4 == 0) goto L_0x002e
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d9 }
                r4.<init>()     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = r7.b     // Catch:{ IOException -> 0x00d9 }
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = java.io.File.separator     // Catch:{ IOException -> 0x00d9 }
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r5 = "armeabi"
                r4.append(r5)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00d9 }
                r7.b(r4)     // Catch:{ IOException -> 0x00d9 }
                java.lang.String r4 = "armeabi"
                java.lang.String r3 = r7.a((java.lang.String) r3)     // Catch:{ IOException -> 0x00d9 }
                r7.a(r1, r2, r4, r3)     // Catch:{ IOException -> 0x00d9 }
                goto L_0x002e
            L_0x00d5:
                r1.close()     // Catch:{ Exception -> 0x00ea }
                goto L_0x00ea
            L_0x00d9:
                r0 = move-exception
                goto L_0x00e4
            L_0x00db:
                r1 = move-exception
                r6 = r1
                r1 = r0
                r0 = r6
                goto L_0x00ec
            L_0x00e0:
                r1 = move-exception
                r6 = r1
                r1 = r0
                r0 = r6
            L_0x00e4:
                com.tencent.bugly.crashreport.CrashReport.postCatchedException(r0)     // Catch:{ all -> 0x00eb }
                if (r1 == 0) goto L_0x00ea
                goto L_0x00d5
            L_0x00ea:
                return
            L_0x00eb:
                r0 = move-exception
            L_0x00ec:
                if (r1 == 0) goto L_0x00f1
                r1.close()     // Catch:{ Exception -> 0x00f1 }
            L_0x00f1:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager.CopySoTask.run():void");
        }
    }
}
