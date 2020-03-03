package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ArchTaskExecutor extends TaskExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ArchTaskExecutor f422a;
    @NonNull
    private static final Executor d = new Executor() {
        public void execute(Runnable runnable) {
            ArchTaskExecutor.a().b(runnable);
        }
    };
    @NonNull
    private static final Executor e = new Executor() {
        public void execute(Runnable runnable) {
            ArchTaskExecutor.a().a(runnable);
        }
    };
    @NonNull
    private TaskExecutor b = this.c;
    @NonNull
    private TaskExecutor c = new DefaultTaskExecutor();

    private ArchTaskExecutor() {
    }

    @NonNull
    public static ArchTaskExecutor a() {
        if (f422a != null) {
            return f422a;
        }
        synchronized (ArchTaskExecutor.class) {
            if (f422a == null) {
                f422a = new ArchTaskExecutor();
            }
        }
        return f422a;
    }

    public void a(@Nullable TaskExecutor taskExecutor) {
        if (taskExecutor == null) {
            taskExecutor = this.c;
        }
        this.b = taskExecutor;
    }

    public void a(Runnable runnable) {
        this.b.a(runnable);
    }

    public void b(Runnable runnable) {
        this.b.b(runnable);
    }

    @NonNull
    public static Executor b() {
        return d;
    }

    @NonNull
    public static Executor c() {
        return e;
    }

    public boolean d() {
        return this.b.d();
    }
}
