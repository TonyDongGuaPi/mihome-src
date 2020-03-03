package com.xiaomi.loan.sdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.jr.agreement.AgreementUpdateManager;
import com.xiaomi.jr.appbase.LinkableActivity;
import com.xiaomi.jr.appbase.configuration.ConfigurationManager;

public class MiFinanceActivity extends LinkableActivity {

    /* renamed from: a  reason: collision with root package name */
    private static MiFinanceActivity f11091a;
    private Handler b = new Handler(Looper.getMainLooper());

    public static MiFinanceActivity getInstance() {
        return f11091a;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        f11091a = this;
        super.onCreate(bundle);
        this.b.postDelayed(new Runnable() {
            public final void run() {
                MiFinanceActivity.this.b();
            }
        }, 2000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b() {
        ConfigurationManager.a((Context) this).a((Context) this, (ConfigurationManager.RequestConfigurationListener) null, 0);
        AgreementUpdateManager.a().g();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.b.removeCallbacksAndMessages((Object) null);
        f11091a = null;
    }
}
