package pl.droidsonroids.gif;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

public class MultiCallback implements Drawable.Callback {

    /* renamed from: a  reason: collision with root package name */
    private final CopyOnWriteArrayList<CallbackWeakReference> f11976a;
    private final boolean b;

    public MultiCallback() {
        this(false);
    }

    public MultiCallback(boolean z) {
        this.f11976a = new CopyOnWriteArrayList<>();
        this.b = z;
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        for (int i = 0; i < this.f11976a.size(); i++) {
            CallbackWeakReference callbackWeakReference = this.f11976a.get(i);
            Drawable.Callback callback = (Drawable.Callback) callbackWeakReference.get();
            if (callback == null) {
                this.f11976a.remove(callbackWeakReference);
            } else if (!this.b || !(callback instanceof View)) {
                callback.invalidateDrawable(drawable);
            } else {
                ((View) callback).invalidate();
            }
        }
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        for (int i = 0; i < this.f11976a.size(); i++) {
            CallbackWeakReference callbackWeakReference = this.f11976a.get(i);
            Drawable.Callback callback = (Drawable.Callback) callbackWeakReference.get();
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            } else {
                this.f11976a.remove(callbackWeakReference);
            }
        }
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        for (int i = 0; i < this.f11976a.size(); i++) {
            CallbackWeakReference callbackWeakReference = this.f11976a.get(i);
            Drawable.Callback callback = (Drawable.Callback) callbackWeakReference.get();
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            } else {
                this.f11976a.remove(callbackWeakReference);
            }
        }
    }

    public void a(Drawable.Callback callback) {
        for (int i = 0; i < this.f11976a.size(); i++) {
            CallbackWeakReference callbackWeakReference = this.f11976a.get(i);
            if (((Drawable.Callback) callbackWeakReference.get()) == null) {
                this.f11976a.remove(callbackWeakReference);
            }
        }
        this.f11976a.addIfAbsent(new CallbackWeakReference(callback));
    }

    public void b(Drawable.Callback callback) {
        for (int i = 0; i < this.f11976a.size(); i++) {
            CallbackWeakReference callbackWeakReference = this.f11976a.get(i);
            Drawable.Callback callback2 = (Drawable.Callback) callbackWeakReference.get();
            if (callback2 == null || callback2 == callback) {
                this.f11976a.remove(callbackWeakReference);
            }
        }
    }

    static final class CallbackWeakReference extends WeakReference<Drawable.Callback> {
        CallbackWeakReference(Drawable.Callback callback) {
            super(callback);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass() && get() == ((CallbackWeakReference) obj).get()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            Drawable.Callback callback = (Drawable.Callback) get();
            if (callback != null) {
                return callback.hashCode();
            }
            return 0;
        }
    }
}
