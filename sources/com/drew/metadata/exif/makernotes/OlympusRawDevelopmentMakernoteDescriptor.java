package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class OlympusRawDevelopmentMakernoteDescriptor extends TagDescriptor<OlympusRawDevelopmentMakernoteDirectory> {
    public OlympusRawDevelopmentMakernoteDescriptor(@NotNull OlympusRawDevelopmentMakernoteDirectory olympusRawDevelopmentMakernoteDirectory) {
        super(olympusRawDevelopmentMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 0) {
            return a();
        }
        switch (i) {
            case 264:
                return b();
            case 265:
                return c();
            case 266:
                return d();
            case 267:
                return e();
            case 268:
                return f();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a(0, 4);
    }

    @Nullable
    public String b() {
        return a(264, "sRGB", "Adobe RGB", "Pro Photo RGB");
    }

    @Nullable
    public String c() {
        return a(265, "High Speed", "High Function", "Advanced High Speed", "Advanced High Function");
    }

    @Nullable
    public String d() {
        Integer c = ((OlympusRawDevelopmentMakernoteDirectory) this.f5211a).c(266);
        if (c == null) {
            return null;
        }
        if (c.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        int intValue = c.intValue();
        if ((intValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((intValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((intValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String e() {
        Integer c = ((OlympusRawDevelopmentMakernoteDirectory) this.f5211a).c(267);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 6 || intValue == 8) {
            return "Edited (Portrait)";
        }
        switch (intValue) {
            case 0:
                return "Original";
            case 1:
                return "Edited (Landscape)";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String f() {
        Integer c = ((OlympusRawDevelopmentMakernoteDirectory) this.f5211a).c(268);
        if (c == null) {
            return null;
        }
        if (c.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        int intValue = c.intValue();
        if ((intValue & 1) != 0) {
            sb.append("WB Color Temp, ");
        }
        if (((intValue >> 1) & 1) != 0) {
            sb.append("WB Gray Point, ");
        }
        if (((intValue >> 2) & 1) != 0) {
            sb.append("Saturation, ");
        }
        if (((intValue >> 3) & 1) != 0) {
            sb.append("Contrast, ");
        }
        if (((intValue >> 4) & 1) != 0) {
            sb.append("Sharpness, ");
        }
        if (((intValue >> 5) & 1) != 0) {
            sb.append("Color Space, ");
        }
        if (((intValue >> 6) & 1) != 0) {
            sb.append("High Function, ");
        }
        if (((intValue >> 7) & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
