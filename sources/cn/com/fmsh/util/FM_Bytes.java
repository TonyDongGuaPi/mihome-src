package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.coloros.mcssdk.c.a;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public class FM_Bytes {
    private static String clazzName = FM_Bytes.class.getName();
    private static String hexString = a.f;
    private static FMLog log = LogFactory.getInstance().getLog();
    public byte[] data = new byte[0];

    public FM_Bytes() {
    }

    public FM_Bytes(String str) {
        valueof(str);
    }

    public int length() {
        return this.data.length;
    }

    public void clear() {
        if (this.data == null) {
            this.data = new byte[0];
        } else if (this.data.length > 0) {
            this.data = new byte[0];
        }
    }

    public int preplace(int i, byte b) {
        if (this.data.length != i) {
            this.data = new byte[i];
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.data[i2] = b;
        }
        return this.data.length;
    }

    public int preplace(int i) {
        return preplace(i, (byte) 0);
    }

    public boolean setData(int i, byte[] bArr) throws FM_Exception {
        if (bArr.length + i <= this.data.length) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                this.data[i + i2] = bArr[i2];
            }
            return true;
        }
        throw new FM_Exception("overstep the boundary");
    }

    public boolean setData(int i, byte[] bArr, int i2, int i3) throws FM_Exception {
        if (i2 < 0 || i2 + i3 > bArr.length) {
            throw new FM_Exception("invalid parameters");
        } else if (i + i3 <= this.data.length) {
            for (int i4 = 0; i4 < i3; i4++) {
                this.data[i + i4] = bArr[i2 + i4];
            }
            return true;
        } else {
            throw new FM_Exception("overstep the boundary");
        }
    }

    public boolean setData(int i, String str) throws FM_Exception {
        int i2;
        int length = (str.length() + 1) / 2;
        if (length > 0) {
            byte[] bArr = new byte[length];
            String upperCase = str.toUpperCase();
            int i3 = 0;
            int i4 = 0;
            byte b = 0;
            for (int i5 = 0; i5 < upperCase.length(); i5++) {
                int indexOf = hexString.indexOf(upperCase.charAt(i5));
                if (indexOf != -1) {
                    i3++;
                    if (i3 % 2 == 1) {
                        b = (byte) (b | (indexOf << 4));
                    } else {
                        bArr[i4] = (byte) (b | indexOf);
                        i4++;
                        b = 0;
                    }
                }
            }
            if (i3 % 2 == 1) {
                i2 = i4 + 1;
                bArr[i4] = b;
            } else {
                i2 = i4;
            }
            if (i + i2 <= this.data.length) {
                for (int i6 = 0; i6 < i2; i6++) {
                    this.data[i + i6] = bArr[i6];
                }
            } else {
                throw new FM_Exception("overstep the boundary");
            }
        }
        return true;
    }

    public void copy(int i) throws FM_Exception {
        if (i < 0 || i < this.data.length) {
            throw new FM_Exception("overstep the boundary");
        }
        this.data = copyOf(this.data, i);
    }

    public boolean valueof(String str) {
        int i;
        int length = (str.length() + 1) / 2;
        if (length > 0) {
            byte[] bArr = new byte[length];
            String upperCase = str.toUpperCase();
            int i2 = 0;
            int i3 = 0;
            byte b = 0;
            for (int i4 = 0; i4 < upperCase.length(); i4++) {
                int indexOf = hexString.indexOf(upperCase.charAt(i4));
                if (indexOf != -1) {
                    i2++;
                    if (i2 % 2 == 1) {
                        b = (byte) (b | (indexOf << 4));
                    } else {
                        bArr[i3] = (byte) (b | indexOf);
                        i3++;
                        b = 0;
                    }
                }
            }
            if (i2 % 2 == 1) {
                bArr[i3] = b;
                i = i3 + 1;
            } else {
                i = i3;
            }
            if (i == length) {
                this.data = bArr;
            } else if (i == 0) {
                this.data = new byte[0];
            } else {
                this.data = new byte[i];
                for (int i5 = 0; i5 < i; i5++) {
                    this.data[i5] = bArr[i5];
                }
            }
        } else {
            this.data = new byte[0];
        }
        return true;
    }

    public String toHexString(char c) {
        StringBuilder sb = new StringBuilder();
        for (byte b : this.data) {
            sb.append(hexString.charAt((b & 240) >> 4));
            sb.append(hexString.charAt(b & 15));
            if (c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String toString() {
        return toHexString(' ');
    }

    public short shortValue() throws FM_Exception {
        return shortValue(0, false);
    }

    public short shortValue(boolean z) throws FM_Exception {
        return shortValue(0, z);
    }

    public short shortValue(int i, boolean z) throws FM_Exception {
        if (i + 2 > this.data.length) {
            throw new FM_Exception("overstep the boundary");
        } else if (z) {
            return (short) ((this.data[i + 1] & 255) | ((short) (this.data[i] << 8)));
        } else {
            return (short) ((this.data[i] & 255) | ((short) (this.data[i + 1] << 8)));
        }
    }

    public int intValue() throws FM_Exception {
        return intValue(0, false);
    }

    public int intValue(boolean z) throws FM_Exception {
        return intValue(0, z);
    }

    public int intValue(int i, boolean z) throws FM_Exception {
        byte b;
        if (i + 4 <= this.data.length) {
            if (z) {
                b = this.data[i];
                for (int i2 = 1; i2 < 4; i2++) {
                    b = (b << 8) | (this.data[i + i2] & 255);
                }
            } else {
                byte b2 = this.data[i + 3];
                for (int i3 = 2; i3 >= 0; i3--) {
                    b2 = (b << 8) | (this.data[i + i3] & 255);
                }
            }
            return b;
        }
        throw new FM_Exception("overstep the boundary");
    }

    public long longValue() throws FM_Exception {
        return longValue(0, false);
    }

    public long longValue(boolean z) throws FM_Exception {
        return longValue(0, z);
    }

    public long longValue(int i, boolean z) throws FM_Exception {
        long j;
        if (i + 8 <= this.data.length) {
            if (z) {
                j = (long) this.data[i];
                for (int i2 = 1; i2 < 8; i2++) {
                    j = (j << 8) | ((long) (this.data[i + i2] & 255));
                }
            } else {
                long j2 = (long) this.data[i + 7];
                for (int i3 = 6; i3 >= 0; i3--) {
                    j2 = (j << 8) | ((long) (this.data[i + i3] & 255));
                }
            }
            return j;
        }
        throw new FM_Exception("overstep the boundary");
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public static byte[] xor(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            if (log != null) {
                log.error(clazzName, "异或时，数组为null");
            }
            return null;
        } else if (bArr.length != bArr2.length) {
            if (log != null) {
                log.error(clazzName, "异或时，byte数组长度不等");
            }
            return null;
        } else {
            byte[] bArr3 = (byte[]) bArr.clone();
            for (int i = 0; i < bArr3.length; i++) {
                bArr3[i] = (byte) (bArr3[i] ^ bArr2[i]);
            }
            return bArr3;
        }
    }

    public static byte[] not(byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = (byte[]) bArr.clone();
            for (int i = 0; i < bArr2.length; i++) {
                bArr2[i] = (byte) (bArr[i] ^ -1);
            }
            return bArr2;
        } else if (log == null) {
            return null;
        } else {
            log.error(clazzName, "取反时，数组为null");
            return null;
        }
    }

    public static byte[] and(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            if (log != null) {
                log.error(clazzName, "与运算时，数组为null");
            }
            return null;
        } else if (bArr.length != bArr2.length) {
            if (log != null) {
                log.error(clazzName, "与运算时，byte数组长度不等");
            }
            return null;
        } else {
            byte[] bArr3 = (byte[]) bArr.clone();
            for (int i = 0; i < bArr3.length; i++) {
                bArr3[i] = (byte) (bArr3[i] & bArr2[i]);
            }
            return bArr3;
        }
    }

    public static byte[] or(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            if (log != null) {
                log.error(clazzName, "或运算时，数组为null");
            }
            return null;
        } else if (bArr.length != bArr2.length) {
            if (log != null) {
                log.error(clazzName, "或运算时，byte数组长度不等");
            }
            return null;
        } else {
            byte[] bArr3 = (byte[]) bArr.clone();
            for (int i = 0; i < bArr3.length; i++) {
                bArr3[i] = (byte) (bArr3[i] | bArr2[i]);
            }
            return bArr3;
        }
    }

    public static byte[] join(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            if (log != null) {
                log.error(clazzName, "字节数组合并时，数组为null");
            }
            return bArr;
        }
        int length = bArr.length;
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        for (int i = 0; i < length; i++) {
            bArr3[i] = bArr[i];
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            bArr3[length + i2] = bArr2[i2];
        }
        return bArr3;
    }

    public static byte[] hexStringToBytes(String str) {
        int i;
        if (str == null || str.length() < 1) {
            return null;
        }
        int length = (str.length() + 1) / 2;
        if (length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[length];
        String upperCase = str.toUpperCase();
        int i2 = 0;
        int i3 = 0;
        byte b = 0;
        for (int i4 = 0; i4 < upperCase.length(); i4++) {
            int indexOf = hexString.indexOf(upperCase.charAt(i4));
            if (indexOf != -1) {
                i2++;
                if (i2 % 2 == 1) {
                    b = (byte) (b | (indexOf << 4));
                } else {
                    bArr[i3] = (byte) (b | indexOf);
                    i3++;
                    b = 0;
                }
            }
        }
        if (i2 % 2 == 1) {
            bArr[i3] = b;
            i = i3 + 1;
        } else {
            i = i3;
        }
        if (i == length) {
            return bArr;
        }
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i];
        for (int i5 = 0; i5 < i; i5++) {
            bArr2[i5] = bArr[i5];
        }
        return bArr2;
    }

    public static String bytesToHexString(byte[] bArr, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString2 = Integer.toHexString(b & 255);
            if (hexString2.length() == 1) {
                hexString2 = String.valueOf('0') + hexString2;
            }
            if (str != null && !"".equals(str)) {
                sb.append(str);
            }
            sb.append(hexString2.toUpperCase());
            if (str2 != null && !"".equals(str2)) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bytesToHexString(bArr, "", "");
    }

    public static int bytesToInt(byte[] bArr) {
        return bytesToInt(bArr, true);
    }

    public static int bytesToInt(byte[] bArr, boolean z) {
        byte b;
        if (bArr == null) {
            if (log != null) {
                log.error(clazzName, "字节数组转成十进制整数时，数组为null");
            }
            return -1;
        }
        if (bArr.length < 1) {
            if (log != null) {
                log.error(clazzName, "字节数组转成十进制整数时，数组长度为0");
            }
            return -1;
        } else if (bArr.length == 1) {
            return bArr[0] & 255;
        } else {
            if (z) {
                b = bArr[0];
                for (int i = 1; i < bArr.length; i++) {
                    b = (b << 8) | (bArr[i] & 255);
                }
            } else {
                byte b2 = bArr[bArr.length - 1];
                for (int length = bArr.length - 2; length >= 0; length--) {
                    b2 = (b << 8) | (bArr[length] & 255);
                }
            }
            return b;
        }
    }

    public static long bytesToLong(byte[] bArr, boolean z) {
        long j;
        if (bArr == null) {
            if (log != null) {
                log.error(clazzName, "字节数组转成十进制整数时，数组为null");
            }
            return -1;
        }
        if (bArr.length < 1) {
            if (log != null) {
                log.error(clazzName, "字节数组转成十进制整数时，数组长度为0");
            }
            return -1;
        } else if (bArr.length == 1) {
            return (long) (bArr[0] & 255);
        } else {
            if (z) {
                j = (long) (bArr[0] & 255);
                for (int i = 1; i < bArr.length; i++) {
                    j = (j << 8) | ((long) (bArr[i] & 255));
                }
            } else {
                long j2 = (long) bArr[bArr.length - 1];
                for (int length = bArr.length - 2; length >= 0; length--) {
                    j2 = (j << 8) | ((long) (bArr[length] & 255));
                }
            }
            return j;
        }
    }

    public static long bytesToLong(byte[] bArr) {
        return bytesToLong(bArr, true);
    }

    public static byte[] intToBytes(int i, int i2, boolean z) {
        if (i2 >= 1) {
            byte[] bArr = new byte[i2];
            if (z) {
                for (int length = bArr.length - 1; length > -1; length--) {
                    bArr[length] = Integer.valueOf(i & 255).byteValue();
                    i >>= 8;
                }
            } else {
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    bArr[i3] = Integer.valueOf(i & 255).byteValue();
                    i >>= 8;
                }
            }
            return bArr;
        } else if (log == null) {
            return null;
        } else {
            log.error(clazzName, "十进制int整数转成字节数组时，指定的数组长度非正");
            return null;
        }
    }

    public static byte[] longToBytes(long j, int i, boolean z) {
        if (i >= 1) {
            byte[] bArr = new byte[i];
            if (z) {
                for (int length = bArr.length - 1; length > -1; length--) {
                    bArr[length] = Long.valueOf(j & 255).byteValue();
                    j >>= 8;
                }
            } else {
                for (int i2 = 0; i2 < bArr.length; i2++) {
                    bArr[i2] = Long.valueOf(j & 255).byteValue();
                    j >>= 8;
                }
            }
            return bArr;
        } else if (log == null) {
            return null;
        } else {
            log.error(clazzName, "十进制long整数转成字节数组时，指定的数组长度非正");
            return null;
        }
    }

    public static byte[] longToBytes(long j, int i) {
        return longToBytes(j, i, true);
    }

    public static byte[] intToBytes(int i, int i2) {
        return intToBytes(i, i2, true);
    }

    public static byte[] longToAsciiBytes(long j) {
        char[] charArray = new StringBuilder(String.valueOf(j)).toString().toCharArray();
        int length = charArray.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (Integer.parseInt(new StringBuilder(String.valueOf(charArray[i])).toString()) + 48);
        }
        return bArr;
    }

    public static boolean tlv(byte[] bArr, byte b, byte b2, byte[] bArr2) {
        if (b != bArr[0] || b2 != bArr[1] || bArr.length != b2 + 2 || bArr2.length != b2) {
            return false;
        }
        for (int i = 0; i < b2; i++) {
            bArr2[i] = bArr[i + 2];
        }
        return true;
    }

    public static void reverse(byte[] bArr) {
        int length = bArr.length;
        byte[] copyOf = copyOf(bArr, length);
        for (int i = 0; i < length; i++) {
            bArr[i] = copyOf[(length - i) - 1];
        }
    }

    public static byte[] copyOf(byte[] bArr, int i) {
        if (bArr != null) {
            byte[] bArr2 = new byte[i];
            int i2 = 0;
            if (bArr.length < i) {
                while (i2 < bArr.length) {
                    bArr2[i2] = bArr[i2];
                    i2++;
                }
            } else {
                while (i2 < i) {
                    bArr2[i2] = bArr[i2];
                    i2++;
                }
            }
            return bArr2;
        }
        throw new NullPointerException(" original Arrays is null");
    }

    public static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            int i3 = i2 - i;
            if (i3 <= 0) {
                throw new IllegalArgumentException(" from[" + i + "]>to[" + i2 + Operators.ARRAY_END_STR);
            } else if (bArr.length < i2 || bArr.length < i) {
                throw new IllegalArgumentException(" ");
            } else {
                byte[] bArr2 = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    bArr2[i4] = bArr[i + i4];
                }
                return bArr2;
            }
        } else {
            throw new NullPointerException(" original Arrays is null");
        }
    }

    public static boolean isEnd(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        int length = bArr.length;
        for (int length2 = bArr2.length - 1; length2 >= 0; length2--) {
            length--;
            if (bArr2[length2] != bArr[length]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEnd9000(byte[] bArr) {
        byte[] bArr2 = new byte[2];
        bArr2[0] = Constants.TagName.SYSTEM_VERSION;
        return isEnd(bArr, bArr2);
    }

    public static byte[] patch(byte[] bArr, int i, byte b) {
        if (bArr == null || i <= 0) {
            return null;
        }
        int length = bArr.length;
        if (length >= i) {
            return (byte[]) bArr.clone();
        }
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2] = bArr[i2];
        }
        for (int i3 = 0; i3 < i - length; i3++) {
            bArr2[length + i3] = b;
        }
        return bArr2;
    }

    public static byte getByteParity(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return -1;
        }
        if (bArr.length == 1) {
            return bArr[0];
        }
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            b = (byte) (b ^ bArr[i]);
        }
        return b;
    }

    public static byte[] concatArrays(byte[] bArr, byte[]... bArr2) {
        int length = bArr.length;
        for (byte[] length2 : bArr2) {
            length += length2.length;
        }
        byte[] copyOf = Arrays.copyOf(bArr, length);
        int length3 = bArr.length;
        int i = length3;
        for (byte[] bArr3 : bArr2) {
            System.arraycopy(bArr3, 0, copyOf, i, bArr3.length);
            i += bArr3.length;
        }
        return copyOf;
    }

    public static byte[] bytePatch4Des(byte[] bArr) {
        int i = 8;
        byte[] bArr2 = new byte[8];
        bArr2[0] = Byte.MIN_VALUE;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length % 8;
        if (length != 0) {
            i = 8 - length;
        }
        return join(bArr, copyOf(bArr2, i));
    }

    public static byte[] byteRemovePatch4Des(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        while (true) {
            length--;
            if (length <= bArr.length - 9) {
                return bArr;
            }
            if (Byte.MIN_VALUE == bArr[length]) {
                return Arrays.copyOf(bArr, length);
            }
        }
    }

    public static boolean isPatch4Des(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (int length = bArr.length - 1; length > bArr.length - 9; length--) {
            if (Byte.MIN_VALUE == bArr[length]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] strArr) {
        byte[] bArr = new byte[2];
        bArr[0] = Constants.TagName.ELECTRONIC_STARTTIME;
        System.out.println(isEnd9000(bArr));
    }
}
