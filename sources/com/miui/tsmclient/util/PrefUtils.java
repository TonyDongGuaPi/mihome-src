package com.miui.tsmclient.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.RfDefaultCardCacheData;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

public class PrefUtils {
    public static final String PREF_KEY_BANK_CACHED = "key_bank_cached";
    public static final String PREF_KEY_CARD_CONFIG = "key_card_config";
    public static final String PREF_KEY_CARD_DETAIL_BG_PREFIX = "key_card_detail_bg_";
    public static final String PREF_KEY_CARD_EXTRA = "key_card_extra_%1$s";
    public static final String PREF_KEY_CURRENT_NFC_CONFIG_MD5 = "key_current_nfc_config";
    public static final String PREF_KEY_CUSTOM_TIME_NEED_SHOW_GUIDE = "key_custom_time_need_show_guide";
    public static final String PREF_KEY_CUSTOM_TIME_RF_CARDS = "key_custom_time_rf_cards";
    public static final String PREF_KEY_DEFAULT_BANK_CARD = "key_default_bank_card";
    public static final String PREF_KEY_DEFAULT_TRANSCARD_CHECKED = "transcard_checked";
    public static final String PREF_KEY_DEFAULT_TRANS_CARD = "key_default_trans_card";
    public static final String PREF_KEY_DISABLED_TRANSIT_CARD_AID = "key_disabled_transit_card_aid";
    public static final String PREF_KEY_FETCH_NFC_CONFIG_TIME = "key_fetch_swipe_card_config_time";
    public static final String PREF_KEY_HAS_SET_DOUBLE_PRESS_POWER_KEY = "key_has_set_double_press_power_key";
    public static final String PREF_KEY_HAS_VIEW_OTHER_PHONE_TRANSIT_CARDS = "key_has_view_other_phone_transit_cards";
    public static final String PREF_KEY_LANDSCAPE_RF_DEFAULT_CARD_INFO = "key_landscape_rf_default_card_info";
    public static final String PREF_KEY_LANDSCAPE_SWIPING_CARD_SWITCH = "key_landscape_swiping_card_switch";
    public static final String PREF_KEY_LAST_CARD = "key_last_card";
    public static final String PREF_KEY_LAST_INAPP_CARD = "key_last_inapp_card";
    public static final String PREF_KEY_LAST_SE_ROUTE = "key_last_se_route";
    public static final String PREF_KEY_LAST_UPLOAD_DADA_STAT_TIME = "pref_key_last_upload_data_stat_time";
    public static final String PREF_KEY_LAST_UPLOAD_TRADE_LOGS_END_ID = "key__%1$s_last_upload_trade_logs_end_id";
    public static final String PREF_KEY_LAST_UPLOAD_TRADE_LOGS_TIME = "key_%1$s_last_upload_trade_logs_time";
    public static final String PREF_KEY_MIFARE_CACHED = "key_mifare_cached";
    public static final String PREF_KEY_NFC_CONFIG_RETRY_COUNT = "key_nfc_config_retry_count";
    public static final String PREF_KEY_NFC_CONFIG_VERSION = "key_nfc_config_version";
    public static final String PREF_KEY_NFC_ID = "pref_key_nfc_id";
    public static final String PREF_KEY_NOTIFY_SERVER_UPDATE_CARD = "key_notify_server_update_card";
    public static final String PREF_KEY_PHONE_NUMBER_HAS_UPLOAD = "key_phone_number_has_upload_";
    public static final String PREF_KEY_POPUP_RECHARGE_TIPS_ALREADY = "key_popup_recharge_tips_already";
    public static final String PREF_KEY_RF_DEFAULT_CARD_AID = "key_rf_default_card_aid";
    public static final String PREF_KEY_SHOULD_UPLOAD_PHONE_NUMBER = "key_should_upload_phone_number_";
    public static final String PREF_KEY_SHOW_NEW_SWIPING_SETTINGS_BADGE = "key_show_new_swiping_settings_badge";
    public static String PREF_KEY_SPI_PK_STATE = "key_spi_pk_state_new";
    public static final String PREF_KEY_SWIPE_CARD_CONFIG = "pref_key_swipe_card_config";
    public static final String PREF_KEY_SWITCH_CARD_DEFAULT_AID = "key_switch_card_default_aid";
    public static final String PREF_KEY_SWITCH_DOUBLE_CLICK_HOME = "key_switch_double_click_home";
    public static final String PREF_KEY_TRANS_CACHED = "key_trans_cached";
    public static final String PREF_KEY_TRANS_CARD_BALANCE_NOTIFICATION_QUOTA = "key_trans_card_balance_notification_quota_%1$s";
    public static final String PREF_KEY_TRANS_CARD_LIST_NOTICE = "key_trans_card_list_notice";
    public static final String PREF_KEY_TRANS_CARD_MODE = "key_trans_card_mode";
    public static final String PREF_KEY_UPGRADE_CPLC_SUCCESS = "pref_key_upgrade";
    public static final String PREF_KEY_UPGRADE_FORCE = "key_upgrade_force";
    public static final String PREF_KEY_UPGRADE_LATEST_TIME = "key_upgrade_latest_time";
    public static final String PREF_KEY_UPLOAD_SWIPE_CARD_ERROR_INFO = "key_upload_swipe_card_error_info";
    public static final String PREF_KEY_UPLOAD_SWIPE_CARD_HCI_TRADE_INFO = "key_upload_swipe_card_hci_trade_info";
    public static final String PREF_KEY_UP_INITED = "key_up_inited";
    public static final String PREF_KEY_VERSION_CONTROL_ID = "key_version_control_id_";
    private static final String PREF_NAME = "pref_com_miui_tsmclient";
    public static final String RF_DEFAULT_CARD_AID_PREF_VALUE_NONE = "none";
    public static final String SETTINGS_SYSTEM_PREF_KEY_BANK_CARD = "key_bank_card_in_ese";
    public static final String SETTINGS_SYSTEM_PREF_KEY_DOUBLE_HOME_AVAILABILITY = "key_double_home_availability";
    public static final String SETTINGS_SYSTEM_PREF_KEY_DOUBLE_PRESS_POWER_KEY = "double_click_power_key";
    public static final String SETTINGS_SYSTEM_PREF_KEY_NFC_STATE = "system_key_nfc_state";
    public static final String SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD = "key_trans_card_in_ese";
    public static final int SETTINGS_SYSTEM_PREF_VALUE_DEFAULT = 0;
    public static final int SETTINGS_SYSTEM_PREF_VALUE_DOUBLE_HOME_AVAILABLE = 1;
    public static final int SETTINGS_SYSTEM_PREF_VALUE_DOUBLE_HOME_UNAVAILABLE = 0;
    public static final String SETTINGS_SYSTEM_PREF_VALUE_DOUBLE_PRESS_POWER_MI_PAY = "mi_pay";
    public static final String SETTINGS_SYSTEM_PREF_VALUE_DOUBLE_PRESS_POWER_NONE = "none";
    public static final int SETTINGS_SYSTEM_PREF_VALUE_HAS_CARD = 1;
    private static Set<String> sInvalidSPIKeyDeviceSet = new HashSet();

