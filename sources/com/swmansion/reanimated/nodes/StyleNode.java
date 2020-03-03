package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.Utils;
import java.util.Map;

public class StyleNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Integer> f8932a;

    public StyleNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8932a = Utils.a(readableMap.getMap("style"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public WritableMap evaluate() {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        for (Map.Entry next : this.f8932a.entrySet()) {
            Node a2 = this.mNodesManager.a(((Integer) next.getValue()).intValue(), Node.class);
            if (a2 instanceof TransformNode) {
                javaOnlyMap.putArray((String) next.getKey(), (WritableArray) a2.value());
            } else {
                Object value = a2.value();
                if (value instanceof Double) {
                    javaOnlyMap.putDouble((String) next.getKey(), ((Double) value).doubleValue());
                } else if (value instanceof String) {
                    javaOnlyMap.putString((String) next.getKey(), (String) value);
                } else {
                    throw new IllegalStateException("Wrong style form");
                }
            }
        }
        return javaOnlyMap;
    }
}
