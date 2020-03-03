package com.xiaomi.youpin.core;

import com.google.gson.JsonElement;

public interface JsonParser<T> {
    T a(JsonElement jsonElement);
}
