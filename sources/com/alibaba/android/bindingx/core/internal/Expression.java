package com.alibaba.android.bindingx.core.internal;

import com.alibaba.android.bindingx.core.LogProxy;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

class Expression {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f755a;

    Expression(String str) {
        try {
            this.f755a = (JSONObject) new JSONTokener(str).nextValue();
        } catch (Throwable th) {
            LogProxy.e("[Expression] expression is illegal. \n ", th);
        }
    }

    Expression(JSONObject jSONObject) {
        this.f755a = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public Object a(Map<String, Object> map) throws IllegalArgumentException, JSONException {
        return a(this.f755a, map);
    }

    private double a(Object obj) {
        if (obj instanceof String) {
            return Double.parseDouble((String) obj);
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? 1.0d : 0.0d;
        }
        return ((Double) obj).doubleValue();
    }

    private boolean b(Object obj) {
        if (obj instanceof String) {
            return "".equals(obj);
        }
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue() != 0.0d;
        }
        return ((Boolean) obj).booleanValue();
    }

    private String c(Object obj) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? "true" : "false";
        }
        if (obj instanceof Double) {
            return Double.toString(((Double) obj).doubleValue());
        }
        return (String) obj;
    }

    private boolean a(Object obj, Object obj2) {
        if ((obj instanceof JSObjectInterface) && (obj2 instanceof JSObjectInterface)) {
            return obj == obj2;
        }
        if ((obj instanceof String) && (obj2 instanceof String)) {
            return obj.equals(obj2);
        }
        if (!(obj instanceof Boolean) || !(obj2 instanceof Boolean)) {
            if (a(obj) == a(obj2)) {
                return true;
            }
            return false;
        } else if (b(obj) == b(obj2)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean b(Object obj, Object obj2) {
        if ((obj instanceof JSObjectInterface) && !(obj2 instanceof JSObjectInterface)) {
            return false;
        }
        if ((obj instanceof Boolean) && !(obj2 instanceof Boolean)) {
            return false;
        }
        if ((obj instanceof Double) && !(obj2 instanceof Double)) {
            return false;
        }
        if ((!(obj instanceof String) || (obj2 instanceof String)) && obj == obj2) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r4v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [int] */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r4v8 */
    /* JADX WARNING: type inference failed for: r4v10 */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object a(org.json.JSONObject r7, java.util.Map<java.lang.String, java.lang.Object> r8) throws java.lang.IllegalArgumentException, org.json.JSONException {
        /*
            r6 = this;
            java.lang.String r0 = "type"
            java.lang.String r0 = r7.getString(r0)
            java.lang.String r1 = "children"
            org.json.JSONArray r1 = r7.optJSONArray(r1)
            int r2 = r0.hashCode()
            r3 = 2
            r4 = 0
            r5 = 1
            switch(r2) {
                case -1746151498: goto L_0x0112;
                case 33: goto L_0x0107;
                case 37: goto L_0x00fc;
                case 42: goto L_0x00f1;
                case 43: goto L_0x00e7;
                case 45: goto L_0x00dd;
                case 47: goto L_0x00d2;
                case 60: goto L_0x00c7;
                case 62: goto L_0x00bc;
                case 63: goto L_0x00b2;
                case 1084: goto L_0x00a6;
                case 1216: goto L_0x009a;
                case 1344: goto L_0x008e;
                case 1921: goto L_0x0082;
                case 1952: goto L_0x0076;
                case 1983: goto L_0x006a;
                case 3968: goto L_0x005d;
                case 33665: goto L_0x0051;
                case 60573: goto L_0x0045;
                case 189157634: goto L_0x003a;
                case 375032009: goto L_0x002f;
                case 1074430782: goto L_0x0024;
                case 1816238983: goto L_0x0019;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x011c
        L_0x0019:
            java.lang.String r2 = "BooleanLiteral"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 2
            goto L_0x011d
        L_0x0024:
            java.lang.String r2 = "StringLiteral"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 0
            goto L_0x011d
        L_0x002f:
            java.lang.String r2 = "Identifier"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 3
            goto L_0x011d
        L_0x003a:
            java.lang.String r2 = "NumericLiteral"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 1
            goto L_0x011d
        L_0x0045:
            java.lang.String r2 = "==="
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 17
            goto L_0x011d
        L_0x0051:
            java.lang.String r2 = "!=="
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 19
            goto L_0x011d
        L_0x005d:
            java.lang.String r2 = "||"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 21
            goto L_0x011d
        L_0x006a:
            java.lang.String r2 = ">="
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 14
            goto L_0x011d
        L_0x0076:
            java.lang.String r2 = "=="
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 16
            goto L_0x011d
        L_0x0082:
            java.lang.String r2 = "<="
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 15
            goto L_0x011d
        L_0x008e:
            java.lang.String r2 = "**"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 11
            goto L_0x011d
        L_0x009a:
            java.lang.String r2 = "&&"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 20
            goto L_0x011d
        L_0x00a6:
            java.lang.String r2 = "!="
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 18
            goto L_0x011d
        L_0x00b2:
            java.lang.String r2 = "?"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 5
            goto L_0x011d
        L_0x00bc:
            java.lang.String r2 = ">"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 12
            goto L_0x011d
        L_0x00c7:
            java.lang.String r2 = "<"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 13
            goto L_0x011d
        L_0x00d2:
            java.lang.String r2 = "/"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 9
            goto L_0x011d
        L_0x00dd:
            java.lang.String r2 = "-"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 7
            goto L_0x011d
        L_0x00e7:
            java.lang.String r2 = "+"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 6
            goto L_0x011d
        L_0x00f1:
            java.lang.String r2 = "*"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 8
            goto L_0x011d
        L_0x00fc:
            java.lang.String r2 = "%"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 10
            goto L_0x011d
        L_0x0107:
            java.lang.String r2 = "!"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 22
            goto L_0x011d
        L_0x0112:
            java.lang.String r2 = "CallExpression"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x011c
            r0 = 4
            goto L_0x011d
        L_0x011c:
            r0 = -1
        L_0x011d:
            switch(r0) {
                case 0: goto L_0x0382;
                case 1: goto L_0x0376;
                case 2: goto L_0x036a;
                case 3: goto L_0x035e;
                case 4: goto L_0x032c;
                case 5: goto L_0x030a;
                case 6: goto L_0x02ec;
                case 7: goto L_0x02ce;
                case 8: goto L_0x02af;
                case 9: goto L_0x0291;
                case 10: goto L_0x0273;
                case 11: goto L_0x0252;
                case 12: goto L_0x0230;
                case 13: goto L_0x020e;
                case 14: goto L_0x01ec;
                case 15: goto L_0x01ca;
                case 16: goto L_0x01b1;
                case 17: goto L_0x0198;
                case 18: goto L_0x017e;
                case 19: goto L_0x0164;
                case 20: goto L_0x014c;
                case 21: goto L_0x0134;
                case 22: goto L_0x0122;
                default: goto L_0x0120;
            }
        L_0x0120:
            r7 = 0
            return r7
        L_0x0122:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r7 = r6.b(r7)
            r7 = r7 ^ r5
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            return r7
        L_0x0134:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r0 = r6.b(r7)
            if (r0 == 0) goto L_0x0143
            return r7
        L_0x0143:
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            return r7
        L_0x014c:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r0 = r6.b(r7)
            if (r0 != 0) goto L_0x015b
            return r7
        L_0x015b:
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            return r7
        L_0x0164:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            org.json.JSONObject r0 = r1.getJSONObject(r5)
            java.lang.Object r8 = r6.a((org.json.JSONObject) r0, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r7 = r6.b(r7, r8)
            r7 = r7 ^ r5
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            return r7
        L_0x017e:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            org.json.JSONObject r0 = r1.getJSONObject(r5)
            java.lang.Object r8 = r6.a((org.json.JSONObject) r0, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r7 = r6.a((java.lang.Object) r7, (java.lang.Object) r8)
            r7 = r7 ^ r5
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            return r7
        L_0x0198:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            org.json.JSONObject r0 = r1.getJSONObject(r5)
            java.lang.Object r8 = r6.a((org.json.JSONObject) r0, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r7 = r6.b(r7, r8)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            return r7
        L_0x01b1:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            org.json.JSONObject r0 = r1.getJSONObject(r5)
            java.lang.Object r8 = r6.a((org.json.JSONObject) r0, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            boolean r7 = r6.a((java.lang.Object) r7, (java.lang.Object) r8)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            return r7
        L_0x01ca:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x01e7
            r4 = 1
        L_0x01e7:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            return r7
        L_0x01ec:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x0209
            r4 = 1
        L_0x0209:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            return r7
        L_0x020e:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x022b
            r4 = 1
        L_0x022b:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            return r7
        L_0x0230:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x024d
            r4 = 1
        L_0x024d:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            return r7
        L_0x0252:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            double r7 = java.lang.Math.pow(r2, r7)
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            return r7
        L_0x0273:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            double r2 = r2 % r7
            java.lang.Double r7 = java.lang.Double.valueOf(r2)
            return r7
        L_0x0291:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            double r2 = r2 / r7
            java.lang.Double r7 = java.lang.Double.valueOf(r2)
            return r7
        L_0x02af:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            double r2 = r2 * r7
            java.lang.Double r7 = java.lang.Double.valueOf(r2)
            return r7
        L_0x02ce:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            double r2 = r2 - r7
            java.lang.Double r7 = java.lang.Double.valueOf(r2)
            return r7
        L_0x02ec:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r2 = r6.a((java.lang.Object) r7)
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            double r7 = r6.a((java.lang.Object) r7)
            double r2 = r2 + r7
            java.lang.Double r7 = java.lang.Double.valueOf(r2)
            return r7
        L_0x030a:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0323
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            return r7
        L_0x0323:
            org.json.JSONObject r7 = r1.getJSONObject(r3)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            return r7
        L_0x032c:
            org.json.JSONObject r7 = r1.getJSONObject(r4)
            java.lang.Object r7 = r6.a((org.json.JSONObject) r7, (java.util.Map<java.lang.String, java.lang.Object>) r8)
            com.alibaba.android.bindingx.core.internal.JSFunctionInterface r7 = (com.alibaba.android.bindingx.core.internal.JSFunctionInterface) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.json.JSONObject r1 = r1.getJSONObject(r5)
            java.lang.String r2 = "children"
            org.json.JSONArray r1 = r1.getJSONArray(r2)
        L_0x0345:
            int r2 = r1.length()
            if (r4 >= r2) goto L_0x0359
            org.json.JSONObject r2 = r1.getJSONObject(r4)
            java.lang.Object r2 = r6.a((org.json.JSONObject) r2, (java.util.Map<java.lang.String, java.lang.Object>) r8)     // Catch:{ Throwable -> 0x038a }
            r0.add(r2)
            int r4 = r4 + 1
            goto L_0x0345
        L_0x0359:
            java.lang.Object r7 = r7.a(r0)
            return r7
        L_0x035e:
            java.lang.String r0 = "value"
            java.lang.String r7 = r7.getString(r0)
            java.lang.Object r7 = r8.get(r7)
            return r7
        L_0x036a:
            java.lang.String r8 = "value"
            boolean r7 = r7.getBoolean(r8)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            return r7
        L_0x0376:
            java.lang.String r8 = "value"
            double r7 = r7.getDouble(r8)
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            return r7
        L_0x0382:
            java.lang.String r8 = "value"
            java.lang.String r7 = r7.getString(r8)
            return r7
        L_0x038a:
            r7 = move-exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.core.internal.Expression.a(org.json.JSONObject, java.util.Map):java.lang.Object");
    }
}
