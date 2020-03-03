package com.xiaomi.smarthome.scene.timer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.redpoint.ServerTimerManager;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.scene.timer.TimerCommonManager;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;

public class CountDownTimerStartActivity extends BaseActivity implements TimerCommonManager.PlugSceneListener {
    public static final int MAX_MINUTE_COUNT_DOWN = 1440;

    /* renamed from: a  reason: collision with root package name */
    private static final int f21667a = 1;
    private static final int b = 2;
    private static final int c = 20000;
    private View d;
    /* access modifiers changed from: private */
    public XQProgressDialog e;
    private TextView f;
    private TextView g;
    private ImageView h;
    /* access modifiers changed from: private */
    public TimerCommonManager i = null;
    private Device j;
    private String k;
    /* access modifiers changed from: private */
    public MyHandler l = null;
    /* access modifiers changed from: private */
    public PlugTimer m = null;
    /* access modifiers changed from: private */
    public PlugTimer n = null;
    private boolean o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private View.OnClickListener v = new View.OnClickListener() {
        public void onClick(View view) {
            CountDownTimerStartActivity.this.e.show();
            CountDownTimerStartActivity.this.i.a(CountDownTimerStartActivity.this.m, (TimerCommonManager.PlugSceneListener) new TimerCommonManager.PlugSceneListener() {
                public void onGetSceneFailed(int i) {
                }

                public void onGetSceneSuccess() {
                }

                public void onSetSceneSuccess(CommonTimer commonTimer) {
                    CountDownTimerStartActivity.this.l.removeMessages(1);
                    CountDownTimerStartActivity.this.finish();
                    CountDownTimerStartActivity.this.a();
                }

                public void onSetSceneFailed(Error error) {
                    CountDownTimerStartActivity.this.e.dismiss();
                    ToastUtil.a((Context) CountDownTimerStartActivity.this, (int) R.string.set_timer_fail_delete, 0);
                }
            });
        }
    };
    private int w;
    private Drawable[] x = new Drawable[3];

    /* access modifiers changed from: private */
    public void c() {
    }

    /* access modifiers changed from: private */
    public void k() {
    }

    public void onGetSceneFailed(int i2) {
    }

    public void onSetSceneFailed(Error error) {
    }

    public void onSetSceneSuccess(CommonTimer commonTimer) {
    }

