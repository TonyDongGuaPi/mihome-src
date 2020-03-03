package com.xiaomi.smarthome.core.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.internal.scene.SceneManager;
import com.xiaomi.smarthome.core.server.internal.statistic.StatManager;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.util.Date;

public abstract class PhonecallReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static int f14052a;
    private static Date b;
    private static boolean c;
    private static String d;

    public void onCallStateChanged(Context context, int i, String str) {
    }

    /* access modifiers changed from: protected */
    public abstract void onIncomingCallAnswered(Context context, String str, Date date);

    /* access modifiers changed from: protected */
    public abstract void onIncomingCallEnded(Context context, String str, Date date, Date date2);

    /* access modifiers changed from: protected */
    public abstract void onIncomingCallReceived(Context context, String str, Date date);

    /* access modifiers changed from: protected */
    public abstract void onMissedCall(Context context, String str, Date date);

    /* access modifiers changed from: protected */
    public abstract void onOutgoingCallEnded(Context context, String str, Date date, Date date2);

    /* access modifiers changed from: protected */
    public abstract void onOutgoingCallStarted(Context context, String str, Date date);

    /* access modifiers changed from: protected */
    public abstract void onSMSReceived(Context context);

    public void onReceive(final Context context, final Intent intent) {
        final AnonymousClass1 r0 = new Runnable() {
            public void run() {
                if (TextUtils.equals("com.xiaomi.metoknlp.geofencing.state_change", intent.getAction())) {
                    PhonecallReceiver.this.a(intent, context);
                } else if (intent.getExtras() != null) {
                    String string = intent.getExtras().getString("state");
                    String string2 = intent.getExtras().getString("incoming_number");
                    int i = 0;
                    if (!string.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                        if (string.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                            i = 2;
                        } else if (string.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                            i = 1;
                        }
                    }
                    PhonecallReceiver.this.onCallStateChanged(context, i, string2);
                }
            }
        };
        CoreApi.a().a(context, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                r0.run();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Intent intent, Context context) {
        final String str;
        String stringExtra = intent.getStringExtra("Location");
        String stringExtra2 = intent.getStringExtra("State");
        final String stringExtra3 = intent.getStringExtra("Describe");
        if (HostSetting.g) {
            a("notified:location=" + stringExtra + ",state=" + stringExtra2 + ",desc=" + stringExtra3);
        }
        if (!TextUtils.isEmpty(stringExtra3) && !TextUtils.isEmpty(stringExtra2) && !TextUtils.equals(WifiScanHomelog.p, stringExtra3)) {
            if (TextUtils.equals(WifiScanHomelog.r, stringExtra2) || TextUtils.equals(ReactEditTextInputConnectionWrapper.ENTER_KEY_VALUE, stringExtra2)) {
                str = SceneApi.ConditionELLocation.l;
            } else if (TextUtils.equals(WifiScanHomelog.q, stringExtra2)) {
                str = SceneApi.ConditionELLocation.m;
            } else {
                return;
            }
            StatManager c2 = StatManager.c();
            String value = StatType.EVENT.getValue();
            c2.a(value, "mihome", "geofence_MIUI_notified", stringExtra3 + "," + stringExtra2 + "," + stringExtra, (String) null, false);
            if (SceneManager.a().c()) {
                SceneManager a2 = SceneManager.a();
                a2.a(str + stringExtra3, true);
                return;
            }
            if (HostSetting.g) {
                a("start init scene_manager");
            }
            StatManager.c().a(StatType.EVENT.getValue(), "mihome", "geofence_init_scene_manager", "", (String) null, false);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("scene_manager_init_success_normal_scene");
            intentFilter.addAction("scene_manager_init_fail_normal_scene");
            LocalBroadcastManager.getInstance(CoreService.getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    LocalBroadcastManager.getInstance(CoreService.getAppContext()).unregisterReceiver(this);
                    if (TextUtils.equals(intent.getAction(), "scene_manager_init_success_normal_scene")) {
                        StatManager.c().a(StatType.EVENT.getValue(), "mihome", "geofence_init_scene_manager_suc", "", (String) null, false);
                        SceneManager a2 = SceneManager.a();
                        a2.a(str + stringExtra3, true);
                        return;
                    }
                    StatManager.c().a(StatType.EVENT.getValue(), "mihome", "geofence_init_scene_manager_fail", "", (String) null, false);
                    if (HostSetting.g) {
                        PhonecallReceiver.this.a("geofence execute fail due to scenemanager init fail");
                    }
                }
            }, intentFilter);
            SceneManager.a().b();
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Toast.makeText(CoreService.getAppContext(), str, 1).show();
            }
        });
    }
}
