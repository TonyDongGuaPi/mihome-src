package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.mi.global.shop.componentService.AppVersionServiceImpl;
import com.mi.global.shop.componentService.ConnectionHelperServiceImpl;
import com.mi.global.shop.componentService.CookieUtilServiceImpl;
import com.mi.global.shop.componentService.GlobalConfigServiceImpl;
import com.mi.global.shop.componentService.LocaleServiceImpl;
import com.mi.global.shop.componentService.LoginManagerServiceImpl;
import com.mi.global.shop.componentService.WebViewCookieManagerServiceImpl;
import java.util.Map;

public class ARouter$$Providers$$shopsdk implements IProviderGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.mi.global.shop.base.service.AppVersionService", RouteMeta.build(RouteType.PROVIDER, AppVersionServiceImpl.class, "/shopSdk/AppVersionService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("com.mi.global.shop.base.service.ConnectionHelperService", RouteMeta.build(RouteType.PROVIDER, ConnectionHelperServiceImpl.class, "/shopSdk/ConnectionHelperService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("com.mi.global.shop.base.service.CookieUtilService", RouteMeta.build(RouteType.PROVIDER, CookieUtilServiceImpl.class, "/shopSdk/CookieUtilService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("com.mi.global.shop.base.service.GlobalConfigService", RouteMeta.build(RouteType.PROVIDER, GlobalConfigServiceImpl.class, "/shopSdk/GlobalConfigService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("com.mi.global.shop.base.service.LocaleService", RouteMeta.build(RouteType.PROVIDER, LocaleServiceImpl.class, "/shopSdk/LocaleService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("com.mi.global.shop.base.service.LoginManagerService", RouteMeta.build(RouteType.PROVIDER, LoginManagerServiceImpl.class, "/shopSdk/LoginManagerService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("com.mi.global.shop.base.service.WebViewCookieManagerService", RouteMeta.build(RouteType.PROVIDER, WebViewCookieManagerServiceImpl.class, "/shopSdk/WebViewCookieManagerService", "shopSdk", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
