package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.os.Build;

public class q9q99gq99gggqg9qqqgg {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("413e603a6c04500b531142055e0846", 4);

    public static boolean gqg9qq9gqq9q9q() {
        return gqg9qq9gqq9q9q.equals(Build.PRODUCT) || gqg9qq9gqq9q9q.equals(Build.DEVICE);
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 51);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 41);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
