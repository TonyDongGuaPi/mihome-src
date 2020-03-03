package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.PrintStream;
import java.lang.reflect.Array;

public class MAC {
    private static FMLog log = LogFactory.getInstance().getLog();

    public static byte[] MAC4DESNoPadding(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr3 == null) {
            log.error(MAC.class.getName(), "MAC计算时，数据为null");
            return null;
        } else if (bArr3.length % 8 != 0) {
            log.error(MAC.class.getName(), "MAC计算时，数据长度不是8的倍数");
            return null;
        } else {
            if (bArr2 == null) {
                bArr2 = new byte[8];
            }
            int length = bArr3.length / 8;
            byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, new int[]{length, 8});
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < 8; i2++) {
                    bArr4[i][i2] = bArr3[(i * 8) + i2];
                }
            }
            byte[] xor = FM_Bytes.xor(bArr2, bArr4[0]);
            for (int i3 = 1; i3 < length; i3++) {
                xor = FM_Bytes.xor(DES.encrypt4des(bArr, xor), bArr4[i3]);
            }
            return DES.encrypt4des(bArr, xor);
        }
    }

    public static byte[] calculateMAC4DES(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[8];
        bArr4[0] = Byte.MIN_VALUE;
        if (bArr3 == null) {
            log.error(MAC.class.getName(), "MAC计算时，数据为null");
        }
        if (bArr2 == null) {
            bArr2 = new byte[8];
        }
        byte[] join = FM_Bytes.join(bArr3, FM_Bytes.copyOf(bArr4, bArr3.length % 8 != 0 ? 8 - (bArr3.length % 8) : 8));
        int length = join.length / 8;
        byte[][] bArr5 = (byte[][]) Array.newInstance(byte.class, new int[]{length, 8});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr5[i][i2] = join[(i * 8) + i2];
            }
        }
        byte[] xor = FM_Bytes.xor(bArr2, bArr5[0]);
        for (int i3 = 1; i3 < length; i3++) {
            xor = FM_Bytes.xor(DES.encrypt4des(bArr, xor), bArr5[i3]);
        }
        return DES.encrypt4des(bArr, xor);
    }

    public static byte[] calculateMAC4DES3(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[8];
        bArr4[0] = Byte.MIN_VALUE;
        if (bArr3 == null) {
            log.error(MAC.class.getName(), "MAC计算时，数据为null");
        }
        if (bArr2 == null) {
            bArr2 = new byte[8];
        }
        byte[] join = FM_Bytes.join(bArr3, FM_Bytes.copyOf(bArr4, bArr3.length % 8 != 0 ? 8 - (bArr3.length % 8) : 8));
        int length = join.length / 8;
        byte[][] bArr5 = (byte[][]) Array.newInstance(byte.class, new int[]{length, 8});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr5[i][i2] = join[(i * 8) + i2];
            }
        }
        byte[] xor = FM_Bytes.xor(bArr2, bArr5[0]);
        for (int i3 = 1; i3 < length; i3++) {
            xor = FM_Bytes.xor(DES.encrypt4des(bArr, xor), bArr5[i3]);
        }
        return DES.encrypt4des3(bArr, xor);
    }

    public static byte[] calculateMAC4DES3AN919(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[8];
        if (bArr3 == null) {
            log.error(MAC.class.getName(), "MAC计算时，数据为null");
        }
        if (bArr2 == null) {
            bArr2 = new byte[8];
        }
        byte[] join = FM_Bytes.join(bArr3, FM_Bytes.copyOf(bArr4, bArr3.length % 8 != 0 ? 8 - (bArr3.length % 8) : 0));
        int length = join.length / 8;
        byte[][] bArr5 = (byte[][]) Array.newInstance(byte.class, new int[]{length, 8});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr5[i][i2] = join[(i * 8) + i2];
            }
        }
        byte[] xor = FM_Bytes.xor(bArr2, bArr5[0]);
        for (int i3 = 1; i3 < length; i3++) {
            xor = FM_Bytes.xor(DES.encrypt4des(bArr, xor), bArr5[i3]);
        }
        return DES.encrypt4des3(bArr, xor);
    }

    public static void main(String[] strArr) {
        byte[] bArr = new byte[16];
        bArr[1] = Constants.TagName.PREDEPOSIT_INFO;
        bArr[2] = Constants.TagName.USER_LOCK_TIME;
        bArr[3] = Constants.TagName.MAIN_ORDER;
        bArr[4] = -86;
        bArr[5] = Constants.TagName.TERMINAL_BACK_CHILDREN_ID;
        bArr[6] = Constants.TagName.ELECTRONIC_STARTTIME;
        bArr[7] = Constants.TagName.ORDER_TYPE;
        bArr[8] = Constants.TagName.ORDER_BRIEF_INFO_LIST;
        bArr[9] = -12;
        bArr[10] = Constants.TagName.PREDEPOSIT_LIST;
        bArr[11] = Constants.TagName.BUSINESS_ORDER_TYPE;
        bArr[12] = Constants.TagName.TERMINAL_BACK_INFO_LIST;
        bArr[13] = Constants.TagName.PAY_ORDER;
        bArr[14] = -73;
        bArr[15] = 24;
        byte[] calculateMAC4DES3AN919 = calculateMAC4DES3AN919(FM_Bytes.hexStringToBytes("561F07C68A1C36F504D29C854B4C6B82"), new byte[8], bArr);
        PrintStream printStream = System.out;
        printStream.println("mac:" + FM_Bytes.bytesToHexString(calculateMAC4DES3AN919));
    }
}
