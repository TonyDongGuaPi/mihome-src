package com.alibaba.android.bindingx.core.internal;

import android.support.annotation.Nullable;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import org.json.JSONException;

class TimingFunctions {
    private static Object A = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            double d;
            ArrayList<Object> arrayList2 = arrayList;
            double doubleValue = ((Double) arrayList2.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList2.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList2.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList2.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min == 0.0d) {
                return Double.valueOf(doubleValue2);
            }
            double d2 = min / (doubleValue4 / 2.0d);
            if (d2 == 2.0d) {
                return Double.valueOf(doubleValue2 + doubleValue3);
            }
            double d3 = 0.44999999999999996d * doubleValue4;
            if (doubleValue3 < Math.abs(doubleValue3)) {
                d = d3 / 4.0d;
            } else {
                d = (d3 / 6.283185307179586d) * Math.asin(doubleValue3 / doubleValue3);
            }
            if (d2 < 1.0d) {
                double d4 = d2 - 1.0d;
                return Double.valueOf((doubleValue3 * Math.pow(2.0d, d4 * 10.0d) * Math.sin((((d4 * doubleValue4) - d) * 6.283185307179586d) / d3) * -0.5d) + doubleValue2);
            }
            double d5 = d2 - 1.0d;
            return Double.valueOf((Math.pow(2.0d, -10.0d * d5) * doubleValue3 * Math.sin((((d5 * doubleValue4) - d) * 6.283185307179586d) / d3) * 0.5d) + doubleValue3 + doubleValue2);
        }
    };
    private static Object B = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf((doubleValue3 * min * min * ((2.70158d * min) - 1.70158d)) + doubleValue2);
        }
    };
    private static Object C = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = (Math.min(doubleValue, doubleValue4) / doubleValue4) - 1.0d;
            return Double.valueOf((doubleValue3 * ((min * min * ((2.70158d * min) + 1.70158d)) + 1.0d)) + doubleValue2);
        }
    };
    private static Object D = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / (doubleValue4 / 2.0d);
            if (min < 1.0d) {
                return Double.valueOf(((doubleValue3 / 2.0d) * min * min * ((3.5949095d * min) - 2.5949095d)) + doubleValue2);
            }
            double d = min - 2.0d;
            return Double.valueOf(((doubleValue3 / 2.0d) * ((d * d * ((3.5949095d * d) + 2.5949095d)) + 2.0d)) + doubleValue2);
        }
    };
    private static Object E = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf(TimingFunctions.c(Math.min(doubleValue, doubleValue4), doubleValue2, doubleValue3, doubleValue4));
        }
    };
    private static Object F = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf(TimingFunctions.d(Math.min(doubleValue, doubleValue4), doubleValue2, doubleValue3, doubleValue4));
        }
    };
    private static Object G = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            ArrayList<Object> arrayList2 = arrayList;
            double doubleValue = ((Double) arrayList2.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList2.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList2.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList2.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min < doubleValue4 / 2.0d) {
                return Double.valueOf((TimingFunctions.c(min * 2.0d, 0.0d, doubleValue3, doubleValue4) * 0.5d) + doubleValue2);
            }
            return Double.valueOf((TimingFunctions.d((min * 2.0d) - doubleValue4, 0.0d, doubleValue3, doubleValue4) * 0.5d) + (doubleValue3 * 0.5d) + doubleValue2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static Object f763a = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf((doubleValue3 * (Math.min(doubleValue, doubleValue4) / doubleValue4)) + doubleValue2);
        }
    };
    private static Object b = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            ArrayList<Object> arrayList2 = arrayList;
            double doubleValue = ((Double) arrayList2.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList2.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList2.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList2.get(3)).doubleValue();
            double doubleValue5 = ((Double) arrayList2.get(4)).doubleValue();
            double doubleValue6 = ((Double) arrayList2.get(5)).doubleValue();
            double doubleValue7 = ((Double) arrayList2.get(6)).doubleValue();
            double doubleValue8 = ((Double) arrayList2.get(7)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min == doubleValue4) {
                return Double.valueOf(doubleValue2 + doubleValue3);
            }
            float f = (float) doubleValue5;
            float f2 = (float) doubleValue6;
            float f3 = (float) doubleValue7;
            float f4 = (float) doubleValue8;
            BezierInterpolatorWrapper a2 = TimingFunctions.b(f, f2, f3, f4);
            if (a2 == null) {
                a2 = new BezierInterpolatorWrapper(f, f2, f3, f4);
                TimingFunctions.c.a(a2);
            }
            double interpolation = (double) a2.getInterpolation((float) (min / doubleValue4));
            Double.isNaN(interpolation);
            return Double.valueOf((doubleValue3 * interpolation) + doubleValue2);
        }
    };
    /* access modifiers changed from: private */
    public static final InnerCache<BezierInterpolatorWrapper> c = new InnerCache<>(4);
    private static Object d = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf((doubleValue3 * min * min) + doubleValue2);
        }
    };
    private static Object e = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf(((-doubleValue3) * min * (min - 2.0d)) + doubleValue2);
        }
    };
    private static Object f = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / (doubleValue4 / 2.0d);
            if (min < 1.0d) {
                return Double.valueOf(((doubleValue3 / 2.0d) * min * min) + doubleValue2);
            }
            double d = min - 1.0d;
            return Double.valueOf((((-doubleValue3) / 2.0d) * ((d * (d - 2.0d)) - 1.0d)) + doubleValue2);
        }
    };
    private static Object g = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf((doubleValue3 * min * min * min) + doubleValue2);
        }
    };
    private static Object h = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = (Math.min(doubleValue, doubleValue4) / doubleValue4) - 1.0d;
            return Double.valueOf((doubleValue3 * ((min * min * min) + 1.0d)) + doubleValue2);
        }
    };
    private static Object i = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / (doubleValue4 / 2.0d);
            if (min < 1.0d) {
                return Double.valueOf(((doubleValue3 / 2.0d) * min * min * min) + doubleValue2);
            }
            double d = min - 2.0d;
            return Double.valueOf(((doubleValue3 / 2.0d) * ((d * d * d) + 2.0d)) + doubleValue2);
        }
    };
    private static Object j = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf((doubleValue3 * min * min * min * min) + doubleValue2);
        }
    };
    private static Object k = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = (Math.min(doubleValue, doubleValue4) / doubleValue4) - 1.0d;
            return Double.valueOf(((-doubleValue3) * ((((min * min) * min) * min) - 1.0d)) + doubleValue2);
        }
    };
    private static Object l = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / (doubleValue4 / 2.0d);
            if (min < 1.0d) {
                return Double.valueOf(((doubleValue3 / 2.0d) * min * min * min * min) + doubleValue2);
            }
            double d = min - 2.0d;
            return Double.valueOf((((-doubleValue3) / 2.0d) * ((((d * d) * d) * d) - 2.0d)) + doubleValue2);
        }
    };
    private static Object m = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf((doubleValue3 * min * min * min * min * min) + doubleValue2);
        }
    };
    private static Object n = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = (Math.min(doubleValue, doubleValue4) / doubleValue4) - 1.0d;
            return Double.valueOf((doubleValue3 * ((min * min * min * min * min) + 1.0d)) + doubleValue2);
        }
    };
    private static Object o = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / (doubleValue4 / 2.0d);
            if (min < 1.0d) {
                return Double.valueOf(((doubleValue3 / 2.0d) * min * min * min * min * min) + doubleValue2);
            }
            double d = min - 2.0d;
            return Double.valueOf(((doubleValue3 / 2.0d) * ((d * d * d * d * d) + 2.0d)) + doubleValue2);
        }
    };
    private static Object p = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf(((-doubleValue3) * Math.cos((Math.min(doubleValue, doubleValue4) / doubleValue4) * 1.5707963267948966d)) + doubleValue3 + doubleValue2);
        }
    };
    private static Object q = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf((doubleValue3 * Math.sin((Math.min(doubleValue, doubleValue4) / doubleValue4) * 1.5707963267948966d)) + doubleValue2);
        }
    };
    private static Object r = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf((((-doubleValue3) / 2.0d) * (Math.cos((Math.min(doubleValue, doubleValue4) * 3.141592653589793d) / doubleValue4) - 1.0d)) + doubleValue2);
        }
    };
    private static Object s = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min != 0.0d) {
                doubleValue2 += doubleValue3 * Math.pow(2.0d, ((min / doubleValue4) - 1.0d) * 10.0d);
            }
            return Double.valueOf(doubleValue2);
        }
    };
    private static Object t = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double d;
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min == doubleValue4) {
                d = doubleValue2 + doubleValue3;
            } else {
                d = doubleValue2 + (doubleValue3 * ((-Math.pow(2.0d, (min * -10.0d) / doubleValue4)) + 1.0d));
            }
            return Double.valueOf(d);
        }
    };
    private static Object u = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min == 0.0d) {
                return Double.valueOf(doubleValue2);
            }
            if (min == doubleValue4) {
                return Double.valueOf(doubleValue2 + doubleValue3);
            }
            double d = min / (doubleValue4 / 2.0d);
            if (d < 1.0d) {
                return Double.valueOf(((doubleValue3 / 2.0d) * Math.pow(2.0d, (d - 1.0d) * 10.0d)) + doubleValue2);
            }
            return Double.valueOf(((doubleValue3 / 2.0d) * ((-Math.pow(2.0d, (d - 1.0d) * -10.0d)) + 2.0d)) + doubleValue2);
        }
    };
    private static Object v = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / doubleValue4;
            return Double.valueOf(((-doubleValue3) * (Math.sqrt(1.0d - (min * min)) - 1.0d)) + doubleValue2);
        }
    };
    private static Object w = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = (Math.min(doubleValue, doubleValue4) / doubleValue4) - 1.0d;
            return Double.valueOf((doubleValue3 * Math.sqrt(1.0d - (min * min))) + doubleValue2);
        }
    };
    private static Object x = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4) / (doubleValue4 / 2.0d);
            if (min < 1.0d) {
                return Double.valueOf((((-doubleValue3) / 2.0d) * (Math.sqrt(1.0d - (min * min)) - 1.0d)) + doubleValue2);
            }
            double d = min - 2.0d;
            return Double.valueOf(((doubleValue3 / 2.0d) * (Math.sqrt(1.0d - (d * d)) + 1.0d)) + doubleValue2);
        }
    };
    private static Object y = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            double d;
            ArrayList<Object> arrayList2 = arrayList;
            double doubleValue = ((Double) arrayList2.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList2.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList2.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList2.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min == 0.0d) {
                return Double.valueOf(doubleValue2);
            }
            double d2 = min / doubleValue4;
            if (d2 == 1.0d) {
                return Double.valueOf(doubleValue2 + doubleValue3);
            }
            double d3 = 0.3d * doubleValue4;
            if (doubleValue3 < Math.abs(doubleValue3)) {
                d = d3 / 4.0d;
            } else {
                d = (d3 / 6.283185307179586d) * Math.asin(doubleValue3 / doubleValue3);
            }
            double d4 = d2 - 1.0d;
            return Double.valueOf((-(doubleValue3 * Math.pow(2.0d, d4 * 10.0d) * Math.sin((((d4 * doubleValue4) - d) * 6.283185307179586d) / d3))) + doubleValue2);
        }
    };
    private static Object z = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            double d;
            ArrayList<Object> arrayList2 = arrayList;
            double doubleValue = ((Double) arrayList2.get(0)).doubleValue();
            double doubleValue2 = ((Double) arrayList2.get(1)).doubleValue();
            double doubleValue3 = ((Double) arrayList2.get(2)).doubleValue();
            double doubleValue4 = ((Double) arrayList2.get(3)).doubleValue();
            double min = Math.min(doubleValue, doubleValue4);
            if (min == 0.0d) {
                return Double.valueOf(doubleValue2);
            }
            double d2 = min / doubleValue4;
            if (d2 == 1.0d) {
                return Double.valueOf(doubleValue2 + doubleValue3);
            }
            double d3 = 0.3d * doubleValue4;
            if (doubleValue3 < Math.abs(doubleValue3)) {
                d = d3 / 4.0d;
            } else {
                d = (d3 / 6.283185307179586d) * Math.asin(doubleValue3 / doubleValue3);
            }
            return Double.valueOf((Math.pow(2.0d, d2 * -10.0d) * doubleValue3 * Math.sin((((d2 * doubleValue4) - d) * 6.283185307179586d) / d3)) + doubleValue3 + doubleValue2);
        }
    };

    /* access modifiers changed from: private */
    public static double d(double d2, double d3, double d4, double d5) {
        double d6 = d2 / d5;
        if (d6 < 0.36363636363636365d) {
            return (d4 * 7.5625d * d6 * d6) + d3;
        }
        if (d6 < 0.7272727272727273d) {
            double d7 = d6 - 0.5454545454545454d;
            return (d4 * ((7.5625d * d7 * d7) + 0.75d)) + d3;
        } else if (d6 < 0.9090909090909091d) {
            double d8 = d6 - 0.8181818181818182d;
            return (d4 * ((7.5625d * d8 * d8) + 0.9375d)) + d3;
        } else {
            double d9 = d6 - 0.9545454545454546d;
            return (d4 * ((7.5625d * d9 * d9) + 0.984375d)) + d3;
        }
    }

    private TimingFunctions() {
    }

    static void a(Map<String, Object> map) {
        map.put("linear", f763a);
        map.put("easeInQuad", d);
        map.put("easeOutQuad", e);
        map.put("easeInOutQuad", f);
        map.put("easeInCubic", g);
        map.put("easeOutCubic", h);
        map.put("easeInOutCubic", i);
        map.put("easeInQuart", j);
        map.put("easeOutQuart", k);
        map.put("easeInOutQuart", l);
        map.put("easeInQuint", m);
        map.put("easeOutQuint", n);
        map.put("easeInOutQuint", o);
        map.put("easeInSine", p);
        map.put("easeOutSine", q);
        map.put("easeInOutSine", r);
        map.put("easeInExpo", s);
        map.put("easeOutExpo", t);
        map.put("easeInOutExpo", u);
        map.put("easeInCirc", v);
        map.put("easeOutCirc", w);
        map.put("easeInOutCirc", x);
        map.put("easeInElastic", y);
        map.put("easeOutElastic", z);
        map.put("easeInOutElastic", A);
        map.put("easeInBack", B);
        map.put("easeOutBack", C);
        map.put("easeInOutBack", D);
        map.put("easeInBounce", E);
        map.put("easeOutBounce", F);
        map.put("easeInOutBounce", G);
        map.put("cubicBezier", b);
    }

    /* access modifiers changed from: private */
    @Nullable
    public static BezierInterpolatorWrapper b(float f2, float f3, float f4, float f5) {
        for (BezierInterpolatorWrapper next : c.a()) {
            if (Float.compare(next.f764a, f2) == 0 && Float.compare(next.c, f4) == 0 && Float.compare(next.b, f3) == 0 && Float.compare(next.d, f5) == 0) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static double c(double d2, double d3, double d4, double d5) {
        return (d4 - d(d5 - d2, 0.0d, d4, d5)) + d3;
    }

    private static class InnerCache<T> {

        /* renamed from: a  reason: collision with root package name */
        private final ArrayDeque<T> f765a;

        InnerCache(int i) {
            this.f765a = new ArrayDeque<>(i);
        }

        /* access modifiers changed from: package-private */
        public void a(T t) {
            if (this.f765a.size() >= 4) {
                this.f765a.removeFirst();
                this.f765a.addLast(t);
                return;
            }
            this.f765a.addLast(t);
        }

        /* access modifiers changed from: package-private */
        public Deque<T> a() {
            return this.f765a;
        }
    }

    private static class BezierInterpolatorWrapper implements Interpolator {

        /* renamed from: a  reason: collision with root package name */
        float f764a;
        float b;
        float c;
        float d;
        private Interpolator e;

        BezierInterpolatorWrapper(float f, float f2, float f3, float f4) {
            this.f764a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
            this.e = PathInterpolatorCompat.create(f, f2, f3, f4);
        }

        public float getInterpolation(float f) {
            return this.e.getInterpolation(f);
        }
    }
}
