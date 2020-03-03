package com.yanzhenjie.permission.checker;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.List;

public final class DoubleChecker implements PermissionChecker {

    /* renamed from: a  reason: collision with root package name */
    private static final PermissionChecker f2414a = new StandardChecker();
    private static final PermissionChecker b = new StrictChecker();

    public boolean a(@NonNull Context context, @NonNull String... strArr) {
        return f2414a.a(context, strArr) && b.a(context, strArr);
    }

    public boolean a(@NonNull Context context, @NonNull List<String> list) {
        return f2414a.a(context, list) && b.a(context, list);
    }
}
