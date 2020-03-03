package com.xiaomi.smarthome.camera.lowpower.timerange;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.lowpower.entity.EffectiveTimeSpan;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CreateTimeEdit2Activity extends BaseActivity implements View.OnClickListener {
    private static final int FLAG_EVERYDAY = 127;
    private static final int FLAG_WORKDAY = 62;
    public static final int REQUEST_TIMESPAN = 1011;
    private EffectiveTimeSpan effectiveTimeSpan;
    private int flag = 0;
    private boolean isAllday;
    ImageView mConfirmView;
    TextView mExecuteFrom;
    TimePicker mExecuteFromPicker;
    TextView mExecuteFromTitle;
    TextView mExecuteTo;
    TimePicker mExecuteToPicker;
    TextView mExecuteToTitle;
    SwitchButton mIsAlldaySwitch;
    /* access modifiers changed from: private */
    public int mOffsetHours = 0;
    private String[] mRepeatArray;
    TextView mRepeatDayHint;
    /* access modifiers changed from: private */
    public boolean mRepeatTrigger;
    private EffectiveTimeSpan mTempTimeSpan;
    TextView mTitleView;
    /* access modifiers changed from: private */
    public boolean[] mTriggerDays = new boolean[7];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_scene_create_time_edit2);
        ButterKnife.bind((Activity) this);
        this.effectiveTimeSpan = (EffectiveTimeSpan) getIntent().getParcelableExtra("time_span");
        if (this.effectiveTimeSpan == null) {
            this.effectiveTimeSpan = new EffectiveTimeSpan();
        }
        this.mTempTimeSpan = new EffectiveTimeSpan();
        this.mRepeatArray = new String[]{getString(R.string.smarthome_scene_timer_everyday), getString(R.string.smarthome_scene_timer_workday), getString(R.string.smarthome_scene_custom)};
        findViews();
        initSceneTimer();
        initView();
        initTitle();
    }

    private void findViews() {
        this.mTitleView = (TextView) findViewById(R.id.module_a_3_return_title);
        this.mConfirmView = (ImageView) findViewById(R.id.module_a_3_right_btn);
        this.mRepeatDayHint = (TextView) findViewById(R.id.smarthome_scene_timer_day_hint);
        this.mIsAlldaySwitch = (SwitchButton) findViewById(R.id.all_day_switch);
        this.mExecuteFromTitle = (TextView) findViewById(R.id.execute_from_title);
        this.mExecuteFrom = (TextView) findViewById(R.id.execute_from_hint);
        this.mExecuteTo = (TextView) findViewById(R.id.execute_to_hint);
        this.mExecuteToTitle = (TextView) findViewById(R.id.execute_to_title);
        this.mExecuteFromPicker = (TimePicker) findViewById(R.id.execute_from_picker);
        this.mExecuteToPicker = (TimePicker) findViewById(R.id.execute_to_picker);
    }

    public void onBackPressed() {
        backConfig();
    }

    @OnClick({2131430969, 2131430980, 2131431847, 2131429077, 2131429081})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.execute_from_layout:
                if (!this.mIsAlldaySwitch.isChecked()) {
                    if (this.mExecuteFromPicker.getVisibility() == 0) {
                        this.mExecuteFromPicker.setVisibility(8);
                    } else {
                        this.mExecuteFromPicker.setVisibility(0);
                    }
                    this.mExecuteToPicker.setVisibility(8);
                    return;
                }
                return;
            case R.id.execute_to_layout:
                if (!this.mIsAlldaySwitch.isChecked()) {
                    if (this.mExecuteToPicker.getVisibility() == 0) {
                        this.mExecuteToPicker.setVisibility(8);
                    } else {
                        this.mExecuteToPicker.setVisibility(0);
                    }
                    this.mExecuteFromPicker.setVisibility(8);
                    return;
                }
                return;
            case R.id.module_a_3_return_btn:
                backConfig();
                return;
            case R.id.module_a_3_right_btn:
                saveTimerSettings();
                return;
            case R.id.repeat_setting:
                showDayOptions();
                return;
            default:
                return;
        }
    }

    private void initTitle() {
        this.mTitleView.setText(R.string.smarthome_scene_effective_timer_title);
        this.mConfirmView.setVisibility(0);
        this.mConfirmView.setImageResource(R.drawable.title_right_tick_drawable);
    }

    /* access modifiers changed from: private */
    public void setTimerPikerUsable(boolean z) {
        if (z) {
            this.mExecuteFromPicker.setCurrentHour(0);
            this.mExecuteFromPicker.setCurrentMinute(0);
            this.mExecuteToPicker.setCurrentHour(0);
            this.mExecuteToPicker.setCurrentMinute(0);
            this.mExecuteFromPicker.setVisibility(8);
            this.mExecuteToPicker.setVisibility(8);
            showNextTriggerHint(this.mOffsetHours);
            this.mExecuteFromTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_subtitle));
            this.mExecuteToTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_subtitle));
            return;
        }
        this.mExecuteFromTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_title));
        this.mExecuteToTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_title));
    }

    private void initView() {
        this.mIsAlldaySwitch.setChecked(this.isAllday);
        setTimerPikerUsable(this.isAllday);
        this.mIsAlldaySwitch.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CreateTimeEdit2Activity.this.mIsAlldaySwitch.setChecked(z);
                CreateTimeEdit2Activity.this.setTimerPikerUsable(z);
            }
        });
        generateShortcutDaysHint();
        this.mOffsetHours = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        this.mExecuteFromPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                CreateTimeEdit2Activity.this.showNextTriggerHint(CreateTimeEdit2Activity.this.mOffsetHours);
            }
        });
        this.mExecuteToPicker.setIs24HourView(true);
        this.mExecuteToPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                CreateTimeEdit2Activity.this.showNextTriggerHint(CreateTimeEdit2Activity.this.mOffsetHours);
            }
        });
        if (this.mIsAlldaySwitch.isChecked()) {
            this.mExecuteFromPicker.setCurrentHour(0);
            this.mExecuteFromPicker.setCurrentMinute(0);
            this.mExecuteToPicker.setCurrentHour(0);
            this.mExecuteToPicker.setCurrentMinute(0);
            return;
        }
        showNextTriggerHint(this.mOffsetHours);
    }

    private void backConfig() {
        if (this.mTempTimeSpan == null) {
            finish();
        }
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        int convert = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        this.mTempTimeSpan.fromHour = (((intValue - convert) + 8) + 24) % 24;
        this.mTempTimeSpan.fromMin = intValue2;
        this.mTempTimeSpan.toHour = (((intValue3 - convert) + 8) + 24) % 24;
        this.mTempTimeSpan.toMin = intValue4;
        if (this.mRepeatTrigger) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 7; i++) {
                if (this.mTriggerDays[i]) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            this.mTempTimeSpan.wDay = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.mTempTimeSpan.wDay[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
        }
        if (this.mTempTimeSpan.isEqual(this.effectiveTimeSpan)) {
            finish();
        } else {
            new MLAlertDialog.Builder(this).b((int) R.string.smarthome_scene_quit).a((int) R.string.smarthome_scene_quest_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CreateTimeEdit2Activity.this.finish();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).d();
        }
    }

    private void saveTimerSettings() {
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        TimeZone timeZone = new GregorianCalendar().getTimeZone();
        this.effectiveTimeSpan.fromHour = intValue;
        this.effectiveTimeSpan.fromMin = intValue2;
        this.effectiveTimeSpan.toHour = intValue3;
        this.effectiveTimeSpan.toMin = intValue4;
        if (this.mRepeatTrigger) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 7; i++) {
                if (this.mTriggerDays[i]) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            this.effectiveTimeSpan.wDay = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.effectiveTimeSpan.wDay[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
        }
        this.effectiveTimeSpan.timezone = timeZone.getID();
        Intent intent = new Intent();
        intent.putExtra("time_span", this.effectiveTimeSpan);
        setResult(-1, intent);
        finish();
    }

    private void showDayOptions() {
        int i = 0;
        this.flag = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            this.flag <<= 1;
            if (this.mTriggerDays[i2]) {
                this.flag++;
            }
        }
        if (this.flag != 127) {
            i = this.flag == 62 ? 1 : 2;
        }
        MLAlertDialog b = new MLAlertDialog.Builder(this).a((CharSequence[]) this.mRepeatArray, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    boolean unused = CreateTimeEdit2Activity.this.mRepeatTrigger = true;
                    boolean[] unused2 = CreateTimeEdit2Activity.this.mTriggerDays = new boolean[]{true, true, true, true, true, true, true};
                    boolean unused3 = CreateTimeEdit2Activity.this.generateShortcutDaysHint();
                    CreateTimeEdit2Activity.this.showNextTriggerHint(CreateTimeEdit2Activity.this.mOffsetHours);
                    dialogInterface.dismiss();
                } else if (i == 1) {
                    boolean unused4 = CreateTimeEdit2Activity.this.mRepeatTrigger = true;
                    boolean[] unused5 = CreateTimeEdit2Activity.this.mTriggerDays = new boolean[]{false, true, true, true, true, true, false};
                    boolean unused6 = CreateTimeEdit2Activity.this.generateShortcutDaysHint();
                    CreateTimeEdit2Activity.this.showNextTriggerHint(CreateTimeEdit2Activity.this.mOffsetHours);
                    dialogInterface.dismiss();
                } else if (i == 2) {
                    final boolean[] zArr = (boolean[]) CreateTimeEdit2Activity.this.mTriggerDays.clone();
                    dialogInterface.dismiss();
                    MLAlertDialog b = new MLAlertDialog.Builder(CreateTimeEdit2Activity.this).a((int) R.array.weekday, zArr, (DialogInterface.OnMultiChoiceClickListener) new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                            zArr[i] = z;
                        }
                    }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean access$300 = CreateTimeEdit2Activity.this.mRepeatTrigger;
                            boolean[] access$400 = CreateTimeEdit2Activity.this.mTriggerDays;
                            boolean unused = CreateTimeEdit2Activity.this.mRepeatTrigger = true;
                            boolean[] unused2 = CreateTimeEdit2Activity.this.mTriggerDays = zArr;
                            if (!CreateTimeEdit2Activity.this.generateShortcutDaysHint()) {
                                boolean unused3 = CreateTimeEdit2Activity.this.mRepeatTrigger = access$300;
                                boolean[] unused4 = CreateTimeEdit2Activity.this.mTriggerDays = access$400;
                                Toast.makeText(CreateTimeEdit2Activity.this, R.string.smarthome_span_error, 0).show();
                                return;
                            }
                            CreateTimeEdit2Activity.this.showNextTriggerHint(CreateTimeEdit2Activity.this.mOffsetHours);
                            ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                        }
                    }).b();
                    b.show();
                    MultipleChoiceDialogHelper.a(b.getContext(), b);
                }
            }
        }).b();
        b.show();
        MultipleChoiceDialogHelper.a(b.getContext(), b);
    }

    /* access modifiers changed from: private */
    public boolean generateShortcutDaysHint() {
        String str;
        this.flag = 0;
        TypedArray obtainTypedArray = getResources().obtainTypedArray(R.array.weekday_short);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            this.flag <<= 1;
            if (this.mTriggerDays[i]) {
                this.flag++;
                sb.append(". ");
                sb.append(obtainTypedArray.getString(i));
            }
        }
        if (this.flag == 0) {
            return false;
        }
        this.mRepeatTrigger = true;
        int i2 = this.flag;
        if (i2 == 62) {
            str = getResources().getString(R.string.smarthome_scene_timer_workday);
        } else if (i2 != 127) {
            str = sb.substring(1);
        } else {
            str = getResources().getString(R.string.smarthome_scene_timer_everyday);
        }
        this.mRepeatDayHint.setText(str);
        return true;
    }

    /* access modifiers changed from: private */
    public void showNextTriggerHint(int i) {
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        if (this.mExecuteFromPicker.getVisibility() == 0) {
            this.mExecuteFrom.setText(formatTime(intValue, intValue2));
        } else if (this.mExecuteToPicker.getVisibility() == 0) {
            if (intValue3 < intValue) {
                TextView textView = this.mExecuteTo;
                textView.setText(formatTime(intValue3, intValue4) + Operators.BRACKET_START_STR + getResources().getString(R.string.scene_exetime_second_day) + Operators.BRACKET_END_STR);
                return;
            }
            this.mExecuteTo.setText(formatTime(intValue3, intValue4));
        } else if (this.mExecuteFromPicker.getVisibility() != 8 || this.mExecuteToPicker.getVisibility() != 8) {
        } else {
            if (this.mIsAlldaySwitch.isChecked()) {
                this.mExecuteFrom.setText(formatTime(0, 0));
                this.mExecuteTo.setText(formatTime(0, 0));
                return;
            }
            this.mExecuteFrom.setText(formatTime((((this.effectiveTimeSpan.fromHour + i) - 8) + 24) % 24, this.effectiveTimeSpan.fromMin));
            this.mExecuteTo.setText(formatTime((((this.effectiveTimeSpan.toHour + i) - 8) + 24) % 24, this.effectiveTimeSpan.toMin));
        }
    }

    private void initSceneTimer() {
        int i = 0;
        Arrays.fill(this.mTriggerDays, false);
        int rawOffset = new GregorianCalendar().getTimeZone().getRawOffset();
        int convert = (int) TimeUnit.HOURS.convert((long) rawOffset, TimeUnit.MILLISECONDS);
        SceneLogUtil.a("offsetHOser----" + convert + "--mGTMoffeset---" + rawOffset);
        this.mExecuteFromPicker.setIs24HourView(true);
        this.mExecuteFromPicker.setCurrentHour(Integer.valueOf((((this.effectiveTimeSpan.fromHour + convert) + -8) + 24) % 24));
        this.mExecuteFromPicker.setCurrentMinute(Integer.valueOf(this.effectiveTimeSpan.fromMin));
        this.mExecuteToPicker.setIs24HourView(true);
        this.mExecuteToPicker.setCurrentHour(Integer.valueOf((((this.effectiveTimeSpan.toHour + convert) + -8) + 24) % 24));
        this.mExecuteToPicker.setCurrentMinute(Integer.valueOf(this.effectiveTimeSpan.toMin));
        if (this.effectiveTimeSpan.fromHour == this.effectiveTimeSpan.toHour && this.effectiveTimeSpan.fromMin == this.effectiveTimeSpan.toMin) {
            this.isAllday = true;
        } else {
            this.isAllday = false;
        }
        if (this.effectiveTimeSpan.wDay != null) {
            while (i < this.effectiveTimeSpan.wDay.length && i < 7 && this.effectiveTimeSpan.wDay[i] < 7) {
                this.mTriggerDays[this.effectiveTimeSpan.wDay[i]] = true;
                i++;
            }
            this.mRepeatTrigger = true;
            return;
        }
        Arrays.fill(this.mTriggerDays, true);
        this.effectiveTimeSpan.wDay = new int[this.mTriggerDays.length];
        while (i < this.mTriggerDays.length) {
            this.effectiveTimeSpan.wDay[i] = i;
            i++;
        }
    }

    private String formatTime(int i, int i2) {
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
}
