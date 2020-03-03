package com.xiaomi.smarthome.scene.geofence.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.scene.GeoActionService;
import java.util.ArrayList;
import java.util.List;

public class MIUIGeoFenceBroadcastReceiver extends BroadcastReceiver {
    public static final String GEOFENCE_ACTION = "com.xiaomi.smarthome.miui.geofence";

    /* renamed from: a  reason: collision with root package name */
    private static final String f21540a = "MIUIGeoFenceBroadcastReceiver";
    static int count = 453544;
    private List<AsyncCallback> b = new ArrayList();

    public MIUIGeoFenceBroadcastReceiver() {
    }

    public MIUIGeoFenceBroadcastReceiver(AsyncCallback asyncCallback) {
        if (asyncCallback != null) {
            this.b.add(asyncCallback);
        }
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        if (StartupCheckList.b()) {
            if (intent == null) {
                str = null;
            } else {
                str = intent.getAction();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("receive action: ");
            sb.append(intent == null ? "action==null" : intent.getAction());
            GrayLogDelegate.a(f21540a, sb.toString());
            if (!TextUtils.isEmpty(str) && TextUtils.equals(str, GEOFENCE_ACTION)) {
                a(context, intent);
            }
        }
    }

    private void a(Context context, Intent intent) {
        if (!this.b.isEmpty()) {
            for (AsyncCallback next : this.b) {
                if (next != null) {
                    next.onSuccess(intent);
                }
            }
        }
        String stringExtra = intent.getStringExtra("context-data");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                int intExtra = intent.getIntExtra("transition-event", -1);
                Location location = (Location) intent.getParcelableExtra("transition-location");
                String str = null;
                if (intExtra == 1) {
                    str = "GEOFENCE_EVENT_ENTERED";
                } else if (intExtra == 2) {
                    str = "GEOFENCE_EVENT_EXITED";
                } else if (intExtra == 4) {
                    str = "GEOFENCE_EVENT_UNCERTAIN";
                }
                if (str == null) {
                    str = "unknown:" + intExtra;
                }
                GrayLogDelegate.a(f21540a, "processGeoFenceIntent id=" + stringExtra + ",transEvent=" + str + ",location=" + location);
                if (MIUIGeoFenceHelper.a(stringExtra)) {
                    MIUIGeoFenceManager.b().a(stringExtra);
                    if (MIUIGeoFenceManager.b().d(stringExtra)) {
                        GrayLogDelegate.a(f21540a, "is first trigger");
                    }
                    Intent intent2 = new Intent(context, GeoActionService.class);
                    intent2.setAction(GeoActionService.ACTION_ON_TRIGER);
                    intent2.putExtra(GeoActionService.EXTRA_TRANS_EVENT, intExtra);
                    intent2.putExtra(GeoActionService.EXTRA_GEOFENCE_ID, stringExtra);
                    intent2.putExtra("location", location);
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.startForegroundService(intent2);
                        GrayLogDelegate.a(f21540a, "startForegroundService called");
                        return;
                    }
                    context.startService(intent2);
                    GrayLogDelegate.a(f21540a, "startService called");
                }
            } catch (Exception e) {
                e.printStackTrace();
                GrayLogDelegate.a(f21540a, "exception:" + e.getMessage());
            }
        }
    }
}
