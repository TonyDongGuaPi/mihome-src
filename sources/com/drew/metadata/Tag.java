package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.taobao.weex.el.parse.Operators;

public class Tag {

    /* renamed from: a  reason: collision with root package name */
    private final int f5210a;
    @NotNull
    private final Directory b;

    public Tag(int i, @NotNull Directory directory) {
        this.f5210a = i;
        this.b = directory;
    }

    public int a() {
        return this.f5210a;
    }

    @NotNull
    public String b() {
        return String.format("0x%04x", new Object[]{Integer.valueOf(this.f5210a)});
    }

    @Nullable
    public String c() {
        return this.b.x(this.f5210a);
    }

    public boolean d() {
        return this.b.w(this.f5210a);
    }

    @NotNull
    public String e() {
        return this.b.v(this.f5210a);
    }

    @NotNull
    public String f() {
        return this.b.a();
    }

    @NotNull
    public String toString() {
        String c = c();
        if (c == null) {
            c = this.b.s(a()) + " (unable to formulate description)";
        }
        return Operators.ARRAY_START_STR + this.b.a() + "] " + e() + " - " + c;
    }
}
