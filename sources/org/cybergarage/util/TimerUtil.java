package org.cybergarage.util;

public final class TimerUtil {
    public static final void wait(int i) {
        try {
            Thread.sleep((long) i);
        } catch (Exception unused) {
        }
    }

    public static final void waitRandom(int i) {
        double random = Math.random();
        double d = (double) i;
        Double.isNaN(d);
        try {
            Thread.sleep((long) ((int) (random * d)));
        } catch (Exception unused) {
        }
    }
}
