package com.mi.account.db;

import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Pair;
import com.mi.util.ResourceUtil;

public class DBContract {

    /* renamed from: a  reason: collision with root package name */
    public static String f6713a;
    public static final Uri b = Uri.parse("content://" + f6713a);

    public static final class DataStats implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DBContract.b, "data_stats");
        public static final String DIRECTORY = "data_stats";
        public static final String STATS = "stats";
        public static final String TYPE = "type";
    }

    public DBContract() {
        f6713a = ResourceUtil.a("account_cache_authority");
    }

    public static final class Cache implements BaseColumns {
        public static final String ACCOUNT_ID = "account_id";
        public static final String CONTENT = "content";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DBContract.b, "cache");
        public static final String DIRECTORY = "cache";
        public static final String ETAG = "etag";
        public static final String KEY = "key";

        private Cache() {
        }
    }

    public static final class DataMimeType implements BaseColumns {
        public static final String SERVICE_TOKEN = "service_token";

        public static String formatServiceToken(String str, String str2) {
            return str + ":" + str2;
        }

        public static Pair<String, String> parseServiceToken(String str) {
            String[] split;
            if (TextUtils.isEmpty(str) || (split = str.split(":")) == null || split.length != 2) {
                return null;
            }
            return new Pair<>(split[0], split[1]);
        }
    }
}
