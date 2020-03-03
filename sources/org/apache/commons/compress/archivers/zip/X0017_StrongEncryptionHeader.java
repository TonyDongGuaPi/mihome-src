package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;

public class X0017_StrongEncryptionHeader extends PKWareExtraHeader {

    /* renamed from: a  reason: collision with root package name */
    private int f3278a;
    private PKWareExtraHeader.EncryptionAlgorithm b;
    private int c;
    private int d;
    private long e;
    private PKWareExtraHeader.HashAlgorithm f;
    private int g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;

    public X0017_StrongEncryptionHeader() {
        super(new ZipShort(23));
    }

    public long a() {
        return this.e;
    }

    public PKWareExtraHeader.HashAlgorithm b() {
        return this.f;
    }

    public PKWareExtraHeader.EncryptionAlgorithm c() {
        return this.b;
    }

    public void a(byte[] bArr, int i2, int i3) {
        this.f3278a = ZipShort.getValue(bArr, i2);
        this.b = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i2 + 2));
        this.c = ZipShort.getValue(bArr, i2 + 4);
        this.d = ZipShort.getValue(bArr, i2 + 6);
        this.e = ZipLong.getValue(bArr, i2 + 8);
        if (this.e > 0) {
            this.f = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i2 + 12));
            this.g = ZipShort.getValue(bArr, i2 + 14);
            for (int i4 = 0; ((long) i4) < this.e; i4++) {
                for (int i5 = 0; i5 < this.g; i5++) {
                }
            }
        }
    }

    public void b(byte[] bArr, int i2, int i3) {
        int value = ZipShort.getValue(bArr, i2);
        this.h = new byte[value];
        System.arraycopy(bArr, i2 + 4, this.h, 0, value);
        int i4 = i2 + value;
        this.f3278a = ZipShort.getValue(bArr, i4 + 6);
        this.b = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i4 + 8));
        this.c = ZipShort.getValue(bArr, i4 + 10);
        this.d = ZipShort.getValue(bArr, i4 + 12);
        int value2 = ZipShort.getValue(bArr, i4 + 14);
        this.i = new byte[value2];
        int i5 = i4 + 16;
        System.arraycopy(bArr, i5, this.i, 0, value2);
        this.e = ZipLong.getValue(bArr, i5 + value2);
        System.out.println("rcount: " + this.e);
        if (this.e == 0) {
            int value3 = ZipShort.getValue(bArr, i4 + 20 + value2);
            int i6 = value3 - 4;
            this.l = new byte[i6];
            this.m = new byte[4];
            int i7 = i4 + 22 + value2;
            System.arraycopy(bArr, i7, this.l, 0, i6);
            System.arraycopy(bArr, (i7 + value3) - 4, this.m, 0, 4);
            return;
        }
        this.f = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i4 + 20 + value2));
        int i8 = i4 + 22 + value2;
        this.g = ZipShort.getValue(bArr, i8);
        int i9 = i4 + 24 + value2;
        int value4 = ZipShort.getValue(bArr, i9);
        this.j = new byte[this.g];
        this.k = new byte[(value4 - this.g)];
        System.arraycopy(bArr, i9, this.j, 0, this.g);
        System.arraycopy(bArr, i9 + this.g, this.k, 0, value4 - this.g);
        int value5 = ZipShort.getValue(bArr, i4 + 26 + value2 + value4);
        int i10 = value5 - 4;
        this.l = new byte[i10];
        this.m = new byte[4];
        int i11 = i8 + value4;
        System.arraycopy(bArr, i11, this.l, 0, i10);
        System.arraycopy(bArr, (i11 + value5) - 4, this.m, 0, 4);
    }

    public void parseFromLocalFileData(byte[] bArr, int i2, int i3) {
        super.parseFromLocalFileData(bArr, i2, i3);
        b(bArr, i2, i3);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i2, int i3) {
        super.parseFromCentralDirectoryData(bArr, i2, i3);
        a(bArr, i2, i3);
    }
}
