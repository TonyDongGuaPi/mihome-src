package com.taobao.weex.common;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.taobao.weex.WXEnvironment;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class WXCompatModule extends WXModule implements Destroyable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private ModuleReceive mModuleReceive = new ModuleReceive(this);

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-276115113810559299L, "com/taobao/weex/common/WXCompatModule", 10);
        $jacocoData = a2;
        return a2;
    }

    public WXCompatModule() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(WXEnvironment.getApplication());
        ModuleReceive moduleReceive = this.mModuleReceive;
        IntentFilter intentFilter = new IntentFilter(WXModule.ACTION_ACTIVITY_RESULT);
        $jacocoInit[2] = true;
        instance.registerReceiver(moduleReceive, intentFilter);
        $jacocoInit[3] = true;
        LocalBroadcastManager instance2 = LocalBroadcastManager.getInstance(WXEnvironment.getApplication());
        ModuleReceive moduleReceive2 = this.mModuleReceive;
        IntentFilter intentFilter2 = new IntentFilter(WXModule.ACTION_REQUEST_PERMISSIONS_RESULT);
        $jacocoInit[4] = true;
        instance2.registerReceiver(moduleReceive2, intentFilter2);
        $jacocoInit[5] = true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        $jacocoInit()[6] = true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        $jacocoInit()[7] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(WXEnvironment.getApplication());
        ModuleReceive moduleReceive = this.mModuleReceive;
        $jacocoInit[8] = true;
        instance.unregisterReceiver(moduleReceive);
        $jacocoInit[9] = true;
    }

    static class ModuleReceive extends BroadcastReceiver {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private WXCompatModule mWXCompatModule;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-804664355209222150L, "com/taobao/weex/common/WXCompatModule$ModuleReceive", 16);
            $jacocoData = a2;
            return a2;
        }

        ModuleReceive(WXCompatModule wXCompatModule) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mWXCompatModule = wXCompatModule;
            $jacocoInit[0] = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0043  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x006f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                boolean[] r6 = $jacocoInit()
                java.lang.String r0 = r7.getAction()
                r1 = 1
                r6[r1] = r1
                int r2 = r0.hashCode()
                r3 = 306451426(0x124413e2, float:6.1871202E-28)
                r4 = -1
                if (r2 == r3) goto L_0x002f
                r3 = 1904079688(0x717df348, float:1.2575011E30)
                if (r2 == r3) goto L_0x001e
                r0 = 2
                r6[r0] = r1
                goto L_0x003a
            L_0x001e:
                java.lang.String r2 = "actionRequestPermissionsResult"
                boolean r0 = r0.equals(r2)
                if (r0 != 0) goto L_0x002a
                r0 = 5
                r6[r0] = r1
                goto L_0x003a
            L_0x002a:
                r0 = 6
                r6[r0] = r1
                r0 = 1
                goto L_0x0040
            L_0x002f:
                java.lang.String r2 = "actionActivityResult"
                boolean r0 = r0.equals(r2)
                if (r0 != 0) goto L_0x003c
                r0 = 3
                r6[r0] = r1
            L_0x003a:
                r0 = -1
                goto L_0x0040
            L_0x003c:
                r0 = 0
                r2 = 4
                r6[r2] = r1
            L_0x0040:
                switch(r0) {
                    case 0: goto L_0x006f;
                    case 1: goto L_0x0047;
                    default: goto L_0x0043;
                }
            L_0x0043:
                r7 = 7
                r6[r7] = r1
                goto L_0x008c
            L_0x0047:
                java.lang.String r0 = "requestCode"
                int r0 = r7.getIntExtra(r0, r4)
                r2 = 11
                r6[r2] = r1
                java.lang.String r2 = "permissions"
                java.lang.String[] r2 = r7.getStringArrayExtra(r2)
                r3 = 12
                r6[r3] = r1
                java.lang.String r3 = "grantResults"
                int[] r7 = r7.getIntArrayExtra(r3)
                r3 = 13
                r6[r3] = r1
                com.taobao.weex.common.WXCompatModule r3 = r5.mWXCompatModule
                r3.onRequestPermissionsResult(r0, r2, r7)
                r7 = 14
                r6[r7] = r1
                goto L_0x008c
            L_0x006f:
                java.lang.String r0 = "requestCode"
                int r0 = r7.getIntExtra(r0, r4)
                r2 = 8
                r6[r2] = r1
                java.lang.String r2 = "resultCode"
                int r2 = r7.getIntExtra(r2, r4)
                r3 = 9
                r6[r3] = r1
                com.taobao.weex.common.WXCompatModule r3 = r5.mWXCompatModule
                r3.onActivityResult(r0, r2, r7)
                r7 = 10
                r6[r7] = r1
            L_0x008c:
                r7 = 15
                r6[r7] = r1
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.common.WXCompatModule.ModuleReceive.onReceive(android.content.Context, android.content.Intent):void");
        }
    }
}
