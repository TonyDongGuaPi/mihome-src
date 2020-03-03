package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.router.RouterConfig;
import java.util.Map;

public class ARouter$$Group$$globalShop implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConfig.b, RouteMeta.build(RouteType.ACTIVITY, WebActivity.class, "/globalshop/webactivity", "globalshop", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
