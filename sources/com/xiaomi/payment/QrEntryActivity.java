package com.xiaomi.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.MistatisticUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.ui.PaymentActivity;
import java.util.regex.Pattern;

public class QrEntryActivity extends BaseEntryActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12148a = "QrEntryActivity";
    private static final String b = "https://([a-zA-Z]+\\.)?m\\.mibi\\.mi\\.com/qrpay/[0-9a-zA-Z]{20}";
    private static final String c = "http://(sandbox|staging)\\.m\\.mibi\\.xiaomi\\.com/qrpay/[0-9a-zA-Z]{20}";

    /* access modifiers changed from: protected */
    public String getEntryName() {
        return "QrEntry";
    }

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        if (!a()) {
            Log.e(f12148a, "qr url is not matched, finish. qrUrl = " + getIntent().getDataString());
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void doEntrySuccess() {
        b();
        finish();
    }

    /* access modifiers changed from: protected */
    public void doEntryFailed(int i, String str) {
        finish();
    }

    private boolean a() {
        String dataString = getIntent().getDataString();
        return !TextUtils.isEmpty(dataString) && (Pattern.matches(b, dataString) || Pattern.matches(c, dataString));
    }

    private void b() {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(MibiConstants.f12225dk, true);
        intent.putExtra("url", getIntent().getDataString());
        intent.putExtra("entry", true);
        startActivity(intent);
        MistatisticUtils.a((Activity) this, getEntryName());
        MistatisticUtils.b((Activity) this, getEntryName());
    }
}
