package com.mi.global.shop.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.gson.Gson;
import com.mi.util.Utils;
import com.taobao.weex.el.parse.Operators;

public class SaveObjectUtils {
    public static void a(Context context, String str, Object obj) {
        Utils.Preference.setStringPref(context, str, new Gson().toJson(obj));
    }

    public static <T> T a(Context context, String str, Class<T> cls) {
        return new Gson().fromJson(Utils.Preference.getStringPref(context, str, ""), cls);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042 A[Catch:{ IOException -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050 A[Catch:{ IOException -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.content.Context r4, java.lang.String r5, java.lang.Object r6) {
        /*
            android.content.SharedPreferences r4 = android.preference.PreferenceManager.getDefaultSharedPreferences(r4)
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0039 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0039 }
            r2.writeObject(r6)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            java.lang.String r6 = new java.lang.String     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            byte[] r1 = r0.toByteArray()     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r3 = 0
            byte[] r1 = android.util.Base64.encode(r1, r3)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r6.<init>(r1)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r4.putString(r5, r6)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r4.commit()     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r0.close()     // Catch:{ IOException -> 0x0046 }
            r2.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0031:
            r4 = move-exception
            goto L_0x004b
        L_0x0033:
            r4 = move-exception
            r1 = r2
            goto L_0x003a
        L_0x0036:
            r4 = move-exception
            r2 = r1
            goto L_0x004b
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            r4.printStackTrace()     // Catch:{ all -> 0x0036 }
            r0.close()     // Catch:{ IOException -> 0x0046 }
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004a:
            return
        L_0x004b:
            r0.close()     // Catch:{ IOException -> 0x0054 }
            if (r2 == 0) goto L_0x0058
            r2.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0058:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.SaveObjectUtils.b(android.content.Context, java.lang.String, java.lang.Object):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0041 A[Catch:{ IOException -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004f A[Catch:{ IOException -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x005d A[Catch:{ IOException -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x006c A[Catch:{ IOException -> 0x0070 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0055=Splitter:B:35:0x0055, B:27:0x0047=Splitter:B:27:0x0047, B:19:0x0039=Splitter:B:19:0x0039} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T b(android.content.Context r1, java.lang.String r2, java.lang.Class<T> r3) {
        /*
            android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
            boolean r3 = r1.contains(r2)
            r0 = 0
            if (r3 == 0) goto L_0x0075
            java.lang.String r1 = r1.getString(r2, r0)
            r2 = 0
            byte[] r1 = android.util.Base64.decode(r1, r2)
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r1)
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ StreamCorruptedException -> 0x0053, IOException -> 0x0045, ClassNotFoundException -> 0x0037, all -> 0x0034 }
            r1.<init>(r2)     // Catch:{ StreamCorruptedException -> 0x0053, IOException -> 0x0045, ClassNotFoundException -> 0x0037, all -> 0x0034 }
            java.lang.Object r3 = r1.readObject()     // Catch:{ StreamCorruptedException -> 0x0032, IOException -> 0x0030, ClassNotFoundException -> 0x002e }
            r2.close()     // Catch:{ IOException -> 0x0029 }
            r1.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r1 = move-exception
            r1.printStackTrace()
        L_0x002d:
            return r3
        L_0x002e:
            r3 = move-exception
            goto L_0x0039
        L_0x0030:
            r3 = move-exception
            goto L_0x0047
        L_0x0032:
            r3 = move-exception
            goto L_0x0055
        L_0x0034:
            r3 = move-exception
            r1 = r0
            goto L_0x0067
        L_0x0037:
            r3 = move-exception
            r1 = r0
        L_0x0039:
            r3.printStackTrace()     // Catch:{ all -> 0x0066 }
            r2.close()     // Catch:{ IOException -> 0x0061 }
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0075
        L_0x0045:
            r3 = move-exception
            r1 = r0
        L_0x0047:
            r3.printStackTrace()     // Catch:{ all -> 0x0066 }
            r2.close()     // Catch:{ IOException -> 0x0061 }
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0075
        L_0x0053:
            r3 = move-exception
            r1 = r0
        L_0x0055:
            r3.printStackTrace()     // Catch:{ all -> 0x0066 }
            r2.close()     // Catch:{ IOException -> 0x0061 }
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0075
        L_0x0061:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0075
        L_0x0066:
            r3 = move-exception
        L_0x0067:
            r2.close()     // Catch:{ IOException -> 0x0070 }
            if (r1 == 0) goto L_0x0074
            r1.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0074
        L_0x0070:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0074:
            throw r3
        L_0x0075:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.SaveObjectUtils.b(android.content.Context, java.lang.String, java.lang.Class):java.lang.Object");
    }

    public static <T> T c(Context context, String str, Class<T> cls) {
        if (context == null) {
            return null;
        }
        return a(str, cls, PreferenceManager.getDefaultSharedPreferences(context));
    }

    private static <T> T a(String str, Class<T> cls, SharedPreferences sharedPreferences) {
        try {
            T newInstance = cls.newInstance();
            if (newInstance instanceof Integer) {
                return Integer.valueOf(sharedPreferences.getInt(str, 0));
            }
            if (newInstance instanceof String) {
                return sharedPreferences.getString(str, "");
            }
            if (newInstance instanceof Boolean) {
                return Boolean.valueOf(sharedPreferences.getBoolean(str, false));
            }
            if (newInstance instanceof Long) {
                return Long.valueOf(sharedPreferences.getLong(str, 0));
            }
            if (newInstance instanceof Float) {
                return Float.valueOf(sharedPreferences.getFloat(str, 0.0f));
            }
            Log.e("system", "无法找到" + str + "对应的值");
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            Log.e("system", "类型输入错误或者复杂类型无法解析[" + e.getMessage() + Operators.ARRAY_END_STR);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.e("system", "类型输入错误或者复杂类型无法解析[" + e2.getMessage() + Operators.ARRAY_END_STR);
        }
    }
}
