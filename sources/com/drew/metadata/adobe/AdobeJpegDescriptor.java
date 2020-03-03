package com.drew.metadata.adobe;

import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class AdobeJpegDescriptor extends TagDescriptor<AdobeJpegDirectory> {
    public AdobeJpegDescriptor(AdobeJpegDirectory adobeJpegDirectory) {
        super(adobeJpegDirectory);
    }

    public String a(int i) {
        if (i == 0) {
            return a();
        }
        if (i != 3) {
            return super.a(i);
        }
        return b();
    }

    @Nullable
    private String a() {
        Integer c = ((AdobeJpegDirectory) this.f5211a).c(0);
        if (c == null) {
            return null;
        }
        return c.intValue() == 100 ? "100" : Integer.toString(c.intValue());
    }

    @Nullable
    private String b() {
        return a(3, "Unknown (RGB or CMYK)", "YCbCr", "YCCK");
    }
}
