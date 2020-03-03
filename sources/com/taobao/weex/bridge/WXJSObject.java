package com.taobao.weex.bridge;

import com.taobao.weex.utils.WXJsonUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXJSObject {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int JSON = 3;
    public static final int NUMBER = 1;
    public static final int String = 2;
    public static final int WSON = 4;
    public Object data;
    public String key;
    public int type;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2719106493538064811L, "com/taobao/weex/bridge/WXJSObject", 13);
        $jacocoData = a2;
        return a2;
    }

    public WXJSObject(int i, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        this.type = i;
        this.data = obj;
        $jacocoInit[0] = true;
    }

    public WXJSObject(int i, Object obj, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.type = i;
        this.data = obj;
        this.key = str;
        $jacocoInit[1] = true;
    }

    public WXJSObject(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            this.type = 2;
            this.data = "";
            $jacocoInit[2] = true;
            return;
        }
        this.data = obj;
        if (obj instanceof Integer) {
            this.type = 1;
            $jacocoInit[3] = true;
            this.data = new Double((double) ((Integer) obj).intValue());
            $jacocoInit[4] = true;
        } else if (obj instanceof Double) {
            this.type = 1;
            $jacocoInit[5] = true;
        } else if (obj instanceof Float) {
            this.type = 1;
            $jacocoInit[6] = true;
            this.data = new Double((double) ((Float) obj).intValue());
            $jacocoInit[7] = true;
        } else if (obj instanceof String) {
            this.type = 2;
            $jacocoInit[8] = true;
        } else if (!(obj instanceof Object)) {
            $jacocoInit[9] = true;
        } else {
            this.type = 3;
            $jacocoInit[10] = true;
            this.data = WXJsonUtils.fromObjectToJSONString(obj, true);
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
    }
}