    /* access modifiers changed from: private */
    public void a() {
        Intent intent = new Intent(this, CountDownTimerActivity.class);
        intent.putExtra(TimerCommonManager.j, this.k);
        intent.putExtra(TimerCommonManager.o, this.p);
        intent.putExtra(TimerCommonManager.p, this.q);
        intent.putExtra(TimerCommonManager.k, this.r);
        intent.putExtra(TimerCommonManager.m, this.s);
        intent.putExtra(TimerCommonManager.q, this.o);
        intent.putExtra(TimerCommonManager.h, this.u);
        intent.putExtra(TimerCommonManager.i, this.t);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_count_down_start_v2);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra(TimerCommonManager.j);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.k = stringExtra;
            this.j = SmartHomeDeviceManager.a().b(stringExtra);
            if (this.j != null) {
                this.o = intent.getBooleanExtra(TimerCommonManager.q, false);
                this.m = (PlugTimer) intent.getParcelableExtra(TimerCommonManager.l);
                if (this.m != null && this.m.b) {
                    this.p = intent.getStringExtra(TimerCommonManager.o);
                    this.r = intent.getStringExtra(TimerCommonManager.k);
                    this.q = intent.getStringExtra(TimerCommonManager.p);
                    this.s = intent.getStringExtra(TimerCommonManager.m);
                    this.u = intent.getStringExtra(TimerCommonManager.h);
                    this.t = intent.getStringExtra(TimerCommonManager.i);
                    if (TextUtils.isEmpty(this.t)) {
                        this.t = "display_name";
                    }
                    this.n = (PlugTimer) this.m.clone();
                    b();
                    this.l = new MyHandler(this, getMainLooper());
                    g();
                    if (this.m == null || !this.m.b) {
                        d();
                    } else {
                        c();
                        this.l.sendEmptyMessageDelayed(1, 20000);
                    }
                    this.i = TimerCommonManager.i();
                    this.i.a(this.j, "timer_name", this.t);
                    this.i.a((TimerCommonManager.PlugSceneListener) this);
                    f();
                }
            }
        }
    }

    private void b() {
        e();
        this.d = findViewById(R.id.view_count_down);
        this.g = (TextView) findViewById(R.id.count_down_tip);
        this.h = (ImageView) findViewById(R.id.iv_count_down_start);
        this.w = h();
        a(this.d, 100, a(this.w));
        this.f = (TextView) findViewById(R.id.button_cancel);
        this.f.setOnClickListener(this.v);
    }

    private void a(int i2, Device device) {
        CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
        Calendar instance = Calendar.getInstance();
        instance.add(12, i2);
        long c2 = ServerTimerManager.a((Context) this).c();
        if (ServerTimerManager.a((Context) this).b()) {
            instance.add(12, (int) (c2 / 60000));
        } else {
            ServerTimerManager.a((Context) this).a();
        }
        corntabParam.e = instance.get(2) + 1;
        corntabParam.d = instance.get(5);
        corntabParam.c = instance.get(11);
        corntabParam.b = instance.get(12);
        boolean z = this.o;
        final PlugTimer plugTimer = this.m == null ? new PlugTimer() : this.m;
        plugTimer.f21674a = true;
        plugTimer.b = true;
        if (z) {
            plugTimer.c = false;
            plugTimer.f = true;
            plugTimer.h = corntabParam;
        } else {
            plugTimer.c = true;
            plugTimer.e = corntabParam;
            plugTimer.f = false;
        }
        plugTimer.d = plugTimer.c;
        plugTimer.g = plugTimer.f;
        plugTimer.k = "1";
        this.e.show();
        this.i.a(this.n, plugTimer, this.p, this.r, this.q, this.s, new TimerCommonManager.PlugSceneListener() {
            public void onGetSceneSuccess() {
            }

            public void onSetSceneSuccess(CommonTimer commonTimer) {
                CountDownTimerStartActivity.this.e.dismiss();
                PlugTimer unused = CountDownTimerStartActivity.this.n = plugTimer;
                PlugTimer unused2 = CountDownTimerStartActivity.this.m = plugTimer;
                CountDownTimerStartActivity.this.c();
                CountDownTimerStartActivity.this.l.sendEmptyMessageDelayed(1, 20000);
            }

            public void onSetSceneFailed(Error error) {
                CountDownTimerStartActivity.this.e.dismiss();
                ToastUtil.a((Context) CountDownTimerStartActivity.this, (int) R.string.plug_timer_set_fail, 0);
            }

            public void onGetSceneFailed(int i) {
                ToastUtil.a((Context) CountDownTimerStartActivity.this, (int) R.string.plug_timer_get_fail, 0);
            }
        }, this.u);
    }

    public boolean detectConflicting(int i2, List<PlugTimer> list) {
        List<Pair<Float, Float>> a2;
        if (list == null || list.size() == 0 || (a2 = TimerCommonManager.a(list)) == null || a2.isEmpty()) {
            return false;
        }
        boolean z = this.o;
        float a3 = TimerCommonManager.a(Calendar.getInstance()) + ((((float) i2) + (((float) ServerTimerManager.a((Context) this).c()) / 60000.0f)) / 1440.0f);
        for (Pair next : a2) {
            if (a3 < ((Float) next.first).floatValue() && a3 + 6.9444446E-4f >= ((Float) next.first).floatValue()) {
                return true;
            }
            if (a3 > ((Float) next.first).floatValue() && a3 - 6.9444446E-4f <= ((Float) next.first).floatValue()) {
                return true;
            }
            if (a3 < ((Float) next.second).floatValue() && a3 + 6.9444446E-4f >= ((Float) next.second).floatValue()) {
                return true;
            }
            if (a3 > ((Float) next.second).floatValue() && a3 - 6.9444446E-4f <= ((Float) next.second).floatValue()) {
                return true;
            }
        }
        return false;
    }

    private void d() {
        if (this.m != null) {
            this.m.b = false;
        }
        this.l.removeMessages(1);
    }

    private void e() {
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CountDownTimerStartActivity.this.finish();
                }
            });
        }
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.plug_timer_set_countdown);
    }

    private void f() {
        this.e = new XQProgressDialog(this);
        this.e.setMessage(getString(R.string.gateway_magnet_location_updating));
        this.e.setCancelable(true);
    }

    public void onGetSceneSuccess() {
        if (!isFinishing()) {
            this.e.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.m != null) {
            if (!this.m.b) {
                this.l.removeMessages(1);
            }
            int h2 = h();
            if (this.w == 0) {
                a(this.d, 0, getResources().getString(R.string.count_down_end));
                this.h.setImageResource(R.drawable.count_down_shape_end);
                this.f.setVisibility(8);
            } else if (h2 >= 0) {
                a(this.d, (h2 * 100) / 60, a(h2));
                if (h2 == 0) {
                    a(this.d, 0, getResources().getString(R.string.count_down_end));
                    this.h.setImageResource(R.drawable.count_down_shape_end);
                    this.f.setVisibility(8);
                    this.l.removeMessages(1);
                    this.l.postDelayed(new Runnable() {
                        public void run() {
                            CountDownTimerStartActivity.this.finish();
                            CountDownTimerStartActivity.this.a();
                        }
                    }, 500);
                }
            } else {
                a(this.d, 0, getResources().getString(R.string.count_down_end));
                this.h.setImageResource(R.drawable.count_down_shape_end);
                this.l.removeMessages(1);
            }
        }
    }

    private int h() {
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(12) + (instance.get(11) * 60);
        CorntabUtils.CorntabParam j2 = j();
        return ((((j2.c * 60) + j2.b) - i2) - (((int) ServerTimerManager.a((Context) this).c()) / 60000)) + (instance.get(5) == j2.d ? 0 : 1440);
    }

    private int i() {
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(13) + (instance.get(12) * 60) + (instance.get(11) * 60 * 60);
        CorntabUtils.CorntabParam j2 = j();
        long c2 = ServerTimerManager.a((Context) this).c();
        int i3 = instance.get(5);
        int i4 = 0;
        int i5 = ((((j2.b * 60) + 0) + ((j2.c * 60) * 60)) - i2) - (((int) c2) / 1000);
        if (i3 != j2.d) {
            i4 = 86400;
        }
        return i5 + i4;
    }

    private CorntabUtils.CorntabParam j() {
        return this.o ? this.m.h : this.m.e;
    }

    private String a(int i2) {
        int i3;
        if (i2 == 0) {
            return getString(R.string.count_down_end);
        }
        int i4 = i2 < 60 ? i2 : 0;
        if (i2 >= 60) {
            i4 = i2 % 60;
            i3 = i2 / 60;
        } else {
            i3 = 0;
        }
        String str = null;
        String quantityString = i3 > 0 ? getResources().getQuantityString(R.plurals.automation_hour, i3, new Object[]{Integer.valueOf(i3)}) : null;
        if (i4 > 0) {
            str = getResources().getQuantityString(R.plurals.count_down_minute, i4, new Object[]{Integer.valueOf(i4)});
        }
        if (this.o) {
            Object[] objArr = new Object[2];
            if (TextUtils.isEmpty(quantityString)) {
                quantityString = "";
            }
            objArr[0] = quantityString;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr[1] = str;
            return getString(R.string.count_down_timer_hint_off, objArr);
        }
        Object[] objArr2 = new Object[2];
        if (TextUtils.isEmpty(quantityString)) {
            quantityString = "";
        }
        objArr2[0] = quantityString;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        objArr2[1] = str;
        return getString(R.string.count_down_timer_hint_on, objArr2);
    }

    private void a(View view, int i2, String str) {
        if (i2 > 100) {
            i2 = 100;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(-1);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        this.x[2] = shapeDrawable;
        float f2 = (i2 < 0 || i2 > 100) ? 0.0f : ((float) i2) * 3.6f;
        int a2 = DisplayUtils.a(10.0f);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new ArcShape(-90.0f, f2));
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        if (f2 == 0.0f) {
            shapeDrawable2.getPaint().setColor(0);
        } else {
            shapeDrawable2.getPaint().setColor(Color.parseColor("#32BAC0"));
        }
        this.x[0] = shapeDrawable2;
        ShapeDrawable shapeDrawable3 = new ShapeDrawable(new ArcShape(f2 - 90.0f, 360.0f - f2));
        if (f2 == 360.0f) {
            shapeDrawable3.getPaint().setColor(0);
        } else {
            shapeDrawable3.getPaint().setColor(Color.parseColor("#f2f2f2"));
        }
        shapeDrawable3.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        this.x[1] = shapeDrawable3;
        LayerDrawable layerDrawable = new LayerDrawable(this.x);
        layerDrawable.setLayerInset(2, a2, a2, a2, a2);
        view.setBackground(layerDrawable);
        int indexOf = str.indexOf("\n");
        if (indexOf < 0 || indexOf > str.length()) {
            this.g.setText(str);
            this.g.setTextColor(Color.parseColor("#CCCCCC"));
            return;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(15, true), indexOf, str.length(), 33);
        this.g.setText(spannableString);
        this.g.setTextColor(Color.parseColor("#32BAC0"));
    }

    private static class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<CountDownTimerStartActivity> f21673a;

        public MyHandler(CountDownTimerStartActivity countDownTimerStartActivity, Looper looper) {
            super(looper);
            this.f21673a = new WeakReference<>(countDownTimerStartActivity);
        }

        public void handleMessage(Message message) {
            CountDownTimerStartActivity countDownTimerStartActivity = (CountDownTimerStartActivity) this.f21673a.get();
            if (a(countDownTimerStartActivity)) {
                switch (message.what) {
                    case 1:
                        countDownTimerStartActivity.g();
                        countDownTimerStartActivity.l.sendEmptyMessageDelayed(1, 20000);
                        return;
                    case 2:
                        countDownTimerStartActivity.k();
                        return;
                    default:
                        return;
                }
            }
        }

        private boolean a(CountDownTimerStartActivity countDownTimerStartActivity) {
            if (countDownTimerStartActivity == null || countDownTimerStartActivity.isFinishing()) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 17 || !countDownTimerStartActivity.isDestroyed()) {
                return true;
            }
            return false;
        }
    }
}
