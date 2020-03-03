package com.amap.openapi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class bd {
    @NonNull
    public static String a(@Nullable String str) {
        return str == null ? "" : str;
    }
}
