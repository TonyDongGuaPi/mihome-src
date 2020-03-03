package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.scene.timer.PlugTimer;

public class ChooseTimeDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    protected Context f18568a;
    private TextView b;
    private TimePicker c;
    private TextView d;
    private CorntabUtils.CorntabParam e;
    private boolean f;
    private boolean g;
    private CorntabUtils.CorntabParam h;
    private CorntabUtils.CorntabParam i;
    private boolean j;
    private boolean k;

    public ChooseTimeDialog(Context context, CorntabUtils.CorntabParam corntabParam, CorntabUtils.CorntabParam corntabParam2, CorntabUtils.CorntabParam corntabParam3, boolean z, boolean z2, boolean z3) {
        this(context, 2131559365);
        this.e = corntabParam;
        this.h = corntabParam2;
        this.i = corntabParam3;
        this.f = z;
        this.g = z2;
        this.j = z3;
    }

    public ChooseTimeDialog(Context context, int i2) {
        this(context, i2, 80, 0, (CorntabUtils.CorntabParam) null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.f18568a).inflate(R.layout.choose_time_dialog, (ViewGroup) null);
        this.b = (TextView) inflate.findViewById(R.id.tv_timer_off_hint);
        this.c = (TimePicker) inflate.findViewById(R.id.tp_timer_off);
        this.d = (TextView) inflate.findViewById(R.id.tv_custom_title);
        this.c.setIs24HourView(true);
        if (this.e != null) {
            this.c.setCurrentHour(Integer.valueOf(this.e.c));
            this.c.setCurrentMinute(Integer.valueOf(this.e.b));
            this.b.setText(PlugTimer.a(this.f18568a, this.h, this.i, this.f, this.g, this.e.c, this.e.b, this.j));
        } else {
            this.c.setCurrentHour(0);
            this.c.setCurrentMinute(0);
            this.b.setText(R.string.plug_timer_no_set);
        }
        this.c.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
                ChooseTimeDialog.this.a(timePicker, i, i2);
            }
        });
        if (this.k) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        setView(inflate);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(TimePicker timePicker, int i2, int i3) {
        String str;
        String str2;
        if (this.e != null) {
            this.b.setText(PlugTimer.a(this.f18568a, this.h, this.i, this.f, this.g, i2, i3, this.j));
        } else if (i2 == 0 && i3 == 0) {
            this.b.setText(R.string.plug_timer_no_set);
            getButton(-1).setEnabled(false);
        } else {
            TextView textView = this.b;
            Context context = getContext();
            Object[] objArr = new Object[2];
            if (i2 == 0) {
                str = "";
            } else {
                str = getContext().getResources().getQuantityString(R.plurals.automation_hour, i2, new Object[]{Integer.valueOf(i2)});
            }
            objArr[0] = str;
            if (i3 == 0) {
                str2 = "";
            } else {
                str2 = getContext().getResources().getQuantityString(R.plurals.count_down_minute, i3, new Object[]{Integer.valueOf(i3)});
            }
            objArr[1] = str2;
            textView.setText(context.getString(R.string.count_down_timer_frequent, objArr));
            getButton(-1).setEnabled(true);
        }
    }

    public ChooseTimeDialog(Context context, int i2, int i3, int i4, CorntabUtils.CorntabParam corntabParam) {
        super(context, i2);
        this.k = false;
        this.f18568a = context;
        this.e = corntabParam;
    }

    public TimePicker a() {
        return this.c;
    }

    public void a(int i2, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, (CharSequence) getContext().getString(i2), onClickListener);
    }

    public void b(int i2, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, (CharSequence) getContext().getString(i2), onClickListener);
    }

    public void a(int i2) {
        this.k = true;
        if (this.d != null) {
            this.d.setVisibility(0);
            this.d.setText(i2);
        }
    }
}
