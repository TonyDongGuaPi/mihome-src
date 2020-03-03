package com.xiaomi.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.PaymentResponse;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Session;
import com.mibi.common.data.SessionManager;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.PaymentActivity;

public class PaymentOrderEntryActivity extends BaseEntryActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12145a = "PayEntryActivity";
    private static final int b = 1;
    private String c;
    private boolean d;

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
            a(1, getString(R.string.mibi_illegal_intent));
            finish();
            return;
        }
        String string = bundleExtra.getString(MibiConstants.cT);
        if (TextUtils.isEmpty(string)) {
            Toast.makeText(this, getString(R.string.mibi_illegal_intent), 0).show();
            a(2, getString(R.string.mibi_illegal_intent));
            finish();
            return;
        }
        this.c = string;
        this.d = getIntent().getBundleExtra(MibiConstants.cS).getBoolean(MibiConstants.gK, false);
    }

    /* access modifiers changed from: protected */
    public Session buildSession(AccountLoader accountLoader) {
        return SessionManager.a((Context) this, accountLoader, (PaymentResponse) null);
    }

    /* access modifiers changed from: protected */
    public void doEntrySuccess() {
        PrivacyManager.a(getSession(), "105", MibiPrivacyUtils.c);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(MibiConstants.gK, this.d);
        intent.putExtra(MibiConstants.cT, this.c);
        intent.putExtra("entry", true);
        intent.putExtra("payment_is_no_account", isNoAccount());
        startActivityForResult(intent, 1, (Bundle) null);
        MistatisticUtils.a((Activity) this, getEntryName());
        MistatisticUtils.b((Activity) this, getEntryName());
    }

    /* access modifiers changed from: protected */
    public void doEntryFailed(int i, String str) {
        a(i, str);
        finish();
    }

    private void a(int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("code", i);
        intent.putExtra("message", str);
        setResult(i, intent);
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
        super.doActivityResult(i, i2, intent);
        Log.v(f12145a, this + ".doActivityResult, requestCode" + i + "  and resultCode = " + i2);
        if (i == 1) {
            setResult(i2, intent);
            finish();
        }
    }
}
