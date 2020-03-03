package com.taobao.weex.ui.config;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.MethodInvoker;
import com.taobao.weex.bridge.ModuleFactory;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ConfigModuleFactory<T extends WXModule> implements ModuleFactory<T> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String TAG = "WeexScanConfigRegister";
    private ClassLoader mClassLoader;
    private String mClassName;
    private Class<T> mClazz;
    private Map<String, Invoker> mMethodMap;
    private String mName;
    private String[] methods;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5739268125672868045L, "com/taobao/weex/ui/config/ConfigModuleFactory", 59);
        $jacocoData = a2;
        return a2;
    }

    public ConfigModuleFactory(String str, String str2, String[] strArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mName = str;
        this.mClassName = str2;
        this.methods = strArr;
        $jacocoInit[0] = true;
    }

    public String[] getMethods() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.methods == null) {
            String[] strArr = new String[0];
            $jacocoInit[1] = true;
            return strArr;
        }
        String[] strArr2 = this.methods;
        $jacocoInit[2] = true;
        return strArr2;
    }

    public Invoker getMethodInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodMap != null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            generateMethodMap();
            $jacocoInit[5] = true;
        }
        Invoker invoker = this.mMethodMap.get(str);
        $jacocoInit[6] = true;
        return invoker;
    }

    public T buildInstance() throws IllegalAccessException, InstantiationException {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mClazz != null) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            this.mClazz = WXSDKManager.getInstance().getClassLoaderAdapter().getModuleClass(this.mName, this.mClassName, WXEnvironment.getApplication().getApplicationContext());
            $jacocoInit[9] = true;
        }
        T t = (WXModule) this.mClazz.newInstance();
        $jacocoInit[10] = true;
        return t;
    }

    public T buildInstance(WXSDKInstance wXSDKInstance) throws IllegalAccessException, InstantiationException {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXSDKInstance == null) {
            $jacocoInit[11] = true;
            T buildInstance = buildInstance();
            $jacocoInit[12] = true;
            return buildInstance;
        }
        if (this.mClazz == null) {
            $jacocoInit[13] = true;
        } else if (this.mClassLoader == wXSDKInstance.getContext().getClassLoader()) {
            $jacocoInit[14] = true;
            T t = (WXModule) this.mClazz.newInstance();
            $jacocoInit[18] = true;
            return t;
        } else {
            $jacocoInit[15] = true;
        }
        this.mClazz = WXSDKManager.getInstance().getClassLoaderAdapter().getModuleClass(this.mName, this.mClassName, wXSDKInstance.getContext());
        $jacocoInit[16] = true;
        this.mClassLoader = wXSDKInstance.getContext().getClassLoader();
        $jacocoInit[17] = true;
        T t2 = (WXModule) this.mClazz.newInstance();
        $jacocoInit[18] = true;
        return t2;
    }

    private void generateMethodMap() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            WXLogUtils.d("WeexScanConfigRegister", "extractMethodNames:" + this.mClazz.getSimpleName());
            $jacocoInit[21] = true;
        }
        HashMap hashMap = new HashMap();
        try {
            $jacocoInit[22] = true;
            Method[] methods2 = this.mClazz.getMethods();
            int length = methods2.length;
            $jacocoInit[23] = true;
            int i = 0;
            while (i < length) {
                Method method = methods2[i];
                $jacocoInit[24] = true;
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                int length2 = declaredAnnotations.length;
                $jacocoInit[25] = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        $jacocoInit[26] = true;
                        break;
                    }
                    Annotation annotation = declaredAnnotations[i2];
                    if (annotation != null) {
                        if (!(annotation instanceof JSMethod)) {
                            if (annotation instanceof WXModuleAnno) {
                                $jacocoInit[34] = true;
                                hashMap.put(method.getName(), new MethodInvoker(method, ((WXModuleAnno) annotation).runOnUIThread()));
                                $jacocoInit[35] = true;
                                break;
                            }
                            $jacocoInit[33] = true;
                        } else {
                            JSMethod jSMethod = (JSMethod) annotation;
                            $jacocoInit[28] = true;
                            if (JSMethod.NOT_SET.equals(jSMethod.alias())) {
                                str = method.getName();
                                $jacocoInit[29] = true;
                            } else {
                                str = jSMethod.alias();
                                $jacocoInit[30] = true;
                            }
                            $jacocoInit[31] = true;
                            hashMap.put(str, new MethodInvoker(method, jSMethod.uiThread()));
                            $jacocoInit[32] = true;
                        }
                    } else {
                        $jacocoInit[27] = true;
                    }
                    i2++;
                    $jacocoInit[36] = true;
                }
                i++;
                $jacocoInit[37] = true;
            }
            $jacocoInit[38] = true;
        } catch (Throwable th) {
            $jacocoInit[39] = true;
            WXLogUtils.e("[WXModuleManager] extractMethodNames:", th);
            $jacocoInit[40] = true;
        }
        this.mMethodMap = hashMap;
        $jacocoInit[41] = true;
    }

    public static ConfigModuleFactory fromConfig(JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONObject != null) {
            try {
                $jacocoInit[42] = true;
                String string = jSONObject.getString("name");
                $jacocoInit[44] = true;
                String string2 = jSONObject.getString("className");
                $jacocoInit[45] = true;
                JSONArray jSONArray = jSONObject.getJSONArray("methods");
                $jacocoInit[46] = true;
                if (TextUtils.isEmpty(string)) {
                    $jacocoInit[47] = true;
                } else if (!TextUtils.isEmpty(string2)) {
                    $jacocoInit[48] = true;
                    String[] strArr = new String[jSONArray.size()];
                    $jacocoInit[51] = true;
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[52] = true;
                    } else {
                        $jacocoInit[53] = true;
                        WXLogUtils.d("WeexScanConfigRegister", " resolve module " + string + " className " + string2 + " methods " + jSONArray);
                        $jacocoInit[54] = true;
                    }
                    ConfigModuleFactory configModuleFactory = new ConfigModuleFactory(string, string2, (String[]) jSONArray.toArray(strArr));
                    $jacocoInit[55] = true;
                    return configModuleFactory;
                } else {
                    $jacocoInit[49] = true;
                }
                $jacocoInit[50] = true;
                return null;
            } catch (Exception e) {
                $jacocoInit[56] = true;
                WXLogUtils.e("WeexScanConfigRegister", (Throwable) e);
                $jacocoInit[57] = true;
                return null;
            }
        } else {
            $jacocoInit[43] = true;
            return null;
        }
    }

    public String getName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mName;
        $jacocoInit[58] = true;
        return str;
    }
}
