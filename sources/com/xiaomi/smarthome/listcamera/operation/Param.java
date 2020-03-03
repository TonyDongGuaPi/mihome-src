package com.xiaomi.smarthome.listcamera.operation;

import com.mi.mistatistic.sdk.data.EventData;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Param {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, Class> f19331a = new HashMap<>();
    private List<ParamItem> b = new ArrayList();
    private Object c;

    private static class ParamItem {

        /* renamed from: a  reason: collision with root package name */
        String f19332a;
        int b;
        String c;

        private ParamItem() {
        }
    }

    static {
        f19331a.put("JSONObject", JSONObject.class);
        f19331a.put("JSONArray", JSONArray.class);
        f19331a.put(EventData.b, String.class);
        f19331a.put("double", Double.class);
        f19331a.put("int", Integer.class);
    }

    public boolean a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            ParamItem paramItem = new ParamItem();
            paramItem.f19332a = optJSONObject.optString("type");
            if (paramItem.f19332a == null) {
                return false;
            }
            if (paramItem.f19332a == null || paramItem.f19332a.equals(EventData.b) || paramItem.f19332a.equals("double") || paramItem.f19332a.equals("int")) {
                this.b.add(paramItem);
                return true;
            }
            if (paramItem.f19332a.equals("JSONObject")) {
                paramItem.c = optJSONObject.optString("key");
            } else if (!paramItem.f19332a.equals("JSONArray")) {
                return false;
            } else {
                paramItem.b = optJSONObject.optInt("index");
            }
            this.b.add(paramItem);
        }
        return true;
    }

    private Object a(ParamItem paramItem, Object obj) {
        JSONArray jSONArray = null;
        if (!(f19331a.get(paramItem.f19332a) == null || paramItem.f19332a == null)) {
            if (paramItem.f19332a.equals(EventData.b)) {
                return String.valueOf(obj);
            }
            if (paramItem.f19332a.equals("double")) {
                return Double.valueOf(NumberUtils.a((Object) String.valueOf(obj), 0.0d));
            }
            if (paramItem.f19332a.equals("int")) {
                return Integer.valueOf(NumberUtils.a((Object) String.valueOf(obj), 0));
            }
            if (paramItem.f19332a.equals("JSONObject")) {
                try {
                    return new JSONObject(obj.toString()).opt(paramItem.c);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (paramItem.f19332a.equals("JSONArray")) {
                try {
                    JSONArray jSONArray2 = new JSONArray(obj.toString());
                    try {
                        return jSONArray2.opt(paramItem.b);
                    } catch (JSONException e2) {
                        e = e2;
                        jSONArray = jSONArray2;
                        e.printStackTrace();
                        return jSONArray.opt(paramItem.b);
                    }
                } catch (JSONException e3) {
                    e = e3;
                    e.printStackTrace();
                    return jSONArray.opt(paramItem.b);
                }
            }
        }
        return null;
    }

    public boolean a(Object obj) {
        this.c = obj;
        for (int i = 0; i < this.b.size(); i++) {
            this.c = a(this.b.get(i), this.c);
            if (this.c == null) {
                return false;
            }
        }
        return true;
    }

    public void b(Object obj) {
        this.c = obj;
    }

    public boolean c(Object obj) {
        return this.c != null && this.c.equals(obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: org.json.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object a() {
        /*
            r4 = this;
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r0 = r4.b
            int r0 = r0.size()
            int r0 = r0 + -1
            r1 = 0
        L_0x0009:
            if (r0 < 0) goto L_0x00a9
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r2 = r4.b
            java.lang.Object r2 = r2.get(r0)
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r2 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r2
            java.lang.String r2 = r2.f19332a
            if (r2 == 0) goto L_0x00a3
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r2 = r4.b
            java.lang.Object r2 = r2.get(r0)
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r2 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r2
            java.lang.String r2 = r2.f19332a
            java.lang.String r3 = "string"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00a3
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r2 = r4.b
            java.lang.Object r2 = r2.get(r0)
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r2 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r2
            java.lang.String r2 = r2.f19332a
            java.lang.String r3 = "double"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00a3
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r2 = r4.b
            java.lang.Object r2 = r2.get(r0)
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r2 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r2
            java.lang.String r2 = r2.f19332a
            java.lang.String r3 = "int"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x004e
            goto L_0x00a3
        L_0x004e:
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r2 = r4.b
            java.lang.Object r2 = r2.get(r0)
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r2 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r2
            java.lang.String r2 = r2.f19332a
            java.lang.String r3 = "JSONObject"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r3 = r4.b     // Catch:{ JSONException -> 0x0073 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x0073 }
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r3 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r3     // Catch:{ JSONException -> 0x0073 }
            java.lang.String r3 = r3.c     // Catch:{ JSONException -> 0x0073 }
            r2.put(r3, r1)     // Catch:{ JSONException -> 0x0073 }
            goto L_0x009c
        L_0x0073:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00a5
        L_0x0078:
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r2 = r4.b
            java.lang.Object r2 = r2.get(r0)
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r2 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r2
            java.lang.String r2 = r2.f19332a
            java.lang.String r3 = "JSONArray"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00a5
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            java.util.List<com.xiaomi.smarthome.listcamera.operation.Param$ParamItem> r3 = r4.b     // Catch:{ JSONException -> 0x009e }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x009e }
            com.xiaomi.smarthome.listcamera.operation.Param$ParamItem r3 = (com.xiaomi.smarthome.listcamera.operation.Param.ParamItem) r3     // Catch:{ JSONException -> 0x009e }
            int r3 = r3.b     // Catch:{ JSONException -> 0x009e }
            r2.put(r3, r1)     // Catch:{ JSONException -> 0x009e }
        L_0x009c:
            r1 = r2
            goto L_0x00a5
        L_0x009e:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00a5
        L_0x00a3:
            java.lang.Object r1 = r4.c
        L_0x00a5:
            int r0 = r0 + -1
            goto L_0x0009
        L_0x00a9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.listcamera.operation.Param.a():java.lang.Object");
    }
}
