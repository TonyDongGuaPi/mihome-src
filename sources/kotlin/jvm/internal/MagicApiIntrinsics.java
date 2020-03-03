package kotlin.jvm.internal;

import kotlin.SinceKotlin;

@SinceKotlin(version = "1.2")
public class MagicApiIntrinsics {
    public static int a(int i, long j, long j2, Object obj) {
        return 0;
    }

    public static int a(int i, long j, Object obj) {
        return 0;
    }

    public static int a(int i, Object obj, Object obj2) {
        return 0;
    }

    public static int a(int i, Object obj, Object obj2, Object obj3, Object obj4) {
        return 0;
    }

    public static <T> T a(int i) {
        return null;
    }

    public static <T> T a(Object obj) {
        return null;
    }

    public static <T> T b(int i, long j, long j2, Object obj) {
        return null;
    }

    public static <T> T b(int i, long j, Object obj) {
        return null;
    }

    public static <T> T b(int i, Object obj, Object obj2) {
        return null;
    }

    public static <T> T b(int i, Object obj, Object obj2, Object obj3, Object obj4) {
        return null;
    }

    public static void b(int i) {
    }

    public static void b(Object obj) {
    }

    public static int c(int i) {
        return 0;
    }

    public static int c(Object obj) {
        return 0;
    }
}
