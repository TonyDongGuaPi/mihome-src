package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.settings.CaptchaDialogController;
import com.xiaomi.passport.ui.settings.SendEmailActivateMessageTask;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;

public class UnactivatedEmailFragment extends Fragment implements View.OnClickListener {
    private static final String EXTRA_EMAIL_ADDRESS = "extra_email_address";
    private static final String TAG = "UnactivatedEmailFragmen";
    private TextView mActivateNoticeView;
    /* access modifiers changed from: private */
    public CaptchaDialogController mCaptchaDialogController;
    /* access modifiers changed from: private */
    public String mEmailAddress;
    private TextView mEmailAddressView;
    /* access modifiers changed from: private */
    public ResendActivateEmailTask mResendActivateEmailTask;
    private Button mResendEmailBtn;
    private Button mVerifiedEmailBtn;

    public static UnactivatedEmailFragment getUnactivatedEmailFragment(String str) {
        UnactivatedEmailFragment unactivatedEmailFragment = new UnactivatedEmailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_EMAIL_ADDRESS, str);
        unactivatedEmailFragment.setArguments(bundle);
        return unactivatedEmailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null || arguments.getString(EXTRA_EMAIL_ADDRESS) == null) {
            getActivity().finish();
            return;
        }
        this.mEmailAddress = arguments.getString(EXTRA_EMAIL_ADDRESS);
        this.mCaptchaDialogController = new CaptchaDialogController(getActivity(), new CaptchaDialogController.CaptchaInterface() {
            public void onCaptchaFinished() {
            }

            public void onCaptchaRequired() {
            }

            public void tryCaptcha(String str, String str2) {
                UnactivatedEmailFragment.this.resendActivateEmail(str, str2);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.unactivated_bind_email, viewGroup, false);
        this.mEmailAddressView = (TextView) inflate.findViewById(R.id.email_address);
        this.mEmailAddressView.setText(this.mEmailAddress);
        this.mActivateNoticeView = (TextView) inflate.findViewById(R.id.activate_email_notice);
        this.mResendEmailBtn = (Button) inflate.findViewById(R.id.resend_email_btn);
        this.mVerifiedEmailBtn = (Button) inflate.findViewById(R.id.verified_email_btn);
        this.mResendEmailBtn.setOnClickListener(this);
        this.mVerifiedEmailBtn.setOnClickListener(this);
        setActivateNotice();
        return inflate;
    }

    private void setActivateNotice() {
        String str = getString(R.string.activate_email_notice) + " ";
        String string = getString(R.string.change_activate_email);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str).append(string);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                UnactivatedEmailFragment.this.getActivity().setResult(9999);
                UnactivatedEmailFragment.this.getActivity().finish();
            }
        }, str.length(), spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(36), str.length(), spannableStringBuilder.length(), 33);
        this.mActivateNoticeView.setText(spannableStringBuilder);
        this.mActivateNoticeView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void onClick(View view) {
        if (view == this.mResendEmailBtn) {
            resendActivateEmail((String) null, (String) null);
        } else if (view == this.mVerifiedEmailBtn) {
            getActivity().finish();
        }
    }

    public void onDestroy() {
        if (this.mResendActivateEmailTask != null) {
            this.mResendActivateEmailTask.cancel(true);
            this.mResendActivateEmailTask = null;
        }
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void resendActivateEmail(String str, String str2) {
        if (this.mResendActivateEmailTask == null) {
            Account xiaomiAccount = AuthenticatorUtil.getXiaomiAccount(getActivity());
            if (xiaomiAccount == null) {
                AccountLog.w(TAG, "no xiaomi account");
                getActivity().finish();
            }
            this.mResendActivateEmailTask = new ResendActivateEmailTask(getActivity(), this.mEmailAddress, AccountManager.get(getActivity()).getUserData(xiaomiAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN), str, str2);
            this.mResendActivateEmailTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
        }
    }

    private class ResendActivateEmailTask extends SendEmailActivateMessageTask {
        public ResendActivateEmailTask(Context context, String str, String str2, String str3, String str4) {
            super(context, str, str2, str3, str4);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(SendEmailActivateMessageTask.EmailTaskResult emailTaskResult) {
            int i;
            ResendActivateEmailTask unused = UnactivatedEmailFragment.this.mResendActivateEmailTask = null;
            if (emailTaskResult != null) {
                if (!TextUtils.isEmpty(emailTaskResult.captchaPath)) {
                    CaptchaDialogController access$200 = UnactivatedEmailFragment.this.mCaptchaDialogController;
                    access$200.triggerNewCaptchaTask(URLs.ACCOUNT_DOMAIN + emailTaskResult.captchaPath, Constants.URL_ANTI_SPAM_GET_VOICE_CAPTCHA_CODE);
                    return;
                }
                UnactivatedEmailFragment.this.mCaptchaDialogController.dismiss();
                if (emailTaskResult.exceptionType == 13) {
                    UnactivatedEmailFragment.this.showSendEmailReachLimitDialog();
                    return;
                }
                AsyncTaskResult asyncTaskResult = new AsyncTaskResult(emailTaskResult.exceptionType);
                if (asyncTaskResult.hasException()) {
                    i = asyncTaskResult.getExceptionReason();
                } else {
                    i = R.string.resend_email_success;
                    UnactivatedEmailFragment.this.saveUnactivatedEmailAddressAndTimeStamp(UnactivatedEmailFragment.this.mEmailAddress, Long.valueOf(System.currentTimeMillis()));
                }
                Toast.makeText(UnactivatedEmailFragment.this.getActivity(), i, 1).show();
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(SendEmailActivateMessageTask.EmailTaskResult emailTaskResult) {
            super.onCancelled(emailTaskResult);
            ResendActivateEmailTask unused = UnactivatedEmailFragment.this.mResendActivateEmailTask = null;
        }
    }

    /* access modifiers changed from: private */
    public void saveUnactivatedEmailAddressAndTimeStamp(String str, Long l) {
        Account xiaomiAccount = AuthenticatorUtil.getXiaomiAccount(getActivity());
        if (xiaomiAccount == null) {
            AccountLog.w(TAG, "no xiaomi account");
            getActivity().finish();
            return;
        }
        SharedPreferences.Editor edit = getActivity().getSharedPreferences(xiaomiAccount.name, 0).edit();
        edit.putString(Constants.SP_UNACTIVATED_EMAIL_ADDRESS, str);
        edit.putLong(Constants.SP_UNACTIVATED_EMAIL_TIME_STAMP, l.longValue());
        edit.commit();
    }

    /* access modifiers changed from: private */
    public void showSendEmailReachLimitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.resend_email_reach_limit_title);
        builder.setMessage(R.string.resend_email_reach_limit_message);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }
}
