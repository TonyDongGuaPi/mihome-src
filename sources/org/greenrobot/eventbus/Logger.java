package org.greenrobot.eventbus;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.io.PrintStream;
import java.util.logging.Level;

public interface Logger {
    void a(Level level, String str);

    void a(Level level, String str, Throwable th);

    public static class AndroidLogger implements Logger {

        /* renamed from: a  reason: collision with root package name */
        static final boolean f3487a;
        private final String b;

        static {
            boolean z = false;
            try {
                if (Class.forName("android.util.Log") != null) {
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            }
            f3487a = z;
        }

        public static boolean a() {
            return f3487a;
        }

        public AndroidLogger(String str) {
            this.b = str;
        }

        public void a(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(a(level), this.b, str);
            }
        }

        public void a(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                int a2 = a(level);
                String str2 = this.b;
                Log.println(a2, str2, str + "\n" + Log.getStackTraceString(th));
            }
        }

        /* access modifiers changed from: protected */
        public int a(Level level) {
            int intValue = level.intValue();
            if (intValue < 800) {
                return intValue < 500 ? 2 : 3;
            }
            if (intValue < 900) {
                return 4;
            }
            return intValue < 1000 ? 5 : 6;
        }
    }

    public static class JavaLogger implements Logger {

        /* renamed from: a  reason: collision with root package name */
        protected final java.util.logging.Logger f3488a;

        public JavaLogger(String str) {
            this.f3488a = java.util.logging.Logger.getLogger(str);
        }

        public void a(Level level, String str) {
            this.f3488a.log(level, str);
        }

        public void a(Level level, String str, Throwable th) {
            this.f3488a.log(level, str, th);
        }
    }

    public static class SystemOutLogger implements Logger {
        public void a(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println(Operators.ARRAY_START_STR + level + "] " + str);
        }

        public void a(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println(Operators.ARRAY_START_STR + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }
}
