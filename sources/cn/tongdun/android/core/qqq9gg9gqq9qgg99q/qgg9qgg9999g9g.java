package cn.tongdun.android.core.qqq9gg9gqq9qgg99q;

import cn.tongdun.android.shell.settings.Constants;

public class qgg9qgg9999g9g {
    public static final String g9q9q9g9 = gqg9qq9gqq9q9q("1d7a2070366d3060677c387e25772a72233967207b21", 21);
    public static final String gqg9qq9gqq9q9q = Constants.VERSION;
    public static final String q9gqqq99999qq = gqg9qq9gqq9q9q("5a0768036b4a7e4a375c69147d076916601e71186a093c4431057f0a751c681a654d79127b0f720077093c4d255124", 58);
    public static final String q9q99gq99gggqg9qqqgg = gqg9qq9gqq9q9q("1c433a502e412749364f2d5e7b1376", 42);
    public static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("531861264e2a46", 40);
    public static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("53456b5376557b", 107);
    public static final String qqq9gg9gqq9qgg99q = gqg9qq9gqq9q9q("5a48684c6b057e053713330225057f4d6b5e7f4f764767417c502a1d275c695363457e4373146f4b6d56645961502a14330832", 117);

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 33);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 50);
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
