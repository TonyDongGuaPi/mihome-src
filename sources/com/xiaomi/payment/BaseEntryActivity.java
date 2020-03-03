package com.xiaomi.payment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.alipay.sdk.packet.e;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.base.DecoratableActivity;
import com.mibi.common.data.Client;
import com.mibi.common.data.Session;
import com.mibi.common.data.SessionManager;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.EntryResultUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.ui.BaseEntryHandler;
import com.xiaomi.payment.ui.EntryResultCallback;

public abstract class BaseEntryActivity extends DecoratableActivity implements EntryResultCallback {

    /* renamed from: a  reason: collision with root package name */
    private final String f12141a = "BaseEntryActivity";
    private BaseEntryHandler b;
    protected Session mSession;

    /* access modifiers changed from: protected */
    public abstract void doEntryFailed(int i, String str);

    /* access modifiers changed from: protected */
    public abstract void doEntrySuccess();

    /* access modifiers changed from: protected */
    public abstract String getEntryName();

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        if (this.b == null) {
            this.b = new BaseEntryHandler(this, this);
        }
        if (bundle != null) {
            this.mSession = SessionManager.a((Context) this, (Session.SessionSaveData) bundle.getParcelable("session"), (Session.SessionSaveData) null);
        }
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        if (bundle == null) {
            a(bundle, isNoAccount());
        }
    }

    private void a(Bundle bundle, boolean z) {
        this.b.a(bundle, z);
    }

    /* access modifiers changed from: protected */
    public boolean isNoAccount() {
        Bundle bundleExtra = getIntent().getBundleExtra(MibiConstants.cS);
        if (bundleExtra == null) {
            return false;
        }
        return bundleExtra.getBoolean("payment_is_no_account", false);
    }

    /* access modifiers changed from: protected */
    public void doSaveInstanceState(Bundle bundle) {
        super.doSaveInstanceState(bundle);
        if (this.mSession != null) {
            bundle.putParcelable("session", SessionManager.a(this.mSession));
        }
    }

    public Session getSession() {
        return this.mSession;
    }

    /* access modifiers changed from: protected */
    public Session buildSession(AccountLoader accountLoader) {
        return SessionManager.a(this, accountLoader);
    }

    public final void onEntrySuccess(AccountLoader accountLoader) {
        this.mSession = buildSession(accountLoader);
        Log.d(e.e, "versionCode:" + String.valueOf(Client.F().b()) + ", " + "versionName:" + Client.F().a());
        doEntrySuccess();
    }

    public final void onEntryFailed(int i, String str) {
        doEntryFailed(i, str);
    }

    /* access modifiers changed from: protected */
    public void returnError(int i, String str) {
        returnError(i, str, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void returnError(int i, String str, Bundle bundle) {
        setResult(i, EntryResultUtils.a(i, str, bundle));
    }

    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (Utils.a((Context) this, intent) && this.mSession != null) {
            intent.putExtra("session", SessionManager.a(this.mSession));
        }
        super.startActivityForResult(intent, i, bundle);
    }

    public final void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (Utils.a((Context) this, intent) && this.mSession != null) {
            intent.putExtra("session", SessionManager.a(this.mSession));
        }
        super.startActivityFromFragment(fragment, intent, i, bundle);
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
        super.doActivityResult(i, i2, intent);
        this.b.a(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.b.a(i, strArr, iArr);
    }
}
