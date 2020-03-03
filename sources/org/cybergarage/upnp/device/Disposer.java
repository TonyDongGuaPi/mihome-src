package org.cybergarage.upnp.device;

import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;
import org.cybergarage.util.ThreadCore;

public class Disposer extends ThreadCore {
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private ControlPoint ctrlPoint;

    public Disposer(ControlPoint controlPoint) {
        setControlPoint(controlPoint);
    }

    public void setControlPoint(ControlPoint controlPoint) {
        this.ctrlPoint = controlPoint;
    }

    public ControlPoint getControlPoint() {
        return this.ctrlPoint;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            org.cybergarage.upnp.ControlPoint r0 = r7.getControlPoint()
            long r1 = r0.getExpiredDeviceMonitoringInterval()
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r3
        L_0x000c:
            boolean r3 = r7.isRunnable()
            r4 = 1
            if (r3 != r4) goto L_0x003c
            java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x0016 }
        L_0x0016:
            java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0020 }
            r0.removeExpiredDevices()     // Catch:{ Exception -> 0x0020 }
            java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0020 }
            goto L_0x000c
        L_0x0020:
            r3 = move-exception
            org.cybergarage.util.CommonLog r4 = log
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "catch exception!!!e = "
            r5.append(r6)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.e((java.lang.Object) r3)
            goto L_0x000c
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.upnp.device.Disposer.run():void");
    }
}
