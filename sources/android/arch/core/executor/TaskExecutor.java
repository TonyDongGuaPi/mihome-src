package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class TaskExecutor {
    public abstract void a(@NonNull Runnable runnable);

    public abstract void b(@NonNull Runnable runnable);

    public abstract boolean d();

    public void c(@NonNull Runnable runnable) {
        if (d()) {
            runnable.run();
        } else {
            b(runnable);
        }
    }
}
