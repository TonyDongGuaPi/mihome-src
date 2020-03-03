package com.xiaomi.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtils {
    public static JsonElement getJsonElement(JsonElement jsonElement, String[] strArr) {
        if (jsonElement == null || strArr == null || strArr.length == 0) {
            return jsonElement;
        }
        if (!jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        for (int i = 0; i < strArr.length - 1; i++) {
            if (asJsonObject != null) {
                JsonElement jsonElement2 = asJsonObject.get(strArr[i]);
                if (jsonElement2 == null || !jsonElement2.isJsonObject()) {
                    return null;
                }
                try {
                    asJsonObject = jsonElement2.getAsJsonObject();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        if (asJsonObject != null) {
            return asJsonObject.get(strArr[strArr.length - 1]);
        }
        return null;
    }

    public static JsonObject getJsonObject(JsonElement jsonElement, String[] strArr) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null || !jsonElement2.isJsonObject()) {
            return null;
        }
        try {
            return jsonElement2.getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonArray getJsonArray(JsonElement jsonElement, String[] strArr) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null || !jsonElement2.isJsonArray()) {
            return null;
        }
        try {
            return jsonElement2.getAsJsonArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getString(JsonElement jsonElement, String[] strArr) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null || !jsonElement2.isJsonPrimitive()) {
            return null;
        }
        try {
            return jsonElement2.getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getInt(JsonElement jsonElement, String[] strArr) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null || !jsonElement2.isJsonPrimitive()) {
            return 0;
        }
        try {
            return jsonElement2.getAsInt();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getBoolean(JsonElement jsonElement, String[] strArr) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null || !jsonElement2.isJsonPrimitive()) {
            return false;
        }
        try {
            return jsonElement2.getAsBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static double getDouble(JsonElement jsonElement, String[] strArr) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null || !jsonElement2.isJsonPrimitive()) {
            return 0.0d;
        }
        try {
            return jsonElement2.getAsDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static JsonElement parse(String str) {
        try {
            return new JsonParser().parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parse(String str, Class<T> cls) {
        return parse(str, (String[]) null, cls);
    }

    public static <T> T parse(String str, String[] strArr, Class<T> cls) {
        return parse(parse(str), strArr, cls);
    }

    public static <T> T parse(JsonElement jsonElement, Class<T> cls) {
        return parse(jsonElement, (String[]) null, cls);
    }

    public static <T> T parse(JsonElement jsonElement, String[] strArr, Class<T> cls) {
        JsonElement jsonElement2 = getJsonElement(jsonElement, strArr);
        if (jsonElement2 == null) {
            return null;
        }
        try {
            return new Gson().fromJson(jsonElement2, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Error e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> JsonElement toJsonObject(T t) {
        try {
            return new Gson().toJsonTree(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String toJsonString(T t) {
        try {
            return new Gson().toJsonTree(t).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
