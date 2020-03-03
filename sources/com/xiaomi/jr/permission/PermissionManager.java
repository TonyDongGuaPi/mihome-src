package com.xiaomi.jr.permission;

import android.content.Context;
import android.support.annotation.NonNull;
import com.xiaomi.jr.permission.Request;
import java.util.Arrays;
import java.util.List;

public class PermissionManager {

    /* renamed from: a  reason: collision with root package name */
    private static PermissionDialogDelegate f1450a;

    public static void a(PermissionDialogDelegate permissionDialogDelegate) {
        f1450a = permissionDialogDelegate;
    }

    public static void a(@NonNull Context context, @NonNull String str, Request.Callback callback) {
        a(context, new String[]{str}, callback);
    }

    public static void a(@NonNull Context context, @NonNull String[] strArr, Request.Callback callback) {
        Request.a(context).a((List<String>) Arrays.asList(strArr)).a(callback).a(f1450a).c();
    }
}
