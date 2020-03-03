package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.zxing.integration.android.IntentIntegrator;

public class SonyType1MakernoteDescriptor extends TagDescriptor<SonyType1MakernoteDirectory> {
    public SonyType1MakernoteDescriptor(@NotNull SonyType1MakernoteDirectory sonyType1MakernoteDirectory) {
        super(sonyType1MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 258:
                return a();
            case 260:
                return b();
            case 261:
                return c();
            case 277:
                return d();
            case SonyType1MakernoteDirectory.z /*8201*/:
                return o();
            case SonyType1MakernoteDirectory.C /*8206*/:
                return p();
            case SonyType1MakernoteDirectory.D /*8207*/:
                return q();
            case 8209:
                return r();
            case 8210:
                return s();
            case SonyType1MakernoteDirectory.G /*8211*/:
                return t();
            case SonyType1MakernoteDirectory.I /*8214*/:
                return u();
            case SonyType1MakernoteDirectory.J /*8219*/:
                return v();
            case SonyType1MakernoteDirectory.K /*8222*/:
                return w();
            case SonyType1MakernoteDirectory.N /*45057*/:
                return x();
            case SonyType1MakernoteDirectory.P /*45089*/:
                return e();
            case SonyType1MakernoteDirectory.R /*45091*/:
                return y();
            case SonyType1MakernoteDirectory.S /*45092*/:
                return f();
            case SonyType1MakernoteDirectory.T /*45093*/:
                return g();
            case SonyType1MakernoteDirectory.U /*45094*/:
                return h();
            case SonyType1MakernoteDirectory.X /*45097*/:
                return i();
            case SonyType1MakernoteDirectory.ab /*45120*/:
                return j();
            case SonyType1MakernoteDirectory.ac /*45121*/:
                return k();
            case SonyType1MakernoteDirectory.ae /*45123*/:
                return z();
            case SonyType1MakernoteDirectory.af /*45124*/:
                return A();
            case SonyType1MakernoteDirectory.ag /*45127*/:
                return l();
            case SonyType1MakernoteDirectory.ah /*45128*/:
                return B();
            case SonyType1MakernoteDirectory.ai /*45129*/:
                return C();
            case SonyType1MakernoteDirectory.aj /*45130*/:
                return D();
            case SonyType1MakernoteDirectory.ak /*45131*/:
                return m();
            case SonyType1MakernoteDirectory.al /*45134*/:
                return n();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a(258, "RAW", "Super Fine", "Fine", "Standard", "Economy", "Extra Fine", "RAW + JPEG", "Compressed RAW", "Compressed RAW + JPEG");
    }

    @Nullable
    public String b() {
        return a(260, "%d EV");
    }

    @Nullable
    public String c() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(261);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "None";
        }
        if (intValue == 72) {
            return "Minolta/Sony AF 2x APO (D)";
        }
        if (intValue == 80) {
            return "Minolta AF 2x APO II";
        }
        if (intValue == 96) {
            return "Minolta AF 2x APO";
        }
        if (intValue == 136) {
            return "Minolta/Sony AF 1.4x APO (D)";
        }
        if (intValue == 144) {
            return "Minolta AF 1.4x APO II";
        }
        if (intValue == 160) {
            return "Minolta AF 1.4x APO";
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    @Nullable
    public String d() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(277);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 16) {
            return "Daylight";
        }
        if (intValue == 32) {
            return "Cloudy";
        }
        if (intValue == 48) {
            return "Shade";
        }
        if (intValue == 64) {
            return "Tungsten";
        }
        if (intValue == 80) {
            return ExifInterface.TAG_FLASH;
        }
        if (intValue == 96) {
            return "Fluorescent";
        }
        if (intValue == 112) {
            return "Custom";
        }
        switch (intValue) {
            case 0:
                return "Auto";
            case 1:
                return "Color Temperature/Color Filter";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String e() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.P);
        if (c == null) {
            return null;
        }
        if (c.intValue() == 0) {
            return "Auto";
        }
        return String.format("%d K", new Object[]{Integer.valueOf(((c.intValue() & -16777216) >> 24) | ((c.intValue() & 16711680) >> 8))});
    }

    @Nullable
    public String f() {
        return a((int) SonyType1MakernoteDirectory.S, "ISO Setting Used", "High Key", "Low Key");
    }

    @Nullable
    public String g() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.T);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 0:
                return "Off";
            case 1:
                return "Standard";
            case 2:
                return "Advanced Auto";
            case 3:
                return "Auto";
            case 8:
                return "Advanced LV1";
            case 9:
                return "Advanced LV2";
            case 10:
                return "Advanced LV3";
            case 11:
                return "Advanced LV4";
            case 12:
                return "Advanced LV5";
            case 16:
                return "LV1";
            case 17:
                return "LV2";
            case 18:
                return "LV3";
            case 19:
                return "LV4";
            case 20:
                return "LV5";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String h() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.U);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 0:
                return "Off";
            case 1:
                return "On";
            default:
                return SensorsDataManager.u;
        }
    }

    @Nullable
    public String i() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.X);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        switch (intValue) {
            case 0:
                return "Standard";
            case 1:
                return "Vivid";
            case 2:
                return "Portrait";
            case 3:
                return "Landscape";
            case 4:
                return "Sunset";
            case 5:
                return "Night Portrait";
            case 6:
                return "Black & White";
            case 7:
                return "Adobe RGB";
            default:
                switch (intValue) {
                    case 12:
                        return "Neutral";
                    case 13:
                        return "Clear";
                    case 14:
                        return "Deep";
                    case 15:
                        return "Light";
                    case 16:
                        return "Autumn";
                    case 17:
                        return "Sepia";
                    default:
                        switch (intValue) {
                            case 100:
                                return "Neutral";
                            case 101:
                                return "Clear";
                            case 102:
                                return "Deep";
                            case 103:
                                return "Light";
                            case 104:
                                return "Night View";
                            case 105:
                                return "Autumn Leaves";
                            default:
                                return String.format("Unknown (%d)", new Object[]{c});
                        }
                }
        }
    }

    @Nullable
    public String j() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ab);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "On";
            case 2:
                return "Magnifying Glass/Super Macro";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String k() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ac);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 29) {
            return "Underwater";
        }
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 0:
                return "Program";
            case 1:
                return "Portrait";
            case 2:
                return "Beach";
            case 3:
                return "Sports";
            case 4:
                return "Snow";
            case 5:
                return "Landscape";
            case 6:
                return "Auto";
            case 7:
                return "Aperture Priority";
            case 8:
                return "Shutter Priority";
            case 9:
                return "Night Scene / Twilight";
            case 10:
                return "Hi-Speed Shutter";
            case 11:
                return "Twilight Portrait";
            case 12:
                return "Soft Snap/Portrait";
            case 13:
                return "Fireworks";
            case 14:
                return "Smile Shutter";
            case 15:
                return "Manual";
            default:
                switch (intValue) {
                    case 18:
                        return "High Sensitivity";
                    case 19:
                        return "Macro";
                    case 20:
                        return "Advanced Sports Shooting";
                    default:
                        switch (intValue) {
                            case 33:
                                return "Food";
                            case 34:
                                return "Panorama";
                            case 35:
                                return "Handheld Night Shot";
                            case 36:
                                return "Anti Motion Blur";
                            case 37:
                                return "Pet";
                            case 38:
                                return "Backlight Correction HDR";
                            case 39:
                                return "Superior Auto";
                            case 40:
                                return "Background Defocus";
                            case 41:
                                return "Soft Skin";
                            case 42:
                                return "3D Image";
                            default:
                                return String.format("Unknown (%d)", new Object[]{c});
                        }
                }
        }
    }

    @Nullable
    public String l() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ag);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 0:
                return "Normal";
            case 1:
                return "Fine";
            case 2:
                return "Extra Fine";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String m() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ak);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "On (Continuous)";
            case 2:
                return "On (Shooting)";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String n() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.al);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "On";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String o() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.z);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 256) {
            return "Auto";
        }
        if (intValue == 65535) {
            return SensorsDataManager.u;
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "On";
            case 2:
                return "Normal";
            case 3:
                return "High";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String p() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.C);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 13) {
            return "High Contrast Monochrome";
        }
        if (intValue == 80) {
            return "Rich-tone Monochrome";
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "Toy Camera";
            case 2:
                return "Pop Color";
            case 3:
                return "Posterization";
            case 4:
                return "Posterization B/W";
            case 5:
                return "Retro Photo";
            case 6:
                return "Soft High Key";
            case 7:
                return "Partial Color (red)";
            case 8:
                return "Partial Color (green)";
            case 9:
                return "Partial Color (blue)";
            case 10:
                return "Partial Color (yellow)";
            default:
                switch (intValue) {
                    case 16:
                        return "Toy Camera (normal)";
                    case 17:
                        return "Toy Camera (cool)";
                    case 18:
                        return "Toy Camera (warm)";
                    case 19:
                        return "Toy Camera (green)";
                    case 20:
                        return "Toy Camera (magenta)";
                    default:
                        switch (intValue) {
                            case 32:
                                return "Soft Focus (low)";
                            case 33:
                                return "Soft Focus";
                            case 34:
                                return "Soft Focus (high)";
                            default:
                                switch (intValue) {
                                    case 48:
                                        return "Miniature (auto)";
                                    case 49:
                                        return "Miniature (top)";
                                    case 50:
                                        return "Miniature (middle horizontal)";
                                    case 51:
                                        return "Miniature (bottom)";
                                    case 52:
                                        return "Miniature (left)";
                                    case 53:
                                        return "Miniature (middle vertical)";
                                    case 54:
                                        return "Miniature (right)";
                                    default:
                                        switch (intValue) {
                                            case 64:
                                                return "HDR Painting (low)";
                                            case 65:
                                                return "HDR Painting";
                                            case 66:
                                                return "HDR Painting (high)";
                                            default:
                                                switch (intValue) {
                                                    case 97:
                                                        return "Water Color";
                                                    case 98:
                                                        return "Water Color 2";
                                                    default:
                                                        switch (intValue) {
                                                            case 112:
                                                                return "Illustration (low)";
                                                            case 113:
                                                                return "Illustration";
                                                            case 114:
                                                                return "Illustration (high)";
                                                            default:
                                                                return String.format("Unknown (%d)", new Object[]{c});
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Nullable
    public String q() {
        return a((int) SonyType1MakernoteDirectory.D, "Off", "Low", "Mid", "High");
    }

    @Nullable
    public String r() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(8209);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 2) {
            return "Auto";
        }
        switch (intValue) {
            case -1:
                return SensorsDataManager.u;
            case 0:
                return "Off";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String s() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(8210);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 2) {
            return "Auto";
        }
        switch (intValue) {
            case -1:
                return SensorsDataManager.u;
            case 0:
                return "Off";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String t() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.G);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 2) {
            return "Auto";
        }
        switch (intValue) {
            case -1:
                return SensorsDataManager.u;
            case 0:
                return "Off";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String u() {
        return a((int) SonyType1MakernoteDirectory.I, IntentIntegrator.e, IntentIntegrator.d);
    }

    @Nullable
    public String v() {
        return a((int) SonyType1MakernoteDirectory.J, "Manual", null, "AF-A", "AF-C", "AF-S", null, "DMF", "AF-D");
    }

    @Nullable
    public String w() {
        return a((int) SonyType1MakernoteDirectory.K, "Auto", "Center", "Top", "Upper-right", "Right", "Lower-right", "Bottom", "Lower-left", "Left", "Upper-left\t  \t", "Far Right", "Far Left", "Upper-middle", "Near Right", "Lower-middle", "Near Left", "Upper Far Right", "Lower Far Right", "Lower Far Left", "Upper Far Left");
    }

    @Nullable
    public String x() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.N);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 2) {
            return "DSC-R1";
        }
        switch (intValue) {
            case 256:
                return "DSLR-A100";
            case 257:
                return "DSLR-A900";
            case 258:
                return "DSLR-A700";
            case 259:
                return "DSLR-A200";
            case 260:
                return "DSLR-A350";
            case 261:
                return "DSLR-A300";
            case 262:
                return "DSLR-A900 (APS-C mode)";
            case 263:
                return "DSLR-A380/A390";
            case 264:
                return "DSLR-A330";
            case 265:
                return "DSLR-A230";
            case 266:
                return "DSLR-A290";
            default:
                switch (intValue) {
                    case 269:
                        return "DSLR-A850";
                    case 270:
                        return "DSLR-A850 (APS-C mode)";
                    default:
                        switch (intValue) {
                            case 273:
                                return "DSLR-A550";
                            case 274:
                                return "DSLR-A500";
                            case 275:
                                return "DSLR-A450";
                            default:
                                switch (intValue) {
                                    case 278:
                                        return "NEX-5";
                                    case 279:
                                        return "NEX-3";
                                    case 280:
                                        return "SLT-A33";
                                    case 281:
                                        return "SLT-A55V";
                                    case 282:
                                        return "DSLR-A560";
                                    case 283:
                                        return "DSLR-A580";
                                    case 284:
                                        return "NEX-C3";
                                    case 285:
                                        return "SLT-A35";
                                    case 286:
                                        return "SLT-A65V";
                                    case OlympusImageProcessingMakernoteDirectory.I:
                                        return "SLT-A77V";
                                    case 288:
                                        return "NEX-5N";
                                    case 289:
                                        return "NEX-7";
                                    case OlympusRawInfoMakernoteDirectory.j:
                                        return "NEX-VG20E";
                                    case 291:
                                        return "SLT-A37";
                                    case OlympusRawInfoMakernoteDirectory.l:
                                        return "SLT-A57";
                                    case 293:
                                        return "NEX-F3";
                                    case 294:
                                        return "SLT-A99V";
                                    case 295:
                                        return "NEX-6";
                                    case 296:
                                        return "NEX-5R";
                                    case ExifDirectoryBase.F:
                                        return "DSC-RX100";
                                    case 298:
                                        return "DSC-RX1";
                                    default:
                                        return "Unknown (" + c + Operators.BRACKET_END_STR;
                                }
                        }
                }
        }
    }

    @Nullable
    public String y() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.R);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        switch (intValue) {
            case 0:
                return "Standard";
            case 1:
                return "Portrait";
            case 2:
                return "Text";
            case 3:
                return "Night Scene";
            case 4:
                return "Sunset";
            case 5:
                return "Sports";
            case 6:
                return "Landscape";
            case 7:
                return "Night Portrait";
            case 8:
                return "Macro";
            case 9:
                return "Super Macro";
            default:
                switch (intValue) {
                    case 16:
                        return "Auto";
                    case 17:
                        return "Night View/Portrait";
                    case 18:
                        return "Sweep Panorama";
                    case 19:
                        return "Handheld Night Shot";
                    case 20:
                        return "Anti Motion Blur";
                    case 21:
                        return "Cont. Priority AE";
                    case 22:
                        return "Auto+";
                    case 23:
                        return "3D Sweep Panorama";
                    case 24:
                        return "Superior Auto";
                    case 25:
                        return "High Sensitivity";
                    case 26:
                        return "Fireworks";
                    case 27:
                        return "Food";
                    case 28:
                        return "Pet";
                    default:
                        return "Unknown (" + c + Operators.BRACKET_END_STR;
                }
        }
    }

    @Nullable
    public String z() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ae);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 6) {
            return "Touch";
        }
        if (intValue == 65535) {
            return "n/a";
        }
        switch (intValue) {
            case 0:
                return "Default";
            case 1:
                return "Multi";
            case 2:
                return "Center";
            case 3:
                return "Spot";
            case 4:
                return "Flexible Spot";
            default:
                switch (intValue) {
                    case 14:
                        return "Manual Focus";
                    case 15:
                        return "Face Detected";
                    default:
                        return "Unknown (" + c + Operators.BRACKET_END_STR;
                }
        }
    }

    @Nullable
    public String A() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.af);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 65535) {
            return "n/a";
        }
        switch (intValue) {
            case 0:
                return "Off";
            case 1:
                return "Auto";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String B() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ah);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == -32768) {
            return "Low";
        }
        if (intValue == 128) {
            return "n/a";
        }
        if (intValue == 32767) {
            return "High";
        }
        switch (intValue) {
            case -3:
                return "-3/3";
            case -2:
                return "-2/3";
            case -1:
                return "-1/3";
            case 0:
                return "Normal";
            case 1:
                return "+1/3";
            case 2:
                return "+2/3";
            case 3:
                return "+3/3";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String C() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ai);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Normal";
        }
        if (intValue == 2) {
            return "Continuous";
        }
        if (intValue == 65535) {
            return "n/a";
        }
        switch (intValue) {
            case 5:
                return "Exposure Bracketing";
            case 6:
                return "White Balance Bracketing";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    @Nullable
    public String D() {
        Integer c = ((SonyType1MakernoteDirectory) this.f5211a).c(SonyType1MakernoteDirectory.ai);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue != 0) {
            return intValue != 65535 ? c.toString() : "n/a";
        }
        return "Single";
    }
}
