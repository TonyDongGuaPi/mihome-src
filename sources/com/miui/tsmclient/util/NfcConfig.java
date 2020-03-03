package com.miui.tsmclient.util;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclientsdk.UnSupportedException;
import java.io.IOException;
import java.util.regex.Pattern;

public class NfcConfig {
    static final String TRANS_CARD_CONFIG_ASSET_DIRECTORY = "nxpconf";

    interface NfcConfigStrategy {
        boolean setConfig(String str);
    }

    public static boolean setConfig(Context context, String str) {
        try {
            String[] list = context.getAssets().list(TRANS_CARD_CONFIG_ASSET_DIRECTORY);
            if (list != null && list.length > 0) {
                return new AssetsStrategy(context).setConfig(str);
            }
        } catch (IOException e) {
            LogUtils.e("listing files in assets occurred error", e);
        }
        return new AidStrategy(context).setConfig(str);
    }

    public static boolean setConfigByUrl(Context context, String str) throws UnSupportedException {
        throw new UnSupportedException();
    }

    private static class AidStrategy implements NfcConfigStrategy {
        private Context mContext;

        AidStrategy(Context context) {
            this.mContext = context;
        }

        public boolean setConfig(String str) {
            LogUtils.d("setConfig: " + str + " by AidStrategy");
            return true;
        }
    }

    private static class AssetsStrategy implements NfcConfigStrategy {
        static final String CONFIG_DEFAULT_NAME = "common";
        private Context mContext;

        AssetsStrategy(Context context) {
            this.mContext = context;
        }

        public boolean setConfig(String str) {
            LogUtils.d("setConfig: " + str + " by AssetsStrategy");
            return true;
        }

        private static final class Config {
            static final String CONFIG_MD5_SEPARATOR = "_";
            static final String CONFIG_NAME_PATTERN = "\\w+_[a-f0-9]{32}\\.conf";
            static final String CONFIG_SUFFIX = "conf";
            String mConfigFileFullName;
            String mConfigFileName;
            String mConfigType;
            String mMD5;

            private Config() {
            }

            public static Config createConfig(String str) {
                if (TextUtils.isEmpty(str) || !Pattern.compile(CONFIG_NAME_PATTERN).matcher(str).matches()) {
                    return null;
                }
                String substring = str.substring(0, str.lastIndexOf(".conf"));
                int lastIndexOf = substring.lastIndexOf("_");
                Config config = new Config();
                config.mConfigFileFullName = str;
                config.mConfigFileName = substring;
                config.mConfigType = substring.substring(0, lastIndexOf);
                config.mMD5 = substring.substring(lastIndexOf + 1);
                return config;
            }
        }
    }
}
