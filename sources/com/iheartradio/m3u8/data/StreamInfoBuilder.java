package com.iheartradio.m3u8.data;

import java.util.List;

public interface StreamInfoBuilder {
    StreamInfoBuilder b(float f);

    StreamInfoBuilder b(Resolution resolution);

    StreamInfoBuilder b(List<String> list);

    StreamInfoBuilder c(int i);

    StreamInfoBuilder c(String str);

    StreamInfoBuilder d(int i);
}
