package cn.tongdun.android.core.q9gqqq99999qq;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class qgggqg999gg9qqggq extends gqg9qq9gqq9q9q {
    private List gqg9qq9gqq9q9q = new ArrayList();

    public boolean gqg9qq9gqq9q9q(String str, String str2, int i) {
        return this.gqg9qq9gqq9q9q.add(new qq9gq9g9g99(this, str, str2, i));
    }

    public String gqg9qq9gqq9q9q() {
        JSONArray jSONArray = new JSONArray();
        for (qq9gq9g9g99 gqg9qq9gqq9q9q2 : this.gqg9qq9gqq9q9q) {
            jSONArray.put(gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q());
        }
        return jSONArray.toString();
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
            byte b = (byte) (i ^ 9);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.BUSINESS_ORDER_TYPE);
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
