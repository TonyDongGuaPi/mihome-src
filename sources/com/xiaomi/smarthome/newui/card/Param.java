package com.xiaomi.smarthome.newui.card;

import android.util.Log;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Param {
    private static final String c = "Param";

    /* renamed from: a  reason: collision with root package name */
    public List<ParamTypeItem> f20522a;
    public List<Object> b;

    public static class ParamTypeItem {

        /* renamed from: a  reason: collision with root package name */
        public String f20523a;
        public int b;
        public String c;

        public ParamTypeItem(JSONObject jSONObject) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                char c2 = 65535;
                int hashCode = next.hashCode();
                if (hashCode != 106079) {
                    if (hashCode != 3575610) {
                        if (hashCode == 100346066 && next.equals("index")) {
                            c2 = 1;
                        }
                    } else if (next.equals("type")) {
                        c2 = 2;
                    }
                } else if (next.equals("key")) {
                    c2 = 0;
                }
                switch (c2) {
                    case 0:
                        this.f20523a = jSONObject.optString(next);
                        break;
                    case 1:
                        this.b = jSONObject.optInt("index");
                        break;
                    case 2:
                        this.c = jSONObject.optString("type");
                        break;
                }
            }
        }
    }

    public Param(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray != null) {
            this.b = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.b.add(jSONArray.opt(i));
            }
        }
        if (jSONArray2 != null) {
            this.f20522a = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                this.f20522a.add(new ParamTypeItem(jSONArray2.optJSONObject(i2)));
            }
        }
    }

    public Object a(Object obj) {
        if (this.b == null || this.b.size() <= 0) {
            if (obj != null) {
                return a(obj, (List<Object>) null);
            }
            return new JSONArray().toString();
        } else if (this.f20522a != null && this.f20522a.size() != 0) {
            return a(obj, this.b);
        } else {
            JSONArray jSONArray = new JSONArray();
            for (Object put : this.b) {
                jSONArray.put(put);
            }
            return jSONArray;
        }
    }

    public Object b(Object obj) {
        return a(obj, (List<Object>) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v39, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v40, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v41, resolved type: org.json.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object a(java.lang.Object r8, java.util.List<java.lang.Object> r9) {
        /*
            r7 = this;
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r0 = r7.f20522a
            if (r0 == 0) goto L_0x017b
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r0 = r7.f20522a
            int r0 = r0.size()
            if (r0 != 0) goto L_0x000e
            goto L_0x017b
        L_0x000e:
            r0 = 0
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r1 = r7.f20522a
            int r1 = r1.size()
            int r1 = r1 + -1
        L_0x0017:
            if (r1 < 0) goto L_0x017a
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            if (r2 != 0) goto L_0x0027
            goto L_0x00b2
        L_0x0027:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            java.lang.String r3 = "int"
            boolean r2 = r2.equalsIgnoreCase(r3)
            r3 = 0
            if (r2 == 0) goto L_0x0061
            boolean r0 = r8 instanceof java.lang.Integer
            if (r0 == 0) goto L_0x0040
            goto L_0x00b2
        L_0x0040:
            java.lang.String r0 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x0053 }
            double r4 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x0053 }
            float r0 = (float) r4     // Catch:{ Exception -> 0x0053 }
            int r0 = java.lang.Math.round(r0)     // Catch:{ Exception -> 0x0053 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0053 }
            goto L_0x0176
        L_0x0053:
            r0 = move-exception
            java.lang.String r2 = "Param"
            java.lang.String r4 = "error"
            android.util.Log.e(r2, r4, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            goto L_0x0176
        L_0x0061:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            java.lang.String r4 = "long"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 == 0) goto L_0x009c
            boolean r0 = r8 instanceof java.lang.Long
            if (r0 != 0) goto L_0x00b2
            boolean r0 = r8 instanceof java.lang.Integer
            if (r0 == 0) goto L_0x007c
            goto L_0x00b2
        L_0x007c:
            java.lang.String r0 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x008e }
            double r4 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x008e }
            long r4 = java.lang.Math.round(r4)     // Catch:{ Exception -> 0x008e }
            java.lang.Long r0 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x008e }
            goto L_0x0176
        L_0x008e:
            r0 = move-exception
            java.lang.String r2 = "Param"
            java.lang.String r4 = "error"
            android.util.Log.e(r2, r4, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            goto L_0x0176
        L_0x009c:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            java.lang.String r4 = "double"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 == 0) goto L_0x00d1
            boolean r0 = r8 instanceof java.lang.Number
            if (r0 == 0) goto L_0x00b5
        L_0x00b2:
            r0 = r8
            goto L_0x0176
        L_0x00b5:
            java.lang.String r0 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00c3 }
            double r4 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x00c3 }
            java.lang.Double r0 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x00c3 }
            goto L_0x0176
        L_0x00c3:
            r0 = move-exception
            java.lang.String r2 = "Param"
            java.lang.String r4 = "error"
            android.util.Log.e(r2, r4, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            goto L_0x0176
        L_0x00d1:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            java.lang.String r3 = "string"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x00e9
            java.lang.String r0 = java.lang.String.valueOf(r8)
            goto L_0x0176
        L_0x00e9:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            java.lang.String r3 = "JSONObject"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x0113
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r3 = r7.f20522a     // Catch:{ JSONException -> 0x010e }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ JSONException -> 0x010e }
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r3 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r3     // Catch:{ JSONException -> 0x010e }
            java.lang.String r3 = r3.f20523a     // Catch:{ JSONException -> 0x010e }
            r2.put(r3, r0)     // Catch:{ JSONException -> 0x010e }
            goto L_0x0171
        L_0x010e:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0176
        L_0x0113:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r2 = r7.f20522a
            java.lang.Object r2 = r2.get(r1)
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r2 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r2
            java.lang.String r2 = r2.c
            java.lang.String r3 = "JSONArray"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x0176
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            if (r9 == 0) goto L_0x0164
            int r3 = r9.size()     // Catch:{ JSONException -> 0x0162 }
            if (r3 != 0) goto L_0x0133
            goto L_0x0164
        L_0x0133:
            java.util.Iterator r3 = r9.iterator()     // Catch:{ JSONException -> 0x0162 }
        L_0x0137:
            boolean r4 = r3.hasNext()     // Catch:{ JSONException -> 0x0162 }
            if (r4 == 0) goto L_0x0171
            java.lang.Object r4 = r3.next()     // Catch:{ JSONException -> 0x0162 }
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r5 = r7.f20522a     // Catch:{ JSONException -> 0x0162 }
            java.lang.Object r5 = r5.get(r1)     // Catch:{ JSONException -> 0x0162 }
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r5 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r5     // Catch:{ JSONException -> 0x0162 }
            int r5 = r5.b     // Catch:{ JSONException -> 0x0162 }
            int r6 = r2.length()     // Catch:{ JSONException -> 0x0162 }
            if (r5 != r6) goto L_0x015e
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r5 = r7.f20522a     // Catch:{ JSONException -> 0x0162 }
            java.lang.Object r5 = r5.get(r1)     // Catch:{ JSONException -> 0x0162 }
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r5 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r5     // Catch:{ JSONException -> 0x0162 }
            int r5 = r5.b     // Catch:{ JSONException -> 0x0162 }
            r2.put(r5, r0)     // Catch:{ JSONException -> 0x0162 }
        L_0x015e:
            r2.put(r4)     // Catch:{ JSONException -> 0x0162 }
            goto L_0x0137
        L_0x0162:
            r2 = move-exception
            goto L_0x0173
        L_0x0164:
            java.util.List<com.xiaomi.smarthome.newui.card.Param$ParamTypeItem> r3 = r7.f20522a     // Catch:{ JSONException -> 0x0162 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ JSONException -> 0x0162 }
            com.xiaomi.smarthome.newui.card.Param$ParamTypeItem r3 = (com.xiaomi.smarthome.newui.card.Param.ParamTypeItem) r3     // Catch:{ JSONException -> 0x0162 }
            int r3 = r3.b     // Catch:{ JSONException -> 0x0162 }
            r2.put(r3, r0)     // Catch:{ JSONException -> 0x0162 }
        L_0x0171:
            r0 = r2
            goto L_0x0176
        L_0x0173:
            r2.printStackTrace()
        L_0x0176:
            int r1 = r1 + -1
            goto L_0x0017
        L_0x017a:
            return r0
        L_0x017b:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.Param.a(java.lang.Object, java.util.List):java.lang.Object");
    }

    public Object c(Object obj) {
        if (this.f20522a == null || this.f20522a.size() == 0) {
            return obj;
        }
        LogUtil.c("mijia-card", "parsePropValue:" + obj);
        Object obj2 = obj;
        for (int i = 0; i < this.f20522a.size(); i++) {
            obj2 = a(this.f20522a.get(i), obj2);
            if (obj2 == null) {
                if (i != 0) {
                    return null;
                }
                obj2 = obj;
            }
        }
        return obj2;
    }

    private Object a(ParamTypeItem paramTypeItem, Object obj) {
        if (paramTypeItem.c == null || obj == null || obj.toString().equalsIgnoreCase("null")) {
            return null;
        }
        if (paramTypeItem.c.equalsIgnoreCase("JSONObject")) {
            try {
                return new JSONObject(obj.toString()).opt(paramTypeItem.f20523a);
            } catch (JSONException e) {
                Log.e(c, "error", e);
                return obj;
            }
        } else if (!paramTypeItem.c.equalsIgnoreCase("JSONArray")) {
            return obj;
        } else {
            try {
                return new JSONArray(obj.toString()).opt(paramTypeItem.b);
            } catch (JSONException e2) {
                Log.e(c, "error", e2);
                return obj;
            }
        }
    }
}
