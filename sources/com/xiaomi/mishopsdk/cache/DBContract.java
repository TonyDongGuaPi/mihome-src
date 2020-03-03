package com.xiaomi.mishopsdk.cache;

import android.net.Uri;
import android.provider.BaseColumns;

public class DBContract {
    public static final String AUTHORITY = "com.xiaomi.mishopsdk";
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.xiaomi.mishopsdk");

    public static final class ServiceTokenCacheColumns implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DBContract.AUTHORITY_URI, DIRECTORY);
        public static final String DIRECTORY = "service_token_cache";
        public static final String SID = "sid";
        public static final String TOKEN = "token";
    }
}
