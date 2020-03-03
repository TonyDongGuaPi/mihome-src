package com.taobao.weex.ui;

import android.util.Pair;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.MethodInvoker;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SimpleComponentHolder implements IFComponentHolder {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String TAG = "SimpleComponentHolder";
    private final Class<? extends WXComponent> mClz;
    private ComponentCreator mCreator;
    private Map<String, Invoker> mMethodInvokers;
    private Map<String, Invoker> mPropertyInvokers;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5336444009389537624L, "com/taobao/weex/ui/SimpleComponentHolder", 57);
        $jacocoData = a2;
        return a2;
    }

    public static class ClazzComponentCreator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private final Class<? extends WXComponent> mCompClz;
        private Constructor<? extends WXComponent> mConstructor;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(4835737115875640601L, "com/taobao/weex/ui/SimpleComponentHolder$ClazzComponentCreator", 20);
            $jacocoData = a2;
            return a2;
        }

        public ClazzComponentCreator(Class<? extends WXComponent> cls) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mCompClz = cls;
            $jacocoInit[0] = true;
        }

        private void loadConstructor() {
            Constructor<? extends WXComponent> constructor;
            boolean[] $jacocoInit = $jacocoInit();
            Class<? extends WXComponent> cls = this.mCompClz;
            try {
                $jacocoInit[1] = true;
                constructor = cls.getConstructor(new Class[]{WXSDKInstance.class, WXVContainer.class, BasicComponentData.class});
                $jacocoInit[2] = true;
            } catch (NoSuchMethodException unused) {
                $jacocoInit[3] = true;
                WXLogUtils.d("ClazzComponentCreator", "Use deprecated component constructor");
                try {
                    $jacocoInit[4] = true;
                    Constructor<? extends WXComponent> constructor2 = cls.getConstructor(new Class[]{WXSDKInstance.class, WXVContainer.class, Boolean.TYPE, BasicComponentData.class});
                    $jacocoInit[5] = true;
                    constructor = constructor2;
                } catch (NoSuchMethodException unused2) {
                    try {
                        $jacocoInit[6] = true;
                        constructor = cls.getConstructor(new Class[]{WXSDKInstance.class, WXVContainer.class, String.class, Boolean.TYPE, BasicComponentData.class});
                        $jacocoInit[7] = true;
                    } catch (NoSuchMethodException unused3) {
                        $jacocoInit[8] = true;
                        WXRuntimeException wXRuntimeException = new WXRuntimeException("Can't find constructor of component.");
                        $jacocoInit[9] = true;
                        throw wXRuntimeException;
                    }
                }
            }
            this.mConstructor = constructor;
            $jacocoInit[10] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            WXComponent wXComponent;
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mConstructor != null) {
                $jacocoInit[11] = true;
            } else {
                $jacocoInit[12] = true;
                loadConstructor();
                $jacocoInit[13] = true;
            }
            int length = this.mConstructor.getParameterTypes().length;
            if (length == 3) {
                $jacocoInit[14] = true;
                wXComponent = (WXComponent) this.mConstructor.newInstance(new Object[]{wXSDKInstance, wXVContainer, basicComponentData});
                $jacocoInit[15] = true;
            } else if (length == 4) {
                $jacocoInit[16] = true;
                wXComponent = (WXComponent) this.mConstructor.newInstance(new Object[]{wXSDKInstance, wXVContainer, false, basicComponentData});
                $jacocoInit[17] = true;
            } else {
                wXComponent = (WXComponent) this.mConstructor.newInstance(new Object[]{wXSDKInstance, wXVContainer, wXSDKInstance.getInstanceId(), Boolean.valueOf(wXVContainer.isLazy())});
                $jacocoInit[18] = true;
            }
            $jacocoInit[19] = true;
            return wXComponent;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleComponentHolder(Class<? extends WXComponent> cls) {
        this(cls, new ClazzComponentCreator(cls));
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public SimpleComponentHolder(Class<? extends WXComponent> cls, ComponentCreator componentCreator) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mClz = cls;
        this.mCreator = componentCreator;
        $jacocoInit[1] = true;
    }

    public void loadIfNonLazy() {
        boolean[] $jacocoInit = $jacocoInit();
        Annotation[] declaredAnnotations = this.mClz.getDeclaredAnnotations();
        int length = declaredAnnotations.length;
        $jacocoInit[2] = true;
        int i = 0;
        while (i < length) {
            Annotation annotation = declaredAnnotations[i];
            if (annotation instanceof Component) {
                $jacocoInit[3] = true;
                if (((Component) annotation).lazyload()) {
                    $jacocoInit[4] = true;
                } else if (this.mMethodInvokers != null) {
                    $jacocoInit[5] = true;
                } else {
                    $jacocoInit[6] = true;
                    generate();
                    $jacocoInit[7] = true;
                }
                $jacocoInit[8] = true;
                return;
            }
            i++;
            $jacocoInit[9] = true;
        }
        $jacocoInit[10] = true;
    }

    private synchronized void generate() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            WXLogUtils.d("SimpleComponentHolder", "Generate Component:" + this.mClz.getSimpleName());
            $jacocoInit[13] = true;
        }
        Pair<Map<String, Invoker>, Map<String, Invoker>> methods = getMethods(this.mClz);
        this.mPropertyInvokers = (Map) methods.first;
        this.mMethodInvokers = (Map) methods.second;
        $jacocoInit[14] = true;
    }

    public static Pair<Map<String, Invoker>, Map<String, Invoker>> getMethods(Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[15] = true;
        HashMap hashMap2 = new HashMap();
        try {
            $jacocoInit[16] = true;
            Method[] methods = cls.getMethods();
            int length = methods.length;
            $jacocoInit[17] = true;
            int i = 0;
            while (i < length) {
                Method method = methods[i];
                try {
                    $jacocoInit[18] = true;
                    Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                    int length2 = declaredAnnotations.length;
                    $jacocoInit[19] = true;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            $jacocoInit[20] = true;
                            break;
                        }
                        Annotation annotation = declaredAnnotations[i2];
                        if (annotation == null) {
                            $jacocoInit[21] = true;
                        } else if (annotation instanceof WXComponentProp) {
                            $jacocoInit[22] = true;
                            String name = ((WXComponentProp) annotation).name();
                            $jacocoInit[23] = true;
                            hashMap.put(name, new MethodInvoker(method, true));
                            $jacocoInit[24] = true;
                            break;
                        } else if (!(annotation instanceof JSMethod)) {
                            $jacocoInit[25] = true;
                        } else {
                            JSMethod jSMethod = (JSMethod) annotation;
                            $jacocoInit[26] = true;
                            String alias = jSMethod.alias();
                            $jacocoInit[27] = true;
                            if (!JSMethod.NOT_SET.equals(alias)) {
                                $jacocoInit[28] = true;
                            } else {
                                $jacocoInit[29] = true;
                                alias = method.getName();
                                $jacocoInit[30] = true;
                            }
                            hashMap2.put(alias, new MethodInvoker(method, jSMethod.uiThread()));
                            $jacocoInit[31] = true;
                        }
                        i2++;
                        $jacocoInit[32] = true;
                    }
                    $jacocoInit[33] = true;
                } catch (ArrayIndexOutOfBoundsException | IncompatibleClassChangeError unused) {
                    $jacocoInit[34] = true;
                }
                i++;
                $jacocoInit[35] = true;
            }
            $jacocoInit[36] = true;
        } catch (IndexOutOfBoundsException e) {
            $jacocoInit[37] = true;
            e.printStackTrace();
            $jacocoInit[38] = true;
        } catch (Exception e2) {
            $jacocoInit[39] = true;
            WXLogUtils.e("SimpleComponentHolder", (Throwable) e2);
            $jacocoInit[40] = true;
        }
        Pair<Map<String, Invoker>, Map<String, Invoker>> pair = new Pair<>(hashMap, hashMap2);
        $jacocoInit[41] = true;
        return pair;
    }

    public synchronized WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        WXComponent createInstance;
        boolean[] $jacocoInit = $jacocoInit();
        createInstance = this.mCreator.createInstance(wXSDKInstance, wXVContainer, basicComponentData);
        $jacocoInit[42] = true;
        createInstance.bindHolder(this);
        $jacocoInit[43] = true;
        return createInstance;
    }

    public synchronized Invoker getPropertyInvoker(String str) {
        Invoker invoker;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPropertyInvokers != null) {
            $jacocoInit[44] = true;
        } else {
            $jacocoInit[45] = true;
            generate();
            $jacocoInit[46] = true;
        }
        invoker = this.mPropertyInvokers.get(str);
        $jacocoInit[47] = true;
        return invoker;
    }

    public Invoker getMethodInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodInvokers != null) {
            $jacocoInit[48] = true;
        } else {
            $jacocoInit[49] = true;
            generate();
            $jacocoInit[50] = true;
        }
        Invoker invoker = this.mMethodInvokers.get(str);
        $jacocoInit[51] = true;
        return invoker;
    }

    public synchronized String[] getMethods() {
        String[] strArr;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodInvokers != null) {
            $jacocoInit[52] = true;
        } else {
            $jacocoInit[53] = true;
            generate();
            $jacocoInit[54] = true;
        }
        Set<String> keySet = this.mMethodInvokers.keySet();
        $jacocoInit[55] = true;
        strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        $jacocoInit[56] = true;
        return strArr;
    }
}
