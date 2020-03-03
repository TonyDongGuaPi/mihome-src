package com.drew.metadata.jfxx;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class JfxxDirectory extends Directory {
    public static final int e = 5;
    @NotNull
    protected static final HashMap<Integer, String> f = new HashMap<>();

    @NotNull
    public String a() {
        return JfxxReader.f5232a;
    }

    static {
        f.put(5, "Extension Code");
    }

    public JfxxDirectory() {
        a((TagDescriptor) new JfxxDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return f;
    }

    public int j() throws MetadataException {
        return b(5);
    }
}
