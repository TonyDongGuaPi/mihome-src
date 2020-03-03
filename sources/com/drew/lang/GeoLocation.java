package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.text.DecimalFormat;

public final class GeoLocation {

    /* renamed from: a  reason: collision with root package name */
    private final double f5196a;
    private final double b;

    public GeoLocation(double d, double d2) {
        this.f5196a = d;
        this.b = d2;
    }

    public double a() {
        return this.f5196a;
    }

    public double b() {
        return this.b;
    }

    public boolean c() {
        return this.f5196a == 0.0d && this.b == 0.0d;
    }

    @NotNull
    public static String a(double d) {
        double[] b2 = b(d);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return String.format("%s° %s' %s\"", new Object[]{decimalFormat.format(b2[0]), decimalFormat.format(b2[1]), decimalFormat.format(b2[2])});
    }

    @NotNull
    public static double[] b(double d) {
        int i = (int) d;
        double abs = Math.abs((d % 1.0d) * 60.0d);
        return new double[]{(double) i, (double) ((int) abs), (abs % 1.0d) * 60.0d};
    }

    @Nullable
    public static Double a(@NotNull Rational rational, @NotNull Rational rational2, @NotNull Rational rational3, boolean z) {
        double abs = Math.abs(rational.doubleValue()) + (rational2.doubleValue() / 60.0d) + (rational3.doubleValue() / 3600.0d);
        if (Double.isNaN(abs)) {
            return null;
        }
        if (z) {
            abs *= -1.0d;
        }
        return Double.valueOf(abs);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoLocation geoLocation = (GeoLocation) obj;
        return Double.compare(geoLocation.f5196a, this.f5196a) == 0 && Double.compare(geoLocation.b, this.b) == 0;
    }

    public int hashCode() {
        long j = 0;
        long doubleToLongBits = this.f5196a != 0.0d ? Double.doubleToLongBits(this.f5196a) : 0;
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        if (this.b != 0.0d) {
            j = Double.doubleToLongBits(this.b);
        }
        return (i * 31) + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        return this.f5196a + ", " + this.b;
    }

    @NotNull
    public String d() {
        return a(this.f5196a) + ", " + a(this.b);
    }
}
