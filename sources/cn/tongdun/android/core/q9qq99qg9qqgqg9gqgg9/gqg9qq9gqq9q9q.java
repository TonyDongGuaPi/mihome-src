package cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9;

import android.os.Build;
import android.util.Base64;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class gqg9qq9gqq9q9q {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("663357", 67);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("5268672372", 29);

    public static byte[] gqg9qq9gqq9q9q(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, gqg9qq9gqq9q9q);
            Cipher instance = Cipher.getInstance(gqg9qq9gqq9q9q("6623575f3d593c34432f4b3f2d5a1c5f1c521b5b", 83));
            instance.init(1, secretKeySpec);
            return qgg9qgg9999g9g.gqg9qq9gqq9q9q(instance.doFinal(bArr)).getBytes(qgg9qgg9999g9g);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] qgg9qgg9999g9g(byte[] bArr, byte[] bArr2) throws Exception {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, gqg9qq9gqq9q9q);
        Cipher instance = Cipher.getInstance(gqg9qq9gqq9q9q("662857543d523c3f43244b342d511c541c591b50", 88));
        instance.init(2, secretKeySpec);
        return instance.doFinal(qgg9qgg9999g9g.gqg9qq9gqq9q9q(new String(bArr, qgg9qgg9999g9g)));
    }

    public static String gqg9qq9gqq9q9q(String str, String str2) {
        SecureRandom secureRandom;
        if (str == null || str2 == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return gqg9qq9gqq9q9q("49637537262120372720", 22);
        }
        try {
            byte[] decode = Base64.decode(str2, 0);
            if (decode == null) {
                return null;
            }
            KeyGenerator instance = KeyGenerator.getInstance(gqg9qq9gqq9q9q);
            if (gqgqgqq9gq9q9q9.qgg9qgg9999g9g(17)) {
                secureRandom = SecureRandom.getInstance(gqg9qq9gqq9q9q("74455a353b37273e", 42), gqg9qq9gqq9q9q("641148184c03", 84));
            } else {
                secureRandom = SecureRandom.getInstance(gqg9qq9gqq9q9q("74385a483b4a2743", 87));
            }
            secureRandom.setSeed(decode);
            instance.init(128, secureRandom);
            Cipher instance2 = Cipher.getInstance(gqg9qq9gqq9q9q("665357", 35));
            instance2.init(2, (SecretKeySpec) instance.generateKey());
            return new String(instance2.doFinal(qgg9qgg9999g9g.gqg9qq9gqq9q9q(str)), qgg9qgg9999g9g);
        } catch (Exception unused) {
            return "";
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
            byte b = (byte) (i ^ 116);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 39);
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
