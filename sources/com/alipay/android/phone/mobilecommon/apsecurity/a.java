package com.alipay.android.phone.mobilecommon.apsecurity;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.mobile.security.bio.utils.BioLog;
import java.util.Map;

public class a {
    public static void a(Context context, e eVar) {
        try {
            APSecuritySdk.getInstance(context).initToken(0, (Map<String, String>) null, new b(eVar));
        } catch (Throwable th) {
            BioLog.w("APSecuritySdk.initToken() GOT EXCEPTION!", th);
        }
    }
}
