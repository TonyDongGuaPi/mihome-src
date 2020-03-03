package com.swmansion.reanimated.nodes;

import com.brentvatne.react.ReactVideoView;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class SetNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private int f8931a;
    private int b;

    public SetNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8931a = readableMap.getInt(ReactVideoView.EVENT_PROP_WHAT);
        this.b = readableMap.getInt("value");
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        Object a2 = this.mNodesManager.a(this.b);
        ((ValueNode) this.mNodesManager.a(this.f8931a, ValueNode.class)).a(a2);
        return a2;
    }
}
