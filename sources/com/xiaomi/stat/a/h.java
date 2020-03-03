package com.xiaomi.stat.a;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.d.k;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f23005a;
    final /* synthetic */ c b;

    h(c cVar, String str) {
        this.b = cVar;
        this.f23005a = str;
    }

    public void run() {
        String[] strArr;
        String str;
        try {
            SQLiteDatabase writableDatabase = this.b.l.getWritableDatabase();
            if (TextUtils.equals(this.f23005a, ak.b())) {
                str = "sub is null";
                strArr = null;
            } else {
                str = "sub = ?";
                strArr = new String[]{this.f23005a};
            }
            writableDatabase.delete(j.b, str, strArr);
        } catch (Exception e) {
            k.b("EventManager", "removeAllEventsForApp exception: " + e.toString());
        }
    }
}
