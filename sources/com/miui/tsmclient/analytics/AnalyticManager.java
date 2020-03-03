package com.miui.tsmclient.analytics;

import android.content.Context;
import android.content.Intent;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.util.DeviceUtils;
import com.miui.tsmclient.util.EnvironmentConfig;
import com.miui.tsmclient.util.LogUtils;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.util.HashMap;
import java.util.Map;

public class AnalyticManager {
    private static final String APP_ID = "2882303761517368855";
    private static final String APP_KEY = "5281736870855";
    public static final String CATEGORY_BANK = "bank";
    public static final String CATEGORY_COMMON = "common";
    public static final String CATEGORY_FMSH = "fmsh";
    public static final String CATEGORY_MI_QUICK_PAY = "MIQuickPay";
    public static final String CATEGORY_NFC = "nfc";
    public static final String CATEGORY_PAY = "pay";
    public static final String CATEGORY_TRANSIT = "transit";
    public static final String CATEGORY_UP = "UPService";
    public static final String EVENT_PARAM_AID = "aid";
    public static final String EVENT_PARAM_CARD_BALANCE = "card_balance";
    public static final String EVENT_PARAM_CARD_TYPE = "card_type";
    public static final String EVENT_PARAM_ERROR_CODE = "error_code";
    public static final String EVENT_PARAM_FAIL_REASON = "fail_reason";
    public static final String EVENT_PARAM_HAS_ISSUE = "has_issue";
    public static final String EVENT_PARAM_IS_ENABLE = "is_enable";
    public static final String EVENT_PARAM_MIUI_ROM_TYPE = "miui_rom_type";
    public static final String EVENT_PARAM_MODEL = "model";
    public static final String EVENT_PARAM_PAY_CHANNEL = "pay_channel";
    public static final String EVENT_PARAM_PAY_PLUGIN_TYPE = "pay_plugin_type";
    public static final String EVENT_PARAM_RECHARGE_AMOUNT = "recharge_amount";
    public static final String EVENT_PARAM_RECHARGE_BY_NFC = "recharge_by_nfc";
    public static final String EVENT_PARAM_SYSTEM_VERSION = "system_version";
    public static final String EVENT_PARAM_TSMCLIENT_VERSION_CODE = "tsmclient_version_code";
    public static final String EVENT_PARAM_TSMCLIENT_VERSION_NAME = "tsmclient_version_name";
    public static final String EVENT_TIME = "time";
    public static final String EVENT_TRADE_AMOUNT = "trade_amount";
    public static final String EVENT_TRADE_LOCATION = "trade_location";
    public static final String KEY_CARD_DETAIL_BANNER = "card_detail_banner";
    public static final String KEY_CARD_DETAIL_FOOTER_LINK = "card_detail_footer_link";
    public static final String KEY_CARD_TYPE = "card_type";
    public static final String KEY_CLICK_PURCHASE_RECORD = "click_purchase_record/%s";
    public static final String KEY_CREATE_ORDER = "create_order/%s";
    public static final String KEY_DEACTIVATE_CACHED_BANK_CARD = "deactivate_cached_bank_card";
    public static final String KEY_ENTER = "key_enter/%s";
    public static final String KEY_ERROR_CODE = "error_code";
    public static final String KEY_INPUT_BANK_CARD_NUM = "input_bank_card_num";
    public static final String KEY_INSTALL_NOW = "install_now/%s";
    public static final String KEY_ISSUE_CARDS = "issue_cards";
    public static final String KEY_ISSUE_RECHARGE_PAY_NOW = "issue_recharge_pay_now/%s";
    public static final String KEY_MORE_SETTINGS = "more_settings";
    public static final String KEY_MY_CARDS = "my_cards";
    public static final String KEY_NFC_EE_IO_EXCEPTION = "key_nfc_ee_io_exception";
    public static final String KEY_NFC_EE_RESTRICTED = "key_nfc_ee_restricted";
    public static final String KEY_NFC_RECHARGE = "nfc_recharge/%s";
    public static final String KEY_NFC_UNUSUAL_DISABLED = "key_nfc_unusual_disabled";
    public static final String KEY_NFC_UNUSUAL_DISABLED_RESTORE = "key_nfc_unusual_disabled_restore";
    public static final String KEY_NOTIFY_TRANSIT_INFO = "notify_transit_card_info";
    public static final String KEY_OPERATION_FAILED = "operation_%s_failed";
    public static final String KEY_OPERATION_LAUNCH = "operation_%s_launch";
    public static final String KEY_OPERATION_SUCCESS = "operation_%s_success";
    public static final String KEY_PAY_RESULT = "pay_result";
    public static final String KEY_RECHARGE_AMOUNT = "recharge_amount/%s";
    public static final String KEY_RECHARGE_NOW = "recharge_now/%s";
    public static final String KEY_RECHARGE_PAY_NOW = "recharge_pay_now/%s";
    public static final String KEY_SET_DEFAULT_CARD = "set_default_card";
    public static final String KEY_SOURCE_CHANNEL = "source_channel/%s";
    public static final String PAY_PLUGIN_TYPE_UNION_APK = "union_apk";
    private static final String PUBLIC_KEY_RSA_DMP = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgA/ZfzV1GkdjfCisZRtorDWRm\n8FklXv5wjoNQxFL+qjiN5rxmQSrP0ksyuJVeSiy7mPRMYLMoUF7bDvYudhRvxkuL\nq6EByE/O3lksvJUjvlxr2KqcGQ3xbTY3xK52ilvh4CfBarAzzXl850ejqMhsLEZ7\npH2mQm+AMQ75+PD7NwIDAQAB";
    public static final String UNION_PAY_RESULT_NOT_PAY = "not_pay";
    private static final String URL_DMP_RECEIVE = "content://com.miui.analytics.server.AnalyticsProvider";
    private static volatile AnalyticManager sInstance;
    private int mClientVersionCode;
    private String mClientVersionName;
    private Context mContext;

