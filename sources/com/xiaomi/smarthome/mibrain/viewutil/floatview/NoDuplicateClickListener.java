package com.xiaomi.smarthome.mibrain.viewutil.floatview;

import android.view.View;
import java.util.Calendar;

public abstract class NoDuplicateClickListener implements View.OnClickListener {
    public static final int b = 200;

    /* renamed from: a  reason: collision with root package name */
    private long f10734a = 0;

    public abstract void a(View view);

    public void onClick(View view) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.f10734a > 200) {
            this.f10734a = timeInMillis;
            a(view);
        }
    }
}
