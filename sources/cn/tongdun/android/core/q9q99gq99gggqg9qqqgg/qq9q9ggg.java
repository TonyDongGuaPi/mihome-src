package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.content.Context;
import android.content.SharedPreferences;
import cn.com.fmsh.tsm.business.constants.Constants;

public class qq9q9ggg {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("027854544f5d5c4a5d", 75);

    private static void gqg9qq9gqq9q9q(SharedPreferences.Editor editor, String str, Object obj) {
        if (obj instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof String) {
            editor.putString(str, (String) obj);
        }
    }

    public static void gqg9qq9gqq9q9q(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        gqg9qq9gqq9q9q(edit, str2, (Object) str3);
        edit.commit();
    }

    public static void gqg9qq9gqq9q9q(Context context, String str, String str2) {
        gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, str, str2);
    }

    private static Object gqg9qq9gqq9q9q(SharedPreferences sharedPreferences, String str, Object obj) {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        return null;
    }

    public static Object gqg9qq9gqq9q9q(Context context, String str, String str2, Object obj) {
        return gqg9qq9gqq9q9q(context.getSharedPreferences(str, 0), str2, obj);
    }

    public static Object gqg9qq9gqq9q9q(Context context, String str, Object obj) {
        return gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, str, obj);
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
            byte b = (byte) (i ^ 56);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.PAY_ORDER_LIST);
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
