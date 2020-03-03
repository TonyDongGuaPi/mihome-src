package com.mipay.sdk.app;

import android.graphics.Color;
import com.taobao.weex.ui.module.WXModalUIModule;
import java.io.File;
import java.util.Locale;

public class Constants {
    public static int BACKGROUND_COLOR = Color.parseColor("#f0f0f0");
    public static int ID_CAMERA_TEXT = 3;
    public static int ID_CANCEL_TEXT = 2;
    public static int ID_INFO_TEXT = 0;
    public static int ID_OK_TEXT = 1;
    public static int INFO_TEXT_COLOR = Color.parseColor("#bababa");
    public static int INFO_TEXT_SIZE = 11;
    public static String KEY_URL = "url";
    public static final boolean STAGING = (new File("/data/system/server_staging").exists() || new File("/sdcard/server_staging").exists());
    public static final boolean TEST = (new File("/data/system/server_testing").exists() || new File("/sdcard/server_testing").exists());
    public static String[][] UI_STRINGS = {new String[]{"正在跳转到小米钱包", "正在跳轉到小米錢包", "Loading"}, new String[]{"确定", "確定", "OK"}, new String[]{"取消", "取消", WXModalUIModule.CANCEL}, new String[]{"请打开相机权限", "請打開相機權限", "Please open camera permission"}};

    /* renamed from: a  reason: collision with root package name */
    private static int f8154a = 0;
    private static int b = 1;
    private static int c = 2;

    public static String getString(int i) {
        if (UI_STRINGS.length < i) {
            return "";
        }
        Locale locale = Locale.getDefault();
        return locale.equals(Locale.SIMPLIFIED_CHINESE) ? UI_STRINGS[i][f8154a] : locale.equals(Locale.TRADITIONAL_CHINESE) ? UI_STRINGS[i][b] : UI_STRINGS[i][c];
    }
}
