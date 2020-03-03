package com.drew.metadata.webp;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class WebpDescriptor extends TagDescriptor<WebpDirectory> {
    public WebpDescriptor(@NotNull WebpDirectory webpDirectory) {
        super(webpDirectory);
    }

    @Nullable
    public String a(int i) {
        return super.a(i);
    }
}
