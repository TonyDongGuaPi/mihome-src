package com.xiaomi.ai.mibrain;

public interface MibrainOauthManagerCallbacks {
    String getOauthCode();

    String getOauthData(String str);

    boolean putOauthData(String str, String str2);
}
