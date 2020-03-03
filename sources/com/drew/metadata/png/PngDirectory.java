package com.drew.metadata.png;

import com.drew.imaging.png.PngChunkType;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PngDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    public static final int n = 10;
    public static final int o = 11;
    public static final int p = 12;
    public static final int q = 13;
    public static final int r = 14;
    public static final int s = 15;
    public static final int t = 16;
    public static final int u = 17;
    public static final int v = 18;
    public static final int w = 19;
    @NotNull
    protected static final HashMap<Integer, String> x = new HashMap<>();
    private final PngChunkType y;

    static {
        x.put(2, "Image Height");
        x.put(1, "Image Width");
        x.put(3, "Bits Per Sample");
        x.put(4, "Color Type");
        x.put(5, "Compression Type");
        x.put(6, "Filter Method");
        x.put(7, "Interlace Method");
        x.put(8, "Palette Size");
        x.put(9, "Palette Has Transparency");
        x.put(10, "sRGB Rendering Intent");
        x.put(11, "Image Gamma");
        x.put(12, "ICC Profile Name");
        x.put(13, "Textual Data");
        x.put(14, "Last Modification Time");
        x.put(15, "Background Color");
        x.put(16, "Pixels Per Unit X");
        x.put(17, "Pixels Per Unit Y");
        x.put(18, "Unit Specifier");
        x.put(19, "Significant Bits");
    }

    public PngDirectory(@NotNull PngChunkType pngChunkType) {
        this.y = pngChunkType;
        a((TagDescriptor) new PngDescriptor(this));
    }

    @NotNull
    public PngChunkType j() {
        return this.y;
    }

    @NotNull
    public String a() {
        return "PNG-" + this.y.f();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return x;
    }
}
