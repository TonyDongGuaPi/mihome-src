package cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9;

import com.google.code.microlog4android.format.PatternFormatter;
import java.util.Random;

public class qgg9qgg9999g9g {
    public static String gqg9qq9gqq9q9q(int i) {
        char[] cArr = {'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        int i2 = 0;
        while (i2 < i) {
            int abs = (int) Math.abs((((long) random.nextInt()) + System.nanoTime()) % 36);
            if (abs >= 0 && abs < cArr.length) {
                stringBuffer.append(cArr[abs]);
                i2++;
            }
        }
        return stringBuffer.toString();
    }

    public static String gqg9qq9gqq9q9q(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] gqg9qq9gqq9q9q(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }
}
