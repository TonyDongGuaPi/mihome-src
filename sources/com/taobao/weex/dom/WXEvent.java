package com.taobao.weex.dom;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.dom.binding.JSONUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXEvent extends ArrayList<String> implements Serializable, Cloneable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String EVENT_KEY_ARGS = "params";
    public static final String EVENT_KEY_TYPE = "type";
    private static final long serialVersionUID = -8186587029452440107L;
    private ArrayMap mEventBindingArgs;
    private ArrayMap<String, List<Object>> mEventBindingArgsValues;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3105840358190111091L, "com/taobao/weex/dom/WXEvent", 75);
        $jacocoData = a2;
        return a2;
    }

    public WXEvent() {
        $jacocoInit()[0] = true;
    }

    public void clear() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEventBindingArgs == null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            this.mEventBindingArgs.clear();
            $jacocoInit[3] = true;
        }
        if (this.mEventBindingArgsValues == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            this.mEventBindingArgsValues.clear();
            $jacocoInit[6] = true;
        }
        super.clear();
        $jacocoInit[7] = true;
    }

    public boolean remove(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEventBindingArgs == null) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            this.mEventBindingArgs.remove(str);
            $jacocoInit[10] = true;
        }
        if (this.mEventBindingArgsValues == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            this.mEventBindingArgsValues.remove(str);
            $jacocoInit[13] = true;
        }
        boolean remove = super.remove(str);
        $jacocoInit[14] = true;
        return remove;
    }

    public ArrayMap getEventBindingArgs() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayMap arrayMap = this.mEventBindingArgs;
        $jacocoInit[15] = true;
        return arrayMap;
    }

    public ArrayMap<String, List<Object>> getEventBindingArgsValues() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayMap<String, List<Object>> arrayMap = this.mEventBindingArgsValues;
        $jacocoInit[16] = true;
        return arrayMap;
    }

    public void addEvent(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof CharSequence) {
            $jacocoInit[17] = true;
            if (JSONUtils.isJSON(obj.toString())) {
                $jacocoInit[18] = true;
                addEvent(JSONUtils.toJSON(obj.toString()));
                $jacocoInit[19] = true;
                return;
            }
            String obj2 = obj.toString();
            $jacocoInit[20] = true;
            if (contains(obj2)) {
                $jacocoInit[21] = true;
            } else {
                $jacocoInit[22] = true;
                add(obj2);
                $jacocoInit[23] = true;
            }
            $jacocoInit[24] = true;
        } else if (!(obj instanceof JSONObject)) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            addBindingEvent((JSONObject) obj);
            $jacocoInit[27] = true;
        }
        $jacocoInit[28] = true;
    }

    public static String getEventName(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof CharSequence) {
            $jacocoInit[29] = true;
            String obj2 = obj.toString();
            $jacocoInit[30] = true;
            return obj2;
        } else if (obj instanceof JSONObject) {
            $jacocoInit[31] = true;
            String string = ((JSONObject) obj).getString("type");
            $jacocoInit[32] = true;
            return string;
        } else if (obj == null) {
            $jacocoInit[33] = true;
            return null;
        } else {
            String obj3 = obj.toString();
            $jacocoInit[34] = true;
            return obj3;
        }
    }

    public void parseStatements() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isEmpty()) {
            $jacocoInit[35] = true;
        } else {
            $jacocoInit[36] = true;
            int i = 0;
            $jacocoInit[37] = true;
            while (i < size()) {
                $jacocoInit[39] = true;
                String str = (String) get(i);
                $jacocoInit[40] = true;
                if (!JSONUtils.isJSON(str)) {
                    $jacocoInit[41] = true;
                } else {
                    $jacocoInit[42] = true;
                    JSONObject json = JSONUtils.toJSON(str);
                    $jacocoInit[43] = true;
                    String addBindingEvent = addBindingEvent(json);
                    $jacocoInit[44] = true;
                    set(i, addBindingEvent);
                    $jacocoInit[45] = true;
                }
                i++;
                $jacocoInit[46] = true;
            }
            $jacocoInit[38] = true;
        }
        $jacocoInit[47] = true;
    }

    private String addBindingEvent(JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        String string = jSONObject.getString("type");
        $jacocoInit[48] = true;
        Object obj = jSONObject.get("params");
        if (string == null) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
            addBindingArgsEvent(string, obj);
            $jacocoInit[51] = true;
        }
        $jacocoInit[52] = true;
        return string;
    }

    private void addBindingArgsEvent(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (contains(str)) {
            $jacocoInit[53] = true;
        } else {
            $jacocoInit[54] = true;
            add(str);
            $jacocoInit[55] = true;
        }
        if (obj == null) {
            $jacocoInit[56] = true;
        } else {
            if (this.mEventBindingArgs != null) {
                $jacocoInit[57] = true;
            } else {
                $jacocoInit[58] = true;
                this.mEventBindingArgs = new ArrayMap();
                $jacocoInit[59] = true;
            }
            this.mEventBindingArgs.put(str, ELUtils.bindingBlock(obj));
            $jacocoInit[60] = true;
        }
        $jacocoInit[61] = true;
    }

    public void putEventBindingArgsValue(String str, List<Object> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEventBindingArgsValues != null) {
            $jacocoInit[62] = true;
        } else {
            $jacocoInit[63] = true;
            this.mEventBindingArgsValues = new ArrayMap<>();
            $jacocoInit[64] = true;
        }
        if (list == null) {
            $jacocoInit[65] = true;
            this.mEventBindingArgsValues.remove(str);
            $jacocoInit[66] = true;
        } else {
            this.mEventBindingArgsValues.put(str, list);
            $jacocoInit[67] = true;
        }
        $jacocoInit[68] = true;
    }

    public WXEvent clone() {
        boolean[] $jacocoInit = $jacocoInit();
        WXEvent wXEvent = new WXEvent();
        $jacocoInit[69] = true;
        wXEvent.addAll(this);
        if (this.mEventBindingArgs == null) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            wXEvent.mEventBindingArgs = new ArrayMap((SimpleArrayMap) this.mEventBindingArgs);
            $jacocoInit[72] = true;
        }
        wXEvent.mEventBindingArgsValues = null;
        $jacocoInit[73] = true;
        return wXEvent;
    }
}
