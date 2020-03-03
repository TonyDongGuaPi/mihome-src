package com.xiaomi.youpin.mipay;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.jr.appbase.accounts.MiFiAccountNotifierImpl;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;

public class MiFiAccountManagerImpl extends MiFiAccountNotifierImpl implements IAccountProvider {
    private static final String j = "MiFiAccountManagerImpl";
    private static final String k = "com.xiaomi";
    private final Handler l = new Handler(this.m.getMainLooper());
    /* access modifiers changed from: private */
    public Context m;

    public MiFiAccountManagerImpl(Context context) {
        LogUtils.d(j, "new MiFiAccountManagerImpl");
        this.m = context.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            /* JADX WARNING: Removed duplicated region for block: B:17:0x004d  */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
            /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onReceive(android.content.Context r4, android.content.Intent r5) {
                /*
                    r3 = this;
                    if (r5 != 0) goto L_0x0003
                    return
                L_0x0003:
                    java.lang.String r5 = r5.getAction()
                    java.lang.String r0 = "MiFiAccountManagerImpl"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "onReceive:"
                    r1.append(r2)
                    r1.append(r5)
                    java.lang.String r1 = r1.toString()
                    com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r0, (java.lang.String) r1)
                    boolean r0 = android.text.TextUtils.isEmpty(r5)
                    if (r0 == 0) goto L_0x0024
                    return
                L_0x0024:
                    int r0 = r5.hashCode()
                    r1 = -1984077386(0xffffffff89bd61b6, float:-4.5591982E-33)
                    r2 = -1
                    if (r0 == r1) goto L_0x003e
                    r1 = -1470224095(0xffffffffa85e2921, float:-1.2332394E-14)
                    if (r0 == r1) goto L_0x0034
                    goto L_0x0048
                L_0x0034:
                    java.lang.String r0 = "action_on_logout"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0048
                    r5 = 1
                    goto L_0x0049
                L_0x003e:
                    java.lang.String r0 = "action_on_login_success"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0048
                    r5 = 0
                    goto L_0x0049
                L_0x0048:
                    r5 = -1
                L_0x0049:
                    switch(r5) {
                        case 0: goto L_0x0053;
                        case 1: goto L_0x004d;
                        default: goto L_0x004c;
                    }
                L_0x004c:
                    goto L_0x0064
                L_0x004d:
                    com.xiaomi.youpin.mipay.MiFiAccountManagerImpl r5 = com.xiaomi.youpin.mipay.MiFiAccountManagerImpl.this
                    r5.a((android.content.Context) r4)
                    goto L_0x0064
                L_0x0053:
                    com.xiaomi.youpin.mipay.MiFiAccountManagerImpl r5 = com.xiaomi.youpin.mipay.MiFiAccountManagerImpl.this
                    android.content.Context r5 = r5.m
                    boolean r5 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.e((android.content.Context) r5)
                    if (r5 != 0) goto L_0x0064
                    com.xiaomi.youpin.mipay.MiFiAccountManagerImpl r5 = com.xiaomi.youpin.mipay.MiFiAccountManagerImpl.this
                    r5.a((android.content.Context) r4, (int) r2)
                L_0x0064:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.mipay.MiFiAccountManagerImpl.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        }, intentFilter);
    }

    public boolean a() {
        LogUtils.d(j, "hasLogin " + CoreApi.a().q());
        return CoreApi.a().q();
    }

    public boolean b() {
        LogUtils.d(j, "isUseSystem " + CoreApi.a().v());
        return CoreApi.a().v();
    }

    public Account c() {
        LogUtils.d(j, "getAccount " + AccountManagerUtil.c(this.m));
        if (b()) {
            return AccountManagerUtil.c(this.m);
        }
        return AccountManagerUtil.d(this.m);
    }

    public AccountManagerFuture<Bundle> a(Activity activity, AccountManagerCallback<Bundle> accountManagerCallback) {
        LogUtils.d(j, "addAccount");
        return null;
    }

    public void a(Account account, AccountManagerCallback<Boolean> accountManagerCallback) {
        LogUtils.d(j, "removeAccount " + account);
    }

    public AccountManagerFuture<Bundle> a(String str) {
        LogUtils.d(j, "getServiceToken " + str + " isMiSystemAccount " + CoreApi.a().v());
        if (CoreApi.a().v()) {
            return new ServiceTokenAccountManagerFuture(MiAccountManager.get(this.m).getServiceToken(this.m, str));
        }
        final Account account = new Account(CoreApi.a().s(), "com.xiaomi");
        final String str2 = str;
        return new YPAccountManagerFuture(this, (Activity) null, this.l, (Handler) null, (AccountManagerCallback) null, str, CoreApi.a().u()) {
            final /* synthetic */ MiFiAccountManagerImpl c;

            {
                this.c = r9;
            }

            @SuppressLint({"StaticFieldLeak"})
            public void a() {
                AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                    /* access modifiers changed from: protected */
                    public Object doInBackground(Object... objArr) {
                        MiPayAccountUtil.a(AnonymousClass2.this.d, account, str2);
                        return null;
                    }
                }, new Object[0]);
            }
        }.b();
    }

    public void a(Bundle bundle) {
        LogUtils.d(j, "invalidateServiceToken(): " + bundle);
    }

    public void a(Context context, int i) {
        super.a(context, i);
    }

    public void a(Context context) {
        super.a(context);
    }
}
