package com.drew.metadata.photoshop;

import com.bumptech.glide.Registry;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class PsdHeaderDescriptor extends TagDescriptor<PsdHeaderDirectory> {
    public PsdHeaderDescriptor(@NotNull PsdHeaderDirectory psdHeaderDirectory) {
        super(psdHeaderDirectory);
    }

    public String a(int i) {
        switch (i) {
            case 1:
                return a();
            case 2:
                return d();
            case 3:
                return e();
            case 4:
                return b();
            case 5:
                return c();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        Integer c = ((PsdHeaderDirectory) this.f5211a).c(1);
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(" channel");
        sb.append(c.intValue() == 1 ? "" : "s");
        return sb.toString();
    }

    @Nullable
    public String b() {
        Integer c = ((PsdHeaderDirectory) this.f5211a).c(4);
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(" bit");
        sb.append(c.intValue() == 1 ? "" : "s");
        sb.append(" per channel");
        return sb.toString();
    }

    @Nullable
    public String c() {
        return a(5, Registry.b, "Grayscale", "Indexed", "RGB", "CMYK", null, null, "Multichannel", "Duotone", "Lab");
    }

    @Nullable
    public String d() {
        Integer c = ((PsdHeaderDirectory) this.f5211a).c(2);
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(" pixel");
        sb.append(c.intValue() == 1 ? "" : "s");
        return sb.toString();
    }

    @Nullable
    public String e() {
        try {
            Integer c = ((PsdHeaderDirectory) this.f5211a).c(3);
            if (c == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(c);
            sb.append(" pixel");
            sb.append(c.intValue() == 1 ? "" : "s");
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
