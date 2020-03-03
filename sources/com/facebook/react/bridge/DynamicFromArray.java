package com.facebook.react.bridge;

import android.support.annotation.Nullable;
import android.support.v4.util.Pools;

public class DynamicFromArray implements Dynamic {
    private static final Pools.SimplePool<DynamicFromArray> sPool = new Pools.SimplePool<>(10);
    @Nullable
    private ReadableArray mArray;
    private int mIndex = -1;

    private DynamicFromArray() {
    }

    public static DynamicFromArray create(ReadableArray readableArray, int i) {
        DynamicFromArray acquire = sPool.acquire();
        if (acquire == null) {
            acquire = new DynamicFromArray();
        }
        acquire.mArray = readableArray;
        acquire.mIndex = i;
        return acquire;
    }

    public void recycle() {
        this.mArray = null;
        this.mIndex = -1;
        sPool.release(this);
    }

    public boolean isNull() {
        if (this.mArray != null) {
            return this.mArray.isNull(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public boolean asBoolean() {
        if (this.mArray != null) {
            return this.mArray.getBoolean(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public double asDouble() {
        if (this.mArray != null) {
            return this.mArray.getDouble(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public int asInt() {
        if (this.mArray != null) {
            return this.mArray.getInt(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public String asString() {
        if (this.mArray != null) {
            return this.mArray.getString(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableArray asArray() {
        if (this.mArray != null) {
            return this.mArray.getArray(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableMap asMap() {
        if (this.mArray != null) {
            return this.mArray.getMap(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableType getType() {
        if (this.mArray != null) {
            return this.mArray.getType(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }
}
