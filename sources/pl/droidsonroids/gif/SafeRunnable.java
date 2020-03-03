package pl.droidsonroids.gif;

import java.lang.Thread;

abstract class SafeRunnable implements Runnable {
    final GifDrawable c;

    /* access modifiers changed from: package-private */
    public abstract void a();

    SafeRunnable(GifDrawable gifDrawable) {
        this.c = gifDrawable;
    }

    public final void run() {
        try {
            if (!this.c.b()) {
                a();
            }
        } catch (Throwable th) {
            Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
            }
            throw th;
        }
    }
}
