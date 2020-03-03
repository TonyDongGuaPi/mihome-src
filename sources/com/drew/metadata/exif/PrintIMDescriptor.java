package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class PrintIMDescriptor extends TagDescriptor<PrintIMDirectory> {
    public PrintIMDescriptor(@NotNull PrintIMDirectory printIMDirectory) {
        super(printIMDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 0) {
            return super.a(i);
        }
        Integer c = ((PrintIMDirectory) this.f5211a).c(i);
        if (c == null) {
            return null;
        }
        return String.format("0x%08x", new Object[]{c});
    }
}
