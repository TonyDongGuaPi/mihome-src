package org.apache.commons.compress.archivers.zip;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

public class AsiExtraField implements Cloneable, UnixStat, ZipExtraField {
    private static final ZipShort h = new ZipShort(30062);
    private static final int i = 4;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private String m = "";
    private boolean n = false;
    private CRC32 o = new CRC32();

    public ZipShort getHeaderId() {
        return h;
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(c().getBytes().length + 14);
    }

    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public byte[] getLocalFileDataData() {
        byte[] bArr = new byte[(getLocalFileDataLength().getValue() - 4)];
        System.arraycopy(ZipShort.getBytes(e()), 0, bArr, 0, 2);
        byte[] bytes = c().getBytes();
        System.arraycopy(ZipLong.getBytes((long) bytes.length), 0, bArr, 2, 4);
        System.arraycopy(ZipShort.getBytes(a()), 0, bArr, 6, 2);
        System.arraycopy(ZipShort.getBytes(b()), 0, bArr, 8, 2);
        System.arraycopy(bytes, 0, bArr, 10, bytes.length);
        this.o.reset();
        this.o.update(bArr);
        long value = this.o.getValue();
        byte[] bArr2 = new byte[(bArr.length + 4)];
        System.arraycopy(ZipLong.getBytes(value), 0, bArr2, 0, 4);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        return bArr2;
    }

    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    public void a(int i2) {
        this.k = i2;
    }

    public int a() {
        return this.k;
    }

    public void b(int i2) {
        this.l = i2;
    }

    public int b() {
        return this.l;
    }

    public void a(String str) {
        this.m = str;
        this.j = d(this.j);
    }

    public String c() {
        return this.m;
    }

    public boolean d() {
        return c().length() != 0;
    }

    public void c(int i2) {
        this.j = d(i2);
    }

    public int e() {
        return this.j;
    }

    public void a(boolean z) {
        this.n = z;
        this.j = d(this.j);
    }

    public boolean f() {
        return this.n && !d();
    }

    public void parseFromLocalFileData(byte[] bArr, int i2, int i3) throws ZipException {
        long value = ZipLong.getValue(bArr, i2);
        int i4 = i3 - 4;
        byte[] bArr2 = new byte[i4];
        boolean z = false;
        System.arraycopy(bArr, i2 + 4, bArr2, 0, i4);
        this.o.reset();
        this.o.update(bArr2);
        long value2 = this.o.getValue();
        if (value == value2) {
            int value3 = ZipShort.getValue(bArr2, 0);
            byte[] bArr3 = new byte[((int) ZipLong.getValue(bArr2, 2))];
            this.k = ZipShort.getValue(bArr2, 6);
            this.l = ZipShort.getValue(bArr2, 8);
            if (bArr3.length == 0) {
                this.m = "";
            } else {
                System.arraycopy(bArr2, 10, bArr3, 0, bArr3.length);
                this.m = new String(bArr3);
            }
            if ((value3 & 16384) != 0) {
                z = true;
            }
            a(z);
            c(value3);
            return;
        }
        throw new ZipException("bad CRC checksum " + Long.toHexString(value) + " instead of " + Long.toHexString(value2));
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i2, int i3) throws ZipException {
        parseFromLocalFileData(bArr, i2, i3);
    }

    /* access modifiers changed from: protected */
    public int d(int i2) {
        int i3;
        if (d()) {
            i3 = 40960;
        } else {
            i3 = f() ? 16384 : 32768;
        }
        return (i2 & UnixStat.f3272a) | i3;
    }

    public Object clone() {
        try {
            AsiExtraField asiExtraField = (AsiExtraField) super.clone();
            asiExtraField.o = new CRC32();
            return asiExtraField;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
