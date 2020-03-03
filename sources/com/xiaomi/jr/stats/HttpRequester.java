package com.xiaomi.jr.stats;

import java.util.Map;

public interface HttpRequester {
    void get(String str, Map<String, String> map);
}
