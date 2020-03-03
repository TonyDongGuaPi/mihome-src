package com.taobao.weex.ui.component.pesudo;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.taobao.weex.common.Constants;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class PesudoStatus {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    static final int CLASS_ACTIVE = 0;
    static final int CLASS_DISABLED = 3;
    static final int CLASS_ENABLED = 2;
    static final int CLASS_FOCUS = 1;
    private static final int SET = 1;
    private static final int UNSET = 0;
    private int[] mStatuses = new int[4];

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6298279680128159169L, "com/taobao/weex/ui/component/pesudo/PesudoStatus", 58);
        $jacocoData = a2;
        return a2;
    }

    public PesudoStatus() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        int i = 0;
        while (i < this.mStatuses.length) {
            this.mStatuses[i] = 0;
            i++;
            $jacocoInit[1] = true;
        }
        $jacocoInit[2] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStatus(java.lang.String r8, boolean r9) {
        /*
            r7 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r8.hashCode()
            r2 = -1487344704(0xffffffffa758ebc0, float:-3.0103822E-15)
            r3 = 2
            r4 = 0
            r5 = 3
            r6 = 1
            if (r1 == r2) goto L_0x005a
            r2 = -1482202954(0xffffffffa7a760b6, float:-4.6456665E-15)
            if (r1 == r2) goto L_0x0049
            r2 = 689157575(0x2913b5c7, float:3.2798224E-14)
            if (r1 == r2) goto L_0x0036
            r2 = 1758095582(0x68ca68de, float:7.64682E24)
            if (r1 == r2) goto L_0x0023
            r0[r5] = r6
            goto L_0x0065
        L_0x0023:
            java.lang.String r1 = ":focus"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L_0x0030
            r8 = 10
            r0[r8] = r6
            goto L_0x0065
        L_0x0030:
            r8 = 11
            r0[r8] = r6
            r8 = 3
            goto L_0x006b
        L_0x0036:
            java.lang.String r1 = ":enabled"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L_0x0043
            r8 = 8
            r0[r8] = r6
            goto L_0x0065
        L_0x0043:
            r8 = 9
            r0[r8] = r6
            r8 = 2
            goto L_0x006b
        L_0x0049:
            java.lang.String r1 = ":disabled"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L_0x0055
            r8 = 6
            r0[r8] = r6
            goto L_0x0065
        L_0x0055:
            r8 = 7
            r0[r8] = r6
            r8 = 1
            goto L_0x006b
        L_0x005a:
            java.lang.String r1 = ":active"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L_0x0067
            r8 = 4
            r0[r8] = r6
        L_0x0065:
            r8 = -1
            goto L_0x006b
        L_0x0067:
            r8 = 5
            r0[r8] = r6
            r8 = 0
        L_0x006b:
            switch(r8) {
                case 0: goto L_0x008b;
                case 1: goto L_0x0083;
                case 2: goto L_0x007b;
                case 3: goto L_0x0073;
                default: goto L_0x006e;
            }
        L_0x006e:
            r8 = 12
            r0[r8] = r6
            goto L_0x0092
        L_0x0073:
            r7.setStatus((int) r6, (boolean) r9)
            r8 = 16
            r0[r8] = r6
            goto L_0x0092
        L_0x007b:
            r7.setStatus((int) r3, (boolean) r9)
            r8 = 15
            r0[r8] = r6
            goto L_0x0092
        L_0x0083:
            r7.setStatus((int) r5, (boolean) r9)
            r8 = 14
            r0[r8] = r6
            goto L_0x0092
        L_0x008b:
            r7.setStatus((int) r4, (boolean) r9)
            r8 = 13
            r0[r8] = r6
        L_0x0092:
            r8 = 17
            r0[r8] = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.pesudo.PesudoStatus.setStatus(java.lang.String, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public void setStatus(int i, boolean z) {
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        int[] iArr = this.mStatuses;
        if (z) {
            $jacocoInit[18] = true;
            i2 = 1;
        } else {
            i2 = 0;
            $jacocoInit[19] = true;
        }
        iArr[i] = i2;
        $jacocoInit[20] = true;
    }

    public boolean isSet(int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStatuses[i] == 1) {
            $jacocoInit[21] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[22] = true;
        }
        $jacocoInit[23] = true;
        return z;
    }

    @Nullable
    public String getStatuses() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = new StringBuilder();
        $jacocoInit[24] = true;
        if (!isSet(0)) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            sb.append(Constants.PSEUDO.ACTIVE);
            $jacocoInit[27] = true;
        }
        if (!isSet(3)) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            sb.append(Constants.PSEUDO.DISABLED);
            $jacocoInit[30] = true;
        }
        if (!isSet(1)) {
            $jacocoInit[31] = true;
        } else if (isSet(3)) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            sb.append(Constants.PSEUDO.FOCUS);
            $jacocoInit[34] = true;
        }
        if (sb.length() == 0) {
            str = null;
            $jacocoInit[35] = true;
        } else {
            str = sb.toString();
            $jacocoInit[36] = true;
        }
        $jacocoInit[37] = true;
        return str;
    }

    public Map<String, Object> updateStatusAndGetUpdateStyles(String str, boolean z, Map<String, Map<String, Object>> map, Map<String, Object> map2) {
        Object obj;
        boolean[] $jacocoInit = $jacocoInit();
        String statuses = getStatuses();
        $jacocoInit[38] = true;
        setStatus(str, z);
        $jacocoInit[39] = true;
        String statuses2 = getStatuses();
        $jacocoInit[40] = true;
        Map map3 = map.get(statuses2);
        $jacocoInit[41] = true;
        Map map4 = map.get(statuses);
        $jacocoInit[42] = true;
        ArrayMap arrayMap = new ArrayMap();
        if (map4 == null) {
            $jacocoInit[43] = true;
        } else {
            $jacocoInit[44] = true;
            arrayMap.putAll(map4);
            $jacocoInit[45] = true;
        }
        $jacocoInit[46] = true;
        for (String str2 : arrayMap.keySet()) {
            $jacocoInit[47] = true;
            if (map2.containsKey(str2)) {
                obj = map2.get(str2);
                $jacocoInit[48] = true;
            } else {
                obj = "";
                $jacocoInit[49] = true;
            }
            arrayMap.put(str2, obj);
            $jacocoInit[50] = true;
        }
        if (map3 == null) {
            $jacocoInit[51] = true;
        } else {
            $jacocoInit[52] = true;
            $jacocoInit[53] = true;
            for (Map.Entry entry : map3.entrySet()) {
                $jacocoInit[55] = true;
                arrayMap.put(entry.getKey(), entry.getValue());
                $jacocoInit[56] = true;
            }
            $jacocoInit[54] = true;
        }
        $jacocoInit[57] = true;
        return arrayMap;
    }
}
