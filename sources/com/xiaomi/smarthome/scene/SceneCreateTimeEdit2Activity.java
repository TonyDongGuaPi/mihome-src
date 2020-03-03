package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class SceneCreateTimeEdit2Activity extends BaseActivity implements View.OnClickListener {
    public static final int REQUEST_TIMESPAN = 1011;

    /* renamed from: a  reason: collision with root package name */
    private static final int f21290a = 127;
    private static final int b = 62;
    private String c;
    private SceneApi.SmartHomeScene d;
    private boolean e;
    private int f = 0;
    /* access modifiers changed from: private */
    public boolean[] g = new boolean[7];
    private String[] h;
    private SceneApi.EffectiveTimeSpan i;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public int k = 0;
    @BindView(2131430980)
    ImageView mConfirmView;
    @BindView(2131429076)
    TextView mExecuteFrom;
    @BindView(2131429078)
    TimePicker mExecuteFromPicker;
    @BindView(2131429079)
    TextView mExecuteFromTitle;
    @BindView(2131429080)
    TextView mExecuteTo;
    @BindView(2131429082)
    TimePicker mExecuteToPicker;
    @BindView(2131429083)
    TextView mExecuteToTitle;
    @BindView(2131427669)
    SwitchButton mIsAlldaySwitch;
    @BindView(2131432534)
    TextView mRepeatDayHint;
    @BindView(2131430975)
    TextView mTitleView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_scene_create_time_edit2);
        ButterKnife.bind((Activity) this);
        if (!getIntent().getBooleanExtra("is_from_recommend", false)) {
            this.d = SmarthomeCreateAutoSceneActivity.mScene;
        } else {
            this.d = RecommendSceneCreator.a().h;
        }
        if (this.d == null) {
            SceneLogUtil.a("mScene == null");
            finish();
            return;
        }
        this.c = this.d.f;
        if (this.d.u == null) {
            this.d.u = new SceneApi.EffectiveTimeSpan();
        }
        this.i = new SceneApi.EffectiveTimeSpan();
        if (this.d.u == null) {
            finish();
            return;
        }
        g();
        this.h = new String[]{getString(R.string.smarthome_scene_timer_everyday), getString(R.string.smarthome_scene_timer_workday), getString(R.string.smarthome_scene_custom)};
        a();
        b();
    }

    public void onBackPressed() {
        c();
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
                c();
                return;
            case R.id.module_a_3_right_btn:
                d();
                return;
            case R.id.repeat_setting:
                e();
                return;
            default:
                return;
        }
    }

    private void a() {
        this.mTitleView.setText(R.string.smarthome_scene_effective_timer_title);
        this.mConfirmView.setVisibility(0);
        this.mConfirmView.setImageResource(R.drawable.title_right_tick_drawable);
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.mExecuteFromPicker.setCurrentHour(0);
            this.mExecuteFromPicker.setCurrentMinute(0);
            this.mExecuteToPicker.setCurrentHour(0);
            this.mExecuteToPicker.setCurrentMinute(0);
            this.mExecuteFromPicker.setVisibility(8);
            this.mExecuteToPicker.setVisibility(8);
            a(this.k);
            this.mExecuteFromTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_subtitle));
            this.mExecuteToTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_subtitle));
            return;
        }
        this.mExecuteFromTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_title));
        this.mExecuteToTitle.setTextColor(getContext().getResources().getColor(R.color.std_list_title));
    }

    private void b() {
        this.mIsAlldaySwitch.setChecked(this.e);
        a(this.e);
        this.mIsAlldaySwitch.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SceneCreateTimeEdit2Activity.this.mIsAlldaySwitch.setChecked(z);
                SceneCreateTimeEdit2Activity.this.a(z);
            }
        });
        f();
        this.k = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        this.mExecuteFromPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                SceneCreateTimeEdit2Activity.this.a(SceneCreateTimeEdit2Activity.this.k);
            }
        });
        this.mExecuteToPicker.setIs24HourView(true);
        this.mExecuteToPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                SceneCreateTimeEdit2Activity.this.a(SceneCreateTimeEdit2Activity.this.k);
            }
        });
        if (this.mIsAlldaySwitch.isChecked()) {
            this.mExecuteFromPicker.setCurrentHour(0);
            this.mExecuteFromPicker.setCurrentMinute(0);
            this.mExecuteToPicker.setCurrentHour(0);
            this.mExecuteToPicker.setCurrentMinute(0);
            return;
        }
        a(this.k);
    }

    private void c() {
        if (this.i == null) {
            finish();
        }
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        int convert = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        this.i.f21526a = (((intValue - convert) + 8) + 24) % 24;
        this.i.c = intValue2;
        this.i.b = (((intValue3 - convert) + 8) + 24) % 24;
        this.i.d = intValue4;
        if (this.j) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 7; i2++) {
                if (this.g[i2]) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            this.i.e = new int[arrayList.size()];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                this.i.e[i3] = ((Integer) arrayList.get(i3)).intValue();
            }
        }
        if (this.i.a(this.d.u)) {
            finish();
        } else {
            new MLAlertDialog.Builder(this).b((int) R.string.smarthome_scene_quit).a((int) R.string.smarthome_scene_quest_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SceneCreateTimeEdit2Activity.this.finish();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).d();
        }
    }

    private void d() {
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        int convert = (int) TimeUnit.HOURS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        this.d.u.f21526a = (((intValue - convert) + 8) + 24) % 24;
        this.d.u.c = intValue2;
        this.d.u.b = (((intValue3 - convert) + 8) + 24) % 24;
        this.d.u.d = intValue4;
        if (this.j) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 7; i2++) {
                if (this.g[i2]) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            this.d.u.e = new int[arrayList.size()];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                this.d.u.e[i3] = ((Integer) arrayList.get(i3)).intValue();
            }
        }
        setResult(-1);
        finish();
    }

    private void e() {
        int i2 = 0;
        this.f = 0;
        for (int i3 = 0; i3 < 7; i3++) {
            this.f <<= 1;
            if (this.g[i3]) {
                this.f++;
            }
        }
        if (this.f != 127) {
            i2 = this.f == 62 ? 1 : 2;
        }
        MLAlertDialog b2 = new MLAlertDialog.Builder(this).a((CharSequence[]) this.h, i2, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    boolean unused = SceneCreateTimeEdit2Activity.this.j = true;
                    boolean[] unused2 = SceneCreateTimeEdit2Activity.this.g = new boolean[]{true, true, true, true, true, true, true};
                    boolean unused3 = SceneCreateTimeEdit2Activity.this.f();
                    SceneCreateTimeEdit2Activity.this.a(SceneCreateTimeEdit2Activity.this.k);
                    dialogInterface.dismiss();
                } else if (i == 1) {
                    boolean unused4 = SceneCreateTimeEdit2Activity.this.j = true;
                    boolean[] unused5 = SceneCreateTimeEdit2Activity.this.g = new boolean[]{false, true, true, true, true, true, false};
                    boolean unused6 = SceneCreateTimeEdit2Activity.this.f();
                    SceneCreateTimeEdit2Activity.this.a(SceneCreateTimeEdit2Activity.this.k);
                    dialogInterface.dismiss();
                } else if (i == 2) {
                    final boolean[] zArr = (boolean[]) SceneCreateTimeEdit2Activity.this.g.clone();
                    dialogInterface.dismiss();
                    MLAlertDialog b = new MLAlertDialog.Builder(SceneCreateTimeEdit2Activity.this).a((int) R.array.weekday, zArr, (DialogInterface.OnMultiChoiceClickListener) new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                            zArr[i] = z;
                        }
                    }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean access$300 = SceneCreateTimeEdit2Activity.this.j;
                            boolean[] access$400 = SceneCreateTimeEdit2Activity.this.g;
                            boolean unused = SceneCreateTimeEdit2Activity.this.j = true;
                            boolean[] unused2 = SceneCreateTimeEdit2Activity.this.g = zArr;
                            if (!SceneCreateTimeEdit2Activity.this.f()) {
                                boolean unused3 = SceneCreateTimeEdit2Activity.this.j = access$300;
                                boolean[] unused4 = SceneCreateTimeEdit2Activity.this.g = access$400;
                                Toast.makeText(SceneCreateTimeEdit2Activity.this, R.string.smarthome_span_error, 0).show();
                                return;
                            }
                            SceneCreateTimeEdit2Activity.this.a(SceneCreateTimeEdit2Activity.this.k);
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
        b2.show();
        MultipleChoiceDialogHelper.a(b2.getContext(), b2);
    }

    /* access modifiers changed from: private */
    public boolean f() {
        String str;
        this.f = 0;
        TypedArray obtainTypedArray = getResources().obtainTypedArray(R.array.weekday_short);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 7; i2++) {
            this.f <<= 1;
            if (this.g[i2]) {
                this.f++;
                sb.append(". ");
                sb.append(obtainTypedArray.getString(i2));
            }
        }
        if (this.f == 0) {
            return false;
        }
        this.j = true;
        int i3 = this.f;
        if (i3 == 62) {
            str = getResources().getString(R.string.smarthome_scene_timer_workday);
        } else if (i3 != 127) {
            str = sb.substring(1);
        } else {
            str = getResources().getString(R.string.smarthome_scene_timer_everyday);
        }
        this.mRepeatDayHint.setText(str);
        return true;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        int intValue = this.mExecuteFromPicker.getCurrentHour().intValue();
        int intValue2 = this.mExecuteFromPicker.getCurrentMinute().intValue();
        int intValue3 = this.mExecuteToPicker.getCurrentHour().intValue();
        int intValue4 = this.mExecuteToPicker.getCurrentMinute().intValue();
        if (this.mExecuteFromPicker.getVisibility() == 0) {
            this.mExecuteFrom.setText(a(intValue, intValue2));
        } else if (this.mExecuteToPicker.getVisibility() == 0) {
            if (intValue3 < intValue) {
                TextView textView = this.mExecuteTo;
                textView.setText(a(intValue3, intValue4) + Operators.BRACKET_START_STR + getResources().getString(R.string.scene_exetime_second_day) + Operators.BRACKET_END_STR);
                return;
            }
            this.mExecuteTo.setText(a(intValue3, intValue4));
        } else if (this.mExecuteFromPicker.getVisibility() != 8 || this.mExecuteToPicker.getVisibility() != 8) {
        } else {
            if (this.mIsAlldaySwitch.isChecked()) {
                this.mExecuteFrom.setText(a(0, 0));
                this.mExecuteTo.setText(a(0, 0));
                return;
            }
            this.mExecuteFrom.setText(a((((this.d.u.f21526a + i2) - 8) + 24) % 24, this.d.u.c));
            this.mExecuteTo.setText(a((((this.d.u.b + i2) - 8) + 24) % 24, this.d.u.d));
        }
    }

    private void g() {
        int i2 = 0;
        Arrays.fill(this.g, false);
        int rawOffset = new GregorianCalendar().getTimeZone().getRawOffset();
        int convert = (int) TimeUnit.HOURS.convert((long) rawOffset, TimeUnit.MILLISECONDS);
        SceneLogUtil.a("offsetHOser----" + convert + "--mGTMoffeset---" + rawOffset);
        this.mExecuteFromPicker.setIs24HourView(true);
        this.mExecuteFromPicker.setCurrentHour(Integer.valueOf((((this.d.u.f21526a + convert) + -8) + 24) % 24));
        this.mExecuteFromPicker.setCurrentMinute(Integer.valueOf(this.d.u.c));
        this.mExecuteToPicker.setIs24HourView(true);
        this.mExecuteToPicker.setCurrentHour(Integer.valueOf((((this.d.u.b + convert) + -8) + 24) % 24));
        this.mExecuteToPicker.setCurrentMinute(Integer.valueOf(this.d.u.d));
        if (this.d.u.f21526a == this.d.u.b && this.d.u.c == this.d.u.d) {
            this.e = true;
        } else {
            this.e = false;
        }
        if (this.d.u.e != null) {
            while (i2 < this.d.u.e.length && i2 < 7 && this.d.u.e[i2] < 7) {
                this.g[this.d.u.e[i2]] = true;
                i2++;
            }
            this.j = true;
            return;
        }
        Arrays.fill(this.g, true);
        this.d.u.e = new int[this.g.length];
        while (i2 < this.g.length) {
            this.d.u.e[i2] = i2;
            i2++;
        }
    }

    private String a(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        if (i2 < 0 || i2 >= 10) {
            sb.append(i2);
        } else {
            sb.append("0" + i2);
        }
        sb.append(":");
        if (i3 < 0 || i3 >= 10) {
            sb.append(i3);
        } else {
            sb.append("0" + i3);
        }
        return sb.toString();
    }
}
