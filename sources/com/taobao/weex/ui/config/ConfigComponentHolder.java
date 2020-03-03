package com.taobao.weex.ui.config;

import android.text.TextUtils;
import android.util.Pair;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ConfigComponentHolder implements IFComponentHolder {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String TAG = "WeexScanConfigRegister";
    private boolean mAppendTree;
    private Class mClass;
    private ClassLoader mClassLoader;
    private String mClassName;
    private Map<String, Invoker> mMethodInvokers;
    private Map<String, Invoker> mPropertyInvokers;
    private String mType;
    private String[] methods;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(831280943034208721L, "com/taobao/weex/ui/config/ConfigComponentHolder", 46);
        $jacocoData = a2;
        return a2;
    }

    public ConfigComponentHolder(String str, boolean z, String str2, String[] strArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mType = str;
        this.mAppendTree = z;
        this.mClassName = str2;
        this.methods = strArr;
        $jacocoInit[0] = true;
    }

    public void loadIfNonLazy() {
        $jacocoInit()[1] = true;
    }

    private synchronized boolean generate() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mClass == null) {
            $jacocoInit[2] = true;
            return false;
        }
        Pair<Map<String, Invoker>, Map<String, Invoker>> methods2 = SimpleComponentHolder.getMethods(this.mClass);
        this.mPropertyInvokers = (Map) methods2.first;
        this.mMethodInvokers = (Map) methods2.second;
        $jacocoInit[3] = true;
        return true;
    }

    public synchronized WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        WXComponent createInstance;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mClass == null) {
            $jacocoInit[4] = true;
        } else if (this.mClassLoader == wXSDKInstance.getContext().getClassLoader()) {
            $jacocoInit[5] = true;
            SimpleComponentHolder.ClazzComponentCreator clazzComponentCreator = new SimpleComponentHolder.ClazzComponentCreator(this.mClass);
            $jacocoInit[9] = true;
            createInstance = clazzComponentCreator.createInstance(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[10] = true;
            createInstance.bindHolder(this);
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[6] = true;
        }
        this.mClass = WXSDKManager.getInstance().getClassLoaderAdapter().getComponentClass(this.mType, this.mClassName, wXSDKInstance);
        $jacocoInit[7] = true;
        this.mClassLoader = wXSDKInstance.getContext().getClassLoader();
        $jacocoInit[8] = true;
        SimpleComponentHolder.ClazzComponentCreator clazzComponentCreator2 = new SimpleComponentHolder.ClazzComponentCreator(this.mClass);
        $jacocoInit[9] = true;
        createInstance = clazzComponentCreator2.createInstance(wXSDKInstance, wXVContainer, basicComponentData);
        $jacocoInit[10] = true;
        createInstance.bindHolder(this);
        $jacocoInit[11] = true;
        return createInstance;
    }

    public synchronized Invoker getPropertyInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPropertyInvokers != null) {
            $jacocoInit[12] = true;
        } else if (generate()) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            return null;
        }
        Invoker invoker = this.mPropertyInvokers.get(str);
        $jacocoInit[15] = true;
        return invoker;
    }

    public Invoker getMethodInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodInvokers != null) {
            $jacocoInit[16] = true;
        } else if (generate()) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            return null;
        }
        Invoker invoker = this.mMethodInvokers.get(str);
        $jacocoInit[19] = true;
        return invoker;
    }

    public String[] getMethods() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.methods == null) {
            String[] strArr = new String[0];
            $jacocoInit[20] = true;
            return strArr;
        }
        String[] strArr2 = this.methods;
        $jacocoInit[21] = true;
        return strArr2;
    }

    public static final ConfigComponentHolder fromConfig(JSONObject jSONObject) {
        JSONArray jSONArray;
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONObject != null) {
            $jacocoInit[22] = true;
            try {
                String string = jSONObject.getString("name");
                $jacocoInit[24] = true;
                boolean booleanValue = jSONObject.getBooleanValue("appendTree");
                $jacocoInit[25] = true;
                String string2 = jSONObject.getString("className");
                $jacocoInit[26] = true;
                if (!jSONObject.containsKey("methods")) {
                    $jacocoInit[27] = true;
                    jSONArray = null;
                } else {
                    $jacocoInit[28] = true;
                    jSONArray = jSONObject.getJSONArray("methods");
                    $jacocoInit[29] = true;
                }
                if (TextUtils.isEmpty(string)) {
                    $jacocoInit[30] = true;
                } else if (!TextUtils.isEmpty(string2)) {
                    $jacocoInit[31] = true;
                    String[] strArr = new String[0];
                    if (jSONArray == null) {
                        $jacocoInit[34] = true;
                    } else {
                        $jacocoInit[35] = true;
                        strArr = new String[jSONArray.size()];
                        $jacocoInit[36] = true;
                        jSONArray.toArray(strArr);
                        $jacocoInit[37] = true;
                    }
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[38] = true;
                    } else {
                        $jacocoInit[39] = true;
                        WXLogUtils.d("WeexScanConfigRegister", "resolve component " + string + " className " + string2 + " methods " + jSONArray);
                        $jacocoInit[40] = true;
                    }
                    ConfigComponentHolder configComponentHolder = new ConfigComponentHolder(string, booleanValue, string2, strArr);
                    $jacocoInit[41] = true;
                    return configComponentHolder;
                } else {
                    $jacocoInit[32] = true;
                }
                $jacocoInit[33] = true;
                return null;
            } catch (Exception e) {
                $jacocoInit[42] = true;
                WXLogUtils.e("WeexScanConfigRegister", (Throwable) e);
                $jacocoInit[43] = true;
                return null;
            }
        } else {
            $jacocoInit[23] = true;
            return null;
        }
    }

    public boolean isAppendTree() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mAppendTree;
        $jacocoInit[44] = true;
        return z;
    }

    public String getType() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mType;
        $jacocoInit[45] = true;
        return str;
    }
}
