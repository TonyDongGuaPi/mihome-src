package com.mi.global.bbs.db;

import android.content.Context;
import android.util.Log;
import com.mi.global.bbs.dao.DaoMaster;
import com.mi.global.bbs.dao.SubcribtionDao;
import org.greenrobot.greendao.database.Database;

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String str) {
        super(context, str);
    }

    public void onUpgrade(Database database, int i, int i2) {
        Log.v("MyOpenHelper", "db version update from " + i + " to " + i2);
        if (i == 1) {
            SubcribtionDao.createTable(database, true);
            database.a("ALTER TABLE 'STUDENT' ADD 'SCORE' TEXT;");
        }
    }
}
