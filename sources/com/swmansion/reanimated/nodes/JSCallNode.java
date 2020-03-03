package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.Utils;

public class JSCallNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f8926a;

    public JSCallNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8926a = Utils.a(readableMap.getArray("input"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Double evaluate() {
        WritableArray createArray = Arguments.createArray();
        for (int a2 : this.f8926a) {
            Node a3 = this.mNodesManager.a(a2, Node.class);
            if (a3.value() == null) {
                createArray.pushNull();
            } else {
                Object value = a3.value();
                if (value instanceof String) {
                    createArray.pushString((String) value);
                } else {
                    createArray.pushDouble(a3.doubleValue().doubleValue());
                }
            }
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.mNodeID);
        createMap.putArray("args", createArray);
        this.mNodesManager.a("onReanimatedCall", createMap);
        return ZERO;
    }
}
