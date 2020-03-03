package org.cybergarage.upnp.control;

import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;
import org.cybergarage.util.ThreadCore;

public class RenewSubscriber extends ThreadCore {
    public static final long INTERVAL = 120;
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private ControlPoint ctrlPoint;

    public RenewSubscriber(ControlPoint controlPoint) {
        setControlPoint(controlPoint);
    }

    public void setControlPoint(ControlPoint controlPoint) {
        this.ctrlPoint = controlPoint;
    }

    public ControlPoint getControlPoint() {
        return this.ctrlPoint;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r5 = this;
            org.cybergarage.upnp.ControlPoint r0 = r5.getControlPoint()
        L_0x0004:
            boolean r1 = r5.isRunnable()
            r2 = 1
            if (r1 != r2) goto L_0x0037
            r1 = 120000(0x1d4c0, double:5.9288E-319)
            java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x0011 }
        L_0x0011:
            java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x001b }
            r0.renewSubscriberService()     // Catch:{ Exception -> 0x001b }
            java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x001b }
            goto L_0x0004
        L_0x001b:
            r1 = move-exception
            org.cybergarage.util.CommonLog r2 = log
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "catch exception!!!e = "
            r3.append(r4)
            java.lang.String r1 = r1.getMessage()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.e((java.lang.Object) r1)
            goto L_0x0004
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.upnp.control.RenewSubscriber.run():void");
    }
}
