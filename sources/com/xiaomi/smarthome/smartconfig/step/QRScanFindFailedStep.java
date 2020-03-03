package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.ResetDevicePageScan;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class QRScanFindFailedStep extends SmartConfigStep {
    @BindView(2131429563)
    TextView mHelpTitle;
    @BindView(2131432516)
    ImageView mIcon;
    @BindView(2131430408)
    TextView mLeftBtn;
    @BindView(2131432518)
    TextView mMainTitle;
    @BindView(2131431897)
    TextView mRightBtn;
    @BindView(2131432517)
    TextView mSubMainTitle;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_scan_qr_find_failed_ui);
        this.mIcon.setImageResource(R.drawable.config_failed_timeout);
        this.mMainTitle.setText(R.string.smart_config_faile_qr_find);
        this.mSubMainTitle.setText(R.string.smart_config_find_new_device_error);
        this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRScanFindFailedStep.this.d_(false);
            }
        });
        this.mRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRScanFindFailedStep.this.D();
            }
        });
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(this.af.getString(R.string.smart_config_scan_qr_help));
        valueOf.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(QRScanFindFailedStep.this.af.getResources().getColor(R.color.black_30_transparent));
                textPaint.setUnderlineText(true);
            }

            public void onClick(View view) {
                Intent intent = new Intent(QRScanFindFailedStep.this.af, ResetDevicePageScan.class);
                intent.putExtra("model", (String) SmartConfigDataProvider.a().a("device_model"));
                QRScanFindFailedStep.this.af.startActivity(intent);
                QRScanFindFailedStep.this.d_(true);
            }
        }, 0, valueOf.length(), 33);
        this.mHelpTitle.setHighlightColor(0);
        this.mHelpTitle.setText(valueOf);
        this.mHelpTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.mHelpTitle.setMovementMethod(LinkMovementMethod.getInstance());
        StatHelper.A();
    }
}
