package org.greenrobot.greendao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteDatabase;

public abstract class DatabaseOpenHelper extends SQLiteOpenHelper {
    private final Context context;
    private EncryptedHelper encryptedHelper;
    private boolean loadSQLCipherNativeLibs;
    private final String name;
    private final int version;

    public void onCreate(Database database) {
    }

    public void onOpen(Database database) {
    }

    public void onUpgrade(Database database, int i, int i2) {
    }

    public DatabaseOpenHelper(Context context2, String str, int i) {
        this(context2, str, (SQLiteDatabase.CursorFactory) null, i);
    }

    public DatabaseOpenHelper(Context context2, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context2, str, cursorFactory, i);
        this.loadSQLCipherNativeLibs = true;
        this.context = context2;
        this.name = str;
        this.version = i;
    }

    public void setLoadSQLCipherNativeLibs(boolean z) {
        this.loadSQLCipherNativeLibs = z;
    }

    public Database getWritableDb() {
        return wrap(getWritableDatabase());
    }

    public Database getReadableDb() {
        return wrap(getReadableDatabase());
    }

    /* access modifiers changed from: protected */
    public Database wrap(SQLiteDatabase sQLiteDatabase) {
        return new StandardDatabase(sQLiteDatabase);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        onCreate(wrap(sQLiteDatabase));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(wrap(sQLiteDatabase), i, i2);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        onOpen(wrap(sQLiteDatabase));
    }

    private EncryptedHelper checkEncryptedHelper() {
        if (this.encryptedHelper == null) {
            this.encryptedHelper = new EncryptedHelper(this.context, this.name, this.version, this.loadSQLCipherNativeLibs);
        }
        return this.encryptedHelper;
    }

    public Database getEncryptedWritableDb(String str) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.c(checkEncryptedHelper.getReadableDatabase(str));
    }

    public Database getEncryptedWritableDb(char[] cArr) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.c(checkEncryptedHelper.getWritableDatabase(cArr));
    }

    public Database getEncryptedReadableDb(String str) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.c(checkEncryptedHelper.getReadableDatabase(str));
    }

    public Database getEncryptedReadableDb(char[] cArr) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.c(checkEncryptedHelper.getReadableDatabase(cArr));
    }

    private class EncryptedHelper extends net.sqlcipher.database.SQLiteOpenHelper {
        public EncryptedHelper(Context context, String str, int i, boolean z) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, i);
            if (z) {
                net.sqlcipher.database.SQLiteDatabase.loadLibs(context);
            }
        }

        public void a(net.sqlcipher.database.SQLiteDatabase sQLiteDatabase) {
            DatabaseOpenHelper.this.onCreate(c(sQLiteDatabase));
        }

        public void a(net.sqlcipher.database.SQLiteDatabase sQLiteDatabase, int i, int i2) {
            DatabaseOpenHelper.this.onUpgrade(c(sQLiteDatabase), i, i2);
        }

        public void b(net.sqlcipher.database.SQLiteDatabase sQLiteDatabase) {
            DatabaseOpenHelper.this.onOpen(c(sQLiteDatabase));
        }

        /* access modifiers changed from: protected */
        public Database c(net.sqlcipher.database.SQLiteDatabase sQLiteDatabase) {
            return new EncryptedDatabase(sQLiteDatabase);
        }
    }
}