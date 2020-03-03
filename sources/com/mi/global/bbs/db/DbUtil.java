package com.mi.global.bbs.db;

import com.mi.global.bbs.dao.SubcribtionDao;

public class DbUtil {
    private static SubscripionHelper sStudentHelper;

    private static SubcribtionDao getDriverDao() {
        return DbCore.getDaoSession().getSubcribtionDao();
    }

    public static SubscripionHelper getDriverHelper() {
        if (sStudentHelper == null) {
            sStudentHelper = new SubscripionHelper(getDriverDao());
        }
        return sStudentHelper;
    }
}
