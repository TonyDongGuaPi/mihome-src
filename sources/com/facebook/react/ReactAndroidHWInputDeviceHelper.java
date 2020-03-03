package com.facebook.react;

import android.view.KeyEvent;
import android.view.View;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.taobao.weex.common.Constants;
import java.util.Map;

public class ReactAndroidHWInputDeviceHelper {
    private static final Map<Integer, String> KEY_EVENTS_ACTIONS = MapBuilder.builder().put(23, "select").put(66, "select").put(62, "select").put(85, "playPause").put(89, "rewind").put(90, "fastForward").put(19, "up").put(22, "right").put(20, "down").put(21, "left").build();
    private int mLastFocusedViewId = -1;
    private final ReactRootView mReactRootView;

    ReactAndroidHWInputDeviceHelper(ReactRootView reactRootView) {
        this.mReactRootView = reactRootView;
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        if ((action == 1 || action == 0) && KEY_EVENTS_ACTIONS.containsKey(Integer.valueOf(keyCode))) {
            dispatchEvent(KEY_EVENTS_ACTIONS.get(Integer.valueOf(keyCode)), this.mLastFocusedViewId, action);
        }
    }

    public void onFocusChanged(View view) {
        if (this.mLastFocusedViewId != view.getId()) {
            if (this.mLastFocusedViewId != -1) {
                dispatchEvent(Constants.Event.BLUR, this.mLastFocusedViewId);
            }
            this.mLastFocusedViewId = view.getId();
            dispatchEvent(Constants.Event.FOCUS, view.getId());
        }
    }

    public void clearFocus() {
        if (this.mLastFocusedViewId != -1) {
            dispatchEvent(Constants.Event.BLUR, this.mLastFocusedViewId);
        }
        this.mLastFocusedViewId = -1;
    }

    private void dispatchEvent(String str, int i) {
        dispatchEvent(str, i, -1);
    }

    private void dispatchEvent(String str, int i, int i2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("eventType", str);
        writableNativeMap.putInt("eventKeyAction", i2);
        if (i != -1) {
            writableNativeMap.putInt("tag", i);
        }
        this.mReactRootView.sendEvent("onHWKeyEvent", writableNativeMap);
    }
}