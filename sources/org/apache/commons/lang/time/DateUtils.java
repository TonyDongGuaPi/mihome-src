package org.apache.commons.lang.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;

public class DateUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final TimeZone f3412a = TimeZone.getTimeZone("GMT");
    public static final long b = 1000;
    public static final long c = 60000;
    public static final long d = 3600000;
    public static final long e = 86400000;
    public static final int f = 1001;
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 3;
    public static final int j = 4;
    public static final int k = 5;
    public static final int l = 6;
    public static final int m = 1000;
    public static final int n = 60000;
    public static final int o = 3600000;
    public static final int p = 86400000;
    private static final int[][] q = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;

    public static boolean a(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return a(instance, instance2);
    }

    public static boolean a(Calendar calendar, Calendar calendar2) {
        if (calendar != null && calendar2 != null) {
            return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean b(Date date, Date date2) {
        if (date != null && date2 != null) {
            return date.getTime() == date2.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean b(Calendar calendar, Calendar calendar2) {
        if (calendar != null && calendar2 != null) {
            return calendar.getTime().getTime() == calendar2.getTime().getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean c(Calendar calendar, Calendar calendar2) {
        if (calendar != null && calendar2 != null) {
            return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(10) == calendar2.get(10) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date a(String str, String[] strArr) throws ParseException {
        return a(str, strArr, true);
    }

    public static Date b(String str, String[] strArr) throws ParseException {
        return a(str, strArr, false);
    }

    private static Date a(String str, String[] strArr, boolean z) throws ParseException {
        String str2;
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setLenient(z);
        ParsePosition parsePosition = new ParsePosition(0);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str3 = strArr[i2];
            if (strArr[i2].endsWith("ZZ")) {
                str3 = str3.substring(0, str3.length() - 1);
            }
            simpleDateFormat.applyPattern(str3);
            parsePosition.setIndex(0);
            if (strArr[i2].endsWith("ZZ")) {
                int a2 = a(str, 0);
                str2 = str;
                while (a2 >= 0) {
                    str2 = b(str2, a2);
                    a2 = a(str2, a2 + 1);
                }
            } else {
                str2 = str;
            }
            Date parse = simpleDateFormat.parse(str2, parsePosition);
            if (parse != null && parsePosition.getIndex() == str2.length()) {
                return parse;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unable to parse the date: ");
        stringBuffer.append(str);
        throw new ParseException(stringBuffer.toString(), -1);
    }

    private static int a(String str, int i2) {
        int a2 = StringUtils.a(str, '+', i2);
        return a2 < 0 ? StringUtils.a(str, '-', i2) : a2;
    }

    private static String b(String str, int i2) {
        int i3;
        if (i2 < 0 || (i3 = i2 + 5) >= str.length() || !Character.isDigit(str.charAt(i2 + 1)) || !Character.isDigit(str.charAt(i2 + 2))) {
            return str;
        }
        int i4 = i2 + 3;
        if (str.charAt(i4) != ':') {
            return str;
        }
        int i5 = i2 + 4;
        if (!Character.isDigit(str.charAt(i5)) || !Character.isDigit(str.charAt(i3))) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(0, i4));
        stringBuffer.append(str.substring(i5));
        return stringBuffer.toString();
    }

    public static Date a(Date date, int i2) {
        return a(date, 1, i2);
    }

    public static Date b(Date date, int i2) {
        return a(date, 2, i2);
    }

    public static Date c(Date date, int i2) {
        return a(date, 3, i2);
    }

    public static Date d(Date date, int i2) {
        return a(date, 5, i2);
    }

    public static Date e(Date date, int i2) {
        return a(date, 11, i2);
    }

    public static Date f(Date date, int i2) {
        return a(date, 12, i2);
    }

    public static Date g(Date date, int i2) {
        return a(date, 13, i2);
    }

    public static Date h(Date date, int i2) {
        return a(date, 14, i2);
    }

    public static Date a(Date date, int i2, int i3) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(i2, i3);
            return instance.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date i(Date date, int i2) {
        return b(date, 1, i2);
    }

    public static Date j(Date date, int i2) {
        return b(date, 2, i2);
    }

    public static Date k(Date date, int i2) {
        return b(date, 5, i2);
    }

    public static Date l(Date date, int i2) {
        return b(date, 11, i2);
    }

    public static Date m(Date date, int i2) {
        return b(date, 12, i2);
    }

    public static Date n(Date date, int i2) {
        return b(date, 13, i2);
    }

    public static Date o(Date date, int i2) {
        return b(date, 14, i2);
    }

    private static Date b(Date date, int i2, int i3) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setLenient(false);
            instance.setTime(date);
            instance.set(i2, i3);
            return instance.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar a(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

    public static Date p(Date date, int i2) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            a(instance, i2, 1);
            return instance.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar a(Calendar calendar, int i2) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            a(calendar2, i2, 1);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date a(Object obj, int i2) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return p((Date) obj, i2);
        } else {
            if (obj instanceof Calendar) {
                return a((Calendar) obj, i2).getTime();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not round ");
            stringBuffer.append(obj);
            throw new ClassCastException(stringBuffer.toString());
        }
    }

    public static Date q(Date date, int i2) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            a(instance, i2, 0);
            return instance.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar b(Calendar calendar, int i2) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            a(calendar2, i2, 0);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date b(Object obj, int i2) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return q((Date) obj, i2);
        } else {
            if (obj instanceof Calendar) {
                return b((Calendar) obj, i2).getTime();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not truncate ");
            stringBuffer.append(obj);
            throw new ClassCastException(stringBuffer.toString());
        }
    }

    public static Date r(Date date, int i2) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            a(instance, i2, 2);
            return instance.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar c(Calendar calendar, int i2) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            a(calendar2, i2, 2);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date c(Object obj, int i2) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return r((Date) obj, i2);
        } else {
            if (obj instanceof Calendar) {
                return c((Calendar) obj, i2).getTime();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not find ceiling of for type: ");
            stringBuffer.append(obj.getClass());
            throw new ClassCastException(stringBuffer.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d6, code lost:
        if (r3 > 7) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ea, code lost:
        if (r3 >= 6) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ee, code lost:
        r4 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0135 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.util.Calendar r13, int r14, int r15) {
        /*
            r0 = 1
            int r1 = r13.get(r0)
            r2 = 280000000(0x10b07600, float:6.960157E-29)
            if (r1 > r2) goto L_0x0155
            r1 = 14
            if (r14 != r1) goto L_0x000f
            return
        L_0x000f:
            java.util.Date r2 = r13.getTime()
            long r3 = r2.getTime()
            int r1 = r13.get(r1)
            if (r15 == 0) goto L_0x0021
            r5 = 500(0x1f4, float:7.0E-43)
            if (r1 >= r5) goto L_0x0023
        L_0x0021:
            long r5 = (long) r1
            long r3 = r3 - r5
        L_0x0023:
            r1 = 13
            r5 = 0
            if (r14 != r1) goto L_0x002a
            r6 = 1
            goto L_0x002b
        L_0x002a:
            r6 = 0
        L_0x002b:
            int r1 = r13.get(r1)
            r7 = 30
            if (r6 != 0) goto L_0x003d
            if (r15 == 0) goto L_0x0037
            if (r1 >= r7) goto L_0x003d
        L_0x0037:
            long r8 = (long) r1
            r10 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 * r10
            long r3 = r3 - r8
        L_0x003d:
            r1 = 12
            if (r14 != r1) goto L_0x0042
            r6 = 1
        L_0x0042:
            int r8 = r13.get(r1)
            if (r6 != 0) goto L_0x0053
            if (r15 == 0) goto L_0x004c
            if (r8 >= r7) goto L_0x0053
        L_0x004c:
            long r6 = (long) r8
            r8 = 60000(0xea60, double:2.9644E-319)
            long r6 = r6 * r8
            long r3 = r3 - r6
        L_0x0053:
            long r6 = r2.getTime()
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 == 0) goto L_0x0061
            r2.setTime(r3)
            r13.setTime(r2)
        L_0x0061:
            r2 = 0
            r3 = 0
        L_0x0063:
            int[][] r4 = q
            int r4 = r4.length
            if (r2 >= r4) goto L_0x0139
            r4 = 0
        L_0x0069:
            int[][] r6 = q
            r6 = r6[r2]
            int r6 = r6.length
            r7 = 15
            r8 = 1001(0x3e9, float:1.403E-42)
            r9 = 9
            r10 = 2
            r11 = 11
            r12 = 5
            if (r4 >= r6) goto L_0x00bf
            int[][] r6 = q
            r6 = r6[r2]
            r6 = r6[r4]
            if (r6 != r14) goto L_0x00bc
            if (r15 == r10) goto L_0x0088
            if (r15 != r0) goto L_0x00bb
            if (r3 == 0) goto L_0x00bb
        L_0x0088:
            if (r14 != r8) goto L_0x009d
            int r14 = r13.get(r12)
            if (r14 != r0) goto L_0x0094
            r13.add(r12, r7)
            goto L_0x00bb
        L_0x0094:
            r14 = -15
            r13.add(r12, r14)
            r13.add(r10, r0)
            goto L_0x00bb
        L_0x009d:
            if (r14 != r9) goto L_0x00b2
            int r14 = r13.get(r11)
            if (r14 != 0) goto L_0x00a9
            r13.add(r11, r1)
            goto L_0x00bb
        L_0x00a9:
            r14 = -12
            r13.add(r11, r14)
            r13.add(r12, r0)
            goto L_0x00bb
        L_0x00b2:
            int[][] r14 = q
            r14 = r14[r2]
            r14 = r14[r5]
            r13.add(r14, r0)
        L_0x00bb:
            return
        L_0x00bc:
            int r4 = r4 + 1
            goto L_0x0069
        L_0x00bf:
            if (r14 == r9) goto L_0x00d9
            if (r14 == r8) goto L_0x00c4
            goto L_0x00f2
        L_0x00c4:
            int[][] r4 = q
            r4 = r4[r2]
            r4 = r4[r5]
            if (r4 != r12) goto L_0x00f2
            int r3 = r13.get(r12)
            int r3 = r3 - r0
            if (r3 < r7) goto L_0x00d5
            int r3 = r3 + -15
        L_0x00d5:
            r4 = 7
            if (r3 <= r4) goto L_0x00ee
            goto L_0x00ec
        L_0x00d9:
            int[][] r4 = q
            r4 = r4[r2]
            r4 = r4[r5]
            if (r4 != r11) goto L_0x00f2
            int r3 = r13.get(r11)
            if (r3 < r1) goto L_0x00e9
            int r3 = r3 + -12
        L_0x00e9:
            r4 = 6
            if (r3 < r4) goto L_0x00ee
        L_0x00ec:
            r4 = 1
            goto L_0x00ef
        L_0x00ee:
            r4 = 0
        L_0x00ef:
            r6 = r3
            r3 = 1
            goto L_0x00f5
        L_0x00f2:
            r4 = r3
            r3 = 0
            r6 = 0
        L_0x00f5:
            if (r3 != 0) goto L_0x011e
            int[][] r3 = q
            r3 = r3[r2]
            r3 = r3[r5]
            int r3 = r13.getActualMinimum(r3)
            int[][] r4 = q
            r4 = r4[r2]
            r4 = r4[r5]
            int r4 = r13.getActualMaximum(r4)
            int[][] r6 = q
            r6 = r6[r2]
            r6 = r6[r5]
            int r6 = r13.get(r6)
            int r6 = r6 - r3
            int r4 = r4 - r3
            int r4 = r4 / r10
            if (r6 <= r4) goto L_0x011c
            r3 = 1
            goto L_0x011f
        L_0x011c:
            r3 = 0
            goto L_0x011f
        L_0x011e:
            r3 = r4
        L_0x011f:
            if (r6 == 0) goto L_0x0135
            int[][] r4 = q
            r4 = r4[r2]
            r4 = r4[r5]
            int[][] r7 = q
            r7 = r7[r2]
            r7 = r7[r5]
            int r7 = r13.get(r7)
            int r7 = r7 - r6
            r13.set(r4, r7)
        L_0x0135:
            int r2 = r2 + 1
            goto L_0x0063
        L_0x0139:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r15 = new java.lang.StringBuffer
            r15.<init>()
            java.lang.String r0 = "The field "
            r15.append(r0)
            r15.append(r14)
            java.lang.String r14 = " is not supported"
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            r13.<init>(r14)
            throw r13
        L_0x0155:
            java.lang.ArithmeticException r13 = new java.lang.ArithmeticException
            java.lang.String r14 = "Calendar value too large for accurate calculations"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.a(java.util.Calendar, int, int):void");
    }

    public static Iterator s(Date date, int i2) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return d(instance, i2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003b, code lost:
        r7 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Iterator d(java.util.Calendar r7, int r8) {
        /*
            if (r7 == 0) goto L_0x008e
            r0 = -1
            r1 = 2
            r2 = 5
            r3 = 1
            r4 = 7
            switch(r8) {
                case 1: goto L_0x0042;
                case 2: goto L_0x0042;
                case 3: goto L_0x0042;
                case 4: goto L_0x0042;
                case 5: goto L_0x0026;
                case 6: goto L_0x0026;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "The range style "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = " is not valid."
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        L_0x0026:
            java.util.Calendar r7 = b((java.util.Calendar) r7, (int) r1)
            java.lang.Object r5 = r7.clone()
            java.util.Calendar r5 = (java.util.Calendar) r5
            r5.add(r1, r3)
            r5.add(r2, r0)
            r6 = 6
            if (r8 != r6) goto L_0x003d
            r6 = r5
            r5 = r7
        L_0x003b:
            r7 = 1
            goto L_0x0064
        L_0x003d:
            r6 = r5
            r1 = 1
            r5 = r7
            r7 = 7
            goto L_0x0064
        L_0x0042:
            java.util.Calendar r5 = b((java.util.Calendar) r7, (int) r2)
            java.util.Calendar r6 = b((java.util.Calendar) r7, (int) r2)
            switch(r8) {
                case 1: goto L_0x0062;
                case 2: goto L_0x003b;
                case 3: goto L_0x005b;
                case 4: goto L_0x004e;
                default: goto L_0x004d;
            }
        L_0x004d:
            goto L_0x0062
        L_0x004e:
            int r8 = r7.get(r4)
            int r1 = r8 + -3
            int r7 = r7.get(r4)
            int r7 = r7 + 3
            goto L_0x0064
        L_0x005b:
            int r1 = r7.get(r4)
            int r7 = r1 + -1
            goto L_0x0064
        L_0x0062:
            r7 = 7
            r1 = 1
        L_0x0064:
            if (r1 >= r3) goto L_0x0068
            int r1 = r1 + 7
        L_0x0068:
            if (r1 <= r4) goto L_0x006c
            int r1 = r1 + -7
        L_0x006c:
            if (r7 >= r3) goto L_0x0070
            int r7 = r7 + 7
        L_0x0070:
            if (r7 <= r4) goto L_0x0074
            int r7 = r7 + -7
        L_0x0074:
            int r8 = r5.get(r4)
            if (r8 == r1) goto L_0x007e
            r5.add(r2, r0)
            goto L_0x0074
        L_0x007e:
            int r8 = r6.get(r4)
            if (r8 == r7) goto L_0x0088
            r6.add(r2, r3)
            goto L_0x007e
        L_0x0088:
            org.apache.commons.lang.time.DateUtils$DateIterator r7 = new org.apache.commons.lang.time.DateUtils$DateIterator
            r7.<init>(r5, r6)
            return r7
        L_0x008e:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "The date must not be null"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.d(java.util.Calendar, int):java.util.Iterator");
    }

    public static Iterator d(Object obj, int i2) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return s((Date) obj, i2);
        } else {
            if (obj instanceof Calendar) {
                return d((Calendar) obj, i2);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not iterate based on ");
            stringBuffer.append(obj);
            throw new ClassCastException(stringBuffer.toString());
        }
    }

    public static long t(Date date, int i2) {
        return c(date, i2, 14);
    }

    public static long u(Date date, int i2) {
        return c(date, i2, 13);
    }

    public static long v(Date date, int i2) {
        return c(date, i2, 12);
    }

    public static long w(Date date, int i2) {
        return c(date, i2, 11);
    }

    public static long x(Date date, int i2) {
        return c(date, i2, 6);
    }

    public static long e(Calendar calendar, int i2) {
        return b(calendar, i2, 14);
    }

    public static long f(Calendar calendar, int i2) {
        return b(calendar, i2, 13);
    }

    public static long g(Calendar calendar, int i2) {
        return b(calendar, i2, 12);
    }

    public static long h(Calendar calendar, int i2) {
        return b(calendar, i2, 11);
    }

    public static long i(Calendar calendar, int i2) {
        return b(calendar, i2, 6);
    }

    private static long c(Date date, int i2, int i3) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return b(instance, i2, i3);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005f, code lost:
        r2 = r2 + ((((long) r8.get(13)) * 1000) / r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return r2 + (((long) (r8.get(14) * 1)) / r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0051, code lost:
        r2 = r2 + ((((long) r8.get(12)) * 60000) / r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long b(java.util.Calendar r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L_0x0078
            long r0 = a((int) r10)
            r2 = 0
            r4 = 86400000(0x5265c00, double:4.2687272E-316)
            switch(r9) {
                case 1: goto L_0x001a;
                case 2: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0024
        L_0x000f:
            r10 = 5
            int r10 = r8.get(r10)
            long r6 = (long) r10
            long r6 = r6 * r4
            long r6 = r6 / r0
            long r2 = r2 + r6
            goto L_0x0024
        L_0x001a:
            r10 = 6
            int r10 = r8.get(r10)
            long r6 = (long) r10
            long r6 = r6 * r4
            long r6 = r6 / r0
            long r2 = r2 + r6
        L_0x0024:
            switch(r9) {
                case 1: goto L_0x0043;
                case 2: goto L_0x0043;
                case 3: goto L_0x0027;
                case 4: goto L_0x0027;
                case 5: goto L_0x0043;
                case 6: goto L_0x0043;
                case 7: goto L_0x0027;
                case 8: goto L_0x0027;
                case 9: goto L_0x0027;
                case 10: goto L_0x0027;
                case 11: goto L_0x0051;
                case 12: goto L_0x005f;
                case 13: goto L_0x006c;
                case 14: goto L_0x0077;
                default: goto L_0x0027;
            }
        L_0x0027:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r10.<init>()
            java.lang.String r0 = "The fragment "
            r10.append(r0)
            r10.append(r9)
            java.lang.String r9 = " is not supported"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L_0x0043:
            r9 = 11
            int r9 = r8.get(r9)
            long r9 = (long) r9
            r4 = 3600000(0x36ee80, double:1.7786363E-317)
            long r9 = r9 * r4
            long r9 = r9 / r0
            long r2 = r2 + r9
        L_0x0051:
            r9 = 12
            int r9 = r8.get(r9)
            long r9 = (long) r9
            r4 = 60000(0xea60, double:2.9644E-319)
            long r9 = r9 * r4
            long r9 = r9 / r0
            long r2 = r2 + r9
        L_0x005f:
            r9 = 13
            int r9 = r8.get(r9)
            long r9 = (long) r9
            r4 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 * r4
            long r9 = r9 / r0
            long r2 = r2 + r9
        L_0x006c:
            r9 = 14
            int r8 = r8.get(r9)
            int r8 = r8 * 1
            long r8 = (long) r8
            long r8 = r8 / r0
            long r2 = r2 + r8
        L_0x0077:
            return r2
        L_0x0078:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "The date must not be null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.b(java.util.Calendar, int, int):long");
    }

    public static boolean a(Calendar calendar, Calendar calendar2, int i2) {
        return b(calendar, calendar2, i2) == 0;
    }

    public static boolean a(Date date, Date date2, int i2) {
        return b(date, date2, i2) == 0;
    }

    public static int b(Calendar calendar, Calendar calendar2, int i2) {
        return b(calendar, i2).getTime().compareTo(b(calendar2, i2).getTime());
    }

    public static int b(Date date, Date date2, int i2) {
        return q(date, i2).compareTo(q(date2, i2));
    }

    private static long a(int i2) {
        switch (i2) {
            case 5:
            case 6:
                return 86400000;
            default:
                switch (i2) {
                    case 11:
                        return 3600000;
                    case 12:
                        return 60000;
                    case 13:
                        return 1000;
                    case 14:
                        return 1;
                    default:
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("The unit ");
                        stringBuffer.append(i2);
                        stringBuffer.append(" cannot be represented is milleseconds");
                        throw new IllegalArgumentException(stringBuffer.toString());
                }
        }
    }

    static class DateIterator implements Iterator {

        /* renamed from: a  reason: collision with root package name */
        private final Calendar f3413a;
        private final Calendar b;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.f3413a = calendar2;
            this.b = calendar;
            this.b.add(5, -1);
        }

        public boolean hasNext() {
            return this.b.before(this.f3413a);
        }

        public Object next() {
            if (!this.b.equals(this.f3413a)) {
                this.b.add(5, 1);
                return this.b.clone();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
