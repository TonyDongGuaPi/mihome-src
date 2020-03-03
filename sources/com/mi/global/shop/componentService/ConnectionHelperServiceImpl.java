package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.global.shop.base.service.ConnectionHelperService;
import com.mi.global.shop.util.ConnectionHelper;

@Route(path = "/shopSdk/ConnectionHelperService")
public class ConnectionHelperServiceImpl implements ConnectionHelperService {
    public String c() {
        return ConnectionHelper.I;
    }

    public void init(Context context) {
    }

    public String a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        return ConnectionHelper.b(str, str2, str3, str4, str5, str6, str7);
    }

    public String a() {
        return ConnectionHelper.bv();
    }

    public String a(String str) {
        return ConnectionHelper.q(str);
    }

    public String b() {
        return ConnectionHelper.u;
    }

    public String d() {
        return ConnectionHelper.z();
    }

    public boolean b(String str) {
        return ConnectionHelper.l(str);
    }

    public String e() {
        return ConnectionHelper.aA();
    }
}
