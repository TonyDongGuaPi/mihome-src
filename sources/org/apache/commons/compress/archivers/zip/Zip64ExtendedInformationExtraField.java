package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

public class Zip64ExtendedInformationExtraField implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    static final ZipShort f3281a = new ZipShort(1);
    private static final String b = "Zip64 extended information must contain both size values in the local file header.";
    private static final byte[] c = new byte[0];
    private ZipEightByteInteger d;
    private ZipEightByteInteger e;
    private ZipEightByteInteger f;
    private ZipLong g;
    private byte[] h;

    public Zip64ExtendedInformationExtraField() {
    }

    public Zip64ExtendedInformationExtraField(ZipEightByteInteger zipEightByteInteger, ZipEightByteInteger zipEightByteInteger2) {
        this(zipEightByteInteger, zipEightByteInteger2, (ZipEightByteInteger) null, (ZipLong) null);
    }

    public Zip64ExtendedInformationExtraField(ZipEightByteInteger zipEightByteInteger, ZipEightByteInteger zipEightByteInteger2, ZipEightByteInteger zipEightByteInteger3, ZipLong zipLong) {
        this.d = zipEightByteInteger;
        this.e = zipEightByteInteger2;
        this.f = zipEightByteInteger3;
        this.g = zipLong;
    }

    public ZipShort getHeaderId() {
        return f3281a;
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.d != null ? 16 : 0);
    }

    public ZipShort getCentralDirectoryLength() {
        int i = 8;
        int i2 = 0;
        int i3 = (this.d != null ? 8 : 0) + (this.e != null ? 8 : 0);
        if (this.f == null) {
            i = 0;
        }
        int i4 = i3 + i;
        if (this.g != null) {
            i2 = 4;
        }
        return new ZipShort(i4 + i2);
    }

    public byte[] getLocalFileDataData() {
        if (this.d == null && this.e == null) {
            return c;
        }
        if (this.d == null || this.e == null) {
            throw new IllegalArgumentException(b);
        }
        byte[] bArr = new byte[16];
        a(bArr);
        return bArr;
    }

    public byte[] getCentralDirectoryData() {
        byte[] bArr = new byte[getCentralDirectoryLength().getValue()];
        int a2 = a(bArr);
        if (this.f != null) {
            System.arraycopy(this.f.getBytes(), 0, bArr, a2, 8);
            a2 += 8;
        }
        if (this.g != null) {
            System.arraycopy(this.g.getBytes(), 0, bArr, a2, 4);
        }
        return bArr;
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 != 0) {
            if (i2 >= 16) {
                this.d = new ZipEightByteInteger(bArr, i);
                int i3 = i + 8;
                this.e = new ZipEightByteInteger(bArr, i3);
                int i4 = i3 + 8;
                int i5 = i2 - 16;
                if (i5 >= 8) {
                    this.f = new ZipEightByteInteger(bArr, i4);
                    i4 += 8;
                    i5 -= 8;
                }
                if (i5 >= 4) {
                    this.g = new ZipLong(bArr, i4);
                    return;
                }
                return;
            }
            throw new ZipException(b);
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        this.h = new byte[i2];
        System.arraycopy(bArr, i, this.h, 0, i2);
        if (i2 >= 28) {
            parseFromLocalFileData(bArr, i, i2);
        } else if (i2 == 24) {
            this.d = new ZipEightByteInteger(bArr, i);
            int i3 = i + 8;
            this.e = new ZipEightByteInteger(bArr, i3);
            this.f = new ZipEightByteInteger(bArr, i3 + 8);
        } else if (i2 % 8 == 4) {
            this.g = new ZipLong(bArr, (i + i2) - 4);
        }
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) throws ZipException {
        if (this.h != null) {
            int i = 0;
            int i2 = (z ? 8 : 0) + (z2 ? 8 : 0) + (z3 ? 8 : 0) + (z4 ? 4 : 0);
            if (this.h.length >= i2) {
                if (z) {
                    this.d = new ZipEightByteInteger(this.h, 0);
                    i = 8;
                }
                if (z2) {
                    this.e = new ZipEightByteInteger(this.h, i);
                    i += 8;
                }
                if (z3) {
                    this.f = new ZipEightByteInteger(this.h, i);
                    i += 8;
                }
                if (z4) {
                    this.g = new ZipLong(this.h, i);
                    return;
                }
                return;
            }
            throw new ZipException("central directory zip64 extended information extra field's length doesn't match central directory data.  Expected length " + i2 + " but is " + this.h.length);
        }
    }

    public ZipEightByteInteger a() {
        return this.d;
    }

    public void a(ZipEightByteInteger zipEightByteInteger) {
        this.d = zipEightByteInteger;
    }

    public ZipEightByteInteger b() {
        return this.e;
    }

    public void b(ZipEightByteInteger zipEightByteInteger) {
        this.e = zipEightByteInteger;
    }

    public ZipEightByteInteger c() {
        return this.f;
    }

    public void c(ZipEightByteInteger zipEightByteInteger) {
        this.f = zipEightByteInteger;
    }

    public ZipLong d() {
        return this.g;
    }

    public void a(ZipLong zipLong) {
        this.g = zipLong;
    }

    private int a(byte[] bArr) {
        int i;
        if (this.d != null) {
            System.arraycopy(this.d.getBytes(), 0, bArr, 0, 8);
            i = 8;
        } else {
            i = 0;
        }
        if (this.e == null) {
            return i;
        }
        System.arraycopy(this.e.getBytes(), 0, bArr, i, 8);
        return i + 8;
    }
}
