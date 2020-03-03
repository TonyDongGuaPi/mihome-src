package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginRecommendSceneInfo {
    public static final String KEY_PLUGIN_REC = "is_plugin_main_page_show";
    public static final String SP_NAME = "plugin_scene_rec";
    public boolean hasScene = false;
    public JSONObject mActionSaIds = new JSONObject();
    public Map<Integer, CommonSceneAction> mCommonActions = new HashMap();
    public Map<Integer, CommonSceneCondition> mCommonConditions = new HashMap();
    public JSONObject mConditionScIds = new JSONObject();
    public List<RecommendSceneItem> mSceneItems = new ArrayList();

    public static class SceneAttr {
        int attrId;
        public String subTitle;
    }

    public static class SceneAttrFencing extends SceneAttr {
        public String actName;
        public float latitude;
        public float longtitude;
        public float radius;
        public String radiusDegree;
    }

    public static class SceneAttrNumberPicker extends SceneAttr {
        public float defaultValue;
        public String degree;
        public float interval;
        public String jsonTag;
        public float maxValue;
        public float minValue;
        public ArrayList<NumberPickerTag> showTags = new ArrayList<>();
    }

    /* access modifiers changed from: private */
    public static boolean isSupportAndroid(int i) {
        return i == 0 || i == 1;
    }

    public static PluginRecommendSceneInfo parse(JSONObject jSONObject) {
        PluginRecommendSceneInfo pluginRecommendSceneInfo = new PluginRecommendSceneInfo();
        JSONArray optJSONArray = jSONObject.optJSONArray("scene_recom");
        pluginRecommendSceneInfo.hasScene = jSONObject.optBoolean("has_scene_already");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null) {
            return pluginRecommendSceneInfo;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i).optJSONObject("info");
            if (optJSONObject != null) {
                RecommendSceneItem recommendSceneItem = new RecommendSceneItem();
                recommendSceneItem.entryDesc = optJSONArray.optJSONObject(i).optString("entry_desc");
                recommendSceneItem.cardDesc = optJSONArray.optJSONObject(i).optString("desc");
                recommendSceneItem.needDelay = optJSONArray.optJSONObject(i).optBoolean("need_delay", false);
                recommendSceneItem.cardImgUrl = optJSONArray.optJSONObject(i).optString("img_url");
                recommendSceneItem.ua = optJSONObject.optInt("ua", -1);
                if (isSupportAndroid(recommendSceneItem.ua)) {
                    recommendSceneItem.sr_id = optJSONObject.optString("sr_id");
                    recommendSceneItem.intro = optJSONObject.optString("intro");
                    if (TextUtils.isEmpty(recommendSceneItem.intro)) {
                        recommendSceneItem.intro = optJSONArray.optJSONObject(i).optString("title");
                    }
                    recommendSceneItem.st_id = optJSONObject.optInt("st_id");
                    recommendSceneItem.enable = optJSONObject.optInt(AutoSceneAction.f21495a);
                    recommendSceneItem.enable_push = optJSONObject.optInt("enable_push");
                    recommendSceneItem.url = optJSONObject.optString("jpg", "");
                    recommendSceneItem.gifUrl = optJSONObject.optString("gif");
                    recommendSceneItem.isConditionDevice = TextUtils.equals("trigger", optJSONObject.optString("trigger_or_action"));
                    recommendSceneItem.isActionDevice = TextUtils.equals("action", optJSONObject.optString("trigger_or_action"));
                    recommendSceneItem.selectActionHint = optJSONArray.optJSONObject(i).optString("action_device_name");
                    recommendSceneItem.selectConditionHint = optJSONArray.optJSONObject(i).optString("trigger_device_name");
                    recommendSceneItem.selectDeviceHint = optJSONArray.optJSONObject(i).optString("select_device");
                    recommendSceneItem.selectValueHint = optJSONArray.optJSONObject(i).optString("select_value");
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("launch");
                    if (optJSONArray2 != null) {
                        recommendSceneItem.mConditionList.addAll(ConditionActionItem.parseList(optJSONArray2));
                    }
                    JSONArray optJSONArray3 = optJSONObject.optJSONArray("action");
                    if (optJSONArray3 != null) {
                        recommendSceneItem.mActionList.addAll(ConditionActionItem.parseList(optJSONArray3));
                    }
                    arrayList.add(recommendSceneItem);
                }
            }
        }
        pluginRecommendSceneInfo.mSceneItems.addAll(arrayList);
        pluginRecommendSceneInfo.mConditionScIds = jSONObject.optJSONObject("sc_ids");
        Iterator<String> it = null;
        Iterator<String> keys = pluginRecommendSceneInfo.mConditionScIds == null ? null : pluginRecommendSceneInfo.mConditionScIds.keys();
        while (keys != null && keys.hasNext()) {
            String next = keys.next();
            CommonSceneCondition parseFromJSON = CommonSceneCondition.parseFromJSON(pluginRecommendSceneInfo.mConditionScIds.optJSONObject(next));
            if (parseFromJSON != null) {
                try {
                    parseFromJSON.id = Integer.parseInt(next);
                } catch (Exception unused) {
                }
                if (parseFromJSON.id > 0) {
                    pluginRecommendSceneInfo.mCommonConditions.put(Integer.valueOf(parseFromJSON.id), parseFromJSON);
                }
            }
        }
        pluginRecommendSceneInfo.mActionSaIds = jSONObject.optJSONObject("sa_ids");
        if (pluginRecommendSceneInfo.mActionSaIds != null) {
            it = pluginRecommendSceneInfo.mActionSaIds.keys();
        }
        while (it != null && it.hasNext()) {
            String next2 = it.next();
            CommonSceneAction parseFromJSON2 = CommonSceneAction.parseFromJSON(pluginRecommendSceneInfo.mActionSaIds.optJSONObject(next2));
            if (parseFromJSON2 != null) {
                try {
                    parseFromJSON2.id = Integer.parseInt(next2);
                } catch (Exception unused2) {
                }
                if (parseFromJSON2.id > 0) {
                    pluginRecommendSceneInfo.mCommonActions.put(Integer.valueOf(parseFromJSON2.id), parseFromJSON2);
                }
            }
        }
        return pluginRecommendSceneInfo;
    }

    public static class RecommendSceneItem {
        public String cardDesc;
        public String cardImgUrl;
        public int enable;
        public int enable_push;
        public String entryDesc;
        public String gifUrl;
        public String intro;
        public boolean isActionDevice;
        public boolean isConditionDevice;
        public List<ConditionActionItem> mActionList = new ArrayList();
        public List<RecommendBuy> mBuyLinks = new ArrayList();
        public Map<Integer, CommonSceneAction> mCommonActions = new HashMap();
        public Map<Integer, CommonSceneCondition> mCommonConditions = new HashMap();
        public List<ConditionActionItem> mConditionList = new ArrayList();
        public boolean needDelay = false;
        public String selectActionHint;
        public String selectConditionHint;
        public String selectDeviceHint;
        public String selectValueHint;
        public String smallImgUrl;
        public String sr_id;
        public int st_id;
        public String timeSpan;
        public int ua;
        public String url;

        public static RecommendSceneItem parseFrom(String str) {
            RecommendSceneItem recommendSceneItem = new RecommendSceneItem();
            try {
                JSONObject jSONObject = new JSONObject(str);
                recommendSceneItem.ua = jSONObject.optInt("ua", -1);
                if (!PluginRecommendSceneInfo.isSupportAndroid(recommendSceneItem.ua)) {
                    return null;
                }
                recommendSceneItem.sr_id = jSONObject.optString("sr_id");
                recommendSceneItem.intro = jSONObject.optString("intro");
                recommendSceneItem.entryDesc = jSONObject.optString("entry_desc");
                recommendSceneItem.smallImgUrl = jSONObject.optString("small_img_url");
                recommendSceneItem.st_id = jSONObject.optInt("st_id");
                recommendSceneItem.enable = jSONObject.optInt(AutoSceneAction.f21495a);
                recommendSceneItem.enable_push = jSONObject.optInt("enable_push");
                recommendSceneItem.url = jSONObject.optString("jpg", "");
                recommendSceneItem.gifUrl = jSONObject.optString("gif");
                JSONArray optJSONArray = jSONObject.optJSONArray("launch");
                if (optJSONArray != null) {
                    recommendSceneItem.mConditionList.addAll(ConditionActionItem.parseList(optJSONArray));
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("action");
                if (optJSONArray2 != null) {
                    recommendSceneItem.mActionList.addAll(ConditionActionItem.parseList(optJSONArray2));
                }
                return recommendSceneItem;
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public static class ConditionActionItem {
        public int actionType;
        public String mConditionKey;
        public String mConditionSrc;
        public JSONArray mGidJArray = new JSONArray();
        public JSONObject modelListJobj = new JSONObject();
        public String name;

        public static List<ConditionActionItem> parseList(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray == null || jSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                ConditionActionItem conditionActionItem = new ConditionActionItem();
                conditionActionItem.name = optJSONObject.optString("name", "");
                conditionActionItem.modelListJobj = optJSONObject.optJSONObject("model_list");
                conditionActionItem.mGidJArray = optJSONObject.optJSONArray(ApiConst.j);
                conditionActionItem.actionType = optJSONObject.optInt("type", 0);
                conditionActionItem.mConditionSrc = optJSONObject.optString("src", "");
                conditionActionItem.mConditionKey = optJSONObject.optString("key", "");
                arrayList.add(conditionActionItem);
            }
            return arrayList;
        }
    }

    public static class NumberPickerTag implements Parcelable {
        public static final Parcelable.Creator<NumberPickerTag> CREATOR = new Parcelable.Creator<NumberPickerTag>() {
            public NumberPickerTag createFromParcel(Parcel parcel) {
                return new NumberPickerTag(parcel);
            }

            public NumberPickerTag[] newArray(int i) {
                return new NumberPickerTag[i];
            }
        };
        public float from;
        public String tag;
        public float to;

        public int describeContents() {
            return 0;
        }

        public NumberPickerTag() {
        }

        protected NumberPickerTag(Parcel parcel) {
            this.from = parcel.readFloat();
            this.to = parcel.readFloat();
            this.tag = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.from);
            parcel.writeFloat(this.to);
            parcel.writeString(this.tag);
        }
    }

    public static class CommonSceneCondition {
        public int id;
        public SceneAttr mAttr;
        public int mCompatibleId = -1;
        public int mGroupId = -1;
        public String mGroupName;
        public String mKey;
        public String mName;
        public String mParamAction;
        public Object mValue;

        public static CommonSceneCondition parseFromJSON(JSONObject jSONObject) {
            JSONObject optJSONObject;
            CommonSceneCondition commonSceneCondition = new CommonSceneCondition();
            commonSceneCondition.mName = jSONObject.optString("name");
            commonSceneCondition.mKey = jSONObject.optString("key");
            commonSceneCondition.mCompatibleId = jSONObject.optInt("tr_id");
            commonSceneCondition.id = jSONObject.optInt("sc_id");
            commonSceneCondition.mValue = jSONObject.opt("value");
            commonSceneCondition.mParamAction = jSONObject.optString("plug_id");
            if (jSONObject.has("groupInfo") && (optJSONObject = jSONObject.optJSONObject("groupInfo")) != null && optJSONObject.has("id")) {
                commonSceneCondition.mGroupId = optJSONObject.optInt("id", -1);
                commonSceneCondition.mGroupName = optJSONObject.optString("intro");
            }
            if (jSONObject.has(RichTextNode.ATTR)) {
                JSONObject optJSONObject2 = jSONObject.optJSONObject(RichTextNode.ATTR);
                int optInt = optJSONObject2.optInt("attr_id");
                if (optInt == 1001) {
                    SceneAttrNumberPicker sceneAttrNumberPicker = new SceneAttrNumberPicker();
                    sceneAttrNumberPicker.attrId = optInt;
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("params");
                    sceneAttrNumberPicker.maxValue = (float) optJSONObject3.optDouble("max_val");
                    sceneAttrNumberPicker.minValue = (float) optJSONObject3.optDouble("min_val");
                    sceneAttrNumberPicker.interval = (float) optJSONObject3.optDouble(Constants.Name.INTERVAL);
                    sceneAttrNumberPicker.degree = optJSONObject3.optString("degree");
                    sceneAttrNumberPicker.jsonTag = optJSONObject3.optString("json_val_tag");
                    sceneAttrNumberPicker.subTitle = optJSONObject3.optString("display_sub_title");
                    sceneAttrNumberPicker.defaultValue = (float) optJSONObject3.optDouble("default_val");
                    if (optJSONObject3.has("show_tags")) {
                        JSONArray optJSONArray = optJSONObject3.optJSONArray("show_tags");
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            NumberPickerTag numberPickerTag = new NumberPickerTag();
                            numberPickerTag.from = (float) optJSONArray.optJSONObject(i).optDouble("from");
                            numberPickerTag.to = (float) optJSONArray.optJSONObject(i).optDouble("to");
                            numberPickerTag.tag = optJSONArray.optJSONObject(i).optString("tag");
                            sceneAttrNumberPicker.showTags.add(numberPickerTag);
                        }
                    }
                    commonSceneCondition.mAttr = sceneAttrNumberPicker;
                } else if (optInt == 1002) {
                    SceneAttrFencing sceneAttrFencing = new SceneAttrFencing();
                    sceneAttrFencing.attrId = optInt;
                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("params");
                    sceneAttrFencing.subTitle = optJSONObject4.optString("display_sub_title");
                    sceneAttrFencing.actName = optJSONObject4.optString("act_name");
                    sceneAttrFencing.radiusDegree = optJSONObject4.optString("radius_degree");
                    commonSceneCondition.mAttr = sceneAttrFencing;
                }
            }
            if (jSONObject.has("attr_new")) {
                JSONObject optJSONObject5 = jSONObject.optJSONObject(RichTextNode.ATTR);
                int optInt2 = optJSONObject5.optInt("attr_id");
                if (optInt2 == 1001) {
                    SceneAttrNumberPicker sceneAttrNumberPicker2 = new SceneAttrNumberPicker();
                    sceneAttrNumberPicker2.attrId = optInt2;
                    JSONObject optJSONObject6 = optJSONObject5.optJSONObject("params");
                    sceneAttrNumberPicker2.maxValue = (float) optJSONObject6.optDouble("max_val");
                    sceneAttrNumberPicker2.minValue = (float) optJSONObject6.optDouble("min_val");
                    sceneAttrNumberPicker2.interval = (float) optJSONObject6.optDouble(Constants.Name.INTERVAL);
                    sceneAttrNumberPicker2.degree = optJSONObject6.optString("degree");
                    sceneAttrNumberPicker2.jsonTag = optJSONObject6.optString("json_val_tag");
                    sceneAttrNumberPicker2.subTitle = optJSONObject6.optString("display_sub_title");
                    sceneAttrNumberPicker2.defaultValue = (float) optJSONObject6.optDouble("default_val");
                    if (optJSONObject6.has("show_tags")) {
                        JSONArray optJSONArray2 = optJSONObject6.optJSONArray("show_tags");
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            NumberPickerTag numberPickerTag2 = new NumberPickerTag();
                            numberPickerTag2.from = (float) optJSONArray2.optJSONObject(i2).optDouble("from");
                            numberPickerTag2.to = (float) optJSONArray2.optJSONObject(i2).optDouble("to");
                            numberPickerTag2.tag = optJSONArray2.optJSONObject(i2).optString("tag");
                            sceneAttrNumberPicker2.showTags.add(numberPickerTag2);
                        }
                    }
                    commonSceneCondition.mAttr = sceneAttrNumberPicker2;
                } else if (optInt2 == 1002) {
                    SceneAttrFencing sceneAttrFencing2 = new SceneAttrFencing();
                    sceneAttrFencing2.attrId = optInt2;
                    JSONObject optJSONObject7 = optJSONObject5.optJSONObject("params");
                    sceneAttrFencing2.subTitle = optJSONObject7.optString("display_sub_title");
                    sceneAttrFencing2.actName = optJSONObject7.optString("act_name");
                    sceneAttrFencing2.radiusDegree = optJSONObject7.optString("radius_degree");
                    commonSceneCondition.mAttr = sceneAttrFencing2;
                }
            }
            return commonSceneCondition;
        }
    }

    public static class CommonSceneAction {
        public int id;
        public SceneAttr mAttr;
        public String mCommand;
        public int mCompatibleId;
        public int mGroupId = -1;
        public String mGroupName;
        public String mName;
        public String mParamAction;
        public Object mValue;

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x006c, code lost:
            r6 = r1.optJSONObject(com.taobao.weex.ui.component.richtext.node.RichTextNode.ATTR);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneAction parseFromJSON(org.json.JSONObject r6) {
            /*
                com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction r0 = new com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction
                r0.<init>()
                java.lang.String r1 = "name"
                java.lang.String r1 = r6.optString(r1)
                r0.mName = r1
                java.lang.String r1 = "sa_id"
                int r1 = r6.optInt(r1)
                r0.id = r1
                java.lang.String r1 = "payload"
                org.json.JSONObject r1 = r6.optJSONObject(r1)
                java.lang.String r2 = "command"
                java.lang.String r2 = r1.optString(r2)
                r0.mCommand = r2
                java.lang.String r2 = "value"
                java.lang.Object r2 = r1.opt(r2)
                r0.mValue = r2
                java.lang.String r2 = "plug_id"
                java.lang.String r2 = r1.optString(r2)
                r0.mParamAction = r2
                java.lang.String r2 = "tr_id"
                int r2 = r6.optInt(r2)
                r0.mCompatibleId = r2
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
                r0.mGroupId = r2
                java.lang.String r2 = "intro"
                java.lang.String r6 = r6.optString(r2)
                r0.mGroupName = r6
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
                com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttrNumberPicker r2 = new com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttrNumberPicker
                r2.<init>()
                r2.attrId = r1
                java.lang.String r1 = "params"
                org.json.JSONObject r1 = r6.optJSONObject(r1)
                java.lang.String r3 = "max_val"
                double r3 = r1.optDouble(r3)
                float r3 = (float) r3
                r2.maxValue = r3
                java.lang.String r3 = "min_val"
                double r3 = r1.optDouble(r3)
                float r3 = (float) r3
                r2.minValue = r3
                java.lang.String r3 = "interval"
                double r3 = r1.optDouble(r3)
                float r3 = (float) r3
                r2.interval = r3
                java.lang.String r3 = "degree"
                java.lang.String r3 = r1.optString(r3)
                r2.degree = r3
                java.lang.String r3 = "json_val_tag"
                java.lang.String r3 = r1.optString(r3)
                r2.jsonTag = r3
                java.lang.String r3 = "default_val"
                double r3 = r1.optDouble(r3)
                float r1 = (float) r3
                r2.defaultValue = r1
                java.lang.String r1 = "show_tags"
                boolean r1 = r6.has(r1)
                if (r1 == 0) goto L_0x0105
                java.lang.String r1 = "show_tags"
                org.json.JSONArray r6 = r6.optJSONArray(r1)
                r1 = 0
            L_0x00cc:
                int r3 = r6.length()
                if (r1 >= r3) goto L_0x0105
                com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$NumberPickerTag r3 = new com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$NumberPickerTag
                r3.<init>()
                org.json.JSONObject r4 = r6.optJSONObject(r1)
                java.lang.String r5 = "from"
                double r4 = r4.optDouble(r5)
                float r4 = (float) r4
                r3.from = r4
                org.json.JSONObject r4 = r6.optJSONObject(r1)
                java.lang.String r5 = "to"
                double r4 = r4.optDouble(r5)
                float r4 = (float) r4
                r3.to = r4
                org.json.JSONObject r4 = r6.optJSONObject(r1)
                java.lang.String r5 = "tag"
                java.lang.String r4 = r4.optString(r5)
                r3.tag = r4
                java.util.ArrayList<com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$NumberPickerTag> r4 = r2.showTags
                r4.add(r3)
                int r1 = r1 + 1
                goto L_0x00cc
            L_0x0105:
                r0.mAttr = r2
            L_0x0107:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneAction.parseFromJSON(org.json.JSONObject):com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction");
        }
    }

    public static class RecommendBuy {
        public String model;
        public String url;

        public RecommendBuy(String str, String str2) {
            this.model = str;
            this.url = str2;
        }
    }

    public RecommendSceneItem getItemBy(String str) {
        if (TextUtils.isEmpty(str) || this.mSceneItems == null || this.mSceneItems.size() <= 0) {
            return null;
        }
        for (RecommendSceneItem next : this.mSceneItems) {
            if (TextUtils.equals(str, next.sr_id)) {
                return next;
            }
        }
        return null;
    }
}
