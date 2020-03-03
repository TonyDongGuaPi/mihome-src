package com.xiaomi.infra.galaxy.fds.auth.signature;

import com.alipay.sdk.sys.a;
import com.google.common.collect.LinkedListMultimap;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<SimpleDateFormat> f10153a = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    };
    private static final ThreadLocal<SimpleDateFormat> b = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    };
    private static final ThreadLocal<SimpleDateFormat> c = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    };

    public static LinkedListMultimap<String, String> a(URI uri) {
        LinkedListMultimap<String, String> create = LinkedListMultimap.create();
        String query = uri.getQuery();
        if (query != null) {
            for (String str : query.split(a.b)) {
                String[] split = str.split("=");
                if (split.length >= 2) {
                    create.put(split[0], str.substring(split[0].length() + 1));
                } else {
                    create.put(split[0], "");
                }
            }
        }
        return create;
    }

    public static Date a(String str) {
        Date a2 = a(str, f10153a.get());
        if (a2 == null) {
            a2 = a(str, b.get());
        }
        return a2 == null ? a(str, c.get()) : a2;
    }

    public static long b(String str) {
        Date a2 = a(str);
        if (a2 != null) {
            return a2.getTime();
        }
        return 0;
    }

    public static String a(Date date) {
        return f10153a.get().format(date);
    }

    private static Date a(String str, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }
}
