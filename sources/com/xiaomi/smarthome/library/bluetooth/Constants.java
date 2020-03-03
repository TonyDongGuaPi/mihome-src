package com.xiaomi.smarthome.library.bluetooth;

public class Constants {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18455a = 2;
    public static final int b = 1;
    public static final int c = 3;
    public static final int d = 0;
    public static final int e = 19;

    public static String a(int i) {
        if (i == 19) {
            return "Service Ready";
        }
        switch (i) {
            case 0:
                return "Disconnected";
            case 1:
                return "Connecting";
            case 2:
                return "Connected";
            case 3:
                return "Disconnecting";
            default:
                return String.format("Unknown %d", new Object[]{Integer.valueOf(i)});
        }
    }
}
