package org.greenrobot.greendao;

import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.identityscope.IdentityScope;
import org.greenrobot.greendao.identityscope.IdentityScopeLong;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.TableStatements;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;
import rx.schedulers.Schedulers;

public abstract class AbstractDao<T, K> {
    protected final DaoConfig config;
    protected final Database db;
    protected final IdentityScope<K, T> identityScope;
    protected final IdentityScopeLong<T> identityScopeLong;
    protected final boolean isStandardSQLite;
    protected final int pkOrdinal;
    private volatile RxDao<T, K> rxDao;
    private volatile RxDao<T, K> rxDaoPlain;
    protected final AbstractDaoSession session;
    protected final TableStatements statements;

    /* access modifiers changed from: protected */
    public void attachEntity(T t) {
    }

    /* access modifiers changed from: protected */
    public abstract void bindValues(SQLiteStatement sQLiteStatement, T t);

    /* access modifiers changed from: protected */
    public abstract void bindValues(DatabaseStatement databaseStatement, T t);

    /* access modifiers changed from: protected */
    public abstract K getKey(T t);

    /* access modifiers changed from: protected */
    public abstract boolean hasKey(T t);

    /* access modifiers changed from: protected */
    public abstract boolean isEntityUpdateable();

    /* access modifiers changed from: protected */
    public abstract T readEntity(Cursor cursor, int i);

    /* access modifiers changed from: protected */
    public abstract void readEntity(Cursor cursor, T t, int i);

    /* access modifiers changed from: protected */
    public abstract K readKey(Cursor cursor, int i);

    /* access modifiers changed from: protected */
    public abstract K updateKeyAfterInsert(T t, long j);

    public AbstractDao(DaoConfig daoConfig) {
        this(daoConfig, (AbstractDaoSession) null);
    }

    public AbstractDao(DaoConfig daoConfig, AbstractDaoSession abstractDaoSession) {
        this.config = daoConfig;
        this.session = abstractDaoSession;
        this.db = daoConfig.f3527a;
        this.isStandardSQLite = this.db.g() instanceof SQLiteDatabase;
        this.identityScope = daoConfig.b();
        if (this.identityScope instanceof IdentityScopeLong) {
            this.identityScopeLong = (IdentityScopeLong) this.identityScope;
        } else {
            this.identityScopeLong = null;
        }
        this.statements = daoConfig.i;
        this.pkOrdinal = daoConfig.g != null ? daoConfig.g.f3515a : -1;
    }

    public AbstractDaoSession getSession() {
        return this.session;
    }

    /* access modifiers changed from: package-private */
    public TableStatements getStatements() {
        return this.config.i;
    }

    public String getTablename() {
        return this.config.b;
    }

    public Property[] getProperties() {
        return this.config.c;
    }

    public Property getPkProperty() {
        return this.config.g;
    }

    public String[] getAllColumns() {
        return this.config.d;
    }

    public String[] getPkColumns() {
        return this.config.e;
    }

    public String[] getNonPkColumns() {
        return this.config.f;
    }

    public T load(K k) {
        T a2;
        assertSinglePk();
        if (k == null) {
            return null;
        }
        if (this.identityScope != null && (a2 = this.identityScope.a(k)) != null) {
            return a2;
        }
        return loadUniqueAndCloseCursor(this.db.a(this.statements.h(), new String[]{k.toString()}));
    }

    public T loadByRowId(long j) {
        return loadUniqueAndCloseCursor(this.db.a(this.statements.i(), new String[]{Long.toString(j)}));
    }

    /* access modifiers changed from: protected */
    public T loadUniqueAndCloseCursor(Cursor cursor) {
        try {
            return loadUnique(cursor);
        } finally {
            cursor.close();
        }
    }

