package com.youpin.weex.app.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.alibaba.android.bindingx.plugin.weex.BindingX;
import com.google.android.exoplayer2.C;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.common.WXConfig;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXJSExceptionInfo;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.taobao.weex.ui.component.WXComponent;
import com.xiaomi.youpin.common.cache.StringCache;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.cookie.CookieConfig;
import com.xiaomi.youpin.cookie.CookieConfigManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomiyoupin.toast.YPDToast;
import com.xiaomiyoupin.ypdimage.YPDImage;
import com.youpin.weex.app.WXPageActivity;
import com.youpin.weex.app.YPCustomNavBarSetter;
import com.youpin.weex.app.adapter.YPAccountAdapter;
import com.youpin.weex.app.adapter.YPCookieAdapter;
import com.youpin.weex.app.adapter.YPLinkingAdapter;
import com.youpin.weex.app.adapter.YPNetInfoAdapter;
import com.youpin.weex.app.adapter.YPStatisticsAdapter;
import com.youpin.weex.app.adapter.YPWXBundleManagerAdapter;
import com.youpin.weex.app.adapter.YPWXPayAdapter;
import com.youpin.weex.app.adapter.YPWXSocialShareAdapter;
import com.youpin.weex.app.common.WeexCacheManager;
import com.youpin.weex.app.extend.FrescoImageAdapter;
import com.youpin.weex.app.extend.HttpAdapter;
import com.youpin.weex.app.extend.component.fresco_image.FrescoImageComponent;
import com.youpin.weex.app.extend.component.slider_neighbor.WXIndicator;
import com.youpin.weex.app.extend.component.slider_neighbor.WXSlider;
import com.youpin.weex.app.extend.component.slider_neighbor.WXSliderNeighbor;
import com.youpin.weex.app.extend.component.text.WXYPText;
import com.youpin.weex.app.extend.component.video.WeexExoplayer;
import com.youpin.weex.app.model.pojo.PreCachePage;
import com.youpin.weex.app.module.WXEventModule;
import com.youpin.weex.app.module.account.AccountModule;
import com.youpin.weex.app.module.account.IAccountAdapter;
import com.youpin.weex.app.module.bundlemanager.IWXBundleManagerAdapter;
import com.youpin.weex.app.module.bundlemanager.WXBundleManagerModule;
import com.youpin.weex.app.module.cookie.CookieManagerModule;
import com.youpin.weex.app.module.cookie.ICookieAdapter;
import com.youpin.weex.app.module.event_monitor.EventMonitorModule;
import com.youpin.weex.app.module.linking.ILinkingAdapter;
import com.youpin.weex.app.module.linking.LinkingModule;
import com.youpin.weex.app.module.netinfo.INetInfoAdapter;
import com.youpin.weex.app.module.netinfo.NetInfoModule;
import com.youpin.weex.app.module.payment.IWXPayAdapter;
import com.youpin.weex.app.module.payment.WXPayModule;
import com.youpin.weex.app.module.share.IWXSocialShareAdapter;
import com.youpin.weex.app.module.share.WXSocialShareModule;
import com.youpin.weex.app.module.statistics.IStatisticsAdapter;
import com.youpin.weex.app.module.statistics.StatisticsModule;
import com.youpin.weex.app.ui.WeexFragmentForPager;
import com.youpin.weex.app.util.OpenUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WXAppStoreApiManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2504a = "success";
    public static final String b = "failed";
    public static final String c = "result";
    public static final String d = "msg";
    public static final String e = "data";
    public static final String f = "errorCode";
    /* access modifiers changed from: private */
    public WXStoreApiProvider g;
    private WeakReference<Activity> h;
    /* access modifiers changed from: private */
    public YPAdapterManager i;

    public HashMap<String, Object> a() {
        return WeexCacheManager.a().b();
    }

    private static class Holder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static WXAppStoreApiManager f2509a = new WXAppStoreApiManager();

        private Holder() {
        }
    }

    public static WXAppStoreApiManager b() {
        return Holder.f2509a;
    }

    public WXStoreApiProvider c() {
        return this.g;
    }

    public Application d() {
        return AppInfo.a();
    }

    public void a(WXStoreApiProvider wXStoreApiProvider) {
        Application a2 = AppInfo.a();
        this.g = wXStoreApiProvider;
        CookieConfigManager.a().a(new CookieConfig.Builder(a2.getApplicationContext()).a());
        this.i = new YPAdapterManager();
        UserAgent.a(OpenUtils.e, "1", OpenUtils.f);
        WeexConstant.a((Context) a2);
        g();
    }

    private void g() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                WXSDKEngine.addCustomOptions("appName", StoreApiManager.a().b().d());
                WXSDKEngine.addCustomOptions(WXConfig.appGroup, "XIAOMI");
                WXSDKEngine.initialize(AppInfo.a(), new InitConfig.Builder().setImgAdapter(new FrescoImageAdapter()).setHttpAdapter(new HttpAdapter()).setJSExceptionAdapter(new IWXJSExceptionAdapter() {
                    public void onJSException(WXJSExceptionInfo wXJSExceptionInfo) {
                    }
                }).build());
                WXAppStoreApiManager.this.i.a(IAccountAdapter.class, new YPAccountAdapter());
                WXAppStoreApiManager.this.i.a(ICookieAdapter.class, new YPCookieAdapter());
                WXAppStoreApiManager.this.i.a(IWXPayAdapter.class, new YPWXPayAdapter());
                WXAppStoreApiManager.this.i.a(IWXSocialShareAdapter.class, new YPWXSocialShareAdapter());
                WXAppStoreApiManager.this.i.a(IStatisticsAdapter.class, new YPStatisticsAdapter());
                WXAppStoreApiManager.this.i.a(INetInfoAdapter.class, new YPNetInfoAdapter());
                WXAppStoreApiManager.this.i.a(ILinkingAdapter.class, new YPLinkingAdapter());
                WXAppStoreApiManager.this.i.a(IWXBundleManagerAdapter.class, new YPWXBundleManagerAdapter());
                try {
                    WXSDKEngine.registerModule("event", WXEventModule.class);
                    WXSDKEngine.registerModule(CookieManagerModule.MODULE_NAME, CookieManagerModule.class);
                    WXSDKEngine.registerModule(WXSocialShareModule.MODULE_NAME, WXSocialShareModule.class);
                    WXSDKEngine.registerModule(WXPayModule.MODULE_NAME, WXPayModule.class);
                    WXSDKEngine.registerModule(AccountModule.MODULE_NAME, AccountModule.class);
                    WXSDKEngine.registerModule(StatisticsModule.MODULE_NAME, StatisticsModule.class);
                    WXSDKEngine.registerModule(NetInfoModule.MODULE_NAME, NetInfoModule.class);
                    WXSDKEngine.registerModule(LinkingModule.MODULE_NAME, LinkingModule.class);
                    WXSDKEngine.registerModule(WXBundleManagerModule.MODULE_NAME, WXBundleManagerModule.class);
                    WXSDKEngine.registerModule(EventMonitorModule.MODULE_NAME, EventMonitorModule.class);
                    WXSDKEngine.registerModule(YPDToast.getInstance().getWXModuleName(), YPDToast.getInstance().getWXModuleClass());
                    WXSDKEngine.registerModule(YPDImage.a().f(), YPDImage.a().g());
                    WXSDKEngine.registerComponent("image", (Class<? extends WXComponent>) FrescoImageComponent.class);
                    WXSDKEngine.registerComponent("img", (Class<? extends WXComponent>) FrescoImageComponent.class);
                    WXSDKEngine.registerComponent(YPDImage.a().d(), YPDImage.a().e());
                    WXSDKEngine.registerComponent((IFComponentHolder) new SimpleComponentHolder(WXSliderNeighbor.class, new WXSliderNeighbor.Creator()), true, "yp-slider-neighbor");
                    WXSDKEngine.registerComponent("yp-indicator", (Class<? extends WXComponent>) WXIndicator.class, true);
                    WXSDKEngine.registerComponent((IFComponentHolder) new SimpleComponentHolder(WXSlider.class, new WXSlider.Creator()), true, "yp-slider", "yp-cycleslider");
                    WXSDKEngine.registerComponent((IFComponentHolder) new SimpleComponentHolder(WXYPText.class, new WXYPText.Creator()), false, "text");
                    WXSDKEngine.registerComponent("yp-video", (Class<? extends WXComponent>) WeexExoplayer.class, false);
                    BindingX.a();
                } catch (WXException e) {
                    e.printStackTrace();
                }
                WXSDKEngine.setActivityNavBarSetter(new YPCustomNavBarSetter() {
                });
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ArrayList<PreCachePage> b = WXAppStoreApiManager.this.g.b();
                if (b == null || b.size() == 0) {
                    LogUtils.d("Weex", "no cache page");
                    return;
                }
                StringCache b2 = StringCache.b(AppInfo.a(), "weex_precache_time");
                long j = 0;
                try {
                    j = Long.parseLong(b2.d("last_cache_time"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() - j < 3600000) {
                    LogUtils.d("Weex", "span < 1h");
                    return;
                }
                b2.a("last_cache_time", String.valueOf(System.currentTimeMillis()));
                Iterator<PreCachePage> it = b.iterator();
                while (it.hasNext()) {
                    PreCachePage next = it.next();
                    LogUtils.d("Weex", " startPreCache", next.getPage(), Boolean.valueOf(next.getForce()));
                    if (!TextUtils.isEmpty(next.getPage())) {
                        WeexCacheManager.a().a(Uri.parse(next.getPage()), next.getForce(), (WeexCacheManager.LoadCallBackListener) null);
                    } else {
                        return;
                    }
                }
            }
        }, 10000);
    }

    public Object a(Class cls) {
        return this.i.a(cls);
    }

    public void a(String str) {
        WXEnvironment.sRemoteDebugProxyUrl = str;
        WXEnvironment.sDebugServerConnectable = true;
        WXEnvironment.sDebugMode = true;
        WXSDKEngine.reload();
        YPDToast.getInstance().toast((Context) AppInfo.a(), "devtool");
    }

    public void e() {
        WXEnvironment.sDebugServerConnectable = true;
        WXEnvironment.sRemoteDebugMode = false;
        WXEnvironment.sRemoteDebugProxyUrl = "ws://host:8088/debugProxy/native";
    }

    public void a(Activity activity) {
        if (activity == null) {
            this.h = null;
        } else {
            this.h = new WeakReference<>(activity);
        }
    }

    public Activity f() {
        if (this.h == null) {
            return null;
        }
        return (Activity) this.h.get();
    }

    public void b(String str) {
        a((Activity) null, str, -1);
    }

    public void a(Activity activity, String str, int i2) {
        Uri parse = Uri.parse(str);
        boolean booleanQueryParameter = parse.getBooleanQueryParameter("enableTransParent", false);
        if (activity != null) {
            Intent intent = new Intent(activity, WXPageActivity.class);
            intent.setData(parse);
            activity.startActivityForResult(intent, i2);
            return;
        }
        Intent intent2 = new Intent(AppInfo.a(), WXPageActivity.class);
        intent2.setFlags(C.ENCODING_PCM_MU_LAW);
        intent2.setData(parse);
        AppInfo.a().startActivity(intent2);
    }

    public Fragment a(String str, boolean z) {
        WeexFragmentForPager weexFragmentForPager = new WeexFragmentForPager();
        Bundle bundle = new Bundle();
        if (z) {
            if (str.contains("?")) {
                str = str + "&hideNavBar=1&miotstore_transition_type=2";
            } else {
                str = str + "?hideNavBar=1&miotstore_transition_type=2";
            }
        }
        bundle.putString("bundleUrl", str);
        weexFragmentForPager.setArguments(bundle);
        return weexFragmentForPager;
    }
}
