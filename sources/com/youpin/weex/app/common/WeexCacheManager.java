package com.youpin.weex.app.common;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.common.WXResponse;
import com.taobao.weex.http.WXHttpUtil;
import com.xiaomi.youpin.common.cache.StringCache;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.common.util.crypto.MD5Utils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import com.youpin.weex.app.model.pojo.DownloadJsBundleInfo;
import com.youpin.weex.app.model.pojo.JSBundleBean;
import com.youpin.weex.app.model.pojo.UpdateActBean;
import com.youpin.weex.app.util.OpenUtils;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeexCacheManager {
    private static final String b = "WeexCacheManager";
    private static final String c = "weex_bundle_key_cache";
    private static final String d = "weex_cache_js_dir";
    private static final String e = "st_weex_bundle_key_cache";
    private static final String f = "st_weex_cache_js_dir";
    private static final int g = 50;
    private static final long h = 300000;

    /* renamed from: a  reason: collision with root package name */
    WXAppStoreApiManager f2511a;
    private StringCache i;
    private StringCache j;
    private StringCache k;
    private StringCache l;
    /* access modifiers changed from: private */
    public Map<Uri, String> m;

    public interface LoadCallBackListener {
        void a();

        void a(String str);

        void a(String str, String str2, String str3, String str4, String str5, String str6, boolean z);
    }

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static WeexCacheManager f2514a = new WeexCacheManager();

        private Holder() {
        }
    }

    public static WeexCacheManager a() {
        return Holder.f2514a;
    }

    private WeexCacheManager() {
        this.f2511a = WXAppStoreApiManager.b();
        Application a2 = AppInfo.a();
        this.i = StringCache.b(a2, c);
        this.j = StringCache.a(a2, d, 50, false);
        this.k = StringCache.b(a2, e);
        this.l = StringCache.a(a2, f, 50, false);
        this.m = new HashMap();
    }

    public HashMap<String, Object> b() {
        JSBundleBean jSBundleBean;
        StringCache d2 = d();
        Set<String> a2 = d2.a();
        if (a2 == null || a2.size() <= 0) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        for (String next : a2) {
            String a3 = d2.d(next);
            if (!TextUtils.isEmpty(a3)) {
                try {
                    jSBundleBean = (JSBundleBean) JSON.parseObject(a3, JSBundleBean.class);
                } catch (Exception unused) {
                    jSBundleBean = null;
                }
                if (jSBundleBean != null) {
                    hashMap.put(next, "id:" + jSBundleBean.id + " version:" + jSBundleBean.jsVersion);
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public StringCache d() {
        StoreApiProvider b2 = StoreApiManager.a().b();
        return (b2 == null || !b2.f()) ? this.i : this.k;
    }

    /* access modifiers changed from: private */
    public StringCache e() {
        StoreApiProvider b2 = StoreApiManager.a().b();
        return (b2 == null || !b2.f()) ? this.j : this.l;
    }

    /* access modifiers changed from: private */
    public String f() {
        StoreApiProvider b2 = StoreApiManager.a().b();
        File file = new File(AppInfo.a().getCacheDir(), (b2 == null || !b2.f()) ? d : f);
        return "file:///" + file.getAbsolutePath();
    }

    public void a(String str) {
        LogUtils.e(b, "remove cache", str);
        d().c(str);
    }

    public void a(Uri uri, LoadCallBackListener loadCallBackListener) {
        a(uri, false, loadCallBackListener);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.net.Uri r20, boolean r21, com.youpin.weex.app.common.WeexCacheManager.LoadCallBackListener r22) {
        /*
            r19 = this;
            r7 = r19
            r5 = r20
            r0 = r22
            java.lang.String r1 = r20.getPath()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0018
            if (r0 == 0) goto L_0x0017
            java.lang.String r1 = "path is empty"
            r0.a(r1)
        L_0x0017:
            return
        L_0x0018:
            java.lang.String r2 = "/"
            int r2 = r1.lastIndexOf(r2)
            r3 = 1
            int r2 = r2 + r3
            java.lang.String r2 = r1.substring(r2)
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 == 0) goto L_0x0032
            if (r0 == 0) goto L_0x0031
            java.lang.String r1 = "path is empty"
            r0.a(r1)
        L_0x0031:
            return
        L_0x0032:
            java.lang.String r1 = "WeexCacheManager"
            r4 = 2
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.String r8 = "path:"
            r9 = 0
            r6[r9] = r8
            r6[r3] = r2
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r1, (java.lang.Object[]) r6)
            com.xiaomi.youpin.common.cache.StringCache r1 = r19.d()
            java.lang.String r1 = r1.d((java.lang.String) r2)
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 == 0) goto L_0x005a
            java.lang.String r1 = "WeexCacheManager"
            java.lang.String r3 = "js bundle file is not in cache, load server"
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r1, (java.lang.String) r3)
            r7.a((java.lang.String) r2, (android.net.Uri) r5, (com.youpin.weex.app.common.WeexCacheManager.LoadCallBackListener) r0)
            return
        L_0x005a:
            java.lang.String r6 = "WeexCacheManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = "jsfileKey:"
            r8.append(r10)
            r8.append(r1)
            java.lang.String r8 = r8.toString()
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r6, (java.lang.String) r8)
            r6 = 0
            java.lang.Class<com.youpin.weex.app.model.pojo.JSBundleBean> r8 = com.youpin.weex.app.model.pojo.JSBundleBean.class
            java.lang.Object r8 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r1, r8)     // Catch:{ Exception -> 0x007a }
            com.youpin.weex.app.model.pojo.JSBundleBean r8 = (com.youpin.weex.app.model.pojo.JSBundleBean) r8     // Catch:{ Exception -> 0x007a }
            r6 = r8
        L_0x007a:
            if (r6 != 0) goto L_0x008e
            java.lang.String r3 = "WeexCacheManager"
            java.lang.String r4 = "js key error, force use server js"
            com.xiaomi.youpin.log.LogUtils.e((java.lang.String) r3, (java.lang.String) r4)
            com.xiaomi.youpin.common.cache.StringCache r3 = r19.d()
            r3.c(r1)
            r7.a((java.lang.String) r2, (android.net.Uri) r5, (com.youpin.weex.app.common.WeexCacheManager.LoadCallBackListener) r0)
            return
        L_0x008e:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = r6.jsVersion
            r8.append(r10)
            java.lang.String r10 = r6.jsbundleUrl
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            java.lang.String r15 = com.xiaomi.youpin.common.util.crypto.MD5Utils.e(r8)
            com.xiaomi.youpin.common.cache.StringCache r8 = r19.e()
            boolean r8 = r8.b(r15)
            if (r8 != 0) goto L_0x00c7
            java.lang.String r6 = "WeexCacheManager"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r8 = "cache file not exist"
            r4[r9] = r8
            r4[r3] = r15
            com.xiaomi.youpin.log.LogUtils.e((java.lang.String) r6, (java.lang.Object[]) r4)
            com.xiaomi.youpin.common.cache.StringCache r3 = r19.d()
            r3.c(r1)
            r7.a((java.lang.String) r2, (android.net.Uri) r5, (com.youpin.weex.app.common.WeexCacheManager.LoadCallBackListener) r0)
            return
        L_0x00c7:
            com.youpin.weex.app.common.TimeManager r8 = com.youpin.weex.app.common.TimeManager.a()
            java.lang.String r10 = "cache_load"
            r8.b(r10)
            com.xiaomi.youpin.common.cache.StringCache r8 = r19.e()
            java.lang.String r11 = r8.d((java.lang.String) r15)
            com.youpin.weex.app.common.TimeManager r8 = com.youpin.weex.app.common.TimeManager.a()
            java.lang.String r10 = "cache_load"
            r8.c(r10)
            boolean r8 = android.text.TextUtils.isEmpty(r11)
            if (r8 == 0) goto L_0x00ff
            java.lang.String r6 = "WeexCacheManager"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r8 = "js bundle content is empty"
            r4[r9] = r8
            r4[r3] = r15
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r6, (java.lang.Object[]) r4)
            com.xiaomi.youpin.common.cache.StringCache r3 = r19.d()
            r3.c(r1)
            r7.a((java.lang.String) r2, (android.net.Uri) r5, (com.youpin.weex.app.common.WeexCacheManager.LoadCallBackListener) r0)
            return
        L_0x00ff:
            java.lang.String r14 = com.xiaomi.youpin.common.util.crypto.MD5Utils.e(r11)
            java.lang.String r8 = r6.md5
            boolean r8 = r14.equalsIgnoreCase(r8)
            r10 = 3
            if (r8 != 0) goto L_0x0128
            java.lang.String r8 = "WeexCacheManager"
            java.lang.Object[] r10 = new java.lang.Object[r10]
            java.lang.String r11 = "local js bundle md5 error, content and save:"
            r10[r9] = r11
            r10[r3] = r14
            java.lang.String r3 = r6.md5
            r10[r4] = r3
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r8, (java.lang.Object[]) r10)
            com.xiaomi.youpin.common.cache.StringCache r3 = r19.d()
            r3.c(r1)
            r7.a((java.lang.String) r2, (android.net.Uri) r5, (com.youpin.weex.app.common.WeexCacheManager.LoadCallBackListener) r0)
            return
        L_0x0128:
            long r12 = java.lang.System.currentTimeMillis()
            long r4 = r6.time
            long r12 = r12 - r4
            com.xiaomi.youpin.youpin_common.StoreApiManager r1 = com.xiaomi.youpin.youpin_common.StoreApiManager.a()
            com.xiaomi.youpin.youpin_common.StoreApiProvider r1 = r1.b()
            if (r1 == 0) goto L_0x016f
            boolean r8 = r1.g()
            if (r8 == 0) goto L_0x016f
            java.lang.String r8 = "Dev_WeexTime"
            java.lang.String r4 = ""
            java.lang.Object r1 = r1.b((java.lang.String) r8, (java.lang.Object) r4)
            java.lang.String r1 = (java.lang.String) r1
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x016f
            int r4 = java.lang.Integer.parseInt(r1)
            int r4 = r4 * 1000
            long r4 = (long) r4
            java.lang.String r8 = "WeexCacheManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r3 = "Dev_WeexTime: "
            r10.append(r3)
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r8, (java.lang.String) r1)
            r17 = r4
            goto L_0x0172
        L_0x016f:
            r17 = 300000(0x493e0, double:1.482197E-318)
        L_0x0172:
            r1 = 4
            if (r21 != 0) goto L_0x01c7
            int r4 = (r12 > r17 ? 1 : (r12 == r17 ? 0 : -1))
            if (r4 <= 0) goto L_0x017a
            goto L_0x01c7
        L_0x017a:
            java.lang.String r3 = "WeexCacheManager"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r4 = "load from cache, size:"
            r1[r9] = r4
            int r4 = r11.length()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5 = 1
            r1[r5] = r4
            r4 = 2
            r1[r4] = r15
            r4 = 3
            r1[r4] = r0
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r3, (java.lang.Object[]) r1)
            if (r0 == 0) goto L_0x01c6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r19.f()
            r1.append(r3)
            java.lang.String r3 = "/"
            r1.append(r3)
            r1.append(r15)
            java.lang.String r10 = r1.toString()
            java.lang.String r12 = r6.jsbundleUrl
            java.lang.String r13 = r6.htmlUrl
            java.util.Map<android.net.Uri, java.lang.String> r1 = r7.m
            r5 = r20
            java.lang.Object r1 = r1.get(r5)
            r14 = r1
            java.lang.String r14 = (java.lang.String) r14
            r15 = 1
            r8 = r22
            r9 = r2
            r8.a(r9, r10, r11, r12, r13, r14, r15)
        L_0x01c6:
            return
        L_0x01c7:
            r5 = r20
            java.lang.String r4 = "WeexCacheManager"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r8 = "bigger than 5 min, so load cache amd server"
            r1[r9] = r8
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r21)
            r8 = 1
            r1[r8] = r3
            r8 = 60
            long r12 = r12 / r8
            java.lang.Long r3 = java.lang.Long.valueOf(r12)
            r8 = 2
            r1[r8] = r3
            r3 = 3
            r1[r3] = r0
            com.xiaomi.youpin.log.LogUtils.w((java.lang.String) r4, (java.lang.Object[]) r1)
            if (r0 == 0) goto L_0x021b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r19.f()
            r1.append(r3)
            java.lang.String r3 = "/"
            r1.append(r3)
            r1.append(r15)
            java.lang.String r10 = r1.toString()
            java.lang.String r12 = r6.jsbundleUrl
            java.lang.String r13 = r6.htmlUrl
            java.util.Map<android.net.Uri, java.lang.String> r1 = r7.m
            java.lang.Object r1 = r1.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r3 = 1
            r8 = r22
            r9 = r2
            r4 = r14
            r14 = r1
            r16 = r15
            r15 = r3
            r8.a(r9, r10, r11, r12, r13, r14, r15)
            goto L_0x021e
        L_0x021b:
            r4 = r14
            r16 = r15
        L_0x021e:
            r8 = 0
            r0 = r19
            r1 = r6
            r3 = r4
            r4 = r16
            r5 = r20
            r6 = r8
            r0.a(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.common.WeexCacheManager.a(android.net.Uri, boolean, com.youpin.weex.app.common.WeexCacheManager$LoadCallBackListener):void");
    }

    private void a(String str, Uri uri, LoadCallBackListener loadCallBackListener) {
        a((JSBundleBean) null, str, (String) null, (String) null, uri, loadCallBackListener);
    }

    private void a(JSBundleBean jSBundleBean, String str, String str2, String str3, Uri uri, LoadCallBackListener loadCallBackListener) {
        LogUtils.d(b, "loadCheckJs", str, uri);
        if (loadCallBackListener != null) {
            loadCallBackListener.a();
        }
        TimeManager.a().b("check");
        final Uri uri2 = uri;
        final LoadCallBackListener loadCallBackListener2 = loadCallBackListener;
        final String str4 = str;
        final String str5 = str3;
        final String str6 = str2;
        final JSBundleBean jSBundleBean2 = jSBundleBean;
        OpenUtils.a(uri, new OpenUtils.UpdateActCallback() {
            public void a(String str, UpdateActBean.DataBean dataBean) {
                TimeManager.a().c("check");
                WeexCacheManager.this.m.put(uri2, str);
                if (dataBean != null) {
                    String url = dataBean.getUrl();
                    String html_url = dataBean.getHtml_url();
                    String valueOf = String.valueOf(dataBean.getVersion());
                    LogUtils.d(WeexCacheManager.b, "loadCheckJs success, and to download js", url, html_url, valueOf, loadCallBackListener2);
                    DownloadJsBundleInfo downloadJsBundleInfo = new DownloadJsBundleInfo();
                    downloadJsBundleInfo.path = str4;
                    downloadJsBundleInfo.oldMd5FileKey = str5;
                    downloadJsBundleInfo.bundleUrl = url;
                    downloadJsBundleInfo.htmlUrl = html_url;
                    downloadJsBundleInfo.version = valueOf;
                    downloadJsBundleInfo.md5 = dataBean.getMd5();
                    downloadJsBundleInfo.id = dataBean.getId();
                    if (TextUtils.isEmpty(downloadJsBundleInfo.md5)) {
                        LogUtils.d(WeexCacheManager.b, "server not return md5");
                        if (loadCallBackListener2 != null) {
                            loadCallBackListener2.a("server not return md5");
                        }
                    } else if (TextUtils.isEmpty(str6) || !str6.equalsIgnoreCase(downloadJsBundleInfo.md5)) {
                        WeexCacheManager.this.a(uri2, downloadJsBundleInfo, loadCallBackListener2);
                    } else {
                        LogUtils.d(WeexCacheManager.b, "local file md5 == server file");
                        if (jSBundleBean2 != null) {
                            jSBundleBean2.time = System.currentTimeMillis();
                        }
                        WeexCacheManager.this.d().a(str4, JSON.toJSONString(jSBundleBean2));
                    }
                } else {
                    LogUtils.d(WeexCacheManager.b, "loadCheckJs fail", loadCallBackListener2);
                    if (loadCallBackListener2 != null) {
                        loadCallBackListener2.a("check error");
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final Uri uri, final DownloadJsBundleInfo downloadJsBundleInfo, final LoadCallBackListener loadCallBackListener) {
        IWXHttpAdapter iWXHttpAdapter = WXSDKManager.getInstance().getIWXHttpAdapter();
        WXRequest wXRequest = new WXRequest();
        wXRequest.url = downloadJsBundleInfo.bundleUrl;
        if (wXRequest.paramMap == null) {
            wXRequest.paramMap = new HashMap();
        }
        wXRequest.paramMap.put(WXHttpUtil.KEY_USER_AGENT, WXHttpUtil.assembleUserAgent(this.f2511a.d().getApplicationContext(), WXEnvironment.getConfig()));
        TimeManager.a().b(Constants.TitleMenu.MENU_DOWNLOAD);
        iWXHttpAdapter.sendRequest(wXRequest, new IWXHttpAdapter.OnHttpListener() {
            public void onHeadersReceived(int i, Map<String, List<String>> map) {
            }

            public void onHttpResponseProgress(int i) {
            }

            public void onHttpStart() {
            }

            public void onHttpUploadProgress(int i) {
            }

            public void onHttpFinish(WXResponse wXResponse) {
                TimeManager.a().c(Constants.TitleMenu.MENU_DOWNLOAD);
                if (wXResponse == null || wXResponse.originalData == null || !TextUtils.equals(XmlyAuthErrorNoConstants.g, wXResponse.statusCode)) {
                    LogUtils.d(WeexCacheManager.b, "downloadJsBundle finish fail");
                    String str = wXResponse != null ? wXResponse.statusCode : "-1";
                    if (loadCallBackListener != null) {
                        LoadCallBackListener loadCallBackListener = loadCallBackListener;
                        loadCallBackListener.a("download js bundle error, code:" + str);
                        return;
                    }
                    LogUtils.d(WeexCacheManager.b, "downloadJsBundle finish fail but don't callback", str);
                    return;
                }
                String str2 = new String(wXResponse.originalData);
                String e = MD5Utils.e(str2);
                if (!e.equalsIgnoreCase(downloadJsBundleInfo.md5)) {
                    LogUtils.d(WeexCacheManager.b, "download file md5 error,download and server:", e, downloadJsBundleInfo.md5);
                    if (loadCallBackListener != null) {
                        LoadCallBackListener loadCallBackListener2 = loadCallBackListener;
                        loadCallBackListener2.a("download file md5 error,download and server:" + e + " , " + downloadJsBundleInfo.md5);
                        return;
                    }
                    return;
                }
                JSBundleBean jSBundleBean = new JSBundleBean();
                jSBundleBean.md5 = e;
                jSBundleBean.id = downloadJsBundleInfo.id;
                jSBundleBean.jsbundleUrl = downloadJsBundleInfo.bundleUrl;
                jSBundleBean.jsVersion = downloadJsBundleInfo.version;
                jSBundleBean.htmlUrl = downloadJsBundleInfo.htmlUrl;
                jSBundleBean.time = System.currentTimeMillis();
                String jSONString = JSON.toJSONString(jSBundleBean);
                WeexCacheManager.this.d().a(downloadJsBundleInfo.path, jSONString);
                String e2 = MD5Utils.e(jSBundleBean.jsVersion + jSBundleBean.jsbundleUrl);
                if (!TextUtils.isEmpty(downloadJsBundleInfo.oldMd5FileKey)) {
                    LogUtils.w(WeexCacheManager.b, "remove old cache File", downloadJsBundleInfo.oldMd5FileKey);
                    WeexCacheManager.this.e().c(downloadJsBundleInfo.oldMd5FileKey);
                }
                WeexCacheManager.this.e().a(e2, str2);
                LogUtils.d(WeexCacheManager.b, "downloadJsBundle finish success", e2, downloadJsBundleInfo.path, jSONString, "value size:", Integer.valueOf(str2.length()));
                if (loadCallBackListener != null) {
                    LoadCallBackListener loadCallBackListener3 = loadCallBackListener;
                    String str3 = downloadJsBundleInfo.path;
                    loadCallBackListener3.a(str3, WeexCacheManager.this.f() + "/" + e2, str2, downloadJsBundleInfo.bundleUrl, downloadJsBundleInfo.htmlUrl, (String) WeexCacheManager.this.m.get(uri), false);
                    return;
                }
                LogUtils.d(WeexCacheManager.b, "downloadJsBundle finish success but don't callback");
            }
        });
    }

    public void c() {
        d().c();
        e().c();
    }
}
