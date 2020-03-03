package com.j256.ormlite.misc;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManager {
    private static final String SAVE_POINT_PREFIX = "ORMLITE";
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) TransactionManager.class);
    private static final AtomicInteger savePointCounter = new AtomicInteger();
    private static final ThreadLocal<TransactionLevel> transactionLevelThreadLocal = new ThreadLocal<TransactionLevel>() {
        /* access modifiers changed from: protected */
        public TransactionLevel initialValue() {
            return new TransactionLevel();
        }
    };
    private ConnectionSource connectionSource;

    public TransactionManager() {
    }

    public TransactionManager(ConnectionSource connectionSource2) {
        this.connectionSource = connectionSource2;
        initialize();
    }

    public void initialize() {
        if (this.connectionSource == null) {
            throw new IllegalStateException("dataSource was not set on " + getClass().getSimpleName());
        }
    }

    public <T> T callInTransaction(Callable<T> callable) throws SQLException {
        return callInTransaction(this.connectionSource, callable);
    }

    public <T> T callInTransaction(String str, Callable<T> callable) throws SQLException {
        return callInTransaction(str, this.connectionSource, callable);
    }

    public static <T> T callInTransaction(ConnectionSource connectionSource2, Callable<T> callable) throws SQLException {
        return callInTransaction((String) null, connectionSource2, callable);
    }

    public static <T> T callInTransaction(String str, ConnectionSource connectionSource2, Callable<T> callable) throws SQLException {
        DatabaseConnection readWriteConnection = connectionSource2.getReadWriteConnection(str);
        try {
            return callInTransaction(readWriteConnection, connectionSource2.saveSpecialConnection(readWriteConnection), connectionSource2.getDatabaseType(), callable);
        } finally {
            connectionSource2.clearSpecialConnection(readWriteConnection);
            connectionSource2.releaseConnection(readWriteConnection);
        }
    }

    public static <T> T callInTransaction(DatabaseConnection databaseConnection, DatabaseType databaseType, Callable<T> callable) throws SQLException {
        return callInTransaction(databaseConnection, false, databaseType, callable);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x009f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x00b7 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072 A[Catch:{ SQLException -> 0x00ad, Exception -> 0x0095, all -> 0x0091 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T callInTransaction(com.j256.ormlite.support.DatabaseConnection r5, boolean r6, com.j256.ormlite.db.DatabaseType r7, java.util.concurrent.Callable<T> r8) throws java.sql.SQLException {
        /*
            java.lang.ThreadLocal<com.j256.ormlite.misc.TransactionManager$TransactionLevel> r0 = transactionLevelThreadLocal
            java.lang.Object r0 = r0.get()
            com.j256.ormlite.misc.TransactionManager$TransactionLevel r0 = (com.j256.ormlite.misc.TransactionManager.TransactionLevel) r0
            r1 = 0
            r2 = 0
            r3 = 1
            if (r6 != 0) goto L_0x0019
            boolean r6 = r7.isNestedSavePointsSupported()     // Catch:{ all -> 0x0016 }
            if (r6 == 0) goto L_0x0014
            goto L_0x0019
        L_0x0014:
            r6 = 0
            goto L_0x0069
        L_0x0016:
            r6 = move-exception
            goto L_0x00bf
        L_0x0019:
            boolean r6 = r5.isAutoCommitSupported()     // Catch:{ all -> 0x0016 }
            if (r6 == 0) goto L_0x0035
            boolean r6 = r5.isAutoCommit()     // Catch:{ all -> 0x0016 }
            if (r6 == 0) goto L_0x0035
            r5.setAutoCommit(r2)     // Catch:{ all -> 0x0016 }
            com.j256.ormlite.logger.Logger r6 = logger     // Catch:{ all -> 0x0031 }
            java.lang.String r7 = "had to set auto-commit to false"
            r6.trace(r7)     // Catch:{ all -> 0x0031 }
            r2 = 1
            goto L_0x0035
        L_0x0031:
            r6 = move-exception
            r2 = 1
            goto L_0x00bf
        L_0x0035:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0016 }
            r6.<init>()     // Catch:{ all -> 0x0016 }
            java.lang.String r7 = "ORMLITE"
            r6.append(r7)     // Catch:{ all -> 0x0016 }
            java.util.concurrent.atomic.AtomicInteger r7 = savePointCounter     // Catch:{ all -> 0x0016 }
            int r7 = r7.incrementAndGet()     // Catch:{ all -> 0x0016 }
            r6.append(r7)     // Catch:{ all -> 0x0016 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0016 }
            java.sql.Savepoint r1 = r5.setSavePoint(r6)     // Catch:{ all -> 0x0016 }
            if (r1 != 0) goto L_0x005b
            com.j256.ormlite.logger.Logger r6 = logger     // Catch:{ all -> 0x0016 }
            java.lang.String r7 = "started savePoint transaction"
            r6.trace(r7)     // Catch:{ all -> 0x0016 }
            goto L_0x0067
        L_0x005b:
            com.j256.ormlite.logger.Logger r6 = logger     // Catch:{ all -> 0x0016 }
            java.lang.String r7 = "started savePoint transaction {}"
            java.lang.String r4 = r1.getSavepointName()     // Catch:{ all -> 0x0016 }
            r6.trace((java.lang.String) r7, (java.lang.Object) r4)     // Catch:{ all -> 0x0016 }
        L_0x0067:
            r6 = r2
            r2 = 1
        L_0x0069:
            r0.incrementAndGet()     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
            java.lang.Object r7 = r8.call()     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
            if (r2 == 0) goto L_0x0084
            int r8 = r0.decrementAndGet()     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
            if (r8 > 0) goto L_0x0081
            commit(r5, r1)     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
            java.lang.ThreadLocal<com.j256.ormlite.misc.TransactionManager$TransactionLevel> r8 = transactionLevelThreadLocal     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
            r8.remove()     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
            goto L_0x0084
        L_0x0081:
            release(r5, r1)     // Catch:{ SQLException -> 0x00ad, Exception -> 0x0095 }
        L_0x0084:
            if (r6 == 0) goto L_0x0090
            r5.setAutoCommit(r3)
            com.j256.ormlite.logger.Logger r5 = logger
            java.lang.String r6 = "restored auto-commit to true"
            r5.trace(r6)
        L_0x0090:
            return r7
        L_0x0091:
            r7 = move-exception
            r2 = r6
            r6 = r7
            goto L_0x00bf
        L_0x0095:
            r7 = move-exception
            r0.decrementAndGet()     // Catch:{ all -> 0x0091 }
            if (r2 == 0) goto L_0x00a6
            rollBack(r5, r1)     // Catch:{ SQLException -> 0x009f }
            goto L_0x00a6
        L_0x009f:
            com.j256.ormlite.logger.Logger r8 = logger     // Catch:{ all -> 0x0091 }
            java.lang.String r0 = "after commit exception, rolling back to save-point also threw exception"
            r8.error((java.lang.Throwable) r7, (java.lang.String) r0)     // Catch:{ all -> 0x0091 }
        L_0x00a6:
            java.lang.String r8 = "Transaction callable threw non-SQL exception"
            java.sql.SQLException r7 = com.j256.ormlite.misc.SqlExceptionUtil.create(r8, r7)     // Catch:{ all -> 0x0091 }
            throw r7     // Catch:{ all -> 0x0091 }
        L_0x00ad:
            r7 = move-exception
            r0.decrementAndGet()     // Catch:{ all -> 0x0091 }
            if (r2 == 0) goto L_0x00be
            rollBack(r5, r1)     // Catch:{ SQLException -> 0x00b7 }
            goto L_0x00be
        L_0x00b7:
            com.j256.ormlite.logger.Logger r8 = logger     // Catch:{ all -> 0x0091 }
            java.lang.String r0 = "after commit exception, rolling back to save-point also threw exception"
            r8.error((java.lang.Throwable) r7, (java.lang.String) r0)     // Catch:{ all -> 0x0091 }
        L_0x00be:
            throw r7     // Catch:{ all -> 0x0091 }
        L_0x00bf:
            if (r2 == 0) goto L_0x00cb
            r5.setAutoCommit(r3)
            com.j256.ormlite.logger.Logger r5 = logger
            java.lang.String r7 = "restored auto-commit to true"
            r5.trace(r7)
        L_0x00cb:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.misc.TransactionManager.callInTransaction(com.j256.ormlite.support.DatabaseConnection, boolean, com.j256.ormlite.db.DatabaseType, java.util.concurrent.Callable):java.lang.Object");
    }

    public void setConnectionSource(ConnectionSource connectionSource2) {
        this.connectionSource = connectionSource2;
    }

    private static void commit(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.commit(savepoint);
        if (savepointName == null) {
            logger.trace("committed savePoint transaction");
        } else {
            logger.trace("committed savePoint transaction {}", (Object) savepointName);
        }
    }

    private static void release(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.releaseSavePoint(savepoint);
        if (savepointName == null) {
            logger.trace("released savePoint transaction");
        } else {
            logger.trace("released savePoint transaction {}", (Object) savepointName);
        }
    }

    private static void rollBack(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.rollback(savepoint);
        if (savepointName == null) {
            logger.trace("rolled back savePoint transaction");
        } else {
            logger.trace("rolled back savePoint transaction {}", (Object) savepointName);
        }
    }

    private static class TransactionLevel {
        int counter;

        private TransactionLevel() {
        }

        /* access modifiers changed from: package-private */
        public int incrementAndGet() {
            int i = this.counter + 1;
            this.counter = i;
            return i;
        }

        /* access modifiers changed from: package-private */
        public int decrementAndGet() {
            int i = this.counter - 1;
            this.counter = i;
            return i;
        }
    }
}
