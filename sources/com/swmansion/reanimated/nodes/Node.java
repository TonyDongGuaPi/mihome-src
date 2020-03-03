package com.swmansion.reanimated.nodes;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.UpdateContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public abstract class Node {
    public static final Double ONE = Double.valueOf(1.0d);
    public static final Double ZERO = Double.valueOf(0.0d);
    @Nullable
    private List<Node> mChildren;
    private final Map<String, Long> mLastLoopID = new HashMap();
    private final Map<String, Object> mMemoizedValue = new HashMap();
    protected final int mNodeID;
    protected final NodesManager mNodesManager;
    protected final UpdateContext mUpdateContext;

    /* access modifiers changed from: protected */
    @Nullable
    public abstract Object evaluate();

    public Node(int i, @Nullable ReadableMap readableMap, NodesManager nodesManager) {
        this.mLastLoopID.put("", -1L);
        this.mNodeID = i;
        this.mNodesManager = nodesManager;
        this.mUpdateContext = nodesManager.b;
    }

    @Nullable
    public final Object value() {
        if (this.mLastLoopID.containsKey(this.mUpdateContext.b) && this.mLastLoopID.get(this.mUpdateContext.b).longValue() >= this.mUpdateContext.f8913a) {
            return this.mMemoizedValue.get(this.mUpdateContext.b);
        }
        this.mLastLoopID.put(this.mUpdateContext.b, Long.valueOf(this.mUpdateContext.f8913a));
        Object evaluate = evaluate();
        this.mMemoizedValue.put(this.mUpdateContext.b, evaluate);
        return evaluate;
    }

    public final Double doubleValue() {
        Object value = value();
        if (value == null) {
            return ZERO;
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Number) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue() ? ONE : ZERO;
        }
        throw new IllegalStateException("Value of node " + this + " cannot be cast to a number");
    }

    public void addChild(Node node) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList();
        }
        this.mChildren.add(node);
        node.dangerouslyRescheduleEvaluate();
    }

    public void removeChild(Node node) {
        if (this.mChildren != null) {
            this.mChildren.remove(node);
        }
    }

    /* access modifiers changed from: protected */
    public void markUpdated() {
        UiThreadUtil.assertOnUiThread();
        this.mUpdateContext.c.add(this);
        this.mNodesManager.c();
    }

    /* access modifiers changed from: protected */
    public final void dangerouslyRescheduleEvaluate() {
        this.mLastLoopID.put(this.mUpdateContext.b, -1L);
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public final void forceUpdateMemoizedValue(Object obj) {
        this.mMemoizedValue.put(this.mUpdateContext.b, obj);
        markUpdated();
    }

    private static void findAndUpdateNodes(Node node, Set<Node> set, Stack<FinalNode> stack) {
        if (!set.contains(node)) {
            set.add(node);
            List<Node> list = node.mChildren;
            if (list != null) {
                for (Node findAndUpdateNodes : list) {
                    findAndUpdateNodes(findAndUpdateNodes, set, stack);
                }
            }
            if (node instanceof FinalNode) {
                stack.push((FinalNode) node);
            }
        }
    }

    public static void runUpdates(UpdateContext updateContext) {
        UiThreadUtil.assertOnUiThread();
        ArrayList<Node> arrayList = updateContext.c;
        HashSet hashSet = new HashSet();
        Stack stack = new Stack();
        for (int i = 0; i < arrayList.size(); i++) {
            findAndUpdateNodes(arrayList.get(i), hashSet, stack);
            if (i == arrayList.size() - 1) {
                while (!stack.isEmpty()) {
                    ((FinalNode) stack.pop()).a();
                }
            }
        }
        arrayList.clear();
        updateContext.f8913a++;
    }
}
