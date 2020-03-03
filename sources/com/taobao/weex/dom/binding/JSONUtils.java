package com.taobao.weex.dom.binding;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.el.parse.Operators;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class JSONUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6719623608981837278L, "com/taobao/weex/dom/binding/JSONUtils", 8);
        $jacocoData = a2;
        return a2;
    }

    public JSONUtils() {
        $jacocoInit()[0] = true;
    }

    public static boolean isJSON(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof JSONObject) {
            $jacocoInit[1] = true;
            return true;
        } else if (obj instanceof String) {
            $jacocoInit[2] = true;
            boolean startsWith = ((String) obj).startsWith(Operators.BLOCK_START_STR);
            $jacocoInit[3] = true;
            return startsWith;
        } else {
            $jacocoInit[4] = true;
            return false;
        }
    }

    public static JSONObject toJSON(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            $jacocoInit[5] = true;
            return jSONObject;
        }
        JSONObject parseObject = JSONObject.parseObject(obj.toString());
        $jacocoInit[6] = true;
        return parseObject;
    }

    public static boolean isJSON(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean startsWith = str.startsWith(Operators.BLOCK_START_STR);
        $jacocoInit[7] = true;
        return startsWith;
    }
}
