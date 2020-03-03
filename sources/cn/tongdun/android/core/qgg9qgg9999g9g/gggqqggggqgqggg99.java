package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import cn.tongdun.android.core.O0o0o0o0o;
import cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q;
import java.io.File;
import java.io.FileInputStream;

public class gggqqggggqgqggg99 {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("0b0f4f1552045f", 123);
    private static final gggqqggggqgqggg99 q9gqqq99999qq = new gggqqggggqgqggg99();
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("71303e31232a2b", 14);
    private String q9qq99qg9qqgqg9gqgg9 = null;

    public static gggqqggggqgqggg99 gqg9qq9gqq9q9q() {
        return q9gqqq99999qq;
    }

    private gggqqggggqgqggg99() {
    }

    public String gqg9qq9gqq9q9q(Context context) {
        if (context == null) {
            return null;
        }
        if (this.q9qq99qg9qqgqg9gqgg9 == null) {
            this.q9qq99qg9qqgqg9gqgg9 = qgg9qgg9999g9g(context);
        }
        return this.q9qq99qg9qqgqg9gqgg9;
    }

    private String qgg9qgg9999g9g(Context context) {
        String q9qq99qg9qqgqg9gqgg92 = q9qq99qg9qqgqg9gqgg9(context);
        if (O0o0o0o0o.o0OOO0ooo0o == null || q9qq99qg9qqgqg9gqgg92 == null) {
            return null;
        }
        try {
            return new String(gqg9qq9gqq9q9q.qgg9qgg9999g9g(q9qq99qg9qqgqg9gqgg92.getBytes(gqg9qq9gqq9q9q("2a02674972", 103)), O0o0o0o0o.o0OOO0ooo0o.getBytes(gqg9qq9gqq9q9q("2a0f674472", 106))), gqg9qq9gqq9q9q("2a6c672772", 9));
        } catch (Exception unused) {
            return null;
        }
    }

    private static String q9qq99qg9qqgqg9gqgg9(Context context) {
        return gqg9qq9gqq9q9q(new File(context.getFilesDir().getAbsolutePath(), qgg9qgg9999g9g));
    }

    private static String gqg9qq9gqq9q9q(File file) {
        try {
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            if (read == -1) {
                return null;
            }
            return new String(bArr, 0, read, gqg9qq9gqq9q9q("2a60672b72", 5));
        } catch (Exception unused) {
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
            byte b = (byte) (i ^ 100);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 95);
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
