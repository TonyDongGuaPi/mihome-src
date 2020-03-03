package com.xiaomi.miot.store.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.shell.MainReactPackage;
import com.google.android.exoplayer2.C;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.xiaomi.miot.store.api.IMiotStoreApi;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.common.update.Callback;
import com.xiaomi.miot.store.common.update.Constants;
import com.xiaomi.miot.store.component.CustomComponetPackage;
import com.xiaomi.miot.store.ui.MiotStoreMainActivity;
import com.xiaomi.miot.store.ui.MiotStoreSingleTaskMainActivity;
import com.xiaomi.miot.store.ui.MiotStoreTransparentMainActivity;
import com.xiaomi.miot.store.utils.Utils;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.cookie.YouPinCookieManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomi.youpin.youpin_common.lifecycle.AppLifecycleListener;
import com.xiaomi.youpin.youpin_common.lifecycle.AppLifecycleManager;
import com.xiaomi.youpin.youpin_common.login.IYouPinAccountManager;
import com.xiaomi.youpin.youpin_network.ISendRnRequest;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import com.zmxv.RNSound.RNSoundPackage;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RNAppStoreApiManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f1470a = "RNAppStoreApiManager";
    static final String b = "ShopRN";
    private static final int n = 1000;
    private static final int o = 5;
    RNStoreApiProvider c;
    String d;
    ReactInstanceManager e;
    WeakReference<Activity> f;
    Handler g;
    boolean h;
    StoreApiProvider i;
    IntialStatus j;
    MiotStorePackage k;
    long l;
    ArrayList<OnInitialCompleteListener> m;
    private MiotJSUpdateManager p;
    private GetRnVersionTask q;
    private IYouPinAccountManager r;

    enum IntialStatus {
        ENotInitial,
        EInitializing,
        EInitialized
    }

    public interface OnInitialCompleteListener {
        void onInitialFail();

        void onInitialSuccess();
    }

    private static class Holder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static RNAppStoreApiManager f11393a = new RNAppStoreApiManager();

        private Holder() {
        }
    }

    public static RNAppStoreApiManager a() {
        return Holder.f11393a;
    }

    public void a(IYouPinAccountManager iYouPinAccountManager) {
        this.r = iYouPinAccountManager;
    }

    public IYouPinAccountManager b() {
        return this.r;
    }

    public boolean c() {
        return AppLifecycleManager.getInstance().isAppQuit();
    }

    private RNAppStoreApiManager() {
        this.h = false;
        this.j = IntialStatus.ENotInitial;
        this.m = new ArrayList<>();
    }

    public static void a(Application application) {
        AppLifecycleManager.getInstance().init(application);
    }

    public void a(IMiotStoreApi iMiotStoreApi, RNStoreApiProvider rNStoreApiProvider) {
        this.i = StoreApiManager.a().b();
        this.c = rNStoreApiProvider;
        this.g = new Handler(Looper.getMainLooper());
        AppLifecycleManager.getInstance().init(AppInfo.a());
        AppLifecycleManager.getInstance().registerAppLifecycleListener(new AppLifecycleListener() {
            public void a() {
            }

            public void b() {
                RNAppStoreApiManager.this.t();
            }

            public void c() {
                if (Fresco.hasBeenInitialized()) {
                    Fresco.getImagePipeline().clearMemoryCaches();
                }
            }
        });
        this.c.onInitial(iMiotStoreApi);
    }

    public String d() {
        return this.d;
    }

    public Application e() {
        return AppInfo.a();
    }

    public boolean f() {
        return this.j == IntialStatus.EInitializing;
    }

    public boolean g() {
        return this.j == IntialStatus.EInitialized;
    }

    public boolean h() {
        return this.c.enableStore() && Utils.a((Context) AppInfo.a());
    }

    public void a(Activity activity) {
        if (activity == null) {
            this.f = null;
        } else {
            this.f = new WeakReference<>(activity);
        }
    }

    public Activity i() {
        if (this.f == null) {
            return null;
        }
        return (Activity) this.f.get();
    }

    public RNStoreApiProvider j() {
        return this.c;
    }

    public ReactInstanceManager k() {
        return this.e;
    }

    public void a(WritableMap writableMap) {
        a().a("updateAuth", writableMap);
    }

    public void l() {
        LogUtils.d(f1470a, "updateAccount()");
        a(true);
    }

    public void m() {
        LogUtils.d(f1470a, "clearAccount()");
        YouPinCookieManager.a().b();
        a(false);
    }

    public void n() {
        b((OnInitialCompleteListener) null);
    }

    public void o() {
        a(true, false);
    }

    public ReactContext p() {
        if (this.e == null) {
            return null;
        }
        return this.e.getCurrentReactContext();
    }

    private void a(boolean z) {
        if (p() != null) {
            LogUtils.d(f1470a, "sendLoginInfoBroadcast");
            WritableMap createMap = Arguments.createMap();
            createMap.putBoolean("login", z);
            a("LoginInfo", createMap);
        }
    }

    public void a(String str) {
        a((Context) null, str);
    }

    public void q() {
        a((Context) null, "http://home.mi.com/shop/main");
    }

    public void a(Context context, String str) {
        a().j().openUrl(str, "");
    }

    public void b(String str) {
        a((Activity) null, str, -1);
    }

    public void r() {
        a((Activity) null, "http://home.mi.com/shop/main", -1);
    }

    public void a(Activity activity, String str, int i2) {
        Uri parse = Uri.parse(str);
        boolean booleanQueryParameter = parse.getBooleanQueryParameter("enableTransParent", false);
        Class cls = MiotStoreMainActivity.class;
        if (parse.getBooleanQueryParameter("isSingleTask", false)) {
            cls = MiotStoreSingleTaskMainActivity.class;
        } else if (booleanQueryParameter) {
            cls = MiotStoreTransparentMainActivity.class;
        }
        if (activity != null) {
            Intent intent = new Intent(activity, cls);
            intent.setData(parse);
            activity.startActivityForResult(intent, i2);
            return;
        }
        Application a2 = AppInfo.a();
        Intent intent2 = new Intent(a2, cls);
        intent2.setFlags(C.ENCODING_PCM_MU_LAW);
        intent2.setData(parse);
        a2.startActivity(intent2);
    }

    public boolean a(String str, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("uri", str);
        hashMap.put("open", Boolean.valueOf(z));
        return a("MiotStoreEventPreloadData", (Map<String, Object>) hashMap);
    }

    public boolean a(String str, Map<String, Object> map) {
        if (p() == null) {
            return false;
        }
        WritableMap writableMap = null;
        if (map != null && map.size() > 0) {
            writableMap = Arguments.createMap();
            for (String next : map.keySet()) {
                Object obj = map.get(next);
                if (obj instanceof String) {
                    writableMap.putString(next, (String) obj);
                } else if ((obj instanceof Float) || (obj instanceof Double)) {
                    writableMap.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof Boolean) {
                    writableMap.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Integer) {
                    writableMap.putInt(next, ((Integer) obj).intValue());
                }
            }
        }
        return a(str, writableMap);
    }

    public boolean a(String str, WritableMap writableMap) {
        if (writableMap == null) {
            return false;
        }
        LogUtils.d(f1470a, "send js EventMap (): " + str + " data:" + writableMap.toString());
        if (p() == null || !p().hasActiveCatalystInstance()) {
            LogUtils.d(f1470a, "sendJsEventMap failure because not initialized");
            return false;
        }
        try {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) p().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            return true;
        } catch (Exception e2) {
            LogUtils.d(f1470a, "YouPinError to send js event map.");
            e2.printStackTrace();
            return false;
        }
    }

    public void a(int i2, int i3, Intent intent) {
        if (i2 == 1000 && this.k != null) {
            this.k.a(i2, i3, intent);
        }
        StoreApiManager.a().a(i2, i3, intent);
    }

    public synchronized void a(OnInitialCompleteListener onInitialCompleteListener) {
        if (onInitialCompleteListener != null) {
            this.m.remove(onInitialCompleteListener);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        return;
     */
    @android.support.annotation.UiThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.xiaomi.miot.store.common.RNAppStoreApiManager.OnInitialCompleteListener r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x007f }
            java.lang.String r2 = "RNAppStoreApiManager"
            java.lang.String r3 = "initialReact"
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x007f }
            boolean r2 = r6.h()     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0019
            if (r7 == 0) goto L_0x0017
            r7.onInitialFail()     // Catch:{ all -> 0x007f }
        L_0x0017:
            monitor-exit(r6)
            return
        L_0x0019:
            com.xiaomi.miot.store.api.RNStoreApiProvider r2 = r6.c     // Catch:{ all -> 0x007f }
            boolean r2 = r2.isRNDebug()     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0029
            com.xiaomi.miot.store.common.RNAppStoreApiManager$IntialStatus r2 = com.xiaomi.miot.store.common.RNAppStoreApiManager.IntialStatus.EInitialized     // Catch:{ all -> 0x007f }
            r6.j = r2     // Catch:{ all -> 0x007f }
            java.lang.String r2 = ""
            r6.d = r2     // Catch:{ all -> 0x007f }
        L_0x0029:
            int[] r2 = com.xiaomi.miot.store.common.RNAppStoreApiManager.AnonymousClass4.f11391a     // Catch:{ all -> 0x007f }
            com.xiaomi.miot.store.common.RNAppStoreApiManager$IntialStatus r3 = r6.j     // Catch:{ all -> 0x007f }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x007f }
            r2 = r2[r3]     // Catch:{ all -> 0x007f }
            switch(r2) {
                case 1: goto L_0x0056;
                case 2: goto L_0x003f;
                case 3: goto L_0x0037;
                default: goto L_0x0036;
            }     // Catch:{ all -> 0x007f }
        L_0x0036:
            goto L_0x0061
        L_0x0037:
            if (r7 == 0) goto L_0x0061
            java.util.ArrayList<com.xiaomi.miot.store.common.RNAppStoreApiManager$OnInitialCompleteListener> r2 = r6.m     // Catch:{ all -> 0x007f }
            r2.add(r7)     // Catch:{ all -> 0x007f }
            goto L_0x0061
        L_0x003f:
            com.facebook.react.ReactInstanceManager r2 = r6.e     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0050
            if (r7 == 0) goto L_0x004a
            java.util.ArrayList<com.xiaomi.miot.store.common.RNAppStoreApiManager$OnInitialCompleteListener> r2 = r6.m     // Catch:{ all -> 0x007f }
            r2.add(r7)     // Catch:{ all -> 0x007f }
        L_0x004a:
            java.lang.String r7 = r6.d     // Catch:{ all -> 0x007f }
            r6.c(r7)     // Catch:{ all -> 0x007f }
            goto L_0x0061
        L_0x0050:
            if (r7 == 0) goto L_0x0061
            r7.onInitialSuccess()     // Catch:{ all -> 0x007f }
            goto L_0x0061
        L_0x0056:
            if (r7 == 0) goto L_0x005d
            java.util.ArrayList<com.xiaomi.miot.store.common.RNAppStoreApiManager$OnInitialCompleteListener> r2 = r6.m     // Catch:{ all -> 0x007f }
            r2.add(r7)     // Catch:{ all -> 0x007f }
        L_0x005d:
            r7 = 0
            r6.a((boolean) r7, (boolean) r7)     // Catch:{ all -> 0x007f }
        L_0x0061:
            java.lang.String r7 = "RNAppStoreApiManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            r2.<init>()     // Catch:{ all -> 0x007f }
            java.lang.String r3 = "initialReact time:"
            r2.append(r3)     // Catch:{ all -> 0x007f }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x007f }
            r5 = 0
            long r3 = r3 - r0
            r2.append(r3)     // Catch:{ all -> 0x007f }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x007f }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r7, (java.lang.String) r0)     // Catch:{ all -> 0x007f }
            monitor-exit(r6)
            return
        L_0x007f:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.common.RNAppStoreApiManager.b(com.xiaomi.miot.store.common.RNAppStoreApiManager$OnInitialCompleteListener):void");
    }

    /* access modifiers changed from: private */
    @UiThread
    public void a(boolean z, boolean z2) {
        this.h = z2;
        if (this.c.isRNDebug()) {
            c("");
        } else if (this.j != IntialStatus.EInitializing) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.j = IntialStatus.EInitializing;
            if (this.p != null) {
                this.p.a();
            }
            this.p = new MiotJSUpdateManager(z, z2);
            this.p.a((Context) e(), (Callback) new Callback() {
                public void a(Map<String, String> map) {
                    LogUtils.d(RNAppStoreApiManager.f1470a, "JSUpdateManager time:" + (System.currentTimeMillis() - currentTimeMillis));
                    final String str = map.get(Constants.f11398a);
                    RNAppStoreApiManager.this.g.post(new Runnable() {
                        public void run() {
                            RNAppStoreApiManager.this.j = IntialStatus.EInitialized;
                            if (RNAppStoreApiManager.this.c()) {
                                RNAppStoreApiManager.this.b(false);
                                return;
                            }
                            LogUtils.d(RNAppStoreApiManager.f1470a, "start init:" + str);
                            if (TextUtils.isEmpty(RNAppStoreApiManager.this.d) || RNAppStoreApiManager.this.e == null) {
                                RNAppStoreApiManager.this.c(str);
                                return;
                            }
                            LogUtils.d(RNAppStoreApiManager.f1470a, "notify js to reload.");
                            RNAppStoreApiManager.this.d = str;
                            RNAppStoreApiManager.this.s();
                        }
                    });
                }
            });
            LogUtils.d(f1470a, "initRN time:" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    @UiThread
    public void s() {
        LogUtils.d(f1470a, "recreateRN env.");
        try {
            if (this.c.isRNDebug()) {
                this.e.getDevSupportManager().handleReloadJS();
            } else {
                if (!TextUtils.isEmpty(this.d) && new File(this.d).exists()) {
                    this.e.recreateReactContextInBackground(this.d);
                }
                b(true);
            }
            LogUtils.d(f1470a, "recreateRN env success.");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        return;
     */
    @android.support.annotation.UiThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void t() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c()     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            com.facebook.react.ReactInstanceManager r0 = r1.e     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0017
            com.facebook.react.ReactInstanceManager r0 = r1.e     // Catch:{ all -> 0x0026 }
            r0.destroy()     // Catch:{ all -> 0x0026 }
            r0 = 0
            r1.e = r0     // Catch:{ all -> 0x0026 }
            r1.k = r0     // Catch:{ all -> 0x0026 }
        L_0x0017:
            boolean r0 = com.facebook.react.modules.fresco.FrescoModule.hasBeenInitialized()     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0024
            com.facebook.imagepipeline.core.ImagePipeline r0 = com.facebook.drawee.backends.pipeline.Fresco.getImagePipeline()     // Catch:{ all -> 0x0026 }
            r0.clearMemoryCaches()     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r1)
            return
        L_0x0026:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.common.RNAppStoreApiManager.t():void");
    }

    /* access modifiers changed from: private */
    @UiThread
    public synchronized void c(String str) {
        LogUtils.d(f1470a, "initialReactInstanceManager:" + str);
        UiThreadUtil.assertOnUiThread();
        this.d = str;
        if (TextUtils.isEmpty(str) && !this.c.isRNDebug()) {
            b(false);
        } else if (!new File(this.d).exists() && !this.c.isRNDebug()) {
            LogUtils.e(f1470a, "initialReactInstanceManager:" + str + " not exist");
            b(false);
        } else if (this.e != null) {
            s();
        } else if (AppInfo.a().getResources().getConfiguration().orientation != 2 || StoreApiManager.a().b().h()) {
            this.l = System.currentTimeMillis();
            this.k = new MiotStorePackage();
            ReactInstanceManagerBuilder useDeveloperSupport = ReactInstanceManager.builder().setApplication(AppInfo.a()).addPackage(new MainReactPackage()).addPackage(this.k).addPackage(new RNSoundPackage()).addPackage(new FastImageViewPackage()).addPackage(new CustomComponetPackage()).addPackage(new RNScreensPackage()).addPackage(new RNGestureHandlerPackage()).addPackage(new ReanimatedPackage()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).setJSBundleFile(this.c.isRNDebug() ? "" : this.d).setJSMainModulePath("index.android").setUseDeveloperSupport(this.c.isRNDebug());
            if (!this.c.isRNDebug()) {
                useDeveloperSupport.setNativeModuleCallExceptionHandler(new StoreNativeModuleCallExceptionHandler());
            }
            this.e = useDeveloperSupport.build();
            if (this.c.isRNDebug()) {
                this.d = this.e.getDevSupportManager().getJSBundleURLForRemoteDebugging();
            }
            this.e.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                public void onReactContextInitialized(final ReactContext reactContext) {
                    RNAppStoreApiManager.this.g.post(new Runnable() {
                        public void run() {
                            long currentTimeMillis = System.currentTimeMillis() - RNAppStoreApiManager.this.l;
                            LogUtils.d(RNAppStoreApiManager.f1470a, "initialReactInstanceManager time:" + currentTimeMillis);
                            final NetworkingModule networkingModule = (NetworkingModule) reactContext.getNativeModule(NetworkingModule.class);
                            networkingModule.setNetworkModuleProxy(new NetworkingModule.NetworkModuleProxy() {
                                public boolean doProxy(String str, String str2, OkHttpClient okHttpClient, Request request, RequestBody requestBody, DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, okhttp3.Callback callback) {
                                    String str3 = str2;
                                    if (!str2.contains("shopapi.io.mi.com")) {
                                        return false;
                                    }
                                    final Request request2 = request;
                                    final String str4 = str;
                                    final RequestBody requestBody2 = requestBody;
                                    final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter2 = rCTDeviceEventEmitter;
                                    final int i2 = i;
                                    final OkHttpClient okHttpClient2 = okHttpClient;
                                    final okhttp3.Callback callback2 = callback;
                                    OkHttpClient okHttpClient3 = okHttpClient;
                                    Request request3 = request;
                                    YouPinHttpsApi.a().a(okHttpClient, request, callback, (ISendRnRequest) new ISendRnRequest() {
                                        public void a() {
                                            LogUtils.d(RNAppStoreApiManager.f1470a, "401 换token后，重新请求:");
                                            okHttpClient2.newCall(networkingModule.makeRNRequest(request2, str4, requestBody2, rCTDeviceEventEmitter2, i2)).enqueue(callback2);
                                        }
                                    });
                                    return true;
                                }
                            });
                            RNAppStoreApiManager.this.c.onReactContextInitialed();
                            long j = currentTimeMillis / 1000;
                            if (j > 5 && RNAppStoreApiManager.this.i.b() != null) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("time", String.valueOf(j));
                                hashMap.put("bundleFile", RNAppStoreApiManager.this.d == null ? "" : RNAppStoreApiManager.this.d);
                                RNAppStoreApiManager.this.i.b().a(RNAppStoreApiManager.b, "ReactInitializedTime", (Map) hashMap);
                            }
                        }
                    });
                }
            });
            this.e.createReactContextInBackground();
            b(true);
        } else {
            b(false);
        }
    }

    /* access modifiers changed from: private */
    @UiThread
    public void b(boolean z) {
        LogUtils.d(f1470a, "onReactInited:" + z);
        if (!z && this.i.b() != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("time", String.valueOf((System.currentTimeMillis() - this.l) / 1000));
            hashMap.put("bundleFile", this.d == null ? "" : this.d);
            this.i.b().a(b, "ReactInitedFailed", (Map) hashMap);
        }
        if (this.q != null) {
            this.q.cancel(true);
            this.q = null;
        }
        this.q = new GetRnVersionTask();
        this.q.execute(new Void[0]);
        if (c()) {
            this.m.clear();
            return;
        }
        if (z && i() != null) {
            this.e.onHostResume(i(), (DefaultHardwareBackBtnHandler) i());
        }
        Iterator<OnInitialCompleteListener> it = this.m.iterator();
        while (it.hasNext()) {
            OnInitialCompleteListener next = it.next();
            if (next != null) {
                if (z) {
                    next.onInitialSuccess();
                } else {
                    next.onInitialFail();
                }
            }
        }
        this.m.clear();
    }

    private class StoreNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {
        private StoreNativeModuleCallExceptionHandler() {
        }

        public void handleException(final Exception exc) {
            RNAppStoreApiManager.this.g.post(new Runnable() {
                public void run() {
                    if (exc != null) {
                        if (exc != null) {
                            LogUtils.e(RNAppStoreApiManager.f1470a, "handleException", exc);
                        }
                        if (!(exc instanceof RuntimeException) || exc.getMessage() == null || !exc.getMessage().contains("RCTDeviceEventEmitter.emit")) {
                            int a2 = MiotJSUpdateManager.a((Context) RNAppStoreApiManager.this.e(), RNAppStoreApiManager.this.d);
                            LogUtils.d(RNAppStoreApiManager.f1470a, "handleException bundlePath:" + RNAppStoreApiManager.this.d + " bundleErrorCount:" + a2);
                            if (RNAppStoreApiManager.this.j != IntialStatus.EInitialized) {
                                return;
                            }
                            if (a2 < 5) {
                                MiotJSUpdateManager.a(RNAppStoreApiManager.this.e(), RNAppStoreApiManager.this.d, a2 + 1);
                                RNAppStoreApiManager.this.c.handleException(exc);
                                RNAppStoreApiManager.this.s();
                            } else if (!RNAppStoreApiManager.this.h) {
                                RNAppStoreApiManager.this.a(true, true);
                            } else if (RNAppStoreApiManager.this.i() != null) {
                                RNAppStoreApiManager.this.i().finish();
                            }
                        } else {
                            LogUtils.d(RNAppStoreApiManager.f1470a, "handleException return");
                        }
                    }
                }
            });
        }
    }

    private class GetRnVersionTask extends AsyncTask<Void, Void, Void> {
        private GetRnVersionTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            long currentTimeMillis = System.currentTimeMillis();
            Application e = RNAppStoreApiManager.this.e();
            if (e == null) {
                return null;
            }
            String a2 = Utils.a((Context) e, RNAppStoreApiManager.this.d);
            if (a2 != null && a2.length() > 0) {
                UserAgent.a(a2);
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            LogUtils.d(RNAppStoreApiManager.f1470a, "GetRnVersionTask time:" + (currentTimeMillis2 - currentTimeMillis) + " " + a2);
            return null;
        }
    }
}
