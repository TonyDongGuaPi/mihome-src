package com.xiaomi.plugin;

import com.google.gson.JsonObject;

public class RequestParams {
    public String action;
    public String key;
    public String model;
    public JsonObject parameters;

    public RequestParams(String str, String str2) {
        this(str, str2, (JsonObject) null);
    }

    public RequestParams(String str, String str2, JsonObject jsonObject) {
        this.model = str;
        this.action = str2;
        this.key = str + str2;
        this.parameters = jsonObject;
    }

    public JsonObject toJsonObjectForMulti() {
        JsonObject jsonObject = new JsonObject();
        if (this.parameters == null) {
            this.parameters = new JsonObject();
        }
        jsonObject.addProperty("model", this.model);
        jsonObject.addProperty("action", this.action);
        jsonObject.add("parameters", this.parameters);
        return jsonObject;
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        if (this.parameters == null) {
            this.parameters = new JsonObject();
        }
        jsonObject2.addProperty("model", this.model);
        jsonObject2.addProperty("action", this.action);
        jsonObject2.add("parameters", this.parameters);
        jsonObject.add(this.action, jsonObject2);
        return jsonObject;
    }

    public String toString() {
        return toJsonObject().toString();
    }
}
