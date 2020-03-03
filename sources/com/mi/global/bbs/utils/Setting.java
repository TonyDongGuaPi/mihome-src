package com.mi.global.bbs.utils;

import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.utils.Utils;

public class Setting {
    private static Boolean dataSaverEnable;

    public static boolean isDataSaverEnabled() {
        if (dataSaverEnable == null) {
            dataSaverEnable = new Boolean(Utils.Preference.getBooleanPref(BBSApplication.getInstance(), "pref_data_saver_mode", true));
        }
        return dataSaverEnable.booleanValue();
    }

    public static void setDataSaverEnabled(boolean z) {
        dataSaverEnable = new Boolean(z);
        Utils.Preference.setBooleanPref(BBSApplication.getInstance(), "pref_data_saver_mode", z);
    }

    public static void initSettingDataSaverEnabled() {
        Utils.Preference.setDefaultBooleanValue(BBSApplication.getInstance(), "pref_data_saver_mode", true);
    }
}
