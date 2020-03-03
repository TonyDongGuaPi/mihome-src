package com.swmansion.reanimated.nodes;

import com.brentvatne.react.ReactVideoView;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class AlwaysNode extends Node implements FinalNode {

    /* renamed from: a  reason: collision with root package name */
    private int f8914a;

    public AlwaysNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8914a = readableMap.getInt(ReactVideoView.EVENT_PROP_WHAT);
    }

    public void a() {
        value();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Double evaluate() {
        this.mNodesManager.a(this.f8914a, Node.class).value();
        return ZERO;
    }
}
