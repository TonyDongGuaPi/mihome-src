package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.integration.android.IntentIntegrator;

public class KodakMakernoteDescriptor extends TagDescriptor<KodakMakernoteDirectory> {
    public KodakMakernoteDescriptor(@NotNull KodakMakernoteDirectory kodakMakernoteDirectory) {
        super(kodakMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 9:
                return i();
            case 10:
                return h();
            case 27:
                return g();
            case 56:
                return f();
            case 64:
                return e();
            case 92:
                return d();
            case 93:
                return c();
            case 102:
                return b();
            case 107:
                return a();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a(107, "Normal");
    }

    @Nullable
    public String b() {
        Integer c = ((KodakMakernoteDirectory) this.f5211a).c(102);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 32) {
            return "Saturated Color";
        }
        if (intValue == 64) {
            return "Neutral Color";
        }
        if (intValue == 256) {
            return "Saturated Color";
        }
        if (intValue == 512) {
            return "Neutral Color";
        }
        if (intValue == 8192) {
            return "B&W";
        }
        if (intValue == 16384) {
            return "Sepia";
        }
        switch (intValue) {
            case 1:
                return "B&W";
            case 2:
                return "Sepia";
            case 3:
                return "B&W Yellow Filter";
            case 4:
                return "B&W Red Filter";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String c() {
        return a(93, IntentIntegrator.e, IntentIntegrator.d);
    }

    @Nullable
    public String d() {
        Integer c = ((KodakMakernoteDirectory) this.f5211a).c(92);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 16) {
            return "Fill Flash";
        }
        if (intValue == 32) {
            return "Off";
        }
        if (intValue == 64) {
            return "Red Eye";
        }
        switch (intValue) {
            case 0:
                return "Auto";
            case 1:
                return "Fill Flash";
            case 2:
                return "Off";
            case 3:
                return "Red Eye";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String e() {
        return a(64, "Auto", ExifInterface.TAG_FLASH, "Tungsten", "Daylight");
    }

    @Nullable
    public String f() {
        return a(56, "Normal", null, "Macro");
    }

    @Nullable
    public String g() {
        Integer c = ((KodakMakernoteDirectory) this.f5211a).c(27);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Auto";
        }
        if (intValue == 8) {
            return "Aperture Priority";
        }
        if (intValue == 32) {
            return "Manual";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String h() {
        return a(10, "Off", "On");
    }

    @Nullable
    public String i() {
        return a(9, 1, "Fine", "Normal");
    }
}
