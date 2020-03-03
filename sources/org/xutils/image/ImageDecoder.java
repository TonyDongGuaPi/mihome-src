package org.xutils.image;

import android.backport.webp.WebPFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.widget.ImageView;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.DiskCacheFile;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;

public final class ImageDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11926a;
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static final byte[] e = {Constants.TagName.ACTIVITY_INFO, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BASEBAND_VERSION};
    private static final byte[] f = {87, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO_LIST};
    private static final Executor g = new PriorityExecutor(1, true);
    private static final LruDiskCache h = LruDiskCache.a("xUtils_img_thumb");

    static {
        int i = 1;
        if (Runtime.getRuntime().availableProcessors() > 4) {
            i = 2;
        }
        f11926a = i;
    }

    private ImageDecoder() {
    }

    static void a() {
        h.a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0098, code lost:
        r1 = b(r6, r7, r8);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0068 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.drawable.Drawable a(final java.io.File r6, final org.xutils.image.ImageOptions r7, org.xutils.common.Callback.Cancelable r8) throws java.io.IOException {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x00e2
            boolean r1 = r6.exists()
            if (r1 == 0) goto L_0x00e2
            long r1 = r6.length()
            r3 = 1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0015
            goto L_0x00e2
        L_0x0015:
            if (r8 == 0) goto L_0x0026
            boolean r1 = r8.b()
            if (r1 != 0) goto L_0x001e
            goto L_0x0026
        L_0x001e:
            org.xutils.common.Callback$CancelledException r6 = new org.xutils.common.Callback$CancelledException
            java.lang.String r7 = "cancelled during decode image"
            r6.<init>(r7)
            throw r6
        L_0x0026:
            boolean r1 = r7.i()
            if (r1 != 0) goto L_0x004b
            boolean r1 = a((java.io.File) r6)
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = d
            monitor-enter(r1)
            android.graphics.Movie r7 = c(r6, r7, r8)     // Catch:{ all -> 0x0048 }
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            if (r7 == 0) goto L_0x00cb
            org.xutils.image.GifDrawable r0 = new org.xutils.image.GifDrawable
            long r1 = r6.length()
            int r6 = (int) r1
            r0.<init>(r7, r6)
            goto L_0x00cb
        L_0x0048:
            r6 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            throw r6
        L_0x004b:
            java.util.concurrent.atomic.AtomicInteger r1 = b     // Catch:{ all -> 0x00cf }
            int r1 = r1.get()     // Catch:{ all -> 0x00cf }
            int r2 = f11926a     // Catch:{ all -> 0x00cf }
            if (r1 < r2) goto L_0x0074
            if (r8 == 0) goto L_0x005d
            boolean r1 = r8.b()     // Catch:{ all -> 0x00cf }
            if (r1 != 0) goto L_0x0074
        L_0x005d:
            java.lang.Object r1 = c     // Catch:{ all -> 0x00cf }
            monitor-enter(r1)     // Catch:{ all -> 0x00cf }
            java.lang.Object r2 = c     // Catch:{ InterruptedException -> 0x006a, Throwable -> 0x0068 }
            r2.wait()     // Catch:{ InterruptedException -> 0x006a, Throwable -> 0x0068 }
            goto L_0x0068
        L_0x0066:
            r6 = move-exception
            goto L_0x0072
        L_0x0068:
            monitor-exit(r1)     // Catch:{ all -> 0x0066 }
            goto L_0x004b
        L_0x006a:
            org.xutils.common.Callback$CancelledException r6 = new org.xutils.common.Callback$CancelledException     // Catch:{ all -> 0x0066 }
            java.lang.String r7 = "cancelled during decode image"
            r6.<init>(r7)     // Catch:{ all -> 0x0066 }
            throw r6     // Catch:{ all -> 0x0066 }
        L_0x0072:
            monitor-exit(r1)     // Catch:{ all -> 0x0066 }
            throw r6     // Catch:{ all -> 0x00cf }
        L_0x0074:
            if (r8 == 0) goto L_0x0085
            boolean r1 = r8.b()     // Catch:{ all -> 0x00cf }
            if (r1 != 0) goto L_0x007d
            goto L_0x0085
        L_0x007d:
            org.xutils.common.Callback$CancelledException r6 = new org.xutils.common.Callback$CancelledException     // Catch:{ all -> 0x00cf }
            java.lang.String r7 = "cancelled during decode image"
            r6.<init>(r7)     // Catch:{ all -> 0x00cf }
            throw r6     // Catch:{ all -> 0x00cf }
        L_0x0085:
            java.util.concurrent.atomic.AtomicInteger r1 = b     // Catch:{ all -> 0x00cf }
            r1.incrementAndGet()     // Catch:{ all -> 0x00cf }
            boolean r1 = r7.k()     // Catch:{ all -> 0x00cf }
            if (r1 == 0) goto L_0x0095
            android.graphics.Bitmap r1 = a((java.io.File) r6, (org.xutils.image.ImageOptions) r7)     // Catch:{ all -> 0x00cf }
            goto L_0x0096
        L_0x0095:
            r1 = r0
        L_0x0096:
            if (r1 != 0) goto L_0x00ae
            android.graphics.Bitmap r1 = b((java.io.File) r6, (org.xutils.image.ImageOptions) r7, (org.xutils.common.Callback.Cancelable) r8)     // Catch:{ all -> 0x00cf }
            if (r1 == 0) goto L_0x00ae
            boolean r8 = r7.k()     // Catch:{ all -> 0x00cf }
            if (r8 == 0) goto L_0x00ae
            java.util.concurrent.Executor r8 = g     // Catch:{ all -> 0x00cf }
            org.xutils.image.ImageDecoder$1 r2 = new org.xutils.image.ImageDecoder$1     // Catch:{ all -> 0x00cf }
            r2.<init>(r6, r7, r1)     // Catch:{ all -> 0x00cf }
            r8.execute(r2)     // Catch:{ all -> 0x00cf }
        L_0x00ae:
            java.util.concurrent.atomic.AtomicInteger r6 = b
            r6.decrementAndGet()
            java.lang.Object r6 = c
            monitor-enter(r6)
            java.lang.Object r7 = c     // Catch:{ all -> 0x00cc }
            r7.notifyAll()     // Catch:{ all -> 0x00cc }
            monitor-exit(r6)     // Catch:{ all -> 0x00cc }
            if (r1 == 0) goto L_0x00cb
            org.xutils.image.ReusableBitmapDrawable r0 = new org.xutils.image.ReusableBitmapDrawable
            android.app.Application r6 = org.xutils.x.b()
            android.content.res.Resources r6 = r6.getResources()
            r0.<init>(r6, r1)
        L_0x00cb:
            return r0
        L_0x00cc:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00cc }
            throw r7
        L_0x00cf:
            r6 = move-exception
            java.util.concurrent.atomic.AtomicInteger r7 = b
            r7.decrementAndGet()
            java.lang.Object r7 = c
            monitor-enter(r7)
            java.lang.Object r8 = c     // Catch:{ all -> 0x00df }
            r8.notifyAll()     // Catch:{ all -> 0x00df }
            monitor-exit(r7)     // Catch:{ all -> 0x00df }
            throw r6
        L_0x00df:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00df }
            throw r6
        L_0x00e2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.ImageDecoder.a(java.io.File, org.xutils.image.ImageOptions, org.xutils.common.Callback$Cancelable):android.graphics.drawable.Drawable");
    }

    public static boolean a(File file) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                boolean equals = Arrays.equals(e, IOUtil.a((InputStream) fileInputStream2, 0, 3));
                IOUtil.a((Closeable) fileInputStream2);
                return equals;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                IOUtil.a((Closeable) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            LogUtil.b(th.getMessage(), th);
            IOUtil.a((Closeable) fileInputStream);
            return false;
        }
    }

    public static boolean b(File file) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                boolean equals = Arrays.equals(f, IOUtil.a((InputStream) fileInputStream2, 8, 4));
                IOUtil.a((Closeable) fileInputStream2);
                return equals;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                IOUtil.a((Closeable) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            LogUtil.b(th.getMessage(), th);
            IOUtil.a((Closeable) fileInputStream);
            return false;
        }
    }

    public static Bitmap b(File file, ImageOptions imageOptions, Callback.Cancelable cancelable) throws IOException {
        Bitmap bitmap;
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        if (imageOptions == null) {
            imageOptions = ImageOptions.f11937a;
        }
        if (imageOptions.a() <= 0 || imageOptions.b() <= 0) {
            imageOptions.a((ImageView) null);
        }
        if (cancelable != null) {
            try {
                if (cancelable.b()) {
                    throw new Callback.CancelledException("cancelled during decode image");
                }
            } catch (IOException e2) {
                throw e2;
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
                return null;
            }
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int i = 0;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = imageOptions.l();
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int c2 = imageOptions.c();
        int d2 = imageOptions.d();
        if (imageOptions.j()) {
            i = a(file.getAbsolutePath());
            if ((i / 90) % 2 == 1) {
                i2 = options.outHeight;
                i3 = options.outWidth;
            }
        }
        if (!imageOptions.e() && c2 > 0 && d2 > 0) {
            if ((i / 90) % 2 == 1) {
                options.outWidth = d2;
                options.outHeight = c2;
            } else {
                options.outWidth = c2;
                options.outHeight = d2;
            }
        }
        options.inSampleSize = a(i2, i3, imageOptions.a(), imageOptions.b());
        if (cancelable != null) {
            if (cancelable.b()) {
                throw new Callback.CancelledException("cancelled during decode image");
            }
        }
        Bitmap a2 = b(file) ? WebPFactory.a(file.getAbsolutePath(), options) : null;
        if (a2 == null) {
            a2 = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        }
        if (a2 != null) {
            if (cancelable != null) {
                if (cancelable.b()) {
                    throw new Callback.CancelledException("cancelled during decode image");
                }
            }
            if (i != 0) {
                a2 = a(a2, i, true);
            }
            if (cancelable != null) {
                if (cancelable.b()) {
                    throw new Callback.CancelledException("cancelled during decode image");
                }
            }
            if (imageOptions.e() && c2 > 0 && d2 > 0) {
                a2 = a(a2, c2, d2, true);
            }
            if (a2 != null) {
                if (cancelable != null) {
                    if (cancelable.b()) {
                        throw new Callback.CancelledException("cancelled during decode image");
                    }
                }
                if (imageOptions.h()) {
                    bitmap = b(a2, true);
                } else if (imageOptions.f() > 0) {
                    bitmap = a(a2, imageOptions.f(), imageOptions.g(), true);
                } else {
                    bitmap = imageOptions.g() ? a(a2, true) : a2;
                }
                if (bitmap != null) {
                    return bitmap;
                }
                throw new IOException("decode image error");
            }
            throw new IOException("decode image error");
        }
        throw new IOException("decode image error");
    }

    public static Movie c(File file, ImageOptions imageOptions, Callback.Cancelable cancelable) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        if (cancelable != null) {
            try {
                if (cancelable.b()) {
                    throw new Callback.CancelledException("cancelled during decode image");
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                try {
                    LogUtil.b(th.getMessage(), th);
                    IOUtil.a((Closeable) bufferedInputStream);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream2 = bufferedInputStream;
                    IOUtil.a((Closeable) bufferedInputStream2);
                    throw th;
                }
            }
        }
        bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 16384);
        try {
            bufferedInputStream.mark(16384);
            Movie decodeStream = Movie.decodeStream(bufferedInputStream);
            if (decodeStream != null) {
                IOUtil.a((Closeable) bufferedInputStream);
                return decodeStream;
            }
            throw new IOException("decode image error");
        } catch (IOException e3) {
            e = e3;
            bufferedInputStream2 = bufferedInputStream;
            throw e;
        } catch (Throwable th4) {
            th = th4;
            LogUtil.b(th.getMessage(), th);
            IOUtil.a((Closeable) bufferedInputStream);
            return null;
        }
    }

    public static int a(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = 1;
        if (i > i3 || i2 > i4) {
            if (i > i2) {
                i5 = Math.round(((float) i2) / ((float) i4));
            } else {
                i5 = Math.round(((float) i) / ((float) i3));
            }
            if (i5 >= 1) {
                i6 = i5;
            }
            float f2 = (float) (i * i2);
            while (f2 / ((float) (i6 * i6)) > ((float) (i3 * i4 * 2))) {
                i6++;
            }
        }
        return i6;
    }

    public static Bitmap a(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        }
        int min = Math.min(width, height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, (width - min) / 2, (height - min) / 2, min, min);
        if (createBitmap == null) {
            return bitmap;
        }
        if (z && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap b(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        if (createBitmap == null) {
            return bitmap;
        }
        Canvas canvas = new Canvas(createBitmap);
        float f2 = (float) (min / 2);
        canvas.drawCircle(f2, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) ((min - width) / 2), (float) ((min - height) / 2), paint);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z, boolean z2) {
        int i2;
        int i3;
        if (i <= 0) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (z) {
            i2 = Math.min(width, height);
            i3 = i2;
        } else {
            i2 = width;
            i3 = height;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        if (createBitmap == null) {
            return bitmap;
        }
        Canvas canvas = new Canvas(createBitmap);
        float f2 = (float) i;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) i2, (float) i3), f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) ((i2 - width) / 2), (float) ((i3 - height) / 2), paint);
        if (z2) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, boolean z) {
        int i3;
        int i4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float f2 = (float) i;
        float f3 = (float) width;
        float f4 = f2 / f3;
        float f5 = (float) i2;
        float f6 = (float) height;
        float f7 = f5 / f6;
        if (f4 > f7) {
            float f8 = f5 / f4;
            height = (int) ((f6 + f8) / 2.0f);
            i4 = (int) ((f6 - f8) / 2.0f);
            i3 = 0;
        } else {
            float f9 = f2 / f7;
            i3 = (int) ((f3 - f9) / 2.0f);
            width = (int) ((f3 + f9) / 2.0f);
            f4 = f7;
            i4 = 0;
        }
        matrix.setScale(f4, f4);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i3, i4, width - i3, height - i4, matrix, true);
        if (createBitmap == null) {
            return bitmap;
        }
        if (z && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.graphics.Bitmap r7, int r8, boolean r9) {
        /*
            if (r8 == 0) goto L_0x0024
            android.graphics.Matrix r5 = new android.graphics.Matrix
            r5.<init>()
            float r8 = (float) r8
            r5.setRotate(r8)
            r1 = 0
            r2 = 0
            int r3 = r7.getWidth()     // Catch:{ Throwable -> 0x001c }
            int r4 = r7.getHeight()     // Catch:{ Throwable -> 0x001c }
            r6 = 1
            r0 = r7
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x001c }
            goto L_0x0025
        L_0x001c:
            r8 = move-exception
            java.lang.String r0 = r8.getMessage()
            org.xutils.common.util.LogUtil.b(r0, r8)
        L_0x0024:
            r8 = 0
        L_0x0025:
            if (r8 == 0) goto L_0x002f
            if (r9 == 0) goto L_0x002e
            if (r8 == r7) goto L_0x002e
            r7.recycle()
        L_0x002e:
            r7 = r8
        L_0x002f:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.ImageDecoder.a(android.graphics.Bitmap, int, boolean):android.graphics.Bitmap");
    }

    public static int a(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 0);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
            return 0;
        }
    }

    public static void a(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, OutputStream outputStream) throws IOException {
        if (compressFormat == Bitmap.CompressFormat.WEBP) {
            outputStream.write(WebPFactory.a(bitmap, i));
        } else {
            bitmap.compress(compressFormat, i, outputStream);
        }
    }

    /* access modifiers changed from: private */
    public static void b(File file, ImageOptions imageOptions, Bitmap bitmap) {
        DiskCacheFile diskCacheFile;
        FileOutputStream fileOutputStream;
        Throwable th;
        DiskCacheFile diskCacheFile2;
        if (WebPFactory.a()) {
            DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
            diskCacheEntity.a(file.getAbsolutePath() + "@" + file.lastModified() + imageOptions.toString());
            try {
                diskCacheFile = h.b(diskCacheEntity);
                if (diskCacheFile != null) {
                    try {
                        fileOutputStream = new FileOutputStream(diskCacheFile);
                    } catch (Throwable th2) {
                        fileOutputStream = null;
                        th = th2;
                        IOUtil.a((Closeable) diskCacheFile);
                        IOUtil.a((Closeable) fileOutputStream);
                        throw th;
                    }
                    try {
                        fileOutputStream.write(WebPFactory.a(bitmap, 80));
                        fileOutputStream.flush();
                        diskCacheFile2 = diskCacheFile.commit();
                    } catch (Throwable th3) {
                        th = th3;
                        IOUtil.a((File) diskCacheFile);
                        LogUtil.e(th.getMessage(), th);
                        IOUtil.a((Closeable) diskCacheFile);
                        IOUtil.a((Closeable) fileOutputStream);
                    }
                } else {
                    fileOutputStream = null;
                    diskCacheFile2 = diskCacheFile;
                }
                IOUtil.a((Closeable) diskCacheFile2);
            } catch (Throwable th4) {
                fileOutputStream = null;
                th = th4;
                diskCacheFile = null;
                IOUtil.a((Closeable) diskCacheFile);
                IOUtil.a((Closeable) fileOutputStream);
                throw th;
            }
            IOUtil.a((Closeable) fileOutputStream);
        }
    }

    private static Bitmap a(File file, ImageOptions imageOptions) {
        DiskCacheFile diskCacheFile;
        DiskCacheFile diskCacheFile2 = null;
        if (!WebPFactory.a()) {
            return null;
        }
        try {
            diskCacheFile = h.c(file.getAbsolutePath() + "@" + file.lastModified() + imageOptions.toString());
            if (diskCacheFile != null) {
                try {
                    if (diskCacheFile.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        Bitmap a2 = WebPFactory.a(diskCacheFile.getAbsolutePath(), options);
                        IOUtil.a((Closeable) diskCacheFile);
                        return a2;
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        LogUtil.e(th.getMessage(), th);
                        IOUtil.a((Closeable) diskCacheFile);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        diskCacheFile2 = diskCacheFile;
                        IOUtil.a((Closeable) diskCacheFile2);
                        throw th;
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            IOUtil.a((Closeable) diskCacheFile2);
            throw th;
        }
        IOUtil.a((Closeable) diskCacheFile);
        return null;
    }
}
