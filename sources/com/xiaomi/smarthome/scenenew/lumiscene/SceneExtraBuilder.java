package com.xiaomi.smarthome.scenenew.lumiscene;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class SceneExtraBuilder {
    private static final String A = "^event\\.(\\S+)\\.neutral_0_click$";
    private static final String B = "^event\\.(\\S+)\\.neutral_1_click$";
    private static final String C = "^event\\.(\\S*)sensor_86sw1(\\S*)\\.click_ch0$";
    private static final String D = "^event\\.(\\S*)sensor_86sw1(\\S*)\\.double_click_ch0$";
    private static final String E = "^event\\.(\\S*)sensor_86sw2(\\S*)\\.click_ch0$";
    private static final String F = "^event\\.(\\S*)sensor_86sw2(\\S*)\\.double_click_ch0$";
    private static final String G = "^event\\.(\\S*)sensor_86sw2(\\S*)\\.click_ch1$";
    private static final String H = "^event\\.(\\S*)sensor_86sw2(\\S*)\\.double_click_ch1$";
    private static final String I = "^event\\.(\\S*)sensor_86sw2(\\S*)\\.both_click$";
    private static Set<String> J = new LinkedHashSet();
    private static final SceneExtraBuilder K = new SceneExtraBuilder();

    /* renamed from: a  reason: collision with root package name */
    private static final String f21957a = "active";
    private static final String b = "nonActive";
    private static final String c = "default";
    private static final String d = "lumi_extra_map_info";
    private static final String e = "lumi_extra_map_info_preview";
    private static final String f = "4";
    private static final String g = "en";
    private static final String h = "scene_info";
    private static Map<String, JSONObject[]> i = new LinkedHashMap();
    private static final String j = "^event\\.(\\S*)sensor_switch.v(\\S*)\\.click$";
    private static final String k = "^event\\.(\\S*)sensor_switch.v(\\S*)\\.double_click$";
    private static final String l = "^event\\.(\\S*)sensor_switch.v(\\S*)\\.long_click_press$";
    private static final String m = "^event\\.(\\S*)sensor_switch.aq2(\\S*)\\.click$";
    private static final String n = "^event\\.(\\S*)sensor_switch.aq2(\\S*)\\.double_click$";
    private static final String o = "^event\\.(\\S*)sensor_switch.aq2(\\S*)\\.long_click_press$";
    private static final String p = "^event\\.(\\S*)sensor_switch.aq3(\\S*)\\.click$";
    private static final String q = "^event\\.(\\S*)sensor_switch.aq3(\\S*)\\.double_click$";
    private static final String r = "^event\\.(\\S*)sensor_switch.aq3(\\S*)\\.long_click_press$";
    private static final String s = "^event\\.(\\S*)sensor_switch.aq3(\\S*)\\.long_click_release$";
    private static final String t = "^event\\.(\\S*)sensor_switch.aq3(\\S*)\\.shake$";
    private static final String u = "^event\\.(\\S+)\\.tap_twice$";
    private static final String v = "^event\\.(\\S+)\\.move$";
    private static final String w = "^event\\.(\\S+)\\.flip90$";
    private static final String x = "^event\\.(\\S+)\\.flip180$";
    private static final String y = "^event\\.(\\S+)\\.shake_air$";
    private static final String z = "^event\\.(\\S+)\\.rotate$";

    static {
        J.add(j);
        J.add(k);
        J.add(l);
        J.add(m);
        J.add(n);
        J.add(o);
        J.add(p);
        J.add(q);
        J.add(r);
        J.add(s);
        J.add(t);
        J.add(A);
        J.add(B);
        J.add(C);
        J.add(D);
        J.add(E);
        J.add(F);
        J.add(G);
        J.add(H);
        J.add(I);
        J.add(x);
        J.add(w);
        J.add(v);
        J.add(z);
        J.add(y);
        J.add(u);
    }

    public static SceneExtraBuilder a() {
        return K;
    }

    public void a(final SceneInfo sceneInfo, final Callback<SceneInfo> callback) {
        if (callback != null) {
            if (sceneInfo == null) {
                callback.onFailure(-1, "sceneInfo is null...");
            } else {
                a((Callback<Boolean>) new Callback<Boolean>() {
                    /* renamed from: a */
                    public void onSuccess(Boolean bool) {
                        if (bool.booleanValue()) {
                            SceneExtraBuilder.this.a(sceneInfo);
                            callback.onSuccess(sceneInfo);
                            return;
                        }
                        callback.onFailure(-1, "getCloudExtra error");
                    }

                    public void onFailure(int i, String str) {
                        callback.onFailure(i, str);
                    }
                });
            }
        }
    }

    public void a(final Callback<Boolean> callback) {
        XmPluginHostApi.instance().getAppConfig(GlobalSetting.E ? e : d, "en", "4", new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    callback.onSuccess(Boolean.valueOf(SceneExtraBuilder.this.a(str)));
                    return;
                }
                callback.onSuccess(false);
            }

            public void onFailure(int i, String str) {
                callback.onFailure(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (jSONObject.opt(next) instanceof JSONArray) {
                    JSONArray jSONArray = jSONObject.getJSONArray(next);
                    if (jSONArray.length() == 2 && (jSONArray.get(0) instanceof JSONObject) && (jSONArray.get(1) instanceof JSONObject)) {
                        i.put(next, new JSONObject[]{(JSONObject) jSONArray.get(0), (JSONObject) jSONArray.get(1)});
                    }
                }
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a(SceneInfo sceneInfo) {
        LogUtil.a("lumiscene", "summer receive sceneInfo>>" + JSON.toJSONString(sceneInfo));
        for (SceneInfo.SceneLaunch a2 : sceneInfo.mLaunchList) {
            a(a2);
        }
        boolean a3 = a(sceneInfo.mLaunchList);
        for (SceneInfo.SceneAction a4 : sceneInfo.mActions) {
            a(a4, sceneInfo, a3);
        }
        b(sceneInfo.mActions);
    }

    private void a(SceneInfo.SceneLaunch sceneLaunch) {
        JSONObject[] jSONObjectArr;
        if (sceneLaunch != null && sceneLaunch.mEventString != null) {
            String str = null;
            for (String next : i.keySet()) {
                if (sceneLaunch.mEventString.matches(next)) {
                    str = next;
                }
            }
            if (str != null && (jSONObjectArr = i.get(str)) != null && jSONObjectArr.length == 2) {
                JSONObject jSONObject = jSONObjectArr[0];
                JSONObject jSONObject2 = jSONObjectArr[1];
                try {
                    if (jSONObject.length() == 1) {
                        LaunchExtra.a().a(sceneLaunch, jSONObject.getJSONObject(jSONObject.keys().next()).getJSONArray("active").toString(), str);
                        return;
                    }
                    String optString = jSONObject2.optString(sceneLaunch.mEventValue.toString());
                    if (optString != null) {
                        LaunchExtra.a().a(sceneLaunch, jSONObject.getJSONObject(optString).getJSONArray("active").toString(), str);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void a(SceneInfo.SceneAction sceneAction, SceneInfo sceneInfo, boolean z2) {
        JSONObject[] jSONObjectArr;
        String str;
        JSONArray jSONArray;
        if (sceneAction != null && sceneAction.mActionString != null) {
            String str2 = null;
            Iterator<String> it = i.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (sceneAction.mActionString.matches(next)) {
                    str2 = next;
                    break;
                }
            }
            if (str2 != null && (jSONObjectArr = i.get(str2)) != null && jSONObjectArr.length == 2) {
                JSONObject jSONObject = jSONObjectArr[0];
                JSONObject jSONObject2 = jSONObjectArr[1];
                try {
                    if (jSONObject.length() == 1) {
                        str = jSONObject.keys().next();
                    } else {
                        str = jSONObject2.optString(a(sceneAction));
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = "default";
                    }
                    JSONObject jSONObject3 = jSONObject.getJSONObject(str);
                    if (!z2) {
                        if (a(jSONObject3, b)) {
                            jSONArray = jSONObject3.getJSONArray(b);
                        } else {
                            jSONArray = jSONObject3.getJSONArray("active");
                        }
                        ActionExtra.a().a(sceneAction, sceneInfo, jSONArray.toString(), str2);
                        return;
                    }
                    ActionExtra.a().a(sceneAction, sceneInfo, jSONObject3.getJSONArray("active").toString(), str2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private boolean a(List<SceneInfo.SceneLaunch> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        for (SceneInfo.SceneLaunch next : list) {
            if (next != null && b(next.mEventString)) {
                return true;
            }
        }
        return false;
    }

    private boolean b(String str) {
        for (String matches : J) {
            if (str.matches(matches)) {
                return true;
            }
        }
        return false;
    }

    private void b(List<SceneInfo.SceneAction> list) {
        if (list != null) {
            int i2 = 0;
            try {
                for (SceneInfo.SceneAction next : list) {
                    if (!"delay.delay".equalsIgnoreCase(next.mActionString)) {
                        next.mDelayTime = i2;
                    } else {
                        i2 += next.mDelayTime;
                    }
                }
            } catch (NoSuchFieldError e2) {
                e2.printStackTrace();
            }
        }
    }

    private String a(SceneInfo.SceneAction sceneAction) {
        if (sceneAction.mDeviceModel.equals(DeviceModelDefine.f21947a)) {
            try {
                JSONArray jSONArray = new JSONArray(sceneAction.mActionValue.toString());
                JSONArray jSONArray2 = new JSONArray();
                if (jSONArray.length() == 2) {
                    jSONArray2.put(jSONArray.get(0));
                    jSONArray2.put(String.valueOf(jSONArray.get(1)));
                    return jSONArray2.toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sceneAction.mActionValue.toString();
    }

    private boolean a(JSONObject jSONObject, String str) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (keys.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
