package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class NoopNode extends ValueNode {
    public void a(Object obj) {
    }

    public void addChild(Node node) {
    }

    /* access modifiers changed from: protected */
    public void markUpdated() {
    }

    public void removeChild(Node node) {
    }

    public NoopNode(NodesManager nodesManager) {
        super(-2, (ReadableMap) null, nodesManager);
    }
}
