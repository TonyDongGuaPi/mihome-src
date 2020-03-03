package org.apache.commons.lang.math;

import java.util.Random;

public class RandomUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Random f3396a = new JVMRandom();

    public static int a() {
        return a(f3396a);
    }

    public static int a(Random random) {
        return random.nextInt();
    }

    public static int a(int i) {
        return a(f3396a, i);
    }

    public static int a(Random random, int i) {
        return random.nextInt(i);
    }

    public static long b() {
        return b(f3396a);
    }

    public static long b(Random random) {
        return random.nextLong();
    }

    public static boolean c() {
        return c(f3396a);
    }

    public static boolean c(Random random) {
        return random.nextBoolean();
    }

    public static float d() {
        return d(f3396a);
    }

    public static float d(Random random) {
        return random.nextFloat();
    }

    public static double e() {
        return e(f3396a);
    }

    public static double e(Random random) {
        return random.nextDouble();
    }
}
