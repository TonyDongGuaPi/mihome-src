package cn.com.fmsh.util;

public class CRCUtil {
    public static byte[] calculateCRC16(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        short s = 0;
        while (i < length) {
            short s2 = (short) (bArr[i] << 8);
            short s3 = s;
            for (int i2 = 0; i2 < 8; i2++) {
                s3 = (short) (((short) (s3 ^ s2)) <= 0 ? ((short) (s3 << 1)) ^ 4129 : s3 << 1);
                s2 = (short) (s2 << 1);
            }
            i++;
            s = s3;
        }
        return FM_Bytes.intToBytes(s, 2);
    }

    public static void main(String[] strArr) {
        System.out.println(FM_Bytes.bytesToHexString(calculateCRC16(FM_Bytes.hexStringToBytes("01000000000000000300000000000000000000000000000000000000000000000000000000000000000000000000000000000030910E01010E10423244303832443232443134444345308E0101"))));
        System.out.println(65535);
    }
}
