package com.xiaomi.smarthome.download;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.mi.util.permission.Permission;
import com.mics.core.data.request.SendText;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.download.Downloads;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DownloadProvider extends ContentProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15555a = "downloads.db";
    private static final int b = 107;
    private static final String c = "downloads";
    private static final String d = "vnd.android.cursor.dir/download";
    private static final String e = "vnd.android.cursor.item/download";
    private static final UriMatcher f = new UriMatcher(-1);
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private static final int j = 4;
    private static final int k = 5;
    private static final Uri[] l = {Downloads.CONTENT_URI, Downloads.ALL_DOWNLOADS_CONTENT_URI};
    private static final String[] m = {"_id", Downloads.COLUMN_APP_DATA, Downloads._DATA, Downloads.COLUMN_MIME_TYPE, "visibility", "destination", Downloads.COLUMN_CONTROL, "status", Downloads.COLUMN_LAST_MODIFICATION, Downloads.COLUMN_NOTIFICATION_PACKAGE, Downloads.COLUMN_NOTIFICATION_CLASS, Downloads.COLUMN_TOTAL_BYTES, Downloads.COLUMN_CURRENT_BYTES, "title", "description", "uri", Downloads.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, "hint", Downloads.COLUMN_DELETED, Downloads.COLUMN_UDN};
    private static HashSet<String> n = new HashSet<>();
    SystemFacade mSystemFacade;
    private SQLiteOpenHelper o = null;

    static {
        f.addURI("com.xiaomi.smarthome.downloads", "my_downloads", 1);
        f.addURI("com.xiaomi.smarthome.downloads", "my_downloads/#", 2);
        f.addURI("com.xiaomi.smarthome.downloads", "all_downloads", 3);
        f.addURI("com.xiaomi.smarthome.downloads", "all_downloads/#", 4);
        f.addURI("com.xiaomi.smarthome.downloads", "my_downloads/#/headers", 5);
        f.addURI("com.xiaomi.smarthome.downloads", "all_downloads/#/headers", 5);
        for (String add : m) {
            n.add(add);
        }
    }

    private static class SqlSelection {

        /* renamed from: a  reason: collision with root package name */
        public StringBuilder f15558a;
        public List<String> b;

        private SqlSelection() {
            this.f15558a = new StringBuilder();
            this.b = new ArrayList();
        }

        public <T> void a(String str, T... tArr) {
            if (str != null && str.length() != 0) {
                if (this.f15558a.length() != 0) {
                    this.f15558a.append(" AND ");
                }
                this.f15558a.append(Operators.BRACKET_START_STR);
                this.f15558a.append(str);
                this.f15558a.append(Operators.BRACKET_END_STR);
                if (tArr != null) {
                    for (T obj : tArr) {
                        this.b.add(obj.toString());
                    }
                }
            }
        }

        public String a() {
            return this.f15558a.toString();
        }

        public String[] b() {
            return (String[]) this.b.toArray(new String[this.b.size()]);
        }
    }

    private final class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DownloadProvider.f15555a, (SQLiteDatabase.CursorFactory) null, 107);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.v(Constants.f15548a, "populating new database");
            onUpgrade(sQLiteDatabase, 0, 107);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            int i3 = 99;
            if (i == 31) {
                i3 = 100;
            } else if (i < 100) {
                Log.i(Constants.f15548a, "Upgrading downloads database from version " + i + " to version " + i2 + ", which will destroy all old data");
            } else if (i > i2) {
                Log.i(Constants.f15548a, "Downgrading downloads database from version " + i + " (current version is " + i2 + "), destroying all old data");
            } else {
                i3 = i;
            }
            while (true) {
                i3++;
                if (i3 <= i2) {
                    a(sQLiteDatabase, i3);
                } else {
                    return;
                }
            }
        }

        private void a(SQLiteDatabase sQLiteDatabase, int i) {
            switch (i) {
                case 100:
                    c(sQLiteDatabase);
                    return;
                case 101:
                    d(sQLiteDatabase);
                    return;
                case 102:
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_IS_PUBLIC_API, "INTEGER NOT NULL DEFAULT 0");
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_ALLOW_ROAMING, "INTEGER NOT NULL DEFAULT 0");
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_ALLOWED_NETWORK_TYPES, "INTEGER NOT NULL DEFAULT 0");
                    return;
                case 103:
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, "INTEGER NOT NULL DEFAULT 1");
                    b(sQLiteDatabase);
                    return;
                case 104:
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT, "INTEGER NOT NULL DEFAULT 0");
                    return;
                case 105:
                    a(sQLiteDatabase);
                    return;
                case 106:
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_DELETED, "BOOLEAN NOT NULL DEFAULT 0");
                    return;
                case 107:
                    a(sQLiteDatabase, DownloadProvider.c, Downloads.COLUMN_UDN, SendText.TEXT);
                    return;
                default:
                    throw new IllegalStateException("Don't know how to upgrade to " + i);
            }
        }

        private void a(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads.COLUMN_CURRENT_BYTES, 0);
            a(sQLiteDatabase, contentValues);
            contentValues.put(Downloads.COLUMN_TOTAL_BYTES, -1);
            a(sQLiteDatabase, contentValues);
            contentValues.put("title", "");
            a(sQLiteDatabase, contentValues);
            contentValues.put("description", "");
            a(sQLiteDatabase, contentValues);
        }

        private void a(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
            sQLiteDatabase.update(DownloadProvider.c, contentValues, ((String) contentValues.valueSet().iterator().next().getKey()) + " is null", (String[]) null);
            contentValues.clear();
        }

        private void b(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, false);
            sQLiteDatabase.update(DownloadProvider.c, contentValues, "destination != 0", (String[]) null);
        }

        private void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD COLUMN " + str2 + " " + str3);
        }

        private void c(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS downloads");
                sQLiteDatabase.execSQL("CREATE TABLE downloads(_id INTEGER PRIMARY KEY AUTOINCREMENT,uri TEXT, method INTEGER, entity TEXT, no_integrity BOOLEAN, hint TEXT, otaupdate BOOLEAN, _data TEXT, mimetype TEXT, destination INTEGER, no_system BOOLEAN, visibility INTEGER, control INTEGER, status INTEGER, numfailed INTEGER, lastmod BIGINT, notificationpackage TEXT, notificationclass TEXT, notificationextras TEXT, cookiedata TEXT, useragent TEXT, referer TEXT, total_bytes INTEGER, current_bytes INTEGER, etag TEXT, uid INTEGER, otheruid INTEGER, title TEXT, description TEXT); ");
            } catch (SQLException e) {
                Log.e(Constants.f15548a, "couldn't create table in downloads database");
                throw e;
            }
        }

        private void d(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS request_headers");
            sQLiteDatabase.execSQL("CREATE TABLE request_headers(id INTEGER PRIMARY KEY AUTOINCREMENT,download_id INTEGER NOT NULL,header TEXT NOT NULL,value TEXT NOT NULL);");
        }
    }

    public boolean onCreate() {
        if (this.mSystemFacade == null) {
            this.mSystemFacade = new RealSystemFacade(getContext());
        }
        this.o = new DatabaseHelper(getContext());
        return true;
    }

    public String getType(Uri uri) {
        switch (f.match(uri)) {
            case 1:
                return d;
            case 2:
                return e;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f3, code lost:
        if (r12.mSystemFacade.a(r9, r7) != false) goto L_0x00f5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri insert(android.net.Uri r13, android.content.ContentValues r14) {
        /*
            r12 = this;
            r12.b(r14)
            android.database.sqlite.SQLiteOpenHelper r0 = r12.o
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            android.content.UriMatcher r1 = f
            int r1 = r1.match(r13)
            r2 = 1
            if (r1 != r2) goto L_0x020b
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r4 = "uri"
            c(r4, r14, r3)
            java.lang.String r4 = "entity"
            c(r4, r14, r3)
            java.lang.String r4 = "no_integrity"
            b(r4, r14, r3)
            java.lang.String r4 = "hint"
            c(r4, r14, r3)
            java.lang.String r4 = "mimetype"
            c(r4, r14, r3)
            java.lang.String r4 = "is_public_api"
            b(r4, r14, r3)
            java.lang.String r4 = "is_public_api"
            java.lang.Boolean r4 = r14.getAsBoolean(r4)
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            r6 = 0
            if (r4 != r5) goto L_0x0042
            r4 = 1
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            java.lang.String r5 = "destination"
            java.lang.Integer r5 = r14.getAsInteger(r5)
            if (r5 == 0) goto L_0x008e
            android.content.Context r7 = r12.getContext()
            java.lang.String r8 = "com.xiaomi.smarthome.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED"
            int r7 = r7.checkCallingPermission(r8)
            r8 = 4
            if (r7 == 0) goto L_0x006d
            int r7 = r5.intValue()
            if (r7 == 0) goto L_0x006d
            int r7 = r5.intValue()
            if (r7 != r8) goto L_0x0065
            goto L_0x006d
        L_0x0065:
            java.lang.SecurityException r13 = new java.lang.SecurityException
            java.lang.String r14 = "unauthorized destination code"
            r13.<init>(r14)
            throw r13
        L_0x006d:
            int r7 = r5.intValue()
            if (r7 != r8) goto L_0x0089
            android.content.Context r7 = r12.getContext()
            java.lang.String r8 = "android.permission.WRITE_EXTERNAL_STORAGE"
            int r9 = android.os.Binder.getCallingPid()
            int r10 = android.os.Binder.getCallingUid()
            java.lang.String r11 = "need WRITE_EXTERNAL_STORAGE permission to use DESTINATION_FILE_URI"
            r7.enforcePermission(r8, r9, r10, r11)
            r12.a((android.content.ContentValues) r14)
        L_0x0089:
            java.lang.String r7 = "destination"
            r3.put(r7, r5)
        L_0x008e:
            java.lang.String r7 = "visibility"
            java.lang.Integer r7 = r14.getAsInteger(r7)
            if (r7 != 0) goto L_0x00b1
            int r7 = r5.intValue()
            if (r7 != 0) goto L_0x00a6
            java.lang.String r7 = "visibility"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r3.put(r7, r8)
            goto L_0x00b6
        L_0x00a6:
            java.lang.String r7 = "visibility"
            r8 = 2
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r3.put(r7, r8)
            goto L_0x00b6
        L_0x00b1:
            java.lang.String r8 = "visibility"
            r3.put(r8, r7)
        L_0x00b6:
            java.lang.String r7 = "control"
            a((java.lang.String) r7, (android.content.ContentValues) r14, (android.content.ContentValues) r3)
            java.lang.String r7 = "status"
            r8 = 190(0xbe, float:2.66E-43)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r3.put(r7, r8)
            java.lang.String r7 = "lastmod"
            com.xiaomi.smarthome.download.SystemFacade r8 = r12.mSystemFacade
            long r8 = r8.a()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r3.put(r7, r8)
            java.lang.String r7 = "notificationpackage"
            java.lang.String r7 = r14.getAsString(r7)
            java.lang.String r8 = "notificationclass"
            java.lang.String r8 = r14.getAsString(r8)
            if (r7 == 0) goto L_0x0101
            if (r8 != 0) goto L_0x00e7
            if (r4 == 0) goto L_0x0101
        L_0x00e7:
            int r9 = android.os.Binder.getCallingUid()
            if (r9 == 0) goto L_0x00f5
            com.xiaomi.smarthome.download.SystemFacade r10 = r12.mSystemFacade     // Catch:{ NameNotFoundException -> 0x0101 }
            boolean r9 = r10.a((int) r9, (java.lang.String) r7)     // Catch:{ NameNotFoundException -> 0x0101 }
            if (r9 == 0) goto L_0x0101
        L_0x00f5:
            java.lang.String r9 = "notificationpackage"
            r3.put(r9, r7)     // Catch:{ NameNotFoundException -> 0x0101 }
            if (r8 == 0) goto L_0x0101
            java.lang.String r7 = "notificationclass"
            r3.put(r7, r8)     // Catch:{ NameNotFoundException -> 0x0101 }
        L_0x0101:
            java.lang.String r7 = "notificationextras"
            c(r7, r14, r3)
            java.lang.String r7 = "cookiedata"
            c(r7, r14, r3)
            java.lang.String r7 = "useragent"
            c(r7, r14, r3)
            java.lang.String r7 = "referer"
            c(r7, r14, r3)
            android.content.Context r7 = r12.getContext()
            java.lang.String r8 = "com.xiaomi.smarthome.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED"
            int r7 = r7.checkCallingPermission(r8)
            if (r7 != 0) goto L_0x0126
            java.lang.String r7 = "otheruid"
            a((java.lang.String) r7, (android.content.ContentValues) r14, (android.content.ContentValues) r3)
        L_0x0126:
            java.lang.String r7 = "uid"
            int r8 = android.os.Binder.getCallingUid()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r3.put(r7, r8)
            int r7 = android.os.Binder.getCallingUid()
            if (r7 != 0) goto L_0x013e
            java.lang.String r7 = "uid"
            a((java.lang.String) r7, (android.content.ContentValues) r14, (android.content.ContentValues) r3)
        L_0x013e:
            java.lang.String r7 = "title"
            java.lang.String r8 = ""
            a((java.lang.String) r7, (android.content.ContentValues) r14, (android.content.ContentValues) r3, (java.lang.String) r8)
            java.lang.String r7 = "description"
            java.lang.String r8 = ""
            a((java.lang.String) r7, (android.content.ContentValues) r14, (android.content.ContentValues) r3, (java.lang.String) r8)
            java.lang.String r7 = "total_bytes"
            r8 = -1
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r3.put(r7, r8)
            java.lang.String r7 = "current_bytes"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            r3.put(r7, r8)
            java.lang.String r7 = "is_visible_in_downloads_ui"
            boolean r7 = r14.containsKey(r7)
            if (r7 == 0) goto L_0x016d
            java.lang.String r2 = "is_visible_in_downloads_ui"
            b(r2, r14, r3)
            goto L_0x0180
        L_0x016d:
            if (r5 == 0) goto L_0x0177
            int r5 = r5.intValue()
            if (r5 != 0) goto L_0x0176
            goto L_0x0177
        L_0x0176:
            r2 = 0
        L_0x0177:
            java.lang.String r5 = "is_visible_in_downloads_ui"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r3.put(r5, r2)
        L_0x0180:
            if (r4 == 0) goto L_0x018c
            java.lang.String r2 = "allowed_network_types"
            a((java.lang.String) r2, (android.content.ContentValues) r14, (android.content.ContentValues) r3)
            java.lang.String r2 = "allow_roaming"
            b(r2, r14, r3)
        L_0x018c:
            java.lang.String r2 = "udn"
            c(r2, r14, r3)
            java.lang.String r2 = "DownloadManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "initiating download with UID "
            r4.append(r5)
            java.lang.String r5 = "uid"
            java.lang.Integer r5 = r3.getAsInteger(r5)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.v(r2, r4)
            java.lang.String r2 = "otheruid"
            boolean r2 = r3.containsKey(r2)
            if (r2 == 0) goto L_0x01d1
            java.lang.String r2 = "DownloadManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "other UID "
            r4.append(r5)
            java.lang.String r5 = "otheruid"
            java.lang.Integer r5 = r3.getAsInteger(r5)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.v(r2, r4)
        L_0x01d1:
            android.content.Context r2 = r12.getContext()
            android.content.Intent r4 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.download.DownloadService> r5 = com.xiaomi.smarthome.download.DownloadService.class
            r4.<init>(r2, r5)
            r2.startService(r4)
            java.lang.String r4 = "downloads"
            r5 = 0
            long r3 = r0.insert(r4, r5, r3)
            r6 = -1
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x01f4
            java.lang.String r13 = "DownloadManager"
            java.lang.String r14 = "couldn't insert into downloads database"
            android.util.Log.d(r13, r14)
            return r5
        L_0x01f4:
            r12.a((android.database.sqlite.SQLiteDatabase) r0, (long) r3, (android.content.ContentValues) r14)
            android.content.Intent r14 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.download.DownloadService> r0 = com.xiaomi.smarthome.download.DownloadService.class
            r14.<init>(r2, r0)
            r2.startService(r14)
            r12.a((android.net.Uri) r13, (int) r1)
            android.net.Uri r13 = com.xiaomi.smarthome.download.Downloads.CONTENT_URI
            android.net.Uri r13 = android.content.ContentUris.withAppendedId(r13, r3)
            return r13
        L_0x020b:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "calling insert on an unknown/invalid URI: "
            r14.append(r0)
            r14.append(r13)
            java.lang.String r14 = r14.toString()
            java.lang.String r0 = "DownloadManager"
            android.util.Log.d(r0, r14)
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown/Invalid URI "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            r14.<init>(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.download.DownloadProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    private void a(ContentValues contentValues) {
        String asString = contentValues.getAsString("hint");
        if (asString != null) {
            Uri parse = Uri.parse(asString);
            String scheme = parse.getScheme();
            if (scheme == null || !scheme.equals("file")) {
                throw new IllegalArgumentException("Not a file URI: " + parse);
            }
            String path = parse.getPath();
            if (path == null) {
                throw new IllegalArgumentException("Invalid file URI: " + parse);
            } else if (!path.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                throw new SecurityException("Destination must be on external storage: " + parse);
            }
        } else {
            throw new IllegalArgumentException("DESTINATION_FILE_URI must include a file URI under COLUMN_FILE_NAME_HINT");
        }
    }

    private void b(ContentValues contentValues) {
        if (getContext().checkCallingOrSelfPermission("com.xiaomi.smarthome.permission.ACCESS_DOWNLOAD_MANAGER") != 0) {
            getContext().enforceCallingOrSelfPermission(Permission.y, "INTERNET permission is required to use the download manager");
            ContentValues contentValues2 = new ContentValues(contentValues);
            a(contentValues2, Downloads.COLUMN_IS_PUBLIC_API, Boolean.TRUE);
            a(contentValues2, "destination", 4);
            if (getContext().checkCallingOrSelfPermission(Downloads.PERMISSION_NO_NOTIFICATION) == 0) {
                a(contentValues2, "visibility", 2, 0);
            } else {
                a(contentValues2, "visibility", 0);
            }
            contentValues2.remove("uri");
            contentValues2.remove("title");
            contentValues2.remove("description");
            contentValues2.remove(Downloads.COLUMN_MIME_TYPE);
            contentValues2.remove("hint");
            contentValues2.remove(Downloads.COLUMN_NOTIFICATION_PACKAGE);
            contentValues2.remove(Downloads.COLUMN_ALLOWED_NETWORK_TYPES);
            contentValues2.remove(Downloads.COLUMN_ALLOW_ROAMING);
            contentValues2.remove(Downloads.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI);
            Iterator<Map.Entry<String, Object>> it = contentValues2.valueSet().iterator();
            while (it.hasNext()) {
                if (((String) it.next().getKey()).startsWith(Downloads.RequestHeaders.f)) {
                    it.remove();
                }
            }
            if (contentValues2.size() > 0) {
                StringBuilder sb = new StringBuilder("Invalid columns in request: ");
                for (Map.Entry<String, Object> key : contentValues2.valueSet()) {
                    sb.append((String) key.getKey());
                }
                throw new SecurityException(sb.toString());
            }
        }
    }

    private void a(ContentValues contentValues, String str, Object... objArr) {
        Object obj = contentValues.get(str);
        contentValues.remove(str);
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            Object obj2 = objArr[i2];
            if (obj != null || obj2 != null) {
                if (obj == null || !obj.equals(obj2)) {
                    i2++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        throw new SecurityException("Invalid value for " + str + ": " + obj);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Helpers.a(str, (Set<String>) n);
        SQLiteDatabase readableDatabase = this.o.getReadableDatabase();
        int match = f.match(uri);
        if (match == -1) {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        } else if (match != 5) {
            SqlSelection a2 = a(uri, str, strArr2, match);
            a(strArr, str, strArr2, str2, readableDatabase);
            Cursor query = readableDatabase.query(c, strArr, a2.a(), a2.b(), (String) null, (String) null, str2);
            if (query != null) {
                query = new ReadOnlyCursorWrapper(query);
            }
            if (query != null) {
                query.setNotificationUri(getContext().getContentResolver(), uri);
                Log.v(Constants.f15548a, "created cursor " + query + " on behalf of " + Binder.getCallingPid());
            }
            return query;
        } else if (strArr == null && str == null && str2 == null) {
            return a(readableDatabase, uri);
        } else {
            throw new UnsupportedOperationException("Request header queries do not support projections, selections or sorting");
        }
    }

    private void a(String[] strArr, String str, String[] strArr2, String str2, SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        sb.append("starting query, database is ");
        if (sQLiteDatabase != null) {
            sb.append("not ");
        }
        sb.append("null; ");
        if (strArr == null) {
            sb.append("projection is null; ");
        } else if (strArr.length == 0) {
            sb.append("projection is empty; ");
        } else {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                sb.append("projection[");
                sb.append(i2);
                sb.append("] is ");
                sb.append(strArr[i2]);
                sb.append("; ");
            }
        }
        sb.append("selection is ");
        sb.append(str);
        sb.append("; ");
        if (strArr2 == null) {
            sb.append("selectionArgs is null; ");
        } else if (strArr2.length == 0) {
            sb.append("selectionArgs is empty; ");
        } else {
            for (int i3 = 0; i3 < strArr2.length; i3++) {
                sb.append("selectionArgs[");
                sb.append(i3);
                sb.append("] is ");
                sb.append(strArr2[i3]);
                sb.append("; ");
            }
        }
        sb.append("sort is ");
        sb.append(str2);
        sb.append(".");
        Log.v(Constants.f15548a, sb.toString());
    }

    private String a(Uri uri) {
        return uri.getPathSegments().get(1);
    }

    private void a(SQLiteDatabase sQLiteDatabase, long j2, ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("download_id", Long.valueOf(j2));
        for (Map.Entry next : contentValues.valueSet()) {
            if (((String) next.getKey()).startsWith(Downloads.RequestHeaders.f)) {
                String obj = next.getValue().toString();
                if (obj.contains(":")) {
                    String[] split = obj.split(":", 2);
                    contentValues2.put("header", split[0].trim());
                    contentValues2.put("value", split[1].trim());
                    sQLiteDatabase.insert(Downloads.RequestHeaders.f15565a, (String) null, contentValues2);
                } else {
                    throw new IllegalArgumentException("Invalid HTTP header line: " + obj);
                }
            }
        }
    }

    private Cursor a(SQLiteDatabase sQLiteDatabase, Uri uri) {
        return new ReadOnlyCursorWrapper(sQLiteDatabase.query(Downloads.RequestHeaders.f15565a, new String[]{"header", "value"}, "download_id=" + a(uri), (String[]) null, (String) null, (String) null, (String) null));
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        Cursor query = sQLiteDatabase.query(c, new String[]{"_id"}, str, strArr, (String) null, (String) null, (String) null, (String) null);
        try {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                long j2 = query.getLong(0);
                sQLiteDatabase.delete(Downloads.RequestHeaders.f15565a, "download_id=" + j2, (String[]) null);
                query.moveToNext();
            }
        } finally {
            query.close();
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Helpers.a(str, (Set<String>) n);
        SQLiteDatabase writableDatabase = this.o.getWritableDatabase();
        int i2 = 0;
        boolean z = true;
        boolean z2 = contentValues.containsKey(Downloads.COLUMN_DELETED) && contentValues.getAsInteger(Downloads.COLUMN_DELETED).intValue() == 1;
        if (Binder.getCallingPid() != Process.myPid()) {
            ContentValues contentValues2 = new ContentValues();
            c(Downloads.COLUMN_APP_DATA, contentValues, contentValues2);
            a("visibility", contentValues, contentValues2);
            Integer asInteger = contentValues.getAsInteger(Downloads.COLUMN_CONTROL);
            if (asInteger != null) {
                contentValues2.put(Downloads.COLUMN_CONTROL, asInteger);
            } else {
                z = z2;
            }
            a(Downloads.COLUMN_CONTROL, contentValues, contentValues2);
            c("title", contentValues, contentValues2);
            c("description", contentValues, contentValues2);
            a(Downloads.COLUMN_DELETED, contentValues, contentValues2);
            contentValues = contentValues2;
        } else {
            String asString = contentValues.getAsString(Downloads._DATA);
            if (asString != null) {
                Cursor query = query(uri, new String[]{"title"}, (String) null, (String[]) null, (String) null);
                if (!query.moveToFirst() || query.getString(0).length() == 0) {
                    contentValues.put("title", new File(asString).getName());
                }
                query.close();
            }
            Integer asInteger2 = contentValues.getAsInteger("status");
            boolean z3 = asInteger2 != null && asInteger2.intValue() == 190;
            boolean containsKey = contentValues.containsKey(Downloads.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT);
            if (!z3 && !containsKey) {
                z = z2;
            }
        }
        int match = f.match(uri);
        switch (match) {
            case 1:
            case 2:
            case 3:
            case 4:
                SqlSelection a2 = a(uri, str, strArr, match);
                if (contentValues.size() > 0) {
                    i2 = writableDatabase.update(c, contentValues, a2.a(), a2.b());
                }
                a(uri, match);
                if (z) {
                    Context context = getContext();
                    context.startService(new Intent(context, DownloadService.class));
                }
                return i2;
            default:
                Log.d(Constants.f15548a, "updating unknown/invalid URI: " + uri);
                throw new UnsupportedOperationException("Cannot update URI: " + uri);
        }
    }

    private void a(Uri uri, int i2) {
        Long valueOf = (i2 == 2 || i2 == 4) ? Long.valueOf(Long.parseLong(a(uri))) : null;
        for (Uri uri2 : l) {
            if (valueOf != null) {
                uri2 = ContentUris.withAppendedId(uri2, valueOf.longValue());
            }
            getContext().getContentResolver().notifyChange(uri2, (ContentObserver) null);
        }
    }

    private SqlSelection a(Uri uri, String str, String[] strArr, int i2) {
        SqlSelection sqlSelection = new SqlSelection();
        sqlSelection.a(str, strArr);
        if (i2 == 2 || i2 == 4) {
            sqlSelection.a("_id = ?", a(uri));
        }
        if ((i2 == 1 || i2 == 2) && getContext().checkCallingPermission(Downloads.PERMISSION_ACCESS_ALL) != 0) {
            sqlSelection.a("uid= ? OR otheruid= ?", Integer.valueOf(Binder.getCallingUid()), Integer.valueOf(Binder.getCallingPid()));
        }
        return sqlSelection;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        Helpers.a(str, (Set<String>) n);
        SQLiteDatabase writableDatabase = this.o.getWritableDatabase();
        int match = f.match(uri);
        switch (match) {
            case 1:
            case 2:
            case 3:
            case 4:
                SqlSelection a2 = a(uri, str, strArr, match);
                a(writableDatabase, a2.a(), a2.b());
                int delete = writableDatabase.delete(c, a2.a(), a2.b());
                a(uri, match);
                return delete;
            default:
                Log.d(Constants.f15548a, "deleting unknown/invalid URI: " + uri);
                throw new UnsupportedOperationException("Cannot delete URI: " + uri);
        }
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        int i2;
        a(uri, str);
        Cursor query = query(uri, new String[]{Downloads._DATA}, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                i2 = query.getCount();
            } catch (Throwable th) {
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } else {
            i2 = 0;
        }
        if (i2 == 1) {
            query.moveToFirst();
            String string = query.getString(0);
            if (query != null) {
                query.close();
            }
            if (string == null) {
                throw new FileNotFoundException("No filename found.");
            } else if (!Helpers.b(string)) {
                throw new FileNotFoundException("Invalid filename.");
            } else if ("r".equals(str)) {
                ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(string), C.ENCODING_PCM_MU_LAW);
                if (open != null) {
                    return open;
                }
                throw new FileNotFoundException("couldn't open file");
            } else {
                throw new FileNotFoundException("Bad mode for " + uri + ": " + str);
            }
        } else if (i2 == 0) {
            throw new FileNotFoundException("No entry for " + uri);
        } else {
            throw new FileNotFoundException("Multiple items at " + uri);
        }
    }

    private void a(Uri uri, String str) {
        Log.v(Constants.f15548a, "openFile uri: " + uri + ", mode: " + str + ", uid: " + Binder.getCallingUid());
        Cursor query = query(Downloads.CONTENT_URI, new String[]{"_id"}, (String) null, (String[]) null, "_id");
        if (query == null) {
            Log.v(Constants.f15548a, "null cursor in openFile");
        } else {
            if (!query.moveToFirst()) {
                Log.v(Constants.f15548a, "empty cursor in openFile");
            } else {
                do {
                    Log.v(Constants.f15548a, "row " + query.getInt(0) + " available");
                } while (query.moveToNext());
            }
            query.close();
        }
        Cursor query2 = query(uri, new String[]{Downloads._DATA}, (String) null, (String[]) null, (String) null);
        if (query2 == null) {
            Log.v(Constants.f15548a, "null cursor in openFile");
            return;
        }
        if (!query2.moveToFirst()) {
            Log.v(Constants.f15548a, "empty cursor in openFile");
        } else {
            String string = query2.getString(0);
            Log.v(Constants.f15548a, "filename in openFile: " + string);
            if (new File(string).isFile()) {
                Log.v(Constants.f15548a, "file exists in openFile");
            }
        }
        query2.close();
    }

    private static final void a(String str, ContentValues contentValues, ContentValues contentValues2) {
        Integer asInteger = contentValues.getAsInteger(str);
        if (asInteger != null) {
            contentValues2.put(str, asInteger);
        }
    }

    private static final void b(String str, ContentValues contentValues, ContentValues contentValues2) {
        Boolean asBoolean = contentValues.getAsBoolean(str);
        if (asBoolean != null) {
            contentValues2.put(str, asBoolean);
        }
    }

    private static final void c(String str, ContentValues contentValues, ContentValues contentValues2) {
        String asString = contentValues.getAsString(str);
        if (asString != null) {
            contentValues2.put(str, asString);
        }
    }

    private static final void a(String str, ContentValues contentValues, ContentValues contentValues2, String str2) {
        c(str, contentValues, contentValues2);
        if (!contentValues2.containsKey(str)) {
            contentValues2.put(str, str2);
        }
    }

    private class ReadOnlyCursorWrapper extends CursorWrapper implements CrossProcessCursor {
        private CrossProcessCursor b;

        public ReadOnlyCursorWrapper(Cursor cursor) {
            super(cursor);
            this.b = (CrossProcessCursor) cursor;
        }

        public boolean a() {
            throw new SecurityException("Download manager cursors are read-only");
        }

        public boolean b() {
            throw new SecurityException("Download manager cursors are read-only");
        }

        public void fillWindow(int i, CursorWindow cursorWindow) {
            this.b.fillWindow(i, cursorWindow);
        }

        public CursorWindow getWindow() {
            return this.b.getWindow();
        }

        public boolean onMove(int i, int i2) {
            return this.b.onMove(i, i2);
        }
    }
}
