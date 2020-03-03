package com.ximalaya.ting.android.player;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public class XMediaPlayerConstants {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f2293a = false;
    public static final boolean b = false;
    public static final int c = 65536;
    public static final int d = 3;
    public static final int e = 1024;
    public static final String f = "is_charge";
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;
    public static int l = 30;
    public static String m = (Environment.getExternalStorageDirectory().getPath() + "/ting");
    public static String n = (m + "/player_caching");
    public static String o = (n + "/audio");
    public static String p = "playcache.info";
    public static String q = (o + File.separator + p);
    public static final int r = 30000;
    public static final int s = 20000;
    public static final String t = ".xm";
    public static final String u = ".x2m";

    public static void a(Context context) {
        if (context != null && context.getExternalFilesDir("") != null) {
            try {
                m = context.getExternalFilesDir("") + "";
                n = m + "/player_caching";
                o = n + "/audio";
                q = o + File.separator + p;
                new File(o).mkdirs();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
