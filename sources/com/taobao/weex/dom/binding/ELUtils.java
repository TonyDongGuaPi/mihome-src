package com.taobao.weex.dom.binding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.el.parse.Parser;
import com.taobao.weex.el.parse.Token;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ELUtils {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String BINDING = "@binding";
    public static final String COMPONENT_PROPS = "@componentProps";
    public static final String[] EXCLUDES_BINDING = {"clickEventParams"};
    public static final String IS_COMPONENT_ROOT = "@isComponentRoot";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3260678415062478425L, "com/taobao/weex/dom/binding/ELUtils", 63);
        $jacocoData = a2;
        return a2;
    }

    public ELUtils() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[62] = true;
    }

    public static boolean isBinding(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (obj instanceof JSONObject) {
            $jacocoInit[1] = true;
            if (((JSONObject) obj).containsKey(BINDING)) {
                $jacocoInit[2] = true;
                return true;
            }
            $jacocoInit[3] = true;
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            $jacocoInit[4] = true;
            $jacocoInit[5] = true;
            int i = 0;
            while (i < jSONArray.size()) {
                $jacocoInit[6] = true;
                if (isBinding(jSONArray.get(i))) {
                    $jacocoInit[7] = true;
                    return true;
                }
                i++;
                $jacocoInit[8] = true;
            }
            $jacocoInit[9] = true;
        } else if (!(obj instanceof String)) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            if (((String) obj).indexOf(BINDING) >= 0) {
                $jacocoInit[12] = true;
                z = true;
            } else {
                $jacocoInit[13] = true;
            }
            $jacocoInit[14] = true;
            return z;
        }
        $jacocoInit[15] = true;
        return false;
    }

    public static Object bindingBlock(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            $jacocoInit[16] = true;
            if (!jSONObject.containsKey(BINDING)) {
                $jacocoInit[17] = true;
            } else {
                $jacocoInit[18] = true;
                Object obj2 = jSONObject.get(BINDING);
                if (obj2 instanceof Token) {
                    $jacocoInit[19] = true;
                } else {
                    $jacocoInit[20] = true;
                    jSONObject.put(BINDING, (Object) Parser.parse(obj2.toString()));
                    $jacocoInit[21] = true;
                }
            }
            Set<String> keySet = jSONObject.keySet();
            $jacocoInit[22] = true;
            $jacocoInit[23] = true;
            for (String next : keySet) {
                $jacocoInit[24] = true;
                if (!(jSONObject.get(next) instanceof JSONObject)) {
                    $jacocoInit[25] = true;
                } else {
                    $jacocoInit[26] = true;
                    if (!((JSONObject) jSONObject.get(next)).containsKey(BINDING)) {
                        $jacocoInit[27] = true;
                    } else {
                        $jacocoInit[28] = true;
                        JSONObject jSONObject2 = (JSONObject) jSONObject.get(next);
                        $jacocoInit[29] = true;
                        Object obj3 = jSONObject2.get(BINDING);
                        if (obj3 instanceof Token) {
                            $jacocoInit[30] = true;
                        } else {
                            $jacocoInit[31] = true;
                            jSONObject2.put(BINDING, (Object) Parser.parse(obj3.toString()));
                            $jacocoInit[32] = true;
                        }
                    }
                }
                $jacocoInit[33] = true;
            }
            $jacocoInit[34] = true;
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            $jacocoInit[35] = true;
            int i = 0;
            $jacocoInit[36] = true;
            while (i < jSONArray.size()) {
                $jacocoInit[37] = true;
                bindingBlock(jSONArray.get(i));
                i++;
                $jacocoInit[38] = true;
            }
            $jacocoInit[39] = true;
        } else if (!(obj instanceof String)) {
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            String obj4 = obj.toString();
            $jacocoInit[42] = true;
            if (obj4.startsWith(Operators.BLOCK_START_STR)) {
                $jacocoInit[43] = true;
                Object bindingBlock = bindingBlock(JSON.parseObject(obj4));
                $jacocoInit[44] = true;
                return bindingBlock;
            } else if (!obj4.startsWith(Operators.ARRAY_START_STR)) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                Object bindingBlock2 = bindingBlock(JSON.parseArray(obj4));
                $jacocoInit[47] = true;
                return bindingBlock2;
            }
        }
        $jacocoInit[48] = true;
        return obj;
    }

    public static Object vforBlock(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof JSONObject) {
            $jacocoInit[49] = true;
            JSONObject jSONObject = (JSONObject) obj;
            if (!jSONObject.containsKey(WXStatement.WX_FOR_LIST)) {
                $jacocoInit[50] = true;
            } else {
                $jacocoInit[51] = true;
                Object obj2 = jSONObject.get(WXStatement.WX_FOR_LIST);
                if (obj2 instanceof Token) {
                    $jacocoInit[52] = true;
                } else {
                    $jacocoInit[53] = true;
                    jSONObject.put(WXStatement.WX_FOR_LIST, (Object) Parser.parse(obj2.toString()));
                    $jacocoInit[54] = true;
                }
                $jacocoInit[55] = true;
            }
        } else if (obj instanceof String) {
            $jacocoInit[56] = true;
            Object vforBlock = vforBlock(JSONObject.parseObject(obj.toString()));
            $jacocoInit[57] = true;
            return vforBlock;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[58] = true;
        } else {
            $jacocoInit[59] = true;
            WXLogUtils.e("weex", "weex vfor is illegal " + obj);
            $jacocoInit[60] = true;
        }
        $jacocoInit[61] = true;
        return obj;
    }
}
