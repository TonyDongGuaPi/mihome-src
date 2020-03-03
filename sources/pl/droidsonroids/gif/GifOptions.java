package pl.droidsonroids.gif;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;

public class GifOptions {

    /* renamed from: a  reason: collision with root package name */
    char f11955a;
    boolean b;

    public GifOptions() {
        a();
    }

    private void a() {
        this.f11955a = 1;
        this.b = false;
    }

    public void a(@IntRange(from = 1, to = 65535) int i) {
        if (i < 1 || i > 65535) {
            this.f11955a = 1;
        } else {
            this.f11955a = (char) i;
        }
    }

    public void a(boolean z) {
        this.b = z;
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable GifOptions gifOptions) {
        if (gifOptions == null) {
            a();
            return;
        }
        this.b = gifOptions.b;
        this.f11955a = gifOptions.f11955a;
    }
}
