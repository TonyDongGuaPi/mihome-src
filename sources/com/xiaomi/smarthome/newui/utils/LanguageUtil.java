package com.xiaomi.smarthome.newui.utils;

import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.util.Locale;

public class LanguageUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Locale f20743a = new Locale("zh", ServerCompact.b);

    public static boolean a(Locale locale, Locale locale2) {
        if (locale == locale2) {
            return true;
        }
        if (locale == null || locale2 == null || !locale.getLanguage().equalsIgnoreCase(locale2.getLanguage()) || !locale.getCountry().equalsIgnoreCase(locale2.getCountry())) {
            return false;
        }
        return true;
    }
}
