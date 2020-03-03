package com.mi.global.bbs.dao;

import com.mi.global.bbs.entity.Subcribtion;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

public class DaoSession extends AbstractDaoSession {
    private final SubcribtionDao subcribtionDao = new SubcribtionDao(this.subcribtionDaoConfig, this);
    private final DaoConfig subcribtionDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.subcribtionDaoConfig = map.get(SubcribtionDao.class).clone();
        this.subcribtionDaoConfig.a(identityScopeType);
        registerDao(Subcribtion.class, this.subcribtionDao);
    }

    public void clear() {
        this.subcribtionDaoConfig.c();
    }

    public SubcribtionDao getSubcribtionDao() {
        return this.subcribtionDao;
    }
}
