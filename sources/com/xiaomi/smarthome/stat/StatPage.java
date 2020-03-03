package com.xiaomi.smarthome.stat;

import android.text.TextUtils;
import com.xiaomi.smarthome.stat.report.StatReporter;

public abstract class StatPage {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22754a = new StatReporter("page");

    public long a(Object obj, String str) {
        if (obj == null) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            str = StatReporter.a(obj);
        }
        return this.f22754a.a("page_start", "name", str);
    }

    public long a(Object obj, long j, String str) {
        if (obj == null) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            str = StatReporter.a(obj);
        }
        return this.f22754a.a("page_end", "name", str, "stay_time", Long.valueOf((System.currentTimeMillis() - j) / 1000));
    }
}
