package com.swmansion.reanimated.nodes;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.swmansion.reanimated.NodesManager;
import java.util.ArrayList;
import java.util.List;

public class TransformNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private List<TransformConfig> f8933a;

    private static abstract class TransformConfig {
        public String b;

        public abstract Object a(NodesManager nodesManager);

        private TransformConfig() {
        }
    }

    private static class AnimatedTransformConfig extends TransformConfig {

        /* renamed from: a  reason: collision with root package name */
        public int f8934a;

        private AnimatedTransformConfig() {
            super();
        }

        public Object a(NodesManager nodesManager) {
            return nodesManager.a(this.f8934a);
        }
    }

    private static class StaticTransformConfig extends TransformConfig {

        /* renamed from: a  reason: collision with root package name */
        public Object f8935a;

        private StaticTransformConfig() {
            super();
        }

        public Object a(NodesManager nodesManager) {
            return this.f8935a;
        }
    }

    private static List<TransformConfig> a(ReadableArray readableArray) {
        Object obj;
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            String string = map.getString(BindingXConstants.j);
            if (map.hasKey("nodeID")) {
                AnimatedTransformConfig animatedTransformConfig = new AnimatedTransformConfig();
                animatedTransformConfig.b = string;
                animatedTransformConfig.f8934a = map.getInt("nodeID");
                arrayList.add(animatedTransformConfig);
            } else {
                StaticTransformConfig staticTransformConfig = new StaticTransformConfig();
                staticTransformConfig.b = string;
                if (map.getType("value") == ReadableType.String) {
                    obj = map.getString("value");
                } else {
                    obj = Double.valueOf(map.getDouble("value"));
                }
                staticTransformConfig.f8935a = obj;
                arrayList.add(staticTransformConfig);
            }
        }
        return arrayList;
    }

    public TransformNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8933a = a(readableMap.getArray("transform"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public WritableArray evaluate() {
        ArrayList arrayList = new ArrayList(this.f8933a.size());
        for (TransformConfig next : this.f8933a) {
            arrayList.add(JavaOnlyMap.of(next.b, next.a(this.mNodesManager)));
        }
        return JavaOnlyArray.from(arrayList);
    }
}
