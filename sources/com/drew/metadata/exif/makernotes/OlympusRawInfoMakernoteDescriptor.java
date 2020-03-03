package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.facebook.internal.AnalyticsEvents;
import com.taobao.weex.el.parse.Operators;

public class OlympusRawInfoMakernoteDescriptor extends TagDescriptor<OlympusRawInfoMakernoteDirectory> {
    public OlympusRawInfoMakernoteDescriptor(@NotNull OlympusRawInfoMakernoteDirectory olympusRawInfoMakernoteDirectory) {
        super(olympusRawInfoMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 0) {
            return a(0, 4);
        }
        if (i == 512) {
            return a();
        }
        if (i == 1537) {
            return b();
        }
        if (i != 4096) {
            return super.a(i);
        }
        return c();
    }

    @Nullable
    public String a() {
        int[] f = ((OlympusRawInfoMakernoteDirectory) this.f5211a).f(512);
        if (f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            sb.append((short) f[i]);
            if (i < f.length - 1) {
                sb.append(" ");
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public String b() {
        int[] f = ((OlympusRawInfoMakernoteDirectory) this.f5211a).f(1537);
        if (f == null) {
            return null;
        }
        Rational[] rationalArr = new Rational[(f.length / 2)];
        for (int i = 0; i < f.length / 2; i++) {
            int i2 = i * 2;
            rationalArr[i] = new Rational((long) ((short) f[i2]), (long) ((short) f[i2 + 1]));
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < rationalArr.length; i3++) {
            sb.append(rationalArr[i3].doubleValue());
            if (i3 < rationalArr.length - 1) {
                sb.append(" ");
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public String c() {
        Integer c = ((OlympusRawInfoMakernoteDirectory) this.f5211a).c(4096);
        if (c == null) {
            return null;
        }
        short shortValue = c.shortValue();
        if (shortValue == 0) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        if (shortValue == 20) {
            return "Tungsten (Incandescent)";
        }
        if (shortValue == 22) {
            return "Evening Sunlight";
        }
        if (shortValue == 256) {
            return "One Touch White Balance";
        }
        if (shortValue == 512) {
            return "Custom 1-4";
        }
        switch (shortValue) {
            case 16:
                return "Shade";
            case 17:
                return "Cloudy";
            case 18:
                return "Fine Weather";
            default:
                switch (shortValue) {
                    case 33:
                        return "Daylight Fluorescent";
                    case 34:
                        return "Day White Fluorescent";
                    case 35:
                        return "Cool White Fluorescent";
                    case 36:
                        return "White Fluorescent";
                    default:
                        return "Unknown (" + c + Operators.BRACKET_END_STR;
                }
        }
    }
}
