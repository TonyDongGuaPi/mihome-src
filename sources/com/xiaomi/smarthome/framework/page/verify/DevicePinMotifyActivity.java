package com.xiaomi.smarthome.framework.page.verify;

import android.content.Intent;
import android.text.TextUtils;

public abstract class DevicePinMotifyActivity extends DevicePinActivity {
    protected String mFirstPinCode;

    /* access modifiers changed from: protected */
    public abstract void motifyPin(String str);

    /* access modifiers changed from: protected */
    public void onPinCodeInputFinish(String str) {
        if (TextUtils.isEmpty(this.mFirstPinCode)) {
            this.mFirstPinCode = str;
            this.vDesc.setText(this.mDesc2);
            this.vPinInputView.reset();
        } else if (str.equals(this.mFirstPinCode)) {
            showLoadingDialog();
            motifyPin(str);
        } else {
            this.vPinInputView.showError();
            this.vDesc.setText(this.mErrorMsg);
        }
    }

    /* access modifiers changed from: protected */
    public void onRetryClick() {
        motifyPin(this.mFirstPinCode);
    }

    public void onBackClick() {
        if (TextUtils.isEmpty(this.mFirstPinCode)) {
            super.onBackClick();
            return;
        }
        this.vPinInputView.reset();
        this.vDesc.setText(this.mDesc);
        this.mFirstPinCode = "";
    }

    /* access modifiers changed from: protected */
    public void onMotifySuccess() {
        hideLoadingDialog();
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onMotifyFail(String str) {
        hideLoadingDialog();
        Intent intent = new Intent();
        intent.putExtra(DevicePinActivity.RESULT_FAIL_INTENT_ERRORMSG, str);
        setResult(-10, intent);
        finish();
    }
}
