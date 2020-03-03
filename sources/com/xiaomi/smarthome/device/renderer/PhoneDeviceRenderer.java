package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class PhoneDeviceRenderer extends DeviceRenderer {
    /* access modifiers changed from: protected */
    public boolean a(final Context context, final Device device, Handler handler) {
        if (!super.a(context, device, handler)) {
            return false;
        }
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!defaultSharedPreferences.getBoolean("show_privacy", true)) {
            return true;
        }
        new MLAlertDialog.Builder(context).b((CharSequence) context.getString(R.string.home_log_privacy)).a((int) R.string.smarthome_share_accept, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent a2 = PhoneDeviceRenderer.this.a(device, context, (Bundle) null, false, (DeviceRenderer.LoadingCallback) null);
                if (a2 != null && (context instanceof BaseActivity)) {
                    ((BaseActivity) context).addLifecycleObserver(PhoneDeviceRenderer.this);
                    context.startActivity(a2);
                    DeviceRenderer.c = true;
                    defaultSharedPreferences.edit().putBoolean("show_privacy", false).commit();
                }
            }
        }).b((int) R.string.smarthome_share_reject, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).b().show();
        return false;
    }
}
