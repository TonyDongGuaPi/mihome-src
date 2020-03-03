package cn.fraudmetrix.octopus.aspirit.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.h;
import com.coloros.mcssdk.PushManager;

public abstract class AbstractOctopusBaseService extends IntentService {

    /* renamed from: a  reason: collision with root package name */
    protected int f649a = 1;
    private String b = OctopusConstants.j;
    private String c = "cn.fraudmetrix.octopus.aspirit.service";

    public AbstractOctopusBaseService(String str) {
        super(str);
        h.a(str);
    }

    @RequiresApi(api = 26)
    private Notification a() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.b, this.c, 4);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-65536);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(1);
            ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).createNotificationChannel(notificationChannel);
        }
        return new Notification.Builder(getApplicationContext()).setChannelId(this.b).setContentTitle("  ").setContentText("   ").build();
    }

    public void onCreate() {
        super.onCreate();
        h.a("onCreate " + toString());
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(this.f649a, a());
            return;
        }
        startForeground(this.f649a, new Notification());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(@Nullable Intent intent) {
        h.a("onHandleIntent " + toString());
    }
}
