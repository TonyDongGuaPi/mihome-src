package com.drew.metadata.avi;

import com.drew.imaging.riff.RiffHandler;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;

public class AviRiffHandler implements RiffHandler {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final AviDirectory f5213a = new AviDirectory();

    public AviRiffHandler(@NotNull Metadata metadata) {
        metadata.a(this.f5213a);
    }

    public boolean a(@NotNull String str) {
        return str.equals(AviDirectory.q);
    }

    public boolean b(@NotNull String str) {
        return str.equals(AviDirectory.m) || str.equals(AviDirectory.n);
    }

    public boolean c(@NotNull String str) {
        return str.equals(AviDirectory.o) || str.equals(AviDirectory.p) || str.equals(AviDirectory.q);
    }

    public void a(@NotNull String str, @NotNull byte[] bArr) {
        try {
            if (str.equals(AviDirectory.m)) {
                ByteArrayReader byteArrayReader = new ByteArrayReader(bArr);
                byteArrayReader.a(false);
                String str2 = new String(byteArrayReader.c(0, 4));
                String str3 = new String(byteArrayReader.c(4, 4));
                float m = byteArrayReader.m(20);
                float m2 = byteArrayReader.m(24);
                int j = byteArrayReader.j(32);
                if (str2.equals("vids")) {
                    if (!this.f5213a.a(1)) {
                        float f = m2 / m;
                        this.f5213a.a(1, (double) f);
                        double d = (double) (((float) j) / f);
                        int i = (int) d;
                        Integer valueOf = Integer.valueOf(i / ((int) Math.pow(60.0d, 2.0d)));
                        Integer valueOf2 = Integer.valueOf((i / ((int) Math.pow(60.0d, 1.0d))) - (valueOf.intValue() * 60));
                        double pow = Math.pow(60.0d, 0.0d);
                        Double.isNaN(d);
                        double d2 = d / pow;
                        double intValue = (double) (valueOf2.intValue() * 60);
                        Double.isNaN(intValue);
                        this.f5213a.a(3, String.format("%1$02d:%2$02d:%3$02d", new Object[]{valueOf, valueOf2, Integer.valueOf((int) Math.round(d2 - intValue))}));
                        this.f5213a.a(4, str3);
                    }
                } else if (str2.equals("auds") && !this.f5213a.a(2)) {
                    this.f5213a.a(2, (double) (m2 / m));
                }
            } else if (str.equals(AviDirectory.n)) {
                ByteArrayReader byteArrayReader2 = new ByteArrayReader(bArr);
                byteArrayReader2.a(false);
                int j2 = byteArrayReader2.j(24);
                int j3 = byteArrayReader2.j(32);
                int j4 = byteArrayReader2.j(36);
                this.f5213a.a(6, j3);
                this.f5213a.a(7, j4);
                this.f5213a.a(8, j2);
            }
        } catch (IOException e) {
            this.f5213a.a(e.getMessage());
        }
    }
}
