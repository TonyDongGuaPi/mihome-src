package com.alipay.mobile.common.logging.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.taobao.weex.el.parse.Operators;

public class LoggingSPCache {
    public static final String KEY_CUR_UPLOAD_DAY = "curUploadDay";
    public static final String KEY_CUR_UPLOAD_TRAFIC = "curUploadTrafic";
    public static final String LOGGING_CACHE_KEY_INDEX = "behavorLoggingIndex";
    public static final String LOGGING_CACHE_KEY_LOG_DUMP_TAG = "LogDumpTag";
    public static final String STORAGE_APKUNIQUEID = "apkUniqueId";
    public static final String STORAGE_BIRDNESTVERSION = "birdNestVersion";
    public static final String STORAGE_BUNDLEVERSION = "bundleVersion";
    public static final String STORAGE_CHANNELID = "channelId";
    public static final String STORAGE_CLIENTID = "clientID";
    public static final String STORAGE_DEVICEID = "utdid";
    public static final String STORAGE_HOTPATCHVERSION = "hotpatchVersion";
    public static final String STORAGE_LANGUAGE = "language";
    public static final String STORAGE_PACKAGEID = "packageId";
    public static final String STORAGE_PRODUCTID = "productID";
    public static final String STORAGE_PRODUCTVERSION = "productVersion";
    public static final String STORAGE_RELEASECODE = "releaseCode";
    public static final String STORAGE_RELEASETYPE = "releaseType";
    public static final String STORAGE_USERID = "userID";

    /* renamed from: a  reason: collision with root package name */
    private static LoggingSPCache f969a;
    private SharedPreferences b;

    private LoggingSPCache(Context context) {
        String replace = LoggerFactory.getProcessInfo().getProcessName().replace(Operators.CONDITION_IF_MIDDLE, '.');
        this.b = context.getSharedPreferences(replace + '.' + "LoggingCache", 0);
    }

    public static synchronized void createInstance(Context context) {
        synchronized (LoggingSPCache.class) {
            if (f969a == null) {
                f969a = new LoggingSPCache(context);
            }
        }
    }

    public static synchronized LoggingSPCache getInstance() {
        LoggingSPCache loggingSPCache;
        synchronized (LoggingSPCache.class) {
            if (f969a != null) {
                loggingSPCache = f969a;
            } else {
                throw new IllegalStateException("need createInstance befor use");
            }
        }
        return loggingSPCache;
    }

    public void remove(String str) {
        this.b.edit().remove(str).commit();
    }

    public void putString(String str, String str2) {
        this.b.edit().putString(str, str2).commit();
    }

    public String getString(String str, String str2) {
        return this.b.getString(str, str2);
    }

    public void putInt(String str, int i) {
        this.b.edit().putInt(str, i).commit();
    }

    public int getInt(String str, int i) {
        return this.b.getInt(str, i);
    }

    public void putLong(String str, long j) {
        this.b.edit().putLong(str, j).commit();
    }

    public long getLong(String str, long j) {
        return this.b.getLong(str, j);
    }

    public void putBoolean(String str, boolean z) {
        this.b.edit().putBoolean(str, z).commit();
    }

    public boolean getBoolean(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }
}
