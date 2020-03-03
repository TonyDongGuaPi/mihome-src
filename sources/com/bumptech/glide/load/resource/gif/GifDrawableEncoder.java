package com.bumptech.glide.load.resource.gif;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;

public class GifDrawableEncoder implements ResourceEncoder<GifDrawable> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5023a = "GifEncoder";

    @NonNull
    public EncodeStrategy a(@NonNull Options options) {
        return EncodeStrategy.SOURCE;
    }

    public boolean a(@NonNull Resource<GifDrawable> resource, @NonNull File file, @NonNull Options options) {
        try {
            ByteBufferUtil.a(resource.d().d(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(f5023a, 5)) {
                Log.w(f5023a, "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
