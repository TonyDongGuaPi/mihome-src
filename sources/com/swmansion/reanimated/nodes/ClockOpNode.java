package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public abstract class ClockOpNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private int f8920a;

    /* access modifiers changed from: protected */
    public abstract Double a(ClockNode clockNode);

    public static class ClockStartNode extends ClockOpNode {
        /* access modifiers changed from: protected */
        public /* synthetic */ Object evaluate() {
            return ClockOpNode.super.evaluate();
        }

        public ClockStartNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
            super(i, readableMap, nodesManager);
        }

        /* access modifiers changed from: protected */
        public Double a(ClockNode clockNode) {
            clockNode.b();
            return ZERO;
        }
    }

    public static class ClockStopNode extends ClockOpNode {
        /* access modifiers changed from: protected */
        public /* synthetic */ Object evaluate() {
            return ClockOpNode.super.evaluate();
        }

        public ClockStopNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
            super(i, readableMap, nodesManager);
        }

        /* access modifiers changed from: protected */
        public Double a(ClockNode clockNode) {
            clockNode.c();
            return ZERO;
        }
    }

    public static class ClockTestNode extends ClockOpNode {
        /* access modifiers changed from: protected */
        public /* synthetic */ Object evaluate() {
            return ClockOpNode.super.evaluate();
        }

        public ClockTestNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
            super(i, readableMap, nodesManager);
        }

        /* access modifiers changed from: protected */
        public Double a(ClockNode clockNode) {
            return Double.valueOf(clockNode.f8919a ? 1.0d : 0.0d);
        }
    }

    public ClockOpNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8920a = readableMap.getInt("clock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Double evaluate() {
        return a((ClockNode) this.mNodesManager.a(this.f8920a, ClockNode.class));
    }
}
