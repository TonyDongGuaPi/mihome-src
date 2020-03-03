package org.apache.commons.compress.archivers.arj;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

class LocalFileHeader {

    /* renamed from: a  reason: collision with root package name */
    int f3204a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    long i;
    long j;
    long k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    String t;
    String u;
    byte[][] v = null;

    LocalFileHeader() {
    }

    static class Flags {

        /* renamed from: a  reason: collision with root package name */
        static final int f3206a = 1;
        static final int b = 4;
        static final int c = 8;
        static final int d = 16;
        static final int e = 32;

        Flags() {
        }
    }

    static class FileTypes {

        /* renamed from: a  reason: collision with root package name */
        static final int f3205a = 0;
        static final int b = 1;
        static final int c = 3;
        static final int d = 4;
        static final int e = 5;

        FileTypes() {
        }
    }

    static class Methods {

        /* renamed from: a  reason: collision with root package name */
        static final int f3207a = 0;
        static final int b = 1;
        static final int c = 4;
        static final int d = 8;
        static final int e = 9;

        Methods() {
        }
    }

    public String toString() {
        return "LocalFileHeader [archiverVersionNumber=" + this.f3204a + ", minVersionToExtract=" + this.b + ", hostOS=" + this.c + ", arjFlags=" + this.d + ", method=" + this.e + ", fileType=" + this.f + ", reserved=" + this.g + ", dateTimeModified=" + this.h + ", compressedSize=" + this.i + ", originalSize=" + this.j + ", originalCrc32=" + this.k + ", fileSpecPosition=" + this.l + ", fileAccessMode=" + this.m + ", firstChapter=" + this.n + ", lastChapter=" + this.o + ", extendedFilePosition=" + this.p + ", dateTimeAccessed=" + this.q + ", dateTimeCreated=" + this.r + ", originalSizeEvenForVolumes=" + this.s + ", name=" + this.t + ", comment=" + this.u + ", extendedHeaders=" + Arrays.toString(this.v) + Operators.ARRAY_END_STR;
    }
}
