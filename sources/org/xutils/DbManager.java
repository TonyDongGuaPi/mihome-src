package org.xutils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.xutils.common.util.KeyValue;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public interface DbManager extends Closeable {

    public interface DbOpenListener {
        void a(DbManager dbManager);
    }

    public interface DbUpgradeListener {
        void a(DbManager dbManager, int i, int i2);
    }

    public interface TableCreateListener {
        void a(DbManager dbManager, TableEntity<?> tableEntity);
    }

    int a(Class<?> cls, WhereBuilder whereBuilder) throws DbException;

    int a(Class<?> cls, WhereBuilder whereBuilder, KeyValue... keyValueArr) throws DbException;

    int a(String str) throws DbException;

    DaoConfig a();

    DbModel a(SqlInfo sqlInfo) throws DbException;

    void a(Class<?> cls) throws DbException;

    void a(Class<?> cls, Object obj) throws DbException;

    void a(Class<?> cls, String str) throws DbException;

    void a(Object obj, String... strArr) throws DbException;

    boolean a(Object obj) throws DbException;

    SQLiteDatabase b();

    <T> T b(Class<T> cls) throws DbException;

    <T> T b(Class<T> cls, Object obj) throws DbException;

    List<DbModel> b(SqlInfo sqlInfo) throws DbException;

    void b(Object obj) throws DbException;

    void b(String str) throws DbException;

    int c(SqlInfo sqlInfo) throws DbException;

    Cursor c(String str) throws DbException;

    <T> List<T> c(Class<T> cls) throws DbException;

    void c() throws DbException;

    void c(Object obj) throws DbException;

    void close() throws IOException;

    <T> Selector<T> d(Class<T> cls) throws DbException;

    void d(Object obj) throws DbException;

    void d(SqlInfo sqlInfo) throws DbException;

    Cursor e(SqlInfo sqlInfo) throws DbException;

    <T> TableEntity<T> e(Class<T> cls) throws DbException;

    void e(Object obj) throws DbException;

    void f(Class<?> cls) throws DbException;

    public static class DaoConfig {

        /* renamed from: a  reason: collision with root package name */
        private File f4208a;
        private String b = "xUtils.db";
        private int c = 1;
        private boolean d = true;
        private DbUpgradeListener e;
        private TableCreateListener f;
        private DbOpenListener g;

        public DaoConfig a(File file) {
            this.f4208a = file;
            return this;
        }

        public DaoConfig a(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.b = str;
            }
            return this;
        }

        public DaoConfig a(int i) {
            this.c = i;
            return this;
        }

        public DaoConfig a(boolean z) {
            this.d = z;
            return this;
        }

        public DaoConfig a(DbOpenListener dbOpenListener) {
            this.g = dbOpenListener;
            return this;
        }

        public DaoConfig a(DbUpgradeListener dbUpgradeListener) {
            this.e = dbUpgradeListener;
            return this;
        }

        public DaoConfig a(TableCreateListener tableCreateListener) {
            this.f = tableCreateListener;
            return this;
        }

        public File a() {
            return this.f4208a;
        }

        public String b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public boolean d() {
            return this.d;
        }

        public DbOpenListener e() {
            return this.g;
        }

        public DbUpgradeListener f() {
            return this.e;
        }

        public TableCreateListener g() {
            return this.f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DaoConfig daoConfig = (DaoConfig) obj;
            if (!this.b.equals(daoConfig.b)) {
                return false;
            }
            if (this.f4208a != null) {
                return this.f4208a.equals(daoConfig.f4208a);
            }
            if (daoConfig.f4208a == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.b.hashCode() * 31) + (this.f4208a != null ? this.f4208a.hashCode() : 0);
        }

        public String toString() {
            return String.valueOf(this.f4208a) + "/" + this.b;
        }
    }
}
