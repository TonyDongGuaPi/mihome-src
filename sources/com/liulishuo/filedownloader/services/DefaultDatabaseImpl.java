package com.liulishuo.filedownloader.services;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DefaultDatabaseImpl implements FileDownloadDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6447a = "filedownloader";
    public static final String b = "filedownloaderConnection";
    /* access modifiers changed from: private */
    public final SQLiteDatabase c = new DefaultDatabaseOpenHelper(FileDownloadHelper.a()).getWritableDatabase();
    /* access modifiers changed from: private */
    public final SparseArray<FileDownloadModel> d = new SparseArray<>();

    public void e(int i) {
    }

    public FileDownloadModel a(int i) {
        return this.d.get(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.liulishuo.filedownloader.model.ConnectionModel> b(int r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r8.c     // Catch:{ all -> 0x0075 }
            java.lang.String r3 = "SELECT * FROM %s WHERE %s = ?"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0075 }
            java.lang.String r5 = "filedownloaderConnection"
            r6 = 0
            r4[r6] = r5     // Catch:{ all -> 0x0075 }
            java.lang.String r5 = "id"
            r7 = 1
            r4[r7] = r5     // Catch:{ all -> 0x0075 }
            java.lang.String r3 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0075 }
            java.lang.String[] r4 = new java.lang.String[r7]     // Catch:{ all -> 0x0075 }
            java.lang.String r5 = java.lang.Integer.toString(r9)     // Catch:{ all -> 0x0075 }
            r4[r6] = r5     // Catch:{ all -> 0x0075 }
            android.database.Cursor r2 = r2.rawQuery(r3, r4)     // Catch:{ all -> 0x0075 }
        L_0x0027:
            boolean r1 = r2.moveToNext()     // Catch:{ all -> 0x0073 }
            if (r1 == 0) goto L_0x006d
            com.liulishuo.filedownloader.model.ConnectionModel r1 = new com.liulishuo.filedownloader.model.ConnectionModel     // Catch:{ all -> 0x0073 }
            r1.<init>()     // Catch:{ all -> 0x0073 }
            r1.a((int) r9)     // Catch:{ all -> 0x0073 }
            java.lang.String r3 = "connectionIndex"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x0073 }
            int r3 = r2.getInt(r3)     // Catch:{ all -> 0x0073 }
            r1.b((int) r3)     // Catch:{ all -> 0x0073 }
            java.lang.String r3 = "startOffset"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x0073 }
            long r3 = r2.getLong(r3)     // Catch:{ all -> 0x0073 }
            r1.a((long) r3)     // Catch:{ all -> 0x0073 }
            java.lang.String r3 = "currentOffset"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x0073 }
            long r3 = r2.getLong(r3)     // Catch:{ all -> 0x0073 }
            r1.b((long) r3)     // Catch:{ all -> 0x0073 }
            java.lang.String r3 = "endOffset"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x0073 }
            long r3 = r2.getLong(r3)     // Catch:{ all -> 0x0073 }
            r1.c(r3)     // Catch:{ all -> 0x0073 }
            r0.add(r1)     // Catch:{ all -> 0x0073 }
            goto L_0x0027
        L_0x006d:
            if (r2 == 0) goto L_0x0072
            r2.close()
        L_0x0072:
            return r0
        L_0x0073:
            r9 = move-exception
            goto L_0x0077
        L_0x0075:
            r9 = move-exception
            r2 = r1
        L_0x0077:
            if (r2 == 0) goto L_0x007c
            r2.close()
        L_0x007c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.DefaultDatabaseImpl.b(int):java.util.List");
    }

    public void c(int i) {
        SQLiteDatabase sQLiteDatabase = this.c;
        sQLiteDatabase.execSQL("DELETE FROM filedownloaderConnection WHERE id = " + i);
    }

    public void a(ConnectionModel connectionModel) {
        this.c.insert(b, (String) null, connectionModel.f());
    }

    public void a(int i, int i2, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConnectionModel.d, Long.valueOf(j));
        this.c.update(b, contentValues, "id = ? AND connectionIndex = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
    }

    public void a(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.m, Integer.valueOf(i2));
        this.c.update(f6447a, contentValues, "_id = ? ", new String[]{Integer.toString(i)});
    }

    public void a(FileDownloadModel fileDownloadModel) {
        this.d.put(fileDownloadModel.a(), fileDownloadModel);
        this.c.insert(f6447a, (String) null, fileDownloadModel.p());
    }

    public void b(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.d(this, "update but model == null!", new Object[0]);
        } else if (a(fileDownloadModel.a()) != null) {
            this.d.remove(fileDownloadModel.a());
            this.d.put(fileDownloadModel.a(), fileDownloadModel);
            ContentValues p = fileDownloadModel.p();
            this.c.update(f6447a, p, "_id = ? ", new String[]{String.valueOf(fileDownloadModel.a())});
        } else {
            a(fileDownloadModel);
        }
    }

    public boolean d(int i) {
        this.d.remove(i);
        return this.c.delete(f6447a, "_id = ?", new String[]{String.valueOf(i)}) != 0;
    }

    public void a() {
        this.d.clear();
        this.c.delete(f6447a, (String) null, (String[]) null);
        this.c.delete(b, (String) null, (String[]) null);
    }

    public void a(int i, String str, long j, long j2, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.i, Long.valueOf(j));
        contentValues.put("total", Long.valueOf(j2));
        contentValues.put("etag", str);
        contentValues.put(FileDownloadModel.m, Integer.valueOf(i2));
        a(i, contentValues);
    }

    public void a(int i, long j, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (byte) 2);
        contentValues.put("total", Long.valueOf(j));
        contentValues.put("etag", str);
        contentValues.put("filename", str2);
        a(i, contentValues);
    }

    public void a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (byte) 3);
        contentValues.put(FileDownloadModel.i, Long.valueOf(j));
        a(i, contentValues);
    }

    public void a(int i, Throwable th, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("errMsg", th.toString());
        contentValues.put("status", (byte) -1);
        contentValues.put(FileDownloadModel.i, Long.valueOf(j));
        a(i, contentValues);
    }

    public void a(int i, Throwable th) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("errMsg", th.toString());
        contentValues.put("status", (byte) 5);
        a(i, contentValues);
    }

    public void b(int i, long j) {
        d(i);
    }

    public void c(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (byte) -2);
        contentValues.put(FileDownloadModel.i, Long.valueOf(j));
        a(i, contentValues);
    }

    public FileDownloadDatabase.Maintainer b() {
        return new Maintainer();
    }

    private void a(int i, ContentValues contentValues) {
        this.c.update(f6447a, contentValues, "_id = ? ", new String[]{String.valueOf(i)});
    }

    class Maintainer implements FileDownloadDatabase.Maintainer {
        private final SparseArray<FileDownloadModel> b = new SparseArray<>();
        private MaintainerIterator c;

        public void a(FileDownloadModel fileDownloadModel) {
        }

        Maintainer() {
        }

        public Iterator<FileDownloadModel> iterator() {
            MaintainerIterator maintainerIterator = new MaintainerIterator();
            this.c = maintainerIterator;
            return maintainerIterator;
        }

        public void a() {
            if (this.c != null) {
                this.c.b();
            }
            int size = this.b.size();
            if (size >= 0) {
                DefaultDatabaseImpl.this.c.beginTransaction();
                int i = 0;
                while (i < size) {
                    try {
                        int keyAt = this.b.keyAt(i);
                        FileDownloadModel fileDownloadModel = this.b.get(keyAt);
                        DefaultDatabaseImpl.this.c.delete(DefaultDatabaseImpl.f6447a, "_id = ?", new String[]{String.valueOf(keyAt)});
                        DefaultDatabaseImpl.this.c.insert(DefaultDatabaseImpl.f6447a, (String) null, fileDownloadModel.p());
                        if (fileDownloadModel.n() > 1) {
                            List<ConnectionModel> b2 = DefaultDatabaseImpl.this.b(keyAt);
                            if (b2.size() > 0) {
                                DefaultDatabaseImpl.this.c.delete(DefaultDatabaseImpl.b, "id = ?", new String[]{String.valueOf(keyAt)});
                                for (ConnectionModel next : b2) {
                                    next.a(fileDownloadModel.a());
                                    DefaultDatabaseImpl.this.c.insert(DefaultDatabaseImpl.b, (String) null, next.f());
                                }
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        DefaultDatabaseImpl.this.c.endTransaction();
                        throw th;
                    }
                }
                DefaultDatabaseImpl.this.c.setTransactionSuccessful();
                DefaultDatabaseImpl.this.c.endTransaction();
            }
        }

        public void b(FileDownloadModel fileDownloadModel) {
            DefaultDatabaseImpl.this.d.put(fileDownloadModel.a(), fileDownloadModel);
        }

        public void a(int i, FileDownloadModel fileDownloadModel) {
            this.b.put(i, fileDownloadModel);
        }
    }

    class MaintainerIterator implements Iterator<FileDownloadModel> {
        private final Cursor b;
        private final List<Integer> c = new ArrayList();
        private int d;

        MaintainerIterator() {
            this.b = DefaultDatabaseImpl.this.c.rawQuery("SELECT * FROM filedownloader", (String[]) null);
        }

        public boolean hasNext() {
            return this.b.moveToNext();
        }

        /* renamed from: a */
        public FileDownloadModel next() {
            FileDownloadModel fileDownloadModel = new FileDownloadModel();
            fileDownloadModel.a(this.b.getInt(this.b.getColumnIndex("_id")));
            fileDownloadModel.a(this.b.getString(this.b.getColumnIndex("url")));
            String string = this.b.getString(this.b.getColumnIndex("path"));
            boolean z = true;
            if (this.b.getShort(this.b.getColumnIndex(FileDownloadModel.f)) != 1) {
                z = false;
            }
            fileDownloadModel.a(string, z);
            fileDownloadModel.a((byte) this.b.getShort(this.b.getColumnIndex("status")));
            fileDownloadModel.a(this.b.getLong(this.b.getColumnIndex(FileDownloadModel.i)));
            fileDownloadModel.c(this.b.getLong(this.b.getColumnIndex("total")));
            fileDownloadModel.c(this.b.getString(this.b.getColumnIndex("errMsg")));
            fileDownloadModel.b(this.b.getString(this.b.getColumnIndex("etag")));
            fileDownloadModel.d(this.b.getString(this.b.getColumnIndex("filename")));
            fileDownloadModel.b(this.b.getInt(this.b.getColumnIndex(FileDownloadModel.m)));
            this.d = fileDownloadModel.a();
            return fileDownloadModel;
        }

        public void remove() {
            this.c.add(Integer.valueOf(this.d));
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.b.close();
            if (!this.c.isEmpty()) {
                String join = TextUtils.join(", ", this.c);
                if (FileDownloadLog.f6465a) {
                    FileDownloadLog.c(this, "delete %s", join);
                }
                DefaultDatabaseImpl.this.c.execSQL(FileDownloadUtils.a("DELETE FROM %s WHERE %s IN (%s);", DefaultDatabaseImpl.f6447a, "_id", join));
                DefaultDatabaseImpl.this.c.execSQL(FileDownloadUtils.a("DELETE FROM %s WHERE %s IN (%s);", DefaultDatabaseImpl.b, "id", join));
            }
        }
    }
}
