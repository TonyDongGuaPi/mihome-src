package com.xiaomi.push;

import android.content.Context;
import android.database.Cursor;
import com.xiaomi.push.cd;
import java.util.ArrayList;
import java.util.List;

public class by extends cd.b<Long> {

    /* renamed from: a  reason: collision with root package name */
    private long f12662a = 0;
    private String d;

    public by(String str, List<String> list, String str2, String[] strArr, String str3, String str4, String str5, int i, String str6) {
        super(str, list, str2, strArr, str3, str4, str5, i);
        this.d = str6;
    }

    public static by a(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("count(*)");
        return new by(str, arrayList, (String) null, (String[]) null, (String) null, (String) null, (String) null, 0, "job to get count of all message");
    }

    /* renamed from: a */
    public Long b(Context context, Cursor cursor) {
        return Long.valueOf(cursor.getLong(0));
    }

    public Object a() {
        return Long.valueOf(this.f12662a);
    }

    public void a(Context context, List<Long> list) {
        if (context != null && list != null && list.size() > 0) {
            this.f12662a = list.get(0).longValue();
        }
    }
}
