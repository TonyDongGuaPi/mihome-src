package com.mi.global.bbs.view;

import android.content.Context;
import com.mi.global.bbs.R;
import java.util.Calendar;

public class DateHelper {
    private static final int CHANGE_YEAR = 1582;

    private static boolean isLeapYear(int i) {
        if (i > CHANGE_YEAR) {
            return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
        }
        if (i % 4 == 0) {
            return true;
        }
        return false;
    }

    public static int getDaysInMonth(int i, int i2) {
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 1:
                return isLeapYear(i2) ? 29 : 28;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    public static String getCurrentDateString(Context context, Calendar calendar) {
        return context.getResources().getStringArray(R.array.bbs_months)[calendar.get(2)] + " " + calendar.get(1);
    }
}
