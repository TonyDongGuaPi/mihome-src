package com.mi.util;

import android.graphics.drawable.Drawable;
import com.mi.MiApplicationContext;
import com.mi.mistatistic.sdk.data.EventData;

public class ResourceUtil {
    public static String a(String str) {
        return MiApplicationContext.f1260a.getResources().getString(MiApplicationContext.f1260a.getResources().getIdentifier(str, EventData.b, MiApplicationContext.f1260a.getPackageName()));
    }

    public static Drawable b(String str) {
        return MiApplicationContext.f1260a.getResources().getDrawable(MiApplicationContext.f1260a.getResources().getIdentifier(str, "drawable", MiApplicationContext.f1260a.getPackageName()));
    }

    public static int c(String str) {
        return MiApplicationContext.f1260a.getResources().getIdentifier(str, "drawable", MiApplicationContext.f1260a.getPackageName());
    }

    public static boolean d(String str) {
        return MiApplicationContext.f1260a.getResources().getIdentifier(str, EventData.b, MiApplicationContext.f1260a.getPackageName()) > 0;
    }
}
