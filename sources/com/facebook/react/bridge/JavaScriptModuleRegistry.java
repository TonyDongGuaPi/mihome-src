package com.facebook.react.bridge;

import android.support.annotation.Nullable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public final class JavaScriptModuleRegistry {
    private final HashMap<Class<? extends JavaScriptModule>, JavaScriptModule> mModuleInstances = new HashMap<>();

    public synchronized <T extends JavaScriptModule> T getJavaScriptModule(CatalystInstance catalystInstance, Class<T> cls) {
        T t = (JavaScriptModule) this.mModuleInstances.get(cls);
        if (t != null) {
            return t;
        }
        T t2 = (JavaScriptModule) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new JavaScriptModuleInvocationHandler(catalystInstance, cls));
        this.mModuleInstances.put(cls, t2);
        return t2;
    }

    private static class JavaScriptModuleInvocationHandler implements InvocationHandler {
        private final CatalystInstance mCatalystInstance;
        private final Class<? extends JavaScriptModule> mModuleInterface;
        @Nullable
        private String mName;

        public JavaScriptModuleInvocationHandler(CatalystInstance catalystInstance, Class<? extends JavaScriptModule> cls) {
            this.mCatalystInstance = catalystInstance;
            this.mModuleInterface = cls;
        }

        private String getJSModuleName() {
            if (this.mName == null) {
                String simpleName = this.mModuleInterface.getSimpleName();
                int lastIndexOf = simpleName.lastIndexOf(36);
                if (lastIndexOf != -1) {
                    simpleName = simpleName.substring(lastIndexOf + 1);
                }
                this.mName = simpleName;
            }
            return this.mName;
        }

        @Nullable
        public Object invoke(Object obj, Method method, @Nullable Object[] objArr) throws Throwable {
            this.mCatalystInstance.callFunction(getJSModuleName(), method.getName(), objArr != null ? Arguments.fromJavaArgs(objArr) : new WritableNativeArray());
            return null;
        }
    }
}