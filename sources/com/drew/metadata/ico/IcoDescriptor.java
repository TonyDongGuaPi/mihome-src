package com.drew.metadata.ico;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class IcoDescriptor extends TagDescriptor<IcoDirectory> {
    public IcoDescriptor(@NotNull IcoDirectory icoDirectory) {
        super(icoDirectory);
    }

    public String a(int i) {
        switch (i) {
            case 1:
                return a();
            case 2:
                return b();
            case 3:
                return c();
            case 4:
                return d();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        return a(1, 1, "Icon", "Cursor");
    }

    @Nullable
    public String b() {
        Integer c = ((IcoDirectory) this.f5211a).c(2);
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c.intValue() == 0 ? 256 : c.intValue());
        sb.append(" pixels");
        return sb.toString();
    }

    @Nullable
    public String c() {
        Integer c = ((IcoDirectory) this.f5211a).c(3);
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c.intValue() == 0 ? 256 : c.intValue());
        sb.append(" pixels");
        return sb.toString();
    }

    @Nullable
    public String d() {
        Integer c = ((IcoDirectory) this.f5211a).c(4);
        if (c == null) {
            return null;
        }
        if (c.intValue() == 0) {
            return "No palette";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(" colour");
        sb.append(c.intValue() == 1 ? "" : "s");
        return sb.toString();
    }
}
