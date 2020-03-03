package com.xiaomi.miot.store.component;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.xiaomi.miot.store.component.linear_gradient.LinearGradientManager;
import com.xiaomi.miot.store.component.lottie.LottieAnimationViewManager;
import com.xiaomi.miot.store.component.video.ReactExoplayerViewManager;
import com.xiaomi.miot.store.component.viewOverflow.RNViewOverflowManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomComponetPackage implements ReactPackage {
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ViewManager[]{new LottieAnimationViewManager(), new LinearGradientManager(), new ReactExoplayerViewManager(), new RNViewOverflowManager()});
    }
}
