package com.imagepicker;

import android.support.annotation.StyleRes;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImagePickerPackage implements ReactPackage {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6063a = R.style.DefaultExplainingPermissionsTheme;
    @StyleRes
    private final int b;

    public ImagePickerPackage() {
        this.b = f6063a;
    }

    public ImagePickerPackage(@StyleRes int i) {
        this.b = i;
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new NativeModule[]{new ImagePickerModule(reactApplicationContext, this.b)});
    }

    public List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
