package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.PrintStream;
import java.util.Arrays;

public class FM_CN {
    private static String clazzName = FM_CN.class.getName();
    private static FMLog log = LogFactory.getInstance().getLog();
    private static String validBcd = "0123456789";
    public byte[] data = new byte[0];

    protected static int power(int i, int i2) {
        int i3 = 1;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 *= i;
        }
        return i3;
    }

    public FM_CN() {
    }

    public FM_CN(String str) {
        valueOf(str);
    }

    public void clear() {
        if (this.data == null) {
            this.data = new byte[0];
        } else if (this.data.length > 0) {
            this.data = new byte[0];
        }
    }

    public boolean setData(int i, byte[] bArr, int i2, int i3) throws FM_Exception {
        if (i2 < 0 || i2 + i3 > bArr.length) {
            throw new FM_Exception("invalid parameters");
        }
        int i4 = i + i3;
        if (i4 <= this.data.length) {
            Arrays.fill(this.data, i, i4, (byte) -1);
            int i5 = 0;
            while (i5 < i3) {
                int i6 = i2 + i5;
                byte b = (byte) ((bArr[i6] >> 4) & 15);
                if (b < 10) {
                    byte[] bArr2 = this.data;
                    int i7 = i + i5;
                    bArr2[i7] = (byte) (((b << 4) | 15) & bArr2[i7]);
                    byte b2 = (byte) (bArr[i6] & 15);
                    if (b2 < 10) {
                        byte[] bArr3 = this.data;
                        bArr3[i7] = (byte) ((b2 | 240) & bArr3[i7]);
                        i5++;
                    } else if (b2 == 15) {
                        return true;
                    } else {
                        clear();
                        return false;
                    }
                } else if (b == 15) {
                    return true;
                } else {
                    clear();
                    return false;
                }
            }
            return true;
        }
        throw new FM_Exception("overstep the boundary");
    }

    public boolean setData(int i, byte[] bArr) throws FM_Exception {
        return setData(i, bArr, 0, bArr.length);
    }

    public int length() {
        return this.data.length;
    }

    public boolean setData(int i, String str) throws FM_Exception {
        int i2;
        int length = (str.length() + 1) / 2;
        if (length > 0) {
            byte[] bArr = new byte[length];
            String upperCase = str.toUpperCase();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            byte b = 0;
            while (i3 < upperCase.length()) {
                int indexOf = validBcd.indexOf(upperCase.charAt(i3));
                if (indexOf != -1) {
                    i4++;
                    if (i4 % 2 == 1) {
                        b = (byte) (b | (indexOf << 4));
                    } else {
                        bArr[i5] = (byte) (b | indexOf);
                        i5++;
                        b = 0;
                    }
                    i3++;
                } else {
                    clear();
                    return false;
                }
            }
            if (i4 % 2 == 1) {
                i2 = i5 + 1;
                bArr[i5] = b;
            } else {
                i2 = i5;
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

    public boolean setBCD_R(String str, int i) {
        int i2;
        clear();
        if (i <= 0) {
            return false;
        }
        this.data = new byte[i];
        Arrays.fill(this.data, (byte) 0);
        String upperCase = str.toUpperCase();
        int length = upperCase.length();
        if (length <= 0) {
            return false;
        }
        if (length >= this.data.length * 2) {
            length = this.data.length * 2;
            i2 = 0;
        } else {
            i2 = (this.data.length * 2) - length;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int indexOf = validBcd.indexOf(upperCase.charAt(i3));
            byte b = indexOf == -1 ? 15 : (byte) (indexOf & 15);
            int i4 = i2 >> 1;
            this.data[i4] = (byte) (((byte) (i2 % 2 == 1 ? b & 15 : ((byte) (b << 4)) & 240)) | this.data[i4]);
            i2++;
        }
        return true;
    }

    public boolean setBCD_L(String str, int i) {
        clear();
        if (i <= 0) {
            return false;
        }
        this.data = new byte[i];
        Arrays.fill(this.data, (byte) -1);
        String upperCase = str.toUpperCase();
        int length = upperCase.length();
        if (length <= 0) {
            return false;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < length && i2 < i * 2) {
            int indexOf = validBcd.indexOf(upperCase.charAt(i2));
            if (indexOf == -1) {
                return false;
            }
            if (i2 % 2 == 1) {
                byte[] bArr = this.data;
                bArr[i3] = (byte) (((byte) (indexOf | PsExtractor.VIDEO_STREAM_MASK)) & bArr[i3]);
                i3++;
            } else {
                this.data[i3] = (byte) (((indexOf & 15) << 4) | 15);
            }
            i2++;
        }
        return true;
    }

    public boolean valueOf(String str) {
        clear();
        int length = (str.length() + 1) / 2;
        if (length <= 0) {
            return false;
        }
        this.data = new byte[length];
        Arrays.fill(this.data, (byte) -1);
        String upperCase = str.toUpperCase();
        int i = 0;
        byte b = 0;
        int i2 = 0;
        while (i < upperCase.length()) {
            int indexOf = validBcd.indexOf(upperCase.charAt(i));
            if (indexOf != -1) {
                if (i % 2 == 1) {
                    byte[] bArr = this.data;
                    bArr[i2] = (byte) (((byte) (b & (indexOf | 240))) & bArr[i2]);
                    i2++;
                    b = 0;
                } else {
                    b = (byte) (b | (indexOf << 4) | 15);
                    this.data[i2] = b;
                }
                i++;
            } else {
                clear();
                return false;
            }
        }
        return true;
    }

    public int intValue() {
        if (this.data.length <= 0) {
            return 0;
        }
        long bcd2Dec = bcd2Dec();
        if (bcd2Dec > 2147483647L || bcd2Dec < -2147483648L) {
            return 0;
        }
        return (int) bcd2Dec;
    }

    public short shortValue() {
        if (this.data.length <= 0) {
            return 0;
        }
        long bcd2Dec = bcd2Dec();
        if (bcd2Dec > 32767 || bcd2Dec < -32768) {
            return 0;
        }
        return (short) ((int) bcd2Dec);
    }

    public long longValue() {
        if (this.data.length > 0) {
            return bcd2Dec();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public long bcd2Dec() {
        int length = this.data.length;
        int i = 0;
        long j = 0;
        while (i < length) {
            byte b = (byte) ((this.data[i] & 240) >>> 4);
            if (b > 9) {
                break;
            }
            long j2 = (long) (b * 10);
            byte b2 = (byte) (this.data[i] & 15);
            i++;
            j += (b2 > 9 ? j2 + 0 : j2 + ((long) b2)) * ((long) power(100, length - i));
        }
        return j;
    }

    public String toHexString(char c) {
        StringBuilder sb = new StringBuilder();
        for (byte b : this.data) {
            sb.append(validBcd.charAt((b & 240) >> 4));
            sb.append(validBcd.charAt(b & 15));
            if (c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public int preplace(int i) {
        return preplace(i, (byte) -1);
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

    public byte getNumber(int i) {
        if (i == 0 || i > this.data.length * 2) {
            return -1;
        }
        byte b = this.data[(i - 1) / 2];
        return i % 2 == 1 ? (byte) (b >>> 4) : (byte) (b & 15);
    }

    public String getBCD() {
        if (this.data.length > 0) {
            return toHexString(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return this.data == null;
    }

    public byte[] getData() {
        return this.data;
    }

    public byte getByte(int i) {
        if (i < this.data.length) {
            return this.data[i];
        }
        return -1;
    }

    public static String bcdBytesToString(byte[] bArr, int i) {
        if (bArr.length <= i) {
            return bcdBytesToString(bArr);
        }
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = bArr[i2];
        }
        return bcdBytesToString(bArr2);
    }

    public static String bcdBytesToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append((byte) ((bArr[i] & 240) >>> 4));
            sb.append((byte) (bArr[i] & 15));
        }
        String sb2 = sb.toString();
        while (sb2.charAt(0) == '0' && sb2.length() != 1) {
            sb2 = sb2.substring(1);
        }
        return sb2;
    }

    public static int bcdBytesToInt(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = (byte) ((bArr[i] & 240) >>> 4);
            if (b > 9) {
                break;
            }
            int i3 = b * 10;
            byte b2 = (byte) (bArr[i] & 15);
            if (b2 > 9) {
                break;
            }
            i++;
            i2 += (i3 + b2) * power(100, length - i);
        }
        return i2;
    }

    public static byte[] intToBcdBytes(int i, int i2) {
        if (i2 >= 1) {
            byte[] bArr = new byte[i2];
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                int i4 = i % 100;
                bArr[i3] = (byte) (((byte) ((i4 / 10) << 4)) + ((byte) ((i4 % 10) & 15)));
                i /= 100;
            }
            return bArr;
        } else if (log == null) {
            return null;
        } else {
            log.error(clazzName, "十进制int型整数转成BCD码字节数组时，指定字节数组长度非正");
            return null;
        }
    }

    public static byte[] longToBcdBytes(long j, int i) {
        if (i >= 1) {
            byte[] bArr = new byte[i];
            for (int i2 = i - 1; i2 >= 0; i2--) {
                int i3 = (int) (j % 100);
                bArr[i2] = (byte) (((byte) ((i3 / 10) << 4)) + ((byte) ((i3 % 10) & 15)));
                j /= 100;
            }
            return bArr;
        } else if (log == null) {
            return null;
        } else {
            log.error(clazzName, "十进制long型整数转成BCD码字节数组时，指定字节数组长度非正");
            return null;
        }
    }

    public static byte[] string2Bcd(String str) {
        int i;
        int i2;
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length = str.length();
        }
        if (length >= 2) {
            length /= 2;
        }
        byte[] bArr = new byte[length];
        byte[] bytes = str.getBytes();
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 2;
            if (bytes[i4] >= 48 && bytes[i4] <= 57) {
                i = bytes[i4] - 48;
            } else if (bytes[i4] < 97 || bytes[i4] > 122) {
                i = bytes[i4] + Constants.TagName.STATION_ID + 10;
            } else {
                i = (bytes[i4] - 97) + 10;
            }
            int i5 = i4 + 1;
            if (bytes[i5] >= 48 && bytes[i5] <= 57) {
                i2 = bytes[i5] - 48;
            } else if (bytes[i5] < 97 || bytes[i5] > 122) {
                i2 = bytes[i5] + Constants.TagName.STATION_ID + 10;
            } else {
                i2 = (bytes[i5] - 97) + 10;
            }
            bArr[i3] = (byte) ((i << 4) + i2);
        }
        return bArr;
    }

    public static void main(String[] strArr) {
        String bytesToHexString = FM_Bytes.bytesToHexString(intToBcdBytes(500, 3));
        PrintStream printStream = System.out;
        printStream.println("str=" + bytesToHexString);
        FM_CN fm_cn = new FM_CN("01235");
        System.out.println(fm_cn.intValue());
        try {
            fm_cn.setData(0, new byte[]{1, 35, 95});
            System.out.println(fm_cn.intValue());
        } catch (FM_Exception e) {
            e.printStackTrace();
        }
    }
}
