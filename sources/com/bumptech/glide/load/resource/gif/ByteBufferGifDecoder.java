package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

public class ByteBufferGifDecoder implements ResourceDecoder<ByteBuffer, GifDrawable> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5018a = "BufferGifDecoder";
    private static final GifDecoderFactory b = new GifDecoderFactory();
    private static final GifHeaderParserPool c = new GifHeaderParserPool();
    private final Context d;
    private final List<ImageHeaderParser> e;
    private final GifHeaderParserPool f;
    private final GifDecoderFactory g;
    private final GifBitmapProvider h;

    public ByteBufferGifDecoder(Context context) {
        this(context, Glide.b(context).j().a(), Glide.b(context).b(), Glide.b(context).c());
    }

    public ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this(context, list, bitmapPool, arrayPool, c, b);
    }

    @VisibleForTesting
    ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool, GifHeaderParserPool gifHeaderParserPool, GifDecoderFactory gifDecoderFactory) {
        this.d = context.getApplicationContext();
        this.e = list;
        this.g = gifDecoderFactory;
        this.h = new GifBitmapProvider(bitmapPool, arrayPool);
        this.f = gifHeaderParserPool;
    }

    public boolean a(@NonNull ByteBuffer byteBuffer, @NonNull Options options) throws IOException {
        return !((Boolean) options.a(GifOptions.b)).booleanValue() && ImageHeaderParserUtils.a(this.e, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    public GifDrawableResource a(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull Options options) {
        GifHeaderParser a2 = this.f.a(byteBuffer);
        try {
            return a(byteBuffer, i, i2, a2, options);
        } finally {
            this.f.a(a2);
        }
    }

    @Nullable
    private GifDrawableResource a(ByteBuffer byteBuffer, int i, int i2, GifHeaderParser gifHeaderParser, Options options) {
        long a2 = LogTime.a();
        try {
            GifHeader b2 = gifHeaderParser.b();
            if (b2.c() > 0) {
                if (b2.d() == 0) {
                    Bitmap.Config config = options.a(GifOptions.f5028a) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                    ByteBuffer byteBuffer2 = byteBuffer;
                    GifDecoder a3 = this.g.a(this.h, b2, byteBuffer, a(b2, i, i2));
                    a3.a(config);
                    a3.e();
                    Bitmap n = a3.n();
                    if (n == null) {
                        if (Log.isLoggable(f5018a, 2)) {
                            Log.v(f5018a, "Decoded GIF from stream in " + LogTime.a(a2));
                        }
                        return null;
                    }
                    GifDrawableResource gifDrawableResource = new GifDrawableResource(new GifDrawable(this.d, a3, UnitTransformation.a(), i, i2, n));
                    if (Log.isLoggable(f5018a, 2)) {
                        Log.v(f5018a, "Decoded GIF from stream in " + LogTime.a(a2));
                    }
                    return gifDrawableResource;
                }
            }
            if (Log.isLoggable(f5018a, 2)) {
                Log.v(f5018a, "Decoded GIF from stream in " + LogTime.a(a2));
            }
            return null;
        } catch (Throwable th) {
            if (Log.isLoggable(f5018a, 2)) {
                Log.v(f5018a, "Decoded GIF from stream in " + LogTime.a(a2));
            }
            throw th;
        }
    }

    private static int a(GifHeader gifHeader, int i, int i2) {
        int i3;
        int min = Math.min(gifHeader.a() / i2, gifHeader.b() / i);
        if (min == 0) {
            i3 = 0;
        } else {
            i3 = Integer.highestOneBit(min);
        }
        int max = Math.max(1, i3);
        if (Log.isLoggable(f5018a, 2) && max > 1) {
            Log.v(f5018a, "Downsampling GIF, sampleSize: " + max + ", target dimens: [" + i + "x" + i2 + "], actual dimens: [" + gifHeader.b() + "x" + gifHeader.a() + Operators.ARRAY_END_STR);
        }
        return max;
    }

    @VisibleForTesting
    static class GifDecoderFactory {
        GifDecoderFactory() {
        }

        /* access modifiers changed from: package-private */
        public GifDecoder a(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
            return new StandardGifDecoder(bitmapProvider, gifHeader, byteBuffer, i);
        }
    }

    @VisibleForTesting
    static class GifHeaderParserPool {

        /* renamed from: a  reason: collision with root package name */
        private final Queue<GifHeaderParser> f5019a = Util.a(0);

        GifHeaderParserPool() {
        }

        /* access modifiers changed from: package-private */
        public synchronized GifHeaderParser a(ByteBuffer byteBuffer) {
            GifHeaderParser poll;
            poll = this.f5019a.poll();
            if (poll == null) {
                poll = new GifHeaderParser();
            }
            return poll.a(byteBuffer);
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(GifHeaderParser gifHeaderParser) {
            gifHeaderParser.a();
            this.f5019a.offer(gifHeaderParser);
        }
    }
}
