package cn.tongdun.android.core.q9gqqq99999qq;

import org.json.JSONArray;

public class qgg9qgg9999g9g extends gqg9qq9gqq9q9q {
    private int gqg9qq9gqq9q9q;
    private JSONArray qgg9qgg9999g9g = new JSONArray();

    public void gqg9qq9gqq9q9q(int i, int... iArr) {
        if (iArr[0] != Integer.MAX_VALUE) {
            this.gqg9qq9gqq9q9q = i;
            if (i == 0 && iArr.length == 6) {
                this.qgg9qgg9999g9g.put(new g9q9q9g9(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5]).gqg9qq9gqq9q9q());
            } else if (i == 0 && iArr.length == 3) {
                this.qgg9qgg9999g9g.put(new g9q9q9g9(this, -1, iArr[0], iArr[1], 0, 0, iArr[2]).gqg9qq9gqq9q9q());
            } else if (i == 1 && iArr.length == 6) {
                this.qgg9qgg9999g9g.put(new q9qq99qg9qqgqg9gqgg9(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5]).gqg9qq9gqq9q9q());
            } else if (i == 2 && iArr.length == 6) {
                this.qgg9qgg9999g9g.put(new qqq9gg9gqq9qgg99q(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5]).gqg9qq9gqq9q9q());
            } else if (i == 2 && iArr.length == 3) {
                this.qgg9qgg9999g9g.put(new qqq9gg9gqq9qgg99q(this, -1, iArr[0], iArr[1], 0, 0, iArr[2]).gqg9qq9gqq9q9q());
            } else if (i == 3 && iArr.length == 6) {
                this.qgg9qgg9999g9g.put(new q9q99gq99gggqg9qqqgg(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5]).gqg9qq9gqq9q9q());
            } else if (i == 3 && iArr.length == 3) {
                this.qgg9qgg9999g9g.put(new q9q99gq99gggqg9qqqgg(this, -1, iArr[0], iArr[1], 0, 0, iArr[2]).gqg9qq9gqq9q9q());
            } else if (i == 0 && iArr.length == 5) {
                this.qgg9qgg9999g9g.put(new g9q9q9g9(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            } else if (i == 0 && iArr.length == 2) {
                this.qgg9qgg9999g9g.put(new g9q9q9g9(this, -1, iArr[0], iArr[1], 0, 0, Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            } else if (i == 1 && iArr.length == 5) {
                this.qgg9qgg9999g9g.put(new q9qq99qg9qqgqg9gqgg9(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            } else if (i == 2 && iArr.length == 5) {
                this.qgg9qgg9999g9g.put(new qqq9gg9gqq9qgg99q(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            } else if (i == 2 && iArr.length == 2) {
                this.qgg9qgg9999g9g.put(new qqq9gg9gqq9qgg99q(this, -1, iArr[0], iArr[1], 0, 0, Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            } else if (i == 3 && iArr.length == 5) {
                this.qgg9qgg9999g9g.put(new q9q99gq99gggqg9qqqgg(this, iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            } else if (i == 3 && iArr.length == 2) {
                this.qgg9qgg9999g9g.put(new q9q99gq99gggqg9qqqgg(this, -1, iArr[0], iArr[1], 0, 0, Integer.MIN_VALUE).gqg9qq9gqq9q9q());
            }
        }
    }

    public String gqg9qq9gqq9q9q() {
        return this.qgg9qgg9999g9g.toString();
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
            byte b = (byte) (i ^ 22);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 30);
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
