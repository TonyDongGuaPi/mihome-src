package com.xiaomi.passport.uicontroller;

import android.content.Context;

public interface MiPassportUIControllerFactory {
    public static final MiPassportUIControllerFactory DEFAULT_IMPL = new MiPassportUIControllerFactory() {
        public MiPassportUIController newMUiController(Context context, String str, String str2) {
            return new MiPassportUIController(context, str, str2);
        }
    };

    MiPassportUIController newMUiController(Context context, String str, String str2);
}
