package com.libra;

import java.util.HashMap;
import java.util.Locale;

public class Color {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6236a = -16777216;
    public static final int b = -12303292;
    public static final int c = -7829368;
    public static final int d = -3355444;
    public static final int e = -1;
    public static final int f = -65536;
    public static final int g = -16711936;
    public static final int h = -16776961;
    public static final int i = -256;
    public static final int j = -16711681;
    public static final int k = -65281;
    public static final int l = 0;
    private static final HashMap<String, Integer> m = new HashMap<>();

    static {
        m.put("black", -16777216);
        m.put("darkgray", Integer.valueOf(b));
        m.put("gray", Integer.valueOf(c));
        m.put("lightgray", Integer.valueOf(d));
        m.put("white", -1);
        m.put("red", -65536);
        m.put("green", Integer.valueOf(g));
        m.put("blue", Integer.valueOf(h));
        m.put("yellow", -256);
        m.put("cyan", Integer.valueOf(j));
        m.put("magenta", Integer.valueOf(k));
        m.put("aqua", Integer.valueOf(j));
        m.put("fuchsia", Integer.valueOf(k));
        m.put("darkgrey", Integer.valueOf(b));
        m.put("grey", Integer.valueOf(c));
        m.put("lightgrey", Integer.valueOf(d));
        m.put("lime", Integer.valueOf(g));
        m.put("maroon", -8388608);
        m.put("navy", -16777088);
        m.put("olive", -8355840);
        m.put("purple", -8388480);
        m.put("silver", -4144960);
        m.put("teal", -16744320);
    }

    public static int a(String str) {
        if (str.charAt(0) == '#') {
            long parseLong = Long.parseLong(str.substring(1), 16);
            if (str.length() == 7) {
                parseLong |= -16777216;
            } else if (str.length() != 9) {
                throw new IllegalArgumentException("Unknown color");
            }
            return (int) parseLong;
        }
        Integer num = m.get(str.toLowerCase(Locale.ROOT));
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Unknown color");
    }
}
