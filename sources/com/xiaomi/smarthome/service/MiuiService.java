package com.xiaomi.smarthome.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.push.PushManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.OnStateChangedListener;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.xiaomi.smarthome.service.tasks.LoginTask;
import com.xiaomi.smarthome.service.tasks.ServiceManager;

public class MiuiService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private BroadcastReceiver f22053a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("param_did");
            if (!MIUI10CardActivity.ACTION_OPENCARD.equals(action)) {
                return;
            }
            if (SmartHomeDeviceManager.a().b(stringExtra) == null) {
                PushManager.a().c();
                MiuiService.this.a();
            } else if (!DeviceListSwitchManager.a().f()) {
                MiuiService.this.a();
            }
        }
    };
    private OnStateChangedListener b = new OnStateChangedListener() {
        public void onStateChanged(String str, String str2, Object obj) {
            Log.i("mijia-card", "MiuiService onStateChanged notify 负一屏 did:" + str);
            Device b = SmartHomeDeviceManager.a().b(str);
            if (b != null) {
                CommonApplication.getAppContext().sendBroadcast(new Intent("com.xiaomi.smarthome.refresh_device").putExtra("packagename", "com.miui.home").putExtra(LoginTask.f22078a, CoreApi.a().s()).putExtra(GetDeviceTask.c, GetDeviceTask.a(b, HomeManager.z(b.getName().toString()))));
            }
        }
    };
    BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, final Intent intent) {
            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    String stringExtra = intent.getStringExtra("did");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        for (GridViewData next : HomeManager.a().F()) {
                            if (next.f18311a == GridViewData.GridType.TYPE_NORMAL && next.b.did.equals(stringExtra)) {
                                Intent intent = new Intent();
                                intent.setAction("com.xiaomi.smarthome.refresh_device");
                                intent.putExtra(LoginTask.f22078a, CoreApi.a().s());
                                intent.putExtra(GetDeviceTask.c, GetDeviceTask.a(next));
                                SHApplication.getAppContext().sendBroadcast(intent);
                                return;
                            }
                        }
                    }
                }
            });
        }
    };
    ServiceManager serviceManager = new ServiceManager();

    /* access modifiers changed from: private */
    public void a() {
        CommonApplication.getGlobalWorkerHandler().post(new GetDeviceTask((ICallback) null, true));
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return this.serviceManager;
    }

    public void onCreate() {
        super.onCreate();
        CardActive.instance.create();
        PushManager.a().c();
        registerReceiver(this.receiver, new IntentFilter(XmBluetoothManager.ACTION_RENAME_NOTIFY));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MIUI10CardActivity.ACTION_OPENCARD);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f22053a, intentFilter);
        MiotSpecCardManager.f().a(this.b);
        ControlCardInfoManager.f().a(this.b);
    }

    public void onDestroy() {
        super.onDestroy();
        CardActive.instance.destory();
        unregisterReceiver(this.receiver);
    }
}
