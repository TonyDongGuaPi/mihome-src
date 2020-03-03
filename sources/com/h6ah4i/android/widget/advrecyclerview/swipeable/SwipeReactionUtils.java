package com.h6ah4i.android.widget.advrecyclerview.swipeable;

class SwipeReactionUtils {
    public static int a(int i) {
        return (i >>> 0) & 3;
    }

    public static int b(int i) {
        return (i >>> 6) & 3;
    }

    public static int c(int i) {
        return (i >>> 12) & 3;
    }

    public static int d(int i) {
        return (i >>> 18) & 3;
    }

    SwipeReactionUtils() {
    }

    public static boolean e(int i) {
        return a(i) == 2;
    }

    public static boolean f(int i) {
        return b(i) == 2;
    }

    public static boolean g(int i) {
        return c(i) == 2;
    }

    public static boolean h(int i) {
        return d(i) == 2;
    }
}
