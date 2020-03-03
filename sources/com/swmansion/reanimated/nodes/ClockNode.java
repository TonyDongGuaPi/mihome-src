package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class ClockNode extends Node implements NodesManager.OnAnimationFrame {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8919a;

    public ClockNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
    }

    public void b() {
        if (!this.f8919a) {
            this.f8919a = true;
            this.mNodesManager.a((NodesManager.OnAnimationFrame) this);
        }
    }

    public void c() {
        this.f8919a = false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Double evaluate() {
        return Double.valueOf(this.mNodesManager.f8898a);
    }

    public void a() {
        if (this.f8919a) {
            markUpdated();
            this.mNodesManager.a((NodesManager.OnAnimationFrame) this);
        }
    }
}
