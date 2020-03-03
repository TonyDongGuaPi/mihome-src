package cn.com.fmsh.tsm.business.card;

import cn.com.fmsh.util.FM_Bytes;

public class CardTools {
    public static String getFaceID4UID(byte[] bArr) {
        int i;
        if (bArr == null) {
            return null;
        }
        if (bArr.length < 1) {
            return null;
        }
        byte[] join = FM_Bytes.join(new byte[1], bArr);
        String str = new String("");
        int[] iArr = new int[20];
        long bytesToLong = FM_Bytes.bytesToLong(join);
        int i2 = 0;
        while (bytesToLong > 0) {
            iArr[i2] = (int) (bytesToLong % 10);
            bytesToLong /= 10;
            i2++;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if ((i4 & 1) == 0) {
                int i5 = iArr[i4] + iArr[i4];
                i3 += i5 % 10;
                i = i5 / 10;
            } else {
                i = iArr[i4];
            }
            i3 += i;
        }
        String str2 = String.valueOf(str) + ((10 - (i3 % 10)) % 10);
        long bytesToLong2 = FM_Bytes.bytesToLong(join);
        for (int i6 = 1; i6 < 11; i6 += 2) {
            int i7 = (int) (bytesToLong2 % 100);
            StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(str2) + (i7 / 10)));
            sb.append(i7 % 10);
            str2 = sb.toString();
            bytesToLong2 /= 100;
        }
        return str2;
    }

    public static String getFaceNo4uidByLnt(byte[] bArr) {
        return FM_Bytes.bytesToHexString(bArr);
    }
}
