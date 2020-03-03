package com.xiaomi.smarthome.scene.timer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.ChooseTimeDialog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.scene.timer.TimerCommonManager;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import org.jetbrains.annotations.NotNull;

public class SetTimerCommonActivity extends BaseActivity implements TimerCommonManager.PlugSceneListener {
    public static final String BOTH_TIMER_MUST_BE_SET = "both_timer_must_be_set";
    private static final int L = 604800000;
    private static final int M = 86400;
    private static final int N = 3600;
    private static final int O = 60;
    protected static final String TAG = "SetTimerCommonActivity";
    public static final String TIMER_TIPS_OFF_TIMER = "off_timer_tips";
    public static final String TIMER_TIPS_ON_TIMER = "on_timer_tips";
    public static final int TIMER_TYPE_EVERYDAY = 1;
    public static final int TIMER_TYPE_INTERNATION_SELF_DEFINE = 2;
    public static final int TIMER_TYPE_ONCE = 0;
    public static final int TIMER_TYPE_SELF_DEFINE = 4;
    public static final int TIMER_TYPE_WEEKEND = 3;
    public static final int TIMER_TYPE_WORKDAY = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f21675a = "current.timer";
    private static final String b = "original.timer";
    private static final int c = 1;
    private static final int d = 2;
    private String A;
    private String B;
    /* access modifiers changed from: private */
    public String C;
    private XQProgressDialog D;
    private AnimationDrawable E;
    private ImageView F;
    private String G;
    /* access modifiers changed from: private */
    public boolean H = true;
    /* access modifiers changed from: private */
    public String I;
    /* access modifiers changed from: private */
    public String J;
    private MyHandler K = new MyHandler(this);
    /* access modifiers changed from: private */
    public TimerCommonManager e;
    private boolean f;
    private View g;
    /* access modifiers changed from: private */
    public TextView h;
    private int i;
    private View j;
    private View k;
    private TextView l;
    private TextView m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    public boolean o;
    public String offMethod;
    public String offParams;
    public String onMethod;
    public String onParams;
    /* access modifiers changed from: private */
    public boolean[] p;
    /* access modifiers changed from: private */
    public CorntabUtils.CorntabParam q;
    private View r;
    private View s;
    private TextView t;
    private TextView u;
    /* access modifiers changed from: private */
    public boolean v;
    /* access modifiers changed from: private */
    public boolean w;
    /* access modifiers changed from: private */
    public CorntabUtils.CorntabParam x;
    /* access modifiers changed from: private */
    public CommonTimer y;
    private boolean z;

    public void onGetSceneFailed(int i2) {
    }

    public void onGetSceneSuccess() {
    }

    private static class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<SetTimerCommonActivity> f21689a;

        public MyHandler(SetTimerCommonActivity setTimerCommonActivity) {
            this.f21689a = new WeakReference<>(setTimerCommonActivity);
        }

