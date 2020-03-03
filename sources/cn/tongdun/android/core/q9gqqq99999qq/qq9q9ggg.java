package cn.tongdun.android.core.q9gqqq99999qq;

import org.json.JSONException;
import org.json.JSONObject;

public class qq9q9ggg extends gqg9qq9gqq9q9q {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("062e74396b2466", 55);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("06476c5a704a7c5569", 85);
    private JSONObject q9gqqq99999qq;
    private JSONObject q9qq99qg9qqgqg9gqgg9;

    public void gqg9qq9gqq9q9q(float f, float f2, float f3) {
        try {
            this.q9gqqq99999qq.put(gqg9qq9gqq9q9q("19", 6), (double) f);
            this.q9gqqq99999qq.put(gqg9qq9gqq9q9q("18", 56), (double) f2);
            this.q9gqqq99999qq.put(gqg9qq9gqq9q9q("1b", 82), (double) f3);
        } catch (JSONException unused) {
        }
    }

    public String gqg9qq9gqq9q9q() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(gqg9qq9gqq9q9q, this.q9qq99qg9qqgqg9gqgg9);
            jSONObject.put(qgg9qgg9999g9g, this.q9gqqq99999qq);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
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
            byte b = (byte) (i ^ 12);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 97);
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
