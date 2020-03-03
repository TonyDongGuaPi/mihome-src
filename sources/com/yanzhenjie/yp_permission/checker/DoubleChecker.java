package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import java.util.List;

public final class DoubleChecker implements PermissionChecker {

    /* renamed from: a  reason: collision with root package name */
    private static final PermissionChecker f2443a = new StandardChecker();
    private static final PermissionChecker b = new StrictChecker();

    public boolean a(Context context, String... strArr) {
        return b.a(context, strArr) && f2443a.a(context, strArr);
    }

    public boolean a(Context context, List<String> list) {
        return b.a(context, list) && f2443a.a(context, list);
    }
}
