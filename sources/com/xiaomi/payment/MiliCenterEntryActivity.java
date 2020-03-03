package com.xiaomi.payment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.base.ActivityDecoratorAdapter;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.SessionManager;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.homepage.MiliCenterFragment;
import com.xiaomi.payment.homepage.MiliCenterPresenter;
import com.xiaomi.payment.ui.BaseEntryHandler;
import com.xiaomi.payment.ui.EntryResultCallback;
import com.xiaomi.payment.ui.IEntryProxy;

public class MiliCenterEntryActivity extends BaseActivity implements EntryResultCallback, IEntryProxy {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12143a = "homepage";
    private BaseEntryHandler b;
    private MiliCenterFragment c;

    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
        super.handleCreate(bundle);
        PaymentApp.a(getApplication());
        Bundle extras = getIntent().getExtras();
        if (this.b == null) {
            this.b = new BaseEntryHandler(this, this);
        }
        if (bundle == null) {
            a(extras);
        }
    }

    public void startEntryProcess(Bundle bundle) {
        this.b.a(bundle, false);
    }

    private void a(Bundle bundle) {
        MiliCenterFragment miliCenterFragment = new MiliCenterFragment();
        miliCenterFragment.setArguments(new Bundle());
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (bundle != null) {
            miliCenterFragment.setArguments(bundle);
        }
        beginTransaction.add(16908290, miliCenterFragment, f12143a);
        beginTransaction.commit();
    }

    /* access modifiers changed from: private */
    public void a() {
        this.c = (MiliCenterFragment) getFragmentManager().findFragmentByTag(f12143a);
        if (this.c != null) {
            MiliCenterPresenter miliCenterPresenter = (MiliCenterPresenter) this.c.H_();
            miliCenterPresenter.a(getSession());
            miliCenterPresenter.h();
            return;
        }
        finish();
    }

    public void onEntrySuccess(AccountLoader accountLoader) {
        updateSession(SessionManager.a(this, accountLoader));
        PrivacyManager.a(getSession(), "105", MibiPrivacyUtils.b);
        if (!isPaused()) {
            a();
        } else {
            decorate(new ActivityDecoratorAdapter() {
                public void b() {
                    MiliCenterEntryActivity.this.unDecorate(this);
                    MiliCenterEntryActivity.this.a();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        super.doResume();
        if (hasSession() && this.c == null) {
            a();
        }
    }

    public void onEntryFailed(int i, String str) {
        finish();
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
        super.doActivityResult(i, i2, intent);
        this.b.a(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.b.a(i, strArr, iArr);
    }
}
