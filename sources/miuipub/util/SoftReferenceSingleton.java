package miuipub.util;

import java.lang.ref.SoftReference;

public abstract class SoftReferenceSingleton<T> {
    private SoftReference<T> mInstance = null;

    /* access modifiers changed from: protected */
    public abstract T createInstance();

    public final T get() {
        T t;
        synchronized (this) {
            if (this.mInstance == null || (t = this.mInstance.get()) == null) {
                t = createInstance();
                this.mInstance = new SoftReference<>(t);
            }
        }
        return t;
    }
}
