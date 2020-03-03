package com.xiaomi.smarthome.library.apache.http.client.utils;

import com.xiaomi.smarthome.library.apache.http.Header;
import com.xiaomi.smarthome.library.apache.http.HttpEntity;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair;
import com.xiaomi.smarthome.library.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class URLEncodedUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18446a = "application/x-www-form-urlencoded";
    private static final String b = "&";
    private static final String c = "=";

    public static List<NameValuePair> a(URI uri, String str) {
        List<NameValuePair> emptyList = Collections.emptyList();
        String rawQuery = uri.getRawQuery();
        if (rawQuery == null || rawQuery.length() <= 0) {
            return emptyList;
        }
        ArrayList arrayList = new ArrayList();
        a(arrayList, new Scanner(rawQuery), str);
        return arrayList;
    }

    public static List<NameValuePair> a(HttpEntity httpEntity) throws IOException {
        List<NameValuePair> emptyList = Collections.emptyList();
        if (b(httpEntity)) {
            String c2 = EntityUtils.c(httpEntity);
            Header e = httpEntity.e();
            if (c2 != null && c2.length() > 0) {
                emptyList = new ArrayList<>();
                a(emptyList, new Scanner(c2), e != null ? e.b() : null);
            }
        }
        return emptyList;
    }

    public static boolean b(HttpEntity httpEntity) {
        Header d = httpEntity.d();
        return d != null && d.b().equalsIgnoreCase("application/x-www-form-urlencoded");
    }

    public static void a(List<NameValuePair> list, Scanner scanner, String str) {
        scanner.useDelimiter("&");
        while (scanner.hasNext()) {
            String[] split = scanner.next().split("=");
            if (split.length == 0 || split.length > 2) {
                throw new IllegalArgumentException("bad parameter");
            }
            String a2 = a(split[0], str);
            String str2 = null;
            if (split.length == 2) {
                str2 = a(split[1], str);
            }
            list.add(new BasicNameValuePair(a2, str2));
        }
    }

    public static String a(List<? extends NameValuePair> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (NameValuePair nameValuePair : list) {
            String b2 = b(nameValuePair.a(), str);
            String b3 = nameValuePair.b();
            String b4 = b3 != null ? b(b3, str) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(b2);
            sb.append("=");
            sb.append(b4);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String b(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
