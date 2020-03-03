package com.xiaomi.passport.ui.settings;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.settings.SimpleDialogFragment;
import com.xiaomi.passport.ui.settings.utils.AccountSmsVerifyCodeReceiver;

public abstract class AbstractVerifyCodeFragment extends Fragment implements View.OnClickListener {
    private static final long COUNT_DOWN_GET_VERIFY_CODE_TIME = 60000;
    private static final long COUNT_DOWN_READ_SMS_TIME = 4000;
    protected static final String EXTRA_SEND_VCODE_PHONE_NUM = "phone";
    private static final String TAG = "AbstractVerifyCodeFragment";
    /* access modifiers changed from: private */
    public MyCountDownTimer mAutoReadSmsCountDownTimer;
    /* access modifiers changed from: private */
    public MyCountDownTimer mGetVCodeCountDownTimer;
    /* access modifiers changed from: private */
    public TextView mGetVerifyCodeView;
    protected String mPhoneNumber;
    private AccountSmsVerifyCodeReceiver mSmsReceiver;
    protected CheckBox mTrustDeviceCheckBox;
    private Button mVerifyBtn;
    /* access modifiers changed from: private */
    public EditText mVerifyCodeView;
    private long millisCountDownGetVerifyCode = 30000;

    public abstract void doAfterGetVerifyCode(String str, String str2, boolean z);

    public abstract void sendVerifyCode(String str);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        countDownAutoReadSmsTimer();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getVCodeLayout(), viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments == null) {
            AccountLog.i(TAG, "args is null");
            getActivity().finish();
            return inflate;
        }
        this.mPhoneNumber = arguments.getString("phone");
        ((TextView) inflate.findViewById(R.id.sms_send_notice)).setText(String.format(getString(R.string.passport_vcode_sms_send_prompt), new Object[]{this.mPhoneNumber}));
        this.mVerifyCodeView = (EditText) inflate.findViewById(R.id.ev_verify_code);
        this.mGetVerifyCodeView = (TextView) inflate.findViewById(R.id.get_vcode_notice);
        this.mVerifyBtn = (Button) inflate.findViewById(R.id.btn_verify);
        this.mTrustDeviceCheckBox = (CheckBox) inflate.findViewById(R.id.trust_device);
        this.mGetVerifyCodeView.setOnClickListener(this);
        this.mVerifyBtn.setOnClickListener(this);
        countDownGetVerifyCodeTime();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public int getVCodeLayout() {
        return R.layout.passport_input_phone_vcode;
    }

    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        this.mSmsReceiver = new AccountSmsVerifyCodeReceiver(new SmsVerifyCodeListener());
        getActivity().registerReceiver(this.mSmsReceiver, intentFilter);
    }

    public void onPause() {
        if (this.mSmsReceiver != null) {
            getActivity().unregisterReceiver(this.mSmsReceiver);
            this.mSmsReceiver = null;
        }
        cancelCountDownAutoReadSmsTimer();
        cancelCountDownGetVCodeTimer();
        super.onPause();
    }

    public void onClick(View view) {
        if (view == this.mGetVerifyCodeView) {
            sendVerifyCode(this.mPhoneNumber);
        } else if (view == this.mVerifyBtn) {
            onVerifyButtonClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void onVerifyButtonClicked() {
        String obj = this.mVerifyCodeView.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mVerifyCodeView.requestFocus();
            this.mVerifyCodeView.setError(getString(R.string.passport_error_empty_vcode));
            return;
        }
        doAfterGetVerifyCode(this.mPhoneNumber, obj, isTrustDevice());
    }

    /* access modifiers changed from: protected */
    public void showErrorMessageDialog(String str) {
        new AlertDialog.Builder(getActivity()).setMessage(str).setNeutralButton(17039370, (DialogInterface.OnClickListener) null).create().show();
    }

    private void countDownAutoReadSmsTimer() {
        final SimpleDialogFragment create = new SimpleDialogFragment.AlertDialogFragmentBuilder(2).setMessage(getString(R.string.passport_trying_read_verify_code_sms)).setCancelable(false).create();
        create.showAllowingStateLoss(getFragmentManager(), "autoReadSmsProgress");
        this.mAutoReadSmsCountDownTimer = new MyCountDownTimer(COUNT_DOWN_READ_SMS_TIME, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (!(create == null || create.getActivity() == null || create.getActivity().isFinishing())) {
                    create.dismissAllowingStateLoss();
                }
                MyCountDownTimer unused = AbstractVerifyCodeFragment.this.mAutoReadSmsCountDownTimer = null;
            }

            public void cancelCountDown() {
                super.cancelCountDown();
                onFinish();
            }
        };
        this.mAutoReadSmsCountDownTimer.start();
    }

    /* access modifiers changed from: private */
    public void cancelCountDownAutoReadSmsTimer() {
        if (this.mAutoReadSmsCountDownTimer != null) {
            this.mAutoReadSmsCountDownTimer.cancelCountDown();
            this.mAutoReadSmsCountDownTimer = null;
        }
    }

    /* access modifiers changed from: protected */
    public void countDownGetVerifyCodeTime() {
        this.mGetVerifyCodeView.setEnabled(false);
        this.millisCountDownGetVerifyCode *= 2;
        this.mGetVCodeCountDownTimer = new MyCountDownTimer(this.millisCountDownGetVerifyCode, 1000) {
            public void onTick(long j) {
                TextView access$200 = AbstractVerifyCodeFragment.this.mGetVerifyCodeView;
                access$200.setText(AbstractVerifyCodeFragment.this.getString(R.string.passport_getting_verify_code) + " (" + (j / 1000) + Operators.BRACKET_END_STR);
            }

            public void onFinish() {
                AbstractVerifyCodeFragment.this.mGetVerifyCodeView.setText(AbstractVerifyCodeFragment.this.getString(R.string.passport_re_get_verify_code));
                AbstractVerifyCodeFragment.this.mGetVerifyCodeView.setEnabled(true);
                MyCountDownTimer unused = AbstractVerifyCodeFragment.this.mGetVCodeCountDownTimer = null;
            }

            public void cancelCountDown() {
                super.cancelCountDown();
                MyCountDownTimer unused = AbstractVerifyCodeFragment.this.mGetVCodeCountDownTimer = null;
            }
        };
        this.mGetVCodeCountDownTimer.start();
    }

    private void cancelCountDownGetVCodeTimer() {
        if (this.mGetVCodeCountDownTimer != null) {
            this.mGetVCodeCountDownTimer.cancelCountDown();
            this.mGetVCodeCountDownTimer = null;
        }
    }

    private class SmsVerifyCodeListener implements AccountSmsVerifyCodeReceiver.SmsVerifyCodeMessageListener {
        private SmsVerifyCodeListener() {
        }

        public void onReceived(String str, String str2) {
            if (TextUtils.isEmpty(AbstractVerifyCodeFragment.this.mVerifyCodeView.getText().toString())) {
                AbstractVerifyCodeFragment.this.doAfterGetVerifyCode(AbstractVerifyCodeFragment.this.mPhoneNumber, str2, AbstractVerifyCodeFragment.this.isTrustDevice());
            }
            AbstractVerifyCodeFragment.this.cancelCountDownAutoReadSmsTimer();
        }
    }

    /* access modifiers changed from: private */
    public boolean isTrustDevice() {
        if (this.mTrustDeviceCheckBox != null) {
            return this.mTrustDeviceCheckBox.isChecked();
        }
        return false;
    }

    private abstract class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long j, long j2) {
            super(j, j2);
        }

        public void cancelCountDown() {
            cancel();
        }
    }
}
