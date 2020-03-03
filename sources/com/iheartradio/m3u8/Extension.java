package com.iheartradio.m3u8;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum Extension {
    M3U("m3u", Encoding.WINDOWS_1252),
    M3U8("m3u8", Encoding.UTF_8);
    
    private static final Map<String, Extension> sMap = null;
    final Encoding encoding;
    final String value;

    static {
        int i;
        sMap = new HashMap();
        for (Extension extension : values()) {
            sMap.put(extension.value, extension);
        }
    }

    private Extension(String str, Encoding encoding2) {
        this.value = str;
        this.encoding = encoding2;
    }

    public static Extension fromValue(String str) {
        if (str == null) {
            return null;
        }
        return sMap.get(str.toLowerCase(Locale.US));
    }

    public String getValue() {
        return this.value;
    }

    public Encoding getEncoding() {
        return this.encoding;
    }
}
