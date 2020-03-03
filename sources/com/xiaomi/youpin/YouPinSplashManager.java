package com.xiaomi.youpin;

import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mi.global.shop.model.Tags;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.JsonParserUtils;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.pojo.SplashData;
import com.xiaomi.youpin.pojo.SplashItem;
import com.xiaomi.youpin.pojo.WeexCache;
import com.xiaomi.youpin.utils.FrescoUtils;
import com.xiaomi.youpin.youpin_common.SharedDataKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class YouPinSplashManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f23198a = "YouPinSplashManager";
    static final String b = "/shopv3/config?n=";
    private static final String i = "ad_prf";
    private static final String j = "device_ad_prf";
    private static final String k = "prf_data";
    private static final String l = "prf_showned";
    private static final String m = "classify_url";
    private static YouPinSplashManager n = new YouPinSplashManager();
    SharedPreferences c;
    Set<String> d = new HashSet();
    SplashData e;
    boolean f = false;
    long g = 0;
    SplashItem h;

    private YouPinSplashManager() {
    }

    public synchronized void a() {
        if (!this.f) {
            try {
                this.c = XmPluginHostApi.instance().context().getSharedPreferences(i, 0);
                this.d = this.c.getStringSet(l, new HashSet());
                String string = this.c.getString(k, "");
                if (!TextUtils.isEmpty(string)) {
                    this.e = (SplashData) JsonParserUtils.parse((JsonElement) (JsonObject) new JsonParser().parse(string), (String[]) null, SplashData.class);
                    a(this.e);
                }
                this.f = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            return;
        }
        return;
    }

    public ArrayList<WeexCache> b() {
        if (this.e == null) {
            return null;
        }
        return this.e.getWeexcache();
    }

    public static YouPinSplashManager c() {
        return n;
    }

    public synchronized SplashItem d() {
        return this.h;
    }

    public boolean e() {
        if (this.h == null || Fresco.getDraweeControllerBuilderSupplier() == null) {
            return false;
        }
        return Fresco.getImagePipeline().isInDiskCacheSync(Uri.parse(this.h.getImg()));
    }

    public synchronized void a(SplashItem splashItem) {
        this.h = splashItem;
    }

    public void f() {
        a(Tags.BaiduLbs.ADDRTYPE);
    }

    /* access modifiers changed from: package-private */
    public void a(SplashData splashData) {
        if (splashData != null) {
            try {
                if (splashData.getTablinks() != null && splashData.getTablinks().size() > 2) {
                    String str = splashData.getTablinks().get(1);
                    if (!TextUtils.isEmpty(str)) {
                        LogUtil.a("jc", " mijia *** classifyUrl ***  " + str);
                        this.c.edit().putString(m, str).commit();
                    }
                }
                UrlDispatchManger.a().a((List<String>) splashData.getRnmap());
                ArrayList<SplashItem> splash = splashData.getSplash();
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                if (splash != null && splash.size() > 0) {
                    for (int i2 = 0; i2 < splash.size(); i2++) {
                        SplashItem splashItem = splash.get(i2);
                        if (splashItem.getPriority() > -1 && splashItem.getStart() <= currentTimeMillis && splashItem.getEnd() >= currentTimeMillis && (splashItem.isRepeat() || !this.d.contains(splashItem.getLink_param()))) {
                            this.h = splashItem;
                        }
                    }
                    if (!(this.h == null || Fresco.getDraweeControllerBuilderSupplier() == null)) {
                        Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(this.h.getImg())).build(), (Object) null);
                    }
                }
                if (splashData.getSwitchList() != null) {
                    JsonObject jsonObject = new JsonObject();
                    JsonObject jsonObject2 = new JsonObject();
                    for (int i3 = 0; i3 < splashData.getSwitchList().size(); i3++) {
                        jsonObject2.addProperty(splashData.getSwitchList().get(i3), Boolean.TRUE);
                    }
                    jsonObject.add("switch", jsonObject2);
                    XmPluginHostApi.instance().setSharedValue("config", jsonObject.toString(), (Callback<Void>) null);
                    b(jsonObject.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                ShopApp.getInstance().setSdkProperty(Constants.SdkSettings.KEY_ENABLE_CUSTOMER_SERVICE, ((JSONObject) new JSONObject(str).get("switch")).getBoolean(SharedDataKey.e) ? "" : "hide");
            } catch (JSONException e2) {
                LogUtils.d("YouPinSplashManager processInfo() initMiShopSDKCS()", e2.getMessage());
                ShopApp.getInstance().setSdkProperty(Constants.SdkSettings.KEY_ENABLE_CUSTOMER_SERVICE, "hide");
            } catch (Throwable th) {
                ShopApp.getInstance().setSdkProperty(Constants.SdkSettings.KEY_ENABLE_CUSTOMER_SERVICE, "hide");
                throw th;
            }
        }
    }

    public void b(SplashItem splashItem) {
        for (String equals : this.d) {
            if (equals.equals(splashItem.getLink_param())) {
                return;
            }
        }
        this.d.add(splashItem.getLink_param());
        this.c.edit().putStringSet(l, this.d).commit();
        if (this.h != null && !this.h.isRepeat()) {
            this.h = null;
        }
    }

    public void a(String str) {
        if (System.currentTimeMillis() - this.g >= 180000) {
            this.g = System.currentTimeMillis();
            if (XmPluginHostApi.instance() != null) {
                XmPluginHostApi instance = XmPluginHostApi.instance();
                instance.sendMijiaShopRequest(b + str, new JsonObject(), new Callback<SplashData>() {
                    /* renamed from: a */
                    public void onCache(SplashData splashData) {
                    }

                    /* renamed from: a */
                    public void onSuccess(SplashData splashData, boolean z) {
                        YouPinSplashManager.this.b(splashData);
                        YouPinSplashManager.this.a(splashData);
                    }

                    public void onFailure(int i, String str) {
                        YouPinSplashManager.this.g = 0;
                    }
                }, new Parser<SplashData>() {
                    /* renamed from: a */
                    public SplashData parse(JsonElement jsonElement) {
                        ArrayList<SplashItem> splash;
                        SplashData splashData = (SplashData) JsonParserUtils.parse((JsonElement) (JsonObject) jsonElement, (String[]) null, SplashData.class);
                        if (!(splashData == null || (splash = splashData.getSplash()) == null)) {
                            Iterator<SplashItem> it = splash.iterator();
                            while (it.hasNext()) {
                                SplashItem next = it.next();
                                if (!TextUtils.isEmpty(next.getImg())) {
                                    next.setImg(FrescoUtils.a(next.getImg()));
                                }
                            }
                        }
                        return splashData;
                    }
                }, false);
            }
        }
    }

    public void b(SplashData splashData) {
        if (splashData != null && this.c != null) {
            LogUtils.d(f23198a, "saveSplashData");
            SharedPreferences.Editor edit = this.c.edit();
            if (this.e != null && this.e.equals(splashData)) {
                LogUtils.d(f23198a, "saveSplashData splash data has changed");
                this.d.clear();
                edit.putStringSet(l, this.d);
            }
            this.e = splashData;
            try {
                edit.putString(k, new Gson().toJson((Object) splashData));
                edit.apply();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
