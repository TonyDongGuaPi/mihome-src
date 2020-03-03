package com.taobao.weex.dom.binding;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXStatement extends ArrayMap<String, Object> implements Cloneable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String WX_FOR = "[[repeat]]";
    public static final String WX_FOR_INDEX = "@index";
    public static final String WX_FOR_ITEM = "@alias";
    public static final String WX_FOR_LIST = "@expression";
    public static final String WX_IF = "[[match]]";
    public static final String WX_ONCE = "[[once]]";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-9128863202634621473L, "com/taobao/weex/dom/binding/WXStatement", 4);
        $jacocoData = a2;
        return a2;
    }

    public WXStatement() {
        $jacocoInit()[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXStatement(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public WXStatement clone() {
        boolean[] $jacocoInit = $jacocoInit();
        WXStatement wXStatement = new WXStatement(this);
        $jacocoInit[2] = true;
        return wXStatement;
    }
}
