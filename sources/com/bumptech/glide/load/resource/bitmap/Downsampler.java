package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class Downsampler {

    /* renamed from: a  reason: collision with root package name */
    static final String f5000a = "Downsampler";
    public static final Option<DecodeFormat> b = Option.a("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    @Deprecated
    public static final Option<DownsampleStrategy> c = DownsampleStrategy.h;
    public static final Option<Boolean> d = Option.a("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    public static final Option<Boolean> e = Option.a("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", false);
    private static final String f = "image/vnd.wap.wbmp";
    private static final String g = "image/x-ico";
    private static final Set<String> h = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{f, g})));
    private static final DecodeCallbacks i = new DecodeCallbacks() {
        public void a() {
        }

        public void a(BitmapPool bitmapPool, Bitmap bitmap) {
        }
    };
    private static final Set<ImageHeaderParser.ImageType> j = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final Queue<BitmapFactory.Options> k = Util.a(0);
    private static final int l = 10485760;
    private final BitmapPool m;
    private final DisplayMetrics n;
    private final ArrayPool o;
    private final List<ImageHeaderParser> p;
    private final HardwareConfigState q = HardwareConfigState.a();

    public interface DecodeCallbacks {
        void a();

        void a(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;
    }

    private static int c(double d2) {
        return (int) (d2 + 0.5d);
    }

    public boolean a(InputStream inputStream) {
        return true;
    }

    public boolean a(ByteBuffer byteBuffer) {
        return true;
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this.p = list;
        this.n = (DisplayMetrics) Preconditions.a(displayMetrics);
        this.m = (BitmapPool) Preconditions.a(bitmapPool);
        this.o = (ArrayPool) Preconditions.a(arrayPool);
    }

    public Resource<Bitmap> a(InputStream inputStream, int i2, int i3, Options options) throws IOException {
        return a(inputStream, i2, i3, options, i);
    }

    public Resource<Bitmap> a(InputStream inputStream, int i2, int i3, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        Options options2 = options;
        Preconditions.a(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.o.a(65536, byte[].class);
        BitmapFactory.Options a2 = a();
        a2.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options2.a(b);
        try {
            return BitmapResource.a(a(inputStream, a2, (DownsampleStrategy) options2.a(DownsampleStrategy.h), decodeFormat, options2.a(e) != null && ((Boolean) options2.a(e)).booleanValue(), i2, i3, ((Boolean) options2.a(d)).booleanValue(), decodeCallbacks), this.m);
        } finally {
            c(a2);
            this.o.a(bArr);
        }
    }

    private Bitmap a(InputStream inputStream, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean z, int i2, int i3, boolean z2, DecodeCallbacks decodeCallbacks) throws IOException {
        int i4;
        int i5;
        Downsampler downsampler;
        int i6;
        int i7;
        int i8;
        InputStream inputStream2 = inputStream;
        BitmapFactory.Options options2 = options;
        DecodeCallbacks decodeCallbacks2 = decodeCallbacks;
        long a2 = LogTime.a();
        int[] a3 = a(inputStream2, options2, decodeCallbacks2, this.m);
        boolean z3 = false;
        int i9 = a3[0];
        int i10 = a3[1];
        String str = options2.outMimeType;
        boolean z4 = (i9 == -1 || i10 == -1) ? false : z;
        int b2 = ImageHeaderParserUtils.b(this.p, inputStream2, this.o);
        int a4 = TransformationUtils.a(b2);
        boolean b3 = TransformationUtils.b(b2);
        int i11 = i2;
        if (i11 == Integer.MIN_VALUE) {
            i5 = i3;
            i4 = i9;
        } else {
            i5 = i3;
            i4 = i11;
        }
        int i12 = i5 == Integer.MIN_VALUE ? i10 : i5;
        ImageHeaderParser.ImageType a5 = ImageHeaderParserUtils.a(this.p, inputStream2, this.o);
        BitmapPool bitmapPool = this.m;
        ImageHeaderParser.ImageType imageType = a5;
        a(a5, inputStream, decodeCallbacks, bitmapPool, downsampleStrategy, a4, i9, i10, i4, i12, options);
        int i13 = b2;
        String str2 = str;
        int i14 = i10;
        int i15 = i9;
        DecodeCallbacks decodeCallbacks3 = decodeCallbacks2;
        BitmapFactory.Options options3 = options2;
        a(inputStream, decodeFormat, z4, b3, options, i4, i12);
        if (Build.VERSION.SDK_INT >= 19) {
            z3 = true;
        }
        if (options3.inSampleSize == 1 || z3) {
            downsampler = this;
            if (downsampler.a(imageType)) {
                if (i15 < 0 || i14 < 0 || !z2 || !z3) {
                    float f2 = a(options) ? ((float) options3.inTargetDensity) / ((float) options3.inDensity) : 1.0f;
                    int i16 = options3.inSampleSize;
                    float f3 = (float) i16;
                    i8 = Math.round(((float) ((int) Math.ceil((double) (((float) i15) / f3)))) * f2);
                    i7 = Math.round(((float) ((int) Math.ceil((double) (((float) i14) / f3)))) * f2);
                    if (Log.isLoggable(f5000a, 2)) {
                        Log.v(f5000a, "Calculated target [" + i8 + "x" + i7 + "] for source [" + i15 + "x" + i14 + "], sampleSize: " + i16 + ", targetDensity: " + options3.inTargetDensity + ", density: " + options3.inDensity + ", density multiplier: " + f2);
                    }
                } else {
                    i8 = i4;
                    i7 = i12;
                }
                if (i8 > 0 && i7 > 0) {
                    a(options3, downsampler.m, i8, i7);
                }
            }
        } else {
            downsampler = this;
        }
        Bitmap b4 = b(inputStream, options3, decodeCallbacks3, downsampler.m);
        decodeCallbacks3.a(downsampler.m, b4);
        if (Log.isLoggable(f5000a, 2)) {
            i6 = i13;
            a(i15, i14, str2, options, b4, i2, i3, a2);
        } else {
            i6 = i13;
        }
        Bitmap bitmap = null;
        if (b4 != null) {
            b4.setDensity(downsampler.n.densityDpi);
            bitmap = TransformationUtils.a(downsampler.m, b4, i6);
            if (!b4.equals(bitmap)) {
                downsampler.m.a(b4);
            }
        }
        return bitmap;
    }

    private static void a(ImageHeaderParser.ImageType imageType, InputStream inputStream, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool, DownsampleStrategy downsampleStrategy, int i2, int i3, int i4, int i5, int i6, BitmapFactory.Options options) throws IOException {
        float f2;
        int i7;
        int i8;
        int i9;
        int i10;
        ImageHeaderParser.ImageType imageType2 = imageType;
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        int i11 = i2;
        int i12 = i3;
        int i13 = i4;
        int i14 = i5;
        int i15 = i6;
        BitmapFactory.Options options2 = options;
        if (i12 > 0 && i13 > 0) {
            if (i11 == 90 || i11 == 270) {
                f2 = downsampleStrategy2.a(i13, i12, i14, i15);
            } else {
                f2 = downsampleStrategy2.a(i12, i13, i14, i15);
            }
            if (f2 > 0.0f) {
                DownsampleStrategy.SampleSizeRounding b2 = downsampleStrategy2.b(i12, i13, i14, i15);
                if (b2 != null) {
                    float f3 = (float) i12;
                    float f4 = (float) i13;
                    int c2 = i12 / c((double) (f2 * f3));
                    int c3 = i13 / c((double) (f2 * f4));
                    if (b2 == DownsampleStrategy.SampleSizeRounding.MEMORY) {
                        i7 = Math.max(c2, c3);
                    } else {
                        i7 = Math.min(c2, c3);
                    }
                    if (Build.VERSION.SDK_INT > 23 || !h.contains(options2.outMimeType)) {
                        int max = Math.max(1, Integer.highestOneBit(i7));
                        i8 = (b2 != DownsampleStrategy.SampleSizeRounding.MEMORY || ((float) max) >= 1.0f / f2) ? max : max << 1;
                    } else {
                        i8 = 1;
                    }
                    options2.inSampleSize = i8;
                    if (imageType2 == ImageHeaderParser.ImageType.JPEG) {
                        float min = (float) Math.min(i8, 8);
                        i9 = (int) Math.ceil((double) (f3 / min));
                        i10 = (int) Math.ceil((double) (f4 / min));
                        int i16 = i8 / 8;
                        if (i16 > 0) {
                            i9 /= i16;
                            i10 /= i16;
                        }
                    } else if (imageType2 == ImageHeaderParser.ImageType.PNG || imageType2 == ImageHeaderParser.ImageType.PNG_A) {
                        float f5 = (float) i8;
                        i9 = (int) Math.floor((double) (f3 / f5));
                        i10 = (int) Math.floor((double) (f4 / f5));
                    } else if (imageType2 == ImageHeaderParser.ImageType.WEBP || imageType2 == ImageHeaderParser.ImageType.WEBP_A) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            float f6 = (float) i8;
                            i9 = Math.round(f3 / f6);
                            i10 = Math.round(f4 / f6);
                        } else {
                            float f7 = (float) i8;
                            i9 = (int) Math.floor((double) (f3 / f7));
                            i10 = (int) Math.floor((double) (f4 / f7));
                        }
                    } else if (i12 % i8 == 0 && i13 % i8 == 0) {
                        i9 = i12 / i8;
                        i10 = i13 / i8;
                    } else {
                        int[] a2 = a(inputStream, options2, decodeCallbacks, bitmapPool);
                        i9 = a2[0];
                        i10 = a2[1];
                    }
                    double a3 = (double) downsampleStrategy2.a(i9, i10, i14, i15);
                    if (Build.VERSION.SDK_INT >= 19) {
                        options2.inTargetDensity = a(a3);
                        options2.inDensity = b(a3);
                    }
                    if (a(options)) {
                        options2.inScaled = true;
                    } else {
                        options2.inTargetDensity = 0;
                        options2.inDensity = 0;
                    }
                    if (Log.isLoggable(f5000a, 2)) {
                        Log.v(f5000a, "Calculate scaling, source: [" + i12 + "x" + i13 + "], target: [" + i14 + "x" + i15 + "], power of two scaled: [" + i9 + "x" + i10 + "], exact scale factor: " + f2 + ", power of 2 sample size: " + i8 + ", adjusted scale factor: " + a3 + ", target density: " + options2.inTargetDensity + ", density: " + options2.inDensity);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("Cannot round with null rounding");
            }
            throw new IllegalArgumentException("Cannot scale with factor: " + f2 + " from: " + downsampleStrategy2 + ", source: [" + i12 + "x" + i13 + "], target: [" + i14 + "x" + i15 + Operators.ARRAY_END_STR);
        } else if (Log.isLoggable(f5000a, 3)) {
            Log.d(f5000a, "Unable to determine dimensions for: " + imageType + " with target [" + i14 + "x" + i15 + Operators.ARRAY_END_STR);
        }
    }

    private static int a(double d2) {
        int b2 = b(d2);
        double d3 = (double) b2;
        Double.isNaN(d3);
        int c2 = c(d3 * d2);
        double d4 = (double) (((float) c2) / ((float) b2));
        Double.isNaN(d4);
        double d5 = (double) c2;
        Double.isNaN(d5);
        return c((d2 / d4) * d5);
    }

    private static int b(double d2) {
        if (d2 > 1.0d) {
            d2 = 1.0d / d2;
        }
        return (int) Math.round(d2 * 2.147483647E9d);
    }

    private boolean a(ImageHeaderParser.ImageType imageType) {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return j.contains(imageType);
    }

    private void a(InputStream inputStream, DecodeFormat decodeFormat, boolean z, boolean z2, BitmapFactory.Options options, int i2, int i3) {
        boolean z3;
        if (!this.q.a(i2, i3, options, decodeFormat, z, z2)) {
            if (decodeFormat == DecodeFormat.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                return;
            }
            try {
                z3 = ImageHeaderParserUtils.a(this.p, inputStream, this.o).hasAlpha();
            } catch (IOException e2) {
                if (Log.isLoggable(f5000a, 3)) {
                    Log.d(f5000a, "Cannot determine whether the image has alpha or not from header, format " + decodeFormat, e2);
                }
                z3 = false;
            }
            options.inPreferredConfig = z3 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            if (options.inPreferredConfig == Bitmap.Config.RGB_565) {
                options.inDither = true;
            }
        }
    }

    private static int[] a(InputStream inputStream, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        options.inJustDecodeBounds = true;
        b(inputStream, options, decodeCallbacks, bitmapPool);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:23|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap b(java.io.InputStream r5, android.graphics.BitmapFactory.Options r6, com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r7, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r8) throws java.io.IOException {
        /*
            boolean r0 = r6.inJustDecodeBounds
            if (r0 == 0) goto L_0x000a
            r0 = 10485760(0xa00000, float:1.469368E-38)
            r5.mark(r0)
            goto L_0x000d
        L_0x000a:
            r7.a()
        L_0x000d:
            int r0 = r6.outWidth
            int r1 = r6.outHeight
            java.lang.String r2 = r6.outMimeType
            java.util.concurrent.locks.Lock r3 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.a()
            r3.lock()
            r3 = 0
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r5, r3, r6)     // Catch:{ IllegalArgumentException -> 0x0030 }
            java.util.concurrent.locks.Lock r7 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.a()
            r7.unlock()
            boolean r6 = r6.inJustDecodeBounds
            if (r6 == 0) goto L_0x002d
            r5.reset()
        L_0x002d:
            return r4
        L_0x002e:
            r5 = move-exception
            goto L_0x0061
        L_0x0030:
            r4 = move-exception
            java.io.IOException r0 = a((java.lang.IllegalArgumentException) r4, (int) r0, (int) r1, (java.lang.String) r2, (android.graphics.BitmapFactory.Options) r6)     // Catch:{ all -> 0x002e }
            java.lang.String r1 = "Downsampler"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0045
            java.lang.String r1 = "Downsampler"
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r1, r2, r0)     // Catch:{ all -> 0x002e }
        L_0x0045:
            android.graphics.Bitmap r1 = r6.inBitmap     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0060
            r5.reset()     // Catch:{ IOException -> 0x005f }
            android.graphics.Bitmap r1 = r6.inBitmap     // Catch:{ IOException -> 0x005f }
            r8.a((android.graphics.Bitmap) r1)     // Catch:{ IOException -> 0x005f }
            r6.inBitmap = r3     // Catch:{ IOException -> 0x005f }
            android.graphics.Bitmap r5 = b(r5, r6, r7, r8)     // Catch:{ IOException -> 0x005f }
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.a()
            r6.unlock()
            return r5
        L_0x005f:
            throw r0     // Catch:{ all -> 0x002e }
        L_0x0060:
            throw r0     // Catch:{ all -> 0x002e }
        L_0x0061:
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.a()
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.b(java.io.InputStream, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool):android.graphics.Bitmap");
    }

    private static boolean a(BitmapFactory.Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    private static void a(int i2, int i3, String str, BitmapFactory.Options options, Bitmap bitmap, int i4, int i5, long j2) {
        Log.v(f5000a, "Decoded " + a(bitmap) + " from [" + i2 + "x" + i3 + "] " + str + " with inBitmap " + b(options) + " for [" + i4 + "x" + i5 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + LogTime.a(j2));
    }

    private static String b(BitmapFactory.Options options) {
        return a(options.inBitmap);
    }

    @Nullable
    @TargetApi(19)
    private static String a(Bitmap bitmap) {
        String str;
        if (bitmap == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            str = " (" + bitmap.getAllocationByteCount() + Operators.BRACKET_END_STR;
        } else {
            str = "";
        }
        return Operators.ARRAY_START_STR + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + str;
    }

    private static IOException a(IllegalArgumentException illegalArgumentException, int i2, int i3, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i2 + ", outHeight: " + i3 + ", outMimeType: " + str + ", inBitmap: " + b(options), illegalArgumentException);
    }

    @TargetApi(26)
    private static void a(BitmapFactory.Options options, BitmapPool bitmapPool, int i2, int i3) {
        Bitmap.Config config;
        if (Build.VERSION.SDK_INT < 26) {
            config = null;
        } else if (options.inPreferredConfig != Bitmap.Config.HARDWARE) {
            config = options.outConfig;
        } else {
            return;
        }
        if (config == null) {
            config = options.inPreferredConfig;
        }
        options.inBitmap = bitmapPool.b(i2, i3, config);
    }

    private static synchronized BitmapFactory.Options a() {
        BitmapFactory.Options poll;
        synchronized (Downsampler.class) {
            synchronized (k) {
                poll = k.poll();
            }
            if (poll == null) {
                poll = new BitmapFactory.Options();
                d(poll);
            }
        }
        return poll;
    }

    private static void c(BitmapFactory.Options options) {
        d(options);
        synchronized (k) {
            k.offer(options);
        }
    }

    private static void d(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }
}
