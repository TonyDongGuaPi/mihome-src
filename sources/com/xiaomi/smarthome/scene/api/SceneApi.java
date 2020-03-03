package com.xiaomi.smarthome.scene.api;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.scene.HomeSceneFactory;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21520a = "miio_sub_device";
    public static final String b = "extra_scene_account";
    @Deprecated
    public static final String c = "extra_scene_id";
    public static final String d = "extra_str_scene_id";
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = -1;
    public static final int h = 0;
    public static final int i = 3;
    public static final String j = "miio";

    public static class SHSceneDelayPayload extends SHSceneItemPayload {
        public JSONObject a() throws JSONException {
            return null;
        }
    }

    public static abstract class SHSceneItemExtra {
        public abstract JSONObject a() throws JSONException;
    }

    public static abstract class SHSceneItemPayload {
        public String c;
        @Deprecated
        public String d;
        public String e;
        public Object f;
        public int g = 0;
        public long h;

        public abstract JSONObject a() throws JSONException;
    }

    public static class SmartHomeScene {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21533a = 15;
        public static final int b = 30;
        public static final int c = 50;
        public static final int d = 0;
        public static final int e = 1;
        public String f;
        public String g;
        @Deprecated
        public boolean h = false;
        public int i = -1;
        public String j;
        public List<Action> k = new ArrayList();
        public List<Condition> l = new ArrayList();
        @Deprecated
        public boolean m;
        public boolean n = true;
        public boolean o = false;
        public boolean p = false;
        public int q = 0;
        public int r;
        public int s;
        public boolean t;
        public EffectiveTimeSpan u;
        public boolean v = false;
        public List<String> w = new ArrayList();
        public GroupCondition x = null;

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene r6) {
            /*
                r5 = this;
                boolean r0 = r5.n
                boolean r1 = r6.n
                r2 = 0
                if (r0 != r1) goto L_0x01aa
                int r0 = r5.q
                int r1 = r6.q
                if (r0 == r1) goto L_0x000f
                goto L_0x01aa
            L_0x000f:
                com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r5.l
                boolean r0 = r0.c((java.util.List<com.xiaomi.smarthome.scene.api.SceneApi.Condition>) r1)
                com.xiaomi.smarthome.scene.CreateSceneManager r1 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r3 = r6.l
                boolean r1 = r1.c((java.util.List<com.xiaomi.smarthome.scene.api.SceneApi.Condition>) r3)
                if (r0 == r1) goto L_0x0026
                return r2
            L_0x0026:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r5.l
                int r0 = r0.size()
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r6.l
                int r1 = r1.size()
                if (r0 == r1) goto L_0x0035
                return r2
            L_0x0035:
                r0 = 0
            L_0x0036:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r5.l
                int r1 = r1.size()
                if (r0 >= r1) goto L_0x008e
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r5.l     // Catch:{ JSONException -> 0x0089 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ JSONException -> 0x0089 }
                if (r1 != 0) goto L_0x004e
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r6.l     // Catch:{ JSONException -> 0x0089 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ JSONException -> 0x0089 }
                if (r1 != 0) goto L_0x005e
            L_0x004e:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r5.l     // Catch:{ JSONException -> 0x0089 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ JSONException -> 0x0089 }
                if (r1 == 0) goto L_0x005f
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r6.l     // Catch:{ JSONException -> 0x0089 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ JSONException -> 0x0089 }
                if (r1 != 0) goto L_0x005f
            L_0x005e:
                return r2
            L_0x005f:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r5.l     // Catch:{ JSONException -> 0x0089 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ JSONException -> 0x0089 }
                com.xiaomi.smarthome.scene.api.SceneApi$Condition r1 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r1     // Catch:{ JSONException -> 0x0089 }
                org.json.JSONObject r1 = r1.a()     // Catch:{ JSONException -> 0x0089 }
                java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0089 }
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r3 = r6.l     // Catch:{ JSONException -> 0x0089 }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x0089 }
                com.xiaomi.smarthome.scene.api.SceneApi$Condition r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r3     // Catch:{ JSONException -> 0x0089 }
                org.json.JSONObject r3 = r3.a()     // Catch:{ JSONException -> 0x0089 }
                java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x0089 }
                boolean r1 = android.text.TextUtils.equals(r1, r3)     // Catch:{ JSONException -> 0x0089 }
                if (r1 != 0) goto L_0x0086
                return r2
            L_0x0086:
                int r0 = r0 + 1
                goto L_0x0036
            L_0x0089:
                r6 = move-exception
                r6.getStackTrace()
                return r2
            L_0x008e:
                com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r5.l
                boolean r0 = r0.c((java.util.List<com.xiaomi.smarthome.scene.api.SceneApi.Condition>) r1)
                if (r0 != 0) goto L_0x00b6
                com.xiaomi.smarthome.scene.api.SceneApi$EffectiveTimeSpan r0 = r5.u
                if (r0 == 0) goto L_0x00ad
                com.xiaomi.smarthome.scene.api.SceneApi$EffectiveTimeSpan r0 = r6.u
                if (r0 == 0) goto L_0x00ad
                com.xiaomi.smarthome.scene.api.SceneApi$EffectiveTimeSpan r0 = r5.u
                com.xiaomi.smarthome.scene.api.SceneApi$EffectiveTimeSpan r1 = r6.u
                boolean r0 = r0.a((com.xiaomi.smarthome.scene.api.SceneApi.EffectiveTimeSpan) r1)
                if (r0 != 0) goto L_0x00b6
                return r2
            L_0x00ad:
                com.xiaomi.smarthome.scene.api.SceneApi$EffectiveTimeSpan r0 = r5.u
                if (r0 != 0) goto L_0x00b5
                com.xiaomi.smarthome.scene.api.SceneApi$EffectiveTimeSpan r0 = r6.u
                if (r0 == 0) goto L_0x00b6
            L_0x00b5:
                return r2
            L_0x00b6:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r5.k
                int r0 = r0.size()
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r1 = r6.k
                int r1 = r1.size()
                if (r0 == r1) goto L_0x00c5
                return r2
            L_0x00c5:
                r0 = 0
                r1 = 0
            L_0x00c7:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x018f
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k
                java.lang.Object r3 = r3.get(r0)
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g
                boolean r3 = r3 instanceof com.xiaomi.smarthome.scene.api.SceneApi.SHSceneDelayPayload
                if (r3 == 0) goto L_0x011b
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r6.k
                java.lang.Object r3 = r3.get(r0)
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g
                boolean r3 = r3 instanceof com.xiaomi.smarthome.scene.api.SceneApi.SHSceneDelayPayload
                if (r3 != 0) goto L_0x00ec
                return r2
            L_0x00ec:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k
                java.lang.Object r3 = r3.get(r0)
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneDelayPayload r3 = (com.xiaomi.smarthome.scene.api.SceneApi.SHSceneDelayPayload) r3
                int r3 = r3.g
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r4 = r6.k
                java.lang.Object r4 = r4.get(r0)
                com.xiaomi.smarthome.scene.api.SceneApi$Action r4 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r4
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r4 = r4.g
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneDelayPayload r4 = (com.xiaomi.smarthome.scene.api.SceneApi.SHSceneDelayPayload) r4
                int r4 = r4.g
                if (r3 == r4) goto L_0x010b
                return r2
            L_0x010b:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k
                java.lang.Object r3 = r3.get(r0)
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneDelayPayload r3 = (com.xiaomi.smarthome.scene.api.SceneApi.SHSceneDelayPayload) r3
                int r3 = r3.g
                int r1 = r1 + r3
                goto L_0x018a
            L_0x011b:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g     // Catch:{ JSONException -> 0x018e }
                r3.g = r1     // Catch:{ JSONException -> 0x018e }
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r6.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g     // Catch:{ JSONException -> 0x018e }
                r3.g = r1     // Catch:{ JSONException -> 0x018e }
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3     // Catch:{ JSONException -> 0x018e }
                org.json.JSONObject r3 = r3.a()     // Catch:{ JSONException -> 0x018e }
                java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x018e }
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r4 = r6.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r4 = r4.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r4 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r4     // Catch:{ JSONException -> 0x018e }
                org.json.JSONObject r4 = r4.a()     // Catch:{ JSONException -> 0x018e }
                java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x018e }
                boolean r3 = r3.equalsIgnoreCase(r4)     // Catch:{ JSONException -> 0x018e }
                if (r3 != 0) goto L_0x0172
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r1 = r5.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r1 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r1     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r1 = r1.g     // Catch:{ JSONException -> 0x018e }
                r1.g = r2     // Catch:{ JSONException -> 0x018e }
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r6 = r6.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r6 = r6.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r6 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r6     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r6 = r6.g     // Catch:{ JSONException -> 0x018e }
                r6.g = r2     // Catch:{ JSONException -> 0x018e }
                return r2
            L_0x0172:
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r5.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g     // Catch:{ JSONException -> 0x018e }
                r3.g = r2     // Catch:{ JSONException -> 0x018e }
                java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r6.k     // Catch:{ JSONException -> 0x018e }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r3     // Catch:{ JSONException -> 0x018e }
                com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r3 = r3.g     // Catch:{ JSONException -> 0x018e }
                r3.g = r2     // Catch:{ JSONException -> 0x018e }
            L_0x018a:
                int r0 = r0 + 1
                goto L_0x00c7
            L_0x018e:
                return r2
            L_0x018f:
                com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene$GroupCondition r0 = r5.x
                if (r0 == 0) goto L_0x01a0
                com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene$GroupCondition r0 = r6.x
                if (r0 == 0) goto L_0x01a0
                com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene$GroupCondition r0 = r5.x
                com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene$GroupCondition r6 = r6.x
                boolean r6 = r0.a((com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene.GroupCondition) r6)
                return r6
            L_0x01a0:
                com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene$GroupCondition r0 = r5.x
                if (r0 != 0) goto L_0x01a9
                com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene$GroupCondition r6 = r6.x
                if (r6 != 0) goto L_0x01a9
                r2 = 1
            L_0x01a9:
                return r2
            L_0x01aa:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene.a(com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene):boolean");
        }

        public void a(List<String> list, List<Integer> list2) {
            int i2 = 0;
            for (Action next : this.k) {
                if (!(next.g instanceof SHSceneDelayPayload)) {
                    if (next.g instanceof SHScenePushPayload) {
                        list2.add(Integer.valueOf(R.drawable.std_scene_icon_push));
                    } else if (next.g instanceof SHLiteScenePayload) {
                        list2.add(Integer.valueOf(SmartHomeSceneUtility.a(this.i)));
                    } else if (next.g instanceof SHSceneAutoPayload) {
                        list2.add(Integer.valueOf(R.drawable.scene_auto_icon));
                    } else if (CoreApi.a().d(next.e) != null) {
                        list.add(CoreApi.a().d(next.e).t());
                    } else {
                        list2.add(Integer.valueOf(DeviceFactory.q(next.e)));
                    }
                    i2++;
                    if (i2 >= 4) {
                        return;
                    }
                }
            }
        }

        public static class GroupCondition {

            /* renamed from: a  reason: collision with root package name */
            public String f21534a;
            public boolean b = false;
            public String c;
            public int d = 0;
            public List<Condition> e = new ArrayList(5);
            public GroupCondition f = null;

            public static GroupCondition a(JSONObject jSONObject) throws JSONException {
                JSONArray optJSONArray;
                JSONObject optJSONObject;
                GroupCondition a2;
                if (jSONObject == null || jSONObject.isNull(RichTextNode.ATTR) || (optJSONArray = jSONObject.optJSONArray(RichTextNode.ATTR)) == null || optJSONArray.length() <= 0) {
                    return null;
                }
                GroupCondition groupCondition = new GroupCondition();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Condition a3 = Condition.a(optJSONArray.optJSONObject(i));
                    if (a3 != null) {
                        groupCondition.e.add(a3);
                    }
                }
                groupCondition.f21534a = jSONObject.optString("name", (String) null);
                groupCondition.c = jSONObject.optString("desc", (String) null);
                groupCondition.b = jSONObject.optBoolean(AutoSceneAction.f21495a, false);
                groupCondition.d = jSONObject.optInt("express", 0);
                if (!(!jSONObject.has("sub_launch") || (optJSONObject = jSONObject.optJSONObject("sub_launch")) == null || (a2 = a(optJSONObject)) == null)) {
                    groupCondition.f = a2;
                }
                return groupCondition;
            }

            public boolean a(GroupCondition groupCondition) {
                if (groupCondition == null || this.b != groupCondition.b || this.d != groupCondition.d || this.e.size() != groupCondition.e.size()) {
                    return false;
                }
                int i = 0;
                while (i < this.e.size()) {
                    try {
                        if (!this.e.get(i).a().toString().equalsIgnoreCase(groupCondition.e.get(i).a().toString())) {
                            return false;
                        }
                        i++;
                    } catch (JSONException unused) {
                        return false;
                    }
                }
                if (groupCondition.f != null && this.f != null) {
                    return groupCondition.f.a(this.f);
                }
                if (groupCondition.f == null && this.f == null) {
                    return true;
                }
                return false;
            }

            public JSONObject a() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", this.f21534a);
                jSONObject.put("desc", this.c);
                jSONObject.put(AutoSceneAction.f21495a, this.b);
                jSONObject.put("express", this.d);
                JSONArray jSONArray = new JSONArray();
                for (Condition a2 : this.e) {
                    jSONArray.put(a2.a());
                }
                jSONObject.put(RichTextNode.ATTR, jSONArray);
                if (this.f != null) {
                    jSONObject.put("sub_launch", this.f.a());
                }
                return jSONObject;
            }
        }

        public static SmartHomeScene a(JSONObject jSONObject, boolean z) throws JSONException {
            JSONArray optJSONArray;
            JSONObject optJSONObject;
            Condition a2;
            if (jSONObject == null) {
                return null;
            }
            SmartHomeScene smartHomeScene = new SmartHomeScene();
            smartHomeScene.f = jSONObject.optString("us_id");
            smartHomeScene.g = jSONObject.optString("name");
            if (jSONObject.has("type")) {
                smartHomeScene.s = jSONObject.optInt("type");
            }
            if (jSONObject.has("status")) {
                smartHomeScene.r = jSONObject.optInt("status");
            }
            if (jSONObject.has("create_by_template")) {
                smartHomeScene.h = jSONObject.getBoolean("create_by_template");
            }
            if (jSONObject.has("local_dev")) {
                smartHomeScene.j = jSONObject.optString("local_dev");
            }
            if (jSONObject.has("sr_id")) {
                smartHomeScene.i = jSONObject.getInt("sr_id");
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(a.j);
            if (optJSONObject2.has("enable_push")) {
                smartHomeScene.o = optJSONObject2.optInt("enable_push") == 1;
            }
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("launch");
            if (optJSONObject3 == null) {
                return null;
            }
            smartHomeScene.q = optJSONObject3.optInt("express");
            JSONArray optJSONArray2 = optJSONObject3.optJSONArray(RichTextNode.ATTR);
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    Object obj = optJSONArray2.get(i2);
                    if ((obj instanceof JSONObject) && (a2 = Condition.a((JSONObject) obj)) != null) {
                        smartHomeScene.l.add(a2);
                    }
                }
                if (optJSONObject3.has("sub_launch") && (optJSONObject = optJSONObject3.optJSONObject("sub_launch")) != null) {
                    smartHomeScene.x = GroupCondition.a(optJSONObject);
                }
                if (optJSONObject2.has("timespan")) {
                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("timespan");
                    if (optJSONObject4 != null) {
                        smartHomeScene.v = true;
                        smartHomeScene.u = EffectiveTimeSpan.a(optJSONObject4);
                    }
                } else {
                    smartHomeScene.v = false;
                }
            }
            smartHomeScene.n = optJSONObject2.optString(AutoSceneAction.f21495a).equals("1");
            JSONArray optJSONArray3 = optJSONObject2.optJSONArray("action_list");
            if (optJSONArray3 == null) {
                return null;
            }
            if (optJSONArray3.length() > 0) {
                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                    Action a3 = Action.a(optJSONArray3.getJSONObject(i3));
                    if (a3 != null) {
                        smartHomeScene.k.add(a3);
                    }
                }
            }
            if (smartHomeScene.k.size() > 0) {
                a(smartHomeScene.k);
            }
            smartHomeScene.t = z;
            if (jSONObject.has("authed") && (optJSONArray = jSONObject.optJSONArray("authed")) != null && optJSONArray.length() > 0) {
                for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                    smartHomeScene.w.add(optJSONArray.optString(i4));
                }
            }
            return smartHomeScene;
        }

        private static void a(List<Action> list) {
            Collections.sort(list, new Comparator<Action>() {
                /* renamed from: a */
                public int compare(Action action, Action action2) {
                    return action.g.g - action.g.g;
                }
            });
            int i2 = 0;
            int i3 = 0;
            while (i2 < list.size()) {
                if (!(list.get(i2).g == null || list.get(i2).g.g == 0)) {
                    if (i2 == 0 || !(list.get(i2 - 1) == null || list.get(i2).g.g == i3)) {
                        Action action = new Action();
                        action.g = new SHSceneDelayPayload();
                        action.g.g = list.get(i2).g.g - i3;
                        action.g.c = "delay.delay";
                        i3 += action.g.g;
                        list.add(i2, action);
                        i2++;
                    }
                    list.get(i2).g.g = 0;
                }
                i2++;
            }
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", this.g);
            if (!TextUtils.isEmpty(this.f)) {
                jSONObject.put("us_id", this.f);
            }
            if (this.t) {
                jSONObject.put("st_id", 30);
            } else {
                jSONObject.put("st_id", 15);
            }
            if (this.i != -1) {
                jSONObject.put("sr_id", this.i);
            }
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            ArrayList<String> arrayList = new ArrayList<>();
            jSONObject2.put("enable_push", this.o ? 1 : 0);
            if (!this.t) {
                JSONArray jSONArray = new JSONArray();
                for (Condition next : this.l) {
                    jSONArray.put(next.a());
                    if (next.c != null) {
                        arrayList.add(next.c.f21523a);
                    }
                }
                if (this.x != null) {
                    jSONObject3.put("sub_launch", this.x.a());
                }
                jSONObject3.put(RichTextNode.ATTR, jSONArray);
                jSONObject3.put("express", this.q);
                if (this.u != null) {
                    SceneLogUtil.a("mEffectiveTimeSpan" + this.u.toString());
                    jSONObject2.put("timespan", this.u.a());
                } else {
                    SceneLogUtil.a("mEffectiveTimeSpannull");
                }
            } else if (this.l == null || this.l.size() <= 0) {
                JSONArray jSONArray2 = new JSONArray();
                Condition condition = new Condition();
                condition.f21522a = Condition.LAUNCH_TYPE.CLICK;
                condition.k = 101;
                jSONArray2.put(condition.a());
                jSONObject3.put(RichTextNode.ATTR, jSONArray2);
            } else {
                JSONArray jSONArray3 = new JSONArray();
                jSONArray3.put(this.l.get(0).a());
                jSONObject3.put(RichTextNode.ATTR, jSONArray3);
            }
            jSONObject2.put("launch", jSONObject3);
            JSONArray jSONArray4 = new JSONArray();
            int i2 = 0;
            for (Action next2 : this.k) {
                if (next2.g != null) {
                    if (next2.g instanceof SHSceneDelayPayload) {
                        i2 += ((SHSceneDelayPayload) next2.g).g;
                    } else {
                        next2.g.g = i2;
                        jSONArray4.put(next2.a());
                        next2.g.g = 0;
                        if (!TextUtils.isEmpty(next2.g.e)) {
                            arrayList.add(next2.g.e);
                        }
                    }
                }
            }
            if (!(this.x == null || this.x.e == null || this.x.e.size() <= 0)) {
                for (Condition next3 : this.x.e) {
                    if (next3.c != null) {
                        arrayList.add(next3.c.f21523a);
                    }
                }
            }
            jSONObject2.put("action_list", jSONArray4);
            jSONObject2.put(AutoSceneAction.f21495a, this.n ? "1" : "0");
            jSONObject.put(a.j, jSONObject2);
            JSONArray jSONArray5 = new JSONArray();
            for (String put : arrayList) {
                jSONArray5.put(put);
            }
            if (arrayList.size() > 0) {
                jSONObject.put("authed", jSONArray5);
            }
            return jSONObject;
        }

        public String toString() {
            return "SmartHomeScene{id=" + this.f + ", name='" + this.g + Operators.SINGLE_QUOTE + ", isCreateByTemplate=" + this.h + ", recommId=" + this.i + ", localDevDid='" + this.j + Operators.SINGLE_QUOTE + ", actionList=" + this.k + ", conditionList=" + this.l + ", isNew=" + this.m + ", enable=" + this.n + ", enablePush=" + this.o + ", notify=" + this.p + ", express=" + this.q + ", mStatus=" + this.r + ", mType=" + this.s + ", mIsLite=" + this.t + ", groupCondition=" + this.x + Operators.BLOCK_END;
        }
    }

    public static void a(SmartHomeScene smartHomeScene) {
        int i2 = 0;
        for (int i3 = 0; i3 < smartHomeScene.k.size(); i3++) {
            if (smartHomeScene.k.get(i3).g instanceof SHSceneDelayPayload) {
                i2 += ((SHSceneDelayPayload) smartHomeScene.k.get(i3).g).g;
            } else {
                smartHomeScene.k.get(i3).g.g = i2;
            }
        }
    }

    public static class ConditionELLocation extends ConditionDevice {
        public static final String l = "enter_";
        public static final String m = "leave_";
        public String n;
        public String o;
        public JSONObject p;

        public static ConditionELLocation b(JSONObject jSONObject) {
            String optString = jSONObject.optString("key");
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            try {
                ConditionELLocation conditionELLocation = new ConditionELLocation();
                conditionELLocation.o = optString;
                conditionELLocation.b = jSONObject.optString("name");
                conditionELLocation.n = jSONObject.optString("event");
                conditionELLocation.p = jSONObject.optJSONObject("info");
                return conditionELLocation;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event", this.n);
            jSONObject.put("key", this.o);
            jSONObject.put("name", this.b);
            if (this.p == null) {
                this.p = new JSONObject();
                this.p.put("platform", com.alipay.android.phone.a.a.a.f813a);
            }
            jSONObject.put("info", this.p);
            return jSONObject;
        }
    }

    public static class ConditionIZatGeoFencing extends ConditionDevice {
        public static final String l = "enter_fence";
        public static final String m = "leave_fence";
        public String n;
        public String o;
        public long p;
        public String q;
        public double r;
        public double s;
        public float t;

        public static ConditionIZatGeoFencing b(JSONObject jSONObject) {
            String optString = jSONObject.optString("key");
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            ConditionIZatGeoFencing conditionIZatGeoFencing = new ConditionIZatGeoFencing();
            conditionIZatGeoFencing.q = optString;
            conditionIZatGeoFencing.o = jSONObject.optString("src");
            conditionIZatGeoFencing.b = jSONObject.optString("name");
            conditionIZatGeoFencing.n = jSONObject.optString("subName");
            JSONObject optJSONObject = jSONObject.optJSONObject("info");
            if (optJSONObject != null) {
                conditionIZatGeoFencing.r = optJSONObject.optDouble("latitude");
                conditionIZatGeoFencing.s = optJSONObject.optDouble("longitude");
                conditionIZatGeoFencing.t = (float) optJSONObject.optDouble("raduis");
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("value");
            if (optJSONObject2 != null) {
                conditionIZatGeoFencing.p = optJSONObject2.optLong("po_id");
            }
            return conditionIZatGeoFencing;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", this.q);
            jSONObject.put("name", this.b);
            jSONObject.put("src", this.o);
            JSONObject jSONObject2 = new JSONObject();
            if (!Double.valueOf(this.r).isNaN()) {
                jSONObject2.put("latitude", this.r);
            }
            if (!Double.valueOf(this.s).isNaN()) {
                jSONObject2.put("longitude", this.s);
            }
            jSONObject2.put("raduis", (double) this.t);
            jSONObject2.put("platform", "iOS");
            jSONObject.put("info", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("po_id", this.p);
            jSONObject.put("value", jSONObject3);
            return jSONObject;
        }
    }

    public static class EffectiveTimeSpan {

        /* renamed from: a  reason: collision with root package name */
        public int f21526a;
        public int b;
        public int c;
        public int d;
        public int[] e;

        public EffectiveTimeSpan() {
            this.f21526a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.f21526a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = new int[7];
            for (int i = 0; i < this.e.length; i++) {
                this.e[i] = i;
            }
        }

        public static EffectiveTimeSpan a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            EffectiveTimeSpan effectiveTimeSpan = new EffectiveTimeSpan();
            JSONObject optJSONObject = jSONObject.optJSONObject("from");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("to");
            JSONArray optJSONArray = jSONObject.optJSONArray("wday");
            if (optJSONObject != null) {
                effectiveTimeSpan.f21526a = optJSONObject.optInt("hour");
                effectiveTimeSpan.c = optJSONObject.optInt("min");
            }
            if (optJSONObject2 != null) {
                effectiveTimeSpan.b = optJSONObject2.optInt("hour");
                effectiveTimeSpan.d = optJSONObject2.optInt("min");
            }
            if (optJSONArray != null) {
                effectiveTimeSpan.e = new int[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        effectiveTimeSpan.e[i] = optJSONArray.getInt(i);
                    } catch (JSONException unused) {
                    }
                }
            }
            return effectiveTimeSpan;
        }

        public boolean a(EffectiveTimeSpan effectiveTimeSpan) {
            if (effectiveTimeSpan == null || this.f21526a != effectiveTimeSpan.f21526a || this.b != effectiveTimeSpan.b || this.c != effectiveTimeSpan.c || this.d != effectiveTimeSpan.d) {
                return false;
            }
            if (this.e == null || effectiveTimeSpan.e == null) {
                if (this.e == null && effectiveTimeSpan.e == null) {
                    return true;
                }
                return false;
            } else if (this.e.length != effectiveTimeSpan.e.length) {
                return false;
            } else {
                for (int i = 0; i < this.e.length; i++) {
                    if (this.e[i] != effectiveTimeSpan.e[i]) {
                        return false;
                    }
                }
                return true;
            }
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (this.f21526a != -1) {
                jSONObject2.put("hour", this.f21526a);
                jSONObject2.put("min", this.c);
                jSONObject3.put("hour", this.b);
                jSONObject3.put("min", this.d);
                if (this.e != null) {
                    for (int put : this.e) {
                        jSONArray.put(put);
                    }
                }
                jSONObject.put("from", jSONObject2);
                jSONObject.put("to", jSONObject3);
                jSONObject.put("wday", jSONArray);
            }
            return jSONObject;
        }

        public String toString() {
            return "EffectiveTimeSpan{fromHour=" + this.f21526a + ", toHour=" + this.b + ", fromMin=" + this.c + ", toMin=" + this.d + ", wDay=" + Arrays.toString(this.e) + Operators.BLOCK_END;
        }
    }

    public static class Condition {

        /* renamed from: a  reason: collision with root package name */
        public LAUNCH_TYPE f21522a;
        public LaunchSceneTimer b;
        public ConditionDevice c;
        public LaunchUser d;
        public ConditionMiKey e;
        public ConditionMiBand f;
        public ConditionUser g;
        public ConditionELLocation h;
        public ConditionIZatGeoFencing i;
        public ConditionWeather j;
        public int k;
        public boolean l = true;

        public enum LAUNCH_TYPE {
            CLICK,
            TIMER,
            DEVICE,
            LEAVE_HOME,
            COME_HOME,
            MIKEY,
            MIBAND,
            USER,
            PHONE_CALL,
            PHONE_SMS,
            COME_LOC,
            LEAVE_LOC,
            SUN_RISE,
            SUN_SET,
            HUMIDITY,
            TEMPERATURE,
            AQI,
            ENTER_FENCE,
            LEAVE_FENCE
        }

        public boolean a(Condition condition) {
            if (condition.f21522a != this.f21522a) {
                return false;
            }
            if (this.f21522a == LAUNCH_TYPE.TIMER) {
                return this.b.a(condition.b);
            }
            if (this.f21522a == LAUNCH_TYPE.DEVICE) {
                if (this.c != null && condition.c != null) {
                    try {
                        return this.c.a().toString().equalsIgnoreCase(condition.c.a().toString());
                    } catch (JSONException unused) {
                    }
                } else if (this.c == null && condition.c == null) {
                    return true;
                } else {
                    return false;
                }
            }
            if (this.f21522a == LAUNCH_TYPE.MIKEY) {
                if (!TextUtils.isEmpty(this.e.l) && !TextUtils.isEmpty(condition.e.l)) {
                    return this.e.l.equalsIgnoreCase(condition.e.l);
                }
                if (!TextUtils.isEmpty(this.e.l) || !TextUtils.isEmpty(condition.e.l)) {
                    return false;
                }
                return true;
            } else if (this.f21522a != LAUNCH_TYPE.USER) {
                return true;
            } else {
                if (condition.g != null) {
                    if (this.g.f21524a != null) {
                        return this.g.f21524a.equalsIgnoreCase(condition.g.f21524a);
                    }
                    if (condition.g.f21524a == null) {
                        return true;
                    }
                }
                return false;
            }
        }

        public static Condition a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            Condition condition = new Condition();
            if (jSONObject.has(AutoSceneAction.f21495a)) {
                condition.l = jSONObject.optBoolean(AutoSceneAction.f21495a, true);
            }
            if (jSONObject.has("tr_id")) {
                condition.k = jSONObject.optInt("tr_id");
            }
            String optString = jSONObject.optString("src");
            if (optString.equalsIgnoreCase("user")) {
                String optString2 = jSONObject.optString("key");
                if (TextUtils.isEmpty(optString2) || optString2.equalsIgnoreCase("click")) {
                    condition.f21522a = LAUNCH_TYPE.CLICK;
                    return condition;
                } else if (optString2.equalsIgnoreCase(HomeSceneFactory.PHONE_SCENE_CON_KEY) || optString2.equalsIgnoreCase(HomeSceneFactory.PHONE_SCENE_DISCON_KEY) || optString2.equalsIgnoreCase(SceneManager.f13036a) || optString2.equalsIgnoreCase(SceneManager.b)) {
                    condition.d = LaunchUser.a(jSONObject);
                    if (condition.d.f21528a.equals(HomeSceneFactory.PHONE_SCENE_CON_KEY)) {
                        condition.f21522a = LAUNCH_TYPE.COME_HOME;
                    } else if (optString2.equalsIgnoreCase(HomeSceneFactory.PHONE_SCENE_DISCON_KEY)) {
                        condition.f21522a = LAUNCH_TYPE.LEAVE_HOME;
                    } else if (optString2.equalsIgnoreCase(SceneManager.f13036a)) {
                        condition.f21522a = LAUNCH_TYPE.PHONE_CALL;
                    } else if (optString2.equalsIgnoreCase(SceneManager.b)) {
                        condition.f21522a = LAUNCH_TYPE.PHONE_SMS;
                    }
                } else if (optString2.startsWith("mikey")) {
                    condition.e = ConditionMiKey.b(jSONObject);
                    condition.f21522a = LAUNCH_TYPE.MIKEY;
                } else if (optString2.startsWith("miband")) {
                    condition.f = ConditionMiBand.b(jSONObject);
                    condition.f21522a = LAUNCH_TYPE.MIBAND;
                } else if (optString2.equalsIgnoreCase(ConditionIZatGeoFencing.l)) {
                    condition.f21522a = LAUNCH_TYPE.ENTER_FENCE;
                    condition.i = ConditionIZatGeoFencing.b(jSONObject);
                } else if (optString2.equalsIgnoreCase(ConditionIZatGeoFencing.m)) {
                    condition.f21522a = LAUNCH_TYPE.LEAVE_FENCE;
                    condition.i = ConditionIZatGeoFencing.b(jSONObject);
                } else if (optString2.startsWith(ConditionELLocation.l)) {
                    condition.f21522a = LAUNCH_TYPE.COME_LOC;
                    condition.h = ConditionELLocation.b(jSONObject);
                } else if (optString2.startsWith(ConditionELLocation.m)) {
                    condition.f21522a = LAUNCH_TYPE.LEAVE_LOC;
                    condition.h = ConditionELLocation.b(jSONObject);
                } else {
                    condition.f21522a = LAUNCH_TYPE.USER;
                    condition.g = ConditionUser.a(jSONObject);
                }
            } else if (optString.equalsIgnoreCase("timer")) {
                condition.b = LaunchSceneTimer.a(jSONObject);
                condition.f21522a = LAUNCH_TYPE.TIMER;
            } else if (optString.equalsIgnoreCase("cloud")) {
                String optString3 = jSONObject.optString("key");
                if (optString3.equalsIgnoreCase(WeatherInnerCondition.WeatherConditionType.TYPE_SUNRISE.key)) {
                    condition.f21522a = LAUNCH_TYPE.SUN_RISE;
                    condition.j = ConditionWeather.a(jSONObject);
                } else if (optString3.equalsIgnoreCase(WeatherInnerCondition.WeatherConditionType.TYPE_SUNSET.key)) {
                    condition.f21522a = LAUNCH_TYPE.SUN_SET;
                    condition.j = ConditionWeather.a(jSONObject);
                } else if (optString3.equalsIgnoreCase(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY.key)) {
                    condition.f21522a = LAUNCH_TYPE.HUMIDITY;
                    condition.j = ConditionWeather.a(jSONObject);
                } else if (optString3.equalsIgnoreCase(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE.key)) {
                    condition.f21522a = LAUNCH_TYPE.TEMPERATURE;
                    condition.j = ConditionWeather.a(jSONObject);
                } else if (optString3.equalsIgnoreCase(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI.key)) {
                    condition.f21522a = LAUNCH_TYPE.AQI;
                    condition.j = ConditionWeather.a(jSONObject);
                }
            } else if (optString.equalsIgnoreCase("device")) {
                condition.c = ConditionDevice.a(jSONObject);
                condition.f21522a = LAUNCH_TYPE.DEVICE;
                if (condition.c == null) {
                    return condition;
                }
            }
            return condition;
        }

        public JSONObject a() throws JSONException {
            if (this.f21522a == LAUNCH_TYPE.TIMER) {
                JSONObject a2 = this.b.a();
                a2.put("src", "timer");
                a2.put(AutoSceneAction.f21495a, this.l);
                a2.put("tr_id", this.k);
                return a2;
            } else if (this.f21522a == LAUNCH_TYPE.DEVICE) {
                JSONObject a3 = this.c.a();
                a3.put("src", "device");
                a3.put(AutoSceneAction.f21495a, this.l);
                a3.put("tr_id", this.k);
                return a3;
            } else if (this.f21522a == LAUNCH_TYPE.LEAVE_HOME || this.f21522a == LAUNCH_TYPE.COME_HOME || this.f21522a == LAUNCH_TYPE.PHONE_CALL || this.f21522a == LAUNCH_TYPE.PHONE_SMS) {
                JSONObject a4 = this.d.a();
                a4.put("src", "user");
                a4.put(AutoSceneAction.f21495a, this.l);
                a4.put("tr_id", this.k);
                return a4;
            } else if (this.f21522a == LAUNCH_TYPE.CLICK) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("src", "user");
                jSONObject.put("key", "");
                jSONObject.put(AutoSceneAction.f21495a, this.l);
                jSONObject.put("tr_id", this.k);
                return jSONObject;
            } else if (this.f21522a == LAUNCH_TYPE.COME_LOC || this.f21522a == LAUNCH_TYPE.LEAVE_LOC) {
                JSONObject a5 = this.h.a();
                a5.put("src", "user");
                a5.put(AutoSceneAction.f21495a, this.l);
                a5.put("tr_id", this.k);
                return a5;
            } else if (this.f21522a == LAUNCH_TYPE.ENTER_FENCE || this.f21522a == LAUNCH_TYPE.LEAVE_FENCE) {
                JSONObject a6 = this.i.a();
                a6.put("src", "user");
                a6.put(AutoSceneAction.f21495a, this.l);
                a6.put("tr_id", this.k);
                return a6;
            } else if (this.f21522a == LAUNCH_TYPE.MIKEY) {
                JSONObject a7 = this.e.a();
                a7.put("src", "user");
                a7.put(AutoSceneAction.f21495a, this.l);
                a7.put("tr_id", this.k);
                return a7;
            } else if (this.f21522a == LAUNCH_TYPE.MIBAND) {
                JSONObject a8 = this.f.a();
                a8.put("src", "user");
                a8.put(AutoSceneAction.f21495a, this.l);
                a8.put("tr_id", this.k);
                return a8;
            } else if (this.f21522a == LAUNCH_TYPE.USER) {
                JSONObject a9 = this.g.a();
                a9.put("src", "user");
                a9.put(AutoSceneAction.f21495a, this.l);
                a9.put("tr_id", this.k);
                return a9;
            } else if (this.f21522a != LAUNCH_TYPE.SUN_RISE && this.f21522a != LAUNCH_TYPE.SUN_SET && this.f21522a != LAUNCH_TYPE.AQI && this.f21522a != LAUNCH_TYPE.HUMIDITY && this.f21522a != LAUNCH_TYPE.TEMPERATURE) {
                return new JSONObject();
            } else {
                JSONObject a10 = this.j.a();
                a10.put("src", "cloud");
                a10.put(AutoSceneAction.f21495a, this.l);
                a10.put("tr_id", this.k);
                return a10;
            }
        }

        public boolean b() {
            return this.f21522a == LAUNCH_TYPE.HUMIDITY || this.f21522a == LAUNCH_TYPE.AQI || this.f21522a == LAUNCH_TYPE.TEMPERATURE || this.f21522a == LAUNCH_TYPE.SUN_RISE || this.f21522a == LAUNCH_TYPE.SUN_SET;
        }
    }

    public static abstract class ConditionDevice {

        /* renamed from: a  reason: collision with root package name */
        public String f21523a;
        public String b;
        public String c;
        public String d;
        public int e = -1;
        public int f = -1;
        public int g = -1;
        public int h = -1;
        public int[] i;
        public String j;
        public int k = -1;

        public abstract JSONObject a() throws JSONException;

        public static ConditionDevice a(JSONObject jSONObject) {
            ConditionDeviceCommon conditionDeviceCommon = new ConditionDeviceCommon();
            JSONObject optJSONObject = jSONObject.optJSONObject("timespan");
            Device a2 = SceneApi.b(jSONObject);
            if (a2 == null) {
                return conditionDeviceCommon;
            }
            ConditionDeviceCommon conditionDeviceCommon2 = conditionDeviceCommon;
            conditionDeviceCommon2.l = jSONObject.opt("value");
            conditionDeviceCommon.d = a2.model;
            conditionDeviceCommon.b = jSONObject.optString("name");
            conditionDeviceCommon.c = jSONObject.optString("device_name");
            conditionDeviceCommon.f21523a = jSONObject.optString("did");
            if (jSONObject.has("tempId")) {
                conditionDeviceCommon.k = jSONObject.optInt("tempId");
            }
            if (jSONObject.has("key")) {
                conditionDeviceCommon.j = jSONObject.optString("key");
            }
            if (jSONObject.has("extra")) {
                conditionDeviceCommon2.m = jSONObject.optString("extra");
            }
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("from");
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("to");
                JSONArray optJSONArray = optJSONObject.optJSONArray("wday");
                if (optJSONObject2 != null) {
                    conditionDeviceCommon.e = optJSONObject2.optInt("hour");
                    conditionDeviceCommon.g = optJSONObject2.optInt("min");
                }
                if (optJSONObject3 != null) {
                    conditionDeviceCommon.f = optJSONObject3.optInt("hour");
                    conditionDeviceCommon.h = optJSONObject3.optInt("min");
                }
                if (optJSONArray != null) {
                    conditionDeviceCommon.i = new int[optJSONArray.length()];
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        try {
                            conditionDeviceCommon.i[i2] = optJSONArray.getInt(i2);
                        } catch (JSONException unused) {
                        }
                    }
                }
            }
            return conditionDeviceCommon;
        }
    }

    /* access modifiers changed from: private */
    public static Device b(JSONObject jSONObject) {
        Device k;
        Device n;
        String optString = jSONObject.optString("did");
        if (!TextUtils.isEmpty(optString) && (n = SmartHomeDeviceManager.a().n(optString)) != null) {
            return n;
        }
        String optString2 = jSONObject.optString("model");
        if (!TextUtils.isEmpty(optString2) && (k = DeviceFactory.k(optString2)) != null) {
            return k;
        }
        String[] split = jSONObject.optString("key").split("\\.");
        if (split.length < 3) {
            return null;
        }
        try {
            if (!split[0].equalsIgnoreCase("event")) {
                if (!split[0].equalsIgnoreCase(XmBluetoothRecord.TYPE_PROP)) {
                    return DeviceFactory.k(split[0] + "." + split[1] + "." + split[2]);
                }
            }
            return DeviceFactory.k(split[1] + "." + split[2] + "." + split[3]);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static class ConditionDeviceCommon extends ConditionDevice {
        public Object l = new Object();
        public String m = "";

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", this.j);
            jSONObject.put("did", this.f21523a);
            jSONObject.put("name", this.b);
            jSONObject.put("value", this.l);
            jSONObject.put("device_name", this.c);
            jSONObject.put("tempId", this.k);
            jSONObject.put("extra", this.m);
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (this.e != -1) {
                jSONObject3.put("hour", this.e);
                jSONObject3.put("min", this.g);
                jSONObject4.put("hour", this.f);
                jSONObject4.put("min", this.h);
                if (this.i != null) {
                    for (int put : this.i) {
                        jSONArray.put(put);
                    }
                }
                jSONObject2.put("from", jSONObject3);
                jSONObject2.put("to", jSONObject4);
                jSONObject2.put("wday", jSONArray);
                jSONObject.put("timespan", jSONObject2);
            }
            return jSONObject;
        }
    }

    public static class ConditionMiKey extends ConditionDevice {
        public String l;

        public static ConditionMiKey b(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            ConditionMiKey conditionMiKey = new ConditionMiKey();
            if (jSONObject.has("key")) {
                conditionMiKey.l = jSONObject.optString("key");
            }
            if (jSONObject.has("event")) {
                conditionMiKey.j = jSONObject.getString("event");
            }
            if (!TextUtils.isEmpty(conditionMiKey.l)) {
                String[] split = conditionMiKey.l.split("@");
                if (split.length == 3 && split[0].equalsIgnoreCase("mikey")) {
                    conditionMiKey.f21523a = split[1];
                    conditionMiKey.j = split[2];
                }
            }
            return conditionMiKey;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event", this.j);
            if (TextUtils.isEmpty(this.l) && !TextUtils.isEmpty(this.f21523a) && !TextUtils.isEmpty(this.j)) {
                this.l = String.format("mikey@%s@%s", new Object[]{this.f21523a, this.j});
            }
            jSONObject.put("key", this.l);
            return jSONObject;
        }
    }

    public static class ConditionMiBand extends ConditionDevice {
        public String l;

        public static ConditionMiBand b(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            ConditionMiBand conditionMiBand = new ConditionMiBand();
            if (jSONObject.has("key")) {
                conditionMiBand.l = jSONObject.optString("key");
            }
            if (jSONObject.has("event")) {
                conditionMiBand.j = jSONObject.getString("event");
            }
            if (!TextUtils.isEmpty(conditionMiBand.l)) {
                String[] split = conditionMiBand.l.split("@");
                if (split.length == 3 && split[0].equalsIgnoreCase("miband")) {
                    conditionMiBand.f21523a = split[1];
                    conditionMiBand.j = split[2];
                }
            }
            conditionMiBand.d = "xiaomi.ble.v1";
            return conditionMiBand;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event", this.j);
            if (TextUtils.isEmpty(this.l) && !TextUtils.isEmpty(this.f21523a) && !TextUtils.isEmpty(this.j)) {
                this.l = String.format("miband@%s@%s", new Object[]{this.f21523a, this.j});
            }
            jSONObject.put("key", this.l);
            return jSONObject;
        }
    }

    public static class ConditionUser {

        /* renamed from: a  reason: collision with root package name */
        public String f21524a;
        public String b;

        public static ConditionUser a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            ConditionUser conditionUser = new ConditionUser();
            conditionUser.f21524a = jSONObject.optString("key");
            conditionUser.b = jSONObject.optString("name");
            return conditionUser;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", this.f21524a);
            jSONObject.put("name", this.b);
            return jSONObject;
        }
    }

    public static class ConditionWeather {

        /* renamed from: a  reason: collision with root package name */
        public String f21525a;
        public String b;
        public Object c;
        public int d;
        public String e;
        public String f;
        public String g;
        public int h;

        public static ConditionWeather a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            ConditionWeather conditionWeather = new ConditionWeather();
            conditionWeather.f21525a = jSONObject.optString("key", "");
            conditionWeather.b = jSONObject.optString("name", "");
            conditionWeather.c = jSONObject.opt("value");
            conditionWeather.d = jSONObject.optInt("condition_id", 0);
            conditionWeather.e = jSONObject.optString("city_id", "");
            conditionWeather.f = jSONObject.optString("city_name", "");
            conditionWeather.g = jSONObject.optString("sub_name", "");
            conditionWeather.h = jSONObject.optInt("default_value", 0);
            return conditionWeather;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", this.f21525a);
            jSONObject.put("name", this.b);
            jSONObject.put("value", this.c);
            jSONObject.put("condition_id", this.d);
            jSONObject.put("city_id", this.e);
            jSONObject.put("city_name", this.f);
            jSONObject.put("sub_name", this.g);
            jSONObject.put("default_value", this.h);
            return jSONObject;
        }
    }

    public static class LaunchUser {

        /* renamed from: a  reason: collision with root package name */
        public String f21528a;

        public static LaunchUser a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            LaunchUser launchUser = new LaunchUser();
            launchUser.f21528a = jSONObject.optString("key");
            return launchUser;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", this.f21528a);
            return jSONObject;
        }
    }

    public static class LaunchSceneTimer {

        /* renamed from: a  reason: collision with root package name */
        public CorntabUtils.CorntabParam f21527a;

        public static LaunchSceneTimer a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            String optString = jSONObject.optString("key");
            LaunchSceneTimer launchSceneTimer = new LaunchSceneTimer();
            try {
                launchSceneTimer.f21527a = CorntabUtils.d(optString);
            } catch (Exception unused) {
            }
            return launchSceneTimer;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", CorntabUtils.b(this.f21527a));
            return jSONObject;
        }

        public boolean a(LaunchSceneTimer launchSceneTimer) {
            return this.f21527a.c == launchSceneTimer.f21527a.c && this.f21527a.b == launchSceneTimer.f21527a.b && this.f21527a.d == launchSceneTimer.f21527a.d && this.f21527a.d() == this.f21527a.d();
        }
    }

    public static class Action {

        /* renamed from: a  reason: collision with root package name */
        public int f21521a;
        public String b;
        public String c;
        public int d;
        public String e;
        public int f;
        public SHSceneItemPayload g;
        public SHSceneItemExtra h;

        public enum ACTION_TYPE {
            TYPE_DEVICE(0),
            TYPE_PUSH(1),
            TYPE_LITE_SCENE(2),
            TYPE_AUTO_SCENE(3);
            
            public int value;

            private ACTION_TYPE(int i) {
                this.value = i;
            }
        }

        public static Action a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            Action action = new Action();
            action.f21521a = jSONObject.optInt("type");
            action.b = jSONObject.optString("name");
            action.e = jSONObject.optString("model");
            action.f = jSONObject.optInt("sa_id");
            if (jSONObject.has("tr_id")) {
                action.d = jSONObject.optInt("tr_id");
            }
            action.c = jSONObject.optString("keyName");
            JSONObject optJSONObject = jSONObject.optJSONObject(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
            if (action.f21521a == 1) {
                if (optJSONObject == null) {
                    action.g = new SHScenePushPayload();
                } else {
                    action.g = SHScenePushPayload.a(optJSONObject);
                }
            } else if (action.f21521a == 2) {
                action.g = SHLiteScenePayload.a(optJSONObject);
            } else if (action.f21521a == 3) {
                action.g = SHSceneAutoPayload.a(optJSONObject);
            } else if (optJSONObject != null && action.f21521a == 0) {
                action.g = SHSceneItemPayloadCommon.a(optJSONObject, action.f);
            }
            if (action.g == null) {
                return null;
            }
            return action;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", this.b);
            jSONObject.put("type", this.f21521a);
            jSONObject.put("model", this.e);
            jSONObject.put("tr_id", this.d);
            jSONObject.put("sa_id", this.f);
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("keyName", this.c);
            }
            if (this.g != null) {
                jSONObject.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, this.g.a());
            }
            if (this.h != null) {
                jSONObject.put("extra", this.h.a());
            }
            return jSONObject;
        }

        public String toString() {
            return "Action{type=" + this.f21521a + ", name='" + this.b + Operators.SINGLE_QUOTE + ", keyName='" + this.c + Operators.SINGLE_QUOTE + ", mCompatibleId=" + this.d + ", deviceModel='" + this.e + Operators.SINGLE_QUOTE + ", payload=" + this.g + ", extra=" + this.h + Operators.BLOCK_END;
        }
    }

    public static class SHLiteScenePayload extends SHSceneItemPayload {

        /* renamed from: a  reason: collision with root package name */
        public String f21529a;

        public static SHLiteScenePayload a(JSONObject jSONObject) {
            SHLiteScenePayload sHLiteScenePayload = new SHLiteScenePayload();
            sHLiteScenePayload.f21529a = jSONObject.optString("us_id");
            if (jSONObject.has("delay_time")) {
                sHLiteScenePayload.g = jSONObject.optInt("delay_time");
            }
            return sHLiteScenePayload;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("us_id", this.f21529a);
            if (this.g != -1) {
                jSONObject.put("delay_time", this.g);
            }
            return jSONObject;
        }
    }

    public static class SHSceneAutoPayload extends SHSceneItemPayload {

        /* renamed from: a  reason: collision with root package name */
        public String f21530a;
        public int b = -1;

        public static SHSceneAutoPayload a(JSONObject jSONObject) {
            SHSceneAutoPayload sHSceneAutoPayload = new SHSceneAutoPayload();
            sHSceneAutoPayload.f21530a = jSONObject.optString("us_id");
            if (jSONObject.has("delay_time")) {
                sHSceneAutoPayload.g = jSONObject.optInt("delay_time");
            }
            if (jSONObject.has(AutoSceneAction.f21495a)) {
                sHSceneAutoPayload.b = jSONObject.optInt(AutoSceneAction.f21495a);
            }
            return sHSceneAutoPayload;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("us_id", this.f21530a);
            if (this.g != -1) {
                jSONObject.put("delay_time", this.g);
            }
            if (this.b != -1) {
                jSONObject.put(AutoSceneAction.f21495a, this.b);
            }
            return jSONObject;
        }
    }

    public static class SHScenePushPayload extends SHSceneItemPayload {

        /* renamed from: a  reason: collision with root package name */
        public String f21532a;
        public String b;

        public static SHScenePushPayload a(JSONObject jSONObject) {
            SHScenePushPayload sHScenePushPayload = new SHScenePushPayload();
            sHScenePushPayload.f21532a = jSONObject.optString("title");
            sHScenePushPayload.b = jSONObject.optString("desc");
            sHScenePushPayload.g = jSONObject.optInt("delay_time");
            return sHScenePushPayload;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", this.f21532a);
            jSONObject.put("desc", this.b);
            if (this.g != -1) {
                jSONObject.put("delay_time", this.g);
            }
            return jSONObject;
        }
    }

    public static class SHSceneItemPayloadCommon extends SHSceneItemPayload {

        /* renamed from: a  reason: collision with root package name */
        public String f21531a;
        public JSONObject b;
        public int i = -1;
        public String j;

        public static SHSceneItemPayloadCommon a(JSONObject jSONObject, int i2) {
            if (jSONObject == null) {
                return null;
            }
            SHSceneItemPayloadCommon sHSceneItemPayloadCommon = new SHSceneItemPayloadCommon();
            sHSceneItemPayloadCommon.c = jSONObject.optString(CommandMessage.COMMAND);
            sHSceneItemPayloadCommon.e = jSONObject.optString("did");
            sHSceneItemPayloadCommon.g = jSONObject.optInt("delay_time");
            if (i2 > 0) {
                sHSceneItemPayloadCommon.i = i2;
            } else if (jSONObject.has("tempId")) {
                sHSceneItemPayloadCommon.i = jSONObject.optInt("tempId");
            }
            if (jSONObject.has("value")) {
                sHSceneItemPayloadCommon.f = jSONObject.opt("value");
            }
            if (jSONObject.has("extra")) {
                sHSceneItemPayloadCommon.f21531a = jSONObject.optString("extra");
            }
            if (jSONObject.has("plug_id")) {
                sHSceneItemPayloadCommon.j = jSONObject.optString("plug_id");
            }
            sHSceneItemPayloadCommon.b = jSONObject;
            return sHSceneItemPayloadCommon;
        }

        public JSONObject a() throws JSONException {
            this.b = new JSONObject();
            this.b.put(CommandMessage.COMMAND, this.c);
            this.b.put("did", this.e);
            if (this.g != -1) {
                this.b.put("delay_time", this.g);
            }
            if (this.i != -1) {
                this.b.put("tempId", this.i);
            }
            if (this.f != null) {
                this.b.put("value", this.f);
            }
            if (this.f21531a != null) {
                this.b.put("extra", this.f21531a);
            }
            if (!TextUtils.isEmpty(this.j)) {
                this.b.put("plug_id", this.j);
            }
            this.b.put("total_length", this.h);
            return this.b;
        }
    }
}
