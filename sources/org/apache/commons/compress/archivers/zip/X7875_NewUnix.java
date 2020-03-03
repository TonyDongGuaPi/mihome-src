package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.zip.ZipException;

public class X7875_NewUnix implements Serializable, Cloneable, ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private static final ZipShort f3280a = new ZipShort(30837);
    private static final ZipShort b = new ZipShort(0);
    private static final BigInteger c = BigInteger.valueOf(1000);
    private static final long serialVersionUID = 1;
    private BigInteger gid;
    private BigInteger uid;
    private int version = 1;

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
    }

    public X7875_NewUnix() {
        a();
    }

    public ZipShort getHeaderId() {
        return f3280a;
    }

    public long getUID() {
        return ZipUtil.a(this.uid);
    }

    public long getGID() {
        return ZipUtil.a(this.gid);
    }

    public void setUID(long j) {
        this.uid = ZipUtil.b(j);
    }

    public void setGID(long j) {
        this.gid = ZipUtil.b(j);
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(trimLeadingZeroesForceMinLength(this.uid.toByteArray()).length + 3 + trimLeadingZeroesForceMinLength(this.gid.toByteArray()).length);
    }

    public ZipShort getCentralDirectoryLength() {
        return b;
    }

    public byte[] getLocalFileDataData() {
        byte[] byteArray = this.uid.toByteArray();
        byte[] byteArray2 = this.gid.toByteArray();
        byte[] trimLeadingZeroesForceMinLength = trimLeadingZeroesForceMinLength(byteArray);
        byte[] trimLeadingZeroesForceMinLength2 = trimLeadingZeroesForceMinLength(byteArray2);
        byte[] bArr = new byte[(trimLeadingZeroesForceMinLength.length + 3 + trimLeadingZeroesForceMinLength2.length)];
        ZipUtil.a(trimLeadingZeroesForceMinLength);
        ZipUtil.a(trimLeadingZeroesForceMinLength2);
        bArr[0] = ZipUtil.b(this.version);
        bArr[1] = ZipUtil.b(trimLeadingZeroesForceMinLength.length);
        System.arraycopy(trimLeadingZeroesForceMinLength, 0, bArr, 2, trimLeadingZeroesForceMinLength.length);
        int length = 2 + trimLeadingZeroesForceMinLength.length;
        bArr[length] = ZipUtil.b(trimLeadingZeroesForceMinLength2.length);
        System.arraycopy(trimLeadingZeroesForceMinLength2, 0, bArr, length + 1, trimLeadingZeroesForceMinLength2.length);
        return bArr;
    }

    public byte[] getCentralDirectoryData() {
        return new byte[0];
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        a();
        int i3 = i + 1;
        this.version = ZipUtil.a(bArr[i]);
        int i4 = i3 + 1;
        int a2 = ZipUtil.a(bArr[i3]);
        byte[] bArr2 = new byte[a2];
        System.arraycopy(bArr, i4, bArr2, 0, a2);
        int i5 = i4 + a2;
        this.uid = new BigInteger(1, ZipUtil.a(bArr2));
        int i6 = i5 + 1;
        int a3 = ZipUtil.a(bArr[i5]);
        byte[] bArr3 = new byte[a3];
        System.arraycopy(bArr, i6, bArr3, 0, a3);
        this.gid = new BigInteger(1, ZipUtil.a(bArr3));
    }

    private void a() {
        this.uid = c;
        this.gid = c;
    }

    public String toString() {
        return "0x7875 Zip Extra Field: UID=" + this.uid + " GID=" + this.gid;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof X7875_NewUnix)) {
            return false;
        }
        X7875_NewUnix x7875_NewUnix = (X7875_NewUnix) obj;
        if (this.version != x7875_NewUnix.version || !this.uid.equals(x7875_NewUnix.uid) || !this.gid.equals(x7875_NewUnix.gid)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.version * -1234567) ^ Integer.rotateLeft(this.uid.hashCode(), 16)) ^ this.gid.hashCode();
    }

    static byte[] trimLeadingZeroesForceMinLength(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length && bArr[i] == 0) {
            i2++;
            i++;
        }
        byte[] bArr2 = new byte[Math.max(1, bArr.length - i2)];
        int length2 = bArr2.length - (bArr.length - i2);
        System.arraycopy(bArr, i2, bArr2, length2, bArr2.length - length2);
        return bArr2;
    }
}
