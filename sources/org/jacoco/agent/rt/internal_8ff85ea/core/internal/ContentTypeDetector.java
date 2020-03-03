package org.jacoco.agent.rt.internal_8ff85ea.core.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ContentTypeDetector {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3617a = -1;
    public static final int b = -889275714;
    public static final int c = 1347093252;
    public static final int d = 529203200;
    public static final int e = -889270259;
    private static final int f = 8;
    private final InputStream g;
    private final int h;

    public ContentTypeDetector(InputStream inputStream) throws IOException {
        if (inputStream.markSupported()) {
            this.g = inputStream;
        } else {
            this.g = new BufferedInputStream(inputStream, 8);
        }
        this.g.mark(8);
        this.h = a(this.g);
        this.g.reset();
    }

    private static int a(InputStream inputStream) throws IOException {
        int b2 = b(inputStream);
        if (b2 == -889275714) {
            int b3 = b(inputStream);
            if (b3 != 196653) {
                switch (b3) {
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                        break;
                }
            }
            return b;
        } else if (b2 == -889270259) {
            return e;
        } else {
            if (b2 == 1347093252) {
                return c;
            }
        }
        if ((-65536 & b2) == 529203200) {
            return d;
        }
        return -1;
    }

    private static int b(InputStream inputStream) throws IOException {
        return inputStream.read() | (inputStream.read() << 24) | (inputStream.read() << 16) | (inputStream.read() << 8);
    }

    public InputStream a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }
}
