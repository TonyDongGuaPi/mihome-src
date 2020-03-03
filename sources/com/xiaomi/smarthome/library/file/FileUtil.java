package com.xiaomi.smarthome.library.file;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {
    public static int a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return 0;
        }
        return a(file);
    }

    public static int a(File file) {
        File[] listFiles;
        if (file == null || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return 0;
        }
        int i = 0;
        for (File isHidden : listFiles) {
            if (!isHidden.isHidden()) {
                i++;
            }
        }
        return i;
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : "";
    }

    public static String c(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1) : str;
    }

    public static String d(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public static String e(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1) : "";
    }

    public static String f(String str) {
        return e(c(str));
    }

    public static Bitmap a(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i4 = options.outWidth / i;
        int i5 = options.outHeight / i2;
        if (i4 >= i5) {
            i4 = i5;
        }
        if (i4 > 0) {
            i3 = i4;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i3;
        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(str, options), i, i2, 2);
    }

    public static boolean g(String str) {
        return str != null && str.length() > 50;
    }

    @TargetApi(19)
    public static String a(Context context, Uri uri) {
        Uri uri2 = null;
        if (uri != null) {
            if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
                if ("content".equalsIgnoreCase(uri.getScheme())) {
                    return a(context, uri, (String) null, (String[]) null);
                }
                if ("file".equalsIgnoreCase(uri.getScheme())) {
                    return uri.getPath();
                }
            } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str = split[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return a(context, uri2, "_id=?", new String[]{split[1]});
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r0 = "_data"
            r1 = 0
            r3[r1] = r0
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ all -> 0x0035 }
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0035 }
            if (r7 == 0) goto L_0x002f
            boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x002d }
            if (r8 == 0) goto L_0x002f
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x002d }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ all -> 0x002d }
            if (r7 == 0) goto L_0x002c
            r7.close()
        L_0x002c:
            return r8
        L_0x002d:
            r8 = move-exception
            goto L_0x0037
        L_0x002f:
            if (r7 == 0) goto L_0x0034
            r7.close()
        L_0x0034:
            return r0
        L_0x0035:
            r8 = move-exception
            r7 = r0
        L_0x0037:
            if (r7 == 0) goto L_0x003c
            r7.close()
        L_0x003c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.file.FileUtil.a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static File b(Context context, Uri uri) {
        String a2 = a(context, uri);
        if (!TextUtils.isEmpty(a2)) {
            return new File(a2);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = com.xiaomi.smarthome.library.mime.MimeUtils.b(e(r0.getName()));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(android.content.Context r0, android.net.Uri r1) {
        /*
            java.io.File r0 = b(r0, r1)
            if (r0 == 0) goto L_0x0015
            java.lang.String r0 = r0.getName()
            java.lang.String r0 = e(r0)
            java.lang.String r0 = com.xiaomi.smarthome.library.mime.MimeUtils.b(r0)
            if (r0 == 0) goto L_0x0015
            return r0
        L_0x0015:
            java.lang.String r0 = "application/octet-stream"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.file.FileUtil.c(android.content.Context, android.net.Uri):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r1 = com.xiaomi.smarthome.library.mime.MimeUtils.b(e(r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String h(java.lang.String r1) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x001b
            java.lang.String r1 = c((java.lang.String) r1)
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x001b
            java.lang.String r1 = e(r1)
            java.lang.String r1 = com.xiaomi.smarthome.library.mime.MimeUtils.b(r1)
            if (r1 == 0) goto L_0x001b
            return r1
        L_0x001b:
            java.lang.String r1 = "application/octet-stream"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.file.FileUtil.h(java.lang.String):java.lang.String");
    }

    public static void a(Context context, Uri uri, byte[] bArr) throws IOException {
        File b = b(context, uri);
        if (b != null) {
            a(b, bArr);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r3, byte[] r4) throws java.io.IOException {
        /*
            r0 = 0
            boolean r1 = r3.exists()     // Catch:{ all -> 0x0079 }
            if (r1 == 0) goto L_0x005d
            boolean r1 = r3.isDirectory()     // Catch:{ all -> 0x0079 }
            if (r1 != 0) goto L_0x0041
            boolean r1 = r3.canRead()     // Catch:{ all -> 0x0079 }
            if (r1 == 0) goto L_0x0025
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0079 }
            r1.<init>(r3)     // Catch:{ all -> 0x0079 }
            r1.write(r4)     // Catch:{ all -> 0x0022 }
            r1.flush()     // Catch:{ all -> 0x0022 }
            r1.close()
            return
        L_0x0022:
            r3 = move-exception
            r0 = r1
            goto L_0x007a
        L_0x0025:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0079 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0079 }
            r1.<init>()     // Catch:{ all -> 0x0079 }
            java.lang.String r2 = "File '"
            r1.append(r2)     // Catch:{ all -> 0x0079 }
            r1.append(r3)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = "' cannot be read"
            r1.append(r3)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0079 }
            r4.<init>(r3)     // Catch:{ all -> 0x0079 }
            throw r4     // Catch:{ all -> 0x0079 }
        L_0x0041:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0079 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0079 }
            r1.<init>()     // Catch:{ all -> 0x0079 }
            java.lang.String r2 = "File '"
            r1.append(r2)     // Catch:{ all -> 0x0079 }
            r1.append(r3)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = "' exists but is a directory"
            r1.append(r3)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0079 }
            r4.<init>(r3)     // Catch:{ all -> 0x0079 }
            throw r4     // Catch:{ all -> 0x0079 }
        L_0x005d:
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException     // Catch:{ all -> 0x0079 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0079 }
            r1.<init>()     // Catch:{ all -> 0x0079 }
            java.lang.String r2 = "File '"
            r1.append(r2)     // Catch:{ all -> 0x0079 }
            r1.append(r3)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = "' does not exist"
            r1.append(r3)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0079 }
            r4.<init>(r3)     // Catch:{ all -> 0x0079 }
            throw r4     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r3 = move-exception
        L_0x007a:
            if (r0 == 0) goto L_0x007f
            r0.close()
        L_0x007f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.file.FileUtil.a(java.io.File, byte[]):void");
    }

    public static byte[] d(Context context, Uri uri) throws IOException {
        File b = b(context, uri);
        if (b != null) {
            return c(b);
        }
        return new byte[0];
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] c(java.io.File r5) throws java.io.IOException {
        /*
            java.io.FileInputStream r0 = d((java.io.File) r5)     // Catch:{ all -> 0x006a }
            long r1 = r5.length()     // Catch:{ all -> 0x0068 }
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0051
            int r5 = (int) r1     // Catch:{ all -> 0x0068 }
            r1 = 0
            if (r5 != 0) goto L_0x001b
            byte[] r5 = new byte[r1]     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x001a
            r0.close()
        L_0x001a:
            return r5
        L_0x001b:
            byte[] r2 = new byte[r5]     // Catch:{ all -> 0x0068 }
        L_0x001d:
            if (r1 >= r5) goto L_0x002a
            int r3 = r5 - r1
            int r3 = r0.read(r2, r1, r3)     // Catch:{ all -> 0x0068 }
            r4 = -1
            if (r3 == r4) goto L_0x002a
            int r1 = r1 + r3
            goto L_0x001d
        L_0x002a:
            if (r1 != r5) goto L_0x0032
            if (r0 == 0) goto L_0x0031
            r0.close()
        L_0x0031:
            return r2
        L_0x0032:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r3.<init>()     // Catch:{ all -> 0x0068 }
            java.lang.String r4 = "Unexpected read size. current: "
            r3.append(r4)     // Catch:{ all -> 0x0068 }
            r3.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = ", expected: "
            r3.append(r1)     // Catch:{ all -> 0x0068 }
            r3.append(r5)     // Catch:{ all -> 0x0068 }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x0068 }
            r2.<init>(r5)     // Catch:{ all -> 0x0068 }
            throw r2     // Catch:{ all -> 0x0068 }
        L_0x0051:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r3.<init>()     // Catch:{ all -> 0x0068 }
            java.lang.String r4 = "Size cannot be greater than Integer max value: "
            r3.append(r4)     // Catch:{ all -> 0x0068 }
            r3.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0068 }
            r5.<init>(r1)     // Catch:{ all -> 0x0068 }
            throw r5     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r5 = move-exception
            goto L_0x006c
        L_0x006a:
            r5 = move-exception
            r0 = 0
        L_0x006c:
            if (r0 == 0) goto L_0x0071
            r0.close()
        L_0x0071:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.file.FileUtil.c(java.io.File):byte[]");
    }

    public static boolean b(File file) {
        return file != null && i(file.getAbsolutePath());
    }

    public static boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File("" + System.currentTimeMillis());
        file.renameTo(file2);
        return file2.delete();
    }

    private static FileInputStream d(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (file.canRead()) {
            return new FileInputStream(file);
        } else {
            throw new IOException("File '" + file + "' cannot be read");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c A[SYNTHETIC, Splitter:B:23:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0047 A[SYNTHETIC, Splitter:B:28:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0036 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0036 }
            boolean r1 = r1.exists()     // Catch:{ Exception -> 0x0036 }
            if (r1 == 0) goto L_0x002e
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0036 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0036 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
        L_0x001a:
            int r0 = r1.read(r4)     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            r2 = -1
            if (r0 == r2) goto L_0x0026
            r2 = 0
            r3.write(r4, r2, r0)     // Catch:{ Exception -> 0x002b, all -> 0x0028 }
            goto L_0x001a
        L_0x0026:
            r0 = r1
            goto L_0x002e
        L_0x0028:
            r3 = move-exception
            r0 = r1
            goto L_0x0045
        L_0x002b:
            r3 = move-exception
            r0 = r1
            goto L_0x0037
        L_0x002e:
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0034:
            r3 = move-exception
            goto L_0x0045
        L_0x0036:
            r3 = move-exception
        L_0x0037:
            r3.printStackTrace()     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0044:
            return
        L_0x0045:
            if (r0 == 0) goto L_0x004f
            r0.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.file.FileUtil.a(java.lang.String, java.lang.String):void");
    }
}
