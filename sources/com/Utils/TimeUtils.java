package com.Utils;

import android.text.TextUtils;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.miui.tsmclient.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class TimeUtils {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleDateFormat f669a = new SimpleDateFormat("MM-dd HH:mm:ss");
    private static SimpleDateFormat b = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT);
    private static SimpleDateFormat c = new SimpleDateFormat("MM/dd");
    private static TimeZone d = TimeZone.getDefault();

    public static void a(String str) {
        String displayName = d.getDisplayName(false, 0);
        if (!TextUtils.isEmpty(str) && !displayName.equals(str)) {
            if (!Pattern.compile("^GMT[-+](\\d{1,2})(:?(\\d\\d))?$").matcher(str).matches()) {
                SDKLog.d(Tag.b, "setTimeZone not matches " + str);
                return;
            }
            d = TimeZone.getTimeZone(str);
            SDKLog.d(Tag.b, "new TimeZone " + d.getDisplayName(false, 0));
            b.setTimeZone(d);
            f669a.setTimeZone(d);
            c.setTimeZone(d);
        }
    }

    public static TimeZone a() {
        return TimeZone.getDefault();
    }

    public static String a(long j) {
        return b.format(Long.valueOf(j));
    }

    public static String b(long j) {
        return f669a.format(new Date(j));
    }

    public static String c(long j) {
        return c.format(new Date(j));
    }

    public static void b() {
        TimeZone timeZone = TimeZone.getDefault();
        if (!d.getID().equals(timeZone)) {
            f669a = new SimpleDateFormat("MM-dd HH:mm:ss");
            b = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT);
            c = new SimpleDateFormat("MM/dd");
            d = timeZone;
        }
    }
}
