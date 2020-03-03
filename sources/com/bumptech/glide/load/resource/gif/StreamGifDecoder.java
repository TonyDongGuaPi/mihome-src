package com.bumptech.glide.load.resource.gif;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class StreamGifDecoder implements ResourceDecoder<InputStream, GifDrawable> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5029a = "StreamGifDecoder";
    private final List<ImageHeaderParser> b;
    private final ResourceDecoder<ByteBuffer, GifDrawable> c;
    private final ArrayPool d;

    public StreamGifDecoder(List<ImageHeaderParser> list, ResourceDecoder<ByteBuffer, GifDrawable> resourceDecoder, ArrayPool arrayPool) {
        this.b = list;
        this.c = resourceDecoder;
        this.d = arrayPool;
    }

    public boolean a(@NonNull InputStream inputStream, @NonNull Options options) throws IOException {
        return !((Boolean) options.a(GifOptions.b)).booleanValue() && ImageHeaderParserUtils.a(this.b, inputStream, this.d) == ImageHeaderParser.ImageType.GIF;
    }

    public Resource<GifDrawable> a(@NonNull InputStream inputStream, int i, int i2, @NonNull Options options) throws IOException {
        byte[] a2 = a(inputStream);
        if (a2 == null) {
            return null;
        }
        return this.c.a(ByteBuffer.wrap(a2), i, i2, options);
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            if (!Log.isLoggable(f5029a, 5)) {
                return null;
            }
            Log.w(f5029a, "Error reading data from stream", e);
            return null;
        }
    }
}
