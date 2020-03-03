package org.apache.commons.compress.archivers.zip;

public class UnrecognizedExtraField implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private ZipShort f3274a;
    private byte[] b;
    private byte[] c;

    public void a(ZipShort zipShort) {
        this.f3274a = zipShort;
    }

    public ZipShort getHeaderId() {
        return this.f3274a;
    }

    public void a(byte[] bArr) {
        this.b = ZipUtil.b(bArr);
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.b != null ? this.b.length : 0);
    }

    public byte[] getLocalFileDataData() {
        return ZipUtil.b(this.b);
    }

    public void b(byte[] bArr) {
        this.c = ZipUtil.b(bArr);
    }

    public ZipShort getCentralDirectoryLength() {
        if (this.c != null) {
            return new ZipShort(this.c.length);
        }
        return getLocalFileDataLength();
    }

    public byte[] getCentralDirectoryData() {
        if (this.c != null) {
            return ZipUtil.b(this.c);
        }
        return getLocalFileDataData();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        a(bArr2);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        b(bArr2);
        if (this.b == null) {
            a(bArr2);
        }
    }
}
