package org.apache.commons.lang.time;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.text.StrBuilder;

public class FastDateFormat extends Format {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;

    /* renamed from: a  reason: collision with root package name */
    private static String f3416a = null;
    private static final Map b = new HashMap(7);
    private static final Map c = new HashMap(7);
    private static final Map d = new HashMap(7);
    private static final Map e = new HashMap(7);
    private static final Map f = new HashMap(7);
    private static final long serialVersionUID = 1;
    private transient Rule[] g;
    private transient int h;
    private final Locale mLocale;
    private final boolean mLocaleForced;
    private final String mPattern;
    private final TimeZone mTimeZone;
    private final boolean mTimeZoneForced;

    private interface NumberRule extends Rule {
        void a(StringBuffer stringBuffer, int i);
    }

    private interface Rule {
        int a();

        void a(StringBuffer stringBuffer, Calendar calendar);
    }

    public static FastDateFormat getInstance() {
        return getInstance(a(), (TimeZone) null, (Locale) null);
    }

    public static FastDateFormat getInstance(String str) {
        return getInstance(str, (TimeZone) null, (Locale) null);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone) {
        return getInstance(str, timeZone, (Locale) null);
    }

    public static FastDateFormat getInstance(String str, Locale locale) {
        return getInstance(str, (TimeZone) null, locale);
    }

    public static synchronized FastDateFormat getInstance(String str, TimeZone timeZone, Locale locale) {
        FastDateFormat fastDateFormat;
        synchronized (FastDateFormat.class) {
            FastDateFormat fastDateFormat2 = new FastDateFormat(str, timeZone, locale);
            fastDateFormat = (FastDateFormat) b.get(fastDateFormat2);
            if (fastDateFormat == null) {
                fastDateFormat2.init();
                b.put(fastDateFormat2, fastDateFormat2);
                fastDateFormat = fastDateFormat2;
            }
        }
        return fastDateFormat;
    }

    public static FastDateFormat getDateInstance(int i) {
        return getDateInstance(i, (TimeZone) null, (Locale) null);
    }

