package com.xiaomi.aiot.mibeacon.distance;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.aiot.mibeacon.logging.LogManager;
import java.util.ArrayList;
import java.util.List;

public class StrictAverageDistanceCalculator implements DistanceCalculator {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9979a = "StrictCalculator";
    private DistanceCalculator b;
    private List<Double> c;
    private int d;
    private int e = 0;
    private int f = 0;

    public StrictAverageDistanceCalculator(int i, DistanceCalculator distanceCalculator) {
        this.d = i;
        this.c = new ArrayList(this.d);
        this.b = distanceCalculator;
    }

    public double a(int i, double d2) {
        a(d2);
        a();
        if (this.c.size() < this.d) {
            LogManager.b(f9979a, "当前采样量不足,返回200", new Object[0]);
            this.c.add(Double.valueOf(d2));
            return 200.0d;
        }
        return this.b.a(i, e());
    }

    private void a() {
        StringBuilder sb = new StringBuilder();
        sb.append("all data: ");
        for (Double doubleValue : this.c) {
            sb.append(doubleValue.doubleValue());
            sb.append(" ");
        }
        sb.append("maxIndex[");
        sb.append(this.e);
        sb.append(Operators.ARRAY_END_STR);
        sb.append(" ");
        sb.append("minIndex[");
        sb.append(this.f);
        sb.append(Operators.ARRAY_END_STR);
        LogManager.b(f9979a, sb.toString(), new Object[0]);
    }

    private void a(double d2) {
        if (this.c.size() < this.d) {
            this.c.add(Double.valueOf(d2));
            if (this.c.get(this.e).doubleValue() < d2) {
                this.e = this.c.size() - 1;
            }
            if (this.c.get(this.f).doubleValue() > d2) {
                this.f = this.c.size() - 1;
                return;
            }
            return;
        }
        this.c.remove(0);
        this.c.add(Double.valueOf(d2));
        b();
        c();
    }

    private void b() {
        int i = 0;
        int i2 = 0;
        for (Double doubleValue : this.c) {
            if (this.c.get(i).doubleValue() < doubleValue.doubleValue()) {
                i = i2;
            }
            i2++;
        }
        this.e = i;
    }

    private void c() {
        int i = 0;
        int i2 = 0;
        for (Double doubleValue : this.c) {
            if (this.c.get(i).doubleValue() > doubleValue.doubleValue()) {
                i = i2;
            }
            i2++;
        }
        this.f = i;
    }

    private void d() {
        double doubleValue = this.c.get(0).doubleValue();
        double doubleValue2 = this.c.get(0).doubleValue();
        for (Double doubleValue3 : this.c) {
            double doubleValue4 = doubleValue3.doubleValue();
            if (doubleValue4 > doubleValue2) {
                doubleValue2 = doubleValue4;
            }
            if (doubleValue4 < doubleValue) {
                doubleValue = doubleValue4;
            }
        }
        this.c.remove(0);
        this.c.remove(0);
    }

    private double e() {
        double d2 = 0.0d;
        int i = -1;
        for (Double doubleValue : this.c) {
            double doubleValue2 = doubleValue.doubleValue();
            i++;
            if (!(i == this.e || i == this.f)) {
                d2 += doubleValue2;
            }
        }
        double size = (double) (this.c.size() - 2);
        Double.isNaN(size);
        return d2 / size;
    }
}