    /* access modifiers changed from: protected */
    public T loadUnique(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return null;
        }
        if (cursor.isLast()) {
            return loadCurrent(cursor, 0, true);
        }
        throw new DaoException("Expected unique result, but count was " + cursor.getCount());
    }

    public List<T> loadAll() {
        return loadAllAndCloseCursor(this.db.a(this.statements.f(), (String[]) null));
    }

    public boolean detach(T t) {
        if (this.identityScope == null) {
            return false;
        }
        return this.identityScope.c(getKeyVerified(t), t);
    }

    public void detachAll() {
        if (this.identityScope != null) {
            this.identityScope.a();
        }
    }

    /* access modifiers changed from: protected */
    public List<T> loadAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public void insertInTx(Iterable<T> iterable) {
        insertInTx(iterable, isEntityUpdateable());
    }

    public void insertInTx(T... tArr) {
        insertInTx(Arrays.asList(tArr), isEntityUpdateable());
    }

    public void insertInTx(Iterable<T> iterable, boolean z) {
        executeInsertInTx(this.statements.a(), iterable, z);
    }

    public void insertOrReplaceInTx(Iterable<T> iterable, boolean z) {
        executeInsertInTx(this.statements.b(), iterable, z);
    }

    public void insertOrReplaceInTx(Iterable<T> iterable) {
        insertOrReplaceInTx(iterable, isEntityUpdateable());
    }

    public void insertOrReplaceInTx(T... tArr) {
        insertOrReplaceInTx(Arrays.asList(tArr), isEntityUpdateable());
    }

    private void executeInsertInTx(DatabaseStatement databaseStatement, Iterable<T> iterable, boolean z) {
        this.db.a();
        try {
            synchronized (databaseStatement) {
                if (this.identityScope != null) {
                    this.identityScope.b();
                }
                try {
                    if (this.isStandardSQLite) {
                        SQLiteStatement sQLiteStatement = (SQLiteStatement) databaseStatement.f();
                        for (T next : iterable) {
                            bindValues(sQLiteStatement, next);
                            if (z) {
                                updateKeyAfterInsertAndAttach(next, sQLiteStatement.executeInsert(), false);
                            } else {
                                sQLiteStatement.execute();
                            }
                        }
                    } else {
                        for (T next2 : iterable) {
                            bindValues(databaseStatement, next2);
                            if (z) {
                                updateKeyAfterInsertAndAttach(next2, databaseStatement.c(), false);
                            } else {
                                databaseStatement.a();
                            }
                        }
                    }
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.c();
                    }
                }
            }
            this.db.d();
        } finally {
            this.db.b();
        }
    }

    public long insert(T t) {
        return executeInsert(t, this.statements.a(), true);
    }

    public long insertWithoutSettingPk(T t) {
        return executeInsert(t, this.statements.b(), false);
    }

    public long insertOrReplace(T t) {
        return executeInsert(t, this.statements.b(), true);
    }

    private long executeInsert(T t, DatabaseStatement databaseStatement, boolean z) {
        long j;
        if (this.db.e()) {
            j = insertInsideTx(t, databaseStatement);
        } else {
            this.db.a();
            try {
                j = insertInsideTx(t, databaseStatement);
                this.db.d();
            } finally {
                this.db.b();
            }
        }
        if (z) {
            updateKeyAfterInsertAndAttach(t, j, true);
        }
        return j;
    }

    private long insertInsideTx(T t, DatabaseStatement databaseStatement) {
        synchronized (databaseStatement) {
            if (this.isStandardSQLite) {
                SQLiteStatement sQLiteStatement = (SQLiteStatement) databaseStatement.f();
                bindValues(sQLiteStatement, t);
                long executeInsert = sQLiteStatement.executeInsert();
                return executeInsert;
            }
            bindValues(databaseStatement, t);
            long c = databaseStatement.c();
            return c;
        }
    }

    /* access modifiers changed from: protected */
    public void updateKeyAfterInsertAndAttach(T t, long j, boolean z) {
        if (j != -1) {
            attachEntity(updateKeyAfterInsert(t, j), t, z);
        } else {
            DaoLog.d("Could not insert row (executeInsert returned -1)");
        }
    }

    public void save(T t) {
        if (hasKey(t)) {
            update(t);
        } else {
            insert(t);
        }
    }

    public void saveInTx(T... tArr) {
        saveInTx(Arrays.asList(tArr));
    }

    public void saveInTx(Iterable<T> iterable) {
        int i = 0;
        int i2 = 0;
        for (T hasKey : iterable) {
            if (hasKey(hasKey)) {
                i++;
            } else {
                i2++;
            }
        }
        if (i > 0 && i2 > 0) {
            ArrayList arrayList = new ArrayList(i);
            ArrayList arrayList2 = new ArrayList(i2);
            for (T next : iterable) {
                if (hasKey(next)) {
                    arrayList.add(next);
                } else {
                    arrayList2.add(next);
                }
            }
            this.db.a();
            try {
                updateInTx(arrayList);
                insertInTx(arrayList2);
                this.db.d();
            } finally {
                this.db.b();
            }
        } else if (i2 > 0) {
            insertInTx(iterable);
        } else if (i > 0) {
            updateInTx(iterable);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<T> loadAllFromCursor(android.database.Cursor r7) {
        /*
            r6 = this;
            int r0 = r7.getCount()
            if (r0 != 0) goto L_0x000c
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            return r7
        L_0x000c:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
            r2 = 0
            boolean r3 = r7 instanceof android.database.CrossProcessCursor
            r4 = 0
            if (r3 == 0) goto L_0x004d
            r2 = r7
            android.database.CrossProcessCursor r2 = (android.database.CrossProcessCursor) r2
            android.database.CursorWindow r2 = r2.getWindow()
            if (r2 == 0) goto L_0x004d
            int r3 = r2.getNumRows()
            if (r3 != r0) goto L_0x002d
            org.greenrobot.greendao.internal.FastCursor r7 = new org.greenrobot.greendao.internal.FastCursor
            r7.<init>(r2)
            r3 = 1
            goto L_0x004e
        L_0x002d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Window vs. result size: "
            r3.append(r5)
            int r5 = r2.getNumRows()
            r3.append(r5)
            java.lang.String r5 = "/"
            r3.append(r5)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            org.greenrobot.greendao.DaoLog.b((java.lang.String) r3)
        L_0x004d:
            r3 = 0
        L_0x004e:
            boolean r5 = r7.moveToFirst()
            if (r5 == 0) goto L_0x0090
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r5 = r6.identityScope
            if (r5 == 0) goto L_0x0062
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r5 = r6.identityScope
            r5.b()
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r5 = r6.identityScope
            r5.a((int) r0)
        L_0x0062:
            if (r3 != 0) goto L_0x006e
            if (r2 == 0) goto L_0x006e
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r0 = r6.identityScope     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x006e
            r6.loadAllUnlockOnWindowBounds(r7, r2, r1)     // Catch:{ all -> 0x0085 }
            goto L_0x007b
        L_0x006e:
            java.lang.Object r0 = r6.loadCurrent(r7, r4, r4)     // Catch:{ all -> 0x0085 }
            r1.add(r0)     // Catch:{ all -> 0x0085 }
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x0085 }
            if (r0 != 0) goto L_0x006e
        L_0x007b:
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r7 = r6.identityScope
            if (r7 == 0) goto L_0x0090
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r7 = r6.identityScope
            r7.c()
            goto L_0x0090
        L_0x0085:
            r7 = move-exception
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r0 = r6.identityScope
            if (r0 == 0) goto L_0x008f
            org.greenrobot.greendao.identityscope.IdentityScope<K, T> r0 = r6.identityScope
            r0.c()
        L_0x008f:
            throw r7
        L_0x0090:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.greendao.AbstractDao.loadAllFromCursor(android.database.Cursor):java.util.List");
    }

    private void loadAllUnlockOnWindowBounds(Cursor cursor, CursorWindow cursorWindow, List<T> list) {
        int startPosition = cursorWindow.getStartPosition() + cursorWindow.getNumRows();
        int i = 0;
        while (true) {
            list.add(loadCurrent(cursor, 0, false));
            int i2 = i + 1;
            if (i2 >= startPosition) {
                CursorWindow moveToNextUnlocked = moveToNextUnlocked(cursor);
                if (moveToNextUnlocked != null) {
                    startPosition = moveToNextUnlocked.getStartPosition() + moveToNextUnlocked.getNumRows();
                } else {
                    return;
                }
            } else if (!cursor.moveToNext()) {
                return;
            }
            i = i2 + 1;
        }
    }

    private CursorWindow moveToNextUnlocked(Cursor cursor) {
        CursorWindow cursorWindow;
        this.identityScope.c();
        try {
            if (cursor.moveToNext()) {
                cursorWindow = ((CrossProcessCursor) cursor).getWindow();
            } else {
                cursorWindow = null;
            }
            return cursorWindow;
        } finally {
            this.identityScope.b();
        }
    }

    /* access modifiers changed from: protected */
    public final T loadCurrent(Cursor cursor, int i, boolean z) {
        if (this.identityScopeLong != null) {
            if (i != 0 && cursor.isNull(this.pkOrdinal + i)) {
                return null;
            }
            long j = cursor.getLong(this.pkOrdinal + i);
            T a2 = z ? this.identityScopeLong.a(j) : this.identityScopeLong.b(j);
            if (a2 != null) {
                return a2;
            }
            T readEntity = readEntity(cursor, i);
            attachEntity(readEntity);
            if (z) {
                this.identityScopeLong.a(j, readEntity);
            } else {
                this.identityScopeLong.b(j, readEntity);
            }
            return readEntity;
        } else if (this.identityScope != null) {
            Object readKey = readKey(cursor, i);
            if (i != 0 && readKey == null) {
                return null;
            }
            T a3 = z ? this.identityScope.a(readKey) : this.identityScope.b(readKey);
            if (a3 != null) {
                return a3;
            }
            T readEntity2 = readEntity(cursor, i);
            attachEntity(readKey, readEntity2, z);
            return readEntity2;
        } else if (i != 0 && readKey(cursor, i) == null) {
            return null;
        } else {
            T readEntity3 = readEntity(cursor, i);
            attachEntity(readEntity3);
            return readEntity3;
        }
    }

    /* access modifiers changed from: protected */
    public final <O> O loadCurrentOther(AbstractDao<O, ?> abstractDao, Cursor cursor, int i) {
        return abstractDao.loadCurrent(cursor, i, true);
    }

    public List<T> queryRaw(String str, String... strArr) {
        Database database = this.db;
        return loadAllAndCloseCursor(database.a(this.statements.f() + str, strArr));
    }

    public Query<T> queryRawCreate(String str, Object... objArr) {
        return queryRawCreateListArgs(str, Arrays.asList(objArr));
    }

    public Query<T> queryRawCreateListArgs(String str, Collection<Object> collection) {
        return Query.a(this, this.statements.f() + str, collection.toArray());
    }

    public void deleteAll() {
        Database database = this.db;
        database.a("DELETE FROM '" + this.config.b + "'");
        if (this.identityScope != null) {
            this.identityScope.a();
        }
    }

    public void delete(T t) {
        assertSinglePk();
        deleteByKey(getKeyVerified(t));
    }

    public void deleteByKey(K k) {
        assertSinglePk();
        DatabaseStatement c = this.statements.c();
        if (this.db.e()) {
            synchronized (c) {
                deleteByKeyInsideSynchronized(k, c);
            }
        } else {
            this.db.a();
            try {
                synchronized (c) {
                    deleteByKeyInsideSynchronized(k, c);
                }
                this.db.d();
                this.db.b();
            } catch (Throwable th) {
                this.db.b();
                throw th;
            }
        }
        if (this.identityScope != null) {
            this.identityScope.c(k);
        }
    }

    private void deleteByKeyInsideSynchronized(K k, DatabaseStatement databaseStatement) {
        if (k instanceof Long) {
            databaseStatement.a(1, ((Long) k).longValue());
        } else if (k != null) {
            databaseStatement.a(1, k.toString());
        } else {
            throw new DaoException("Cannot delete entity, key is null");
        }
        databaseStatement.a();
    }

    private void deleteInTxInternal(Iterable<T> iterable, Iterable<K> iterable2) {
        ArrayList arrayList;
        assertSinglePk();
        DatabaseStatement c = this.statements.c();
        this.db.a();
        try {
            synchronized (c) {
                if (this.identityScope != null) {
                    this.identityScope.b();
                    arrayList = new ArrayList();
                } else {
                    arrayList = null;
                }
                if (iterable != null) {
                    try {
                        for (T keyVerified : iterable) {
                            Object keyVerified2 = getKeyVerified(keyVerified);
                            deleteByKeyInsideSynchronized(keyVerified2, c);
                            if (arrayList != null) {
                                arrayList.add(keyVerified2);
                            }
                        }
                    } finally {
                        if (this.identityScope != null) {
                            this.identityScope.c();
                        }
                    }
                }
                if (iterable2 != null) {
                    for (K next : iterable2) {
                        deleteByKeyInsideSynchronized(next, c);
                        if (arrayList != null) {
                            arrayList.add(next);
                        }
                    }
                }
                if (this.identityScope != null) {
                    this.identityScope.c();
                }
            }
            this.db.d();
            if (!(arrayList == null || this.identityScope == null)) {
                this.identityScope.a(arrayList);
            }
        } finally {
            this.db.b();
        }
    }

    public void deleteInTx(Iterable<T> iterable) {
        deleteInTxInternal(iterable, (Iterable) null);
    }

    public void deleteInTx(T... tArr) {
        deleteInTxInternal(Arrays.asList(tArr), (Iterable) null);
    }

    public void deleteByKeyInTx(Iterable<K> iterable) {
        deleteInTxInternal((Iterable) null, iterable);
    }

    public void deleteByKeyInTx(K... kArr) {
        deleteInTxInternal((Iterable) null, Arrays.asList(kArr));
    }

    public void refresh(T t) {
        assertSinglePk();
        Object keyVerified = getKeyVerified(t);
        Cursor a2 = this.db.a(this.statements.h(), new String[]{keyVerified.toString()});
        try {
            if (!a2.moveToFirst()) {
                throw new DaoException("Entity does not exist in the database anymore: " + t.getClass() + " with key " + keyVerified);
            } else if (a2.isLast()) {
                readEntity(a2, t, 0);
                attachEntity(keyVerified, t, true);
            } else {
                throw new DaoException("Expected unique result, but count was " + a2.getCount());
            }
        } finally {
            a2.close();
        }
    }

    public void update(T t) {
        assertSinglePk();
        DatabaseStatement d = this.statements.d();
        if (this.db.e()) {
            synchronized (d) {
                if (this.isStandardSQLite) {
                    updateInsideSynchronized(t, (SQLiteStatement) d.f(), true);
                } else {
                    updateInsideSynchronized(t, d, true);
                }
            }
            return;
        }
        this.db.a();
        try {
            synchronized (d) {
                updateInsideSynchronized(t, d, true);
            }
            this.db.d();
            this.db.b();
        } catch (Throwable th) {
            this.db.b();
            throw th;
        }
    }

    public QueryBuilder<T> queryBuilder() {
        return QueryBuilder.a(this);
    }

    /* access modifiers changed from: protected */
    public void updateInsideSynchronized(T t, DatabaseStatement databaseStatement, boolean z) {
        bindValues(databaseStatement, t);
        int length = this.config.d.length + 1;
        Object key = getKey(t);
        if (key instanceof Long) {
            databaseStatement.a(length, ((Long) key).longValue());
        } else if (key != null) {
            databaseStatement.a(length, key.toString());
        } else {
            throw new DaoException("Cannot update entity without key - was it inserted before?");
        }
        databaseStatement.a();
        attachEntity(key, t, z);
    }

    /* access modifiers changed from: protected */
    public void updateInsideSynchronized(T t, SQLiteStatement sQLiteStatement, boolean z) {
        bindValues(sQLiteStatement, t);
        int length = this.config.d.length + 1;
        Object key = getKey(t);
        if (key instanceof Long) {
            sQLiteStatement.bindLong(length, ((Long) key).longValue());
        } else if (key != null) {
            sQLiteStatement.bindString(length, key.toString());
        } else {
            throw new DaoException("Cannot update entity without key - was it inserted before?");
        }
        sQLiteStatement.execute();
        attachEntity(key, t, z);
    }

    /* access modifiers changed from: protected */
    public final void attachEntity(K k, T t, boolean z) {
        attachEntity(t);
        if (this.identityScope != null && k != null) {
            if (z) {
                this.identityScope.a(k, t);
            } else {
                this.identityScope.b(k, t);
            }
        }
    }

    public void updateInTx(Iterable<T> iterable) {
        DatabaseStatement d = this.statements.d();
        this.db.a();
        try {
            synchronized (d) {
                if (this.identityScope != null) {
                    this.identityScope.b();
                }
                try {
                    if (this.isStandardSQLite) {
                        SQLiteStatement sQLiteStatement = (SQLiteStatement) d.f();
                        for (T updateInsideSynchronized : iterable) {
                            updateInsideSynchronized(updateInsideSynchronized, sQLiteStatement, false);
                        }
                    } else {
                        for (T updateInsideSynchronized2 : iterable) {
                            updateInsideSynchronized(updateInsideSynchronized2, d, false);
                        }
                    }
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.c();
                    }
                }
            }
            this.db.d();
            try {
                this.db.b();
            } catch (RuntimeException e) {
                throw e;
            }
        } catch (RuntimeException e2) {
            try {
                this.db.b();
            } catch (RuntimeException e3) {
                DaoLog.d("Could not end transaction (rethrowing initial exception)", e3);
                throw e2;
            }
        } catch (Throwable th) {
            try {
                this.db.b();
                throw th;
            } catch (RuntimeException e4) {
                throw e4;
            }
        }
    }

    public void updateInTx(T... tArr) {
        updateInTx(Arrays.asList(tArr));
    }

    /* access modifiers changed from: protected */
    public void assertSinglePk() {
        if (this.config.e.length != 1) {
            throw new DaoException(this + " (" + this.config.b + ") does not have a single-column primary key");
        }
    }

    public long count() {
        return this.statements.e().b();
    }

    /* access modifiers changed from: protected */
    public K getKeyVerified(T t) {
        K key = getKey(t);
        if (key != null) {
            return key;
        }
        if (t == null) {
            throw new NullPointerException("Entity may not be null");
        }
        throw new DaoException("Entity has no key");
    }

    @Experimental
    public RxDao<T, K> rxPlain() {
        if (this.rxDaoPlain == null) {
            this.rxDaoPlain = new RxDao<>(this);
        }
        return this.rxDaoPlain;
    }

    @Experimental
    public RxDao<T, K> rx() {
        if (this.rxDao == null) {
            this.rxDao = new RxDao<>(this, Schedulers.io());
        }
        return this.rxDao;
    }

    public Database getDatabase() {
        return this.db;
    }
}
