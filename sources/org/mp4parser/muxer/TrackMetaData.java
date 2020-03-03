package org.mp4parser.muxer;

import java.util.Date;
import org.mp4parser.support.Matrix;

public class TrackMetaData implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    int f3990a;
    private String b = "eng";
    private long c;
    private Date d = new Date();
    private Date e = new Date();
    private Matrix f = Matrix.f4105a;
    private double g;
    private double h;
    private float i;
    private long j = 1;
    private int k = 0;

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public long b() {
        return this.c;
    }

    public void a(long j2) {
        this.c = j2;
    }

    public Date c() {
        return this.d;
    }

    public void a(Date date) {
        this.d = date;
    }

    public Date d() {
        return this.e;
    }

    public void b(Date date) {
        this.e = date;
    }

    public double e() {
        return this.g;
    }

    public void a(double d2) {
        this.g = d2;
    }

    public double f() {
        return this.h;
    }

    public void b(double d2) {
        this.h = d2;
    }

    public long g() {
        return this.j;
    }

    public void b(long j2) {
        this.j = j2;
    }

    public int h() {
        return this.f3990a;
    }

    public void a(int i2) {
        this.f3990a = i2;
    }

    public float i() {
        return this.i;
    }

    public void a(float f2) {
        this.i = f2;
    }

    public int j() {
        return this.k;
    }

    public void b(int i2) {
        this.k = i2;
    }

    public Matrix k() {
        return this.f;
    }

    public void a(Matrix matrix) {
        this.f = matrix;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
