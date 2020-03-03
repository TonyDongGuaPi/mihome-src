package com.mipay.ucashier.ui;

import android.os.Bundle;
import com.mipay.common.base.c;
import com.mipay.ucashier.component.ProgressDialogFragment;

public class BaseUCashierFragment extends c {
    public static int RESULT_CANCELED = 0;
    public static int RESULT_ERROR = 1;
    public static int RESULT_OK = -1;
    private ProgressDialogFragment g;

    /* access modifiers changed from: protected */
    public void a(int i) {
        c(getString(i));
    }

    /* access modifiers changed from: protected */
    public void a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("errcode", i);
        bundle.putString("errDesc", str);
        setResult(RESULT_ERROR, bundle);
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        a(2, str);
    }

    /* access modifiers changed from: protected */
    public void c(String str) {
        if (this.g == null) {
            this.g = new ProgressDialogFragment.ProgressDialogFragmentBuilder().setMessage(str).setCancelable(false).create();
        }
        if (!this.g.isShowing()) {
            this.g.show(getFragmentManager(), "dialog");
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        setResult(RESULT_CANCELED, (Bundle) null);
    }

    public void doBackPressed() {
        b("user canceled");
        finish();
    }

    public void doFragmentResult(int i, int i2, Bundle bundle) {
        super.doFragmentResult(i, i2, bundle);
        if (i2 == RESULT_ERROR) {
            setResult(RESULT_ERROR, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.g != null) {
            this.g.dismissAllowingStateLoss();
            this.g = null;
        }
    }
}
