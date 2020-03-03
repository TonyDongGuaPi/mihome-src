package com.alipay.android.phone.mobilecommon.apsecurity;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.service.local.apsecurity.ApSecurityService;
import com.alipay.mobile.security.bio.utils.BioLog;

public class AlipayApSecurityService extends ApSecurityService {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f814a = null;

    public void init(Context context) {
        BioLog.i("AlipayApSecurityService init");
        if (TextUtils.isEmpty(this.f814a) && TextUtils.isEmpty(sApdidToken)) {
            try {
                f.a(context, new c(this));
            } catch (Throwable th) {
                BioLog.w("DeviceTokenClient.initToken() GOT EXCEPTION!", th);
                a(context);
            }
        }
    }

    private void a(Context context) {
        try {
            a.a(context, new d(this));
        } catch (Throwable th) {
            BioLog.w("APSecuritySdk.initToken() GOT EXCEPTION!", th);
        }
    }

    public String getApDidToken() {
        String str = this.f814a == null ? "" : this.f814a;
        BioLog.d("AlipayApSecurityService.getApDidToken() == " + str);
        return str;
    }
}
