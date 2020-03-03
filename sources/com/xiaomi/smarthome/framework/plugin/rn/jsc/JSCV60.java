package com.xiaomi.smarthome.framework.plugin.rn.jsc;

import android.os.Build;
import android.util.Log;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.jscexecutor.JSCExecutorFactory;
import com.facebook.soloader.SoLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class JSCV60 extends JSC {
    private static final AtomicLong d = new AtomicLong(100);
    private static final long f = 2000000;

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, ModuleHolder> f17297a = new HashMap();
    private JSCallee b = null;
    private CatalystInstanceImpl c = null;
    private final String e = ("jsc_" + d.incrementAndGet());

    public String a() {
        return this.e;
    }

    public JSCV60(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void a(NativeModule nativeModule) {
        String name = nativeModule.getName();
        Class<?> cls = nativeModule.getClass();
        if (this.f17297a.containsKey(name)) {
            ModuleHolder moduleHolder = this.f17297a.get(name);
            if (moduleHolder.getCanOverrideExistingModule()) {
                this.f17297a.remove(moduleHolder);
            } else {
                throw new IllegalStateException("Native module " + cls.getSimpleName() + " tried to override " + moduleHolder.getClassName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
            }
        }
        this.f17297a.put(name, new ModuleHolder(nativeModule));
    }

    private ReactQueueConfigurationSpec a(String str) {
        MessageQueueThreadSpec messageQueueThreadSpec;
        if (Build.VERSION.SDK_INT < 21) {
            messageQueueThreadSpec = MessageQueueThreadSpec.newBackgroundThreadSpec(str, f);
        } else {
            messageQueueThreadSpec = MessageQueueThreadSpec.newBackgroundThreadSpec(str);
        }
        ReactQueueConfigurationSpec.Builder builder = ReactQueueConfigurationSpec.builder();
        return builder.setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec(str + "_js")).setNativeModulesQueueThreadSpec(messageQueueThreadSpec).build();
    }

    private JavaScriptExecutorFactory a(String str, String str2) {
        try {
            SoLoader.loadLibrary("jscexecutor");
            return new JSCExecutorFactory(str, str2);
        } catch (UnsatisfiedLinkError unused) {
            return new HermesExecutorFactory();
        }
    }

    public void a(final ReactApplicationContext reactApplicationContext, final String str, String str2) throws Exception {
        JavaScriptExecutor create = a("JSC", "jscDevice").create();
        this.b = new JSCallee();
        a((NativeModule) this.b);
        NativeModuleRegistry nativeModuleRegistry = new NativeModuleRegistry(reactApplicationContext, this.f17297a);
        CatalystInstanceImpl.Builder builder = new CatalystInstanceImpl.Builder();
        builder.setJSBundleLoader(new JSBundleLoader() {
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                JSBundleLoader.createAssetLoader(reactApplicationContext, "assets://plugin/RNExecutorBase.jx", true).loadScript(jSBundleLoaderDelegate);
                if (str.startsWith("assets://")) {
                    return JSBundleLoader.createAssetLoader(reactApplicationContext, str, true).loadScript(jSBundleLoaderDelegate);
                }
                return JSBundleLoader.createFileLoader(str, str, true).loadScript(jSBundleLoaderDelegate);
            }
        });
        builder.setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler() {
            public void handleException(Exception exc) {
                Log.i("JSCORE", "error raised", exc);
            }
        });
        builder.setReactQueueConfigurationSpec(a(a()));
        builder.setJSExecutor(create);
        builder.setRegistry(nativeModuleRegistry);
        this.c = builder.build();
        this.c.setGlobalVariable("initialProps", str2);
        this.c.runJSBundle();
    }

    public boolean b() {
        return this.c != null && this.c.hasRunJSBundle() && !this.c.isDestroyed();
    }

    public void close() {
        CatalystInstanceImpl catalystInstanceImpl = this.c;
        if (catalystInstanceImpl != null) {
            if (catalystInstanceImpl.isDestroyed()) {
                this.c = null;
                return;
            }
            synchronized (this) {
                if (!catalystInstanceImpl.isDestroyed()) {
                    catalystInstanceImpl.destroy();
                }
            }
            this.c = null;
        }
    }

    public void a(String str, String str2, JSCallback jSCallback) {
        if (this.b == null || !b()) {
            jSCallback.b("jsc is not ready");
            return;
        }
        String add = this.b.add(jSCallback);
        try {
            ((JSCaller) this.c.getJSModule(JSCaller.class)).invoke(add, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!this.b.checkInvokedAndRemove(add)) {
                jSCallback.b(e2.getMessage());
            }
        }
    }
}
