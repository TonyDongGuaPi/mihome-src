package pl.droidsonroids.gif;

import android.graphics.Bitmap;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

public class GifDecoder {

    /* renamed from: a  reason: collision with root package name */
    private final GifInfoHandle f11948a;

    public GifDecoder(@NonNull InputSource inputSource) throws IOException {
        this(inputSource, (GifOptions) null);
    }

    public GifDecoder(@NonNull InputSource inputSource, @Nullable GifOptions gifOptions) throws IOException {
        this.f11948a = inputSource.a();
        if (gifOptions != null) {
            this.f11948a.a(gifOptions.f11955a, gifOptions.b);
        }
    }

    public String a() {
        return this.f11948a.e();
    }

    public int b() {
        return this.f11948a.f();
    }

    public long c() {
        return this.f11948a.g();
    }

    public void a(@IntRange(from = 0, to = 2147483647L) int i, @NonNull Bitmap bitmap) {
        a(bitmap);
        this.f11948a.a(i, bitmap);
    }

    public void b(@IntRange(from = 0, to = 2147483647L) int i, @NonNull Bitmap bitmap) {
        a(bitmap);
        this.f11948a.b(i, bitmap);
    }

    public long d() {
        return this.f11948a.m();
    }

    public int a(@IntRange(from = 0) int i) {
        return this.f11948a.b(i);
    }

    public int e() {
        return this.f11948a.i();
    }

    public int f() {
        return this.f11948a.s();
    }

    public int g() {
        return this.f11948a.t();
    }

    public int h() {
        return this.f11948a.u();
    }

    public boolean i() {
        return this.f11948a.u() > 1 && e() > 0;
    }

    public void j() {
        this.f11948a.a();
    }

    private void a(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            throw new IllegalArgumentException("Bitmap is recycled");
        } else if (bitmap.getWidth() < this.f11948a.s() || bitmap.getHeight() < this.f11948a.t()) {
            throw new IllegalArgumentException("Bitmap ia too small, size must be greater than or equal to GIF size");
        } else if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            throw new IllegalArgumentException("Only Config.ARGB_8888 is supported. Current bitmap config: " + bitmap.getConfig());
        }
    }
}
