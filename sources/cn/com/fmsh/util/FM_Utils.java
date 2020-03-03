package cn.com.fmsh.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Character;

public class FM_Utils {
    private static String hexString = "0123456789ABCDEF";

    public static String intToHexString(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] intToBytes = FM_Bytes.intToBytes(i, i2);
        for (byte b : intToBytes) {
            stringBuffer.append(hexString.charAt((b & 240) >> 4));
            stringBuffer.append(hexString.charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    public static long hexStringToLong(String str) {
        int length = str.length();
        char[] charArray = str.toCharArray();
        long j = 0;
        for (int i = 0; i < length; i++) {
            int parseInt = Integer.parseInt(new StringBuilder(String.valueOf(charArray[i])).toString(), 16);
            for (int i2 = 0; i2 < (length - i) - 1 && parseInt != 0; i2++) {
                parseInt *= 16;
            }
            j += (long) parseInt;
        }
        return j;
    }

    public static String getExceptionInfo(Exception exc) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exc.printStackTrace(new PrintStream(byteArrayOutputStream));
        return byteArrayOutputStream.toString();
    }

    public static int String2Int(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static void exceptionHandle(Exception exc) {
        try {
            throw exc;
        } catch (Exception unused) {
        }
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.GENERAL_PUNCTUATION || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }
}
