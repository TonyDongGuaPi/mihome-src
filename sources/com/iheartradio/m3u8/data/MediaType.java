package com.iheartradio.m3u8.data;

import java.util.HashMap;
import java.util.Map;

public enum MediaType {
    AUDIO(Constants.J),
    VIDEO("VIDEO"),
    SUBTITLES(Constants.K),
    CLOSED_CAPTIONS(Constants.L);
    
    private static final Map<String, MediaType> sMap = null;
    private final String value;

    static {
        int i;
        sMap = new HashMap();
        for (MediaType mediaType : values()) {
            sMap.put(mediaType.value, mediaType);
        }
    }

    private MediaType(String str) {
        this.value = str;
    }

    public static MediaType fromValue(String str) {
        return sMap.get(str);
    }

    public String getValue() {
        return this.value;
    }
}
