package com.xiaomi.chatbot.speechsdk.common;

import android.content.Context;
import com.google.gson.Gson;
import com.xiaomi.youpin.network.annotation.Encoding;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class Utils {
    public static final Gson GSON = new Gson();
    private static final int MAX_GENERATE_COUNT = 9999;
    private static final String TAG = "Utils";
    private static volatile int generateCount = 0;

    public static void asserts(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    public static <T> T notNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " should not be null!");
    }

    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNumberString(String str) {
        return str.matches("-?[0-9]+");
    }

    public static boolean isContainChinese(String str) {
        return Pattern.compile("[一-龥]").matcher(str).find();
    }

    public static boolean StringIsURL(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^((https|http|ftp|rtsp|mms)?://)+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.[a-z]{2,6})(:[0-9]{1,5})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
    }

    public static int getFloatArrayListLength(ArrayList<float[]> arrayList) {
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            float[] fArr = arrayList.get(i2);
            if (fArr != null) {
                i += fArr.length;
            }
        }
        return i;
    }

    public static float[] combineFloatArrayList(ArrayList<float[]> arrayList) {
        Iterator<float[]> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            float[] next = it.next();
            if (next != null) {
                i += next.length;
            }
        }
        if (i <= 0) {
            return null;
        }
        float[] fArr = new float[i];
        if (i != 0) {
            Iterator<float[]> it2 = arrayList.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                float[] next2 = it2.next();
                if (next2 != null) {
                    System.arraycopy(next2, 0, fArr, i2, next2.length);
                    i2 += next2.length;
                }
            }
        }
        return fArr;
    }

    public static int getByteArrayListLength(ArrayList<byte[]> arrayList) {
        Iterator<byte[]> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            if (next != null) {
                i += next.length;
            }
        }
        return i;
    }

    public static byte[] combineByteArrayList(ArrayList<byte[]> arrayList) {
        Iterator<byte[]> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            if (next != null) {
                i += next.length;
            }
        }
        if (i <= 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        if (i != 0) {
            Iterator<byte[]> it2 = arrayList.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                byte[] next2 = it2.next();
                if (next2 != null) {
                    System.arraycopy(next2, 0, bArr, i2, next2.length);
                    i2 += next2.length;
                }
            }
        }
        return bArr;
    }

    public static byte[] combineByteLinkedList(LinkedList<byte[]> linkedList) {
        Iterator it = linkedList.iterator();
        int i = 0;
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            if (bArr != null) {
                i += bArr.length;
            }
        }
        if (i <= 0) {
            return null;
        }
        byte[] bArr2 = new byte[i];
        if (i != 0) {
            int i2 = 0;
            while (true) {
                try {
                    byte[] removeFirst = linkedList.removeFirst();
                    if (removeFirst == null) {
                        break;
                    }
                    System.arraycopy(removeFirst, 0, bArr2, i2, removeFirst.length);
                    i2 += removeFirst.length;
                } catch (NoSuchElementException unused) {
                }
            }
        }
        return bArr2;
    }

    public static byte[] combineByteArrayList(ArrayList<byte[]> arrayList, byte[] bArr, int i, int i2) {
        Iterator<byte[]> it = arrayList.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            if (next != null) {
                i3 += next.length;
            }
        }
        if (i3 <= 0) {
            return null;
        }
        if (i3 != 0) {
            int i4 = i;
            int i5 = 0;
            while (i4 <= i && i4 < arrayList.size()) {
                byte[] bArr2 = arrayList.get(i4);
                if (bArr2 != null) {
                    System.arraycopy(bArr2, 0, bArr, i5, bArr2.length);
                    i5 += bArr2.length;
                }
                i4++;
            }
        }
        return bArr;
    }

    public static boolean resetByteArray(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= i + i2) {
            return false;
        }
        while (i < i2) {
            bArr[i] = 0;
            i++;
        }
        return true;
    }

    public static void SleepCatchException(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean IsValidPcm(byte[] bArr) {
        return bArr == null || bArr.length % 2 == 0;
    }

    public static boolean LoadJniLibraryCatchException(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int GetWavVolume(byte[] bArr) {
        long j = 0;
        for (int i = 0; i < bArr.length; i++) {
            j += (long) (bArr[i] * bArr[i]);
        }
        double d = (double) j;
        double length = (double) bArr.length;
        Double.isNaN(d);
        Double.isNaN(length);
        double log10 = Math.log10(d / length) * 10.0d;
        if (log10 <= 30.0d) {
            log10 = 30.0d;
        } else if (log10 >= 90.0d) {
            log10 = 90.0d;
        }
        return (int) (((log10 - 30.0d) * 100.0d) / 60.0d);
    }

    public static String toUtf8(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getUniqueId() {
        if (generateCount > 9999) {
            generateCount = 0;
        }
        long parseLong = (Long.parseLong(Long.toString(System.currentTimeMillis()).substring(4)) * 10000) + ((long) generateCount);
        generateCount++;
        return parseLong;
    }

    public static String getUtf8StringFromGbkArray(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, Encoding.GBK);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    static boolean isUnitValueValid(String str, String str2) {
        if (isEmptyString(str) || isEmptyString(str2) || !str.contains(str2) || str.indexOf(str2) == 0) {
            return false;
        }
        return true;
    }

    public static byte[] getGbkArrayFromString(String str) {
        try {
            return str.getBytes(Encoding.GBK);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static byte[] getFromSD(Context context, String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getFromResRaw(Context context, String str) {
        try {
            InputStream openRawResource = context.getResources().openRawResource(Integer.valueOf(str).intValue());
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            openRawResource.close();
            return bArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getFromAssets(Context context, String str) {
        try {
            InputStream open = context.getResources().getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return bArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void writeAppFile(Context context, String str, String str2) {
        writeAppFile(context, str, str2.getBytes());
    }

    public static void writeAppFile(Context context, String str, byte[] bArr) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            try {
                openFileOutput.write(bArr);
                openFileOutput.flush();
                openFileOutput.close();
            } catch (IOException e) {
                SpeechLog.printException("Utils", e);
            }
        } catch (FileNotFoundException e2) {
            SpeechLog.printException("Utils", e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0034 A[SYNTHETIC, Splitter:B:22:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005a A[Catch:{ all -> 0x0022 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0061 A[SYNTHETIC, Splitter:B:34:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067 A[SYNTHETIC, Splitter:B:38:0x0067] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readAppFile(android.content.Context r4, java.lang.String r5, boolean r6) {
        /*
            r0 = 0
            java.io.FileInputStream r4 = r4.openFileInput(r5)     // Catch:{ FileNotFoundException -> 0x003f, Exception -> 0x002b, all -> 0x0028 }
            int r1 = r4.available()     // Catch:{ FileNotFoundException -> 0x0026, Exception -> 0x0024 }
            byte[] r1 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x0026, Exception -> 0x0024 }
            r4.read(r1)     // Catch:{ FileNotFoundException -> 0x0026, Exception -> 0x0024 }
            java.lang.String r2 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0026, Exception -> 0x0024 }
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)     // Catch:{ FileNotFoundException -> 0x0026, Exception -> 0x0024 }
            if (r4 == 0) goto L_0x0021
            r4.close()     // Catch:{ IOException -> 0x001b }
            goto L_0x0021
        L_0x001b:
            r4 = move-exception
            java.lang.String r5 = "Utils"
            com.xiaomi.chatbot.speechsdk.common.SpeechLog.printException(r5, r4)
        L_0x0021:
            return r2
        L_0x0022:
            r5 = move-exception
            goto L_0x0065
        L_0x0024:
            r5 = move-exception
            goto L_0x002d
        L_0x0026:
            r1 = move-exception
            goto L_0x0041
        L_0x0028:
            r5 = move-exception
            r4 = r0
            goto L_0x0065
        L_0x002b:
            r5 = move-exception
            r4 = r0
        L_0x002d:
            java.lang.String r6 = "Utils"
            com.xiaomi.chatbot.speechsdk.common.SpeechLog.printException(r6, r5)     // Catch:{ all -> 0x0022 }
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0064
        L_0x0038:
            r4 = move-exception
            java.lang.String r5 = "Utils"
            com.xiaomi.chatbot.speechsdk.common.SpeechLog.printException(r5, r4)
            goto L_0x0064
        L_0x003f:
            r1 = move-exception
            r4 = r0
        L_0x0041:
            if (r6 == 0) goto L_0x005a
            java.lang.String r6 = "Utils"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
            r1.<init>()     // Catch:{ all -> 0x0022 }
            java.lang.String r2 = "startup not find file:"
            r1.append(r2)     // Catch:{ all -> 0x0022 }
            r1.append(r5)     // Catch:{ all -> 0x0022 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0022 }
            com.xiaomi.chatbot.speechsdk.common.SpeechLog.i(r6, r5)     // Catch:{ all -> 0x0022 }
            goto L_0x005f
        L_0x005a:
            java.lang.String r5 = "Utils"
            com.xiaomi.chatbot.speechsdk.common.SpeechLog.printException(r5, r1)     // Catch:{ all -> 0x0022 }
        L_0x005f:
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0064:
            return r0
        L_0x0065:
            if (r4 == 0) goto L_0x0071
            r4.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0071
        L_0x006b:
            r4 = move-exception
            java.lang.String r6 = "Utils"
            com.xiaomi.chatbot.speechsdk.common.SpeechLog.printException(r6, r4)
        L_0x0071:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.chatbot.speechsdk.common.Utils.readAppFile(android.content.Context, java.lang.String, boolean):java.lang.String");
    }
}
