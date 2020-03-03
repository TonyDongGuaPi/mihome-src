package com.drew.metadata.jfif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class JfifDescriptor extends TagDescriptor<JfifDirectory> {
    public JfifDescriptor(@NotNull JfifDirectory jfifDirectory) {
        super(jfifDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 5) {
            return a();
        }
        if (i == 10) {
            return b();
        }
        switch (i) {
            case 7:
                return d();
            case 8:
                return c();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        Integer c = ((JfifDirectory) this.f5211a).c(5);
        if (c == null) {
            return null;
        }
        return String.format("%d.%d", new Object[]{Integer.valueOf((c.intValue() & 65280) >> 8), Integer.valueOf(c.intValue() & 255)});
    }

    @Nullable
    public String b() {
        Integer c = ((JfifDirectory) this.f5211a).c(10);
        if (c == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = c;
        objArr[1] = c.intValue() == 1 ? "" : "s";
        return String.format("%d dot%s", objArr);
    }

    @Nullable
    public String c() {
        Integer c = ((JfifDirectory) this.f5211a).c(8);
        if (c == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = c;
        objArr[1] = c.intValue() == 1 ? "" : "s";
        return String.format("%d dot%s", objArr);
    }

    @Nullable
    public String d() {
        Integer c = ((JfifDirectory) this.f5211a).c(7);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 0:
                return "none";
            case 1:
                return "inch";
            case 2:
                return "centimetre";
            default:
                return "unit";
        }
    }
}