    public static AnalyticManager getInstance() {
        if (sInstance == null) {
            synchronized (AnalyticManager.class) {
                if (sInstance == null) {
                    sInstance = new AnalyticManager();
                    sInstance.init(EnvironmentConfig.getContext());
                }
            }
        }
        return sInstance;
    }

    private AnalyticManager() {
    }

    private void init(Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
            MiStatInterface.a(this.mContext, APP_ID, APP_KEY, (String) null);
            MiStatInterface.a(4, 600000);
            this.mClientVersionCode = DeviceUtils.getAppVersionCode(this.mContext);
            this.mClientVersionName = DeviceUtils.getAppVersionName(this.mContext);
            return;
        }
        throw new IllegalArgumentException("context can not be null");
    }

    public void recordEvent(String str, String str2) {
        recordEvent(str, str2, (Object) null, (Map<String, String>) null);
    }

    public void recordEvent(String str, String str2, Map<String, String> map) {
        recordEvent(str, str2, (Object) null, map);
    }

    public void recordEvent(String str, String str2, Object obj) {
        recordEvent(str, str2, obj, (Map<String, String>) null);
    }

    private void recordEvent(String str, String str2, Object obj, Map<String, String> map) {
        if (!EnvironmentConfig.isStaging()) {
            if (map == null) {
                map = new HashMap<>();
            }
            buildCommonParams(map);
            if (obj == null) {
                MiStatInterface.a(str, str2, map);
            } else {
                MiStatInterface.a(str, str2, String.valueOf(obj));
            }
        }
    }

    public void recordCalculateEvent(String str, String str2, long j) {
        recordCalculateEvent(str, str2, j, (Map<String, String>) null);
    }

    public void recordCalculateEvent(String str, String str2, long j, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        buildCommonParams(map);
        MiStatInterface.a(str, str2, j, map);
    }

    public void recordPageStart(Context context, String str) {
        MiStatInterface.a(context, str);
    }

    public void recordPageEnd() {
        MiStatInterface.c();
    }

    private void buildCommonParams(Map<String, String> map) {
        map.put("system_version", DeviceUtils.getRomVersion());
        map.put("model", DeviceUtils.getDeviceModel((CardInfo) null));
        map.put(EVENT_PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType((CardInfo) null));
        map.put(EVENT_PARAM_TSMCLIENT_VERSION_CODE, String.valueOf(this.mClientVersionCode));
        map.put(EVENT_PARAM_TSMCLIENT_VERSION_NAME, this.mClientVersionName);
    }

    public void bugReport(Context context, String str, long j) {
        if (!EnvironmentConfig.isStaging()) {
            LogUtils.d("RomVersion:" + DeviceUtils.getRomVersion());
            Intent intent = new Intent("com.miui.klo.COMMON_LOG");
            intent.setPackage("com.miui.klo.bugreport");
            intent.putExtra("name", str);
            intent.putExtra("logcatCmd", "*:V");
            context.sendBroadcast(intent);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendDataToDMP(android.content.Context r6, org.json.JSONObject r7) {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "content://com.miui.analytics.server.AnalyticsProvider"
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x009f }
            android.content.ContentResolver r2 = r6.getContentResolver()     // Catch:{ Exception -> 0x009f }
            android.content.ContentProviderClient r1 = r2.acquireContentProviderClient(r1)     // Catch:{ Exception -> 0x009f }
            if (r1 != 0) goto L_0x0017
            if (r1 == 0) goto L_0x0016
            r1.release()
        L_0x0016:
            return
        L_0x0017:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.<init>()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = "c"
            r0.put(r2, r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r7 = r0.toString()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            android.util.Pair r7 = com.miui.tsmclient.util.DMPEncryptUtil.encodeAES(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.<init>()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = "i"
            java.lang.Object r3 = r7.first     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = "t"
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = "v"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r3.<init>()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r4 = r6.getPackageName()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r3.append(r4)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r4 = ","
            r3.append(r4)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r6 = com.miui.tsmclient.util.DeviceUtils.getAppVersionName(r6)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r3.append(r6)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r6 = r3.toString()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.put(r2, r6)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r6 = "n"
            java.util.UUID r2 = java.util.UUID.randomUUID()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.put(r6, r2)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r6 = "k"
            java.lang.Object r7 = r7.second     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            byte[] r7 = (byte[]) r7     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgA/ZfzV1GkdjfCisZRtorDWRm\n8FklXv5wjoNQxFL+qjiN5rxmQSrP0ksyuJVeSiy7mPRMYLMoUF7bDvYudhRvxkuL\nq6EByE/O3lksvJUjvlxr2KqcGQ3xbTY3xK52ilvh4CfBarAzzXl850ejqMhsLEZ7\npH2mQm+AMQ75+PD7NwIDAQAB"
            java.lang.String r7 = com.miui.tsmclient.util.DMPEncryptUtil.encodeRSAByPublicKey(r7, r2)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r0.put(r6, r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r6.<init>()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r7 = "data"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r6.putString(r7, r0)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r7 = "queryLiteral"
            java.lang.String r0 = ""
            r1.call(r7, r0, r6)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            if (r1 == 0) goto L_0x00aa
            r1.release()
            goto L_0x00aa
        L_0x0097:
            r6 = move-exception
            goto L_0x00ab
        L_0x0099:
            r6 = move-exception
            r0 = r1
            goto L_0x00a0
        L_0x009c:
            r6 = move-exception
            r1 = r0
            goto L_0x00ab
        L_0x009f:
            r6 = move-exception
        L_0x00a0:
            java.lang.String r7 = "failed to send data to DMP"
            com.miui.tsmclient.util.LogUtils.e(r7, r6)     // Catch:{ all -> 0x009c }
            if (r0 == 0) goto L_0x00aa
            r0.release()
        L_0x00aa:
            return
        L_0x00ab:
            if (r1 == 0) goto L_0x00b0
            r1.release()
        L_0x00b0:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.analytics.AnalyticManager.sendDataToDMP(android.content.Context, org.json.JSONObject):void");
    }
}
