package com.tencent.tinker.loader.shareutil;

import android.content.Intent;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ShareIntentUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1359a = "intent_return_code";
    public static final String b = "intent_patch_old_version";
    public static final String c = "intent_patch_new_version";
    public static final String d = "intent_is_protected_app";
    public static final String e = "intent_patch_mismatch_dex_path";
    public static final String f = "intent_patch_missing_dex_path";
    public static final String g = "intent_patch_dexes_path";
    public static final String h = "intent_patch_mismatch_lib_path";
    public static final String i = "intent_patch_missing_lib_path";
    public static final String j = "intent_patch_libs_path";
    public static final String k = "intent_patch_cost_time";
    public static final String l = "intent_patch_exception";
    public static final String m = "intent_patch_package_patch_check";
    public static final String n = "intent_patch_package_config";
    public static final String o = "intent_patch_system_ota";
    public static final String p = "intent_patch_oat_dir";
    public static final String q = "intent_patch_interpret_exception";
    private static final String r = "ShareIntentUtil";

    public static void a(Intent intent, int i2) {
        intent.putExtra(f1359a, i2);
    }

    public static int a(Intent intent) {
        return a(intent, f1359a, -10000);
    }

    public static void a(Intent intent, long j2) {
        intent.putExtra(k, j2);
    }

    public static long b(Intent intent) {
        return intent.getLongExtra(k, 0);
    }

    public static Throwable c(Intent intent) {
        Serializable c2 = c(intent, l);
        if (c2 != null) {
            return (Throwable) c2;
        }
        return null;
    }

    public static Throwable d(Intent intent) {
        Serializable c2 = c(intent, q);
        if (c2 != null) {
            return (Throwable) c2;
        }
        return null;
    }

    public static HashMap<String, String> e(Intent intent) {
        Serializable c2 = c(intent, g);
        if (c2 != null) {
            return (HashMap) c2;
        }
        return null;
    }

    public static HashMap<String, String> f(Intent intent) {
        Serializable c2 = c(intent, j);
        if (c2 != null) {
            return (HashMap) c2;
        }
        return null;
    }

    public static HashMap<String, String> g(Intent intent) {
        Serializable c2 = c(intent, n);
        if (c2 != null) {
            return (HashMap) c2;
        }
        return null;
    }

    public static ArrayList<String> a(Intent intent, String str) {
        if (intent == null) {
            return null;
        }
        try {
            return intent.getStringArrayListExtra(str);
        } catch (Exception e2) {
            Log.e(r, "getStringExtra exception:" + e2.getMessage());
            return null;
        }
    }

    public static String b(Intent intent, String str) {
        if (intent == null) {
            return null;
        }
        try {
            return intent.getStringExtra(str);
        } catch (Exception e2) {
            Log.e(r, "getStringExtra exception:" + e2.getMessage());
            return null;
        }
    }

    public static Serializable c(Intent intent, String str) {
        if (intent == null) {
            return null;
        }
        try {
            return intent.getSerializableExtra(str);
        } catch (Exception e2) {
            Log.e(r, "getSerializableExtra exception:" + e2.getMessage());
            return null;
        }
    }

    public static int a(Intent intent, String str, int i2) {
        if (intent == null) {
            return i2;
        }
        try {
            return intent.getIntExtra(str, i2);
        } catch (Exception e2) {
            Log.e(r, "getIntExtra exception:" + e2.getMessage());
            return i2;
        }
    }

    public static boolean a(Intent intent, String str, boolean z) {
        if (intent == null) {
            return z;
        }
        try {
            return intent.getBooleanExtra(str, z);
        } catch (Exception e2) {
            Log.e(r, "getBooleanExtra exception:" + e2.getMessage());
            return z;
        }
    }

    public static long a(Intent intent, String str, long j2) {
        if (intent == null) {
            return j2;
        }
        try {
            return intent.getLongExtra(str, j2);
        } catch (Exception e2) {
            Log.e(r, "getIntExtra exception:" + e2.getMessage());
            return j2;
        }
    }

    public static void a(Intent intent, ClassLoader classLoader) {
        try {
            intent.setExtrasClassLoader(classLoader);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
