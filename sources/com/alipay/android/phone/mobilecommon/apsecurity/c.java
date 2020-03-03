package com.alipay.android.phone.mobilecommon.apsecurity;

class c implements e {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AlipayApSecurityService f816a;

    c(AlipayApSecurityService alipayApSecurityService) {
        this.f816a = alipayApSecurityService;
    }

    public void a(String str) {
        String unused = this.f816a.f814a = str;
        String unused2 = AlipayApSecurityService.sApdidToken = str;
    }
}
