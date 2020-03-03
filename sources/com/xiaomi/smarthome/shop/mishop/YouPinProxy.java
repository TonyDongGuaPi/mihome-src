package com.xiaomi.smarthome.shop.mishop;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.youpin.IShoppingCountCallback;
import com.xiaomi.mishopsdk.youpin.IYouPinProxy;
import com.xiaomi.plugin.RedpointManager;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouPinProxy implements IYouPinProxy {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1571a = "YouPinProxy";
    /* access modifiers changed from: private */
    public Handler b = new Handler();

    public void gotoSearchPage() {
        LogUtils.d(f1571a, "gotoSearchPage");
        XmPluginHostApi.instance().openUrl(UrlConstants.search);
    }

    public void gotoShopCart() {
        LogUtils.d(f1571a, "gotoShopCart");
        XmPluginHostApi.instance().openUrl(UrlConstants.cart);
    }

    public void gotoCheckout(Bundle bundle) {
        LogUtils.d(f1571a, "gotoCheckout");
        boolean z = bundle.getBoolean("quickOrder");
        XmPluginHostApi instance = XmPluginHostApi.instance();
        instance.openUrl("check?quickOrder=" + (z ? 1 : 0) + "&isFromMiShop=1");
    }

    public void gotoHome() {
        XmPluginHostApi.instance().openUrl("main");
    }

    public void getShoppingCount(IShoppingCountCallback iShoppingCountCallback, boolean z) {
        final RedpointManager redpointManager = XmPluginHostApi.instance().getRedpointManager();
        if (!z) {
            final WeakReference weakReference = new WeakReference(iShoppingCountCallback);
            redpointManager.addRedPoint(new RedpointManager.RedpointListener() {
                public void onRefresh() {
                    IShoppingCountCallback iShoppingCountCallback = (IShoppingCountCallback) weakReference.get();
                    if (iShoppingCountCallback != null) {
                        YouPinProxy.this.a(iShoppingCountCallback);
                    }
                    YouPinProxy.this.b.post(new Runnable() {
                        public void run() {
                            redpointManager.removeRedPoint(this);
                        }
                    });
                }
            });
            redpointManager.update();
            return;
        }
        a(iShoppingCountCallback);
    }

    public void showNotifcation(Bundle bundle) {
        LogUtils.d(f1571a, "showNotification() is running.");
        String string = bundle.getString("title");
        String string2 = bundle.getString("content");
        String string3 = bundle.getString("avatar");
        HashMap hashMap = new HashMap();
        hashMap.put("avatar", string3);
        if (string == null) {
            string = "小米客服";
        }
        hashMap.put("username", string);
        if (string2 == null) {
            string2 = "您收到一条新消息";
        }
        hashMap.put("content", string2);
        XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.csPushHeader, hashMap));
    }

    /* access modifiers changed from: private */
    public void a(IShoppingCountCallback iShoppingCountCallback) {
        if (iShoppingCountCallback != null) {
            iShoppingCountCallback.updateShoppingCount(XmPluginHostApi.instance().getRedpointManager().getRedPoint(2));
        }
    }

    public void onPageStart(String str, HashMap<String, String> hashMap) {
        if ("com.xiaomi.shop2.plugin.goodsdetail.GoodsDetailFragment".equals(str)) {
            String str2 = hashMap.get("goods_id");
            XmPluginHostApi.instance().addViewRecord("$MiDetail$", StatManager.a().g(), hashMap.get("product_id"), 0);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("init", "true");
            XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.csPushHeader, hashMap2));
        } else if ("com.xiaomi.shop2.plugin.hdchannel.RootFragment".equals(str)) {
            String str3 = hashMap.get("goods_id");
            XmPluginHostApi.instance().addViewRecord("$MiChannel$", StatManager.a().g(), hashMap.get("product_id"), 0);
        }
    }

    public void onPageEnd(String str, HashMap<String, String> hashMap) {
        XmPluginHostApi.instance().addViewEndRecord();
    }

    public void onEvent(String str, HashMap<String, String> hashMap) {
        if (!TextUtils.isEmpty(str)) {
            if ("MiShopServicesTouch".equalsIgnoreCase(str)) {
                XmPluginHostApi.instance().addTouchRecord("$MiDetail$", "", "service", hashMap.get("goods_id"));
                return;
            }
            Matcher matcher = Pattern.compile("pid=(\\d+)").matcher(str);
            String str2 = "";
            if (matcher.find() && matcher.groupCount() > 0) {
                str2 = matcher.group(1);
            }
            XmPluginHostApi.instance().addTouchRecord("$MiChannel$", "", "list", str2);
        }
    }

    public void onAddCartSuccess(HashMap<String, String> hashMap) {
        String str = hashMap.get("comboGoodsIds");
        XmPluginHostApi.instance().addTouchRecord("$MiDetail$", "", "addCart_success", hashMap.get("goods_id"));
    }
}
