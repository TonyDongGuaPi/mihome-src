package com.xiaomi.smarthome.scenenew.pluginrecommend;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import java.util.ArrayList;

public class ActionUI extends RecommendUI {
    public ActionUI(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        super(recommendSceneItem);
    }

    public void a(BaseActivity baseActivity, SceneApi.SmartHomeScene smartHomeScene, int i, RecommendSceneCreator.OnSelectCallback onSelectCallback) {
        a(baseActivity, smartHomeScene.l.get(i), onSelectCallback);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.xiaomi.smarthome.scene.api.SceneApi$Condition} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene a(com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene r4) {
        /*
            r3 = this;
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r0 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition>> r0 = r0.e
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0022
            java.lang.Object r0 = r0.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r0 = r0.getValue()
            java.util.List r0 = (java.util.List) r0
            goto L_0x0023
        L_0x0022:
            r0 = r2
        L_0x0023:
            if (r0 == 0) goto L_0x0033
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0033
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            r2 = r0
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r2 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r2
        L_0x0033:
            if (r2 == 0) goto L_0x004c
            android.util.SparseArray r0 = r3.d
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice r1 = r2.c
            int r1 = r1.k
            java.lang.Object r0 = r0.get(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x004c
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r4.l
            r0.add(r2)
        L_0x004c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.pluginrecommend.ActionUI.a(com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene):com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene");
    }

    public SceneApi.SmartHomeScene a(SceneApi.SmartHomeScene smartHomeScene, Device device) {
        if (RecommendSceneCreator.a().f != null && RecommendSceneCreator.a().f.size() > 0) {
            int i = 0;
            String[] strArr = (String[]) RecommendSceneCreator.a().f.keySet().toArray(new String[0]);
            if (strArr.length > 0) {
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    arrayList.addAll(RecommendSceneCreator.a().f.get(str));
                }
                if (arrayList.size() > 0) {
                    while (true) {
                        if (i < arrayList.size()) {
                            if (((SceneApi.Action) arrayList.get(i)).g != null && TextUtils.equals(((SceneApi.Action) arrayList.get(i)).g.e, device.did)) {
                                smartHomeScene.k.add(arrayList.get(i));
                                break;
                            }
                            i++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return smartHomeScene;
    }

    public String a(SceneApi.SmartHomeScene smartHomeScene, int i) {
        if (smartHomeScene == null || smartHomeScene.l == null || smartHomeScene.l.isEmpty()) {
            return null;
        }
        return c(smartHomeScene.l.get(i));
    }

    public String b(SceneApi.SmartHomeScene smartHomeScene, int i) {
        if (smartHomeScene.l == null || smartHomeScene.l.size() <= i) {
            return null;
        }
        return b(smartHomeScene.l.get(i));
    }

    public String c(SceneApi.SmartHomeScene smartHomeScene, int i) {
        SceneApi.ConditionDevice conditionDevice;
        Device b;
        if (smartHomeScene == null || smartHomeScene.l == null || smartHomeScene.l.size() <= i || (conditionDevice = smartHomeScene.l.get(i).c) == null || (b = SmartHomeDeviceManager.a().b(conditionDevice.f21523a)) == null) {
            return null;
        }
        return TextUtils.isEmpty(b.name) ? "" : b.name;
    }
}