    public static FastDateFormat getDateInstance(int i, Locale locale) {
        return getDateInstance(i, (TimeZone) null, locale);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone) {
        return getDateInstance(i, timeZone, (Locale) null);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0039 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized org.apache.commons.lang.time.FastDateFormat getDateInstance(int r3, java.util.TimeZone r4, java.util.Locale r5) {
        /*
            java.lang.Class<org.apache.commons.lang.time.FastDateFormat> r0 = org.apache.commons.lang.time.FastDateFormat.class
            monitor-enter(r0)
            java.lang.Integer r1 = new java.lang.Integer     // Catch:{ all -> 0x0052 }
            r1.<init>(r3)     // Catch:{ all -> 0x0052 }
            if (r4 == 0) goto L_0x0010
            org.apache.commons.lang.time.FastDateFormat$Pair r2 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x0052 }
            r2.<init>(r1, r4)     // Catch:{ all -> 0x0052 }
            r1 = r2
        L_0x0010:
            if (r5 != 0) goto L_0x0016
            java.util.Locale r5 = java.util.Locale.getDefault()     // Catch:{ all -> 0x0052 }
        L_0x0016:
            org.apache.commons.lang.time.FastDateFormat$Pair r2 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x0052 }
            r2.<init>(r1, r5)     // Catch:{ all -> 0x0052 }
            java.util.Map r1 = c     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0052 }
            org.apache.commons.lang.time.FastDateFormat r1 = (org.apache.commons.lang.time.FastDateFormat) r1     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x0050
            java.text.DateFormat r3 = java.text.DateFormat.getDateInstance(r3, r5)     // Catch:{ ClassCastException -> 0x0039 }
            java.text.SimpleDateFormat r3 = (java.text.SimpleDateFormat) r3     // Catch:{ ClassCastException -> 0x0039 }
            java.lang.String r3 = r3.toPattern()     // Catch:{ ClassCastException -> 0x0039 }
            org.apache.commons.lang.time.FastDateFormat r1 = getInstance(r3, r4, r5)     // Catch:{ ClassCastException -> 0x0039 }
            java.util.Map r3 = c     // Catch:{ ClassCastException -> 0x0039 }
            r3.put(r2, r1)     // Catch:{ ClassCastException -> 0x0039 }
            goto L_0x0050
        L_0x0039:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0052 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ all -> 0x0052 }
            r4.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "No date pattern for locale: "
            r4.append(r1)     // Catch:{ all -> 0x0052 }
            r4.append(r5)     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0052 }
            r3.<init>(r4)     // Catch:{ all -> 0x0052 }
            throw r3     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r0)
            return r1
        L_0x0052:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.FastDateFormat.getDateInstance(int, java.util.TimeZone, java.util.Locale):org.apache.commons.lang.time.FastDateFormat");
    }

    public static FastDateFormat getTimeInstance(int i) {
        return getTimeInstance(i, (TimeZone) null, (Locale) null);
    }

    public static FastDateFormat getTimeInstance(int i, Locale locale) {
        return getTimeInstance(i, (TimeZone) null, locale);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone) {
        return getTimeInstance(i, timeZone, (Locale) null);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:(1:11)|12|13|14|15|16) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized org.apache.commons.lang.time.FastDateFormat getTimeInstance(int r3, java.util.TimeZone r4, java.util.Locale r5) {
        /*
            java.lang.Class<org.apache.commons.lang.time.FastDateFormat> r0 = org.apache.commons.lang.time.FastDateFormat.class
            monitor-enter(r0)
            java.lang.Integer r1 = new java.lang.Integer     // Catch:{ all -> 0x0055 }
            r1.<init>(r3)     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x0010
            org.apache.commons.lang.time.FastDateFormat$Pair r2 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x0055 }
            r2.<init>(r1, r4)     // Catch:{ all -> 0x0055 }
            r1 = r2
        L_0x0010:
            if (r5 == 0) goto L_0x0018
            org.apache.commons.lang.time.FastDateFormat$Pair r2 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x0055 }
            r2.<init>(r1, r5)     // Catch:{ all -> 0x0055 }
            r1 = r2
        L_0x0018:
            java.util.Map r2 = d     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0055 }
            org.apache.commons.lang.time.FastDateFormat r2 = (org.apache.commons.lang.time.FastDateFormat) r2     // Catch:{ all -> 0x0055 }
            if (r2 != 0) goto L_0x0053
            if (r5 != 0) goto L_0x0028
            java.util.Locale r5 = java.util.Locale.getDefault()     // Catch:{ all -> 0x0055 }
        L_0x0028:
            java.text.DateFormat r3 = java.text.DateFormat.getTimeInstance(r3, r5)     // Catch:{ ClassCastException -> 0x003c }
            java.text.SimpleDateFormat r3 = (java.text.SimpleDateFormat) r3     // Catch:{ ClassCastException -> 0x003c }
            java.lang.String r3 = r3.toPattern()     // Catch:{ ClassCastException -> 0x003c }
            org.apache.commons.lang.time.FastDateFormat r2 = getInstance(r3, r4, r5)     // Catch:{ ClassCastException -> 0x003c }
            java.util.Map r3 = d     // Catch:{ ClassCastException -> 0x003c }
            r3.put(r1, r2)     // Catch:{ ClassCastException -> 0x003c }
            goto L_0x0053
        L_0x003c:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0055 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ all -> 0x0055 }
            r4.<init>()     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "No date pattern for locale: "
            r4.append(r1)     // Catch:{ all -> 0x0055 }
            r4.append(r5)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0055 }
            r3.<init>(r4)     // Catch:{ all -> 0x0055 }
            throw r3     // Catch:{ all -> 0x0055 }
        L_0x0053:
            monitor-exit(r0)
            return r2
        L_0x0055:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.FastDateFormat.getTimeInstance(int, java.util.TimeZone, java.util.Locale):org.apache.commons.lang.time.FastDateFormat");
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2) {
        return getDateTimeInstance(i, i2, (TimeZone) null, (Locale) null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, Locale locale) {
        return getDateTimeInstance(i, i2, (TimeZone) null, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone) {
        return getDateTimeInstance(i, i2, timeZone, (Locale) null);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0043 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized org.apache.commons.lang.time.FastDateFormat getDateTimeInstance(int r4, int r5, java.util.TimeZone r6, java.util.Locale r7) {
        /*
            java.lang.Class<org.apache.commons.lang.time.FastDateFormat> r0 = org.apache.commons.lang.time.FastDateFormat.class
            monitor-enter(r0)
            org.apache.commons.lang.time.FastDateFormat$Pair r1 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x005c }
            java.lang.Integer r2 = new java.lang.Integer     // Catch:{ all -> 0x005c }
            r2.<init>(r4)     // Catch:{ all -> 0x005c }
            java.lang.Integer r3 = new java.lang.Integer     // Catch:{ all -> 0x005c }
            r3.<init>(r5)     // Catch:{ all -> 0x005c }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x005c }
            if (r6 == 0) goto L_0x001a
            org.apache.commons.lang.time.FastDateFormat$Pair r2 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x005c }
            r2.<init>(r1, r6)     // Catch:{ all -> 0x005c }
            r1 = r2
        L_0x001a:
            if (r7 != 0) goto L_0x0020
            java.util.Locale r7 = java.util.Locale.getDefault()     // Catch:{ all -> 0x005c }
        L_0x0020:
            org.apache.commons.lang.time.FastDateFormat$Pair r2 = new org.apache.commons.lang.time.FastDateFormat$Pair     // Catch:{ all -> 0x005c }
            r2.<init>(r1, r7)     // Catch:{ all -> 0x005c }
            java.util.Map r1 = e     // Catch:{ all -> 0x005c }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x005c }
            org.apache.commons.lang.time.FastDateFormat r1 = (org.apache.commons.lang.time.FastDateFormat) r1     // Catch:{ all -> 0x005c }
            if (r1 != 0) goto L_0x005a
            java.text.DateFormat r4 = java.text.DateFormat.getDateTimeInstance(r4, r5, r7)     // Catch:{ ClassCastException -> 0x0043 }
            java.text.SimpleDateFormat r4 = (java.text.SimpleDateFormat) r4     // Catch:{ ClassCastException -> 0x0043 }
            java.lang.String r4 = r4.toPattern()     // Catch:{ ClassCastException -> 0x0043 }
            org.apache.commons.lang.time.FastDateFormat r1 = getInstance(r4, r6, r7)     // Catch:{ ClassCastException -> 0x0043 }
            java.util.Map r4 = e     // Catch:{ ClassCastException -> 0x0043 }
            r4.put(r2, r1)     // Catch:{ ClassCastException -> 0x0043 }
            goto L_0x005a
        L_0x0043:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x005c }
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ all -> 0x005c }
            r5.<init>()     // Catch:{ all -> 0x005c }
            java.lang.String r6 = "No date time pattern for locale: "
            r5.append(r6)     // Catch:{ all -> 0x005c }
            r5.append(r7)     // Catch:{ all -> 0x005c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x005c }
            r4.<init>(r5)     // Catch:{ all -> 0x005c }
            throw r4     // Catch:{ all -> 0x005c }
        L_0x005a:
            monitor-exit(r0)
            return r1
        L_0x005c:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.FastDateFormat.getDateTimeInstance(int, int, java.util.TimeZone, java.util.Locale):org.apache.commons.lang.time.FastDateFormat");
    }

    static synchronized String getTimeZoneDisplay(TimeZone timeZone, boolean z, int i, Locale locale) {
        String str;
        synchronized (FastDateFormat.class) {
            TimeZoneDisplayKey timeZoneDisplayKey = new TimeZoneDisplayKey(timeZone, z, i, locale);
            str = (String) f.get(timeZoneDisplayKey);
            if (str == null) {
                str = timeZone.getDisplayName(z, i, locale);
                f.put(timeZoneDisplayKey, str);
            }
        }
        return str;
    }

    private static synchronized String a() {
        String str;
        synchronized (FastDateFormat.class) {
            if (f3416a == null) {
                f3416a = new SimpleDateFormat().toPattern();
            }
            str = f3416a;
        }
        return str;
    }

    protected FastDateFormat(String str, TimeZone timeZone, Locale locale) {
        if (str != null) {
            this.mPattern = str;
            boolean z = false;
            this.mTimeZoneForced = timeZone != null;
            this.mTimeZone = timeZone == null ? TimeZone.getDefault() : timeZone;
            this.mLocaleForced = locale != null ? true : z;
            this.mLocale = locale == null ? Locale.getDefault() : locale;
            return;
        }
        throw new IllegalArgumentException("The pattern must not be null");
    }

    /* access modifiers changed from: protected */
    public void init() {
        List parsePattern = parsePattern();
        this.g = (Rule[]) parsePattern.toArray(new Rule[parsePattern.size()]);
        int length = this.g.length;
        int i = 0;
        while (true) {
            length--;
            if (length >= 0) {
                i += this.g[length].a();
            } else {
                this.h = i;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List parsePattern() {
        /*
            r16 = this;
            r0 = r16
            java.text.DateFormatSymbols r1 = new java.text.DateFormatSymbols
            java.util.Locale r2 = r0.mLocale
            r1.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String[] r3 = r1.getEras()
            java.lang.String[] r4 = r1.getMonths()
            java.lang.String[] r5 = r1.getShortMonths()
            java.lang.String[] r6 = r1.getWeekdays()
            java.lang.String[] r7 = r1.getShortWeekdays()
            java.lang.String[] r1 = r1.getAmPmStrings()
            java.lang.String r8 = r0.mPattern
            int r8 = r8.length()
            r9 = 1
            int[] r10 = new int[r9]
            r11 = 0
            r12 = 0
        L_0x0031:
            if (r12 >= r8) goto L_0x0153
            r10[r11] = r12
            java.lang.String r12 = r0.mPattern
            java.lang.String r12 = r0.parseToken(r12, r10)
            r13 = r10[r11]
            int r14 = r12.length()
            if (r14 != 0) goto L_0x0045
            goto L_0x0153
        L_0x0045:
            char r15 = r12.charAt(r11)
            r11 = 4
            switch(r15) {
                case 39: goto L_0x012e;
                case 68: goto L_0x0126;
                case 69: goto L_0x0119;
                case 70: goto L_0x0112;
                case 71: goto L_0x010b;
                case 72: goto L_0x0104;
                case 75: goto L_0x00fd;
                case 77: goto L_0x00e3;
                case 83: goto L_0x00dc;
                case 87: goto L_0x00d7;
                case 90: goto L_0x00cf;
                case 97: goto L_0x00c6;
                case 100: goto L_0x00bf;
                case 104: goto L_0x00b2;
                case 107: goto L_0x00a5;
                case 109: goto L_0x009d;
                case 115: goto L_0x0095;
                case 119: goto L_0x008e;
                case 121: goto L_0x0081;
                case 122: goto L_0x0064;
                default: goto L_0x004d;
            }
        L_0x004d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "Illegal pattern component: "
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0064:
            if (r14 < r11) goto L_0x0073
            org.apache.commons.lang.time.FastDateFormat$TimeZoneNameRule r11 = new org.apache.commons.lang.time.FastDateFormat$TimeZoneNameRule
            java.util.TimeZone r12 = r0.mTimeZone
            boolean r14 = r0.mTimeZoneForced
            java.util.Locale r15 = r0.mLocale
            r11.<init>(r12, r14, r15, r9)
            goto L_0x012c
        L_0x0073:
            org.apache.commons.lang.time.FastDateFormat$TimeZoneNameRule r11 = new org.apache.commons.lang.time.FastDateFormat$TimeZoneNameRule
            java.util.TimeZone r12 = r0.mTimeZone
            boolean r14 = r0.mTimeZoneForced
            java.util.Locale r15 = r0.mLocale
            r9 = 0
            r11.<init>(r12, r14, r15, r9)
            goto L_0x012b
        L_0x0081:
            if (r14 < r11) goto L_0x008a
            r9 = 1
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012c
        L_0x008a:
            org.apache.commons.lang.time.FastDateFormat$TwoDigitYearField r11 = org.apache.commons.lang.time.FastDateFormat.TwoDigitYearField.f3429a
            goto L_0x012b
        L_0x008e:
            r9 = 3
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x0095:
            r9 = 13
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x009d:
            r9 = 12
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x00a5:
            org.apache.commons.lang.time.FastDateFormat$TwentyFourHourField r11 = new org.apache.commons.lang.time.FastDateFormat$TwentyFourHourField
            r9 = 11
            org.apache.commons.lang.time.FastDateFormat$NumberRule r9 = r0.selectNumberRule(r9, r14)
            r11.<init>(r9)
            goto L_0x012b
        L_0x00b2:
            org.apache.commons.lang.time.FastDateFormat$TwelveHourField r11 = new org.apache.commons.lang.time.FastDateFormat$TwelveHourField
            r9 = 10
            org.apache.commons.lang.time.FastDateFormat$NumberRule r9 = r0.selectNumberRule(r9, r14)
            r11.<init>(r9)
            goto L_0x012b
        L_0x00bf:
            r9 = 5
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x00c6:
            org.apache.commons.lang.time.FastDateFormat$TextField r11 = new org.apache.commons.lang.time.FastDateFormat$TextField
            r9 = 9
            r11.<init>(r9, r1)
            goto L_0x012b
        L_0x00cf:
            if (r14 != r9) goto L_0x00d4
            org.apache.commons.lang.time.FastDateFormat$TimeZoneNumberRule r11 = org.apache.commons.lang.time.FastDateFormat.TimeZoneNumberRule.b
            goto L_0x012b
        L_0x00d4:
            org.apache.commons.lang.time.FastDateFormat$TimeZoneNumberRule r11 = org.apache.commons.lang.time.FastDateFormat.TimeZoneNumberRule.f3424a
            goto L_0x012b
        L_0x00d7:
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012b
        L_0x00dc:
            r9 = 14
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x00e3:
            r9 = 2
            if (r14 < r11) goto L_0x00ec
            org.apache.commons.lang.time.FastDateFormat$TextField r11 = new org.apache.commons.lang.time.FastDateFormat$TextField
            r11.<init>(r9, r4)
            goto L_0x012b
        L_0x00ec:
            r11 = 3
            if (r14 != r11) goto L_0x00f5
            org.apache.commons.lang.time.FastDateFormat$TextField r11 = new org.apache.commons.lang.time.FastDateFormat$TextField
            r11.<init>(r9, r5)
            goto L_0x012b
        L_0x00f5:
            if (r14 != r9) goto L_0x00fa
            org.apache.commons.lang.time.FastDateFormat$TwoDigitMonthField r11 = org.apache.commons.lang.time.FastDateFormat.TwoDigitMonthField.f3427a
            goto L_0x012b
        L_0x00fa:
            org.apache.commons.lang.time.FastDateFormat$UnpaddedMonthField r11 = org.apache.commons.lang.time.FastDateFormat.UnpaddedMonthField.f3430a
            goto L_0x012b
        L_0x00fd:
            r9 = 10
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x0104:
            r9 = 11
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x010b:
            org.apache.commons.lang.time.FastDateFormat$TextField r11 = new org.apache.commons.lang.time.FastDateFormat$TextField
            r9 = 0
            r11.<init>(r9, r3)
            goto L_0x012b
        L_0x0112:
            r9 = 8
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012b
        L_0x0119:
            org.apache.commons.lang.time.FastDateFormat$TextField r9 = new org.apache.commons.lang.time.FastDateFormat$TextField
            r12 = 7
            if (r14 >= r11) goto L_0x0120
            r11 = r7
            goto L_0x0121
        L_0x0120:
            r11 = r6
        L_0x0121:
            r9.<init>(r12, r11)
            r11 = r9
            goto L_0x012b
        L_0x0126:
            r9 = 6
            org.apache.commons.lang.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
        L_0x012b:
            r9 = 1
        L_0x012c:
            r14 = 0
            goto L_0x014b
        L_0x012e:
            java.lang.String r11 = r12.substring(r9)
            int r12 = r11.length()
            if (r12 != r9) goto L_0x0144
            org.apache.commons.lang.time.FastDateFormat$CharacterLiteral r12 = new org.apache.commons.lang.time.FastDateFormat$CharacterLiteral
            r14 = 0
            char r11 = r11.charAt(r14)
            r12.<init>(r11)
        L_0x0142:
            r11 = r12
            goto L_0x014b
        L_0x0144:
            r14 = 0
            org.apache.commons.lang.time.FastDateFormat$StringLiteral r12 = new org.apache.commons.lang.time.FastDateFormat$StringLiteral
            r12.<init>(r11)
            goto L_0x0142
        L_0x014b:
            r2.add(r11)
            int r12 = r13 + 1
            r11 = 0
            goto L_0x0031
        L_0x0153:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.FastDateFormat.parsePattern():java.util.List");
    }

    /* access modifiers changed from: protected */
    public String parseToken(String str, int[] iArr) {
        StrBuilder strBuilder = new StrBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            strBuilder.a(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                strBuilder.a(charAt);
                i = i2;
            }
        } else {
            strBuilder.a((char) Operators.SINGLE_QUOTE);
            boolean z = false;
            while (true) {
                if (i >= length) {
                    break;
                }
                char charAt2 = str.charAt(i);
                if (charAt2 == '\'') {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        strBuilder.a(charAt2);
                        i = i3;
                    }
                } else if (z || ((charAt2 < 'A' || charAt2 > 'Z') && (charAt2 < 'a' || charAt2 > 'z'))) {
                    strBuilder.a(charAt2);
                }
                i++;
            }
            i--;
        }
        iArr[0] = i;
        return strBuilder.toString();
    }

    /* access modifiers changed from: protected */
    public NumberRule selectNumberRule(int i, int i2) {
        switch (i2) {
            case 1:
                return new UnpaddedNumberField(i);
            case 2:
                return new TwoDigitNumberField(i);
            default:
                return new PaddedNumberField(i, i2);
        }
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Unknown class: ");
        stringBuffer2.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(stringBuffer2.toString());
    }

    public String format(long j) {
        return format(new Date(j));
    }

    public String format(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone, this.mLocale);
        gregorianCalendar.setTime(date);
        return applyRules(gregorianCalendar, new StringBuffer(this.h)).toString();
    }

    public String format(Calendar calendar) {
        return format(calendar, new StringBuffer(this.h)).toString();
    }

    public StringBuffer format(long j, StringBuffer stringBuffer) {
        return format(new Date(j), stringBuffer);
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone);
        gregorianCalendar.setTime(date);
        return applyRules(gregorianCalendar, stringBuffer);
    }

    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        if (this.mTimeZoneForced) {
            calendar.getTime();
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.mTimeZone);
        }
        return applyRules(calendar, stringBuffer);
    }

