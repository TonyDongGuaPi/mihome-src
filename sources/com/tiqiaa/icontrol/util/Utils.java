package com.tiqiaa.icontrol.util;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.imi.fastjson.JSON;
import com.taobao.weex.el.parse.Operators;
import com.tiqiaa.util.NetUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import miuipub.reflect.Field;

public class Utils {
    private static final String TAG = "ICONTROL_Utils";
    public static byte checkKey = 87;
    private static char[] numbersAndLetters;
    private static Random randGen;

    public static byte getCheckRs(byte[] bArr) {
        return 0;
    }

    public static byte[] getObjectBytes(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object clone(byte[] bArr) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object clone(Object obj) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(getObjectBytes(obj)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr != null) {
            for (byte hexString : bArr) {
                String hexString2 = Integer.toHexString(hexString);
                if (hexString2.length() < 2) {
                    stringBuffer.append("0" + hexString2);
                } else if (hexString2.length() > 2) {
                    stringBuffer.append(hexString2.substring(hexString2.length() - 2));
                } else {
                    stringBuffer.append(hexString2);
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String hexString(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        int length = bArr.length;
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START);
        sb.append(length);
        sb.append(Operators.ARRAY_END);
        sb.append(Operators.ARRAY_START);
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            byte b = bArr[i] & 255;
            sb.append("0123456789abcdef".charAt(b >> 4));
            sb.append("0123456789abcdef".charAt(b & 15));
        }
        sb.append(Operators.ARRAY_END);
        return sb.toString();
    }

    public static String intString(int[] iArr) {
        return iArr == null ? "null" : Arrays.toString(iArr);
    }

    public static byte[] fromHex(String str) {
        if (str.length() % 2 == 1) {
            str = "0" + str;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static String bytes2str(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR);
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if (i < bArr.length - 1) {
                sb.append(String.valueOf(b) + ",");
            } else {
                sb.append(b);
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public static final String randomString(int i) {
        if (i < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(cArr);
    }

    public static int getStringLengthSmall(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            i = (charAt < 19968 || charAt > 40891) ? i + 10 : i + 15;
        }
        return i;
    }

    public static int getStringLengthBig(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            i = (charAt < 19968 || charAt > 40891) ? i + 1 : i + 2;
        }
        return i;
    }

    public static String clearDBStr(String str) {
        if (str == null) {
            return "";
        }
        String trim = str.trim();
        char[] cArr = {Operators.SINGLE_QUOTE};
        StringBuilder sb = new StringBuilder();
        for (char c : trim.toCharArray()) {
            if (!inCharArray(c, cArr)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static boolean inCharArray(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static List<byte[]> getBytesFromFile(String str) throws IOException {
        return getBytesFromFile(new File(str));
    }

    public static List<byte[]> getBytesFromFile(File file) throws IOException {
        long j;
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        FileInputStream fileInputStream = new FileInputStream(file);
        long length = file.length();
        long j2 = (long) 256;
        if (length % j2 == 0) {
            j = length / j2;
        } else {
            j = (length / j2) + 1;
        }
        for (int i = 0; ((long) i) < j; i++) {
            byte[] bArr = new byte[256];
            fileInputStream.read(bArr, 0, 256);
            arrayList.add(packData(bArr));
        }
        fileInputStream.close();
        return arrayList;
    }

    private static byte[] packData(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[(length + 5)];
        bArr2[0] = Constants.TagName.USER_LOGIN_FAIL_COUNT;
        bArr2[1] = Constants.TagName.QUERY_RECORD_COUNT;
        System.arraycopy(bArr, 0, bArr2, 2, length);
        bArr2[length + 2] = getCheckByte(bArr);
        bArr2[length + 3] = Framer.ENTER_FRAME_PREFIX;
        bArr2[length + 4] = 64;
        return bArr2;
    }

    private static byte getCheckByte(byte[] bArr) {
        byte b = 0;
        for (byte b2 : bArr) {
            b = (byte) (b ^ b2);
        }
        return b;
    }

    public static String getSizeStr(int i) {
        if (i > 1024) {
            int i2 = i / 1024;
            if (i2 > 1024) {
                return String.valueOf(i2 / 1024) + "." + (((i2 % 1024) * 1000) / 1024) + "M";
            }
            return String.valueOf(i2) + "K";
        }
        return String.valueOf(i) + Field.b;
    }

    public static UUID bytesToUUID(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
            if (i == 3 || i == 5 || i == 7 || i == 9) {
                sb.append("-");
            }
        }
        return UUID.fromString(sb.toString());
    }

    public static byte[] uuidToBinary(UUID uuid) {
        String replace = uuid.toString().replace("-", "");
        if (replace.length() % 2 == 1) {
            replace = "0" + replace;
        }
        byte[] bArr = new byte[(replace.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(replace.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static void sortArr(int[] iArr) {
        for (int i = 0; i < iArr.length - 1; i++) {
            int i2 = i;
            while (i2 < iArr.length - 1) {
                int i3 = i2 + 1;
                if (iArr[i2] > iArr[i3]) {
                    int i4 = iArr[i3];
                    iArr[i3] = iArr[i2];
                    iArr[i2] = i4;
                }
                i2 = i3;
            }
        }
    }

    public static List<Integer> disintegrateChannelNum(int i) {
        ArrayList arrayList = new ArrayList();
        if (i >= 100) {
            arrayList.add(Integer.valueOf(i / 100));
        }
        if (i >= 10) {
            arrayList.add(Integer.valueOf((i % 100) / 10));
        }
        arrayList.add(Integer.valueOf((i % 100) % 10));
        return arrayList;
    }

    public static byte[] intToBytes2(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (24 - (i2 * 8)));
        }
        return bArr;
    }

    public static byte[] shortToByteArray(short s) {
        byte[] bArr = new byte[2];
        for (int i = 0; i < 2; i++) {
            bArr[i] = (byte) ((s >>> (((bArr.length - 1) - i) * 8)) & 255);
        }
        return bArr;
    }

    public static String filteExtraBlackSpace(String str) {
        if (str == null) {
            return null;
        }
        if (str.trim().equals("")) {
            return "";
        }
        int indexOf = str.indexOf("\n");
        if (indexOf == 0) {
            LogUtil.e(TAG, "filteExtraBlackSpace....开始就是“\n”.");
            str = str.substring(1);
        } else if (indexOf > 0 && str.substring(0, indexOf).replace("\n", "").trim().equals("")) {
            LogUtil.e(TAG, "filteExtraBlackSpace...###.开始是  空格+“\n”");
            str = str.substring(indexOf + 1);
        }
        return str.replace("\n\n", "\n");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getLocalResolution(android.content.Context r3) {
        /*
            android.util.DisplayMetrics r0 = new android.util.DisplayMetrics
            r0.<init>()
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            r0 = 0
            int r1 = r3.widthPixels     // Catch:{ Exception -> 0x0015 }
            int r3 = r3.heightPixels     // Catch:{ Exception -> 0x0013 }
            goto L_0x001b
        L_0x0013:
            r3 = move-exception
            goto L_0x0017
        L_0x0015:
            r3 = move-exception
            r1 = 0
        L_0x0017:
            r3.printStackTrace()
            r3 = 0
        L_0x001b:
            if (r1 >= r3) goto L_0x001f
            r0 = r1
            goto L_0x0020
        L_0x001f:
            r0 = r3
        L_0x0020:
            if (r1 <= r3) goto L_0x0023
            r3 = r1
        L_0x0023:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r0)
            java.lang.String r0 = "x"
            r1.append(r0)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            java.lang.String r0 = "ICONTROL_Utils"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getLocalResolution........获取本地分辨率标记..........resolution = "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.i(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.icontrol.util.Utils.getLocalResolution(android.content.Context):java.lang.String");
    }

    public static <T> T JsonParseObject(String str, Class<T> cls) {
        try {
            return JSON.parseObject(NetUtil.decode(TiqiaaService.getAppContext(), str), cls);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }
}
