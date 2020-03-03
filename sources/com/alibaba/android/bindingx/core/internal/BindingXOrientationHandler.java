package com.alibaba.android.bindingx.core.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.alibaba.android.bindingx.core.internal.OrientationDetector;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BindingXOrientationHandler extends AbstractEventHandler implements OrientationDetector.OnOrientationChangedListener {
    private boolean k = false;
    private double l;
    private double m;
    private double n;
    private double o;
    private double p;
    private double q;
    private OrientationDetector r;
    private OrientationEvaluator s;
    private OrientationEvaluator t;
    private OrientationEvaluator u;
    private String v;
    private LinkedList<Double> w = new LinkedList<>();
    private Vector3 x = new Vector3(0.0d, 0.0d, 1.0d);
    private Vector3 y = new Vector3(0.0d, 1.0d, 1.0d);
    private ValueHolder z = new ValueHolder(0.0d, 0.0d, 0.0d);

    public void b(@NonNull String str, @NonNull String str2) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BindingXOrientationHandler(Context context, PlatformManager platformManager, Object... objArr) {
        super(context, platformManager, objArr);
        if (context != null) {
            this.r = OrientationDetector.a(context);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    BindingXOrientationHandler(Context context, PlatformManager platformManager, OrientationDetector orientationDetector, Object... objArr) {
        super(context, platformManager, objArr);
        this.r = orientationDetector;
    }

    public boolean a(@NonNull String str, @NonNull String str2) {
        if (this.r == null) {
            return false;
        }
        this.r.a((OrientationDetector.OnOrientationChangedListener) this);
        return this.r.a(1);
    }

    public void a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable ExpressionPair expressionPair, @NonNull List<Map<String, Object>> list, @Nullable BindingXCore.JavaScriptCallback javaScriptCallback) {
        String str2;
        super.a(str, map, expressionPair, list, javaScriptCallback);
        if (map != null) {
            String str3 = (String) map.get(BindingXConstants.u);
            str2 = TextUtils.isEmpty(str3) ? "2d" : str3.toLowerCase();
        } else {
            str2 = null;
        }
        if (TextUtils.isEmpty(str2) || (!"2d".equals(str2) && !"3d".equals(str2))) {
            str2 = "2d";
        }
        this.v = str2;
        LogProxy.c("[ExpressionOrientationHandler] sceneType is " + str2);
        if ("2d".equals(str2)) {
            this.s = new OrientationEvaluator((Double) null, Double.valueOf(90.0d), (Double) null);
            this.t = new OrientationEvaluator(Double.valueOf(0.0d), (Double) null, Double.valueOf(90.0d));
        } else if ("3d".equals(str2)) {
            this.u = new OrientationEvaluator((Double) null, (Double) null, (Double) null);
        }
    }

    public boolean c(@NonNull String str, @NonNull String str2) {
        d();
        if (this.r == null) {
            return false;
        }
        a("end", this.o, this.p, this.q, new Object[0]);
        return this.r.b((OrientationDetector.OnOrientationChangedListener) this);
    }

    public void a() {
        super.a();
        if (this.r != null) {
            this.r.b((OrientationDetector.OnOrientationChangedListener) this);
            this.r.a();
        }
        if (this.f749a != null) {
            this.f749a.clear();
            this.f749a = null;
        }
    }

    public void a(double d, double d2, double d3) {
        char c;
        double d4;
        boolean z2;
        double round = (double) Math.round(d);
        double round2 = (double) Math.round(d2);
        double round3 = (double) Math.round(d3);
        if (round != this.o || round2 != this.p || round3 != this.q) {
            if (!this.k) {
                this.k = true;
                c = 0;
                a("start", round, round2, round3, new Object[0]);
                this.l = round;
                this.m = round2;
                d4 = round3;
                this.n = d4;
            } else {
                d4 = round3;
                c = 0;
            }
            if ("2d".equals(this.v)) {
                z2 = b(round, round2, d4);
            } else {
                z2 = "3d".equals(this.v) ? c(round, round2, d4) : false;
            }
            if (z2) {
                double d5 = this.z.f753a;
                double d6 = this.z.b;
                double d7 = this.z.c;
                this.o = round;
                this.p = round2;
                this.q = d4;
                try {
                    if (LogProxy.f745a) {
                        Locale locale = Locale.getDefault();
                        Object[] objArr = new Object[6];
                        objArr[c] = Double.valueOf(round);
                        objArr[1] = Double.valueOf(round2);
                        objArr[2] = Double.valueOf(d4);
                        objArr[3] = Double.valueOf(d5);
                        objArr[4] = Double.valueOf(d6);
                        objArr[5] = Double.valueOf(d7);
                        LogProxy.c(String.format(locale, "[OrientationHandler] orientation changed. (alpha:%f,beta:%f,gamma:%f,x:%f,y:%f,z:%f)", objArr));
                    }
                    JSMath.a(this.d, round, round2, d4, this.l, this.m, this.n, d5, d6, d7);
                    if (!a(this.j, (Map<String, Object>) this.d)) {
                        a((Map<String, List<ExpressionHolder>>) this.f749a, (Map<String, Object>) this.d, "orientation");
                    }
                } catch (Exception e) {
                    LogProxy.e("runtime error", e);
                }
            }
        }
    }

    private boolean b(double d, double d2, double d3) {
        if (!(this.s == null || this.t == null)) {
            this.w.add(Double.valueOf(d));
            if (this.w.size() > 5) {
                this.w.removeFirst();
            }
            a((List<Double>) this.w, 360);
            double d4 = d;
            double d5 = d2;
            double d6 = d3;
            double doubleValue = (this.w.get(this.w.size() - 1).doubleValue() - this.l) % 360.0d;
            Quaternion a2 = this.s.a(d4, d5, d6, doubleValue);
            Quaternion a3 = this.t.a(d4, d5, d6, doubleValue);
            this.x.a(0.0d, 0.0d, 1.0d);
            this.x.a(a2);
            this.y.a(0.0d, 1.0d, 1.0d);
            this.y.a(a3);
            double degrees = Math.toDegrees(Math.acos(this.x.f766a)) - 90.0d;
            double degrees2 = Math.toDegrees(Math.acos(this.y.b)) - 90.0d;
            if (Double.isNaN(degrees) || Double.isNaN(degrees2) || Double.isInfinite(degrees) || Double.isInfinite(degrees2)) {
                return false;
            }
            this.z.f753a = (double) Math.round(degrees);
            this.z.b = (double) Math.round(degrees2);
        }
        return true;
    }

    private boolean c(double d, double d2, double d3) {
        if (this.u != null) {
            this.w.add(Double.valueOf(d));
            if (this.w.size() > 5) {
                this.w.removeFirst();
            }
            a((List<Double>) this.w, 360);
            Quaternion a2 = this.u.a(d, d2, d3, (this.w.get(this.w.size() - 1).doubleValue() - this.l) % 360.0d);
            if (Double.isNaN(a2.f761a) || Double.isNaN(a2.b) || Double.isNaN(a2.c) || Double.isInfinite(a2.f761a) || Double.isInfinite(a2.b) || Double.isInfinite(a2.c)) {
                return false;
            }
            this.z.f753a = a2.f761a;
            this.z.b = a2.b;
            this.z.c = a2.c;
        }
        return true;
    }

    private void a(List<Double> list, int i) {
        int size = list.size();
        if (size > 1) {
            for (int i2 = 1; i2 < size; i2++) {
                int i3 = i2 - 1;
                if (!(list.get(i3) == null || list.get(i2) == null)) {
                    if (list.get(i2).doubleValue() - list.get(i3).doubleValue() < ((double) ((-i) / 2))) {
                        double doubleValue = list.get(i3).doubleValue();
                        double d = (double) i;
                        Double.isNaN(d);
                        double doubleValue2 = list.get(i2).doubleValue();
                        Double.isNaN(d);
                        list.set(i2, Double.valueOf(doubleValue2 + ((Math.floor(doubleValue / d) + 1.0d) * d)));
                    }
                    if (list.get(i2).doubleValue() - list.get(i3).doubleValue() > ((double) (i / 2))) {
                        double doubleValue3 = list.get(i2).doubleValue();
                        double d2 = (double) i;
                        Double.isNaN(d2);
                        list.set(i2, Double.valueOf(doubleValue3 - d2));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(@NonNull Map<String, Object> map) {
        a("exit", ((Double) map.get("alpha")).doubleValue(), ((Double) map.get("beta")).doubleValue(), ((Double) map.get("gamma")).doubleValue(), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void a(String str, @NonNull Map<String, Object> map) {
        a(BindingXConstants.h, ((Double) map.get("alpha")).doubleValue(), ((Double) map.get("beta")).doubleValue(), ((Double) map.get("gamma")).doubleValue(), Collections.singletonMap(BindingXConstants.h, str));
    }

    private void a(String str, double d, double d2, double d3, Object... objArr) {
        if (this.c != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("state", str);
            hashMap.put("alpha", Double.valueOf(d));
            hashMap.put("beta", Double.valueOf(d2));
            hashMap.put("gamma", Double.valueOf(d3));
            hashMap.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                hashMap.putAll(objArr[0]);
            }
            this.c.a(hashMap);
            LogProxy.c(">>>>>>>>>>>fire event:(" + str + "," + d + "," + d2 + "," + d3 + Operators.BRACKET_END_STR);
        }
    }

    public void b() {
        if (this.r != null) {
            this.r.a();
        }
    }

    public void c() {
        if (this.r != null) {
            this.r.a(1);
        }
    }

    static class ValueHolder {

        /* renamed from: a  reason: collision with root package name */
        double f753a;
        double b;
        double c;

        ValueHolder() {
        }

        ValueHolder(double d, double d2, double d3) {
            this.f753a = d;
            this.b = d2;
            this.c = d3;
        }
    }
}
