package com.xiaomi.jr.appbase;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.miui.supportlite.app.Activity;
import com.xiaomi.jr.appbase.app.MiFiAppDelegate;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.appbase.utils.PausableHandler;
import com.xiaomi.jr.appbase.utils.WebUtils;
import com.xiaomi.jr.base.IAppDelegate;
import com.xiaomi.jr.base.IPageDelegate;
import com.xiaomi.jr.common.utils.MifiTrace;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.deeplink.DeeplinkConstants;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.pagereload.pagereload.DefaultPageReloader;
import com.xiaomi.jr.pagereload.pagereload.IPageReloader;
import com.xiaomi.jr.pagereload.pagereload.PageReloadUtils;
import com.xiaomi.jr.stats.StatUtils;

public abstract class BaseActivity extends Activity implements IPageDelegate {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10306a = "MiFinanceBaseActivity";
    private IPageReloader b = new DefaultPageReloader();
    protected boolean mBackHome;
    protected String mBackUrl;

    public /* synthetic */ void a(boolean z) {
        IPageDelegate.CC.$default$a(this, z);
    }

    public /* synthetic */ boolean a() {
        return IPageDelegate.CC.$default$a(this);
    }

    public void reload() {
    }

    public static final class StateFragment extends Fragment {

        /* renamed from: a  reason: collision with root package name */
        private static final String f10307a = "State";
        private PausableHandler b = new PausableHandler(Looper.getMainLooper());

        public void a(Runnable runnable) {
            this.b.post(runnable);
        }

        public void a(Runnable runnable, long j) {
            this.b.postDelayed(runnable, j);
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setRetainInstance(true);
        }

        public void onResume() {
            super.onResume();
            this.b.a();
        }

        public void onPause() {
            super.onPause();
            this.b.b();
        }

        public void onDestroy() {
            super.onDestroy();
            this.b.c();
        }
    }

    public void postForeground(Runnable runnable) {
        StateFragment stateFragment = (StateFragment) getSupportFragmentManager().findFragmentByTag("State");
        if (stateFragment != null) {
            stateFragment.a(runnable);
        }
    }

    public void postDelayedForeground(Runnable runnable, long j) {
        StateFragment stateFragment = (StateFragment) getSupportFragmentManager().findFragmentByTag("State");
        if (stateFragment != null) {
            stateFragment.a(runnable, j);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        MifiTrace.a("BaseActivity.onCreate");
        PageReloadUtils.a(this.b);
        super.onCreate(bundle);
        if (bundle == null) {
            StateFragment stateFragment = new StateFragment();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) stateFragment, "State");
            beginTransaction.commit();
        }
        MifiTrace.a();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PageReloadUtils.b(this.b);
        super.onDestroy();
    }

    public void onHomeSelected() {
        onBackPressed();
    }

    public void onBackPressed() {
        finish();
    }

    public void finish() {
        if (this.mBackHome) {
            a(this.mBackUrl);
        }
        Bundle extras = getIntent().getExtras();
        if (Build.VERSION.SDK_INT < 21 || extras == null || !extras.getBoolean(DeeplinkConstants.KEY_START_FROM_ENTRY)) {
            super.finish();
        } else {
            super.finishAndRemoveTask();
        }
    }

    private void a(String str) {
        if (str == null) {
            str = AppConstants.B;
        } else if (!WebUtils.h(str)) {
            str = WebUtils.a(str, false);
        }
        DeeplinkUtils.openDeeplink(this, (String) null, str);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        StatUtils.a((android.app.Activity) this, "");
        this.b.a(getTaskId());
        if (this.b.a()) {
            reload();
            this.b.a(IPageReloader.ReloadOnResumeType.NOT_RELOAD);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        StatUtils.b(this, "");
        super.onPause();
    }

    public boolean isOnDestroyCalled() {
        return !MiFiActivityManager.a().c(this);
    }

    /* access modifiers changed from: protected */
    public void setBackHome(boolean z, String str) {
        this.mBackHome = z;
        this.mBackUrl = str;
    }

    public void reload(Fragment fragment) {
        reload();
    }

    public void startActivity(Intent intent) {
        this.b.c();
        Utils.a((Context) this, intent);
        super.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.b.c();
        Utils.a((Context) this, intent);
        super.startActivityForResult(intent, i);
    }

    public IAppDelegate getAppDelegate() {
        return MiFiAppDelegate.a();
    }

    public IPageReloader getPageReloader() {
        return this.b;
    }
}
