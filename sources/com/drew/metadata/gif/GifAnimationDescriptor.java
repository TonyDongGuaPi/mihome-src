package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class GifAnimationDescriptor extends TagDescriptor<GifAnimationDirectory> {
    public GifAnimationDescriptor(@NotNull GifAnimationDirectory gifAnimationDirectory) {
        super(gifAnimationDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i != 1) {
            return super.a(i);
        }
        return a();
    }

    @Nullable
    public String a() {
        Integer c = ((GifAnimationDirectory) this.f5211a).c(1);
        if (c == null) {
            return null;
        }
        if (c.intValue() == 0) {
            return "Infinite";
        }
        if (c.intValue() == 1) {
            return "Once";
        }
        if (c.intValue() == 2) {
            return "Twice";
        }
        return c.toString() + " times";
    }
}
