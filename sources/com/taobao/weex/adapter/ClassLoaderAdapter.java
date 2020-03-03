package com.taobao.weex.adapter;

import android.content.Context;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ClassLoaderAdapter {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3376211851352628335L, "com/taobao/weex/adapter/ClassLoaderAdapter", 7);
        $jacocoData = a2;
        return a2;
    }

    public ClassLoaderAdapter() {
        $jacocoInit()[0] = true;
    }

    public Class<? extends WXModule> getModuleClass(String str, String str2, Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Class<?> loadClass = context.getClassLoader().loadClass(str2);
            $jacocoInit[1] = true;
            return loadClass;
        } catch (ClassNotFoundException e) {
            $jacocoInit[2] = true;
            RuntimeException runtimeException = new RuntimeException(e);
            $jacocoInit[3] = true;
            throw runtimeException;
        }
    }

    public Class<? extends WXComponent> getComponentClass(String str, String str2, WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Class<?> loadClass = wXSDKInstance.getContext().getClassLoader().loadClass(str2);
            $jacocoInit[4] = true;
            return loadClass;
        } catch (ClassNotFoundException e) {
            $jacocoInit[5] = true;
            RuntimeException runtimeException = new RuntimeException(e);
            $jacocoInit[6] = true;
            throw runtimeException;
        }
    }
}
