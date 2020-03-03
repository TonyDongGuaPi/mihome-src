package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class SigmaMakernoteDescriptor extends TagDescriptor<SigmaMakernoteDirectory> {
    public SigmaMakernoteDescriptor(@NotNull SigmaMakernoteDirectory sigmaMakernoteDirectory) {
        super(sigmaMakernoteDirectory);
    }

    public String a(int i) {
        switch (i) {
            case 8:
                return b();
            case 9:
                return a();
            default:
                return super.a(i);
        }
    }

    @Nullable
    private String a() {
        String s = ((SigmaMakernoteDirectory) this.f5211a).s(9);
        if (s == null || s.length() == 0) {
            return null;
        }
        char charAt = s.charAt(0);
        if (charAt == '8') {
            return "Multi Segment";
        }
        if (charAt != 'A') {
            return charAt != 'C' ? s : "Center Weighted Average";
        }
        return "Average";
    }

    @Nullable
    private String b() {
        String s = ((SigmaMakernoteDirectory) this.f5211a).s(8);
        if (s == null || s.length() == 0) {
            return null;
        }
        char charAt = s.charAt(0);
        if (charAt == 'A') {
            return "Aperture Priority AE";
        }
        if (charAt == 'M') {
            return "Manual";
        }
        if (charAt != 'P') {
            return charAt != 'S' ? s : "Shutter Speed Priority AE";
        }
        return "Program AE";
    }
}
