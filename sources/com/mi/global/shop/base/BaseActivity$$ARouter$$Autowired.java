package com.mi.global.shop.base;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mi.global.shop.base.service.ConnectionHelperService;
import com.mi.global.shop.base.service.LoginManagerService;
import com.mi.global.shop.base.service.MiPushClientService;
import com.mi.global.shop.base.service.WebViewCookieManagerService;

public class BaseActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.a().a(SerializationService.class);
        BaseActivity baseActivity = (BaseActivity) obj;
        baseActivity.loginManagerService = (LoginManagerService) ARouter.a().a(LoginManagerService.class);
        baseActivity.connectionHelperService = (ConnectionHelperService) ARouter.a().a(ConnectionHelperService.class);
        baseActivity.webViewCookieManagerService = (WebViewCookieManagerService) ARouter.a().a(WebViewCookieManagerService.class);
        baseActivity.miPushClientService = (MiPushClientService) ARouter.a().a(MiPushClientService.class);
    }
}
