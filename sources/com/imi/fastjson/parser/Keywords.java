package com.imi.fastjson.parser;

import java.util.HashMap;
import java.util.Map;

public class Keywords {

    /* renamed from: a  reason: collision with root package name */
    public static Keywords f6089a;
    private final Map<String, Integer> b;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("null", 8);
        hashMap.put("new", 9);
        hashMap.put("true", 6);
        hashMap.put("false", 7);
        f6089a = new Keywords(hashMap);
    }

    public Keywords(Map<String, Integer> map) {
        this.b = map;
    }

    public Integer a(String str) {
        return this.b.get(str);
    }
}
