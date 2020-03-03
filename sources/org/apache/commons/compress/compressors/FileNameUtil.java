package org.apache.commons.compress.compressors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileNameUtil {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, String> f3310a = new HashMap();
    private final Map<String, String> b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final String g;

    public FileNameUtil(Map<String, String> map, String str) {
        this.b = Collections.unmodifiableMap(map);
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Map.Entry next : map.entrySet()) {
            int length = ((String) next.getKey()).length();
            i = length > i ? length : i;
            i3 = length < i3 ? length : i3;
            String str2 = (String) next.getValue();
            int length2 = str2.length();
            if (length2 > 0) {
                if (!this.f3310a.containsKey(str2)) {
                    this.f3310a.put(str2, next.getKey());
                }
                i2 = length2 > i2 ? length2 : i2;
                if (length2 < i4) {
                    i4 = length2;
                }
            }
        }
        this.c = i;
        this.e = i2;
        this.d = i3;
        this.f = i4;
        this.g = str;
    }

    public boolean a(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        int i = this.d;
        while (i <= this.c && i < length) {
            if (this.b.containsKey(lowerCase.substring(length - i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    public String b(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        int i = this.d;
        while (i <= this.c && i < length) {
            int i2 = length - i;
            String str2 = this.b.get(lowerCase.substring(i2));
            if (str2 != null) {
                return str.substring(0, i2) + str2;
            }
            i++;
        }
        return str;
    }

    public String c(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        int i = this.f;
        while (i <= this.e && i < length) {
            int i2 = length - i;
            String str2 = this.f3310a.get(lowerCase.substring(i2));
            if (str2 != null) {
                return str.substring(0, i2) + str2;
            }
            i++;
        }
        return str + this.g;
    }
}
