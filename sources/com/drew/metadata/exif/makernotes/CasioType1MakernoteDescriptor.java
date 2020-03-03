package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class CasioType1MakernoteDescriptor extends TagDescriptor<CasioType1MakernoteDirectory> {
    public CasioType1MakernoteDescriptor(@NotNull CasioType1MakernoteDirectory casioType1MakernoteDirectory) {
        super(casioType1MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 20) {
            return a();
        }
        switch (i) {
            case 1:
                return l();
            case 2:
                return k();
            case 3:
                return j();
            case 4:
                return i();
            case 5:
                return h();
            case 6:
                return g();
            case 7:
                return f();
            default:
                switch (i) {
                    case 10:
                        return e();
                    case 11:
                        return d();
                    case 12:
                        return c();
                    case 13:
                        return b();
                    default:
                        return super.a(i);
                }
        }
    }

    @Nullable
    public String a() {
        Integer c = ((CasioType1MakernoteDirectory) this.f5211a).c(20);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 64) {
            return "Normal";
        }
        if (intValue == 80) {
            return "Normal (ISO 80 equivalent)";
        }
        if (intValue == 100) {
            return "High";
        }
        if (intValue == 125) {
            return "+1.0";
        }
        if (intValue == 244) {
            return "+3.0";
        }
        if (intValue == 250) {
            return "+2.0";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String b() {
        return a(13, "Normal", "Low", "High");
    }

    @Nullable
    public String c() {
        return a(12, "Normal", "Low", "High");
    }

    @Nullable
    public String d() {
        return a(11, "Normal", "Soft", "Hard");
    }

    @Nullable
    public String e() {
        Integer c = ((CasioType1MakernoteDirectory) this.f5211a).c(10);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 131072) {
            return "2x digital zoom";
        }
        if (intValue == 262144) {
            return "4x digital zoom";
        }
        switch (intValue) {
            case 65536:
                return "No digital zoom";
            case 65537:
                return "2x digital zoom";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String f() {
        Integer c = ((CasioType1MakernoteDirectory) this.f5211a).c(7);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 129) {
            return "Manual";
        }
        switch (intValue) {
            case 1:
                return "Auto";
            case 2:
                return "Tungsten";
            case 3:
                return "Daylight";
            case 4:
                return "Florescent";
            case 5:
                return "Shade";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String g() {
        Integer c = ((CasioType1MakernoteDirectory) this.f5211a).c(6);
        if (c == null) {
            return null;
        }
        return b((double) c.intValue());
    }

    @Nullable
    public String h() {
        Integer c = ((CasioType1MakernoteDirectory) this.f5211a).c(5);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 11) {
            return "Weak";
        }
        if (intValue == 13) {
            return "Normal";
        }
        if (intValue == 15) {
            return "Strong";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String i() {
        return a(4, 1, "Auto", "On", "Off", "Red eye reduction");
    }

    @Nullable
    public String j() {
        return a(3, 2, "Macro", "Auto focus", "Manual focus", "Infinity");
    }

    @Nullable
    public String k() {
        return a(2, 1, "Economy", "Normal", "Fine");
    }

    @Nullable
    public String l() {
        return a(1, 1, "Single shutter", "Panorama", "Night scene", "Portrait", "Landscape");
    }
}