        public void handleMessage(Message message) {
            SetTimerCommonActivity setTimerCommonActivity = (SetTimerCommonActivity) this.f21689a.get();
            if (setTimerCommonActivity != null) {
                switch (message.what) {
                    case 1:
                        CommonTimer commonTimer = (CommonTimer) message.obj;
                        if (!(commonTimer == null || commonTimer.q == 0)) {
                            ToastUtil.a((Context) setTimerCommonActivity, (int) R.string.about_check_update_new, 1);
                        }
                        setTimerCommonActivity.a();
                        setTimerCommonActivity.finish();
                        return;
                    case 2:
                        setTimerCommonActivity.a();
                        ToastUtil.a((int) R.string.intelligent_plug_failed);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        this.E.stop();
        this.F.setVisibility(4);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.E.start();
        this.F.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.plug_set_timer_activity);
        Intent intent = getIntent();
        if (intent.getBooleanExtra("is_group", false)) {
            this.e = TimerCommonGroupManager.a();
        } else {
            this.e = TimerCommonManager.i();
        }
        this.F = (ImageView) findViewById(R.id.loading);
        this.E = (AnimationDrawable) this.F.getDrawable();
        this.f = intent.getBooleanExtra(TimerCommonManager.f, false);
        this.y = (CommonTimer) intent.getParcelableExtra(TimerCommonManager.e);
        this.G = intent.getStringExtra(TimerCommonManager.r);
        this.i = intent.getIntExtra(TimerCommonManager.w, 0);
        if (this.y == null) {
            Log.e(TAG, "error get timer==null");
            finish();
        }
        this.C = intent.getStringExtra(TimerCommonManager.h);
        this.H = intent.getBooleanExtra("both_timer_must_be_set", false);
        this.I = intent.getStringExtra("on_timer_tips");
        this.J = intent.getStringExtra("off_timer_tips");
        c();
        g();
        this.p = (boolean[]) this.y.h.g.clone();
        this.g = findViewById(R.id.view_repeat);
        this.h = (TextView) findViewById(R.id.tv_repeat_detail);
        this.j = findViewById(R.id.view_timer_on);
        this.k = findViewById(R.id.timer_on_divider);
        this.l = (TextView) findViewById(R.id.tv_timer_on);
        if (!TextUtils.isEmpty(this.I)) {
            this.l.setText(this.I);
        }
        this.m = (TextView) findViewById(R.id.tv_timer_on_detail);
        this.r = findViewById(R.id.view_timer_off);
        this.s = findViewById(R.id.timer_off_divider);
        this.t = (TextView) findViewById(R.id.tv_timer_off);
        if (!TextUtils.isEmpty(this.J)) {
            this.t.setText(this.J);
        }
        this.u = (TextView) findViewById(R.id.tv_timer_off_detail);
        String c2 = this.q.c((Context) this);
        if (c2.equals(getString(R.string.plug_timer_today)) || c2.equals(getString(R.string.plug_timer_tomorrow))) {
            c2 = getString(R.string.plug_timer_onetime);
        }
        this.h.setText(c2);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetTimerCommonActivity.this.a((CoreApi.a().D() || TimerCommonManager.i().j()) ? new String[]{SetTimerCommonActivity.this.getString(R.string.plug_timer_onetime), SetTimerCommonActivity.this.getString(R.string.plug_timer_everyday), SetTimerCommonActivity.this.getString(R.string.plug_timer_sef_define)} : new String[]{SetTimerCommonActivity.this.getString(R.string.plug_timer_onetime), SetTimerCommonActivity.this.getString(R.string.plug_timer_everyday), SetTimerCommonActivity.this.getString(R.string.plug_timer_statutory_workingday), SetTimerCommonActivity.this.getString(R.string.plug_timer_statutory_holidays), SetTimerCommonActivity.this.getString(R.string.plug_timer_sef_define)});
            }
        });
        if (this.n && this.v) {
            this.m.setText(PlugTimer.a(getContext(), this.q, this.x, this.n, this.v, this.q.c, this.q.b, true));
            this.u.setText(PlugTimer.a(getContext(), this.q, this.x, this.n, this.v, this.x.c, this.x.b, false));
            this.o = true;
            this.w = true;
        } else if (this.n && !this.v) {
            this.m.setText(PlugTimer.a(getContext(), this.q, this.x, this.n, this.v, this.q.c, this.q.b, true));
            this.o = true;
            this.u.setText(R.string.plug_timer_no_set);
            this.w = false;
        } else if (!this.n && this.v) {
            this.m.setText(R.string.plug_timer_no_set);
            this.o = false;
            this.u.setText(PlugTimer.a(getContext(), this.q, this.x, this.n, this.v, this.x.c, this.x.b, false));
            this.w = true;
        } else if (!this.n && !this.v) {
            this.m.setText(R.string.plug_timer_no_set);
            this.u.setText(R.string.plug_timer_no_set);
            this.o = false;
            this.w = false;
        }
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetTimerCommonActivity.this.f();
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetTimerCommonActivity.this.e();
            }
        });
        if (TextUtils.isEmpty(this.onMethod) || this.i == 2) {
            this.j.setVisibility(8);
            this.k.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.offMethod) || this.i == 1) {
            this.r.setVisibility(8);
            this.s.setVisibility(8);
        }
        d();
        this.e.a((TimerCommonManager.PlugSceneListener) this);
        if (this.i == 2) {
            this.r.performClick();
        } else {
            this.j.performClick();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.e.b((TimerCommonManager.PlugSceneListener) this);
        this.K.removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void c() {
        this.z = this.y.d;
        this.B = this.y.b;
        this.A = this.y.f21632a;
        this.onMethod = this.y.f;
        this.onParams = this.y.g;
        this.offMethod = this.y.j;
        this.offParams = this.y.k;
        this.n = this.y.e;
        this.q = (CorntabUtils.CorntabParam) this.y.h.clone();
        this.v = this.y.i;
        this.x = (CorntabUtils.CorntabParam) this.y.l.clone();
    }

    private void d() {
        this.D = new XQProgressDialog(this);
        this.D.setMessage(getString(R.string.gateway_magnet_location_updating));
        this.D.setCancelable(true);
    }

    /* access modifiers changed from: private */
    public void e() {
        final ChooseTimeDialog chooseTimeDialog = new ChooseTimeDialog(this, this.x, this.q, this.x, this.n, this.v, false);
        chooseTimeDialog.setCancelable(true);
        chooseTimeDialog.a(R.string.ok_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                TimePicker a2 = chooseTimeDialog.a();
                if (a2 != null) {
                    boolean unused = SetTimerCommonActivity.this.v = true;
                    boolean unused2 = SetTimerCommonActivity.this.w = true;
                    SetTimerCommonActivity.this.x.c = a2.getCurrentHour().intValue();
                    SetTimerCommonActivity.this.x.b = a2.getCurrentMinute().intValue();
                    SetTimerCommonActivity.this.i();
                }
            }
        });
        chooseTimeDialog.b(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        chooseTimeDialog.show();
        chooseTimeDialog.a(R.string.plug_timer_off_time);
    }

    /* access modifiers changed from: private */
    public void f() {
        final ChooseTimeDialog chooseTimeDialog = new ChooseTimeDialog(this, this.q, this.q, this.x, this.n, this.v, true);
        chooseTimeDialog.setCancelable(true);
        chooseTimeDialog.a(R.string.ok_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                TimePicker a2 = chooseTimeDialog.a();
                if (a2 != null) {
                    boolean unused = SetTimerCommonActivity.this.n = true;
                    boolean unused2 = SetTimerCommonActivity.this.o = true;
                    SetTimerCommonActivity.this.q.c = a2.getCurrentHour().intValue();
                    SetTimerCommonActivity.this.q.b = a2.getCurrentMinute().intValue();
                    SetTimerCommonActivity.this.i();
                }
            }
        });
        chooseTimeDialog.b(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        chooseTimeDialog.show();
        chooseTimeDialog.a(R.string.plug_timer_on_time);
    }

    private void g() {
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_return_btn);
        imageView.setImageResource(R.drawable.title_cancel_selector);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SetTimerCommonActivity.this.k()) {
                        SetTimerCommonActivity.this.j();
                    } else {
                        SetTimerCommonActivity.this.finish();
                    }
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (!TextUtils.isEmpty(this.G)) {
            textView.setText(this.G);
        } else if (this.n && this.v) {
            textView.setText(R.string.plug_timer_type_period);
        } else if (this.n) {
            textView.setText(R.string.plug_timer_type_on);
        } else if (this.v) {
            textView.setText(R.string.plug_timer_type_off);
        }
        View findViewById = findViewById(R.id.module_a_3_right_iv_confirm_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String str;
                    String str2;
                    CommonTimer access$1300 = SetTimerCommonActivity.this.l();
                    if (!SetTimerCommonActivity.this.e.a(access$1300)) {
                        ToastUtil.a((int) R.string.plug_timer_offtime_illegal);
                    } else if (!SetTimerCommonActivity.this.o && !SetTimerCommonActivity.this.w) {
                        ToastUtil.a((int) R.string.plug_timer_unset);
                    } else if (SetTimerCommonActivity.this.H && (!access$1300.i || !access$1300.e)) {
                        Resources resources = SetTimerCommonActivity.this.getResources();
                        if (!access$1300.i) {
                            String string = resources.getString(R.string.plug_timer_off_time);
                            if (TextUtils.isEmpty(SetTimerCommonActivity.this.J)) {
                                str2 = resources.getString(R.string.timer_must_be_set, new Object[]{string});
                            } else {
                                str2 = resources.getString(R.string.timer_must_be_set, new Object[]{SetTimerCommonActivity.this.J});
                            }
                            ToastUtil.a((CharSequence) str2);
                        } else if (!access$1300.e) {
                            String string2 = resources.getString(R.string.plug_timer_on_time);
                            if (TextUtils.isEmpty(SetTimerCommonActivity.this.I)) {
                                str = resources.getString(R.string.timer_must_be_set, new Object[]{string2});
                            } else {
                                str = resources.getString(R.string.timer_must_be_set, new Object[]{SetTimerCommonActivity.this.I});
                            }
                            ToastUtil.a((CharSequence) str);
                        }
                    } else if (SetTimerCommonActivity.this.a(access$1300)) {
                        ToastUtil.a((int) R.string.plug_timer_has_setted);
                    } else {
                        SetTimerCommonActivity.this.b();
                        SetTimerCommonActivity.this.e.a(SetTimerCommonActivity.this.y, access$1300, SetTimerCommonActivity.this.C);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean a(CommonTimer commonTimer) {
        for (CommonTimer next : this.e.c()) {
            if (next.e == commonTimer.e && next.i == commonTimer.i) {
                if (!next.e || (next.h.a((Object) commonTimer.h) && TextUtils.equals(next.h.h, commonTimer.h.h))) {
                    if (!next.i) {
                        return true;
                    }
                    if (next.l.a((Object) commonTimer.l) && TextUtils.equals(next.l.h, commonTimer.l.h)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void a(String[] strArr) {
        int i2;
        switch (this.q.d()) {
            case 0:
                i2 = 0;
                break;
            case 1:
                i2 = 1;
                break;
            case 4:
                i2 = strArr.length;
                break;
            case 5:
                i2 = 2;
                break;
            case 6:
                i2 = 3;
                break;
            default:
                i2 = -1;
                break;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.plug_timer_repeat_selection).a((CharSequence[]) strArr, i2, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SetTimerCommonActivity.this.a(dialogInterface, i);
            }
        }).b().show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        String str;
        if (CoreApi.a().D() || TimerCommonManager.i().j()) {
            str = a(i2);
        } else {
            str = b(i2);
        }
        this.h.setText(str);
        i();
        dialogInterface.cancel();
    }

    @NotNull
    private String a(int i2) {
        switch (i2) {
            case 0:
                this.q.a(0);
                this.x.a(0);
                this.q.h = "";
                this.x.h = "";
                break;
            case 1:
                this.q.a(127);
                this.x.a(127);
                this.q.h = "";
                this.x.h = "";
                break;
            case 2:
                h();
                break;
        }
        String c2 = this.q.c((Context) this);
        return (c2.equals(getString(R.string.plug_timer_today)) || c2.equals(getString(R.string.plug_timer_tomorrow))) ? getString(R.string.plug_timer_onetime) : c2;
    }

    @NotNull
    private String b(int i2) {
        switch (i2) {
            case 0:
                this.q.a(0);
                this.x.a(0);
                this.q.h = "";
                this.x.h = "";
                break;
            case 1:
                this.q.a(127);
                this.x.a(127);
                this.q.h = "";
                this.x.h = "";
                break;
            case 2:
                this.q.a(127);
                this.x.a(127);
                this.q.h = CorntabUtils.p;
                this.x.h = CorntabUtils.p;
                break;
            case 3:
                this.q.a(127);
                this.x.a(127);
                this.q.h = CorntabUtils.q;
                this.x.h = CorntabUtils.q;
                break;
            case 4:
                h();
                break;
        }
        String c2 = this.q.c((Context) this);
        return (c2.equals(getString(R.string.plug_timer_today)) || c2.equals(getString(R.string.plug_timer_tomorrow))) ? getString(R.string.plug_timer_onetime) : c2;
    }

    private void h() {
        this.p = (boolean[]) this.q.g.clone();
        new MLAlertDialog.Builder(this).a((int) R.string.plug_timer_custom_repeat).a(getResources().getTextArray(R.array.weekday_short), this.p, (DialogInterface.OnMultiChoiceClickListener) new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                SetTimerCommonActivity.this.p[i] = z;
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int i2 = 0; i2 < SetTimerCommonActivity.this.q.g.length; i2++) {
                    SetTimerCommonActivity.this.p[i2] = SetTimerCommonActivity.this.q.g[i2];
                }
            }
        }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean z = false;
                for (boolean z2 : SetTimerCommonActivity.this.p) {
                    z |= z2;
                }
                if (z) {
                    for (int i2 = 0; i2 < SetTimerCommonActivity.this.p.length; i2++) {
                        SetTimerCommonActivity.this.q.a(i2, SetTimerCommonActivity.this.p[i2]);
                        SetTimerCommonActivity.this.x.a(i2, SetTimerCommonActivity.this.p[i2]);
                    }
                    SetTimerCommonActivity.this.q.h = "";
                    SetTimerCommonActivity.this.x.h = "";
                    SetTimerCommonActivity.this.h.setText(SetTimerCommonActivity.this.q.c((Context) SetTimerCommonActivity.this));
                    SetTimerCommonActivity.this.i();
                }
            }
        }).d();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(b, this.y);
        bundle.putParcelable(f21675a, l());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        CommonTimer commonTimer = (CommonTimer) bundle.getParcelable(b);
        if (commonTimer != null) {
            this.y = commonTimer;
        }
        CommonTimer commonTimer2 = (CommonTimer) bundle.getParcelable(f21675a);
        if (commonTimer2 != null) {
            b(commonTimer2);
        }
    }

    private void b(CommonTimer commonTimer) {
        this.z = commonTimer.d;
        this.B = commonTimer.b;
        this.onMethod = commonTimer.f;
        this.onParams = commonTimer.g;
        this.A = commonTimer.f21632a;
        this.offMethod = commonTimer.j;
        this.offParams = commonTimer.k;
        this.n = commonTimer.e;
        this.q = commonTimer.h;
        this.v = commonTimer.i;
        this.x = commonTimer.l;
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.n) {
            this.m.setText(PlugTimer.a(getContext(), this.q, this.x, this.n, this.v, this.q.c, this.q.b, true));
        }
        if (this.v) {
            this.u.setText(PlugTimer.a(getContext(), this.q, this.x, this.n, this.v, this.x.c, this.x.b, false));
        }
    }

    public void onBackPressed() {
        if (k()) {
            j();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        new MLAlertDialog.Builder(this).a((int) R.string.plug_dialog_confirm_abort).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.plug_confirm_abort, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SetTimerCommonActivity.this.finish();
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public boolean k() {
        return this.y.e != this.n || !this.y.h.equals(this.q) || this.y.i != this.v || !this.y.l.equals(this.x);
    }

    private void c(CommonTimer commonTimer) {
        boolean z2;
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        int i2 = instance.get(11);
        int i3 = instance.get(12);
        if (!commonTimer.e || (i2 * 60) + i3 < (commonTimer.h.c * 60) + commonTimer.h.b) {
            commonTimer.h.d = instance2.get(5);
            commonTimer.h.e = instance2.get(2) + 1;
            z2 = false;
        } else {
            instance2.setTimeInMillis(instance.getTimeInMillis() + 86400000);
            commonTimer.h.d = instance2.get(5);
            commonTimer.h.e = instance2.get(2) + 1;
            z2 = true;
        }
        Calendar instance3 = Calendar.getInstance();
        if (z2 || (commonTimer.i && (i2 * 60) + i3 >= (commonTimer.l.c * 60) + commonTimer.l.b)) {
            instance3.setTimeInMillis(instance.getTimeInMillis() + 86400000);
            commonTimer.l.d = instance3.get(5);
            commonTimer.l.e = instance3.get(2) + 1;
            return;
        }
        commonTimer.l.d = instance3.get(5);
        commonTimer.l.e = instance3.get(2) + 1;
    }

    /* access modifiers changed from: private */
    public CommonTimer l() {
        CommonTimer commonTimer = new CommonTimer();
        commonTimer.b = this.B;
        commonTimer.f = this.onMethod;
        commonTimer.g = this.onParams;
        commonTimer.f21632a = this.A;
        commonTimer.j = this.offMethod;
        commonTimer.k = this.offParams;
        commonTimer.d = true;
        commonTimer.h = (CorntabUtils.CorntabParam) this.q.clone();
        if (this.i == 2) {
            commonTimer.e = false;
            commonTimer.i = true;
        }
        if (this.i == 1) {
            commonTimer.e = true;
            commonTimer.i = false;
        }
        if (this.i == 0) {
            commonTimer.e = this.n;
            commonTimer.i = this.v;
        }
        commonTimer.l = (CorntabUtils.CorntabParam) this.x.clone();
        if (this.q.d() == 0) {
            c(commonTimer);
        }
        return commonTimer;
    }

    public void onSetSceneSuccess(CommonTimer commonTimer) {
        Message obtainMessage = this.K.obtainMessage(1);
        obtainMessage.obj = commonTimer;
        this.K.sendMessage(obtainMessage);
    }

    public void onSetSceneFailed(Error error) {
        this.K.sendMessage(this.K.obtainMessage(2));
    }
}
