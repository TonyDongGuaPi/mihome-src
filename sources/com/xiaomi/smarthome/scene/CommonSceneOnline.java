package com.xiaomi.smarthome.scene;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommonSceneOnline {

    /* renamed from: a  reason: collision with root package name */
    public String f21198a;
    public String b;
    public String c;
    public String d;
    public ArrayList<CommonSceneCondition> e = new ArrayList<>();
    public ArrayList<CommonSceneAction> f = new ArrayList<>();
    public ArrayList<GroupInfo> g = new ArrayList<>();
    public ArrayList<GroupInfo> h = new ArrayList<>();

    public static class GroupInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f21201a;
        public int b;
    }

    public static class SceneAttr {

        /* renamed from: a  reason: collision with root package name */
        int f21203a;
    }

    public static class SceneAttrFencing extends SceneAttr {
        public float b;
        public float c;
        public float d;
        public String e;
        public String f;
        public String g;
    }

    public static class SceneAttrNumberPicker extends SceneAttr {
        public float b;
        public float c;
        public float d;
        public String e;
        public String f;
        public String g;
        public float h;
        public ArrayList<NumberPickerTag> i = new ArrayList<>();
    }

    public static class NumberPickerTag implements Parcelable {
        public static final Parcelable.Creator<NumberPickerTag> CREATOR = new Parcelable.Creator<NumberPickerTag>() {
            /* renamed from: a */
            public NumberPickerTag createFromParcel(Parcel parcel) {
                return new NumberPickerTag(parcel);
            }

            /* renamed from: a */
            public NumberPickerTag[] newArray(int i) {
                return new NumberPickerTag[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public float f21202a;
        public float b;
        public String c;

        public int describeContents() {
            return 0;
        }

        public NumberPickerTag() {
        }

        protected NumberPickerTag(Parcel parcel) {
            this.f21202a = parcel.readFloat();
            this.b = parcel.readFloat();
            this.c = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.f21202a);
            parcel.writeFloat(this.b);
            parcel.writeString(this.c);
        }
    }

    public static class CommonSceneCondition {

        /* renamed from: a  reason: collision with root package name */
        public String f21200a;
        public int b;
        public int c = -1;
        public int d = -1;
        public String e;
        public String f;
        public Object g;
        public String h;
        public SceneAttr i;

        public static CommonSceneCondition a(JSONObject jSONObject) {
            JSONObject optJSONObject;
            CommonSceneCondition commonSceneCondition = new CommonSceneCondition();
            commonSceneCondition.f21200a = jSONObject.optString("name");
            commonSceneCondition.f = jSONObject.optString("key");
            commonSceneCondition.d = jSONObject.optInt("tr_id");
            commonSceneCondition.b = jSONObject.optInt("sc_id");
            commonSceneCondition.g = jSONObject.opt("value");
            commonSceneCondition.h = jSONObject.optString("plug_id");
            if (jSONObject.has("groupInfo") && (optJSONObject = jSONObject.optJSONObject("groupInfo")) != null && optJSONObject.has("id")) {
                commonSceneCondition.c = optJSONObject.optInt("id", -1);
                commonSceneCondition.e = optJSONObject.optString("intro");
            }
            if (jSONObject.has(RichTextNode.ATTR)) {
                JSONObject optJSONObject2 = jSONObject.optJSONObject(RichTextNode.ATTR);
                int optInt = optJSONObject2.optInt("attr_id");
                if (optInt == 1001) {
                    SceneAttrNumberPicker sceneAttrNumberPicker = new SceneAttrNumberPicker();
                    sceneAttrNumberPicker.f21203a = optInt;
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("params");
                    sceneAttrNumberPicker.b = (float) optJSONObject3.optDouble("max_val");
                    sceneAttrNumberPicker.c = (float) optJSONObject3.optDouble("min_val");
                    sceneAttrNumberPicker.d = (float) optJSONObject3.optDouble(Constants.Name.INTERVAL);
                    sceneAttrNumberPicker.e = optJSONObject3.optString("degree");
                    sceneAttrNumberPicker.f = optJSONObject3.optString("json_val_tag");
                    sceneAttrNumberPicker.g = optJSONObject3.optString("display_sub_title");
                    sceneAttrNumberPicker.h = (float) optJSONObject3.optDouble("default_val");
                    if (optJSONObject3.has("show_tags")) {
                        JSONArray optJSONArray = optJSONObject3.optJSONArray("show_tags");
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            NumberPickerTag numberPickerTag = new NumberPickerTag();
                            numberPickerTag.f21202a = (float) optJSONArray.optJSONObject(i2).optDouble("from");
                            numberPickerTag.b = (float) optJSONArray.optJSONObject(i2).optDouble("to");
                            numberPickerTag.c = optJSONArray.optJSONObject(i2).optString("tag");
                            sceneAttrNumberPicker.i.add(numberPickerTag);
                        }
                    }
                    commonSceneCondition.i = sceneAttrNumberPicker;
                } else if (optInt == 1002) {
                    SceneAttrFencing sceneAttrFencing = new SceneAttrFencing();
                    sceneAttrFencing.f21203a = optInt;
                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("params");
                    sceneAttrFencing.e = optJSONObject4.optString("display_sub_title");
                    sceneAttrFencing.g = optJSONObject4.optString("act_name");
                    sceneAttrFencing.f = optJSONObject4.optString("radius_degree");
                    commonSceneCondition.i = sceneAttrFencing;
                }
            }
            if (jSONObject.has("attr_new")) {
                JSONObject optJSONObject5 = jSONObject.optJSONObject(RichTextNode.ATTR);
                int optInt2 = optJSONObject5.optInt("attr_id");
                if (optInt2 == 1001) {
                    SceneAttrNumberPicker sceneAttrNumberPicker2 = new SceneAttrNumberPicker();
                    sceneAttrNumberPicker2.f21203a = optInt2;
                    JSONObject optJSONObject6 = optJSONObject5.optJSONObject("params");
                    sceneAttrNumberPicker2.b = (float) optJSONObject6.optDouble("max_val");
                    sceneAttrNumberPicker2.c = (float) optJSONObject6.optDouble("min_val");
                    sceneAttrNumberPicker2.d = (float) optJSONObject6.optDouble(Constants.Name.INTERVAL);
                    sceneAttrNumberPicker2.e = optJSONObject6.optString("degree");
                    sceneAttrNumberPicker2.f = optJSONObject6.optString("json_val_tag");
                    sceneAttrNumberPicker2.g = optJSONObject6.optString("display_sub_title");
                    sceneAttrNumberPicker2.h = (float) optJSONObject6.optDouble("default_val");
                    if (optJSONObject6.has("show_tags")) {
                        JSONArray optJSONArray2 = optJSONObject6.optJSONArray("show_tags");
                        for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                            NumberPickerTag numberPickerTag2 = new NumberPickerTag();
                            numberPickerTag2.f21202a = (float) optJSONArray2.optJSONObject(i3).optDouble("from");
                            numberPickerTag2.b = (float) optJSONArray2.optJSONObject(i3).optDouble("to");
                            numberPickerTag2.c = optJSONArray2.optJSONObject(i3).optString("tag");
                            sceneAttrNumberPicker2.i.add(numberPickerTag2);
                        }
                    }
                    commonSceneCondition.i = sceneAttrNumberPicker2;
                } else if (optInt2 == 1002) {
                    SceneAttrFencing sceneAttrFencing2 = new SceneAttrFencing();
                    sceneAttrFencing2.f21203a = optInt2;
                    JSONObject optJSONObject7 = optJSONObject5.optJSONObject("params");
                    sceneAttrFencing2.e = optJSONObject7.optString("display_sub_title");
                    sceneAttrFencing2.g = optJSONObject7.optString("act_name");
                    sceneAttrFencing2.f = optJSONObject7.optString("radius_degree");
                    commonSceneCondition.i = sceneAttrFencing2;
                }
            }
            return commonSceneCondition;
        }
    }

    public static class CommonSceneAction {

        /* renamed from: a  reason: collision with root package name */
        public String f21199a;
        public int b;
        public int c = -1;
        public String d;
        public String e;
        public Object f;
        public String g;
        public int h;
        public SceneAttr i;

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x006c, code lost:
            r6 = r1.optJSONObject(com.taobao.weex.ui.component.richtext.node.RichTextNode.ATTR);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction a(org.json.JSONObject r6) {
            /*
                com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction r0 = new com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction
                r0.<init>()
                java.lang.String r1 = "name"
                java.lang.String r1 = r6.optString(r1)
                r0.f21199a = r1
                java.lang.String r1 = "sa_id"
                int r1 = r6.optInt(r1)
                r0.b = r1
                java.lang.String r1 = "payload"
                org.json.JSONObject r1 = r6.optJSONObject(r1)
                java.lang.String r2 = "command"
                java.lang.String r2 = r1.optString(r2)
                r0.e = r2
                java.lang.String r2 = "value"
                java.lang.Object r2 = r1.opt(r2)
                r0.f = r2
                java.lang.String r2 = "plug_id"
                java.lang.String r2 = r1.optString(r2)
                r0.g = r2
                java.lang.String r2 = "tr_id"
                int r2 = r6.optInt(r2)
                r0.h = r2
                java.lang.String r2 = "groupInfo"
                boolean r2 = r6.has(r2)
                if (r2 == 0) goto L_0x0064
                java.lang.String r2 = "groupInfo"
                org.json.JSONObject r6 = r6.optJSONObject(r2)
                if (r6 == 0) goto L_0x0064
                java.lang.String r2 = "id"
                boolean r2 = r6.has(r2)
                if (r2 == 0) goto L_0x0064
                java.lang.String r2 = "id"
                r3 = -1
                int r2 = r6.optInt(r2, r3)
                r0.c = r2
                java.lang.String r2 = "intro"
                java.lang.String r6 = r6.optString(r2)
                r0.d = r6
            L_0x0064:
                java.lang.String r6 = "attr"
                boolean r6 = r1.has(r6)
                if (r6 == 0) goto L_0x0107
                java.lang.String r6 = "attr"
                org.json.JSONObject r6 = r1.optJSONObject(r6)
                java.lang.String r1 = "attr_id"
                int r1 = r6.optInt(r1)
                r2 = 2001(0x7d1, float:2.804E-42)
                if (r1 != r2) goto L_0x0107
                com.xiaomi.smarthome.scene.CommonSceneOnline$SceneAttrNumberPicker r2 = new com.xiaomi.smarthome.scene.CommonSceneOnline$SceneAttrNumberPicker
                r2.<init>()
                r2.f21203a = r1
                java.lang.String r1 = "params"
                org.json.JSONObject r1 = r6.optJSONObject(r1)
                java.lang.String r3 = "max_val"
                double r3 = r1.optDouble(r3)
                float r3 = (float) r3
                r2.b = r3
                java.lang.String r3 = "min_val"
                double r3 = r1.optDouble(r3)
                float r3 = (float) r3
                r2.c = r3
                java.lang.String r3 = "interval"
                double r3 = r1.optDouble(r3)
                float r3 = (float) r3
                r2.d = r3
                java.lang.String r3 = "degree"
                java.lang.String r3 = r1.optString(r3)
                r2.e = r3
                java.lang.String r3 = "json_val_tag"
                java.lang.String r3 = r1.optString(r3)
                r2.f = r3
                java.lang.String r3 = "default_val"
                double r3 = r1.optDouble(r3)
                float r1 = (float) r3
                r2.h = r1
                java.lang.String r1 = "show_tags"
                boolean r1 = r6.has(r1)
                if (r1 == 0) goto L_0x0105
                java.lang.String r1 = "show_tags"
                org.json.JSONArray r6 = r6.optJSONArray(r1)
                r1 = 0
            L_0x00cc:
                int r3 = r6.length()
                if (r1 >= r3) goto L_0x0105
                com.xiaomi.smarthome.scene.CommonSceneOnline$NumberPickerTag r3 = new com.xiaomi.smarthome.scene.CommonSceneOnline$NumberPickerTag
                r3.<init>()
                org.json.JSONObject r4 = r6.optJSONObject(r1)
                java.lang.String r5 = "from"
                double r4 = r4.optDouble(r5)
                float r4 = (float) r4
                r3.f21202a = r4
                org.json.JSONObject r4 = r6.optJSONObject(r1)
                java.lang.String r5 = "to"
                double r4 = r4.optDouble(r5)
                float r4 = (float) r4
                r3.b = r4
                org.json.JSONObject r4 = r6.optJSONObject(r1)
                java.lang.String r5 = "tag"
                java.lang.String r4 = r4.optString(r5)
                r3.c = r4
                java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$NumberPickerTag> r4 = r2.i
                r4.add(r3)
                int r1 = r1 + 1
                goto L_0x00cc
            L_0x0105:
                r0.i = r2
            L_0x0107:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction.a(org.json.JSONObject):com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction");
        }
    }

    public static CommonSceneOnline a(JSONObject jSONObject) {
        CommonSceneOnline commonSceneOnline = new CommonSceneOnline();
        commonSceneOnline.f21198a = jSONObject.optString("model");
        commonSceneOnline.b = jSONObject.optString("did");
        commonSceneOnline.c = jSONObject.optString("ptName");
        JSONObject optJSONObject = jSONObject.optJSONObject("value");
        if (optJSONObject != null) {
            if (optJSONObject.has("launch")) {
                JSONArray optJSONArray = optJSONObject.optJSONArray("launch");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    commonSceneOnline.e.add(CommonSceneCondition.a(optJSONArray.optJSONObject(i)));
                }
            }
            if (optJSONObject.has("action_list")) {
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("action_list");
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    commonSceneOnline.f.add(CommonSceneAction.a(optJSONArray2.optJSONObject(i2)));
                }
            }
        }
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < commonSceneOnline.e.size(); i3++) {
            if (commonSceneOnline.e.get(i3).c != -1 && !hashMap.containsKey(Integer.valueOf(commonSceneOnline.e.get(i3).c))) {
                GroupInfo groupInfo = new GroupInfo();
                groupInfo.f21201a = commonSceneOnline.e.get(i3).e;
                groupInfo.b = commonSceneOnline.e.get(i3).c;
                commonSceneOnline.g.add(groupInfo);
                hashMap.put(Integer.valueOf(groupInfo.b), groupInfo.f21201a);
            }
        }
        hashMap.clear();
        for (int i4 = 0; i4 < commonSceneOnline.f.size(); i4++) {
            if (commonSceneOnline.f.get(i4).c != -1 && !hashMap.containsKey(Integer.valueOf(commonSceneOnline.f.get(i4).c))) {
                GroupInfo groupInfo2 = new GroupInfo();
                groupInfo2.f21201a = commonSceneOnline.f.get(i4).d;
                groupInfo2.b = commonSceneOnline.f.get(i4).c;
                commonSceneOnline.h.add(groupInfo2);
                hashMap.put(Integer.valueOf(groupInfo2.b), groupInfo2.f21201a);
            }
        }
        return commonSceneOnline;
    }
}
