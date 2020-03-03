package com.swmansion.reanimated.transitions;

import android.support.annotation.RequiresApi;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;

public class TransitionModule {

    /* renamed from: a  reason: collision with root package name */
    private final UIManagerModule f8940a;

    public TransitionModule(UIManagerModule uIManagerModule) {
        this.f8940a = uIManagerModule;
    }

    public void a(final int i, final ReadableMap readableMap) {
        this.f8940a.prependUIBlock(new UIBlock() {
            @RequiresApi(api = 26)
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    View resolveView = nativeViewHierarchyManager.resolveView(i);
                    if (resolveView instanceof ViewGroup) {
                        ReadableArray array = readableMap.getArray("transitions");
                        int size = array.size();
                        for (int i = 0; i < size; i++) {
                            TransitionManager.beginDelayedTransition((ViewGroup) resolveView, TransitionUtils.a(array.getMap(i)));
                        }
                    }
                } catch (IllegalViewOperationException unused) {
                }
            }
        });
    }
}
