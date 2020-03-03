package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class AppleMakernoteDescriptor extends TagDescriptor<AppleMakernoteDirectory> {
    public AppleMakernoteDescriptor(@NotNull AppleMakernoteDirectory appleMakernoteDirectory) {
        super(appleMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i != 10) {
            return super.a(i);
        }
        return a();
    }

    @Nullable
    public String a() {
        return a(10, 3, "HDR Image", "Original Image");
    }
}
