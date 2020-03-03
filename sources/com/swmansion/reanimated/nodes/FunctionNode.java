package com.swmansion.reanimated.nodes;

import com.brentvatne.react.ReactVideoView;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class FunctionNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final int f8925a;

    public FunctionNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8925a = readableMap.getInt(ReactVideoView.EVENT_PROP_WHAT);
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        return this.mNodesManager.a(this.f8925a, Node.class).value();
    }
}
