package com.xiaomi.passport.ui.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import com.xiaomi.accountsdk.account.data.MiuiActivatorInfo;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.settings.BindPhoneActivity;

public class InputBindedVerifyCodeFragment extends AbstractVerifyCodeFragment {
    private static final String TAG = "InputBindedVerifyCodeFr";
    /* access modifiers changed from: private */
    public CaptchaView mCaptchaView;

    public static InputBindedVerifyCodeFragment getInstance(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("phone", str);
        InputBindedVerifyCodeFragment inputBindedVerifyCodeFragment = new InputBindedVerifyCodeFragment();
        inputBindedVerifyCodeFragment.setArguments(bundle);
        return inputBindedVerifyCodeFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (getArguments() == null) {
            AccountLog.i(TAG, "args is null");
            getActivity().finish();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTrustDeviceCheckBox.setVisibility(8);
        this.mCaptchaView = (CaptchaView) view.findViewById(R.id.captcha_layout);
    }

    public void doAfterGetVerifyCode(String str, String str2, boolean z) {
        modifySafePhone(str, str2);
    }

    public void sendVerifyCode(String str) {
        sendModifyPhoneTicket(str);
    }

    private void modifySafePhone(String str, String str2) {
        ((BindPhoneActivity) getActivity()).modifySafePhone(str, (MiuiActivatorInfo) null, str2, new BindPhoneActivity.ModifyPhoneCallback() {
            public void onNeedSMSCode(String str) {
                InputBindedVerifyCodeFragment.this.sendModifyPhoneTicket(str);
            }

            public void onError(int i) {
                InputBindedVerifyCodeFragment.this.showErrorMessageDialog(InputBindedVerifyCodeFragment.this.getString(i));
            }

            public void onSuccess() {
                AccountLog.i(InputBindedVerifyCodeFragment.TAG, "modify phone success");
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendModifyPhoneTicket(String str) {
        String str2;
        BindPhoneActivity bindPhoneActivity = (BindPhoneActivity) getActivity();
        if (this.mCaptchaView.getVisibility() == 0) {
            str2 = this.mCaptchaView.getCaptchaCode();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
        } else {
            str2 = null;
        }
        bindPhoneActivity.sendModifySafePhoneTicket(str, str2, this.mCaptchaView.getCaptchaIck(), new BindPhoneActivity.SendTicketCallback() {
            public void onNeedCaptchaCode(String str) {
                if (InputBindedVerifyCodeFragment.this.mCaptchaView.getVisibility() == 0) {
                    InputBindedVerifyCodeFragment.this.showErrorMessageDialog(InputBindedVerifyCodeFragment.this.getString(R.string.passport_wrong_captcha));
                }
                InputBindedVerifyCodeFragment.this.mCaptchaView.setVisibility(0);
                InputBindedVerifyCodeFragment.this.mCaptchaView.downloadCaptcha(str, Constants.URL_ANTI_SPAM_GET_VOICE_CAPTCHA_CODE);
            }

            public void onError(int i) {
                InputBindedVerifyCodeFragment.this.showErrorMessageDialog(InputBindedVerifyCodeFragment.this.getString(i));
            }

            public void onSuccess() {
                InputBindedVerifyCodeFragment.this.countDownGetVerifyCodeTime();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackKeyPressed();
        return true;
    }

    private void onBackKeyPressed() {
        int i = R.string.restart_phone_bind_title;
        int i2 = R.string.restart_phone_bind_message;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(i);
        builder.setMessage(i2);
        builder.setPositiveButton(R.string.restart_action, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                InputBindedVerifyCodeFragment.this.getActivity().finish();
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }
}
