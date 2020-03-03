package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.Utils;

public class ConcatNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f8921a;

    public ConcatNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8921a = Utils.a(readableMap.getArray("input"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String evaluate() {
        StringBuilder sb = new StringBuilder();
        for (int a2 : this.f8921a) {
            sb.append(this.mNodesManager.a(a2, Node.class).value());
        }
        return sb.toString();
    }
}
