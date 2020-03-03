package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class PanasonicRawWbInfo2Descriptor extends TagDescriptor<PanasonicRawWbInfo2Directory> {
    public PanasonicRawWbInfo2Descriptor(@NotNull PanasonicRawWbInfo2Directory panasonicRawWbInfo2Directory) {
        super(panasonicRawWbInfo2Directory);
    }

    @Nullable
    public String a(int i) {
        if (i == 1 || i == 5 || i == 9 || i == 13 || i == 17 || i == 21 || i == 25) {
            return j(i);
        }
        return super.a(i);
    }

    @Nullable
    public String j(int i) {
        Integer c = ((PanasonicRawWbInfo2Directory) this.f5211a).c(i);
        if (c == null) {
            return null;
        }
        return super.a(c.shortValue());
    }
}
