package com.xiaomi.smarthome.scene.action;

import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;

public class SmartHomeScenceActionFactory {
    public static BaseInnerAction a(Object obj) {
        if (obj instanceof Device) {
            Device device = (Device) obj;
            if (!(SceneManager.x().a(device.model, device.did) == null || SceneManager.x().a(device.model, device.did).f == null || SceneManager.x().a(device.model, device.did).f.size() <= 0)) {
                return new InnerActionCommon(device, (SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) null);
            }
        }
        return null;
    }

    public static BaseInnerAction a(Object obj, SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet) {
        if (!(obj instanceof Device)) {
            return null;
        }
        Device device = (Device) obj;
        if (SceneManager.x().a(device.model, device.did) == null || SceneManager.x().a(device.model, device.did).f == null || SceneManager.x().a(device.model, device.did).f.size() <= 0) {
            return null;
        }
        return new InnerActionCommon(device, defaultSceneItemSet);
    }
}
