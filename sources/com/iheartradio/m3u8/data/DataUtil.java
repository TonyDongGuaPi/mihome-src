package com.iheartradio.m3u8.data;

import java.util.Collections;
import java.util.List;

class DataUtil {
    DataUtil() {
    }

    static <T> List<T> a(List<T> list) {
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }
}
