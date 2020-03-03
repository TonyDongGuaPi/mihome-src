package com.alipay.android.phone.mobilecommon.apsecurity;

import com.alipay.deviceid.DeviceTokenClient;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.taobao.weex.el.parse.Operators;

final class g implements DeviceTokenClient.InitResultListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f818a;

    g(e eVar) {
        this.f818a = eVar;
    }

    public void onResult(String str, int i) {
        BioLog.i("DeviceTokenClient.initToken() => InitResultListener.onResult(token=" + str + ", errorCode=" + i + Operators.BRACKET_END_STR);
        this.f818a.a(str);
    }
}
