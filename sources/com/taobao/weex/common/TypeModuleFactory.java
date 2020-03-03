package com.taobao.weex.common;

import com.taobao.weex.WXEnvironment;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.MethodInvoker;
import com.taobao.weex.bridge.ModuleFactory;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TypeModuleFactory<T extends WXModule> implements ModuleFactory<T> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String TAG = "TypeModuleFactory";
    Class<T> mClazz;
    Map<String, Invoker> mMethodMap;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3691112754961099057L, "com/taobao/weex/common/TypeModuleFactory", 34);
        $jacocoData = a2;
        return a2;
    }

    public TypeModuleFactory(Class<T> cls) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mClazz = cls;
        $jacocoInit[0] = true;
    }

    private void generateMethodMap() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            WXLogUtils.d(TAG, "extractMethodNames:" + this.mClazz.getSimpleName());
            $jacocoInit[3] = true;
        }
        HashMap hashMap = new HashMap();
        try {
            $jacocoInit[4] = true;
            Method[] methods = this.mClazz.getMethods();
            int length = methods.length;
            $jacocoInit[5] = true;
            int i = 0;
            while (i < length) {
                Method method = methods[i];
                $jacocoInit[6] = true;
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                int length2 = declaredAnnotations.length;
                $jacocoInit[7] = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        $jacocoInit[8] = true;
                        break;
                    }
                    Annotation annotation = declaredAnnotations[i2];
                    if (annotation != null) {
                        if (!(annotation instanceof JSMethod)) {
                            if (annotation instanceof WXModuleAnno) {
                                $jacocoInit[16] = true;
                                hashMap.put(method.getName(), new MethodInvoker(method, ((WXModuleAnno) annotation).runOnUIThread()));
                                $jacocoInit[17] = true;
                                break;
                            }
                            $jacocoInit[15] = true;
                        } else {
                            JSMethod jSMethod = (JSMethod) annotation;
                            $jacocoInit[10] = true;
                            if (JSMethod.NOT_SET.equals(jSMethod.alias())) {
                                str = method.getName();
                                $jacocoInit[11] = true;
                            } else {
                                str = jSMethod.alias();
                                $jacocoInit[12] = true;
                            }
                            $jacocoInit[13] = true;
                            hashMap.put(str, new MethodInvoker(method, jSMethod.uiThread()));
                            $jacocoInit[14] = true;
                        }
                    } else {
                        $jacocoInit[9] = true;
                    }
                    i2++;
                    $jacocoInit[18] = true;
                }
                i++;
                $jacocoInit[19] = true;
            }
            $jacocoInit[20] = true;
        } catch (Throwable th) {
            $jacocoInit[21] = true;
            WXLogUtils.e("[WXModuleManager] extractMethodNames:", th);
            $jacocoInit[22] = true;
        }
        this.mMethodMap = hashMap;
        $jacocoInit[23] = true;
    }

    public T buildInstance() throws IllegalAccessException, InstantiationException {
        boolean[] $jacocoInit = $jacocoInit();
        T t = (WXModule) this.mClazz.newInstance();
        $jacocoInit[24] = true;
        return t;
    }

    public String[] getMethods() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodMap != null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            generateMethodMap();
            $jacocoInit[27] = true;
        }
        Set<String> keySet = this.mMethodMap.keySet();
        $jacocoInit[28] = true;
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        $jacocoInit[29] = true;
        return strArr;
    }

    public Invoker getMethodInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodMap != null) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            generateMethodMap();
            $jacocoInit[32] = true;
        }
        Invoker invoker = this.mMethodMap.get(str);
        $jacocoInit[33] = true;
        return invoker;
    }
}
