package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class CondNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final int f8922a;
    private final int b;
    private final int c;

    public CondNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8922a = readableMap.getInt("cond");
        int i2 = -1;
        this.b = readableMap.hasKey("ifBlock") ? readableMap.getInt("ifBlock") : -1;
        this.c = readableMap.hasKey("elseBlock") ? readableMap.getInt("elseBlock") : i2;
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        Object a2 = this.mNodesManager.a(this.f8922a);
        return (!(a2 instanceof Number) || ((Number) a2).doubleValue() == 0.0d) ? this.c != -1 ? this.mNodesManager.a(this.c) : ZERO : this.b != -1 ? this.mNodesManager.a(this.b) : ZERO;
    }
}
