package com.xiaomi.smarthome.frame.login.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.ui.view.LoginBaseTitleBar;
import com.xiaomi.smarthome.frame.login.ui.view.VerifyCodeInputView;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractVerifyCodeActivity extends LoginBaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private Pattern f16187a = Pattern.compile("^(\\d{" + getVerifyCodeBit() + "})$");
    private ClipboardManager.OnPrimaryClipChangedListener b = new ClipboardManager.OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {
            AbstractVerifyCodeActivity.this.a();
        }
    };
    protected ClipboardManager clipboardManager;
    protected CountDownTimer mCountDownTimer = new CountDownTimer(60000, 1000) {
        public void onTick(long j) {
            AbstractVerifyCodeActivity.this.vCountDown.setText(AbstractVerifyCodeActivity.this.getString(R.string.login_verify_code_second, new Object[]{Long.valueOf(j / 1000)}));
        }

        public void onFinish() {
            AbstractVerifyCodeActivity.this.vCountDown.setText(R.string.login_verify_code_again);
            AbstractVerifyCodeActivity.this.vCountDown.setEnabled(true);
        }
    };
    protected TextView vCopy;
    protected TextView vCountDown;
    protected TextView vErrorInfo;
    protected TextView vSendInfo;
    protected LoginBaseTitleBar vTitleBar;
    protected VerifyCodeInputView vVerifyCodeInputView;

    /* access modifiers changed from: protected */
    public abstract void checkVerifyCode(String str);

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.login_act_verify_code;
    }

    /* access modifiers changed from: protected */
    public abstract String getTitleText();

    /* access modifiers changed from: protected */
    public abstract int getVerifyCodeBit();

    /* access modifiers changed from: protected */
    public abstract void onSendSmsVerifyCode();

    /* access modifiers changed from: private */
    public void a() {
        ClipData primaryClip = this.clipboardManager.getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() == 0) {
            b();
            return;
        }
        ClipData.Item itemAt = primaryClip.getItemAt(0);
        if (itemAt == null) {
            b();
            return;
        }
        CharSequence text = itemAt.getText();
        if (TextUtils.isEmpty(text)) {
            b();
            return;
        }
        Matcher matcher = this.f16187a.matcher(text);
        if (matcher.find()) {
            final String group = matcher.group(0);
            this.vCopy.setVisibility(0);
            this.vCopy.setText(getString(R.string.login_verify_code_copy, new Object[]{group}));
            this.vCopy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AbstractVerifyCodeActivity.this.vVerifyCodeInputView.setCode(group);
                }
            });
            return;
        }
        b();
    }

    private void b() {
        this.vCopy.setText("");
        this.vCopy.setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.vTitleBar.setCenterText(getTitleText());
        this.vSendInfo = (TextView) findViewById(R.id.login_verify_code_send_info);
        this.vVerifyCodeInputView = (VerifyCodeInputView) findViewById(R.id.login_verify_code_input);
        this.vErrorInfo = (TextView) findViewById(R.id.login_verify_code_input_error);
        this.vCountDown = (TextView) findViewById(R.id.login_verify_code_count_down);
        this.vCopy = (TextView) findViewById(R.id.login_verify_code_copy);
        this.clipboardManager = (ClipboardManager) getSystemService(ShareChannel.d);
        this.vCountDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!NetworkUtils.a()) {
                    ToastManager.a().a((int) R.string.login_verify_code_network_unavailable);
                    return;
                }
                AbstractVerifyCodeActivity.this.vErrorInfo.setVisibility(4);
                AbstractVerifyCodeActivity.this.vCopy.setVisibility(4);
                AbstractVerifyCodeActivity.this.vLoadingDialog.setMessage(AbstractVerifyCodeActivity.this.getString(R.string.login_passport_login_waiting));
                AbstractVerifyCodeActivity.this.vLoadingDialog.show();
                AbstractVerifyCodeActivity.this.mCountDownTimer.start();
                AbstractVerifyCodeActivity.this.vVerifyCodeInputView.reset();
                AbstractVerifyCodeActivity.this.onSendSmsVerifyCode();
            }
        });
        this.mCountDownTimer.start();
        this.vCountDown.setEnabled(false);
        this.vVerifyCodeInputView.setOnCodeInputFinishListener(new VerifyCodeInputView.OnCodeInputFinishListener() {
            public void a(String str) {
                if (!NetworkUtils.a()) {
                    ToastManager.a().a((int) R.string.login_network_not_available);
                    return;
                }
                AbstractVerifyCodeActivity.this.vLoadingDialog.setMessage(AbstractVerifyCodeActivity.this.getString(R.string.login_verify_code_checking));
                AbstractVerifyCodeActivity.this.vLoadingDialog.show();
                AbstractVerifyCodeActivity.this.checkVerifyCode(str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.clipboardManager.addPrimaryClipChangedListener(this.b);
        a();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.clipboardManager.removePrimaryClipChangedListener(this.b);
        super.onStop();
    }
}
