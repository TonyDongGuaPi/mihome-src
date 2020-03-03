package com.drew.metadata.avi;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class AviDescriptor extends TagDescriptor<AviDirectory> {
    public AviDescriptor(@NotNull AviDirectory aviDirectory) {
        super(aviDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 6:
            case 7:
                return j(i);
            default:
                return super.a(i);
        }
    }

    public String j(int i) {
        return ((AviDirectory) this.f5211a).s(i) + " pixels";
    }
}
