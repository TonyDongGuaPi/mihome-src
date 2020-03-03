package com.taobao.weex.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class InstanceOnFireEventInterceptor {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private List<String> listenEvents = new ArrayList();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6379065447717223873L, "com/taobao/weex/instance/InstanceOnFireEventInterceptor", 12);
        $jacocoData = a2;
        return a2;
    }

    public abstract void onFireEvent(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2);

    public InstanceOnFireEventInterceptor() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public void addInterceptEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.listenEvents.contains(str)) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            this.listenEvents.add(str);
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public List<String> getListenEvents() {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = this.listenEvents;
        $jacocoInit[6] = true;
        return list;
    }

    public void onInterceptFireEvent(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[7] = true;
            return;
        }
        if (!this.listenEvents.contains(str3)) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            onFireEvent(str, str2, str3, map, map2);
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }
}
