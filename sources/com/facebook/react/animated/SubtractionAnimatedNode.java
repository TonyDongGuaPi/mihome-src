package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class SubtractionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public SubtractionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        for (int i = 0; i < this.mInputNodes.length; i++) {
            this.mInputNodes[i] = array.getInt(i);
        }
    }

    public void update() {
        for (int i = 0; i < this.mInputNodes.length; i++) {
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodes[i]);
            if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
                throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.subtract node");
            }
            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
            double value = valueAnimatedNode.getValue();
            if (i == 0) {
                this.mValue = value;
            } else {
                this.mValue -= valueAnimatedNode.getValue();
            }
        }
    }
}
