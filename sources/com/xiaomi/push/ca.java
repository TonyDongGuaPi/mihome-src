package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import com.xiaomi.push.cd;

public class ca extends cd.e {

    /* renamed from: a  reason: collision with root package name */
    private String f12665a = "MessageInsertJob";

    public ca(String str, ContentValues contentValues, String str2) {
        super(str, contentValues);
        this.f12665a = str2;
    }

    public static ca a(Context context, String str, hs hsVar) {
        byte[] a2 = iy.a(hsVar);
        if (a2 == null || a2.length <= 0) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", 0);
        contentValues.put("messageId", "");
        contentValues.put("messageItemId", hsVar.d());
        contentValues.put("messageItem", a2);
        contentValues.put("appId", br.a(context).b());
        contentValues.put("packageName", br.a(context).a());
        contentValues.put("createTimeStamp", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("uploadTimestamp", 0);
        return new ca(str, contentValues, "a job build to insert message to db");
    }
}
