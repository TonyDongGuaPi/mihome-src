package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class JpegDirectory extends Directory {
    public static final int e = -3;
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 3;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    @NotNull
    protected static final HashMap<Integer, String> n = new HashMap<>();

    @NotNull
    public String a() {
        return "JPEG";
    }

    static {
        n.put(-3, "Compression Type");
        n.put(0, "Data Precision");
        n.put(3, "Image Width");
        n.put(1, "Image Height");
        n.put(5, "Number of Components");
        n.put(6, "Component 1");
        n.put(7, "Component 2");
        n.put(8, "Component 3");
        n.put(9, "Component 4");
    }

    public JpegDirectory() {
        a((TagDescriptor) new JpegDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return n;
    }

    @Nullable
    public JpegComponent y(int i2) {
        return (JpegComponent) u(i2 + 6);
    }

    public int j() throws MetadataException {
        return b(3);
    }

    public int k() throws MetadataException {
        return b(1);
    }

    public int l() throws MetadataException {
        return b(5);
    }
}
