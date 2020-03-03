package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.global.shop.base.service.CookieUtilService;
import com.mi.global.shop.request.CookieUtil;

@Route(path = "/shopSdk/CookieUtilService")
public class CookieUtilServiceImpl implements CookieUtilService {
    public void init(Context context) {
    }

    public String a() {
        return CookieUtil.a();
    }
}
