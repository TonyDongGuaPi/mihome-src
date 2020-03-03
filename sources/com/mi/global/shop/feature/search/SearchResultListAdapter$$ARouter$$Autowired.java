package com.mi.global.shop.feature.search;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mi.global.shop.base.service.LocaleService;

public class SearchResultListAdapter$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.a().a(SerializationService.class);
        ((SearchResultListAdapter) obj).localeService = (LocaleService) ARouter.a().a(LocaleService.class);
    }
}
