package com.miui.tsmclient.util;

import android.content.Context;
import com.mi.mistatistic.sdk.data.EventData;

public class ResUtils {
    public static int getStringIdentifier(Context context, String str) {
        return getIdentifier(context, str, EventData.b);
    }

    public static int getStyleIdentifier(Context context, String str) {
        return getIdentifier(context, str, "style");
    }

    public static int getDimenIdentifier(Context context, String str) {
        return getIdentifier(context, str, "dimen");
    }

    public static int getDrawableIdentifier(Context context, String str) {
        return getIdentifier(context, str, "drawable");
    }

    public static int getAnimatorIdentifier(Context context, String str) {
        return getIdentifier(context, str, "animator");
    }

    public static int getId(Context context, String str) {
        return getIdentifier(context, str, "id");
    }

    public static String getString(Context context, String str) {
        int stringIdentifier = getStringIdentifier(context, str);
        if (stringIdentifier != 0) {
            return context.getString(stringIdentifier);
        }
        return "";
    }

    private static int getIdentifier(Context context, String str, String str2) {
        int identifier = context.getResources().getIdentifier(str, str2, "com.miui.tsmclient");
        return identifier == 0 ? context.getResources().getIdentifier(str, str2, context.getPackageName()) : identifier;
    }
}
