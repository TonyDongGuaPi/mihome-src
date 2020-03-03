package cn.tongdun.android.shell.common;

import android.content.Context;
import cn.tongdun.android.shell.common.CollectorError;
import cn.tongdun.android.shell.utils.LogUtil;
import org.json.JSONObject;

public class HelperJNI {
    public static final int Tag = 31323334;

    public static native String base64encode(byte[] bArr);

    public static native Class dexLoad(Context context, byte[] bArr, int i, String str, String str2);

    public static native String err0r(String str, String str2);

    public static native String fpic5(Context context);

    public static native String getBaseBand();

    public static native byte[] getData2(String str);

    public static native void n0(int i, int i2);

    public static native String n1();

    public static native void setContext(Context context);

    public static native String shooted();

    public static native String simplemd5(byte[] bArr);

    public static native String[] td_listfiles(String str, int i);

    public static native String tdread(String str, int i, int i2);

    public static native int tdwrite(String str, int i, int i2, String str2);

    static {
        try {
            System.loadLibrary(linkxxxxx("6a19751076016d", 108));
        } catch (Throwable th) {
            JSONObject catchErr = CollectorError.catchErr(th);
            LogUtil.err(linkxxxxx("5d35592c512618754c394f374a73196f4c", 119) + catchErr);
            CollectorError.addError(CollectorError.TYPE.ERROR_SO_LOAD, catchErr);
        }
    }

    public static void exprot(int i, int i2) {
        try {
            n0(i, i2);
        } catch (Throwable th) {
            CollectorError.addError(CollectorError.TYPE.ERROR_NATIVE_METHOD, CollectorError.catchErr(th));
        }
    }

    public static String linkxxxxx(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 110);
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
