package com.xiaomi.jr.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.amap.api.services.cloud.CloudSearch;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.IWebLoginProcessor;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.account.XiaomiServiceTokenHelper;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import java.lang.ref.WeakReference;

public class XiaomiAccountManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1380a = "com.xiaomi";
    public static final int b = -1;
    public static final int c = -2;
    public static final int d = 4;
    private static final String e = "MiFiXiaomiAccountManager";
    private static final int f = 0;
    private static volatile XiaomiAccountManager j;
    private String g = null;
    private XiaomiServiceTokenHelper h = new XiaomiServiceTokenHelper();
    private IWebLoginProcessor i;
    private IAccountProvider k;
    private AccountNotifier l = new AccountNotifier();

    public interface GetAccountInfoObserver {
        void onGetAccountInfo(boolean z, XiaomiService xiaomiService);
    }

    private static final class LoginCallback implements AccountManagerCallback<Bundle> {

        /* renamed from: a  reason: collision with root package name */
        private Context f10280a;
        private WeakReference<AccountNotifier> b;
        private String c;

        LoginCallback(Context context, AccountNotifier accountNotifier) {
            this.f10280a = context.getApplicationContext();
            this.b = new WeakReference<>(accountNotifier);
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            Utils.a();
            if (!accountManagerFuture.isDone()) {
                QualityMonitor.a(Constants.i, "login_abort", "reason", "future not done");
                return;
            }
            AccountNotifier accountNotifier = (AccountNotifier) this.b.get();
            try {
                Bundle result = accountManagerFuture.getResult();
                if (result == null) {
                    QualityMonitor.a(Constants.i, "login_abort", "reason", "result is null");
                    return;
                }
                XiaomiAccountManager.b(this.f10280a, result, accountNotifier);
                MifiLog.a(XiaomiAccountManager.e, "mLoginCallBack - result = " + result);
            } catch (OperationCanceledException e) {
                if (accountNotifier != null) {
                    accountNotifier.a(this.f10280a, 4);
                }
                MifiLog.e(XiaomiAccountManager.e, "Login is canceled - " + e);
            } catch (Exception e2) {
                MifiLog.e(XiaomiAccountManager.e, "Login throws exception - " + e2);
                QualityMonitor.a(Constants.i, "login_abort", "reason", "unhandled exception: " + e2.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, Bundle bundle, AccountNotifier accountNotifier) {
        Intent intent = (Intent) bundle.getParcelable("intent");
        if (intent != null) {
            AccountResultActivity.startActivity(context, intent, 1);
            return;
        }
        boolean z = bundle.getBoolean("booleanResult");
        int i2 = bundle.getInt("errorCode");
        if (z) {
            i2 = -1;
        } else if (i2 == 0) {
            MifiLog.e(e, "Account relogin. Should NOT happen!");
            QualityMonitor.a(Constants.i, "login_abort", "reason", "relogin");
            return;
        }
        if (accountNotifier != null) {
            accountNotifier.a(context, i2);
        }
    }

    public static XiaomiAccountManager a() {
        if (j == null) {
            synchronized (XiaomiAccountManager.class) {
                if (j == null) {
                    j = new XiaomiAccountManager();
                }
            }
        }
        return j;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        j = null;
        super.finalize();
    }

    private XiaomiAccountManager() {
    }

    public void a(IWebLoginProcessor iWebLoginProcessor) {
        this.i = iWebLoginProcessor;
    }

    public void a(Activity activity, AccountNotifier.AccountLoginCallback accountLoginCallback) {
        this.l.a(accountLoginCallback);
        Context applicationContext = activity.getApplicationContext();
        if (d()) {
            this.l.a(applicationContext, -2);
        } else {
            j().a(activity, (AccountManagerCallback<Bundle>) new LoginCallback(applicationContext, this.l));
        }
    }

    public void logout(Context context, AccountNotifier.AccountLogoutCallback accountLogoutCallback) {
        if (d()) {
            this.l.a(accountLogoutCallback);
            if (j().b()) {
                this.l.a(context);
                return;
            }
            Account i2 = i();
            if (i2 != null) {
                j().a(i2, (AccountManagerCallback<Boolean>) new AccountManagerCallback(context) {
                    private final /* synthetic */ Context f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run(AccountManagerFuture accountManagerFuture) {
                        XiaomiAccountManager.this.a(this.f$1, accountManagerFuture);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Context context, AccountManagerFuture accountManagerFuture) {
        if (accountManagerFuture.isDone()) {
            try {
                Boolean bool = (Boolean) accountManagerFuture.getResult();
                if (bool != null && bool.booleanValue()) {
                    this.l.a(context);
                }
            } catch (Exception e2) {
                MifiLog.e(e, "Logout throws exception - " + e2);
            }
        }
    }

    public boolean b() {
        return d() && !j().b();
    }

    public boolean c() {
        return d() && j().b();
    }

    public boolean d() {
        return j().a();
    }

    public boolean a(Context context) {
        if (!d()) {
            return false;
        }
        if (j().b()) {
            return true;
        }
        Account i2 = i();
        Account b2 = b(context);
        if (b2 == null || i2 == null || !TextUtils.equals(i2.name, b2.name)) {
            return false;
        }
        return true;
    }

    public String e() {
        if (!d()) {
            return "Logout";
        }
        return j().b() ? CloudSearch.SearchBound.LOCAL_SHAPE : "System";
    }

    public XiaomiAccountInfo a(@NonNull Context context, @NonNull String str, String str2) {
        String str3 = str;
        String str4 = str2;
        MifiLog.b(e, "getAccountInfo - url = " + str3 + ", scene = " + str4);
        XiaomiService a2 = XiaomiServices.a(UrlUtils.a(str));
        if (a2 != null) {
            XiaomiAccountInfo a3 = a(context, a2);
            if (a3 == null) {
                QualityMonitor.a(Constants.j, "get_account_info_failure", "reason", "account info null", PageUrl.j, str4, "url", str3, "service", a2.toString());
            }
            return a3;
        }
        QualityMonitor.a(Constants.j, "get_account_info_failure", "reason", "no XiaomiService found", PageUrl.j, str4, "url", str3, "supportServices", XiaomiServices.a().toString());
        return null;
    }

    private XiaomiAccountInfo a(@NonNull Context context, @NonNull XiaomiService xiaomiService) {
        XiaomiAccountInfo a2 = this.h.d(context.getApplicationContext(), xiaomiService.b, xiaomiService.f10282a);
        if (a2 == null) {
            return null;
        }
        if (this.i != null && !a2.h && !TextUtils.isEmpty(xiaomiService.c)) {
            this.i.a(context, xiaomiService.c, (IWebLoginProcessor.WebLoginListener) null);
        }
        return a2;
    }

    public XiaomiAccountInfo b(@NonNull Context context, @NonNull String str, String str2) {
        String str3 = str;
        XiaomiService a2 = XiaomiServices.a(UrlUtils.a(str));
        if (a2 != null) {
            XiaomiAccountInfo a3 = a(context, a2, str3);
            if (a3 == null) {
                QualityMonitor.a(Constants.j, "reset_account_info_failure", "reason", "account info null", PageUrl.j, str2, "url", str3, "service", a2.toString());
            }
            return a3;
        }
        QualityMonitor.a(Constants.j, "reset_account_info_failure", "reason", "no XiaomiService found", PageUrl.j, str2, "url", str3, "supportServices", XiaomiServices.a().toString());
        return null;
    }

    private XiaomiAccountInfo a(@NonNull Context context, @NonNull XiaomiService xiaomiService, @NonNull String str) {
        MifiLog.b(e, "resetAccountInfo - baseUrl = " + xiaomiService.f10282a + ", serviceId = " + xiaomiService.b);
        XiaomiAccountInfo b2 = this.h.c(context.getApplicationContext(), xiaomiService.b, str);
        if (b2 == null) {
            return null;
        }
        if (this.i != null && !b2.h && !TextUtils.isEmpty(xiaomiService.c)) {
            this.i.a(context, xiaomiService.c, (IWebLoginProcessor.WebLoginListener) null);
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.h.a();
    }

    public void a(@NonNull Context context, @NonNull XiaomiService xiaomiService, @Nullable GetAccountInfoObserver getAccountInfoObserver) {
        MifiLog.b(e, "asyncGetAccountInfo - baseUrl = " + xiaomiService.f10282a + ", serviceId = " + xiaomiService.b);
        this.h.a(context, xiaomiService.b, xiaomiService.f10282a, (XiaomiServiceTokenHelper.XiaomiServiceTokenObserver) new XiaomiServiceTokenHelper.XiaomiServiceTokenObserver(getAccountInfoObserver, xiaomiService, context) {
            private final /* synthetic */ XiaomiAccountManager.GetAccountInfoObserver f$1;
            private final /* synthetic */ XiaomiService f$2;
            private final /* synthetic */ Context f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onServiceTokenReady(XiaomiAccountInfo xiaomiAccountInfo) {
                XiaomiAccountManager.this.a(this.f$1, this.f$2, this.f$3, xiaomiAccountInfo);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(@Nullable GetAccountInfoObserver getAccountInfoObserver, @NonNull XiaomiService xiaomiService, @NonNull Context context, XiaomiAccountInfo xiaomiAccountInfo) {
        if (xiaomiAccountInfo != null && xiaomiAccountInfo.h) {
            this.g = xiaomiAccountInfo.b;
            if (getAccountInfoObserver != null) {
                getAccountInfoObserver.onGetAccountInfo(true, xiaomiService);
            }
        } else if (TextUtils.isEmpty(xiaomiService.c)) {
            if (getAccountInfoObserver != null) {
                getAccountInfoObserver.onGetAccountInfo(false, xiaomiService);
            }
        } else if (this.i != null) {
            MifiLog.a(e, "asyncGetAccountInfo - setCookie failed for " + xiaomiService.f10282a + Operators.BRACKET_START_STR + xiaomiService.b + "), need do weblogin.");
            this.i.a(context, xiaomiService.c, new IWebLoginProcessor.WebLoginListener(xiaomiService) {
                private final /* synthetic */ XiaomiService f$1;

                {
                    this.f$1 = r2;
                }

                public final void onWebLoginResult(boolean z, String str) {
                    XiaomiAccountManager.a(XiaomiAccountManager.GetAccountInfoObserver.this, this.f$1, z, str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(@Nullable GetAccountInfoObserver getAccountInfoObserver, @NonNull XiaomiService xiaomiService, boolean z, String str) {
        if (getAccountInfoObserver != null) {
            getAccountInfoObserver.onGetAccountInfo(z, xiaomiService);
        }
    }

    public void a(@NonNull Context context, @NonNull XiaomiService xiaomiService, @NonNull String str, @Nullable GetAccountInfoObserver getAccountInfoObserver) {
        MifiLog.b(e, "asyncResetAccountInfo - baseUrl = " + xiaomiService.f10282a + ", serviceId = " + xiaomiService.b);
        XiaomiServices.a(xiaomiService);
        this.h.b(context, xiaomiService.b, str, new XiaomiServiceTokenHelper.XiaomiServiceTokenObserver(getAccountInfoObserver, xiaomiService) {
            private final /* synthetic */ XiaomiAccountManager.GetAccountInfoObserver f$1;
            private final /* synthetic */ XiaomiService f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onServiceTokenReady(XiaomiAccountInfo xiaomiAccountInfo) {
                XiaomiAccountManager.this.a(this.f$1, this.f$2, xiaomiAccountInfo);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(@Nullable GetAccountInfoObserver getAccountInfoObserver, @NonNull XiaomiService xiaomiService, XiaomiAccountInfo xiaomiAccountInfo) {
        if (xiaomiAccountInfo != null && xiaomiAccountInfo.h) {
            this.g = xiaomiAccountInfo.b;
            if (getAccountInfoObserver != null) {
                getAccountInfoObserver.onGetAccountInfo(true, xiaomiService);
            }
        } else if (getAccountInfoObserver != null) {
            getAccountInfoObserver.onGetAccountInfo(false, xiaomiService);
        }
    }

    public static String g() {
        return a().g;
    }

    public static void a(String str) {
        a().g = str;
    }

    public static String h() {
        Account i2 = i();
        if (i2 != null) {
            return i2.name;
        }
        return null;
    }

    public static Account i() {
        return j().c();
    }

    public static Account b(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0];
        }
        return null;
    }

    @NonNull
    public static IAccountProvider j() {
        return a().k;
    }

    public static void a(@NonNull IAccountProvider iAccountProvider) {
        a().k = iAccountProvider;
    }

    @NonNull
    public static AccountNotifier k() {
        return a().l;
    }

    public static void a(@NonNull AccountNotifier accountNotifier) {
        a().l = accountNotifier;
    }
}
