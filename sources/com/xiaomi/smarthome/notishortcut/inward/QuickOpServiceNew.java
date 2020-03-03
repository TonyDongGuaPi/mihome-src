package com.xiaomi.smarthome.notishortcut.inward;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.smarthome.notishortcut.NotiSmartService;
import com.xiaomi.smarthome.notishortcut.R;

public class QuickOpServiceNew extends Service {
    public static final String ACTION_AUTH_EXPIRED = "auth_expired";
    public static final String ACTION_NOTIFICATION_CANCEL = "notification_cancel";
    public static final String ACTION_NOTIFICATION_CLICK = "notification_click";
    public static final String ACTION_NOTIFICATION_UPDATE = "quick_noti_setting_update";
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_INDEX = "device_index";

    /* renamed from: a  reason: collision with root package name */
    private static final String f1566a = "QuickOpServiceNew";

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a(f1566a, "Quickop onStartCommand");
        a((String) null);
        a(intent);
        return super.onStartCommand(intent, i, i2);
    }

    private void a(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (TextUtils.equals(ACTION_NOTIFICATION_CANCEL, action)) {
                NotificationController.a((Service) this);
            } else if (TextUtils.equals(ACTION_NOTIFICATION_UPDATE, action)) {
                a(intent.getStringExtra("device_id"));
            } else {
                onHandleIntent(intent);
            }
        }
    }

    public void onDestroy() {
        stopForeground(true);
        NotiSettingManager.a((Context) this).d();
        NotiSettingManager.a((Context) this).c();
        LogUtil.b(f1566a, "service onDestroy");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            LogUtil.b(f1566a, action);
            if (TextUtils.equals(ACTION_NOTIFICATION_CLICK, action)) {
                a(intent.getIntExtra(INTENT_KEY_INDEX, -1));
            } else if (TextUtils.equals(ACTION_AUTH_EXPIRED, action)) {
                NotificationController.a(this, NotificationController.f1565a, getString(R.string.noti_token_expired_title), getString(R.string.noti_token_expired), new Intent("com.xiaomi.smarthome.notification.auth_expired"));
            }
        }
    }

    private void a(int i) {
        if (i < NotiSettingManager.a((Context) this).b.size() && !NotiSettingManager.a((Context) this).b.get(i).f) {
            NotiSettingManager.a((Context) this).b.get(i).f = true;
            NotiSettingManager.a((Context) this).b.get(i).g = 1;
            NotificationController.a(this, NotiSettingManager.a((Context) this).b);
            NotiSettingManager.a((Context) this).a(i);
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str) || !NotiSettingManager.a((Context) this).f.containsKey(str)) {
            NotificationController.a(this, NotiSettingManager.a((Context) this).b);
            NotiSettingManager.a((Context) this).e();
            return;
        }
        LogUtil.a(f1566a, "等到了全量列表");
        int intValue = NotiSettingManager.a((Context) this).f.get(str).intValue();
        if (intValue < 0 || intValue >= NotiSettingManager.a((Context) this).b.size()) {
            NotiSettingManager.a((Context) this).d();
        } else {
            NotiSettingManager.a((Context) this).b.get(intValue).f = false;
            NotiSettingManager.a((Context) this).f.remove(str);
        }
        if (NotiSmartService.getHandler() != null) {
            NotiSmartService.getHandler().postDelayed(new Runnable() {
                public void run() {
                    NotificationController.a(QuickOpServiceNew.this, NotiSettingManager.a((Context) QuickOpServiceNew.this).b);
                    NotiSettingManager.a((Context) QuickOpServiceNew.this).e();
                }
            }, 1000);
        } else {
            NotificationController.a(this, NotiSettingManager.a((Context) this).b);
        }
    }
}
