package com.xiaomi.smarthome.scene;

import com.xiaomi.smarthome.scene.api.SceneApi;

public enum SceneDataCache {
    INSTANCE;
    
    private SceneApi.SmartHomeScene cachedScene;

    public void setCachedScene(SceneApi.SmartHomeScene smartHomeScene) {
        this.cachedScene = smartHomeScene;
    }

    public SceneApi.SmartHomeScene getCahcedScene() {
        return this.cachedScene;
    }
}
