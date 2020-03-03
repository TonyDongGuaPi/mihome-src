package com.miui.tsmclient.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.util.i;
import com.miui.tsmclient.database.DatabaseConstants;
import com.miui.tsmclient.util.IOUtils;
import com.miui.tsmclient.util.LogUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class TSMDatabaseHelper extends SQLiteOpenHelper {
    private static final String CONTENT_SPLIT = ",";
    private static final String DB_NAME = "tsmclient.db";
    private static final int DB_VERSION = 8;
    private static final String FILE_BANK_BIN = "bank_bin";
    private static final String FILE_BANK_INFO = "bank_info";
    private static final String FILE_TRANS_CARD_INFO = "trans_card_info";
    private static final String TAG = "TSMDatabaseHelper";
    private Context mContext;

    public TSMDatabaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 8);
        this.mContext = context;
        setWriteAheadLoggingEnabled(true);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTables(sQLiteDatabase);
        initTables(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.i("upgrading db from " + i + " to " + i2);
        sQLiteDatabase.beginTransaction();
        if (i < 7) {
            try {
                dropTable(sQLiteDatabase, "cache");
            } catch (Throwable th) {
                LogUtils.e("error occurred to upgrade db: " + th.getMessage());
            }
        }
        createTables(sQLiteDatabase);
        initTables(sQLiteDatabase);
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    private void createTables(SQLiteDatabase sQLiteDatabase) {
        createCacheTb(sQLiteDatabase);
        createBankInfoTb(sQLiteDatabase);
        createTransCardInfoTb(sQLiteDatabase);
        createBankBinTb(sQLiteDatabase);
        createNoPromptBulletinTb(sQLiteDatabase);
        createDataStatTb(sQLiteDatabase);
    }

    private void initTables(SQLiteDatabase sQLiteDatabase) {
        initBankInfoTb(sQLiteDatabase);
        initTransCardInfoTb(sQLiteDatabase);
        initBankBinTb(sQLiteDatabase);
    }

    private void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str + i.b);
    }

    private void createCacheTb(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cache(_id INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,value TEXT);");
    }

    private void createBankBinTb(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS bank_bin(_id INTEGER PRIMARY KEY AUTOINCREMENT,bin_code TEXT NOT NULL,bank_name TEXT NOT NULL,card_type INTEGER);");
    }

    private void createBankInfoTb(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS bank_info (_id INTEGER PRIMARY KEY AUTOINCREMENT,bank_code TEXT ,bank_id TEXT ,bank_name TEXT ,bank_logo TEXT );");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS BANK_ID_INDEX ON bank_info ( bank_id)");
    }

    private void createTransCardInfoTb(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS trans_card_info (_id INTEGER PRIMARY KEY AUTOINCREMENT,card_code TEXT ,card_id TEXT ,card_name TEXT ,card_logo TEXT ,issuable INTEGER );");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS TRANS_CARD_ID_INDEX ON trans_card_info ( card_id)");
    }

    private void createNoPromptBulletinTb(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS no_prompt_bulletin(_id INTEGER PRIMARY KEY AUTOINCREMENT,bulletin_id TEXT NOT NULL);");
    }

    private void createDataStatTb(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data_stat(_id INTEGER PRIMARY KEY AUTOINCREMENT,data_id INTEGER ,data_value TEXT ,data_time TEXT);");
    }

    private void initBankInfoTb(SQLiteDatabase sQLiteDatabase) {
        BufferedReader bufferedReader = null;
        try {
            sQLiteDatabase.delete("bank_info", (String) null, (String[]) null);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open("bank_info")));
            try {
                ContentValues contentValues = new ContentValues();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        contentValues.clear();
                        String[] split = readLine.split(",");
                        contentValues.put(DatabaseConstants.BankInfoTable.COLUMN_BANK_CODE, split[0]);
                        contentValues.put(DatabaseConstants.BankInfoTable.COLUMN_BANK_ID, split[1]);
                        contentValues.put("bank_name", split[2]);
                        if (split.length > 3) {
                            contentValues.put(DatabaseConstants.BankInfoTable.COLUMN_BANK_LOGO, split[3]);
                        }
                        sQLiteDatabase.insert("bank_info", (String) null, contentValues);
                    } else {
                        IOUtils.closeQuietly((Reader) bufferedReader2);
                        return;
                    }
                }
            } catch (IOException e) {
                e = e;
                bufferedReader = bufferedReader2;
                try {
                    Log.e(TAG, "Error when process file", e);
                    IOUtils.closeQuietly((Reader) bufferedReader);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    IOUtils.closeQuietly((Reader) bufferedReader2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly((Reader) bufferedReader2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, "Error when process file", e);
            IOUtils.closeQuietly((Reader) bufferedReader);
        }
    }

    private void initBankBinTb(SQLiteDatabase sQLiteDatabase) {
        BufferedReader bufferedReader = null;
        try {
            sQLiteDatabase.delete("bank_bin", (String) null, (String[]) null);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open("bank_bin")));
            try {
                ContentValues contentValues = new ContentValues();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        contentValues.clear();
                        String[] split = readLine.split(",");
                        int i = 0;
                        contentValues.put("bank_name", split[0]);
                        contentValues.put(DatabaseConstants.BankBinTable.COLUMN_BIN_CODE, split[1]);
                        if (TextUtils.isDigitsOnly(split[2])) {
                            i = Integer.valueOf(split[2]).intValue();
                        }
                        contentValues.put("card_type", Integer.valueOf(i));
                        sQLiteDatabase.insert("bank_bin", (String) null, contentValues);
                    } else {
                        IOUtils.closeQuietly((Reader) bufferedReader2);
                        return;
                    }
                }
            } catch (IOException e) {
                e = e;
                bufferedReader = bufferedReader2;
                try {
                    LogUtils.e("failed to init bank card info table", e);
                    IOUtils.closeQuietly((Reader) bufferedReader);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    IOUtils.closeQuietly((Reader) bufferedReader2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly((Reader) bufferedReader2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            LogUtils.e("failed to init bank card info table", e);
            IOUtils.closeQuietly((Reader) bufferedReader);
        }
    }

    private void initTransCardInfoTb(SQLiteDatabase sQLiteDatabase) {
        BufferedReader bufferedReader = null;
        try {
            sQLiteDatabase.delete("trans_card_info", (String) null, (String[]) null);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open("trans_card_info")));
            try {
                ContentValues contentValues = new ContentValues();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        contentValues.clear();
                        String[] split = readLine.split(",");
                        contentValues.put(DatabaseConstants.TransCardInfoTable.COLUMN_CARD_CODE, split[0]);
                        contentValues.put("card_id", split[1]);
                        contentValues.put("card_name", split[2]);
                        contentValues.put(DatabaseConstants.TransCardInfoTable.COLUMN_CARD_LOGO, split[3]);
                        contentValues.put(DatabaseConstants.TransCardInfoTable.COLUMN_ISSUABLE, split[4]);
                        sQLiteDatabase.insert("trans_card_info", (String) null, contentValues);
                    } else {
                        IOUtils.closeQuietly((Reader) bufferedReader2);
                        return;
                    }
                }
            } catch (IOException e) {
                e = e;
                bufferedReader = bufferedReader2;
                try {
                    Log.e(TAG, "Error when process file", e);
                    IOUtils.closeQuietly((Reader) bufferedReader);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    IOUtils.closeQuietly((Reader) bufferedReader2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly((Reader) bufferedReader2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, "Error when process file", e);
            IOUtils.closeQuietly((Reader) bufferedReader);
        }
    }
}
