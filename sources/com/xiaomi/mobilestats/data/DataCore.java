package com.xiaomi.mobilestats.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.mobilestats.common.AESUtils;
import com.xiaomi.mobilestats.common.CommonUtil;
import com.xiaomi.mobilestats.common.StrUtil;

public class DataCore extends BasicStoreToolsBase {
    private static MobileInfo N = new MobileInfo();
    public static final String PREF_UID = "shop_sdk_pref_uid";

    public static final String encodeBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static String getAppChannel(Context context) {
        if (!(N == null || !TextUtils.isEmpty(N.appChannel) || context == null)) {
            String appChannel = BasicStoreTools.getInstance().getAppChannel(context);
            if (!TextUtils.isEmpty(appChannel)) {
                N.appChannel = appChannel;
            } else {
                String appChannel2 = CommonUtil.getAppChannel(context);
                if (!TextUtils.isEmpty(appChannel2)) {
                    N.appChannel = appChannel2;
                    BasicStoreTools.getInstance().setAppChannel(context, appChannel2);
                }
            }
        }
        return N != null ? N.appChannel : "";
    }

    public static int getAppVersionCode(Context context) {
        if (!(N == null || N.appVersionCode > 0 || context == null)) {
            N.appVersionCode = CommonUtil.getVersionCode(context);
        }
        if (N != null) {
            return N.appVersionCode;
        }
        return -1;
    }

