package com.xiaomi.passport;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.account.data.PassportCAToken;
import com.xiaomi.accountsdk.account.PassportCAExternal;

public class PassportCAExternalImpl implements PassportCAExternal {
    private static final String SP_KEY_CA_TOKEN = "ca_token";
    private static final String SP_KEY_CA_TOKEN_SECURITY = "ca_token_security";
    private static final String SP_KEY_NEXT_ENABLED_TIME = "next_enabled_time";
    private static final String SP_NAME = "passport_ca";
    private final Context mAppContext;

    public PassportCAExternalImpl(Context context) {
        if (context != null) {
            this.mAppContext = context.getApplicationContext();
            return;
        }
        throw new IllegalArgumentException("context can't be null");
    }

    public PassportCAToken loadCAToken() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        String string = sharedPreferences.getString(SP_KEY_CA_TOKEN, (String) null);
        String string2 = sharedPreferences.getString(SP_KEY_CA_TOKEN_SECURITY, (String) null);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return null;
        }
        return new PassportCAToken(string, string2);
    }

    public void saveCAToken(PassportCAToken passportCAToken) {
        if (passportCAToken != null) {
            SharedPreferences.Editor edit = getSharedPreferences().edit();
            edit.putString(SP_KEY_CA_TOKEN, passportCAToken.token);
            edit.putString(SP_KEY_CA_TOKEN_SECURITY, passportCAToken.security);
            edit.commit();
        }
    }

    public void saveNextCAEnabledTime(long j) {
        getSharedPreferences().edit().putLong(SP_KEY_NEXT_ENABLED_TIME, j).commit();
    }

    public long loadNextCAEnabledTime(long j) {
        return getSharedPreferences().getLong(SP_KEY_NEXT_ENABLED_TIME, j);
    }

    private SharedPreferences getSharedPreferences() {
        return this.mAppContext.getSharedPreferences(SP_NAME, 0);
    }
}
