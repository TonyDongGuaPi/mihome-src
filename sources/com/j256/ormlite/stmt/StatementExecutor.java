package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DatabaseResultsMapper;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.dao.RawRowObjectMapper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.IOUtils;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.mapped.MappedCreate;
import com.j256.ormlite.stmt.mapped.MappedDelete;
import com.j256.ormlite.stmt.mapped.MappedDeleteCollection;
import com.j256.ormlite.stmt.mapped.MappedQueryForFieldEq;
import com.j256.ormlite.stmt.mapped.MappedRefresh;
import com.j256.ormlite.stmt.mapped.MappedUpdate;
import com.j256.ormlite.stmt.mapped.MappedUpdateId;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import com.mi.global.shop.model.Tags;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class StatementExecutor<T, ID> implements GenericRowMapper<String[]> {
    private static Logger logger = LoggerFactory.getLogger((Class<?>) StatementExecutor.class);
    private static final FieldType[] noFieldTypes = new FieldType[0];
    private String countStarQuery;
    private final Dao<T, ID> dao;
    private final DatabaseType databaseType;
    private FieldType[] ifExistsFieldTypes;
    private String ifExistsQuery;
    private final ThreadLocal<Boolean> localIsInBatchMode = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean initialValue() {
            return false;
        }
    };
    private MappedDelete<T, ID> mappedDelete;
    private MappedCreate<T, ID> mappedInsert;
    private MappedQueryForFieldEq<T, ID> mappedQueryForId;
    private MappedRefresh<T, ID> mappedRefresh;
    private MappedUpdate<T, ID> mappedUpdate;
    private MappedUpdateId<T, ID> mappedUpdateId;
    private PreparedQuery<T> preparedQueryForAll;
    private RawRowMapper<T> rawRowMapper;
    private final TableInfo<T, ID> tableInfo;

    public StatementExecutor(DatabaseType databaseType2, TableInfo<T, ID> tableInfo2, Dao<T, ID> dao2) {
        this.databaseType = databaseType2;
        this.tableInfo = tableInfo2;
        this.dao = dao2;
    }

    public T queryForId(DatabaseConnection databaseConnection, ID id, ObjectCache objectCache) throws SQLException {
        if (this.mappedQueryForId == null) {
            this.mappedQueryForId = MappedQueryForFieldEq.build(this.databaseType, this.tableInfo, (FieldType) null);
        }
        return this.mappedQueryForId.execute(databaseConnection, id, objectCache);
    }

    public T queryForFirst(DatabaseConnection databaseConnection, PreparedStmt<T> preparedStmt, ObjectCache objectCache) throws SQLException {
        DatabaseResults databaseResults;
        CompiledStatement compile = preparedStmt.compile(databaseConnection, StatementBuilder.StatementType.SELECT);
        try {
            compile.setMaxRows(1);
            databaseResults = compile.runQuery(objectCache);
            try {
                if (databaseResults.first()) {
                    logger.debug("query-for-first of '{}' returned at least 1 result", (Object) preparedStmt.getStatement());
                    T mapRow = preparedStmt.mapRow(databaseResults);
                    IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                    IOUtils.closeThrowSqlException(compile, "compiled statement");
                    return mapRow;
                }
                logger.debug("query-for-first of '{}' returned at 0 results", (Object) preparedStmt.getStatement());
                IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                IOUtils.closeThrowSqlException(compile, "compiled statement");
                return null;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                IOUtils.closeThrowSqlException(compile, "compiled statement");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            databaseResults = null;
            IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
            IOUtils.closeThrowSqlException(compile, "compiled statement");
            throw th;
        }
    }

    public List<T> queryForAll(ConnectionSource connectionSource, ObjectCache objectCache) throws SQLException {
        prepareQueryForAll();
        return query(connectionSource, this.preparedQueryForAll, objectCache);
    }

    public long queryForCountStar(DatabaseConnection databaseConnection) throws SQLException {
        if (this.countStarQuery == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("SELECT COUNT(*) FROM ");
            this.databaseType.appendEscapedEntityName(sb, this.tableInfo.getTableName());
            this.countStarQuery = sb.toString();
        }
        long queryForLong = databaseConnection.queryForLong(this.countStarQuery);
        logger.debug("query of '{}' returned {}", (Object) this.countStarQuery, (Object) Long.valueOf(queryForLong));
        return queryForLong;
    }

    public long queryForLong(DatabaseConnection databaseConnection, PreparedStmt<T> preparedStmt) throws SQLException {
        DatabaseResults databaseResults;
        CompiledStatement compile = preparedStmt.compile(databaseConnection, StatementBuilder.StatementType.SELECT_LONG);
        try {
            databaseResults = compile.runQuery((ObjectCache) null);
            try {
                if (databaseResults.first()) {
                    long j = databaseResults.getLong(0);
                    IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                    IOUtils.closeThrowSqlException(compile, "compiled statement");
                    return j;
                }
                throw new SQLException("No result found in queryForLong: " + preparedStmt.getStatement());
            } catch (Throwable th) {
                th = th;
                IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                IOUtils.closeThrowSqlException(compile, "compiled statement");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            databaseResults = null;
            IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
            IOUtils.closeThrowSqlException(compile, "compiled statement");
            throw th;
        }
    }

    public long queryForLong(DatabaseConnection databaseConnection, String str, String[] strArr) throws SQLException {
        DatabaseResults databaseResults;
        CompiledStatement compiledStatement;
        logger.debug("executing raw query for long: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("query arguments: {}", (Object) strArr);
        }
        try {
            compiledStatement = databaseConnection.compileStatement(str, StatementBuilder.StatementType.SELECT, noFieldTypes, -1, false);
            try {
                assignStatementArguments(compiledStatement, strArr);
                databaseResults = compiledStatement.runQuery((ObjectCache) null);
            } catch (Throwable th) {
                th = th;
                databaseResults = null;
                IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
                throw th;
            }
            try {
                if (databaseResults.first()) {
                    long j = databaseResults.getLong(0);
                    IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                    IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
                    return j;
                }
                throw new SQLException("No result found in queryForLong: " + str);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
                IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compiledStatement = null;
            databaseResults = null;
            IOUtils.closeThrowSqlException(databaseResults, Tags.MiHomeStorage.RESULTS);
            IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
            throw th;
        }
    }

    public List<T> query(ConnectionSource connectionSource, PreparedStmt<T> preparedStmt, ObjectCache objectCache) throws SQLException {
        String str;
        SelectIterator<T, ID> buildIterator = buildIterator((BaseDaoImpl) null, connectionSource, preparedStmt, objectCache, -1);
        try {
            ArrayList arrayList = new ArrayList();
            while (buildIterator.hasNextThrow()) {
                arrayList.add(buildIterator.nextThrow());
            }
            logger.debug("query of '{}' returned {} results", (Object) preparedStmt.getStatement(), (Object) Integer.valueOf(arrayList.size()));
            return arrayList;
        } finally {
            str = "iterator";
            IOUtils.closeThrowSqlException(buildIterator, str);
        }
    }

    public SelectIterator<T, ID> buildIterator(BaseDaoImpl<T, ID> baseDaoImpl, ConnectionSource connectionSource, int i, ObjectCache objectCache) throws SQLException {
        prepareQueryForAll();
        return buildIterator(baseDaoImpl, connectionSource, this.preparedQueryForAll, objectCache, i);
    }

    public GenericRowMapper<T> getSelectStarRowMapper() throws SQLException {
        prepareQueryForAll();
        return this.preparedQueryForAll;
    }

    public RawRowMapper<T> getRawRowMapper() {
        if (this.rawRowMapper == null) {
            this.rawRowMapper = new RawRowMapperImpl(this.tableInfo);
        }
        return this.rawRowMapper;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.j256.ormlite.stmt.SelectIterator<T, ID> buildIterator(com.j256.ormlite.dao.BaseDaoImpl<T, ID> r16, com.j256.ormlite.support.ConnectionSource r17, com.j256.ormlite.stmt.PreparedStmt<T> r18, com.j256.ormlite.dao.ObjectCache r19, int r20) throws java.sql.SQLException {
        /*
            r15 = this;
            r1 = r15
            r11 = r17
            com.j256.ormlite.table.TableInfo<T, ID> r0 = r1.tableInfo
            java.lang.String r0 = r0.getTableName()
            com.j256.ormlite.support.DatabaseConnection r12 = r11.getReadOnlyConnection(r0)
            r13 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r0 = com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT     // Catch:{ all -> 0x003a }
            r5 = r18
            r2 = r20
            com.j256.ormlite.support.CompiledStatement r14 = r5.compile(r12, r0, r2)     // Catch:{ all -> 0x003a }
            com.j256.ormlite.stmt.SelectIterator r0 = new com.j256.ormlite.stmt.SelectIterator     // Catch:{ all -> 0x0038 }
            com.j256.ormlite.table.TableInfo<T, ID> r2 = r1.tableInfo     // Catch:{ all -> 0x0038 }
            java.lang.Class r3 = r2.getDataClass()     // Catch:{ all -> 0x0038 }
            java.lang.String r9 = r18.getStatement()     // Catch:{ all -> 0x0038 }
            r2 = r0
            r4 = r16
            r5 = r18
            r6 = r17
            r7 = r12
            r8 = r14
            r10 = r19
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0038 }
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r13, r2)
            return r0
        L_0x0038:
            r0 = move-exception
            goto L_0x003c
        L_0x003a:
            r0 = move-exception
            r14 = r13
        L_0x003c:
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r14, r2)
            if (r12 == 0) goto L_0x0046
            r11.releaseConnection(r12)
        L_0x0046:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.buildIterator(com.j256.ormlite.dao.BaseDaoImpl, com.j256.ormlite.support.ConnectionSource, com.j256.ormlite.stmt.PreparedStmt, com.j256.ormlite.dao.ObjectCache, int):com.j256.ormlite.stmt.SelectIterator");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.j256.ormlite.dao.GenericRawResults<java.lang.String[]> queryRaw(com.j256.ormlite.support.ConnectionSource r12, java.lang.String r13, java.lang.String[] r14, com.j256.ormlite.dao.ObjectCache r15) throws java.sql.SQLException {
        /*
            r11 = this;
            com.j256.ormlite.logger.Logger r0 = logger
            java.lang.String r1 = "executing raw query for: {}"
            r0.debug((java.lang.String) r1, (java.lang.Object) r13)
            int r0 = r14.length
            if (r0 <= 0) goto L_0x0011
            com.j256.ormlite.logger.Logger r0 = logger
            java.lang.String r1 = "query arguments: {}"
            r0.trace((java.lang.String) r1, (java.lang.Object) r14)
        L_0x0011:
            com.j256.ormlite.table.TableInfo<T, ID> r0 = r11.tableInfo
            java.lang.String r0 = r0.getTableName()
            com.j256.ormlite.support.DatabaseConnection r0 = r12.getReadOnlyConnection(r0)
            r9 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r3 = com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT     // Catch:{ all -> 0x0041 }
            com.j256.ormlite.field.FieldType[] r4 = noFieldTypes     // Catch:{ all -> 0x0041 }
            r5 = -1
            r6 = 0
            r1 = r0
            r2 = r13
            com.j256.ormlite.support.CompiledStatement r10 = r1.compileStatement(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0041 }
            r11.assignStatementArguments(r10, r14)     // Catch:{ all -> 0x003f }
            com.j256.ormlite.stmt.RawResultsImpl r14 = new com.j256.ormlite.stmt.RawResultsImpl     // Catch:{ all -> 0x003f }
            java.lang.Class<java.lang.String[]> r5 = java.lang.String[].class
            r1 = r14
            r2 = r12
            r3 = r0
            r4 = r13
            r6 = r10
            r7 = r11
            r8 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x003f }
            java.lang.String r12 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r9, r12)
            return r14
        L_0x003f:
            r13 = move-exception
            goto L_0x0043
        L_0x0041:
            r13 = move-exception
            r10 = r9
        L_0x0043:
            java.lang.String r14 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r10, r14)
            if (r0 == 0) goto L_0x004d
            r12.releaseConnection(r0)
        L_0x004d:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.queryRaw(com.j256.ormlite.support.ConnectionSource, java.lang.String, java.lang.String[], com.j256.ormlite.dao.ObjectCache):com.j256.ormlite.dao.GenericRawResults");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <UO> com.j256.ormlite.dao.GenericRawResults<UO> queryRaw(com.j256.ormlite.support.ConnectionSource r16, java.lang.String r17, com.j256.ormlite.dao.RawRowMapper<UO> r18, java.lang.String[] r19, com.j256.ormlite.dao.ObjectCache r20) throws java.sql.SQLException {
        /*
            r15 = this;
            r1 = r15
            r10 = r16
            r0 = r19
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "executing raw query for: {}"
            r11 = r17
            r2.debug((java.lang.String) r3, (java.lang.Object) r11)
            int r2 = r0.length
            if (r2 <= 0) goto L_0x0018
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "query arguments: {}"
            r2.trace((java.lang.String) r3, (java.lang.Object) r0)
        L_0x0018:
            com.j256.ormlite.table.TableInfo<T, ID> r2 = r1.tableInfo
            java.lang.String r2 = r2.getTableName()
            com.j256.ormlite.support.DatabaseConnection r12 = r10.getReadOnlyConnection(r2)
            r13 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r6 = com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT     // Catch:{ all -> 0x0052 }
            com.j256.ormlite.field.FieldType[] r7 = noFieldTypes     // Catch:{ all -> 0x0052 }
            r8 = -1
            r9 = 0
            r4 = r12
            r5 = r17
            com.j256.ormlite.support.CompiledStatement r14 = r4.compileStatement(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0052 }
            r15.assignStatementArguments(r14, r0)     // Catch:{ all -> 0x0050 }
            com.j256.ormlite.stmt.RawResultsImpl r0 = new com.j256.ormlite.stmt.RawResultsImpl     // Catch:{ all -> 0x0050 }
            java.lang.Class<java.lang.String[]> r6 = java.lang.String[].class
            com.j256.ormlite.stmt.StatementExecutor$UserRawRowMapper r8 = new com.j256.ormlite.stmt.StatementExecutor$UserRawRowMapper     // Catch:{ all -> 0x0050 }
            r2 = r18
            r8.<init>(r2, r15)     // Catch:{ all -> 0x0050 }
            r2 = r0
            r3 = r16
            r4 = r12
            r5 = r17
            r7 = r14
            r9 = r20
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r13, r2)
            return r0
        L_0x0050:
            r0 = move-exception
            goto L_0x0054
        L_0x0052:
            r0 = move-exception
            r14 = r13
        L_0x0054:
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r14, r2)
            if (r12 == 0) goto L_0x005e
            r10.releaseConnection(r12)
        L_0x005e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.queryRaw(com.j256.ormlite.support.ConnectionSource, java.lang.String, com.j256.ormlite.dao.RawRowMapper, java.lang.String[], com.j256.ormlite.dao.ObjectCache):com.j256.ormlite.dao.GenericRawResults");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <UO> com.j256.ormlite.dao.GenericRawResults<UO> queryRaw(com.j256.ormlite.support.ConnectionSource r16, java.lang.String r17, com.j256.ormlite.field.DataType[] r18, com.j256.ormlite.dao.RawRowObjectMapper<UO> r19, java.lang.String[] r20, com.j256.ormlite.dao.ObjectCache r21) throws java.sql.SQLException {
        /*
            r15 = this;
            r1 = r15
            r10 = r16
            r0 = r20
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "executing raw query for: {}"
            r11 = r17
            r2.debug((java.lang.String) r3, (java.lang.Object) r11)
            int r2 = r0.length
            if (r2 <= 0) goto L_0x0018
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "query arguments: {}"
            r2.trace((java.lang.String) r3, (java.lang.Object) r0)
        L_0x0018:
            com.j256.ormlite.table.TableInfo<T, ID> r2 = r1.tableInfo
            java.lang.String r2 = r2.getTableName()
            com.j256.ormlite.support.DatabaseConnection r12 = r10.getReadOnlyConnection(r2)
            r13 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r6 = com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT     // Catch:{ all -> 0x0054 }
            com.j256.ormlite.field.FieldType[] r7 = noFieldTypes     // Catch:{ all -> 0x0054 }
            r8 = -1
            r9 = 0
            r4 = r12
            r5 = r17
            com.j256.ormlite.support.CompiledStatement r14 = r4.compileStatement(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0054 }
            r15.assignStatementArguments(r14, r0)     // Catch:{ all -> 0x0052 }
            com.j256.ormlite.stmt.RawResultsImpl r0 = new com.j256.ormlite.stmt.RawResultsImpl     // Catch:{ all -> 0x0052 }
            java.lang.Class<java.lang.String[]> r6 = java.lang.String[].class
            com.j256.ormlite.stmt.StatementExecutor$UserRawRowObjectMapper r8 = new com.j256.ormlite.stmt.StatementExecutor$UserRawRowObjectMapper     // Catch:{ all -> 0x0052 }
            r2 = r18
            r3 = r19
            r8.<init>(r3, r2)     // Catch:{ all -> 0x0052 }
            r2 = r0
            r3 = r16
            r4 = r12
            r5 = r17
            r7 = r14
            r9 = r21
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r13, r2)
            return r0
        L_0x0052:
            r0 = move-exception
            goto L_0x0056
        L_0x0054:
            r0 = move-exception
            r14 = r13
        L_0x0056:
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r14, r2)
            if (r12 == 0) goto L_0x0060
            r10.releaseConnection(r12)
        L_0x0060:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.queryRaw(com.j256.ormlite.support.ConnectionSource, java.lang.String, com.j256.ormlite.field.DataType[], com.j256.ormlite.dao.RawRowObjectMapper, java.lang.String[], com.j256.ormlite.dao.ObjectCache):com.j256.ormlite.dao.GenericRawResults");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.j256.ormlite.dao.GenericRawResults<java.lang.Object[]> queryRaw(com.j256.ormlite.support.ConnectionSource r16, java.lang.String r17, com.j256.ormlite.field.DataType[] r18, java.lang.String[] r19, com.j256.ormlite.dao.ObjectCache r20) throws java.sql.SQLException {
        /*
            r15 = this;
            r1 = r15
            r10 = r16
            r0 = r19
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "executing raw query for: {}"
            r11 = r17
            r2.debug((java.lang.String) r3, (java.lang.Object) r11)
            int r2 = r0.length
            if (r2 <= 0) goto L_0x0018
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "query arguments: {}"
            r2.trace((java.lang.String) r3, (java.lang.Object) r0)
        L_0x0018:
            com.j256.ormlite.table.TableInfo<T, ID> r2 = r1.tableInfo
            java.lang.String r2 = r2.getTableName()
            com.j256.ormlite.support.DatabaseConnection r12 = r10.getReadOnlyConnection(r2)
            r13 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r6 = com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT     // Catch:{ all -> 0x0052 }
            com.j256.ormlite.field.FieldType[] r7 = noFieldTypes     // Catch:{ all -> 0x0052 }
            r8 = -1
            r9 = 0
            r4 = r12
            r5 = r17
            com.j256.ormlite.support.CompiledStatement r14 = r4.compileStatement(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0052 }
            r15.assignStatementArguments(r14, r0)     // Catch:{ all -> 0x0050 }
            com.j256.ormlite.stmt.RawResultsImpl r0 = new com.j256.ormlite.stmt.RawResultsImpl     // Catch:{ all -> 0x0050 }
            java.lang.Class<java.lang.Object[]> r6 = java.lang.Object[].class
            com.j256.ormlite.stmt.StatementExecutor$ObjectArrayRowMapper r8 = new com.j256.ormlite.stmt.StatementExecutor$ObjectArrayRowMapper     // Catch:{ all -> 0x0050 }
            r2 = r18
            r8.<init>(r2)     // Catch:{ all -> 0x0050 }
            r2 = r0
            r3 = r16
            r4 = r12
            r5 = r17
            r7 = r14
            r9 = r20
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r13, r2)
            return r0
        L_0x0050:
            r0 = move-exception
            goto L_0x0054
        L_0x0052:
            r0 = move-exception
            r14 = r13
        L_0x0054:
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r14, r2)
            if (r12 == 0) goto L_0x005e
            r10.releaseConnection(r12)
        L_0x005e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.queryRaw(com.j256.ormlite.support.ConnectionSource, java.lang.String, com.j256.ormlite.field.DataType[], java.lang.String[], com.j256.ormlite.dao.ObjectCache):com.j256.ormlite.dao.GenericRawResults");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <UO> com.j256.ormlite.dao.GenericRawResults<UO> queryRaw(com.j256.ormlite.support.ConnectionSource r16, java.lang.String r17, com.j256.ormlite.dao.DatabaseResultsMapper<UO> r18, java.lang.String[] r19, com.j256.ormlite.dao.ObjectCache r20) throws java.sql.SQLException {
        /*
            r15 = this;
            r1 = r15
            r10 = r16
            r0 = r19
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "executing raw query for: {}"
            r11 = r17
            r2.debug((java.lang.String) r3, (java.lang.Object) r11)
            int r2 = r0.length
            if (r2 <= 0) goto L_0x0018
            com.j256.ormlite.logger.Logger r2 = logger
            java.lang.String r3 = "query arguments: {}"
            r2.trace((java.lang.String) r3, (java.lang.Object) r0)
        L_0x0018:
            com.j256.ormlite.table.TableInfo<T, ID> r2 = r1.tableInfo
            java.lang.String r2 = r2.getTableName()
            com.j256.ormlite.support.DatabaseConnection r12 = r10.getReadOnlyConnection(r2)
            r13 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r6 = com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT     // Catch:{ all -> 0x0052 }
            com.j256.ormlite.field.FieldType[] r7 = noFieldTypes     // Catch:{ all -> 0x0052 }
            r8 = -1
            r9 = 0
            r4 = r12
            r5 = r17
            com.j256.ormlite.support.CompiledStatement r14 = r4.compileStatement(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0052 }
            r15.assignStatementArguments(r14, r0)     // Catch:{ all -> 0x0050 }
            com.j256.ormlite.stmt.RawResultsImpl r0 = new com.j256.ormlite.stmt.RawResultsImpl     // Catch:{ all -> 0x0050 }
            java.lang.Class<java.lang.Object[]> r6 = java.lang.Object[].class
            com.j256.ormlite.stmt.StatementExecutor$UserDatabaseResultsMapper r8 = new com.j256.ormlite.stmt.StatementExecutor$UserDatabaseResultsMapper     // Catch:{ all -> 0x0050 }
            r2 = r18
            r8.<init>(r2)     // Catch:{ all -> 0x0050 }
            r2 = r0
            r3 = r16
            r4 = r12
            r5 = r17
            r7 = r14
            r9 = r20
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r13, r2)
            return r0
        L_0x0050:
            r0 = move-exception
            goto L_0x0054
        L_0x0052:
            r0 = move-exception
            r14 = r13
        L_0x0054:
            java.lang.String r2 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r14, r2)
            if (r12 == 0) goto L_0x005e
            r10.releaseConnection(r12)
        L_0x005e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.queryRaw(com.j256.ormlite.support.ConnectionSource, java.lang.String, com.j256.ormlite.dao.DatabaseResultsMapper, java.lang.String[], com.j256.ormlite.dao.ObjectCache):com.j256.ormlite.dao.GenericRawResults");
    }

    public int updateRaw(DatabaseConnection databaseConnection, String str, String[] strArr) throws SQLException {
        String str2;
        logger.debug("running raw update statement: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("update arguments: {}", (Object) strArr);
        }
        CompiledStatement compileStatement = databaseConnection.compileStatement(str, StatementBuilder.StatementType.UPDATE, noFieldTypes, -1, false);
        try {
            assignStatementArguments(compileStatement, strArr);
            return compileStatement.runUpdate();
        } finally {
            str2 = "compiled statement";
            IOUtils.closeThrowSqlException(compileStatement, str2);
        }
    }

    public int executeRawNoArgs(DatabaseConnection databaseConnection, String str) throws SQLException {
        logger.debug("running raw execute statement: {}", (Object) str);
        return databaseConnection.executeStatement(str, -1);
    }

    public int executeRaw(DatabaseConnection databaseConnection, String str, String[] strArr) throws SQLException {
        String str2;
        logger.debug("running raw execute statement: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("execute arguments: {}", (Object) strArr);
        }
        CompiledStatement compileStatement = databaseConnection.compileStatement(str, StatementBuilder.StatementType.EXECUTE, noFieldTypes, -1, false);
        try {
            assignStatementArguments(compileStatement, strArr);
            return compileStatement.runExecute();
        } finally {
            str2 = "compiled statement";
            IOUtils.closeThrowSqlException(compileStatement, str2);
        }
    }

    public int create(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedInsert == null) {
            this.mappedInsert = MappedCreate.build(this.databaseType, this.tableInfo);
        }
        int insert = this.mappedInsert.insert(this.databaseType, databaseConnection, t, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return insert;
    }

    public int update(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedUpdate == null) {
            this.mappedUpdate = MappedUpdate.build(this.databaseType, this.tableInfo);
        }
        int update = this.mappedUpdate.update(databaseConnection, t, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return update;
    }

    public int updateId(DatabaseConnection databaseConnection, T t, ID id, ObjectCache objectCache) throws SQLException {
        if (this.mappedUpdateId == null) {
            this.mappedUpdateId = MappedUpdateId.build(this.databaseType, this.tableInfo);
        }
        int execute = this.mappedUpdateId.execute(databaseConnection, t, id, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return execute;
    }

    public int update(DatabaseConnection databaseConnection, PreparedUpdate<T> preparedUpdate) throws SQLException {
        String str;
        CompiledStatement compile = preparedUpdate.compile(databaseConnection, StatementBuilder.StatementType.UPDATE);
        try {
            int runUpdate = compile.runUpdate();
            if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
                this.dao.notifyChanges();
            }
            return runUpdate;
        } finally {
            str = "compiled statement";
            IOUtils.closeThrowSqlException(compile, str);
        }
    }

    public int refresh(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedRefresh == null) {
            this.mappedRefresh = MappedRefresh.build(this.databaseType, this.tableInfo);
        }
        return this.mappedRefresh.executeRefresh(databaseConnection, t, objectCache);
    }

    public int delete(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedDelete == null) {
            this.mappedDelete = MappedDelete.build(this.databaseType, this.tableInfo);
        }
        int delete = this.mappedDelete.delete(databaseConnection, t, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return delete;
    }

    public int deleteById(DatabaseConnection databaseConnection, ID id, ObjectCache objectCache) throws SQLException {
        if (this.mappedDelete == null) {
            this.mappedDelete = MappedDelete.build(this.databaseType, this.tableInfo);
        }
        int deleteById = this.mappedDelete.deleteById(databaseConnection, id, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return deleteById;
    }

    public int deleteObjects(DatabaseConnection databaseConnection, Collection<T> collection, ObjectCache objectCache) throws SQLException {
        int deleteObjects = MappedDeleteCollection.deleteObjects(this.databaseType, this.tableInfo, databaseConnection, collection, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return deleteObjects;
    }

    public int deleteIds(DatabaseConnection databaseConnection, Collection<ID> collection, ObjectCache objectCache) throws SQLException {
        int deleteIds = MappedDeleteCollection.deleteIds(this.databaseType, this.tableInfo, databaseConnection, collection, objectCache);
        if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
            this.dao.notifyChanges();
        }
        return deleteIds;
    }

    public int delete(DatabaseConnection databaseConnection, PreparedDelete<T> preparedDelete) throws SQLException {
        String str;
        CompiledStatement compile = preparedDelete.compile(databaseConnection, StatementBuilder.StatementType.DELETE);
        try {
            int runUpdate = compile.runUpdate();
            if (this.dao != null && !this.localIsInBatchMode.get().booleanValue()) {
                this.dao.notifyChanges();
            }
            return runUpdate;
        } finally {
            str = "compiled statement";
            IOUtils.closeThrowSqlException(compile, str);
        }
    }

    public <CT> CT callBatchTasks(ConnectionSource connectionSource, Callable<CT> callable) throws SQLException {
        CT doCallBatchTasks;
        if (!connectionSource.isSingleConnection(this.tableInfo.getTableName())) {
            return doCallBatchTasks(connectionSource, callable);
        }
        synchronized (this) {
            doCallBatchTasks = doCallBatchTasks(connectionSource, callable);
        }
        return doCallBatchTasks;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <CT> CT doCallBatchTasks(com.j256.ormlite.support.ConnectionSource r5, java.util.concurrent.Callable<CT> r6) throws java.sql.SQLException {
        /*
            r4 = this;
            com.j256.ormlite.table.TableInfo<T, ID> r0 = r4.tableInfo
            java.lang.String r0 = r0.getTableName()
            com.j256.ormlite.support.DatabaseConnection r0 = r5.getReadWriteConnection(r0)
            r1 = 0
            java.lang.ThreadLocal<java.lang.Boolean> r2 = r4.localIsInBatchMode     // Catch:{ all -> 0x003a }
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x003a }
            r2.set(r3)     // Catch:{ all -> 0x003a }
            boolean r2 = r5.saveSpecialConnection(r0)     // Catch:{ all -> 0x003a }
            java.lang.Object r6 = r4.doCallBatchTasks(r0, r2, r6)     // Catch:{ all -> 0x0038 }
            if (r2 == 0) goto L_0x0022
            r5.clearSpecialConnection(r0)
        L_0x0022:
            r5.releaseConnection(r0)
            java.lang.ThreadLocal<java.lang.Boolean> r5 = r4.localIsInBatchMode
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r5.set(r0)
            com.j256.ormlite.dao.Dao<T, ID> r5 = r4.dao
            if (r5 == 0) goto L_0x0037
            com.j256.ormlite.dao.Dao<T, ID> r5 = r4.dao
            r5.notifyChanges()
        L_0x0037:
            return r6
        L_0x0038:
            r6 = move-exception
            goto L_0x003c
        L_0x003a:
            r6 = move-exception
            r2 = 0
        L_0x003c:
            if (r2 == 0) goto L_0x0041
            r5.clearSpecialConnection(r0)
        L_0x0041:
            r5.releaseConnection(r0)
            java.lang.ThreadLocal<java.lang.Boolean> r5 = r4.localIsInBatchMode
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r5.set(r0)
            com.j256.ormlite.dao.Dao<T, ID> r5 = r4.dao
            if (r5 == 0) goto L_0x0056
            com.j256.ormlite.dao.Dao<T, ID> r5 = r4.dao
            r5.notifyChanges()
        L_0x0056:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.doCallBatchTasks(com.j256.ormlite.support.ConnectionSource, java.util.concurrent.Callable):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <CT> CT doCallBatchTasks(com.j256.ormlite.support.DatabaseConnection r4, boolean r5, java.util.concurrent.Callable<CT> r6) throws java.sql.SQLException {
        /*
            r3 = this;
            com.j256.ormlite.db.DatabaseType r0 = r3.databaseType
            boolean r0 = r0.isBatchUseTransaction()
            if (r0 == 0) goto L_0x000f
            com.j256.ormlite.db.DatabaseType r0 = r3.databaseType
            java.lang.Object r4 = com.j256.ormlite.misc.TransactionManager.callInTransaction(r4, r5, r0, r6)
            return r4
        L_0x000f:
            r5 = 0
            r0 = 1
            boolean r1 = r4.isAutoCommitSupported()     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0033
            boolean r1 = r4.isAutoCommit()     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0033
            r4.setAutoCommit(r5)     // Catch:{ all -> 0x0054 }
            com.j256.ormlite.logger.Logger r5 = logger     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "disabled auto-commit on table {} before batch tasks"
            com.j256.ormlite.table.TableInfo<T, ID> r2 = r3.tableInfo     // Catch:{ all -> 0x002f }
            java.lang.String r2 = r2.getTableName()     // Catch:{ all -> 0x002f }
            r5.debug((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x002f }
            r5 = 1
            goto L_0x0033
        L_0x002f:
            r5 = move-exception
            r6 = r5
            r5 = 1
            goto L_0x0055
        L_0x0033:
            java.lang.Object r6 = r6.call()     // Catch:{ SQLException -> 0x0052, Exception -> 0x004a }
            if (r5 == 0) goto L_0x0049
            r4.setAutoCommit(r0)
            com.j256.ormlite.logger.Logger r4 = logger
            java.lang.String r5 = "re-enabled auto-commit on table {} after batch tasks"
            com.j256.ormlite.table.TableInfo<T, ID> r0 = r3.tableInfo
            java.lang.String r0 = r0.getTableName()
            r4.debug((java.lang.String) r5, (java.lang.Object) r0)
        L_0x0049:
            return r6
        L_0x004a:
            r6 = move-exception
            java.lang.String r1 = "Batch tasks callable threw non-SQL exception"
            java.sql.SQLException r6 = com.j256.ormlite.misc.SqlExceptionUtil.create(r1, r6)     // Catch:{ all -> 0x0054 }
            throw r6     // Catch:{ all -> 0x0054 }
        L_0x0052:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r6 = move-exception
        L_0x0055:
            if (r5 == 0) goto L_0x0067
            r4.setAutoCommit(r0)
            com.j256.ormlite.logger.Logger r4 = logger
            com.j256.ormlite.table.TableInfo<T, ID> r5 = r3.tableInfo
            java.lang.String r5 = r5.getTableName()
            java.lang.String r0 = "re-enabled auto-commit on table {} after batch tasks"
            r4.debug((java.lang.String) r0, (java.lang.Object) r5)
        L_0x0067:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.StatementExecutor.doCallBatchTasks(com.j256.ormlite.support.DatabaseConnection, boolean, java.util.concurrent.Callable):java.lang.Object");
    }

    public String[] mapRow(DatabaseResults databaseResults) throws SQLException {
        int columnCount = databaseResults.getColumnCount();
        String[] strArr = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            strArr[i] = databaseResults.getString(i);
        }
        return strArr;
    }

    public boolean ifExists(DatabaseConnection databaseConnection, ID id) throws SQLException {
        if (this.ifExistsQuery == null) {
            QueryBuilder queryBuilder = new QueryBuilder(this.databaseType, this.tableInfo, this.dao);
            queryBuilder.selectRaw("COUNT(*)");
            queryBuilder.where().eq(this.tableInfo.getIdField().getColumnName(), new SelectArg());
            this.ifExistsQuery = queryBuilder.prepareStatementString();
            this.ifExistsFieldTypes = new FieldType[]{this.tableInfo.getIdField()};
        }
        Object convertJavaFieldToSqlArgValue = this.tableInfo.getIdField().convertJavaFieldToSqlArgValue(id);
        long queryForLong = databaseConnection.queryForLong(this.ifExistsQuery, new Object[]{convertJavaFieldToSqlArgValue}, this.ifExistsFieldTypes);
        logger.debug("query of '{}' returned {}", (Object) this.ifExistsQuery, (Object) Long.valueOf(queryForLong));
        if (queryForLong != 0) {
            return true;
        }
        return false;
    }

    private void assignStatementArguments(CompiledStatement compiledStatement, String[] strArr) throws SQLException {
        for (int i = 0; i < strArr.length; i++) {
            compiledStatement.setObject(i, strArr[i], SqlType.STRING);
        }
    }

    private void prepareQueryForAll() throws SQLException {
        if (this.preparedQueryForAll == null) {
            this.preparedQueryForAll = new QueryBuilder(this.databaseType, this.tableInfo, this.dao).prepare();
        }
    }

    private static class UserRawRowMapper<UO> implements GenericRowMapper<UO> {
        private String[] columnNames;
        private final RawRowMapper<UO> mapper;
        private final GenericRowMapper<String[]> stringRowMapper;

        public UserRawRowMapper(RawRowMapper<UO> rawRowMapper, GenericRowMapper<String[]> genericRowMapper) {
            this.mapper = rawRowMapper;
            this.stringRowMapper = genericRowMapper;
        }

        public UO mapRow(DatabaseResults databaseResults) throws SQLException {
            return this.mapper.mapRow(getColumnNames(databaseResults), this.stringRowMapper.mapRow(databaseResults));
        }

        private String[] getColumnNames(DatabaseResults databaseResults) throws SQLException {
            if (this.columnNames != null) {
                return this.columnNames;
            }
            this.columnNames = databaseResults.getColumnNames();
            return this.columnNames;
        }
    }

    private static class UserRawRowObjectMapper<UO> implements GenericRowMapper<UO> {
        private String[] columnNames;
        private final DataType[] columnTypes;
        private final RawRowObjectMapper<UO> mapper;

        public UserRawRowObjectMapper(RawRowObjectMapper<UO> rawRowObjectMapper, DataType[] dataTypeArr) {
            this.mapper = rawRowObjectMapper;
            this.columnTypes = dataTypeArr;
        }

        public UO mapRow(DatabaseResults databaseResults) throws SQLException {
            int columnCount = databaseResults.getColumnCount();
            Object[] objArr = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                if (i >= this.columnTypes.length) {
                    objArr[i] = null;
                } else {
                    objArr[i] = this.columnTypes[i].getDataPersister().resultToJava((FieldType) null, databaseResults, i);
                }
            }
            return this.mapper.mapRow(getColumnNames(databaseResults), this.columnTypes, objArr);
        }

        private String[] getColumnNames(DatabaseResults databaseResults) throws SQLException {
            if (this.columnNames != null) {
                return this.columnNames;
            }
            this.columnNames = databaseResults.getColumnNames();
            return this.columnNames;
        }
    }

    private static class ObjectArrayRowMapper implements GenericRowMapper<Object[]> {
        private final DataType[] columnTypes;

        public ObjectArrayRowMapper(DataType[] dataTypeArr) {
            this.columnTypes = dataTypeArr;
        }

        public Object[] mapRow(DatabaseResults databaseResults) throws SQLException {
            DataType dataType;
            int columnCount = databaseResults.getColumnCount();
            Object[] objArr = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                if (i >= this.columnTypes.length) {
                    dataType = DataType.STRING;
                } else {
                    dataType = this.columnTypes[i];
                }
                objArr[i] = dataType.getDataPersister().resultToJava((FieldType) null, databaseResults, i);
            }
            return objArr;
        }
    }

    private static class UserDatabaseResultsMapper<UO> implements GenericRowMapper<UO> {
        public final DatabaseResultsMapper<UO> mapper;

        private UserDatabaseResultsMapper(DatabaseResultsMapper<UO> databaseResultsMapper) {
            this.mapper = databaseResultsMapper;
        }

        public UO mapRow(DatabaseResults databaseResults) throws SQLException {
            return this.mapper.mapRow(databaseResults);
        }
    }
}
