package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.stats.SensorsDataManager;

public class FujifilmMakernoteDescriptor extends TagDescriptor<FujifilmMakernoteDirectory> {
    public FujifilmMakernoteDescriptor(@NotNull FujifilmMakernoteDirectory fujifilmMakernoteDirectory) {
        super(fujifilmMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 0:
                return x();
            case 4097:
                return a();
            case 4098:
                return b();
            case 4099:
                return c();
            case 4100:
                return d();
            case 4102:
                return e();
            case 4107:
                return f();
            case 4110:
                return g();
            case 4112:
                return h();
            case 4113:
                return i();
            case 4128:
                return j();
            case 4129:
                return k();
            case 4144:
                return l();
            case 4145:
                return m();
            case 4147:
                return n();
            case 4148:
                return o();
            case FujifilmMakernoteDirectory.z /*4352*/:
                return p();
            case FujifilmMakernoteDirectory.B /*4624*/:
                return q();
            case FujifilmMakernoteDirectory.C /*4864*/:
                return r();
            case FujifilmMakernoteDirectory.D /*4865*/:
                return s();
            case FujifilmMakernoteDirectory.E /*4866*/:
                return t();
            case 5120:
                return u();
            case FujifilmMakernoteDirectory.H /*5121*/:
                return v();
            case FujifilmMakernoteDirectory.I /*5122*/:
                return w();
            default:
                return super.a(i);
        }
    }

    @Nullable
    private String x() {
        return a(0, 2);
    }

