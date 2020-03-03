package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class NikonType1MakernoteDescriptor extends TagDescriptor<NikonType1MakernoteDirectory> {
    public NikonType1MakernoteDescriptor(@NotNull NikonType1MakernoteDirectory nikonType1MakernoteDirectory) {
        super(nikonType1MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 3:
                return h();
            case 4:
                return g();
            case 5:
                return f();
            case 6:
                return e();
            case 7:
                return d();
            case 8:
                return c();
            case 10:
                return b();
            case 11:
                return a();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a(11, "None", "Fisheye converter");
    }

    @Nullable
    public String b() {
        Rational q = ((NikonType1MakernoteDirectory) this.f5211a).q(10);
        if (q == null) {
            return null;
        }
        if (q.getNumerator() == 0) {
            return "No digital zoom";
        }
        return q.toSimpleString(true) + "x digital zoom";
    }

    @Nullable
    public String c() {
        Rational q = ((NikonType1MakernoteDirectory) this.f5211a).q(8);
        if (q == null) {
            return null;
        }
        return (q.getNumerator() == 1 && q.getDenominator() == 0) ? "Infinite" : q.toSimpleString(true);
    }

    @Nullable
    public String d() {
        return a(7, "Auto", "Preset", "Daylight", "Incandescence", "Florescence", "Cloudy", "SpeedLight");
    }

    @Nullable
    public String e() {
        return a(6, "ISO80", null, "ISO160", null, "ISO320", "ISO100");
    }

    @Nullable
    public String f() {
        return a(5, "Normal", "Bright +", "Bright -", "Contrast +", "Contrast -");
    }

    @Nullable
    public String g() {
        return a(4, 1, "Color", "Monochrome");
    }

    @Nullable
    public String h() {
        return a(3, 1, "VGA Basic", "VGA Normal", "VGA Fine", "SXGA Basic", "SXGA Normal", "SXGA Fine");
    }
}
