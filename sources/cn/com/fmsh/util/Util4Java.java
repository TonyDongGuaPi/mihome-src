package cn.com.fmsh.util;

import com.miui.tsmclient.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util4Java {
    private static final int[] model;

    static {
        int[] iArr = new int[8];
        iArr[1] = 1;
        iArr[2] = 3;
        iArr[3] = 7;
        iArr[4] = 15;
        iArr[5] = 31;
        iArr[6] = 63;
        iArr[7] = 127;
        model = iArr;
    }

    public static String getExceptionInfo(Exception exc) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        exc.printStackTrace(printStream);
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        try {
            printStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream2;
        } catch (IOException unused) {
            return "";
        }
    }

    public static int String2Int(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception unused) {
            return i;
        }
    }

    public static byte String2Byte(String str, byte b) {
        try {
            return Byte.parseByte(str, 16);
        } catch (Exception unused) {
            return b;
        }
    }

    public static String date2string(Date date, String str) {
        if (date == null) {
            date = new Date();
        }
        if (str == null) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str).format(date);
    }

    public static String date2string(String str) {
        return date2string((Date) null, str);
    }

    public static String getCurrentTime() {
        return date2string((Date) null, StringUtils.EXPECT_TIME_FORMAT);
    }

    public static int getBitNumber(byte b, int i, int i2) {
        int i3;
        if (i2 > 8 || i2 < 0 || (i3 = i - i2) < 0) {
            return -1;
        }
        return (b >> i3) & model[i2];
    }

    public static void main(String[] strArr) {
        int String2Int = String2Int(" 1 ", -2);
        PrintStream printStream = System.out;
        printStream.println("result:" + String2Int);
    }
}
