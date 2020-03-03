package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class JpegCommentDescriptor extends TagDescriptor<JpegCommentDirectory> {
    public JpegCommentDescriptor(@NotNull JpegCommentDirectory jpegCommentDirectory) {
        super(jpegCommentDirectory);
    }

    @Nullable
    public String a() {
        return ((JpegCommentDirectory) this.f5211a).s(0);
    }
}
