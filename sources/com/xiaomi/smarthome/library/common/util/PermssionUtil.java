package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.widget.SingleCheckLinearLayout;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PermssionUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18693a = "PermssionUtil";
    private static final String b = "8.3.0";
    private static final String c = "7.3.13";

    public static boolean a(Context context) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            int i = IMediaPlayer.MEDIA_INFO_VIDEO_SEEK_RENDERING_START;
            if (Build.VERSION.SDK_INT < 23) {
                i = 50;
            }
            if (((Integer) appOpsManager.getClass().getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(i), Integer.valueOf(Process.myUid()), context.getPackageName()})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e(f18693a, "not support", th);
            return false;
        }
    }

    public static void a(Context context, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("pkg", context.getPackageName());
        bundle.putBoolean("status", z);
        try {
            context.getContentResolver().call(Uri.parse("content://com.miui.security.permission"), "setAutoStart", (String) null, bundle);
        } catch (Exception e) {
            Log.e(f18693a, "not support", e);
        }
    }

    public static MLAlertDialog a(final Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        if ((Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) || Build.VERSION.SDK_INT <= 19) {
            return null;
        }
        final SharedPreferences sharedPreferences = activity.getSharedPreferences("auto_start_permission_remind", 0);
        if (sharedPreferences.getBoolean("checked", false) || a((Context) activity) || !CommonUtils.m(activity)) {
            return null;
        }
        View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_auto_start_permission_view, (ViewGroup) null);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.select_icon);
        ((SingleCheckLinearLayout) inflate.findViewById(R.id.single_click_layout)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        inflate.findViewById(R.id.text_leave_back_home).setVisibility(8);
        MLAlertDialog b2 = new MLAlertDialog.Builder(activity).b(inflate).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                sharedPreferences.edit().putBoolean("checked", checkBox.isChecked()).apply();
            }
        }).a((int) R.string.dialog_btn_allow, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                try {
                    activity.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sharedPreferences.edit().putBoolean("checked", checkBox.isChecked()).apply();
            }
        }).b();
        b2.show();
        return b2;
    }
}
