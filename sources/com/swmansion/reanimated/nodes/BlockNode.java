package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.Utils;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;

public class BlockNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f8917a;

    public BlockNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8917a = Utils.a(readableMap.getArray(MiotApmTask.j));
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        Object obj = null;
        for (int a2 : this.f8917a) {
            obj = this.mNodesManager.a(a2, Node.class).value();
        }
        return obj;
    }
}
