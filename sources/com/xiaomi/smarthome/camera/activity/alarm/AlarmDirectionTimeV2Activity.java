package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.HourNumberPicker;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import java.text.DecimalFormat;

public class AlarmDirectionTimeV2Activity extends CameraBaseActivity implements View.OnClickListener {
    public static final String ALL_DAY_END_TIME = "23:59:59";
    public static final String ALL_DAY_END_TIME_MIN = "23:59";
    public static final String ALL_DAY_START_TIME = "00:00:00";
    public static final String DAY_END_TIME = "20:00:00";
    public static final String DAY_START_TIME = "08:00:00";
    public static final String NIGHT_END_TIME = "08:00:00";
    public static final String NIGHT_START_TIME = "20:00:00";
    private static final String TAG = "AlarmDirectionTimeV2Activity";
    /* access modifiers changed from: private */
    public String endTime;
    private View mAllDayCheck;
    private View mCustomCheck;
    private TextView mCustomTitle;
    private View mDayCheck;
    private DecimalFormat mDf;
    private View mNightCheck;
    int mTimeHourBegin;
    int mTimeHourEnd;
    int mTimeMinuteBegin;
    int mTimeMinuteEnd;
    /* access modifiers changed from: private */
    public String startTime;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_direction_time);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.alarm_direction_time);
        this.startTime = getIntent().getStringExtra("startTime");
        this.endTime = getIntent().getStringExtra("endTime");
        this.mDf = new DecimalFormat("00");
        initView();
    }

    private void initView() {
        findViewById(R.id.title_bar_more).setVisibility(8);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.time_all_day).setOnClickListener(this);
        findViewById(R.id.time_day).setOnClickListener(this);
        findViewById(R.id.time_night).setOnClickListener(this);
        findViewById(R.id.time_custom).setOnClickListener(this);
        this.mCustomTitle = (TextView) findViewById(R.id.time_custom_title);
        this.mAllDayCheck = findViewById(R.id.icon_all_day);
        this.mDayCheck = findViewById(R.id.icon_day);
        this.mNightCheck = findViewById(R.id.icon_night);
        this.mCustomCheck = findViewById(R.id.icon_custom);
        changeCheck();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_all_day:
                this.startTime = "00:00:00";
                this.endTime = "23:59:59";
                changeCheck();
                return;
            case R.id.time_custom:
                customChoice();
                return;
            case R.id.time_day:
                this.startTime = "08:00:00";
                this.endTime = "20:00:00";
                changeCheck();
                return;
            case R.id.time_night:
                this.startTime = "20:00:00";
                this.endTime = "08:00:00";
                changeCheck();
                return;
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void changeCheck() {
        this.mAllDayCheck.setVisibility(8);
        this.mDayCheck.setVisibility(8);
        this.mNightCheck.setVisibility(8);
        this.mCustomCheck.setVisibility(8);
        SDKLog.b(TAG, "startTime:" + this.startTime + " endTime:" + this.endTime);
        if (!TextUtils.isEmpty(this.startTime) && !TextUtils.isEmpty(this.endTime)) {
            if ("00:00:00".equals(this.startTime) && ("23:59:59".equals(this.endTime) || this.endTime.contains("23:59"))) {
                this.mAllDayCheck.setVisibility(0);
                this.mCustomTitle.setText(getString(R.string.alarm_direction_custom_subtitle));
            } else if ("08:00:00".equals(this.startTime) && "20:00:00".equals(this.endTime)) {
                this.mDayCheck.setVisibility(0);
                this.mCustomTitle.setText(getString(R.string.alarm_direction_custom_subtitle));
            } else if (!"20:00:00".equals(this.startTime) || !"08:00:00".equals(this.endTime)) {
                this.mCustomCheck.setVisibility(0);
                this.mCustomTitle.setText(getString(R.string.alarm_direction_custom_title, new Object[]{this.startTime, this.endTime}));
            } else {
                this.mNightCheck.setVisibility(0);
                this.mCustomTitle.setText(getString(R.string.alarm_direction_custom_subtitle));
            }
        }
    }

    private void customChoice() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.camera_alarm_direction_time_choose, (ViewGroup) null);
        builder.b(inflate);
        HourNumberPicker hourNumberPicker = (HourNumberPicker) inflate.findViewById(R.id.number_picker_hour_begin);
        HourNumberPicker hourNumberPicker2 = (HourNumberPicker) inflate.findViewById(R.id.number_picker_hour_end);
        hourNumberPicker.setMinValue(0);
        hourNumberPicker.setMaxValue(143);
        hourNumberPicker2.setMinValue(6);
        hourNumberPicker2.setMaxValue(144);
        hourNumberPicker.setFormatter(HourNumberPicker.HOUR_MINUTE_DIGIT_FORMATTER);
        hourNumberPicker2.setFormatter(HourNumberPicker.HOUR_MINUTE_DIGIT_FORMATTER);
        this.mTimeHourBegin = Integer.valueOf(this.startTime.split(":")[0]).intValue();
        this.mTimeMinuteBegin = Integer.valueOf(this.startTime.split(":")[1]).intValue();
        this.mTimeHourEnd = Integer.valueOf(this.endTime.split(":")[0]).intValue();
        this.mTimeMinuteEnd = Integer.valueOf(this.endTime.split(":")[1]).intValue();
        hourNumberPicker.setValue(((this.mTimeHourBegin * 60) + this.mTimeMinuteBegin) / 10);
        if (this.mTimeHourEnd == 23 && this.mTimeMinuteEnd == 59) {
            hourNumberPicker2.setValue((((this.mTimeHourEnd * 60) + this.mTimeMinuteEnd) + 1) / 10);
        } else {
            hourNumberPicker2.setValue(((this.mTimeHourEnd * 60) + this.mTimeMinuteEnd) / 10);
        }
        hourNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int i3 = i2 / 6;
                int i4 = (i2 % 6) * 10;
                if (i3 == AlarmDirectionTimeV2Activity.this.mTimeHourEnd && i4 == AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd) {
                    if (r5[0] != null) {
                        r5[0].setClickable(false);
                        r5[0].setTextColor(Color.rgb(200, 200, 200));
                    }
                } else if (r5[0] != null) {
                    r5[0].setClickable(true);
                    r5[0].setTextColor(r6[0]);
                }
                AlarmDirectionTimeV2Activity.this.mTimeHourBegin = i3;
                AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin = i4;
            }
        });
        hourNumberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int i3 = i2 / 6;
                int i4 = (i2 % 6) * 10;
                if (i3 == AlarmDirectionTimeV2Activity.this.mTimeHourBegin && i4 == AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin) {
                    if (r5[0] != null) {
                        r5[0].setClickable(false);
                        r5[0].setTextColor(Color.rgb(200, 200, 200));
                    }
                } else if (r5[0] != null) {
                    r5[0].setClickable(true);
                    r5[0].setTextColor(r6[0]);
                }
                AlarmDirectionTimeV2Activity.this.mTimeHourEnd = i3;
                AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd = i4;
            }
        });
        builder.a((CharSequence) getResources().getString(R.string.camera_sure), (DialogInterface.OnClickListener) null);
        builder.b((CharSequence) getResources().getString(R.string.camera_cancel), (DialogInterface.OnClickListener) null);
        final MLAlertDialog d = builder.d();
        Button button = d.getButton(-1);
        final Button[] buttonArr = {button};
        final int[] iArr = {button.getCurrentTextColor()};
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlarmDirectionTimeV2Activity.this.mTimeHourBegin < 10) {
                    if (AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin < 10) {
                        AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity = AlarmDirectionTimeV2Activity.this;
                        String unused = alarmDirectionTimeV2Activity.startTime = "0" + AlarmDirectionTimeV2Activity.this.mTimeHourBegin + ":0" + AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin + ":00";
                    } else {
                        AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity2 = AlarmDirectionTimeV2Activity.this;
                        String unused2 = alarmDirectionTimeV2Activity2.startTime = "0" + AlarmDirectionTimeV2Activity.this.mTimeHourBegin + ":" + AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin + ":00";
                    }
                } else if (AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin < 10) {
                    AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity3 = AlarmDirectionTimeV2Activity.this;
                    String unused3 = alarmDirectionTimeV2Activity3.startTime = "" + AlarmDirectionTimeV2Activity.this.mTimeHourBegin + ":0" + AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin + ":00";
                } else {
                    AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity4 = AlarmDirectionTimeV2Activity.this;
                    String unused4 = alarmDirectionTimeV2Activity4.startTime = "" + AlarmDirectionTimeV2Activity.this.mTimeHourBegin + ":" + AlarmDirectionTimeV2Activity.this.mTimeMinuteBegin + ":00";
                }
                if (AlarmDirectionTimeV2Activity.this.mTimeHourEnd < 10) {
                    if (AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd < 10) {
                        AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity5 = AlarmDirectionTimeV2Activity.this;
                        String unused5 = alarmDirectionTimeV2Activity5.endTime = "0" + AlarmDirectionTimeV2Activity.this.mTimeHourEnd + ":0" + AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd + ":00";
                    } else {
                        AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity6 = AlarmDirectionTimeV2Activity.this;
                        String unused6 = alarmDirectionTimeV2Activity6.endTime = "0" + AlarmDirectionTimeV2Activity.this.mTimeHourEnd + ":" + AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd + ":00";
                    }
                } else if (AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd < 10) {
                    AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity7 = AlarmDirectionTimeV2Activity.this;
                    String unused7 = alarmDirectionTimeV2Activity7.endTime = "" + AlarmDirectionTimeV2Activity.this.mTimeHourEnd + ":0" + AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd + ":00";
                } else {
                    AlarmDirectionTimeV2Activity alarmDirectionTimeV2Activity8 = AlarmDirectionTimeV2Activity.this;
                    String unused8 = alarmDirectionTimeV2Activity8.endTime = "" + AlarmDirectionTimeV2Activity.this.mTimeHourEnd + ":" + AlarmDirectionTimeV2Activity.this.mTimeMinuteEnd + ":00";
                }
                AlarmDirectionTimeV2Activity.this.changeCheck();
                d.dismiss();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("startTime", this.startTime);
        intent.putExtra("endTime", this.endTime);
        setResult(-1, intent);
        super.onBackPressed();
    }
}
