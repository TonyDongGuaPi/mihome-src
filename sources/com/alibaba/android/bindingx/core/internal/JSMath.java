package com.alibaba.android.bindingx.core.internal;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.support.media.ExifInterface;
import android.text.TextUtils;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.feedback.FeedbackApi;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.json.JSONException;

class JSMath {
    /* access modifiers changed from: private */
    public static ArgbEvaluator A = new ArgbEvaluator();
    private static Object B = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            int a2 = JSMath.b((String) arrayList.get(0));
            int a3 = JSMath.b((String) arrayList.get(1));
            return JSMath.A.evaluate((float) Math.min(1.0d, Math.max(0.0d, ((Double) arrayList.get(2)).doubleValue())), Integer.valueOf(a2), Integer.valueOf(a3));
        }
    };
    private static Object C = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            return arrayList;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static Object f758a = Double.valueOf(2.718281828459045d);
    private static Object b = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.sin(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object c = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.cos(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object d = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.tan(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object e = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.asin(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object f = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.acos(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object g = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.atan(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object h = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.atan2(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue()));
        }
    };
    private static Object i = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.pow(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue()));
        }
    };
    private static Object j = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.exp(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object k = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.sqrt(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object l = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.cbrt(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object m = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.log(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object n = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.abs(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object o = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            if (doubleValue > 0.0d) {
                return 1;
            }
            if (doubleValue == 0.0d) {
                return 0;
            }
            if (doubleValue < 0.0d) {
                return -1;
            }
            return Double.valueOf(Double.NaN);
        }
    };
    private static Object p = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.ceil(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object q = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.floor(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object r = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            return Long.valueOf(Math.round(((Double) arrayList.get(0)).doubleValue()));
        }
    };
    private static Object s = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            if (arrayList == null) {
                return null;
            }
            if (arrayList.size() < 1) {
                return null;
            }
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            int size = arrayList.size();
            for (int i = 1; i < size; i++) {
                double doubleValue2 = ((Double) arrayList.get(i)).doubleValue();
                if (doubleValue2 > doubleValue) {
                    doubleValue = doubleValue2;
                }
            }
            return Double.valueOf(doubleValue);
        }
    };
    private static Object t = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) {
            if (arrayList == null) {
                return null;
            }
            if (arrayList.size() < 1) {
                return null;
            }
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            int size = arrayList.size();
            for (int i = 1; i < size; i++) {
                double doubleValue2 = ((Double) arrayList.get(i)).doubleValue();
                if (doubleValue2 < doubleValue) {
                    doubleValue = doubleValue2;
                }
            }
            return Double.valueOf(doubleValue);
        }
    };
    private static Object u = Double.valueOf(3.141592653589793d);
    private static Object v = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            if (arrayList == null || arrayList.size() < 2) {
                return null;
            }
            return arrayList;
        }
    };
    private static Object w = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            if (arrayList == null || arrayList.size() < 2) {
                return null;
            }
            return arrayList;
        }
    };
    private static Object x = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            if (arrayList == null || arrayList.size() < 6) {
                return null;
            }
            return arrayList;
        }
    };
    private static Object y = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            if (arrayList == null || arrayList.size() < 3) {
                return null;
            }
            return Integer.valueOf(Color.rgb((int) ((Double) arrayList.get(0)).doubleValue(), (int) ((Double) arrayList.get(1)).doubleValue(), (int) ((Double) arrayList.get(2)).doubleValue()));
        }
    };
    private static Object z = new JSFunctionInterface() {
        public Object a(ArrayList<Object> arrayList) throws NumberFormatException, JSONException {
            if (arrayList == null || arrayList.size() < 4) {
                return null;
            }
            return Integer.valueOf(Color.argb((int) (((Double) arrayList.get(3)).doubleValue() * 255.0d), (int) ((Double) arrayList.get(0)).doubleValue(), (int) ((Double) arrayList.get(1)).doubleValue(), (int) ((Double) arrayList.get(2)).doubleValue()));
        }
    };

    private JSMath() {
    }

    /* access modifiers changed from: private */
    public static int b(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("'") || str.startsWith("\"")) {
                str = str.substring(1, str.length() - 1);
            }
            int parseColor = Color.parseColor(str);
            return Color.argb(255, Color.red(parseColor), Color.green(parseColor), Color.blue(parseColor));
        }
        throw new IllegalArgumentException("Unknown color");
    }

    static void a(Map<String, Object> map, double d2, double d3, PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator) {
        map.put("x", Double.valueOf(iDeviceResolutionTranslator.b(d2, new Object[0])));
        map.put(Constants.Name.Y, Double.valueOf(iDeviceResolutionTranslator.b(d3, new Object[0])));
        map.put("internal_x", Double.valueOf(d2));
        map.put("internal_y", Double.valueOf(d3));
    }

    static void a(Map<String, Object> map, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        Map<String, Object> map2 = map;
        map.put("alpha", Double.valueOf(d2));
        map.put("beta", Double.valueOf(d3));
        map.put("gamma", Double.valueOf(d4));
        map.put("dalpha", Double.valueOf(d2 - d5));
        map.put("dbeta", Double.valueOf(d3 - d6));
        map.put("dgamma", Double.valueOf(d4 - d7));
        map.put("x", Double.valueOf(d8));
        map.put(Constants.Name.Y, Double.valueOf(d9));
        map.put(CompressorStreamFactory.h, Double.valueOf(d10));
    }

    static void a(Map<String, Object> map, double d2) {
        map.put("t", Double.valueOf(d2));
    }

    static void a(Map<String, Object> map, double d2, double d3, double d4, double d5, double d6, double d7, PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator) {
        Map<String, Object> map2 = map;
        PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
        double d8 = d2;
        map.put("x", Double.valueOf(iDeviceResolutionTranslator2.b(d2, new Object[0])));
        double d9 = d3;
        map.put(Constants.Name.Y, Double.valueOf(iDeviceResolutionTranslator2.b(d3, new Object[0])));
        map.put("dx", Double.valueOf(iDeviceResolutionTranslator2.b(d4, new Object[0])));
        map.put(Constants.Name.DISTANCE_Y, Double.valueOf(iDeviceResolutionTranslator2.b(d5, new Object[0])));
        map.put("tdx", Double.valueOf(iDeviceResolutionTranslator2.b(d6, new Object[0])));
        map.put("tdy", Double.valueOf(iDeviceResolutionTranslator2.b(d7, new Object[0])));
        map.put("internal_x", Double.valueOf(d2));
        map.put("internal_y", Double.valueOf(d3));
    }

    static void a(Map<String, Object> map) {
        map.put("sin", b);
        map.put("cos", c);
        map.put("tan", d);
        map.put("asin", e);
        map.put("acos", f);
        map.put("atan", g);
        map.put("atan2", h);
        map.put("pow", i);
        map.put(FeedbackApi.COMMON_EXP, j);
        map.put("sqrt", k);
        map.put("cbrt", l);
        map.put("log", m);
        map.put("abs", n);
        map.put("sign", o);
        map.put("ceil", p);
        map.put(Tags.Kuwan.COMMENT_FLOOR, q);
        map.put("round", r);
        map.put("max", s);
        map.put("min", t);
        map.put("PI", u);
        map.put(ExifInterface.LONGITUDE_EAST, f758a);
        map.put("translate", v);
        map.put("scale", w);
        map.put("matrix", x);
        map.put("rgb", y);
        map.put("rgba", z);
        map.put("evaluateColor", B);
        map.put("asArray", C);
    }
}
