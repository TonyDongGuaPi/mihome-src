package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.mi.global.shop.feature.search.SearchActivity;
import com.mi.global.shop.feature.search.SearchResultFragment;
import com.mi.global.shop.router.RouterConfig;
import java.util.Map;

public class ARouter$$Group$$featureGlobalshopSearch implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterConfig.d, RouteMeta.build(RouteType.FRAGMENT, SearchResultFragment.class, "/featureglobalshopsearch/searchresultfragment", "featureglobalshopsearch", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterConfig.c, RouteMeta.build(RouteType.ACTIVITY, SearchActivity.class, "/featureglobalshopsearch/searchactivity", "featureglobalshopsearch", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
