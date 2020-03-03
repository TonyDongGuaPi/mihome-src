package com.xiaomi.loan.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.jr.appbase.LinkableActivity;
import com.xiaomi.jr.appbase.MiFiActivityManager;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.appbase.utils.MiFiUrlUtils;
import com.xiaomi.jr.appbase.utils.WebUtils;
import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.deeplink.CustomDeeplinkHandler;
import com.xiaomi.jr.deeplink.DeeplinkConfig;
import com.xiaomi.jr.deeplink.DeeplinkConstants;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;
import com.xiaomi.jr.deeplink.Utils;
import com.xiaomi.jr.stats.StatUtils;
import java.util.HashMap;

public class MiFiDeeplinkConfig {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1465a = "MiFiDeeplinkConfig";

    static class UrlTranslator implements DeeplinkConfig.UrlTranslator {
        UrlTranslator() {
        }

        public String toEnv(String str) {
            return MifiHostsUtils.g(str);
        }

        public String toNormal(String str) {
            return MifiHostsUtils.h(str);
        }
    }

    public static void a(Context context) {
        b(context);
        a();
        b();
        DeeplinkConfig.setUrlTranslator(new UrlTranslator());
        DeeplinkConfig.setDeeplinkProcessor(new DeeplinkPolicy.DeeplinkProcessor(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final String process(Object obj, String str) {
                return MiFiDeeplinkConfig.a(this.f$0, obj, str);
            }
        });
        DeeplinkConfig.setBasicTargetProcessor($$Lambda$MiFiDeeplinkConfig$o5Hae_5AL6G97QkTyUojSwKVDrk.INSTANCE);
        DeeplinkConfig.setDefaultRouteBuilder($$Lambda$MiFiDeeplinkConfig$KedSsqZ8trk2vBAbzgMQ4yV7bpU.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String a(Context context, Object obj, String str) {
        if (!TextUtils.isEmpty(WebUtils.a(str))) {
            return str;
        }
        String str2 = null;
        Context context2 = Utils.toContext(obj);
        boolean z = context2 instanceof Activity;
        if (z) {
            str2 = ((Activity) context2).getIntent().getStringExtra("from");
        }
        if (TextUtils.isEmpty(str2)) {
            com.xiaomi.jr.common.utils.Utils.b(context, "Missing available from parameter when starting " + str);
        }
        return (!MiFiUrlUtils.b(str) || !z) ? str : StatUtils.a(str, (Activity) context2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void c(Object obj, String str, DeeplinkPolicy.Target target) {
        Context context = Utils.toContext(obj);
        target.extras = StatUtils.a(target.extras, str, context instanceof Activity ? (Activity) context : null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target b(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("page_type", 1);
        return new DeeplinkPolicy.ActivityTarget(LinkableActivity.class, (DeeplinkPolicy.TargetProcessor) null, str, bundle);
    }

    private static void b(Context context) {
        HashMap hashMap = new HashMap();
        $$Lambda$MiFiDeeplinkConfig$Z5ldrvNLV6ZwIhkG2KPFYeJtACo r0 = $$Lambda$MiFiDeeplinkConfig$Z5ldrvNLV6ZwIhkG2KPFYeJtACo.INSTANCE;
        Bundle bundle = new Bundle();
        bundle.putInt("page_type", 1);
        hashMap.put(MifiHostsUtils.h(AppConstants.B), new DeeplinkConfig.RouteBuilder(bundle) {
            private final /* synthetic */ Bundle f$1;

            {
                this.f$1 = r2;
            }

            public final DeeplinkPolicy.Target build(String str) {
                return MiFiDeeplinkConfig.b(DeeplinkPolicy.TargetProcessor.this, this.f$1, str);
            }
        });
        DeeplinkConfig.addSimpleRouteBuilders(hashMap);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(Object obj, String str, DeeplinkPolicy.Target target) {
        if (MiFinanceActivity.getInstance() != null) {
            target.extras.putInt(DeeplinkConstants.KEY_INTENT_FLAGS, 335544320);
        } else {
            MiFiActivityManager.a().b();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target b(DeeplinkPolicy.TargetProcessor targetProcessor, Bundle bundle, String str) {
        return new DeeplinkPolicy.ActivityTarget(MiFinanceActivity.class, targetProcessor, (String) null, bundle);
    }

    private static void a() {
        HashMap hashMap = new HashMap();
        $$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQIfsG6I r1 = $$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQIfsG6I.INSTANCE;
        CustomDeeplinkHandler.addHandler(r1);
        hashMap.put($$Lambda$qd_gs20ai76XYFgBpbj4ByRrOs.INSTANCE, new DeeplinkConfig.RouteBuilder() {
            public final DeeplinkPolicy.Target build(String str) {
                return MiFiDeeplinkConfig.a(CustomDeeplinkHandler.Handler.this, str);
            }
        });
        $$Lambda$MiFiDeeplinkConfig$sv0gXcICl6BMzsT6nzLAS_HKt64 r12 = $$Lambda$MiFiDeeplinkConfig$sv0gXcICl6BMzsT6nzLAS_HKt64.INSTANCE;
        Bundle bundle = new Bundle();
        bundle.putInt("page_type", 1);
        hashMap.put($$Lambda$MiFiDeeplinkConfig$6j0k82zy1jYrcoOXaPwS94bcQY.INSTANCE, new DeeplinkConfig.RouteBuilder(bundle) {
            private final /* synthetic */ Bundle f$1;

            {
                this.f$1 = r2;
            }

            public final DeeplinkPolicy.Target build(String str) {
                return MiFiDeeplinkConfig.a(DeeplinkPolicy.TargetProcessor.this, this.f$1, str);
            }
        });
        DeeplinkConfig.addPatternRouteBuilders(hashMap);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target a(CustomDeeplinkHandler.Handler handler, String str) {
        return new DeeplinkPolicy.CustomTarget(handler, (String) null, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Object obj, String str, DeeplinkPolicy.Target target) {
        if (MiFinanceActivity.getInstance() != null) {
            target.extras.putInt(DeeplinkConstants.KEY_INTENT_FLAGS, 335544320);
        } else {
            MiFiActivityManager.a().b();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeeplinkPolicy.Target a(DeeplinkPolicy.TargetProcessor targetProcessor, Bundle bundle, String str) {
        return new DeeplinkPolicy.ActivityTarget(MiFinanceActivity.class, targetProcessor, str, bundle);
    }

    private static void b() {
        HashMap hashMap = new HashMap();
        hashMap.put(AppConstants.C, AppConstants.B);
        DeeplinkConfig.addDeprecatedDeeplinkTable(hashMap);
    }
}
