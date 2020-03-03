package com.xiaomi.loan.sdk;

import android.app.Activity;
import android.content.Context;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.jr.appbase.accounts.MiFiAccountNotifier;
import com.xiaomi.jr.appbase.accounts.MiFiAccountNotifierImpl;
import com.xiaomi.jr.appbase.accounts.MiFiAccountProvider;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;

public class MiFiSdk {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1466a = "MiFiSdk";
    private static boolean b;

    public static class Application {

        /* renamed from: a  reason: collision with root package name */
        private static MiFiApplication f1467a;

        public static void a(Context context, android.app.Application application) {
            f1467a = new MiFiApplication(application);
            f1467a.attachBaseContext(context);
        }

        public static void a() {
            f1467a.onCreate();
        }

        public static void b() {
            f1467a.onTerminate();
        }
    }

    public static void a(IAccountProvider iAccountProvider) {
        MiFiAccountProvider.a(iAccountProvider);
    }

    public static void a(MiFiAccountNotifierImpl miFiAccountNotifierImpl) {
        MiFiAccountNotifier.a(miFiAccountNotifierImpl);
    }

    public static AccountNotifier a() {
        return MiFiAccountNotifier.a();
    }

    public static void a(Activity activity, String str) {
        a(activity, str, (String) null);
    }

    public static void a(final Activity activity, final String str, final String str2) {
        if (!b) {
            PermissionManager.a((Context) activity, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.READ_EXTERNAL_STORAGE"}, (Request.Callback) new Request.Callback() {
                public void a() {
                    c();
                }

                public void a(String str) {
                    c();
                }

                public void b() {
                    c();
                }

                private void c() {
                    DeeplinkUtils.openDeeplink(activity, str2, str);
                }
            });
            b = true;
            return;
        }
        DeeplinkUtils.openDeeplink(activity, str2, str);
    }
}
