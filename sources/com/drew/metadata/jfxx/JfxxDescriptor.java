package com.drew.metadata.jfxx;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class JfxxDescriptor extends TagDescriptor<JfxxDirectory> {
    public JfxxDescriptor(@NotNull JfxxDirectory jfxxDirectory) {
        super(jfxxDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i != 5) {
            return super.a(i);
        }
        return a();
    }

    @Nullable
    public String a() {
        Integer c = ((JfxxDirectory) this.f5211a).c(5);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 16:
                return "Thumbnail coded using JPEG";
            case 17:
                return "Thumbnail stored using 1 byte/pixel";
            case 19:
                return "Thumbnail stored using 3 bytes/pixel";
            default:
                return "Unknown extension code " + c;
        }
    }
}
