package com.xiaomi.youpin.youpin_network.callback;

import com.google.gson.JsonElement;

public interface YouPinJsonParser<T> {
    T b(JsonElement jsonElement);
}
