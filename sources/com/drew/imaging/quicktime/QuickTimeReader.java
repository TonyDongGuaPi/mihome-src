package com.drew.imaging.quicktime;

import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;
import java.io.InputStream;

public class QuickTimeReader {
    private QuickTimeReader() {
    }

    public static void a(@NotNull InputStream inputStream, @NotNull QuickTimeHandler quickTimeHandler) {
        StreamReader streamReader = new StreamReader(inputStream);
        streamReader.a(true);
        a(streamReader, -1, quickTimeHandler);
    }

    private static void a(StreamReader streamReader, long j, QuickTimeHandler quickTimeHandler) {
        while (true) {
            if (j != -1) {
                try {
                    if (streamReader.a() >= j) {
                        return;
                    }
                } catch (IOException e) {
                    quickTimeHandler.a(e.getMessage());
                    return;
                }
            }
            Atom atom = new Atom((SequentialReader) streamReader);
            if (quickTimeHandler.b(atom)) {
                a(streamReader, (atom.f5238a + streamReader.a()) - 8, quickTimeHandler.c(atom));
            } else if (quickTimeHandler.a(atom)) {
                quickTimeHandler = quickTimeHandler.a(atom, streamReader.a(((int) atom.f5238a) - 8));
            } else if (atom.f5238a > 1) {
                streamReader.a(atom.f5238a - 8);
            } else if (atom.f5238a == -1) {
                return;
            }
        }
    }
}
