package com.mijia.model.sdcard;

import com.Utils.TimeUtils;
import com.mi.global.bbs.manager.Region;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.stat.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TimeItemDay {

    /* renamed from: a  reason: collision with root package name */
    static SimpleDateFormat f8099a;
    public long b;
    public String c;
    public int d;
    public List<TimeItemHour> e = new ArrayList();
    public boolean[] f = new boolean[24];

    public void a(boolean z) {
        if (!z) {
            for (int i = 0; i < 24; i++) {
                this.f[i] = false;
            }
            return;
        }
        for (TimeItemHour timeItemHour : this.e) {
            int i2 = timeItemHour.f8100a;
            if (i2 >= 0 && i2 <= 23) {
                this.f[i2] = z;
            }
        }
    }

    public boolean a(int i) {
        if (i < 0 || i > 23) {
            return false;
        }
        return this.f[i];
    }

    public TimeItemHour b(int i) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (this.e.get(i2).f8100a == i) {
                return this.e.get(i2);
            }
        }
        return null;
    }

    public List<TimeItem> a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.e.size(); i++) {
            if (a(this.e.get(i).f8100a)) {
                arrayList.addAll(this.e.get(i).b);
            }
        }
        return arrayList;
    }

    public int b() {
        int i = 0;
        for (boolean z : this.f) {
            if (z) {
                i++;
            }
        }
        return i;
    }

    public static List<TimeItemDay> a(List<TimeItem> list) {
        String language = XmPluginHostApi.instance().getSettingLocale() != null ? XmPluginHostApi.instance().getSettingLocale().getLanguage() : null;
        if (XmPluginHostApi.instance().getApiLevel() < 51 || (!"de".equalsIgnoreCase(language) && !d.u.equalsIgnoreCase(language) && !"fr".equalsIgnoreCase(language) && !"it".equalsIgnoreCase(language) && !d.U.equalsIgnoreCase(language) && !Region.RU.equalsIgnoreCase(language))) {
            f8099a = new SimpleDateFormat("yyyy.MM.dd");
        } else {
            f8099a = new SimpleDateFormat("dd.MM.yyyy");
        }
        Calendar instance = Calendar.getInstance(TimeUtils.a());
        f8099a.setTimeZone(TimeUtils.a());
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            int size = list.size();
            TimeItemDay timeItemDay = null;
            TimeItemHour timeItemHour = null;
            for (int i = 0; i < size; i++) {
                TimeItem timeItem = list.get(i);
                instance.setTimeInMillis(timeItem.f8098a);
                int i2 = instance.get(5);
                int i3 = instance.get(11);
                if (!(timeItemDay == null || timeItemDay.d == i2)) {
                    timeItemDay = null;
                    timeItemHour = null;
                }
                if (!(timeItemHour == null || timeItemHour.f8100a == i3)) {
                    timeItemHour = null;
                }
                if (timeItemDay == null) {
                    timeItemDay = new TimeItemDay();
                    timeItemDay.c = f8099a.format(Long.valueOf(timeItem.f8098a));
                    timeItemDay.b = timeItem.f8098a;
                    timeItemDay.d = i2;
                    arrayList.add(timeItemDay);
                }
                if (timeItemHour == null) {
                    timeItemHour = new TimeItemHour();
                    timeItemHour.f8100a = i3;
                    timeItemDay.e.add(timeItemHour);
                }
                timeItemHour.b.add(timeItem);
            }
            Collections.reverse(arrayList);
        }
        return arrayList;
    }
}
