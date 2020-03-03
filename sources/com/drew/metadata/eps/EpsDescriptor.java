package com.drew.metadata.eps;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class EpsDescriptor extends TagDescriptor<EpsDirectory> {
    public EpsDescriptor(@NotNull EpsDirectory epsDirectory) {
        super(epsDirectory);
    }

    public String a(int i) {
        switch (i) {
            case 28:
            case 29:
                return j(i);
            case 30:
                return a();
            case 32:
            case 33:
                return k(i);
            default:
                return ((EpsDirectory) this.f5211a).s(i);
        }
    }

    public String j(int i) {
        return ((EpsDirectory) this.f5211a).s(i) + " pixels";
    }

    public String k(int i) {
        return ((EpsDirectory) this.f5211a).s(i) + " bytes";
    }

    @Nullable
    public String a() {
        return a(30, 1, "Grayscale", "Lab", "RGB", "CMYK");
    }
}
