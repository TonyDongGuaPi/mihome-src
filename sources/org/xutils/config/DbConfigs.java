package org.xutils.config;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;

public enum DbConfigs {
    HTTP(new DbManager.DaoConfig().a("xUtils_http_cache.db").a(1).a((DbManager.DbOpenListener) new DbManager.DbOpenListener() {
        public void a(DbManager dbManager) {
            dbManager.b().enableWriteAheadLogging();
        }
    }).a((DbManager.DbUpgradeListener) new DbManager.DbUpgradeListener() {
        public void a(DbManager dbManager, int i, int i2) {
            try {
                dbManager.c();
            } catch (DbException e) {
                LogUtil.b(e.getMessage(), e);
            }
        }
    })),
    COOKIE(new DbManager.DaoConfig().a("xUtils_http_cookie.db").a(1).a((DbManager.DbOpenListener) new DbManager.DbOpenListener() {
        public void a(DbManager dbManager) {
            dbManager.b().enableWriteAheadLogging();
        }
    }).a((DbManager.DbUpgradeListener) new DbManager.DbUpgradeListener() {
        public void a(DbManager dbManager, int i, int i2) {
            try {
                dbManager.c();
            } catch (DbException e) {
                LogUtil.b(e.getMessage(), e);
            }
        }
    }));
    
    private DbManager.DaoConfig config;

    private DbConfigs(DbManager.DaoConfig daoConfig) {
        this.config = daoConfig;
    }

    public DbManager.DaoConfig getConfig() {
        return this.config;
    }
}
