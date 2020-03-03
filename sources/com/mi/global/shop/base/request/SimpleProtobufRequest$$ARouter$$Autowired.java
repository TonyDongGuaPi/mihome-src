package com.mi.global.shop.base.request;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mi.global.shop.base.service.AppVersionService;
import com.mi.global.shop.base.service.CookieUtilService;
import com.mi.global.shop.base.service.GlobalConfigService;
import com.mi.global.shop.base.service.LocaleService;

public class SimpleProtobufRequest$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.a().a(SerializationService.class);
        ((SimpleProtobufRequest) obj).cookieUtilService = (CookieUtilService) ARouter.a().a(CookieUtilService.class);
        SimpleProtobufRequest.localeService = (LocaleService) ARouter.a().a(LocaleService.class);
        SimpleProtobufRequest.globalConfigService = (GlobalConfigService) ARouter.a().a(GlobalConfigService.class);
        SimpleProtobufRequest.appVersionService = (AppVersionService) ARouter.a().a(AppVersionService.class);
    }
}
