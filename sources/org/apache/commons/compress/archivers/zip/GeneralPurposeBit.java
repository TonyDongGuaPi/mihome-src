package org.apache.commons.compress.archivers.zip;

import com.taobao.weex.ui.component.list.template.TemplateDom;

public final class GeneralPurposeBit implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3251a = 2048;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 4;
    private static final int e = 8;
    private static final int f = 64;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private int k;
    private int l;

    public boolean a() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean b() {
        return this.h;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public boolean c() {
        return this.i;
    }

    public void c(boolean z) {
        this.i = z;
    }

    public boolean d() {
        return this.i && this.j;
    }

    public void d(boolean z) {
        this.j = z;
        if (z) {
            c(true);
        }
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.l;
    }

    public byte[] g() {
        byte[] bArr = new byte[2];
        a(bArr, 0);
        return bArr;
    }

    public void a(byte[] bArr, int i2) {
        char c2 = 0;
        boolean z = (this.h ? (char) 8 : 0) | (this.g ? (char) 2048 : 0) | this.i;
        if (this.j) {
            c2 = TemplateDom.SEPARATOR;
        }
        ZipShort.putShort(z | c2 ? 1 : 0, bArr, i2);
    }

    public static GeneralPurposeBit b(byte[] bArr, int i2) {
        int value = ZipShort.getValue(bArr, i2);
        GeneralPurposeBit generalPurposeBit = new GeneralPurposeBit();
        boolean z = false;
        generalPurposeBit.b((value & 8) != 0);
        generalPurposeBit.a((value & 2048) != 0);
        generalPurposeBit.d((value & 64) != 0);
        if ((value & 1) != 0) {
            z = true;
        }
        generalPurposeBit.c(z);
        generalPurposeBit.k = (value & 2) != 0 ? 8192 : 4096;
        generalPurposeBit.l = (value & 4) != 0 ? 3 : 2;
        return generalPurposeBit;
    }

    public int hashCode() {
        return (((((((this.i ? 1 : 0) * true) + (this.j ? 1 : 0)) * 13) + (this.g ? 1 : 0)) * 7) + (this.h ? 1 : 0)) * 3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeneralPurposeBit)) {
            return false;
        }
        GeneralPurposeBit generalPurposeBit = (GeneralPurposeBit) obj;
        if (generalPurposeBit.i == this.i && generalPurposeBit.j == this.j && generalPurposeBit.g == this.g && generalPurposeBit.h == this.h) {
            return true;
        }
        return false;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException("GeneralPurposeBit is not Cloneable?", e2);
        }
    }
}
