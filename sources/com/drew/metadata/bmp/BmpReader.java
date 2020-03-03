package com.drew.metadata.bmp;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.ErrorDirectory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.bmp.BmpHeaderDirectory;
import com.drew.metadata.icc.IccReader;
import java.io.IOException;

public class BmpReader {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5215a = 19778;
    public static final int b = 16706;
    public static final int c = 17225;
    public static final int d = 18755;
    public static final int e = 20547;
    public static final int f = 21584;

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        sequentialReader.a(false);
        a(sequentialReader, metadata, true);
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata, boolean z) {
        try {
            int g = sequentialReader.g();
            BmpHeaderDirectory bmpHeaderDirectory = null;
            if (g != 16706) {
                if (g == 17225 || g == 18755 || g == 19778 || g == 20547 || g == 21584) {
                    BmpHeaderDirectory bmpHeaderDirectory2 = new BmpHeaderDirectory();
                    try {
                        metadata.a(bmpHeaderDirectory2);
                        bmpHeaderDirectory2.a(-2, g);
                        sequentialReader.a(12);
                        a(sequentialReader, bmpHeaderDirectory2, metadata);
                    } catch (IOException unused) {
                        bmpHeaderDirectory = bmpHeaderDirectory2;
                    }
                } else {
                    try {
                        metadata.a(new ErrorDirectory("Invalid BMP magic number 0x" + Integer.toHexString(g)));
                    } catch (IOException unused2) {
                        if (bmpHeaderDirectory == null) {
                            a("Unable to read BMP file header", metadata);
                        } else {
                            bmpHeaderDirectory.a("Unable to read BMP file header");
                        }
                    }
                }
            } else if (!z) {
                a("Invalid bitmap file - nested arrays not allowed", metadata);
            } else {
                sequentialReader.a(4);
                long i = sequentialReader.i();
                sequentialReader.a(4);
                a(sequentialReader, metadata, false);
                if (i != 0) {
                    if (sequentialReader.a() > i) {
                        a("Invalid next header offset", metadata);
                        return;
                    }
                    sequentialReader.a(i - sequentialReader.a());
                    a(sequentialReader, metadata, true);
                }
            }
        } catch (IOException e2) {
            metadata.a(new ErrorDirectory("Couldn't determine bitmap type: " + e2.getMessage()));
        }
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialReader sequentialReader, @NotNull BmpHeaderDirectory bmpHeaderDirectory, @NotNull Metadata metadata) {
        SequentialReader sequentialReader2 = sequentialReader;
        BmpHeaderDirectory bmpHeaderDirectory2 = bmpHeaderDirectory;
        try {
            int b2 = bmpHeaderDirectory2.b(-2);
            long a2 = sequentialReader.a();
            int j = sequentialReader.j();
            bmpHeaderDirectory2.a(-1, j);
            if (j == 12 && b2 == 19778) {
                bmpHeaderDirectory2.a(2, (int) sequentialReader.h());
                bmpHeaderDirectory2.a(1, (int) sequentialReader.h());
                bmpHeaderDirectory2.a(3, sequentialReader.g());
                bmpHeaderDirectory2.a(4, sequentialReader.g());
            } else if (j == 12) {
                bmpHeaderDirectory2.a(2, sequentialReader.g());
                bmpHeaderDirectory2.a(1, sequentialReader.g());
                bmpHeaderDirectory2.a(3, sequentialReader.g());
                bmpHeaderDirectory2.a(4, sequentialReader.g());
            } else {
                if (j != 16) {
                    if (j != 64) {
                        if (!(j == 40 || j == 52 || j == 56 || j == 108)) {
                            if (j != 124) {
                                bmpHeaderDirectory2.a("Unexpected DIB header size: " + j);
                                return;
                            }
                        }
                        bmpHeaderDirectory2.a(2, sequentialReader.j());
                        bmpHeaderDirectory2.a(1, sequentialReader.j());
                        bmpHeaderDirectory2.a(3, sequentialReader.g());
                        bmpHeaderDirectory2.a(4, sequentialReader.g());
                        bmpHeaderDirectory2.a(5, sequentialReader.j());
                        sequentialReader2.a(4);
                        bmpHeaderDirectory2.a(6, sequentialReader.j());
                        bmpHeaderDirectory2.a(7, sequentialReader.j());
                        bmpHeaderDirectory2.a(8, sequentialReader.j());
                        bmpHeaderDirectory2.a(9, sequentialReader.j());
                        if (j != 40) {
                            bmpHeaderDirectory2.a(12, sequentialReader.i());
                            bmpHeaderDirectory2.a(13, sequentialReader.i());
                            bmpHeaderDirectory2.a(14, sequentialReader.i());
                            if (j != 52) {
                                bmpHeaderDirectory2.a(15, sequentialReader.i());
                                if (j != 56) {
                                    long i = sequentialReader.i();
                                    bmpHeaderDirectory2.a(16, i);
                                    sequentialReader2.a(36);
                                    bmpHeaderDirectory2.a(17, sequentialReader.i());
                                    bmpHeaderDirectory2.a(18, sequentialReader.i());
                                    bmpHeaderDirectory2.a(19, sequentialReader.i());
                                    if (j != 108) {
                                        bmpHeaderDirectory2.a(20, sequentialReader.j());
                                        if (i != BmpHeaderDirectory.ColorSpaceType.PROFILE_EMBEDDED.getValue()) {
                                            if (i != BmpHeaderDirectory.ColorSpaceType.PROFILE_LINKED.getValue()) {
                                                sequentialReader2.a(12);
                                                return;
                                            }
                                        }
                                        long i2 = sequentialReader.i();
                                        int j2 = sequentialReader.j();
                                        long j3 = a2 + i2;
                                        if (sequentialReader.a() > j3) {
                                            bmpHeaderDirectory2.a("Invalid profile data offset 0x" + Long.toHexString(j3));
                                            return;
                                        }
                                        sequentialReader2.a(j3 - sequentialReader.a());
                                        if (i == BmpHeaderDirectory.ColorSpaceType.PROFILE_LINKED.getValue()) {
                                            bmpHeaderDirectory2.a(21, sequentialReader2.c(j2, Charsets.g));
                                            return;
                                        }
                                        new IccReader().a((RandomAccessReader) new ByteArrayReader(sequentialReader2.a(j2)), metadata, (Directory) bmpHeaderDirectory2);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
                bmpHeaderDirectory2.a(2, sequentialReader.j());
                bmpHeaderDirectory2.a(1, sequentialReader.j());
                bmpHeaderDirectory2.a(3, sequentialReader.g());
                bmpHeaderDirectory2.a(4, sequentialReader.g());
                if (j > 16) {
                    bmpHeaderDirectory2.a(5, sequentialReader.j());
                    sequentialReader2.a(4);
                    bmpHeaderDirectory2.a(6, sequentialReader.j());
                    bmpHeaderDirectory2.a(7, sequentialReader.j());
                    bmpHeaderDirectory2.a(8, sequentialReader.j());
                    bmpHeaderDirectory2.a(9, sequentialReader.j());
                    sequentialReader2.a(6);
                    bmpHeaderDirectory2.a(10, sequentialReader.g());
                    sequentialReader2.a(8);
                    bmpHeaderDirectory2.a(11, sequentialReader.j());
                    sequentialReader2.a(4);
                }
            }
        } catch (IOException unused) {
            bmpHeaderDirectory2.a("Unable to read BMP header");
        } catch (MetadataException unused2) {
            bmpHeaderDirectory2.a("Internal error");
        }
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull String str, @NotNull Metadata metadata) {
        ErrorDirectory errorDirectory = (ErrorDirectory) metadata.b(ErrorDirectory.class);
        if (errorDirectory == null) {
            metadata.a(new ErrorDirectory(str));
        } else {
            errorDirectory.a(str);
        }
    }
}
