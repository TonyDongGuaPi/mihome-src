package com.xiaomi.smarthome.shop;

import com.facebook.react.bridge.WritableNativeMap;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.youpin.ServiceTokenUtil;
import com.xiaomi.youpin.YouPinSplashManager;
import com.xiaomi.youpin.app_sdk.weex.AbsWXStoreApiProviderImpl;
import com.xiaomi.youpin.pojo.WeexCache;
import com.youpin.weex.app.model.pojo.PreCachePage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class WXStoreApiProviderImp extends AbsWXStoreApiProviderImpl {
    public void a(String str) {
        XmPluginHostApi.instance().openUrl(str);
    }

    public Map a() {
        return ((WritableNativeMap) ServiceTokenUtil.a()).toHashMap();
    }

    public ArrayList<PreCachePage> b() {
        ArrayList<WeexCache> b = YouPinSplashManager.c().b();
        if (b == null || b.size() == 0) {
            return null;
        }
        ArrayList<PreCachePage> arrayList = new ArrayList<>();
        Iterator<WeexCache> it = b.iterator();
        while (it.hasNext()) {
            WeexCache next = it.next();
            PreCachePage preCachePage = new PreCachePage();
            preCachePage.setForce(next.isForce());
            preCachePage.setPage(next.getPage());
            arrayList.add(preCachePage);
        }
        return arrayList;
    }

    public void a(Throwable th) {
        if (th != null) {
            th.printStackTrace();
            CrashReport.postCatchedException(th);
        }
    }
}
