package com.mijia.model.sdcard;

import java.util.Comparator;

public class TimeItemComparator implements Comparator<TimeItem> {
    /* renamed from: a */
    public int compare(TimeItem timeItem, TimeItem timeItem2) {
        if (timeItem.f8098a == timeItem2.f8098a) {
            return 0;
        }
        return timeItem.f8098a > timeItem2.f8098a ? 1 : -1;
    }
}
