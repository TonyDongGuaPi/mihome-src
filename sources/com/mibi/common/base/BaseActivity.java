package com.mibi.common.base;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.mibi.common.R;
import com.mibi.common.data.Client;
import com.mibi.common.data.Session;
import com.mibi.common.data.SessionManager;
import com.mibi.common.data.Utils;
import java.util.HashMap;
import java.util.Map;
import miuipub.app.ActionBar;

public abstract class BaseActivity extends DecoratableActivity implements IPresenterFactory, IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7448a = "BaseActivity";
    private static final String b = "SAVE_VIEW_UUID";
    private static final String c = "SAVE_SESSION";
    private static final String d = "KEY_SAVE_PRESENTER_DATA";
    private IPresenterFactory e;
    private String f;
    private final TaskHolder g = new TaskHolder();
    private boolean h;
    private boolean i;
    private CharSequence j;
    private boolean k = true;
    String mBackButtonText;
    boolean mCanBack = true;
    View.OnClickListener mExtraButtonClickListener;
    int mExtraButtonIconRes;
    String mExtraButtonText;
    boolean mHasActionBarInitialized = false;
    boolean mInDialog = false;
    boolean mIsActionBarDirty = false;
    Map<String, IPresenter> mPresenters = new HashMap();
    protected Session mSession;
    boolean mShowActionBar = false;
    boolean mShowBack = true;
    boolean mShowExtraButtonBubble = false;

    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void handleIntent(Intent intent) {
    }

    public Presenter onCreatePresenter() {
        return null;
    }

    /* JADX WARNING: type inference failed for: r4v7, types: [android.os.Parcelable] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doPreCreate(android.os.Bundle r4) {
        /*
            r3 = this;
            super.doPreCreate(r4)
            boolean r0 = com.mibi.common.data.CommonConstants.b
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = "BaseActivity"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onCreate, savedInstanceState="
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x001d:
            if (r4 != 0) goto L_0x002a
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            r3.f = r0
            goto L_0x003a
        L_0x002a:
            java.lang.String r0 = "SAVE_VIEW_UUID"
            java.lang.String r0 = r4.getString(r0)
            r3.f = r0
            java.lang.String[] r0 = com.mibi.common.data.PermissionUtils.f7535a
            boolean r0 = com.mibi.common.data.PermissionUtils.a((android.content.Context) r3, (java.lang.String[]) r0)
            r3.k = r0
        L_0x003a:
            r3.setOrientation()
            android.content.Intent r0 = r3.getIntent()
            java.lang.String r1 = "session"
            android.os.Parcelable r0 = r0.getParcelableExtra(r1)
            com.mibi.common.data.Session$SessionSaveData r0 = (com.mibi.common.data.Session.SessionSaveData) r0
            if (r0 != 0) goto L_0x0052
            java.lang.String r1 = "BaseActivity"
            java.lang.String r2 = "session in intent is null"
            android.util.Log.d(r1, r2)
        L_0x0052:
            r1 = 0
            if (r4 == 0) goto L_0x005e
            java.lang.String r1 = "SAVE_SESSION"
            android.os.Parcelable r4 = r4.getParcelable(r1)
            r1 = r4
            com.mibi.common.data.Session$SessionSaveData r1 = (com.mibi.common.data.Session.SessionSaveData) r1
        L_0x005e:
            com.mibi.common.data.Session r4 = com.mibi.common.data.SessionManager.a((android.content.Context) r3, (com.mibi.common.data.Session.SessionSaveData) r1, (com.mibi.common.data.Session.SessionSaveData) r0)
            r3.mSession = r4
            boolean r4 = com.mibi.common.data.CommonConstants.b
            if (r4 == 0) goto L_0x0080
            java.lang.String r4 = "BaseActivity"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "BaseActivity.onCreate, mSession = "
            r0.append(r1)
            com.mibi.common.data.Session r1 = r3.mSession
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.v(r4, r0)
        L_0x0080:
            android.content.Intent r4 = r3.getIntent()
            r3.handleIntent(r4)
            android.content.res.Resources$Theme r4 = r3.getTheme()
            int[] r0 = com.mibi.common.R.styleable.Mibi_Dialog
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r0)
            int r0 = com.mibi.common.R.styleable.Mibi_Dialog_inDialog
            r1 = 0
            boolean r0 = r4.getBoolean(r0, r1)
            r3.mInDialog = r0
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.base.BaseActivity.doPreCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public final void updateSession(Session session) {
        this.mSession = session;
    }

    /* access modifiers changed from: protected */
    public final void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        IPresenter presenter = getPresenter();
        if (presenter != null) {
            Bundle bundle2 = null;
            if (bundle != null) {
                bundle2 = bundle.getBundle(d);
            }
            presenter.a(this, this.mSession, getIntent().getExtras(), bundle2);
        }
        handleCreate(bundle);
        setUpActionBar();
        this.g.c();
    }

    public final void attachPresenterFactory(IPresenterFactory iPresenterFactory) {
        this.e = iPresenterFactory;
    }

    public final IPresenter getPresenter() {
        IPresenter iPresenter = this.mPresenters.get(this.f);
        if (iPresenter != null) {
            return iPresenter;
        }
        if (this.e != null) {
            iPresenter = this.e.onCreatePresenter();
        }
        if (iPresenter == null) {
            iPresenter = onCreatePresenter();
        }
        if (iPresenter != null) {
            this.mPresenters.put(this.f, iPresenter);
        }
        return iPresenter;
    }

    /* access modifiers changed from: package-private */
    public void setUpActionBar() {
        if (!this.mHasActionBarInitialized) {
            handleActionBar();
        }
        this.mHasActionBarInitialized = true;
        this.mIsActionBarDirty = false;
    }

    /* access modifiers changed from: package-private */
    public void updateActionBar() {
        if (this.mHasActionBarInitialized && this.mIsActionBarDirty) {
            handleActionBar();
        }
        this.mIsActionBarDirty = false;
    }

    /* access modifiers changed from: protected */
    public final void forceUpdateActionBar() {
        if (this.mHasActionBarInitialized) {
            handleActionBar();
        }
        this.mIsActionBarDirty = false;
    }

    /* access modifiers changed from: protected */
    public void handleActionBar() {
        ActionBar miuiActionBar = getMiuiActionBar();
        if (miuiActionBar != null) {
            if (!this.mShowActionBar) {
                miuiActionBar.hide();
                return;
            }
            miuiActionBar.show();
            BaseFragment baseFragment = null;
            if (!this.mFragmentStack.a()) {
                baseFragment = (BaseFragment) this.mFragmentStack.c();
            }
            CharSequence title = getTitle();
            if (baseFragment != null) {
                title = baseFragment.g;
            }
            if (TextUtils.isEmpty(title)) {
                title = getApplicationLabel();
            }
            miuiActionBar.setTitle(title);
            boolean z = this.mShowBack;
            if (baseFragment != null) {
                z = baseFragment.i;
            }
            miuiActionBar.setHomeButtonEnabled(z);
            miuiActionBar.setDisplayHomeAsUpEnabled(z);
            int i2 = 0;
            if (!z) {
                miuiActionBar.setDisplayOptions(0, 7);
            } else {
                miuiActionBar.setDisplayOptions(4, 7);
            }
            int i3 = this.mExtraButtonIconRes;
            View.OnClickListener onClickListener = this.mExtraButtonClickListener;
            if (baseFragment != null) {
                i3 = baseFragment.l;
                onClickListener = baseFragment.n;
            }
            if (i3 != 0) {
                miuiActionBar.setDisplayShowCustomEnabled(true);
                miuiActionBar.setCustomView(R.layout.mibi_custom_action_bar_extra_button);
                Button button = (Button) miuiActionBar.getCustomView().findViewById(R.id.extra);
                View findViewById = miuiActionBar.getCustomView().findViewById(R.id.bubble);
                if (i3 != 0) {
                    button.setBackgroundResource(i3);
                    if (!this.mShowExtraButtonBubble) {
                        i2 = 8;
                    }
                    findViewById.setVisibility(i2);
                }
                button.setOnClickListener(onClickListener);
                return;
            }
            miuiActionBar.setDisplayShowCustomEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public final void setBackButtonText(int i2) {
        setBackButtonText(getString(i2));
    }

    /* access modifiers changed from: protected */
    public final void setBackButtonText(String str) {
        if (!TextUtils.equals(this.mBackButtonText, str)) {
            this.mIsActionBarDirty = true;
        }
        this.mBackButtonText = str;
        updateActionBar();
    }

    /* access modifiers changed from: protected */
    public final void setExtraButton(String str, int i2, View.OnClickListener onClickListener) {
        if (!TextUtils.equals(this.mExtraButtonText, str)) {
            this.mIsActionBarDirty = true;
        }
        if (this.mExtraButtonIconRes != i2) {
            this.mIsActionBarDirty = true;
        }
        if (this.mExtraButtonClickListener != onClickListener) {
            this.mIsActionBarDirty = true;
        }
        this.mExtraButtonText = str;
        this.mExtraButtonIconRes = i2;
        this.mExtraButtonClickListener = onClickListener;
        updateActionBar();
    }

    /* access modifiers changed from: protected */
    public final void showExtraButtonBubble(boolean z) {
        if (this.mExtraButtonIconRes != 0) {
            if (this.mShowExtraButtonBubble != z) {
                this.mIsActionBarDirty = true;
            }
            this.mShowExtraButtonBubble = z;
            updateActionBar();
        }
    }

    /* access modifiers changed from: protected */
    public final void setCanBack(boolean z) {
        if (!(this.mCanBack == z && this.mShowBack == z)) {
            this.mIsActionBarDirty = true;
        }
        this.mCanBack = z;
        this.mShowBack = z;
        updateActionBar();
    }

    /* access modifiers changed from: protected */
    public final boolean canBack() {
        return this.mCanBack;
    }

    /* access modifiers changed from: protected */
    public final void setShowBack(boolean z) {
        if (this.mShowBack != z) {
            this.mIsActionBarDirty = true;
        }
        this.mShowBack = z;
        updateActionBar();
    }

    /* access modifiers changed from: protected */
    public final boolean showBack() {
        return this.mShowBack;
    }

    /* access modifiers changed from: protected */
    public final void setShowActionBar(boolean z) {
        if (this.mShowActionBar != z) {
            this.mIsActionBarDirty = true;
        }
        this.mShowActionBar = z;
        updateActionBar();
    }

    /* access modifiers changed from: protected */
    public final boolean showActionBar() {
        return this.mShowActionBar;
    }

    /* access modifiers changed from: protected */
    public void doSaveInstanceState(Bundle bundle) {
        super.doSaveInstanceState(bundle);
        bundle.putString(b, this.f);
        if (this.mSession != null) {
            bundle.putParcelable(c, SessionManager.a(this.mSession));
        }
        IPresenter presenter = getPresenter();
        if (presenter != null) {
            Bundle bundle2 = new Bundle();
            presenter.a(bundle2);
            bundle.putBundle(d, bundle2);
        }
    }

    public final boolean hasSession() {
        return this.mSession != null;
    }

    public final Session getSession() {
        return this.mSession;
    }

    /* access modifiers changed from: protected */
    public boolean isPaused() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public void doStart() {
        super.doStart();
        if (!this.k) {
            finish();
            return;
        }
        if (!this.h) {
            this.h = true;
            this.g.d();
        }
        IPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.a((IView) this);
        }
    }

    /* access modifiers changed from: protected */
    public void doStop() {
        super.doStop();
        if (this.h) {
            this.h = false;
            if (!Client.c()) {
                this.g.e();
            } else if (isChangingConfigurations()) {
                this.g.f();
            }
        }
        IPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.a();
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        super.doResume();
        if (this.mSession != null) {
            if (this.mSession.b()) {
                finish();
                return;
            } else if (this.mSession.a()) {
                new AlertDialog.Builder(this).setTitle(R.string.mibi_error_account_changed_title).setMessage(R.string.mibi_error_account_changed_summary).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BaseActivity.this.mSession.a(10, BaseActivity.this.getString(R.string.mibi_error_account_changed_summary));
                        BaseActivity.this.finish();
                    }
                }).show().setCancelable(false);
            }
        }
        this.i = false;
    }

    /* access modifiers changed from: protected */
    public void doPause() {
        super.doPause();
        this.i = true;
    }

    /* access modifiers changed from: protected */
    public void setOrientation() {
        if (Utils.b()) {
            setRequestedOrientation(-1);
        }
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        super.doDestroy();
        this.g.g();
        if (!isChangingConfigurations()) {
            for (IPresenter c2 : this.mPresenters.values()) {
                c2.c();
            }
            this.mPresenters.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void doBackPressed() {
        if (this.mCanBack) {
            super.doBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public boolean doOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        doBackPressed();
        return true;
    }

    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        a(intent);
        super.startActivityForResult(intent, i2, bundle);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        a(intent);
        super.startActivityFromFragment(fragment, intent, i2, bundle);
    }

    private void a(Intent intent) {
        if (Utils.a((Context) this, intent) && this.mSession != null) {
            intent.putExtra("session", SessionManager.a(this.mSession));
        }
    }

    public final TaskManager getTaskManager() {
        return this.g;
    }

    public final boolean isInDialog() {
        return this.mInDialog;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getApplicationLabel() {
        if (TextUtils.isEmpty(this.j)) {
            this.j = getApplicationInfo().loadLabel(getPackageManager());
        }
        return this.j;
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i2, int i3, Intent intent) {
        super.doActivityResult(i2, i3, intent);
        if (getPresenter() != null) {
            if (intent == null) {
                intent = new Intent();
            }
            getPresenter().a(i2, i3, intent.getExtras());
        }
    }
}
