package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;

public class ExifThumbnailDescriptor extends ExifDescriptorBase<ExifThumbnailDirectory> {
    public ExifThumbnailDescriptor(@NotNull ExifThumbnailDirectory exifThumbnailDirectory) {
        super(exifThumbnailDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 513:
                return at();
            case 514:
                return as();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String as() {
        String s = ((ExifThumbnailDirectory) this.f5211a).s(514);
        if (s == null) {
            return null;
        }
        return s + " bytes";
    }

    @Nullable
    public String at() {
        String s = ((ExifThumbnailDirectory) this.f5211a).s(513);
        if (s == null) {
            return null;
        }
        return s + " bytes";
    }
}
