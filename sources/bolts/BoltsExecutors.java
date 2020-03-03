package bolts;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class BoltsExecutors {

    /* renamed from: a  reason: collision with root package name */
    private static final BoltsExecutors f498a = new BoltsExecutors();
    private final ExecutorService b;
    private final ScheduledExecutorService c;
    private final Executor d;

    private static boolean d() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains("android");
    }

    private BoltsExecutors() {
        this.b = !d() ? Executors.newCachedThreadPool() : AndroidExecutors.a();
        this.c = Executors.newSingleThreadScheduledExecutor();
        this.d = new ImmediateExecutor();
    }

    public static ExecutorService a() {
        return f498a.b;
    }

    static ScheduledExecutorService b() {
        return f498a.c;
    }

    static Executor c() {
        return f498a.d;
    }

    private static class ImmediateExecutor implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private static final int f499a = 15;
        private ThreadLocal<Integer> b;

        private ImmediateExecutor() {
            this.b = new ThreadLocal<>();
        }

        private int a() {
            Integer num = this.b.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() + 1;
            this.b.set(Integer.valueOf(intValue));
            return intValue;
        }

        private int b() {
            Integer num = this.b.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.b.remove();
            } else {
                this.b.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (a() <= 15) {
                try {
                    runnable.run();
                } finally {
                    b();
                }
            } else {
                BoltsExecutors.a().execute(runnable);
            }
        }
    }
}
