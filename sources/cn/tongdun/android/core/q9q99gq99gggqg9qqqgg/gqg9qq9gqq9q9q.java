package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;

public class gqg9qq9gqq9q9q {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("593661752e782b7328702b3f68267064336d2733623f702b633d", 59);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("577c753d3d203131262d2920", 100);

    public static boolean gqg9qq9gqq9q9q(PackageManager packageManager, String str) {
        if (q9gqqq99999qq.gqg9qq9gqq9q9q(packageManager, gqg9qq9gqq9q9q) == null) {
            return !TextUtils.isEmpty(str) && str.contains(qgg9qgg9999g9g);
        }
        return true;
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
            int i4 = 1;
            byte b = (byte) (i ^ 1);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.BUSINESS_ORDER_OP_TYPE);
            byte b2 = bArr[0];
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