    static {
        sInvalidSPIKeyDeviceSet.add("polaris");
        sInvalidSPIKeyDeviceSet.add("dipper");
        sInvalidSPIKeyDeviceSet.add("equuleus");
        sInvalidSPIKeyDeviceSet.add("ursa");
        if (sInvalidSPIKeyDeviceSet.contains(Build.DEVICE)) {
        }
    }

    private PrefUtils() {
    }

    public static void putBoolean(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return context.getSharedPreferences(PREF_NAME, 0).getBoolean(str, z);
    }

    public static void putString(Context context, String str, String str2) {
        putString(context, PREF_NAME, str, str2);
    }

    public static void putString(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences(PREF_NAME, 0).getString(str, str2);
    }

    public static void putInt(Context context, String str, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static int getInt(Context context, String str, int i) {
        return context.getSharedPreferences(PREF_NAME, 0).getInt(str, i);
    }

    public static void putLong(Context context, String str, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static long getLong(Context context, String str, long j) {
        return context.getSharedPreferences(PREF_NAME, 0).getLong(str, j);
    }

    public static boolean contains(Context context, String str) {
        return context.getSharedPreferences(PREF_NAME, 0).contains(str);
    }

    public static boolean remove(Context context, String str) {
        return context.getSharedPreferences(PREF_NAME, 0).edit().remove(str).commit();
    }

    public static void setDefaultCard(Context context, String str, boolean z) {
        if (z) {
            putString(context, PREF_KEY_DEFAULT_TRANS_CARD, str);
            CardInfo makeCardInfo = CardInfoFactory.makeCardInfo(CardInfo.CARD_TYPE_DUMMY, (JSONObject) null);
            for (boolean z2 : new boolean[]{true, false}) {
                if (getRfCardCacheData(context, z2).getCardGroupType() == CardGroupType.TRANSCARD && !TextUtils.isEmpty(str)) {
                    makeCardInfo.mAid = str;
                    saveRfCard(context, makeCardInfo, z2);
                }
            }
            return;
        }
        putString(context, PREF_KEY_DEFAULT_BANK_CARD, str);
    }

    public static void saveRfCard(Context context, CardInfo cardInfo, boolean z) {
        String buildCacheStr = new RfDefaultCardCacheData(cardInfo).buildCacheStr();
        LogUtils.d("save rf data: " + buildCacheStr);
        putString(context, z ? PREF_KEY_LANDSCAPE_RF_DEFAULT_CARD_INFO : PREF_KEY_RF_DEFAULT_CARD_AID, buildCacheStr);
    }

    public static RfDefaultCardCacheData getRfCardCacheData(Context context, boolean z) {
        String str;
        if (z) {
            str = getString(context, PREF_KEY_LANDSCAPE_RF_DEFAULT_CARD_INFO, "");
        } else {
            str = getString(context, PREF_KEY_RF_DEFAULT_CARD_AID, "");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("cached rf data: ");
        sb.append(str);
        sb.append(", ");
        sb.append(z ? "is" : "is not");
        sb.append("landscape data");
        LogUtils.d(sb.toString());
        return new RfDefaultCardCacheData(str);
    }

    public static String getDefaultCard(Context context, boolean z) {
        if (z) {
            return getString(context, PREF_KEY_DEFAULT_TRANS_CARD, (String) null);
        }
        return getString(context, PREF_KEY_DEFAULT_BANK_CARD, (String) null);
    }

    public static void putSecureSettings(Context context, String str, int i) {
        putSecureSettings(context, str, i, true);
    }

    public static void putSecureSettings(Context context, String str, int i, boolean z) {
        LogUtils.d("save secure settings, key: " + str + ", value: " + i);
        if (z) {
            putInt(context, str, i);
        }
        SettingKeys.putSecureInt(context, str, i);
        if (SETTINGS_SYSTEM_PREF_KEY_BANK_CARD.equals(str) || SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD.equals(str)) {
            setDoubleHomeAvailability(context);
        }
    }

    public static int getSecureSettings(Context context, String str) {
        int i;
        try {
            i = SettingKeys.getSecureInt(context, str);
        } catch (Settings.SettingNotFoundException e) {
            LogUtils.e("failed to get secure value for key: " + str, e);
            i = -1;
        }
        LogUtils.d("get secure value for key: " + str + ", value: " + i);
        return i;
    }

    public static void setDoubleHomeAvailability(Context context) {
        int secureSettings = getSecureSettings(context, SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD);
        int secureSettings2 = getSecureSettings(context, SETTINGS_SYSTEM_PREF_KEY_BANK_CARD);
        if ((secureSettings == 1 || secureSettings2 == 1) && getBoolean(context, PREF_KEY_SWITCH_DOUBLE_CLICK_HOME, false)) {
            putSecureSettings(context, SETTINGS_SYSTEM_PREF_KEY_DOUBLE_HOME_AVAILABILITY, 1);
        } else {
            putSecureSettings(context, SETTINGS_SYSTEM_PREF_KEY_DOUBLE_HOME_AVAILABILITY, 0);
        }
    }

    public static void clear(Context context) {
        context.getSharedPreferences(PREF_NAME, 0).edit().clear().commit();
    }
}
