package com.tmall.wireless.vaf.virtualview.event;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9395a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    private static final String h = "EventManager_TMTEST";
    private Object[] i = new Object[6];

    public void a(int i2, IEventProcessor iEventProcessor) {
        if (iEventProcessor == null || i2 < 0 || i2 >= 6) {
            Log.e(h, "register failed type:" + i2 + "  processor:" + iEventProcessor);
            return;
        }
        List list = (List) this.i[i2];
        if (list == null) {
            list = new ArrayList();
            this.i[i2] = list;
        }
        list.add(iEventProcessor);
    }

    public void b(int i2, IEventProcessor iEventProcessor) {
        if (iEventProcessor == null || i2 < 0 || i2 >= 6) {
            Log.e(h, "unregister failed type:" + i2 + "  processor:" + iEventProcessor);
            return;
        }
        List list = (List) this.i[i2];
        if (list != null) {
            list.remove(iEventProcessor);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [int] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(int r5, com.tmall.wireless.vaf.virtualview.event.EventData r6) {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            if (r5 < 0) goto L_0x0006
            r2 = 1
            goto L_0x0007
        L_0x0006:
            r2 = 0
        L_0x0007:
            r3 = 6
            if (r5 >= r3) goto L_0x000b
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            r0 = r0 & r2
            if (r0 == 0) goto L_0x002c
            java.lang.Object[] r0 = r4.i
            r5 = r0[r5]
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L_0x002c
            int r0 = r5.size()
            r2 = 0
        L_0x001c:
            if (r1 >= r0) goto L_0x002b
            java.lang.Object r2 = r5.get(r1)
            com.tmall.wireless.vaf.virtualview.event.IEventProcessor r2 = (com.tmall.wireless.vaf.virtualview.event.IEventProcessor) r2
            boolean r2 = r2.a(r6)
            int r1 = r1 + 1
            goto L_0x001c
        L_0x002b:
            r1 = r2
        L_0x002c:
            if (r6 == 0) goto L_0x0031
            r6.b()
        L_0x0031:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.event.EventManager.a(int, com.tmall.wireless.vaf.virtualview.event.EventData):boolean");
    }
}
