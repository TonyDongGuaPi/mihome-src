package com.xiaomi.smarthome.scene;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

public class SmartHomeSceneTimerActivity extends BaseActivity {
    static final int DAY = 86400;
    public static final int FLAG_EVERYDAY = 127;
    public static final int FLAG_WORKDAY = 62;
    static final int HOUR = 3600;
    static final int MINUTE = 60;
    static final int SECOND = 60;
    public static final String TIME_PARAM = "time_param";

    /* renamed from: a  reason: collision with root package name */
    private CorntabUtils.CorntabParam f21453a = null;
    int flag;
    View mBackBtn;
    View mConfirmBtn;
    Context mContext;
    SceneApi.LaunchSceneTimer mLaunchSceneTimer;
    TextView mNextTriggerTimeHint;
    String[] mRepeatArray;
    TextView mRepeatDayHint;
    boolean mRepeatTrigger;
    TimePicker mTimePicker;
    TextView mTitle;
    boolean[] mTriggerDays;

    public static String getRemainTimeHint(Context context, SceneApi.LaunchSceneTimer launchSceneTimer) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_timer_setting_activity);
        this.mContext = this;
        this.mTitle = (TextView) findViewById(R.id.module_a_4_return_more_title);
        this.mTitle.setText(R.string.smarthome_scene_timer_title);
        this.mBackBtn = findViewById(R.id.module_a_4_return_more_btn);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimerActivity.this.finish();
            }
        });
        this.mConfirmBtn = findViewById(R.id.module_a_4_return_finish_btn);
        ((TextView) this.mConfirmBtn).setText(R.string.confirm_button);
        this.mConfirmBtn.setEnabled(true);
        this.mRepeatDayHint = (TextView) findViewById(R.id.smarthome_scene_timer_day_hint);
        this.mNextTriggerTimeHint = (TextView) findViewById(R.id.smarthome_scene_time_hint);
        this.mTimePicker = (TimePicker) findViewById(R.id.smarthome_scene_time_picker);
        this.mTimePicker.setIs24HourView(true);
        Calendar instance = Calendar.getInstance();
        this.mTimePicker.setCurrentHour(Integer.valueOf(instance.get(11)));
        this.mTimePicker.setCurrentMinute(Integer.valueOf(instance.get(12)));
        this.mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                SmartHomeSceneTimerActivity.this.f();
            }
        });
        findViewById(R.id.smarthome_scene_day_settting).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimerActivity.this.d();
            }
        });
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimerActivity.this.c();
            }
        });
        this.f21453a = (CorntabUtils.CorntabParam) getIntent().getParcelableExtra(TIME_PARAM);
        if (this.f21453a != null) {
            a(this.f21453a);
        } else {
            SceneApi.Condition i = CreateSceneManager.a().i();
            if (i == null) {
                finish();
                return;
            } else {
                this.mLaunchSceneTimer = i.b;
                a();
            }
        }
        this.mRepeatArray = new String[]{getString(R.string.smarthome_scene_timer_once), getString(R.string.smarthome_scene_timer_everyday), getString(R.string.smarthome_scene_timer_workday), getString(R.string.smarthome_scene_custom)};
    }

    private void a(CorntabUtils.CorntabParam corntabParam) {
        this.mTriggerDays = new boolean[7];
        Arrays.fill(this.mTriggerDays, false);
        if (corntabParam != null) {
            CorntabUtils.CorntabParam a2 = CorntabUtils.a(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
            this.mTimePicker.setCurrentHour(Integer.valueOf(a2.c % 24));
            this.mTimePicker.setCurrentMinute(Integer.valueOf(a2.b));
            if (a2.d() == 0) {
                this.mRepeatTrigger = false;
            } else {
                this.mTriggerDays = a2.g;
                this.mRepeatTrigger = true;
            }
            e();
            f();
        }
    }

    private void a() {
        if (this.mLaunchSceneTimer != null) {
            a(this.mLaunchSceneTimer.f21527a);
        } else {
            a((CorntabUtils.CorntabParam) null);
        }
    }

    private CorntabUtils.CorntabParam b() {
        return CorntabUtils.a(this.mTimePicker, this.mTriggerDays, this.mRepeatTrigger);
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f21453a != null) {
            Intent intent = new Intent();
            intent.putExtra(TIME_PARAM, b());
            setResult(-1, intent);
        } else {
            if (this.mLaunchSceneTimer == null) {
                this.mLaunchSceneTimer = new SceneApi.LaunchSceneTimer();
            }
            SceneApi.Condition i = CreateSceneManager.a().i();
            if (i != null) {
                i.b = this.mLaunchSceneTimer;
                i.f21522a = SceneApi.Condition.LAUNCH_TYPE.TIMER;
                i.b.f21527a = b();
                setResult(-1);
            }
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void d() {
        int i;
        calculateFlag();
        if (this.flag == 0) {
            i = 0;
        } else if (this.flag == 127) {
            i = 1;
        } else {
            i = this.flag == 62 ? 2 : 3;
        }
        MLAlertDialog b = new MLAlertDialog.Builder(this).a((CharSequence[]) this.mRepeatArray, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    SmartHomeSceneTimerActivity.this.mRepeatTrigger = false;
                    Arrays.fill(SmartHomeSceneTimerActivity.this.mTriggerDays, false);
                    dialogInterface.dismiss();
                    SmartHomeSceneTimerActivity.this.e();
                    SmartHomeSceneTimerActivity.this.f();
                } else if (i == 1) {
                    SmartHomeSceneTimerActivity.this.mRepeatTrigger = true;
                    SmartHomeSceneTimerActivity.this.mTriggerDays = new boolean[]{true, true, true, true, true, true, true};
                    SmartHomeSceneTimerActivity.this.e();
                    SmartHomeSceneTimerActivity.this.f();
                    dialogInterface.dismiss();
                } else if (i == 2) {
                    SmartHomeSceneTimerActivity.this.mRepeatTrigger = true;
                    SmartHomeSceneTimerActivity.this.mTriggerDays = new boolean[]{false, true, true, true, true, true, false};
                    SmartHomeSceneTimerActivity.this.e();
                    SmartHomeSceneTimerActivity.this.f();
                    dialogInterface.dismiss();
                } else if (i == 3) {
                    final boolean[] zArr = (boolean[]) SmartHomeSceneTimerActivity.this.mTriggerDays.clone();
                    dialogInterface.dismiss();
                    MLAlertDialog b = new MLAlertDialog.Builder(SmartHomeSceneTimerActivity.this.mContext).a((int) R.array.weekday, zArr, (DialogInterface.OnMultiChoiceClickListener) new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                            zArr[i] = z;
                        }
                    }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SmartHomeSceneTimerActivity.this.mRepeatTrigger = true;
                            SmartHomeSceneTimerActivity.this.mTriggerDays = zArr;
                            SmartHomeSceneTimerActivity.this.e();
                            SmartHomeSceneTimerActivity.this.f();
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
                    b.show();
                    MultipleChoiceDialogHelper.a(SmartHomeSceneTimerActivity.this.getContext(), b);
                }
            }
        }).b();
        b.show();
        MultipleChoiceDialogHelper.a(getContext(), b);
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

    public static String getTimerHint(Context context, CorntabUtils.CorntabParam corntabParam) {
        String str;
        String str2;
        boolean[] zArr = new boolean[7];
        Arrays.fill(zArr, false);
        if (corntabParam == null) {
            return null;
        }
        String str3 = "";
        CorntabUtils.CorntabParam a2 = CorntabUtils.a(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
        if (a2.b < 10) {
            str = "0" + a2.b;
        } else {
            str = "" + a2.b;
        }
        try {
            str3 = context.getString(R.string.smarthome_time_hint, new Object[]{Integer.valueOf(a2.c), str});
        } catch (Exception unused) {
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.weekday_short);
        StringBuilder sb = new StringBuilder();
        if (a2.d() != 0) {
            zArr = a2.g;
        }
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            i <<= 1;
            if (zArr[i2]) {
                i++;
                sb.append(". ");
                sb.append(obtainTypedArray.getString(i2));
            }
        }
        if (i != 0) {
            str2 = i != 62 ? i != 127 ? sb.substring(1) : context.getResources().getString(R.string.smarthome_scene_timer_everyday) : context.getResources().getString(R.string.smarthome_scene_timer_workday);
        } else {
            str2 = "";
        }
        return str2 + str3;
    }

    /* access modifiers changed from: private */
    public void e() {
        String str;
        this.flag = 0;
        TypedArray obtainTypedArray = getResources().obtainTypedArray(R.array.weekday_short);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            this.flag <<= 1;
            if (this.mTriggerDays[i]) {
                this.flag++;
                sb.append(", ");
                sb.append(obtainTypedArray.getString(i));
            }
        }
        this.mRepeatTrigger = true;
        int i2 = this.flag;
        if (i2 == 0) {
            String string = getResources().getString(R.string.smarthome_scene_timer_once);
            this.mRepeatTrigger = false;
            str = string;
        } else if (i2 == 62) {
            str = getResources().getString(R.string.smarthome_scene_timer_workday);
        } else if (i2 != 127) {
            str = sb.substring(1);
        } else {
            str = getResources().getString(R.string.smarthome_scene_timer_everyday);
        }
        this.mRepeatDayHint.setText(str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.util.GregorianCalendar} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f() {
        /*
            r13 = this;
            com.xiaomi.smarthome.library.common.widget.TimePicker r0 = r13.mTimePicker
            java.lang.Integer r0 = r0.getCurrentHour()
            int r0 = r0.intValue()
            com.xiaomi.smarthome.library.common.widget.TimePicker r1 = r13.mTimePicker
            java.lang.Integer r1 = r1.getCurrentMinute()
            int r1 = r1.intValue()
            java.util.Calendar r2 = java.util.GregorianCalendar.getInstance()
            boolean r3 = r13.mRepeatTrigger
            r4 = 12
            r5 = 11
            if (r3 == 0) goto L_0x0088
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0028:
            r9 = 5
            r10 = 7
            if (r8 >= r10) goto L_0x0061
            boolean[] r11 = r13.mTriggerDays
            boolean r11 = r11[r8]
            if (r11 == 0) goto L_0x005e
            int r11 = r8 + 1
            int r12 = r2.get(r10)
            int r11 = r11 - r12
            java.util.GregorianCalendar r12 = new java.util.GregorianCalendar
            r12.<init>()
            r12.add(r9, r11)
            int r11 = r2.get(r5)
            int r11 = r0 - r11
            r12.add(r5, r11)
            int r11 = r2.get(r4)
            int r11 = r1 - r11
            r12.add(r4, r11)
            boolean r11 = r12.after(r2)
            if (r11 == 0) goto L_0x005b
            r6 = r12
            goto L_0x0061
        L_0x005b:
            r3.add(r12)
        L_0x005e:
            int r8 = r8 + 1
            goto L_0x0028
        L_0x0061:
            if (r6 != 0) goto L_0x006d
            java.lang.Object r0 = r3.get(r7)
            r6 = r0
            java.util.GregorianCalendar r6 = (java.util.GregorianCalendar) r6
            r6.add(r9, r10)
        L_0x006d:
            java.util.Date r0 = r6.getTime()
            long r0 = r0.getTime()
            java.util.Date r2 = r2.getTime()
            long r2 = r2.getTime()
            long r0 = r0 - r2
            android.widget.TextView r2 = r13.mNextTriggerTimeHint
            java.lang.String r0 = r13.formatTime(r13, r0)
            r2.setText(r0)
            goto L_0x00b4
        L_0x0088:
            int r0 = r0 * 3600
            int r1 = r1 * 60
            int r0 = r0 + r1
            int r1 = r2.get(r5)
            int r1 = r1 * 3600
            int r3 = r2.get(r4)
            int r3 = r3 * 60
            int r1 = r1 + r3
            r3 = 13
            int r2 = r2.get(r3)
            int r1 = r1 + r2
            int r0 = r0 - r1
            if (r0 >= 0) goto L_0x00a8
            r1 = 86400(0x15180, float:1.21072E-40)
            int r0 = r0 + r1
        L_0x00a8:
            android.widget.TextView r1 = r13.mNextTriggerTimeHint
            int r0 = r0 * 1000
            long r2 = (long) r0
            java.lang.String r0 = r13.formatTime(r13, r2)
            r1.setText(r0)
        L_0x00b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.SmartHomeSceneTimerActivity.f():void");
    }

    public String formatTime(Context context, long j) {
        String str;
        long j2 = j / 1000;
        int i = (int) (j2 / 86400);
        int i2 = (int) ((j2 % 86400) / 3600);
        int i3 = (int) ((j2 % 3600) / 60);
        switch ((i != 0 ? 4 : 0) + (i2 != 0 ? 2 : 0) + (i3 != 0 ? 1 : 0)) {
            case 0:
                str = getString(R.string.scene_timer_less_than_1_minute_hint_open);
                break;
            case 1:
                str = getResources().getQuantityString(R.plurals.scene_timer_hint_open_only_minute, i3, new Object[]{Integer.valueOf(i3)});
                break;
            case 2:
                str = getResources().getQuantityString(R.plurals.scene_timer_hint_open_only_hour, i2, new Object[]{Integer.valueOf(i2)});
                break;
            case 3:
                String quantityString = getResources().getQuantityString(R.plurals.automation_hour, i2, new Object[]{Integer.valueOf(i2)});
                String quantityString2 = getResources().getQuantityString(R.plurals.automation_minute, i3, new Object[]{Integer.valueOf(i3)});
                String string = getString(R.string.scene_timer_hint_open_no_day);
                Object[] objArr = new Object[2];
                if (TextUtils.isEmpty(quantityString)) {
                    quantityString = "";
                }
                objArr[0] = quantityString;
                if (TextUtils.isEmpty(quantityString2)) {
                    quantityString2 = "";
                }
                objArr[1] = quantityString2;
                str = String.format(string, objArr);
                break;
            case 4:
                str = getResources().getQuantityString(R.plurals.scene_timer_hint_open_only_day, i, new Object[]{Integer.valueOf(i)});
                break;
            case 5:
                String quantityString3 = getResources().getQuantityString(R.plurals.automation_day, i, new Object[]{Integer.valueOf(i)});
                String quantityString4 = getResources().getQuantityString(R.plurals.automation_minute, i3, new Object[]{Integer.valueOf(i3)});
                String string2 = getString(R.string.scene_timer_hint_open_no_hour);
                Object[] objArr2 = new Object[2];
                if (TextUtils.isEmpty(quantityString3)) {
                    quantityString3 = "";
                }
                objArr2[0] = quantityString3;
                if (TextUtils.isEmpty(quantityString4)) {
                    quantityString4 = "";
                }
                objArr2[1] = quantityString4;
                str = String.format(string2, objArr2);
                break;
            case 6:
                String quantityString5 = getResources().getQuantityString(R.plurals.automation_day, i, new Object[]{Integer.valueOf(i)});
                String quantityString6 = getResources().getQuantityString(R.plurals.automation_hour, i2, new Object[]{Integer.valueOf(i2)});
                String string3 = getString(R.string.scene_timer_hint_open_no_minute);
                Object[] objArr3 = new Object[2];
                if (TextUtils.isEmpty(quantityString5)) {
                    quantityString5 = "";
                }
                objArr3[0] = quantityString5;
                if (TextUtils.isEmpty(quantityString6)) {
                    quantityString6 = "";
                }
                objArr3[1] = quantityString6;
                str = String.format(string3, objArr3);
                break;
            case 7:
                try {
                    String quantityString7 = getResources().getQuantityString(R.plurals.automation_day, i, new Object[]{Integer.valueOf(i)});
                    String quantityString8 = getResources().getQuantityString(R.plurals.automation_hour, i2, new Object[]{Integer.valueOf(i2)});
                    String quantityString9 = getResources().getQuantityString(R.plurals.automation_minute, i3, new Object[]{Integer.valueOf(i3)});
                    String string4 = getString(R.string.scene_timer_hint_open_all);
                    Object[] objArr4 = new Object[3];
                    if (TextUtils.isEmpty(quantityString7)) {
                        quantityString7 = "";
                    }
                    objArr4[0] = quantityString7;
                    if (TextUtils.isEmpty(quantityString8)) {
                        quantityString8 = "";
                    }
                    objArr4[1] = quantityString8;
                    if (TextUtils.isEmpty(quantityString9)) {
                        quantityString9 = "";
                    }
                    objArr4[2] = quantityString9;
                    str = String.format(string4, objArr4);
                    break;
                } catch (Exception unused) {
                    return "";
                }
            default:
                return "";
        }
        return str;
    }
}
