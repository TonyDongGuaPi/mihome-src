package com.xiaomi.smarthome.library.bluetooth.utils;

import java.util.UUID;

public class UUIDUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18554a = "0000%04x-0000-1000-8000-00805f9b34fb";
    private static final String b = "0000%4s-0000-1000-8000-00805f9b34fb";

    public static UUID a(int i) {
        return UUID.fromString(String.format("0000%04x-0000-1000-8000-00805f9b34fb", new Object[]{Integer.valueOf(i)}));
    }

    public static UUID a(String str) {
        return UUID.fromString(String.format(b, new Object[]{str}));
    }
}
