package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class LeicaMakernoteDescriptor extends TagDescriptor<LeicaMakernoteDirectory> {
    public LeicaMakernoteDescriptor(@NotNull LeicaMakernoteDirectory leicaMakernoteDirectory) {
        super(leicaMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == 768) {
            return g();
        }
        if (i == 770) {
            return f();
        }
        if (i == 772) {
            return e();
        }
        if (i == 800) {
            return a();
        }
        switch (i) {
            case 785:
                return d();
            case LeicaMakernoteDirectory.k /*786*/:
                return c();
            case LeicaMakernoteDirectory.l /*787*/:
                return b();
            default:
                switch (i) {
                    case 802:
                    case 803:
                    case 804:
                        return c(i);
                    default:
                        return super.a(i);
                }
        }
    }

    @Nullable
    private String a() {
        return a(800, "%d C");
    }

    @Nullable
    private String b() {
        return c(LeicaMakernoteDirectory.l);
    }

    @Nullable
    private String c() {
        return c(LeicaMakernoteDirectory.k);
    }

    @Nullable
    private String d() {
        return c(785);
    }

    @Nullable
    private String e() {
        return a(772, "Auto or Manual", "Daylight", "Fluorescent", "Tungsten", ExifInterface.TAG_FLASH, "Cloudy", "Shadow");
    }

    @Nullable
    private String f() {
        return a(768, 1, "User Profile 1", "User Profile 2", "User Profile 3", "User Profile 0 (Dynamic)");
    }

    @Nullable
    private String g() {
        return a(768, 1, "Fine", "Basic");
    }
}
