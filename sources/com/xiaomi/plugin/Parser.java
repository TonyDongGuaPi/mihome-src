package com.xiaomi.plugin;

import com.google.gson.JsonElement;

public interface Parser<T> {
    public static final Parser<JsonElement> DEFAULT_PARSER = new Parser<JsonElement>() {
        public JsonElement parse(JsonElement jsonElement) {
            return jsonElement;
        }
    };

    T parse(JsonElement jsonElement);
}
