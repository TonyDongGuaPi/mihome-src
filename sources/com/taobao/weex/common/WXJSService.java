package com.taobao.weex.common;

import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXJSService implements IWXObject {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String name;
    private Map<String, Object> options = new HashMap();
    private String script;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(568982132268943462L, "com/taobao/weex/common/WXJSService", 8);
        $jacocoData = a2;
        return a2;
    }

    public WXJSService() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public String getName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.name;
        $jacocoInit[2] = true;
        return str;
    }

    public void setName(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.name = str;
        $jacocoInit[3] = true;
    }

    public String getScript() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.script;
        $jacocoInit[4] = true;
        return str;
    }

    public void setScript(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.script = str;
        $jacocoInit[5] = true;
    }

    public Map<String, Object> getOptions() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, Object> map = this.options;
        $jacocoInit[6] = true;
        return map;
    }

    public void setOptions(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.options = map;
        $jacocoInit[7] = true;
    }
}
