package com.xiaomi.passport.accountmanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.passport.accountmanager.MiAccountManager;

public class MiAccountManagerSettingsPersistent {
    private static final String KEY_AUTHENTICATOR = "authenticator";
    private static final String SP_NAME = "MiAccountManagerSettings";
    private static MiAccountManagerSettingsPersistent instance;
    private final Context mContext;

    MiAccountManagerSettingsPersistent(Context context) {
        this.mContext = context;
    }

    public static synchronized MiAccountManagerSettingsPersistent getInstance(Context context) {
        MiAccountManagerSettingsPersistent miAccountManagerSettingsPersistent;
        synchronized (MiAccountManagerSettingsPersistent.class) {
            if (context != null) {
                if (instance == null) {
                    instance = new MiAccountManagerSettingsPersistent(context.getApplicationContext());
                }
                miAccountManagerSettingsPersistent = instance;
            } else {
                throw new IllegalArgumentException("context cannot be null");
            }
        }
        return miAccountManagerSettingsPersistent;
    }

    public MiAccountManager.AccountAuthenticator loadAccountAuthenticator() {
        int i = getSP().getInt(KEY_AUTHENTICATOR, -1);
        MiAccountManager.AccountAuthenticator[] values = MiAccountManager.AccountAuthenticator.values();
        if (i < 0 || i >= values.length) {
            return null;
        }
        return values[i];
    }

    public void saveAccountAuthenticator(MiAccountManager.AccountAuthenticator accountAuthenticator) {
        if (accountAuthenticator != null) {
            getSP().edit().putInt(KEY_AUTHENTICATOR, accountAuthenticator.ordinal()).apply();
            return;
        }
        throw new IllegalArgumentException("accountAuthenticator can not be null");
    }

    /* access modifiers changed from: package-private */
    public SharedPreferences getSP() {
        return this.mContext.getSharedPreferences(SP_NAME, 0);
    }
}
