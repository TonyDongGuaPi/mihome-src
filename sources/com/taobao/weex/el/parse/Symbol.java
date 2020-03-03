package com.taobao.weex.el.parse;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Symbol {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public final String op;
    public final int pos;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2249961552617643295L, "com/taobao/weex/el/parse/Symbol", 1);
        $jacocoData = a2;
        return a2;
    }

    public Symbol(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.op = str;
        this.pos = i;
        $jacocoInit[0] = true;
    }
}
