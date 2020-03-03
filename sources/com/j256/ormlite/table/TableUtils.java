package com.j256.ormlite.table;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.IOUtils;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TableUtils {
    private static Logger logger = LoggerFactory.getLogger((Class<?>) TableUtils.class);
    private static final FieldType[] noFieldTypes = new FieldType[0];

    private TableUtils() {
    }

    public static <T> int createTable(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        return doCreateTable(DaoManager.createDao(connectionSource, cls), false);
    }

    public static int createTable(Dao<?, ?> dao) throws SQLException {
        return doCreateTable(dao, false);
    }

    public static <T> int createTableIfNotExists(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        return doCreateTable(DaoManager.createDao(connectionSource, cls), true);
    }

    public static <T> int createTable(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        return doCreateTable(DaoManager.createDao(connectionSource, databaseTableConfig), false);
    }

    public static <T> int createTableIfNotExists(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        return doCreateTable(DaoManager.createDao(connectionSource, databaseTableConfig), true);
    }

    public static <T, ID> List<String> getCreateTableStatements(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        Dao createDao = DaoManager.createDao(connectionSource, cls);
        if (createDao instanceof BaseDaoImpl) {
            return addCreateTableStatements(connectionSource, ((BaseDaoImpl) createDao).getTableInfo(), false);
        }
        return addCreateTableStatements(connectionSource, new TableInfo(connectionSource, (BaseDaoImpl) null, cls), false);
    }

    public static <T, ID> List<String> getCreateTableStatements(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        Dao createDao = DaoManager.createDao(connectionSource, databaseTableConfig);
        if (createDao instanceof BaseDaoImpl) {
            return addCreateTableStatements(connectionSource, ((BaseDaoImpl) createDao).getTableInfo(), false);
        }
        databaseTableConfig.extractFieldTypes(connectionSource);
        return addCreateTableStatements(connectionSource, new TableInfo(connectionSource.getDatabaseType(), (BaseDaoImpl) null, databaseTableConfig), false);
    }

    public static <T, ID> int dropTable(ConnectionSource connectionSource, Class<T> cls, boolean z) throws SQLException {
        return dropTable(DaoManager.createDao(connectionSource, cls), z);
    }

    public static <T, ID> int dropTable(Dao<T, ID> dao, boolean z) throws SQLException {
        ConnectionSource connectionSource = dao.getConnectionSource();
        Class<T> dataClass = dao.getDataClass();
        DatabaseType databaseType = connectionSource.getDatabaseType();
        if (dao instanceof BaseDaoImpl) {
            return doDropTable(databaseType, connectionSource, ((BaseDaoImpl) dao).getTableInfo(), z);
        }
        return doDropTable(databaseType, connectionSource, new TableInfo(connectionSource, (BaseDaoImpl) null, dataClass), z);
    }

    public static <T, ID> int dropTable(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig, boolean z) throws SQLException {
        DatabaseType databaseType = connectionSource.getDatabaseType();
        Dao createDao = DaoManager.createDao(connectionSource, databaseTableConfig);
        if (createDao instanceof BaseDaoImpl) {
            return doDropTable(databaseType, connectionSource, ((BaseDaoImpl) createDao).getTableInfo(), z);
        }
        databaseTableConfig.extractFieldTypes(connectionSource);
        return doDropTable(databaseType, connectionSource, new TableInfo(databaseType, (BaseDaoImpl) null, databaseTableConfig), z);
    }

    public static <T> int clearTable(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        DatabaseType databaseType = connectionSource.getDatabaseType();
        String extractTableName = DatabaseTableConfig.extractTableName(databaseType, cls);
        if (databaseType.isEntityNamesMustBeUpCase()) {
            extractTableName = databaseType.upCaseEntityName(extractTableName);
        }
        return clearTable(connectionSource, extractTableName);
    }

    public static <T> int clearTable(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        return clearTable(connectionSource, databaseTableConfig.getTableName());
    }

    private static <T> int clearTable(ConnectionSource connectionSource, String str) throws SQLException {
        DatabaseType databaseType = connectionSource.getDatabaseType();
        StringBuilder sb = new StringBuilder(48);
        if (databaseType.isTruncateSupported()) {
            sb.append("TRUNCATE TABLE ");
        } else {
            sb.append("DELETE FROM ");
        }
        databaseType.appendEscapedEntityName(sb, str);
        String sb2 = sb.toString();
        logger.info("clearing table '{}' with '{}", (Object) str, (Object) sb2);
        CompiledStatement compiledStatement = null;
        DatabaseConnection readWriteConnection = connectionSource.getReadWriteConnection(str);
        try {
            CompiledStatement compileStatement = readWriteConnection.compileStatement(sb2, StatementBuilder.StatementType.EXECUTE, noFieldTypes, -1, false);
            try {
                int runExecute = compileStatement.runExecute();
                IOUtils.closeThrowSqlException(compileStatement, "compiled statement");
                connectionSource.releaseConnection(readWriteConnection);
                return runExecute;
            } catch (Throwable th) {
                CompiledStatement compiledStatement2 = compileStatement;
                th = th;
                compiledStatement = compiledStatement2;
                IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
                connectionSource.releaseConnection(readWriteConnection);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
            connectionSource.releaseConnection(readWriteConnection);
            throw th;
        }
    }

    private static <T, ID> int doDropTable(DatabaseType databaseType, ConnectionSource connectionSource, TableInfo<T, ID> tableInfo, boolean z) throws SQLException {
        logger.info("dropping table '{}'", (Object) tableInfo.getTableName());
        ArrayList arrayList = new ArrayList();
        addDropIndexStatements(databaseType, tableInfo, arrayList);
        addDropTableStatements(databaseType, tableInfo, arrayList);
        DatabaseConnection readWriteConnection = connectionSource.getReadWriteConnection(tableInfo.getTableName());
        try {
            return doStatements(readWriteConnection, "drop", arrayList, z, databaseType.isCreateTableReturnsNegative(), false);
        } finally {
            connectionSource.releaseConnection(readWriteConnection);
        }
    }

    private static <T, ID> void addDropIndexStatements(DatabaseType databaseType, TableInfo<T, ID> tableInfo, List<String> list) {
        HashSet<String> hashSet = new HashSet<>();
        for (FieldType fieldType : tableInfo.getFieldTypes()) {
            String indexName = fieldType.getIndexName();
            if (indexName != null) {
                hashSet.add(indexName);
            }
            String uniqueIndexName = fieldType.getUniqueIndexName();
            if (uniqueIndexName != null) {
                hashSet.add(uniqueIndexName);
            }
        }
        StringBuilder sb = new StringBuilder(48);
        for (String str : hashSet) {
            logger.info("dropping index '{}' for table '{}", (Object) str, (Object) tableInfo.getTableName());
            sb.append("DROP INDEX ");
            databaseType.appendEscapedEntityName(sb, str);
            list.add(sb.toString());
            sb.setLength(0);
        }
    }

    private static <T, ID> void addCreateIndexStatements(DatabaseType databaseType, TableInfo<T, ID> tableInfo, List<String> list, boolean z, boolean z2) {
        String str;
        HashMap hashMap = new HashMap();
        for (FieldType fieldType : tableInfo.getFieldTypes()) {
            if (z2) {
                str = fieldType.getUniqueIndexName();
            } else {
                str = fieldType.getIndexName();
            }
            if (str != null) {
                List list2 = (List) hashMap.get(str);
                if (list2 == null) {
                    list2 = new ArrayList();
                    hashMap.put(str, list2);
                }
                list2.add(fieldType.getColumnName());
            }
        }
        StringBuilder sb = new StringBuilder(128);
        for (Map.Entry entry : hashMap.entrySet()) {
            logger.info("creating index '{}' for table '{}", entry.getKey(), (Object) tableInfo.getTableName());
            sb.append("CREATE ");
            if (z2) {
                sb.append("UNIQUE ");
            }
            sb.append("INDEX ");
            if (z && databaseType.isCreateIndexIfNotExistsSupported()) {
                sb.append("IF NOT EXISTS ");
            }
            databaseType.appendEscapedEntityName(sb, (String) entry.getKey());
            sb.append(" ON ");
            databaseType.appendEscapedEntityName(sb, tableInfo.getTableName());
            sb.append(" ( ");
            boolean z3 = true;
            for (String str2 : (List) entry.getValue()) {
                if (z3) {
                    z3 = false;
                } else {
                    sb.append(", ");
                }
                databaseType.appendEscapedEntityName(sb, str2);
            }
            sb.append(" )");
            list.add(sb.toString());
            sb.setLength(0);
        }
    }

    private static <T, ID> void addDropTableStatements(DatabaseType databaseType, TableInfo<T, ID> tableInfo, List<String> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (FieldType dropColumnArg : tableInfo.getFieldTypes()) {
            databaseType.dropColumnArg(dropColumnArg, arrayList, arrayList2);
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append("DROP TABLE ");
        databaseType.appendEscapedEntityName(sb, tableInfo.getTableName());
        sb.append(' ');
        list.addAll(arrayList);
        list.add(sb.toString());
        list.addAll(arrayList2);
    }

    private static <T, ID> int doCreateTable(Dao<T, ID> dao, boolean z) throws SQLException {
        if (dao instanceof BaseDaoImpl) {
            return doCreateTable(dao.getConnectionSource(), ((BaseDaoImpl) dao).getTableInfo(), z);
        }
        return doCreateTable(dao.getConnectionSource(), new TableInfo(dao.getConnectionSource(), (BaseDaoImpl) null, dao.getDataClass()), z);
    }

    private static <T, ID> int doCreateTable(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo, boolean z) throws SQLException {
        DatabaseType databaseType = connectionSource.getDatabaseType();
        logger.info("creating table '{}'", (Object) tableInfo.getTableName());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        addCreateTableStatements(databaseType, tableInfo, arrayList, arrayList2, z);
        DatabaseConnection readWriteConnection = connectionSource.getReadWriteConnection(tableInfo.getTableName());
        try {
            return doStatements(readWriteConnection, "create", arrayList, false, databaseType.isCreateTableReturnsNegative(), databaseType.isCreateTableReturnsZero()) + doCreateTestQueries(readWriteConnection, databaseType, arrayList2);
        } finally {
            connectionSource.releaseConnection(readWriteConnection);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        r5 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        logger.info("ignoring {} error '{}' for statement: {}", (java.lang.Object) r11, (java.lang.Object) r5, (java.lang.Object) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r9, "compiled statement");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b7, code lost:
        throw com.j256.ormlite.misc.SqlExceptionUtil.create("SQL statement failed: " + r2, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b8, code lost:
        com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r9, "compiled statement");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00bd, code lost:
        throw r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045 A[SYNTHETIC, Splitter:B:21:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a2 A[SYNTHETIC, Splitter:B:33:0x00a2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int doStatements(com.j256.ormlite.support.DatabaseConnection r10, java.lang.String r11, java.util.Collection<java.lang.String> r12, boolean r13, boolean r14, boolean r15) throws java.sql.SQLException {
        /*
            java.util.Iterator r12 = r12.iterator()
            r0 = 0
            r1 = 0
        L_0x0006:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x00be
            java.lang.Object r2 = r12.next()
            java.lang.String r2 = (java.lang.String) r2
            r9 = 0
            com.j256.ormlite.stmt.StatementBuilder$StatementType r5 = com.j256.ormlite.stmt.StatementBuilder.StatementType.EXECUTE     // Catch:{ SQLException -> 0x0041 }
            com.j256.ormlite.field.FieldType[] r6 = noFieldTypes     // Catch:{ SQLException -> 0x0041 }
            r7 = -1
            r8 = 0
            r3 = r10
            r4 = r2
            com.j256.ormlite.support.CompiledStatement r3 = r3.compileStatement(r4, r5, r6, r7, r8)     // Catch:{ SQLException -> 0x0041 }
            int r4 = r3.runExecute()     // Catch:{ SQLException -> 0x003b, all -> 0x0037 }
            com.j256.ormlite.logger.Logger r5 = logger     // Catch:{ SQLException -> 0x0034, all -> 0x0037 }
            java.lang.String r6 = "executed {} table statement changed {} rows: {}"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLException -> 0x0034, all -> 0x0037 }
            r5.info((java.lang.String) r6, (java.lang.Object) r11, (java.lang.Object) r7, (java.lang.Object) r2)     // Catch:{ SQLException -> 0x0034, all -> 0x0037 }
            java.lang.String r5 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r3, r5)
            goto L_0x0051
        L_0x0034:
            r5 = move-exception
            r9 = r3
            goto L_0x0043
        L_0x0037:
            r10 = move-exception
            r9 = r3
            goto L_0x00b8
        L_0x003b:
            r5 = move-exception
            r9 = r3
            goto L_0x0042
        L_0x003e:
            r10 = move-exception
            goto L_0x00b8
        L_0x0041:
            r5 = move-exception
        L_0x0042:
            r4 = 0
        L_0x0043:
            if (r13 == 0) goto L_0x00a2
            com.j256.ormlite.logger.Logger r3 = logger     // Catch:{ all -> 0x003e }
            java.lang.String r6 = "ignoring {} error '{}' for statement: {}"
            r3.info((java.lang.String) r6, (java.lang.Object) r11, (java.lang.Object) r5, (java.lang.Object) r2)     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r9, r3)
        L_0x0051:
            if (r4 >= 0) goto L_0x007a
            if (r14 == 0) goto L_0x0056
            goto L_0x009e
        L_0x0056:
            java.sql.SQLException r10 = new java.sql.SQLException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "SQL statement "
            r11.append(r12)
            r11.append(r2)
            java.lang.String r12 = " updated "
            r11.append(r12)
            r11.append(r4)
            java.lang.String r12 = " rows, we were expecting >= 0"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x007a:
            if (r4 <= 0) goto L_0x009e
            if (r15 != 0) goto L_0x007f
            goto L_0x009e
        L_0x007f:
            java.sql.SQLException r10 = new java.sql.SQLException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "SQL statement updated "
            r11.append(r12)
            r11.append(r4)
            java.lang.String r12 = " rows, we were expecting == 0: "
            r11.append(r12)
            r11.append(r2)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x009e:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x00a2:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            r10.<init>()     // Catch:{ all -> 0x003e }
            java.lang.String r11 = "SQL statement failed: "
            r10.append(r11)     // Catch:{ all -> 0x003e }
            r10.append(r2)     // Catch:{ all -> 0x003e }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x003e }
            java.sql.SQLException r10 = com.j256.ormlite.misc.SqlExceptionUtil.create(r10, r5)     // Catch:{ all -> 0x003e }
            throw r10     // Catch:{ all -> 0x003e }
        L_0x00b8:
            java.lang.String r11 = "compiled statement"
            com.j256.ormlite.misc.IOUtils.closeThrowSqlException(r9, r11)
            throw r10
        L_0x00be:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.table.TableUtils.doStatements(com.j256.ormlite.support.DatabaseConnection, java.lang.String, java.util.Collection, boolean, boolean, boolean):int");
    }

    private static int doCreateTestQueries(DatabaseConnection databaseConnection, DatabaseType databaseType, List<String> list) throws SQLException {
        int i = 0;
        for (String next : list) {
            CompiledStatement compiledStatement = null;
            try {
                CompiledStatement compileStatement = databaseConnection.compileStatement(next, StatementBuilder.StatementType.SELECT, noFieldTypes, -1, false);
                try {
                    DatabaseResults runQuery = compileStatement.runQuery((ObjectCache) null);
                    int i2 = 0;
                    for (boolean first = runQuery.first(); first; first = runQuery.next()) {
                        i2++;
                    }
                    logger.info("executing create table after-query got {} results: {}", (Object) Integer.valueOf(i2), (Object) next);
                    IOUtils.closeThrowSqlException(compileStatement, "compiled statement");
                    i++;
                } catch (SQLException e) {
                    e = e;
                    compiledStatement = compileStatement;
                    try {
                        throw SqlExceptionUtil.create("executing create table after-query failed: " + next, e);
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    compiledStatement = compileStatement;
                    IOUtils.closeThrowSqlException(compiledStatement, "compiled statement");
                    throw th;
                }
            } catch (SQLException e2) {
                e = e2;
                throw SqlExceptionUtil.create("executing create table after-query failed: " + next, e);
            }
        }
        return i;
    }

    private static <T, ID> List<String> addCreateTableStatements(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo, boolean z) throws SQLException {
        ArrayList arrayList = new ArrayList();
        addCreateTableStatements(connectionSource.getDatabaseType(), tableInfo, arrayList, new ArrayList(), z);
        return arrayList;
    }

    private static <T, ID> void addCreateTableStatements(DatabaseType databaseType, TableInfo<T, ID> tableInfo, List<String> list, List<String> list2, boolean z) throws SQLException {
        FieldType[] fieldTypeArr;
        int i;
        int i2;
        boolean z2;
        DatabaseType databaseType2 = databaseType;
        TableInfo<T, ID> tableInfo2 = tableInfo;
        List<String> list3 = list;
        boolean z3 = z;
        StringBuilder sb = new StringBuilder(256);
        sb.append("CREATE TABLE ");
        if (z3 && databaseType.isCreateIfNotExistsSupported()) {
            sb.append("IF NOT EXISTS ");
        }
        databaseType2.appendEscapedEntityName(sb, tableInfo.getTableName());
        sb.append(" (");
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        FieldType[] fieldTypes = tableInfo.getFieldTypes();
        int length = fieldTypes.length;
        boolean z4 = true;
        int i3 = 0;
        while (i3 < length) {
            FieldType fieldType = fieldTypes[i3];
            if (fieldType.isForeignCollection()) {
                i2 = i3;
                i = length;
                fieldTypeArr = fieldTypes;
            } else {
                if (z4) {
                    z2 = false;
                } else {
                    sb.append(", ");
                    z2 = z4;
                }
                String columnDefinition = fieldType.getColumnDefinition();
                if (columnDefinition == null) {
                    i2 = i3;
                    i = length;
                    fieldTypeArr = fieldTypes;
                    databaseType.appendColumnArg(tableInfo.getTableName(), sb, fieldType, arrayList, arrayList2, arrayList3, list2);
                } else {
                    i2 = i3;
                    i = length;
                    fieldTypeArr = fieldTypes;
                    databaseType2.appendEscapedEntityName(sb, fieldType.getColumnName());
                    sb.append(' ');
                    sb.append(columnDefinition);
                    sb.append(' ');
                }
                z4 = z2;
            }
            i3 = i2 + 1;
            length = i;
            fieldTypes = fieldTypeArr;
            TableInfo<T, ID> tableInfo3 = tableInfo;
        }
        DatabaseType databaseType3 = databaseType;
        ArrayList arrayList4 = arrayList;
        ArrayList arrayList5 = arrayList2;
        ArrayList arrayList6 = arrayList3;
        List<String> list4 = list2;
        databaseType3.addPrimaryKeySql(tableInfo.getFieldTypes(), arrayList4, arrayList5, arrayList6, list4);
        databaseType3.addUniqueComboSql(tableInfo.getFieldTypes(), arrayList4, arrayList5, arrayList6, list4);
        for (String append : arrayList) {
            sb.append(", ");
            sb.append(append);
        }
        sb.append(") ");
        databaseType2.appendCreateTableSuffix(sb);
        list3.addAll(arrayList2);
        list3.add(sb.toString());
        list3.addAll(arrayList3);
        TableInfo<T, ID> tableInfo4 = tableInfo;
        addCreateIndexStatements(databaseType2, tableInfo4, list3, z3, false);
        addCreateIndexStatements(databaseType2, tableInfo4, list3, z3, true);
    }
}