    @Nullable
    public String a() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4097);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 130) {
            return "Medium Soft";
        }
        if (intValue == 132) {
            return "Medium Hard";
        }
        if (intValue == 32768) {
            return "Film Simulation";
        }
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 1:
                return "Softest";
            case 2:
                return "Soft";
            case 3:
                return "Normal";
            case 4:
                return "Hard";
            case 5:
                return "Hardest";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String b() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4098);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Auto";
        }
        if (intValue == 256) {
            return "Daylight";
        }
        if (intValue == 512) {
            return "Cloudy";
        }
        if (intValue == 1024) {
            return "Incandescence";
        }
        if (intValue == 1280) {
            return ExifInterface.TAG_FLASH;
        }
        if (intValue == 4080) {
            return "Kelvin";
        }
        switch (intValue) {
            case 768:
                return "Daylight Fluorescent";
            case 769:
                return "Day White Fluorescent";
            case 770:
                return "White Fluorescent";
            case 771:
                return "Warm White Fluorescent";
            case 772:
                return "Living Room Warm White Fluorescent";
            default:
                switch (intValue) {
                    case 3840:
                        return "Custom White Balance";
                    case OlympusMakernoteDirectory.O /*3841*/:
                        return "Custom White Balance 2";
                    case 3842:
                        return "Custom White Balance 3";
                    case 3843:
                        return "Custom White Balance 4";
                    case 3844:
                        return "Custom White Balance 5";
                    default:
                        return "Unknown (" + c + Operators.BRACKET_END_STR;
                }
        }
    }

    @Nullable
    public String c() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4099);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Normal";
        }
        if (intValue == 128) {
            return "Medium High";
        }
        if (intValue == 256) {
            return "High";
        }
        if (intValue == 384) {
            return "Medium Low";
        }
        if (intValue == 512) {
            return "Low";
        }
        if (intValue == 32768) {
            return "Film Simulation";
        }
        switch (intValue) {
            case 768:
                return "None (B&W)";
            case 769:
                return "B&W Green Filter";
            case 770:
                return "B&W Yellow Filter";
            case 771:
                return "B&W Blue Filter";
            case 772:
                return "B&W Sepia";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String d() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4100);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Normal";
        }
        if (intValue == 128) {
            return "Medium High";
        }
        if (intValue == 256) {
            return "High";
        }
        if (intValue == 384) {
            return "Medium Low";
        }
        if (intValue == 512) {
            return "Low";
        }
        if (intValue == 768) {
            return "None (B&W)";
        }
        if (intValue == 32768) {
            return "Film Simulation";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String e() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4102);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Normal";
        }
        if (intValue == 256) {
            return "High";
        }
        if (intValue == 768) {
            return "Low";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String f() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4107);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 64) {
            return "Low";
        }
        if (intValue == 128) {
            return "Normal";
        }
        if (intValue == 256) {
            return SensorsDataManager.u;
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String g() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4110);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Normal";
        }
        if (intValue == 256) {
            return "Strong";
        }
        if (intValue == 512) {
            return "Weak";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String h() {
        return a(4112, "Auto", "On", "Off", "Red-eye Reduction", "External");
    }

    @Nullable
    public String i() {
        Rational q = ((FujifilmMakernoteDirectory) this.f5211a).q(4113);
        if (q == null) {
            return null;
        }
        return q.toSimpleString(false) + " EV (Apex)";
    }

    @Nullable
    public String j() {
        return a(4128, "Off", "On");
    }

    @Nullable
    public String k() {
        return a(4129, "Auto Focus", "Manual Focus");
    }

    @Nullable
    public String l() {
        return a(4144, "Off", "On");
    }

    @Nullable
    public String m() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4145);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 256) {
            return "Aperture priority AE";
        }
        if (intValue == 512) {
            return "Shutter priority AE";
        }
        if (intValue == 768) {
            return "Manual exposure";
        }
        switch (intValue) {
            case 0:
                return "Auto";
            case 1:
                return "Portrait scene";
            case 2:
                return "Landscape scene";
            case 3:
                return "Macro";
            case 4:
                return "Sports scene";
            case 5:
                return "Night scene";
            case 6:
                return "Program AE";
            case 7:
                return "Natural Light";
            case 8:
                return "Anti-blur";
            case 9:
                return "Beach & Snow";
            case 10:
                return "Sunset";
            case 11:
                return "Museum";
            case 12:
                return "Party";
            case 13:
                return "Flower";
            case 14:
                return "Text";
            case 15:
                return "Natural Light & Flash";
            case 16:
                return "Beach";
            case 17:
                return "Snow";
            case 18:
                return "Fireworks";
            case 19:
                return "Underwater";
            case 20:
                return "Portrait with Skin Correction";
            default:
                switch (intValue) {
                    case 22:
                        return "Panorama";
                    case 23:
                        return "Night (Tripod)";
                    case 24:
                        return "Pro Low-light";
                    case 25:
                        return "Pro Focus";
                    default:
                        switch (intValue) {
                            case 27:
                                return "Dog Face Detection";
                            case 28:
                                return "Cat Face Detection";
                            default:
                                return "Unknown (" + c + Operators.BRACKET_END_STR;
                        }
                }
        }
    }

    @Nullable
    public String n() {
        return a(4147, "Auto", "Manual");
    }

    @Nullable
    public String o() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(4148);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 256) {
            return "HR (High Resolution)";
        }
        if (intValue == 512) {
            return "SN (Signal to Noise Priority)";
        }
        if (intValue == 768) {
            return "DR (Dynamic Range Priority)";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String p() {
        return a((int) FujifilmMakernoteDirectory.z, "Off", "On", "No Flash & Flash");
    }

    @Nullable
    public String q() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(FujifilmMakernoteDirectory.B);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Standard";
        }
        if (intValue == 16) {
            return "Chrome";
        }
        if (intValue == 48) {
            return "B&W";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String r() {
        return a((int) FujifilmMakernoteDirectory.C, "No Blur Warning", "Blur warning");
    }

    @Nullable
    public String s() {
        return a((int) FujifilmMakernoteDirectory.D, "Good Focus", "Out Of Focus");
    }

    @Nullable
    public String t() {
        return a((int) FujifilmMakernoteDirectory.E, "AE Good", "Over Exposed");
    }

    @Nullable
    public String u() {
        return a(5120, 1, "Standard", null, "Wide");
    }

    @Nullable
    public String v() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(FujifilmMakernoteDirectory.H);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "F0/Standard (Provia) ";
        }
        if (intValue == 256) {
            return "F1/Studio Portrait";
        }
        if (intValue == 272) {
            return "F1a/Studio Portrait Enhanced Saturation";
        }
        if (intValue == 288) {
            return "F1b/Studio Portrait Smooth Skin Tone (Astia)";
        }
        if (intValue == 304) {
            return "F1c/Studio Portrait Increased Sharpness";
        }
        if (intValue == 512) {
            return "F2/Fujichrome (Velvia)";
        }
        if (intValue == 768) {
            return "F3/Studio Portrait Ex";
        }
        if (intValue == 1024) {
            return "F4/Velvia";
        }
        switch (intValue) {
            case 1280:
                return "Pro Neg. Std";
            case OlympusCameraSettingsMakernoteDirectory.C /*1281*/:
                return "Pro Neg. Hi";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String w() {
        Integer c = ((FujifilmMakernoteDirectory) this.f5211a).c(FujifilmMakernoteDirectory.I);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 0:
                return "Auto (100-400%)";
            case 1:
                return "Manual";
            case 256:
                return "Standard (100%)";
            case 512:
                return "Wide 1 (230%)";
            case 513:
                return "Wide 2 (400%)";
            case 32768:
                return "Film Simulation";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }
}
