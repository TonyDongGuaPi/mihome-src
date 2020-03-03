package org.apache.commons.compress.archivers.zip;

public final class UnparseableExtraFieldData implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private static final ZipShort f3273a = new ZipShort(44225);
    private byte[] b;
    private byte[] c;

    public ZipShort getHeaderId() {
        return f3273a;
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.b == null ? 0 : this.b.length);
    }

    public ZipShort getCentralDirectoryLength() {
        return this.c == null ? getLocalFileDataLength() : new ZipShort(this.c.length);
    }

    public byte[] getLocalFileDataData() {
        return ZipUtil.b(this.b);
    }

    public byte[] getCentralDirectoryData() {
        return this.c == null ? getLocalFileDataData() : ZipUtil.b(this.c);
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        this.b = new byte[i2];
        System.arraycopy(bArr, i, this.b, 0, i2);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        this.c = new byte[i2];
        System.arraycopy(bArr, i, this.c, 0, i2);
        if (this.b == null) {
            parseFromLocalFileData(bArr, i, i2);
        }
    }
}