    public static String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(N.appVersionName) && context != null) {
            N.appVersionName = CommonUtil.getVersionName(context);
        }
        return N.appVersionName;
    }

    public static String getAppkey(Context context) {
        if (!(N == null || !TextUtils.isEmpty(N.appKey) || context == null)) {
            String appKey = BasicStoreTools.getInstance().getAppKey(context);
            if (!TextUtils.isEmpty(appKey)) {
                N.appKey = appKey;
            } else {
                String appKey2 = CommonUtil.getAppKey(context);
                if (!TextUtils.isEmpty(appKey2)) {
                    N.appKey = appKey2;
                    BasicStoreTools.getInstance().setAppKey(context, appKey2);
                }
            }
        }
        return N != null ? N.appKey : "";
    }

    public static String getCurrentSessionId(Context context) {
        if (TextUtils.isEmpty(N.sessionId) && context != null) {
            N.sessionId = o(context);
        }
        return N.sessionId;
    }

    public static String getDeviceId(Context context) {
        if (!(N == null || !TextUtils.isEmpty(N.deviceId) || context == null)) {
            N.deviceId = CommonUtil.getDeviceID(context);
        }
        return N != null ? N.deviceId : "";
    }

    public static String getEncodeDeviceId(Context context) {
        if (!(N == null || !TextUtils.isEmpty(N.imei) || context == null)) {
            String deviceId = getDeviceId(context);
            if (!TextUtils.isEmpty(deviceId)) {
                N.imei = AESUtils.encrpt(deviceId.getBytes(), AESUtils.ENCODE_KEY);
            }
        }
        return (N == null || TextUtils.isEmpty(N.imei)) ? "" : N.imei;
    }

    public static String getMacID(Context context) {
        if (!(N == null || !TextUtils.isEmpty(N.macID) || context == null)) {
            String appDeviceMac = BasicStoreTools.getInstance().getAppDeviceMac(context);
            if (!TextUtils.isEmpty(appDeviceMac)) {
                N.macID = appDeviceMac;
            } else {
                String macAddress = CommonUtil.getMacAddress(context);
                if (!TextUtils.isEmpty(macAddress)) {
                    N.macID = macAddress;
                    BasicStoreTools.getInstance().setAppDeviceMac(context, macAddress);
                }
            }
        }
        return N != null ? N.macID : "";
    }

    public static MobileInfo getMoblieInfo() {
        return N;
    }

    public static String getOSSysVersion() {
        if (TextUtils.isEmpty(N.OSSysVersion)) {
            N.OSSysVersion = Build.VERSION.RELEASE;
        }
        return N.OSSysVersion;
    }

    public static String getOperator(TelephonyManager telephonyManager) {
        if (!(N == null || !TextUtils.isEmpty(N.networkOperator) || telephonyManager == null)) {
            N.networkOperator = telephonyManager.getNetworkOperator();
        }
        return N.networkOperator;
    }

    public static String getPackageName(Context context) {
        if (!(N == null || !TextUtils.isEmpty(N.package_name) || context == null)) {
            String packageName = CommonUtil.getPackageName(context);
            if (!TextUtils.isEmpty(packageName)) {
                N.package_name = packageName;
            }
        }
        return N != null ? N.package_name : "";
    }

    public static String getPhoneModel() {
        if (TextUtils.isEmpty(N.phoneModel)) {
            N.phoneModel = Build.MODEL;
        }
        return N.phoneModel;
    }

    public static String getSDKVersion() {
        if (N != null && TextUtils.isEmpty(N.OSVersion)) {
            N.OSVersion = Build.VERSION.SDK;
        }
        return N.OSVersion;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getUserId(android.content.Context r3) {
        /*
            com.xiaomi.mobilestats.data.BasicStoreTools r0 = com.xiaomi.mobilestats.data.BasicStoreTools.getInstance()
            java.lang.String r0 = r0.getUserId(r3)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0048
            if (r3 == 0) goto L_0x0048
            android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r3)
            if (r1 == 0) goto L_0x0048
            java.lang.String r0 = "shop_sdk_pref_uid"
            java.lang.String r2 = ""
            java.lang.String r0 = r1.getString(r0, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0048
            java.lang.String r1 = "UTF-8"
            byte[] r1 = r0.getBytes(r1)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            byte[] r1 = com.xiaomi.mobilestats.data.AESUtil.encrypt(r1)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r1 = encodeBase64(r1)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch:{ UnsupportedEncodingException -> 0x0041 }
            if (r0 == 0) goto L_0x003f
            com.xiaomi.mobilestats.data.BasicStoreTools r0 = com.xiaomi.mobilestats.data.BasicStoreTools.getInstance()     // Catch:{ UnsupportedEncodingException -> 0x0041 }
            r0.setUserId(r3, r1)     // Catch:{ UnsupportedEncodingException -> 0x0041 }
        L_0x003f:
            r0 = r1
            goto L_0x0048
        L_0x0041:
            r3 = move-exception
            r0 = r1
            goto L_0x0045
        L_0x0044:
            r3 = move-exception
        L_0x0045:
            r3.printStackTrace()
        L_0x0048:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x0050
            java.lang.String r0 = ""
        L_0x0050:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.data.DataCore.getUserId(android.content.Context):java.lang.String");
    }

    public static int getVMIMEI(Context context) {
        int vmimei = BasicStoreTools.getInstance().getVMIMEI(context);
        if (vmimei != 0) {
            return vmimei;
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        BasicStoreTools.getInstance().setVMIMEI(context, currentTimeMillis);
        return currentTimeMillis;
    }

    public static int getVMIMEI(Context context, boolean z) {
        int vmimei;
        if (!z && (vmimei = BasicStoreTools.getInstance().getVMIMEI(context)) != 0) {
            return vmimei;
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        BasicStoreTools.getInstance().setVMIMEI(context, currentTimeMillis);
        return currentTimeMillis;
    }

    public static String getXMSDKVersion() {
        return "1.0";
    }

    private static String o(Context context) {
        if (context == null) {
            return "";
        }
        String str = getAppkey(context) + getDeviceId(context) + System.currentTimeMillis();
        if (str == null) {
            return "";
        }
        String md5 = StrUtil.md5(str);
        SharedPreferences.Editor edit = context.getSharedPreferences("XM_sessionID", 0).edit();
        edit.putString("session_id", md5);
        edit.commit();
        return md5;
    }
}
