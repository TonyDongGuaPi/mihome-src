package pl.droidsonroids.gif;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.Iterator;

class InvalidationHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    static final int f11974a = -1;
    private final WeakReference<GifDrawable> b;

    InvalidationHandler(GifDrawable gifDrawable) {
        super(Looper.getMainLooper());
        this.b = new WeakReference<>(gifDrawable);
    }

    public void handleMessage(@NonNull Message message) {
        GifDrawable gifDrawable = (GifDrawable) this.b.get();
        if (gifDrawable != null) {
            if (message.what == -1) {
                gifDrawable.invalidateSelf();
                return;
            }
            Iterator<AnimationListener> it = gifDrawable.g.iterator();
            while (it.hasNext()) {
                it.next().a(message.what);
            }
        }
    }
}
