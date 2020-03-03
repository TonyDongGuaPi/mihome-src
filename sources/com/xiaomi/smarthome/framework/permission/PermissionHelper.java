package com.xiaomi.smarthome.framework.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.SettingService;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermissionHelper {
    public static boolean a(final Activity activity, boolean z, final Action action, final String str) {
        if (TextUtils.isEmpty(str)) {
            str = activity.getApplication().getResources().getString(R.string.open_location_permission1);
        }
        if (!b()) {
            if (z) {
                new MLAlertDialog.Builder(activity).b((CharSequence) str).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.set_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                    }
                }).b().show();
            }
            return false;
        } else if (!a()) {
            if (z) {
                View inflate = LayoutInflater.from(activity).inflate(R.layout.permisson_request_dialog_view, (ViewGroup) null);
                ((TextView) inflate.findViewById(R.id.subtitle1)).setText(R.string.permission_location_rational_desc_new);
                new MLAlertDialog.Builder(activity).b(inflate).d(true).a((int) R.string.next, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        PermissionHelper.a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), str, action, Permission.Group.d);
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).d();
            }
            return false;
        } else {
            if (action != null) {
                action.onAction(Arrays.asList(Permission.Group.d));
            }
            return true;
        }
    }

    public static boolean a(final Activity activity, boolean z, final Action action) {
        if (a(Permission.Group.b)) {
            if (action != null) {
                action.onAction(Arrays.asList(Permission.Group.b));
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            View inflate = LayoutInflater.from(activity).inflate(R.layout.permisson_request_dialog_view, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.subtitle1)).setText(R.string.permission_camera_desc_new);
            new MLAlertDialog.Builder(activity).b(inflate).d(true).a((int) R.string.next, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PermissionHelper.a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_camera_msg), action, Permission.Group.b);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).d();
            return false;
        }
    }

    public static boolean b(final Activity activity, boolean z, final Action action) {
        if (a(Permission.Group.e)) {
            if (action != null) {
                action.onAction(Arrays.asList(Permission.Group.e));
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            View inflate = LayoutInflater.from(activity).inflate(R.layout.permisson_request_dialog_view, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.subtitle1)).setText(R.string.permission_microphone_desc_new);
            new MLAlertDialog.Builder(activity).b(inflate).d(true).a((int) R.string.next, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PermissionHelper.a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, Permission.Group.e);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).d();
            return false;
        }
    }

    public static boolean c(Activity activity, boolean z, Action action) {
        if (!a(Permission.Group.f2404a)) {
            if (!z) {
                return false;
            }
            a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, Permission.Group.f2404a);
            return false;
        } else if (action == null) {
            return true;
        } else {
            action.onAction(Arrays.asList(Permission.Group.f2404a));
            return true;
        }
    }

    public static boolean d(Activity activity, boolean z, Action action) {
        if (!a(Permission.Group.c)) {
            if (!z) {
                return false;
            }
            a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, Permission.Group.c);
            return false;
        } else if (action == null) {
            return true;
        } else {
            action.onAction(Arrays.asList(Permission.Group.c));
            return true;
        }
    }

    public static boolean e(Activity activity, boolean z, Action action) {
        return a(activity, z, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), "android.permission.CALL_PHONE", action);
    }

    public static boolean f(Activity activity, boolean z, Action action) {
        return a(activity, z, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), "android.permission.READ_PHONE_STATE", action);
    }

    public static boolean g(Activity activity, boolean z, Action action) {
        if (!a(Permission.Group.i)) {
            if (!z) {
                return false;
            }
            a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, Permission.Group.i);
            return false;
        } else if (action == null) {
            return true;
        } else {
            action.onAction(Arrays.asList(Permission.Group.i));
            return true;
        }
    }

    public static boolean h(Activity activity, boolean z, Action action) {
        return a(activity, z, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), "android.permission.SEND_SMS", action);
    }

    public static boolean i(Activity activity, boolean z, Action action) {
        return a(activity, z, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), "android.permission.RECEIVE_SMS", action);
    }

    public static boolean j(Activity activity, boolean z, Action action) {
        return a(activity, z, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), "android.permission.READ_SMS", action);
    }

    public static boolean k(Activity activity, boolean z, Action action) {
        if (!a(Permission.Group.g)) {
            if (!z) {
                return false;
            }
            a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, Permission.Group.g);
            return false;
        } else if (action == null) {
            return true;
        } else {
            action.onAction(Arrays.asList(Permission.Group.g));
            return true;
        }
    }

    public static boolean l(Activity activity, boolean z, Action action) {
        String[] strArr;
        if (!HomeManager.A()) {
            strArr = Permission.Group.j;
        } else if (ShopGlobalHelper.a(activity.getApplicationContext())) {
            strArr = Permission.Group.k;
        } else {
            strArr = Permission.Group.i;
        }
        String[] strArr2 = strArr;
        if (!a(strArr2)) {
            if (!z) {
                return false;
            }
            a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, strArr2);
            return false;
        } else if (action == null) {
            return true;
        } else {
            action.onAction(Arrays.asList(strArr2));
            return true;
        }
    }

    private static boolean a(Activity activity, boolean z, Rationale rationale, String str, String str2, boolean z2, String str3, String str4, String str5, Action action) {
        String str6 = str5;
        Activity activity2 = activity;
        Action action2 = action;
        if (!CommonUtils.e((Context) activity, str6)) {
            if (z) {
                a(activity, rationale, str, str2, z2, str3, str4, action, str6);
            }
            return false;
        }
        if (action2 != null) {
            action2.onAction(Collections.singletonList(str5));
        }
        return true;
    }

    public static void a(Activity activity, Rationale rationale, String str, String str2, boolean z, String str3, String str4, Action action, String... strArr) {
        final WeakReference weakReference = new WeakReference(activity);
        final boolean z2 = z;
        final String str5 = str2;
        final String str6 = str3;
        final String str7 = str4;
        AndPermission.b((Activity) weakReference.get()).a(strArr).a(rationale).a(action).b(new Action() {
            public void onAction(@NonNull List<String> list) {
                if (!z2) {
                    ToastUtil.a((CharSequence) str5);
                    return;
                }
                Activity activity = (Activity) weakReference.get();
                if (activity != null && !activity.isFinishing()) {
                    if (Build.VERSION.SDK_INT < 17 || activity.isDestroyed()) {
                        if (AndPermission.a(activity, list)) {
                            PermissionHelper.b(activity, str6, str7, list);
                        } else {
                            ToastUtil.a((CharSequence) str5);
                        }
                    } else if (AndPermission.a(activity, list)) {
                        PermissionHelper.b(activity, str6, str7, list);
                    } else {
                        ToastUtil.a((CharSequence) str5);
                    }
                }
            }
        }).a();
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, String str, String str2, List<String> list) {
        if (activity != null && !activity.isFinishing()) {
            if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
                String format = String.format(str2, new Object[]{TextUtils.join("\n", Permission.a((Context) activity, list))});
                final SettingService a2 = AndPermission.a(activity);
                new MLAlertDialog.Builder(activity).a(false).a((CharSequence) str).b((CharSequence) format).a((int) R.string.setting, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (CommonUtils.o()) {
                            a2.b();
                        } else {
                            a2.a();
                        }
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        a2.c();
                    }
                }).b().show();
            }
        }
    }

    public static boolean a() {
        if (!CommonUtils.e(SHApplication.getAppContext(), "android.permission.ACCESS_FINE_LOCATION") && !CommonUtils.e(SHApplication.getAppContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
            return false;
        }
        return true;
    }

    public static boolean a(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String e : strArr) {
            if (!CommonUtils.e(SHApplication.getAppContext(), e)) {
                return false;
            }
        }
        return true;
    }

    public static boolean b() {
        try {
            if (Build.VERSION.SDK_INT < 19) {
                return !TextUtils.isEmpty(Settings.Secure.getString(SHApplication.getAppContext().getContentResolver(), "location_providers_allowed"));
            }
            try {
                if (Settings.Secure.getInt(SHApplication.getAppContext().getContentResolver(), "location_mode") != 0) {
                    return true;
                }
                return false;
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean m(Activity activity, boolean z, Action action) {
        if (!a("android.permission.GET_ACCOUNTS")) {
            if (z) {
                a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_denied_msg), action, "android.permission.GET_ACCOUNTS");
            }
            return false;
        }
        if (action != null) {
            action.onAction(Arrays.asList(new String[]{"android.permission.GET_ACCOUNTS"}));
        }
        return true;
    }
}
