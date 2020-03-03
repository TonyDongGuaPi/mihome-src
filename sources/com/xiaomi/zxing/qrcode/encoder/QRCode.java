package com.xiaomi.zxing.qrcode.encoder;

import com.xiaomi.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiaomi.zxing.qrcode.decoder.Mode;
import com.xiaomi.zxing.qrcode.decoder.Version;

public final class QRCode {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1778a = 8;
    private Mode b;
    private ErrorCorrectionLevel c;
    private Version d;
    private int e = -1;
    private ByteMatrix f;

    public static boolean b(int i) {
        return i >= 0 && i < 8;
    }

    public Mode a() {
        return this.b;
    }

    public ErrorCorrectionLevel b() {
        return this.c;
    }

    public Version c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public ByteMatrix e() {
        return this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.b);
        sb.append("\n ecLevel: ");
        sb.append(this.c);
        sb.append("\n version: ");
        sb.append(this.d);
        sb.append("\n maskPattern: ");
        sb.append(this.e);
        if (this.f == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    public void a(Mode mode) {
        this.b = mode;
    }

    public void a(ErrorCorrectionLevel errorCorrectionLevel) {
        this.c = errorCorrectionLevel;
    }

    public void a(Version version) {
        this.d = version;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(ByteMatrix byteMatrix) {
        this.f = byteMatrix;
    }
}