    /* access modifiers changed from: protected */
    public StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        Rule[] ruleArr = this.g;
        int length = this.g.length;
        for (int i = 0; i < length; i++) {
            ruleArr[i].a(stringBuffer, calendar);
        }
        return stringBuffer;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        parsePosition.setIndex(0);
        parsePosition.setErrorIndex(0);
        return null;
    }

    public String getPattern() {
        return this.mPattern;
    }

    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public boolean getTimeZoneOverridesCalendar() {
        return this.mTimeZoneForced;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        FastDateFormat fastDateFormat = (FastDateFormat) obj;
        if ((this.mPattern == fastDateFormat.mPattern || this.mPattern.equals(fastDateFormat.mPattern)) && ((this.mTimeZone == fastDateFormat.mTimeZone || this.mTimeZone.equals(fastDateFormat.mTimeZone)) && ((this.mLocale == fastDateFormat.mLocale || this.mLocale.equals(fastDateFormat.mLocale)) && this.mTimeZoneForced == fastDateFormat.mTimeZoneForced && this.mLocaleForced == fastDateFormat.mLocaleForced))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mPattern.hashCode() + 0 + this.mTimeZone.hashCode() + (this.mTimeZoneForced ? 1 : 0) + this.mLocale.hashCode() + (this.mLocaleForced ? 1 : 0);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FastDateFormat[");
        stringBuffer.append(this.mPattern);
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }

    private static class CharacterLiteral implements Rule {

        /* renamed from: a  reason: collision with root package name */
        private final char f3417a;

        public int a() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.f3417a = c;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.f3417a);
        }
    }

    private static class StringLiteral implements Rule {

        /* renamed from: a  reason: collision with root package name */
        private final String f3420a;

        StringLiteral(String str) {
            this.f3420a = str;
        }

        public int a() {
            return this.f3420a.length();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.f3420a);
        }
    }

    private static class TextField implements Rule {

        /* renamed from: a  reason: collision with root package name */
        private final int f3421a;
        private final String[] b;

        TextField(int i, String[] strArr) {
            this.f3421a = i;
            this.b = strArr;
        }

        public int a() {
            int length = this.b.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                int length2 = this.b[length].length();
                if (length2 > i) {
                    i = length2;
                }
            }
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.b[calendar.get(this.f3421a)]);
        }
    }

    private static class UnpaddedNumberField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        private final int f3431a;

        public int a() {
            return 4;
        }

        UnpaddedNumberField(int i) {
            this.f3431a = i;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(this.f3431a));
        }

        public final void a(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            } else {
                stringBuffer.append(Integer.toString(i));
            }
        }
    }

    private static class UnpaddedMonthField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        static final UnpaddedMonthField f3430a = new UnpaddedMonthField();

        public int a() {
            return 2;
        }

        UnpaddedMonthField() {
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(2) + 1);
        }

        public final void a(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
                return;
            }
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class PaddedNumberField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        private final int f3418a;
        private final int b;

        public int a() {
            return 4;
        }

        PaddedNumberField(int i, int i2) {
            if (i2 >= 3) {
                this.f3418a = i;
                this.b = i2;
                return;
            }
            throw new IllegalArgumentException();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(this.f3418a));
        }

        public final void a(StringBuffer stringBuffer, int i) {
            int i2;
            if (i < 100) {
                int i3 = this.b;
                while (true) {
                    i3--;
                    if (i3 >= 2) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append((char) ((i / 10) + 48));
                        stringBuffer.append((char) ((i % 10) + 48));
                        return;
                    }
                }
            } else {
                if (i < 1000) {
                    i2 = 3;
                } else {
                    Validate.a(i > -1, "Negative values should not be possible", (long) i);
                    i2 = Integer.toString(i).length();
                }
                int i4 = this.b;
                while (true) {
                    i4--;
                    if (i4 >= i2) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append(Integer.toString(i));
                        return;
                    }
                }
            }
        }
    }

    private static class TwoDigitNumberField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        private final int f3428a;

        public int a() {
            return 2;
        }

        TwoDigitNumberField(int i) {
            this.f3428a = i;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(this.f3428a));
        }

        public final void a(StringBuffer stringBuffer, int i) {
            if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
                return;
            }
            stringBuffer.append(Integer.toString(i));
        }
    }

    private static class TwoDigitYearField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        static final TwoDigitYearField f3429a = new TwoDigitYearField();

        public int a() {
            return 2;
        }

        TwoDigitYearField() {
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(1) % 100);
        }

        public final void a(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class TwoDigitMonthField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        static final TwoDigitMonthField f3427a = new TwoDigitMonthField();

        public int a() {
            return 2;
        }

        TwoDigitMonthField() {
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(2) + 1);
        }

        public final void a(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class TwelveHourField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        private final NumberRule f3425a;

        TwelveHourField(NumberRule numberRule) {
            this.f3425a = numberRule;
        }

        public int a() {
            return this.f3425a.a();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(10);
            if (i == 0) {
                i = calendar.getLeastMaximum(10) + 1;
            }
            this.f3425a.a(stringBuffer, i);
        }

        public void a(StringBuffer stringBuffer, int i) {
            this.f3425a.a(stringBuffer, i);
        }
    }

    private static class TwentyFourHourField implements NumberRule {

        /* renamed from: a  reason: collision with root package name */
        private final NumberRule f3426a;

        TwentyFourHourField(NumberRule numberRule) {
            this.f3426a = numberRule;
        }

        public int a() {
            return this.f3426a.a();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(11);
            if (i == 0) {
                i = calendar.getMaximum(11) + 1;
            }
            this.f3426a.a(stringBuffer, i);
        }

        public void a(StringBuffer stringBuffer, int i) {
            this.f3426a.a(stringBuffer, i);
        }
    }

    private static class TimeZoneNameRule implements Rule {

        /* renamed from: a  reason: collision with root package name */
        private final TimeZone f3423a;
        private final boolean b;
        private final Locale c;
        private final int d;
        private final String e;
        private final String f;

        TimeZoneNameRule(TimeZone timeZone, boolean z, Locale locale, int i) {
            this.f3423a = timeZone;
            this.b = z;
            this.c = locale;
            this.d = i;
            if (z) {
                this.e = FastDateFormat.getTimeZoneDisplay(timeZone, false, i, locale);
                this.f = FastDateFormat.getTimeZoneDisplay(timeZone, true, i, locale);
                return;
            }
            this.e = null;
            this.f = null;
        }

        public int a() {
            if (this.b) {
                return Math.max(this.e.length(), this.f.length());
            }
            return this.d == 0 ? 4 : 40;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            if (!this.b) {
                TimeZone timeZone = calendar.getTimeZone();
                if (!timeZone.useDaylightTime() || calendar.get(16) == 0) {
                    stringBuffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, false, this.d, this.c));
                } else {
                    stringBuffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, true, this.d, this.c));
                }
            } else if (!this.f3423a.useDaylightTime() || calendar.get(16) == 0) {
                stringBuffer.append(this.e);
            } else {
                stringBuffer.append(this.f);
            }
        }
    }

    private static class TimeZoneNumberRule implements Rule {

        /* renamed from: a  reason: collision with root package name */
        static final TimeZoneNumberRule f3424a = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule b = new TimeZoneNumberRule(false);
        final boolean c;

        public int a() {
            return 5;
        }

        TimeZoneNumberRule(boolean z) {
            this.c = z;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                stringBuffer.append('-');
                i = -i;
            } else {
                stringBuffer.append('+');
            }
            int i2 = i / 3600000;
            stringBuffer.append((char) ((i2 / 10) + 48));
            stringBuffer.append((char) ((i2 % 10) + 48));
            if (this.c) {
                stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
            }
            int i3 = (i / 60000) - (i2 * 60);
            stringBuffer.append((char) ((i3 / 10) + 48));
            stringBuffer.append((char) ((i3 % 10) + 48));
        }
    }

    private static class TimeZoneDisplayKey {

        /* renamed from: a  reason: collision with root package name */
        private final TimeZone f3422a;
        private final int b;
        private final Locale c;

        TimeZoneDisplayKey(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.f3422a = timeZone;
            this.b = z ? i | Integer.MIN_VALUE : i;
            this.c = locale;
        }

        public int hashCode() {
            return (this.b * 31) + this.c.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeZoneDisplayKey)) {
                return false;
            }
            TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
            if (!this.f3422a.equals(timeZoneDisplayKey.f3422a) || this.b != timeZoneDisplayKey.b || !this.c.equals(timeZoneDisplayKey.c)) {
                return false;
            }
            return true;
        }
    }

    private static class Pair {

        /* renamed from: a  reason: collision with root package name */
        private final Object f3419a;
        private final Object b;

        public Pair(Object obj, Object obj2) {
            this.f3419a = obj;
            this.b = obj2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pair)) {
                return false;
            }
            Pair pair = (Pair) obj;
            if (this.f3419a != null ? this.f3419a.equals(pair.f3419a) : pair.f3419a == null) {
                if (this.b == null) {
                    if (pair.b == null) {
                        return true;
                    }
                } else if (this.b.equals(pair.b)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f3419a == null ? 0 : this.f3419a.hashCode();
            if (this.b != null) {
                i = this.b.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(Operators.ARRAY_START_STR);
            stringBuffer.append(this.f3419a);
            stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
            stringBuffer.append(this.b);
            stringBuffer.append(Operators.ARRAY_END);
            return stringBuffer.toString();
        }
    }
}
