package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public interface GifDecoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4829a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GifDecodeStatus {
    }

    public interface BitmapProvider {
        @NonNull
        Bitmap a(int i, int i2, @NonNull Bitmap.Config config);

        void a(@NonNull Bitmap bitmap);

        void a(@NonNull byte[] bArr);

        void a(@NonNull int[] iArr);

        @NonNull
        byte[] a(int i);

        @NonNull
        int[] b(int i);
    }

    int a();

    int a(int i);

    int a(@Nullable InputStream inputStream, int i);

    int a(@Nullable byte[] bArr);

    void a(@NonNull Bitmap.Config config);

    void a(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer);

    void a(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i);

    void a(@NonNull GifHeader gifHeader, @NonNull byte[] bArr);

    int b();

    @NonNull
    ByteBuffer c();

    int d();

    void e();

    int f();

    int g();

    int h();

    void i();

    @Deprecated
    int j();

    int k();

    int l();

    int m();

    @Nullable
    Bitmap n();

    void o();
}
