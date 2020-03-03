package com.Utils;

import com.mijia.debug.SDKLog;
import com.miui.tsmclient.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class TimeZoneUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f670a = "TimeZoneUtils";
    private static TimeZoneUtils b;

    private TimeZoneUtils() {
    }

    public static TimeZoneUtils a() {
        if (b == null) {
            synchronized (TimeZoneUtils.class) {
                if (b == null) {
                    b = new TimeZoneUtils();
                }
            }
        }
        return b;
    }

    public String a(String str) {
        if (!c(str)) {
            return null;
        }
        SDKLog.b(f670a, "localTimeString:" + str);
        int intValue = Integer.valueOf(str.split(":")[0]).intValue();
        int intValue2 = Integer.valueOf(str.split(":")[1]).intValue();
        int intValue3 = Integer.valueOf(str.split(":")[2]).intValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT);
        Calendar instance = Calendar.getInstance();
        instance.set(11, intValue);
        instance.set(12, intValue2);
        instance.set(13, intValue3);
        instance.set(14, 0);
        instance.add(14, -(instance.get(15) + instance.get(16)));
        Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08"));
        instance2.setTimeInMillis(instance.getTimeInMillis());
        instance2.add(14, instance2.get(15) + instance2.get(16));
        String str2 = simpleDateFormat.format(instance2.getTime()).toString();
        SDKLog.b(f670a, "toGMT8TimeString:" + str2);
        return str2;
    }

    public String b(String str) {
        if (!c(str)) {
            return null;
        }
        SDKLog.b(f670a, "gmt8TimeString:" + str);
        int intValue = Integer.valueOf(str.split(":")[0]).intValue();
        int intValue2 = Integer.valueOf(str.split(":")[1]).intValue();
        int intValue3 = Integer.valueOf(str.split(":")[2]).intValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+08"));
        instance.set(11, intValue);
        instance.set(12, intValue2);
        instance.set(13, intValue3);
        instance.set(14, 0);
        instance.add(14, -(instance.get(15) + instance.get(16)));
        Calendar instance2 = Calendar.getInstance(TimeZone.getDefault());
        instance2.setTimeInMillis(instance.getTimeInMillis());
        instance2.add(14, instance2.get(15) + instance2.get(16));
        String str2 = simpleDateFormat.format(instance2.getTime()).toString();
        SDKLog.b(f670a, "toLocalTimeString:" + str2);
        return str2;
    }

    public static boolean c(String str) {
        return Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$").matcher(str).matches();
    }

    public static boolean d(String str) {
        return Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$").matcher(str).matches();
    }

    public static boolean e(String str) {
        return Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$").matcher(str).matches();
    }
}
