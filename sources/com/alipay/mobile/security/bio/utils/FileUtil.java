package com.alipay.mobile.security.bio.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class FileUtil {
    public static final int STREAM_BUFFER_SIZE = 8192;

    public static synchronized byte[] toByteArray(String str) {
        byte[] byteArray;
        synchronized (FileUtil.class) {
            File file = new File(str);
            if (file.exists()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
                BufferedInputStream bufferedInputStream = null;
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream2.read(bArr, 0, 1024);
                            if (-1 != read) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            } else {
                                byteArray = byteArrayOutputStream.toByteArray();
                                InputStreamUtils.close(bufferedInputStream2);
                                OutputStreamUtils.close(byteArrayOutputStream);
                            }
                        }
                    } catch (IOException e) {
                        e = e;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            BioLog.e(e.toString());
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            InputStreamUtils.close(bufferedInputStream);
                            OutputStreamUtils.close(byteArrayOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedInputStream = bufferedInputStream2;
                        InputStreamUtils.close(bufferedInputStream);
                        OutputStreamUtils.close(byteArrayOutputStream);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    BioLog.e(e.toString());
                    throw e;
                }
            } else {
                throw new FileNotFoundException(str);
            }
        }
        return byteArray;
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void bitmap2File(Bitmap bitmap, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            BioLog.w((Throwable) e);
        } catch (IOException e2) {
            BioLog.w((Throwable) e2);
        }
    }

    public static byte[] getAssetsData(Context context, String str) {
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(str);
            try {
                byte[] input2byte = InputStreamUtils.input2byte(inputStream);
                InputStreamUtils.close(inputStream);
                return input2byte;
            } catch (IOException e) {
                e = e;
                try {
                    BioLog.e((Throwable) e);
                    InputStreamUtils.close(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    InputStreamUtils.close(inputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            BioLog.e((Throwable) e);
            InputStreamUtils.close(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            InputStreamUtils.close(inputStream);
            throw th;
        }
    }

    public static byte[] getAssetsData(Resources resources, String str) {
        InputStream inputStream;
        try {
            inputStream = resources.getAssets().open(str);
            try {
                byte[] input2byte = InputStreamUtils.input2byte(inputStream);
                InputStreamUtils.close(inputStream);
                return input2byte;
            } catch (IOException e) {
                e = e;
                try {
                    BioLog.e((Throwable) e);
                    InputStreamUtils.close(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    InputStreamUtils.close(inputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            BioLog.e((Throwable) e);
            InputStreamUtils.close(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            InputStreamUtils.close(inputStream);
            throw th;
        }
    }

    public static byte[] getRawData(Context context, int i) {
        InputStream inputStream;
        try {
            inputStream = context.getResources().openRawResource(i);
            try {
                byte[] input2byte = InputStreamUtils.input2byte(inputStream);
                InputStreamUtils.close(inputStream);
                return input2byte;
            } catch (IOException e) {
                e = e;
                try {
                    BioLog.e(e.toString());
                    InputStreamUtils.close(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    InputStreamUtils.close(inputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            BioLog.e(e.toString());
            InputStreamUtils.close(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            InputStreamUtils.close(inputStream);
            throw th;
        }
    }

    public static synchronized boolean save(File file, byte[] bArr) {
        boolean z;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        synchronized (FileUtil.class) {
            z = false;
            if (!(file == null || bArr == null)) {
                if (file.exists()) {
                    file.delete();
                } else {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        BioLog.e(e.toString());
                    }
                }
                BufferedOutputStream bufferedOutputStream2 = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            BioLog.e(e.toString());
                            OutputStreamUtils.close(bufferedOutputStream2);
                            OutputStreamUtils.close(fileOutputStream);
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            OutputStreamUtils.close(bufferedOutputStream2);
                            OutputStreamUtils.close(fileOutputStream);
                            throw th;
                        }
                    }
                    try {
                        bufferedOutputStream.write(bArr);
                        bufferedOutputStream.flush();
                        z = true;
                        OutputStreamUtils.close(bufferedOutputStream);
                    } catch (IOException e3) {
                        e = e3;
                        bufferedOutputStream2 = bufferedOutputStream;
                        BioLog.e(e.toString());
                        OutputStreamUtils.close(bufferedOutputStream2);
                        OutputStreamUtils.close(fileOutputStream);
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        OutputStreamUtils.close(bufferedOutputStream2);
                        OutputStreamUtils.close(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    fileOutputStream = null;
                    BioLog.e(e.toString());
                    OutputStreamUtils.close(bufferedOutputStream2);
                    OutputStreamUtils.close(fileOutputStream);
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    OutputStreamUtils.close(bufferedOutputStream2);
                    OutputStreamUtils.close(fileOutputStream);
                    throw th;
                }
                OutputStreamUtils.close(fileOutputStream);
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0059 A[SYNTHETIC, Splitter:B:34:0x0059] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0067 A[SYNTHETIC, Splitter:B:42:0x0067] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void saveContent(java.io.File r5, java.lang.String r6, boolean r7) {
        /*
            java.lang.Class<com.alipay.mobile.security.bio.utils.FileUtil> r0 = com.alipay.mobile.security.bio.utils.FileUtil.class
            monitor-enter(r0)
            if (r5 != 0) goto L_0x000e
            if (r6 == 0) goto L_0x0008
            goto L_0x000e
        L_0x0008:
            com.alipay.mobile.security.bio.exception.BioIllegalArgumentException r5 = new com.alipay.mobile.security.bio.exception.BioIllegalArgumentException     // Catch:{ all -> 0x0074 }
            r5.<init>()     // Catch:{ all -> 0x0074 }
            throw r5     // Catch:{ all -> 0x0074 }
        L_0x000e:
            boolean r1 = r5.exists()     // Catch:{ all -> 0x0074 }
            if (r1 != 0) goto L_0x0027
            java.io.File r1 = r5.getParentFile()     // Catch:{ IOException -> 0x001f }
            r1.mkdirs()     // Catch:{ IOException -> 0x001f }
            r5.createNewFile()     // Catch:{ IOException -> 0x001f }
            goto L_0x0027
        L_0x001f:
            r1 = move-exception
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0074 }
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r1)     // Catch:{ all -> 0x0074 }
        L_0x0027:
            r1 = 0
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x004f }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x004f }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x004f }
            r4.<init>(r5, r7)     // Catch:{ Exception -> 0x004f }
            r3.<init>(r4)     // Catch:{ Exception -> 0x004f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x004f }
            r2.write(r6)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            r2.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0063
        L_0x003e:
            r5 = move-exception
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0074 }
        L_0x0043:
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r5)     // Catch:{ all -> 0x0074 }
            goto L_0x0063
        L_0x0047:
            r5 = move-exception
            r1 = r2
            goto L_0x0065
        L_0x004a:
            r5 = move-exception
            r1 = r2
            goto L_0x0050
        L_0x004d:
            r5 = move-exception
            goto L_0x0065
        L_0x004f:
            r5 = move-exception
        L_0x0050:
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x004d }
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r5)     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0063
            r1.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x0063
        L_0x005d:
            r5 = move-exception
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0074 }
            goto L_0x0043
        L_0x0063:
            monitor-exit(r0)
            return
        L_0x0065:
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0073
        L_0x006b:
            r6 = move-exception
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0074 }
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r6)     // Catch:{ all -> 0x0074 }
        L_0x0073:
            throw r5     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.utils.FileUtil.saveContent(java.io.File, java.lang.String, boolean):void");
    }

    public static void recursionDeleteFile(File file) {
        if (file != null) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    file.delete();
                    return;
                }
                for (File recursionDeleteFile : listFiles) {
                    recursionDeleteFile(recursionDeleteFile);
                }
                file.delete();
            }
        }
    }

    public static void copyDirectory(String str, String str2) {
        File file = new File(str);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                String str3 = str + File.separator + listFiles[i].getName();
                String str4 = str2 + File.separator + listFiles[i].getName();
                File file2 = new File(str2);
                if (!file2.exists()) {
                    file2.mkdir();
                }
                copyDirectory(str3, str4);
            }
        } else if (file.isFile()) {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(str)));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
            byte[] bArr = new byte[524288];
            while (dataInputStream.read(bArr) != -1) {
                dataOutputStream.write(bArr);
            }
            dataInputStream.close();
            dataOutputStream.close();
        } else {
            BioLog.e("please input correct path!");
        }
    }

    public static File extractAssets(Context context, String str) {
        AssetManager assets = context.getAssets();
        File file = new File(context.getFilesDir().getAbsolutePath());
        file.mkdirs();
        File file2 = new File(file, str);
        try {
            if (assets.list(str) != null) {
                streamToStream(assets.open(str), new FileOutputStream(file2));
            }
        } catch (IOException e) {
            BioLog.w("ZCAuth", "extractAssets: ", e);
        }
        return file2;
    }

    public static boolean streamToStream(InputStream inputStream, OutputStream outputStream) {
        boolean z = false;
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            z = true;
        } catch (Exception e) {
            BioLog.w("ContentValues", "streamToStream(InputStream, OutputStream): Exception occur.", e);
        } catch (Throwable th) {
            closeSafely(outputStream);
            closeSafely(inputStream);
            throw th;
        }
        closeSafely(outputStream);
        closeSafely(inputStream);
        return z;
    }

    public static void closeSafely(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                BioLog.w("ContentValues", "closeSafely(Closeable): Exception occur.", e);
            }
        }
    }

    public static String assetsToString(Context context, String str) {
        try {
            Scanner useDelimiter = new Scanner(context.getAssets().open(str)).useDelimiter("\\A");
            return useDelimiter.hasNext() ? useDelimiter.next() : "";
        } catch (IOException e) {
            BioLog.w("ZCAuth", "extractAssets: ", e);
            return null;
        }
    }
}
