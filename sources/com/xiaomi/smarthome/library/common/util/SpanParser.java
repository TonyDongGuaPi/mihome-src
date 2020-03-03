package com.xiaomi.smarthome.library.common.util;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpanParser {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18733a = SHApplication.getAppContext().getResources().getColor(R.color.black_40_transparent);
    public static final int b = SHApplication.getAppContext().getResources().getColor(R.color.black_30_transparent);
    public static final int c = SHApplication.getAppContext().getResources().getColor(R.color.class_text_trans_40);
    private static final String d = "p";
    private static final String e = String.format("([^<>/]*)<\\s*%s\\s*([^>]*)\\s*>([^<>]*)<\\s*/%s\\s*>([^<>/]*)", new Object[]{"p", "p"});
    private static final String f = "([a-z]+)\\s*=\\s*\"([^\"]+)\"";

    public static void a(TextView textView, String str, boolean z, boolean z2) {
        try {
            SpannableStringBuilder a2 = a(a(str), z, z2);
            if (a2 != null) {
                textView.setText(a2);
            }
        } catch (Exception unused) {
            textView.setText(str);
        }
    }

    private static SpannableStringBuilder a(List<Span> list, boolean z, boolean z2) {
        if (list == null || list.size() == 0) {
            return null;
        }
        int size = list.size();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Span span = list.get(i2);
            if (!TextUtils.isEmpty(span.b)) {
                i = a(spannableStringBuilder, span.b, z ? c : f18733a, i);
            }
            i = a(spannableStringBuilder, span.c, a(span, z, z2), i);
            if (!TextUtils.isEmpty(span.d)) {
                i = a(spannableStringBuilder, span.d, z ? c : f18733a, i);
            }
        }
        return spannableStringBuilder;
    }

    private static int a(Span span, boolean z, boolean z2) {
        int i = z ? c : f18733a;
        if (span.e != null && span.e.containsKey("color")) {
            try {
                return Color.parseColor((String) span.e.get("color"));
            } catch (Exception unused) {
            }
        }
        return i;
    }

    private static int a(SpannableStringBuilder spannableStringBuilder, String str, int i, int i2) {
        spannableStringBuilder.append(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), i2, str.length() + i2, 17);
        return i2 + str.length();
    }

    public static List<Span> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        Matcher matcher = Pattern.compile(e).matcher(str);
        while (matcher.find()) {
            Span span = new Span();
            String unused = span.f18734a = matcher.group(0);
            String unused2 = span.b = matcher.group(1);
            HashMap unused3 = span.e = b(matcher.group(2));
            String unused4 = span.c = matcher.group(3);
            String unused5 = span.d = matcher.group(4);
            arrayList.add(span);
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new Span(str));
        }
        return arrayList;
    }

    private static HashMap<String, String> b(String str) {
        Matcher matcher = Pattern.compile(f).matcher(str);
        HashMap<String, String> hashMap = new HashMap<>();
        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                hashMap.put(matcher.group(1), matcher.group(2));
            }
        }
        return hashMap;
    }

    public static class Span {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f18734a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public HashMap<String, String> e;

        public Span() {
        }

        public Span(String str) {
            this.c = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("orig = %s\r\n", new Object[]{this.f18734a}));
            sb.append(String.format("prefix = %s\r\n", new Object[]{this.b}));
            sb.append(String.format("text = %s\r\n", new Object[]{this.c}));
            sb.append(String.format("suffix = %s\r\n", new Object[]{this.d}));
            if (this.e != null && this.e.size() > 0) {
                for (Map.Entry next : this.e.entrySet()) {
                    sb.append(String.format("%s = %s\r\n", new Object[]{next.getKey(), next.getValue()}));
                }
            }
            return sb.toString();
        }
    }
}
