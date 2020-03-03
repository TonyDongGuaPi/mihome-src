package com.xiaomi.miot.support.monitor.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.utils.AsyncThreadTask;

public class MonitorConfigBroadCastReceiver extends BroadcastReceiver {
    public static final String EXTRA_TYPE = "type";
    public static final String FILTER_NAME = "com.xiaomi.miot.support.monitor";
    public static final String TYPE_ENTER = "enter_app";
    public static final String TYPE_EXIT = "exit_app";
    public static final String TYPE_REPORT_NET = "report_net";
    public static final String TYPE_START_MONITOR = "start_monitor";
    public static final String TYPE_STOP_MONITOR = "stop_monitor";
    public static final String TYPE_UPDATE_CONFIG = "update_config";

    /* renamed from: a  reason: collision with root package name */
    private static final String f11451a = "MonitorConfigBroadCast";

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String stringExtra = intent.getStringExtra("type");
            if (!TextUtils.isEmpty(stringExtra)) {
                AsyncThreadTask.a((Runnable) new Runnable() {
                    /* JADX WARNING: Can't fix incorrect switch cases order */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r3 = this;
                            java.lang.String r0 = r1
                            int r1 = r0.hashCode()
                            r2 = 1
                            switch(r1) {
                                case -2122407040: goto L_0x003d;
                                case -1938339299: goto L_0x0033;
                                case -1806924936: goto L_0x0029;
                                case -857701318: goto L_0x001f;
                                case -637950339: goto L_0x0015;
                                case 1931703314: goto L_0x000b;
                                default: goto L_0x000a;
                            }
                        L_0x000a:
                            goto L_0x0047
                        L_0x000b:
                            java.lang.String r1 = "report_net"
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L_0x0047
                            r0 = 5
                            goto L_0x0048
                        L_0x0015:
                            java.lang.String r1 = "start_monitor"
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L_0x0047
                            r0 = 0
                            goto L_0x0048
                        L_0x001f:
                            java.lang.String r1 = "enter_app"
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L_0x0047
                            r0 = 2
                            goto L_0x0048
                        L_0x0029:
                            java.lang.String r1 = "update_config"
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L_0x0047
                            r0 = 1
                            goto L_0x0048
                        L_0x0033:
                            java.lang.String r1 = "stop_monitor"
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L_0x0047
                            r0 = 4
                            goto L_0x0048
                        L_0x003d:
                            java.lang.String r1 = "exit_app"
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L_0x0047
                            r0 = 3
                            goto L_0x0048
                        L_0x0047:
                            r0 = -1
                        L_0x0048:
                            switch(r0) {
                                case 0: goto L_0x0080;
                                case 1: goto L_0x0078;
                                case 2: goto L_0x006d;
                                case 3: goto L_0x0058;
                                case 4: goto L_0x0054;
                                case 5: goto L_0x004c;
                                default: goto L_0x004b;
                            }
                        L_0x004b:
                            goto L_0x0087
                        L_0x004c:
                            com.xiaomi.miot.support.monitor.core.MiotDataStorage r0 = com.xiaomi.miot.support.monitor.core.MiotDataStorage.a()
                            r0.b()
                            goto L_0x0087
                        L_0x0054:
                            com.xiaomi.miot.support.monitor.MiotMonitorClient.f()
                            goto L_0x0087
                        L_0x0058:
                            com.xiaomi.miot.support.monitor.core.MiotDataStorage r0 = com.xiaomi.miot.support.monitor.core.MiotDataStorage.a()
                            r0.e()
                            com.xiaomi.miot.support.monitor.MiotMonitorClient.a()
                            com.xiaomi.miot.support.monitor.MiotMonitorManager r0 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()
                            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r0 = r0.c()
                            r0.j = r2
                            goto L_0x0087
                        L_0x006d:
                            com.xiaomi.miot.support.monitor.core.MiotDataStorage r0 = com.xiaomi.miot.support.monitor.core.MiotDataStorage.a()
                            r0.d()
                            com.xiaomi.miot.support.monitor.MiotMonitorClient.b()
                            goto L_0x0087
                        L_0x0078:
                            com.xiaomi.miot.support.monitor.MiotMonitorManager r0 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()
                            r0.b()
                            goto L_0x0087
                        L_0x0080:
                            com.xiaomi.miot.support.monitor.MiotMonitorManager r0 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()
                            r0.b()
                        L_0x0087:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.support.monitor.broadcastreceiver.MonitorConfigBroadCastReceiver.AnonymousClass1.run():void");
                    }
                });
            }
        }
    }
}
