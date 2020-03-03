package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.taobao.weex.el.parse.Operators;

public class Mp4VideoDescriptor extends TagDescriptor<Mp4VideoDirectory> {
    public Mp4VideoDescriptor(@NotNull Mp4VideoDirectory mp4VideoDirectory) {
        super(mp4VideoDirectory);
    }

    public String a(int i) {
        if (i == 109) {
            return a();
        }
        if (i == 111) {
            return c();
        }
        if (i == 113) {
            return b();
        }
        switch (i) {
            case 104:
            case 105:
                return j(i);
            default:
                return super.a(i);
        }
    }

    private String j(int i) {
        String s = ((Mp4VideoDirectory) this.f5211a).s(i);
        if (s == null) {
            return null;
        }
        return s + " pixels";
    }

    private String a() {
        Integer c = ((Mp4VideoDirectory) this.f5211a).c(109);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 34 || intValue == 36 || intValue == 40) {
            StringBuilder sb = new StringBuilder();
            sb.append(c.intValue() - 32);
            sb.append("-bit grayscale");
            return sb.toString();
        }
        return "Unknown (" + c + Operators.BRACKET_END_STR;
    }

    private String b() {
        Integer c = ((Mp4VideoDirectory) this.f5211a).c(113);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case -1:
                Integer c2 = ((Mp4VideoDirectory) this.f5211a).c(109);
                return (c2 != null && c2.intValue() < 16) ? "Default" : "None";
            case 0:
                return "Color table within file";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    private String c() {
        Integer c = ((Mp4VideoDirectory) this.f5211a).c(111);
        if (c == null) {
            return null;
        }
        int intValue = c.intValue();
        if (intValue == 0) {
            return "Copy";
        }
        if (intValue == 32) {
            return "Blend";
        }
        if (intValue == 36) {
            return "Transparent";
        }
        if (intValue == 64) {
            return "Dither copy";
        }
        switch (intValue) {
            case 256:
                return "Straight alpha";
            case 257:
                return "Premul white alpha";
            case 258:
                return "Premul black alpha";
            case 259:
                return "Composition (dither copy)";
            case 260:
                return "Straight alpha blend";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }
}
