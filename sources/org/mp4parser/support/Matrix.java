package org.mp4parser.support;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class Matrix {

    /* renamed from: a  reason: collision with root package name */
    public static final Matrix f4105a = new Matrix(1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final Matrix b = new Matrix(0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final Matrix c = new Matrix(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final Matrix d = new Matrix(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    double e;
    double f;
    double g;
    double h;
    double i;
    double j;
    double k;
    double l;
    double m;

    public Matrix(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        this.e = d6;
        this.f = d7;
        this.g = d8;
        this.h = d2;
        this.i = d3;
        this.j = d4;
        this.k = d5;
        this.l = d9;
        this.m = d10;
    }

    public static Matrix a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        return new Matrix(d2, d3, d5, d6, d4, d7, d10, d8, d9);
    }

    public static Matrix a(ByteBuffer byteBuffer) {
        return a(IsoTypeReader.i(byteBuffer), IsoTypeReader.i(byteBuffer), IsoTypeReader.j(byteBuffer), IsoTypeReader.i(byteBuffer), IsoTypeReader.i(byteBuffer), IsoTypeReader.j(byteBuffer), IsoTypeReader.i(byteBuffer), IsoTypeReader.i(byteBuffer), IsoTypeReader.j(byteBuffer));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) obj;
        return Double.compare(matrix.h, this.h) == 0 && Double.compare(matrix.i, this.i) == 0 && Double.compare(matrix.j, this.j) == 0 && Double.compare(matrix.k, this.k) == 0 && Double.compare(matrix.l, this.l) == 0 && Double.compare(matrix.m, this.m) == 0 && Double.compare(matrix.e, this.e) == 0 && Double.compare(matrix.f, this.f) == 0 && Double.compare(matrix.g, this.g) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.e);
        long doubleToLongBits2 = Double.doubleToLongBits(this.f);
        long doubleToLongBits3 = Double.doubleToLongBits(this.g);
        long doubleToLongBits4 = Double.doubleToLongBits(this.h);
        long doubleToLongBits5 = Double.doubleToLongBits(this.i);
        long doubleToLongBits6 = Double.doubleToLongBits(this.j);
        long doubleToLongBits7 = Double.doubleToLongBits(this.k);
        long doubleToLongBits8 = Double.doubleToLongBits(this.l);
        long doubleToLongBits9 = Double.doubleToLongBits(this.m);
        return (((((((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + ((int) (doubleToLongBits7 ^ (doubleToLongBits7 >>> 32)))) * 31) + ((int) (doubleToLongBits8 ^ (doubleToLongBits8 >>> 32)))) * 31) + ((int) ((doubleToLongBits9 >>> 32) ^ doubleToLongBits9));
    }

    public String toString() {
        if (equals(f4105a)) {
            return "Rotate 0°";
        }
        if (equals(b)) {
            return "Rotate 90°";
        }
        if (equals(c)) {
            return "Rotate 180°";
        }
        if (equals(d)) {
            return "Rotate 270°";
        }
        return "Matrix{u=" + this.e + ", v=" + this.f + ", w=" + this.g + ", a=" + this.h + ", b=" + this.i + ", c=" + this.j + ", d=" + this.k + ", tx=" + this.l + ", ty=" + this.m + Operators.BLOCK_END;
    }

    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.a(byteBuffer, this.h);
        IsoTypeWriter.a(byteBuffer, this.i);
        IsoTypeWriter.b(byteBuffer, this.e);
        IsoTypeWriter.a(byteBuffer, this.j);
        IsoTypeWriter.a(byteBuffer, this.k);
        IsoTypeWriter.b(byteBuffer, this.f);
        IsoTypeWriter.a(byteBuffer, this.l);
        IsoTypeWriter.a(byteBuffer, this.m);
        IsoTypeWriter.b(byteBuffer, this.g);
    }
}
