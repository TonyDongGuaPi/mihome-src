package com.xiaomi.smarthome.splashads;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.daimajia.easing.BuildConfig;
import com.google.android.exoplayer2.C;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.ImageDownloadManager;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.splashads.model.MiuiAdsInfo;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MiuiSplashAdsManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22730a = "MiuiSplashAdsManager";
    private static final String b = "https://api.ad.xiaomi.com/u/getSplashAds";
    private static final String c = "e68543d543f00c34b253de6d40954f31";
    private static MiuiSplashAdsManager d = null;
    private static final String f = "miui_splash_ads";
    private HttpAsyncHandle e;
    /* access modifiers changed from: private */
    public List<MiuiAdsInfo> g = new ArrayList();

    public MiuiAdsInfo b() {
        return null;
    }

    public static MiuiSplashAdsManager a() {
        if (d == null) {
            synchronized (MiuiSplashAdsManager.class) {
                if (d == null) {
                    d = new MiuiSplashAdsManager();
                }
            }
        }
        return d;
    }

    public List<MiuiAdsInfo> c() {
        return this.g;
    }

    public void a(List<MiuiAdsInfo> list) {
        this.g.clear();
        this.g.addAll(list);
    }

    public MiuiAdsInfo a(Context context) {
        if (CoreApi.a().D()) {
            return null;
        }
        String string = context.getSharedPreferences(f, 0).getString("content", "");
        LogUtil.a(f22730a, " isSplashAdsCacheValid content:" + string);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            List<MiuiAdsInfo> a2 = MiuiAdsInfo.a(new JSONArray(string));
            if (a2 != null) {
                if (a2.size() != 0) {
                    a(a2);
                    MiuiAdsInfo miuiAdsInfo = null;
                    for (int i = 0; i < a2.size(); i++) {
                        miuiAdsInfo = a2.get(i);
                        if (!(miuiAdsInfo == null || miuiAdsInfo.f == null)) {
                            if (miuiAdsInfo.f.size() != 0) {
                                MiuiAdsInfo.ADAssetsInfo aDAssetsInfo = miuiAdsInfo.f.get(0);
                                if (aDAssetsInfo.c == 1) {
                                    String str = aDAssetsInfo.f22743a;
                                    if (!TextUtils.isEmpty(str)) {
                                        if (ImageDownloadManager.a().a(str)) {
                                            long currentTimeMillis = System.currentTimeMillis();
                                            if (miuiAdsInfo.o <= currentTimeMillis) {
                                                if (miuiAdsInfo.p >= currentTimeMillis) {
                                                    if (!(miuiAdsInfo == null || miuiAdsInfo.Z() == 0)) {
                                                        return miuiAdsInfo;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        miuiAdsInfo = null;
                    }
                    return miuiAdsInfo;
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(BaseActivity baseActivity, final ImageView imageView, MiuiAdsInfo miuiAdsInfo, final IShowAdsCallBack iShowAdsCallBack) {
        if (baseActivity == null || miuiAdsInfo == null || miuiAdsInfo.f == null || miuiAdsInfo.f.size() == 0) {
            imageView.setVisibility(8);
            return;
        }
        String str = miuiAdsInfo.f.get(0).f22743a;
        final String m = miuiAdsInfo.m();
        if (TextUtils.isEmpty(str)) {
            imageView.setVisibility(8);
            return;
        }
        ImageDownloadManager.a().a(str, (ImageDownloadManager.ImageCallback) new ImageDownloadManager.ImageCallback() {
            public void a(Bitmap bitmap) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    if (iShowAdsCallBack != null) {
                        iShowAdsCallBack.a();
                    }
                }
            }

            public void a() {
                if (iShowAdsCallBack != null) {
                    iShowAdsCallBack.b();
                }
            }
        });
        final ImageView imageView2 = imageView;
        final MiuiAdsInfo miuiAdsInfo2 = miuiAdsInfo;
        final BaseActivity baseActivity2 = baseActivity;
        baseActivity.mHandler.postDelayed(new Runnable() {
            public void run() {
                imageView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(m)) {
                            imageView2.setOnClickListener((View.OnClickListener) null);
                            MiuiSplashAdsManager.this.c(miuiAdsInfo2);
                            MiuiSplashAdsManager.this.a(baseActivity2, m);
                            StatHelper.aB();
                            MobclickAgent.a((Context) baseActivity2, StatUtil.h, "miui_splash_click_monitor");
                            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                public void run() {
                                    baseActivity2.finish();
                                }
                            }, 100);
                        }
                    }
                });
            }
        }, 100);
        StatHelper.aA();
        MobclickAgent.a((Context) baseActivity, StatUtil.h, "miui_splash_view_monitor");
        b(miuiAdsInfo);
    }

    /* access modifiers changed from: private */
    public void a(MiuiAdsInfo miuiAdsInfo) {
        LogUtil.a(f22730a, "before delete:" + MiuiAdsInfo.a(this.g));
        if (this.g.contains(miuiAdsInfo)) {
            this.g.remove(miuiAdsInfo);
        }
        LogUtil.a(f22730a, "after delete:" + MiuiAdsInfo.a(this.g));
        b(MiuiAdsInfo.a(this.g), this.g);
        d();
    }

    /* access modifiers changed from: private */
    public void a(BaseActivity baseActivity, String str) {
        Intent intent = new Intent(baseActivity, SmartHomeMainActivity.class);
        intent.putExtras(baseActivity.getIntent());
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        intent.putExtras(bundle);
        intent.putExtra("source", 4);
        intent.putExtra(MiuiSplashActivity.KEY_JUMP_TO_FLAG, true);
        intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        SHApplication.getAppContext().startActivity(intent);
    }

    private void b(final MiuiAdsInfo miuiAdsInfo) {
        List<String> n = miuiAdsInfo.n();
        if (n != null && n.size() != 0) {
            for (int i = 0; i < n.size(); i++) {
                String str = n.get(i);
                if (!TextUtils.isEmpty(str)) {
                    RemoteFamilyApi.a().a(SHApplication.getAppContext(), str, (Map<String, Object>) null, "GET", (AsyncResponseCallback<Object>) new AsyncResponseCallback<Object>() {
                        public void a(Object obj) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("reportViewMonitorUrl onSuccess ");
                            sb.append(obj == null ? null : obj.toString());
                            LogUtil.a(MiuiSplashAdsManager.f22730a, sb.toString());
                            SHApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                                public void run() {
                                    MiuiSplashAdsManager.this.a(miuiAdsInfo);
                                }
                            }, 10000);
                        }

                        public void a(int i) {
                            Miio.b(MiuiSplashAdsManager.f22730a, "reportViewMonitorUrl onFailure" + i);
                            SHApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                                public void run() {
                                    MiuiSplashAdsManager.this.a(miuiAdsInfo);
                                }
                            }, 10000);
                        }

                        public void a(int i, Object obj) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("reportViewMonitorUrl onFailure");
                            sb.append(i);
                            sb.append(",");
                            sb.append(obj == null ? null : obj.toString());
                            Miio.b(MiuiSplashAdsManager.f22730a, sb.toString());
                            SHApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                                public void run() {
                                    MiuiSplashAdsManager.this.a(miuiAdsInfo);
                                }
                            }, 10000);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(MiuiAdsInfo miuiAdsInfo) {
        List<String> p = miuiAdsInfo.p();
        if (p != null && p.size() != 0) {
            for (int i = 0; i < p.size(); i++) {
                String str = p.get(i);
                if (!TextUtils.isEmpty(str)) {
                    RemoteFamilyApi.a().a(SHApplication.getAppContext(), str, (Map<String, Object>) null, "GET", (AsyncResponseCallback<Object>) new AsyncResponseCallback<Object>() {
                        public void a(Object obj) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("reportClickMonitorUrl onSuccess ");
                            sb.append(obj == null ? null : obj.toString());
                            LogUtil.a(MiuiSplashAdsManager.f22730a, sb.toString());
                        }

                        public void a(int i) {
                            LogUtil.a(MiuiSplashAdsManager.f22730a, "reportClickMonitorUrl onFailure" + i);
                        }

                        public void a(int i, Object obj) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("reportClickMonitorUrl onFailure");
                            sb.append(i);
                            sb.append(",");
                            sb.append(obj == null ? null : obj.toString());
                            LogUtil.a(MiuiSplashAdsManager.f22730a, sb.toString());
                        }
                    });
                }
            }
        }
    }

    public void d() {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                MiuiSplashAdsManager.this.e();
            }
        });
    }

    public void e() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) SHApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            STAT.f.a();
            this.e = RemoteFamilyApi.a().a(SHApplication.getAppContext(), b, g(), "POST", (AsyncResponseCallback<Object>) new AsyncResponseCallback<Object>() {
                public void a(Object obj) {
                    if (obj != null && (GlobalSetting.q || GlobalSetting.w)) {
                        LogUtil.a(MiuiSplashAdsManager.f22730a, "onSuccess:" + obj.toString());
                    }
                    try {
                        STAT.f.b();
                        JSONObject jSONObject = new JSONObject(obj.toString());
                        if (jSONObject.isNull("adInfos")) {
                            MiuiSplashAdsManager.b((String) null, (List<MiuiAdsInfo>) null);
                        } else if (jSONObject.optInt("code") != 0) {
                            MiuiSplashAdsManager.b((String) null, (List<MiuiAdsInfo>) null);
                        } else {
                            JSONArray optJSONArray = jSONObject.optJSONArray("adInfos");
                            if (optJSONArray != null) {
                                if (optJSONArray.length() != 0) {
                                    List unused = MiuiSplashAdsManager.this.g = MiuiAdsInfo.a(optJSONArray);
                                    MiuiSplashAdsManager.b(optJSONArray.toString(), (List<MiuiAdsInfo>) MiuiSplashAdsManager.this.g);
                                    return;
                                }
                            }
                            MiuiSplashAdsManager.b((String) null, (List<MiuiAdsInfo>) null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void a(int i) {
                    STAT.f.c();
                    Log.e(MiuiSplashAdsManager.f22730a, "onFailure:" + i);
                }

                public void a(int i, Object obj) {
                    STAT.f.c();
                    StringBuilder sb = new StringBuilder();
                    sb.append("onFailure:");
                    sb.append(i);
                    sb.append(",errorInfo=");
                    sb.append(obj != null ? obj.toString() : null);
                    Log.e(MiuiSplashAdsManager.f22730a, sb.toString());
                }
            });
        }
    }

    public void f() {
        if (this.e != null) {
            this.e.a();
        }
    }

    private static void d(MiuiAdsInfo miuiAdsInfo) {
        if (miuiAdsInfo != null && miuiAdsInfo.f != null && miuiAdsInfo.f.size() != 0) {
            for (int i = 0; i < miuiAdsInfo.f.size(); i++) {
                String str = miuiAdsInfo.f.get(i).f22743a;
                if (!TextUtils.isEmpty(str) && !ImageDownloadManager.a().a(str)) {
                    ImageDownloadManager.a().a(str, (ImageView) null);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, List<MiuiAdsInfo> list) {
        Context appContext = SHApplication.getAppContext();
        if (appContext != null) {
            SharedPreferences sharedPreferences = appContext.getSharedPreferences(f, 0);
            if (str == null || TextUtils.isEmpty(str)) {
                sharedPreferences.edit().putString("content", "").apply();
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                d(list.get(i));
            }
            sharedPreferences.edit().putString("content", str).apply();
            sharedPreferences.edit().putLong("cache_time", System.currentTimeMillis()).apply();
        }
    }

    public static Map<String, Object> g() {
        HashMap hashMap = new HashMap();
        hashMap.put("upId", c);
        hashMap.put("v", BuildConfig.VERSION_NAME);
        hashMap.put("clientInfo", AdsUtil.a());
        return hashMap;
    }
}
