package com.drew.metadata.webp;

import com.drew.imaging.riff.RiffHandler;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.IOException;

public class WebpRiffHandler implements RiffHandler {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final Metadata f5268a;

    public boolean c(@NotNull String str) {
        return false;
    }

    public WebpRiffHandler(@NotNull Metadata metadata) {
        this.f5268a = metadata;
    }

    public boolean a(@NotNull String str) {
        return str.equals(WebpDirectory.o);
    }

    public boolean b(@NotNull String str) {
        return str.equals(WebpDirectory.i) || str.equals(WebpDirectory.j) || str.equals(WebpDirectory.k) || str.equals(WebpDirectory.l) || str.equals(WebpDirectory.m) || str.equals(WebpDirectory.n);
    }

    public void a(@NotNull String str, @NotNull byte[] bArr) {
        WebpDirectory webpDirectory = new WebpDirectory();
        if (str.equals(WebpDirectory.l)) {
            new ExifReader().a(new ByteArrayReader(bArr), this.f5268a);
        } else if (str.equals(WebpDirectory.m)) {
            new IccReader().a(new ByteArrayReader(bArr), this.f5268a);
        } else if (str.equals(WebpDirectory.n)) {
            new XmpReader().a(bArr, this.f5268a);
        } else if (str.equals(WebpDirectory.i) && bArr.length == 10) {
            ByteArrayReader byteArrayReader = new ByteArrayReader(bArr);
            byteArrayReader.a(false);
            try {
                boolean c = byteArrayReader.c(1);
                boolean c2 = byteArrayReader.c(4);
                int h = byteArrayReader.h(4);
                int h2 = byteArrayReader.h(7);
                webpDirectory.a(2, h + 1);
                webpDirectory.a(1, h2 + 1);
                webpDirectory.a(3, c2);
                webpDirectory.a(4, c);
                this.f5268a.a(webpDirectory);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        } else if (str.equals(WebpDirectory.j) && bArr.length > 4) {
            ByteArrayReader byteArrayReader2 = new ByteArrayReader(bArr);
            byteArrayReader2.a(false);
            try {
                if (byteArrayReader2.e(0) == 47) {
                    short d = byteArrayReader2.d(1);
                    short d2 = byteArrayReader2.d(2);
                    short d3 = byteArrayReader2.d(3);
                    webpDirectory.a(2, (d | ((d2 & 63) << 8)) + 1);
                    webpDirectory.a(1, (((byteArrayReader2.d(4) & 15) << 10) | (d3 << 2) | ((d2 & 192) >> 6)) + 1);
                    this.f5268a.a(webpDirectory);
                }
            } catch (IOException e2) {
                e2.printStackTrace(System.err);
            }
        } else if (str.equals(WebpDirectory.k) && bArr.length > 9) {
            ByteArrayReader byteArrayReader3 = new ByteArrayReader(bArr);
            byteArrayReader3.a(false);
            try {
                if (byteArrayReader3.d(3) != 157 || byteArrayReader3.d(4) != 1) {
                    return;
                }
                if (byteArrayReader3.d(5) == 42) {
                    int f = byteArrayReader3.f(6);
                    int f2 = byteArrayReader3.f(8);
                    webpDirectory.a(2, f);
                    webpDirectory.a(1, f2);
                    this.f5268a.a(webpDirectory);
                }
            } catch (IOException e3) {
                webpDirectory.a(e3.getMessage());
            }
        }
    }
}
