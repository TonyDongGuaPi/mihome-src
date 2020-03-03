package com.xiaomi.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.data.MibiSdkConstants;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.PaymentResponse;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Session;
import com.mibi.common.data.SessionManager;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.PaymentActivity;

public class PaymentOrderResponseEntryActivity extends BaseEntryActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12146a = "PayResponseActivity";
    private String b;
    private String c;
    private boolean d;
    private PaymentResponse e;
    private boolean f;
    private boolean g;
    private boolean h;

    /* access modifiers changed from: protected */
    public String getEntryName() {
        return "PaymentOrderEntry";
    }

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        Bundle bundleExtra = getIntent().getBundleExtra(MibiConstants.cS);
        if (bundleExtra == null) {
            Toast.makeText(this, getString(R.string.mibi_illegal_intent), 0).show();
            returnError(1, getString(R.string.mibi_illegal_intent));
            finish();
            return;
        }
        bundleExtra.setClassLoader(getClassLoader());
        this.e = (PaymentResponse) bundleExtra.getParcelable(MibiSdkConstants.F);
        String string = bundleExtra.getString(MibiConstants.cT);
        if (TextUtils.isEmpty(string)) {
            Toast.makeText(this, getString(R.string.mibi_illegal_intent), 0).show();
            returnError(2, getString(R.string.mibi_illegal_intent));
            finish();
            return;
        }
        this.b = string;
        this.d = getIntent().getBundleExtra(MibiConstants.cS).getBoolean(MibiConstants.gK, false);
        this.c = getIntent().getBundleExtra(MibiConstants.cS).getString(MibiConstants.cX, "");
        this.f = getIntent().getBundleExtra(MibiConstants.cS).getBoolean(MibiConstants.eD, true);
        this.g = getIntent().getBundleExtra(MibiConstants.cS).getBoolean(MibiConstants.eE, true);
        this.h = getIntent().getBundleExtra(MibiConstants.cS).getBoolean(MibiConstants.eH, true);
    }

    /* access modifiers changed from: protected */
    public Session buildSession(AccountLoader accountLoader) {
        return SessionManager.a((Context) this, accountLoader, this.e);
    }

    /* access modifiers changed from: protected */
    public void doEntrySuccess() {
        PrivacyManager.a(getSession(), "105", MibiPrivacyUtils.c);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(MibiConstants.gK, this.d);
        intent.putExtra(MibiConstants.cT, this.b);
        intent.putExtra(MibiConstants.cX, this.c);
        intent.putExtra("entry", true);
        intent.putExtra("payment_is_no_account", isNoAccount());
        intent.putExtra(MibiConstants.eD, this.f);
        intent.putExtra(MibiConstants.eE, this.g);
        intent.putExtra(MibiConstants.eG, this.h);
        startActivity(intent);
        MistatisticUtils.a((Activity) this, getEntryName());
        MistatisticUtils.b((Activity) this, getEntryName());
        finish();
    }

    /* access modifiers changed from: protected */
    public void doEntryFailed(int i, String str) {
        returnError(i, str);
        finish();
    }

    /* access modifiers changed from: protected */
    public void returnError(int i, String str) {
        if (this.e != null && this.e.a()) {
            this.e.a(i, str);
        }
    }
}
