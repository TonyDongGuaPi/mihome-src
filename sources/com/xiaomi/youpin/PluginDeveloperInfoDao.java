package com.xiaomi.youpin;

import java.sql.SQLException;

public class PluginDeveloperInfoDao {
    public static boolean a(PluginDeveloperInfoDao pluginDeveloperInfoDao) {
        try {
            if (PluginDBHelper.a().getDao(PluginDeveloperInfoDao.class).create(pluginDeveloperInfoDao) == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(PluginDeveloperInfo pluginDeveloperInfo) {
        try {
            PluginDBHelper.a().getDao(PluginDeveloperInfoDao.class);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
