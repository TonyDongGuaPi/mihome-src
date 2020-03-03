package com.drew.metadata.iptc;

import com.alipay.sdk.util.i;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class IptcDescriptor extends TagDescriptor<IptcDirectory> {
    public IptcDescriptor(@NotNull IptcDirectory iptcDirectory) {
        super(iptcDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 276:
                return a();
            case 326:
                return l();
            case IptcDirectory.n /*336*/:
                return D();
            case 537:
                return p();
            case 542:
                return w();
            case 547:
                return x();
            case 549:
                return m();
            case IptcDirectory.J /*550*/:
                return n();
            case 559:
                return v();
            case IptcDirectory.P /*567*/:
                return j();
            case IptcDirectory.Q /*572*/:
                return B();
            case IptcDirectory.R /*574*/:
                return k();
            case IptcDirectory.S /*575*/:
                return C();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String j(int i) {
        String s = ((IptcDirectory) this.f5211a).s(i);
        if (s == null) {
            return null;
        }
        if (s.length() != 8) {
            return s;
        }
        return s.substring(0, 4) + Operators.CONDITION_IF_MIDDLE + s.substring(4, 6) + Operators.CONDITION_IF_MIDDLE + s.substring(6);
    }

    @Nullable
    public String k(int i) {
        String s = ((IptcDirectory) this.f5211a).s(i);
        if (s == null) {
            return null;
        }
        if (s.length() != 6 && s.length() != 11) {
            return s;
        }
        return s.substring(0, 2) + Operators.CONDITION_IF_MIDDLE + s.substring(2, 4) + Operators.CONDITION_IF_MIDDLE + s.substring(4);
    }

    @Nullable
    public String a() {
        Integer c = ((IptcDirectory) this.f5211a).c(276);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 0:
                return "No ObjectData";
            case 1:
                return "IPTC-NAA Digital Newsphoto Parameter Record";
            case 2:
                return "IPTC7901 Recommended Message Format";
            case 3:
                return "Tagged Image File Format (Adobe/Aldus Image data)";
            case 4:
                return "Illustrator (Adobe Graphics data)";
            case 5:
                return "AppleSingle (Apple Computer Inc)";
            case 6:
                return "NAA 89-3 (ANPA 1312)";
            case 7:
                return "MacBinary II";
            case 8:
                return "IPTC Unstructured Character Oriented File Format (UCOFF)";
            case 9:
                return "United Press International ANPA 1312 variant";
            case 10:
                return "United Press International Down-Load Message";
            case 11:
                return "JPEG File Interchange (JFIF)";
            case 12:
                return "Photo-CD Image-Pac (Eastman Kodak)";
            case 13:
                return "Bit Mapped Graphics File [.BMP] (Microsoft)";
            case 14:
                return "Digital Audio File [.WAV] (Microsoft & Creative Labs)";
            case 15:
                return "Audio plus Moving Video [.AVI] (Microsoft)";
            case 16:
                return "PC DOS/Windows Executable Files [.COM][.EXE]";
            case 17:
                return "Compressed Binary File [.ZIP] (PKWare Inc)";
            case 18:
                return "Audio Interchange File Format AIFF (Apple Computer Inc)";
            case 19:
                return "RIFF Wave (Microsoft Corporation)";
            case 20:
                return "Freehand (Macromedia/Aldus)";
            case 21:
                return "Hypertext Markup Language [.HTML] (The Internet Society)";
            case 22:
                return "MPEG 2 Audio Layer 2 (Musicom), ISO/IEC";
            case 23:
                return "MPEG 2 Audio Layer 3, ISO/IEC";
            case 24:
                return "Portable Document File [.PDF] Adobe";
            case 25:
                return "News Industry Text Format (NITF)";
            case 26:
                return "Tape Archive [.TAR]";
            case 27:
                return "Tidningarnas Telegrambyra NITF version (TTNITF DTD)";
            case 28:
                return "Ritzaus Bureau NITF version (RBNITF DTD)";
            case 29:
                return "Corel Draw [.CDR]";
            default:
                return String.format("Unknown (%d)", new Object[]{c});
        }
    }

    @Nullable
    public String b() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.W);
    }

    @Nullable
    public String c() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.X);
    }

    @Nullable
    public String d() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.aj);
    }

    @Nullable
    public String e() {
        return ((IptcDirectory) this.f5211a).s(527);
    }

    @Nullable
    public String f() {
        return ((IptcDirectory) this.f5211a).s(602);
    }

    @Nullable
    public String g() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.ah);
    }

    @Nullable
    public String h() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.ac);
    }

    @Nullable
    public String i() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.af);
    }

    @Nullable
    public String j() {
        return j(IptcDirectory.P);
    }

    @Nullable
    public String k() {
        return j(IptcDirectory.R);
    }

    @Nullable
    public String l() {
        return j(326);
    }

    @Nullable
    public String m() {
        return j(549);
    }

    @Nullable
    public String n() {
        return k(IptcDirectory.J);
    }

    @Nullable
    public String o() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.ae);
    }

    @Nullable
    public String p() {
        String[] d = ((IptcDirectory) this.f5211a).d(537);
        if (d == null) {
            return null;
        }
        return StringUtil.a((T[]) d, i.b);
    }

    @Nullable
    public String q() {
        return ((IptcDirectory) this.f5211a).s(517);
    }

    @Nullable
    public String r() {
        return ((IptcDirectory) this.f5211a).s(615);
    }

    @Nullable
    public String s() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.T);
    }

    @Nullable
    public String t() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.aa);
    }

    @Nullable
    public String u() {
        return ((IptcDirectory) this.f5211a).s(512);
    }

    @Nullable
    public String v() {
        return j(559);
    }

    @Nullable
    public String w() {
        return j(542);
    }

    @Nullable
    public String x() {
        return k(547);
    }

    @Nullable
    public String y() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.ag);
    }

    @Nullable
    public String z() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.K);
    }

    @Nullable
    public String A() {
        return ((IptcDirectory) this.f5211a).s(532);
    }

    @Nullable
    public String B() {
        return k(IptcDirectory.Q);
    }

    @Nullable
    public String C() {
        return k(IptcDirectory.S);
    }

    @Nullable
    public String D() {
        return k(IptcDirectory.n);
    }

    @Nullable
    public String E() {
        return ((IptcDirectory) this.f5211a).s(522);
    }

    @Nullable
    public String F() {
        return ((IptcDirectory) this.f5211a).s(IptcDirectory.al);
    }
}
