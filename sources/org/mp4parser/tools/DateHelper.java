package org.mp4parser.tools;

import java.util.Date;

public class DateHelper {
    public static Date a(long j) {
        return new Date((j - 2082844800) * 1000);
    }

    public static long a(Date date) {
        return (date.getTime() / 1000) + 2082844800;
    }
}
