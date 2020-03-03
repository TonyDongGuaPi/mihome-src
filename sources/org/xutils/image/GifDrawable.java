package org.xutils.image;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import org.xutils.common.util.LogUtil;

public class GifDrawable extends Drawable implements Animatable, Runnable {

    /* renamed from: a  reason: collision with root package name */
    private int f11924a;
    private int b = 300;
    private volatile boolean c;
    private final Movie d;
    private final int e;
    private final long f = SystemClock.uptimeMillis();

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public GifDrawable(Movie movie, int i) {
        this.d = movie;
        this.f11924a = i;
        this.e = movie.duration();
    }

    public int a() {
        return this.e;
    }

    public Movie b() {
        return this.d;
    }

    public int c() {
        if (this.f11924a == 0) {
            this.f11924a = this.d.width() * this.d.height() * 3 * 5;
        }
        return this.f11924a;
    }

    public int d() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void draw(Canvas canvas) {
        try {
            this.d.setTime(this.e > 0 ? ((int) (SystemClock.uptimeMillis() - this.f)) % this.e : 0);
            this.d.draw(canvas, 0.0f, 0.0f);
            start();
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void start() {
        if (!isRunning()) {
            this.c = true;
            run();
        }
    }

    public void stop() {
        if (isRunning()) {
            unscheduleSelf(this);
        }
    }

    public boolean isRunning() {
        return this.c && this.e > 0;
    }

    public void run() {
        if (this.e > 0) {
            invalidateSelf();
            scheduleSelf(this, SystemClock.uptimeMillis() + ((long) this.b));
        }
    }

    public int getIntrinsicWidth() {
        return this.d.width();
    }

    public int getIntrinsicHeight() {
        return this.d.height();
    }

    public int getOpacity() {
        return this.d.isOpaque() ? -1 : -3;
    }
}
