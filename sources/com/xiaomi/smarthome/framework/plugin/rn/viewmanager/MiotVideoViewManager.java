package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import com.brentvatne.react.ReactVideoPackage;
import com.brentvatne.react.ReactVideoView;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.List;

public class MiotVideoViewManager extends ReactVideoPackage {
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ViewManager[]{new ReactVideoViewManager() {
            /* access modifiers changed from: protected */
            public ReactVideoView createViewInstance(ThemedReactContext themedReactContext) {
                return new ReactVideoView(themedReactContext) {
                };
            }
        }});
    }
}
