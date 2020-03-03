package com.xiaomi.jr.appbase;

import android.content.Context;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.verification.VerificationUserEnvironment;

public class MiFiVerificationCallback implements VerificationUserEnvironment.Callback {
    public void a(Context context, String str, String str2) {
        DeeplinkUtils.openDeeplink(context, str2, str);
    }
}
