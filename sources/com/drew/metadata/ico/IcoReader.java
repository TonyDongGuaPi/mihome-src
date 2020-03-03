package com.drew.metadata.ico;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;

public class IcoReader {
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        sequentialReader.a(false);
        try {
            if (sequentialReader.g() != 0) {
                IcoDirectory icoDirectory = new IcoDirectory();
                icoDirectory.a("Invalid header bytes");
                metadata.a(icoDirectory);
                return;
            }
            int g = sequentialReader.g();
            if (g == 1 || g == 2) {
                int g2 = sequentialReader.g();
                if (g2 == 0) {
                    IcoDirectory icoDirectory2 = new IcoDirectory();
                    icoDirectory2.a("Image count cannot be zero");
                    metadata.a(icoDirectory2);
                    return;
                }
                for (int i = 0; i < g2; i++) {
                    IcoDirectory icoDirectory3 = new IcoDirectory();
                    try {
                        icoDirectory3.a(1, g);
                        icoDirectory3.a(2, (int) sequentialReader.e());
                        icoDirectory3.a(3, (int) sequentialReader.e());
                        icoDirectory3.a(4, (int) sequentialReader.e());
                        sequentialReader.e();
                        if (g == 1) {
                            icoDirectory3.a(5, sequentialReader.g());
                            icoDirectory3.a(7, sequentialReader.g());
                        } else {
                            icoDirectory3.a(6, sequentialReader.g());
                            icoDirectory3.a(8, sequentialReader.g());
                        }
                        icoDirectory3.a(9, sequentialReader.i());
                        icoDirectory3.a(10, sequentialReader.i());
                    } catch (IOException e) {
                        icoDirectory3.a("Exception reading ICO file metadata: " + e.getMessage());
                    }
                    metadata.a(icoDirectory3);
                }
                return;
            }
            IcoDirectory icoDirectory4 = new IcoDirectory();
            icoDirectory4.a("Invalid type " + g + " -- expecting 1 or 2");
            metadata.a(icoDirectory4);
        } catch (IOException e2) {
            IcoDirectory icoDirectory5 = new IcoDirectory();
            icoDirectory5.a("Exception reading ICO file metadata: " + e2.getMessage());
            metadata.a(icoDirectory5);
        }
    }
}
