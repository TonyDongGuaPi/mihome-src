package com.yanzhenjie.yp_permission.checker;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

public final class StandardChecker implements PermissionChecker {
    public boolean a(Context context, String... strArr) {
        return a(context, (List<String>) Arrays.asList(strArr));
    }

    public boolean a(Context context, List<String> list) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        AppOpsManager appOpsManager = null;
        for (String next : list) {
            if (context.checkPermission(next, Process.myPid(), Process.myUid()) == -1) {
                return false;
            }
            String permissionToOp = AppOpsManager.permissionToOp(next);
            if (!TextUtils.isEmpty(permissionToOp)) {
                if (appOpsManager == null) {
                    appOpsManager = (AppOpsManager) context.getSystemService("appops");
                }
                if (appOpsManager.checkOpNoThrow(permissionToOp, Process.myUid(), context.getPackageName()) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
