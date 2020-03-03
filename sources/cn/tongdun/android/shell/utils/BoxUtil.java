package cn.tongdun.android.shell.utils;

import android.os.Parcelable;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.shell.FMAgent;
import cn.tongdun.android.shell.common.CollectorError;
import java.net.HttpURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BoxUtil {
    private static final String PLACEHOLDER = linkxxxxx("1a20", 55);
    private static final String PLACEHOLDER2 = "";
    public static final int Tag = 789123456;
    public static long stamp = 0;

    public static native void box1(int i, byte[] bArr);

    public static native double box10(double d);

    public static native Parcelable box11(Parcelable parcelable);

    public static native Parcelable box12(Parcelable parcelable);

    public static native FMAgent box13(int i);

    public static native void box14(String str, int i);

    public static native char[] box15(String str);

    public static native byte[] box2(String str);

    public static native int box3(int i);

    public static native long box4(long j);

    public static native String box5(long j);

    public static native boolean box6(int i);

    public static native String box7();

    public static native void box8(byte[] bArr);

    public static native float box9(float f, int i);

    public static native String getdata(HttpURLConnection httpURLConnection, byte[] bArr);

    public static native String mixup(String str);

    public static String limitBox(JSONObject jSONObject, int i) throws JSONException {
        if ((jSONObject.toString().length() * 4) / 3 < i) {
            return jSONObject.toString();
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject(linkxxxxx("272a6537781a4e1d4900", 42));
        JSONArray jSONArray = new JSONArray(jSONObject2.getString(linkxxxxx("2750654d78604a7e5e", 80)));
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
            jSONObject3.put(CollectorError.KEY_REASON, "");
            jSONArray2.put(jSONObject3);
        }
        jSONObject2.put(linkxxxxx("2731652c78014a1f5e", 49), jSONArray2);
        jSONObject.put(linkxxxxx("275a6547786a4e6d4970", 90), jSONObject2);
        if ((jSONObject.toString().length() * 4) / 3 > i) {
            jSONObject2.put(linkxxxxx("2777656a78474a595e", 119), "");
            jSONObject2.put(linkxxxxx("266877777d71", 126), "");
            jSONObject.put(linkxxxxx("2717650a78274e20493d", 23), jSONObject2);
        }
        return jSONObject.toString();
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
            byte b = (byte) (i ^ 23);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.INVOICE_TOKEN);
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
