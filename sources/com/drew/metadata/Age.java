package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;

public class Age {

    /* renamed from: a  reason: collision with root package name */
    private final int f5204a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    @Nullable
    public static Age a(@NotNull String str) {
        if (str.length() != 19 || str.startsWith("9999:99:99")) {
            return null;
        }
        try {
            return new Age(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)), Integer.parseInt(str.substring(8, 10)), Integer.parseInt(str.substring(11, 13)), Integer.parseInt(str.substring(14, 16)), Integer.parseInt(str.substring(17, 19)));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public Age(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f5204a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
    }

    public int a() {
        return this.f5204a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public String toString() {
        return String.format("%04d:%02d:%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(this.f5204a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f)});
    }

    public String g() {
        StringBuilder sb = new StringBuilder();
        a(sb, this.f5204a, "year");
        a(sb, this.b, "month");
        a(sb, this.c, "day");
        a(sb, this.d, "hour");
        a(sb, this.e, "minute");
        a(sb, this.f, "second");
        return sb.toString();
    }

    private static void a(StringBuilder sb, int i, String str) {
        if (i != 0) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(i);
            sb.append(' ');
            sb.append(str);
            if (i != 1) {
                sb.append('s');
            }
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Age age = (Age) obj;
        return this.c == age.c && this.d == age.d && this.e == age.e && this.b == age.b && this.f == age.f && this.f5204a == age.f5204a;
    }

    public int hashCode() {
        return (((((((((this.f5204a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f;
    }
}
