package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;
import com.taobao.weex.el.parse.Operators;

public class DeleteMountItem implements MountItem {
    private int mReactTag;

    public DeleteMountItem(int i) {
        this.mReactTag = i;
    }

    public void execute(MountingManager mountingManager) {
        mountingManager.deleteView(this.mReactTag);
    }

    public String toString() {
        return "DeleteMountItem [" + this.mReactTag + Operators.ARRAY_END_STR;
    }
}
