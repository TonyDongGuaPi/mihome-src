package com.xiaomi.jr;

import android.support.annotation.Keep;
import com.xiaomi.jr.appbase.ApplicationSpec;
import com.xiaomi.jr.appbase.CustomizedSnippets;
import com.xiaomi.jr.common.utils.AppUtils;

@Keep
public class ApplicationConfigurator {
    static /* synthetic */ Object lambda$injectSnippets$1(Object[] objArr) {
        return "sdk";
    }

    @Keep
    public static void config() {
        configConstants();
        injectSnippets();
    }

    private static void configConstants() {
        ApplicationSpec.f1387a = "2882303761517327335";
        ApplicationSpec.b = "5151732783335";
        ApplicationSpec.c = "?app=com.xiaomi.jr#/e/EJR_FAQ_SETTING";
        ApplicationSpec.d = "?app=com.xiaomi.jr#/e/EJR_FAQ_FEEDBACK";
    }

    private static void injectSnippets() {
        CustomizedSnippets.a(CustomizedSnippets.k, (CustomizedSnippets.Snippet) $$Lambda$ApplicationConfigurator$xpfrpAOs0REx5Oew8qR5_9Xp8.INSTANCE);
        CustomizedSnippets.a(CustomizedSnippets.f1388a, (CustomizedSnippets.Snippet) $$Lambda$ApplicationConfigurator$rnE1c3UVfSQKIGMmgwRAnq4Lqw.INSTANCE);
        CustomizedSnippets.a(CustomizedSnippets.l, (CustomizedSnippets.Snippet) $$Lambda$ApplicationConfigurator$F65FHOybW0r5vBsr04mil_v9o8.INSTANCE);
    }

    static /* synthetic */ Object lambda$injectSnippets$0(Object[] objArr) {
        boolean z = false;
        int f = AppUtils.f(objArr[0]);
        if (f == 17 || f == 69) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Object lambda$injectSnippets$2(Object[] objArr) {
        return false;
    }
}
