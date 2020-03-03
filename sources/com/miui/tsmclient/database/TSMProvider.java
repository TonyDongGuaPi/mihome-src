package com.miui.tsmclient.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.google.android.gms.actions.SearchIntents;
import com.miui.tsmclient.util.LogUtils;

public class TSMProvider extends ContentProvider {
    private static final int CODE_URI_BANK_BIN = 2;
    private static final int CODE_URI_BANK_INFO = 3;
    private static final int CODE_URI_CACHE = 0;
    private static final int CODE_URI_DATA_STAT = 6;
    private static final int CODE_URI_NO_PROMPT_BULLETIN = 5;
    private static final int CODE_URI_TRANS_CARD_INFO = 4;
    private TSMDatabaseHelper mDbHelper;
    private UriMatcher mUriMatcher;

    public String getType(Uri uri) {
        return null;
    }

    public boolean onCreate() {
        this.mDbHelper = new TSMDatabaseHelper(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.mDbHelper.getReadableDatabase();
        int findMatch = findMatch(uri, SearchIntents.EXTRA_QUERY);
        if (findMatch == 0) {
            return readableDatabase.query("cache", strArr, str, strArr2, (String) null, (String) null, str2);
        }
        switch (findMatch) {
            case 2:
                return readableDatabase.query(DatabaseConstants.TABLE_BANK_BIN, strArr, str, strArr2, (String) null, (String) null, str2);
            case 3:
                return readableDatabase.query("bank_bin,bank_info", strArr, "bank_bin.bank_name" + "=" + "bank_info.bank_name" + " AND " + str, strArr2, (String) null, (String) null, str2);
            case 4:
                return readableDatabase.query(DatabaseConstants.TABLE_TRANS_CARD_INFO, strArr, str, strArr2, (String) null, (String) null, str2);
            case 5:
                return readableDatabase.query(DatabaseConstants.TABLE_NO_PROMPT_BULLETIN, strArr, str, strArr2, (String) null, (String) null, str2);
            case 6:
                return readableDatabase.query(DatabaseConstants.TABLE_DATA_STAT, strArr, str, strArr2, (String) null, (String) null, str2);
            default:
                return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri insert(android.net.Uri r14, android.content.ContentValues r15) {
        /*
            r13 = this;
            com.miui.tsmclient.database.TSMDatabaseHelper r0 = r13.mDbHelper
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r1 = "insert"
            int r1 = r13.findMatch(r14, r1)
            r9 = 0
            r11 = 0
            if (r1 == 0) goto L_0x0025
            switch(r1) {
                case 5: goto L_0x0089;
                case 6: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0098
        L_0x0016:
            java.lang.String r1 = "data_stat"
            long r0 = r0.insert(r1, r11, r15)
            int r15 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r15 <= 0) goto L_0x0098
            android.net.Uri r14 = android.content.ContentUris.withAppendedId(r14, r0)
            return r14
        L_0x0025:
            java.lang.String r1 = "key"
            java.lang.String r1 = r15.getAsString(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "key='"
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "'"
            r2.append(r1)
            java.lang.String r12 = r2.toString()
            java.lang.String r2 = "cache"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            r4 = r12
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x009b }
            int r2 = r1.getCount()     // Catch:{ all -> 0x0099 }
            if (r2 <= 0) goto L_0x0065
            int r2 = r13.update(r14, r15, r12, r11)     // Catch:{ all -> 0x0099 }
            if (r2 <= 0) goto L_0x0084
            long r2 = (long) r2     // Catch:{ all -> 0x0099 }
            android.net.Uri r14 = android.content.ContentUris.withAppendedId(r14, r2)     // Catch:{ all -> 0x0099 }
            if (r1 == 0) goto L_0x0064
            r1.close()
        L_0x0064:
            return r14
        L_0x0065:
            java.lang.String r2 = "cache"
            long r2 = r0.insert(r2, r11, r15)     // Catch:{ all -> 0x0099 }
            int r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r4 <= 0) goto L_0x0084
            android.content.Context r15 = r13.getContext()     // Catch:{ all -> 0x0099 }
            android.content.ContentResolver r15 = r15.getContentResolver()     // Catch:{ all -> 0x0099 }
            r15.notifyChange(r14, r11)     // Catch:{ all -> 0x0099 }
            android.net.Uri r14 = android.content.ContentUris.withAppendedId(r14, r2)     // Catch:{ all -> 0x0099 }
            if (r1 == 0) goto L_0x0083
            r1.close()
        L_0x0083:
            return r14
        L_0x0084:
            if (r1 == 0) goto L_0x0089
            r1.close()
        L_0x0089:
            java.lang.String r1 = "no_prompt_bulletin"
            long r0 = r0.insert(r1, r11, r15)
            int r15 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r15 <= 0) goto L_0x0098
            android.net.Uri r14 = android.content.ContentUris.withAppendedId(r14, r0)
            return r14
        L_0x0098:
            return r11
        L_0x0099:
            r14 = move-exception
            goto L_0x009d
        L_0x009b:
            r14 = move-exception
            r1 = r11
        L_0x009d:
            if (r1 == 0) goto L_0x00a2
            r1.close()
        L_0x00a2:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.database.TSMProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        int findMatch = findMatch(uri, "delete");
        if (findMatch == 0) {
            int delete = writableDatabase.delete("cache", str, strArr);
            if (delete > 0) {
                getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            }
            return delete;
        } else if (findMatch != 6) {
            return 0;
        } else {
            return writableDatabase.delete(DatabaseConstants.TABLE_DATA_STAT, str, strArr);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        if (findMatch(uri, "update") != 0) {
            return 0;
        }
        int update = writableDatabase.update("cache", contentValues, str, strArr);
        if (update > 0) {
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        }
        return update;
    }

    private int findMatch(Uri uri, String str) {
        int match = getUriMatcher().match(uri);
        if (match >= 0) {
            LogUtils.v(str + ": uri=" + uri + ", match is " + match);
            return match;
        }
        throw new IllegalArgumentException("Unknown uri: " + uri);
    }

    private synchronized UriMatcher getUriMatcher() {
        if (this.mUriMatcher == null) {
            this.mUriMatcher = new UriMatcher(-1);
            this.mUriMatcher.addURI(DatabaseConstants.AUTHORITY, "cache", 0);
            this.mUriMatcher.addURI(DatabaseConstants.AUTHORITY, DatabaseConstants.TABLE_BANK_BIN, 2);
            this.mUriMatcher.addURI(DatabaseConstants.AUTHORITY, DatabaseConstants.TABLE_BANK_INFO, 3);
            this.mUriMatcher.addURI(DatabaseConstants.AUTHORITY, DatabaseConstants.TABLE_TRANS_CARD_INFO, 4);
            this.mUriMatcher.addURI(DatabaseConstants.AUTHORITY, DatabaseConstants.TABLE_NO_PROMPT_BULLETIN, 5);
            this.mUriMatcher.addURI(DatabaseConstants.AUTHORITY, DatabaseConstants.TABLE_DATA_STAT, 6);
        }
        return this.mUriMatcher;
    }
}
