package com.youpin.weex.app.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;

public class ModuleUtils {
    public static void a(JSONObject jSONObject, JSCallback jSCallback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("result", (Object) "success");
            jSONObject2.put("data", (Object) jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invoke(jSONObject2);
        }
    }

    public static void a(JSONArray jSONArray, JSCallback jSCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", (Object) "success");
            jSONObject.put("data", (Object) jSONArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invoke(jSONObject);
        }
    }

    public static void a(String str, JSCallback jSCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", (Object) "success");
            jSONObject.put("data", (Object) str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invoke(jSONObject);
        }
    }

    public static void b(JSONObject jSONObject, JSCallback jSCallback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("result", (Object) "success");
            jSONObject2.put("data", (Object) jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invokeAndKeepAlive(jSONObject2);
        }
    }

    public static void b(String str, JSCallback jSCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", (Object) "failed");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("msg", (Object) str);
            jSONObject.put("data", (Object) jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invoke(jSONObject);
        }
    }

    public static void c(JSONObject jSONObject, JSCallback jSCallback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("result", (Object) "failed");
            jSONObject2.put("data", (Object) jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invoke(jSONObject2);
        }
    }

    public static void a(String str, String str2, JSCallback jSCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", (Object) str);
            jSONObject.put("data", (Object) str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSCallback != null) {
            jSCallback.invoke(jSONObject);
        }
    }
}
