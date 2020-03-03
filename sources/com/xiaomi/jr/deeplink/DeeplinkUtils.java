package com.xiaomi.jr.deeplink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;

public class DeeplinkUtils {
    public static boolean DEBUG = false;
    private static final String TAG = "DeeplinkUtils";

    public static boolean startActivity(Object obj, Intent intent) {
        try {
            if (obj instanceof Activity) {
                ((Activity) obj).startActivity(intent);
                return true;
            } else if (obj instanceof Fragment) {
                ((Fragment) obj).startActivity(intent);
                return true;
            } else if (obj instanceof Context) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.addCategory("android.intent.category.DEFAULT");
                ((Context) obj).startActivity(intent);
                return true;
            } else {
                if (DEBUG) {
                    Log.e(TAG, "startActivity failed - support caller types: Activity, Fragment, Context");
                }
                return false;
            }
        } catch (Exception e) {
            if (DEBUG) {
                Log.e(TAG, "startActivity failed - " + e);
            }
            return false;
        }
    }

    public static void startActivity(Activity activity, Class<?> cls) {
        startActivity((Object) activity, new Intent(activity, cls));
    }

    public static boolean startActivityForResult(Object obj, Intent intent, int i) {
        try {
            if (obj instanceof Activity) {
                ((Activity) obj).startActivityForResult(intent, i);
                return true;
            } else if (obj instanceof Fragment) {
                ((Fragment) obj).startActivityForResult(intent, i);
                return true;
            } else {
                if (DEBUG) {
                    Log.e(TAG, "startActivityForResult failed - support caller types: Activity, Fragment");
                }
                return false;
            }
        } catch (Exception e) {
            if (DEBUG) {
                Log.e(TAG, "startActivityForResult failed - " + e);
            }
            return false;
        }
    }

    public static void openDeeplink(Object obj, String str, String str2) {
        openDeeplink(obj, str, str2, (String) null);
    }

    public static void openDeeplink(Object obj, String str, String str2, String str3) {
        openDeeplink(obj, str, str2, str3, (Bundle) null);
    }

    public static void openDeeplink(Object obj, String str, String str2, String str3, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("title", str);
        bundle2.putString("fallback", str3);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        DeeplinkPolicy.openDeeplink(obj, str2, bundle2);
    }

    public static void openExternalUrl(Object obj, String str) {
        openExternalUrl(obj, str, (String) null);
    }

    public static void openExternalUrl(Object obj, String str, String str2) {
        Intent buildExternalIntent = buildExternalIntent(str);
        if (buildExternalIntent == null) {
            if (DEBUG) {
                Log.e(TAG, "intent uri invalid - " + str);
            }
        } else if (!startActivity(obj, buildExternalIntent)) {
            Intent buildExternalIntent2 = buildExternalIntent(str2);
            if (buildExternalIntent2 == null) {
                if (DEBUG) {
                    Log.e(TAG, "intent uri invalid - " + str2);
                }
            } else if (!startActivity(obj, buildExternalIntent2)) {
                String str3 = buildExternalIntent.getPackage();
                if (!TextUtils.isEmpty(str3)) {
                    if (!startActivity(obj, new Intent("android.intent.action.VIEW", Uri.parse("market://details?back=true&ref=mifinance&id=" + str3)))) {
                        startActivity(obj, new Intent("android.intent.action.VIEW", Uri.parse("http://m.app.xiaomi.com/details?ref=mifinance&id=" + str3)));
                    }
                } else if (DEBUG) {
                    Log.e(TAG, "package name invalid - " + str3);
                }
            }
        }
    }

    private static Intent buildExternalIntent(String str) {
        Intent parseUrlIntoIntent = Utils.parseUrlIntoIntent(str);
        if (parseUrlIntoIntent == null) {
            return null;
        }
        if (str.startsWith("http")) {
            parseUrlIntoIntent.addCategory("android.intent.category.BROWSABLE");
        }
        return parseUrlIntoIntent;
    }

    public static Intent buildIntent(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString("fallback", str3);
        return DeeplinkPolicy.buildIntent(context, str2, bundle);
    }

    public static DeeplinkPolicy.Target buildTarget(String str) {
        return DeeplinkPolicy.buildTarget(str);
    }
}
