package com.xiaomi.smarthome.operation;

import android.text.TextUtils;
import com.xiaomi.smarthome.config.SHBusinessManager;
import com.xiaomi.smarthome.operation.appcolud.Banner;
import com.xiaomi.smarthome.operation.my.BannerItem;
import com.xiaomi.smarthome.operation.my.TabItem;

public class OperationRoute {
    private OperationRoute() {
    }

    public static void a(Operation operation) {
        if (operation != null && !TextUtils.isEmpty(operation.d)) {
            SHBusinessManager.BusinessContent businessContent = new SHBusinessManager.BusinessContent();
            SHBusinessManager.BusinessTarget businessTarget = new SHBusinessManager.BusinessTarget();
            if (operation.e == 2) {
                operation.e = 4;
            }
            businessTarget.f13951a = operation.e;
            businessTarget.c = operation.d;
            businessTarget.b = operation.c;
            businessContent.j = businessTarget;
            SHBusinessManager.a().b(businessContent);
        }
    }

    public static void a(Banner banner) {
        if (banner != null && !TextUtils.isEmpty(banner.c)) {
            SHBusinessManager.BusinessContent businessContent = new SHBusinessManager.BusinessContent();
            SHBusinessManager.BusinessTarget businessTarget = new SHBusinessManager.BusinessTarget();
            businessTarget.c = banner.c;
            businessTarget.b = banner.b;
            businessTarget.f13951a = 4;
            businessContent.j = businessTarget;
            SHBusinessManager.a().b(businessContent);
        }
    }

    public static void a(BannerItem bannerItem) {
        if (bannerItem != null && !TextUtils.isEmpty(bannerItem.f21134a)) {
            SHBusinessManager.BusinessContent businessContent = new SHBusinessManager.BusinessContent();
            SHBusinessManager.BusinessTarget businessTarget = new SHBusinessManager.BusinessTarget();
            if (bannerItem.h == 2) {
                bannerItem.h = 4;
            }
            businessTarget.f13951a = bannerItem.h;
            businessTarget.c = bannerItem.f21134a;
            businessTarget.b = bannerItem.c;
            businessContent.j = businessTarget;
            SHBusinessManager.a().b(businessContent);
        }
    }

    public static void a(TabItem tabItem) {
        if (tabItem != null && !TextUtils.isEmpty(tabItem.f21148a)) {
            SHBusinessManager.BusinessContent businessContent = new SHBusinessManager.BusinessContent();
            SHBusinessManager.BusinessTarget businessTarget = new SHBusinessManager.BusinessTarget();
            businessTarget.c = tabItem.f21148a;
            businessTarget.b = tabItem.b;
            businessTarget.f13951a = 4;
            businessContent.j = businessTarget;
            SHBusinessManager.a().b(businessContent);
        }
    }
}
