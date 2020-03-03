package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;

public class Face {

    /* renamed from: a  reason: collision with root package name */
    private final int f5206a;
    private final int b;
    private final int c;
    private final int d;
    @Nullable
    private final String e;
    @Nullable
    private final Age f;

    public Face(int i, int i2, int i3, int i4, @Nullable String str, @Nullable Age age) {
        this.f5206a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = str;
        this.f = age;
    }

    public int a() {
        return this.f5206a;
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

    @Nullable
    public String e() {
        return this.e;
    }

    @Nullable
    public Age f() {
        return this.f;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Face face = (Face) obj;
        if (this.d != face.d || this.c != face.c || this.f5206a != face.f5206a || this.b != face.b) {
            return false;
        }
        if (this.f == null ? face.f == null : this.f.equals(face.f)) {
            return this.e == null ? face.e == null : this.e.equals(face.e);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.f5206a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("x: ");
        sb.append(this.f5206a);
        sb.append(" y: ");
        sb.append(this.b);
        sb.append(" width: ");
        sb.append(this.c);
        sb.append(" height: ");
        sb.append(this.d);
        if (this.e != null) {
            sb.append(" name: ");
            sb.append(this.e);
        }
        if (this.f != null) {
            sb.append(" age: ");
            sb.append(this.f.g());
        }
        return sb.toString();
    }
}
