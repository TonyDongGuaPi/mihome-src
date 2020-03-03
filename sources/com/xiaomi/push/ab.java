package com.xiaomi.push;

public class ab {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12622a = (ae.f12623a ? "ONEBOX" : "@SHIP.TO.2A2FE0D7@");
    public static final boolean b = f12622a.contains("2A2FE0D7");
    public static final boolean c = (b || "DEBUG".equalsIgnoreCase(f12622a));
    public static final boolean d = "LOGABLE".equalsIgnoreCase(f12622a);
    public static final boolean e = f12622a.contains("YY");
    public static boolean f = f12622a.equalsIgnoreCase("TEST");
    public static final boolean g = "BETA".equalsIgnoreCase(f12622a);
    public static final boolean h;
    private static int i;

    static {
        int i2;
        boolean z = false;
        if (f12622a != null && f12622a.startsWith("RC")) {
            z = true;
        }
        h = z;
        i = 1;
        if (f12622a.equalsIgnoreCase("SANDBOX")) {
            i2 = 2;
        } else if (f12622a.equalsIgnoreCase("ONEBOX")) {
            i2 = 3;
        } else {
            i = 1;
            return;
        }
        i = i2;
    }

    public static void a(int i2) {
        i = i2;
    }

    public static boolean a() {
        return i == 2;
    }

    public static boolean b() {
        return i == 3;
    }

    public static int c() {
        return i;
    }
}
