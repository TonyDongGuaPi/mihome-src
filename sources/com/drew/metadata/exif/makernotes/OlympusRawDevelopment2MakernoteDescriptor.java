package com.drew.metadata.exif.makernotes;

import com.alipay.zoloz.toyger.BuildConfig;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;

public class OlympusRawDevelopment2MakernoteDescriptor extends TagDescriptor<OlympusRawDevelopment2MakernoteDirectory> {
    private static final HashMap<Integer, String> c = new HashMap<>();

    public OlympusRawDevelopment2MakernoteDescriptor(@NotNull OlympusRawDevelopment2MakernoteDirectory olympusRawDevelopment2MakernoteDirectory) {
        super(olympusRawDevelopment2MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 0) {
            return a();
        }
        if (i == 256) {
            return b();
        }
        if (i == 289) {
            return i();
        }
        switch (i) {
            case 265:
                return c();
            case 266:
                return d();
            case 267:
                return e();
            case 268:
                return f();
            default:
                switch (i) {
                    case 272:
                        return g();
                    case 273:
                        return h();
                    default:
                        return super.a(i);
                }
        }
    }

    @Nullable
    public String a() {
        return a(0, 4);
    }

    @Nullable
    public String b() {
        return a(256, 1, "Color Temperature", "Gray Point");
    }

    @Nullable
    public String c() {
        return a(265, "sRGB", "Adobe RGB", "Pro Photo RGB");
    }

    @Nullable
    public String d() {
        Integer c2 = ((OlympusRawDevelopment2MakernoteDirectory) this.f5211a).c(266);
        if (c2 == null) {
            return null;
        }
        if (c2.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        int intValue = c2.intValue();
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
        return a(267, "High Speed", "High Function", "Advanced High Speed", "Advanced High Function");
    }

    @Nullable
    public String f() {
        Integer c2 = ((OlympusRawDevelopment2MakernoteDirectory) this.f5211a).c(268);
        if (c2 == null) {
            return null;
        }
        int intValue = c2.intValue();
        if (intValue == 256) {
            return "Monotone";
        }
        if (intValue == 512) {
            return "Sepia";
        }
        switch (intValue) {
            case 1:
                return "Vivid";
            case 2:
                return "Natural";
            case 3:
                return "Muted";
            default:
                return "Unknown (" + c2 + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String g() {
        return a(272, "Neutral", "Yellow", BuildConfig.UI_ORANGE, "Red", "Green");
    }

    @Nullable
    public String h() {
        return a(273, "Neutral", "Sepia", "Blue", "Purple", "Green");
    }

    @Nullable
    public String i() {
        return j(289);
    }

    @Nullable
    public String j(int i) {
        int[] f = ((OlympusRawDevelopment2MakernoteDirectory) this.f5211a).f(i);
        if (f == null || f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < f.length; i2++) {
            if (i2 == 0) {
                sb.append(c.containsKey(Integer.valueOf(f[i2])) ? c.get(Integer.valueOf(f[i2])) : "[unknown]");
            } else {
                sb.append(f[i2]);
                sb.append("; ");
            }
            sb.append("; ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    static {
        c.put(0, "Off");
        c.put(1, "Soft Focus");
        c.put(2, "Pop Art");
        c.put(3, "Pale & Light Color");
        c.put(4, "Light Tone");
        c.put(5, "Pin Hole");
        c.put(6, "Grainy Film");
        c.put(9, "Diorama");
        c.put(10, "Cross Process");
        c.put(12, "Fish Eye");
        c.put(13, "Drawing");
        c.put(14, "Gentle Sepia");
        c.put(15, "Pale & Light Color II");
        c.put(16, "Pop Art II");
        c.put(17, "Pin Hole II");
        c.put(18, "Pin Hole III");
        c.put(19, "Grainy Film II");
        c.put(20, "Dramatic Tone");
        c.put(21, "Punk");
        c.put(22, "Soft Focus 2");
        c.put(23, "Sparkle");
        c.put(24, "Watercolor");
        c.put(25, "Key Line");
        c.put(26, "Key Line II");
        c.put(27, "Miniature");
        c.put(28, "Reflection");
        c.put(29, "Fragmented");
        c.put(31, "Cross Process II");
        c.put(32, "Dramatic Tone II");
        c.put(33, "Watercolor I");
        c.put(34, "Watercolor II");
        c.put(35, "Diorama II");
        c.put(36, "Vintage");
        c.put(37, "Vintage II");
        c.put(38, "Vintage III");
        c.put(39, "Partial Color");
        c.put(40, "Partial Color II");
        c.put(41, "Partial Color III");
    }
}
