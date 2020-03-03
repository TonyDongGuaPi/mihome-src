package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;

public class qq9gq9g9g99 {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("02315f3c5937436e3a5f2c58147517", 93);

    public static boolean gqg9qq9gqq9q9q(String str) {
        return !TextUtils.isEmpty(str) && str.contains(gqg9qq9gqq9q9q);
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
            byte b = (byte) (i ^ 93);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.QUERY_DATA_SORT_TYPE);
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
