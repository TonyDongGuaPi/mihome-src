package com.xiaomi.mishopsdk.utils;

import android.app.Application;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.mishopsdk.util.FileUtil;
import com.xiaomi.mishopsdk.util.Log;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SoDefender {
    private static final String SO_DTOKEN = "DToken";
    private static final String SO_NATIVE = "Native";
    private static final String SO_SHOP_SIGNED = "ShopSigned";
    private static final String TAG = "SoDefender";
    private static String sDefenderDirPath;
    private static PathClassLoader sPathClassLoader;
    private static String sTimeConfiFilePath;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void defendLoadBaseLibrary(android.app.Application r4) {
        /*
            r0 = 1
            loadBaseLibrary()     // Catch:{ Throwable -> 0x0006 }
            r1 = 1
            goto L_0x000f
        L_0x0006:
            r1 = move-exception
            java.lang.String r2 = "SoDefender"
            java.lang.String r3 = "loadBaseLibrary failed."
            com.xiaomi.mishopsdk.util.Log.e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object) r1)
            r1 = 0
        L_0x000f:
            if (r1 != 0) goto L_0x0023
            initialize(r4)
            exactSosAndSetLibPath()
            loadBaseLibrary()     // Catch:{ Throwable -> 0x001b }
            goto L_0x0024
        L_0x001b:
            r4 = move-exception
            java.lang.String r0 = "SoDefender"
            java.lang.String r2 = "loadBaseLibrary failed2."
            com.xiaomi.mishopsdk.util.Log.e((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object) r4)
        L_0x0023:
            r0 = r1
        L_0x0024:
            if (r0 != 0) goto L_0x0029
            loadAllLibrary()
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.utils.SoDefender.defendLoadBaseLibrary(android.app.Application):void");
    }

    private static void loadBaseLibrary() {
        try {
            System.loadLibrary(SO_NATIVE);
            System.loadLibrary(SO_SHOP_SIGNED);
            System.loadLibrary(SO_DTOKEN);
        } catch (Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("loadBaseLibrary t:" + th.getMessage());
        }
    }

    private static boolean exactSosAndSetLibPath() {
        try {
            String path = sPathClassLoader.getResource(ShareConstants.K).getPath();
            String substring = path.substring(path.indexOf("/data/"), path.length() - 21);
            return (!shouldCopySoFiles(substring) || exactSoFiles(substring)) && trySetLibPath(sDefenderDirPath);
        } catch (Exception e) {
            Log.e(TAG, "get sPathClassLoader failed.", (Object) e);
        }
    }

    private static boolean exactSoFiles(String str) {
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(new File(str), 1);
            try {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    if (zipEntry.getName().endsWith(".so")) {
                        storeSoFile(zipFile, zipEntry);
                    }
                }
                recordCurApkModifyTime(str);
                FileUtil.closeQuietly((Closeable) null);
                FileUtil.closeQuietly(zipFile);
                return true;
            } catch (Exception e) {
                e = e;
                try {
                    Log.e(TAG, "exactSoFiles failed.", (Object) e);
                    FileUtil.closeQuietly((Closeable) null);
                    FileUtil.closeQuietly(zipFile);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    FileUtil.closeQuietly((Closeable) null);
                    FileUtil.closeQuietly(zipFile);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            zipFile = null;
            Log.e(TAG, "exactSoFiles failed.", (Object) e);
            FileUtil.closeQuietly((Closeable) null);
            FileUtil.closeQuietly(zipFile);
            return false;
        } catch (Throwable th2) {
            th = th2;
            zipFile = null;
            FileUtil.closeQuietly((Closeable) null);
            FileUtil.closeQuietly(zipFile);
            throw th;
        }
    }

    private static boolean storeSoFile(ZipFile zipFile, ZipEntry zipEntry) {
        OutputStream outputStream;
        InputStream inputStream;
        OutputStream outputStream2;
        OutputStream outputStream3;
        String name = zipEntry.getName();
        boolean z = false;
        BufferedInputStream bufferedInputStream = null;
        try {
            byte[] bArr = new byte[1024];
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                outputStream = new FileOutputStream(sDefenderDirPath + "/" + new File(name).getName());
            } catch (Exception e) {
                e = e;
                outputStream3 = null;
                outputStream2 = outputStream;
                try {
                    Log.e(TAG, "storeSoFile failed.", (Object) e);
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream2);
                    FileUtil.closeQuietly(outputStream);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream2);
                    FileUtil.closeQuietly(outputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
                outputStream2 = outputStream;
                FileUtil.closeQuietly(bufferedInputStream);
                FileUtil.closeQuietly(inputStream);
                FileUtil.closeQuietly(outputStream2);
                FileUtil.closeQuietly(outputStream);
                throw th;
            }
            try {
                outputStream2 = new BufferedOutputStream(outputStream);
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
                    while (true) {
                        try {
                            int read = bufferedInputStream2.read(bArr, 0, 1024);
                            if (read < 0) {
                                break;
                            }
                            outputStream2.write(bArr, 0, read);
                        } catch (Exception e2) {
                            e = e2;
                            bufferedInputStream = bufferedInputStream2;
                            Log.e(TAG, "storeSoFile failed.", (Object) e);
                            FileUtil.closeQuietly(bufferedInputStream);
                            FileUtil.closeQuietly(inputStream);
                            FileUtil.closeQuietly(outputStream2);
                            FileUtil.closeQuietly(outputStream);
                            return z;
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedInputStream = bufferedInputStream2;
                            FileUtil.closeQuietly(bufferedInputStream);
                            FileUtil.closeQuietly(inputStream);
                            FileUtil.closeQuietly(outputStream2);
                            FileUtil.closeQuietly(outputStream);
                            throw th;
                        }
                    }
                    z = true;
                    FileUtil.closeQuietly(bufferedInputStream2);
                } catch (Exception e3) {
                    e = e3;
                    Log.e(TAG, "storeSoFile failed.", (Object) e);
                    FileUtil.closeQuietly(bufferedInputStream);
                    FileUtil.closeQuietly(inputStream);
                    FileUtil.closeQuietly(outputStream2);
                    FileUtil.closeQuietly(outputStream);
                    return z;
                }
            } catch (Exception e4) {
                e = e4;
                outputStream2 = null;
                Log.e(TAG, "storeSoFile failed.", (Object) e);
                FileUtil.closeQuietly(bufferedInputStream);
                FileUtil.closeQuietly(inputStream);
                FileUtil.closeQuietly(outputStream2);
                FileUtil.closeQuietly(outputStream);
                return z;
            } catch (Throwable th4) {
                th = th4;
                outputStream2 = null;
                FileUtil.closeQuietly(bufferedInputStream);
                FileUtil.closeQuietly(inputStream);
                FileUtil.closeQuietly(outputStream2);
                FileUtil.closeQuietly(outputStream);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            inputStream = null;
            outputStream3 = null;
            outputStream2 = outputStream;
            Log.e(TAG, "storeSoFile failed.", (Object) e);
            FileUtil.closeQuietly(bufferedInputStream);
            FileUtil.closeQuietly(inputStream);
            FileUtil.closeQuietly(outputStream2);
            FileUtil.closeQuietly(outputStream);
            return z;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            outputStream = null;
            outputStream2 = outputStream;
            FileUtil.closeQuietly(bufferedInputStream);
            FileUtil.closeQuietly(inputStream);
            FileUtil.closeQuietly(outputStream2);
            FileUtil.closeQuietly(outputStream);
            throw th;
        }
        FileUtil.closeQuietly(inputStream);
        FileUtil.closeQuietly(outputStream2);
        FileUtil.closeQuietly(outputStream);
        return z;
    }

    private static void initialize(Application application) {
        sPathClassLoader = (PathClassLoader) SoDefender.class.getClassLoader();
        sDefenderDirPath = application.getDir("libDefender", 0).getAbsolutePath();
        sTimeConfiFilePath = sDefenderDirPath + "/apk_modify_time.cfg";
        File file = new File(sDefenderDirPath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    private static boolean trySetLibPath(String str) {
        try {
            Field declaredField = BaseDexClassLoader.class.getDeclaredField("pathList");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(sPathClassLoader);
            trySetNativeLibraryDirectories(obj, str);
            trySetNativeLibraryPathElements(obj, str);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "trySetLibPath failed.", (Object) e);
            return false;
        }
    }

    private static boolean trySetNativeLibraryDirectories(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField("nativeLibraryDirectories");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            if (obj2 instanceof File[]) {
                File[] fileArr = (File[]) obj2;
                File[] fileArr2 = new File[(fileArr.length + 1)];
                for (int i = 0; i < fileArr.length; i++) {
                    fileArr2[i] = fileArr[i];
                }
                fileArr2[fileArr.length] = new File(str);
                declaredField.set(obj, fileArr2);
            } else if (!(obj2 instanceof List)) {
                return false;
            } else {
                ((List) obj2).add(new File(str));
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "trySetNativeLibraryDirectories failed.", (Object) e);
            return false;
        }
    }

    private static boolean trySetNativeLibraryPathElements(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField("nativeLibraryPathElements");
            declaredField.setAccessible(true);
            Object[] objArr = (Object[]) declaredField.get(obj);
            Class<?> cls = Class.forName("dalvik.system.DexPathList$Element");
            Object newInstance = cls.getConstructor(new Class[]{File.class, Boolean.TYPE, File.class, DexFile.class}).newInstance(new Object[]{new File(str), true, null, null});
            Object newInstance2 = Array.newInstance(cls, objArr.length + 1);
            for (int i = 0; i < objArr.length; i++) {
                Array.set(newInstance2, i, objArr[i]);
            }
            Array.set(newInstance2, objArr.length, newInstance);
            declaredField.set(obj, newInstance2);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "trySetNativeLibraryPathElements failed.", (Object) e);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0078 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean shouldCopySoFiles(java.lang.String r10) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = sDefenderDirPath
            r1.append(r2)
            java.lang.String r2 = "/lib"
            r1.append(r2)
            java.lang.String r2 = "Native"
            r1.append(r2)
            java.lang.String r2 = ".so"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            boolean r0 = r0.isFile()
            r1 = 1
            if (r0 != 0) goto L_0x002a
            return r1
        L_0x002a:
            java.io.File r0 = new java.io.File
            java.lang.String r2 = sTimeConfiFilePath
            r0.<init>(r2)
            boolean r2 = r0.isFile()
            if (r2 != 0) goto L_0x0038
            return r1
        L_0x0038:
            long r2 = getApkModifyTime(r10)
            r4 = 0
            r10 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r6.<init>(r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            java.io.DataInputStream r0 = new java.io.DataInputStream     // Catch:{ Exception -> 0x005f }
            r0.<init>(r6)     // Catch:{ Exception -> 0x005f }
            long r7 = r0.readLong()     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r0)
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r6)
            r4 = r7
            goto L_0x0073
        L_0x0055:
            r10 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x007b
        L_0x005a:
            r10 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x0066
        L_0x005f:
            r0 = move-exception
            goto L_0x0066
        L_0x0061:
            r0 = move-exception
            r6 = r10
            goto L_0x007b
        L_0x0064:
            r0 = move-exception
            r6 = r10
        L_0x0066:
            java.lang.String r7 = "SoDefender"
            java.lang.String r8 = "read time cfg file failed."
            com.xiaomi.mishopsdk.util.Log.e((java.lang.String) r7, (java.lang.String) r8, (java.lang.Object) r0)     // Catch:{ all -> 0x007a }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r10)
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r6)
        L_0x0073:
            int r10 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r10 == 0) goto L_0x0078
            goto L_0x0079
        L_0x0078:
            r1 = 0
        L_0x0079:
            return r1
        L_0x007a:
            r0 = move-exception
        L_0x007b:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r10)
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.utils.SoDefender.shouldCopySoFiles(java.lang.String):boolean");
    }

    private static void recordCurApkModifyTime(String str) {
        FileOutputStream fileOutputStream;
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(new File(sTimeConfiFilePath));
            try {
                dataOutputStream = new DataOutputStream(fileOutputStream);
            } catch (Exception e) {
                e = e;
                try {
                    Log.e(TAG, "write time cfg file failed.", (Object) e);
                    FileUtil.closeQuietly(dataOutputStream2);
                    FileUtil.closeQuietly(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    FileUtil.closeQuietly(dataOutputStream2);
                    FileUtil.closeQuietly(fileOutputStream);
                    throw th;
                }
            }
            try {
                dataOutputStream.writeLong(getApkModifyTime(str));
                FileUtil.closeQuietly(dataOutputStream);
            } catch (Exception e2) {
                e = e2;
                dataOutputStream2 = dataOutputStream;
                Log.e(TAG, "write time cfg file failed.", (Object) e);
                FileUtil.closeQuietly(dataOutputStream2);
                FileUtil.closeQuietly(fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream2 = dataOutputStream;
                FileUtil.closeQuietly(dataOutputStream2);
                FileUtil.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
            Log.e(TAG, "write time cfg file failed.", (Object) e);
            FileUtil.closeQuietly(dataOutputStream2);
            FileUtil.closeQuietly(fileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            FileUtil.closeQuietly(dataOutputStream2);
            FileUtil.closeQuietly(fileOutputStream);
            throw th;
        }
        FileUtil.closeQuietly(fileOutputStream);
    }

    private static long getApkModifyTime(String str) {
        return new File(str).lastModified();
    }

    private static void loadAllLibrary() {
        File file = new File(sDefenderDirPath);
        if (file.isDirectory()) {
            doLoadAllLibrary(file);
        }
    }

    private static void doLoadAllLibrary(File file) {
        for (File file2 : file.listFiles()) {
            if (file2.isFile() && file2.getName().endsWith(".so") && !"libfb.so".equals(file2.getName()) && !"libweexv8.so".equals(file2.getName())) {
                String absolutePath = file2.getAbsolutePath();
                try {
                    System.load(absolutePath);
                } catch (Throwable th) {
                    Log.e(TAG, "load so %s failed", absolutePath, th);
                }
            }
        }
    }
}
