package com.xiaomi.push.providers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mics.core.data.request.SendText;
import com.xiaomi.channel.commonutils.logger.b;
import org.cybergarage.http.HTTP;

public class a extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f12841a = new Object();
    private static int b = 1;
    private static final String[] c = {"package_name", SendText.TEXT, "message_ts", " LONG DEFAULT 0 ", HTTP.CONTENT_RANGE_BYTES, " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", SendText.TEXT};

    public a(Context context) {
        super(context, "traffic.db", (SQLiteDatabase.CursorFactory) null, b);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        for (int i = 0; i < c.length - 1; i += 2) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(c[i]);
            sb.append(" ");
            sb.append(c[i + 1]);
        }
        sb.append(");");
        sQLiteDatabase.execSQL(sb.toString());
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f12841a) {
            try {
                a(sQLiteDatabase);
            } catch (SQLException e) {
                b.a((Throwable) e);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
