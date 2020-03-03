package com.mi.global.bbs.utils;

import android.text.TextUtils;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import org.json.JSONObject;

public class ScratchUtil {
    public static String getGameTitle() {
        try {
            String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_GAME_INFO, "");
            if (!TextUtils.isEmpty(stringPref)) {
                return new JSONObject(stringPref).optString("name");
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getGameScratchTitle() {
        try {
            return new JSONObject(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_GAME_INFO, "")).optString("scratch");
        } catch (Exception e) {
            e.printStackTrace();
            return "Scratch now";
        }
    }

    public static String getGameDateInfo() {
        try {
            String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_GAME_INFO, "");
            if (TextUtils.isEmpty(stringPref)) {
                return "";
            }
            JSONObject jSONObject = new JSONObject(stringPref);
            long optLong = jSONObject.optLong("start");
            long optLong2 = jSONObject.optLong("end");
            String onlyDateStr = LocaleHelper.getOnlyDateStr(Long.valueOf(optLong * 1000));
            String onlyDateStr2 = LocaleHelper.getOnlyDateStr(Long.valueOf(optLong2 * 1000));
            return onlyDateStr + "-" + onlyDateStr2;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
