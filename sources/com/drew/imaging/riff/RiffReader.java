package com.drew.imaging.riff;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;

public class RiffReader {
    public void a(@NotNull SequentialReader sequentialReader, @NotNull RiffHandler riffHandler) throws RiffProcessingException, IOException {
        sequentialReader.a(false);
        String b = sequentialReader.b(4);
        if (b.equals("RIFF")) {
            int j = sequentialReader.j() - 4;
            if (riffHandler.a(sequentialReader.b(4))) {
                a(sequentialReader, j, riffHandler);
                return;
            }
            return;
        }
        throw new RiffProcessingException("Invalid RIFF header: " + b);
    }

    public void a(SequentialReader sequentialReader, int i, RiffHandler riffHandler) throws IOException {
        while (sequentialReader.a() < ((long) i)) {
            String str = new String(sequentialReader.a(4));
            int j = sequentialReader.j();
            if (!str.equals("LIST") && !str.equals("RIFF")) {
                if (riffHandler.b(str)) {
                    riffHandler.a(str, sequentialReader.a(j));
                } else {
                    sequentialReader.a((long) j);
                }
                if (j % 2 == 1) {
                    sequentialReader.a(1);
                }
            } else if (riffHandler.c(new String(sequentialReader.a(4)))) {
                a(sequentialReader, j - 4, riffHandler);
            } else {
                sequentialReader.a((long) (j - 4));
            }
        }
    }
}
