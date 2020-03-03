package com.xiaomi.smarthome.scene;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.widget.HourSpanPicker;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class SmartHomeSceneTimeSpan extends BaseActivity {
    static final int DAY = 86400;
    public static final int FLAG_EVERYDAY = 127;
    public static final int FLAG_WORKDAY = 62;
    static final int HOUR = 3600;
    static final int MINUTE = 60;
    static final int SECOND = 60;
    int flag;
    View mBackBtn;
    SceneApi.ConditionDevice mConditionDevice;
    View mConfirmBtn;
    Context mContext;
    TextView mNextTriggerTimeHint;
    String[] mRepeatArray;
    TextView mRepeatDayHint;
    boolean mRepeatTrigger;
    SceneApi.SmartHomeScene mScene;
    String mSceneID;
    HourSpanPicker mTimePicker;
    TextView mTitle;
    boolean[] mTriggerDays;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_timespan);
        this.mContext = this;
        this.mTitle = (TextView) findViewById(R.id.module_a_3_return_title);
        this.mTitle.setText(R.string.smarthome_scene_timer_title);
        this.mBackBtn = findViewById(R.id.module_a_3_return_btn);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimeSpan.this.finish();
            }
        });
        this.mConfirmBtn = findViewById(R.id.module_a_3_right_text_btn);
        ((TextView) this.mConfirmBtn).setText(R.string.ok_button);
        this.mConfirmBtn.setEnabled(true);
        this.mRepeatDayHint = (TextView) findViewById(R.id.smarthome_scene_timer_day_hint);
        this.mNextTriggerTimeHint = (TextView) findViewById(R.id.smarthome_scene_time_hint);
        this.mTimePicker = (HourSpanPicker) findViewById(R.id.smarthome_scene_time_picker);
        this.mTimePicker.setOnTimeChangedListener(new HourSpanPicker.OnTimeChangedListener() {
            public void a(HourSpanPicker hourSpanPicker, int i, int i2) {
                SmartHomeSceneTimeSpan.this.e();
            }
        });
        findViewById(R.id.smarthome_scene_day_settting).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimeSpan.this.c();
            }
        });
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimeSpan.this.b();
            }
        });
        this.mScene = SmartHomeSceneCreateEditActivity.mScene;
        if (this.mScene == null) {
            finish();
            return;
        }
        this.mSceneID = this.mScene.f;
        this.mConditionDevice = CreateSceneManager.a().i().c;
        a();
        this.mRepeatArray = new String[]{getString(R.string.smarthome_scene_timer_everyday), getString(R.string.smarthome_scene_timer_workday), getString(R.string.smarthome_scene_custom)};
    }

    private void a() {
        this.mTriggerDays = new boolean[7];
        int i = 0;
        Arrays.fill(this.mTriggerDays, false);
        int convert = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        if (this.mConditionDevice != null) {
            this.mTimePicker.setFromHour(Integer.valueOf((((this.mConditionDevice.e + convert) - 8) + 24) % 24));
            this.mTimePicker.setToHour(Integer.valueOf((((this.mConditionDevice.f + convert) - 8) + 24) % 24));
            if (this.mConditionDevice.i != null) {
                while (i < this.mConditionDevice.i.length && i < 7 && this.mConditionDevice.i[i] < 7) {
                    this.mTriggerDays[this.mConditionDevice.i[i]] = true;
                    i++;
                }
                this.mRepeatTrigger = true;
            } else {
                Arrays.fill(this.mTriggerDays, true);
                this.mConditionDevice.i = new int[this.mTriggerDays.length];
                while (i < this.mTriggerDays.length) {
                    this.mConditionDevice.i[i] = i;
                    i++;
                }
            }
            d();
            e();
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void b() {
        int intValue = this.mTimePicker.getFromHour().intValue();
        int intValue2 = this.mTimePicker.getToHour().intValue();
        int convert = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        this.mConditionDevice.e = (((intValue - convert) + 8) + 24) % 24;
        this.mConditionDevice.g = 0;
        this.mConditionDevice.f = (((intValue2 - convert) + 8) + 24) % 24;
        this.mConditionDevice.h = 0;
        if (this.mRepeatTrigger) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 7; i++) {
                if (this.mTriggerDays[i]) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            this.mConditionDevice.i = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.mConditionDevice.i[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
        }
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: private */
    public void c() {
        int i;
        calculateFlag();
        if (this.flag == 127) {
            i = 0;
        } else {
            i = this.flag == 62 ? 1 : 2;
        }
        new MLAlertDialog.Builder(this).a((CharSequence[]) this.mRepeatArray, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    SmartHomeSceneTimeSpan.this.mRepeatTrigger = true;
                    SmartHomeSceneTimeSpan.this.mTriggerDays = new boolean[]{true, true, true, true, true, true, true};
                    boolean unused = SmartHomeSceneTimeSpan.this.d();
                    SmartHomeSceneTimeSpan.this.e();
                    dialogInterface.dismiss();
                } else if (i == 1) {
                    SmartHomeSceneTimeSpan.this.mRepeatTrigger = true;
                    SmartHomeSceneTimeSpan.this.mTriggerDays = new boolean[]{false, true, true, true, true, true, false};
                    boolean unused2 = SmartHomeSceneTimeSpan.this.d();
                    SmartHomeSceneTimeSpan.this.e();
                    dialogInterface.dismiss();
                } else if (i == 2) {
                    final boolean[] zArr = (boolean[]) SmartHomeSceneTimeSpan.this.mTriggerDays.clone();
                    dialogInterface.dismiss();
                    new MLAlertDialog.Builder(SmartHomeSceneTimeSpan.this.mContext).a((int) R.array.weekday, zArr, (DialogInterface.OnMultiChoiceClickListener) new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                            zArr[i] = z;
                        }
                    }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean z = SmartHomeSceneTimeSpan.this.mRepeatTrigger;
                            boolean[] zArr = SmartHomeSceneTimeSpan.this.mTriggerDays;
                            SmartHomeSceneTimeSpan.this.mRepeatTrigger = true;
                            SmartHomeSceneTimeSpan.this.mTriggerDays = zArr;
                            if (!SmartHomeSceneTimeSpan.this.d()) {
                                SmartHomeSceneTimeSpan.this.mRepeatTrigger = z;
                                SmartHomeSceneTimeSpan.this.mTriggerDays = zArr;
                                Toast.makeText(SmartHomeSceneTimeSpan.this, R.string.smarthome_span_error, 0).show();
                                return;
                            }
                            SmartHomeSceneTimeSpan.this.e();
                            ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                        }
                    }).d(false).b().show();
                }
            }
        }).b().show();
    }

    /* access modifiers changed from: package-private */
    public void calculateFlag() {
        this.flag = 0;
        for (int i = 0; i < 7; i++) {
            this.flag <<= 1;
            if (this.mTriggerDays[i]) {
                this.flag++;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean d() {
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
    public void e() {
        int intValue = this.mTimePicker.getFromHour().intValue();
        int intValue2 = this.mTimePicker.getToHour().intValue();
        TextView textView = this.mNextTriggerTimeHint;
        textView.setText("" + intValue + ":00-" + intValue2 + ":00");
    }
}
