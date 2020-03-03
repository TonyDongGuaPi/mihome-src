package com.taobao.weex.bridge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public interface WXValidateProcessor {
    boolean needValidate(String str);

    WXComponentValidateResult onComponentValidate(WXSDKInstance wXSDKInstance, String str, WXComponent wXComponent);

    WXModuleValidateResult onModuleValidate(WXSDKInstance wXSDKInstance, String str, String str2, JSONArray jSONArray, JSONObject jSONObject);

    public static class WXComponentValidateResult {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public boolean isSuccess;
        public String replacedComponent;
        public JSONObject validateInfo;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(5834291316418757526L, "com/taobao/weex/bridge/WXValidateProcessor$WXComponentValidateResult", 1);
            $jacocoData = a2;
            return a2;
        }

        public WXComponentValidateResult() {
            $jacocoInit()[0] = true;
        }
    }

    public static class WXModuleValidateResult {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public boolean isSuccess;
        public JSONObject validateInfo;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(114054860698020862L, "com/taobao/weex/bridge/WXValidateProcessor$WXModuleValidateResult", 1);
            $jacocoData = a2;
            return a2;
        }

        public WXModuleValidateResult() {
            $jacocoInit()[0] = true;
        }
    }
}
