package com.alipay.android.phone.mobilecommon.rpc;

import com.alipay.mobile.security.bio.service.local.rpc.IRpcException;
import com.alipay.zoloz.mobile.a.a.b;

public class e extends b implements IRpcException {
    public e(b bVar) {
        super(Integer.valueOf(bVar.getCode()), bVar.getMsg());
    }
}
