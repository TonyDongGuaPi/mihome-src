package com.imi.fastjson.serializer;

public class PascalNameFilter implements NameFilter {
    public String a(Object obj, String str, Object obj2) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char upperCase = Character.toUpperCase(str.charAt(0));
        return upperCase + str.substring(1);
    }
}
