package org.greenrobot.greendao;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.greenrobot.greendao.database.Database;

public class DbUtils {
    public static void a(Database database) {
        database.a("VACUUM");
    }

    public static int a(Context context, Database database, String str) throws IOException {
        return a(context, database, str, true);
    }

    public static int a(Context context, Database database, String str, boolean z) throws IOException {
        int i;
        String[] split = new String(a(context, str), "UTF-8").split(";(\\s)*[\n\r]");
        if (z) {
            i = a(database, split);
        } else {
            i = b(database, split);
        }
        DaoLog.c("Executed " + i + " statements from SQL script '" + str + "'");
        return i;
    }

    public static int a(Database database, String[] strArr) {
        database.a();
        try {
            int b = b(database, strArr);
            database.d();
            return b;
        } finally {
            database.b();
        }
    }

    public static int b(Database database, String[] strArr) {
        int i = 0;
        for (String trim : strArr) {
            String trim2 = trim.trim();
            if (trim2.length() > 0) {
                database.a(trim2);
                i++;
            }
        }
        return i;
    }

    public static int a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i;
            }
            outputStream.write(bArr, 0, read);
            i += read;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(Context context, String str) throws IOException {
        InputStream open = context.getResources().getAssets().open(str);
        try {
            return a(open);
        } finally {
            open.close();
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query(str, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        try {
            DaoLog.b(DatabaseUtils.dumpCursorToString(query));
        } finally {
            query.close();
        }
    }
}
