package com.drew.imaging.png;

import com.drew.lang.annotations.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PngChunkType {

    /* renamed from: a  reason: collision with root package name */
    public static final PngChunkType f5186a;
    public static final PngChunkType b;
    public static final PngChunkType c;
    public static final PngChunkType d;
    public static final PngChunkType e;
    public static final PngChunkType f;
    public static final PngChunkType g;
    public static final PngChunkType h;
    public static final PngChunkType i;
    public static final PngChunkType j;
    public static final PngChunkType k;
    public static final PngChunkType l;
    public static final PngChunkType m;
    public static final PngChunkType n;
    public static final PngChunkType o;
    public static final PngChunkType p;
    public static final PngChunkType q;
    public static final PngChunkType r;
    static final /* synthetic */ boolean s = (!PngChunkType.class.desiredAssertionStatus());
    private static final Set<String> t = new HashSet(Arrays.asList(new String[]{"IDAT", "sPLT", "iTXt", "tEXt", "zTXt"}));
    private final byte[] u;
    private final boolean v;

    private static boolean a(byte b2) {
        return (b2 & 32) != 0;
    }

    private static boolean b(byte b2) {
        return (b2 & 32) == 0;
    }

    private static boolean c(byte b2) {
        return (b2 >= 65 && b2 <= 90) || (b2 >= 97 && b2 <= 122);
    }

    static {
        try {
            f5186a = new PngChunkType("IHDR");
            b = new PngChunkType("PLTE");
            c = new PngChunkType("IDAT", true);
            d = new PngChunkType("IEND");
            e = new PngChunkType("cHRM");
            f = new PngChunkType("gAMA");
            g = new PngChunkType("iCCP");
            h = new PngChunkType("sBIT");
            i = new PngChunkType("sRGB");
            j = new PngChunkType("bKGD");
            k = new PngChunkType("hIST");
            l = new PngChunkType("tRNS");
            m = new PngChunkType("pHYs");
            n = new PngChunkType("sPLT", true);
            o = new PngChunkType("tIME");
            p = new PngChunkType("iTXt", true);
            q = new PngChunkType("tEXt", true);
            r = new PngChunkType("zTXt", true);
        } catch (PngProcessingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public PngChunkType(@NotNull String str) throws PngProcessingException {
        this(str, false);
    }

    public PngChunkType(@NotNull String str, boolean z) throws PngProcessingException {
        this.v = z;
        try {
            byte[] bytes = str.getBytes("ASCII");
            a(bytes);
            this.u = bytes;
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalArgumentException("Unable to convert string code to bytes.");
        }
    }

    public PngChunkType(@NotNull byte[] bArr) throws PngProcessingException {
        a(bArr);
        this.u = bArr;
        this.v = t.contains(f());
    }

    private static void a(byte[] bArr) throws PngProcessingException {
        if (bArr.length == 4) {
            int length = bArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (c(bArr[i2])) {
                    i2++;
                } else {
                    throw new PngProcessingException("PNG chunk type identifier may only contain alphabet characters");
                }
            }
            return;
        }
        throw new PngProcessingException("PNG chunk type identifier must be four bytes in length");
    }

    public boolean a() {
        return b(this.u[0]);
    }

    public boolean b() {
        return !a();
    }

    public boolean c() {
        return b(this.u[1]);
    }

    public boolean d() {
        return a(this.u[3]);
    }

    public boolean e() {
        return this.v;
    }

    public String f() {
        try {
            return new String(this.u, "ASCII");
        } catch (UnsupportedEncodingException unused) {
            if (s) {
                return "Invalid object instance";
            }
            throw new AssertionError();
        }
    }

    public String toString() {
        return f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.u, ((PngChunkType) obj).u);
    }

    public int hashCode() {
        return Arrays.hashCode(this.u);
    }
}
