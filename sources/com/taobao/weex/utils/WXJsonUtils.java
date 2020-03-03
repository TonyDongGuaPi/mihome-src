package com.taobao.weex.utils;

import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.common.WXRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXJsonUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1657602546850538280L, "com/taobao/weex/utils/WXJsonUtils", 25);
        $jacocoData = a2;
        return a2;
    }

    public WXJsonUtils() {
        $jacocoInit()[0] = true;
    }

    @NonNull
    public static <T> List<T> getList(String str, Class<T> cls) {
        List<T> list;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[1] = true;
            list = JSONObject.parseArray(str, cls);
            $jacocoInit[2] = true;
        } catch (Exception e) {
            $jacocoInit[3] = true;
            e.printStackTrace();
            $jacocoInit[4] = true;
            list = new ArrayList<>();
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
        return list;
    }

    @NonNull
    public static String fromObjectToJSONString(Object obj, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!z) {
            try {
                $jacocoInit[7] = true;
                String jSONString = JSON.toJSONString(obj);
                $jacocoInit[10] = true;
                return jSONString;
            } catch (Exception e) {
                $jacocoInit[11] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    WXLogUtils.e("fromObjectToJSONString error:", (Throwable) e);
                    $jacocoInit[14] = true;
                    return "{}";
                }
                $jacocoInit[12] = true;
                WXRuntimeException wXRuntimeException = new WXRuntimeException("fromObjectToJSONString parse error!");
                $jacocoInit[13] = true;
                throw wXRuntimeException;
            }
        } else {
            $jacocoInit[8] = true;
            String jSONString2 = JSON.toJSONString(obj, SerializerFeature.WriteNonStringKeyAsString);
            $jacocoInit[9] = true;
            return jSONString2;
        }
    }

    @NonNull
    public static String fromObjectToJSONString(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        String fromObjectToJSONString = fromObjectToJSONString(obj, false);
        $jacocoInit[15] = true;
        return fromObjectToJSONString;
    }

    public static void putAll(Map<String, Object> map, JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[16] = true;
        for (Map.Entry next : jSONObject.entrySet()) {
            $jacocoInit[17] = true;
            String str = (String) next.getKey();
            $jacocoInit[18] = true;
            Object value = next.getValue();
            if (str == null) {
                $jacocoInit[19] = true;
            } else if (value == null) {
                $jacocoInit[20] = true;
            } else {
                $jacocoInit[21] = true;
                map.put(str, value);
                $jacocoInit[22] = true;
            }
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
    }
}
