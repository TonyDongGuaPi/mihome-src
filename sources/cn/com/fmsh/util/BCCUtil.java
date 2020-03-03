package cn.com.fmsh.util;

public class BCCUtil {
    public static byte calculateBCC(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length >= 1) {
                if (bArr.length == 1) {
                    return bArr[0];
                }
                byte b = bArr[0];
                for (int i = 1; i < bArr.length; i++) {
                    b = (byte) (b ^ bArr[i]);
                }
                return b;
            }
        }
        return 0;
    }

    public static void main(String[] strArr) {
        byte calculateBCC = calculateBCC(FM_Bytes.hexStringToBytes("383F2981687E5C020000000000000000000000000000000000000000000000000000000000000000201407221459599F9E63D5581EEEF1595FE022419F15E9000000"));
        System.out.println(String.format("%X", new Object[]{Byte.valueOf(calculateBCC)}));
    }
}
