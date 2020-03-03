package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.content.pm.PackageManager;
import cn.com.fmsh.tsm.business.constants.Constants;

public class qgg9qgg9999g9g {
    private static final String g9q9q9g9 = gqg9qq9gqq9q9q("395c611f2d1c2511344a7753601e2f0f2f132c1b221c", 78);
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("393561762d7525783423773a6077377d216a32", 39);
    private static final String q9gqqq99999qq = gqg9qq9gqq9q9q("394461072d04250934527943645b774b665d610722052f0e2a0726002e09", 86);
    private static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("397661352d36253b346077796034203f3217001501100f0c585649404e", 100);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("390e614d2d4e254334187701604c2f5d2f412c49224e6214730274", 28);
    private static final String[] qqq9gg9gqq9qgg99q = {gqg9qq9gqq9q9q, qgg9qgg9999g9g, q9qq99qg9qqgqg9gqgg9, q9gqqq99999qq, g9q9q9g9};

    public static boolean gqg9qq9gqq9q9q(PackageManager packageManager) {
        int i = 0;
        for (String gqg9qq9gqq9q9q2 : qqq9gg9gqq9qgg99q) {
            if (q9gqqq99999qq.gqg9qq9gqq9q9q(packageManager, gqg9qq9gqq9q9q2) != null) {
                i++;
            }
        }
        if (i >= 3) {
            return true;
        }
        return false;
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
            byte b = (byte) (i ^ 30);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.PREDEPOSIT_TOTAL);
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
