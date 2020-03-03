package com.swmansion.reanimated.nodes;

import android.util.Log;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class DebugNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final String f8923a;
    private final int b;

    public DebugNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8923a = readableMap.getString("message");
        this.b = readableMap.getInt("value");
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        Object value = this.mNodesManager.a(this.b, Node.class).value();
        Log.d("REANIMATED", String.format("%s %s", new Object[]{this.f8923a, value}));
        return value;
    }
}
