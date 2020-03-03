package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class OlympusImageProcessingMakernoteDescriptor extends TagDescriptor<OlympusImageProcessingMakernoteDirectory> {
    public OlympusImageProcessingMakernoteDescriptor(@NotNull OlympusImageProcessingMakernoteDirectory olympusImageProcessingMakernoteDirectory) {
        super(olympusImageProcessingMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 0) {
            return a();
        }
        if (i == 512) {
            return b();
        }
        if (i == 4124) {
            return f();
        }
        if (i == 4370) {
            return g();
        }
        switch (i) {
            case 4112:
                return c();
            case 4113:
                return d();
            case 4114:
                return e();
            default:
                switch (i) {
                    case OlympusImageProcessingMakernoteDirectory.am /*6400*/:
                        return h();
                    case OlympusImageProcessingMakernoteDirectory.an /*6401*/:
                        return i();
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
        int[] f = ((OlympusImageProcessingMakernoteDirectory) this.f5211a).f(512);
        if (f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            sb.append((short) f[i]);
        }
        return sb.toString();
    }

    @Nullable
    public String c() {
        Integer c = ((OlympusImageProcessingMakernoteDirectory) this.f5211a).c(4112);
        if (c == null) {
            return null;
        }
        if (c.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        short shortValue = c.shortValue();
        if ((shortValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((shortValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((shortValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String d() {
        return a(4113, "Off", "On");
    }

    @Nullable
    public String e() {
        return a(4114, "Off", "On");
    }

    @Nullable
    public String f() {
        int[] f = ((OlympusImageProcessingMakernoteDirectory) this.f5211a).f(4124);
        if (f == null) {
            Integer c = ((OlympusImageProcessingMakernoteDirectory) this.f5211a).c(4124);
            if (c == null) {
                return null;
            }
            f = new int[]{c.intValue()};
        }
        if (f.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        short s = (short) f[0];
        if (s != 0) {
            switch (s) {
                case 2:
                    sb.append("On (2 frames)");
                    break;
                case 3:
                    sb.append("On (3 frames)");
                    break;
                default:
                    sb.append("Unknown (");
                    sb.append((short) f[0]);
                    sb.append(Operators.BRACKET_END_STR);
                    break;
            }
        } else {
            sb.append("Off");
        }
        if (f.length > 1) {
            sb.append("; ");
            sb.append((short) f[1]);
        }
        return sb.toString();
    }

    @Nullable
    public String g() {
        byte[] g = ((OlympusImageProcessingMakernoteDirectory) this.f5211a).g(OlympusImageProcessingMakernoteDirectory.ae);
        if (g == null || g.length < 2) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Byte.valueOf(g[0]), Byte.valueOf(g[1])});
        if (format.equals("1 1")) {
            return "4:3";
        }
        if (format.equals("1 4")) {
            return "1:1";
        }
        if (format.equals("2 1")) {
            return "3:2 (RAW)";
        }
        if (format.equals("2 2")) {
            return "3:2";
        }
        if (format.equals("3 1")) {
            return "16:9 (RAW)";
        }
        if (format.equals("3 3")) {
            return "16:9";
        }
        if (format.equals("4 1")) {
            return "1:1 (RAW)";
        }
        if (format.equals("4 4")) {
            return "6:6";
        }
        if (format.equals("5 5")) {
            return "5:4";
        }
        if (format.equals("6 6")) {
            return "7:6";
        }
        if (format.equals("7 7")) {
            return "6:5";
        }
        if (format.equals("8 8")) {
            return "7:5";
        }
        if (format.equals("9 1")) {
            return "3:4 (RAW)";
        }
        if (format.equals("9 9")) {
            return "3:4";
        }
        return "Unknown (" + format + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String h() {
        byte[] g = ((OlympusImageProcessingMakernoteDirectory) this.f5211a).g(OlympusImageProcessingMakernoteDirectory.am);
        if (g == null || g.length < 2) {
            return null;
        }
        String format = String.format("%d %d", new Object[]{Byte.valueOf(g[0]), Byte.valueOf(g[1])});
        if (format.equals("0 0")) {
            return "Off";
        }
        if (format.equals("0 1")) {
            return "On";
        }
        return "Unknown (" + format + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String i() {
        return a((int) OlympusImageProcessingMakernoteDirectory.an, "Vertical", "Horizontal");
    }
}
