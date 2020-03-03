package com.mi.global.shop.base.request;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mi.global.shop.base.service.CookieUtilService;

public class MiProtobufRequest$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.a().a(SerializationService.class);
        ((MiProtobufRequest) obj).cookieUtilService = (CookieUtilService) ARouter.a().a(CookieUtilService.class);
    }
}
