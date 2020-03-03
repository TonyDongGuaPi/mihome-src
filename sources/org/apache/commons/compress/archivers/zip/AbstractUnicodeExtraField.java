package org.apache.commons.compress.archivers.zip;

import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

public abstract class AbstractUnicodeExtraField implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private long f3244a;
    private byte[] b;
    private byte[] c;

    protected AbstractUnicodeExtraField() {
    }

    protected AbstractUnicodeExtraField(String str, byte[] bArr, int i, int i2) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, i2);
        this.f3244a = crc32.getValue();
        try {
            this.b = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("FATAL: UTF-8 encoding not supported.", e);
        }
    }

    protected AbstractUnicodeExtraField(String str, byte[] bArr) {
        this(str, bArr, 0, bArr.length);
    }

    private void c() {
        if (this.b != null) {
            this.c = new byte[(this.b.length + 5)];
            this.c[0] = 1;
            System.arraycopy(ZipLong.getBytes(this.f3244a), 0, this.c, 1, 4);
            System.arraycopy(this.b, 0, this.c, 5, this.b.length);
        }
    }

    public long a() {
        return this.f3244a;
    }

    public void a(long j) {
        this.f3244a = j;
        this.c = null;
    }

    public byte[] b() {
        if (this.b == null) {
            return null;
        }
        byte[] bArr = new byte[this.b.length];
        System.arraycopy(this.b, 0, bArr, 0, bArr.length);
        return bArr;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.b = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.b, 0, bArr.length);
        } else {
            this.b = null;
        }
        this.c = null;
    }

    public byte[] getCentralDirectoryData() {
        if (this.c == null) {
            c();
        }
        if (this.c == null) {
            return null;
        }
        byte[] bArr = new byte[this.c.length];
        System.arraycopy(this.c, 0, bArr, 0, bArr.length);
        return bArr;
    }

    public ZipShort getCentralDirectoryLength() {
        if (this.c == null) {
            c();
        }
        return new ZipShort(this.c != null ? this.c.length : 0);
    }

    public byte[] getLocalFileDataData() {
        return getCentralDirectoryData();
    }

    public ZipShort getLocalFileDataLength() {
        return getCentralDirectoryLength();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 >= 5) {
            byte b2 = bArr[i];
            if (b2 == 1) {
                this.f3244a = ZipLong.getValue(bArr, i + 1);
                int i3 = i2 - 5;
                this.b = new byte[i3];
                System.arraycopy(bArr, i + 5, this.b, 0, i3);
                this.c = null;
                return;
            }
            throw new ZipException("Unsupported version [" + b2 + "] for UniCode path extra data.");
        }
        throw new ZipException("UniCode path extra data must have at least 5 bytes.");
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }
}
