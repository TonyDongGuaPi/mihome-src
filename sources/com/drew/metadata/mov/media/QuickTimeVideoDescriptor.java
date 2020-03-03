package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mov.QuickTimeDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.taobao.weex.el.parse.Operators;

public class QuickTimeVideoDescriptor extends QuickTimeDescriptor {
    public QuickTimeVideoDescriptor(@NotNull QuickTimeVideoDirectory quickTimeVideoDirectory) {
        super(quickTimeVideoDirectory);
    }

    public String a(int i) {
        if (i == 9) {
            return k(i);
        }
        if (i == 11) {
            return a();
        }
        if (i == 13) {
            return l(i);
        }
        switch (i) {
            case 4:
            case 5:
                return j(i);
            default:
                return super.a(i);
        }
    }

    private String j(int i) {
        String s = ((QuickTimeDirectory) this.f5211a).s(i);
        if (s == null) {
            return null;
        }
        return s + " pixels";
    }

    private String k(int i) {
        Integer c = ((QuickTimeDirectory) this.f5211a).c(i);
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

    private String l(int i) {
        Integer c = ((QuickTimeDirectory) this.f5211a).c(i);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case -1:
                return ((QuickTimeDirectory) this.f5211a).c(9).intValue() < 16 ? "Default" : "None";
            case 0:
                return "Color table within file";
            default:
                return "Unknown (" + c + Operators.BRACKET_END_STR;
        }
    }

    private String a() {
        Integer c = ((QuickTimeDirectory) this.f5211a).c(11);
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
