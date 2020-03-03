package com.xiaomi.payment.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.Presenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.Client;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.NetworkExceptionHandler;
import com.mibi.common.exception.rxjava.NoPrivacyRightExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.mibi.common.ui.webview.WebFragment;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.payment.MiliCenterEntryActivity;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.PreloadedAppUtils;
import com.xiaomi.payment.homepage.MiliCenterContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxHomePageGridTask;
import com.xiaomi.payment.task.rxjava.RxHomePageHeaderTask;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MiliCenterPresenter extends Presenter<MiliCenterContract.View> implements MiliCenterContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12328a = "MiliCenterPresenter";
    private static final String b = "com.miui.securitycenter.action.TRANSITION";
    private static final String c = "com.android.launcher.action.INSTALL_SHORTCUT";
    private RxHomePageGridTask d;
    private RxHomePageHeaderTask e;
    private boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;
    private long h;
    private String i;

    public MiliCenterPresenter() {
        super(MiliCenterContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        Bundle n_ = n_();
        if (n_ != null) {
            this.i = n_.getString("miref", "");
            a(n_.getString(MibiConstants.dY));
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.equals(MibiConstants.eT)) {
            ((MiliCenterContract.View) l()).a_(false);
            ((MiliCenterContract.View) l()).a((EntryData) null);
        } else if (str.equals(MibiConstants.eU)) {
            ((MiliCenterContract.View) l()).a_(false);
            ((MiliCenterContract.View) l()).j();
        } else if (str.equals(MibiConstants.eV)) {
            ((MiliCenterContract.View) l()).a_(false);
            ((MiliCenterContract.View) l()).x_();
        } else if (str.equals(MibiConstants.eX)) {
            B_();
            ((MiliCenterContract.View) l()).y_();
        }
    }

    public void a(final boolean z) {
        HeaderListener headerListener = new HeaderListener(e(), z);
        headerListener.a().a((ExceptionDispatcher.ExceptionHandler) new NetworkExceptionHandler(e()) {
            public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).c(z);
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).c_(MiliCenterPresenter.this.e().getString(R.string.mibi_header_network_error));
                return true;
            }
        }).a((ExceptionDispatcher.ExceptionHandler) new NoPrivacyRightExceptionHandler(e()) {
            public boolean a(int i, String str) {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).a(i, str);
                return true;
            }
        });
        if (this.e == null) {
            this.e = new RxHomePageHeaderTask(e(), f());
        }
        Observable.create(this.e).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).b_(z);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(headerListener);
    }

    public void a(boolean z, final boolean z2) {
        if (this.d == null) {
            this.d = new RxHomePageGridTask(e(), f());
        }
        this.d.b(z);
        HomeGridListener homeGridListener = new HomeGridListener(e(), z);
        homeGridListener.a().a((ExceptionDispatcher.ExceptionHandler) new NetworkExceptionHandler(e()) {
            public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
                super.a(th, bundle, exceptionDispatcher);
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).v_();
                return true;
            }
        });
        Observable.create(this.d).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).q_();
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).p_();
                if (z2) {
                    ((MiliCenterContract.View) MiliCenterPresenter.this.l()).b_(0);
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(homeGridListener);
    }

    public void h() {
        if (!this.f && f() != null) {
            ((MiliCenterContract.View) l()).t_();
            if (this.e == null) {
                this.e = new RxHomePageHeaderTask(e(), f());
            }
            a(true, false);
            if (f() != null && !Utils.b() && Utils.a(f())) {
                ((MiliCenterContract.View) l()).r_();
                Utils.b(f());
            }
            this.f = true;
        }
    }

    private class HomeGridListener extends RxBaseErrorHandleTaskListener<RxHomePageGridTask.Result> {
        private boolean b = false;

        public HomeGridListener(Context context, boolean z) {
            super(context);
            this.b = z;
        }

        /* access modifiers changed from: protected */
        public void a(RxHomePageGridTask.Result result) {
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).b_(8);
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).w_();
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).a(result);
            if (!MiliCenterPresenter.this.g) {
                MiliCenterPresenter.this.a(false);
            }
            boolean unused = MiliCenterPresenter.this.g = true;
            if (this.b) {
                MiliCenterPresenter.this.a(false, false);
            }
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).b_(8);
            if (this.b) {
                MiliCenterPresenter.this.a(false, true);
            } else if (MiliCenterPresenter.this.g) {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).w_();
                MiliCenterPresenter.this.a(false);
            } else {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).d_(str + ":" + i);
            }
        }
    }

    private class HeaderListener extends RxBaseErrorHandleTaskListener<RxHomePageHeaderTask.Result> {
        private boolean b = false;

        public HeaderListener(Context context, boolean z) {
            super(context);
            this.b = z;
        }

        /* access modifiers changed from: protected */
        public void a(RxHomePageHeaderTask.Result result) {
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).c(this.b);
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).c();
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).a(result.f12424a);
            if (result.d) {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).u_();
            } else {
                ((MiliCenterContract.View) MiliCenterPresenter.this.l()).a(result.b, result.c);
            }
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).c(this.b);
            ((MiliCenterContract.View) MiliCenterPresenter.this.l()).c_(str + ":" + i);
        }
    }

    public void a(final EntryData entryData) {
        if (!PreloadedAppUtils.a(e(), entryData) || !PreloadedAppUtils.a(e(), entryData, new PreloadedAppUtils.PackageInstallListener() {
            public void a(String str, int i) {
                if (i == 1) {
                    ((MiliCenterContract.View) MiliCenterPresenter.this.l()).b(entryData);
                }
            }
        })) {
            ((MiliCenterContract.View) l()).b(entryData);
        }
    }

    public void a(Activity activity) {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bw, "menu_send_short_cut");
        MistatisticUtils.a("menu", (Map<String, String>) hashMap);
        if (Build.VERSION.SDK_INT >= 26) {
            Intent intent = new Intent(activity, MiliCenterEntryActivity.class);
            intent.setAction("android.intent.action.VIEW");
            ShortcutManager shortcutManager = (ShortcutManager) e().getApplicationContext().getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                shortcutManager.requestPinShortcut(new ShortcutInfo.Builder(activity, "com.xiaomi.payment:string/mibi_mili_center").setIcon(Icon.createWithResource(e(), R.drawable.mibi_ic_milicenter)).setShortLabel("com.xiaomi.payment:string/mibi_mili_center").setIntent(intent).setActivity(activity.getComponentName()).build(), (IntentSender) null);
            }
        } else {
            Intent intent2 = new Intent(c);
            intent2.putExtra("duplicate", false);
            intent2.putExtra("android.intent.extra.shortcut.NAME", "com.xiaomi.payment:string/mibi_mili_center");
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(e(), R.drawable.mibi_ic_milicenter));
            intent2.putExtra("android.intent.extra.shortcut.INTENT", new Intent(e(), MiliCenterEntryActivity.class));
            e().sendBroadcast(intent2);
        }
        if (Client.e()) {
            Toast.makeText(e(), R.string.mibi_shurtcut_create_success, 0).show();
        }
    }

    public void a(long j) {
        this.h = j;
    }

    public void g() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bw, "menu_lock_pattern");
        MistatisticUtils.a("menu", (Map<String, String>) hashMap);
        Intent intent = new Intent(b);
        intent.putExtra(MibiConstants.hd, e().getPackageName());
        if (!Utils.b(e(), intent)) {
            Log.d(f12328a, "com.miui.securitycenter.action.TRANSITION is not existed");
        } else {
            ((MiliCenterContract.View) l()).a_(intent);
        }
    }

    public void i() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bw, "menu_show_FAQ");
        MistatisticUtils.a("menu", (Map<String, String>) hashMap);
        String b2 = MibiConstants.b(MibiConstants.bv);
        Bundle bundle = new Bundle();
        bundle.putString("webUrl", b2);
        bundle.putString("webTitle", e().getString(R.string.mibi_menu_qa));
        ((MiliCenterContract.View) l()).a((Class<? extends StepFragment>) WebFragment.class, bundle);
    }

    public void j() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bw, "menu_show_feedback");
        MistatisticUtils.a("menu", (Map<String, String>) hashMap);
        ((MiliCenterContract.View) l()).a_(k());
    }

    public Intent k() {
        Intent intent = new Intent("miui.intent.action.BUGREPORT");
        intent.putExtra("appTitle", Client.F().d());
        intent.putExtra("packageName", Client.F().e());
        intent.putExtra(TSMAuthContants.PARAM_APP_VERSION_NAME, Client.F().c());
        intent.putExtra(TSMAuthContants.PARAM_APP_VERSION_CODE, Client.F().b());
        return intent;
    }

    public void B_() {
        MistatisticUtils.a(AnalyticsConstants.bs);
        if (!TextUtils.isEmpty(this.i)) {
            HashMap hashMap = new HashMap();
            hashMap.put("miref", this.i);
            hashMap.put(AnalyticsConstants.bH, "recharge_milicenter_start");
            MistatisticUtils.a(AnalyticsConstants.bE, AnalyticsConstants.bF, (Map<String, String>) hashMap);
        }
        f().m().a("miref", (Object) this.i);
    }

    public void a(int i2, int i3, Bundle bundle) {
        super.a(i2, i3, bundle);
        if (i2 == MiliCenterContract.d && i3 == -1) {
            B_();
            ((MiliCenterContract.View) l()).y_();
        } else if (i2 == MiliCenterContract.f12310a) {
            if (i3 == 1000 || i3 == 1002) {
                a(true);
            } else if (i3 == 18) {
                ((MiliCenterContract.View) l()).s_();
            }
        } else if (i2 == MiliCenterContract.c) {
            if (i3 == 1000 || i3 == 1002) {
                a(true);
            }
        } else if (i2 == MiliCenterContract.b && i3 == 1 && this.h != bundle.getLong("giftcardValue", this.h)) {
            a(true);
        }
    }
}
