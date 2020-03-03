package pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

class RenderTask extends SafeRunnable {
    RenderTask(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    public void a() {
        long a2 = this.c.f.a(this.c.e);
        if (a2 >= 0) {
            this.c.c = SystemClock.uptimeMillis() + a2;
            if (this.c.isVisible() && this.c.b && !this.c.h) {
                this.c.f11949a.remove(this);
                this.c.j = this.c.f11949a.schedule(this, a2, TimeUnit.MILLISECONDS);
            }
            if (!this.c.g.isEmpty() && this.c.n() == this.c.f.u() - 1) {
                this.c.i.sendEmptyMessageAtTime(this.c.o(), this.c.c);
            }
        } else {
            this.c.c = Long.MIN_VALUE;
            this.c.b = false;
        }
        if (this.c.isVisible() && !this.c.i.hasMessages(-1)) {
            this.c.i.sendEmptyMessageAtTime(-1, 0);
        }
    }
}
