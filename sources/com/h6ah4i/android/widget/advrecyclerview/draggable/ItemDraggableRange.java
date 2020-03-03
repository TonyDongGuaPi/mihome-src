package com.h6ah4i.android.widget.advrecyclerview.draggable;

import com.taobao.weex.el.parse.Operators;

public class ItemDraggableRange {

    /* renamed from: a  reason: collision with root package name */
    private final int f5704a;
    private final int b;

    /* access modifiers changed from: protected */
    public String c() {
        return "ItemDraggableRange";
    }

    public ItemDraggableRange(int i, int i2) {
        if (i <= i2) {
            this.f5704a = i;
            this.b = i2;
            return;
        }
        throw new IllegalArgumentException("end position (= " + i2 + ") is smaller than start position (=" + i + Operators.BRACKET_END_STR);
    }

    public int a() {
        return this.f5704a;
    }

    public int b() {
        return this.b;
    }

    public boolean a(int i) {
        return i >= this.f5704a && i <= this.b;
    }

    public String toString() {
        return c() + Operators.BLOCK_START_STR + "mStart=" + this.f5704a + ", mEnd=" + this.b + Operators.BLOCK_END;
    }
}
