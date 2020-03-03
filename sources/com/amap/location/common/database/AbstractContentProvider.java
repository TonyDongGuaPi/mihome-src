package com.amap.location.common.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import java.util.HashMap;

public abstract class AbstractContentProvider extends ContentProvider {

    /* renamed from: a  reason: collision with root package name */
    protected UriMatcher f4573a = new UriMatcher(-1);
    protected HashMap<Integer, SQLiteOpenHelper> b = new HashMap<>();
    protected HashMap<Integer, String> c = new HashMap<>();
    private final String d = "AbstractContentProvider";
    private String e = "";

    private <T> T a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public int a(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        a(uri, (Object) "uri");
        try {
            int match = this.f4573a.match(uri);
            SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
            if (sQLiteOpenHelper == null) {
                return 0;
            }
            SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
            String str2 = this.c.get(Integer.valueOf(match));
            if (writableDatabase != null) {
                if (str2 != null) {
                    return writableDatabase.update(str2, contentValues, str, strArr);
                }
            }
            return 0;
        } catch (Exception unused) {
            return -1;
        }
    }

    public int a(Uri uri, String str, String[] strArr) {
        a(uri, (Object) "uri");
        try {
            int match = this.f4573a.match(uri);
            SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
            if (sQLiteOpenHelper == null) {
                return 0;
            }
            SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
            String str2 = this.c.get(Integer.valueOf(match));
            if (writableDatabase != null) {
                if (str2 != null) {
                    return writableDatabase.delete(str2, str, strArr);
                }
            }
            return 0;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0057 A[SYNTHETIC, Splitter:B:29:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005d A[SYNTHETIC, Splitter:B:35:0x005d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(android.net.Uri r6, android.content.ContentValues[] r7) {
        /*
            r5 = this;
            java.lang.String r0 = "uri"
            r5.a(r6, (java.lang.Object) r0)
            int r0 = r7.length
            r1 = 0
            android.content.UriMatcher r2 = r5.f4573a     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            int r6 = r2.match(r6)     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            java.util.HashMap<java.lang.Integer, android.database.sqlite.SQLiteOpenHelper> r2 = r5.b     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            android.database.sqlite.SQLiteOpenHelper r2 = (android.database.sqlite.SQLiteOpenHelper) r2     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            r3 = 0
            if (r2 != 0) goto L_0x001e
            return r3
        L_0x001e:
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ Exception -> 0x005b, all -> 0x0053 }
            java.util.HashMap<java.lang.Integer, java.lang.String> r4 = r5.c     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            java.lang.Object r6 = r4.get(r6)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            if (r2 == 0) goto L_0x0049
            if (r6 != 0) goto L_0x0033
            goto L_0x0049
        L_0x0033:
            r2.beginTransaction()     // Catch:{ Exception -> 0x0051, all -> 0x004f }
        L_0x0036:
            if (r3 >= r0) goto L_0x0040
            r4 = r7[r3]     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r2.insert(r6, r1, r4)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            int r3 = r3 + 1
            goto L_0x0036
        L_0x0040:
            r2.setTransactionSuccessful()     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            if (r2 == 0) goto L_0x0060
            r2.endTransaction()     // Catch:{ Exception -> 0x0060 }
            goto L_0x0060
        L_0x0049:
            if (r2 == 0) goto L_0x004e
            r2.endTransaction()     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            return r3
        L_0x004f:
            r6 = move-exception
            goto L_0x0055
        L_0x0051:
            r1 = r2
            goto L_0x005b
        L_0x0053:
            r6 = move-exception
            r2 = r1
        L_0x0055:
            if (r2 == 0) goto L_0x005a
            r2.endTransaction()     // Catch:{ Exception -> 0x005a }
        L_0x005a:
            throw r6
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.endTransaction()     // Catch:{ Exception -> 0x0060 }
        L_0x0060:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.location.common.database.AbstractContentProvider.a(android.net.Uri, android.content.ContentValues[]):int");
    }

    public long a(Uri uri) {
        a(uri, (Object) "uri");
        int match = this.f4573a.match(uri);
        SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
        if (sQLiteOpenHelper == null) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        String str = this.c.get(Integer.valueOf(match));
        if (writableDatabase == null || str == null) {
            return 0;
        }
        return DatabaseUtils.queryNumEntries(writableDatabase, str);
    }

    public long a(Uri uri, ContentValues contentValues) {
        a(uri, (Object) "uri");
        try {
            int match = this.f4573a.match(uri);
            SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
            if (sQLiteOpenHelper == null) {
                return 0;
            }
            SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
            String str = this.c.get(Integer.valueOf(match));
            if (writableDatabase != null) {
                if (str != null) {
                    return writableDatabase.insert(str, (String) null, contentValues);
                }
            }
            return 0;
        } catch (Exception unused) {
            return -1;
        }
    }

    public Cursor a(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return b(uri, strArr, str, strArr2, str2, (String) null);
    }

    public Cursor a(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        Uri uri2 = uri;
        a(uri, (Object) "uri");
        int match = this.f4573a.match(uri);
        SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
        if (sQLiteOpenHelper == null) {
            return null;
        }
        SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        String str4 = this.c.get(Integer.valueOf(match));
        if (writableDatabase == null || str4 == null) {
            return null;
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str4);
        return sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, (String) null, (String) null, str2, str3);
    }

    public abstract String a();

    public void a(Integer num, String str, SQLiteOpenHelper sQLiteOpenHelper) {
        if (sQLiteOpenHelper != null) {
            this.b.put(num, sQLiteOpenHelper);
            this.c.put(num, str);
            this.f4573a.addURI(this.e, str, num.intValue());
        }
    }

    public Cursor b(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        Uri uri2 = uri;
        a(uri, (Object) "uri");
        try {
            int match = this.f4573a.match(uri);
            SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
            if (sQLiteOpenHelper == null) {
                return null;
            }
            SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
            String str4 = this.c.get(Integer.valueOf(match));
            if (writableDatabase != null) {
                if (str4 != null) {
                    SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                    sQLiteQueryBuilder.setTables(str4);
                    return sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, (String) null, (String) null, str2, str3);
                }
            }
            return null;
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        a(uri, (Object) "uri");
        int match = this.f4573a.match(uri);
        SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
        int i = 0;
        if (sQLiteOpenHelper == null) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        String str = this.c.get(Integer.valueOf(match));
        if (writableDatabase == null || str == null) {
            return 0;
        }
        int length = contentValuesArr.length;
        writableDatabase.beginTransaction();
        while (i < length) {
            try {
                writableDatabase.insert(str, (String) null, contentValuesArr[i]);
                i++;
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        return length;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        a(uri, (Object) "uri");
        int match = this.f4573a.match(uri);
        SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
        if (sQLiteOpenHelper == null) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        String str2 = this.c.get(Integer.valueOf(match));
        if (writableDatabase == null || str2 == null) {
            return 0;
        }
        return writableDatabase.delete(str2, str, strArr);
    }

    public String getType(Uri uri) {
        int match = this.f4573a.match(uri);
        return "vnd.android.cursor.dir/" + this.c.get(Integer.valueOf(match));
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        a(uri, (Object) "uri");
        int match = this.f4573a.match(uri);
        SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
        if (sQLiteOpenHelper == null) {
            return ContentUris.withAppendedId(uri, -1);
        }
        SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        String str = this.c.get(Integer.valueOf(match));
        return (writableDatabase == null || str == null) ? ContentUris.withAppendedId(uri, -1) : ContentUris.withAppendedId(uri, writableDatabase.insert(str, (String) null, contentValues));
    }

    public boolean onCreate() {
        this.e = a();
        b();
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return a(uri, strArr, str, strArr2, str2, (String) null);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        a(uri, (Object) "uri");
        int match = this.f4573a.match(uri);
        SQLiteOpenHelper sQLiteOpenHelper = this.b.get(Integer.valueOf(match));
        if (sQLiteOpenHelper == null) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        String str2 = this.c.get(Integer.valueOf(match));
        if (writableDatabase == null || str2 == null) {
            return 0;
        }
        return writableDatabase.update(str2, contentValues, str, strArr);
    }
}
