package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class ExifSubIFDDirectory extends ExifDirectoryBase {
    public static final int cg = 40965;
    @NotNull
    protected static final HashMap<Integer, String> ch = new HashMap<>();

    @NotNull
    public String a() {
        return "Exif SubIFD";
    }

    public ExifSubIFDDirectory() {
        a((TagDescriptor) new ExifSubIFDDescriptor(this));
    }

    static {
        a(ch);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return ch;
    }

    @Nullable
    public Date j() {
        return a((TimeZone) null);
    }

    @Nullable
    public Date a(@Nullable TimeZone timeZone) {
        return a(ExifDirectoryBase.aH, s(ExifDirectoryBase.bk), timeZone);
    }

    @Nullable
    public Date k() {
        return b((TimeZone) null);
    }

    @Nullable
    public Date b(@Nullable TimeZone timeZone) {
        return a(ExifDirectoryBase.aI, s(ExifDirectoryBase.bl), timeZone);
    }
}
