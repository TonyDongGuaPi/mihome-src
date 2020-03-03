package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.core.O0o0o0o0o;
import cn.tongdun.android.core.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;
import cn.tongdun.android.core.q9gqqq99999qq.g999gqq9ggqgqq;
import cn.tongdun.android.core.q9gqqq99999qq.gqgqgqq9gq9q9q9;
import cn.tongdun.android.core.q9gqqq99999qq.qgg99qqg9gq;
import cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9.qgg9qgg9999g9g;
import cn.tongdun.android.shell.common.CollectorError;
import cn.tongdun.android.shell.common.HelperJNI;
import cn.tongdun.android.shell.utils.BoxUtil;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q9qq99qg9qqgqg9gqgg9 {
    public static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("1853614e7d476c56", 42);
    private static final String[] q9gqqq99999qq = {gqg9qq9gqq9q9q("2f0b55", 27), gqg9qq9gqq9q9q("2f7f54", 111), gqg9qq9gqq9q9q("2f6857", 120), gqg9qq9gqq9q9q("2f3356", 35), gqg9qq9gqq9q9q("2f1f55", 8), gqg9qq9gqq9q9q("2f1e51", 13), gqg9qq9gqq9q9q("2f0b5b", 28), gqg9qq9gqq9q9q("2f795a", 111), gqg9qq9gqq9q9q("2f695d", 113), gqg9qq9gqq9q9q("2f765a", 111), gqg9qq9gqq9q9q("2f445c", 92), gqg9qq9gqq9q9q("2f2a552b", 58), gqg9qq9gqq9q9q("2f775575", 103), gqg9qq9gqq9q9q("2f425541", 82)};
    private static final String[] q9qq99qg9qqgqg9gqgg9 = {gqg9qq9gqq9q9q("2f5259", 71), gqg9qq9gqq9q9q("2f0451", 22), gqg9qq9gqq9q9q("2f2c5c", 53), gqg9qq9gqq9q9q("2f0051", 20), gqg9qq9gqq9q9q("2f795e", 107), gqg9qq9gqq9q9q("2f655c65", 117), gqg9qq9gqq9q9q("2f1d50", 14), gqg9qq9gqq9q9q("2f0f5f", 23)};
    public static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("1f2a6c2877357134", 94);

    public static byte[] gqg9qq9gqq9q9q(g999gqq9ggqgqq g999gqq9ggqgqq, String str, int i) throws IllegalAccessException, JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Field field : g999gqq9ggqgqq.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(gqg9qq9gqq9q9q.class)) {
                field.setAccessible(true);
                gqg9qq9gqq9q9q gqg9qq9gqq9q9q2 = (gqg9qq9gqq9q9q) field.getAnnotation(gqg9qq9gqq9q9q.class);
                gqgqgqq9gq9q9q9 gqgqgqq9gq9q9q9 = (gqgqgqq9gq9q9q9) field.get(g999gqq9ggqgqq);
                if (gqgqgqq9gq9q9q9 == null) {
                    jSONObject.put(gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q(), "");
                } else if (gqgqgqq9gq9q9q9.q9qq99qg9qqgqg9gqgg9) {
                    if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q instanceof cn.tongdun.android.core.q9gqqq99999qq.gqg9qq9gqq9q9q) {
                        String gqg9qq9gqq9q9q3 = ((cn.tongdun.android.core.q9gqqq99999qq.gqg9qq9gqq9q9q) gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q).gqg9qq9gqq9q9q();
                        if (gqg9qq9gqq9q9q3 != null) {
                            jSONObject.put(gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q(), gqg9qq9gqq9q9q3);
                        }
                    } else if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q instanceof String) {
                        jSONObject.put(gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q(), gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q);
                    } else if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q instanceof Map) {
                        String str2 = (String) ((Map) gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q).get(qgg9qgg9999g9g);
                        String str3 = (String) ((Map) gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q).get(gqg9qq9gqq9q9q);
                        String gqg9qq9gqq9q9q4 = gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q();
                        if (str.equals(qgg9qgg9999g9g)) {
                            str3 = str2;
                        }
                        jSONObject.put(gqg9qq9gqq9q9q4, str3);
                    } else if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q != null) {
                        jSONObject.put(gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q(), gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q);
                    }
                } else if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(gqgqgqq9gq9q9q9.qgg9qgg9999g9g)) {
                    jSONObject2.put(gqg9qq9gqq9q9q2.gqg9qq9gqq9q9q(), gqgqgqq9gq9q9q9.qgg9qgg9999g9g);
                }
            }
        }
        long optInt = (long) jSONObject.optInt(gqg9qq9gqq9q9q("2f0750", 19));
        long optLong = jSONObject.optLong(gqg9qq9gqq9q9q("2f6152", 116));
        if (optLong != 0) {
            jSONObject.put(gqg9qq9gqq9q9q("2f6050", 116), optInt ^ optLong);
        }
        jSONObject.put(gqg9qq9gqq9q9q("2f5c55", 69), qgg99qqg9gq.g9q9q9g9);
        qgg99qqg9gq.g9q9q9g9 = "";
        jSONObject2.put(gqg9qq9gqq9q9q("146c736164", 2), CollectorError.getErrorMsg());
        jSONObject.put(gqg9qq9gqq9q9q("2f6a5c6a", 122), jSONObject2.toString());
        String jSONObject3 = jSONObject.toString();
        if (str.equals(qgg9qgg9999g9g)) {
            jSONObject.put(gqg9qq9gqq9q9q("0b516357795c6e", 53), cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qqq9gg9gqq9qgg99q);
            return gqg9qq9gqq9q9q(jSONObject, i).getBytes(gqg9qq9gqq9q9q("0e20676b72", 84));
        }
        O0o0o0o0o.o0ooo0oo0oo0o = qgg9qgg9999g9g.gqg9qq9gqq9q9q(36);
        HelperJNI.exprot(2, 0);
        BoxUtil.stamp = System.currentTimeMillis();
        String str4 = BoxUtil.stamp + O0o0o0o0o.o0o0oo;
        return (jSONObject3 + (str4 + gqg9qq9gqq9q9q("56", 123) + (str4.length() + 1))).getBytes(gqg9qq9gqq9q9q("0e1d675672", 105));
    }

    private static String gqg9qq9gqq9q9q(JSONObject jSONObject, int i) throws JSONException {
        double d = (double) ((((((i * 3) / 4) - 1024) * 3) / 4) - 16);
        Double.isNaN(d);
        int i2 = (int) (d / 0.7d);
        if (i2 < 1024) {
            i2 = 1024;
        }
        if (jSONObject.toString().length() < i2) {
            return jSONObject.toString();
        }
        Iterator<String> keys = jSONObject.keys();
        ArrayList arrayList = new ArrayList();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        for (int i3 = 0; i3 < q9gqqq99999qq.length; i3++) {
            if (arrayList.contains(q9gqqq99999qq[i3])) {
                jSONObject.remove(q9gqqq99999qq[i3]);
            }
            if (jSONObject.toString().length() < i2) {
                return jSONObject.toString();
            }
        }
        for (String str : q9qq99qg9qqgqg9gqgg9) {
            if (jSONObject.toString().length() < i2) {
                break;
            }
            if (arrayList.contains(str)) {
                if (gqg9qq9gqq9q9q("2f1551", 7).equals(str)) {
                    jSONObject.put(str, gqg9qq9gqq9q9q(new JSONArray(jSONObject.getString(str)), 3).toString());
                } else if (gqg9qq9gqq9q9q("2f1a50", 9).equals(str)) {
                    String string = jSONObject.getString(str);
                    if (string.contains(gqg9qq9gqq9q9q("263f2a", 59))) {
                        String[] split = string.trim().split(gqg9qq9gqq9q9q("27382d482a", 76));
                        JSONArray gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q(new JSONArray(split[0] + gqg9qq9gqq9q9q("26", 10)), 1);
                        JSONArray gqg9qq9gqq9q9q3 = gqg9qq9gqq9q9q(new JSONArray(gqg9qq9gqq9q9q(UserConfig.g, 7) + split[1]), 1);
                        jSONObject.put(str, gqg9qq9gqq9q9q2.toString() + gqg9qq9gqq9q9q("57", 100) + gqg9qq9gqq9q9q3.toString());
                    } else {
                        jSONObject.put(str, gqg9qq9gqq9q9q(new JSONArray(jSONObject.getString(str)), 1).toString());
                    }
                } else if (gqg9qq9gqq9q9q("2f7f5f", 103).equals(str)) {
                    gqg9qq9gqq9q9q(jSONObject, str);
                } else {
                    jSONObject.put(str, "");
                }
            }
        }
        return jSONObject.toString();
    }

    private static void gqg9qq9gqq9q9q(JSONObject jSONObject, String str) throws JSONException {
        String string = jSONObject.getString(str);
        if (TextUtils.isEmpty(string)) {
            jSONObject.remove(str);
            return;
        }
        String[] split = string.split(gqg9qq9gqq9q9q("272b", 126));
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < split.length; i3++) {
            if (!"".equals(split[i3])) {
                i++;
                i2 = (split[i3].length() * i) + i3;
                if (i == 3) {
                    break;
                }
            }
        }
        if (i == 0) {
            jSONObject.remove(str);
        } else {
            jSONObject.put(str, string.substring(0, i2));
        }
    }

    private static JSONArray gqg9qq9gqq9q9q(JSONArray jSONArray, int i) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        int i2 = 0;
        while (i2 < length && i2 < i) {
            jSONArray2.put(jSONArray.get(i2));
            i2++;
        }
        return jSONArray2;
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
            byte b = (byte) (i ^ 117);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.ELECTRONIC_USE_TIME);
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
