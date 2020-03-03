package com.xiaomi.youpin.common.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DBStorageUtil {
    static String a(int i) {
        String[] strArr = new String[i];
        Arrays.fill(strArr, "?");
        return "key IN (" + TextUtils.join(", ", strArr) + Operators.BRACKET_END_STR;
    }

    static String[] a(List<String> list, int i, int i2) {
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = list.get(i + i3);
        }
        return strArr;
    }

    public static String a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query("KeyValueStorage", new String[]{"value"}, "key=?", new String[]{str}, (String) null, (String) null, (String) null);
        try {
            if (!query.moveToFirst()) {
                return null;
            }
            String string = query.getString(0);
            query.close();
            return string;
        } finally {
            query.close();
        }
    }

    public static int b(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.delete("KeyValueStorage", "key=?", new String[]{str});
    }

    static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", str);
        contentValues.put("value", str2);
        return -1 != sQLiteDatabase.insertWithOnConflict("KeyValueStorage", (String) null, contentValues, 5);
    }

    static boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2) throws JSONException {
        String a2 = a(sQLiteDatabase, str);
        if (a2 != null) {
            JSONObject jSONObject = new JSONObject(a2);
            a(jSONObject, new JSONObject(str2));
            str2 = jSONObject.toString();
        }
        return a(sQLiteDatabase, str, str2);
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject2.optJSONObject(next);
            JSONObject optJSONObject2 = jSONObject.optJSONObject(next);
            if (optJSONObject == null || optJSONObject2 == null) {
                jSONObject.put(next, jSONObject2.get(next));
            } else {
                a(optJSONObject2, optJSONObject);
                jSONObject.put(next, optJSONObject2);
            }
        }
    }
}
