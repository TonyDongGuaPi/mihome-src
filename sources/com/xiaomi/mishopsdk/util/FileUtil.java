package com.xiaomi.mishopsdk.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import com.xiaomi.shop2.util.Device;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    private static final String FILENAME_FORMAT = "%s_%d.%s";
    public static final int IMAGE_COMPRESS_MODE_QUALITY = 1;
    public static final int IMAGE_COMPRESS_MODE_SIZE = 2;
    public static final int IMAGE_COMPRESS_THRESHOLD = 102400;
    private static final String IMAGE_DIR = "images";
    public static final int IMAGE_HEIGHT_THRESHOLD = 1200;
    public static final int IMAGE_WIDTH_THRESHOLD = 720;
    private static final String ROOT_DIR = "mishop";

    public static File compressImageFile(File file) throws IOException {
        String str;
        String name = file.getName();
        long length = file.length();
        String absolutePath = file.getAbsolutePath();
        if (!name.endsWith("jpg") && !name.endsWith("JPG") && !name.endsWith("png") && !name.endsWith("PNG") && !name.endsWith("jpeg") && !name.endsWith("JPEG")) {
            File file2 = new File(absolutePath);
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf == -1) {
                str = name + ".jpg";
            } else {
                str = name.substring(0, lastIndexOf) + ".jpg";
            }
            String uniqueFileName = getUniqueFileName(getImageDir(), str);
            copyFile(file2, new File(uniqueFileName));
            return compressImage(uniqueFileName, 2);
        } else if (length <= 102400) {
            return file;
        } else {
            File file3 = new File(absolutePath);
            String uniqueFileName2 = getUniqueFileName(getImageDir(), name);
            copyFile(file3, new File(uniqueFileName2));
            return compressImage(uniqueFileName2, 2);
        }
    }

    private static File compressImage(String str, int i) throws IOException {
        if (compressBitmap(str, i)) {
            return new File(str);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0076 A[SYNTHETIC, Splitter:B:46:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x007f A[Catch:{ IOException -> 0x0082 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean compressBitmap(java.lang.String r5, int r6) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            java.lang.String r2 = ".temp"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r1 = 90
            r2 = 1
            r3 = 0
            if (r2 != r6) goto L_0x0026
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r5)     // Catch:{ all -> 0x0023 }
            r1 = 50
            goto L_0x0030
        L_0x0023:
            r5 = move-exception
            r4 = r3
            goto L_0x0074
        L_0x0026:
            r6 = 720(0x2d0, float:1.009E-42)
            r4 = 1200(0x4b0, float:1.682E-42)
            android.graphics.Bitmap r6 = decodeFile(r5, r6, r4)     // Catch:{ IOException | OutOfMemoryError -> 0x002f }
            goto L_0x0030
        L_0x002f:
            r6 = r3
        L_0x0030:
            if (r6 != 0) goto L_0x003d
            r5 = 0
            boolean r6 = r0.exists()     // Catch:{ IOException -> 0x003c }
            if (r6 == 0) goto L_0x003c
            r0.delete()     // Catch:{ IOException -> 0x003c }
        L_0x003c:
            return r5
        L_0x003d:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x0023 }
            r4.<init>(r0)     // Catch:{ all -> 0x0023 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x0073 }
            r6.compress(r3, r1, r4)     // Catch:{ all -> 0x0073 }
            r4.close()     // Catch:{ IOException -> 0x006d }
            r6.recycle()     // Catch:{ all -> 0x0073 }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x0073 }
            r6.<init>(r5)     // Catch:{ all -> 0x0073 }
            boolean r5 = r6.exists()     // Catch:{ all -> 0x0073 }
            if (r5 == 0) goto L_0x005b
            r6.delete()     // Catch:{ all -> 0x0073 }
        L_0x005b:
            r0.renameTo(r6)     // Catch:{ all -> 0x0073 }
            r4.close()     // Catch:{ IOException -> 0x006a }
            boolean r5 = r0.exists()     // Catch:{ IOException -> 0x006a }
            if (r5 == 0) goto L_0x006a
            r0.delete()     // Catch:{ IOException -> 0x006a }
        L_0x006a:
            return r2
        L_0x006b:
            r5 = move-exception
            goto L_0x006f
        L_0x006d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x006b }
        L_0x006f:
            r6.recycle()     // Catch:{ all -> 0x0073 }
            throw r5     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r5 = move-exception
        L_0x0074:
            if (r4 == 0) goto L_0x0079
            r4.close()     // Catch:{ IOException -> 0x0082 }
        L_0x0079:
            boolean r6 = r0.exists()     // Catch:{ IOException -> 0x0082 }
            if (r6 == 0) goto L_0x0082
            r0.delete()     // Catch:{ IOException -> 0x0082 }
        L_0x0082:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.FileUtil.compressBitmap(java.lang.String, int):boolean");
    }

    public static Bitmap decodeFile(String str, int i) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            while ((i3 * i3) + (i4 * i4) > i * i) {
                i2++;
                i3 = options.outWidth / i2;
                i4 = options.outHeight / i2;
            }
            options.inSampleSize = i2;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.inSampleSize = r1;
        r0.inJustDecodeBounds = false;
        r11 = android.graphics.BitmapFactory.decodeFile(r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        if (r11 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        r6 = new android.graphics.Matrix();
        r6.postRotate((float) 0);
        r12 = android.graphics.Bitmap.createBitmap(r11, 0, 0, r11.getWidth(), r11.getHeight(), r6, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005e, code lost:
        if (r12 == r11) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0060, code lost:
        r11.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        return r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeFile(java.lang.String r11, int r12, int r13) throws java.io.IOException, java.lang.OutOfMemoryError {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options     // Catch:{ OutOfMemoryError -> 0x0064 }
            r0.<init>()     // Catch:{ OutOfMemoryError -> 0x0064 }
            r1 = 1
            r0.inJustDecodeBounds = r1     // Catch:{ OutOfMemoryError -> 0x0064 }
            android.graphics.BitmapFactory.decodeFile(r11, r0)     // Catch:{ OutOfMemoryError -> 0x0064 }
            int r2 = r0.outWidth     // Catch:{ OutOfMemoryError -> 0x0064 }
            int r3 = r0.outHeight     // Catch:{ OutOfMemoryError -> 0x0064 }
            int r4 = r0.outWidth     // Catch:{ OutOfMemoryError -> 0x0064 }
            int r5 = r0.outHeight     // Catch:{ OutOfMemoryError -> 0x0064 }
            if (r4 >= r5) goto L_0x0019
            int r2 = r0.outHeight     // Catch:{ OutOfMemoryError -> 0x0064 }
            int r3 = r0.outWidth     // Catch:{ OutOfMemoryError -> 0x0064 }
        L_0x0019:
            double r4 = (double) r2
            double r6 = (double) r13
            r8 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r8
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0038
            double r4 = (double) r3
            double r6 = (double) r12
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r8
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0038
            int r1 = r1 << 1
            int r2 = r2 >> 1
            int r3 = r3 >> 1
            goto L_0x0019
        L_0x0038:
            r0.inSampleSize = r1     // Catch:{ OutOfMemoryError -> 0x0064 }
            r12 = 0
            r0.inJustDecodeBounds = r12     // Catch:{ OutOfMemoryError -> 0x0064 }
            android.graphics.Bitmap r11 = android.graphics.BitmapFactory.decodeFile(r11, r0)     // Catch:{ OutOfMemoryError -> 0x0064 }
            if (r11 != 0) goto L_0x0045
            r11 = 0
            return r11
        L_0x0045:
            android.graphics.Matrix r6 = new android.graphics.Matrix     // Catch:{ OutOfMemoryError -> 0x0064 }
            r6.<init>()     // Catch:{ OutOfMemoryError -> 0x0064 }
            float r12 = (float) r12     // Catch:{ OutOfMemoryError -> 0x0064 }
            r6.postRotate(r12)     // Catch:{ OutOfMemoryError -> 0x0064 }
            r2 = 0
            r3 = 0
            int r4 = r11.getWidth()     // Catch:{ OutOfMemoryError -> 0x0064 }
            int r5 = r11.getHeight()     // Catch:{ OutOfMemoryError -> 0x0064 }
            r7 = 1
            r1 = r11
            android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ OutOfMemoryError -> 0x0064 }
            if (r12 == r11) goto L_0x0063
            r11.recycle()     // Catch:{ OutOfMemoryError -> 0x0064 }
        L_0x0063:
            return r12
        L_0x0064:
            r11 = move-exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.FileUtil.decodeFile(java.lang.String, int, int):android.graphics.Bitmap");
    }

    public static String getUniqueFileName(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            return file2.getAbsolutePath();
        }
        int lastIndexOf = str.lastIndexOf(46);
        String str2 = "";
        if (lastIndexOf > 0) {
            String substring = str.substring(0, lastIndexOf);
            str2 = str.substring(lastIndexOf + 1);
            str = substring;
        }
        int i = 1;
        while (true) {
            File file3 = new File(file, String.format(FILENAME_FORMAT, new Object[]{str, Integer.valueOf(i), str2}));
            if (!file3.exists()) {
                return file3.getAbsolutePath();
            }
            i++;
        }
    }

    public static File getRootDir() {
        if (Device.isSDCardBusy()) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "mishop");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getImageDir() {
        File rootDir = getRootDir();
        if (rootDir == null) {
            return null;
        }
        File file = new File(rootDir, "images");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return file;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(java.io.File r3, java.io.File r4) throws java.io.IOException {
        /*
            java.lang.String r0 = r3.getAbsolutePath()
            java.lang.String r1 = r4.getAbsolutePath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x000f
            return
        L_0x000f:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0035 }
            r1.<init>(r3)     // Catch:{ all -> 0x0035 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0033 }
            r3.<init>(r4)     // Catch:{ all -> 0x0033 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0030 }
        L_0x001e:
            int r0 = r1.read(r4)     // Catch:{ all -> 0x0030 }
            if (r0 < 0) goto L_0x0029
            r2 = 0
            r3.write(r4, r2, r0)     // Catch:{ all -> 0x0030 }
            goto L_0x001e
        L_0x0029:
            r1.close()
            r3.close()
            return
        L_0x0030:
            r4 = move-exception
            r0 = r3
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            goto L_0x0037
        L_0x0035:
            r4 = move-exception
            r1 = r0
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            if (r0 == 0) goto L_0x0041
            r0.close()
        L_0x0041:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.FileUtil.copyFile(java.io.File, java.io.File):void");
    }

    public static byte[] getBytes(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] bArr = new byte[1000];
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
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static File makeDirsIfNeeded() {
        if (Device.isSDCardBusy()) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "mishop");
        if (file.isDirectory() || file.mkdirs()) {
            hideFromMediaScanner(file);
        }
        File file2 = new File(file, "images");
        if (file2.isDirectory() || file2.mkdirs()) {
            hideFromMediaScanner(file2);
        }
        return file2;
    }

    public static void hideFromMediaScanner(File file) {
        File file2 = new File(file, ".nomedia");
        if (!file2.exists() || !file2.isFile()) {
            try {
                file2.createNewFile();
            } catch (IOException unused) {
            }
        }
    }

    static int computeSampleSize(BitmapFactory.Options options, int i) {
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int max = Math.max(i2 / i, i3 / i);
        if (max == 0) {
            return 1;
        }
        if (max > 1 && i2 > i && i2 / max < i) {
            max--;
        }
        return (max <= 1 || i3 <= i || i3 / max >= i) ? max : max - 1;
    }

    public static void comPressFile2File(String str, String str2, int i) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inDither = true;
            options.inPurgeable = true;
            options.inJustDecodeBounds = false;
            options.inSampleSize = computeSampleSize(options, i);
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            float f = ((float) (options.outHeight > options.outWidth ? options.outHeight : options.outWidth)) / ((float) i);
            float f2 = 1.0f;
            if (f > 1.0f) {
                f2 = f;
            }
            if (decodeFile != null) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeFile, (int) (((float) options.outWidth) / f2), (int) (((float) options.outHeight) / f2), true);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                createScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                fileOutputStream.close();
                if (!createScaledBitmap.isRecycled()) {
                    createScaledBitmap.recycle();
                }
                if (!decodeFile.isRecycled()) {
                    decodeFile.recycle();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static String saveMyBitmap(Bitmap bitmap, String str) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            return str;
        }
        file.createNewFile();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
        if (fileOutputStream != null) {
            try {
                fileOutputStream.flush();
            } catch (IOException e2) {
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return str;
    }

    public static boolean checkOrCreateFolder(String str) {
        try {
            File file = new File(str.toString());
            if (file.exists()) {
                return true;
            }
            file.mkdirs();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFileName(String str) {
        return (TextUtils.isEmpty(str) || !str.contains(File.separator)) ? str : str.substring(str.lastIndexOf(File.separator), str.length());
    }

    public static void deleteFloder(File file) {
        if (file != null) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    file.delete();
                    return;
                }
                for (File deleteFloder : listFiles) {
                    deleteFloder(deleteFloder);
                }
                file.delete();
            }
        }
    }

    @Deprecated
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
