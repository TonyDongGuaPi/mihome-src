package com.mi.global.shop.feature.search;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mi.global.shop.base.service.GlobalConfigService;

public class SearchActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.a().a(SerializationService.class);
        ((SearchActivity) obj).globalConfigService = (GlobalConfigService) ARouter.a().a(GlobalConfigService.class);
    }
}
