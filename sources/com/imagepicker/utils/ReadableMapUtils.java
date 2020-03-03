package com.imagepicker.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableMap;

public class ReadableMapUtils {
    @NonNull
    public static boolean a(@NonNull Class cls, @NonNull ReadableMap readableMap, @NonNull String str) {
        if (!readableMap.hasKey(str) || readableMap.isNull(str)) {
            return false;
        }
        if (String.class.equals(cls)) {
            return !TextUtils.isEmpty(readableMap.getString(str));
        }
        return true;
    }

    @NonNull
    public static boolean a(@NonNull ReadableMap readableMap, @NonNull String str) {
        return a(ReadableMap.class, readableMap, str);
    }

    @NonNull
    public static boolean b(@NonNull ReadableMap readableMap, @NonNull String str) {
        return a(String.class, readableMap, str);
    }
}
