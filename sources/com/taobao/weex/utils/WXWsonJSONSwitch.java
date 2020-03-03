package com.taobao.weex.utils;

import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import com.taobao.weex.bridge.WXJSObject;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.wson.Wson;
import com.taobao.weex.wson.WsonUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXWsonJSONSwitch {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "WXSwitch";
    public static boolean USE_WSON = true;
    public static final String WSON_OFF = "wson_off";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8930285948372926899L, "com/taobao/weex/utils/WXWsonJSONSwitch", 34);
        $jacocoData = a2;
        return a2;
    }

    public WXWsonJSONSwitch() {
        $jacocoInit()[0] = true;
    }

    public static final byte[] convertJSONToWsonIfUseWson(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!USE_WSON) {
            $jacocoInit[1] = true;
            return bArr;
        } else if (bArr == null) {
            $jacocoInit[2] = true;
            return null;
        } else {
            String str = new String(bArr);
            $jacocoInit[3] = true;
            if (str.startsWith(Operators.ARRAY_START_STR)) {
                $jacocoInit[4] = true;
                byte[] wson = WsonUtils.toWson(JSON.parseArray(str));
                $jacocoInit[5] = true;
                return wson;
            }
            byte[] wson2 = WsonUtils.toWson(JSON.parse(str));
            $jacocoInit[6] = true;
            return wson2;
        }
    }

    public static final Object parseWsonOrJSON(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (bArr != null) {
            $jacocoInit[7] = true;
            try {
                if (!USE_WSON) {
                    $jacocoInit[9] = true;
                    Object parse = JSON.parse(new String(bArr, "UTF-8"));
                    $jacocoInit[12] = true;
                    return parse;
                }
                $jacocoInit[10] = true;
                Object parse2 = Wson.parse(bArr);
                $jacocoInit[11] = true;
                return parse2;
            } catch (Exception e) {
                $jacocoInit[13] = true;
                WXLogUtils.e(TAG, (Throwable) e);
                if (USE_WSON) {
                    $jacocoInit[14] = true;
                    Object parse3 = JSON.parse(new String(bArr));
                    $jacocoInit[15] = true;
                    return parse3;
                }
                Object parse4 = Wson.parse(bArr);
                $jacocoInit[16] = true;
                return parse4;
            }
        } else {
            $jacocoInit[8] = true;
            return null;
        }
    }

    public static final WXJSObject toWsonOrJsonWXJSObject(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[17] = true;
            WXJSObject wXJSObject = new WXJSObject((Object) null);
            $jacocoInit[18] = true;
            return wXJSObject;
        } else if (obj.getClass() == WXJSObject.class) {
            WXJSObject wXJSObject2 = (WXJSObject) obj;
            $jacocoInit[19] = true;
            return wXJSObject2;
        } else if (USE_WSON) {
            $jacocoInit[20] = true;
            WXJSObject wXJSObject3 = new WXJSObject(4, Wson.toWson(obj));
            $jacocoInit[21] = true;
            return wXJSObject3;
        } else {
            WXJSObject wXJSObject4 = new WXJSObject(3, WXJsonUtils.fromObjectToJSONString(obj));
            $jacocoInit[22] = true;
            return wXJSObject4;
        }
    }

    public static final Object convertWXJSObjectDataToJSON(WXJSObject wXJSObject) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXJSObject.type == 4) {
            $jacocoInit[23] = true;
            Object parse = JSON.parse(Wson.parse((byte[]) wXJSObject.data).toString());
            $jacocoInit[24] = true;
            return parse;
        }
        Object parse2 = JSON.parse(wXJSObject.data.toString());
        $jacocoInit[25] = true;
        return parse2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[33] = true;
    }

    @NonNull
    public static String fromObjectToJSONString(WXJSObject wXJSObject) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXJSObject == null) {
            $jacocoInit[26] = true;
        } else if (wXJSObject.type != 4) {
            $jacocoInit[27] = true;
        } else {
            $jacocoInit[28] = true;
            Object parse = Wson.parse((byte[]) wXJSObject.data);
            if (parse == null) {
                $jacocoInit[29] = true;
            } else {
                $jacocoInit[30] = true;
                String obj = parse.toString();
                $jacocoInit[31] = true;
                return obj;
            }
        }
        String fromObjectToJSONString = WXJsonUtils.fromObjectToJSONString(wXJSObject, false);
        $jacocoInit[32] = true;
        return fromObjectToJSONString;
    }
}
