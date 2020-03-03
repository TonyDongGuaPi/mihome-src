package com.xiaomi.mobilestats.data;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mobilestats.common.AESUtils;

public class BasicStoreTools extends BasicStoreToolsBase {
    public static final String APP_ANALYSIS_EXCEPTION = "exceptionanalysisflag";
    public static final String APP_ANALYSIS_EXCEPTION_TAG = "exceptionanalysistag";
    public static final String APP_LAST_SENDDATA = "lastdata";
    public static final String APP_MAC_ADDRESS = "sdkmacss";
    public static final String APP_MAC_ADDRESS_TV = "sdkmacsstv";
    public static final String APP_SET_APPKEY = "setappkey";
    public static final String APP_SET_CHANNEL = "setchannelwithcodevalue";
    public static final String APP_SET_CHANNEL_WITH_CODE = "setchannelwithcode";
    public static final String DEVICE_CUID = "cuidsec";
    public static final String DEVICE_ID = "device_id";
    static BasicStoreTools K = new BasicStoreTools();
    public static final String LAST_SEND_TIME = "lastsendtime";
    public static final String ONLY_WIFI = "onlywifi";
    public static final String SEND_LOG_TYPE = "sendLogtype";
    public static final String TIME_INTERVAL = "timeinterval";
    public static final String VMIMEI = "vmimei";

    public static BasicStoreTools getInstance() {
        return K;
    }

    public String getAppChannel(Context context) {
        return getString(context, "appchannel", (String) null);
    }

    public String getAppDeviceMac(Context context) {
        return getString(context, "dkmacss", (String) null);
    }

    public String getAppKey(Context context) {
        return getString(context, APP_SET_APPKEY, (String) null);
    }

    public String getCRKey(Context context) {
        String string = getString(context, "CRKey", "");
        return TextUtils.isEmpty(string) ? AESUtils.ENCODE_KEY : string;
    }

    public String getCrashMD5List(Context context) {
        return getString(context, "crashMD5", "");
    }

    public String getExceptionHeadTag(Context context) {
        return getString(context, APP_ANALYSIS_EXCEPTION_TAG, (String) null);
    }

    public boolean getExceptionTurn(Context context) {
        return getBoolean(context, APP_ANALYSIS_EXCEPTION, false);
    }

    public String getGenerateDeviceCUID(Context context) {
        return getString(context, DEVICE_CUID, (String) null);
    }

    public String getGenerateDeviceId(Context context) {
        return getString(context, "device_id", (String) null);
    }

    public String getLastData(Context context) {
        return getString(context, APP_LAST_SENDDATA, (String) null);
    }

    public long getLastSendTime(Context context) {
        return getLong(context, LAST_SEND_TIME, 0);
    }

    public boolean getOnlyWifi(Context context) {
        return getBoolean(context, ONLY_WIFI, false);
    }

    public int getSendStrategy(Context context) {
        return getInt(context, "sendStrategy", -1);
    }

    public long getSendStrategyTime(Context context) {
        return getLong(context, TIME_INTERVAL, 0);
    }

    public String getUserId(Context context) {
        return getString(context, "userId", "");
    }

    public int getVMIMEI(Context context) {
        return getInt(context, VMIMEI, 0);
    }

    public void setAppChannel(Context context, String str) {
        putString(context, "appchannel", str);
    }

    public void setAppDeviceMac(Context context, String str) {
        putString(context, APP_MAC_ADDRESS, str);
    }

    public void setAppKey(Context context, String str) {
        putString(context, APP_SET_APPKEY, str);
    }

    public void setCRKey(Context context, String str) {
        putString(context, "CRKey", str);
    }

    public void setCrashMD5List(Context context, String str) {
        putString(context, "crashMD5", str);
    }

    public void setExceptionHeadTag(Context context, String str) {
        putString(context, APP_ANALYSIS_EXCEPTION_TAG, str);
    }

    public void setExceptionTurn(Context context, boolean z) {
        putBoolean(context, APP_ANALYSIS_EXCEPTION, z);
    }

    public void setGenerateDeviceCUID(Context context, String str) {
        if (getString(context, "cuid", (String) null) != null) {
            removeString(context, "cuid");
        }
        putString(context, DEVICE_CUID, str);
    }

    public void setGenerateDeviceId(Context context, String str) {
        putString(context, "device_id", str);
    }

    public void setLastData(Context context, String str) {
        putString(context, APP_LAST_SENDDATA, str);
    }

    public void setLastSendTime(Context context, long j) {
        putLong(context, LAST_SEND_TIME, j);
    }

    public void setOnlyWifi(Context context, boolean z) {
        putBoolean(context, ONLY_WIFI, z);
    }

    public void setSendStrategy(Context context, int i) {
        putInt(context, "sendStrategy", i);
    }

    public void setSendStrategyTime(Context context, long j) {
        putLong(context, TIME_INTERVAL, j);
    }

    public void setUserId(Context context, String str) {
        putString(context, "userId", str);
    }

    public void setVMIMEI(Context context, int i) {
        putInt(context, VMIMEI, i);
    }
}
