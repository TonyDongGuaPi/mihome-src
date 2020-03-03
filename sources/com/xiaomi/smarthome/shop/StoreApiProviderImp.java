package com.xiaomi.smarthome.shop;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.miot.store.alipay.AlipayProvider;
import com.xiaomi.miot.store.api.IPayProvider;
import com.xiaomi.miot.store.ucashier.MipayProvider;
import com.xiaomi.miot.store.ucashier.UCashierProvider;
import com.xiaomi.miot.store.wxpay.WxpayProvider;
import com.xiaomi.mishopsdk.io.http.RequestConstants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.youpin.UserMode;
import com.xiaomi.youpin.app_sdk.common.AbsStoreApiProviderImpl;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.unionpay.IUnionpayCallback;
import com.xiaomi.youpin.unionpay.UnionpayProvider;
import com.xiaomi.youpin.youpin_common.AppStoreShareInitUtil;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.api.IAppStatApi;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import com.xiaomi.youpin.youpin_constants.YPStoreConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreApiProviderImp extends AbsStoreApiProviderImpl {
    private static final String e = "StoreApiProviderImp";
    private static final String f = "com.xiaomi";

    /* renamed from: a  reason: collision with root package name */
    AppStatApi f22143a = new AppStatApi();
    Handler b = new Handler(Looper.getMainLooper());

    public String c() {
        return "GooglePlay";
    }

    public String d() {
        return "SmartHome";
    }

    public Map<String, Object> e() {
        return null;
    }

    public boolean h() {
        return false;
    }

    public void j() {
    }

    public boolean m() {
        return false;
    }

    public void a(StoreApiManager storeApiManager) {
        storeApiManager.a((IPayProvider) new AlipayProvider());
        storeApiManager.a((IPayProvider) new UCashierProvider(MiotAccountProvider.a(SHApplication.getAppContext())));
        storeApiManager.a((IPayProvider) new MipayProvider(MiotAccountProvider.a(SHApplication.getAppContext())));
        if (a()) {
            storeApiManager.a((IPayProvider) new WxpayProvider());
        }
        if (UnionpayProvider.a(AppInfo.a(), new IUnionpayCallback() {
            public final void onInfoLoaded(boolean z) {
                StoreApiProviderImp.a(StoreApiManager.this, z);
            }
        })) {
            storeApiManager.a((IPayProvider) new UnionpayProvider());
            storeApiManager.a(YPStoreConstant.PAY_UNIONMIPAY, true);
        }
        AppStoreShareInitUtil.a(storeApiManager);
        storeApiManager.a(YPStoreConstant.PAY_ALIPAY, true);
        storeApiManager.a(YPStoreConstant.PAY_UNIONPAY, true);
        storeApiManager.a(YPStoreConstant.PAY_UCASHIER, true);
        storeApiManager.a(YPStoreConstant.PAY_MIPAY, true);
        storeApiManager.a("share", true);
        if (a()) {
            storeApiManager.a(YPStoreConstant.PAY_WXPAY, true);
        } else {
            storeApiManager.a(YPStoreConstant.PAY_WXPAY, false);
        }
        storeApiManager.a(YPStoreConstant.EXTERN_SHARE, false);
        storeApiManager.a("installmentUrl", "mijia://home.mi.com/miloan");
        storeApiManager.a("Staging", Boolean.valueOf(GlobalSetting.C));
        storeApiManager.a("Channel", c());
        storeApiManager.a("haveXiaomiSDK", true);
        storeApiManager.a("XiaomiSDKClientId", RequestConstants.Values.CLIENT_ID);
        storeApiManager.a("XiaomiSDKChannelId", RequestConstants.SDK_Channel.channel_id);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(StoreApiManager storeApiManager, boolean z) {
        if (z) {
            storeApiManager.a((IPayProvider) new UnionpayProvider());
            storeApiManager.a(YPStoreConstant.PAY_UNIONMIPAY, true);
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

    public IAppStatApi b() {
        return this.f22143a;
    }

    public void a(String str, Object obj) {
        XmPluginHostApi.instance().setPreferenceValue(str, obj);
    }

    public Object b(String str, Object obj) {
        return XmPluginHostApi.instance().getPreferenceValue(str, obj);
    }

    public boolean f() {
        return GlobalSetting.C;
    }

    public boolean g() {
        return XmPluginHostApi.instance().isDevMode();
    }

    public String i() {
        return CoreApi.a().s();
    }

    public void a(Activity activity) {
        if (activity != null) {
            LoginApi.a().a((Context) activity, 4, (LoginApi.LoginCallback) null);
        }
    }

    public void a(Activity activity, String str) {
        a(activity);
    }

    public void a(WebView webView, String str, String str2, String str3, @Nullable StoreApiProvider.OnReceivedLoginRequestCallback onReceivedLoginRequestCallback) {
        WebViewReceivedLoginRequest.a(webView, str, str2, str3, onReceivedLoginRequestCallback);
    }

    public String a(String str) {
        MiServiceTokenInfo a2;
        if (!CoreApi.a().q() || (a2 = CoreApi.a().a(str)) == null) {
            return null;
        }
        return a2.c;
    }

    private AccountInfo a(Context context, String str) {
        try {
            Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
            if (accountsByType.length <= 0) {
                return null;
            }
            String str2 = accountsByType[0].name;
            MiAccountManager.get(context).setUseSystem();
            MiAccountManager.get(context).invalidateServiceToken(context, MiAccountManager.get(context).getServiceToken(context, str).get());
            ServiceTokenResult serviceTokenResult = MiAccountManager.get(context).getServiceToken(context, str).get();
            return new AccountInfo(str2, (String) null, (String) null, serviceTokenResult.cUserId, serviceTokenResult.serviceToken, serviceTokenResult.security, (String) null);
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean k() {
        return CoreApi.a().q();
    }

    public boolean l() {
        return CoreApi.a().v();
    }

    public void a(String str, final StoreBaseCallback<com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo> storeBaseCallback) {
        if (!CoreApi.a().q()) {
            storeBaseCallback.onFail(-1, "not login");
        } else if (CoreApi.a().v()) {
            MiLoginApi.a(str, CoreApi.a().a(str), false, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                public void a(MiServiceTokenInfo miServiceTokenInfo) {
                    CoreApi.a().a(miServiceTokenInfo, (com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>) null);
                    storeBaseCallback.onSuccess(new com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo(miServiceTokenInfo.f23514a, miServiceTokenInfo.b, miServiceTokenInfo.c, miServiceTokenInfo.d, miServiceTokenInfo.f, miServiceTokenInfo.e));
                }

                public void a(ExceptionError exceptionError) {
                    storeBaseCallback.onFail(exceptionError.a(), exceptionError.b());
                }
            });
        } else {
            MiLoginApi.a(str, CoreApi.a().s(), CoreApi.a().w(), false, new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                public void a(MiServiceTokenInfo miServiceTokenInfo) {
                    CoreApi.a().a(miServiceTokenInfo, (com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>) null);
                    storeBaseCallback.onSuccess(new com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo(miServiceTokenInfo.f23514a, miServiceTokenInfo.b, miServiceTokenInfo.c, miServiceTokenInfo.d, miServiceTokenInfo.f, miServiceTokenInfo.e));
                }

                public void a(ExceptionError exceptionError) {
                    storeBaseCallback.onFail(exceptionError.a(), exceptionError.b());
                }
            });
        }
    }

    public void a(final IStoreCallback<Map<String, Object>> iStoreCallback) {
        final HashMap hashMap = new HashMap();
        if (CoreApi.a().q()) {
            hashMap.put("uid", CoreApi.a().s());
            HashMap hashMap2 = new HashMap();
            List<MiServiceTokenInfo> x = CoreApi.a().x();
            if (x != null) {
                for (MiServiceTokenInfo next : x) {
                    hashMap2.put(next.f23514a, next.c);
                }
            }
            hashMap.put("token", hashMap2);
            hashMap.put("mode", UserMode.f23179a);
            UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
                public void a(ShareUserRecord shareUserRecord) {
                    if (shareUserRecord != null) {
                        hashMap.put("icon", shareUserRecord.url);
                        hashMap.put("nickName", shareUserRecord.nickName);
                        hashMap.put("phone", shareUserRecord.phone);
                        hashMap.put("userName", shareUserRecord.nickName);
                        if (iStoreCallback != null) {
                            iStoreCallback.onSuccess(hashMap);
                        }
                    } else if (iStoreCallback != null) {
                        iStoreCallback.onFailed(-1, "");
                    }
                }

                public void a(int i) {
                    if (iStoreCallback != null) {
                        iStoreCallback.onFailed(i, "");
                    }
                }

                public void a(int i, Object obj) {
                    if (iStoreCallback != null) {
                        iStoreCallback.onFailed(i, "");
                    }
                }
            });
        } else if (iStoreCallback != null) {
            iStoreCallback.onFailed(-1, "not login");
        }
    }

    public Context n() {
        return SHApplication.getAppContext();
    }

    public void a(String str, final String str2, IStoreCallback<Void> iStoreCallback) {
        super.a(str, str2, iStoreCallback);
        if ("redpoint_cart".equals(str) && !TextUtils.isEmpty(str2)) {
            try {
                this.b.post(new Runnable() {
                    public void run() {
                        int i;
                        try {
                            i = Integer.parseInt(str2);
                        } catch (Exception e) {
                            e.printStackTrace();
                            i = 0;
                        }
                        XmPluginHostApi.instance().getRedpointManager().setRedPoint(2, i);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
