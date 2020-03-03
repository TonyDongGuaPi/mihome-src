package com.xiaomi.mishopsdk.io.http;

import android.text.TextUtils;
import com.xiaomi.mishopsdk.SdkUtils;

public class RequestConstants {
    private static final String TAG = "RequestConstants";

    public static final class Keys {
        public static final String ANDROID_VERSION = "Android-Ver";
        public static final String Auth = "Mishop-Auth";
        public static final String CITY_NAME = "city_name";
        public static final String ChANNEL_ID = "Mishop-Channel-Id";
        public static final String ClientID = "Mishop-Client-Id";
        public static final String DEVICE_ID = "Device-Id";
        public static final String IS_PAD = "Mishop-Is-Pad";
        public static final String MODEL = "Mishop-Model";
        public static final String NETWORK_STAT = "Network-Stat";
        public static final String PHONE_NAME = "phone_name";
        public static final String PHONE_TYPE = "phone_type";
        public static final String SCREEN_DENSITY = "Screen-density";
        public static final String SCREEN_DENSITYDPI = "Screen-DensityDpi";
        public static final String SCREEN_WIDTH_PX = "Screen-width-px";
        public static final String SDK_CID = "Mishop_SDK_cid";
        public static final String SDK_ISV_CODE = "";
        public static final String USER_ID = "user_Id";
        public static final String VERSION_CDOE = "Mishop-Client-VersionCode";
        public static final String VERSION_NAME = "Mishop-Client-VersionName";
        public static final String uuid = "Uuid";
    }

    public static final class Values {
        public static String CLIENT_ID = SdkUtils.getClientId();
        public static boolean IS_PAD = false;
        public static final int PAGESIZE_VALUE = 20;
    }

    public static boolean updateChannel() {
        Values.CLIENT_ID = SdkUtils.getClientId();
        SDK_Channel.channel_id = SdkUtils.getChannelId();
        String unused = SDK_Channel.channel_id_pre = SdkUtils.getChannelIdPre();
        return true;
    }

    public static class SDK_Channel {
        public static String channel_id = "0000.0000";
        /* access modifiers changed from: private */
        public static String channel_id_pre;

        static {
            String channelId = SdkUtils.getChannelId();
            String channelIdPre = SdkUtils.getChannelIdPre();
            if (!TextUtils.isEmpty(channelId)) {
                channel_id = channelId;
            }
            if (channelIdPre != null && channelIdPre.length() > 1) {
                channel_id_pre = channelIdPre.substring(1);
            }
        }

        public static boolean isChannelIdValid(String str) {
            return !TextUtils.isEmpty(str) && (TextUtils.isEmpty(channel_id_pre) || str.startsWith(channel_id_pre));
        }
    }
}
