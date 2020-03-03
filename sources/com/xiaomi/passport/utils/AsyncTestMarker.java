package com.xiaomi.passport.utils;

public final class AsyncTestMarker {
    private static final Marker DEFAULT_MARKER = new MarkerDefaultImpl();
    private static volatile Marker marker = DEFAULT_MARKER;

    public interface Marker {
        void decrement();

        void increment();
    }

    public static void setMarker(Marker marker2) {
        marker = marker2;
    }

    public static void resetMarker() {
        marker = DEFAULT_MARKER;
    }

    public static void increment() {
        marker.increment();
    }

    public static void decrement() {
        marker.decrement();
    }

    private static class MarkerDefaultImpl implements Marker {
        public void decrement() {
        }

        public void increment() {
        }

        private MarkerDefaultImpl() {
        }
    }
}
