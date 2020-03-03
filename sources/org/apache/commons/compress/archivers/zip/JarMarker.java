package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

public final class JarMarker implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private static final ZipShort f3252a = new ZipShort(51966);
    private static final ZipShort b = new ZipShort(0);
    private static final byte[] c = new byte[0];
    private static final JarMarker d = new JarMarker();

    public static JarMarker a() {
        return d;
    }

    public ZipShort getHeaderId() {
        return f3252a;
    }

    public ZipShort getLocalFileDataLength() {
        return b;
    }

    public ZipShort getCentralDirectoryLength() {
        return b;
    }

    public byte[] getLocalFileDataData() {
        return c;
    }

    public byte[] getCentralDirectoryData() {
        return c;
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 != 0) {
            throw new ZipException("JarMarker doesn't expect any data");
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }
}
