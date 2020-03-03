package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.TimePicker;

public class InterruptPushTimerSettingActiviy extends BaseActivity implements View.OnClickListener {
    public static final int CODE_TIMER = 200;
    public static final String FROM_HOUR = "from_hour";
    public static final String FROM_MIN = "from_min";
    public static final String TO_HOUR = "to_hour";
    public static final String TO_MIN = "to_min";
    @BindView(2131430983)
    TextView mConfirmView;
    @BindView(2131429076)
    TextView mExecuteFrom;
    @BindView(2131429078)
    TimePicker mExecuteFromPicker;
    @BindView(2131429080)
    TextView mExecuteTo;
    @BindView(2131429082)
    TimePicker mExecuteToPicker;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_interrupt_push);
        ButterKnife.bind((Activity) this);
        this.mConfirmView.setVisibility(0);
        this.mConfirmView.setText(R.string.ok_button);
        this.mExecuteFromPicker.setIs24HourView(true);
        this.mExecuteToPicker.setIs24HourView(true);
        if (getIntent() != null) {
            Intent intent = getIntent();
            this.mExecuteFromPicker.setCurrentHour(Integer.valueOf(intent.getIntExtra(FROM_HOUR, 0)));
            this.mExecuteFromPicker.setCurrentMinute(Integer.valueOf(intent.getIntExtra(FROM_MIN, 0)));
            this.mExecuteToPicker.setCurrentHour(Integer.valueOf(intent.getIntExtra(TO_HOUR, 0)));
            this.mExecuteToPicker.setCurrentMinute(Integer.valueOf(intent.getIntExtra(TO_MIN, 0)));
            a();
        }
        this.mExecuteFromPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                InterruptPushTimerSettingActiviy.this.a();
            }
        });
        this.mExecuteToPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                InterruptPushTimerSettingActiviy.this.a();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        this.mExecuteFrom.setText(formatTime(intValue, intValue2));
        if (intValue3 < intValue) {
            TextView textView = this.mExecuteTo;
            textView.setText(Operators.BRACKET_START_STR + getResources().getString(R.string.scene_exetime_second_day) + Operators.BRACKET_END_STR + formatTime(intValue3, intValue4));
            return;
        }
        this.mExecuteTo.setText(formatTime(intValue3, intValue4));
    }

    public static String formatTime(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        if (i < 0 || i >= 10) {
            sb.append(i);
        } else {
            sb.append("0" + i);
        }
        sb.append(":");
        if (i2 < 0 || i2 >= 10) {
            sb.append(i2);
        } else {
            sb.append("0" + i2);
        }
        return sb.toString();
    }

    @OnClick({2131430969, 2131430983, 2131429077, 2131429081})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.execute_from_layout) {
            if (this.mExecuteFromPicker.getVisibility() == 0) {
                this.mExecuteFromPicker.setVisibility(8);
            } else {
                this.mExecuteFromPicker.setVisibility(0);
            }
            this.mExecuteToPicker.setVisibility(8);
        } else if (id == R.id.execute_to_layout) {
            if (this.mExecuteToPicker.getVisibility() == 0) {
                this.mExecuteToPicker.setVisibility(8);
            } else {
                this.mExecuteToPicker.setVisibility(0);
            }
            this.mExecuteFromPicker.setVisibility(8);
        } else if (id == R.id.module_a_3_return_btn) {
            setResult(0);
            finish();
        } else if (id == R.id.module_a_3_right_text_btn) {
            Intent intent = new Intent();
            intent.putExtra(FROM_HOUR, this.mExecuteFromPicker.getCurrentHour());
            intent.putExtra(FROM_MIN, this.mExecuteFromPicker.getCurrentMinute());
            intent.putExtra(TO_HOUR, this.mExecuteToPicker.getCurrentHour());
            intent.putExtra(TO_MIN, this.mExecuteToPicker.getCurrentMinute());
            setResult(-1, intent);
            finish();
        }
    }
}
