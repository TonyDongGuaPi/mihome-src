package com.miui.tsmclient.util;

import android.content.Context;

public class TsmGroupPermissions {
    private static final String PERM_ERROR = "TSM_GROUP permission required";
    private static final String TSM_GROUP_PERM = "com.miui.tsmclient.permission.TSM_GROUP";

    public static void enforcePermissions(Context context) {
        context.enforceCallingOrSelfPermission("com.miui.tsmclient.permission.TSM_GROUP", PERM_ERROR);
    }
}
