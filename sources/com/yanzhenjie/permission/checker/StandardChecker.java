package com.yanzhenjie.permission.checker;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

public final class StandardChecker implements PermissionChecker {
    public boolean a(@NonNull Context context, @NonNull String... strArr) {
        return a(context, (List<String>) Arrays.asList(strArr));
    }

    public boolean a(@NonNull Context context, @NonNull List<String> list) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String next : list) {
            if (context.checkPermission(next, Process.myPid(), Process.myUid()) == -1) {
                return false;
            }
            String permissionToOp = AppOpsManager.permissionToOp(next);
            if (!TextUtils.isEmpty(permissionToOp) && ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(permissionToOp, context.getPackageName()) != 0) {
                return false;
            }
        }
        return true;
    }
}
