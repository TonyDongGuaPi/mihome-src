package kotlin.jvm.internal;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.Arrays;
import java.util.List;
import kotlin.KotlinNullPointerException;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private Intrinsics() {
    }

    public static String a(String str, Object obj) {
        return str + obj;
    }

    public static void a(Object obj) {
        if (obj == null) {
            a();
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            a(str);
        }
    }

    public static void a() {
        throw ((KotlinNullPointerException) a(new KotlinNullPointerException()));
    }

    public static void a(String str) {
        throw ((KotlinNullPointerException) a(new KotlinNullPointerException(str)));
    }

    public static void b(String str) {
        throw ((UninitializedPropertyAccessException) a(new UninitializedPropertyAccessException(str)));
    }

    public static void c(String str) {
        b("lateinit property " + str + " has not been initialized");
    }

    public static void b() {
        throw ((AssertionError) a(new AssertionError()));
    }

    public static void d(String str) {
        throw ((AssertionError) a(new AssertionError(str)));
    }

    public static void c() {
        throw ((IllegalArgumentException) a(new IllegalArgumentException()));
    }

    public static void e(String str) {
        throw ((IllegalArgumentException) a(new IllegalArgumentException(str)));
    }

    public static void d() {
        throw ((IllegalStateException) a(new IllegalStateException()));
    }

    public static void f(String str) {
        throw ((IllegalStateException) a(new IllegalStateException(str)));
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException(str + " must not be null")));
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException(str)));
        }
    }

    public static void a(Object obj, String str, String str2) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException("Method specified as non-null returned null: " + str + "." + str2)));
        }
    }

    public static void d(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException(str)));
        }
    }

    public static void b(Object obj, String str, String str2) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException("Field specified as non-null is null: " + str + "." + str2)));
        }
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) a(new IllegalStateException(str)));
        }
    }

    public static void f(Object obj, String str) {
        if (obj == null) {
            j(str);
        }
    }

    public static void g(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalArgumentException) a(new IllegalArgumentException(str)));
        }
    }

    private static void j(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        throw ((IllegalArgumentException) a(new IllegalArgumentException("Parameter specified as non-null is null: method " + className + "." + methodName + ", parameter " + str)));
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @SinceKotlin(version = "1.1")
    public static boolean a(Double d, Double d2) {
        if (d == null) {
            return d2 == null;
        }
        if (d2 == null || d.doubleValue() != d2.doubleValue()) {
            return false;
        }
    }

    @SinceKotlin(version = "1.1")
    public static boolean a(Double d, double d2) {
        return d != null && d.doubleValue() == d2;
    }

    @SinceKotlin(version = "1.1")
    public static boolean a(double d, Double d2) {
        return d2 != null && d == d2.doubleValue();
    }

    @SinceKotlin(version = "1.1")
    public static boolean a(Float f, Float f2) {
        if (f == null) {
            return f2 == null;
        }
        if (f2 == null || f.floatValue() != f2.floatValue()) {
            return false;
        }
    }

    @SinceKotlin(version = "1.1")
    public static boolean a(Float f, float f2) {
        return f != null && f.floatValue() == f2;
    }

    @SinceKotlin(version = "1.1")
    public static boolean a(float f, Float f2) {
        return f2 != null && f == f2.floatValue();
    }

    public static void e() {
        g("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void g(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static void a(int i, String str) {
        e();
    }

    public static void a(int i, String str, String str2) {
        g(str2);
    }

    public static void f() {
        e();
    }

    public static void h(String str) {
        g(str);
    }

    public static void i(String str) throws ClassNotFoundException {
        String replace = str.replace(IOUtils.f15883a, '.');
        try {
            Class.forName(replace);
        } catch (ClassNotFoundException e) {
            throw ((ClassNotFoundException) a(new ClassNotFoundException("Class " + replace + " is not found. Please update the Kotlin runtime to the latest version", e)));
        }
    }

    public static void a(String str, String str2) throws ClassNotFoundException {
        String replace = str.replace(IOUtils.f15883a, '.');
        try {
            Class.forName(replace);
        } catch (ClassNotFoundException e) {
            throw ((ClassNotFoundException) a(new ClassNotFoundException("Class " + replace + " is not found: this code requires the Kotlin runtime of version at least " + str2, e)));
        }
    }

    private static <T extends Throwable> T a(T t) {
        return a(t, Intrinsics.class.getName());
    }

    static <T extends Throwable> T a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }
}
