package com.mi.global.bbs.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

public class LanguageUtils {
    public static void setLanguage(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = getSetLocale();
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static Locale getSetLocale() {
        return Locale.ENGLISH;
    }
}
