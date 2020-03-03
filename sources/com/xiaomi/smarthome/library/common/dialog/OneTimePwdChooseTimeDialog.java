package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.location.common.model.AmapLoc;
import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.model.OneTimePasswordInfo;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import java.util.ArrayList;

public class OneTimePwdChooseTimeDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    protected Context f18584a;
    protected int b;
    /* access modifiers changed from: private */
    public TextView c;
    private TimePicker d;

    public OneTimePwdChooseTimeDialog(Context context, int i) {
        this(context, i, 2131559365);
        this.b = i;
    }

    public OneTimePwdChooseTimeDialog(Context context, int i, int i2) {
        this(context, i2, 80, 0, (CorntabUtils.CorntabParam) null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.f18584a).inflate(R.layout.choose_time_dialog, (ViewGroup) null);
        this.c = (TextView) inflate.findViewById(R.id.tv_timer_off_hint);
        this.d = (TimePicker) inflate.findViewById(R.id.tp_timer_off);
        this.d.setIs24HourView(true);
        this.d.setHourMinutesStrings(b(), c());
        this.d.setCurrentHour(0);
        this.d.setCurrentMinute(0);
        this.c.setText(this.f18584a.getString(R.string.one_time_password_choose_time_title, new Object[]{"0:00", a(0, 0, this.b)}));
        this.d.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                int i3 = OneTimePwdChooseTimeDialog.this.b * i2;
                OneTimePwdChooseTimeDialog.this.c.setText(OneTimePwdChooseTimeDialog.this.f18584a.getString(R.string.one_time_password_choose_time_title, new Object[]{OneTimePasswordInfo.a(i, i3), OneTimePwdChooseTimeDialog.this.a(i, i3, OneTimePwdChooseTimeDialog.this.b)}));
            }
        });
        setView(inflate);
        super.onCreate(bundle);
    }

    public OneTimePwdChooseTimeDialog(Context context, int i, int i2, int i3, CorntabUtils.CorntabParam corntabParam) {
        super(context, i);
        this.f18584a = context;
    }

    private String[] b() {
        return new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", AmapLoc.u, "15", Tags.Phone.M22S_PHONE, "17", "18", "19", UserConfig.g, "21", "22", "23"};
    }

    private String[] c() {
        if (this.b == 0) {
            return new String[]{"0"};
        }
        int i = 60 / this.b;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            arrayList.add(String.valueOf(i2));
            i2 += this.b;
        }
        return (String[]) arrayList.toArray(new String[i]);
    }

    /* access modifiers changed from: private */
    public String a(int i, int i2, int i3) {
        int i4 = i2 + (i3 * 2);
        if (i4 >= 60) {
            i4 -= 60;
            i++;
            if (i == 24) {
                i = 0;
            }
        }
        return OneTimePasswordInfo.a(i, i4);
    }

    public TimePicker a() {
        return this.d;
    }

    public void a(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, (CharSequence) getContext().getString(i), onClickListener);
    }

    public void b(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, (CharSequence) getContext().getString(i), onClickListener);
    }
}
