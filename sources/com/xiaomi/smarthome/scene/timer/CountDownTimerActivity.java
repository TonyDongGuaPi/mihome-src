package com.xiaomi.smarthome.scene.timer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.redpoint.ServerTimerManager;
import com.xiaomi.smarthome.library.common.dialog.ChooseTimeDialog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CountDownWidget;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miniprogram.EditController;
import com.xiaomi.smarthome.miniprogram.EditModeChangedListener;
import com.xiaomi.smarthome.scene.timer.TimerCommonManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class CountDownTimerActivity extends BaseActivity implements EditModeChangedListener, TimerCommonManager.PlugSceneListener {
    public static final int MAX_MINUTE_COUNT_DOWN = 1440;
    private static final int s = 1;
    private static final int t = 2;
    private static final int u = 20000;
    /* access modifiers changed from: private */
    public int A;
    /* access modifiers changed from: private */
    public MyAdapter B;
    private boolean C = true;
    /* access modifiers changed from: private */
    public String D;
    /* access modifiers changed from: private */
    public List<Integer> E = Collections.emptyList();
    /* access modifiers changed from: private */
    public View.OnClickListener F = new View.OnClickListener() {
        public void onClick(View view) {
            CountDownTimerActivity.this.b.show();
            CountDownTimerActivity.this.l.a(CountDownTimerActivity.this.n, (TimerCommonManager.PlugSceneListener) new TimerCommonManager.PlugSceneListener() {
                public void onGetSceneFailed(int i) {
                }

                public void onGetSceneSuccess() {
                }

                public void onSetSceneSuccess(CommonTimer commonTimer) {
                    PlugTimer unused = CountDownTimerActivity.this.n = null;
                    CountDownTimerActivity.this.b.dismiss();
                    CountDownTimerActivity.this.f21654a.setCurrentValue(0);
                    CountDownTimerActivity.this.f21654a.setSeekArcThumbOff();
                    CountDownTimerActivity.this.c.setVisibility(8);
                    CountDownTimerActivity.this.d.setVisibility(8);
                    CountDownTimerActivity.this.e.setVisibility(0);
                    CountDownTimerActivity.this.r.removeMessages(1);
                }

                public void onSetSceneFailed(Error error) {
                    CountDownTimerActivity.this.b.dismiss();
                    ToastUtil.a((int) R.string.set_timer_fail_delete);
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public View.OnClickListener G = new View.OnClickListener() {
        public void onClick(View view) {
            if (CountDownTimerActivity.this.n == null || !CountDownTimerActivity.this.n.b) {
                CountDownTimerActivity.this.j();
                return;
            }
            CountDownTimerActivity.this.b.show();
            CountDownTimerActivity.this.n.b = false;
            CountDownTimerActivity.this.l.a(CountDownTimerActivity.this.m, CountDownTimerActivity.this.n, CountDownTimerActivity.this.v, CountDownTimerActivity.this.x, CountDownTimerActivity.this.w, CountDownTimerActivity.this.y, new TimerCommonManager.PlugSceneListener() {
                public void onGetSceneSuccess() {
                }

                public void onSetSceneSuccess(CommonTimer commonTimer) {
                    CountDownTimerActivity.this.b.dismiss();
                    CountDownTimerActivity.this.g();
                    PlugTimer unused = CountDownTimerActivity.this.m = CountDownTimerActivity.this.n;
                }

                public void onSetSceneFailed(Error error) {
                    CountDownTimerActivity.this.n.b = true;
                    CountDownTimerActivity.this.b.dismiss();
                    ToastUtil.a((int) R.string.plug_timer_set_fail);
                }

                public void onGetSceneFailed(int i) {
                    ToastUtil.a((int) R.string.plug_timer_get_fail);
                }
            }, CountDownTimerActivity.this.D);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CountDownWidget f21654a;
    /* access modifiers changed from: private */
    public XQProgressDialog b;
    /* access modifiers changed from: private */
    public TextView c;
    /* access modifiers changed from: private */
    public TextView d;
    /* access modifiers changed from: private */
    public TextView e;
    private ListView f;
    private boolean g;
    private String h;
    /* access modifiers changed from: private */
    public Device i;
    /* access modifiers changed from: private */
    public TimePicker j;
    private TextView k;
    /* access modifiers changed from: private */
    public TimerCommonManager l = null;
    /* access modifiers changed from: private */
    public PlugTimer m = null;
    View mCancelBtn;
    ImageView mIvSelectAll;
    /* access modifiers changed from: private */
    public PlugTimer n = null;
    private View o;
    private View p;
    private View q;
    /* access modifiers changed from: private */
    public MyHandler r = null;
    /* access modifiers changed from: private */
    public String v;
    /* access modifiers changed from: private */
    public String w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public String y;
    private String z;

    /* access modifiers changed from: private */
    public static /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
    }

    /* access modifiers changed from: private */
    public void e() {
    }

    public void onSetSceneFailed(Error error) {
    }

    public void onSetSceneSuccess(CommonTimer commonTimer) {
    }

    static class MyAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        private int[] f21663a;
        private Context b;
        /* access modifiers changed from: private */
        public int c = -1;

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f21665a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f21665a = viewHolder;
                viewHolder.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.f13396tv, "field 'textView'", TextView.class);
                viewHolder.imageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv, "field 'imageView'", ImageView.class);
                viewHolder.checkbox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'checkbox'", CheckBox.class);
                viewHolder.ivPickerIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_picker_icon, "field 'ivPickerIcon'", ImageView.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f21665a;
                if (viewHolder != null) {
                    this.f21665a = null;
                    viewHolder.textView = null;
                    viewHolder.imageView = null;
                    viewHolder.checkbox = null;
                    viewHolder.ivPickerIcon = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        MyAdapter(List<Integer> list, Context context) {
            this.b = context;
            this.f21663a = new int[list.size()];
            int i = 0;
            for (Integer intValue : list) {
                this.f21663a[i] = intValue.intValue();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public void a(List<Integer> list) {
            this.f21663a = new int[list.size()];
            int i = 0;
            for (Integer intValue : list) {
                this.f21663a[i] = intValue.intValue();
                i++;
            }
        }

        public int getCount() {
            return this.f21663a.length + 1;
        }

        /* renamed from: a */
        public Integer getItem(int i) {
            if (i >= this.f21663a.length) {
                return Integer.MAX_VALUE;
            }
            return Integer.valueOf(this.f21663a[i]);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.list_item_countdown_minute, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (i >= this.f21663a.length) {
                viewHolder.textView.setText(R.string.customize_minute);
                viewHolder.imageView.setVisibility(0);
                viewHolder.checkbox.setVisibility(8);
            } else {
                viewHolder.imageView.setVisibility(8);
                if (EditController.a().c == 1) {
                    viewHolder.checkbox.setVisibility(8);
                } else {
                    viewHolder.checkbox.setVisibility(0);
                }
                if (EditController.a().d.get(i)) {
                    viewHolder.checkbox.setChecked(true);
                } else {
                    viewHolder.checkbox.setChecked(false);
                }
                int i2 = this.f21663a[i];
                if (i2 < 60) {
                    TextView textView = viewHolder.textView;
                    textView.setText(String.valueOf(i2) + this.b.getString(R.string.minute));
                } else {
                    StringBuilder sb = new StringBuilder();
                    int i3 = i2 / 60;
                    int i4 = i2 % 60;
                    sb.append(i3);
                    sb.append(this.b.getString(R.string.hour));
                    if (i4 > 0) {
                        sb.append(i4);
                        sb.append(this.b.getString(R.string.minute));
                    }
                    viewHolder.textView.setText(sb.toString());
                }
            }
            if (i >= this.f21663a.length || this.c != i) {
                viewHolder.textView.setTextColor(view.getResources().getColor(R.color.black));
                viewHolder.ivPickerIcon.setVisibility(8);
            } else {
                viewHolder.textView.setTextColor(view.getResources().getColor(R.color.class_text_33));
                viewHolder.ivPickerIcon.setVisibility(0);
            }
            return view;
        }

        class ViewHolder {
            @BindView(2131428355)
            CheckBox checkbox;
            @BindView(2131430066)
            ImageView imageView;
            @BindView(2131430201)
            ImageView ivPickerIcon;
            @BindView(2131433092)
            TextView textView;

            public ViewHolder(View view) {
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_count_down_timer);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.h = intent.getStringExtra(TimerCommonManager.j);
        this.i = SmartHomeDeviceManager.a().b(this.h);
        this.g = intent.getBooleanExtra(TimerCommonManager.q, false);
        this.v = intent.getStringExtra(TimerCommonManager.o);
        this.x = intent.getStringExtra(TimerCommonManager.k);
        this.w = intent.getStringExtra(TimerCommonManager.p);
        this.y = intent.getStringExtra(TimerCommonManager.m);
        this.z = intent.getStringExtra(TimerCommonManager.i);
        if (TextUtils.isEmpty(this.z)) {
            this.z = "display_name";
        }
        if (this.i == null) {
            finish();
            return;
        }
        EditController.a().a((EditModeChangedListener) this);
        this.D = intent.getStringExtra(TimerCommonManager.h);
        this.r = new MyHandler(this, getMainLooper());
        this.l = TimerCommonManager.i();
        this.l.a(this.i, "timer_name", this.z);
        a();
        this.l.a((TimerCommonManager.PlugSceneListener) this);
        n();
        this.b.show();
        this.l.b(this.D);
        if (this.l.l()) {
            this.l.m();
            this.l.a(new int[]{10, 30, 60});
            k();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        EditController.a().a(1);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || EditController.a().c != 0) {
            return super.onKeyDown(i2, keyEvent);
        }
        EditController.a().a(1);
        return false;
    }

    private void a() {
        d();
        this.f21654a = (CountDownWidget) findViewById(R.id.count_down_timer);
        this.f21654a.setupValues(1440, 0, 60);
        c();
        this.f = (ListView) findViewById(R.id.list_minute);
        this.k = (TextView) findViewById(R.id.tv_cur_set_time);
        this.o = findViewById(R.id.bottom_delete_bar);
        this.p = findViewById(R.id.delete_msg_btn);
        this.q = findViewById(R.id.top_delete_bar);
        if (TitleBarUtil.f11582a) {
            this.q.getLayoutParams().height += TitleBarUtil.b();
            this.q.setPadding(0, this.q.getPaddingTop() + TitleBarUtil.b(), 0, 0);
            this.q.setLayoutParams(this.q.getLayoutParams());
        }
        this.mCancelBtn = findViewById(R.id.cancel_btn);
        this.e = (TextView) findViewById(R.id.button3);
        this.c = (TextView) findViewById(R.id.button1);
        this.d = (TextView) findViewById(R.id.button2);
        this.mIvSelectAll = (ImageView) findViewById(R.id.select_all_btn);
        this.mIvSelectAll.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CountDownTimerActivity.this.d(view);
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CountDownTimerActivity.this.c(view);
            }
        });
        this.mCancelBtn.setOnClickListener($$Lambda$CountDownTimerActivity$fh5cODj2OAXWETtc0iI53z98T5U.INSTANCE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        if (EditController.a().d.size() == this.B.getCount() - 1) {
            q();
        } else {
            p();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (EditController.a().d.size() > 0) {
            l();
        } else {
            ToastUtil.a((CharSequence) getString(R.string.choose_one_tip));
        }
    }

    private void b() {
        i();
        m();
        k();
        if (this.n == null || !this.n.b) {
            g();
        } else {
            e();
            this.r.sendEmptyMessageDelayed(1, 20000);
        }
        this.f21654a.setStatus(this.g);
    }

    private void c() {
        this.j = (TimePicker) findViewById(R.id.tp_timer);
        this.j.setIs24HourView(true);
        this.j.setCurrentHour(0);
        this.j.setCurrentMinute(0);
        this.j.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
                CountDownTimerActivity.this.a(timePicker, i, i2);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(TimePicker timePicker, int i2, int i3) {
        String str;
        String str2;
        String str3;
        if (i2 == 0 && i3 == 0) {
            this.e.setEnabled(false);
            this.k.setText(R.string.count_down_not_set);
            return;
        }
        this.e.setEnabled(true);
        this.f21654a.setCurrentValue((i2 * 60) + i3);
        String str4 = "";
        if (i2 == 0) {
            str = "";
        } else {
            str = getResources().getQuantityString(R.plurals.automation_hour, i2, new Object[]{Integer.valueOf(i2)});
        }
        if (i3 == 0) {
            str2 = "";
        } else {
            str2 = getResources().getQuantityString(R.plurals.automation_minute, i3, new Object[]{Integer.valueOf(i3)});
        }
        if (!this.g) {
            String string = getString(R.string.timer_hint_open_all);
            Object[] objArr = new Object[3];
            if (TextUtils.isEmpty(str4)) {
                str4 = "";
            }
            objArr[0] = str4;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr[1] = str;
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            objArr[2] = str2;
            str3 = String.format(string, objArr);
        } else {
            String string2 = getString(R.string.timer_hint_close_all);
            Object[] objArr2 = new Object[3];
            if (TextUtils.isEmpty(str4)) {
                str4 = "";
            }
            objArr2[0] = str4;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr2[1] = str;
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            objArr2[2] = str2;
            str3 = String.format(string2, objArr2);
        }
        TextView textView = this.k;
        if (TextUtils.isEmpty(str3)) {
            str3 = getString(R.string.count_down_not_set);
        }
        textView.setText(str3);
    }

    private void d() {
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CountDownTimerActivity.this.a(view);
                }
            });
        }
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.plug_timer_set_countdown);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public void f() {
        Intent intent = new Intent(this, CountDownTimerStartActivity.class);
        intent.putExtra(TimerCommonManager.j, this.h);
        intent.putExtra(TimerCommonManager.o, this.v);
        intent.putExtra(TimerCommonManager.p, this.w);
        intent.putExtra(TimerCommonManager.k, this.x);
        intent.putExtra(TimerCommonManager.m, this.y);
        intent.putExtra(TimerCommonManager.q, this.g);
        intent.putExtra(TimerCommonManager.l, this.n);
        intent.putExtra(TimerCommonManager.h, this.D);
        intent.putExtra(TimerCommonManager.i, this.z);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.n != null) {
            this.n.b = false;
        }
        this.f21654a.setSeekArcThumbOff();
        this.c.setText(R.string.start);
        this.r.removeMessages(1);
    }

    /* access modifiers changed from: private */
    public void h() {
        ChooseTimeDialog chooseTimeDialog = new ChooseTimeDialog(this, 2131559365);
        chooseTimeDialog.setCancelable(true);
        chooseTimeDialog.a(R.string.ok_button, new DialogInterface.OnClickListener(chooseTimeDialog) {
            private final /* synthetic */ ChooseTimeDialog f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                CountDownTimerActivity.this.a(this.f$1, dialogInterface, i);
            }
        });
        chooseTimeDialog.b(R.string.cancel, $$Lambda$CountDownTimerActivity$bFhVDAh5bdbpwzrRnWW5Px69API.INSTANCE);
        chooseTimeDialog.show();
        chooseTimeDialog.a(R.string.customize_minute);
        if (chooseTimeDialog.a().getCurrentHour().intValue() == 0 && chooseTimeDialog.a().getCurrentMinute().intValue() == 0) {
            chooseTimeDialog.getButton(-1).setEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ChooseTimeDialog chooseTimeDialog, DialogInterface dialogInterface, int i2) {
        int intValue;
        TimePicker a2 = chooseTimeDialog.a();
        if (a2 != null && (intValue = (a2.getCurrentHour().intValue() * 60) + a2.getCurrentMinute().intValue()) > 0) {
            for (Integer intValue2 : this.E) {
                if (intValue2.intValue() == intValue) {
                    ToastUtil.a((int) R.string.count_down_already_set);
                    return;
                }
            }
            this.f21654a.setCurrentValue(intValue);
            a(intValue);
            k();
        }
    }

    private void i() {
        this.e.setText(R.string.start);
        this.c.setText(R.string.stop);
        this.d.setText(R.string.cancel);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CountDownTimerActivity.this.j();
            }
        });
        this.e.setEnabled(false);
        if (this.n == null) {
            g();
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            this.e.setVisibility(0);
            return;
        }
        this.c.setVisibility(0);
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        if (!this.n.b) {
            e();
        }
        this.d.setOnClickListener(this.F);
        this.c.setOnClickListener(this.G);
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.f21654a.getCurrentValue() != 0 && this.i != null) {
            List<PlugTimer> k2 = this.l.k();
            final int currentValue = this.f21654a.getCurrentValue();
            if (detectConflicting(currentValue, k2)) {
                new MLAlertDialog.Builder(this).b((int) R.string.set_timer_conflict).b(17039360, (DialogInterface.OnClickListener) null).a(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CountDownTimerActivity.this.a(currentValue, CountDownTimerActivity.this.i);
                    }
                }).d();
            } else {
                a(currentValue, this.i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, Device device) {
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
        boolean z2 = this.g;
        final PlugTimer plugTimer = this.n == null ? new PlugTimer() : this.n;
        plugTimer.f21674a = true;
        plugTimer.b = true;
        if (z2) {
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
        this.b.show();
        this.l.a(this.m, plugTimer, this.v, this.x, this.w, this.y, new TimerCommonManager.PlugSceneListener() {
            public void onGetSceneSuccess() {
            }

            public void onSetSceneSuccess(CommonTimer commonTimer) {
                CountDownTimerActivity.this.b.dismiss();
                PlugTimer unused = CountDownTimerActivity.this.m = plugTimer;
                PlugTimer unused2 = CountDownTimerActivity.this.n = plugTimer;
                CountDownTimerActivity.this.c.setVisibility(0);
                CountDownTimerActivity.this.d.setVisibility(0);
                CountDownTimerActivity.this.d.setOnClickListener(CountDownTimerActivity.this.F);
                CountDownTimerActivity.this.c.setOnClickListener(CountDownTimerActivity.this.G);
                CountDownTimerActivity.this.e.setVisibility(8);
                CountDownTimerActivity.this.e();
                CountDownTimerActivity.this.r.sendEmptyMessageDelayed(1, 20000);
                CountDownTimerActivity.this.f();
                CountDownTimerActivity.this.finish();
            }

            public void onSetSceneFailed(Error error) {
                CountDownTimerActivity.this.b.dismiss();
                ToastUtil.a((int) R.string.plug_timer_set_fail);
            }

            public void onGetSceneFailed(int i) {
                ToastUtil.a((int) R.string.plug_timer_get_fail);
            }
        }, this.D);
    }

    public boolean detectConflicting(int i2, List<PlugTimer> list) {
        List<Pair<Float, Float>> a2;
        if (list == null || list.size() == 0 || (a2 = TimerCommonManager.a(list)) == null || a2.isEmpty()) {
            return false;
        }
        boolean z2 = this.g;
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

    private boolean a(int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        arrayList.addAll(this.E);
        HashSet hashSet = new HashSet();
        hashSet.addAll(arrayList);
        int[] iArr = new int[hashSet.size()];
        int i3 = 0;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            if (hashSet.contains(arrayList.get(i4))) {
                iArr[i3] = ((Integer) arrayList.get(i4)).intValue();
                hashSet.remove(arrayList.get(i4));
                i3++;
            }
        }
        if (this.l == null) {
            return true;
        }
        this.l.a(iArr);
        return true;
    }

    private void a(List<Integer> list) {
        List<Integer> n2 = this.l.n();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(n2);
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = this.E.get(list.get(i3).intValue()).intValue();
            Iterator it = arrayList.iterator();
            int i4 = 0;
            while (it.hasNext() && ((Integer) it.next()).intValue() != intValue) {
                i4++;
            }
            arrayList.remove(i4);
        }
        HashSet hashSet = new HashSet();
        hashSet.addAll(arrayList);
        int[] iArr = new int[hashSet.size()];
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            iArr[i2] = ((Integer) it2.next()).intValue();
            i2++;
        }
        this.l.a(iArr);
    }

    private void k() {
        List<Integer> emptyList = Collections.emptyList();
        if (this.l != null) {
            emptyList = this.l.n();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(emptyList);
        this.E = new ArrayList();
        this.E.addAll(arrayList);
        if (this.B == null) {
            this.B = new MyAdapter(this.E, this);
            this.f.setAdapter(this.B);
            this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (EditController.a().c != 1) {
                        if (EditController.a().d.get(i)) {
                            EditController.a().d.delete(i);
                        } else {
                            EditController.a().d.put(i, true);
                        }
                        if (EditController.a().d.size() == CountDownTimerActivity.this.B.getCount() - 1) {
                            CountDownTimerActivity.this.mIvSelectAll.setImageResource(R.drawable.un_select_selector);
                        } else {
                            CountDownTimerActivity.this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
                        }
                        CountDownTimerActivity.this.B.notifyDataSetChanged();
                    } else if (i >= CountDownTimerActivity.this.E.size()) {
                        int unused = CountDownTimerActivity.this.B.c = -1;
                        CountDownTimerActivity.this.B.notifyDataSetChanged();
                        if (CountDownTimerActivity.this.E.size() >= 10) {
                            ToastUtil.a((int) R.string.frequent_use_timer_max);
                        } else {
                            CountDownTimerActivity.this.h();
                        }
                    } else {
                        int intValue = ((Integer) CountDownTimerActivity.this.E.get(i)).intValue();
                        int unused2 = CountDownTimerActivity.this.B.c = i;
                        CountDownTimerActivity.this.B.notifyDataSetChanged();
                        int unused3 = CountDownTimerActivity.this.A = intValue;
                        CountDownTimerActivity.this.f21654a.setCurrentValue(intValue);
                        if (intValue < 60) {
                            CountDownTimerActivity.this.j.setCurrentMinute(Integer.valueOf(intValue));
                            CountDownTimerActivity.this.j.setCurrentHour(0);
                            return;
                        }
                        CountDownTimerActivity.this.j.setCurrentHour(Integer.valueOf(intValue / 60));
                        CountDownTimerActivity.this.j.setCurrentMinute(Integer.valueOf(intValue % 60));
                    }
                }
            });
            this.f.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                public final boolean onItemLongClick(AdapterView adapterView, View view, int i, long j) {
                    return CountDownTimerActivity.this.a(adapterView, view, i, j);
                }
            });
        } else {
            this.B.a(this.E);
            this.B.notifyDataSetChanged();
        }
        if (EditController.a().c == 0) {
            EditController.a().a(1);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(AdapterView adapterView, View view, int i2, long j2) {
        if (i2 >= this.B.getCount() - 1 || EditController.a().c != 1) {
            return false;
        }
        EditController.a().d.put(i2, true);
        EditController.a().a(0);
        return true;
    }

    private void l() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.b((CharSequence) "\n" + getResources().getString(R.string.delete_custom_timer) + "\n");
        builder.a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CountDownTimerActivity.this.b(dialogInterface, i);
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$CountDownTimerActivity$mULjtX58Ao556l3DjrbvO8GFFPY.INSTANCE);
        builder.b().show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.E.size(); i3++) {
            if (EditController.a().d.get(i3)) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        b((List<Integer>) arrayList);
        dialogInterface.dismiss();
    }

    private void b(List<Integer> list) {
        a(list);
        k();
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.n != null) {
            if (!this.n.b) {
                this.r.removeMessages(1);
            }
            Calendar instance = Calendar.getInstance();
            int i2 = instance.get(12) + (instance.get(11) * 60);
            CorntabUtils.CorntabParam corntabParam = this.g ? this.n.h : this.n.e;
            int c2 = ((((corntabParam.c * 60) + corntabParam.b) - i2) - (((int) ServerTimerManager.a((Context) this).c()) / 60000)) + (instance.get(5) == corntabParam.d ? 0 : 1440);
            if (c2 >= 0) {
                this.f21654a.setCurrentValue(c2);
                return;
            }
            this.f21654a.setSeekArcThumbOff();
            this.f21654a.setCurrentValue(0);
            this.r.removeMessages(1);
        }
    }

    private void n() {
        this.b = new XQProgressDialog(this);
        this.b.setMessage(getString(R.string.gateway_magnet_location_updating));
        this.b.setCancelable(true);
    }

    private void o() {
        List<PlugTimer> o2 = this.l.o();
        if (o2 != null && o2.size() != 0) {
            ArrayList<PlugTimer> arrayList = new ArrayList<>();
            Calendar instance = Calendar.getInstance();
            int i2 = instance.get(5);
            int i3 = instance.get(12) + (instance.get(11) * 60);
            boolean z2 = this.g;
            for (PlugTimer next : o2) {
                if (next.b && (!z2 || !next.c || (next.f && next.g))) {
                    if (z2 || !next.f || (next.c && next.d)) {
                        if (!next.c || !next.f) {
                            CorntabUtils.CorntabParam corntabParam = z2 ? next.h : next.e;
                            if (corntabParam.d() == 0 && ((i2 != corntabParam.d || i3 < (corntabParam.c * 60) + corntabParam.b) && corntabParam.d() == 0)) {
                                arrayList.add(next);
                            }
                        }
                    }
                }
            }
            if (arrayList.size() != 0) {
                PlugTimer plugTimer = (PlugTimer) arrayList.get(0);
                CorntabUtils.CorntabParam corntabParam2 = z2 ? plugTimer.h : plugTimer.e;
                int i4 = (((corntabParam2.c * 60) + corntabParam2.b) - i3) + (i2 == corntabParam2.d ? 0 : 1440);
                for (PlugTimer plugTimer2 : arrayList) {
                    CorntabUtils.CorntabParam corntabParam3 = z2 ? plugTimer2.h : plugTimer2.e;
                    if (i4 > (((corntabParam3.c * 60) + corntabParam3.b) - i3) + (i2 == corntabParam3.d ? 0 : 1440)) {
                        i4 = (((corntabParam3.c * 60) + corntabParam3.b) - i3) + (i2 == corntabParam3.d ? 0 : 1440);
                        plugTimer = plugTimer2;
                    }
                }
                if (i4 <= 1440) {
                    this.m = plugTimer;
                    this.n = (PlugTimer) plugTimer.clone();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.l != null) {
            this.l.b((TimerCommonManager.PlugSceneListener) this);
        }
        EditController.a().b();
    }

    public void onGetSceneSuccess() {
        if (!isFinishing()) {
            o();
            if (this.n == null || !this.n.b || !this.C) {
                this.C = false;
                b();
                this.b.dismiss();
                return;
            }
            f();
            finish();
        }
    }

    public void onGetSceneFailed(int i2) {
        if (!isFinishing()) {
            this.b.dismiss();
            ToastUtil.a((int) R.string.network_error_info);
            this.e.setEnabled(false);
            this.j.setEnabled(false);
            this.j.setAlpha(0.3f);
            this.k.setAlpha(0.3f);
        }
    }

    public void onBrowseMode() {
        q();
        this.j.setAlpha(1.0f);
        this.j.setEnabled(true);
        this.k.setAlpha(1.0f);
        if (this.B != null) {
            this.B.notifyDataSetChanged();
        }
        this.q.setVisibility(8);
        this.o.setVisibility(8);
    }

    public void onManageMode() {
        int unused = this.B.c = -1;
        this.B.notifyDataSetChanged();
        this.q.setVisibility(0);
        this.j.setEnabled(false);
        this.j.setAlpha(0.3f);
        this.k.setAlpha(0.3f);
        this.o.setVisibility(0);
        this.q.measure(0, 0);
        this.o.measure(0, 0);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.q, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.o.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.o, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.o.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    private void p() {
        int count = this.B.getCount() - 1;
        EditController.a().d.clear();
        for (int i2 = 0; i2 < count; i2++) {
            EditController.a().d.put(i2, true);
        }
        this.mIvSelectAll.setImageResource(R.drawable.un_select_selector);
        this.B.notifyDataSetChanged();
    }

    private void q() {
        EditController.a().d.clear();
        this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
        if (this.B != null) {
            this.B.notifyDataSetChanged();
        }
    }

    private static class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<CountDownTimerActivity> f21666a;

        public MyHandler(CountDownTimerActivity countDownTimerActivity, Looper looper) {
            super(looper);
            this.f21666a = new WeakReference<>(countDownTimerActivity);
        }

        public void handleMessage(Message message) {
            CountDownTimerActivity countDownTimerActivity = (CountDownTimerActivity) this.f21666a.get();
            if (a(countDownTimerActivity)) {
                switch (message.what) {
                    case 1:
                        countDownTimerActivity.m();
                        countDownTimerActivity.r.sendEmptyMessageDelayed(1, 20000);
                        return;
                    case 2:
                        countDownTimerActivity.r();
                        return;
                    default:
                        return;
                }
            }
        }

        private boolean a(CountDownTimerActivity countDownTimerActivity) {
            if (countDownTimerActivity == null || countDownTimerActivity.isFinishing()) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 17 || !countDownTimerActivity.isDestroyed()) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void r() {
        this.f21654a.setStatus(this.g);
        this.f21654a.invalidate();
    }
}
