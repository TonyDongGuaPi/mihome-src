package com.xiaomi.mobilestats.common;

import android.text.TextUtils;

public class HostManager {
    public static boolean DEBUG_MODE = false;
    public static final String DEFAULT_CACHE_DIR = "StatCache";
    public static final String LOG_VERSION = "mishopsdk_20170414";
    public static String PREURL = "http://a.hl.mi.com/m";
    public static final int PROTO_HEAD_VERSION = 1;
    public static final String TAG = "MobileStats";
    public static long kContinueSessionMillis = 30000;
    public static long update_check_inteval = 60000;

    public static void setDebugOn(boolean z, String str) {
        DEBUG_MODE = z;
        if (z && !TextUtils.isEmpty(str)) {
            PREURL = str;
        }
    }
}
