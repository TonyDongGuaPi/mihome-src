package org.apache.commons.compress.archivers.arj;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

class MainHeader {

    /* renamed from: a  reason: collision with root package name */
    int f3208a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    long j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    String r;
    String s;
    byte[] t = null;

    MainHeader() {
    }

    static class Flags {

        /* renamed from: a  reason: collision with root package name */
        static final int f3209a = 1;
        static final int b = 2;
        static final int c = 4;
        static final int d = 8;
        static final int e = 16;
        static final int f = 32;
        static final int g = 64;
        static final int h = 128;

        Flags() {
        }
    }

    public String toString() {
        return "MainHeader [archiverVersionNumber=" + this.f3208a + ", minVersionToExtract=" + this.b + ", hostOS=" + this.c + ", arjFlags=" + this.d + ", securityVersion=" + this.e + ", fileType=" + this.f + ", reserved=" + this.g + ", dateTimeCreated=" + this.h + ", dateTimeModified=" + this.i + ", archiveSize=" + this.j + ", securityEnvelopeFilePosition=" + this.k + ", fileSpecPosition=" + this.l + ", securityEnvelopeLength=" + this.m + ", encryptionVersion=" + this.n + ", lastChapter=" + this.o + ", arjProtectionFactor=" + this.p + ", arjFlags2=" + this.q + ", name=" + this.r + ", comment=" + this.s + ", extendedHeaderBytes=" + Arrays.toString(this.t) + Operators.ARRAY_END_STR;
    }
}
