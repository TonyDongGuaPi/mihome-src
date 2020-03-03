package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;
import java.util.Stack;

public class ParamNode extends ValueNode {

    /* renamed from: a  reason: collision with root package name */
    private final Stack<Integer> f8928a = new Stack<>();
    private String b;

    public ParamNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
    }

    public void a(Object obj) {
        Node a2 = this.mNodesManager.a(this.f8928a.peek().intValue(), Node.class);
        String str = this.mUpdateContext.b;
        this.mUpdateContext.b = this.b;
        ((ValueNode) a2).a(obj);
        this.mUpdateContext.b = str;
    }

    public void a(Integer num, String str) {
        this.b = str;
        this.f8928a.push(num);
    }

    public void a() {
        this.f8928a.pop();
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        String str = this.mUpdateContext.b;
        this.mUpdateContext.b = this.b;
        Object value = this.mNodesManager.a(this.f8928a.peek().intValue(), Node.class).value();
        this.mUpdateContext.b = str;
        return value;
    }
}
