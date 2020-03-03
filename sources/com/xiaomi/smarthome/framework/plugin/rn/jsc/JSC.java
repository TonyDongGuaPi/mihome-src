package com.xiaomi.smarthome.framework.plugin.rn.jsc;

import android.os.Build;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class JSC implements Closeable {
    private static final AtomicLong e = new AtomicLong(100);
    private static final long g = 2000000;

    /* renamed from: a  reason: collision with root package name */
    private final Map<Class<? extends NativeModule>, ModuleHolder> f17282a = new HashMap();
    private final Map<String, Class<? extends NativeModule>> b = new HashMap();
    private JSCallee c = null;
    private CatalystInstanceImpl d = null;
    private final String f = ("jsc_" + e.incrementAndGet());

    public void a(ReactApplicationContext reactApplicationContext, String str, String str2) throws Exception {
    }

    public String a() {
        return this.f;
    }

    public JSC(ReactApplicationContext reactApplicationContext) {
    }

    public void a(NativeModule nativeModule) {
        String name = nativeModule.getName();
        Class<?> cls = nativeModule.getClass();
        if (this.b.containsKey(name)) {
            Class cls2 = this.b.get(name);
            if (nativeModule.canOverrideExistingModule()) {
                this.f17282a.remove(cls2);
            } else {
                throw new IllegalStateException("Native module " + cls.getSimpleName() + " tried to override " + cls2.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
            }
        }
        this.b.put(name, cls);
        this.f17282a.put(cls, new ModuleHolder(nativeModule));
    }

    private ReactQueueConfigurationSpec a(String str) {
        MessageQueueThreadSpec messageQueueThreadSpec;
        if (Build.VERSION.SDK_INT < 21) {
            messageQueueThreadSpec = MessageQueueThreadSpec.newBackgroundThreadSpec(str, g);
        } else {
            messageQueueThreadSpec = MessageQueueThreadSpec.newBackgroundThreadSpec(str);
        }
        ReactQueueConfigurationSpec.Builder builder = ReactQueueConfigurationSpec.builder();
        return builder.setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec(str + "_js")).setNativeModulesQueueThreadSpec(messageQueueThreadSpec).build();
    }

    public boolean b() {
        return this.d != null && this.d.hasRunJSBundle() && !this.d.isDestroyed();
    }

    public void close() {
        CatalystInstanceImpl catalystInstanceImpl = this.d;
        if (catalystInstanceImpl != null) {
            if (catalystInstanceImpl.isDestroyed()) {
                this.d = null;
                return;
            }
            synchronized (this) {
                if (!catalystInstanceImpl.isDestroyed()) {
                    catalystInstanceImpl.destroy();
                }
            }
            this.d = null;
        }
    }

    public void a(String str, String str2, JSCallback jSCallback) {
        if (this.c == null || !b()) {
            jSCallback.b("jsc is not ready");
            return;
        }
        String add = this.c.add(jSCallback);
        try {
            ((JSCaller) this.d.getJSModule(JSCaller.class)).invoke(add, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!this.c.checkInvokedAndRemove(add)) {
                jSCallback.b(e2.getMessage());
            }
        }
    }
}
