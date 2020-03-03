package com.mi.global.bbs.db;

import android.content.Context;
import com.mi.global.bbs.dao.DaoMaster;
import com.mi.global.bbs.dao.DaoSession;
import org.greenrobot.greendao.query.QueryBuilder;

public class DbCore {
    private static String DB_NAME = null;
    private static final String DEFAULT_DB_NAME = "bbs_subscription.db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static Context mContext;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String str) {
        if (context != null) {
            mContext = context.getApplicationContext();
            DB_NAME = str;
            return;
        }
        throw new IllegalArgumentException("context can't be null");
    }

    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(new MyOpenHelper(mContext, DB_NAME).getWritableDb());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog() {
        QueryBuilder.f3538a = true;
        QueryBuilder.b = true;
    }
}
