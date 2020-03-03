package org.apache.commons.compress.archivers.zip;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.Serializable;
import java.util.Date;
import java.util.zip.ZipException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class X5455_ExtendedTimestamp implements Serializable, Cloneable, ZipExtraField {
    public static final byte ACCESS_TIME_BIT = 2;
    public static final byte CREATE_TIME_BIT = 4;
    public static final byte MODIFY_TIME_BIT = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final ZipShort f3279a = new ZipShort(21589);
    private static final long serialVersionUID = 1;
    private ZipLong accessTime;
    private boolean bit0_modifyTimePresent;
    private boolean bit1_accessTimePresent;
    private boolean bit2_createTimePresent;
    private ZipLong createTime;
    private byte flags;
    private ZipLong modifyTime;

    public ZipShort getHeaderId() {
        return f3279a;
    }

    public ZipShort getLocalFileDataLength() {
        int i = 0;
        int i2 = (this.bit0_modifyTimePresent ? 4 : 0) + 1 + ((!this.bit1_accessTimePresent || this.accessTime == null) ? 0 : 4);
        if (this.bit2_createTimePresent && this.createTime != null) {
            i = 4;
        }
        return new ZipShort(i2 + i);
    }

    public ZipShort getCentralDirectoryLength() {
        return new ZipShort((this.bit0_modifyTimePresent ? 4 : 0) + 1);
    }

    public byte[] getLocalFileDataData() {
        byte[] bArr = new byte[getLocalFileDataLength().getValue()];
        bArr[0] = 0;
        int i = 1;
        if (this.bit0_modifyTimePresent) {
            bArr[0] = (byte) (bArr[0] | 1);
            System.arraycopy(this.modifyTime.getBytes(), 0, bArr, 1, 4);
            i = 5;
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            bArr[0] = (byte) (bArr[0] | 2);
            System.arraycopy(this.accessTime.getBytes(), 0, bArr, i, 4);
            i += 4;
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            bArr[0] = (byte) (bArr[0] | 4);
            System.arraycopy(this.createTime.getBytes(), 0, bArr, i, 4);
        }
        return bArr;
    }

    public byte[] getCentralDirectoryData() {
        byte[] bArr = new byte[getCentralDirectoryLength().getValue()];
        System.arraycopy(getLocalFileDataData(), 0, bArr, 0, bArr.length);
        return bArr;
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        int i3;
        a();
        int i4 = i2 + i;
        int i5 = i + 1;
        setFlags(bArr[i]);
        if (this.bit0_modifyTimePresent) {
            this.modifyTime = new ZipLong(bArr, i5);
            i5 += 4;
        }
        if (!this.bit1_accessTimePresent || (i3 = i5 + 4) > i4) {
            i3 = i5;
        } else {
            this.accessTime = new ZipLong(bArr, i5);
        }
        if (this.bit2_createTimePresent && i3 + 4 <= i4) {
            this.createTime = new ZipLong(bArr, i3);
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        a();
        parseFromLocalFileData(bArr, i, i2);
    }

    private void a() {
        setFlags((byte) 0);
        this.modifyTime = null;
        this.accessTime = null;
        this.createTime = null;
    }

    public void setFlags(byte b) {
        this.flags = b;
        boolean z = false;
        this.bit0_modifyTimePresent = (b & 1) == 1;
        this.bit1_accessTimePresent = (b & 2) == 2;
        if ((b & 4) == 4) {
            z = true;
        }
        this.bit2_createTimePresent = z;
    }

    public byte getFlags() {
        return this.flags;
    }

    public boolean isBit0_modifyTimePresent() {
        return this.bit0_modifyTimePresent;
    }

    public boolean isBit1_accessTimePresent() {
        return this.bit1_accessTimePresent;
    }

    public boolean isBit2_createTimePresent() {
        return this.bit2_createTimePresent;
    }

    public ZipLong getModifyTime() {
        return this.modifyTime;
    }

    public ZipLong getAccessTime() {
        return this.accessTime;
    }

    public ZipLong getCreateTime() {
        return this.createTime;
    }

    public Date getModifyJavaTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getValue() * 1000);
        }
        return null;
    }

    public Date getAccessJavaTime() {
        if (this.accessTime != null) {
            return new Date(this.accessTime.getValue() * 1000);
        }
        return null;
    }

    public Date getCreateJavaTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getValue() * 1000);
        }
        return null;
    }

    public void setModifyTime(ZipLong zipLong) {
        this.bit0_modifyTimePresent = zipLong != null;
        this.flags = (byte) (zipLong != null ? 1 | this.flags : this.flags & -2);
        this.modifyTime = zipLong;
    }

    public void setAccessTime(ZipLong zipLong) {
        this.bit1_accessTimePresent = zipLong != null;
        this.flags = (byte) (zipLong != null ? this.flags | 2 : this.flags & -3);
        this.accessTime = zipLong;
    }

    public void setCreateTime(ZipLong zipLong) {
        this.bit2_createTimePresent = zipLong != null;
        this.flags = (byte) (zipLong != null ? this.flags | 4 : this.flags & -5);
        this.createTime = zipLong;
    }

    public void setModifyJavaTime(Date date) {
        setModifyTime(a(date));
    }

    public void setAccessJavaTime(Date date) {
        setAccessTime(a(date));
    }

    public void setCreateJavaTime(Date date) {
        setCreateTime(a(date));
    }

    private static ZipLong a(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime() / 1000;
        if (time < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            return new ZipLong(time);
        }
        throw new IllegalArgumentException("Cannot set an X5455 timestamp larger than 2^32: " + time);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("0x5455 Zip Extra Field: Flags=");
        sb.append(Integer.toBinaryString(ZipUtil.b((int) this.flags)));
        sb.append(" ");
        if (this.bit0_modifyTimePresent && this.modifyTime != null) {
            Date modifyJavaTime = getModifyJavaTime();
            sb.append(" Modify:[");
            sb.append(modifyJavaTime);
            sb.append("] ");
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            Date accessJavaTime = getAccessJavaTime();
            sb.append(" Access:[");
            sb.append(accessJavaTime);
            sb.append("] ");
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            Date createJavaTime = getCreateJavaTime();
            sb.append(" Create:[");
            sb.append(createJavaTime);
            sb.append("] ");
        }
        return sb.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof X5455_ExtendedTimestamp)) {
            return false;
        }
        X5455_ExtendedTimestamp x5455_ExtendedTimestamp = (X5455_ExtendedTimestamp) obj;
        if ((this.flags & 7) != (x5455_ExtendedTimestamp.flags & 7)) {
            return false;
        }
        if (this.modifyTime != x5455_ExtendedTimestamp.modifyTime && (this.modifyTime == null || !this.modifyTime.equals(x5455_ExtendedTimestamp.modifyTime))) {
            return false;
        }
        if (this.accessTime != x5455_ExtendedTimestamp.accessTime && (this.accessTime == null || !this.accessTime.equals(x5455_ExtendedTimestamp.accessTime))) {
            return false;
        }
        if (this.createTime == x5455_ExtendedTimestamp.createTime || (this.createTime != null && this.createTime.equals(x5455_ExtendedTimestamp.createTime))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = (this.flags & 7) * Constants.TagName.ACTIVITY_END;
        if (this.modifyTime != null) {
            i ^= this.modifyTime.hashCode();
        }
        if (this.accessTime != null) {
            i ^= Integer.rotateLeft(this.accessTime.hashCode(), 11);
        }
        return this.createTime != null ? i ^ Integer.rotateLeft(this.createTime.hashCode(), 22) : i;
    }
}
