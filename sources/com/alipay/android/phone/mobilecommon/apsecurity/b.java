package com.alipay.android.phone.mobilecommon.apsecurity;

import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.taobao.weex.el.parse.Operators;

final class b implements APSecuritySdk.InitResultListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f815a;

    b(e eVar) {
        this.f815a = eVar;
    }

    public void onResult(APSecuritySdk.TokenResult tokenResult) {
        BioLog.i("APSecuritySdk.initToken() => InitResultListener.onResult(apdidToken=" + tokenResult.apdidToken + Operators.BRACKET_END_STR);
        this.f815a.a(tokenResult.apdidToken);
    }
}
