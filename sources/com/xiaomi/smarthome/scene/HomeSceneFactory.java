package com.xiaomi.smarthome.scene;

import android.content.Context;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;

public enum HomeSceneFactory {
    INSTANCE;
    
    public static final String PHONE_SCENE_CON_KEY = "come_home";
    public static final String PHONE_SCENE_DISCON_KEY = "leave_home";

    public SceneApi.Condition createHomeDeviceCondition(Device device, RecommendSceneItem recommendSceneItem) {
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.c = new SceneApi.ConditionDeviceCommon();
        condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.DEVICE;
        condition.c.d = device.model;
        condition.c.f21523a = device.did;
        condition.c.c = device.name;
        String str = null;
        int i = 0;
        RecommendSceneItem.RemommendSceneCondition remommendSceneCondition = null;
        for (RecommendSceneItem.RemommendSceneCondition remommendSceneCondition2 : recommendSceneItem.mRecommendConditionList) {
            if (remommendSceneCondition2.mDeviceModels != null) {
                String[] strArr = remommendSceneCondition2.mDeviceModels;
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (DeviceFactory.i(device.model, strArr[i2])) {
                        remommendSceneCondition = remommendSceneCondition2;
                        break;
                    }
                    i2++;
                }
                if (remommendSceneCondition != null) {
                    break;
                }
            }
        }
        if (remommendSceneCondition == null) {
            return null;
        }
        condition.c.b = remommendSceneCondition.mConditionName;
        condition.l = true;
        String str2 = remommendSceneCondition.mKeys[0].mKey;
        ((SceneApi.ConditionDeviceCommon) condition.c).l = remommendSceneCondition.mKeys[0].mValues;
        String[] strArr2 = remommendSceneCondition.mDeviceModels;
        int length2 = strArr2.length;
        while (true) {
            if (i >= length2) {
                break;
            }
            String str3 = strArr2[i];
            if (str2.contains(str3)) {
                str = str2.replace(str3, device.model);
                break;
            }
            i++;
        }
        condition.c.j = str;
        return condition;
    }

    public SceneApi.Condition createPhoneCondition(RecommendSceneItem recommendSceneItem) {
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.d = new SceneApi.LaunchUser();
        condition.l = true;
        boolean a2 = SceneManager.x().a(recommendSceneItem);
        RecommendSceneItem.RemommendSceneCondition[] remommendSceneConditionArr = recommendSceneItem.mRecommendConditionList;
        int length = remommendSceneConditionArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            RecommendSceneItem.RemommendSceneCondition remommendSceneCondition = remommendSceneConditionArr[i];
            if (!a2 || !PHONE_SCENE_CON_KEY.equalsIgnoreCase(remommendSceneCondition.mKeys[0].mKey)) {
                if (!a2 && PHONE_SCENE_DISCON_KEY.equalsIgnoreCase(remommendSceneCondition.mKeys[0].mKey)) {
                    condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.LEAVE_HOME;
                    condition.d.f21528a = PHONE_SCENE_DISCON_KEY;
                    break;
                }
                i++;
            } else {
                condition.d.f21528a = PHONE_SCENE_CON_KEY;
                condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.COME_HOME;
                break;
            }
        }
        return condition;
    }

    public SceneApi.Condition createTimerCondition(CorntabUtils.CorntabParam corntabParam) {
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.TIMER;
        condition.b = new SceneApi.LaunchSceneTimer();
        condition.b.f21527a = corntabParam;
        condition.l = true;
        return condition;
    }

    public CorntabUtils.CorntabParam getDefaultGoHomeTimer() {
        CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
        corntabParam.c = 18;
        corntabParam.b = 0;
        corntabParam.d = -1;
        corntabParam.e = -1;
        corntabParam.a(127);
        return corntabParam;
    }

    public CorntabUtils.CorntabParam getDefaultLeaveHomeTimer() {
        CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
        corntabParam.c = 8;
        corntabParam.b = 0;
        corntabParam.d = -1;
        corntabParam.e = -1;
        corntabParam.a(127);
        return corntabParam;
    }

    public SceneApi.SmartHomeScene createDefaultGoHomeScene(Context context, RecommendSceneItem recommendSceneItem) {
        if (recommendSceneItem == null || recommendSceneItem.mRecommId != 151) {
            return null;
        }
        SceneApi.SmartHomeScene smartHomeScene = new SceneApi.SmartHomeScene();
        smartHomeScene.i = recommendSceneItem.mRecommId;
        smartHomeScene.o = true;
        smartHomeScene.n = true;
        smartHomeScene.q = 1;
        smartHomeScene.g = recommendSceneItem.mName;
        smartHomeScene.h = true;
        smartHomeScene.k = new ArrayList();
        smartHomeScene.l = new ArrayList();
        smartHomeScene.x = new SceneApi.SmartHomeScene.GroupCondition();
        smartHomeScene.x.f = null;
        smartHomeScene.x.b = true;
        smartHomeScene.x.d = 1;
        smartHomeScene.x.e = new ArrayList();
        smartHomeScene.x.c = context.getString(R.string.scene2_device_group_condition_desc);
        smartHomeScene.h = true;
        smartHomeScene.m = true;
        return smartHomeScene;
    }

    public SceneApi.SmartHomeScene createDefaultLeaveHomeScene(Context context, RecommendSceneItem recommendSceneItem) {
        if (recommendSceneItem == null || recommendSceneItem.mRecommId != 152) {
            return null;
        }
        SceneApi.SmartHomeScene smartHomeScene = new SceneApi.SmartHomeScene();
        smartHomeScene.i = recommendSceneItem.mRecommId;
        smartHomeScene.o = true;
        smartHomeScene.n = true;
        smartHomeScene.q = 1;
        smartHomeScene.h = true;
        smartHomeScene.g = recommendSceneItem.mName;
        smartHomeScene.k = new ArrayList();
        smartHomeScene.l = new ArrayList();
        smartHomeScene.x = new SceneApi.SmartHomeScene.GroupCondition();
        smartHomeScene.x.f = null;
        smartHomeScene.x.b = true;
        smartHomeScene.x.d = 0;
        smartHomeScene.x.e = new ArrayList();
        smartHomeScene.x.c = context.getString(R.string.scene2_device_group_condition_desc);
        smartHomeScene.h = true;
        smartHomeScene.m = true;
        return smartHomeScene;
    }
}
