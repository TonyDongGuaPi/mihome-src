package com.xiaomi.payment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.PrivacyManager;
import com.xiaomi.payment.data.EntryResultUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.platform.R;

public class CommonEntryActivity extends BaseEntryActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12142a = "CommonEntryActivity";
    private static final int b = 2;
    private Bundle c;
    protected String mEntryId;

    /* access modifiers changed from: protected */
    public String getEntryName() {
        return "CommonEntry";
    }

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        this.c = getIntent().getBundleExtra("payment_fragment_arguments");
        if (this.c == null) {
            this.c = new Bundle();
        }
        getIntent().putExtra(MibiConstants.cS, this.c);
        if (!parseIntent(getIntent())) {
            Log.e(f12142a, "enter failed parse intent failed");
            returnError(2, "enter failed parse intent failed");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void doEntrySuccess() {
        PrivacyManager.a(getSession(), "105", MibiPrivacyUtils.e);
        a();
    }

    /* access modifiers changed from: protected */
    public void doEntryFailed(int i, String str) {
        returnError(i, str);
        finish();
    }

    /* access modifiers changed from: protected */
    public boolean parseIntent(Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return false;
        }
        if ((!TextUtils.equals(data.getScheme(), "https") || !TextUtils.equals(data.getHost(), "publish.app.mibi.xiaomi.com")) && (!TextUtils.equals(data.getScheme(), MibiConstants.gl) || !TextUtils.equals(data.getHost(), "mibiapp"))) {
            return false;
        }
        Log.d(f12142a, "entry uri = " + data.toString());
        this.mEntryId = data.getQueryParameter("id");
        if (TextUtils.isEmpty(this.mEntryId)) {
            return false;
        }
        for (String next : data.getQueryParameterNames()) {
            if (!TextUtils.isEmpty(next)) {
                String queryParameter = data.getQueryParameter(next);
                if ("false".equalsIgnoreCase(queryParameter) || "true".equalsIgnoreCase(queryParameter)) {
                    this.c.putBoolean(next, Boolean.valueOf(queryParameter).booleanValue());
                } else {
                    this.c.putString(next, queryParameter);
                }
            }
        }
        return true;
    }

    private void a() {
        if (this.c != null) {
            this.c.putBoolean("entry", true);
            boolean a2 = EntryManager.a().a(this.mEntryId, (Activity) this, this.c, 2);
            MistatisticUtils.a((Activity) this, getEntryName());
            MistatisticUtils.b((Activity) this, getEntryName());
            if (!a2) {
                Log.e(f12142a, "enter failed id:" + this.mEntryId);
                Toast.makeText(this, getString(R.string.mibi_enter_failed), 0).show();
                returnError(2, "enter failed parse intent failed");
                finish();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("CommonEntryActivity mBundle should not be null");
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
        Log.d(f12142a, "CommonEntryActivity requestCode = " + i + " resultCode = " + i2);
        super.doActivityResult(i, i2, intent);
        if (i == 2) {
            Intent intent2 = new Intent();
            int i3 = 0;
            if (intent != null) {
                i3 = intent.getExtras().getInt("code");
                intent2 = intent;
            }
            intent2.putExtra("code", i2);
            a(i3, intent2, EntryManager.a().a(this.mEntryId));
            finish();
        }
    }

    private final void a(int i, Intent intent, boolean z) {
        if (z) {
            if (intent == null) {
                intent = new Intent();
            }
            if (i == -1) {
                a(intent.getExtras());
            } else {
                returnError(intent.getIntExtra("errcode", 0), intent.getStringExtra("errDesc"), intent.getExtras());
            }
        } else if (i == -1) {
            a((Bundle) null);
        } else {
            if (intent == null) {
                intent = new Intent();
            }
            returnError(intent.getIntExtra("errcode", 0), intent.getStringExtra("errDesc"), (Bundle) null);
        }
    }

    private void a(Bundle bundle) {
        setResult(-1, EntryResultUtils.a(-1, "success", bundle));
    }
}
