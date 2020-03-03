package com.drew.metadata.pcx;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;

public class PcxReader {
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        sequentialReader.a(false);
        PcxDirectory pcxDirectory = new PcxDirectory();
        metadata.a(pcxDirectory);
        try {
            if (sequentialReader.f() == 10) {
                pcxDirectory.a(1, (int) sequentialReader.f());
                if (sequentialReader.f() == 1) {
                    pcxDirectory.a(2, (int) sequentialReader.e());
                    pcxDirectory.a(3, sequentialReader.g());
                    pcxDirectory.a(4, sequentialReader.g());
                    pcxDirectory.a(5, sequentialReader.g());
                    pcxDirectory.a(6, sequentialReader.g());
                    pcxDirectory.a(7, sequentialReader.g());
                    pcxDirectory.a(8, sequentialReader.g());
                    pcxDirectory.a(9, sequentialReader.a(48));
                    sequentialReader.a(1);
                    pcxDirectory.a(10, (int) sequentialReader.e());
                    pcxDirectory.a(11, sequentialReader.g());
                    int g = sequentialReader.g();
                    if (g != 0) {
                        pcxDirectory.a(12, g);
                    }
                    int g2 = sequentialReader.g();
                    if (g2 != 0) {
                        pcxDirectory.a(13, g2);
                    }
                    int g3 = sequentialReader.g();
                    if (g3 != 0) {
                        pcxDirectory.a(14, g3);
                        return;
                    }
                    return;
                }
                throw new ImageProcessingException("Invalid PCX encoding byte");
            }
            throw new ImageProcessingException("Invalid PCX identifier byte");
        } catch (Exception e) {
            pcxDirectory.a("Exception reading PCX file metadata: " + e.getMessage());
        }
    }
}
