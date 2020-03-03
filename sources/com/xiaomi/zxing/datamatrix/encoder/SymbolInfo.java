package com.xiaomi.zxing.datamatrix.encoder;

import com.drew.metadata.iptc.IptcDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.zxing.Dimension;

public class SymbolInfo {

    /* renamed from: a  reason: collision with root package name */
    static final SymbolInfo[] f1676a = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, IptcDirectory.av, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, IptcDirectory.n, 24, 24, 16, 136, 56), new SymbolInfo(false, PhotoshopDirectory.U, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, Downloads.STATUS_HTTP_EXCEPTION, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144()};
    private static SymbolInfo[] d = f1676a;
    public final int b;
    public final int c;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;

    public static void a(SymbolInfo[] symbolInfoArr) {
        d = symbolInfoArr;
    }

    public SymbolInfo(boolean z, int i2, int i3, int i4, int i5, int i6) {
        this(z, i2, i3, i4, i5, i6, i2, i3);
    }

    SymbolInfo(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.e = z;
        this.f = i2;
        this.g = i3;
        this.b = i4;
        this.c = i5;
        this.h = i6;
        this.i = i7;
        this.j = i8;
    }

    public static SymbolInfo b(int i2) {
        return a(i2, SymbolShapeHint.FORCE_NONE, true);
    }

    public static SymbolInfo a(int i2, SymbolShapeHint symbolShapeHint) {
        return a(i2, symbolShapeHint, true);
    }

    public static SymbolInfo a(int i2, boolean z, boolean z2) {
        return a(i2, z ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z2);
    }

    private static SymbolInfo a(int i2, SymbolShapeHint symbolShapeHint, boolean z) {
        return a(i2, symbolShapeHint, (Dimension) null, (Dimension) null, z);
    }

    public static SymbolInfo a(int i2, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z) {
        for (SymbolInfo symbolInfo : d) {
            if ((symbolShapeHint != SymbolShapeHint.FORCE_SQUARE || !symbolInfo.e) && ((symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || symbolInfo.e) && ((dimension == null || (symbolInfo.f() >= dimension.a() && symbolInfo.g() >= dimension.b())) && ((dimension2 == null || (symbolInfo.f() <= dimension2.a() && symbolInfo.g() <= dimension2.b())) && i2 <= symbolInfo.f)))) {
                return symbolInfo;
            }
        }
        if (!z) {
            return null;
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + i2);
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        int i2 = this.h;
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 36) {
            return 6;
        }
        switch (i2) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    /* access modifiers changed from: package-private */
    public final int c() {
        int i2 = this.h;
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 36) {
            return 6;
        }
        switch (i2) {
            case 1:
                return 1;
            case 2:
                return 1;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int d() {
        return b() * this.b;
    }

    public final int e() {
        return c() * this.c;
    }

    public final int f() {
        return d() + (b() * 2);
    }

    public final int g() {
        return e() + (c() * 2);
    }

    public int h() {
        return this.f + this.g;
    }

    public int a() {
        return this.f / this.i;
    }

    public final int i() {
        return this.f;
    }

    public final int j() {
        return this.g;
    }

    public int a(int i2) {
        return this.i;
    }

    public final int c(int i2) {
        return this.j;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.e ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.b);
        sb.append('x');
        sb.append(this.c);
        sb.append(", symbol size ");
        sb.append(f());
        sb.append('x');
        sb.append(g());
        sb.append(", symbol data size ");
        sb.append(d());
        sb.append('x');
        sb.append(e());
        sb.append(", codewords ");
        sb.append(this.f);
        sb.append('+');
        sb.append(this.g);
        return sb.toString();
    }
}
