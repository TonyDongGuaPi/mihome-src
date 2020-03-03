package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.base.service.AppVersionService;

@Route(path = "/shopSdk/AppVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    public void init(Context context) {
    }

    public int a() {
        return Integer.parseInt(ShopApp.m);
    }
}
