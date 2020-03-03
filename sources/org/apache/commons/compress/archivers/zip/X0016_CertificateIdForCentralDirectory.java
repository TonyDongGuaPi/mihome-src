package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;

public class X0016_CertificateIdForCentralDirectory extends PKWareExtraHeader {

    /* renamed from: a  reason: collision with root package name */
    private int f3277a;
    private PKWareExtraHeader.HashAlgorithm b;

    public X0016_CertificateIdForCentralDirectory() {
        super(new ZipShort(22));
    }

    public int a() {
        return this.f3277a;
    }

    public PKWareExtraHeader.HashAlgorithm b() {
        return this.b;
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        this.f3277a = ZipShort.getValue(bArr, i);
        this.b = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i + 2));
    }
}
