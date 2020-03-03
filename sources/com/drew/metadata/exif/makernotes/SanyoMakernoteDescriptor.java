package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.integration.android.IntentIntegrator;

public class SanyoMakernoteDescriptor extends TagDescriptor<SanyoMakernoteDirectory> {
    public SanyoMakernoteDescriptor(@NotNull SanyoMakernoteDirectory sanyoMakernoteDirectory) {
        super(sanyoMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 513:
                return a();
            case 514:
                return b();
            case 516:
                return c();
            case SanyoMakernoteDirectory.n /*526*/:
                return d();
            case 527:
                return e();
            case 528:
                return f();
            case 531:
                return g();
            case 532:
                return h();
            case 534:
                return i();
            case SanyoMakernoteDirectory.t /*535*/:
                return j();
            case SanyoMakernoteDirectory.u /*536*/:
                return k();
            case 537:
                return l();
            case 539:
                return m();
            case SanyoMakernoteDirectory.x /*541*/:
                return n();
            case 542:
                return o();
            case SanyoMakernoteDirectory.z /*543*/:
                return p();
            case SanyoMakernoteDirectory.B /*548*/:
                return q();
            case 549:
                return r();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        Integer c = ((SanyoMakernoteDirectory) this.f5211a).c(513);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        switch (intValue) {
            case 0:
                return "Normal/Very Low";
            case 1:
                return "Normal/Low";
            case 2:
                return "Normal/Medium Low";
            case 3:
                return "Normal/Medium";
            case 4:
                return "Normal/Medium High";
            case 5:
                return "Normal/High";
            case 6:
                return "Normal/Very High";
            case 7:
                return "Normal/Super High";
            default:
                switch (intValue) {
                    case 256:
                        return "Fine/Very Low";
                    case 257:
                        return "Fine/Low";
                    case 258:
                        return "Fine/Medium Low";
                    case 259:
                        return "Fine/Medium";
                    case 260:
                        return "Fine/Medium High";
                    case 261:
                        return "Fine/High";
                    case 262:
                        return "Fine/Very High";
                    case 263:
                        return "Fine/Super High";
                    default:
                        switch (intValue) {
                            case 512:
                                return "Super Fine/Very Low";
                            case 513:
                                return "Super Fine/Low";
                            case 514:
                                return "Super Fine/Medium Low";
                            case 515:
                                return "Super Fine/Medium";
                            case 516:
                                return "Super Fine/Medium High";
                            case 517:
                                return "Super Fine/High";
                            case 518:
                                return "Super Fine/Very High";
                            case 519:
                                return "Super Fine/Super High";
                            default:
                                return "Unknown (" + c + Operators.BRACKET_END_STR;
                        }
                }
        }
    }

    @Nullable
    private String b() {
        return a(514, "Normal", "Macro", "View", "Manual");
    }

    @Nullable
    private String c() {
        return b(516, 3);
    }

    @Nullable
    private String d() {
        return a((int) SanyoMakernoteDirectory.n, "None", "Standard", "Best", "Adjust Exposure");
    }

    @Nullable
    private String e() {
        return a(527, "Off", "On");
    }

    @Nullable
    private String f() {
        return a(528, "Off", "On");
    }

    @Nullable
    private String g() {
        return a(531, "Off", "On");
    }

    @Nullable
    private String h() {
        return a(532, "Off", "On");
    }

    @Nullable
    private String i() {
        return a(534, "Off", "On");
    }

    @Nullable
    private String j() {
        return a((int) SanyoMakernoteDirectory.t, "Record while down", "Press start, press stop");
    }

    @Nullable
    private String k() {
        return a((int) SanyoMakernoteDirectory.u, "Off", "On");
    }

    @Nullable
    private String l() {
        return a(537, "Off", "On");
    }

    @Nullable
    private String m() {
        return a(539, "Off", "On");
    }

    @Nullable
    private String n() {
        return a((int) SanyoMakernoteDirectory.x, "Off", "On");
    }

    @Nullable
    private String o() {
        return a(542, IntentIntegrator.e, IntentIntegrator.d);
    }

    @Nullable
    private String p() {
        return a((int) SanyoMakernoteDirectory.z, "Off", "Sport", "TV", "Night", "User 1", "User 2", "Lamp");
    }

    @Nullable
    private String q() {
        return a((int) SanyoMakernoteDirectory.B, "5 frames/sec", "10 frames/sec", "15 frames/sec", "20 frames/sec");
    }

    @Nullable
    private String r() {
        return a(549, "Auto", "Force", "Disabled", "Red eye");
    }
}
