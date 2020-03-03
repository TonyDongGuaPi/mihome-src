package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferEncoder implements Encoder<ByteBuffer> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4939a = "ByteBufferEncoder";

    public boolean a(@NonNull ByteBuffer byteBuffer, @NonNull File file, @NonNull Options options) {
        try {
            ByteBufferUtil.a(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(f4939a, 3)) {
                Log.d(f4939a, "Failed to write data", e);
            }
            return false;
        }
    }
}
