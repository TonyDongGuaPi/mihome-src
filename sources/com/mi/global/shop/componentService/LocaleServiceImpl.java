package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.global.shop.base.service.LocaleService;
import com.mi.global.shop.locale.LocaleHelper;

@Route(path = "/shopSdk/LocaleService")
public class LocaleServiceImpl implements LocaleService {
    public void init(Context context) {
    }

    public boolean a() {
        return LocaleHelper.q();
    }

    public String b() {
        return LocaleHelper.e();
    }
}
