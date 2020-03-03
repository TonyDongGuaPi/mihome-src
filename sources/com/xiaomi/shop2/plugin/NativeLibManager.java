package com.xiaomi.shop2.plugin;

import android.text.TextUtils;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.FileUtil;
import com.xiaomi.mishopsdk.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class NativeLibManager {
    private static final String APK_NATIVE_ABI_ARM_PATH = "lib/armeabi";
    public static final String PLUGIN_NATIVE_PATH = "pluginLib";
    private static final String PLUGIN_SUFFIX = ".zip";
    private static final String TAG = "NativeLibManager";

    public static String getPluginNativePath(PluginInfo pluginInfo) {
        if (!pluginInfo.hasSo) {
            return null;
        }
        String initPath = initPath(pluginInfo);
        if (exactSoFiles(pluginInfo.getRestoredPath(), initPath)) {
            return initPath;
        }
        return null;
    }

    private static String initPath(PluginInfo pluginInfo) {
        String replace = FileUtil.getFileName(pluginInfo.localPath).replace(PLUGIN_SUFFIX, "");
        if (TextUtils.isEmpty(replace)) {
            return null;
        }
        File dir = ShopApp.instance.getDir(PLUGIN_NATIVE_PATH, 0);
        return dir.getAbsolutePath() + replace;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean exactSoFiles(java.lang.String r7, java.lang.String r8) {
        /*
            r0 = 1
            r1 = 0
            r2 = 19
            java.util.zip.ZipFile r3 = new java.util.zip.ZipFile     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            r4.<init>(r7)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            r3.<init>(r4, r0)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            java.util.Enumeration r7 = r3.entries()     // Catch:{ Exception -> 0x0045 }
        L_0x0012:
            boolean r4 = r7.hasMoreElements()     // Catch:{ Exception -> 0x0045 }
            if (r4 == 0) goto L_0x0036
            java.lang.Object r4 = r7.nextElement()     // Catch:{ Exception -> 0x0045 }
            java.util.zip.ZipEntry r4 = (java.util.zip.ZipEntry) r4     // Catch:{ Exception -> 0x0045 }
            java.lang.String r5 = r4.getName()     // Catch:{ Exception -> 0x0045 }
            java.lang.String r6 = "lib/armeabi"
            boolean r6 = r5.contains(r6)     // Catch:{ Exception -> 0x0045 }
            if (r6 == 0) goto L_0x0012
            java.lang.String r6 = ".so"
            boolean r5 = r5.endsWith(r6)     // Catch:{ Exception -> 0x0045 }
            if (r5 == 0) goto L_0x0012
            storeSoFile(r3, r4, r8)     // Catch:{ Exception -> 0x0045 }
            goto L_0x0012
        L_0x0036:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r1)
            int r7 = android.os.Build.VERSION.SDK_INT
            if (r7 < r2) goto L_0x0041
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r3)
            goto L_0x0064
        L_0x0041:
            r3.close()     // Catch:{ Exception -> 0x0064 }
            goto L_0x0064
        L_0x0045:
            r7 = move-exception
            goto L_0x004c
        L_0x0047:
            r7 = move-exception
            r3 = r1
            goto L_0x0066
        L_0x004a:
            r7 = move-exception
            r3 = r1
        L_0x004c:
            java.lang.String r8 = TAG     // Catch:{ all -> 0x0065 }
            java.lang.String r0 = "exactSoFiles failed."
            com.xiaomi.mishopsdk.util.Log.e((java.lang.String) r8, (java.lang.String) r0, (java.lang.Object) r7)     // Catch:{ all -> 0x0065 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r1)
            int r7 = android.os.Build.VERSION.SDK_INT
            if (r7 < r2) goto L_0x005e
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r3)
            goto L_0x0063
        L_0x005e:
            if (r3 == 0) goto L_0x0063
            r3.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            r0 = 0
        L_0x0064:
            return r0
        L_0x0065:
            r7 = move-exception
        L_0x0066:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r1)
            int r8 = android.os.Build.VERSION.SDK_INT
            if (r8 >= r2) goto L_0x0073
            if (r3 == 0) goto L_0x0076
            r3.close()     // Catch:{ Exception -> 0x0076 }
            goto L_0x0076
        L_0x0073:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r3)
        L_0x0076:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativeLibManager.exactSoFiles(java.lang.String, java.lang.String):boolean");
    }

    public static boolean storeSoFile(ZipFile zipFile, ZipEntry zipEntry, String str) {
        OutputStream outputStream;
        InputStream inputStream;
        OutputStream outputStream2;
        OutputStream outputStream3;
        OutputStream fileOutputStream;
        String name = zipEntry.getName();
        BufferedInputStream bufferedInputStream = null;
        try {
            byte[] bArr = new byte[1024];
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                FileUtil.checkOrCreateFolder(str);
                File file = new File(str + File.separator + new File(name).getName());
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e) {
                e = e;
                outputStream3 = null;
                outputStream2 = outputStream3;
                try {
                    Log.e(TAG, "storeSoFile failed.", (Object) e);
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream);
                    FileUtil.closeQuietly(outputStream2);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream);
                    FileUtil.closeQuietly(outputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
                outputStream2 = outputStream;
                FileUtil.closeQuietly(bufferedInputStream);
                FileUtil.closeQuietly(inputStream);
                FileUtil.closeQuietly(outputStream);
                FileUtil.closeQuietly(outputStream2);
                throw th;
            }
            try {
                OutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
                    while (true) {
                        try {
                            int read = bufferedInputStream2.read(bArr, 0, 1024);
                            if (read >= 0) {
                                bufferedOutputStream.write(bArr, 0, read);
                            } else {
                                FileUtil.closeQuietly(bufferedInputStream2);
                                FileUtil.closeQuietly(inputStream);
                                FileUtil.closeQuietly(bufferedOutputStream);
                                FileUtil.closeQuietly(fileOutputStream);
                                return true;
                            }
                        } catch (Exception e2) {
                            bufferedInputStream = bufferedInputStream2;
                            outputStream2 = fileOutputStream;
                            outputStream3 = bufferedOutputStream;
                            e = e2;
                            Log.e(TAG, "storeSoFile failed.", (Object) e);
                            FileUtil.closeQuietly(bufferedInputStream);
                            FileUtil.closeQuietly(inputStream);
                            FileUtil.closeQuietly(outputStream);
                            FileUtil.closeQuietly(outputStream2);
                            return false;
                        } catch (Throwable th3) {
                            bufferedInputStream = bufferedInputStream2;
                            outputStream2 = fileOutputStream;
                            outputStream = bufferedOutputStream;
                            th = th3;
                            FileUtil.closeQuietly(bufferedInputStream);
                            FileUtil.closeQuietly(inputStream);
                            FileUtil.closeQuietly(outputStream);
                            FileUtil.closeQuietly(outputStream2);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    OutputStream outputStream4 = fileOutputStream;
                    outputStream3 = bufferedOutputStream;
                    e = e3;
                    outputStream2 = outputStream4;
                    Log.e(TAG, "storeSoFile failed.", (Object) e);
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream);
                    FileUtil.closeQuietly(outputStream2);
                    return false;
                } catch (Throwable th4) {
                    OutputStream outputStream5 = fileOutputStream;
                    outputStream = bufferedOutputStream;
                    th = th4;
                    outputStream2 = outputStream5;
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream);
                    FileUtil.closeQuietly(outputStream2);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                outputStream2 = fileOutputStream;
                outputStream3 = null;
                Log.e(TAG, "storeSoFile failed.", (Object) e);
                FileUtil.closeQuietly(bufferedInputStream);
                FileUtil.closeQuietly(inputStream);
                FileUtil.closeQuietly(outputStream);
                FileUtil.closeQuietly(outputStream2);
                return false;
            } catch (Throwable th5) {
                th = th5;
                outputStream2 = fileOutputStream;
                outputStream = null;
                FileUtil.closeQuietly(bufferedInputStream);
                FileUtil.closeQuietly(inputStream);
                FileUtil.closeQuietly(outputStream);
                FileUtil.closeQuietly(outputStream2);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            inputStream = null;
            outputStream3 = null;
            outputStream2 = outputStream3;
            Log.e(TAG, "storeSoFile failed.", (Object) e);
            FileUtil.closeQuietly(bufferedInputStream);
            FileUtil.closeQuietly(inputStream);
            FileUtil.closeQuietly(outputStream);
            FileUtil.closeQuietly(outputStream2);
            return false;
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
            outputStream = null;
            outputStream2 = outputStream;
            FileUtil.closeQuietly(bufferedInputStream);
            FileUtil.closeQuietly(inputStream);
            FileUtil.closeQuietly(outputStream);
            FileUtil.closeQuietly(outputStream2);
            throw th;
        }
    }
}
