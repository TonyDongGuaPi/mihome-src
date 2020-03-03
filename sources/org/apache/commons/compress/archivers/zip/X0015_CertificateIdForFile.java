package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;

public class X0015_CertificateIdForFile extends PKWareExtraHeader {

    /* renamed from: a  reason: collision with root package name */
    private int f3276a;
    private PKWareExtraHeader.HashAlgorithm b;

    public X0015_CertificateIdForFile() {
        super(new ZipShort(21));
    }

    public int a() {
        return this.f3276a;
    }

    public PKWareExtraHeader.HashAlgorithm b() {
        return this.b;
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        super.parseFromCentralDirectoryData(bArr, i, i2);
        this.f3276a = ZipShort.getValue(bArr, i);
        this.b = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i + 2));
    }
}
