package com.xiaomi.passport.accountmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.xiaomi.accountsdk.utils.AccountLog;

public class MiAuthenticatorService extends Service {
    private static final String TAG = "LocalAuthenticatorSer";
    private LocalAccountAuthenticator mAuthenticator;

    public void onCreate() {
        super.onCreate();
        this.mAuthenticator = new LocalAccountAuthenticator(this);
    }

    public IBinder onBind(Intent intent) {
        AccountLog.v(TAG, "return the AccountAuthenticator binder of package: " + getPackageName());
        return this.mAuthenticator.getIBinder();
    }
}
