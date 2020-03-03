package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class LeicaType5MakernoteDescriptor extends TagDescriptor<LeicaType5MakernoteDirectory> {
    public LeicaType5MakernoteDescriptor(@NotNull LeicaType5MakernoteDirectory leicaType5MakernoteDirectory) {
        super(leicaType5MakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i != 1037) {
            return super.a(i);
        }
        return a();
    }

    @Nullable
    public String a() {
        byte[] g = ((LeicaType5MakernoteDirectory) this.f5211a).g(1037);
        if (g == null || g.length < 4) {
            return null;
        }
        String format = String.format("%d %d %d %d", new Object[]{Byte.valueOf(g[0]), Byte.valueOf(g[1]), Byte.valueOf(g[2]), Byte.valueOf(g[3])});
        if (format.equals("0 0 0 0")) {
            return "Program AE";
        }
        if (format.equals("1 0 0 0")) {
            return "Aperture-priority AE";
        }
        if (format.equals("1 1 0 0")) {
            return "Aperture-priority AE (1)";
        }
        if (format.equals("2 0 0 0")) {
            return "Shutter speed priority AE";
        }
        if (format.equals("3 0 0 0")) {
            return "Manual";
        }
        return String.format("Unknown (%s)", new Object[]{format});
    }
}
