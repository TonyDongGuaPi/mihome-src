package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class PentaxMakernoteDescriptor extends TagDescriptor<PentaxMakernoteDirectory> {
    public PentaxMakernoteDescriptor(@NotNull PentaxMakernoteDirectory pentaxMakernoteDirectory) {
        super(pentaxMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 7) {
            return g();
        }
        if (i == 20) {
            return b();
        }
        if (i == 23) {
            return a();
        }
        switch (i) {
            case 1:
                return k();
            case 2:
                return j();
            case 3:
                return i();
            case 4:
                return h();
            default:
                switch (i) {
                    case 10:
                        return f();
                    case 11:
                        return e();
                    case 12:
                        return d();
                    case 13:
                        return c();
                    default:
                        return super.a(i);
                }
        }
    }

    @Nullable
    public String a() {
        return a(23, 1, "Normal", "Black & White", "Sepia");
    }

    @Nullable
    public String b() {
        Integer c = ((PentaxMakernoteDirectory) this.f5211a).c(20);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 10) {
            return "ISO 100";
        }
        if (intValue == 16) {
            return "ISO 200";
        }
        if (intValue == 100) {
            return "ISO 100";
        }
        if (intValue == 200) {
            return "ISO 200";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String c() {
        return a(13, "Normal", "Low", "High");
    }

    @Nullable
    public String d() {
        return a(12, "Normal", "Low", "High");
    }

    @Nullable
    public String e() {
        return a(11, "Normal", "Soft", "Hard");
    }

    @Nullable
    public String f() {
        Float k = ((PentaxMakernoteDirectory) this.f5211a).k(10);
        if (k == null) {
            return null;
        }
        if (k.floatValue() == 0.0f) {
            return "Off";
        }
        return Float.toString(k.floatValue());
    }

    @Nullable
    public String g() {
        return a(7, "Auto", "Daylight", "Shade", "Tungsten", "Fluorescent", "Manual");
    }

    @Nullable
    public String h() {
        return a(4, 1, "Auto", "Flash On", null, "Flash Off", null, "Red-eye Reduction");
    }

    @Nullable
    public String i() {
        return a(3, 2, "Custom", "Auto");
    }

    @Nullable
    public String j() {
        return a(2, "Good", "Better", "Best");
    }

    @Nullable
    public String k() {
        return a(1, "Auto", "Night-scene", "Manual", null, "Multiple");
    }
}
