package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import java.util.List;

public interface PermissionChecker {
    boolean a(Context context, List<String> list);

    boolean a(Context context, String... strArr);
}
