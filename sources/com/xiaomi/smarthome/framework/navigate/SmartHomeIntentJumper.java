package com.xiaomi.smarthome.framework.navigate;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.runtime.service.PluginServiceConst;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.stat.STAT;

public class SmartHomeIntentJumper extends SmartHomeJumper {
    public SmartHomeIntentJumper(Activity activity) {
        super(activity);
    }

    public void a(Intent intent) {
        Object obj;
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            b((Intent) null);
            this.f16637a.finish();
        } else if (ApiConst.f16684a.equals(action)) {
            XQProgressDialog.a(this.f16637a, "", this.f16637a.getString(R.string.loading), true, true, new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    Activity activity = SmartHomeIntentJumper.this.f16637a;
                    if (activity != null) {
                        activity.finish();
                    }
                }
            }).show();
            try {
                StatHelper.b(intent);
            } catch (Exception unused) {
            }
            final boolean booleanExtra = intent.getBooleanExtra(ApiConst.n, false);
            String str = "";
            Bundle extras = intent.getExtras();
            if (!(extras == null || (obj = extras.get("user_id")) == null)) {
                str = obj.toString();
                if ("0".equals(str)) {
                    str = "";
                }
            }
            final String str2 = str;
            String stringExtra = intent.getStringExtra("device_mac");
            if (!TextUtils.isEmpty(stringExtra)) {
                stringExtra = stringExtra.toLowerCase();
            }
            final String str3 = stringExtra;
            final String stringExtra2 = intent.getStringExtra("device_id");
            STAT.f22748a.a(str2, stringExtra2);
            if (booleanExtra) {
                final Intent intent2 = intent;
                CoreApi.a().a((Context) this.f16637a, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        SmartHomeIntentJumper.this.a(true, booleanExtra, str2, stringExtra2, str3, intent2);
                    }
                });
            }
        } else if (ApiConst.b.equals(action)) {
            Intent intent3 = new Intent(this.f16637a, SmartHomeMainActivity.class);
            intent3.putExtras(intent);
            intent3.putExtra("source", 6);
            intent3.setFlags(268468224);
            intent3.setAction(ApiConst.f16684a);
            this.f16637a.startActivity(intent3);
            this.f16637a.finish();
        } else if (action.equalsIgnoreCase(PluginServiceConst.ACTION_STARTFOREGROUND_NOTIFICATION_PENDING_INTENT)) {
            String stringExtra3 = intent.getStringExtra("model");
            Intent intent4 = (Intent) intent.getParcelableExtra("params");
            String stringExtra4 = intent.getStringExtra("did");
            STAT.f22748a.c(stringExtra3, stringExtra4);
            if (TextUtils.isEmpty(stringExtra3)) {
                Log.d("", "model is null");
                return;
            }
            PluginRecord d = CoreApi.a().d(stringExtra3);
            if (d == null) {
                Log.d("", "PluginRecord is null");
                return;
            }
            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, 17, intent4, SmartHomeDeviceManager.a().o(stringExtra4), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
            this.f16637a.finish();
        } else {
            this.f16637a.finish();
        }
    }
}
