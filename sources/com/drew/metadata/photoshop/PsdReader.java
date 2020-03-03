package com.drew.metadata.photoshop;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;

public class PsdReader {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5264a = (!PsdReader.class.desiredAssertionStatus());

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        PsdHeaderDirectory psdHeaderDirectory = new PsdHeaderDirectory();
        metadata.a(psdHeaderDirectory);
        try {
            if (sequentialReader.j() != 943870035) {
                psdHeaderDirectory.a("Invalid PSD file signature");
                return;
            }
            int g = sequentialReader.g();
            if (g == 1 || g == 2) {
                sequentialReader.a(6);
                psdHeaderDirectory.a(1, sequentialReader.g());
                psdHeaderDirectory.a(2, sequentialReader.j());
                psdHeaderDirectory.a(3, sequentialReader.j());
                psdHeaderDirectory.a(4, sequentialReader.g());
                psdHeaderDirectory.a(5, sequentialReader.g());
                try {
                    sequentialReader.a(sequentialReader.i());
                    try {
                        long i = sequentialReader.i();
                        if (!f5264a) {
                            if (i > 2147483647L) {
                                throw new AssertionError();
                            }
                        }
                        new PhotoshopReader().a(sequentialReader, (int) i, metadata);
                    } catch (IOException unused) {
                    }
                } catch (IOException unused2) {
                }
            } else {
                psdHeaderDirectory.a("Invalid PSD file version (must be 1 or 2)");
            }
        } catch (IOException unused3) {
            psdHeaderDirectory.a("Unable to read PSD header");
        }
    }
}
