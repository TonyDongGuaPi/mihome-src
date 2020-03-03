package com.taobao.weex.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXReflectionUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3608058198171993229L, "com/taobao/weex/utils/WXReflectionUtils", 83);
        $jacocoData = a2;
        return a2;
    }

    public WXReflectionUtils() {
        $jacocoInit()[0] = true;
    }

    public static Object parseArgument(Type type, Object obj) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            if (obj.getClass() == type) {
                $jacocoInit[3] = true;
                return obj;
            } else if (!(type instanceof Class)) {
                $jacocoInit[4] = true;
            } else {
                $jacocoInit[5] = true;
                if (!((Class) type).isAssignableFrom(obj.getClass())) {
                    $jacocoInit[6] = true;
                } else {
                    $jacocoInit[7] = true;
                    return obj;
                }
            }
        }
        if (type == String.class) {
            $jacocoInit[8] = true;
            if (obj instanceof String) {
                $jacocoInit[9] = true;
            } else {
                obj = JSON.toJSONString(obj);
                $jacocoInit[10] = true;
            }
            $jacocoInit[11] = true;
            return obj;
        } else if (type == Integer.TYPE) {
            $jacocoInit[12] = true;
            if (obj.getClass().isAssignableFrom(Integer.TYPE)) {
                $jacocoInit[13] = true;
            } else {
                obj = Integer.valueOf(WXUtils.getInt(obj));
                $jacocoInit[14] = true;
            }
            $jacocoInit[15] = true;
            return obj;
        } else if (type == Long.TYPE) {
            $jacocoInit[16] = true;
            if (obj.getClass().isAssignableFrom(Long.TYPE)) {
                $jacocoInit[17] = true;
            } else {
                obj = Long.valueOf(WXUtils.getLong(obj));
                $jacocoInit[18] = true;
            }
            $jacocoInit[19] = true;
            return obj;
        } else if (type == Double.TYPE) {
            $jacocoInit[20] = true;
            if (obj.getClass().isAssignableFrom(Double.TYPE)) {
                $jacocoInit[21] = true;
            } else {
                obj = Double.valueOf(WXUtils.getDouble(obj));
                $jacocoInit[22] = true;
            }
            $jacocoInit[23] = true;
            return obj;
        } else if (type == Float.TYPE) {
            $jacocoInit[24] = true;
            if (obj.getClass().isAssignableFrom(Float.TYPE)) {
                $jacocoInit[25] = true;
            } else {
                obj = Float.valueOf(WXUtils.getFloat(obj));
                $jacocoInit[26] = true;
            }
            $jacocoInit[27] = true;
            return obj;
        } else {
            if (type != JSONArray.class) {
                $jacocoInit[28] = true;
            } else if (obj == null) {
                $jacocoInit[29] = true;
            } else if (obj.getClass() != JSONArray.class) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                return obj;
            }
            if (type != JSONObject.class) {
                $jacocoInit[32] = true;
            } else if (obj == null) {
                $jacocoInit[33] = true;
            } else if (obj.getClass() != JSONObject.class) {
                $jacocoInit[34] = true;
            } else {
                $jacocoInit[35] = true;
                return obj;
            }
            if (obj instanceof String) {
                str = (String) obj;
                $jacocoInit[36] = true;
            } else {
                str = JSON.toJSONString(obj);
                $jacocoInit[37] = true;
            }
            Object parseObject = JSON.parseObject(str, type, new Feature[0]);
            $jacocoInit[38] = true;
            return parseObject;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ee A[Catch:{ Exception -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f3 A[Catch:{ Exception -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0106 A[Catch:{ Exception -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010b A[Catch:{ Exception -> 0x0123 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setValue(java.lang.Object r5, java.lang.String r6, java.lang.Object r7) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r5 != 0) goto L_0x000d
            r5 = 39
            r0[r5] = r1
            goto L_0x012c
        L_0x000d:
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            if (r2 != 0) goto L_0x0128
            r2 = 40
            r0[r2] = r1
            java.lang.reflect.Field r6 = getDeclaredField(r5, r6)     // Catch:{ Exception -> 0x0123 }
            boolean r2 = r7 instanceof java.math.BigDecimal     // Catch:{ Exception -> 0x0123 }
            if (r2 == 0) goto L_0x0024
            r2 = 43
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x003b
        L_0x0024:
            boolean r2 = r7 instanceof java.lang.Number     // Catch:{ Exception -> 0x0123 }
            if (r2 == 0) goto L_0x002d
            r2 = 44
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x003b
        L_0x002d:
            boolean r2 = r7 instanceof java.lang.String     // Catch:{ Exception -> 0x0123 }
            if (r2 != 0) goto L_0x0037
            r2 = 45
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x00d4
        L_0x0037:
            r2 = 46
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x003b:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class<java.lang.Float> r3 = java.lang.Float.class
            if (r2 != r3) goto L_0x0048
            r2 = 47
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x0054
        L_0x0048:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0123 }
            if (r2 != r3) goto L_0x0066
            r2 = 48
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x0054:
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0123 }
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ Exception -> 0x0123 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0123 }
            r3 = 49
            r0[r3] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x00e6
        L_0x0066:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class<java.lang.Double> r3 = java.lang.Double.class
            if (r2 != r3) goto L_0x0073
            r2 = 50
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x007f
        L_0x0073:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class r3 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0123 }
            if (r2 != r3) goto L_0x0090
            r2 = 51
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x007f:
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0123 }
            double r2 = java.lang.Double.parseDouble(r2)     // Catch:{ Exception -> 0x0123 }
            java.lang.Double r2 = java.lang.Double.valueOf(r2)     // Catch:{ Exception -> 0x0123 }
            r3 = 52
            r0[r3] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x00e6
        L_0x0090:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            if (r2 != r3) goto L_0x009d
            r2 = 53
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x00a9
        L_0x009d:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0123 }
            if (r2 != r3) goto L_0x00bb
            r2 = 54
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x00a9:
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0123 }
            double r2 = java.lang.Double.parseDouble(r2)     // Catch:{ Exception -> 0x0123 }
            int r2 = (int) r2     // Catch:{ Exception -> 0x0123 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0123 }
            r3 = 55
            r0[r3] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x00e6
        L_0x00bb:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class<java.lang.Boolean> r3 = java.lang.Boolean.class
            if (r2 != r3) goto L_0x00c8
            r2 = 56
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x00da
        L_0x00c8:
            java.lang.Class r2 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class r3 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x0123 }
            if (r2 == r3) goto L_0x00d6
            r2 = 57
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x00d4:
            r2 = r7
            goto L_0x00e6
        L_0x00d6:
            r2 = 58
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x00da:
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0123 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x0123 }
            r3 = 59
            r0[r3] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x00e6:
            java.lang.Class r3 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x0123 }
            if (r3 != r4) goto L_0x00f3
            r3 = 60
            r0[r3] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x0104
        L_0x00f3:
            java.lang.Class r3 = r6.getType()     // Catch:{ Exception -> 0x0123 }
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            if (r3 == r4) goto L_0x0100
            r7 = 61
            r0[r7] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x011b
        L_0x0100:
            r3 = 62
            r0[r3] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x0104:
            if (r7 != 0) goto L_0x010b
            r7 = 63
            r0[r7] = r1     // Catch:{ Exception -> 0x0123 }
            goto L_0x011b
        L_0x010b:
            r2 = 64
            r0[r2] = r1     // Catch:{ Exception -> 0x0123 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0123 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r7)     // Catch:{ Exception -> 0x0123 }
            r7 = 65
            r0[r7] = r1     // Catch:{ Exception -> 0x0123 }
        L_0x011b:
            setProperty(r5, r6, r2)     // Catch:{ Exception -> 0x0123 }
            r5 = 67
            r0[r5] = r1
            return
        L_0x0123:
            r5 = 66
            r0[r5] = r1
            return
        L_0x0128:
            r5 = 41
            r0[r5] = r1
        L_0x012c:
            r5 = 42
            r0[r5] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXReflectionUtils.setValue(java.lang.Object, java.lang.String, java.lang.Object):void");
    }

    public static Field getDeclaredField(Object obj, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[68] = true;
        Class cls = obj.getClass();
        $jacocoInit[69] = true;
        while (cls != Object.class) {
            try {
                $jacocoInit[70] = true;
                Field declaredField = cls.getDeclaredField(str);
                $jacocoInit[71] = true;
                return declaredField;
            } catch (Exception unused) {
                $jacocoInit[72] = true;
                cls = cls.getSuperclass();
                $jacocoInit[73] = true;
            }
        }
        $jacocoInit[74] = true;
        return null;
    }

    public static void setProperty(Object obj, Field field, Object obj2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[75] = true;
        } else if (field != null) {
            $jacocoInit[76] = true;
            try {
                field.setAccessible(true);
                $jacocoInit[79] = true;
                field.set(obj, obj2);
                $jacocoInit[80] = true;
            } catch (Exception unused) {
                $jacocoInit[81] = true;
            }
            $jacocoInit[82] = true;
            return;
        } else {
            $jacocoInit[77] = true;
        }
        $jacocoInit[78] = true;
    }
}
