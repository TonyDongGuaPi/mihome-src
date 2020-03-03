package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class PanasonicRawDistortionDescriptor extends TagDescriptor<PanasonicRawDistortionDirectory> {
    public PanasonicRawDistortionDescriptor(@NotNull PanasonicRawDistortionDirectory panasonicRawDistortionDirectory) {
        super(panasonicRawDistortionDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 2:
                return a();
            case 4:
                return b();
            case 5:
                return c();
            case 7:
                return d();
            case 8:
                return e();
            case 9:
                return f();
            case 11:
                return g();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String j(int i) {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(i);
        if (c == null) {
            return null;
        }
        return super.a(c.shortValue());
    }

    @Nullable
    public String a() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(2);
        if (c == null) {
            return null;
        }
        return new Rational((long) c.intValue(), 32678).toString();
    }

    @Nullable
    public String b() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(4);
        if (c == null) {
            return null;
        }
        return new Rational((long) c.intValue(), 32678).toString();
    }

    @Nullable
    public String c() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(5);
        if (c == null) {
            return null;
        }
        return Integer.toString(1 / ((c.intValue() / 32768) + 1));
    }

    @Nullable
    public String d() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(7);
        if (c == null) {
            return null;
        }
        switch (c.intValue() & 15) {
            case 0:
                return "Off";
            case 1:
                return "On";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String e() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(8);
        if (c == null) {
            return null;
        }
        return new Rational((long) c.intValue(), 32678).toString();
    }

    @Nullable
    public String f() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(9);
        if (c == null) {
            return null;
        }
        return new Rational((long) c.intValue(), 32678).toString();
    }

    @Nullable
    public String g() {
        Integer c = ((PanasonicRawDistortionDirectory) this.f5211a).c(11);
        if (c == null) {
            return null;
        }
        return new Rational((long) c.intValue(), 32678).toString();
    }
}
