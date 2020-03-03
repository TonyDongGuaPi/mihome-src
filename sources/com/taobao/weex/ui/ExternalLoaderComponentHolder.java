package com.taobao.weex.ui;

import android.util.Pair;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ExternalLoaderComponentHolder implements IFComponentHolder {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String TAG = "SimpleComponentHolder";
    private Class mClass;
    private final IExternalComponentGetter mClzGetter;
    private Map<String, Invoker> mMethodInvokers;
    private Map<String, Invoker> mPropertyInvokers;
    private final String mType;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7332560660270650555L, "com/taobao/weex/ui/ExternalLoaderComponentHolder", 23);
        $jacocoData = a2;
        return a2;
    }

    public ExternalLoaderComponentHolder(String str, IExternalComponentGetter iExternalComponentGetter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mClzGetter = iExternalComponentGetter;
        this.mType = str;
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
        Pair<Map<String, Invoker>, Map<String, Invoker>> methods = SimpleComponentHolder.getMethods(this.mClass);
        this.mPropertyInvokers = (Map) methods.first;
        this.mMethodInvokers = (Map) methods.second;
        $jacocoInit[3] = true;
        return true;
    }

    public synchronized WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        WXComponent createInstance;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mClass != null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            this.mClass = this.mClzGetter.getExternalComponentClass(this.mType, wXSDKInstance);
            $jacocoInit[6] = true;
        }
        SimpleComponentHolder.ClazzComponentCreator clazzComponentCreator = new SimpleComponentHolder.ClazzComponentCreator(this.mClass);
        $jacocoInit[7] = true;
        createInstance = clazzComponentCreator.createInstance(wXSDKInstance, wXVContainer, basicComponentData);
        $jacocoInit[8] = true;
        createInstance.bindHolder(this);
        $jacocoInit[9] = true;
        return createInstance;
    }

    public synchronized Invoker getPropertyInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPropertyInvokers != null) {
            $jacocoInit[10] = true;
        } else if (generate()) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            return null;
        }
        Invoker invoker = this.mPropertyInvokers.get(str);
        $jacocoInit[13] = true;
        return invoker;
    }

    public Invoker getMethodInvoker(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodInvokers != null) {
            $jacocoInit[14] = true;
        } else if (generate()) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            return null;
        }
        Invoker invoker = this.mMethodInvokers.get(str);
        $jacocoInit[17] = true;
        return invoker;
    }

    public synchronized String[] getMethods() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mMethodInvokers != null) {
            $jacocoInit[18] = true;
        } else if (generate()) {
            $jacocoInit[19] = true;
        } else {
            String[] strArr = new String[0];
            $jacocoInit[20] = true;
            return strArr;
        }
        Set<String> keySet = this.mMethodInvokers.keySet();
        $jacocoInit[21] = true;
        String[] strArr2 = (String[]) keySet.toArray(new String[keySet.size()]);
        $jacocoInit[22] = true;
        return strArr2;
    }
}
