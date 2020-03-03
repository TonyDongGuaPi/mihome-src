package com.xiaomi.smarthome.acp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.io.File;

public class ACPUtil {
    public static boolean a(Context context, String str) {
        boolean isReachMaxCrashTimes = ACPService.isReachMaxCrashTimes(context);
        Intent intent = new Intent(context, ACPService.class);
        intent.setAction(ACPService.ACTION_ACP);
        intent.putExtra(ACPService.INTENT_KEY_REPEATED_CRASH, 1);
        intent.putExtra(ACPService.INTENT_KEY_DATA, str);
        try {
            context.startService(intent);
            return isReachMaxCrashTimes;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, ACPService.class);
        intent.setAction(ACPService.ACTION_ACP);
        intent.putExtra(ACPService.INTENT_KEY_REPEATED_CRASH, 3);
        try {
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a() {
        File file = new File(ACPService.FILE_DIR + ACPService.CRASH_TIME_FILE_NAME_BACKUP);
        return file.exists() && file.length() > 0;
    }

    public static void b() {
        try {
            new File(ACPService.FILE_DIR + ACPService.CRASH_TIME_FILE_NAME_BACKUP).delete();
        } catch (Exception unused) {
        }
    }

    public static void b(Context context) {
        Intent intent = new Intent(context, ACPService.class);
        intent.setAction(ACPService.ACTION_ACP);
        intent.putExtra(ACPService.INTENT_KEY_REPEATED_CRASH, 4);
        try {
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void c(Context context) {
        Intent intent = new Intent(context, ACPService.class);
        intent.setAction(ACPService.ACTION_ACP);
        intent.putExtra(ACPService.INTENT_KEY_REPEATED_CRASH, 5);
        intent.putExtra(ACPService.INTENT_KEY_DATA, System.currentTimeMillis());
        try {
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(final Context context) {
        if (a()) {
            new MLAlertDialog.Builder(context).a(R.string.crash_dialog_title).b(R.string.crash_dialog_msg).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ACPUtil.b();
                }
            }).a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                public void afterDismissCallBack() {
                }

                public void beforeDismissCallBack() {
                }
            }).b(R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ACPUtil.b(context);
                }
            }).a(R.string.crash_dialog_btn_report, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ACPUtil.b(context);
                }
            }).b().show();
        }
    }
}
