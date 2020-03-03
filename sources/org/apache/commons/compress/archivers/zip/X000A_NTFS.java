package org.apache.commons.compress.archivers.zip;

import java.util.Date;
import java.util.zip.ZipException;

public class X000A_NTFS implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private static final ZipShort f3275a = new ZipShort(10);
    private static final ZipShort b = new ZipShort(1);
    private static final ZipShort c = new ZipShort(24);
    private static final long g = -116444736000000000L;
    private ZipEightByteInteger d = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger e = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger f = ZipEightByteInteger.ZERO;

    public ZipShort getHeaderId() {
        return f3275a;
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(32);
    }

    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public byte[] getLocalFileDataData() {
        byte[] bArr = new byte[getLocalFileDataLength().getValue()];
        System.arraycopy(b.getBytes(), 0, bArr, 4, 2);
        System.arraycopy(c.getBytes(), 0, bArr, 6, 2);
        System.arraycopy(this.d.getBytes(), 0, bArr, 8, 8);
        System.arraycopy(this.e.getBytes(), 0, bArr, 16, 8);
        System.arraycopy(this.f.getBytes(), 0, bArr, 24, 8);
        return bArr;
    }

    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        int i3 = i2 + i;
        int i4 = i + 4;
        while (i4 + 4 <= i3) {
            ZipShort zipShort = new ZipShort(bArr, i4);
            int i5 = i4 + 2;
            if (zipShort.equals(b)) {
                a(bArr, i5, i3 - i5);
                return;
            }
            i4 = i5 + new ZipShort(bArr, i5).getValue() + 2;
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        g();
        parseFromLocalFileData(bArr, i, i2);
    }

    public ZipEightByteInteger a() {
        return this.d;
    }

    public ZipEightByteInteger b() {
        return this.e;
    }

    public ZipEightByteInteger c() {
        return this.f;
    }

    public Date d() {
        return d(this.d);
    }

    public Date e() {
        return d(this.e);
    }

    public Date f() {
        return d(this.f);
    }

    public void a(ZipEightByteInteger zipEightByteInteger) {
        if (zipEightByteInteger == null) {
            zipEightByteInteger = ZipEightByteInteger.ZERO;
        }
        this.d = zipEightByteInteger;
    }

    public void b(ZipEightByteInteger zipEightByteInteger) {
        if (zipEightByteInteger == null) {
            zipEightByteInteger = ZipEightByteInteger.ZERO;
        }
        this.e = zipEightByteInteger;
    }

    public void c(ZipEightByteInteger zipEightByteInteger) {
        if (zipEightByteInteger == null) {
            zipEightByteInteger = ZipEightByteInteger.ZERO;
        }
        this.f = zipEightByteInteger;
    }

    public void a(Date date) {
        a(d(date));
    }

    public void b(Date date) {
        b(d(date));
    }

    public void c(Date date) {
        c(d(date));
    }

    public String toString() {
        return "0x000A Zip Extra Field:" + " Modify:[" + d() + "] " + " Access:[" + e() + "] " + " Create:[" + f() + "] ";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof X000A_NTFS)) {
            return false;
        }
        X000A_NTFS x000a_ntfs = (X000A_NTFS) obj;
        if (this.d != x000a_ntfs.d && (this.d == null || !this.d.equals(x000a_ntfs.d))) {
            return false;
        }
        if (this.e != x000a_ntfs.e && (this.e == null || !this.e.equals(x000a_ntfs.e))) {
            return false;
        }
        if (this.f == x000a_ntfs.f || (this.f != null && this.f.equals(x000a_ntfs.f))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = -123;
        if (this.d != null) {
            i = -123 ^ this.d.hashCode();
        }
        if (this.e != null) {
            i ^= Integer.rotateLeft(this.e.hashCode(), 11);
        }
        return this.f != null ? i ^ Integer.rotateLeft(this.f.hashCode(), 22) : i;
    }

    private void g() {
        this.d = ZipEightByteInteger.ZERO;
        this.e = ZipEightByteInteger.ZERO;
        this.f = ZipEightByteInteger.ZERO;
    }

    private void a(byte[] bArr, int i, int i2) {
        if (i2 >= 26) {
            if (c.equals(new ZipShort(bArr, i))) {
                int i3 = i + 2;
                this.d = new ZipEightByteInteger(bArr, i3);
                int i4 = i3 + 8;
                this.e = new ZipEightByteInteger(bArr, i4);
                this.f = new ZipEightByteInteger(bArr, i4 + 8);
            }
        }
    }

    private static ZipEightByteInteger d(Date date) {
        if (date == null) {
            return null;
        }
        return new ZipEightByteInteger((date.getTime() * 10000) - g);
    }

    private static Date d(ZipEightByteInteger zipEightByteInteger) {
        if (zipEightByteInteger == null || ZipEightByteInteger.ZERO.equals(zipEightByteInteger)) {
            return null;
        }
        return new Date((zipEightByteInteger.getLongValue() + g) / 10000);
    }
}
