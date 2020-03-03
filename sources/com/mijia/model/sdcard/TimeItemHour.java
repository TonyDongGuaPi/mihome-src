package com.mijia.model.sdcard;

import java.util.ArrayList;
import java.util.List;

public class TimeItemHour {

    /* renamed from: a  reason: collision with root package name */
    public int f8100a;
    public List<TimeItem> b = new ArrayList();

    public boolean a() {
        for (TimeItem timeItem : this.b) {
            if (timeItem.d == 1) {
                return true;
            }
        }
        return false;
    }
}
