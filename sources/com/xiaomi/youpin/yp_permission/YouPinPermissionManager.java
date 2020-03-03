package com.xiaomi.youpin.yp_permission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.AndPermission;
import com.yanzhenjie.yp_permission.Rationale;
import java.util.ArrayList;
import java.util.List;

public class YouPinPermissionManager {
    public static void a(Activity activity, String str, SimplePermissionCallback simplePermissionCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        a(activity, (List<String>) arrayList, simplePermissionCallback);
    }

    public static void a(Activity activity, List<String> list, final SimplePermissionCallback simplePermissionCallback) {
        AndPermission.a((Context) activity).a().a((String[]) list.toArray(new String[list.size()])).a((Action<List<String>>) new Action<List<String>>() {
            public void a(List<String> list) {
                if (simplePermissionCallback != null) {
                    simplePermissionCallback.a();
                }
            }
        }).b(new Action<List<String>>() {
            public void a(List<String> list) {
                if (simplePermissionCallback != null) {
                    simplePermissionCallback.b();
                }
            }
        }).ac_();
    }

    public static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean a(Context context, List<String> list) {
        for (String checkSelfPermission : list) {
            if (ContextCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void a(Activity activity, String str, PermissionCallback permissionCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        a(activity, (List<String>) arrayList, permissionCallback);
    }

    public static void a(final Activity activity, List<String> list, final PermissionCallback permissionCallback) {
        YouPinRationale youPinRationale = new YouPinRationale(permissionCallback);
        final YouPinPermissionSetting youPinPermissionSetting = new YouPinPermissionSetting(activity, permissionCallback);
        AndPermission.a((Context) activity).a().a((String[]) list.toArray(new String[list.size()])).a((Rationale<List<String>>) youPinRationale).a((Action<List<String>>) new Action<List<String>>() {
            public void a(List<String> list) {
                if (permissionCallback != null) {
                    permissionCallback.a();
                }
            }
        }).b(new Action<List<String>>() {
            public void a(List<String> list) {
                if (AndPermission.a((Context) activity, list)) {
                    youPinPermissionSetting.a(list);
                    if (permissionCallback != null) {
                        permissionCallback.a(true);
                    }
                } else if (permissionCallback != null) {
                    permissionCallback.a(false);
                }
            }
        }).ac_();
    }
}
