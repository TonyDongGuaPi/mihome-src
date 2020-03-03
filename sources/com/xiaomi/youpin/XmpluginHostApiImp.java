package com.xiaomi.youpin;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.coloros.mcssdk.PushManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.miui.tsmclient.entity.CardInfo;
import com.taobao.weex.common.WXModule;
import com.xiaomi.miot.store.alipay.AlipayProvider;
import com.xiaomi.miot.store.api.IMiotStoreApi;
import com.xiaomi.miot.store.api.IPayProvider;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.ucashier.MipayProvider;
import com.xiaomi.miot.store.wxpay.WxpayProvider;
import com.xiaomi.mishopsdk.SdkUtils;
import com.xiaomi.plugin.AccountInfo;
import com.xiaomi.plugin.AsyncCallback;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.Error;
import com.xiaomi.plugin.JsonParserUtils;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.ProgressCallback;
import com.xiaomi.plugin.RedpointManager;
import com.xiaomi.plugin.Request;
import com.xiaomi.plugin.SPM;
import com.xiaomi.plugin.WxTouristAccount;
import com.xiaomi.plugin.XmPluginBaseActivity;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.plugin.XmPluginPackage;
import com.xiaomi.plugin.account.MiServiceTokenInfo;
import com.xiaomi.plugin.location.ILocationListener;
import com.xiaomi.plugin.update.AppUpdateManager;
import com.xiaomi.pluginbase.XmPluginCommonApi;
import com.xiaomi.pluginhost.PluginHostActivity;
import com.xiaomi.pluginhost.PluginRuntimeManager;
import com.xiaomi.pluginhost.PluginSettings;
import com.xiaomi.qrcode2.QrCodeCallback;
import com.xiaomi.qrcode2.QrCodeRouter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.NotificationChannelCreator;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.location.AMapLocationManager;
import com.xiaomi.smarthome.framework.page.PictureShareActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.shop.CurrentPage;
import com.xiaomi.smarthome.shop.MiotAccountProvider;
import com.xiaomi.smarthome.shop.mishop.ProductIdMapDataManager;
import com.xiaomi.smarthome.shop.utils.DeviceShopConstants;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.callback.BasePassportCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiAccountInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.AccountUserApi;
import com.xiaomi.youpin.network.bean.DownloadFileInfo;
import com.xiaomi.youpin.network.bean.KeyValuePair;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.share.ShareRouter;
import com.xiaomi.youpin.youpin_common.SharedDataKey;
import com.xiaomi.youpin.youpin_common.SharedDataManager;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import com.xiaomi.youpin.youpin_common.statistic.params.RecordParams;
import com.xiaomi.youpin.youpin_common.statistic.params.TouchParams;
import com.xiaomi.youpin.youpin_common.statistic.params.VisibleParams;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import com.xiaomi.youpin.youpin_network.NetworkConfigManager;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import com.xiaomi.youpin.youpin_network.http.AsyncHandler;
import com.xiaomi.youpin.youpin_network.http.BinaryAsyncHandler;
import com.xiaomi.youpin.youpin_network.http.HttpApi;
import com.xiaomi.youpin.youpin_network.util.YouPinParamsUtil;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class XmpluginHostApiImp extends XmPluginHostApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23180a = "XmpluginHostApiImp";
    Map<String, IPayProvider> b = new HashMap();
    List<String> c = new ArrayList();
    RedpointManager d = new RedpointManagerImp();
    long e = 0;
    boolean f = true;
    boolean g = true;
    Map<String, Map<String, Object>> h = new HashMap();
    Map<String, List<WeakReference<XmPluginHostApi.SharedValueListener>>> i = new HashMap();
    SharedPreferences j;
    Map<String, Object> k = new HashMap();

    private String b(String str) {
        return str == null ? "" : str;
    }

    public String appId() {
        return "MiJia";
    }

    public void bindWx(AsyncCallback<Void, Error> asyncCallback) {
    }

    public AppUpdateManager getAppUpdateManager() {
        return null;
    }

    public String getEncryptedAccountId() {
        return "";
    }

    public void getPwdCaptchaImage(String str, AsyncCallback<Pair<Bitmap, String>, Error> asyncCallback) {
    }

    public void getWxTouristAccountInfo(Callback<WxTouristAccount> callback) {
    }

    public boolean isDevMode() {
        return false;
    }

    public void isWXBind(AsyncCallback<Boolean, Error> asyncCallback) {
    }

    public boolean isWxTouristLogin() {
        return false;
    }

    public void logout(int i2, Callback<Void> callback) {
    }

    public void logout(Callback<Void> callback) {
    }

    public void onActivityCreate(Activity activity) {
    }

    public void onActivityDestroy(Activity activity) {
    }

    public void openChangePasswordPage(Activity activity, int i2) {
        if (activity != null) {
        }
    }

    public void openH5UserHome() {
    }

    public void routeToPhoneBind(AsyncCallback<Void, Error> asyncCallback) {
    }

    public void sendNetLog(Context context) {
    }

    public void startScreenshotDetecting(Context context) {
    }

    public void stopScreenshotDetecting() {
    }

    public void unbindWX(AsyncCallback<Void, Error> asyncCallback) {
    }

    public void updatePortrait(String str, AsyncCallback<String, Error> asyncCallback) {
    }

    public void updateUserNickName(String str, AsyncCallback<Void, Error> asyncCallback) {
    }

    private XmpluginHostApiImp() {
        if (a()) {
            a((IPayProvider) new WxpayProvider());
        }
        a((IPayProvider) new AlipayProvider());
        a((IPayProvider) new MipayProvider(MiotAccountProvider.a(context())));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(context()).registerReceiver(new BroadcastReceiver() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0050  */
            /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onReceive(android.content.Context r3, android.content.Intent r4) {
                /*
                    r2 = this;
                    java.lang.String r3 = "XmpluginHostApiImp"
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "onReceive:"
                    r0.append(r1)
                    java.lang.String r1 = r4.getAction()
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r3, (java.lang.String) r0)
                    java.lang.String r3 = r4.getAction()
                    int r4 = r3.hashCode()
                    r0 = -1984077386(0xffffffff89bd61b6, float:-4.5591982E-33)
                    r1 = 1
                    if (r4 == r0) goto L_0x0038
                    r0 = -1470224095(0xffffffffa85e2921, float:-1.2332394E-14)
                    if (r4 == r0) goto L_0x002e
                    goto L_0x0042
                L_0x002e:
                    java.lang.String r4 = "action_on_logout"
                    boolean r3 = r3.equals(r4)
                    if (r3 == 0) goto L_0x0042
                    r3 = 1
                    goto L_0x0043
                L_0x0038:
                    java.lang.String r4 = "action_on_login_success"
                    boolean r3 = r3.equals(r4)
                    if (r3 == 0) goto L_0x0042
                    r3 = 0
                    goto L_0x0043
                L_0x0042:
                    r3 = -1
                L_0x0043:
                    switch(r3) {
                        case 0: goto L_0x0050;
                        case 1: goto L_0x0047;
                        default: goto L_0x0046;
                    }
                L_0x0046:
                    goto L_0x005b
                L_0x0047:
                    com.xiaomi.youpin.XmpluginHostApiImp r3 = com.xiaomi.youpin.XmpluginHostApiImp.this
                    r3.f = r1
                    com.xiaomi.youpin.XmpluginHostApiImp r3 = com.xiaomi.youpin.XmpluginHostApiImp.this
                    r3.g = r1
                    goto L_0x005b
                L_0x0050:
                    com.xiaomi.youpin.XmpluginHostApiImp r3 = com.xiaomi.youpin.XmpluginHostApiImp.this
                    r3.f = r1
                    com.xiaomi.youpin.XmpluginHostApiImp r3 = com.xiaomi.youpin.XmpluginHostApiImp.this
                    r3.g = r1
                    com.xiaomi.youpin.youpin_common.statistic.StatManager.e()
                L_0x005b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.XmpluginHostApiImp.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        }, intentFilter);
    }

    public static synchronized void a(Context context) {
        synchronized (XmpluginHostApiImp.class) {
            if (sXmPluginHostApi == null) {
                PluginSettings.f = context.getApplicationContext();
                sXmPluginHostApi = new XmpluginHostApiImp();
                PluginCommonHostApi.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        PackageInfo packageInfo;
        try {
            packageInfo = SHApplication.getAppContext().getPackageManager().getPackageInfo("com.tencent.mm", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /* access modifiers changed from: package-private */
    public void a(IPayProvider iPayProvider) {
        this.b.put(iPayProvider.name(), iPayProvider);
        this.c.add(iPayProvider.name());
    }

    private int a(String str) {
        if ("slide_in_left".equals(str)) {
            return R.anim.activity_slide_in_left;
        }
        if ("slide_in_right".equals(str)) {
            return R.anim.activity_slide_in_right;
        }
        if ("slide_in_top".equals(str)) {
            return R.anim.activity_slide_in_top;
        }
        if ("slide_in_bottom".equals(str)) {
            return R.anim.activity_slide_in_bottom;
        }
        if ("slide_out_left".equals(str)) {
            return R.anim.activity_slide_out_left;
        }
        if ("slide_out_right".equals(str)) {
            return R.anim.activity_slide_out_right;
        }
        if ("slide_out_top".equals(str)) {
            return R.anim.activity_slide_out_top;
        }
        if ("slide_out_bottom".equals(str)) {
            return R.anim.activity_slide_out_bottom;
        }
        if ("fade_in_left".equals(str)) {
            return R.anim.activity_fade_in_left;
        }
        if ("fade_in_right".equals(str)) {
            return R.anim.activity_fade_in_right;
        }
        if ("fade_out_left".equals(str)) {
            return R.anim.activity_fade_out_left;
        }
        if ("fade_out_right".equals(str)) {
            return R.anim.activity_fade_out_right;
        }
        return 0;
    }

    public void overridePendingTransition(Activity activity, String str, String str2) {
        if (activity != null) {
            activity.overridePendingTransition(a(str), a(str2));
        }
    }

    public void enableWhiteTranslucentStatus(Window window) {
        TitleBarUtil.b(window);
    }

    public boolean isTranslucentStatusbarEnable() {
        return TitleBarUtil.f11582a;
    }

    public void enableBlackTranslucentStatus(Window window) {
        TitleBarUtil.a(window);
    }

    public void setTitleBarPadding(View view) {
        TitleBarUtil.a(view);
    }

    public int getStatusHeight(Context context) {
        return TitleBarUtil.a(context);
    }

    public Intent getActivityIntent(XmPluginPackage xmPluginPackage, String str) {
        Intent intent = new Intent(context(), PluginRuntimeManager.a().b((Intent) null, xmPluginPackage.packagePath));
        intent.addCategory(PluginHostActivity.EXTRA_PACKAGE_PATH + xmPluginPackage.packagePath);
        intent.addCategory(PluginHostActivity.EXTRA_CLASS + str);
        return intent;
    }

    public String getChannel() {
        return GlobalSetting.v;
    }

    public Application application() {
        return SHApplication.getApplication();
    }

    public int getAppVersionCode() {
        return SystemApi.a().e(application());
    }

    public String getAppVersionName() {
        return SystemApi.a().f(application());
    }

    public Context context() {
        return SHApplication.getAppContext();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = com.xiaomi.smarthome.frame.core.CoreApi.a().s();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAccountId() {
        /*
            r1 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.q()
            if (r0 == 0) goto L_0x0017
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r0 = r0.s()
            if (r0 != 0) goto L_0x0016
            java.lang.String r0 = ""
        L_0x0016:
            return r0
        L_0x0017:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.XmpluginHostApiImp.getAccountId():java.lang.String");
    }

    public String getPassToken() {
        return CoreApi.a().q() ? CoreApi.a().w() : "";
    }

    public MiServiceTokenInfo getMiServiceToken(String str) {
        if (!CoreApi.a().q()) {
            return null;
        }
        com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo a2 = CoreApi.a().a(str);
        MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo();
        miServiceTokenInfo.domain = a2.f;
        miServiceTokenInfo.serviceToken = a2.c;
        miServiceTokenInfo.sid = a2.f23514a;
        miServiceTokenInfo.ssecurity = a2.d;
        miServiceTokenInfo.timeDiff = a2.e;
        return miServiceTokenInfo;
    }

    public boolean isAccountLogined() {
        return CoreApi.a().q();
    }

    public void openUrl(String str) {
        openUrl((Activity) null, str, -1);
    }

    public void openUrl(Activity activity, String str, int i2) {
        UrlDispatchManger.a().a(activity, str, i2);
    }

    public void openUrlFromMainTab(String str) {
        UrlDispatchManger.a().a(str);
    }

    public void startActivity(Context context, XmPluginPackage xmPluginPackage, Class<? extends XmPluginBaseActivity> cls, Intent intent) {
        int i2 = -1;
        if (intent != null) {
            i2 = intent.getIntExtra(WXModule.REQUEST_CODE, -1);
        }
        PluginHostActivity.startActivityForResult(context, xmPluginPackage, intent, cls, i2);
    }

    public void startActivityForResult(Context context, XmPluginPackage xmPluginPackage, Class<? extends XmPluginBaseActivity> cls, Intent intent, int i2) {
        PluginHostActivity.startActivityForResult(context, xmPluginPackage, intent, cls, i2);
    }

    public <T> void sendMijiaShopRequest(String str, JsonObject jsonObject, Callback<T> callback, Parser<T> parser, boolean z) {
        sendRawMijiaShopRequest("POST", str, jsonObject.toString(), callback, parser, z);
    }

    public void sendMijiaShopRequest(String str, List<Request> list, boolean z) {
        if (NetworkConfigManager.a().b() != null && str != null && !str.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Request next : list) {
                arrayList.add(RequestTypeTransferUtil.a(next.requestParams));
                arrayList2.add(RequestTypeTransferUtil.a(next));
            }
            YouPinHttpsApi.a().a(YouPinParamsUtil.a(1, str, (List<RequestParams>) arrayList), z, (List<PipeRequest>) arrayList2);
        }
    }

    public void addViewRecord(String str, String str2, String str3, int i2) {
        StatManager.a().a(str, str2, str3, i2);
    }

    public void addViewEndRecord() {
        StatManager.a().h();
    }

    public void addTouchRecord(String str, String str2, String str3) {
        StatManager.a().a(new TouchParams.Builder().a(str).b(str2).c(str3).a());
    }

    public void addPaySuccessRecord(String str, String str2, String str3) {
        StatManager.a().c(str, str2, str3);
    }

    public void addPayFailedRecord(String str, String str2, String str3) {
        StatManager.a().e(str, str2, str3);
    }

    public void addRecord(String str, String str2, String str3, Object obj, Map<String, Object> map, boolean z) {
        StatManager.a().a(new RecordParams.Builder().a(str).b(str2).a(map).a(obj).e(z ? StatManager.d : "A").a());
    }

    public void updateProductIdMap() {
        ProductIdMapDataManager.a().b();
    }

    public boolean hasSwitch(String str) {
        return SharedDataManager.a().a(str);
    }

    public void addVisibleRecord(String str, String str2, String str3, String str4) {
        StatManager.a().a(new VisibleParams.Builder().a(str).b(str2).c(str3).d(str4).e("A").a());
    }

    public String registerAppKey() {
        return StoreApiManager.a().b().d();
    }

    public void openUrl(String str, SPM spm) {
        if (spm != null) {
            if (str.contains("?")) {
                str = str + "&spmref=" + spm.toString();
            } else {
                str = str + "?spmref=" + spm.toString();
            }
        }
        openUrl(str);
    }

    public void showNotification(String str, String str2, int i2, PendingIntent pendingIntent) {
        Context appContext = SHApplication.getAppContext();
        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.notify(i2, new Notification.Builder(appContext, NotificationChannelCreator.c(notificationManager)).setTicker(str).setContentTitle(str).setContentText(str2).setAutoCancel(true).setSmallIcon(R.drawable.notify_icon).setContentIntent(pendingIntent).build());
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext);
            builder.setTicker(str);
            builder.setContentTitle(str);
            builder.setContentText(str2);
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.notify_icon);
            builder.setContentIntent(pendingIntent);
            notificationManager.notify(i2, builder.build());
        }
    }

    public void addTouchRecord1(String str, String str2, String str3) {
        StatManager.a().a(new TouchParams.Builder().a(str).b(str2).e(str3).a());
    }

    public void addTouchRecord1(String str, String str2, String str3, String str4) {
        StatManager.a().a(new TouchParams.Builder().a(str).b(str2).c(str3).e(str4).a());
    }

    public void sendYouPinNewRequest(String str, String str2, String str3, boolean z, boolean z2, final AsyncCallback<String, Error> asyncCallback) {
        int i2;
        if (NetworkConfigManager.a().b() != null) {
            if ("GET".equals(str)) {
                i2 = 2;
            } else {
                boolean equals = "POST".equals(str);
                i2 = 1;
            }
            YouPinHttpsApi.a().a((RequestParams) null, new NetRequest.Builder().a(i2).b(1).b(str3).b(z2).a(str2).a(), z, new YouPinJsonParser<String>() {
                /* renamed from: a */
                public String b(JsonElement jsonElement) {
                    return jsonElement.toString();
                }
            }, new RequestAsyncCallback<String, NetError>() {
                public void a(String str, boolean z) {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(str);
                    }
                }

                public void a(NetError netError) {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(netError.a(), netError.b()));
                    }
                }
            });
        }
    }

    public void setBadge(String str, String str2, int i2) {
        this.d.setBadge(str, str2, i2);
    }

    public int getBadgeCount(String str, String str2) {
        return this.d.getBadgeCount(str, str2);
    }

    public int getBadgeCount(String str) {
        return this.d.getBadgeCount(str);
    }

    public int getBadgeCount() {
        return this.d.getBadgeCount();
    }

    public void addBadgeUpdateListener(RedpointManager.BadgeUpdateListener badgeUpdateListener) {
        this.d.addBadgeUpdateListener(badgeUpdateListener);
    }

    public void removeBadgeUpdateListener(RedpointManager.BadgeUpdateListener badgeUpdateListener) {
        this.d.removeBadgeUpdateListener(badgeUpdateListener);
    }

    public void clearBadge() {
        this.d.clearBadge();
    }

    public void getAccountInfo(boolean z, Callback<AccountInfo> callback) {
        if (isAccountLogined()) {
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
            if (a2 != null && !TextUtils.isEmpty(a2.c)) {
                b(callback);
            } else if (CoreApi.a().v()) {
                MiLoginApi.a("passportapi", a2, a(callback));
            } else {
                MiLoginApi.a("passportapi", CoreApi.a().s(), CoreApi.a().w(), a(callback));
            }
        } else if (callback != null) {
            callback.onFailure(-1, "not login");
        }
    }

    public void uploadStatic(boolean z) {
        StatManager.a().a(z);
    }

    public boolean isReactNatvieDebug() {
        return DeviceShopConstants.a();
    }

    public void setReactNativeDebug(boolean z) {
        DeviceShopConstants.a(z);
    }

    public void getAccountInfo(Callback<AccountInfo> callback) {
        getAccountInfo(true, callback);
    }

    private com.xiaomi.youpin.login.AsyncCallback<com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo, ExceptionError> a(final Callback<AccountInfo> callback) {
        return new com.xiaomi.youpin.login.AsyncCallback<com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo, ExceptionError>() {
            public void a(com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo miServiceTokenInfo) {
                if (!CoreApi.a().q()) {
                    callback.onFailure(-1, "Has logout!");
                    return;
                }
                CoreApi.a().a(new com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo("passportapi", miServiceTokenInfo.b, miServiceTokenInfo.c, miServiceTokenInfo.d, "account.xiaomi.com", miServiceTokenInfo.e), (com.xiaomi.smarthome.frame.AsyncCallback<Void, com.xiaomi.smarthome.frame.Error>) null);
                XmpluginHostApiImp.this.b((Callback<AccountInfo>) callback);
            }

            public void a(ExceptionError exceptionError) {
                if (callback != null) {
                    callback.onFailure(-1001, "need logout");
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void b(final Callback<AccountInfo> callback) {
        LoginMiAccount y = CoreApi.a().y();
        if (y != null) {
            LoginMiAccount loginMiAccount = new LoginMiAccount();
            loginMiAccount.a(y.a(), y.c(), y.b());
            loginMiAccount.a(y.d());
            AccountUserApi.a(loginMiAccount, new BasePassportCallback<MiAccountInfo>() {
                public void a(MiAccountInfo miAccountInfo) {
                    if (!CoreApi.a().q()) {
                        callback.onFailure(-1, "Has logout!");
                    } else if (miAccountInfo == null) {
                        callback.onFailure(-1, "userInfo is null!");
                    } else if (!CoreApi.a().s().equals(miAccountInfo.f23513a)) {
                        callback.onFailure(-1, "userId not consistent!");
                    } else {
                        AccountInfo accountInfo = new AccountInfo();
                        accountInfo.mUserId = CoreApi.a().s();
                        accountInfo.mUserName = miAccountInfo.b;
                        accountInfo.mAvatarAddress = miAccountInfo.c;
                        accountInfo.mPhone = miAccountInfo.d;
                        accountInfo.mPhoneList = miAccountInfo.e;
                        accountInfo.mEmail = miAccountInfo.f;
                        accountInfo.mNickName = miAccountInfo.g;
                        accountInfo.mAvatar = miAccountInfo.h;
                        MiAccountInfo.Gender gender = miAccountInfo.i;
                        if (gender != null) {
                            if (gender == MiAccountInfo.Gender.MALE) {
                                accountInfo.mGender = AccountInfo.Gender.MALE;
                            } else if (gender == MiAccountInfo.Gender.FEMALE) {
                                accountInfo.mGender = AccountInfo.Gender.FEMALE;
                            }
                        }
                        accountInfo.mBirthday = miAccountInfo.j;
                        if (callback != null) {
                            callback.onSuccess(accountInfo, true);
                        }
                    }
                }

                public void a(int i, String str) {
                    if (callback != null) {
                        callback.onFailure(i, str);
                    }
                }

                public void a(com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo miServiceTokenInfo) {
                    if (CoreApi.a().q()) {
                        CoreApi.a().a(miServiceTokenInfo, (com.xiaomi.smarthome.frame.AsyncCallback<Void, com.xiaomi.smarthome.frame.Error>) null);
                    }
                }
            });
        } else if (callback != null) {
            callback.onFailure(-1, "userInfo is null!");
        }
    }

    public void login(Context context) {
        if (!isAccountLogined()) {
            LoginApi.a().a(context, 1, (LoginApi.LoginCallback) null);
        }
    }

    public void login(Context context, String str) {
        login(context);
    }

    public List<String> supportPayList() {
        return this.c;
    }

    public IPayProvider getPay(String str) {
        return this.b.get(str);
    }

    public int getPageModel() {
        return AppStoreConstants.d();
    }

    public void setPageModel(int i2) {
        AppStoreConstants.a(i2);
    }

    public void downloadFile(String str, String str2, final ProgressCallback<Void> progressCallback) {
        if (NetworkConfigManager.a().b() != null) {
            DownloadFileInfo downloadFileInfo = new DownloadFileInfo();
            downloadFileInfo.b(str);
            downloadFileInfo.a(str2);
            YouPinHttpsApi.a().a(new NetRequest.Builder().a(str).a(), downloadFileInfo, (RequestAsyncCallback<String, NetError>) new RequestAsyncCallback<String, NetError>() {
                public void a(String str, boolean z) {
                    if (progressCallback != null) {
                        progressCallback.onSuccess(null, true);
                    }
                }

                public void a(NetError netError) {
                    if (progressCallback != null) {
                        progressCallback.onFailure(netError.a(), netError.b());
                    }
                }

                public void a(long j, long j2) {
                    if (progressCallback != null) {
                        progressCallback.onProgress(j, j2);
                    }
                }
            });
        }
    }

    public void httpRequest(String str, String str2, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, final Callback<byte[]> callback) {
        if (NetworkConfigManager.a().b() != null) {
            int i2 = 1;
            if ("GET".equals(str2)) {
                i2 = 2;
            } else {
                boolean equals = "POST".equals(str2);
            }
            HttpApi.a(new NetRequest.Builder().a(i2).a(str).a((Map<String, String>) hashMap).b((Map<String, String>) hashMap2).a(), (AsyncHandler) new BinaryAsyncHandler() {
                public void a(byte[] bArr, Response response) {
                    if (callback != null) {
                        callback.onSuccess(bArr, true);
                    }
                }

                public void a(NetError netError, Exception exc, Response response) {
                    if (callback != null) {
                        callback.onFailure(netError.a(), netError.b());
                    }
                }
            });
        }
    }

    public void setTextViewFont(int i2, TextView textView) {
        XmPluginCommonApi.instance().setTextViewFont(i2, textView);
    }

    public void share(Context context, String str) {
        ShareRouter.a(context, str, true);
    }

    public RedpointManager getRedpointManager() {
        return this.d;
    }

    public <T> void sendRawMijiaShopRequest(String str, String str2, String str3, Callback<T> callback, Parser<T> parser, boolean z) {
        int i2;
        if (NetworkConfigManager.a().b() != null) {
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != 70454) {
                if (hashCode == 2461856 && str.equals("POST")) {
                    c2 = 0;
                }
            } else if (str.equals("GET")) {
                c2 = 1;
            }
            switch (c2) {
                case 0:
                    i2 = 1;
                    break;
                case 1:
                    i2 = 2;
                    break;
                default:
                    LogUtils.e(f23180a, "unsupport method!");
                    return;
            }
            ArrayList arrayList = new ArrayList();
            if (str3 != null) {
                arrayList.add(new KeyValuePair("data", str3));
            }
            NetRequest.Builder a2 = new NetRequest.Builder().b(1).a(str2).a(i2).b((List<KeyValuePair>) arrayList).a(CoreApi.a().q());
            if (YouPinParamsUtil.b.equals(str2)) {
                String e2 = NetworkConfigManager.a().b().a().e();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new KeyValuePair("userid", e2));
                a2.a((List<KeyValuePair>) arrayList2);
            }
            YouPinHttpsApi.a().a((RequestParams) null, a2.a(), z, RequestTypeTransferUtil.a(parser), RequestTypeTransferUtil.a(callback));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void sendRawMijiaShopRequest(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.io.File r9, com.xiaomi.plugin.Callback<T> r10, com.xiaomi.plugin.Parser<T> r11, boolean r12) {
        /*
            r4 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            r12 = -1
            int r0 = r5.hashCode()
            r1 = 70454(0x11336, float:9.8727E-41)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001f
            r1 = 2461856(0x2590a0, float:3.449795E-39)
            if (r0 == r1) goto L_0x0015
            goto L_0x0028
        L_0x0015:
            java.lang.String r0 = "POST"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0028
            r12 = 0
            goto L_0x0028
        L_0x001f:
            java.lang.String r0 = "GET"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0028
            r12 = 1
        L_0x0028:
            switch(r12) {
                case 0: goto L_0x0035;
                case 1: goto L_0x0033;
                default: goto L_0x002b;
            }
        L_0x002b:
            java.lang.String r5 = "XmpluginHostApiImp"
            java.lang.String r6 = "unsupport method!"
            com.xiaomi.youpin.log.LogUtils.e((java.lang.String) r5, (java.lang.String) r6)
            return
        L_0x0033:
            r5 = 2
            goto L_0x0036
        L_0x0035:
            r5 = 1
        L_0x0036:
            com.xiaomi.youpin.network.bean.UploadFileInfo r12 = new com.xiaomi.youpin.network.bean.UploadFileInfo
            r12.<init>()
            r12.a((java.lang.String) r6)
            r12.d(r8)
            java.lang.String r8 = r9.getAbsolutePath()
            r12.b(r8)
            java.util.List r8 = com.xiaomi.youpin.RequestTypeTransferUtil.a((java.lang.String) r7)
            r9 = 0
            int r0 = r8.size()
            if (r0 != 0) goto L_0x005b
            java.lang.String r8 = "XmpluginHostApiImp"
            java.lang.String r0 = "request params is empty or invalid"
            com.xiaomi.youpin.log.LogUtils.w((java.lang.String) r8, (java.lang.String) r0)
            goto L_0x0068
        L_0x005b:
            int r0 = r8.size()
            if (r0 <= r3) goto L_0x006a
            java.lang.String r8 = "XmpluginHostApiImp"
            java.lang.String r0 = "do not use this method to request multi requests!"
            com.xiaomi.youpin.log.LogUtils.w((java.lang.String) r8, (java.lang.String) r0)
        L_0x0068:
            r8 = r9
            goto L_0x0070
        L_0x006a:
            java.lang.Object r8 = r8.get(r2)
            com.xiaomi.youpin.youpin_network.bean.RequestParams r8 = (com.xiaomi.youpin.youpin_network.bean.RequestParams) r8
        L_0x0070:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            if (r7 == 0) goto L_0x0081
            com.xiaomi.youpin.network.bean.KeyValuePair r0 = new com.xiaomi.youpin.network.bean.KeyValuePair
            java.lang.String r1 = "data"
            r0.<init>(r1, r7)
            r9.add(r0)
        L_0x0081:
            com.xiaomi.youpin.network.bean.NetRequest$Builder r7 = new com.xiaomi.youpin.network.bean.NetRequest$Builder
            r7.<init>()
            com.xiaomi.youpin.network.bean.NetRequest$Builder r7 = r7.b((int) r3)
            com.xiaomi.youpin.network.bean.NetRequest$Builder r7 = r7.a((java.lang.String) r6)
            com.xiaomi.youpin.network.bean.NetRequest$Builder r5 = r7.a((int) r5)
            com.xiaomi.youpin.network.bean.NetRequest$Builder r5 = r5.b((java.util.List<com.xiaomi.youpin.network.bean.KeyValuePair>) r9)
            com.xiaomi.smarthome.frame.core.CoreApi r7 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r7 = r7.q()
            com.xiaomi.youpin.network.bean.NetRequest$Builder r5 = r5.a((boolean) r7)
            java.lang.String r7 = "/shop/pipe"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x00cc
            com.xiaomi.youpin.youpin_network.NetworkConfigManager r6 = com.xiaomi.youpin.youpin_network.NetworkConfigManager.a()
            com.xiaomi.youpin.youpin_network.NetworkConfig r6 = r6.b()
            com.xiaomi.youpin.youpin_network.NetWorkDependency r6 = r6.a()
            java.lang.String r6 = r6.e()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            com.xiaomi.youpin.network.bean.KeyValuePair r9 = new com.xiaomi.youpin.network.bean.KeyValuePair
            java.lang.String r0 = "userid"
            r9.<init>(r0, r6)
            r7.add(r9)
            r5.a((java.util.List<com.xiaomi.youpin.network.bean.KeyValuePair>) r7)
        L_0x00cc:
            com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser r11 = com.xiaomi.youpin.RequestTypeTransferUtil.a(r11)
            com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback r0 = com.xiaomi.youpin.RequestTypeTransferUtil.a(r10)
            com.xiaomi.youpin.youpin_network.YouPinHttpsApiInterface r6 = com.xiaomi.youpin.youpin_network.YouPinHttpsApi.a()
            com.xiaomi.youpin.network.bean.NetRequest r5 = r5.a()
            r7 = r8
            r8 = r5
            r9 = r12
            r10 = r11
            r11 = r0
            r6.a((com.xiaomi.youpin.youpin_network.bean.RequestParams) r7, (com.xiaomi.youpin.network.bean.NetRequest) r8, (com.xiaomi.youpin.network.bean.UploadFileInfo) r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.XmpluginHostApiImp.sendRawMijiaShopRequest(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File, com.xiaomi.plugin.Callback, com.xiaomi.plugin.Parser, boolean):void");
    }

    public void syncServerTime() {
        httpRequest("https://tp.hd.mi.com/gettimestamp", "GET", (HashMap<String, String>) null, (HashMap<String, String>) null, new Callback<byte[]>() {
            /* renamed from: a */
            public void onCache(byte[] bArr) {
            }

            public void onFailure(int i, String str) {
            }

            /* renamed from: a */
            public void onSuccess(byte[] bArr, boolean z) {
                String str = new String(bArr);
                int indexOf = str.indexOf(61);
                if (indexOf > 0) {
                    try {
                        XmpluginHostApiImp.this.e = SystemClock.elapsedRealtime() - (Long.valueOf(str.substring(indexOf + 1)).longValue() * 1000);
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    public long getServerTime() {
        return SystemClock.elapsedRealtime() - this.e;
    }

    public void updateSetPasswordInfo(final Callback<Boolean> callback) {
        if (!isAccountLogined()) {
            this.f = true;
            if (callback != null) {
                callback.onSuccess(Boolean.valueOf(this.f), true);
            }
        } else if (this.g) {
            XmPluginHostApi.instance().sendMijiaShopRequest2("User", "GetUserStatus", new Callback<JsonObject>() {
                /* renamed from: a */
                public void onCache(JsonObject jsonObject) {
                }

                /* renamed from: a */
                public void onSuccess(JsonObject jsonObject, boolean z) {
                    LogUtils.d(XmpluginHostApiImp.f23180a, "updateSetPasswordInfo:" + jsonObject.toString());
                    XmpluginHostApiImp.this.f = JsonParserUtils.getBoolean(jsonObject, new String[]{"data", "nopass"}) ^ true;
                    XmpluginHostApiImp.this.g = false;
                    if (callback != null) {
                        callback.onSuccess(Boolean.valueOf(XmpluginHostApiImp.this.f), true);
                    }
                }

                public void onFailure(int i, String str) {
                    LogUtils.d(XmpluginHostApiImp.f23180a, "updateSetPasswordInfo:onFailure" + str);
                    if (callback != null) {
                        callback.onFailure(i, str);
                    }
                }
            }, new Parser<JsonObject>() {
                /* renamed from: a */
                public JsonObject parse(JsonElement jsonElement) {
                    return (JsonObject) jsonElement;
                }
            }, false);
        } else if (callback != null) {
            callback.onSuccess(Boolean.valueOf(this.f), true);
        }
    }

    public boolean isSetPassword() {
        if (!isAccountLogined()) {
            this.f = true;
        }
        return this.f;
    }

    public boolean isTranslucentStatusEnabled() {
        return TitleBarUtil.f11582a;
    }

    public boolean sendJsEvent(String str, Map<String, Object> map) {
        IMiotStoreApi a2 = MiotStoreApi.a();
        return a2 != null && a2.sendJsEvent(str, map);
    }

    public void scanBanner(String str, final Callback<String> callback) {
        QrCodeRouter.a(XmPluginHostApi.instance().application(), str, new QrCodeCallback() {
            public void onSuccess(String str) {
                callback.onSuccess(str, true);
            }

            public void onFail(int i, String str) {
                callback.onFailure(-1, str);
            }
        });
    }

    public synchronized void setSharedValue(String str, String str2, Object obj) {
        Map map = this.h.get(str);
        if (map == null) {
            map = new HashMap();
            this.h.put(str, map);
        }
        map.put(str2, obj);
        List list = this.i.get(str);
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                XmPluginHostApi.SharedValueListener sharedValueListener = (XmPluginHostApi.SharedValueListener) ((WeakReference) list.get(i2)).get();
                if (sharedValueListener != null) {
                    sharedValueListener.onValueChanged(str, str2, obj);
                }
            }
        }
    }

    public synchronized Object getSharedValue(String str, String str2) {
        if ("UserAgentShort".equals(str)) {
            return MiotStoreApi.a().getUserAgent();
        }
        Map map = this.h.get(str);
        if (map == null) {
            return null;
        }
        return map.get(str2);
    }

    public synchronized void removeValue(String str, String str2) {
        if (str != null) {
            if (str2 == null) {
                try {
                    this.h.remove(str);
                } catch (Throwable th) {
                    throw th;
                }
            }
            Map map = this.h.get(str);
            if (map != null) {
                map.remove(str2);
            }
        }
    }

    public synchronized void registerSharedValueChangeListener(String str, XmPluginHostApi.SharedValueListener sharedValueListener) {
        if (sharedValueListener != null) {
            List list = this.i.get(str);
            if (list == null) {
                list = new ArrayList();
                this.i.put(str, list);
            }
            list.add(new WeakReference(sharedValueListener));
        }
    }

    public synchronized void unregisterSharedValueChangeListener(String str, XmPluginHostApi.SharedValueListener sharedValueListener) {
        List list = this.i.get(str);
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                XmPluginHostApi.SharedValueListener sharedValueListener2 = (XmPluginHostApi.SharedValueListener) ((WeakReference) list.get(size)).get();
                if (sharedValueListener2 == null || sharedValueListener2 == sharedValueListener) {
                    list.remove(size);
                }
            }
        }
    }

    public void addVisibleRecord(String str, String str2, String str3) {
        StatManager.a().a(str, str2, str3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b2, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setPreferenceValue(java.lang.String r4, java.lang.Object r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 == 0) goto L_0x00b1
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x000b
            goto L_0x00b1
        L_0x000b:
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            if (r0 != 0) goto L_0x001c
            android.content.Context r0 = r3.context()     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = "shared_pref"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x00ae }
            r3.j = r0     // Catch:{ all -> 0x00ae }
        L_0x001c:
            boolean r0 = r5 instanceof java.lang.String     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x0032
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00ae }
            r1 = r5
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.putString(r4, r1)     // Catch:{ all -> 0x00ae }
            r0.apply()     // Catch:{ all -> 0x00ae }
            goto L_0x00a7
        L_0x0032:
            boolean r0 = r5 instanceof java.lang.Integer     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x004b
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00ae }
            r1 = r5
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x00ae }
            int r1 = r1.intValue()     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.putInt(r4, r1)     // Catch:{ all -> 0x00ae }
            r0.apply()     // Catch:{ all -> 0x00ae }
            goto L_0x00a7
        L_0x004b:
            boolean r0 = r5 instanceof java.lang.Boolean     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x0064
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00ae }
            r1 = r5
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x00ae }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.putBoolean(r4, r1)     // Catch:{ all -> 0x00ae }
            r0.apply()     // Catch:{ all -> 0x00ae }
            goto L_0x00a7
        L_0x0064:
            boolean r0 = r5 instanceof java.lang.Long     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x007d
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00ae }
            r1 = r5
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x00ae }
            long r1 = r1.longValue()     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.putLong(r4, r1)     // Catch:{ all -> 0x00ae }
            r0.apply()     // Catch:{ all -> 0x00ae }
            goto L_0x00a7
        L_0x007d:
            boolean r0 = r5 instanceof java.lang.Float     // Catch:{ all -> 0x00ae }
            if (r0 == 0) goto L_0x0096
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00ae }
            r1 = r5
            java.lang.Float r1 = (java.lang.Float) r1     // Catch:{ all -> 0x00ae }
            float r1 = r1.floatValue()     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.putFloat(r4, r1)     // Catch:{ all -> 0x00ae }
            r0.apply()     // Catch:{ all -> 0x00ae }
            goto L_0x00a7
        L_0x0096:
            android.content.SharedPreferences r0 = r3.j     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x00ae }
            android.content.SharedPreferences$Editor r0 = r0.putString(r4, r1)     // Catch:{ all -> 0x00ae }
            r0.apply()     // Catch:{ all -> 0x00ae }
        L_0x00a7:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r3.k     // Catch:{ all -> 0x00ae }
            r0.put(r4, r5)     // Catch:{ all -> 0x00ae }
            monitor-exit(r3)
            return
        L_0x00ae:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x00b1:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.XmpluginHostApiImp.setPreferenceValue(java.lang.String, java.lang.Object):void");
    }

    public synchronized Object getPreferenceValue(String str, Object obj) {
        Object obj2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.j == null) {
            this.j = context().getSharedPreferences("shared_pref", 0);
        }
        Object obj3 = this.k.get(str);
        if (obj3 != null) {
            return obj3;
        }
        if (obj != null) {
            if (!(obj instanceof String)) {
                if (obj instanceof Integer) {
                    obj2 = Integer.valueOf(this.j.getInt(str, ((Integer) obj).intValue()));
                } else if (obj instanceof Boolean) {
                    obj2 = Boolean.valueOf(this.j.getBoolean(str, ((Boolean) obj).booleanValue()));
                } else if (obj instanceof Long) {
                    obj2 = Long.valueOf(this.j.getLong(str, ((Long) obj).longValue()));
                } else if (obj instanceof Float) {
                    obj2 = Float.valueOf(this.j.getFloat(str, ((Float) obj).floatValue()));
                } else {
                    obj2 = this.j.getString(str, obj.toString());
                }
                this.k.put(str, obj2);
                return obj2;
            }
        }
        obj2 = this.j.getString(str, (String) obj);
        this.k.put(str, obj2);
        return obj2;
    }

    public Fragment getRNFragmentByUrl(Activity activity, String str) {
        return UrlDispatchManger.a().a((Context) activity, str, true);
    }

    public Fragment getFragmentByUrl(Activity activity, String str, boolean z) {
        return UrlDispatchManger.a().a((Context) activity, str, z);
    }

    public int startChat(Context context, Map<String, String> map) {
        boolean z;
        if (!XmPluginHostApi.instance().isAccountLogined()) {
            XmPluginHostApi.instance().login(context);
            return -1;
        }
        if (!(context == null || map == null)) {
            String str = map.get("type");
            if (str == null || !str.equals(UserMode.f23179a)) {
                String str2 = map.get(UrlConstants.customerService);
                String str3 = map.get("number");
                if (!TextUtils.isEmpty(b(str3))) {
                    HashMap hashMap = new HashMap();
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "400-100-5678";
                    }
                    hashMap.put("number", Base64.encodeToString(str3.getBytes(), 0));
                    XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams("call", hashMap));
                    PluginHostActivity topPluginHostActivity = PluginHostActivity.getTopPluginHostActivity();
                    if (topPluginHostActivity != null) {
                        topPluginHostActivity.overridePendingTransition(R.anim.dialog_page_fade_in, R.anim.dialog_page_fade_out);
                    }
                } else if (!TextUtils.isEmpty(str2)) {
                    boolean hasSwitch = hasSwitch(SharedDataKey.d);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        String string = jSONObject.getString(CardInfo.KEY_CARD_GROUP_ID);
                        String string2 = jSONObject.getString("queueId");
                        if ("0".equals(string) && TextUtils.isEmpty(string2)) {
                            z = true;
                            if (hasSwitch || !z) {
                                MiCSHelper.a().a(context, str2);
                            } else {
                                b(context);
                            }
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    z = false;
                    if (hasSwitch) {
                    }
                    MiCSHelper.a().a(context, str2);
                }
            } else {
                b(context);
                return 0;
            }
        }
        return 0;
    }

    private void b(Context context) {
        SdkUtils.startNewPluginActivity((context == null || !(context instanceof Activity)) ? PluginHostActivity.getTopPluginHostActivity() : (Activity) context, Uri.parse("mishopsdk://com.xiaomi.chat.fragment.RootFragment?pid=117"));
        HashMap hashMap = new HashMap();
        hashMap.put("init", "true");
        XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.csPushHeader, hashMap));
    }

    public void setSharedValue(String str, String str2, final Callback<Void> callback) {
        SharedDataManager.a().a(str, str2, new IStoreCallback<Void>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (callback != null) {
                    callback.onSuccess(null, false);
                }
            }

            public void onFailed(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    public void getSharedValue(String str, final Callback<String> callback) {
        SharedDataManager.a().a(str, new IStoreCallback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (callback != null) {
                    callback.onSuccess(str, false);
                }
            }

            public void onFailed(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    public void addRecord(String str, String str2, String str3, Object obj, Map<String, Object> map, String str4) {
        StatManager.a().a(new RecordParams.Builder().a(str).b(str2).a(map).a(obj).e(str4).a());
    }

    public void openWeexDevTool(String str) {
        WXAppStoreApiManager.b().a(str);
    }

    public void sharePicOrVideo(Context context, String str, String str2, String str3) {
        PictureShareActivity.share(context, str, str2, str3);
    }

    public void getLocationInfo(final ILocationListener iLocationListener) {
        AMapLocationManager.a().a((AMapLocationManager.LocationCallback) new AMapLocationManager.LocationCallback() {
            public void a() {
            }

            public void a(AMapLocation aMapLocation) {
                if (aMapLocation != null && iLocationListener != null) {
                    iLocationListener.onLocation(aMapLocation.toStr());
                }
            }
        });
    }

    public void stopLocation() {
        AMapLocationManager.a().b();
    }

    public String getCurrentPageUrl() {
        return CurrentPage.b();
    }

    public String statGetSpmref(String str) {
        return StatManager.a().b(str);
    }

    public String getCurrentPage() {
        return CurrentPage.c();
    }

    public boolean isCurrentInShop() {
        return CurrentPage.d();
    }

    public boolean isYoupinHost(String str) {
        return UrlDispatchManger.a().d(str);
    }

    public String getUserAgent() {
        return UserAgent.d();
    }

    public void addVisibleRecord(String str, String str2, String str3, String str4, String str5) {
        StatManager.a().a(new VisibleParams.Builder().a(str).b(str2).c(str3).d(str4).f(str5).a());
    }

    public void addTouchRecord2(String str, String str2, String str3, String str4) {
        StatManager.a().a(new TouchParams.Builder().a(str).b(str2).e(str3).f(str4).a());
    }

    public void addTouchRecord2(String str, String str2, String str3, String str4, String str5) {
        StatManager.a().a(new TouchParams.Builder().a(str).b(str2).c(str3).e(str4).f(str5).a());
    }

    public String getIMEI() {
        return AppIdManager.a().b();
    }

    public String createSpm(String str, String str2, String str3) {
        return XmPluginHostApi.instance().registerAppKey() + "_A" + "." + str + "." + str2 + "." + str3;
    }

    public void sendRequestWithHeader(String str, String str2, HashMap<String, String> hashMap, String str3, boolean z, boolean z2, final AsyncCallback<String, Error> asyncCallback) {
        int i2;
        if ("GET".equals(str)) {
            i2 = 2;
        } else {
            boolean equals = "POST".equals(str);
            i2 = 1;
        }
        YouPinHttpsApi.a().a((RequestParams) null, new NetRequest.Builder().a(i2).b(1).b(str3).b(z2).a(str2).a((Map<String, String>) hashMap).a(), z, new YouPinJsonParser<String>() {
            /* renamed from: a */
            public String b(JsonElement jsonElement) {
                return jsonElement.toString();
            }
        }, new RequestAsyncCallback<String, NetError>() {
            public void a(String str, boolean z) {
                if (asyncCallback != null) {
                    asyncCallback.sendSuccessMessage(str);
                }
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(netError.a(), netError.b()));
                }
            }
        });
    }
}
