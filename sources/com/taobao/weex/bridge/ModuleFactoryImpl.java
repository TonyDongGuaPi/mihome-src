package com.taobao.weex.bridge;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ModuleFactoryImpl {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public boolean hasRigster = false;
    public ModuleFactory mFactory = null;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3470325910608548948L, "com/taobao/weex/bridge/ModuleFactoryImpl", 2);
        $jacocoData = a2;
        return a2;
    }

    public ModuleFactoryImpl(ModuleFactory moduleFactory) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mFactory = moduleFactory;
        $jacocoInit[0] = true;
    }

    public ModuleFactoryImpl(ModuleFactory moduleFactory, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mFactory = moduleFactory;
        this.hasRigster = z;
        $jacocoInit[1] = true;
    }
}
