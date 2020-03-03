package com.taobao.weex.bridge;

import com.taobao.weex.common.IWXObject;
import java.util.ArrayList;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXTask implements IWXObject {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public ArrayList<String> args;
    public String method;
    public String module;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6072704968077977526L, "com/taobao/weex/bridge/WXTask", 1);
        $jacocoData = a2;
        return a2;
    }

    public WXTask() {
        $jacocoInit()[0] = true;
    }
}
