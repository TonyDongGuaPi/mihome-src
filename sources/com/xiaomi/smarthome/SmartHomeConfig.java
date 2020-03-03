package com.xiaomi.smarthome;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.json.JSONObject;

public class SmartHomeConfig {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13421a = "prefs_promotion";
    public static final String b = "showed_top_banner";
    public static boolean c = true;
    public static final String d = "sh_promotion_info_key_v1";
    public static final String e = "prefs_virtual_device";
    public static final String f = "virtual_device_info_key";
    public static final String g = "virtual_device_last_check_key";
    public static final String h = "virtual_device_last_locale_key";
    public static final String i = "prefs_lite_config";
    public static final String j = "lite_config_info_key";
    public static final String k = "lite_config_last_check_key";
    public static final String l = "lite_config_click_sound";
    public static final String m = "miui_auto_discovery";
    public static final String n = "auto_connect_new";
    public static final String o = "lite_config_navigation_mode";
    public static final String p = "prefs_sign_state";
    public static final String q = "sign_state_ignore_key";
    public static final String r = "lite_config_device_card_shortcut";
    public static final String s = "lite_config_use_geek_mode";
    public static final String t = "sh_lite_config_show_category";
    public static final String u = "sh_lite_config_show_category_in_front_of_room";
    public static final String v = "sh_lite_config_show_subcategory";

    public static String a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optString(ArchiveStreamFactory.g);
    }
}
