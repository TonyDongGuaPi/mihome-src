package com.mi.global.shop.base.request;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mi.global.shop.base.service.CookieUtilService;
import com.mi.global.shop.base.service.LoginManagerService;

public class MiJsonObjectRequest$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.a().a(SerializationService.class);
        MiJsonObjectRequest miJsonObjectRequest = (MiJsonObjectRequest) obj;
        miJsonObjectRequest.cookieUtilService = (CookieUtilService) ARouter.a().a(CookieUtilService.class);
        miJsonObjectRequest.loginManagerService = (LoginManagerService) ARouter.a().a(LoginManagerService.class);
    }
}
