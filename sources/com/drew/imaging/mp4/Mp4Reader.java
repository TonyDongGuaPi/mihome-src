package com.drew.imaging.mp4;

import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;
import java.io.InputStream;

public class Mp4Reader {
    private Mp4Reader() {
    }

    public static void a(@NotNull InputStream inputStream, @NotNull Mp4Handler mp4Handler) {
        StreamReader streamReader = new StreamReader(inputStream);
        streamReader.a(true);
        a(streamReader, -1, mp4Handler);
    }

    private static void a(StreamReader streamReader, long j, Mp4Handler mp4Handler) {
        while (true) {
            if (j != -1) {
                try {
                    if (streamReader.a() >= j) {
                        return;
                    }
                } catch (IOException e) {
                    mp4Handler.a(e.getMessage());
                    return;
                }
            }
            Box box = new Box((SequentialReader) streamReader);
            if (mp4Handler.b(box)) {
                a(streamReader, (box.d + streamReader.a()) - 8, mp4Handler.c(box));
            } else if (mp4Handler.a(box)) {
                mp4Handler = mp4Handler.a(box, streamReader.a(((int) box.d) - 8));
            } else if (box.f != null) {
                streamReader.a(box.d - 24);
            } else if (box.d > 1) {
                streamReader.a(box.d - 8);
            } else if (box.d == -1) {
                return;
            }
        }
    }
}
