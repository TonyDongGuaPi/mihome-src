package com.xiaomi.mishopsdk.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.mishopsdk.volley.VolleyError;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.io.entity.ShopApiBaseResult;
import com.xiaomi.mishopsdk.io.http.ShopApiError;

public class ToastUtil {
    private static Toast sLastToast;

    public static void clear() {
        if (sLastToast != null) {
            sLastToast.cancel();
        }
    }

    private static void show(Context context, CharSequence charSequence, int i) {
        if (sLastToast != null) {
            sLastToast.cancel();
        }
        Toast makeText = Toast.makeText(ShopApp.instance, charSequence, i);
        sLastToast = makeText;
        makeText.show();
    }

    public static void show(CharSequence charSequence) {
        show(ShopApp.instance, charSequence, 0);
    }

    public static void show(Activity activity, int i) {
        if (activity != null) {
            show(activity, activity.getString(i), 0);
        }
    }

    public static void showLong(CharSequence charSequence) {
        show(ShopApp.instance, charSequence, 1);
    }

    public static void showLong(Activity activity, int i) {
        if (activity != null) {
            show(activity, activity.getString(i), 1);
        }
    }

    public static void showApiError(VolleyError volleyError) {
        ShopApiBaseResult apiBaseResult;
        if ((volleyError instanceof ShopApiError) && (apiBaseResult = ((ShopApiError) volleyError).getApiBaseResult()) != null && !TextUtils.isEmpty(apiBaseResult.mDescription)) {
            show(apiBaseResult.mDescription);
        }
    }
}
