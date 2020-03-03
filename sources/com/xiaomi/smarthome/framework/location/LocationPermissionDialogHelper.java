package com.xiaomi.smarthome.framework.location;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep;
import com.xiaomi.youpin.UserMode;
import com.yanzhenjie.permission.AndPermission;

public class LocationPermissionDialogHelper {
    public static boolean a(final Activity activity) {
        if (!SHLocationManager.e()) {
            new MLAlertDialog.Builder(activity).b((CharSequence) activity.getResources().getString(R.string.open_location_permission1)).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.set_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    activity.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            }).b().show();
            return false;
        } else if (SHLocationManager.f()) {
            return true;
        } else {
            new MLAlertDialog.Builder(activity).b((CharSequence) activity.getResources().getString(R.string.open_location_permission1)).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.set_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent();
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", activity.getPackageName(), (String) null));
                    activity.startActivityForResult(intent, ChooseWifiStep.f22500a);
                }
            }).b().show();
            return false;
        }
    }

    public static boolean a(final Activity activity, boolean z, int i) {
        if (!SHLocationManager.e()) {
            if (z) {
                new MLAlertDialog.Builder(activity).b((CharSequence) activity.getResources().getString(i)).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.set_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                    }
                }).b().show();
            }
            return false;
        } else if (SHLocationManager.f()) {
            return true;
        } else {
            if (z) {
                View inflate = LayoutInflater.from(activity).inflate(R.layout.permisson_request_dialog_view, (ViewGroup) null);
                ((TextView) inflate.findViewById(R.id.subtitle1)).setText(R.string.permission_location_rational_desc_new);
                new MLAlertDialog.Builder(activity).b(inflate).a(false).a((int) R.string.next, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (SystemApi.c() || !Build.MANUFACTURER.toLowerCase().contains(UserMode.f23179a)) {
                            AndPermission.a(activity).a(ChooseWifiStep.f22500a, true);
                            return;
                        }
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                        intent.setData(Uri.fromParts("package", activity.getPackageName(), (String) null));
                        activity.startActivityForResult(intent, ChooseWifiStep.f22500a);
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
            }
            return false;
        }
    }
}
