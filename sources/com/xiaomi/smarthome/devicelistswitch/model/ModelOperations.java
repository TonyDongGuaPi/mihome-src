package com.xiaomi.smarthome.devicelistswitch.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelOperations implements Parcelable {
    public static final Parcelable.Creator<ModelOperations> CREATOR = new Parcelable.Creator<ModelOperations>() {
        /* renamed from: a */
        public ModelOperations createFromParcel(Parcel parcel) {
            return new ModelOperations(parcel);
        }

        /* renamed from: a */
        public ModelOperations[] newArray(int i) {
            return new ModelOperations[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f15534a = 0;
    public static final int b = 1;
    private String c;
    private Operation[] d;
    private int e = 0;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d A[Catch:{ JSONException -> 0x008d }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0067 A[Catch:{ JSONException -> 0x008d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.xiaomi.smarthome.devicelistswitch.model.ModelOperations> a(org.json.JSONObject r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r6 == 0) goto L_0x0091
            java.lang.String r1 = "model"
            boolean r1 = r6.isNull(r1)     // Catch:{ JSONException -> 0x008d }
            if (r1 != 0) goto L_0x0091
            java.lang.String r1 = "model"
            java.lang.Object r1 = r6.get(r1)     // Catch:{ JSONException -> 0x008d }
            java.lang.String r2 = "type"
            boolean r2 = r6.isNull(r2)     // Catch:{ JSONException -> 0x008d }
            r3 = 0
            if (r2 != 0) goto L_0x0048
            java.lang.String r2 = "type"
            java.lang.Object r2 = r6.get(r2)     // Catch:{ JSONException -> 0x008d }
            boolean r4 = r2 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x008d }
            if (r4 == 0) goto L_0x0048
            r4 = r2
            org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch:{ JSONException -> 0x008d }
            java.lang.String r5 = "type_value"
            boolean r4 = r4.isNull(r5)     // Catch:{ JSONException -> 0x008d }
            if (r4 != 0) goto L_0x0048
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch:{ JSONException -> 0x008d }
            java.lang.String r4 = "type_value"
            java.lang.Object r2 = r2.get(r4)     // Catch:{ JSONException -> 0x008d }
            boolean r4 = r2 instanceof java.lang.String     // Catch:{ JSONException -> 0x008d }
            if (r4 == 0) goto L_0x0048
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x008d }
            java.lang.String r4 = "pause-resume"
            boolean r2 = android.text.TextUtils.equals(r2, r4)     // Catch:{ JSONException -> 0x008d }
            goto L_0x0049
        L_0x0048:
            r2 = 0
        L_0x0049:
            boolean r4 = r1 instanceof java.lang.String     // Catch:{ JSONException -> 0x008d }
            if (r4 == 0) goto L_0x0067
            java.lang.String r1 = "operations"
            org.json.JSONArray r1 = r6.optJSONArray(r1)     // Catch:{ JSONException -> 0x008d }
            com.xiaomi.smarthome.devicelistswitch.model.ModelOperations r1 = a((org.json.JSONArray) r1)     // Catch:{ JSONException -> 0x008d }
            java.lang.String r3 = "model"
            java.lang.String r6 = r6.optString(r3)     // Catch:{ JSONException -> 0x008d }
            r1.a((java.lang.String) r6)     // Catch:{ JSONException -> 0x008d }
            r1.a((int) r2)     // Catch:{ JSONException -> 0x008d }
            r0.add(r1)     // Catch:{ JSONException -> 0x008d }
            goto L_0x0091
        L_0x0067:
            boolean r4 = r1 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x008d }
            if (r4 == 0) goto L_0x0091
            org.json.JSONArray r1 = (org.json.JSONArray) r1     // Catch:{ JSONException -> 0x008d }
        L_0x006d:
            int r4 = r1.length()     // Catch:{ JSONException -> 0x008d }
            if (r3 >= r4) goto L_0x0091
            java.lang.String r4 = "operations"
            org.json.JSONArray r4 = r6.optJSONArray(r4)     // Catch:{ JSONException -> 0x008d }
            com.xiaomi.smarthome.devicelistswitch.model.ModelOperations r4 = a((org.json.JSONArray) r4)     // Catch:{ JSONException -> 0x008d }
            java.lang.String r5 = r1.optString(r3)     // Catch:{ JSONException -> 0x008d }
            r4.a((java.lang.String) r5)     // Catch:{ JSONException -> 0x008d }
            r4.a((int) r2)     // Catch:{ JSONException -> 0x008d }
            r0.add(r4)     // Catch:{ JSONException -> 0x008d }
            int r3 = r3 + 1
            goto L_0x006d
        L_0x008d:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0091:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.devicelistswitch.model.ModelOperations.a(org.json.JSONObject):java.util.List");
    }

    private static ModelOperations a(JSONArray jSONArray) {
        ModelOperations modelOperations = new ModelOperations();
        if (jSONArray != null && jSONArray.length() > 0) {
            Operation[] operationArr = new Operation[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                operationArr[i] = Operation.a(jSONArray.optJSONObject(i));
            }
            modelOperations.a(operationArr);
        }
        return modelOperations;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", b());
            if (this.d != null && this.d.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (Operation a2 : this.d) {
                    jSONArray.put(a2.a());
                }
                jSONObject.put("operations", jSONArray);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String b() {
        return this.c;
    }

    public Operation[] c() {
        return this.d;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(Operation[] operationArr) {
        this.d = operationArr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeTypedArray(this.d, i);
    }

    public ModelOperations() {
    }

    protected ModelOperations(Parcel parcel) {
        this.c = parcel.readString();
        this.d = (Operation[]) parcel.createTypedArray(Operation.CREATOR);
    }

    public int d() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }
}
