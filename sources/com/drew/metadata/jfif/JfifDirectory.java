package com.drew.metadata.jfif;

import com.alipay.sdk.packet.e;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class JfifDirectory extends Directory {
    public static final int e = 5;
    public static final int f = 7;
    public static final int g = 8;
    public static final int h = 10;
    public static final int i = 12;
    public static final int j = 13;
    @NotNull
    protected static final HashMap<Integer, String> k = new HashMap<>();

    @NotNull
    public String a() {
        return JfifReader.f5231a;
    }

    static {
        k.put(5, e.e);
        k.put(7, "Resolution Units");
        k.put(10, "Y Resolution");
        k.put(8, "X Resolution");
        k.put(12, "Thumbnail Width Pixels");
        k.put(13, "Thumbnail Height Pixels");
    }

    public JfifDirectory() {
        a((TagDescriptor) new JfifDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return k;
    }

    public int j() throws MetadataException {
        return b(5);
    }

    public int k() throws MetadataException {
        return b(7);
    }

    @Deprecated
    public int l() throws MetadataException {
        return b(10);
    }

    public int m() throws MetadataException {
        return b(10);
    }

    @Deprecated
    public int n() throws MetadataException {
        return b(8);
    }

    public int o() throws MetadataException {
        return b(8);
    }
}
