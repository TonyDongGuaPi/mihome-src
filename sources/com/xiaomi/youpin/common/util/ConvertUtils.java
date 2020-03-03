package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class ConvertUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f23247a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static long a(long j, int i) {
        if (j < 0) {
            return -1;
        }
        return j * ((long) i);
    }

    public static long c(long j, int i) {
        return j * ((long) i);
    }

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            for (int i = 7; i >= 0; i--) {
                sb.append(((b >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
        int length = str.length() % 8;
        int length2 = str.length() / 8;
        if (length != 0) {
            while (length < 8) {
                str = "0" + str;
                length++;
            }
            length2++;
        }
        byte[] bArr = new byte[length2];
        for (int i = 0; i < length2; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (bArr[i] << 1);
                bArr[i] = (byte) (bArr[i] | (str.charAt((i * 8) + i2) - '0'));
            }
        }
        return bArr;
    }

    public static char[] b(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static byte[] a(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return null;
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static String c(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = f23247a[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = f23247a[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static byte[] b(String str) {
        if (c(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((a(charArray[i]) << 4) | a(charArray[i + 1]));
        }
        return bArr;
    }

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    public static double b(long j, int i) {
        if (j < 0) {
            return -1.0d;
        }
        double d = (double) j;
        double d2 = (double) i;
        Double.isNaN(d);
        Double.isNaN(d2);
        return d / d2;
    }

    @SuppressLint({"DefaultLocale"})
    public static String a(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < 1024) {
            return String.format("%.3fB", new Object[]{Double.valueOf((double) j)});
        } else if (j < 1048576) {
            double d = (double) j;
            Double.isNaN(d);
            return String.format("%.3fKB", new Object[]{Double.valueOf(d / 1024.0d)});
        } else if (j < 1073741824) {
            double d2 = (double) j;
            Double.isNaN(d2);
            return String.format("%.3fMB", new Object[]{Double.valueOf(d2 / 1048576.0d)});
        } else {
            double d3 = (double) j;
            Double.isNaN(d3);
            return String.format("%.3fGB", new Object[]{Double.valueOf(d3 / 1.073741824E9d)});
        }
    }

    public static long d(long j, int i) {
        return j / ((long) i);
    }

    @SuppressLint({"DefaultLocale"})
    public static String e(long j, int i) {
        if (j <= 0 || i <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] iArr = {86400000, 3600000, 60000, 1000, 1};
        int min = Math.min(i, 5);
        for (int i2 = 0; i2 < min; i2++) {
            if (j >= ((long) iArr[i2])) {
                long j2 = j / ((long) iArr[i2]);
                j -= ((long) iArr[i2]) * j2;
                sb.append(j2);
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    public static ByteArrayOutputStream a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            inputStream.close();
            return byteArrayOutputStream;
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return null;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public ByteArrayInputStream a(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
    }

    public static byte[] b(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return a(inputStream).toByteArray();
    }

    public static InputStream d(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    public static byte[] b(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0023 A[SYNTHETIC, Splitter:B:20:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0030 A[SYNTHETIC, Splitter:B:28:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.OutputStream e(byte[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0039
            int r1 = r2.length
            if (r1 > 0) goto L_0x0007
            goto L_0x0039
        L_0x0007:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x001c, all -> 0x001a }
            r1.<init>()     // Catch:{ IOException -> 0x001c, all -> 0x001a }
            r1.write(r2)     // Catch:{ IOException -> 0x0018 }
            r1.close()     // Catch:{ IOException -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0017:
            return r1
        L_0x0018:
            r2 = move-exception
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            goto L_0x002e
        L_0x001c:
            r2 = move-exception
            r1 = r0
        L_0x001e:
            r2.printStackTrace()     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r2 = move-exception
            r2.printStackTrace()
        L_0x002b:
            return r0
        L_0x002c:
            r2 = move-exception
            r0 = r1
        L_0x002e:
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0038:
            throw r2
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ConvertUtils.e(byte[]):java.io.OutputStream");
    }

    public static String a(InputStream inputStream, String str) {
        if (inputStream == null || c(str)) {
            return "";
        }
        try {
            return new String(b(inputStream), str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static InputStream a(String str, String str2) {
        if (str == null || c(str2)) {
            return null;
        }
        try {
            return new ByteArrayInputStream(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(OutputStream outputStream, String str) {
        if (outputStream == null || c(str)) {
            return "";
        }
        try {
            return new String(b(outputStream), str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static OutputStream b(String str, String str2) {
        if (str == null || c(str2)) {
            return null;
        }
        try {
            return e(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap f(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.a().getResources(), bitmap);
    }

    public static byte[] a(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return a(a(drawable), compressFormat);
    }

    public static Drawable g(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return a(f(bArr));
    }

    public static Bitmap a(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static int a(float f) {
        return (int) ((f * Utils.a().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(float f) {
        return (int) ((f / Utils.a().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int c(float f) {
        return (int) ((f * Utils.a().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int d(float f) {
        return (int) ((f / Utils.a().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private static boolean c(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
