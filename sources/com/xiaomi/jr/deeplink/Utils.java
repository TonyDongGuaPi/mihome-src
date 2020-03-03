package com.xiaomi.jr.deeplink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import java.net.URISyntaxException;

public class Utils {
    private static final String TAG = "Deeplink_Utils";

    public static Context toContext(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Context) {
            return (Context) obj;
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).getActivity();
        }
        return null;
    }

    public static String clearQuery(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str).buildUpon().clearQuery().build().toString();
    }

    public static String appendAllQueries(String str, String str2) {
        Uri parse = Uri.parse(str);
        try {
            for (String next : parse.getQueryParameterNames()) {
                str2 = appendQueryParameter(str2, next, parse.getQueryParameter(next));
            }
            return str2;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage() + " - origUrl: " + str);
        }
    }

    private static String appendQueryParameter(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse.getQueryParameter(str2) == null) {
                parse = parse.buildUpon().appendQueryParameter(str2, str3).build();
            }
            return parse.toString();
        } catch (Exception e) {
            Log.e(TAG, "Exception throws " + e + ", url = " + str);
            return str;
        }
    }

    public static Intent parseUrlIntoIntent(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Intent.parseUri(str, 1);
        } catch (URISyntaxException e) {
            Log.e(TAG, "parse intent failed : " + e.toString());
            return null;
        }
    }
}
