package com.xiaomi.miot.store.utils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMapUtils {
    public static JSONObject a(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            try {
                switch (readableMap.getType(nextKey)) {
                    case Null:
                        break;
                    case Map:
                        jSONObject.putOpt(nextKey, a(readableMap.getMap(nextKey)));
                        break;
                    case Array:
                        jSONObject.putOpt(nextKey, a(readableMap.getArray(nextKey)));
                        break;
                    case Number:
                        jSONObject.putOpt(nextKey, Double.valueOf(readableMap.getDouble(nextKey)));
                        break;
                    case String:
                        jSONObject.putOpt(nextKey, readableMap.getString(nextKey));
                        break;
                    case Boolean:
                        jSONObject.putOpt(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
                        break;
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public static JSONArray a(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            try {
                switch (readableArray.getType(i)) {
                    case Null:
                        break;
                    case Map:
                        jSONArray.put(a(readableArray.getMap(i)));
                        break;
                    case Array:
                        jSONArray.put(a(readableArray.getArray(i)));
                        break;
                    case Number:
                        jSONArray.put(readableArray.getDouble(i));
                        break;
                    case String:
                        jSONArray.put(readableArray.getString(i));
                        break;
                    case Boolean:
                        jSONArray.put(readableArray.getBoolean(i));
                        break;
                }
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }
}
