package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class PanasonicRawWbInfoDescriptor extends TagDescriptor<PanasonicRawWbInfoDirectory> {
    public PanasonicRawWbInfoDescriptor(@NotNull PanasonicRawWbInfoDirectory panasonicRawWbInfoDirectory) {
        super(panasonicRawWbInfoDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13 || i == 16 || i == 19) {
            return j(i);
        }
        return super.a(i);
    }

    @Nullable
    public String j(int i) {
        Integer c = ((PanasonicRawWbInfoDirectory) this.f5211a).c(i);
        if (c == null) {
            return null;
        }
        return super.a(c.shortValue());
    }
}
