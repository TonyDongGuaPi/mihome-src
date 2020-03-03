package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.base.service.GlobalConfigService;
import com.mi.global.shop.constants.GlobalConfig;

@Route(path = "/shopSdk/GlobalConfigService")
public class GlobalConfigServiceImpl implements GlobalConfigService {
    public void init(Context context) {
    }

    public String a() {
        return GlobalConfig.b;
    }

    public boolean b() {
        return ShopApp.n();
    }
}
