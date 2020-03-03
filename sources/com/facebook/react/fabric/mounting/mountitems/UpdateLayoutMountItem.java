package com.facebook.react.fabric.mounting.mountitems;

import android.annotation.TargetApi;
import com.facebook.react.fabric.mounting.MountingManager;

public class UpdateLayoutMountItem implements MountItem {
    private final int mHeight;
    private final int mLayoutDirection;
    private final int mReactTag;
    private final int mWidth;
    private final int mX;
    private final int mY;

    public UpdateLayoutMountItem(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mReactTag = i;
        this.mX = i2;
        this.mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
        this.mLayoutDirection = convertLayoutDirection(i6);
    }

    @TargetApi(19)
    private int convertLayoutDirection(int i) {
        switch (i) {
            case 0:
                return 2;
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                throw new IllegalArgumentException("Unsupported layout direction: " + i);
        }
    }

    public void execute(MountingManager mountingManager) {
        mountingManager.updateLayout(this.mReactTag, this.mX, this.mY, this.mWidth, this.mHeight);
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getLayoutDirection() {
        return this.mLayoutDirection;
    }

    public String toString() {
        return "UpdateLayoutMountItem [" + this.mReactTag + "] - x: " + this.mX + " - y: " + this.mY + " - height: " + this.mHeight + " - width: " + this.mWidth + " - layoutDirection: " + this.mLayoutDirection;
    }
}
