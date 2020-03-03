package com.yanzhenjie.permission.checker;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.List;

public interface PermissionChecker {
    boolean a(@NonNull Context context, @NonNull List<String> list);

    boolean a(@NonNull Context context, @NonNull String... strArr);
}
