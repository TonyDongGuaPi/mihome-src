package com.alipay.android.phone.mobilecommon.apsecurity;

class d implements e {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AlipayApSecurityService f817a;

    d(AlipayApSecurityService alipayApSecurityService) {
        this.f817a = alipayApSecurityService;
    }

    public void a(String str) {
        String unused = this.f817a.f814a = str;
        String unused2 = AlipayApSecurityService.sApdidToken = str;
    }
}
