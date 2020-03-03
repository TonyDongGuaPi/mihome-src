package com.swmansion.reanimated.nodes;

import com.brentvatne.react.ReactVideoView;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.UpdateContext;
import com.swmansion.reanimated.Utils;
import com.xiaomi.smarthome.fastvideo.IOUtils;

public class CallFuncNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private String f8918a;
    private final int b;
    private final int[] c;
    private final int[] d;

    public CallFuncNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.b = readableMap.getInt(ReactVideoView.EVENT_PROP_WHAT);
        this.d = Utils.a(readableMap.getArray("params"));
        this.c = Utils.a(readableMap.getArray("args"));
    }

    private void a() {
        this.f8918a = this.mNodesManager.b.b;
        UpdateContext updateContext = this.mNodesManager.b;
        updateContext.b = this.mNodesManager.b.b + IOUtils.f15883a + String.valueOf(this.mNodeID);
        for (int i = 0; i < this.d.length; i++) {
            ((ParamNode) this.mNodesManager.a(this.d[i], ParamNode.class)).a(Integer.valueOf(this.c[i]), this.f8918a);
        }
    }

    private void b() {
        for (int a2 : this.d) {
            ((ParamNode) this.mNodesManager.a(a2, ParamNode.class)).a();
        }
        this.mNodesManager.b.b = this.f8918a;
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        a();
        Object value = this.mNodesManager.a(this.b, Node.class).value();
        b();
        return value;
    }
}
