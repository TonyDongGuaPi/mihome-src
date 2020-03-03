package com.xiaomi.jr.appbase.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.agreement.AgreementUpdateManager;
import com.xiaomi.jr.appbase.MiFiActivityManager;
import com.xiaomi.jr.appbase.app.MiFiAppControllerImpl;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import com.xiaomi.jr.web.utils.MifiWebUtils;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MiFiAppControllerImpl {
    private static final String b = "MiFiAppControllerImpl";

    /* renamed from: a  reason: collision with root package name */
    protected Application f1394a;

    protected interface PostCleanObserver {
        void onPostClean();
    }

    public MiFiAppControllerImpl(Application application) {
        this.f1394a = application;
    }

    public void a() {
        MiFiAppLifecycle.a().e();
    }

    public void b() {
        c(this.f1394a.getPackageManager().getLaunchIntentForPackage(this.f1394a.getPackageName()));
    }

    public void a(Context context) {
        a(context, (PostCleanObserver) null);
    }

    public void a(Intent intent) {
        a((PostCleanObserver) new PostCleanObserver(intent) {
            private final /* synthetic */ Intent f$1;

            {
                this.f$1 = r2;
            }

            public final void onPostClean() {
                MiFiAppControllerImpl.this.c(this.f$1);
            }
        });
    }

    public void c() {
        a((PostCleanObserver) new PostCleanObserver() {
            public final void onPostClean() {
                MiFiAppControllerImpl.this.b();
            }
        });
    }

    public void d() {
        a((PostCleanObserver) new PostCleanObserver() {
            public final void onPostClean() {
                MiFiAppControllerImpl.this.a();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void c(Intent intent) {
        MiFiActivityManager.a().b();
        intent.addFlags(335544320);
        this.f1394a.startActivity(intent);
    }

    private void a(PostCleanObserver postCleanObserver) {
        if (!XiaomiAccountManager.a().d()) {
            a(this.f1394a, postCleanObserver);
        } else {
            XiaomiAccountManager.a().logout(this.f1394a, new AccountNotifier.AccountLogoutCallback(postCleanObserver) {
                private final /* synthetic */ MiFiAppControllerImpl.PostCleanObserver f$1;

                {
                    this.f$1 = r2;
                }

                public final void onLogout() {
                    MiFiAppControllerImpl.this.b(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(PostCleanObserver postCleanObserver) {
        a(this.f1394a, postCleanObserver);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, PostCleanObserver postCleanObserver) {
        MifiLog.b(b, "clearDiskCache");
        try {
            PreferenceUtils.a(context, AppConstants.m);
            AgreementUpdateManager.a().e();
            MifiWebUtils.a(context);
            String absolutePath = context.getCacheDir().getAbsolutePath();
            HashSet hashSet = null;
            if (Build.VERSION.SDK_INT >= 24) {
                hashSet = new HashSet();
                hashSet.add(new File(absolutePath, "WebView").getCanonicalPath());
                hashSet.add(new File(absolutePath, "org.chromium.android_webview").getCanonicalPath());
            }
            FileUtils.a(new File(absolutePath), (Set) hashSet);
        } catch (Exception unused) {
        }
    }
}
