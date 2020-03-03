package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import com.xiaomi.smarthome.R;

public class TimeZoneApi {
    public static String a(String str, Context context) {
        int i = 0;
        for (String equals : context.getResources().getStringArray(R.array.city_timezone)) {
            if (equals.equals(str)) {
                return context.getResources().getStringArray(R.array.timezone_city_name)[i];
            }
            i++;
        }
        return "";
    }
}
