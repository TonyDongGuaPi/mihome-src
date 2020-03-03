package com.taobao.weex.bridge;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Stack;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXHashMap<K, V> extends HashMap<K, V> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final long serialVersionUID = 4294272345728974369L;
    private Stack<String> instancesStack = new Stack<>();
    private String mTag;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5668060080705978755L, "com/taobao/weex/bridge/WXHashMap", 24);
        $jacocoData = a2;
        return a2;
    }

    public WXHashMap() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public V put(K k, V v) {
        boolean[] $jacocoInit = $jacocoInit();
        if (k == null) {
            $jacocoInit[2] = true;
        } else if (k.toString() == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            if (!this.instancesStack.contains(k)) {
                $jacocoInit[5] = true;
            } else {
                $jacocoInit[6] = true;
                this.instancesStack.remove(k);
                $jacocoInit[7] = true;
            }
            this.instancesStack.push(k.toString());
            $jacocoInit[8] = true;
        }
        V put = super.put(k, v);
        $jacocoInit[9] = true;
        return put;
    }

    public V remove(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        V remove = super.remove(obj);
        $jacocoInit[10] = true;
        return remove;
    }

    public V removeFromMapAndStack(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        this.instancesStack.remove(obj);
        $jacocoInit[11] = true;
        V remove = super.remove(obj);
        $jacocoInit[12] = true;
        return remove;
    }

    public String getStackTopInstanceId() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.instancesStack.isEmpty()) {
            str = "";
            $jacocoInit[13] = true;
        } else {
            str = this.instancesStack.pop();
            $jacocoInit[14] = true;
        }
        $jacocoInit[15] = true;
        return str;
    }

    public void setStackTopInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            this.instancesStack.remove(str);
            $jacocoInit[18] = true;
            this.instancesStack.push(str);
            $jacocoInit[19] = true;
        }
        $jacocoInit[20] = true;
    }

    public Stack<String> getInstanceStack() {
        boolean[] $jacocoInit = $jacocoInit();
        Stack<String> stack = this.instancesStack;
        $jacocoInit[21] = true;
        return stack;
    }

    public String getTag() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mTag;
        $jacocoInit[22] = true;
        return str;
    }

    public void setTag(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTag = str;
        $jacocoInit[23] = true;
    }
}
