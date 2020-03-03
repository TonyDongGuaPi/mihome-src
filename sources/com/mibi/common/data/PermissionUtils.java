package com.mibi.common.data;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;

public class PermissionUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f7535a = {"android.permission.READ_PHONE_STATE"};
    private static final String b = "PermissionUtils";

    public static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean a(Context context, String... strArr) {
        for (String a2 : strArr) {
            if (!a(context, a2)) {
                return false;
            }
        }
        return true;
    }

    public static String[] b(Context context, String... strArr) {
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!a(context, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() > 0) {
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    public static boolean a(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
