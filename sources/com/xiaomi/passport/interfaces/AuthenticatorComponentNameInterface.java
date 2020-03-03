package com.xiaomi.passport.interfaces;

import android.content.ComponentName;

public interface AuthenticatorComponentNameInterface {
    ComponentName getAddAccountActivityComponentName();

    ComponentName getConfirmCredentialActivityComponentName();

    ComponentName getNotificationActivityComponentName();

    ComponentName getProcessScanLoginQRCodeComponentName();
}
