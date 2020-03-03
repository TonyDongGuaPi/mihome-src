package com.xiaomi.smarthome.newui.commonusemgr;

import com.xiaomi.smarthome.R;

public class CUDFragFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20644a = "ROUTER";
    public static final String b = "CATEGORY";
    public static final String c = "ROOM";
    private static final int[] d = {R.string.router_name, R.string.tag_category_title, R.string.room_name};

    private CUDFragFactory() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment a(java.lang.String r5, android.content.Context r6) {
        /*
            int r0 = r5.hashCode()
            r1 = -1871803575(0xffffffff906e8b49, float:-4.7044525E-29)
            r2 = 0
            r3 = 1
            r4 = 2
            if (r0 == r1) goto L_0x002b
            r1 = 2521307(0x2678db, float:3.533104E-39)
            if (r0 == r1) goto L_0x0021
            r1 = 833137918(0x31a8acfe, float:4.9091105E-9)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "CATEGORY"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0035
            r5 = 1
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "ROOM"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0035
            r5 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "ROUTER"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0035
            r5 = 2
            goto L_0x0036
        L_0x0035:
            r5 = -1
        L_0x0036:
            switch(r5) {
                case 0: goto L_0x005d;
                case 1: goto L_0x004c;
                case 2: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            r5 = 0
            return r5
        L_0x003b:
            android.content.res.Resources r5 = r6.getResources()
            int[] r6 = d
            r6 = r6[r2]
            java.lang.String r5 = r5.getString(r6)
            com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment r5 = com.xiaomi.smarthome.newui.commonusemgr.RouterDeviceFragment.a(r5)
            return r5
        L_0x004c:
            android.content.res.Resources r5 = r6.getResources()
            int[] r6 = d
            r6 = r6[r3]
            java.lang.String r5 = r5.getString(r6)
            com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment r5 = com.xiaomi.smarthome.newui.commonusemgr.CategoryDeviceFragment.a(r5)
            return r5
        L_0x005d:
            android.content.res.Resources r5 = r6.getResources()
            int[] r6 = d
            r6 = r6[r4]
            java.lang.String r5 = r5.getString(r6)
            com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment r5 = com.xiaomi.smarthome.newui.commonusemgr.RoomDeviceFragment.a(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.commonusemgr.CUDFragFactory.a(java.lang.String, android.content.Context):com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment");
    }
}
