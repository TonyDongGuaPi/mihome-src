package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class qqg9qgqqqgqqgg9qqqqq {
    private static final String g999gqq9ggqgqq = gqg9qq9gqq9q9q("784e610d2b1c311e354535", 100);
    private static final String g9q9q9g9 = gqg9qq9gqq9q9q("7372606270253d293f6a62747c716f6674697b6379766470657d697668", 93);
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("784e610d0c22092e02342311103d1b3c", 100);
    private static final String gqgqgqq9gq9q9q9 = gqg9qq9gqq9q9q("78656126020a0d0305", 79);
    private static final String q9gqqq99999qq = gqg9qq9gqq9q9q("782a61693b74337b3077216e266735723d356a3f693b", 0);
    private static final String q9q99gq99gggqg9qqqgg = gqg9qq9gqq9q9q("78736130223c3a2c323d32", 89);
    private static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("781c615f2a42334e2e522719781f64", 54);
    private static final String qgg99qqg9gq = gqg9qq9gqq9q9q("781b615827453d533148", 49);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("76512606325e3641795079", 127);
    private static final String qgggqg999gg9qqggq = gqg9qq9gqq9q9q("786061232a3e33322e2e27656a6e6b69642620252c2d20", 74);
    private static final qqg9qgqqqgqqgg9qqqqq qq9gq9g9g99 = new qqg9qgqqqgqqgg9qqqqq();
    private static final String qq9q9ggg = gqg9qq9gqq9q9q("7849610a0c2509290233351f3e1e", 99);
    private static final String qqq9gg9gqq9qgg99q = gqg9qq9gqq9q9q("6240724b7e06351b2c17310b38406f56645d685f795f7a", 112);
    private List qqg9qgqqqgqqgg9qqqqq;

    public static qqg9qgqqqgqqgg9qqqqq gqg9qq9gqq9q9q() {
        return qq9gq9g9g99;
    }

    private qqg9qgqqqgqqgg9qqqqq() {
        this.qqg9qgqqqgqqgg9qqqqq = null;
        this.qqg9qgqqqgqqgg9qqqqq = new ArrayList();
        this.qqg9qgqqqgqqgg9qqqqq.add(gqg9qq9gqq9q9q);
        this.qqg9qgqqqgqqgg9qqqqq.add(qgg9qgg9999g9g);
        this.qqg9qgqqqgqqgg9qqqqq.add(q9qq99qg9qqgqg9gqgg9);
        this.qqg9qgqqqgqqgg9qqqqq.add(q9gqqq99999qq);
        this.qqg9qgqqqgqqgg9qqqqq.add(g9q9q9g9);
        this.qqg9qgqqqgqqgg9qqqqq.add(qqq9gg9gqq9qgg99q);
        this.qqg9qgqqqgqqgg9qqqqq.add(q9q99gq99gggqg9qqqgg);
        this.qqg9qgqqqgqqgg9qqqqq.add(g999gqq9ggqgqq);
        this.qqg9qgqqqgqqgg9qqqqq.add(gqgqgqq9gq9q9q9);
        this.qqg9qgqqqgqqgg9qqqqq.add(qgg99qqg9gq);
        this.qqg9qgqqqgqqgg9qqqqq.add(qq9q9ggg);
        this.qqg9qgqqqgqqgg9qqqqq.add(qgggqg999gg9qqggq);
    }

    public String gqg9qq9gqq9q9q(PackageManager packageManager) {
        JSONArray jSONArray = new JSONArray();
        if (this.qqg9qgqqqgqqgg9qqqqq != null) {
            int size = this.qqg9qgqqqgqqgg9qqqqq.size();
            for (int i = 0; i < size; i++) {
                if (q9gqqq99999qq.gqg9qq9gqq9q9q(packageManager, (String) this.qqg9qgqqqgqqgg9qqqqq.get(i)) != null) {
                    jSONArray.put(i);
                }
            }
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
            byte b = (byte) (i ^ 38);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 27);
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
