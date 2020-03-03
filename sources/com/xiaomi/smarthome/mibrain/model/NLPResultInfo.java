package com.xiaomi.smarthome.mibrain.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

public class NLPResultInfo implements Parcelable {
    public static final Parcelable.Creator<NLPResultInfo> CREATOR = new Parcelable.Creator<NLPResultInfo>() {
        /* renamed from: a */
        public NLPResultInfo createFromParcel(Parcel parcel) {
            return new NLPResultInfo(parcel);
        }

        /* renamed from: a */
        public NLPResultInfo[] newArray(int i) {
            return new NLPResultInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f10654a;
    public String b;
    public String c;
    public SingleDevice d;
    public List<DeviceOption> e;
    public int f = -1;
    public String g;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c A[Catch:{ JSONException -> 0x018e }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d A[Catch:{ JSONException -> 0x018e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.mibrain.model.NLPResultInfo a(java.lang.String r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x018e }
            r0.<init>(r8)     // Catch:{ JSONException -> 0x018e }
            java.lang.String r8 = "answer"
            org.json.JSONArray r8 = r0.getJSONArray(r8)     // Catch:{ JSONException -> 0x018e }
            r2 = 0
            if (r8 == 0) goto L_0x0024
            int r3 = r8.length()     // Catch:{ JSONException -> 0x018e }
            if (r3 > 0) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            java.lang.Object r8 = r8.get(r2)     // Catch:{ JSONException -> 0x018e }
            org.json.JSONObject r8 = (org.json.JSONObject) r8     // Catch:{ JSONException -> 0x018e }
            goto L_0x002a
        L_0x0024:
            java.lang.String r8 = "answer"
            org.json.JSONObject r8 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x018e }
        L_0x002a:
            if (r8 != 0) goto L_0x002d
            return r1
        L_0x002d:
            com.xiaomi.smarthome.mibrain.model.NLPResultInfo r3 = new com.xiaomi.smarthome.mibrain.model.NLPResultInfo     // Catch:{ JSONException -> 0x018e }
            r3.<init>()     // Catch:{ JSONException -> 0x018e }
            java.lang.String r1 = "status"
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch:{ JSONException -> 0x018c }
            java.lang.String r1 = "extend"
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch:{ JSONException -> 0x018c }
            java.lang.String r1 = "code"
            int r0 = r0.getInt(r1)     // Catch:{ JSONException -> 0x018c }
            r3.f10654a = r0     // Catch:{ JSONException -> 0x018c }
            java.lang.String r0 = "action"
            java.lang.String r1 = ""
            java.lang.String r0 = r8.optString(r0, r1)     // Catch:{ JSONException -> 0x018c }
            r3.b = r0     // Catch:{ JSONException -> 0x018c }
            java.lang.String r0 = "content"
            boolean r0 = r8.isNull(r0)     // Catch:{ JSONException -> 0x018c }
            if (r0 == 0) goto L_0x0059
            return r3
        L_0x0059:
            java.lang.String r0 = "content"
            org.json.JSONObject r0 = r8.optJSONObject(r0)     // Catch:{ JSONException -> 0x018c }
            java.lang.String r1 = "to_speak"
            java.lang.String r4 = ""
            java.lang.String r1 = r0.optString(r1, r4)     // Catch:{ JSONException -> 0x018c }
            r3.c = r1     // Catch:{ JSONException -> 0x018c }
            java.lang.String r1 = r3.c     // Catch:{ JSONException -> 0x018c }
            java.lang.String r4 = ""
            boolean r1 = r1.equals(r4)     // Catch:{ JSONException -> 0x018c }
            if (r1 == 0) goto L_0x007b
            java.lang.String r1 = "toSpeak"
            java.lang.String r1 = r0.optString(r1)     // Catch:{ JSONException -> 0x018c }
            r3.c = r1     // Catch:{ JSONException -> 0x018c }
        L_0x007b:
            java.lang.String r1 = "result"
            boolean r1 = r0.isNull(r1)     // Catch:{ JSONException -> 0x018c }
            if (r1 == 0) goto L_0x0084
            return r3
        L_0x0084:
            java.lang.String r1 = "result"
            org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch:{ JSONException -> 0x018c }
            java.lang.String r1 = "type"
            r4 = -1
            int r1 = r0.optInt(r1, r4)     // Catch:{ JSONException -> 0x018c }
            r3.f = r1     // Catch:{ JSONException -> 0x018c }
            int r1 = r3.f     // Catch:{ JSONException -> 0x018c }
            if (r1 != 0) goto L_0x00ef
            com.xiaomi.smarthome.mibrain.model.NLPResultInfo$SingleDevice r0 = new com.xiaomi.smarthome.mibrain.model.NLPResultInfo$SingleDevice     // Catch:{ JSONException -> 0x018c }
            r0.<init>()     // Catch:{ JSONException -> 0x018c }
            r3.d = r0     // Catch:{ JSONException -> 0x018c }
            java.lang.String r0 = "widgets"
            org.json.JSONArray r0 = r8.optJSONArray(r0)     // Catch:{ JSONException -> 0x018c }
            if (r0 == 0) goto L_0x00ee
            int r1 = r0.length()     // Catch:{ JSONException -> 0x018c }
            if (r1 > 0) goto L_0x00ad
            goto L_0x00ee
        L_0x00ad:
            org.json.JSONObject r0 = r0.getJSONObject(r2)     // Catch:{ JSONException -> 0x018c }
            if (r0 != 0) goto L_0x00b4
            return r3
        L_0x00b4:
            java.lang.String r1 = "params"
            org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch:{ JSONException -> 0x018c }
            if (r0 != 0) goto L_0x00bd
            return r3
        L_0x00bd:
            com.xiaomi.smarthome.mibrain.model.NLPResultInfo$SingleDevice r1 = new com.xiaomi.smarthome.mibrain.model.NLPResultInfo$SingleDevice     // Catch:{ JSONException -> 0x018c }
            r1.<init>()     // Catch:{ JSONException -> 0x018c }
            r3.d = r1     // Catch:{ JSONException -> 0x018c }
            com.xiaomi.smarthome.mibrain.model.NLPResultInfo$SingleDevice r1 = r3.d     // Catch:{ JSONException -> 0x018c }
            java.lang.String r2 = "did"
            java.lang.String r4 = ""
            java.lang.String r2 = r0.optString(r2, r4)     // Catch:{ JSONException -> 0x018c }
            r1.f10656a = r2     // Catch:{ JSONException -> 0x018c }
            com.xiaomi.smarthome.mibrain.model.NLPResultInfo$SingleDevice r1 = r3.d     // Catch:{ JSONException -> 0x018c }
            java.lang.String r2 = "image_url"
            java.lang.String r4 = ""
            java.lang.String r0 = r0.optString(r2, r4)     // Catch:{ JSONException -> 0x018c }
            r1.b = r0     // Catch:{ JSONException -> 0x018c }
            java.lang.String r0 = "intention"
            org.json.JSONObject r8 = r8.optJSONObject(r0)     // Catch:{ JSONException -> 0x018c }
            if (r8 != 0) goto L_0x00e5
            return r3
        L_0x00e5:
            java.lang.String r0 = "entity"
            java.lang.String r8 = r8.optString(r0)     // Catch:{ JSONException -> 0x018c }
            r3.g = r8     // Catch:{ JSONException -> 0x018c }
            return r3
        L_0x00ee:
            return r3
        L_0x00ef:
            int r1 = r3.f     // Catch:{ JSONException -> 0x018c }
            r4 = 1
            if (r1 != r4) goto L_0x0182
            java.lang.String r1 = "option"
            org.json.JSONArray r0 = r0.optJSONArray(r1)     // Catch:{ JSONException -> 0x018c }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ JSONException -> 0x018c }
            r1.<init>()     // Catch:{ JSONException -> 0x018c }
            r3.e = r1     // Catch:{ JSONException -> 0x018c }
            if (r0 == 0) goto L_0x0181
            int r1 = r0.length()     // Catch:{ JSONException -> 0x018c }
            if (r1 > 0) goto L_0x010b
            goto L_0x0181
        L_0x010b:
            java.lang.String r1 = "widgets"
            org.json.JSONArray r8 = r8.optJSONArray(r1)     // Catch:{ JSONException -> 0x018c }
            if (r8 == 0) goto L_0x0180
            int r1 = r8.length()     // Catch:{ JSONException -> 0x018c }
            if (r1 > 0) goto L_0x011a
            goto L_0x0180
        L_0x011a:
            int r1 = r0.length()     // Catch:{ JSONException -> 0x018c }
            if (r2 >= r1) goto L_0x017f
            java.lang.Object r1 = r0.get(r2)     // Catch:{ JSONException -> 0x018c }
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ JSONException -> 0x018c }
            if (r1 == 0) goto L_0x017c
            com.xiaomi.smarthome.mibrain.model.NLPResultInfo$DeviceOption r4 = new com.xiaomi.smarthome.mibrain.model.NLPResultInfo$DeviceOption     // Catch:{ JSONException -> 0x018c }
            r4.<init>()     // Catch:{ JSONException -> 0x018c }
            int r5 = r8.length()     // Catch:{ JSONException -> 0x018c }
            if (r2 >= r5) goto L_0x0155
            org.json.JSONObject r5 = r8.getJSONObject(r2)     // Catch:{ JSONException -> 0x018c }
            if (r5 == 0) goto L_0x0155
            java.lang.String r6 = "params"
            org.json.JSONObject r5 = r5.optJSONObject(r6)     // Catch:{ JSONException -> 0x018c }
            if (r5 == 0) goto L_0x0155
            java.lang.String r6 = "did"
            java.lang.String r7 = ""
            java.lang.String r6 = r5.optString(r6, r7)     // Catch:{ JSONException -> 0x018c }
            r4.c = r6     // Catch:{ JSONException -> 0x018c }
            java.lang.String r6 = "image_url"
            java.lang.String r7 = ""
            java.lang.String r5 = r5.optString(r6, r7)     // Catch:{ JSONException -> 0x018c }
            r4.d = r5     // Catch:{ JSONException -> 0x018c }
        L_0x0155:
            java.lang.String r5 = "text"
            java.lang.String r6 = ""
            java.lang.String r5 = r1.optString(r5, r6)     // Catch:{ JSONException -> 0x018c }
            r4.f10655a = r5     // Catch:{ JSONException -> 0x018c }
            java.lang.String r5 = "intention"
            org.json.JSONObject r1 = r1.getJSONObject(r5)     // Catch:{ JSONException -> 0x018c }
            if (r1 == 0) goto L_0x017c
            java.lang.String r5 = r1.toString()     // Catch:{ JSONException -> 0x018c }
            r4.b = r5     // Catch:{ JSONException -> 0x018c }
            java.util.List<com.xiaomi.smarthome.mibrain.model.NLPResultInfo$DeviceOption> r5 = r3.e     // Catch:{ JSONException -> 0x018c }
            r5.add(r4)     // Catch:{ JSONException -> 0x018c }
            if (r2 != 0) goto L_0x017c
            java.lang.String r4 = "entity"
            java.lang.String r1 = r1.optString(r4)     // Catch:{ JSONException -> 0x018c }
            r3.g = r1     // Catch:{ JSONException -> 0x018c }
        L_0x017c:
            int r2 = r2 + 1
            goto L_0x011a
        L_0x017f:
            return r3
        L_0x0180:
            return r3
        L_0x0181:
            return r3
        L_0x0182:
            java.lang.String r8 = "MiBrainManager"
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x018c }
            com.xiaomi.smarthome.miio.Miio.b(r8, r0)     // Catch:{ JSONException -> 0x018c }
            goto L_0x0193
        L_0x018c:
            r8 = move-exception
            goto L_0x0190
        L_0x018e:
            r8 = move-exception
            r3 = r1
        L_0x0190:
            r8.printStackTrace()
        L_0x0193:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.mibrain.model.NLPResultInfo.a(java.lang.String):com.xiaomi.smarthome.mibrain.model.NLPResultInfo");
    }

    public static class SingleDevice implements Parcelable {
        public static final Parcelable.Creator<SingleDevice> CREATOR = new Parcelable.Creator<SingleDevice>() {
            /* renamed from: a */
            public SingleDevice createFromParcel(Parcel parcel) {
                return new SingleDevice(parcel);
            }

            /* renamed from: a */
            public SingleDevice[] newArray(int i) {
                return new SingleDevice[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public String f10656a;
        public String b;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f10656a);
            parcel.writeString(this.b);
        }

        public SingleDevice() {
        }

        protected SingleDevice(Parcel parcel) {
            this.f10656a = parcel.readString();
            this.b = parcel.readString();
        }

        public String toString() {
            return "SingleDevice{did='" + this.f10656a + Operators.SINGLE_QUOTE + ", imgUrl='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public static class DeviceOption implements Parcelable {
        public static final Parcelable.Creator<DeviceOption> CREATOR = new Parcelable.Creator<DeviceOption>() {
            /* renamed from: a */
            public DeviceOption createFromParcel(Parcel parcel) {
                return new DeviceOption(parcel);
            }

            /* renamed from: a */
            public DeviceOption[] newArray(int i) {
                return new DeviceOption[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public String f10655a;
        public String b;
        public String c;
        public String d;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f10655a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
        }

        public DeviceOption() {
        }

        protected DeviceOption(Parcel parcel) {
            this.f10655a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
        }

        public String toString() {
            return "DeviceOption{name='" + this.f10655a + Operators.SINGLE_QUOTE + ", intention='" + this.b + Operators.SINGLE_QUOTE + ", did='" + this.c + Operators.SINGLE_QUOTE + ", imgUrl='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.d, i);
        parcel.writeList(this.e);
        parcel.writeInt(this.f);
    }

    public NLPResultInfo() {
    }

    protected NLPResultInfo(Parcel parcel) {
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = (SingleDevice) parcel.readParcelable(SingleDevice.class.getClassLoader());
        this.e = new ArrayList();
        parcel.readList(this.e, DeviceOption.class.getClassLoader());
        this.f = parcel.readInt();
    }

    public String toString() {
        return "NLPResultInfo{mAction='" + this.b + Operators.SINGLE_QUOTE + ", mToSpeakText='" + this.c + Operators.SINGLE_QUOTE + ", mSingleDevice=" + this.d + ", mDeviceOptions=" + this.e + ", type=" + this.f + Operators.BLOCK_END;
    }
}
