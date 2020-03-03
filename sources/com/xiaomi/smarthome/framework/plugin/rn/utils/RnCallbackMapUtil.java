package com.xiaomi.smarthome.framework.plugin.rn.utils;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.smarthome.framework.plugin.rn.constants.RnApiErrorInfo;

public class RnCallbackMapUtil {
    public static WritableMap a(String str) {
        return a(-1, str);
    }

    public static WritableMap a(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("code", i);
        if (str == null) {
            str = "";
        }
        createMap.putString("message", str);
        return createMap;
    }

    public static WritableMap a(RnApiErrorInfo rnApiErrorInfo) {
        return a(rnApiErrorInfo.getCode(), rnApiErrorInfo.getMessage());
    }

    public static WritableMap a(RnApiErrorInfo rnApiErrorInfo, String str) {
        return a(rnApiErrorInfo.getCode(), str);
    }

    public static WritableMap a(Object obj) {
        return b(0, obj);
    }

    public static WritableMap a(int i, Object obj) {
        return b(i, obj);
    }

    private static WritableMap b(int i, Object obj) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("code", i);
        if (obj == null) {
            createMap.putString("data", "");
        } else if (obj instanceof Boolean) {
            createMap.putBoolean("data", ((Boolean) obj).booleanValue());
        } else if (obj instanceof Double) {
            createMap.putDouble("data", ((Double) obj).doubleValue());
        } else if (obj instanceof Integer) {
            createMap.putInt("data", ((Integer) obj).intValue());
        } else if (obj instanceof WritableArray) {
            createMap.putArray("data", (WritableArray) obj);
        } else if (obj instanceof WritableMap) {
            createMap.putMap("data", (WritableMap) obj);
        } else {
            createMap.putString("data", obj.toString());
        }
        return createMap;
    }
}
