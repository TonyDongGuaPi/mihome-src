package com.xiaomi.push;

import java.net.InetSocketAddress;

public final class cz {

    /* renamed from: a  reason: collision with root package name */
    private String f12684a;
    private int b;

    public cz(String str, int i) {
        this.f12684a = str;
        this.b = i;
    }

    public static cz a(String str, int i) {
        String str2;
        int lastIndexOf = str.lastIndexOf(":");
        if (lastIndexOf != -1) {
            str2 = str.substring(0, lastIndexOf);
            try {
                int parseInt = Integer.parseInt(str.substring(lastIndexOf + 1));
                if (parseInt > 0) {
                    i = parseInt;
                }
            } catch (NumberFormatException unused) {
            }
        } else {
            str2 = str;
        }
        return new cz(str2, i);
    }

    public static InetSocketAddress b(String str, int i) {
        cz a2 = a(str, i);
        return new InetSocketAddress(a2.b(), a2.a());
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.f12684a;
    }

    public String toString() {
        if (this.b <= 0) {
            return this.f12684a;
        }
        return this.f12684a + ":" + this.b;
    }
}
