package com.xiaomi.smarthome.library.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import java.io.ByteArrayOutputStream;

public class BitmapUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18648a = -1;
    private static final String b = "BitmapUtils";
    private static final int c = 90;

    private BitmapUtils() {
    }

    public static int a(int i) {
        if (i <= 0 || i > 1073741824) {
            throw new IllegalArgumentException();
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 16);
        int i4 = i3 | (i3 >> 8);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 2);
        return (i6 | (i6 >> 1)) + 1;
    }

    public static int b(int i) {
        if (i > 0) {
            return Integer.highestOneBit(i);
        }
        throw new IllegalArgumentException();
    }

    public static int a(int i, int i2, int i3, int i4) {
        int b2 = b(i, i2, i3, i4);
        return b2 <= 8 ? a(b2) : ((b2 + 7) / 8) * 8;
    }

    private static int b(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i4 == -1 && i3 == -1) {
            return 1;
        }
        if (i4 != -1) {
            i5 = (int) Math.ceil(Math.sqrt((double) (((float) (i * i2)) / ((float) i4))));
        }
        if (i3 == -1) {
            return i5;
        }
        return Math.max(Math.min(i / i3, i2 / i3), i5);
    }

    public static int a(int i, int i2, int i3) {
        int max = Math.max(i / i3, i2 / i3);
        if (max <= 1) {
            return 1;
        }
        return max <= 8 ? b(max) : (max / 8) * 8;
    }

    public static int a(float f) {
        int floor = (int) Math.floor((double) (1.0f / f));
        if (floor <= 1) {
            return 1;
        }
        return floor <= 8 ? b(floor) : (floor / 8) * 8;
    }

    public static int b(float f) {
        int max = Math.max(1, (int) Math.ceil((double) (1.0f / f)));
        return max <= 8 ? a(max) : ((max + 7) / 8) * 8;
    }

    public static Bitmap a(Bitmap bitmap, float f, boolean z) {
        int round = Math.round(((float) bitmap.getWidth()) * f);
        int round2 = Math.round(((float) bitmap.getHeight()) * f);
        if (round == bitmap.getWidth() && round2 == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(round, round2, c(bitmap));
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(f, f);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static Bitmap.Config c(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        return config == null ? Bitmap.Config.ARGB_8888 : config;
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        float f = (float) i;
        float min = Math.min(f / ((float) bitmap.getWidth()), f / ((float) bitmap.getHeight()));
        if (min >= 1.0f) {
            return bitmap;
        }
        return a(bitmap, min, z);
    }

    public static Bitmap b(Bitmap bitmap, int i, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i) {
            return bitmap;
        }
        float min = ((float) i) / ((float) Math.min(width, height));
        Bitmap createBitmap = Bitmap.createBitmap(i, i, c(bitmap));
        int round = Math.round(((float) bitmap.getWidth()) * min);
        int round2 = Math.round(((float) bitmap.getHeight()) * min);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(((float) (i - round)) / 2.0f, ((float) (i - round2)) / 2.0f);
        canvas.scale(min, min);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Resources resources, int i, int i2, int i3) {
        return a(resources, i, i2, i3, 1);
    }

    public static Bitmap a(Resources resources, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inJustDecodeBounds = false;
        if (i4 > 0) {
            if (i2 > 0) {
                i5 = (int) (((float) i2) / ((float) i4));
            } else {
                i5 = options.outWidth;
            }
            if (i3 > 0) {
                i6 = (int) (((float) i3) / ((float) i4));
            } else {
                i6 = options.outHeight;
            }
            options.inSampleSize = a(options, -1, i5 * i6);
        } else {
            options.inSampleSize = 2;
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(resources.openRawResource(i), (Rect) null, options);
    }

    public static int a(BitmapFactory.Options options, int i, int i2) {
        int b2 = b(options, i, i2);
        if (b2 > 8) {
            return ((b2 + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b2) {
            i3 <<= 1;
        }
        return i3;
    }

    public static int b(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int i4;
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        if (i2 == -1) {
            i3 = 1;
        } else {
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = (double) i2;
            Double.isNaN(d3);
            i3 = (int) Math.ceil(Math.sqrt((d * d2) / d3));
        }
        if (i == -1) {
            i4 = 128;
        } else {
            double d4 = (double) i;
            Double.isNaN(d);
            Double.isNaN(d4);
            double floor = Math.floor(d / d4);
            Double.isNaN(d2);
            Double.isNaN(d4);
            i4 = (int) Math.min(floor, Math.floor(d2 / d4));
        }
        if (i4 < i3) {
            return i3;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? i3 : i4;
    }

    public static void a(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Throwable th) {
                Log.w(b, "unable recycle bitmap", th);
            }
        }
    }

    public static Bitmap c(Bitmap bitmap, int i, boolean z) {
        if (i == 0) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x014f A[Catch:{ Exception -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.lang.String r8) {
        /*
            r0 = 0
            r1 = 0
            java.lang.String r2 = "android.media.MediaMetadataRetriever"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ IllegalArgumentException -> 0x014b, RuntimeException -> 0x013e, InstantiationException -> 0x0118, InvocationTargetException -> 0x0103, ClassNotFoundException -> 0x00ee, NoSuchMethodException -> 0x00d9, IllegalAccessException -> 0x00be, all -> 0x00b9 }
            java.lang.Object r3 = r2.newInstance()     // Catch:{ IllegalArgumentException -> 0x00b6, RuntimeException -> 0x00b3, InstantiationException -> 0x00af, InvocationTargetException -> 0x00ab, ClassNotFoundException -> 0x00a8, NoSuchMethodException -> 0x00a5, IllegalAccessException -> 0x00a2, all -> 0x009e }
            java.lang.String r4 = "setDataSource"
            r5 = 1
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.reflect.Method r4 = r2.getMethod(r4, r6)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            r5[r1] = r8     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            r4.invoke(r3, r5)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            r4 = 9
            if (r8 > r4) goto L_0x0046
            java.lang.String r8 = "captureFrame"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object r8 = r8.invoke(r3, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            if (r3 == 0) goto L_0x0045
            java.lang.String r0 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0045 }
            java.lang.reflect.Method r0 = r2.getMethod(r0, r4)     // Catch:{ Exception -> 0x0045 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0045 }
            r0.invoke(r3, r1)     // Catch:{ Exception -> 0x0045 }
        L_0x0045:
            return r8
        L_0x0046:
            java.lang.String r8 = "getEmbeddedPicture"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object r8 = r8.invoke(r3, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            byte[] r8 = (byte[]) r8     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            byte[] r8 = (byte[]) r8     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            if (r8 == 0) goto L_0x0071
            int r4 = r8.length     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeByteArray(r8, r1, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            if (r8 == 0) goto L_0x0071
            if (r3 == 0) goto L_0x0070
            java.lang.String r0 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0070 }
            java.lang.reflect.Method r0 = r2.getMethod(r0, r4)     // Catch:{ Exception -> 0x0070 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0070 }
            r0.invoke(r3, r1)     // Catch:{ Exception -> 0x0070 }
        L_0x0070:
            return r8
        L_0x0071:
            java.lang.String r8 = "getFrameAtTime"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            java.lang.Object r8 = r8.invoke(r3, r4)     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8     // Catch:{ IllegalArgumentException -> 0x014d, RuntimeException -> 0x0140, InstantiationException -> 0x009b, InvocationTargetException -> 0x0098, ClassNotFoundException -> 0x0095, NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x0091 }
            if (r3 == 0) goto L_0x0090
            java.lang.String r0 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0090 }
            java.lang.reflect.Method r0 = r2.getMethod(r0, r4)     // Catch:{ Exception -> 0x0090 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0090 }
            r0.invoke(r3, r1)     // Catch:{ Exception -> 0x0090 }
        L_0x0090:
            return r8
        L_0x0091:
            r8 = move-exception
            goto L_0x00c1
        L_0x0093:
            r8 = move-exception
            goto L_0x00dc
        L_0x0095:
            r8 = move-exception
            goto L_0x00f1
        L_0x0098:
            r8 = move-exception
            goto L_0x0106
        L_0x009b:
            r8 = move-exception
            goto L_0x011b
        L_0x009e:
            r8 = move-exception
            r3 = r0
            goto L_0x012e
        L_0x00a2:
            r8 = move-exception
            r3 = r0
            goto L_0x00c1
        L_0x00a5:
            r8 = move-exception
            r3 = r0
            goto L_0x00dc
        L_0x00a8:
            r8 = move-exception
            r3 = r0
            goto L_0x00f1
        L_0x00ab:
            r8 = move-exception
            r3 = r0
            goto L_0x0106
        L_0x00af:
            r8 = move-exception
            r3 = r0
            goto L_0x011b
        L_0x00b3:
            r3 = r0
            goto L_0x0140
        L_0x00b6:
            r3 = r0
            goto L_0x014d
        L_0x00b9:
            r8 = move-exception
            r2 = r0
            r3 = r2
            goto L_0x012e
        L_0x00be:
            r8 = move-exception
            r2 = r0
            r3 = r2
        L_0x00c1:
            java.lang.String r4 = "BitmapUtils"
            java.lang.String r5 = "createVideoThumbnail"
            android.util.Log.e(r4, r5, r8)     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
        L_0x00d2:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0159 }
            r8.invoke(r3, r1)     // Catch:{ Exception -> 0x0159 }
            goto L_0x0159
        L_0x00d9:
            r8 = move-exception
            r2 = r0
            r3 = r2
        L_0x00dc:
            java.lang.String r4 = "BitmapUtils"
            java.lang.String r5 = "createVideoThumbnail"
            android.util.Log.e(r4, r5, r8)     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00d2
        L_0x00ee:
            r8 = move-exception
            r2 = r0
            r3 = r2
        L_0x00f1:
            java.lang.String r4 = "BitmapUtils"
            java.lang.String r5 = "createVideoThumbnail"
            android.util.Log.e(r4, r5, r8)     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00d2
        L_0x0103:
            r8 = move-exception
            r2 = r0
            r3 = r2
        L_0x0106:
            java.lang.String r4 = "BitmapUtils"
            java.lang.String r5 = "createVideoThumbnail"
            android.util.Log.e(r4, r5, r8)     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00d2
        L_0x0118:
            r8 = move-exception
            r2 = r0
            r3 = r2
        L_0x011b:
            java.lang.String r4 = "BitmapUtils"
            java.lang.String r5 = "createVideoThumbnail"
            android.util.Log.e(r4, r5, r8)     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00d2
        L_0x012d:
            r8 = move-exception
        L_0x012e:
            if (r3 == 0) goto L_0x013d
            java.lang.String r0 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x013d }
            java.lang.reflect.Method r0 = r2.getMethod(r0, r4)     // Catch:{ Exception -> 0x013d }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x013d }
            r0.invoke(r3, r1)     // Catch:{ Exception -> 0x013d }
        L_0x013d:
            throw r8
        L_0x013e:
            r2 = r0
            r3 = r2
        L_0x0140:
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00d2
        L_0x014b:
            r2 = r0
            r3 = r2
        L_0x014d:
            if (r3 == 0) goto L_0x0159
            java.lang.String r8 = "release"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0159 }
            java.lang.reflect.Method r8 = r2.getMethod(r8, r4)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00d2
        L_0x0159:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.BitmapUtils.a(java.lang.String):android.graphics.Bitmap");
    }

    public static byte[] b(Bitmap bitmap) {
        return a(bitmap, 90);
    }

    public static byte[] a(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(65536);
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("image/") || lowerCase.equals("image/gif") || lowerCase.endsWith("bmp")) {
            return false;
        }
        return true;
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        return str.toLowerCase().equals("image/jpeg");
    }
}
