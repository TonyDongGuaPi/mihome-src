package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alipay.sdk.sys.a;
import com.xiaomi.youpin.network.annotation.Encoding;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18736a = "[一-龥]";
    public static final int b = 19968;
    public static final int c = 40879;
    public static final String d = "[a-zA-Z]";
    public static final int e = 65;
    public static final int f = 90;
    public static final int g = 97;
    public static final int h = 122;
    public static final int i = 32;
    public static final int j = 126;
    public static final String k = "[0-9]";
    public static final int l = 48;
    public static final int m = 57;
    public static final String n = "[\"!$^*{}<>?|\\[\\]=\\\\]|--";
    public static final Pattern o = Pattern.compile("[\"!$^*{}<>?|\\[\\]=\\\\]|--");
    private static WeakReference<TextView> p = new WeakReference<>((Object) null);
    private static final String q = "[\"!$^*{}<>?|\\[\\]=\\\\]|--";

    public static boolean a(char c2) {
        return (c2 == 0 || c2 == 9 || c2 == 10 || c2 == 13 || (c2 >= ' ' && c2 <= 55295) || ((c2 >= 57344 && c2 <= 65533) || (c2 >= 0 && c2 <= 65535))) ? false : true;
    }

    public static boolean b(char c2) {
        return !(c2 == 0 || c2 == 9 || c2 == 10 || c2 == 13 || ((c2 >= ' ' && c2 <= 55295) || ((c2 >= 57344 && c2 <= 65533) || c2 >= 0))) || c2 == 169 || c2 == 174 || c2 == 8482 || c2 == 12336 || (c2 >= 9654 && c2 <= 10175) || c2 == 9000 || ((c2 >= 9193 && c2 <= 9210) || ((c2 >= 61440 && c2 <= 65535) || ((c2 >= 9986 && c2 <= 10160) || (c2 >= 62977 && c2 <= 63055))));
    }

    public static String a(String str) {
        try {
            return URLEncoder.encode(str, Encoding.GBK);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String b(String str) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt == '%') {
                try {
                    sb.append((char) Integer.parseInt(str.substring(i2 + 1, i2 + 3), 16));
                    i2 += 2;
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException();
                }
            } else if (charAt != '+') {
                sb.append(charAt);
            } else {
                sb.append(' ');
            }
            i2++;
        }
        try {
            return new String(sb.toString().getBytes("8859_1"), "UTF-8");
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        return bArr == null ? "" : new String(bArr);
    }

    public static boolean c(String str) {
        return str == null || "".equals(str) || "NULL".equals(str);
    }

    public static boolean a(Object[] objArr) {
        return a(objArr, 1);
    }

    public static boolean a(Object[] objArr, int i2) {
        return objArr == null || objArr.length < i2;
    }

    public static boolean d(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if ("0123456789".indexOf(str.charAt(i2)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static String b(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                str = str + "0" + hexString;
            } else {
                str = str + hexString;
            }
        }
        return str.toUpperCase();
    }

    public static String e(String str) {
        StringBuilder sb = new StringBuilder();
        for (int length = str.length() - 1; length >= 0; length--) {
            sb.append(str.charAt(length));
        }
        return sb.toString();
    }

    public static String a() {
        return UUID.randomUUID().toString();
    }

    public static String a(int i2) {
        return a((long) i2) + "/s";
    }

    public static String a(long j2) {
        double d2 = (double) j2;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        if (d3 == 0.0d) {
            return String.format("%d B", new Object[]{Long.valueOf(j2)});
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return String.format("%.2f K", new Object[]{Double.valueOf(d3)});
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            return String.format("%.2f M", new Object[]{Double.valueOf(d4)});
        }
        return String.format("%.2f G", new Object[]{Double.valueOf(d5)});
    }

    public static String b(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        int i5 = i2 % 60;
        if (i3 != 0) {
            return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
        }
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    public static int f(String str) {
        try {
            if (!c(str)) {
                return Integer.valueOf(str).intValue();
            }
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String g(String str) {
        try {
            if (c(str)) {
                return "";
            }
            for (int i2 = 0; i2 < 2; i2++) {
                str = str.substring(str.indexOf(124) + 1);
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(long j2, long j3) {
        if (j3 == 0) {
            return null;
        }
        double d2 = (double) (j2 * 100);
        double d3 = (double) j3;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return String.format("%.2f%%", new Object[]{Double.valueOf(d2 / d3)});
    }

    public static boolean h(String str) {
        return !c(str) && str.matches("(((http|ftp|https|file)://)?([\\w\\-]+\\.)+[\\w\\-]+(/[\\w\\u4e00-\\u9fa5\\-\\./?\\@\\%\\!\\&=\\+\\~\\:\\#\\;\\,]*)?)");
    }

    public static String i(String str) {
        if (c(str)) {
            return "";
        }
        String str2 = new String();
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static String j(String str) {
        String str2 = new String();
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static String a(String str, String str2) {
        try {
            URL url = new URL(str);
            return new URL(url.getProtocol(), url.getHost(), str2).toString();
        } catch (MalformedURLException unused) {
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String k(String str) {
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException unused) {
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String l(String str) {
        int lastIndexOf;
        String k2 = k(str);
        int lastIndexOf2 = k2.lastIndexOf(46);
        if (lastIndexOf2 == -1 || (lastIndexOf = k2.substring(0, lastIndexOf2).lastIndexOf(46)) == -1) {
            return "";
        }
        String substring = k2.substring(lastIndexOf + 1);
        return substring.endsWith("/") ? substring.substring(0, substring.length() - 1) : substring;
    }

    public static Map<String, String> m(String str) {
        String str2;
        try {
            str2 = new URL(str).getQuery();
        } catch (MalformedURLException unused) {
            str2 = "";
        }
        HashMap hashMap = new HashMap();
        if (str2 == null) {
            return hashMap;
        }
        for (String split : str2.split(a.b)) {
            String[] split2 = split.split("=");
            if (split2.length == 2) {
                hashMap.put(split2[0], split2[1]);
            }
        }
        return hashMap;
    }

    public static float n(String str) {
        try {
            if (!c(str)) {
                return Float.valueOf(str).floatValue();
            }
            return 0.0f;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public static String o(String str) {
        try {
            if (c(str)) {
                return "";
            }
            String substring = str.substring(str.indexOf(124) + 1);
            return substring.substring(0, substring.indexOf(124));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String p(String str) {
        try {
            if (c(str)) {
                return "";
            }
            String j2 = j(str);
            for (int i2 = 0; i2 < 2; i2++) {
                j2 = j2.substring(j2.indexOf(124) + 1);
            }
            int indexOf = j2.indexOf(124);
            if (indexOf >= 0) {
                return j2.substring(0, indexOf);
            }
            return j2.substring(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String q(String str) {
        if (r(str) || str.length() != 8) {
            return str;
        }
        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
    }

    public static boolean r(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String b(String str, String str2) {
        try {
            if (str2.equals("")) {
                return str;
            }
            String str3 = "," + str + ",";
            String str4 = "";
            boolean z = true;
            for (String str5 : str2.split(",")) {
                if (!str5.equals("")) {
                    if (!str3.contains("," + str5 + ",")) {
                        if (!z) {
                            str4 = str4 + ",";
                        }
                        str4 = str4 + str5;
                        z = false;
                    }
                }
            }
            if (str.equals("")) {
                return str4;
            }
            if (str4.equals("")) {
                return str;
            }
            return str + "," + str4;
        } catch (Exception unused) {
            return str;
        }
    }

    public static String s(String str) {
        return (!r(str) && str.startsWith("\"") && str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    public static String b(long j2) {
        double d2 = (double) (j2 / 1024);
        if (d2 < 1.0d) {
            return String.format("%d K", new Object[]{Long.valueOf(j2)});
        }
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return String.format("%.2f M", new Object[]{Double.valueOf(d2)});
        }
        return String.format("%.2f G", new Object[]{Double.valueOf(d3)});
    }

    public static String c(int i2) {
        return b((long) i2) + "/s";
    }

    public static String a(Context context, String str, float f2, TextView textView, int i2) {
        if (TextUtils.isEmpty(str) || str.length() < 4) {
            return str;
        }
        TextView textView2 = (TextView) p.get();
        if (textView2 == null) {
            textView2 = new TextView(context);
            textView2.setSingleLine();
            textView2.setEllipsize(TextUtils.TruncateAt.END);
            textView2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            p = new WeakReference<>(textView2);
        }
        float f3 = context.getResources().getDisplayMetrics().density;
        textView2.setText(str);
        textView2.setTextSize(0, f2);
        textView.getTextScaleX();
        textView2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * ((float) i2)), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        try {
            int lineStart = textView2.getLayout().getLineStart(0);
            int ellipsisStart = textView2.getLayout().getEllipsisStart(0);
            if (ellipsisStart > 0) {
                return textView2.getText().toString().substring(lineStart, ellipsisStart) + "... ";
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static int a(Context context, String str, float f2, int i2) {
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(i2, f2);
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return textView.getMeasuredWidth();
    }

    public static boolean t(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (b(str.charAt(i2))) {
                return true;
            }
        }
        return false;
    }

    public static int a(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < charSequence.length(); i5++) {
            int codePointAt = Character.codePointAt(charSequence, i5);
            if (codePointAt >= 19968 && codePointAt <= 40879) {
                i2++;
            } else if (codePointAt < 32 || codePointAt > 126) {
                i4++;
            } else {
                i3++;
            }
        }
        return (i2 * 2) + 0 + i3 + i4;
    }

    public static int a(CharSequence charSequence, int i2) {
        if (TextUtils.isEmpty(charSequence) || i2 <= 0) {
            return 0;
        }
        int i3 = 1;
        int i4 = 0;
        while (i3 <= charSequence.length() && a(charSequence.subSequence(0, i3)) <= i2) {
            i4 = i3;
            i3++;
        }
        return i4;
    }

    public static int b(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < charSequence.length(); i4++) {
            if (String.valueOf(charSequence.charAt(i4)).matches("[一-龥]")) {
                i2++;
            } else {
                i3++;
            }
        }
        return (i2 * 2) + i3;
    }

    public static String b(CharSequence charSequence, int i2) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        for (int i4 = 0; i4 < charSequence.length(); i4++) {
            String valueOf = String.valueOf(charSequence.charAt(i4));
            i3 = valueOf.matches("[一-龥]") ? i3 + 2 : i3 + 1;
            if (i3 <= i2) {
                sb.append(valueOf);
            }
            if (i3 >= i2) {
                break;
            }
        }
        return sb.toString();
    }

    public static boolean u(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String trim = str.trim();
        if (!TextUtils.isEmpty(trim) && TextUtils.equals(o.matcher(trim).replaceAll("").trim(), trim)) {
            return false;
        }
        return true;
    }

    public static boolean a(CharSequence charSequence, String str) {
        if (TextUtils.isEmpty(charSequence)) {
            return true;
        }
        String trim = ((String) charSequence).trim();
        if (!TextUtils.isEmpty(trim) && TextUtils.equals(Pattern.compile(str).matcher(trim).replaceAll("").trim(), trim)) {
            return false;
        }
        return true;
    }

    public static SpannableStringBuilder a(Context context, int i2, int i3, ClickableSpan clickableSpan) {
        String string = context.getString(i2);
        String string2 = context.getString(i3, new Object[]{string});
        int indexOf = string2.indexOf(string);
        if (indexOf < 0) {
            indexOf = 0;
        }
        int length = string.length() + indexOf;
        if (length > string2.length()) {
            length = string2.length() - 1;
        }
        if (indexOf > length) {
            length = indexOf;
        }
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(string2);
        valueOf.setSpan(clickableSpan, indexOf, length, 33);
        return valueOf;
    }

    public static String a(Activity activity, Locale locale, int i2) {
        try {
            return a(activity, locale).getString(i2);
        } catch (Exception unused) {
            return activity.getApplicationContext().getString(i2);
        }
    }

    public static boolean a(Activity activity, Locale locale, int i2, TextView textView) {
        try {
            textView.setText(a(activity, locale).getString(i2));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Resources a(Activity activity, Locale locale) {
        Configuration configuration = activity.getApplicationContext().getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 17) {
            Configuration configuration2 = new Configuration(configuration);
            configuration2.setLocale(locale);
            return activity.getApplicationContext().createConfigurationContext(configuration2).getResources();
        }
        configuration.locale = locale;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new Resources(activity.getApplicationContext().getAssets(), displayMetrics, configuration);
    }
}
