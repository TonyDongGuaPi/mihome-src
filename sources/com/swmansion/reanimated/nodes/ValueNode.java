package com.swmansion.reanimated.nodes;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.swmansion.reanimated.NodesManager;

public class ValueNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private Object f8936a;

    public ValueNode(int i, @Nullable ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        if (readableMap == null || !readableMap.hasKey("value")) {
            this.f8936a = null;
            return;
        }
        ReadableType type = readableMap.getType("value");
        if (type == ReadableType.String) {
            this.f8936a = readableMap.getString("value");
        } else if (type == ReadableType.Number) {
            this.f8936a = Double.valueOf(readableMap.getDouble("value"));
        } else if (type == ReadableType.Null) {
            this.f8936a = null;
        } else {
            throw new IllegalStateException("Not supported value type. Must be boolean, number or string");
        }
    }

    public void a(Object obj) {
        this.f8936a = obj;
        forceUpdateMemoizedValue(this.f8936a);
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        return this.f8936a;
    }
}
