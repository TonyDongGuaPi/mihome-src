package com.xiaomi.smarthome.newui.utils;

import android.support.annotation.UiThread;
import android.text.TextUtils;
import java.lang.Character;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringLengthUtils {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Integer> f20745a = new ConcurrentHashMap();

    @UiThread
    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        Integer num = f20745a.get(str);
        if (num == null) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (char c : str.toCharArray()) {
                if (a(c)) {
                    i++;
                } else if (b(c)) {
                    i2++;
                } else {
                    i3++;
                }
            }
            num = Integer.valueOf((i * 2) + i2 + (i3 * 2));
            f20745a.put(str, num);
        }
        return num.intValue();
    }

    public static void a() {
        f20745a.clear();
    }

    private static boolean a(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    private static boolean b(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.BASIC_LATIN || of == Character.UnicodeBlock.LATIN_EXTENDED_A || of == Character.UnicodeBlock.LATIN_EXTENDED_B;
    }
}
