package com.xiaomi.jr.mipay.codepay;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.jr.account.AccountChangeEvent;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.deeplink.CustomDeeplinkHandler;
import com.xiaomi.jr.deeplink.DeeplinkConfig;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.mipay.codepay.access.AccessManager;
import com.xiaomi.jr.mipay.codepay.ui.CodePayActivity;
import com.xiaomi.jr.mipay.codepay.ui.CodePayDialogActivity;
import com.xiaomi.jr.mipay.codepay.ui.CodePayFragment;
import com.xiaomi.jr.mipay.common.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;

public class CodePayManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1446a = "https://api.jr.mi.com/app/codepay";
    public static final String b = "installment";
    private static String c;
    private static int d;
    private static String e;
    private static String f;
    private static Handler g;
    /* access modifiers changed from: private */
    public static List<Activity> h = new ArrayList();

    public static void a(Context context, String str, int i, String str2) {
        c = str;
        d = i;
        e = str2;
        g = new Handler(Looper.getMainLooper());
        HashMap hashMap = new HashMap();
        $$Lambda$CodePayManager$7kQgznbG3NHrPxqknckF7RU4BFQ r3 = $$Lambda$CodePayManager$7kQgznbG3NHrPxqknckF7RU4BFQ.INSTANCE;
        CustomDeeplinkHandler.addHandler(r3);
        hashMap.put(MifiHostsUtils.h(f1446a), new DeeplinkConfig.RouteBuilder() {
            public final DeeplinkPolicy.Target build(String str) {
                return CodePayManager.a(CustomDeeplinkHandler.Handler.this, str);
            }
        });
        DeeplinkConfig.addSimpleRouteBuilders(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put($$Lambda$CodePayManager$b2UnAPBHhMZGmEfrIy3PZiDSEA.INSTANCE, $$Lambda$CodePayManager$WF4BCGRxl_g2NDxx6JhUHvyzbg.INSTANCE);
        hashMap2.put($$Lambda$CodePayManager$DM3ZOwzOGBcsitWrtcAlwajK6g.INSTANCE, $$Lambda$CodePayManager$iTZptB6O0z1wykJB5lduW_hEBMo.INSTANCE);
        DeeplinkConfig.addPatternRouteBuilders(hashMap2);
        a(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Object obj, Intent intent) {
        if (obj instanceof Activity) {
            Activity activity = (Activity) obj;
            if (ActivityChecker.a(activity)) {
                a(activity, intent.getStringExtra("url"));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target a(CustomDeeplinkHandler.Handler handler, String str) {
        return new DeeplinkPolicy.CustomTarget(handler, (String) null, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target c(String str) {
        return new DeeplinkPolicy.ActivityTarget(CodePayActivity.class, (DeeplinkPolicy.TargetProcessor) null, (String) null, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target a(String str) {
        return new DeeplinkPolicy.ActivityTarget(CodePayDialogActivity.class, (DeeplinkPolicy.TargetProcessor) null, (String) null, (Bundle) null);
    }

    private static void a(Activity activity, String str) {
        f = UrlUtils.b(str, b);
        AccessManager.a(activity, (AccessManager.Callback) new AccessManager.Callback(str, activity) {
            private final /* synthetic */ String f$0;
            private final /* synthetic */ Activity f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void result(boolean z) {
                CodePayManager.a(this.f$0, this.f$1, z);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(String str, Activity activity, boolean z) {
        if (z) {
            SessionManager.a();
            DeeplinkUtils.openDeeplink(activity, (String) null, UrlUtils.c(str, CodePayActivity.DEEPLINK_PREFIX + CodePayFragment.f10924a + "&miref=" + e));
        }
    }

    private static void a(Context context) {
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (a(activity) && !CodePayManager.h.contains(activity)) {
                    CodePayManager.h.add(activity);
                }
            }

            public void onActivityDestroyed(Activity activity) {
                if (a(activity)) {
                    CodePayManager.h.remove(activity);
                }
            }

            private boolean a(Activity activity) {
                return (activity instanceof CodePayActivity) || (activity instanceof CodePayDialogActivity);
            }
        });
    }

    public static String a() {
        return c;
    }

    public static int b() {
        return d;
    }

    public static String c() {
        return e;
    }

    public static String d() {
        return f;
    }

    public static Handler e() {
        return g;
    }

    public static void a(int i, String str) {
        if (h.size() > 0) {
            for (int size = h.size() - 1; size >= 0; size--) {
                h.get(size).finish();
            }
            h.clear();
        }
        SessionManager.a(i, str);
    }

    @Subscribe
    public static void onAccountChange(AccountChangeEvent accountChangeEvent) {
        if (accountChangeEvent.a() == 1) {
            a(SessionManager.FinishCode.b, (String) null);
        }
    }
}
