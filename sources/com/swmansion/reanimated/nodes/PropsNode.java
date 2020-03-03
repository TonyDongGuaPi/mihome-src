package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.UIImplementation;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.Utils;
import java.util.Map;

public class PropsNode extends Node implements FinalNode {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Integer> f8929a;
    private final UIImplementation b;
    private int c = -1;
    private final JavaOnlyMap d;
    private final ReactStylesDiffMap e;

    private static void a(WritableMap writableMap, String str, Object obj) {
        if (obj == null) {
            writableMap.putNull(str);
        } else if (obj instanceof Double) {
            writableMap.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Integer) {
            writableMap.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Number) {
            writableMap.putDouble(str, ((Number) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof String) {
            writableMap.putString(str, (String) obj);
        } else if (obj instanceof WritableArray) {
            writableMap.putArray(str, (WritableArray) obj);
        } else if (obj instanceof WritableMap) {
            writableMap.putMap(str, (WritableMap) obj);
        } else {
            throw new IllegalStateException("Unknown type of animated value");
        }
    }

    public PropsNode(int i, ReadableMap readableMap, NodesManager nodesManager, UIImplementation uIImplementation) {
        super(i, readableMap, nodesManager);
        this.f8929a = Utils.a(readableMap.getMap("props"));
        this.b = uIImplementation;
        this.d = new JavaOnlyMap();
        this.e = new ReactStylesDiffMap(this.d);
    }

    public void a(int i) {
        this.c = i;
        dangerouslyRescheduleEvaluate();
    }

    public void b(int i) {
        this.c = -1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Double evaluate() {
        boolean z;
        WritableMap writableMap;
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        for (Map.Entry next : this.f8929a.entrySet()) {
            Node a2 = this.mNodesManager.a(((Integer) next.getValue()).intValue(), Node.class);
            if (a2 instanceof StyleNode) {
                WritableMap writableMap2 = (WritableMap) a2.value();
                ReadableMapKeySetIterator keySetIterator = writableMap2.keySetIterator();
                while (keySetIterator.hasNextKey()) {
                    String nextKey = keySetIterator.nextKey();
                    if (this.mNodesManager.c.contains(nextKey)) {
                        writableMap = this.d;
                        z = true;
                    } else if (this.mNodesManager.d.contains(nextKey)) {
                        z = z2;
                        z3 = true;
                        writableMap = createMap2;
                    } else {
                        z = z2;
                        z4 = true;
                        writableMap = createMap;
                    }
                    ReadableType type = writableMap2.getType(nextKey);
                    switch (type) {
                        case Number:
                            writableMap.putDouble(nextKey, writableMap2.getDouble(nextKey));
                            break;
                        case String:
                            writableMap.putString(nextKey, writableMap2.getString(nextKey));
                            break;
                        case Array:
                            writableMap.putArray(nextKey, (WritableArray) writableMap2.getArray(nextKey));
                            break;
                        default:
                            throw new IllegalArgumentException("Unexpected type " + type);
                    }
                    z2 = z;
                }
                continue;
            } else {
                String str = (String) next.getKey();
                Object value = a2.value();
                if (this.mNodesManager.c.contains(str)) {
                    a(this.d, str, value);
                    z2 = true;
                } else {
                    a(createMap2, str, value);
                    z3 = true;
                }
            }
        }
        if (this.c != -1) {
            if (z2) {
                this.b.synchronouslyUpdateViewOnUIThread(this.c, this.e);
            }
            if (z3) {
                this.mNodesManager.a(this.c, createMap2);
            }
            if (z4) {
                WritableMap createMap3 = Arguments.createMap();
                createMap3.putInt("viewTag", this.c);
                createMap3.putMap("props", createMap);
                this.mNodesManager.a("onReanimatedPropsChange", createMap3);
            }
        }
        return ZERO;
    }

    public void a() {
        if (this.c != -1) {
            value();
        }
    }
}
