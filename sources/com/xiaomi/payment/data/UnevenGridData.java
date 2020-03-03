package com.xiaomi.payment.data;

import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UnevenGridData {

    /* renamed from: a  reason: collision with root package name */
    public int f12232a;
    public ArrayList<SingleGridItemInfo> b = new ArrayList<>();

    public void a(JSONObject jSONObject) throws PaymentException {
        if (jSONObject != null) {
            try {
                this.f12232a = jSONObject.getInt(MibiConstants.ei);
                JSONArray optJSONArray = jSONObject.optJSONArray(MibiConstants.ej);
                if (optJSONArray != null) {
                    int i = 0;
                    while (i < optJSONArray.length()) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        int i2 = jSONObject2.getInt("type");
                        if (SingleGridItemInfo.a(i2)) {
                            SingleGridItemInfo a2 = a(i2, jSONObject2);
                            if (a2 != null) {
                                this.b.add(a2);
                            }
                            i++;
                        } else {
                            throw new ResultException("item type is invalid, type is " + i2);
                        }
                    }
                }
            } catch (JSONException e) {
                throw new ResultException((Throwable) e);
            }
        }
    }

    private SingleGridItemInfo a(int i, JSONObject jSONObject) throws PaymentException, JSONException {
        SingleGridItemInfo singleGridItemInfo;
        if (i == 0) {
            singleGridItemInfo = new NormalGridItemInfo();
            jSONObject = jSONObject.getJSONObject("data");
        } else {
            singleGridItemInfo = i == 1 ? new BannerGridItemInfo() : null;
        }
        if (singleGridItemInfo == null) {
            return null;
        }
        if (singleGridItemInfo.a(jSONObject)) {
            return singleGridItemInfo;
        }
        return null;
    }

    public static abstract class SingleGridItemInfo {
        public static final int e = 0;
        public static final int f = 1;
        public int g;
        public int h;
        public int i;

        public static boolean a(int i2) {
            return i2 == 0 || i2 == 1;
        }

        public abstract boolean a(JSONObject jSONObject) throws JSONException, PaymentException;

        public SingleGridItemInfo(int i2) {
            this.g = i2;
        }
    }

    public static class NormalGridItemInfo extends SingleGridItemInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f12234a;
        public String b;
        public String c;
        public EntryData d;

        public NormalGridItemInfo() {
            super(0);
        }

        public boolean a(JSONObject jSONObject) throws JSONException, PaymentException {
            this.f12234a = jSONObject.optString("title");
            this.b = jSONObject.optString(MibiConstants.ee);
            this.c = jSONObject.getString("image");
            this.h = jSONObject.optInt(MibiConstants.eg);
            this.i = jSONObject.optInt(MibiConstants.eh);
            JSONObject jSONObject2 = jSONObject.getJSONObject("entry");
            if (jSONObject2 == null) {
                return false;
            }
            this.d = new EntryData();
            this.d.parseEntryData(jSONObject2);
            return true;
        }
    }

    public static class BannerGridItemInfo extends SingleGridItemInfo {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<NormalGridItemInfo> f12233a = new ArrayList<>();

        public BannerGridItemInfo() {
            super(1);
        }

        public boolean a(JSONObject jSONObject) throws JSONException, PaymentException {
            this.h = jSONObject.optInt(MibiConstants.eg);
            this.i = jSONObject.optInt(MibiConstants.eh);
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            if (jSONArray == null || jSONArray.length() <= 0) {
                return false;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                NormalGridItemInfo normalGridItemInfo = new NormalGridItemInfo();
                normalGridItemInfo.a(jSONObject2);
                this.f12233a.add(normalGridItemInfo);
            }
            return true;
        }
    }
}
