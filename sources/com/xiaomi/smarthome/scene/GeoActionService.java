package com.xiaomi.smarthome.scene;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import com.coloros.mcssdk.PushManager;
import com.tencent.smtt.utils.TbsLog;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.scene.geofence.manager.GrayLogDelegate;
import com.xiaomi.smarthome.scene.geofence.manager.MIUIGeoFenceManager;

public class GeoActionService extends Service {
    public static final String ACTION_ON_TRIGER = "geofence_trigered";
    public static final String EXTRA_GEOFENCE_ID = "geofence_id";
    public static final String EXTRA_LOCATION = "location";
    public static final String EXTRA_TRANS_EVENT = "trans_event";

    /* renamed from: a  reason: collision with root package name */
    private static final String f21211a = "GeoActionService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        GrayLogDelegate.a(f21211a, "onCreate");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        GrayLogDelegate.a(f21211a, "onStartCommand");
        a(intent);
        return super.onStartCommand(intent, i, i2);
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        GrayLogDelegate.a(f21211a, "onTaskRemoved");
    }

    private void a(Intent intent) {
        GrayLogDelegate.a(f21211a, "handleIntent");
        if (intent != null && TextUtils.equals(ACTION_ON_TRIGER, intent.getAction())) {
            int intExtra = intent.getIntExtra(EXTRA_TRANS_EVENT, -90);
            String stringExtra = intent.getStringExtra(EXTRA_GEOFENCE_ID);
            MIUIGeoFenceManager.b().c();
            if (MIUIGeoFenceManager.b().a(stringExtra) == null) {
                GrayLogDelegate.a(f21211a, "there is no GeoFenceItem for " + stringExtra);
                return;
            }
            Location location = (Location) intent.getParcelableExtra("location");
            if (intExtra == -90 || TextUtils.isEmpty(stringExtra) || location == null) {
                stopForeground(true);
                StringBuilder sb = new StringBuilder();
                sb.append("extra has null:");
                sb.append(intExtra == -90 ? "transevent is null  " : "");
                sb.append(TextUtils.isEmpty(stringExtra) ? "id is null  " : "");
                sb.append(location == null ? "location is null  " : "");
                GrayLogDelegate.a(f21211a, sb.toString());
                return;
            }
            SceneManager.x().a(intExtra, stringExtra, location, (AsyncCallback) new AsyncCallback() {
                public void onSuccess(Object obj) {
                    GeoActionService.this.stopForeground(true);
                }

                public void onFailure(Error error) {
                    GeoActionService.this.stopForeground(true);
                }
            });
            a(getString(R.string.scene_manage), getString(R.string.device_more_security_loading_operation));
        }
    }

    private void a(String str, String str2) {
        Notification.Builder builder;
        NotificationManager notificationManager = (NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("app", "app", 2);
            if (Build.VERSION.SDK_INT >= 28) {
                if (notificationManager.getNotificationChannelGroup("other") == null) {
                    notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("other", "other"));
                }
                if (TextUtils.isEmpty(notificationChannel.getGroup())) {
                    notificationChannel.setGroup("other");
                }
            }
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new Notification.Builder(this, "app");
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setSmallIcon(R.drawable.notify_icon).setContentTitle(str).setContentText(str2);
        Notification build = builder.build();
        build.flags |= 16;
        if (Build.VERSION.SDK_INT < 24) {
            notificationManager.notify(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, build);
        } else {
            startForeground(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, build);
        }
    }
}
