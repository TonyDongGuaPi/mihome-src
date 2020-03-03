package com.xiaomi.smarthome.scenenew.pluginrecommend;

import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.ExternalLoadManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.plugin.util.Callback;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.amappoi.AmapGeofencingActivity;
import com.xiaomi.smarthome.scene.SceneNumberPicker;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RecommendUI {

    /* renamed from: a  reason: collision with root package name */
    PluginRecommendSceneInfo.RecommendSceneItem f22033a;
    public boolean b;
    public boolean c;
    public SparseArray<Boolean> d = new SparseArray<>();
    public SparseArray<Boolean> e = new SparseArray<>();
    public boolean f;
    public String g;

    public abstract SceneApi.SmartHomeScene a(SceneApi.SmartHomeScene smartHomeScene);

    public abstract SceneApi.SmartHomeScene a(SceneApi.SmartHomeScene smartHomeScene, Device device);

    public abstract String a(SceneApi.SmartHomeScene smartHomeScene, int i);

    public abstract void a(BaseActivity baseActivity, SceneApi.SmartHomeScene smartHomeScene, int i, RecommendSceneCreator.OnSelectCallback onSelectCallback);

    public abstract String b(SceneApi.SmartHomeScene smartHomeScene, int i);

    public abstract String c(SceneApi.SmartHomeScene smartHomeScene, int i);

    public RecommendUI(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        this.f22033a = recommendSceneItem;
        a(recommendSceneItem);
        b(recommendSceneItem);
    }

    /* access modifiers changed from: protected */
    public void a(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        if (recommendSceneItem.mCommonConditions != null) {
            for (Map.Entry next : recommendSceneItem.mCommonConditions.entrySet()) {
                if (!this.c) {
                    this.c = a((PluginRecommendSceneInfo.CommonSceneCondition) next.getValue());
                }
                this.d.append(((Integer) next.getKey()).intValue(), Boolean.valueOf(b((PluginRecommendSceneInfo.CommonSceneCondition) next.getValue())));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        if (recommendSceneItem.mCommonActions != null) {
            for (Map.Entry next : recommendSceneItem.mCommonActions.entrySet()) {
                if (!this.b) {
                    this.b = b((PluginRecommendSceneInfo.CommonSceneAction) next.getValue());
                }
                this.e.append(((Integer) next.getKey()).intValue(), Boolean.valueOf(a((PluginRecommendSceneInfo.CommonSceneAction) next.getValue())));
            }
        }
    }

    public static RecommendUI c(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        if (!recommendSceneItem.isConditionDevice && !recommendSceneItem.isActionDevice) {
            return null;
        }
        if (recommendSceneItem.isConditionDevice) {
            return new TriggerUI(recommendSceneItem);
        }
        if (recommendSceneItem.isActionDevice) {
            return new ActionUI(recommendSceneItem);
        }
        return null;
    }

    public static boolean a(PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition) {
        if (commonSceneCondition == null) {
            return false;
        }
        return (commonSceneCondition.mAttr != null && ((commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrFencing) || (commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker))) || !TextUtils.isEmpty(commonSceneCondition.mParamAction);
    }

    public static boolean b(PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition) {
        if (commonSceneCondition == null) {
            return false;
        }
        return (commonSceneCondition.mAttr != null && ((commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrFencing) || (commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker))) || TextUtils.isEmpty(commonSceneCondition.mParamAction);
    }

    public static boolean a(PluginRecommendSceneInfo.CommonSceneAction commonSceneAction) {
        if (commonSceneAction == null) {
            return false;
        }
        return (commonSceneAction.mAttr != null && ((commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrFencing) || (commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker))) || TextUtils.isEmpty(commonSceneAction.mParamAction);
    }

    public boolean d(SceneApi.SmartHomeScene smartHomeScene, int i) {
        SceneApi.Action action;
        if (smartHomeScene == null || smartHomeScene.k == null || smartHomeScene.k.size() < i || (action = smartHomeScene.k.get(i)) == null || this.f22033a.mCommonActions == null) {
            return false;
        }
        return b(this.f22033a.mCommonActions.get(Integer.valueOf(action.f)));
    }

    public boolean e(SceneApi.SmartHomeScene smartHomeScene, int i) {
        SceneApi.Condition condition;
        if (smartHomeScene == null || smartHomeScene.l == null || smartHomeScene.l.size() < i || (condition = smartHomeScene.l.get(i)) == null || condition.c == null || !(condition.c instanceof SceneApi.ConditionDeviceCommon) || this.f22033a.mCommonConditions == null) {
            return false;
        }
        return a(this.f22033a.mCommonConditions.get(Integer.valueOf(condition.c.k)));
    }

    public static boolean b(PluginRecommendSceneInfo.CommonSceneAction commonSceneAction) {
        if (commonSceneAction == null) {
            return false;
        }
        return (commonSceneAction.mAttr != null && ((commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrFencing) || (commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker))) || !TextUtils.isEmpty(commonSceneAction.mParamAction);
    }

    public SceneApi.Action a(SceneApi.Action action) {
        if (action == null) {
            return null;
        }
        PluginRecommendSceneInfo.CommonSceneAction commonSceneAction = RecommendSceneCreator.a().f21963a.mCommonActions.get(Integer.valueOf(action.f));
        if (commonSceneAction.mAttr != null && (commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
            float f2 = ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr).defaultValue;
            String str = ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr).jsonTag;
            String str2 = ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr).subTitle;
            Object valueOf = Float.valueOf(f2);
            int i = (int) f2;
            if (((double) Math.abs(((float) i) - f2)) < 1.0E-4d) {
                valueOf = Integer.valueOf(i);
            }
            if (TextUtils.isEmpty(str)) {
                action.g.f = valueOf;
            } else if (str.equals("equal")) {
                action.g.f = valueOf;
            } else {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (str.equals("max")) {
                        jSONObject.put("min", valueOf);
                        jSONObject.put("max", (double) ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr).maxValue);
                    } else {
                        jSONObject.put("min", (double) ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr).minValue);
                        jSONObject.put("max", valueOf);
                    }
                    action.g.f = jSONObject;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    action.b = String.format(str2, new Object[]{valueOf});
                } catch (Exception e3) {
                    e3.printStackTrace();
                    action.b = valueOf + "";
                }
            }
        }
        return action;
    }

    public SceneApi.Condition a(SceneApi.Condition condition) {
        if (condition == null) {
            return null;
        }
        PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition = RecommendSceneCreator.a().f21963a.mCommonConditions.get(Integer.valueOf(condition.c.k));
        if (commonSceneCondition.mAttr != null && (commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
            float f2 = ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr).defaultValue;
            String str = ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr).jsonTag;
            String str2 = ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr).subTitle;
            Object valueOf = Float.valueOf(f2);
            int i = (int) f2;
            if (((double) Math.abs(((float) i) - f2)) < 1.0E-4d) {
                valueOf = Integer.valueOf(i);
            }
            if (TextUtils.isEmpty(str)) {
                ((SceneApi.ConditionDeviceCommon) condition.c).l = valueOf;
            } else if (str.equals("equal")) {
                ((SceneApi.ConditionDeviceCommon) condition.c).l = valueOf;
            } else {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (str.equals("max")) {
                        jSONObject.put("min", valueOf);
                        jSONObject.put("max", (double) ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr).maxValue);
                    } else {
                        jSONObject.put("min", (double) ((PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr).minValue);
                        jSONObject.put("max", valueOf);
                    }
                    ((SceneApi.ConditionDeviceCommon) condition.c).l = jSONObject;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    condition.c.b = String.format(str2, new Object[]{valueOf});
                } catch (Exception e3) {
                    e3.printStackTrace();
                    SceneApi.ConditionDevice conditionDevice = condition.c;
                    conditionDevice.b = valueOf + "";
                }
            }
        }
        return condition;
    }

    /* access modifiers changed from: package-private */
    public String b(SceneApi.Condition condition) {
        if (condition == null || this.f22033a.mCommonConditions == null || condition.c == null || !(condition.c instanceof SceneApi.ConditionDeviceCommon)) {
            return null;
        }
        SceneApi.ConditionDeviceCommon conditionDeviceCommon = (SceneApi.ConditionDeviceCommon) condition.c;
        PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition = this.f22033a.mCommonConditions.get(Integer.valueOf(conditionDeviceCommon.k));
        if (commonSceneCondition == null || !a(commonSceneCondition) || conditionDeviceCommon.l == null) {
            return null;
        }
        Object obj = conditionDeviceCommon.l;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (commonSceneCondition.mAttr != null && (commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
                PluginRecommendSceneInfo.SceneAttrNumberPicker sceneAttrNumberPicker = (PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr;
                if ("min".equalsIgnoreCase(sceneAttrNumberPicker.jsonTag)) {
                    String optString = jSONObject.optString("max");
                    if (TextUtils.isEmpty(sceneAttrNumberPicker.degree)) {
                        return optString;
                    }
                    return optString + sceneAttrNumberPicker.degree;
                } else if ("max".equalsIgnoreCase(sceneAttrNumberPicker.jsonTag)) {
                    String optString2 = jSONObject.optString("min");
                    if (TextUtils.isEmpty(sceneAttrNumberPicker.degree)) {
                        return optString2;
                    }
                    return optString2 + sceneAttrNumberPicker.degree;
                } else if (!"equals".equalsIgnoreCase(sceneAttrNumberPicker.jsonTag)) {
                    return null;
                } else {
                    String optString3 = jSONObject.optString("equals");
                    if (TextUtils.isEmpty(sceneAttrNumberPicker.degree)) {
                        return optString3;
                    }
                    return optString3 + sceneAttrNumberPicker.degree;
                }
            } else if (!jSONObject.has("text") || TextUtils.isEmpty(jSONObject.optString("text"))) {
                return null;
            } else {
                return jSONObject.optString("text");
            }
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            if (jSONArray == null || jSONArray.length() <= 0) {
                return null;
            }
            return jSONArray.optString(0);
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            String str = (String) obj;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (commonSceneCondition.mAttr == null || !(commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
                return str;
            }
            PluginRecommendSceneInfo.SceneAttrNumberPicker sceneAttrNumberPicker2 = (PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr;
            if (TextUtils.isEmpty(sceneAttrNumberPicker2.degree)) {
                return str;
            }
            return str + sceneAttrNumberPicker2.degree;
        }
    }

    /* access modifiers changed from: package-private */
    public String b(SceneApi.Action action) {
        PluginRecommendSceneInfo.CommonSceneAction commonSceneAction;
        if (action == null || this.f22033a.mCommonActions == null || (commonSceneAction = this.f22033a.mCommonActions.get(Integer.valueOf(action.f))) == null || !b(commonSceneAction) || action.g == null) {
            return null;
        }
        Object obj = ((SceneApi.SHSceneItemPayloadCommon) action.g).f;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (commonSceneAction.mAttr != null && (commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
                PluginRecommendSceneInfo.SceneAttrNumberPicker sceneAttrNumberPicker = (PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr;
                if ("min".equalsIgnoreCase(sceneAttrNumberPicker.jsonTag)) {
                    String optString = jSONObject.optString("max");
                    if (TextUtils.isEmpty(sceneAttrNumberPicker.degree)) {
                        return optString;
                    }
                    return optString + sceneAttrNumberPicker.degree;
                } else if ("max".equalsIgnoreCase(sceneAttrNumberPicker.jsonTag)) {
                    String optString2 = jSONObject.optString("min");
                    if (TextUtils.isEmpty(sceneAttrNumberPicker.degree)) {
                        return optString2;
                    }
                    return optString2 + sceneAttrNumberPicker.degree;
                } else if (!"equals".equalsIgnoreCase(sceneAttrNumberPicker.jsonTag)) {
                    return null;
                } else {
                    String optString3 = jSONObject.optString("equals");
                    if (TextUtils.isEmpty(sceneAttrNumberPicker.degree)) {
                        return optString3;
                    }
                    return optString3 + sceneAttrNumberPicker.degree;
                }
            } else if (!jSONObject.has("text") || TextUtils.isEmpty(jSONObject.optString("text"))) {
                return null;
            } else {
                if (TextUtils.equals("action_tts", jSONObject.optString("text"))) {
                    return "";
                }
                return jSONObject.optString("text");
            }
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            if (jSONArray == null || jSONArray.length() <= 0) {
                return null;
            }
            return jSONArray.optString(0);
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            String str = (String) obj;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (commonSceneAction.mAttr == null || !(commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
                return TextUtils.equals("action_tts", str) ? "" : str;
            }
            PluginRecommendSceneInfo.SceneAttrNumberPicker sceneAttrNumberPicker2 = (PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneAction.mAttr;
            if (TextUtils.isEmpty(sceneAttrNumberPicker2.degree)) {
                return str;
            }
            return str + sceneAttrNumberPicker2.degree;
        }
    }

    /* access modifiers changed from: package-private */
    public String c(SceneApi.Action action) {
        PluginRecommendSceneInfo.CommonSceneAction commonSceneAction;
        if (action == null || this.f22033a.mCommonActions == null || (commonSceneAction = this.f22033a.mCommonActions.get(Integer.valueOf(action.f))) == null || !b(commonSceneAction)) {
            return null;
        }
        if (commonSceneAction.mAttr == null || !(commonSceneAction.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
            if (!TextUtils.isEmpty(commonSceneAction.mParamAction)) {
                return action.b;
            }
            return null;
        } else if (!TextUtils.isEmpty(this.f22033a.selectValueHint)) {
            return this.f22033a.selectValueHint;
        } else {
            if (!TextUtils.isEmpty(commonSceneAction.mName)) {
                return commonSceneAction.mName;
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String c(SceneApi.Condition condition) {
        SceneApi.ConditionDeviceCommon conditionDeviceCommon;
        PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition;
        if (condition == null || condition.c == null || !(condition.c instanceof SceneApi.ConditionDeviceCommon) || (conditionDeviceCommon = (SceneApi.ConditionDeviceCommon) condition.c) == null || this.f22033a.mCommonConditions == null || (commonSceneCondition = this.f22033a.mCommonConditions.get(Integer.valueOf(conditionDeviceCommon.k))) == null || !a(commonSceneCondition)) {
            return null;
        }
        if (commonSceneCondition.mAttr == null || !(commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker)) {
            if (!TextUtils.isEmpty(commonSceneCondition.mParamAction)) {
                return condition.c.b;
            }
            return null;
        } else if (!TextUtils.isEmpty(this.f22033a.selectValueHint)) {
            return this.f22033a.selectValueHint;
        } else {
            if (!TextUtils.isEmpty(commonSceneCondition.mName)) {
                return commonSceneCondition.mName;
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00db, code lost:
        if (android.text.TextUtils.equals("action_tts", r1.optString("text")) != false) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f2, code lost:
        if (android.text.TextUtils.equals("action_tts", (java.lang.String) r7.g.f) != false) goto L_0x00f4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.framework.page.BaseActivity r6, com.xiaomi.smarthome.scene.api.SceneApi.Action r7, com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.OnSelectCallback r8) {
        /*
            r5 = this;
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r0 = r5.f22033a
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction> r0 = r0.mCommonActions
            int r1 = r7.f
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r0 = r0.get(r1)
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction r0 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneAction) r0
            if (r0 != 0) goto L_0x0013
            return
        L_0x0013:
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttr r1 = r0.mAttr
            if (r1 == 0) goto L_0x00a0
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttr r8 = r0.mAttr
            boolean r8 = r8 instanceof com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.SceneAttrNumberPicker
            if (r8 == 0) goto L_0x0092
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttr r8 = r0.mAttr
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttrNumberPicker r8 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.SceneAttrNumberPicker) r8
            android.content.Intent r1 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.scene.SceneNumberPicker> r2 = com.xiaomi.smarthome.scene.SceneNumberPicker.class
            r1.<init>(r6, r2)
            java.lang.String r2 = "max_value"
            float r3 = r8.maxValue
            r1.putExtra(r2, r3)
            java.lang.String r2 = "min_value"
            float r3 = r8.minValue
            r1.putExtra(r2, r3)
            java.lang.String r2 = "interval"
            float r3 = r8.interval
            r1.putExtra(r2, r3)
            java.lang.String r2 = "degree"
            java.lang.String r3 = r8.degree
            r1.putExtra(r2, r3)
            java.lang.String r2 = "json_tag"
            java.lang.String r3 = r8.jsonTag
            r1.putExtra(r2, r3)
            java.lang.String r2 = "show_tags"
            java.util.ArrayList<com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$NumberPickerTag> r3 = r8.showTags
            r1.putExtra(r2, r3)
            java.lang.String r2 = "default_value"
            float r3 = r8.defaultValue
            r1.putExtra(r2, r3)
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r2 = r5.f22033a
            java.lang.String r2 = r2.selectValueHint
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L_0x006b
            java.lang.String r2 = "title"
            java.lang.String r0 = r0.mName
            r1.putExtra(r2, r0)
            goto L_0x0074
        L_0x006b:
            java.lang.String r0 = "title"
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r2 = r5.f22033a
            java.lang.String r2 = r2.selectValueHint
            r1.putExtra(r0, r2)
        L_0x0074:
            java.lang.String r0 = "formatter"
            java.lang.String r8 = r8.subTitle
            r1.putExtra(r0, r8)
            if (r7 == 0) goto L_0x008c
            java.lang.String r8 = "last_value"
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r7 = r7.g
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayloadCommon r7 = (com.xiaomi.smarthome.scene.api.SceneApi.SHSceneItemPayloadCommon) r7
            java.lang.Object r7 = r7.f
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r1.putExtra(r8, r7)
        L_0x008c:
            r7 = 103(0x67, float:1.44E-43)
            r6.startActivityForResult(r1, r7)
            goto L_0x00fc
        L_0x0092:
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttr r7 = r0.mAttr
            boolean r7 = r7 instanceof com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.SceneAttrFencing
            if (r7 == 0) goto L_0x00fc
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttr r7 = r0.mAttr
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$SceneAttrFencing r7 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.SceneAttrFencing) r7
            r5.a((com.xiaomi.smarthome.framework.page.BaseActivity) r6, (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.SceneAttrFencing) r7)
            goto L_0x00fc
        L_0x00a0:
            java.lang.String r0 = r0.mParamAction
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x00fc
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r0 = r7.g
            java.lang.Object r0 = r0.f
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r1 = r7.g
            java.lang.Object r1 = r1.f
            boolean r1 = r1 instanceof org.json.JSONObject
            r2 = 0
            if (r1 == 0) goto L_0x00de
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r1 = r7.g
            java.lang.Object r1 = r1.f
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            java.lang.String r3 = "text"
            boolean r3 = r1.has(r3)
            if (r3 == 0) goto L_0x00f5
            java.lang.String r3 = "text"
            java.lang.String r3 = r1.optString(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00f5
            java.lang.String r3 = "action_tts"
            java.lang.String r4 = "text"
            java.lang.String r1 = r1.optString(r4)
            boolean r1 = android.text.TextUtils.equals(r3, r1)
            if (r1 == 0) goto L_0x00f5
            goto L_0x00f4
        L_0x00de:
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r1 = r7.g
            java.lang.Object r1 = r1.f
            boolean r1 = r1 instanceof java.lang.String
            if (r1 == 0) goto L_0x00f5
            java.lang.String r1 = "action_tts"
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r7.g
            java.lang.Object r3 = r3.f
            java.lang.String r3 = (java.lang.String) r3
            boolean r1 = android.text.TextUtils.equals(r1, r3)
            if (r1 == 0) goto L_0x00f5
        L_0x00f4:
            r0 = r2
        L_0x00f5:
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r1 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            r1.a((com.xiaomi.smarthome.framework.page.BaseActivity) r6, (com.xiaomi.smarthome.scene.api.SceneApi.Action) r7, (java.lang.Object) r0, (com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.OnSelectCallback) r8)
        L_0x00fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.pluginrecommend.RecommendUI.a(com.xiaomi.smarthome.framework.page.BaseActivity, com.xiaomi.smarthome.scene.api.SceneApi$Action, com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator$OnSelectCallback):void");
    }

    private void a(BaseActivity baseActivity, PluginRecommendSceneInfo.SceneAttrFencing sceneAttrFencing) {
        ExternalLoadManager.instance.loadExternal(new Callback(sceneAttrFencing) {
            private final /* synthetic */ PluginRecommendSceneInfo.SceneAttrFencing f$1;

            {
                this.f$1 = r2;
            }

            public final Object call(Object obj) {
                return RecommendUI.a(BaseActivity.this, this.f$1, (Integer) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer a(BaseActivity baseActivity, PluginRecommendSceneInfo.SceneAttrFencing sceneAttrFencing, Integer num) throws Exception {
        if (num.intValue() == 3) {
            Intent intent = new Intent(baseActivity, Class.forName("com.xiaomi.smarthome.newui.amappoi.AmapGeofencingActivity"));
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_LATITUDE, (double) sceneAttrFencing.latitude);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_LONGITUDE, (double) sceneAttrFencing.longtitude);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_RADIUS, sceneAttrFencing.radius);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_SUB_TITLE, sceneAttrFencing.subTitle);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_ACT_NAME, sceneAttrFencing.actName);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_RADIUS_DEGREE, sceneAttrFencing.radiusDegree);
            baseActivity.startActivityForResult(intent, 104);
        } else {
            ToastUtil.a((int) R.string.mapload_fail);
        }
        return num;
    }

    public void a(BaseActivity baseActivity, SceneApi.Condition condition, RecommendSceneCreator.OnSelectCallback onSelectCallback) {
        PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition = this.f22033a.mCommonConditions.get(Integer.valueOf(condition.c.k));
        if (commonSceneCondition != null) {
            if (commonSceneCondition.mAttr != null) {
                if (commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrNumberPicker) {
                    PluginRecommendSceneInfo.SceneAttrNumberPicker sceneAttrNumberPicker = (PluginRecommendSceneInfo.SceneAttrNumberPicker) commonSceneCondition.mAttr;
                    Intent intent = new Intent(baseActivity, SceneNumberPicker.class);
                    intent.putExtra("max_value", sceneAttrNumberPicker.maxValue);
                    intent.putExtra("min_value", sceneAttrNumberPicker.minValue);
                    intent.putExtra(Constants.Name.INTERVAL, sceneAttrNumberPicker.interval);
                    intent.putExtra("degree", sceneAttrNumberPicker.degree);
                    intent.putExtra("json_tag", sceneAttrNumberPicker.jsonTag);
                    intent.putExtra("show_tags", sceneAttrNumberPicker.showTags);
                    intent.putExtra("default_value", sceneAttrNumberPicker.defaultValue);
                    if (TextUtils.isEmpty(this.f22033a.selectValueHint)) {
                        intent.putExtra("title", commonSceneCondition.mName);
                    } else {
                        intent.putExtra("title", this.f22033a.selectValueHint);
                    }
                    intent.putExtra("formatter", sceneAttrNumberPicker.subTitle);
                    if (condition != null) {
                        intent.putExtra("last_value", String.valueOf(((SceneApi.ConditionDeviceCommon) condition.c).l));
                    }
                    baseActivity.startActivityForResult(intent, 103);
                } else if (commonSceneCondition.mAttr instanceof PluginRecommendSceneInfo.SceneAttrFencing) {
                    a(baseActivity, (PluginRecommendSceneInfo.SceneAttrFencing) commonSceneCondition.mAttr);
                }
            } else if (!TextUtils.isEmpty(commonSceneCondition.mParamAction)) {
                RecommendSceneCreator.a().a(baseActivity, condition, (Object) null, onSelectCallback);
            }
        }
    }
}
