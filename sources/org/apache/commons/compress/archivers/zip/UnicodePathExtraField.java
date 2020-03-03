package org.apache.commons.compress.archivers.zip;

public class UnicodePathExtraField extends AbstractUnicodeExtraField {

    /* renamed from: a  reason: collision with root package name */
    public static final ZipShort f3271a = new ZipShort(28789);

    public UnicodePathExtraField() {
    }

    public UnicodePathExtraField(String str, byte[] bArr, int i, int i2) {
        super(str, bArr, i, i2);
    }

    public UnicodePathExtraField(String str, byte[] bArr) {
        super(str, bArr);
    }

    public ZipShort getHeaderId() {
        return f3271a;
    }
}
