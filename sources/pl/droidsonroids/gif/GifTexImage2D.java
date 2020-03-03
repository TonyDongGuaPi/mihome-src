package pl.droidsonroids.gif;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import java.io.IOException;

public class GifTexImage2D {

    /* renamed from: a  reason: collision with root package name */
    private final GifInfoHandle f11957a;

    public GifTexImage2D(InputSource inputSource, @Nullable GifOptions gifOptions) throws IOException {
        gifOptions = gifOptions == null ? new GifOptions() : gifOptions;
        this.f11957a = inputSource.a();
        this.f11957a.a(gifOptions.f11955a, gifOptions.b);
        this.f11957a.y();
    }

    public int a(@IntRange(from = 0) int i) {
        return this.f11957a.b(i);
    }

    public void b(@IntRange(from = 0) int i) {
        this.f11957a.c(i);
    }

    public int a() {
        return this.f11957a.u();
    }

    public int b() {
        return this.f11957a.k();
    }

    public void a(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        this.f11957a.a(f);
    }

    public void a(int i, int i2) {
        this.f11957a.a(i, i2);
    }

    public void b(int i, int i2) {
        this.f11957a.b(i, i2);
    }

    public void c() {
        this.f11957a.w();
    }

    public void d() {
        this.f11957a.x();
    }

    public void e() {
        if (this.f11957a != null) {
            this.f11957a.a();
        }
    }

    public int f() {
        return this.f11957a.s();
    }

    public int g() {
        return this.f11957a.t();
    }

    public int h() {
        return this.f11957a.i();
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            e();
        } finally {
            super.finalize();
        }
    }
}
