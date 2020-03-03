package com.xiaomi.mishopsdk.utils;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.util.Constants;
import org.json.JSONObject;

@Deprecated
public class ChannelUtils {
    public static String getNextCid(Bundle bundle, String str) {
        if (bundle == null || bundle == null || !bundle.containsKey(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS)) {
            return str;
        }
        return bundle.getString(Constants.Plugin.ARGUMENT_FORCEVRESION) + Constants.Split.CTRL_A + str;
    }

    public static String getNextCid(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return str + Constants.Split.CTRL_A + str2;
    }

    public static String getAddCartPath(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return str;
        }
        try {
            if (jSONObject.optString(Constants.Plugin.ARGUMENT_LOGCODE) == null) {
                return str;
            }
            return str + Constants.Split.CTRL_B + jSONObject.getString(Constants.Plugin.ARGUMENT_LOGCODE);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
